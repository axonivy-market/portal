package ch.ivy.addon.portalkit.dto.taskdetails;

import java.util.List;

import ch.ivyteam.ivy.workflow.TaskState;

public class TaskDetailsFilters {
  private List<String> taskCategories;
  private List<TaskState> taskStates;

  public List<TaskState> getTaskStates() {
    return taskStates;
  }
  public void setTaskStates(List<TaskState> taskStates) {
    this.taskStates = taskStates;
  }
  public List<String> getTaskCategories() {
    return taskCategories;
  }
  public void setTaskCategories(List<String> taskCategories) {
    this.taskCategories = taskCategories;
  }
}
