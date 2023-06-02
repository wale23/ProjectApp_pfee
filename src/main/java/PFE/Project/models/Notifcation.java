package PFE.Project.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Notifcation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    LocalDateTime date;
    String notification;
    String type;
    @ManyToOne()
    Reclamation reclamation;
    @ManyToOne()
    User sender;
    @ManyToOne()
    User receiver;
}
