package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.PF;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.util.DateTimeFormatterUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.PortalProcessViewerUtils;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.TimesUtils;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

@ManagedBean
@ViewScoped
public class TaskActionBean implements Serializable {

  private static final long serialVersionUID = 7247809679085338843L;
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
    
    EnumSet<TaskBusinessState> taskStates = EnumSet.of(TaskBusinessState.IN_PROGRESS, TaskBusinessState.OPEN, TaskBusinessState.DONE,
        TaskBusinessState.ERROR);
    if (!taskStates.contains(task.getBusinessState())) {
      return false;
    }
    
    if (task.getBusinessState() == TaskBusinessState.DONE) {
      IPermission resetTaskReadyForJoin = IPermissionRepository.instance().findByName(PortalPermission.TASK_RESET_READY_FOR_JOIN.getValue());
      return hasPermission(task, resetTaskReadyForJoin);
    }
  

    return (hasPermission(task, IPermission.TASK_RESET_OWN_WORKING_TASK) && canResume(task))
        || hasPermission(task, IPermission.TASK_RESET);
  }

  public boolean canDelegate(ITask task) {
    if (task == null) {
      return false;
    }
    
    EnumSet<TaskBusinessState> taskStates = EnumSet.of(TaskBusinessState.OPEN, TaskBusinessState.IN_PROGRESS,
        TaskBusinessState.DONE, TaskBusinessState.DESTROYED, TaskBusinessState.ERROR);
    if (taskStates.contains(task.getBusinessState())) {
      return false;
    }

    if (userCanOnlyDelegateAssignedTask(task)) {
      return canResume(task);
    } else {
      if(isNotDoneForWorkingUser(task)) {
        return hasPermission(task, IPermission.TASK_WRITE_ACTIVATOR);
      } else {
        return false;
      }      
    }
  }

  private boolean userCanOnlyDelegateAssignedTask(ITask task) {
    IPermission permission =
        IPermissionRepository.instance().findByName(PortalPermission.TASK_WRITE_ACTIVATOR_OWN_TASKS.getValue());
    if (Objects.isNull(permission)) {
      return false;
    }
    return hasPermission(task, permission) && !hasPermission(task, IPermission.TASK_WRITE_ACTIVATOR);
  }

  public boolean canResume(ITask task) {
    return TaskUtils.canResume(task);
  }

  public boolean canPark(ITask task) {
    if (task == null 
        || (task.getBusinessState() != TaskBusinessState.OPEN 
        && task.getBusinessState() != TaskBusinessState.IN_PROGRESS) 
        || (!canResume(task) && task.getBusinessState() != TaskBusinessState.IN_PROGRESS)) {
      return false;
    }
    
    return hasPermission(task, IPermission.TASK_PARK_OWN_WORKING_TASK);
  }

  private boolean hasPermission(ITask task, IPermission permission) {
    if (task == null || permission == null) {
      return false;
    }
    return PermissionUtils.hasPermission(permission);
  }

  public boolean canChangePriority(ITask task) {
    return isNotDone(task) && hasPermission(task, IPermission.TASK_WRITE_ORIGINAL_PRIORITY);
  }

  public boolean canChangeExpiry(ITask task) {
    return hasPermission(task, IPermission.TASK_WRITE_EXPIRY_TIMESTAMP)
        || (task != null && StringUtils.isNotBlank(task.getExpiryTaskStartElementPid()));
  }
  
  public boolean canChangeDelayTimestamp(ITask task) {
    if (TaskBusinessState.DELAYED != task.getBusinessState()) {
      return false;
    }
    return hasPermission(task, IPermission.TASK_WRITE_DELAY_TIMESTAMP);
  }
  
  public boolean canReadWorkflowEventTask() {
    return PermissionUtils.checkReadAllWorkflowEventPermission();
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

  public boolean canDestroyTask(ITask task) {
    List<TaskBusinessState> taskStates = Arrays.asList(TaskBusinessState.DONE, TaskBusinessState.DESTROYED);
    return hasPermission(task, IPermission.TASK_DESTROY) && !taskStates.contains(task.getBusinessState());
  }

  public boolean canChangeTaskExpiryActivator(ITask task) {
    return hasPermission(task, IPermission.TASK_WRITE_EXPIRY_ACTIVATOR) && isNotDone(task);
  }
  
  public boolean isNotDone(ITask task) {
    if (task == null) {
      return false;
    }
    EnumSet<TaskBusinessState> taskStates = EnumSet.of(TaskBusinessState.IN_PROGRESS, TaskBusinessState.OPEN, TaskBusinessState.DELAYED);
    return taskStates.contains(task.getBusinessState());
  }
  
  public boolean isNotDoneForWorkingUser(ITask task) {
    if(PermissionUtils.checkReadAllTasksPermission()) {
      return true;
    }
    return isNotDone(task) && canResume(task);
  }
  
  public boolean canResumeForWorkingUser(ITask task) {
    if(PermissionUtils.checkReadAllTasksPermission()) {
      return true;
    }
    return canResume(task);
  }
  
  public boolean isTechnicalState(ITask task) {
    EnumSet<TaskBusinessState> taskStates = EnumSet.of(TaskBusinessState.OPEN, TaskBusinessState.ERROR);
    return taskStates.contains(task.getBusinessState());
  }
  
  public boolean showAdditionalOptions(ITask task) {
    return isShowAdditionalOptions && isNotDone(task) && canResumeForWorkingUser(task) && !isTechnicalState(task);
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

  public boolean isShowDestroyTask() {
    return isShowDestroyTask;
  }

  public void setShowDestroyTask(boolean isShowDestroyTask) {
    this.isShowDestroyTask = isShowDestroyTask;
  }

  public boolean isShowReadWorkflowEvent() {
    return isShowReadWorkflowEvent;
  }

  public void setShowReadWorkflowEvent(boolean isShowReadWorkflowEvent) {
    this.isShowReadWorkflowEvent = isShowReadWorkflowEvent;
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
    return TaskBusinessState.DELAYED.equals(task.getBusinessState()) && task.getDelayTimestamp() != null && isNotDoneForWorkingUser(task);
  }

  public boolean showClearExpiryTime(ITask task) {
    return canChangeExpiry(task) && task.getExpiryTimestamp() != null && isNotDoneForWorkingUser(task);
  }

  public boolean noActionAvailable(ITask task) {
    boolean hasWorkflowEventLink = isShowReadWorkflowEvent && canReadWorkflowEventTask();
    return !isNotDone(task) && !canResumeForWorkingUser(task) && !canReset(task) && !isTechnicalState(task)
        && !hasWorkflowEventLink && !showProcessViewer(task);
  }
  
  public void backToPrevPage(ITask task, boolean isFromTaskList, boolean isTaskStartedInDetails) {
    if (isFromTaskList || !isTaskStartedInDetails) {
      TaskUtils.updateTaskStartedAttribute(false);
      PF.current().executeScript("backToPrevPage();");
    } else {
      backToTaskList(task);
    }
  }
  
  private void backToTaskList(ITask task) {
    String friendlyRequestPath = ProcessStartUtils.findFriendlyRequestPathContainsKeyword("BackFromTaskDetails.ivp");
    if (StringUtils.isEmpty(friendlyRequestPath)) {
      friendlyRequestPath = BACK_FROM_TASK_DETAILS;
    }
    String requestPath = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);
    if (StringUtils.isNotEmpty(requestPath)) {
      TaskUtils.updateTaskStartedAttribute(false);
      PortalNavigator.redirect(requestPath + "?endedTaskId=" + task.getId());
    }
  }

  public String getProcessViewerPageUri(ITask task) {
    task.getCase().getBusinessCase();
    return PortalProcessViewerUtils.getStartProcessViewerPageUri(task.getCase().getBusinessCase());
  }

  public String getDurationOfTask(ITask task) {
    return task.getEndTimestamp() != null ? getElapsedTimeForDoneTask(task) : getElapsedTimeForRunningTask(task);
  }

  private String getElapsedTimeForDoneTask(ITask task) {
    long duration = TimesUtils.getDurationInSeconds(task.getStartTimestamp(), task.getEndTimestamp());
    return DateTimeFormatterUtils.formatToShortTimeString(duration);
  }

  private String getElapsedTimeForRunningTask(ITask task) {
    long duration = TimesUtils.getDurationInSeconds(task.getStartTimestamp(), new Date());
    return DateTimeFormatterUtils.formatToShortTimeString(duration);
  }

  public String getDurationOfTaskOnTooltip(ITask task) {
    return task.getEndTimestamp() != null ? getElapsedTimeForDoneTaskOnTooltip(task)
        : getElapsedTimeForRunningTaskOnTooltip(task);
  }

  private String getElapsedTimeForDoneTaskOnTooltip(ITask task) {
    long duration = TimesUtils.getDurationInSeconds(task.getStartTimestamp(), task.getEndTimestamp());
    return DateTimeFormatterUtils.formatToExactTime(duration); 
  }

  private String getElapsedTimeForRunningTaskOnTooltip(ITask task) {
    long duration = TimesUtils.getDurationInSeconds(task.getStartTimestamp(), new Date());
    return DateTimeFormatterUtils.formatToExactTime(duration);
  }

  public boolean showProcessViewer(ITask task) {
    return PortalProcessViewerUtils.isShowProcessViewer(task.getCase().getBusinessCase());
  }
  
  public boolean canExpiry(ITask task) {
    return hasPermission(task, IPermission.TASK_WRITE_EXPIRY_ACTIVATOR) && isExpiryDateLower(task)
        && canExpiryTask(task);
  }

  public boolean isExpiryDateLower(ITask task) {
    return task.getExpiryTimestamp() != null && task.getExpiryTimestamp().after(new Date());
  }

  public boolean canExpiryTask(ITask task) {
    if (task == null) {
      return false;
    }
    EnumSet<TaskBusinessState> taskStates =
        EnumSet.of(TaskBusinessState.DONE, TaskBusinessState.DESTROYED, TaskBusinessState.IN_PROGRESS, TaskBusinessState.ERROR);
    return !taskStates.contains(task.getBusinessState());
  }

}
