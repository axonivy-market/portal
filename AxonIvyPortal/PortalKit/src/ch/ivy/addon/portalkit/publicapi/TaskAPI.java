package ch.ivy.addon.portalkit.publicapi;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * Portal API for {@link ITask}
 *
 */
public final class TaskAPI {
  private TaskAPI() {}
  
  /**
   * Set the "HIDE" additional property to the given task to hide it in task list of Portal.
   * @param task {@link ITask} target task
   */
  public static void setHidePropertyToHideInPortal(ITask task) {
    task.customFields().stringField(AdditionalProperty.HIDE.toString()).set(AdditionalProperty.HIDE.toString());
  }
  
  /**
   * Remove the "HIDE" additional property to the given task to display it in task list of Portal.
   * @param task target task
   */
  public static void removeHidePropertyToDisplayInPortal(ITask task) {
    task.customFields().stringField(AdditionalProperty.HIDE.toString()).set(null);
  }

}
