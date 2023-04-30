package PFE.Project.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CommentRequest {
    String comment;
    Integer user_id;
    Integer reclamation_id;

}
