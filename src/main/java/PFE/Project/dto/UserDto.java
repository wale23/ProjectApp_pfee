package PFE.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
private Integer id ;
private String full_name;
private String type;
private String phone_number;
private String email;
private String company;
private String token;
private Long role_id;
}
