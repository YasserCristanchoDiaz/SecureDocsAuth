package co.com.auth_back.auth_back.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Credential extends GeneralModel{

    @Column(nullable = false,length = 60, unique = true)
    private String mail;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,length = 12,unique = true)
    private String userName;

    @Override
    public String toString() {
        return "Credential{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
