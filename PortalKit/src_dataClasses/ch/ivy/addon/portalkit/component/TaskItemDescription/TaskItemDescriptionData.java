package ch.ivy.addon.portalkit.component.TaskItemDescription;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskItemDescriptionData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskItemDescriptionData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 372451478765760566L;

  private ch.ivy.addon.portalkit.bo.RemoteTask task;

  /**
   * Gets the field task.
   * @return the value of the field task; may be null.
   */
  public ch.ivy.addon.portalkit.bo.RemoteTask getTask()
  {
    return task;
  }

  /**
   * Sets the field task.
   * @param _task the new value of the field task.
   */
  public void setTask(ch.ivy.addon.portalkit.bo.RemoteTask _task)
  {
    task = _task;
  }

  private ch.ivy.ws.addon.IvyTask ivyTask;

  /**
   * Gets the field ivyTask.
   * @return the value of the field ivyTask; may be null.
   */
  public ch.ivy.ws.addon.IvyTask getIvyTask()
  {
    return ivyTask;
  }

  /**
   * Sets the field ivyTask.
   * @param _ivyTask the new value of the field ivyTask.
   */
  public void setIvyTask(ch.ivy.ws.addon.IvyTask _ivyTask)
  {
    ivyTask = _ivyTask;
  }

}
