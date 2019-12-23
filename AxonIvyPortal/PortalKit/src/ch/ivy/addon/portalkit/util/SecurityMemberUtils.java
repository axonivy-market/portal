package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.process.call.SubProcessCallResult;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.server.ServerFactory;

public class SecurityMemberUtils {
  
  public static SecurityMemberDTO getCurrentSessionUserAsSecurityMemberDTO() {
    return IvyExecutor.executeAsSystem(() -> {
      return new SecurityMemberDTO(Ivy.session().getSessionUser());
    });
  }
  
  @SuppressWarnings("unchecked")
  public static List<SecurityMemberDTO> findAllSecurityMembers() {
    List<SecurityMemberDTO> responsibles = new ArrayList<>();
    try {
      SubProcessCallResult result = ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        if (Ivy.request().getApplication().getName().equals(PortalConstants.PORTAL_APPLICATION_NAME)) {
          return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE).withStartName("findSecurityMembersOverAllApplications")
              .call(Ivy.session().getSessionUserName());
        }
        return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE).withStartName("findSecurityMembers")
            .call(Ivy.request().getApplication());
      }); 
      responsibles = result.get("members", List.class);
    } catch (Exception e) {
      Ivy.log().error("Can't get list of security members", e);
    }
    return responsibles;
  }
  
  /**
   * Filter list of security member by name based on provided query
   * 
   * @param securityMembers security members need to be filtered
   * @param query provided query
   * @return Filtered list of security member
   */
  public static List<SecurityMemberDTO> filterSecurityMembers(List<SecurityMemberDTO> securityMembers, String query) {
    if (StringUtils.isEmpty(query)) {
      return securityMembers;
    }

    return IvyExecutor.executeAsSystem(() -> {
      List<SecurityMemberDTO> result = new ArrayList<>();
      for (SecurityMemberDTO securityMember : securityMembers) {
        if (StringUtils.containsIgnoreCase(securityMember.getDisplayName(), query)
            || StringUtils.containsIgnoreCase(securityMember.getName(), query)) {
          result.add(securityMember);
        }
      }
  
      return result;
    });
  }
  
  public static List<SecurityMemberDTO> convertIRoleToSecurityMemberDTO(List<IRole> roles) {
    return IvyExecutor.executeAsSystem(() -> {
      return roles.stream().map(role -> new SecurityMemberDTO(role)).collect(Collectors.toList());
    });
  }
  
  public static ISecurityMember findISecurityMemberFromDTO(SecurityMemberDTO securityMemberDTO) {
    return IvyExecutor.executeAsSystem(() -> {
      if(securityMemberDTO.isUser()) {
        return Ivy.wf().getSecurityContext().findUser(securityMemberDTO.getId());
      }
      else {
        return Ivy.wf().getSecurityContext().findRole(securityMemberDTO.getId());
      }
    });
  }
  
  public static ISecurityMember findISecurityMemberFromUserDTO(UserDTO userDTO) {
    return IvyExecutor.executeAsSystem(() -> {
        return Ivy.wf().getSecurityContext().findUser(userDTO.getId());
    });
  }
  
  public static ISecurityMember findISecurityMemberFromRoleDTO(RoleDTO roleDTO) {
    return IvyExecutor.executeAsSystem(() -> {
        return Ivy.wf().getSecurityContext().findRole(roleDTO.getId());
    });
  }
}
