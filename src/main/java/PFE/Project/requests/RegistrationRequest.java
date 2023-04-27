package PFE.Project.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
private final String full_name;
private final String company;
private final String phone_number;
private final String email;
private final String password;
private final Long role_id;
}
