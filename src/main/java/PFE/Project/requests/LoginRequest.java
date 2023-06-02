package PFE.Project.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class LoginRequest {

  private final String email;
  private final String type;
  private final String password;
  private final String token;

}
