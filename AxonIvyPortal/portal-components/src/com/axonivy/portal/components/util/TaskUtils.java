package com.axonivy.portal.components.util;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

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
    return IvyExecutor.executeAsSystem(() -> {
      TaskQuery taskQuery = TaskQuery.create().where().taskId().isEqual(taskId);
      return Ivy.wf().getTaskQueryExecutor().getFirstResult(taskQuery);
    });
  }
}