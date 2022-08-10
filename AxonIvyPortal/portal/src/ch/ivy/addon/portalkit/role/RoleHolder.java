package ch.ivy.addon.portalkit.role;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;

public class RoleHolder implements Serializable {

  private static final long serialVersionUID = 8077019609293259368L;
  private String name;
  private String description;
  private String displayName;
  private IRole parent;
  private boolean isMember;
  private boolean isDynamic;
  private boolean canManage = true;
  private long totalAssignedUsers;
  private String assignedUsersTooltip;

  public RoleHolder() {}

  public RoleHolder(String name) {
    this.name = name;
  }

  public RoleHolder(String name, String displayName, boolean canManage) {
    this.name = name;
    this.displayName = displayName;
    this.canManage = canManage;
  }

  public RoleHolder(IRole role) {
    this.name = role.getName();
    this.description = role.getDisplayDescription();
    this.displayName = role.getDisplayName();
    this.parent = role.getParent();
    this.isMember = false;
    this.isDynamic = role.isDynamic();
    this.totalAssignedUsers = role.users().allPaged().count();
    this.assignedUsersTooltip = role.users().allPaged(10).stream()
        .map(IUser::getDisplayName)
        .collect(Collectors.joining(", "));
    if (totalAssignedUsers >= 10) {
      assignedUsersTooltip = assignedUsersTooltip.concat(" ...");
    }
  }

  public RoleHolder(IRole role, boolean member) {
    this(role);
    this.isMember = member;
  }

  public RoleHolder(IRole role, boolean canManage, boolean member) {
    this(role);
    this.canManage = canManage;
    this.isMember = member;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public IRole getParent() {
    return parent;
  }

  public void setParent(IRole parent) {
    this.parent = parent;
  }

  public long getTotalAssignedUsers() {
    return totalAssignedUsers;
  }

  public void setTotalAssignedUsers(long totalAssignedUsers) {
    this.totalAssignedUsers = totalAssignedUsers;
  }

  public String getAssignedUsersAsText() {
    if (totalAssignedUsers == 0) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/chat/noUsers");
    }
    if (totalAssignedUsers == 1) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/user", Arrays.asList(totalAssignedUsers));
    }
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/RoleManagement/NumberUsers", Arrays.asList(totalAssignedUsers));
  }

  public String getAssignedUsersTooltip() {
    return assignedUsersTooltip;
  }

  public void setAssignedUsersTooltip(String assignedUsersTooltip) {
    this.assignedUsersTooltip = assignedUsersTooltip;
  }

  public boolean isMember() {
    return isMember;
  }

  public void setMember(boolean isMember) {
    this.isMember = isMember;
  }

  public boolean isDynamic() {
    return isDynamic;
  }

  public void setDynamic(boolean isDynamic) {
    this.isDynamic = isDynamic;
  }

  public boolean isCanManage() {
    return canManage;
  }

  public void setCanManage(boolean canManage) {
    this.canManage = canManage;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (obj instanceof RoleHolder) {
      return Objects.equals(name, ((RoleHolder) obj).getName());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
