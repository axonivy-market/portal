package ch.ivy.addon.portalkit.dto;

import ch.ivy.addon.portalkit.bean.UserUtilBean;

public class UserDTO {

  private String username;
  private String displayName;

  public UserDTO() { }
  
  public UserDTO(String username) {
    this.username = username;
    this.displayName = new UserUtilBean().generateDisplayName(username);
  }
  
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

}
