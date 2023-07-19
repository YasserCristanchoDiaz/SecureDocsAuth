package co.com.auth_back.auth_back.dto;

import co.com.auth_back.auth_back.models.Credential;
import co.com.auth_back.auth_back.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
    private Credential credential;
    private User user;
}
