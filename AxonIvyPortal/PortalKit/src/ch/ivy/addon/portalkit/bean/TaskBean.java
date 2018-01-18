package ch.ivy.addon.portalkit.bean;

import static ch.ivyteam.ivy.security.IPermission.DELEGATE_TASKS;
import static ch.ivyteam.ivy.security.IPermission.TASK_WRITE_ACTIVATOR;
import static ch.ivyteam.ivy.security.IPermission.TASK_WRITE_EXPIRY_PRIORITY;
import static ch.ivyteam.ivy.security.IPermission.TASK_WRITE_ORIGINAL_PRIORITY;
import static ch.ivyteam.ivy.workflow.TaskState.DESTROYED;
import static ch.ivyteam.ivy.workflow.TaskState.DONE;
import static ch.ivyteam.ivy.workflow.TaskState.FAILED;
import static ch.ivyteam.ivy.workflow.TaskState.RESUMED;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.vo.TaskVO;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IProcessModelVersionRequest;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;



/**
 * Provide the utilities related to Task.
 * 
 * @author bolt
 *
 */
@ManagedBean(name = "taskBean")
public class TaskBean {

  private static final String SHARP = "#";
  private static final String FORMAT_DATE = "dd.MM.yyyy HH:mm";
  
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
      if (Ivy.session().getSessionUserName() != null && activator.getMemberName() != null) {
        if (Ivy.session().getSessionUserName().equals(activator.getMemberName().replace(SHARP, StringUtils.EMPTY))) {
          hasTaskActionsPermission = true;
        }
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

  /**
   * Check if the user can start the task
   * 
   * @param task Task need to be checked
   * @return true if user can start the task, false if otherwise
   */
  public boolean checkStartableTask(ITask task) {
    boolean disableTaskStart;
    if (task != null) {
      TaskState state = task.getState();
      disableTaskStart =
          TaskState.DONE.equals(state) || TaskState.DELAYED.equals(state) || !canStartInProgressTask(task);
    } else {
      disableTaskStart = true;
    }
    return disableTaskStart;
  }

  private boolean canStartInProgressTask(ITask task) {
    IWorkflowSession ivySession = Ivy.session();
    if (TaskState.RESUMED.equals(task.getState())) {
      if (ivySession.equals(task.getWorkerSession())) {
        return true;
      }
      return false;
    }
    return true;
  }

  /**
   * Check if the task can run the PARK functionality
   * 
   * @param task Task need to be checked
   * @return boolean True : Disable PARK , False : enable PARK
   */
  public boolean checkDisablePark(ITask task) {
    boolean disableParkLink = false;

    if (task != null) {
      TaskState state = task.getState();
      IWorkflowSession ivySession = Ivy.session();
      IProcessModelVersionRequest Ivyrequest = (IProcessModelVersionRequest) Ivy.html().getObject("request");
      ISecurityDescriptor securityDescriptor = Ivyrequest.getApplication().getSecurityDescriptor();
      boolean hasParkPermission = ivySession.hasPermission(securityDescriptor, IPermission.TASK_PARK_OWN_WORKING_TASK);

      // Task must be in state TaskState.CREATED or TaskState.RESUMED
      if (hasParkPermission
          && (TaskState.RESUMED.equals(state) || TaskState.CREATED.equals(state) || TaskState.SUSPENDED.equals(state))) {
        disableParkLink = false;
      } else {
        disableParkLink = true;
      }

    } else {
      disableParkLink = true;

    }
    return disableParkLink;
  }

  public boolean isDelegatable(ITask task) {
    if (task == null) {
      return false;
    }
    boolean hasDelegatePermission = isSessionUserHasDelegatePermission();
    EnumSet<TaskState> notDelegatableTaskStates = EnumSet.of(DONE, DESTROYED, RESUMED, FAILED);
    if (notDelegatableTaskStates.contains(task.getState()) || !hasDelegatePermission) {
      return false;
    }
    return true;
  }

	private boolean isSessionUserHasDelegatePermission() {
		IWorkflowSession ivySession = Ivy.session();
		ISecurityDescriptor securityDescriptor = Ivy.request().getApplication()
				.getSecurityDescriptor();
		boolean hasDelegatePermission = ivySession.hasPermission(
				securityDescriptor, DELEGATE_TASKS)
				|| ivySession.hasPermission(securityDescriptor,
						TASK_WRITE_ACTIVATOR);
		return hasDelegatePermission;
  }

  /**
   * Check if the task can run the reset functionality
   * 
   * @param task : Task need to be checked
   * @return boolean : True : Disable RESET , False : enable RESET
   */
  public boolean checkDisableReset(ITask task) {
    boolean disableResetLink = false;

    if (task != null) {
      TaskState state = task.getState();
      IWorkflowSession ivySession = Ivy.session();
      boolean hasResetPermission =
          ivySession.hasPermission(Ivy.request().getApplication().getSecurityDescriptor(),
              ch.ivyteam.ivy.security.IPermission.TASK_RESET_OWN_WORKING_TASK);

      state = task.getState();

      if (state == TaskState.RESUMED || state == TaskState.PARKED && hasResetPermission) {
        disableResetLink = false;
      } else {
        disableResetLink = true;
      }
    }
    return disableResetLink;

  }

  public boolean isTaskAbleToAddNote(ITask task) {
    if (task == null) {
      return false;
    }
    EnumSet<TaskState> notAbleToAddNoteTaskStages = EnumSet.of(DONE, DESTROYED, RESUMED, FAILED);
    if (notAbleToAddNoteTaskStages.contains(task.getState())) {
      return false;
    }
    return true;
  }

  public boolean isTaskAbleToChangeDeadline(ITask task) {
    if (task == null) {
      return false;
    }
    
    boolean hasTaskWriteExpiryPermission = isSessionUserHasTaskChangeDeadlinePermission();
    EnumSet<TaskState> notAbleToChangeDeadlineTaskStages = EnumSet.of(DONE, DESTROYED, RESUMED, FAILED);
    if (notAbleToChangeDeadlineTaskStages.contains(task.getState()) || !hasTaskWriteExpiryPermission) {
      return false;
    }
    return true;
  }

  private boolean isSessionUserHasTaskChangeDeadlinePermission() {
    IWorkflowSession ivySession = Ivy.session();
    ISecurityDescriptor securityDescriptor = Ivy.request().getApplication().getSecurityDescriptor();
    boolean hasTaskWriteExpiryPermission = ivySession.hasPermission(securityDescriptor, IPermission.TASK_WRITE_EXPIRY_TIMESTAMP);
    return hasTaskWriteExpiryPermission;
  }

  public boolean isTaskAbleToChangePriority(ITask task) {
    if (task == null) {
      return false;
    }
    boolean hasTaskChangePriorityPermission = isSessionUserHasTaskChangePriorityPermission();
    EnumSet<TaskState> notAbleToChangePriorityTaskStages = EnumSet.of(DONE, DESTROYED, RESUMED, FAILED);
    if (notAbleToChangePriorityTaskStages.contains(task.getState()) || !hasTaskChangePriorityPermission) {
      return false;
    }
    return true;
  }

  private boolean isSessionUserHasTaskChangePriorityPermission() {
    IWorkflowSession ivySession = Ivy.session();
    ISecurityDescriptor securityDescriptor = Ivy.request().getApplication().getSecurityDescriptor();
    boolean hasTaskWriteExpiredPriorityPermission =
        ivySession.hasPermission(securityDescriptor, TASK_WRITE_EXPIRY_PRIORITY);
    boolean hasTaskWriteOriginalPriorityPermission =
        ivySession.hasPermission(securityDescriptor, TASK_WRITE_ORIGINAL_PRIORITY);
    return hasTaskWriteExpiredPriorityPermission && hasTaskWriteOriginalPriorityPermission;
  }
  
  public boolean canChangeOriginalPriority(RemoteTask task) {
    if (task == null) {
      return false;
    }
    return isNotDone(task) && task.canChangePriority();
  }
  
  public boolean isNotDone(RemoteTask task) {
    if (task == null) {
      return false;
    }
    EnumSet<TaskState> taskStages = EnumSet.of(TaskState.RESUMED, TaskState.PARKED, TaskState.SUSPENDED, TaskState.UNASSIGNED);
    return taskStages.contains(task.getState());
  }
  
  public boolean isDone(RemoteTask task) {
	  return !isNotDone(task);
  }

  /**
   * Get the state of task
   * 
   * @param task to get the state
   * @return the state of task
   */
  public String getTranslatedState(TaskState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/" + state);
  }
  
  public String displayRelatedTaskToolTip(ITask task) {
  	String taskResponsible = "";
  	if(task != null) {
  		taskResponsible = ((RemoteTask)task).getActivatorFullName();
  	}
  	List<Object> params = Arrays.asList(getTranslatedState(task.getState()), Objects.toString(taskResponsible, ""));
  	return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskStateAndResponsible", params);
  }
  
  public String displayRelatedTaskToolTipSingleApp(ITask task) {
  	String taskResponsible = "";
  	if(task != null && task.getActivator() != null) {
  		ISecurityMember taskActivor = task.getActivator();
  		if(taskActivor.isUser()) {
    		taskResponsible = ((IUser) taskActivor).getFullName();
  		} else {
  			taskResponsible = !((IRole)taskActivor).getDisplayName().isEmpty() ? ((IRole)taskActivor).getDisplayName() : ((IRole)taskActivor).getMemberName();
  		}
  	}
  	List<Object> params = Arrays.asList(getTranslatedState(task.getState()), Objects.toString(taskResponsible, ""));
  	return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/taskStateAndResponsible", params);
  }
  
  public String getUserFriendlyTaskState(TaskState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    switch (state) {
      case SUSPENDED:
      case UNASSIGNED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/task/taskState/open");
      case CREATED:
      case RESUMED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/task/taskState/inProgress");
      case DONE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/task/taskState/done");
      case PARKED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/task/taskState/reserved");
      case DESTROYED:
      case ZOMBIE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/task/taskState/destroyed");
      default:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/task/taskState/system");
    }
  }

  /**
   * Get the state of task
   * 
   * @param task to check delay
   * @return is task delayed or not
   */
  public Boolean checkDelayedState(TaskVO task) {
    return task.getState().equals(TaskState.DELAYED);
  }

  /**
   * Get the state of task
   * 
   * @param task to get the state
   * @return the state of task
   */
  public String getState(TaskVO task) {
    String stateDisplayOut = "";
    if (task == null)
      return stateDisplayOut;
    if (TaskState.DELAYED == task.getState()) {
      ArrayList<Object> params = new ArrayList<Object>();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE);
      if (task.getDelayTimestamp() != null) {
        params.add(simpleDateFormat.format(task.getDelayTimestamp()));
        stateDisplayOut = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/" + task.getState(), params);
      }
    } else {
      stateDisplayOut = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/" + task.getState());
    }

    return stateDisplayOut;
  }

  public String displayCaseName(ITask task) {
    ICase iCase = task.getCase();
    String caseName = iCase.getName();
    return StringUtils.isNotEmpty(caseName) ? caseName : "#" + iCase.getId();
  }
  
  public String getTechnicalCaseDisplayName(RemoteTask remoteTask){
    RemoteCase technicalCase = remoteTask.getRemoteTechnicalCase();
    if(technicalCase != null){
      if(technicalCase.getName() != null && !technicalCase.getName().isEmpty()){
        return technicalCase.getName();
      }
      return String.valueOf(technicalCase.getId());
    }
    return StringUtils.EMPTY;
  }
  
  public Boolean canChangeExpiry(RemoteTask task){
    return task.canChangeExpiry() && task.getExpiryActivator() != null || StringUtils.isNotBlank(task.getExpiryTaskStartElementPid());
  }
  
  public boolean notHaveExpiryHandleLogic(RemoteTask task) {
    return task.canChangeExpiry() && task.getExpiryActivator() == null && StringUtils.isBlank(task.getExpiryTaskStartElementPid());
  }
}
