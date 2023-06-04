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
    String priority;
    String departement;
    String date;
    boolean archive;
    String description;
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @ElementCollection
    List<String> images;
    @JoinColumn
    @JsonManagedReference
    @ManyToOne()
    User sender;
    @ManyToOne()
    User receiver;
    @JsonIgnore
    @OneToMany(mappedBy = "reclamation", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> comments;
    @OneToMany(mappedBy = "reclamation", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Notifcation> notifcations;

}
