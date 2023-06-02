package PFE.Project.dto;

import com.google.api.client.util.DateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
     LocalDateTime date;
     Integer id;
     UserDto user;
     String comment;


}
