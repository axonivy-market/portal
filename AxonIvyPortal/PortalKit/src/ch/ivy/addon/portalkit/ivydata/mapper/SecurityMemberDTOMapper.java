package ch.ivy.addon.portalkit.ivydata.mapper;

import java.util.List;

import com.axonivy.portal.component.dto.RoleDTO;
import com.axonivy.portal.component.dto.UserDTO;

import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.util.ListUtilities;

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
  
  public static List<SecurityMemberDTO> mapFromUserDTOs(List<UserDTO> userDTOs) {
    return ListUtilities.transformList(userDTOs, user -> mapFromUserDTO(user));
  }
  
  public static List<SecurityMemberDTO> mapFromRoleDTOs(List<RoleDTO> roleDTOs) {
    return ListUtilities.transformList(roleDTOs, role -> mapFromRoleDTO(role));
  }
}
