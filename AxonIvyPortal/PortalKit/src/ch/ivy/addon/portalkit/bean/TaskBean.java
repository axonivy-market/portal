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
import ch.ivyteam.ivy.security.IRole;
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

  private static final String SHARP = "#";
  private static final String TASKSTATE_CMS_PATH = "/ch.ivy.addon.portalkit.ui.jsf/taskState/";
  
  /**
   * Check if task is high priority. return high priority icon css if task is high priority
   * 
   * @param priority : priority of the task
   * @return String : String content the code of icon
   */
  public String checkHighPriority(WorkflowPriority priority) {
    String styleClassOut = "";
    if (WorkflowPriority.HIGH.equals(priority)) {
      styleClassOut = "fa fa-exclamation-triangle";
    } else {
      styleClassOut = "";
    }
    return styleClassOut;
  }

  /**
   * Check if task activator is group or user and return the right icon
   * 
   * @param isUser : Activator is a user or group
   * @return String : String content the code of icon
   */
  public String checkActivator(Boolean isUser) {
    String styleClassOut = "";
    if (isUser) {
      styleClassOut = "fa fa-user";
    } else {
      styleClassOut = "fa fa-users";
    }
    return styleClassOut;
  }

  /**
   * 
   * Check if the activator has permission with the task action
   *
   * @param activator activator
   * @return boolean True : has permission False : do not have permission
   */
  public boolean checkActivatorPermisson(ISecurityMember activator) {
    boolean hasTaskActionsPermission = false;
    if (activator == null) {
      return false;
    }
    if (!activator.isUser()) {
      List<IRole> roles = Ivy.session().getSessionUser().getRoles();
      for (IRole role : roles) {
        if (role.getMemberName().equals(activator.getMemberName())) {
          return true;
        }
      }
    } else {
      if (Ivy.session().getSessionUserName() != null && activator.getMemberName() != null
          && Ivy.session().getSessionUserName().equals(activator.getMemberName().replace(SHARP, StringUtils.EMPTY))) {
        hasTaskActionsPermission = true;
      }
    }
    return hasTaskActionsPermission;

  }


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
        return "fa fa-play-circle task-state-open";
        // In progress
      case CREATED:
      case RESUMED:
        return "fa fa-play-circle task-state-in-progress";
        // Reserved
      case PARKED:
        return "fa fa-pause-circle task-state-reserved";
        // Done
      case DONE:
        return "fa fa-check-circle task-state-done";
        // Destroy
      case DESTROYED:
      case ZOMBIE:
        return "fa fa-exclamation-circle task-state-zombie-destroyed";
        // System
      default:
        return "fa fa-cog task-state-system";
    }

  }

  /**
   * Return icon for passed Task Priority
   * 
   * @param priority priority of the task
   * @return : state of the task
   */
  public String getPriorityIcon(WorkflowPriority priority) {
    String styleClassOut = "";
    if (WorkflowPriority.HIGH.equals(priority) || WorkflowPriority.EXCEPTION.equals(priority)) {
      styleClassOut = "fa fa-arrow-up";
    } else if (WorkflowPriority.NORMAL.equals(priority)) {
      styleClassOut = "fa fa-arrow-right";

    } else if (WorkflowPriority.LOW.equals(priority)) {
      styleClassOut = "fa fa-arrow-down";
    }
    return styleClassOut;
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
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/DONE");
      case PARKED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/RESERVED");
      case DESTROYED:
      case ZOMBIE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/DESTROYED");
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
