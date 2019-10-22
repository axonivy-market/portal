package ch.ivy.addon.portalkit.util;

import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_TIMESTAMP_FIELD5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public final class TaskUtils {
  private TaskUtils() {}

  public static void resetTask(final ITask task) {
    IvyExecutor.executeAsSystem(() -> {
      if (Arrays.asList(TaskState.RESUMED, TaskState.CREATED, TaskState.PARKED, TaskState.READY_FOR_JOIN, TaskState.FAILED)
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
      IWorkflowSession iWorkflowSession = null;
      try {
        iWorkflowSession =
            ServiceUtilities.findUserWorkflowSession(Ivy.session().getSessionUserName(), task.getApplication());
        iWorkflowSession.parkTask(task);
        return null;
      } finally {
        if (iWorkflowSession != null && !Objects.equals(Ivy.wf().getApplication(), task.getApplication())) {
          ISecurityContext securityContext = task.getApplication().getSecurityContext();
          securityContext.destroySession(iWorkflowSession.getIdentifier());
        }
      }
    });
  }

  /**
   * Remove delay time of task
   * 
   * @param task : Task
   * @return Boolean
   */
  public static Boolean removeTaskDelay(final ITask task) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(() -> {
        task.setDelayTimestamp(null);
        return true;
      });
    } catch (Exception e) {
      Ivy.log().error(e);
      return false;
    }
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
    if (iCase != null && iCase.getTasks() != null && !iCase.getTasks().isEmpty()) {
      for (ITask iTask : iCase.getTasks()) {
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
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        iTask.setActivator(iSecurityMember);
        iTask.customFields().timestampField(CUSTOM_TIMESTAMP_FIELD5).set(new Date());
        return null;
      });
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  /**
   * Sets the "HIDE" additional property to the given task to hide it in any task lists of Portal.
   * 
   * @param task
   */
  public static void setHidePropertyToHideInPortal(ITask task) {
    task.customFields().stringField(AdditionalProperty.HIDE.toString()).set(AdditionalProperty.HIDE.toString());
  }

  /**
   * Removes the "HIDE" property to the given task to display it in any task lists of Portal.
   * 
   * @param task
   */
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
      try {
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
      } catch (Exception e) {
        Ivy.log().error(e);
        return null;
      }
    });
  }
  
  public static boolean isTaskCurrentOpeningTask(ITask task){
	  try {
  		return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
  			  var wfTask = Ivy.wfTask();
  			  var currentTaskAppName = Optional.of(wfTask).map(ITask::getApplication).map(IApplication::getName).orElse("");
  			  var taskAppName = Optional.of(task).map(ITask::getApplication).map(IApplication::getName).orElse("");
  			  return task.getState() == TaskState.RESUMED &&
  					  task.getId() == wfTask.getId() &&
  					  taskAppName.equals(currentTaskAppName);
  		});
  	} catch (Exception e) {
  		// TODO Auto-generated catch block
  		Ivy.log().error(e);
  		return false;
  	}
  }
}
