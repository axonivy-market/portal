package ch.ivy.addon.portalkit.util;

import java.util.List;
import java.util.stream.Collectors;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.dto.UserDTO;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.exec.Sudo;

public class SecurityMemberUtils {

  public static SecurityMemberDTO getCurrentSessionUserAsSecurityMemberDTO() {
    return Sudo.get(() -> {
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
    return Sudo.get(() -> {
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
    return Sudo.get(() -> {
      return roles.stream().map(role -> new SecurityMemberDTO(role)).collect(Collectors.toList());
    });
  }

  public static ISecurityMember findISecurityMemberFromUserDTO(UserDTO userDTO) {
    return Sudo.get(() -> {
      return ISecurityContext.current().users().findById(userDTO.getSecurityMemberId());
    });
  }

  public static ISecurityMember findISecurityMemberFromRoleDTO(RoleDTO roleDTO) {
    return Sudo.get(() -> {
      return ISecurityContext.current().roles().findById(roleDTO.getSecurityMemberId());
    });
  }

}
