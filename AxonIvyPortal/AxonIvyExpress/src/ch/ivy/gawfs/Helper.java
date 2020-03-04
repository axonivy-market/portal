package ch.ivy.gawfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import gawfs.TaskDef;

public class Helper {

  private Helper() {}

  public static List<IUser> sortUsers(List<IUser> userList) {
    Collections.sort(userList, (u1, u2) -> {
      try {
        return (u1.getDisplayName().toLowerCase().compareTo(u2.getDisplayName().toLowerCase()));
      } catch (Exception ex) {
        Ivy.log().error(ex);
        return 0;
      }
    });
    return userList;
  }

  public static List<IRole> sortRoles(List<IRole> rolesList) {
    Collections.sort(rolesList, (r1, r2) -> {
      try {
        return (r1.getDisplayName().toLowerCase().compareTo(r2.getDisplayName().toLowerCase()));
      } catch (Exception ex) {
        Ivy.log().error(ex);
        return 0;
      }
    });
    return rolesList;
  }

  public static List<ISecurityMember> sortSecurityMembers(List<ISecurityMember> memberList) {
    Collections.sort(memberList, (m1, m2) -> {
      boolean b1 = m1.isUser();
      boolean b2 = m2.isUser();

      if (b1 && !b2) {
        return -1;
      }
      if (!b1 && b2) {
        return 1;
      }
      try {
        return (m1.getDisplayName().toLowerCase().compareTo(m2.getDisplayName().toLowerCase()));
      } catch (Exception ex) {
        Ivy.log().error(ex);
        return 0;
      }
    });
    return memberList;
  }

  public static List<ISecurityMember> sortSecurityMembersByMemberType(List<ISecurityMember> memberList) {
    Collections.sort(memberList, (m1, m2) -> {
      boolean b1 = m1.isUser();
      boolean b2 = m2.isUser();

      if (b1 && !b2) {
          return -1;
      }
      if (!b1 && b2) {
        return 1;
      }
      return 0;
    });
    return memberList;
  }

  public static List<TaskDef> sortTasks(List<TaskDef> taskList) {
    Collections.sort(taskList, (t1, t2) -> {
      try {
        return (t1.getPosition().compareTo(t2.getPosition()));
      } catch (Exception ex) {
        Ivy.log().error(ex);
        return 0;
      }
    });
    return taskList;
  }

  public static java.util.List<ISecurityMember> filterSecurityMembers(java.util.List<ISecurityMember> securityMembers,
      String query) {
    if (StringUtils.isEmpty(query)) {
      return securityMembers;
    }

    java.util.List<ISecurityMember> result = new ArrayList<>();
    for (ISecurityMember securityMember : securityMembers) {
      if (securityMember.getDisplayName().toLowerCase().contains(query.toLowerCase())
          || securityMember.getMemberName().toLowerCase().contains(query.toLowerCase())) {
        result.add(securityMember);
      }
    }

    result.sort((first, second) -> first.getDisplayName().toLowerCase()
        .compareTo(second.getDisplayName().toLowerCase()));
    return result;
  }

  public static java.util.List<IUser> filterUsers(java.util.List<IUser> users, String query) {
    if (StringUtils.isEmpty(query)) {
      return sortUsers(users);
    }

    java.util.List<IUser> result = new ArrayList<>();
    for (IUser user : users) {
      if (user.getDisplayName().toLowerCase().contains(query.toLowerCase())
          || user.getMemberName().toLowerCase().contains(query.toLowerCase())) {
        result.add(user);
      }
    }

    result.sort((first, second) -> first.getDisplayName().toLowerCase()
        .compareTo(second.getDisplayName().toLowerCase()));
    return result;
  }

  public static java.util.List<IRole> filterRoles(java.util.List<IRole> roles, String query) {
    if (StringUtils.isEmpty(query)) {
      return sortRoles(roles);
    }

    java.util.List<IRole> result = new ArrayList<>();
    for (IRole role : roles) {
      if (role.getDisplayName().toLowerCase().contains(query.toLowerCase())
          || role.getMemberName().toLowerCase().contains(query.toLowerCase())) {
        result.add(role);
      }
    }

    result.sort((first, second) -> first.getDisplayName().toLowerCase()
        .compareTo(second.getDisplayName().toLowerCase()));
    return result;
  }

}
