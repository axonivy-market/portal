package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortMeta;

import ch.ivy.addon.portalkit.util.SortFieldUtil;
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
        return "si si-controls-play task-state-open";
        // In progress
      case CREATED:
      case RESUMED:
        return "si si-hourglass task-state-in-progress";
        // Reserved
      case PARKED:
        return "si si-touch-finger_1 task-state-reserved";
        // Done
      case DONE:
        return "si si-check-circle-1 task-state-done";
        // Destroy
      case DELAYED:
        return "si si-alarm-bell-timer task-state-delayed";
      case DESTROYED:
      case ZOMBIE:
        return "si si-alert-circle task-state-zombie-destroyed";
      case FAILED:
      case JOIN_FAILED:
        return "si si-mood-warning task-state-failed";
      case WAITING_FOR_INTERMEDIATE_EVENT:
        return "si si-synchronize-arrow-clock task-state-waiting";
        // System
      default:
        return "si si-synchronize-arrows task-state-system";
    }

  }

  public String getPriority(WorkflowPriority priority) {
    return cms("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/" + priority.name());
  }

  public boolean isNotDone(ITask task) {
    if (task == null) {
      return false;
    }
    EnumSet<TaskState> taskStages = EnumSet.of(TaskState.RESUMED, TaskState.PARKED, TaskState.SUSPENDED);
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
    return cms(TASKSTATE_CMS_PATH + state);
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
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/SUSPENDED");
      case CREATED:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/OPEN");
      case RESUMED:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/INPROGRESS");
      case DONE:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/DONE_UPPERCASE");
      case PARKED:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/RESERVED");
      case DESTROYED:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/DESTROYED_UPPERCASE");
      case DELAYED:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/DELAYED_UPPERCASE");
      case READY_FOR_JOIN:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/READY_FOR_JOIN_UPPERCASE");
      case ZOMBIE:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/ZOMBIE");
      default:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/SYSTEM");
    }
  }

  private String cms(String uri) {
    return Ivy.cms().co(uri);
  }

  public String getUserFriendlyTaskStateInCapitalization(TaskState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }

    if (state == TaskState.SUSPENDED || state == TaskState.CREATED || state == TaskState.RESUMED
        || state == TaskState.DONE || state == TaskState.PARKED || state == TaskState.DESTROYED
        || state == TaskState.DELAYED || state == TaskState.READY_FOR_JOIN || state == TaskState.ZOMBIE) {
      return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/" + state);
    }

    return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/SYSTEM");
  }

  public String shortenTaskState(TaskState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    if (state == TaskState.READY_FOR_JOIN || state == TaskState.JOINING) {
      return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/JOINING");
    }
    if (state == TaskState.WAITING_FOR_INTERMEDIATE_EVENT) {
      return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/WAITING");
    }
    if (state == TaskState.JOIN_FAILED || state == TaskState.FAILED) {
      return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/" + state);
    }

    return getUserFriendlyTaskStateInCapitalization(state);
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
  
  /**
   * Get failed reason of task FAILED or JOIN_FAILED
   * @param task
   * @return failedReason see more {@link ITask#getFailReason()}
   */
  public String getTaskFailedReason(ITask task) {
    if ((task.getState() == TaskState.FAILED || task.getState() == TaskState.JOIN_FAILED)
         && StringUtils.isNotEmpty(task.getFailReason())) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/noteHistory/taskFailReason", Arrays.asList(task.getFailReason()));
    }
    return StringUtils.EMPTY;
  }

  public SortMeta getTaskWorkflowEventSortByTimestamp() {
    return SortFieldUtil.buildSortMeta("timestamp", true);
  }
}
