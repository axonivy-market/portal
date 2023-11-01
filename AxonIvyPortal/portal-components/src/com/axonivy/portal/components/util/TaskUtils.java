package com.axonivy.portal.components.util;

import java.util.ArrayList;
import java.util.List;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

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

  public static List<ITask> getSuspendedTasksByCase(ICase caze) {
    List<ITask> currentTasks = new ArrayList<>();
    if (caze != null && caze.tasks().all() != null && !caze.tasks().all().isEmpty()) {
      for (ITask iTask : caze.tasks().all()) {
        if (iTask != null && TaskState.SUSPENDED == iTask.getState()) {
          currentTasks.add(iTask);
        }
      }
    }
    return currentTasks;
  }
}