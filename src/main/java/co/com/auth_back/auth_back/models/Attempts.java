package co.com.auth_back.auth_back.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Attempts extends GeneralModel{

    @Column(nullable = false)
    private int nAttempt;

    @OneToOne
    @JoinColumn(name="credential_id")
    @JsonIgnore
    private Credential credential;

    @Column(nullable = false)
    private LocalDateTime dateTimeAttempt;

}
