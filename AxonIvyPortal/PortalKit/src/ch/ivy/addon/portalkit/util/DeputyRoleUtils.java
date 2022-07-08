package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.component.dto.UserDTO;

import ch.ivy.addon.portalkit.dto.DeputyRole;
import ch.ivy.addon.portalkit.enums.DeputyRoleType;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.SubstitutionType;

public class DeputyRoleUtils {

  private DeputyRoleUtils() {}

  public static List<DeputyRole> getDeputyRolesFromSubstitutes(List<IvySubstitute> ivySubstitutes) {
    List<DeputyRole> deputyRoles = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(ivySubstitutes)) {
      Map<String, DeputyRole> deputyRoleMap = new HashMap<>();
      String deputyRoleKey = "";
      String personalTaskPermanentKey = DeputyRoleType.PERSONAL_TASK_PERMANENT.toString();
      String personalTaskDuringAbsenceKey = DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE.toString();
      for (IvySubstitute ivySubstitute : ivySubstitutes) {
        IRole substitutionRole = ivySubstitute.getSubstitionRole();
        DeputyRoleType deputyRoleType = null;
        if (substitutionRole != null) {
          deputyRoleKey = String.valueOf(substitutionRole.getId());
          deputyRoleType = DeputyRoleType.TASK_FOR_ROLE;
        } else if(SubstitutionType.PERMANENT.equals(ivySubstitute.getSubstitutionType())) {
          deputyRoleKey = personalTaskPermanentKey;
          deputyRoleType = DeputyRoleType.PERSONAL_TASK_PERMANENT;
        } else {
          deputyRoleKey = personalTaskDuringAbsenceKey;
          deputyRoleType = DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE;
        }

        DeputyRole deputyRole = deputyRoleMap.get(deputyRoleKey);
        if (deputyRole == null) {
          deputyRole = initDeputyRole(ivySubstitute, substitutionRole, deputyRoleType);
          deputyRoleMap.put(deputyRoleKey, deputyRole);
        }

        UserDTO substituteUser = ivySubstitute.getSubstituteUser();
        ISecurityMember assignee = substituteUser != null ? Ivy.security().members().find(substituteUser.getMemberName()) : null;
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

  public static List<IvySubstitute> getSubstitutesFromDeputyRoles(List<DeputyRole> deputyRoles) {
    List<IvySubstitute> ivySubstitutes = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(deputyRoles)) {
      for (DeputyRole deputyRole : deputyRoles) {
        ivySubstitutes.addAll(getSubstitutesFromDeputyRole(deputyRole));
      }
    }
    return ivySubstitutes;
  }

  public static List<IvySubstitute> getSubstitutesFromDeputyRole(DeputyRole deputyRole) {
    List<IvySubstitute> ivySubstitutes = new ArrayList<>();
    if (deputyRole != null && CollectionUtils.isNotEmpty(deputyRole.getDeputies())) {
      for (ISecurityMember securityMember : deputyRole.getDeputies()) {
        IUser substituteUser = ServiceUtilities.findUser(securityMember.getName());
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

  public static DeputyRole findDeputyRoleByType(List<DeputyRole> deputyRoles, DeputyRoleType deputyRoleType) {
    if (CollectionUtils.isNotEmpty(deputyRoles) && deputyRoleType != null) {
      for (DeputyRole deputyRole : deputyRoles) {
        if (deputyRoleType.equals(deputyRole.getDeputyRoleType())) {
          return deputyRole;
        }
      }
    }
    return null;
  }

  public static boolean isSecurityMemberSelectedInDeputyRoleByType(List<DeputyRole> deputyRoles,
      DeputyRoleType deputyRoleType, ISecurityMember securityMember) {
    DeputyRole deputyRole = findDeputyRoleByType(deputyRoles, deputyRoleType);
    return deputyRole != null && deputyRole.getDeputies().contains(securityMember);
  }
}
