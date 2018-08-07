package ch.ivy.addon.portalkit.singleapp.tasks.TaskPriority;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskPriorityData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskPriorityData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -6264325254592250510L;

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

  private ch.ivyteam.ivy.scripting.objects.List<Object> priorities;

  /**
   * Gets the field priorities.
   * @return the value of the field priorities; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<Object> getPriorities()
  {
    return priorities;
  }

  /**
   * Sets the field priorities.
   * @param _priorities the new value of the field priorities.
   */
  public void setPriorities(ch.ivyteam.ivy.scripting.objects.List<Object> _priorities)
  {
    priorities = _priorities;
  }

  private ch.ivyteam.ivy.workflow.WorkflowPriority currentPriority;

  /**
   * Gets the field currentPriority.
   * @return the value of the field currentPriority; may be null.
   */
  public ch.ivyteam.ivy.workflow.WorkflowPriority getCurrentPriority()
  {
    return currentPriority;
  }

  /**
   * Sets the field currentPriority.
   * @param _currentPriority the new value of the field currentPriority.
   */
  public void setCurrentPriority(ch.ivyteam.ivy.workflow.WorkflowPriority _currentPriority)
  {
    currentPriority = _currentPriority;
  }

}
