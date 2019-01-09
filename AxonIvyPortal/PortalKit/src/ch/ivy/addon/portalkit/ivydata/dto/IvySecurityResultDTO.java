package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;
import java.util.Map;

import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;

public class IvySecurityResultDTO {

  private Map<String, List<IUser>> usersByApp;
  private List<IUser> users;
  private List<IRole> roles;
  private List<PortalIvyDataException> errors;

  public Map<String, List<IUser>> getUsersByApp() {
    return usersByApp;
  }

  public void setUsersByApp(Map<String, List<IUser>> usersByApp) {
    this.usersByApp = usersByApp;
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

  public List<PortalIvyDataException> getErrors() {
    return errors;
  }

  public void setErrors(List<PortalIvyDataException> errors) {
    this.errors = errors;
  }

}
