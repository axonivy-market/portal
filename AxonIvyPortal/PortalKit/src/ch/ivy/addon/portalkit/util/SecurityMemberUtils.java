package ch.ivy.addon.portalkit.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.query.IPagedResult;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;

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
        return ((ISecurityContext)Ivy.security()).findRole(roleDTO.getId());
    });
  }

  public static String buildTooltipFromUsers(String roleName) {
    return IvyExecutor.executeAsSystem(() -> {
      IRole role = Ivy.security().roles().find(roleName);
      IPagedResult<IUser> result = role.users().assignedPaged(10);
      List<IUser> users = result.page(1);
      long totalCount = result.count();

      String fullnameOfRole = SecurityMemberDisplayNameUtils.generateFullDisplayNameForRole(role.getDisplayName(), role.getName());
      String header = cms("userOfRoleTitle", Arrays.asList(fullnameOfRole)).concat(":");
      StringBuilder usersBuilder = new StringBuilder();
      if (CollectionUtils.isEmpty(users)) {
        String noUsers = cms("noUser");
        usersBuilder.append(cms("roleMemberLineFormat", Arrays.asList(noUsers)));
        return cms("roleMembersTooltipFormat", Arrays.asList(header, "no-user", usersBuilder));
      }

      for (IUser user : users) {
        String fullnameOfUser = SecurityMemberDisplayNameUtils.generateFullDisplayNameForUser(user, user.getName());
        usersBuilder.append(cms("roleMemberItemFormat", Arrays.asList(fullnameOfUser)));
      }
      if (totalCount > 10) {
        usersBuilder.append(cms("roleMemberLineFormat",
            Arrays.asList(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/more").concat(" ..."))));
      }
      return cms("roleMembersTooltipFormat", Arrays.asList(header, "", usersBuilder));
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

}
