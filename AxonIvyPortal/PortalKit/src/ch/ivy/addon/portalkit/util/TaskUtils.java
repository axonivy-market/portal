package ch.ivy.addon.portalkit.util;

import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_TIMESTAMP_FIELD5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.publicapi.TaskAPI;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public final class TaskUtils {

  private TaskUtils() {}

  public static void resetTask(final ITask task) {
    IvyExecutor.executeAsSystem(() -> {
      if (Arrays.asList(TaskState.RESUMED, TaskState.CREATED, TaskState.PARKED, TaskState.READY_FOR_JOIN,
          TaskState.FAILED)
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
    IvyExecutor.executeAsSystem(() -> {
      IWorkflowSession iWorkflowSession = Ivy.session();
      iWorkflowSession.parkTask(task);
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
    return IvyExecutor.executeAsSystem(() -> {
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
    return IvyExecutor.executeAsSystem(() -> {
      task.setExpiryTimestamp(null);
      return true;
    });
  }

  /**
   * Get finished tasks by {@link ICase}
   * 
   * @param iCase object as {@link ICase}
   * 
   * @return list of {@link ITask}
   */
  public static List<ITask> getFinishedTasksByCase(ICase iCase) {
    List<ITask> currentTasks = new ArrayList<>();
    if (iCase != null && iCase.tasks().all() != null && !iCase.tasks().all().isEmpty()) {
      for (ITask iTask : iCase.tasks().all()) {
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
    IvyExecutor.executeAsSystem(() -> {
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
    IvyExecutor.executeAsSystem(() -> {
      iTask.setExpiryActivator(iSecurityMember);
      return Void.class;
    });
  }

  /**
   * Sets the "HIDE" additional property to the given task to hide it in any task lists of Portal.
   * @deprecated Use {@link TaskAPI#setHidePropertyToHideInPortal(ITask)} instead
   * @param task
   */
  @Deprecated
  public static void setHidePropertyToHideInPortal(ITask task) {
    task.customFields().stringField(AdditionalProperty.HIDE.toString()).set(AdditionalProperty.HIDE.toString());
  }

  /**
   * Removes the "HIDE" property to the given task to display it in any task lists of Portal.
   * @deprecated Use {@link TaskAPI#removeHidePropertyToDisplayInPortal(ITask)} instead
   * @param task
   */
  @Deprecated
  public static void removeHidePropertyToDisplayInPortal(ITask task) {
    task.customFields().stringField(AdditionalProperty.HIDE.toString()).set(null);
  }

  /**
   * Gets task by ID that session user has permission to see
   * 
   * @param taskId task id to find
   * @return task if it exists and user has insufficient rights to see, null if otherwise
   */
  public static ITask findTaskUserHasPermissionToSee(final long taskId) {
    return IvyExecutor.executeAsSystem(() -> {
      TaskQuery taskQuery1 = TaskQuery.create().where().taskId().isEqual(taskId);
      TaskQuery taskQuery2 = TaskQuery.create().where().currentUserIsInvolved();
      IUser user = Ivy.session().getSessionUser();
      if (user == null) {
        return null;
      }
      for (IRole role : user.getRoles()) {
        taskQuery2 = taskQuery2.where().or().roleIsInvolved(role);
      }
      return Ivy.wf().getTaskQueryExecutor().getFirstResult(taskQuery1.where().and(taskQuery2));
    });
  }

  /**
   * Finds a task by its ID
   * @param taskId
   * @return {@link ITask}
   */
  public static ITask findTaskById(long taskId) {
    return IvyExecutor.executeAsSystem(() -> {
      TaskQuery taskQuery = TaskQuery.create().where().taskId().isEqual(taskId);
      return Ivy.wf().getTaskQueryExecutor().getFirstResult(taskQuery);
    });
  }

  public static boolean isTaskCurrentOpeningTask(ITask task){
    return IvyExecutor.executeAsSystem(() -> {
      var wfTask = Ivy.wfTask();
      return task.getState() == TaskState.RESUMED && task.getId() == wfTask.getId();
    });
  }
  
  public static void updateTaskStartedAttribute(boolean status) {
    Ivy.session().setAttribute(SessionAttribute.IS_TASK_STARTED_IN_DETAILS.toString(), status);
  }

  /** Destroys task if session user has permission TASK_READ, TASK_DESTROY
   * and this task is not DONE or DESTROYED
   * @param taskId
   */
  public static void destroyTaskById(long taskId) {
    if (PermissionUtils.checkDestroyTaskPermission()) {
      ITask task = findTaskById(taskId);
      if (task == null || Arrays.asList(TaskState.DONE, TaskState.DESTROYED).contains(task.getState())) {
        return;
      }
      IvyExecutor.executeAsSystem(() -> {
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
    return states.stream()
        .sorted((s1, s2) -> StringUtils.compare(s1.toString(), s2.toString()))
        .collect(Collectors.toList());
  }

  public static List<TaskState> filterStateByPermission(List<TaskState> states) {
    if (PermissionUtils.checkReadAllTasksPermission()) {
      return states;
    }
    var validStates = getValidStates();
    return CollectionUtils.emptyIfNull(states).stream()
        .filter(state -> validStates.contains(state))
        .collect(Collectors.toList());
  }
}
