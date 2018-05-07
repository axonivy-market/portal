package ch.ivy.gawfs.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum TaskType {
  USER_TASK("/Dialogs/workflowCreation/TaskType/UserTask"),
  USER_TASK_WITH_EMAIL("/Dialogs/workflowCreation/TaskType/UserTaskWithEmail"),
  APPROVAL("/Dialogs/workflowCreation/TaskType/Approval"),
  EMAIL("/Dialogs/workflowCreation/TaskType/Email"),
  FINAL_REVIEW("/Dialogs/workflowCreation/TaskType/Review");

  private final String cmsURI;

  TaskType(String cmsURI) {
    this.cmsURI = cmsURI;
  }

  public String getLabel() {
    return Ivy.cms().co(cmsURI);
  }
}
