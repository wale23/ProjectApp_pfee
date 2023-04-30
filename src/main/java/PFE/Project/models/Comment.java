package PFE.Project.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
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
