package co.com.auth_back.auth_back.service;

import co.com.auth_back.auth_back.models.Credential;
import co.com.auth_back.auth_back.models.User;
import co.com.auth_back.auth_back.repositories.GeneralRepository;
import co.com.auth_back.auth_back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService extends GeneralService<User>{
    private final UserRepository userRepository;

    @Autowired
    public UserService( UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public User getByCredential(Credential credential){
        return userRepository.findByCredential(credential);
    }

}

