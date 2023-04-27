package PFE.Project.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ResetPasswordRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    Long code;
    @ManyToOne()
    @JoinColumn(name = "user_id",nullable = false)
    User user;


}
