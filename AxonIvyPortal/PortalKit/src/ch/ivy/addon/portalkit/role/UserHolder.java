package ch.ivy.addon.portalkit.role;

import java.io.Serializable;
import java.util.Objects;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivyteam.ivy.security.IUser;

public class UserHolder implements Serializable {

  private static final long serialVersionUID = -3387169339793283968L;
  private String name;
  private String displayName;
  private UserDTO userDTO;

  public UserHolder() {}

  public UserHolder(UserDTO userDTO) {
    this.name = userDTO.getName();
    this.displayName = userDTO.getDisplayName();
    this.userDTO = userDTO;
  }

  public UserHolder(IUser iUser) {
    this.name = iUser.getName();
    this.displayName = iUser.getDisplayName();
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

  public UserDTO getUserDTO() {
    return userDTO;
  }

  public void setUserDTO(UserDTO userDTO) {
    this.userDTO = userDTO;
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
