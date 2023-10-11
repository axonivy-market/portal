package com.axonivy.portal.components.util;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ITask;

public final class TaskUtils {

  private TaskUtils() {
  }

  /**
   * Finds a task by its ID
   * 
   * @param taskId
   * @return {@link ITask}
   */
  public static ITask findTask(long id) {
    return Sudo.get(() -> {
      return Ivy.wf().findTask(id);
    });
  }
  
  public static ITask findTask(String uuid) {
    return Sudo.get(() -> {
      return Ivy.wf().findTask(uuid);
    });
  }
}