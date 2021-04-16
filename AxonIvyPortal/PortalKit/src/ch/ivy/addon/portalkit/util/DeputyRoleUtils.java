package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.dto.DeputyRole;
import ch.ivy.addon.portalkit.dto.DeputyRoleType;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;

public class DeputyRoleUtils {

  private DeputyRoleUtils() {}

  public static List<DeputyRole> getDeputyRolesFromSubstitutes(List<IvySubstitute> ivySubstitutes) {
    List<DeputyRole> deputyRoles = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(ivySubstitutes)) {
      Map<String, DeputyRole> deputyRoleMap = new HashMap<>();
      String deputyRoleKey = "";
      String personalAssignedTaskKey = DeputyRoleType.PERSONAL_ASSIGNED_TASK.toString();
      for (IvySubstitute ivySubstitute : ivySubstitutes) {
        IRole substitutionRole = ivySubstitute.getSubstitionRole();
        DeputyRoleType deputyRoleType = null;
        if (substitutionRole != null) {
          deputyRoleKey = String.valueOf(substitutionRole.getId());
          deputyRoleType = DeputyRoleType.TASK_FOR_ROLE;
        } else {
          deputyRoleKey = personalAssignedTaskKey;
          deputyRoleType = DeputyRoleType.PERSONAL_ASSIGNED_TASK;
        }

        DeputyRole deputyRole = deputyRoleMap.get(deputyRoleKey);
        if (deputyRole == null) {
          deputyRole = initDeputyRole(ivySubstitute, substitutionRole, deputyRoleType);
          deputyRoleMap.put(deputyRoleKey, deputyRole);
        }

        UserDTO substituteUser = ivySubstitute.getSubstituteUser();
        ISecurityMember assignee = substituteUser != null ? Ivy.session().getSecurityContext().findSecurityMember(substituteUser.getMemberName()) : null;
        if (assignee != null) {
          deputyRole.addDeputy(assignee);
        }
      }

      deputyRoles.addAll(deputyRoleMap.values());
    }
    return deputyRoles;
  }

  private static DeputyRole initDeputyRole(IvySubstitute ivySubstitute, IRole substitutionRole,
      DeputyRoleType deputyRoleType) {
    DeputyRole deputyRole = new DeputyRole();
    deputyRole.setDeputyRoleType(deputyRoleType);
    deputyRole.setSubstitutionRole(substitutionRole);
    deputyRole.setSubstitutionType(ivySubstitute.getSubstitutionType());
    deputyRole.setDescription(ivySubstitute.getDescription());
    deputyRole.setOwnerUser(ivySubstitute.getOwnerUser());
    return deputyRole;
  }

  public static List<IvySubstitute> getSubstitutesFromDeputyRoles(List<DeputyRole> deputyRoles, IApplication application) {
    List<IvySubstitute> ivySubstitutes = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(deputyRoles)) {
      for (DeputyRole deputyRole : deputyRoles) {
        ivySubstitutes.addAll(getSubstitutesFromDeputyRole(deputyRole, application));
      }
    }
    return ivySubstitutes;
  }

  public static List<IvySubstitute> getSubstitutesFromDeputyRole(DeputyRole deputyRole, IApplication application) {
    List<IvySubstitute> ivySubstitutes = new ArrayList<>();
    if (deputyRole != null && CollectionUtils.isNotEmpty(deputyRole.getDeputies())) {
      for (ISecurityMember securityMember : deputyRole.getDeputies()) {
        IUser substituteUser = ServiceUtilities.findUser(securityMember.getName(), application);
        if (substituteUser != null) {
          IvySubstitute ivySubstitute = initIvySubstitute(deputyRole, substituteUser);

          ivySubstitutes.add(ivySubstitute);
        }
      }
    }
    return ivySubstitutes;
  }

  private static IvySubstitute initIvySubstitute(DeputyRole deputyRole, IUser substituteUser) {
    IvySubstitute ivySubstitute = new IvySubstitute();
    if (DeputyRoleType.TASK_FOR_ROLE.equals(deputyRole.getDeputyRoleType())) {
      ivySubstitute.setSubstitionRole(deputyRole.getSubstitutionRole());
      ivySubstitute.setSubstitionRoleDisplayName(deputyRole.getSubstitutionRole().getDisplayName());
    }
    ivySubstitute.setSubstituteUser(new UserDTO(substituteUser));
    ivySubstitute.setDescription(deputyRole.getDescription());
    ivySubstitute.setSubstitutionType(deputyRole.getSubstitutionType());
    ivySubstitute.setOwnerUser(deputyRole.getOwnerUser());
    return ivySubstitute;
  }
}
