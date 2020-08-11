package ch.ivy.addon.portalkit.util;

import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;

public class SecurityMemberUtils {
  
  public static SecurityMemberDTO getCurrentSessionUserAsSecurityMemberDTO() {
    return IvyExecutor.executeAsSystem(() -> {
      return new SecurityMemberDTO(Ivy.session().getSessionUser());
    });
  }
  
  /**
   * Finds the security members by query in current application
   * @param query
   * @param startIndex index of the first record is 0
   * @param count use -1 to return all beginning from the startIndex
   * @return user list
   */
  @SuppressWarnings("unchecked")
  public static List<SecurityMemberDTO> findSecurityMembers(String query, int startIndex, int count) {
    return IvyExecutor.executeAsSystem(() -> {
      return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE)
          .withStartName("findSecurityMembers")
          .withParam("query", query)
          .withParam("startIndex", startIndex)
          .withParam("count", count)
          .call()
          .get("members", List.class);
    });
  }
  
  public static List<SecurityMemberDTO> convertIRoleToSecurityMemberDTO(List<IRole> roles) {
    return IvyExecutor.executeAsSystem(() -> {
      return roles.stream().map(role -> new SecurityMemberDTO(role)).collect(Collectors.toList());
    });
  }
  
  public static ISecurityMember findISecurityMemberFromUserDTO(UserDTO userDTO) {
    return UserUtils.findUserByUserId(userDTO.getId());
  }
  
  public static ISecurityMember findISecurityMemberFromRoleDTO(RoleDTO roleDTO) {
    return IvyExecutor.executeAsSystem(() -> {
        return Ivy.wf().getSecurityContext().findRole(roleDTO.getId());
    });
  }
}
