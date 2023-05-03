package PFE.Project.models;

import PFE.Project.enumerate.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    String status;
    String subject;
    int priority;
    String date;
    boolean archive;
    String description;
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @ElementCollection
    List<String> images;
    @JoinColumn
    @JsonManagedReference
    @ManyToOne()
    User user;
    @JsonIgnore
    @OneToMany(mappedBy = "reclamation")
    List<Comment> comments;

}
