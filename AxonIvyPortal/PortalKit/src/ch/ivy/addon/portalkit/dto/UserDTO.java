package ch.ivy.addon.portalkit.dto;

import ch.ivyteam.ivy.security.IUser;

public class UserDTO {

  private String username;
  private String displayName;
  private String email;
  private long id;

  public UserDTO() { }
  
  public UserDTO(IUser user) {
    this.username = user.getName();
    this.displayName = user.getDisplayName();
    this.email = user.getEMailAddress();
    this.id = user.getId();
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
