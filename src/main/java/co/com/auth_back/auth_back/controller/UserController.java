package co.com.auth_back.auth_back.controller;

import co.com.auth_back.auth_back.constants.MessageConstants;
import co.com.auth_back.auth_back.models.User;
import co.com.auth_back.auth_back.service.CredentialService;
import co.com.auth_back.auth_back.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Transactional
public class UserController extends GeneralController<User> {

    private final UserService userService;
    private final CredentialService credentialService;

    @Autowired
    public UserController(UserService userService, CredentialService credentialService) {
        super(userService);
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @Override
    public Map<String, String> delete(String id) throws Exception {
        User findUser = userService.findById(id);
        String idCredential = credentialService.findById(findUser.getCredential().getId()).getId();

        userService.delete(findUser.getId());
        credentialService.delete(idCredential);

        Map<String, String> response = new HashMap<>();
        response.put("message", MessageConstants.SUCCESS_MESSAGE);

        return response;
    }

}
