package co.com.auth_back.auth_back.controller;

import co.com.auth_back.auth_back.models.User;
import co.com.auth_back.auth_back.service.GeneralService;
import co.com.auth_back.auth_back.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Transactional
public class UserController extends GeneralController<User> {

    private final UserService userService;
    public UserController(UserService userService) {
        super(userService);
        this.userService=userService;
    }
}
