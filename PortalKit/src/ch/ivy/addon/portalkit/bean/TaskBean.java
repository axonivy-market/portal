package ch.ivy.addon.portalkit.bean;

import static ch.ivyteam.ivy.security.IPermission.DELEGATE_TASKS;
import static ch.ivyteam.ivy.security.IPermission.TASK_WRITE_ACTIVATOR;
import static ch.ivyteam.ivy.security.IPermission.TASK_WRITE_EXPIRY_ACTIVATOR;
import static ch.ivyteam.ivy.security.IPermission.TASK_WRITE_EXPIRY_PRIORITY;
import static ch.ivyteam.ivy.security.IPermission.TASK_WRITE_ORIGINAL_PRIORITY;
import static ch.ivyteam.ivy.workflow.TaskState.DESTROYED;
import static ch.ivyteam.ivy.workflow.TaskState.DONE;
import static ch.ivyteam.ivy.workflow.TaskState.FAILED;
import static ch.ivyteam.ivy.workflow.TaskState.RESUMED;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.text.StrSubstitutor;

import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.support.UrlDetector;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.vo.TaskVO;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IProcessModelVersionRequest;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.IProcessStart;
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

  private String taskDetailsUrl;
  public String relatedCaseUrl;

  @PostConstruct
  public void init() {
    try {
      relatedCaseUrl =
          SecurityServiceUtils
          .findProcessByUserFriendlyRequestPath("Start Processes/PortalTemplatePages/PortalCaseListLinkedFromTask.ivp");
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  public String getTaskDetailsUrl() {
    return taskDetailsUrl;
  }

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
   * @return String : String content the code of icon
   */
  public String getStateIcon(TaskState state) {

    if (state == null) {
      return StringUtils.EMPTY;
    }

    switch (state) {
      case CREATED:
        return "fa fa-flag-o";
      case DELAYED:
        return "fa fa-clock-o";
      case DESTROYED:
        return "fa fa-remove";
      case DONE:
        return "fa fa-check";
      case FAILED:
        return "fa fa-times-circle-o";
      case PARKED:
        return "fa fa-share";
      case RESUMED:
        return "fa fa-spinner";
      case UNASSIGNED:
        return "fa fa-trello";
      case SUSPENDED:
        return "fa fa-flag-o";
      case WAITING_FOR_INTERMEDIATE_EVENT:
        return "fa fa-history";
      case ZOMBIE:
        return "fa fa-chain";
      default:
        return "fa fa-check";
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
   * Get link of task details
   * 
   * @param taskId
   * @throws MalformedURLException
   * @throws UnknownHostException
   */
  public void getUrlOfTaskDetails(final long taskId) throws MalformedURLException {
    IApplication ivyApplication = Ivy.request().getApplication();
    ProcessStartCollector processStartCollector = new ProcessStartCollector(ivyApplication);
    IProcessStart processStart =
        processStartCollector
            .findProcessStartByUserFriendlyRequestPath("Start Processes/PortalTemplatePages/PortalSingleTask.ivp");
    String processStarPath = processStart.getFullRequestPath();
    UrlDetector urlDetector = new UrlDetector();
    String serverUrl = urlDetector.getBaseURL(FacesContext.getCurrentInstance());

    String taskDetailURLFormat = "${serverURL}/pro/${processStartPath}?remoteTaskId=${taskId}";
    Map<String, String> taskDetailURLFormatParams = new HashMap<>();
    taskDetailURLFormatParams.put("serverURL", serverUrl);
    taskDetailURLFormatParams.put("processStartPath", processStarPath);
    taskDetailURLFormatParams.put("taskId", String.valueOf(taskId));

    StrSubstitutor strSubstitutor = new StrSubstitutor(taskDetailURLFormatParams);
    taskDetailsUrl = strSubstitutor.replace(taskDetailURLFormat);
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
    boolean hasTaskWriteExpiryPermission = ivySession.hasPermission(securityDescriptor, TASK_WRITE_EXPIRY_ACTIVATOR);
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

  /**
   * Get the state of task
   * 
   * @param task to get the state
   * @return the state of task
   */
  public String getStateByITask(ITask task) {
    String stateDisplayOut = "";
    if (task == null) {
      return stateDisplayOut;
    }
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

  public String getRelatedCaseUrl() {
    return relatedCaseUrl;
  }

}
