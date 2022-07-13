package ch.ivy.addon.portalkit.ivydata.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.component.dto.RoleDTO;
import com.axonivy.portal.component.dto.UserDTO;

import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;

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
    return map(userDTOs, user -> mapFromUserDTO(user));
  }
  
  public static List<SecurityMemberDTO> mapFromRoleDTOs(List<RoleDTO> roleDTOs) {
    return map(roleDTOs, role -> mapFromRoleDTO(role));
  }
  
  private static <T, R> List<R> map(List<T> list, Function<T, R> function){
    return CollectionUtils.emptyIfNull(list)
        .stream()
        .map(function)
        .collect(Collectors.toList());
  }
}
