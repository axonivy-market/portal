package ch.ivy.add.portalkit;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class Data", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class Data extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 3178881413602263755L;

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> apps;

  /**
   * Gets the field apps.
   * @return the value of the field apps; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getApps()
  {
    return apps;
  }

  /**
   * Sets the field apps.
   * @param _apps the new value of the field apps.
   */
  public void setApps(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _apps)
  {
    apps = _apps;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyTask> tasks;

  /**
   * Gets the field tasks.
   * @return the value of the field tasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyTask> getTasks()
  {
    return tasks;
  }

  /**
   * Sets the field tasks.
   * @param _tasks the new value of the field tasks.
   */
  public void setTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyTask> _tasks)
  {
    tasks = _tasks;
  }

  private transient ch.ivy.ws.addon.WsException error;

  /**
   * Gets the field error.
   * @return the value of the field error; may be null.
   */
  public ch.ivy.ws.addon.WsException getError()
  {
    return error;
  }

  /**
   * Sets the field error.
   * @param _error the new value of the field error.
   */
  public void setError(ch.ivy.ws.addon.WsException _error)
  {
    error = _error;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ITask> taskList;

  /**
   * Gets the field taskList.
   * @return the value of the field taskList; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ITask> getTaskList()
  {
    return taskList;
  }

  /**
   * Sets the field taskList.
   * @param _taskList the new value of the field taskList.
   */
  public void setTaskList(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ITask> _taskList)
  {
    taskList = _taskList;
  }

  private transient java.lang.Number caseId;

  /**
   * Gets the field caseId.
   * @return the value of the field caseId; may be null.
   */
  public java.lang.Number getCaseId()
  {
    return caseId;
  }

  /**
   * Sets the field caseId.
   * @param _caseId the new value of the field caseId.
   */
  public void setCaseId(java.lang.Number _caseId)
  {
    caseId = _caseId;
  }

  private transient java.lang.String spParameters;

  /**
   * Gets the field spParameters.
   * @return the value of the field spParameters; may be null.
   */
  public java.lang.String getSpParameters()
  {
    return spParameters;
  }

  /**
   * Sets the field spParameters.
   * @param _spParameters the new value of the field spParameters.
   */
  public void setSpParameters(java.lang.String _spParameters)
  {
    spParameters = _spParameters;
  }

}
