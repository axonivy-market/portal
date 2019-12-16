package ch.ivy.addon.portalkit.taskfilter;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskInProgressByOthersFilter extends TaskFilter {

  private boolean isTaskInProgressByOthersDisplayed;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskView/DisplayTaskStartedByOtherUsers");
  }

  @Override
  public String value() {
    String isDisplayed = isTaskInProgressByOthersDisplayed ? "display" : "hide";
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/" + isDisplayed); 
  }

  @Override
  public TaskQuery buildQuery() {
    return null;
  }

  @Override
  public void resetValues() {
    isTaskInProgressByOthersDisplayed = false;
  }

  @Override
  public boolean reloadView() {
    return true;
  }

  public boolean getIsTaskInProgressByOthersDisplayed() {
    return isTaskInProgressByOthersDisplayed;
  }

  public void setIsTaskInProgressByOthersDisplayed(boolean isTaskInProgressByOthersDisplayed) {
    this.isTaskInProgressByOthersDisplayed = isTaskInProgressByOthersDisplayed;
  }
  
}
