package ch.ivy.addon.portalkit.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.comparator.TaskExpiryComparator;
import ch.ivy.addon.portalkit.comparator.TaskPriorityComparator;
import ch.ivy.addon.portalkit.enums.SortType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean
@ViewScoped
public class TaskWidgetBean {

  private boolean compactMode;
  private SortType taskSortType;

  public void switchMode() {
    compactMode = !compactMode;
  }

  public String getDisplayTextForSwitchModeButton() {
    return compactMode ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskView/showFullTaskList") : Ivy.cms().co(
        "/ch.ivy.addon.portalkit.ui.jsf/taskView/backToOverview");
  }

  public void sortByPriority(@SuppressWarnings("unchecked") List<ITask>... listTasks) {
    for (List<ITask> tasks : listTasks) {
      tasks.sort(new TaskPriorityComparator());
    }
    taskSortType = SortType.BY_PRIORITY;
  }

  public void sortByExpiryTime(@SuppressWarnings("unchecked") List<ITask>... listTasks) {
    for (List<ITask> tasks : listTasks) {
      tasks.sort(new TaskExpiryComparator());
    }
    taskSortType = SortType.BY_EXPIRY_TIME;
  }

  public boolean isCompactMode() {
    return compactMode;
  }

  public void setIsCompactMode(boolean isCompactMode) {
    this.compactMode = isCompactMode;
  }

  public boolean isExpandedMode() {
    return !compactMode;
  }

  public SortType getTaskSortType() {
    return taskSortType;
  }

  public void setTaskSortType(SortType taskSortType) {
    this.taskSortType = taskSortType;
  }
  
}
