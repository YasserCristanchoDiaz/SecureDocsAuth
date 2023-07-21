package co.com.auth_back.auth_back.models;

import co.com.auth_back.auth_back.utils.GeneratorId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class GeneralModel {
    @Id
    @Column(length = 36)
    private String id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public GeneralModel() {
        this.id = GeneratorId.generateUUID();
    }



}
