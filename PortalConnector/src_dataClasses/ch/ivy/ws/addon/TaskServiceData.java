package ch.ivy.ws.addon;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 4000337353124310612L;

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

  private transient ch.ivy.ws.addon.WSException error;

  /**
   * Gets the field error.
   * @return the value of the field error; may be null.
   */
  public ch.ivy.ws.addon.WSException getError()
  {
    return error;
  }

  /**
   * Sets the field error.
   * @param _error the new value of the field error.
   */
  public void setError(ch.ivy.ws.addon.WSException _error)
  {
    error = _error;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WSException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WSException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WSException> _errors)
  {
    errors = _errors;
  }

  private transient ch.ivy.ws.addon.types.IvySecurityMember ivySecurityMember;

  /**
   * Gets the field ivySecurityMember.
   * @return the value of the field ivySecurityMember; may be null.
   */
  public ch.ivy.ws.addon.types.IvySecurityMember getIvySecurityMember()
  {
    return ivySecurityMember;
  }

  /**
   * Sets the field ivySecurityMember.
   * @param _ivySecurityMember the new value of the field ivySecurityMember.
   */
  public void setIvySecurityMember(ch.ivy.ws.addon.types.IvySecurityMember _ivySecurityMember)
  {
    ivySecurityMember = _ivySecurityMember;
  }

  private transient ch.ivy.ws.addon.types.IvyTask ivyTask;

  /**
   * Gets the field ivyTask.
   * @return the value of the field ivyTask; may be null.
   */
  public ch.ivy.ws.addon.types.IvyTask getIvyTask()
  {
    return ivyTask;
  }

  /**
   * Sets the field ivyTask.
   * @param _ivyTask the new value of the field ivyTask.
   */
  public void setIvyTask(ch.ivy.ws.addon.types.IvyTask _ivyTask)
  {
    ivyTask = _ivyTask;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyTask> ivyTasks;

  /**
   * Gets the field ivyTasks.
   * @return the value of the field ivyTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyTask> getIvyTasks()
  {
    return ivyTasks;
  }

  /**
   * Sets the field ivyTasks.
   * @param _ivyTasks the new value of the field ivyTasks.
   */
  public void setIvyTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyTask> _ivyTasks)
  {
    ivyTasks = _ivyTasks;
  }

  private transient java.lang.String keyword;

  /**
   * Gets the field keyword.
   * @return the value of the field keyword; may be null.
   */
  public java.lang.String getKeyword()
  {
    return keyword;
  }

  /**
   * Sets the field keyword.
   * @param _keyword the new value of the field keyword.
   */
  public void setKeyword(java.lang.String _keyword)
  {
    keyword = _keyword;
  }

  private transient ch.ivy.ws.addon.types.IvyNote note;

  /**
   * Gets the field note.
   * @return the value of the field note; may be null.
   */
  public ch.ivy.ws.addon.types.IvyNote getNote()
  {
    return note;
  }

  /**
   * Sets the field note.
   * @param _note the new value of the field note.
   */
  public void setNote(ch.ivy.ws.addon.types.IvyNote _note)
  {
    note = _note;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyNote> notes;

  /**
   * Gets the field notes.
   * @return the value of the field notes; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyNote> getNotes()
  {
    return notes;
  }

  /**
   * Sets the field notes.
   * @param _notes the new value of the field notes.
   */
  public void setNotes(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyNote> _notes)
  {
    notes = _notes;
  }

  private transient java.lang.Long serverId;

  /**
   * Gets the field serverId.
   * @return the value of the field serverId; may be null.
   */
  public java.lang.Long getServerId()
  {
    return serverId;
  }

  /**
   * Sets the field serverId.
   * @param _serverId the new value of the field serverId.
   */
  public void setServerId(java.lang.Long _serverId)
  {
    serverId = _serverId;
  }

  private transient java.lang.String user;

  /**
   * Gets the field user.
   * @return the value of the field user; may be null.
   */
  public java.lang.String getUser()
  {
    return user;
  }

  /**
   * Sets the field user.
   * @param _user the new value of the field user.
   */
  public void setUser(java.lang.String _user)
  {
    user = _user;
  }

  /**
   * To create URL of a task. E.g. URL of a task = serverUrl + ivy/pro/designer/InternalSupport$1/14B2FC03D2E87141/14B2FC03D2E87141-f7/TaskA.ivp?taskId=2
   */
  private transient java.lang.String serverUrl;

  /**
   * Gets the field serverUrl.
   * @return the value of the field serverUrl; may be null.
   */
  public java.lang.String getServerUrl()
  {
    return serverUrl;
  }

  /**
   * Sets the field serverUrl.
   * @param _serverUrl the new value of the field serverUrl.
   */
  public void setServerUrl(java.lang.String _serverUrl)
  {
    serverUrl = _serverUrl;
  }

  /**
   * For admin to find all running tasks
   */
  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyTask> allIvyTasks;

  /**
   * Gets the field allIvyTasks.
   * @return the value of the field allIvyTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyTask> getAllIvyTasks()
  {
    return allIvyTasks;
  }

  /**
   * Sets the field allIvyTasks.
   * @param _allIvyTasks the new value of the field allIvyTasks.
   */
  public void setAllIvyTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyTask> _allIvyTasks)
  {
    allIvyTasks = _allIvyTasks;
  }

  /**
   * To find tasks related to the case
   */
  private transient ch.ivy.ws.addon.types.IvyCase ivyCase;

  /**
   * Gets the field ivyCase.
   * @return the value of the field ivyCase; may be null.
   */
  public ch.ivy.ws.addon.types.IvyCase getIvyCase()
  {
    return ivyCase;
  }

  /**
   * Sets the field ivyCase.
   * @param _ivyCase the new value of the field ivyCase.
   */
  public void setIvyCase(ch.ivy.ws.addon.types.IvyCase _ivyCase)
  {
    ivyCase = _ivyCase;
  }

  private transient ch.ivy.ws.addon.service.TaskSearchCriteria taskSearchCriteria;

  /**
   * Gets the field taskSearchCriteria.
   * @return the value of the field taskSearchCriteria; may be null.
   */
  public ch.ivy.ws.addon.service.TaskSearchCriteria getTaskSearchCriteria()
  {
    return taskSearchCriteria;
  }

  /**
   * Sets the field taskSearchCriteria.
   * @param _taskSearchCriteria the new value of the field taskSearchCriteria.
   */
  public void setTaskSearchCriteria(ch.ivy.ws.addon.service.TaskSearchCriteria _taskSearchCriteria)
  {
    taskSearchCriteria = _taskSearchCriteria;
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

  private transient java.lang.Integer startIndex;

  /**
   * Gets the field startIndex.
   * @return the value of the field startIndex; may be null.
   */
  public java.lang.Integer getStartIndex()
  {
    return startIndex;
  }

  /**
   * Sets the field startIndex.
   * @param _startIndex the new value of the field startIndex.
   */
  public void setStartIndex(java.lang.Integer _startIndex)
  {
    startIndex = _startIndex;
  }

  private transient java.lang.Integer count;

  /**
   * Gets the field count.
   * @return the value of the field count; may be null.
   */
  public java.lang.Integer getCount()
  {
    return count;
  }

  /**
   * Sets the field count.
   * @param _count the new value of the field count.
   */
  public void setCount(java.lang.Integer _count)
  {
    count = _count;
  }

  /**
   * result of counting number of tasks
   */
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

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> categories;

  /**
   * Gets the field categories.
   * @return the value of the field categories; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getCategories()
  {
    return categories;
  }

  /**
   * Sets the field categories.
   * @param _categories the new value of the field categories.
   */
  public void setCategories(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _categories)
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

  private transient java.lang.Long expireToday;

  /**
   * Gets the field expireToday.
   * @return the value of the field expireToday; may be null.
   */
  public java.lang.Long getExpireToday()
  {
    return expireToday;
  }

  /**
   * Sets the field expireToday.
   * @param _expireToday the new value of the field expireToday.
   */
  public void setExpireToday(java.lang.Long _expireToday)
  {
    expireToday = _expireToday;
  }

  private transient java.lang.Long expireTomorrow;

  /**
   * Gets the field expireTomorrow.
   * @return the value of the field expireTomorrow; may be null.
   */
  public java.lang.Long getExpireTomorrow()
  {
    return expireTomorrow;
  }

  /**
   * Sets the field expireTomorrow.
   * @param _expireTomorrow the new value of the field expireTomorrow.
   */
  public void setExpireTomorrow(java.lang.Long _expireTomorrow)
  {
    expireTomorrow = _expireTomorrow;
  }

  private transient java.lang.Long expireIn2Days;

  /**
   * Gets the field expireIn2Days.
   * @return the value of the field expireIn2Days; may be null.
   */
  public java.lang.Long getExpireIn2Days()
  {
    return expireIn2Days;
  }

  /**
   * Sets the field expireIn2Days.
   * @param _expireIn2Days the new value of the field expireIn2Days.
   */
  public void setExpireIn2Days(java.lang.Long _expireIn2Days)
  {
    expireIn2Days = _expireIn2Days;
  }

  private transient java.lang.Long expireIn3Days;

  /**
   * Gets the field expireIn3Days.
   * @return the value of the field expireIn3Days; may be null.
   */
  public java.lang.Long getExpireIn3Days()
  {
    return expireIn3Days;
  }

  /**
   * Sets the field expireIn3Days.
   * @param _expireIn3Days the new value of the field expireIn3Days.
   */
  public void setExpireIn3Days(java.lang.Long _expireIn3Days)
  {
    expireIn3Days = _expireIn3Days;
  }

  private transient java.lang.Boolean isNoConfigurationMode;

  /**
   * Gets the field isNoConfigurationMode.
   * @return the value of the field isNoConfigurationMode; may be null.
   */
  public java.lang.Boolean getIsNoConfigurationMode()
  {
    return isNoConfigurationMode;
  }

  /**
   * Sets the field isNoConfigurationMode.
   * @param _isNoConfigurationMode the new value of the field isNoConfigurationMode.
   */
  public void setIsNoConfigurationMode(java.lang.Boolean _isNoConfigurationMode)
  {
    isNoConfigurationMode = _isNoConfigurationMode;
  }

}
