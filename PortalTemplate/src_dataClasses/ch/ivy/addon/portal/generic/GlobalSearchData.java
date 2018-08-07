package ch.ivy.addon.portal.generic;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class GlobalSearchData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class GlobalSearchData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 766378131604771935L;

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteCase> cases;

  /**
   * Gets the field cases.
   * @return the value of the field cases; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteCase> getCases()
  {
    return cases;
  }

  /**
   * Sets the field cases.
   * @param _cases the new value of the field cases.
   */
  public void setCases(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteCase> _cases)
  {
    cases = _cases;
  }

  private transient ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria;

  /**
   * Gets the field caseSearchCriteria.
   * @return the value of the field caseSearchCriteria; may be null.
   */
  public ch.ivy.ws.addon.CaseSearchCriteria getCaseSearchCriteria()
  {
    return caseSearchCriteria;
  }

  /**
   * Sets the field caseSearchCriteria.
   * @param _caseSearchCriteria the new value of the field caseSearchCriteria.
   */
  public void setCaseSearchCriteria(ch.ivy.ws.addon.CaseSearchCriteria _caseSearchCriteria)
  {
    caseSearchCriteria = _caseSearchCriteria;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.IProcessStart> processes;

  /**
   * Gets the field processes.
   * @return the value of the field processes; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.IProcessStart> getProcesses()
  {
    return processes;
  }

  /**
   * Sets the field processes.
   * @param _processes the new value of the field processes.
   */
  public void setProcesses(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.IProcessStart> _processes)
  {
    processes = _processes;
  }

  private transient ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria;

  /**
   * Gets the field processSearchCriteria.
   * @return the value of the field processSearchCriteria; may be null.
   */
  public ch.ivy.ws.addon.ProcessSearchCriteria getProcessSearchCriteria()
  {
    return processSearchCriteria;
  }

  /**
   * Sets the field processSearchCriteria.
   * @param _processSearchCriteria the new value of the field processSearchCriteria.
   */
  public void setProcessSearchCriteria(ch.ivy.ws.addon.ProcessSearchCriteria _processSearchCriteria)
  {
    processSearchCriteria = _processSearchCriteria;
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

  private transient java.lang.String username;

  /**
   * Gets the field username.
   * @return the value of the field username; may be null.
   */
  public java.lang.String getUsername()
  {
    return username;
  }

  /**
   * Sets the field username.
   * @param _username the new value of the field username.
   */
  public void setUsername(java.lang.String _username)
  {
    username = _username;
  }

  private transient java.lang.String language;

  /**
   * Gets the field language.
   * @return the value of the field language; may be null.
   */
  public java.lang.String getLanguage()
  {
    return language;
  }

  /**
   * Sets the field language.
   * @param _language the new value of the field language.
   */
  public void setLanguage(java.lang.String _language)
  {
    language = _language;
  }

  /**
   * the id of the selected server
   */
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

  /**
   * the name of the selected application
   */
  private transient java.lang.String applicationName;

  /**
   * Gets the field applicationName.
   * @return the value of the field applicationName; may be null.
   */
  public java.lang.String getApplicationName()
  {
    return applicationName;
  }

  /**
   * Sets the field applicationName.
   * @param _applicationName the new value of the field applicationName.
   */
  public void setApplicationName(java.lang.String _applicationName)
  {
    applicationName = _applicationName;
  }

}
