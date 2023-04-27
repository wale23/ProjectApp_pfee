package PFE.Project.responses;

import PFE.Project.dto.UserDto;
import PFE.Project.enumerate.Results;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  private UserDto user;
  private Results authenticationResult;
}
