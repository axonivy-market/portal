package ch.ivy.addon.portalkit.util;

import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_TIMESTAMP_FIELD5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.constant.DummyTask;
import ch.ivy.addon.portalkit.datamodel.internal.RelatedTaskLazyDataModel;
import ch.ivy.addon.portalkit.dto.TaskEndInfo;
import ch.ivy.addon.portalkit.enums.PortalPage;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.service.StickyTaskListService;
import ch.ivy.addon.portalkit.service.TaskInforActionService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public final class TaskUtils {
  private static final String PORTAL_GLOBAL_GROWL = "portal-global-growl";
  private static final String PORTAL_GLOBAL_GROWL_MESSAGE = "portal-global-growl-message";

  private TaskUtils() {}

  public static void resetTask(final ITask task) {
    Sudo.get(() -> {
      if (Arrays
          .asList(TaskState.RESUMED, TaskState.CREATED, TaskState.PARKED, TaskState.READY_FOR_JOIN, TaskState.FAILED)
          .contains(task.getState())) {
        task.reset();
      }
      return Void.class;
    });
  }

  /**
   * Park The Task.
   * 
   * @param task : Task need to be park
   */
  public static void parkTask(final ITask task) {
    Sudo.get(() -> {
      Ivy.session().parkTask(task);
      return Void.class;
    });
  }

  /**
   * Remove delay time of task
   * 
   * @param task : Task
   * @return Boolean
   */
  public static Boolean removeTaskDelay(final ITask task) {
    return Sudo.get(() -> {
      task.setDelayTimestamp(null);
      return true;
    });
  }

  /**
   * Remove deadline time of task
   * 
   * @param task : Task
   * @return Boolean
   */
  public static Boolean removeTaskDeadline(final ITask task) {
    return Sudo.get(() -> {
      task.setExpiryTimestamp(null);
      return true;
    });
  }

  /**
   * Get finished tasks by {@link ICase}
   * 
   * @param caze object as {@link ICase}
   * 
   * @return list of {@link ITask}
   */
  public static List<ITask> getFinishedTasksByCase(ICase caze) {
    List<ITask> currentTasks = new ArrayList<>();
    if (caze != null && caze.tasks().all() != null && !caze.tasks().all().isEmpty()) {
      for (ITask iTask : caze.tasks().all()) {
        if (iTask != null && TaskState.DONE == iTask.getState()) {
          currentTasks.add(iTask);
        }
      }
    }
    return currentTasks;
  }

  /**
   * Delegate a task
   * 
   * @param iTask task need to delegate
   * @param iSecurityMember
   */
  public static void delegateTask(final ITask iTask, final ISecurityMember iSecurityMember) {
    Sudo.get(() -> {
      iTask.setActivator(iSecurityMember);
      iTask.customFields().timestampField(CUSTOM_TIMESTAMP_FIELD5).set(new Date());
      return Void.class;
    });
  }

  /**
   * Delegate a task after escalation
   * 
   * @param iTask task need to delegate
   * @param iSecurityMember
   */
  public static void delegateTaskAfterEscalation(final ITask iTask, final ISecurityMember iSecurityMember) {
    Sudo.get(() -> {
      iTask.setExpiryActivator(iSecurityMember);
      return Void.class;
    });
  }

  /**
   * Gets task by ID that session user has permission to see
   * 
   * @param taskId task id to find
   * @return task if it exists and user has insufficient rights to see, null if otherwise
   */
  public static ITask findTaskUserHasPermissionToSee(final long taskId) {
    return Sudo.get(() -> {
      TaskQuery taskQuery1 = TaskQuery.create().where().taskId().isEqual(taskId);
      TaskQuery taskQuery2 = TaskQuery.create().where().currentUserIsInvolved();
      IUser user = Ivy.session().getSessionUser();
      if (user == null) {
        return null;
      }
      return Ivy.wf().getTaskQueryExecutor().getFirstResult(taskQuery1.where().and(taskQuery2));
    });
  }

  /**
   * Finds a task by its ID
   * 
   * @param taskId
   * @return {@link ITask}
   */
  public static ITask findTaskById(long taskId) {
    return Sudo.get(() -> {
      return Ivy.wf().findTask(taskId);
    });
  }

  public static boolean isTaskCurrentOpeningTask(ITask task) {
    return Sudo.get(() -> {
      var wfTask = Ivy.wfTask();
      return task.getState() == TaskState.RESUMED || task.getId() == wfTask.getId();
    });
  }

  public static void updateTaskStartedAttribute(boolean status) {
    Ivy.session().setAttribute(SessionAttribute.IS_TASK_STARTED_IN_DETAILS.toString(), status);
  }

  /**
   * Destroys task if session user has permission TASK_READ, TASK_DESTROY and this task is not DONE or DESTROYED
   * 
   * @param taskId
   */
  public static void destroyTaskById(long taskId) {
    if (PermissionUtils.checkDestroyTaskPermission()) {
      ITask task = findTaskById(taskId);
      if (task == null || Arrays.asList(TaskState.DONE, TaskState.DESTROYED).contains(task.getState())) {
        return;
      }
      Sudo.get(() -> {
        task.destroy();
        return Void.class;
      });
    }
  }

  public static List<TaskState> getValidStates() {
    var states = new ArrayList<>(TaskSearchCriteria.STANDARD_STATES);
    if (PermissionUtils.checkReadAllTasksPermission()) {
      states.addAll(TaskSearchCriteria.ADVANCE_STATES);
    } else {
      states.add(TaskState.DONE);
    }
    return states.stream().sorted((s1, s2) -> StringUtils.compare(s1.toString(), s2.toString()))
        .collect(Collectors.toList());
  }

  public static List<TaskState> filterStateByPermission(List<TaskState> states) {
    if (PermissionUtils.checkReadAllTasksPermission()) {
      return states;
    }
    var validStates = getValidStates();
    return CollectionUtils.emptyIfNull(states).stream().filter(state -> validStates.contains(state))
        .collect(Collectors.toList());
  }

  public static void handleStartTask(ITask task, PortalPage portalpage, String dialog) throws IOException {
    // refresh task
    ITask taskToStart = Ivy.wf().findTask(task.getId());
    if (taskToStart.getState() == TaskState.RESUMED) {
      handleStartResumedTask(taskToStart, dialog);
    } else {
      startTask(taskToStart, portalpage);
    }
  }

  private static void handleStartResumedTask(ITask task, String dialog) {
    if (canResume(task)) {
      PrimeFaces.current().executeScript("PF('" + dialog + "').show();");
    } else {
      isStartableTask(task);
    }
  }

  public static boolean canResume(ITask task) {
    if (task == null) {
      return false;
    }
    if (StringUtils.equals(task.names().current(), DummyTask.TASK_NAME)) {
      return true;
    }
    IUser sessionUser = Ivy.session().getSessionUser();
    return sessionUser != null ? task.canUserResumeTask(sessionUser.getUserToken()).wasSuccessful() : false;
  }

  private static void startTask(ITask task, PortalPage currentPortalPage) throws IOException {
    if (isStartableTask(task)) {
      if (currentPortalPage != null) {
        storeEndInfo(task, null, currentPortalPage);
      }
      FacesContext.getCurrentInstance().getExternalContext().redirect(task.getStartLinkEmbedded().getRelative());
    }
  }

  private static boolean isStartableTask(ITask task) {
    String notification = getNotificationWhenStartTask(task);
    if (StringUtils.isNotBlank(notification)) {
      FacesContext facesContext = FacesContext.getCurrentInstance();
      facesContext.validationFailed();
      facesContext.addMessage(PORTAL_GLOBAL_GROWL_MESSAGE,
          FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_INFO, notification, null));
      PrimeFaces.current().ajax().update(PORTAL_GLOBAL_GROWL);
    }
    return StringUtils.isBlank(notification);
  }

  private static String getNotificationWhenStartTask(ITask task) {
    String notification = "";
    List<Object> cmsList = new ArrayList<>();
    cmsList.add(task.names().current());
    if (task.getState() == TaskState.DONE) {
      notification =
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/cannotStartMessages/taskDone", cmsList);
    } else if (!canResume(task)) {
      IUser worker = task.getWorkerUser();
      if (worker != null) {
        String fullName = worker.getFullName();
        String workerName = StringUtils.isBlank(fullName) ? worker.getName() : fullName + " (" + worker.getName() + ")";
        cmsList.add(task.getId());
        cmsList.add(workerName);
        notification = Ivy.cms().co(
            "/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/cannotStartMessages/isAnotherUserWorking", cmsList);
      } else {
        notification = Ivy.cms()
            .co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/cannotStartMessages/cannotStartTask", cmsList);
      }
    }

    return notification;
  }

  private static void storeEndInfo(ITask task, RelatedTaskLazyDataModel dataModel, PortalPage currentPortalPage) {
    TaskEndInfo taskEndInfo = new TaskEndInfo();
    taskEndInfo.setDataModel(dataModel);
    taskEndInfo.setPortalPage(currentPortalPage);
    String taskEndInfoSessionAttributeKey =
        StickyTaskListService.service().getTaskEndInfoSessionAttributeKey(task.getId());
    SecurityServiceUtils.setSessionAttribute(taskEndInfoSessionAttributeKey, taskEndInfo);
  }

  public static void expiryTaskById(Long taskId) {
    ITask task = findTaskById(taskId);
    if (task == null) {
      return;
    }

    if (task.getExpiryActivator() == null && task.getExpiryTimestamp() == null) {
      task.setExpiryActivator(task.getActivator());
    }
    task.setExpiryTimestamp(new Date());

    IUser user = Ivy.session().getSessionUser();
    String fullName = user.getFullName();
    String userName = StringUtils.substring(user.getMemberName(), 1);
    String triggerNote = new TaskInforActionService().prepareTriggerEscalationNoteContent(fullName, userName, taskId);
    task.getCase().createNote(Ivy.session(), triggerNote);
  }

  // To convert Ivy task state to portal task state with multiple languages
  public static String convertToUserFriendlyTaskState(TaskState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    String url = switch (state) {
      case SUSPENDED -> "/ch.ivy.addon.portalkit.ui.jsf/taskState/SUSPENDED_UPPERCASE";
      case CREATED -> "/ch.ivy.addon.portalkit.ui.jsf/taskState/OPEN_UPPERCASE";
      case RESUMED -> "/ch.ivy.addon.portalkit.ui.jsf/taskState/INPROGRESS";
      case DONE -> "/ch.ivy.addon.portalkit.ui.jsf/taskState/DONE_UPPERCASE";
      case PARKED -> "/ch.ivy.addon.portalkit.ui.jsf/taskState/RESERVED";
      case DESTROYED -> "/ch.ivy.addon.portalkit.ui.jsf/taskState/DESTROYED_UPPERCASE";
      case DELAYED -> "/ch.ivy.addon.portalkit.ui.jsf/taskState/DELAYED_UPPERCASE";
      case READY_FOR_JOIN -> "/ch.ivy.addon.portalkit.ui.jsf/taskState/READY_FOR_JOINING_UPPERCASE";
      case FAILED -> "/ch.ivy.addon.portalkit.ui.jsf/taskState/FAILED_UPPERCASE";
      case JOIN_FAILED -> "/ch.ivy.addon.portalkit.ui.jsf/taskState/JOIN_FAILED_UPPERCASE";
      case WAITING_FOR_INTERMEDIATE_EVENT -> "/ch.ivy.addon.portalkit.ui.jsf/taskState/WAITING_FOR_INTERMEDIATE_EVENT_UPPERCASE";
      default -> "/ch.ivy.addon.portalkit.ui.jsf/taskState/SYSTEM";
    };
    return Ivy.cms().co(url);
  }

  // To get Ivy task priority with multiple languages
  public static String convertToUserFriendlyTaskPriority(WorkflowPriority priority) {
    if (priority == null) {
      return StringUtils.EMPTY;
    }
    String url = switch (priority) {
      case NORMAL -> "/ch.ivy.addon.portalkit.ui.jsf/taskPriority/NORMAL";
      case LOW -> "/ch.ivy.addon.portalkit.ui.jsf/taskPriority/LOW";
      case HIGH -> "/ch.ivy.addon.portalkit.ui.jsf/taskPriority/HIGH";
      default -> "/ch.ivy.addon.portalkit.ui.jsf/taskPriority/EXCEPTION";
    };
    return Ivy.cms().co(url);
  }
}
