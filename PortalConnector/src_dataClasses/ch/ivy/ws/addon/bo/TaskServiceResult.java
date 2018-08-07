package ch.ivy.ws.addon.bo;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskServiceResult", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskServiceResult extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -76206609735735725L;

  private transient java.lang.Boolean canUserResumeTask;

  /**
   * Gets the field canUserResumeTask.
   * @return the value of the field canUserResumeTask; may be null.
   */
  public java.lang.Boolean getCanUserResumeTask()
  {
    return canUserResumeTask;
  }

  /**
   * Sets the field canUserResumeTask.
   * @param _canUserResumeTask the new value of the field canUserResumeTask.
   */
  public void setCanUserResumeTask(java.lang.Boolean _canUserResumeTask)
  {
    canUserResumeTask = _canUserResumeTask;
  }

  private transient java.util.List<ch.ivy.ws.addon.WSException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> _errors)
  {
    errors = _errors;
  }

  private transient ch.ivy.ws.addon.types.IvyTask task;

  /**
   * Gets the field task.
   * @return the value of the field task; may be null.
   */
  public ch.ivy.ws.addon.types.IvyTask getTask()
  {
    return task;
  }

  /**
   * Sets the field task.
   * @param _task the new value of the field task.
   */
  public void setTask(ch.ivy.ws.addon.types.IvyTask _task)
  {
    task = _task;
  }

  private transient java.util.List<ch.ivy.ws.addon.types.IvyTask> tasks;

  /**
   * Gets the field tasks.
   * @return the value of the field tasks; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.IvyTask> getTasks()
  {
    return tasks;
  }

  /**
   * Sets the field tasks.
   * @param _tasks the new value of the field tasks.
   */
  public void setTasks(java.util.List<ch.ivy.ws.addon.types.IvyTask> _tasks)
  {
    tasks = _tasks;
  }

  private transient java.util.List<ch.ivy.ws.addon.types.IvyTask> allTasks;

  /**
   * Gets the field allTasks.
   * @return the value of the field allTasks; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.IvyTask> getAllTasks()
  {
    return allTasks;
  }

  /**
   * Sets the field allTasks.
   * @param _allTasks the new value of the field allTasks.
   */
  public void setAllTasks(java.util.List<ch.ivy.ws.addon.types.IvyTask> _allTasks)
  {
    allTasks = _allTasks;
  }

  private transient java.lang.String workerUserName;

  /**
   * Gets the field workerUserName.
   * @return the value of the field workerUserName; may be null.
   */
  public java.lang.String getWorkerUserName()
  {
    return workerUserName;
  }

  /**
   * Sets the field workerUserName.
   * @param _workerUserName the new value of the field workerUserName.
   */
  public void setWorkerUserName(java.lang.String _workerUserName)
  {
    workerUserName = _workerUserName;
  }

  private transient java.lang.Long taskCount;

  /**
   * Gets the field taskCount.
   * @return the value of the field taskCount; may be null.
   */
  public java.lang.Long getTaskCount()
  {
    return taskCount;
  }

  /**
   * Sets the field taskCount.
   * @param _taskCount the new value of the field taskCount.
   */
  public void setTaskCount(java.lang.Long _taskCount)
  {
    taskCount = _taskCount;
  }

  private transient java.util.List<java.lang.String> categories;

  /**
   * Gets the field categories.
   * @return the value of the field categories; may be null.
   */
  public java.util.List<java.lang.String> getCategories()
  {
    return categories;
  }

  /**
   * Sets the field categories.
   * @param _categories the new value of the field categories.
   */
  public void setCategories(java.util.List<java.lang.String> _categories)
  {
    categories = _categories;
  }

  private transient ch.ivy.ws.addon.types.PriorityStatistic priorityStatistic;

  /**
   * Gets the field priorityStatistic.
   * @return the value of the field priorityStatistic; may be null.
   */
  public ch.ivy.ws.addon.types.PriorityStatistic getPriorityStatistic()
  {
    return priorityStatistic;
  }

  /**
   * Sets the field priorityStatistic.
   * @param _priorityStatistic the new value of the field priorityStatistic.
   */
  public void setPriorityStatistic(ch.ivy.ws.addon.types.PriorityStatistic _priorityStatistic)
  {
    priorityStatistic = _priorityStatistic;
  }

  private transient ch.ivy.ws.addon.types.ExpiryStatistic expiryStatistic;

  /**
   * Gets the field expiryStatistic.
   * @return the value of the field expiryStatistic; may be null.
   */
  public ch.ivy.ws.addon.types.ExpiryStatistic getExpiryStatistic()
  {
    return expiryStatistic;
  }

  /**
   * Sets the field expiryStatistic.
   * @param _expiryStatistic the new value of the field expiryStatistic.
   */
  public void setExpiryStatistic(ch.ivy.ws.addon.types.ExpiryStatistic _expiryStatistic)
  {
    expiryStatistic = _expiryStatistic;
  }

}
