package PFE.Project.models;

import PFE.Project.enumerate.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    Status status;
    String subject;
    int priorirty;
    Date date;
    String description;
    @ElementCollection
    List<String> images;
    @JoinColumn
    @ManyToOne()
    User user;
}
