package co.com.auth_back.auth_back.controller;

import co.com.auth_back.auth_back.constants.MessageConstants;
import co.com.auth_back.auth_back.constants.StatusConstants;
import co.com.auth_back.auth_back.dto.PasswordChangeDTO;
import co.com.auth_back.auth_back.dto.RegisterUserDTO;
import co.com.auth_back.auth_back.models.AccessHours;
import co.com.auth_back.auth_back.models.Attempts;
import co.com.auth_back.auth_back.models.Credential;
import co.com.auth_back.auth_back.models.User;
import co.com.auth_back.auth_back.service.AccessHoursService;
import co.com.auth_back.auth_back.service.AttemptsService;
import co.com.auth_back.auth_back.service.CredentialService;
import co.com.auth_back.auth_back.service.UserService;
import co.com.auth_back.auth_back.utils.EmailUtil;
import co.com.auth_back.auth_back.utils.EncryptUtil;
import co.com.auth_back.auth_back.utils.GeneratorId;
import co.com.auth_back.auth_back.utils.TokenUtil;
import com.auth0.jwt.interfaces.Claim;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Transactional
public class CredentialController extends GeneralController<Credential> {
    private final CredentialService credentialService;
    private final UserService userService;
    private final AccessHoursService accessHoursService;
    private final AttemptsService attemptsService;

    public CredentialController(CredentialService credentialService, UserService userService, AccessHoursService accessHoursService, AttemptsService attemptsService) {
        super(credentialService);
        this.credentialService = credentialService;
        this.userService = userService;
        this.accessHoursService = accessHoursService;
        this.attemptsService = attemptsService;
    }

    @Override
    @Hidden
    public long getAllCount() throws Exception {
        throw new Exception(MessageConstants.NOT_IMPLEMENTED_ROUTE);
    }

    @Override
    public List<Credential> getAll(int pageNumber, int pageSize) throws Exception {
        throw new Exception(MessageConstants.NOT_IMPLEMENTED_ROUTE);
    }

    @Override
    public List<Credential> getAllByFilters(Credential credential, int pageNumber, int pageSize) throws Exception {
        throw new Exception("No implemente");
    }

    @Override
    public long countAllByFilters(Credential credential) throws Exception {
        throw new Exception("No implementado");
    }

    @Override
    public Credential getById(String id) throws Exception {
        throw new Exception(MessageConstants.NOT_IMPLEMENTED_ROUTE);
    }

    @Override
    public Credential create(Credential credential) throws Exception {
        throw new Exception(MessageConstants.NOT_IMPLEMENTED_ROUTE);
    }

    @Override
    public Credential update(Credential credential) throws Exception {
        Credential credentialEncryted = credentialWithEncrytedPassword(credential);
        return  credentialService.save(credentialEncryted);
    }

    private Credential credentialWithEncrytedPassword(Credential credential) {
        String hashedPassword = EncryptUtil.encryptValue(credential.getPassword());
        credential.setPassword(hashedPassword);
        return credential;
    }

    private Credential credentialWithEncryptedCode(Credential credential) {
        String hashedCode = EncryptUtil.encryptValue(credential.getCode());
        credential.setCode(hashedCode);
        return credential;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Credential credential) throws Exception {
        Map<String, String> response = new HashMap<>();
        Optional<Credential> credentialFound = credentialService.findByUserAndMail(credential.getMail());

        if (credentialFound.isEmpty()) {
            throw new Exception(StatusConstants.UNAUTHORIZED);
        }

        Attempts attempts = attemptsService.findByCredential(credentialFound.get());

        if (attempts != null && attempts.getNAttempt() == 3){
            throw new Exception(StatusConstants.UNAUTHORIZED);
        }

        User user = userService.getByCredential(credentialFound.get());
        AccessHours accessHours = accessHoursService.getAccessHoursByRol(String.valueOf(user.getRole()));

        LocalTime actualHour = LocalTime.now();

        if ( actualHour.isAfter(accessHours.getEndHour()) || actualHour.isBefore(accessHours.getStarHour()) ) {
            throw new Exception(StatusConstants.UNAUTHORIZED);
        }

        boolean state = EncryptUtil.checkValues(credential.getPassword(), credentialFound.get().getPassword());
        if (!state) {
            if(attempts == null){
                attempts = new Attempts();
                attempts.setCredential(credentialFound.get());
                attempts.setDateTimeAttempt(LocalDateTime.now());
                attempts.setNAttempt(1);
            } else{
                attempts.setNAttempt(attempts.getNAttempt()+1);
            }
            attemptsService.save(attempts);
            response.put("MessageError", "Estado Fallido");
            return response;
        }

        if (user.getActive().equals("N")){
            throw new Exception(StatusConstants.UNAUTHORIZED);
        }

        Map<String, String> userData = new HashMap<>();
        userData.put("id", user.getId());
        userData.put("role", String.valueOf(user.getRole()));

        String token = TokenUtil.generateToken(userData);

        response.put(StatusConstants.STATUS, StatusConstants.AUTHORIZED);
        response.put("token", token);

        return response;
    }

    @GetMapping("/validate")
    public boolean validateUserName(@RequestParam("credential") String credential) {
        return credentialService.findByUserAndMail(credential).isPresent();
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody RegisterUserDTO registerUserDTO) throws Exception {
        Map<String, String> response = new HashMap<>();

        String code = GeneratorId.generateUUID(6);
        registerUserDTO.getCredential().setCode(code);

        Credential encrytedCredential = credentialWithEncryptedCode(registerUserDTO.getCredential());
        Credential createdCredential = credentialService.save(encrytedCredential);

        User user = registerUserDTO.getUser();
        user.setCredential(createdCredential);

        String temporalToken = EmailUtil.sendPasswordChangeMail(user, createdCredential.getMail(), code);

        userService.save(user);

        response.put("message", MessageConstants.SUCCESS_MESSAGE);
        response.put("temporalToken", temporalToken);

        return response;
    }

    @PostMapping("/registerUser")
    public Map<String, String> registerUser(@RequestBody User user) throws Exception {
        Map<String, String> response = new HashMap<>();

        String code = GeneratorId.generateUUID(6);
        user.getCredential().setCode(code);

        Credential encrytedCredential = credentialWithEncryptedCode(user.getCredential());
        Credential createdCredential = credentialService.save(encrytedCredential);

        user.setCredential(createdCredential);

        EmailUtil.sendPasswordChangeMail(user, createdCredential.getMail(), code);

        userService.save(user);

        response.put("message", MessageConstants.SUCCESS_MESSAGE);

        return response;
    }

    @GetMapping("/recoverPassword")
    public Map<String, String> recoverPassword(@RequestParam("mail") String mail) throws Exception {
        Map<String, String> response = new HashMap<>();

        Optional<Credential> credentialFound = credentialService.findByUserAndMail(mail);

        if (credentialFound.isEmpty()) {
            throw new Exception("Mail not found");
        }

        Credential credential = credentialFound.get();

        String code = GeneratorId.generateUUID(6);
        credential.setCode(code);

        Credential encrytedCredencial = credentialWithEncryptedCode(credential);
        credentialService.save(encrytedCredencial);

        User userFound = userService.getByCredential(credential);

        EmailUtil.sendPasswordChangeMail(userFound, mail, code, true);

        response.put("message", "Email send successfully");

        return response;
    }

    @PostMapping("/enableUser")
    public Map<String, String> enableUser(@RequestBody PasswordChangeDTO passwordChangeDTO) throws Exception {
        Map<String, String> response = new HashMap<>();

        Map<String, Claim> payload;
        payload = TokenUtil.validateToken(passwordChangeDTO.getToken());
        String id = payload.get("id").asString();
        User userFind = userService.findById(id);
        boolean isValid = EncryptUtil.checkValues(passwordChangeDTO.getCode(), userFind.getCredential().getCode());

        if (!isValid) {
            throw new Exception(MessageConstants.FAILED_MESSAGE);
        }

        userFind.getCredential().setPassword(passwordChangeDTO.getPassword());
        userFind.getCredential().setCode(null);

        Credential encrytedCredential = credentialWithEncrytedPassword(userFind.getCredential());
        userFind.setCredential(encrytedCredential);
        userFind.setActive("S");

        userService.save(userFind);

        response.put("message", "valid");

        return response;
    }

    @DeleteMapping("/disableUser")
    public Map<String, String> deleteUser(@RequestParam("id") String id) throws Exception {
        User findUser = userService.findById(id);
        findUser.setActive("N");
        userService.save(findUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", MessageConstants.SUCCESS_MESSAGE);

        return response;
    }

    @Override
    public Map<String, String> delete(String id) throws Exception {
        throw new Exception("No implementado");
    }
}
