package PFE.Project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    Integer id;

    String email;
    String password;

    String name;

    String company;

    int phone;

}
