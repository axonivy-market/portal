package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;
import java.util.Map;

import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;

public class IvySecurityResultDTO extends AbstractResultDTO {

  private Map<String, List<IUser>> usersByApp;
  private Map<String, List<IRole>> rolesByApp;
  private List<IUser> users;
  private List<IRole> roles;

  public Map<String, List<IUser>> getUsersByApp() {
    return usersByApp;
  }

  public void setUsersByApp(Map<String, List<IUser>> usersByApp) {
    this.usersByApp = usersByApp;
  }
  
  public Map<String, List<IRole>> getRolesByApp() {
    return rolesByApp;
  }

  public void setRolesByApp(Map<String, List<IRole>> rolesByApp) {
    this.rolesByApp = rolesByApp;
  }

  public List<IUser> getUsers() {
    return users;
  }

  public void setUsers(List<IUser> users) {
    this.users = users;
  }

  public List<IRole> getRoles() {
    return roles;
  }

  public void setRoles(List<IRole> roles) {
    this.roles = roles;
  }

}
