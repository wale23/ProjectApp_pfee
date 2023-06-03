package PFE.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
  Integer id;
  String notification;
  LocalDateTime date;
  UserDto sender;
  UserDto receiver;
  String type;
  ReclamationDto reclamation;
}
