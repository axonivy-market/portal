package ch.ivy.addon.portalkit.singleapp.tasks.TaskExpiryDate;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskExpiryDateData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskExpiryDateData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 7903149690084354796L;

  private ch.ivyteam.ivy.workflow.ITask task;

  /**
   * Gets the field task.
   * @return the value of the field task; may be null.
   */
  public ch.ivyteam.ivy.workflow.ITask getTask()
  {
    return task;
  }

  /**
   * Sets the field task.
   * @param _task the new value of the field task.
   */
  public void setTask(ch.ivyteam.ivy.workflow.ITask _task)
  {
    task = _task;
  }

  private ch.ivyteam.ivy.scripting.objects.Date expiryTimestamp;

  /**
   * Gets the field expiryTimestamp.
   * @return the value of the field expiryTimestamp; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.Date getExpiryTimestamp()
  {
    return expiryTimestamp;
  }

  /**
   * Sets the field expiryTimestamp.
   * @param _expiryTimestamp the new value of the field expiryTimestamp.
   */
  public void setExpiryTimestamp(ch.ivyteam.ivy.scripting.objects.Date _expiryTimestamp)
  {
    expiryTimestamp = _expiryTimestamp;
  }

}
