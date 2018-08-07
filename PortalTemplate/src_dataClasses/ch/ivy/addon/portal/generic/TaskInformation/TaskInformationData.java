package ch.ivy.addon.portal.generic.TaskInformation;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskInformationData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskInformationData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 3019658016415916129L;

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

  private java.lang.Long taskId;

  /**
   * Gets the field taskId.
   * @return the value of the field taskId; may be null.
   */
  public java.lang.Long getTaskId()
  {
    return taskId;
  }

  /**
   * Sets the field taskId.
   * @param _taskId the new value of the field taskId.
   */
  public void setTaskId(java.lang.Long _taskId)
  {
    taskId = _taskId;
  }

  private java.lang.Boolean taskNotFound;

  /**
   * Gets the field taskNotFound.
   * @return the value of the field taskNotFound; may be null.
   */
  public java.lang.Boolean getTaskNotFound()
  {
    return taskNotFound;
  }

  /**
   * Sets the field taskNotFound.
   * @param _taskNotFound the new value of the field taskNotFound.
   */
  public void setTaskNotFound(java.lang.Boolean _taskNotFound)
  {
    taskNotFound = _taskNotFound;
  }

}
