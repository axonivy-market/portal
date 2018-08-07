package ch.ivy.add.portalkit.service.integrators;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskServiceIntegratorData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskServiceIntegratorData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -8513443519718219969L;

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyTask> allTasks;

  /**
   * Gets the field allTasks.
   * @return the value of the field allTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyTask> getAllTasks()
  {
    return allTasks;
  }

  /**
   * Sets the field allTasks.
   * @param _allTasks the new value of the field allTasks.
   */
  public void setAllTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyTask> _allTasks)
  {
    allTasks = _allTasks;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> applicationNames;

  /**
   * Gets the field applicationNames.
   * @return the value of the field applicationNames; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getApplicationNames()
  {
    return applicationNames;
  }

  /**
   * Sets the field applicationNames.
   * @param _applicationNames the new value of the field applicationNames.
   */
  public void setApplicationNames(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _applicationNames)
  {
    applicationNames = _applicationNames;
  }

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

  private transient java.lang.Long caseId;

  /**
   * Gets the field caseId.
   * @return the value of the field caseId; may be null.
   */
  public java.lang.Long getCaseId()
  {
    return caseId;
  }

  /**
   * Sets the field caseId.
   * @param _caseId the new value of the field caseId.
   */
  public void setCaseId(java.lang.Long _caseId)
  {
    caseId = _caseId;
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

  private transient java.lang.String endpoint;

  /**
   * Gets the field endpoint.
   * @return the value of the field endpoint; may be null.
   */
  public java.lang.String getEndpoint()
  {
    return endpoint;
  }

  /**
   * Sets the field endpoint.
   * @param _endpoint the new value of the field endpoint.
   */
  public void setEndpoint(java.lang.String _endpoint)
  {
    endpoint = _endpoint;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> _errors)
  {
    errors = _errors;
  }

  private transient ch.ivy.ws.addon.ExpiryStatistic expiryStatistic;

  /**
   * Gets the field expiryStatistic.
   * @return the value of the field expiryStatistic; may be null.
   */
  public ch.ivy.ws.addon.ExpiryStatistic getExpiryStatistic()
  {
    return expiryStatistic;
  }

  /**
   * Sets the field expiryStatistic.
   * @param _expiryStatistic the new value of the field expiryStatistic.
   */
  public void setExpiryStatistic(ch.ivy.ws.addon.ExpiryStatistic _expiryStatistic)
  {
    expiryStatistic = _expiryStatistic;
  }

  private transient ch.ivy.ws.addon.IvySecurityMember ivySecurityMember;

  /**
   * Gets the field ivySecurityMember.
   * @return the value of the field ivySecurityMember; may be null.
   */
  public ch.ivy.ws.addon.IvySecurityMember getIvySecurityMember()
  {
    return ivySecurityMember;
  }

  /**
   * Sets the field ivySecurityMember.
   * @param _ivySecurityMember the new value of the field ivySecurityMember.
   */
  public void setIvySecurityMember(ch.ivy.ws.addon.IvySecurityMember _ivySecurityMember)
  {
    ivySecurityMember = _ivySecurityMember;
  }

  private transient ch.ivy.ws.addon.IvyNote note;

  /**
   * Gets the field note.
   * @return the value of the field note; may be null.
   */
  public ch.ivy.ws.addon.IvyNote getNote()
  {
    return note;
  }

  /**
   * Sets the field note.
   * @param _note the new value of the field note.
   */
  public void setNote(ch.ivy.ws.addon.IvyNote _note)
  {
    note = _note;
  }

  private transient java.lang.String noteContent;

  /**
   * Gets the field noteContent.
   * @return the value of the field noteContent; may be null.
   */
  public java.lang.String getNoteContent()
  {
    return noteContent;
  }

  /**
   * Sets the field noteContent.
   * @param _noteContent the new value of the field noteContent.
   */
  public void setNoteContent(java.lang.String _noteContent)
  {
    noteContent = _noteContent;
  }

  private transient java.lang.String noteCreator;

  /**
   * Gets the field noteCreator.
   * @return the value of the field noteCreator; may be null.
   */
  public java.lang.String getNoteCreator()
  {
    return noteCreator;
  }

  /**
   * Sets the field noteCreator.
   * @param _noteCreator the new value of the field noteCreator.
   */
  public void setNoteCreator(java.lang.String _noteCreator)
  {
    noteCreator = _noteCreator;
  }

  private transient java.lang.String parkTaskRequester;

  /**
   * Gets the field parkTaskRequester.
   * @return the value of the field parkTaskRequester; may be null.
   */
  public java.lang.String getParkTaskRequester()
  {
    return parkTaskRequester;
  }

  /**
   * Sets the field parkTaskRequester.
   * @param _parkTaskRequester the new value of the field parkTaskRequester.
   */
  public void setParkTaskRequester(java.lang.String _parkTaskRequester)
  {
    parkTaskRequester = _parkTaskRequester;
  }

  private transient ch.ivy.ws.addon.PriorityStatistic priorityStatistic;

  /**
   * Gets the field priorityStatistic.
   * @return the value of the field priorityStatistic; may be null.
   */
  public ch.ivy.ws.addon.PriorityStatistic getPriorityStatistic()
  {
    return priorityStatistic;
  }

  /**
   * Sets the field priorityStatistic.
   * @param _priorityStatistic the new value of the field priorityStatistic.
   */
  public void setPriorityStatistic(ch.ivy.ws.addon.PriorityStatistic _priorityStatistic)
  {
    priorityStatistic = _priorityStatistic;
  }

  private transient java.lang.String resumeTaskRequester;

  /**
   * Gets the field resumeTaskRequester.
   * @return the value of the field resumeTaskRequester; may be null.
   */
  public java.lang.String getResumeTaskRequester()
  {
    return resumeTaskRequester;
  }

  /**
   * Sets the field resumeTaskRequester.
   * @param _resumeTaskRequester the new value of the field resumeTaskRequester.
   */
  public void setResumeTaskRequester(java.lang.String _resumeTaskRequester)
  {
    resumeTaskRequester = _resumeTaskRequester;
  }

  private transient ch.ivy.addon.portalkit.persistence.domain.Server server;

  /**
   * Gets the field server.
   * @return the value of the field server; may be null.
   */
  public ch.ivy.addon.portalkit.persistence.domain.Server getServer()
  {
    return server;
  }

  /**
   * Sets the field server.
   * @param _server the new value of the field server.
   */
  public void setServer(ch.ivy.addon.portalkit.persistence.domain.Server _server)
  {
    server = _server;
  }

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

  private transient ch.ivy.ws.addon.IvyTask task;

  /**
   * Gets the field task.
   * @return the value of the field task; may be null.
   */
  public ch.ivy.ws.addon.IvyTask getTask()
  {
    return task;
  }

  /**
   * Sets the field task.
   * @param _task the new value of the field task.
   */
  public void setTask(ch.ivy.ws.addon.IvyTask _task)
  {
    task = _task;
  }

  private transient java.lang.String taskCategoryRequester;

  /**
   * Gets the field taskCategoryRequester.
   * @return the value of the field taskCategoryRequester; may be null.
   */
  public java.lang.String getTaskCategoryRequester()
  {
    return taskCategoryRequester;
  }

  /**
   * Sets the field taskCategoryRequester.
   * @param _taskCategoryRequester the new value of the field taskCategoryRequester.
   */
  public void setTaskCategoryRequester(java.lang.String _taskCategoryRequester)
  {
    taskCategoryRequester = _taskCategoryRequester;
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

  private transient java.lang.Long taskId;

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

  private transient ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria;

  /**
   * Gets the field taskSearchCriteria.
   * @return the value of the field taskSearchCriteria; may be null.
   */
  public ch.ivy.ws.addon.TaskSearchCriteria getTaskSearchCriteria()
  {
    return taskSearchCriteria;
  }

  /**
   * Sets the field taskSearchCriteria.
   * @param _taskSearchCriteria the new value of the field taskSearchCriteria.
   */
  public void setTaskSearchCriteria(ch.ivy.ws.addon.TaskSearchCriteria _taskSearchCriteria)
  {
    taskSearchCriteria = _taskSearchCriteria;
  }

  private transient java.lang.String taskStatisticRequester;

  /**
   * Gets the field taskStatisticRequester.
   * @return the value of the field taskStatisticRequester; may be null.
   */
  public java.lang.String getTaskStatisticRequester()
  {
    return taskStatisticRequester;
  }

  /**
   * Sets the field taskStatisticRequester.
   * @param _taskStatisticRequester the new value of the field taskStatisticRequester.
   */
  public void setTaskStatisticRequester(java.lang.String _taskStatisticRequester)
  {
    taskStatisticRequester = _taskStatisticRequester;
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
