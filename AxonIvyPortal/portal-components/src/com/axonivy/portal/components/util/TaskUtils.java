package com.axonivy.portal.components.util;

import java.util.ArrayList;
import java.util.List;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public final class TaskUtils {

  private TaskUtils() {
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

  public static List<ITask> getOpenTasksByCase(ICase iCase) {
    List<ITask> currentTasks = new ArrayList<>();
    if (iCase != null && iCase.tasks().all() != null && !iCase.tasks().all().isEmpty()) {
      for (ITask iTask : iCase.tasks().all()) {
        if (iTask != null && TaskBusinessState.OPEN == iTask.getBusinessState()) {
          currentTasks.add(iTask);
        }
      }
    }
    return currentTasks;
  }
  
  /**
   * Park The Task.
   * 
   * @param task : Task need to be park
   */
  public static void parkTask(final ITask task) {
    Sudo.get(() -> {
      IWorkflowSession iWorkflowSession = Ivy.session();
      iWorkflowSession.parkTask(task);
      return Void.class;
    });
  }
}