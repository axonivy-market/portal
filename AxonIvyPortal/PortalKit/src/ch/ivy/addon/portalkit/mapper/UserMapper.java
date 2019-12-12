package ch.ivy.addon.portalkit.mapper;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivyteam.ivy.security.IUser;

public class UserMapper {
  public static UserDTO convertIUserToUserDTO(IUser user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setDisplayName(user.getDisplayName());
    userDTO.setUsername(user.getName());
    return userDTO;
  }
}
