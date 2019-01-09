package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.TaskState;

public final class TaskUtils {

  private static final String HIDE = "HIDE";

  private TaskUtils() {
  }

  public static void resetTask(final ITask task) {
    if (Arrays.asList(TaskState.RESUMED, TaskState.CREATED, TaskState.PARKED, TaskState.READY_FOR_JOIN,
        TaskState.FAILED).contains(task.getState())) {
      IvyExecutor.executeAsSystem(() -> {
        task.reset();
        return true;
      });
    }
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
        iWorkflowSession = findUserWorkflowSession(Ivy.session().getSessionUserName(), task.getApplication());
        if (task.getState() == TaskState.SUSPENDED || task.getState() == TaskState.RESUMED || task.getState() == TaskState.CREATED) {
          iWorkflowSession.parkTask(task);
        }
        return null;
      } finally {
        if (iWorkflowSession != null) {
          ISecurityContext securityContext = task.getApplication().getSecurityContext();
          securityContext.destroySession(iWorkflowSession.getIdentifier());
        }
      }
    });
  }
  
  private static IWorkflowSession findUserWorkflowSession(String username, IApplication app) {
    ISecurityContext securityContext = app.getSecurityContext();
    return IvyExecutor.executeAsSystem(() -> {
      ISession session = securityContext.createSession();
      IUser user = securityContext.findUser(username);

      if (user != null) {
        String authenticationMode = "customAuth";
        session.authenticateSessionUser(user, authenticationMode, Ivy.wfTask().getId());
      }
      return Ivy.wf().getWorkflowSession(session);
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
        iTask.setCustomTimestampField5(new Date());
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
    task.setAdditionalProperty(HIDE, HIDE);
  }

  /**
   * Removes the "HIDE" additional property to the given task to display it in any task lists of Portal.
   * 
   * @param task
   */
  public static void removeHidePropertyToDisplayInPortal(ITask task) {
    task.setAdditionalProperty(HIDE, null);
  }
}
