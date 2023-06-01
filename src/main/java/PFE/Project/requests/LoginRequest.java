package PFE.Project.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {

  private final String email;
  private final String type;
  private final String password;
  private final String token;

}
