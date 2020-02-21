package ch.ivy.gawfs.beans;

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
import gawfs.TaskDef;

@ManagedBean
@RequestScoped
public class WorkflowDefinitionBean implements Serializable {

  private static final long serialVersionUID = 8119703742579630358L;
  private static final String SYSTEM = "SYSTEM";

  private List<IRole> availableRoles;

  @PostConstruct
  public void init() {
    populateAvailableRoles();
  }

  public TaskType[] getTaskTypesForFirstWorkflowTask() {
    return new TaskType[] {TaskType.USER_TASK, TaskType.USER_TASK_WITH_EMAIL, TaskType.EMAIL};
  }

  /**
   * Get array of all task types
   * 
   * @param taskDefList
   * @param currentTaskIndex
   * @return Array of all task types
   */
  public TaskType[] getTaskTypes(List <TaskDef> taskDefList, int currentTaskIndex) {
    List<TaskDef> previousTasks = taskDefList.subList(0, currentTaskIndex);

    for (TaskDef previousTask : previousTasks) {
      if (previousTask.getTaskType() == TaskType.USER_TASK || previousTask.getTaskType() == TaskType.USER_TASK_WITH_EMAIL) {
        return new TaskType[] {TaskType.USER_TASK, TaskType.USER_TASK_WITH_EMAIL, TaskType.EMAIL, TaskType.APPROVAL};
      }
    }

    return new TaskType[] {TaskType.USER_TASK, TaskType.USER_TASK_WITH_EMAIL, TaskType.EMAIL};
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
}
