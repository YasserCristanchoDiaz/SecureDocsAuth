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
public class Document extends GeneralModel{

    @Column(nullable = false,length = 64)
    private String documentName;

    @Column(nullable = false,length = 64)
    private String type;

    @ManyToOne
    @JoinColumn(name="Creator_id")
    private User user;

    public Document(String documentName, String type, User user) {
        this.documentName = documentName;
        this.type = type;
        this.user = user;
    }
}
