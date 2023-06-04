package PFE.Project.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.api.client.util.DateTime;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String date;
    private  String comment;
    @JoinColumn
    @JsonManagedReference
    @ManyToOne
    Reclamation reclamation;
    @JoinColumn
    @JsonManagedReference
    @ManyToOne
    User user;

}
