package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.support.UrlDetector;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

@ManagedBean
@ViewScoped
public class TaskActionBean implements Serializable {

  private static final long serialVersionUID = -7492204954515067995L;
  private boolean isShowResetTask;
  private boolean isShowReserveTask;
  private boolean isShowDelegateTask;
  //This variable control display of side step and create adhoc
  private boolean isShowAdditionalOptions;
  private boolean isShowDestroyTask;
  private boolean isShowReadWorkflowEvent;
  private static final String BACK_FROM_TASK_DETAILS = "Start Processes/PortalStart/BackFromTaskDetails.ivp";

  public TaskActionBean() {
    isShowResetTask = PermissionUtils.hasPortalPermission(PortalPermission.TASK_DISPLAY_RESET_ACTION);
    isShowReserveTask = PermissionUtils.hasPortalPermission(PortalPermission.TASK_DISPLAY_RESERVE_ACTION);
    isShowDelegateTask = PermissionUtils.hasPortalPermission(PortalPermission.TASK_DISPLAY_DELEGATE_ACTION);
    isShowAdditionalOptions = PermissionUtils.hasPortalPermission(PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS);
    isShowDestroyTask = PermissionUtils.hasPortalPermission(PortalPermission.TASK_DISPLAY_DESTROY_ACTION);
    isShowReadWorkflowEvent = PermissionUtils.hasPortalPermission(PortalPermission.TASK_DISPLAY_WORKFLOW_EVENT_ACTION);
  }

  public boolean canReset(ITask task) {
    if (task == null) {
      return false;
    }
    
    EnumSet<TaskState> taskStates = EnumSet.of(TaskState.RESUMED, TaskState.PARKED, TaskState.READY_FOR_JOIN,
        TaskState.FAILED);
    if (!taskStates.contains(task.getState())) {
      return false;
    }
    
    if (task.getState() == TaskState.READY_FOR_JOIN) {
      IPermission resetTaskReadyForJoin = IPermissionRepository.get().findByName(PortalPermission.TASK_RESET_READY_FOR_JOIN.getValue());
      return hasPermission(task, resetTaskReadyForJoin);
    }
  

    return (hasPermission(task, IPermission.TASK_RESET_OWN_WORKING_TASK) && canResume(task))
        || hasPermission(task, IPermission.TASK_RESET);
  }

  public boolean canDelegate(ITask task) {
    if (task == null) {
      return false;
    }
    
    EnumSet<TaskState> taskStates = EnumSet.of(TaskState.RESUMED, TaskState.DONE, TaskState.FAILED, TaskState.DESTROYED,
        TaskState.CREATED, TaskState.READY_FOR_JOIN, TaskState.FAILED, TaskState.JOIN_FAILED, TaskState.WAITING_FOR_INTERMEDIATE_EVENT);
    if (taskStates.contains(task.getState())) {
      return false;
    }

    if (userCanOnlyDelegateAssignedTask(task)) {
      return canResume(task);
    } else {
      return hasPermission(task, IPermission.TASK_WRITE_ACTIVATOR);
    }
  }

  private boolean userCanOnlyDelegateAssignedTask(ITask task) {
    IPermission permission =
        IPermissionRepository.get().findByName(PortalPermission.TASK_WRITE_ACTIVATOR_OWN_TASKS.getValue());
    if (Objects.isNull(permission)) {
      return false;
    }
    return hasPermission(task, permission) && !hasPermission(task, IPermission.TASK_WRITE_ACTIVATOR);
  }

  public boolean canResume(ITask task) {
    if (task == null) {
      return false;
    }
    
    ISession session = null;
    try {
      session = ServiceUtilities.findUserWorkflowSession(Ivy.session().getSessionUserName(), task.getApplication());
      return task.canUserResumeTask(session).wasSuccessful();
    } finally {
      if (session != null && !Objects.equals(IApplication.current(), task.getApplication())) {
        ISecurityContext securityContext = task.getApplication().getSecurityContext();
        securityContext.destroySession(session.getIdentifier());
      }
    }
  }

  public boolean canPark(ITask task) {
    if (task == null 
        || (task.getState() != TaskState.SUSPENDED && task.getState() != TaskState.CREATED && task.getState() != TaskState.RESUMED) 
        || (!canResume(task) && task.getState() != TaskState.CREATED)) {
      return false;
    }
    
    return hasPermission(task, IPermission.TASK_PARK_OWN_WORKING_TASK);
  }

  private boolean hasPermission(ITask task, IPermission permission) {
    if (task == null || permission == null) {
      return false;
    }
    return PermissionUtils.hasPermission(task.getApplication(), Ivy.session().getSessionUserName(), permission);
  }

  public boolean canChangePriority(ITask task) {
    return isNotDone(task) && hasPermission(task, IPermission.TASK_WRITE_ORIGINAL_PRIORITY);
  }

  public boolean canChangeExpiry(ITask task) {
    return (hasPermission(task, IPermission.TASK_WRITE_EXPIRY_TIMESTAMP) && task.getExpiryActivator() != null)
        || (task != null && StringUtils.isNotBlank(task.getExpiryTaskStartElementPid()));
  }
  
  public boolean canChangeDelayTimestamp(ITask task) {
    if (TaskState.DELAYED != task.getState()) {
      return false;
    }
    return hasPermission(task, IPermission.TASK_WRITE_DELAY_TIMESTAMP);
  }

  public boolean notHaveExpiryHandleLogic(ITask task) {
    return isNotDone(task) && hasPermission(task, IPermission.TASK_WRITE_EXPIRY_TIMESTAMP)
        && task.getExpiryActivator() == null && StringUtils.isBlank(task.getExpiryTaskStartElementPid());
  }

  public boolean canChangeName(ITask task) {
    return isNotDone(task) && hasPermission(task, IPermission.TASK_WRITE_NAME);
  }

  public boolean canChangeDescription(ITask task) {
    return isNotDone(task) && hasPermission(task, IPermission.TASK_WRITE_DESCRIPTION);
  }

  public boolean canWriteDocument(ITask task) {
    return hasPermission(task, IPermission.DOCUMENT_WRITE)
        || hasPermission(task, IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE);
  }

  public boolean canDestroyTask(ITask task) {
    List<TaskState> taskStates = Arrays.asList(TaskState.DONE, TaskState.DESTROYED);
    return hasPermission(task, IPermission.TASK_DESTROY) && !taskStates.contains(task.getState());
  }

  public boolean isNotDone(ITask task) {
    if (task == null) {
      return false;
    }
    EnumSet<TaskState> taskStates = EnumSet.of(TaskState.RESUMED, TaskState.PARKED, TaskState.SUSPENDED,
        TaskState.UNASSIGNED, TaskState.CREATED, TaskState.DELAYED);
    return taskStates.contains(task.getState());
  }
  
  public boolean isTechnicalState(ITask task) {
    EnumSet<TaskState> taskStates = EnumSet.of(TaskState.WAITING_FOR_INTERMEDIATE_EVENT, TaskState.FAILED,
        TaskState.JOIN_FAILED);
    return taskStates.contains(task.getState());
  }
  
  public boolean showAdditionalOptions(ITask task) {
    return isShowAdditionalOptions && isNotDone(task) && !isTechnicalState(task);
  }
  
  public boolean isShowResetTask() {
    return isShowResetTask;
  }

  public void setShowResetTask(boolean isShowResetTask) {
    this.isShowResetTask = isShowResetTask;
  }

  public boolean isShowReserveTask() {
    return isShowReserveTask;
  }

  public void setShowReserveTask(boolean isShowReserveTask) {
    this.isShowReserveTask = isShowReserveTask;
  }

  public boolean isShowDelegateTask() {
    return isShowDelegateTask;
  }

  public void setShowDelegateTask(boolean isShowDelegateTask) {
    this.isShowDelegateTask = isShowDelegateTask;
  }

  public boolean isShowAdditionalOptions() {
    return isShowAdditionalOptions;
  }

  public void setShowAdditionalOptions(boolean isShowAdditionalOptions) {
    this.isShowAdditionalOptions = isShowAdditionalOptions;
  }

  public void redirect(String url) {
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    } catch (IOException ex) {
      throw new PortalException(ex);
    }
  }

  public static String getProcessStartUriWithTaskParameters(ITask iTask, String requestPath) {
    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    String urlParameters = "?TaskId=" + iTask.getId();
    try {
      return ProcessStartAPI.findLinkByFriendlyRequestPath(Ivy.request().getApplication(), requestPath) + urlParameters; 
    } catch (Exception e) {
      Ivy.log().error(e);
      IProcessStart process = collector.findProcessStartByUserFriendlyRequestPath(requestPath);
      return RequestUriFactory.createProcessStartUri(process).toASCIIString() + urlParameters;
    }
  }
  
  public void backToTaskList(ITask task) throws MalformedURLException {
    String friendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword("BackFromTaskDetails.ivp");
    if (StringUtils.isEmpty(friendlyRequestPath)) {
      friendlyRequestPath = BACK_FROM_TASK_DETAILS;
    }
    String requestPath = SecurityServiceUtils.findProcessByUserFriendlyRequestPath(friendlyRequestPath);
    if (StringUtils.isNotEmpty(requestPath)) {
      TaskUtils.updateTaskStartedAttribute(false);
      UrlDetector urlDetector = new UrlDetector();
      String serverUrl = urlDetector.getBaseURL(FacesContext.getCurrentInstance());
      redirect(serverUrl + requestPath + "?endedTaskId=" + task.getId());
    }
  }
  
  public void removeTaskAttributesInSession() {
    TaskUtils.updateTaskStartedAttribute(false);
  }

  public boolean isShowDestroyTask() {
    return isShowDestroyTask;
  }

  public void setShowDestroyTask(boolean isShowDestroyTask) {
    this.isShowDestroyTask = isShowDestroyTask;
  }

  public void updateSelectedTaskItemId(boolean isShowInTaskList, Long taskId) {
    if (isShowInTaskList) {
      TaskWidgetBean taskWidgetBean = ManagedBeans.get("taskWidgetBean");
      if (taskWidgetBean != null) {
        taskWidgetBean.setSelectedTaskItemId(taskId);
      }
    }
  }

  public boolean showClearDelayTime(ITask task) {
    return TaskState.DELAYED.equals(task.getState()) && task.getDelayTimestamp() != null;
  }

  public boolean noActionAvailable(ITask task) {
    boolean hasWorkflowEventLink = isShowReadWorkflowEvent && canReadWorkflowEventTask();
    return !isNotDone(task) && !canReset(task) && !isTechnicalState(task) && !hasWorkflowEventLink;
  }

  public boolean canReadWorkflowEventTask() {
    return PermissionUtils.checkReadAllWorkflowEventPermission();
  }

  public boolean isShowReadWorkflowEvent() {
    return isShowReadWorkflowEvent;
  }
}
