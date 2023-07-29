package co.com.auth_back.auth_back.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class AccessHours extends GeneralModel {

    @Column(nullable = false)
    private LocalTime starHour;

    @Column(nullable = false)
    private LocalTime endHour;

    @Column(nullable = false)
    private String rol;
}
