package ch.ivy.gawfs.beans;

import gawfs.TaskDef;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivy.gawfs.Helper;
import ch.ivy.gawfs.enums.ProcessType;
import ch.ivy.gawfs.enums.TaskType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@RequestScoped
public class WorkflowDefinitionBean implements Serializable {

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

  public TaskType[] getTaskTypesForFirstWorkflowTask() {
    TaskType[] availableTaskTypes = {TaskType.USER_TASK, TaskType.USER_TASK_WITH_EMAIL, TaskType.EMAIL};
    return availableTaskTypes;
  }

  /**
   * Get array of all task types
   * 
   * @param taskDefList
   * @return Array of all task types
   */
  public TaskType[] getTaskTypes(List <TaskDef> taskDefList) {
    TaskType[] availableTaskTypes = {TaskType.USER_TASK, TaskType.USER_TASK_WITH_EMAIL, TaskType.EMAIL, TaskType.APPROVAL};

    for (TaskDef task : taskDefList) {
      if (task.getTaskType() == TaskType.USER_TASK) {
        return availableTaskTypes;
      }
    }

    TaskType[] availableTaskTypesWithoutApproval = {TaskType.USER_TASK, TaskType.USER_TASK_WITH_EMAIL, TaskType.EMAIL};
    return availableTaskTypesWithoutApproval;
  }

  /**
   * Get array of all process types
   * 
   * @return Array of process types
   */
  public ProcessType[] getProcessTypes() {
    return ProcessType.values();
  }

  /**
   * Populate available roles
   */
  private void populateAvailableRoles() {
    availableRoles = new ArrayList<>();
    List<IRole> rolesFromSystem = Ivy.wf().getSecurityContext().getRoles();
    rolesFromSystem.stream()
      .filter(role -> role.getName() != SYSTEM)
      .forEach(role -> availableRoles.add(role));
  }

  /**
   * Populate available users
   */
  private void populateAvailableUsers() {
    availableUsers = new ArrayList<>();
    List<IUser> usersFromSystem = Ivy.wf().getSecurityContext().getUsers();
    usersFromSystem.stream()
      .filter(user -> (user.getProperty(HIDE_PROPERTY) == null && !user.getName().equals(String.join("#", SYSTEM))))
      .forEach(user -> availableUsers.add(user));
  }

  /**
   * Populate values for Auto Complete of responsible based on given query
   * 
   * @param query
   * @return values of available responsible
   */
  public List<IUser> populateUserAutoComplete(String query) {
    return Helper.filterUsers(getAvailableUsers(), query);
  }

  /**
   * Populate values for Auto Complete of roles based on given query
   * 
   * @param query
   * @return values of available roles
   */
  public List<IRole> populateRoleAutoComplete(String query) {
    return Helper.filterRoles(getAvailableRoles(), query);
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
