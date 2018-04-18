package ch.ivy.gawfs.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivy.gawfs.Helper;
import ch.ivy.gawfs.enums.TaskType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@RequestScoped
public class WorkflowDefinitionBean implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 8119703742579630358L;
  private static final String SYSTEM = "SYSTEM";
  private static final String HIDE_PROPERTY = "HIDE";

  private List<IRole> availableRoles;
  private List<IUser> availableUsers;

  @PostConstruct
  public void init() {
    populateAvailableRoles();
    populateAvailableUsers();
  }

  private void populateAvailableRoles() {
    availableRoles = new ArrayList<>();
    List<IRole> rolesFromSystem = Ivy.wf().getSecurityContext().getRoles();
    rolesFromSystem.stream()
      .filter(role -> role.getName() != SYSTEM)
      .forEach(role -> availableRoles.add(role));
  }

  private void populateAvailableUsers() {
    availableUsers = new ArrayList<>();
    List<IUser> usersFromSystem = Ivy.wf().getSecurityContext().getUsers();
    usersFromSystem.stream()
      .filter(user -> (user.getProperty(HIDE_PROPERTY) == null && !user.getName().equals(String.join("#", SYSTEM))))
      .forEach(user -> availableUsers.add(user));
  }

  public List<IUser> populateUserAutoComplete(String query) {
    return Helper.filterUsers(getAvailableUsers(), query);
  }

  public List<IRole> populateRoleAutoComplete(String query) {
    return Helper.filterRoles(getAvailableRoles(), query);
  }

  public TaskType[] getTaskTypes() {
    return TaskType.values();
  }

  public List<IRole> getAvailableRoles() {
    return availableRoles;
  }

  public void setAvailableRoles(List<IRole> availableRoles) {
    this.availableRoles = availableRoles;
  }

  public List<IUser> getAvailableUsers() {
    return availableUsers;
  }

  public void setAvailableUsers(List<IUser> availableUsers) {
    this.availableUsers = availableUsers;
  }
}
