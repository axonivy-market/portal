package ch.ivy.addon.portalkit.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;

public class SecurityMemberUtils {
  
  public static SecurityMemberDTO getCurrentSessionUserAsSecurityMemberDTO() {
    return IvyExecutor.executeAsSystem(() -> {
      return new SecurityMemberDTO(Ivy.session().getSessionUser());
    });
  }

  /**
   * Finds security members by query. If current application is Portal, find all users over all applications, otherwise in current application
   * @param query
   * @param startIndex index of the first record is 0
   * @param count use -1 to return all beginning from startIndex
   * @return security member list
   */
  @SuppressWarnings("unchecked")
  public static List<SecurityMemberDTO> findSecurityMembers(String query, int startIndex, int count) {
    return IvyExecutor.executeAsSystem(() -> {
      if (Ivy.request().getApplication().getName().equals(PortalConstants.PORTAL_APPLICATION_NAME)) {
        return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE)
            .withStartName("findSecurityMembersOverAllApplications")
            .withParam("username", Ivy.session().getSessionUserName())
            .withParam("query", query)
            .withParam("startIndex", startIndex)
            .withParam("count", count)
            .call()
            .get("members", List.class);
      }
      
      return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE)
          .withStartName("findSecurityMembers")
          .withParam("application", Ivy.request().getApplication())
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

  public static ISecurityMember findISecurityMemberFromUserDTO(UserDTO userDTO, IApplication application) {
    return IvyExecutor.executeAsSystem(() -> {
      return application.getSecurityContext().findUser(userDTO.getId());
    });
  }
  
  public static ISecurityMember findISecurityMemberFromRoleDTO(RoleDTO roleDTO) {
    return IvyExecutor.executeAsSystem(() -> {
        return Ivy.wf().getSecurityContext().findRole(roleDTO.getId());
    });
  }

  public static ISecurityMember findISecurityMemberFromRoleDTO(RoleDTO roleDTO, IApplication application) {
    return IvyExecutor.executeAsSystem(() -> {
      return application.getSecurityContext().findRole(roleDTO.getId());
    });
  }

  private static String cms(String url, List<Object> params) {
    String securityMemberCms = "/ch.ivy.addon.portalkit.ui.jsf/components/SecurityMemberDisplayName/".concat(url);
    return Ivy.cms().co(securityMemberCms, params);
  }

  private static String cms(String url) {
    String securityMemberCms = "/ch.ivy.addon.portalkit.ui.jsf/components/SecurityMemberDisplayName/".concat(url);
    return Ivy.cms().co(securityMemberCms);
  }

  public static String buildTooltipFromUsers(IRole role, List<IUser> users, long totalCount) {
    String fullnameOfRole = formatWithTip(role.getDisplayName(), role.getName());
    String header = cms("userOfRoleTitle", Arrays.asList(fullnameOfRole)).concat(":");
    StringBuilder usersBuilder = new StringBuilder();
    if (CollectionUtils.isEmpty(users)) {
      String noUsers = cms("noUser");
      usersBuilder.append(cms("roleMemberLineFormat", Arrays.asList(noUsers)));
      return cms("roleMembersTooltipFormat", Arrays.asList(header, "no-user", usersBuilder));
    }

    for (IUser user : users) {
      String fullnameOfUser = formatWithTip(user.getDisplayName(), user.getName());
      usersBuilder.append(cms("roleMemberItemFormat", Arrays.asList(fullnameOfUser)));
    }
    if (totalCount > 10) {
      usersBuilder.append(cms("roleMemberLineFormat",
          Arrays.asList(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/more").concat(" ..."))));
    }
    return cms("roleMembersTooltipFormat", Arrays.asList(header, "", usersBuilder));
  }
  
  private static String formatWithTip(String fullName, String username) {
    if (StringUtils.isBlank(username)) {
      return StringUtils.EMPTY;
    }

    String formattedUsername = username.startsWith("#") ? username.substring(1) : username;
    if (StringUtils.isBlank(fullName)) {
      return "<" + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/noName") + ">" + " (" + formattedUsername + ")";
    }
    return fullName + " (" + formattedUsername + ")";
  }
}
