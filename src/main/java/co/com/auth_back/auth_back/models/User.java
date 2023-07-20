package co.com.auth_back.auth_back.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class User extends GeneralModel{

    @Column(nullable = false,length = 64)
    private String name;
    @Column(nullable = false,length = 64)
    private String lastName;

    @Column(nullable = false, length =64)
    private String phone;
    @Column(nullable = false,length = 1)
    private char role;

    @Column(nullable = false, length = 1)
    private String active;
    @OneToOne
    @JoinColumn(name="credential_id")
    @JsonIgnore
    private Credential credential;

    @OneToMany(mappedBy = "user")
    private List<Document>documents;


    public User(User user) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
        this.active=active;
        this.credential = credential;
        this.documents = documents;
    }
}
