package PFE.Project.dto_convertor;

import PFE.Project.dto.UserDto;
import PFE.Project.models.User;

import org.springframework.stereotype.Component;

@Component
public class UserConvertor {

  public static UserDto userToDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setType(user.getType());
    userDto.setFull_name(user.getFull_name());
    userDto.setEmail(user.getEmail());
    userDto.setPhone_number(user.getPhone_number());
    userDto.setRole_id(user.getRole().getId());
    return userDto;
  }



}
