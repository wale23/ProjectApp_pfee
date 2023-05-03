package PFE.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReclamationDto {
     Integer id;
     String description;
     String subject;
     String status;
     Integer priority;
     UserDto user;
     String date;
     boolean archived;
     List<String> images;

}
