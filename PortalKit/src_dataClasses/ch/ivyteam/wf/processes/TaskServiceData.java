package ch.ivyteam.wf.processes;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -936045901752703251L;

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyApplication> applications;

  /**
   * Gets the field applications.
   * @return the value of the field applications; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyApplication> getApplications()
  {
    return applications;
  }

  /**
   * Sets the field applications.
   * @param _applications the new value of the field applications.
   */
  public void setApplications(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyApplication> _applications)
  {
    applications = _applications;
  }

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

  private transient ch.ivy.ws.addon.WsException errorWs;

  /**
   * Gets the field errorWs.
   * @return the value of the field errorWs; may be null.
   */
  public ch.ivy.ws.addon.WsException getErrorWs()
  {
    return errorWs;
  }

  /**
   * Sets the field errorWs.
   * @param _errorWs the new value of the field errorWs.
   */
  public void setErrorWs(ch.ivy.ws.addon.WsException _errorWs)
  {
    errorWs = _errorWs;
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

  private transient ch.ivy.ws.addon.IvyTask ivyTask;

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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyTask> ivyTasks;

  /**
   * Gets the field ivyTasks.
   * @return the value of the field ivyTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyTask> getIvyTasks()
  {
    return ivyTasks;
  }

  /**
   * Sets the field ivyTasks.
   * @param _ivyTasks the new value of the field ivyTasks.
   */
  public void setIvyTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyTask> _ivyTasks)
  {
    ivyTasks = _ivyTasks;
  }

  private transient java.util.ListIterator listIterator;

  /**
   * Gets the field listIterator.
   * @return the value of the field listIterator; may be null.
   */
  public java.util.ListIterator getListIterator()
  {
    return listIterator;
  }

  /**
   * Sets the field listIterator.
   * @param _listIterator the new value of the field listIterator.
   */
  public void setListIterator(java.util.ListIterator _listIterator)
  {
    listIterator = _listIterator;
  }

  private transient ch.ivy.addon.portalkit.bo.PortalConfiguration portalConfig;

  /**
   * Gets the field portalConfig.
   * @return the value of the field portalConfig; may be null.
   */
  public ch.ivy.addon.portalkit.bo.PortalConfiguration getPortalConfig()
  {
    return portalConfig;
  }

  /**
   * Sets the field portalConfig.
   * @param _portalConfig the new value of the field portalConfig.
   */
  public void setPortalConfig(ch.ivy.addon.portalkit.bo.PortalConfiguration _portalConfig)
  {
    portalConfig = _portalConfig;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> tasks;

  /**
   * Gets the field tasks.
   * @return the value of the field tasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> getTasks()
  {
    return tasks;
  }

  /**
   * Sets the field tasks.
   * @param _tasks the new value of the field tasks.
   */
  public void setTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> _tasks)
  {
    tasks = _tasks;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> tempErrors;

  /**
   * Gets the field tempErrors.
   * @return the value of the field tempErrors; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> getTempErrors()
  {
    return tempErrors;
  }

  /**
   * Sets the field tempErrors.
   * @param _tempErrors the new value of the field tempErrors.
   */
  public void setTempErrors(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> _tempErrors)
  {
    tempErrors = _tempErrors;
  }

  private transient java.lang.String userName;

  /**
   * Gets the field userName.
   * @return the value of the field userName; may be null.
   */
  public java.lang.String getUserName()
  {
    return userName;
  }

  /**
   * Sets the field userName.
   * @param _userName the new value of the field userName.
   */
  public void setUserName(java.lang.String _userName)
  {
    userName = _userName;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyTask> allIvyTasks;

  /**
   * Gets the field allIvyTasks.
   * @return the value of the field allIvyTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyTask> getAllIvyTasks()
  {
    return allIvyTasks;
  }

  /**
   * Sets the field allIvyTasks.
   * @param _allIvyTasks the new value of the field allIvyTasks.
   */
  public void setAllIvyTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyTask> _allIvyTasks)
  {
    allIvyTasks = _allIvyTasks;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> allTasks;

  /**
   * Gets the field allTasks.
   * @return the value of the field allTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> getAllTasks()
  {
    return allTasks;
  }

  /**
   * Sets the field allTasks.
   * @param _allTasks the new value of the field allTasks.
   */
  public void setAllTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> _allTasks)
  {
    allTasks = _allTasks;
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

  private transient java.util.List<ch.ivy.addon.portalkit.persistence.domain.Server> servers;

  /**
   * Gets the field servers.
   * @return the value of the field servers; may be null.
   */
  public java.util.List<ch.ivy.addon.portalkit.persistence.domain.Server> getServers()
  {
    return servers;
  }

  /**
   * Sets the field servers.
   * @param _servers the new value of the field servers.
   */
  public void setServers(java.util.List<ch.ivy.addon.portalkit.persistence.domain.Server> _servers)
  {
    servers = _servers;
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

  private transient java.lang.Long taskCountOfOneServer;

  /**
   * Gets the field taskCountOfOneServer.
   * @return the value of the field taskCountOfOneServer; may be null.
   */
  public java.lang.Long getTaskCountOfOneServer()
  {
    return taskCountOfOneServer;
  }

  /**
   * Sets the field taskCountOfOneServer.
   * @param _taskCountOfOneServer the new value of the field taskCountOfOneServer.
   */
  public void setTaskCountOfOneServer(java.lang.Long _taskCountOfOneServer)
  {
    taskCountOfOneServer = _taskCountOfOneServer;
  }

  /**
   * task count of all servers
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

  private transient ch.ivy.ws.addon.PriorityStatistic tempPriorityStatistic;

  /**
   * Gets the field tempPriorityStatistic.
   * @return the value of the field tempPriorityStatistic; may be null.
   */
  public ch.ivy.ws.addon.PriorityStatistic getTempPriorityStatistic()
  {
    return tempPriorityStatistic;
  }

  /**
   * Sets the field tempPriorityStatistic.
   * @param _tempPriorityStatistic the new value of the field tempPriorityStatistic.
   */
  public void setTempPriorityStatistic(ch.ivy.ws.addon.PriorityStatistic _tempPriorityStatistic)
  {
    tempPriorityStatistic = _tempPriorityStatistic;
  }

  private transient ch.ivy.ws.addon.ExpiryStatistic tempExpiryStatistic;

  /**
   * Gets the field tempExpiryStatistic.
   * @return the value of the field tempExpiryStatistic; may be null.
   */
  public ch.ivy.ws.addon.ExpiryStatistic getTempExpiryStatistic()
  {
    return tempExpiryStatistic;
  }

  /**
   * Sets the field tempExpiryStatistic.
   * @param _tempExpiryStatistic the new value of the field tempExpiryStatistic.
   */
  public void setTempExpiryStatistic(ch.ivy.ws.addon.ExpiryStatistic _tempExpiryStatistic)
  {
    tempExpiryStatistic = _tempExpiryStatistic;
  }

  private transient java.lang.Boolean hasNoSelectedApp;

  /**
   * Gets the field hasNoSelectedApp.
   * @return the value of the field hasNoSelectedApp; may be null.
   */
  public java.lang.Boolean getHasNoSelectedApp()
  {
    return hasNoSelectedApp;
  }

  /**
   * Sets the field hasNoSelectedApp.
   * @param _hasNoSelectedApp the new value of the field hasNoSelectedApp.
   */
  public void setHasNoSelectedApp(java.lang.Boolean _hasNoSelectedApp)
  {
    hasNoSelectedApp = _hasNoSelectedApp;
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
