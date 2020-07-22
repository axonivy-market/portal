package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;


/**
 * Provide the utilities related to Task.
 * 
 * @author bolt
 *
 */
@ManagedBean(name = "taskBean")
public class TaskBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String TASKSTATE_CMS_PATH = "/ch.ivy.addon.portalkit.ui.jsf/taskState/";
  

  /**
   * Return icon for TaskState
   * 
   * @param state : state of the task
   * @return String : String content the code of icon and color
   */
  public String getStateIcon(TaskState state) {

    if (state == null) {
      return StringUtils.EMPTY;
    }

    switch (state) {
    // Open
      case SUSPENDED:
      case UNASSIGNED:
        return "icon ivyicon-controls-play task-state-open";
        // In progress
      case CREATED:
      case RESUMED:
        return "icon ivyicon-hourglass task-state-in-progress";
        // Reserved
      case PARKED:
        return "icon ivyicon-touch-finger_1 task-state-reserved";
        // Done
      case DONE:
        return "icon ivyicon-check-circle-1 task-state-done";
        // Destroy
      case DELAYED:
        return "icon ivyicon-alarm-bell-timer task-state-delayed";
      case DESTROYED:
      case ZOMBIE:
        return "icon ivyicon-alert-circle task-state-zombie-destroyed";
        // System
      default:
        return "icon ivyicon-synchronize-arrows task-state-system";
    }

  }

  public String getPriority(WorkflowPriority priority) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/" + priority.name());
  }

  public boolean isNotDone(ITask task) {
    if (task == null) {
      return false;
    }
    EnumSet<TaskState> taskStages =
        EnumSet.of(TaskState.RESUMED, TaskState.PARKED, TaskState.SUSPENDED, TaskState.UNASSIGNED);
    return taskStages.contains(task.getState());
  }

  public boolean isDone(ITask task) {
    return !isNotDone(task);
  }

  /**
   * Get the state of task
   * 
   * @param state
   * @return the state of task
   */
  public String getTranslatedState(TaskState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    return Ivy.cms().co(TASKSTATE_CMS_PATH + state);
  }

  public String displayRelatedTaskToolTip(ITask task) {
    List<Object> params = new ArrayList<>();
    if (task != null) {
      ISecurityMember taskActivator = task.getActivator();
      String taskActivatorName = taskActivator != null? taskActivator.getDisplayName() : StringUtils.stripStart(task.getActivatorName(), "#");
      params = Arrays.asList(getTranslatedState(task.getState()), Objects.toString(taskActivatorName, ""));
    }
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskStateAndResponsible", params);
  }

  public String getUserFriendlyTaskState(TaskState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    switch (state) {
      case SUSPENDED:
      case UNASSIGNED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/OPEN");
      case CREATED:
      case RESUMED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/INPROGRESS");
      case DONE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/DONE_UPPERCASE");
      case PARKED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/RESERVED");
      case DESTROYED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/DESTROYED_UPPERCASE");
      case DELAYED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/DELAYED_UPPERCASE");
      case READY_FOR_JOIN:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/READY_FOR_JOIN_UPPERCASE");
      case ZOMBIE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/ZOMBIE");
      default:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/SYSTEM");
    }
  }

  public String displayCaseName(ITask task) {
    ICase iCase = task.getCase().getBusinessCase();
    String caseName = iCase.getName();
    return StringUtils.isNotEmpty(caseName) ? caseName : "#" + iCase.getId();
  }

  public String getTechnicalCaseDisplayName(ITask task) {
    ICase technicalCase = task.getCase();
    if (technicalCase != null) {
      if (StringUtils.isNotBlank(technicalCase.getName())) {
        return technicalCase.getName();
      }
      return String.valueOf(technicalCase.getId());
    }
    return StringUtils.EMPTY;
  }
}
