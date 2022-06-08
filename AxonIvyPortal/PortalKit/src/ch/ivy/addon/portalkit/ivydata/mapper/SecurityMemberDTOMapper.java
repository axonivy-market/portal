package ch.ivy.addon.portalkit.ivydata.mapper;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;

public class SecurityMemberDTOMapper {
  
  public static SecurityMemberDTO mapFromUserDTO(UserDTO userDTO) {
    SecurityMemberDTO result = new SecurityMemberDTO();
    
    result.setId(userDTO.getId());
    result.setDisplayName(userDTO.getDisplayName());
    result.setMemberName(userDTO.getMemberName());
    result.setName(userDTO.getName());
    result.setEMailAddress(userDTO.getEmail());
    result.setUser(true);
    result.setEnabled(userDTO.isEnabled());
    
    return result;
  }
  
  public static List<SecurityMemberDTO> mapFromUserDTOs(List<UserDTO> userDTOs) {
    List<SecurityMemberDTO> result = new ArrayList<>();

    for (UserDTO m : userDTOs) {
      SecurityMemberDTO member = mapFromUserDTO(m);

      if (null != member) {
        result.add(member);
      }
    }

    return result;
  }
  
  public static SecurityMemberDTO mapFromRoleDTO(RoleDTO roleDTO) {
    SecurityMemberDTO result = new SecurityMemberDTO();
    
    result.setId(roleDTO.getId());
    result.setDisplayName(roleDTO.getDisplayName());
    result.setMemberName(roleDTO.getMemberName());
    result.setName(roleDTO.getName());
    result.setUser(false);
    result.setEnabled(true);
    
    return result;
  }
  
  public static List<SecurityMemberDTO> mapFromRoleDTOs(List<RoleDTO> roleDTOs) {
    List<SecurityMemberDTO> result = new ArrayList<>();

    for (RoleDTO m : roleDTOs) {
      SecurityMemberDTO member = mapFromRoleDTO(m);

      if (null != member) {
        result.add(member);
      }
    }

    return result;
  }
}
