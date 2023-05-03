package PFE.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    String date;
    Integer id;
     UserDto user;
     String comment;
     ReclamationDto reclamation;

}
