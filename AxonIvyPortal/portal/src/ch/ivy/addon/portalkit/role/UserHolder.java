package ch.ivy.addon.portalkit.role;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.UserDTO;
import ch.ivyteam.ivy.security.IUser;

public class UserHolder implements Serializable {

  private static final long serialVersionUID = -3387169339793283968L;
  private String name;
  private String displayName;
  private IUser iUser;
  private boolean isDirectlyAssignedRole;

  public UserHolder() {}

  public UserHolder(UserDTO userDTO) {
    this.name = userDTO.getName();
    this.displayName = userDTO.getDisplayName();
  }

  public UserHolder(IUser iUser) {
    this.iUser = iUser;
    this.name = iUser.getName();
    this.displayName = iUser.getDisplayName();
  }

  public UserHolder(IUser iUser, String roleName) {
    this.iUser = iUser;
    this.name = iUser.getName();
    this.displayName = iUser.getDisplayName();
    this.isDirectlyAssignedRole = iUser.getRoles().stream()
        .filter(role -> StringUtils.equals(role.getName(), roleName)).findAny().isPresent();
  }

  public UserHolder(String name, String displayName) {
    this.name = name;
    this.displayName = displayName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public IUser getiUser() {
    return iUser;
  }

  public void setiUser(IUser iUser) {
    this.iUser = iUser;
  }

  public boolean isDirectlyAssignedRole() {
    return isDirectlyAssignedRole;
  }

  public void setDirectlyAssignedRole(boolean isDirectlyAssignedRole) {
    this.isDirectlyAssignedRole = isDirectlyAssignedRole;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (obj instanceof UserHolder) {
      return Objects.equals(name, ((UserHolder) obj).getName());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
