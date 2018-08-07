package agileBPM.define_WF;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class define_WFData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class define_WFData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 848368757301222205L;

  private transient ch.ivyteam.ivy.scripting.objects.List<selfServiceBPM.TaskDef> definedTasks;

  /**
   * Gets the field definedTasks.
   * @return the value of the field definedTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<selfServiceBPM.TaskDef> getDefinedTasks()
  {
    return definedTasks;
  }

  /**
   * Sets the field definedTasks.
   * @param _definedTasks the new value of the field definedTasks.
   */
  public void setDefinedTasks(ch.ivyteam.ivy.scripting.objects.List<selfServiceBPM.TaskDef> _definedTasks)
  {
    definedTasks = _definedTasks;
  }

  private transient selfServiceBPM.CaseDef caseInfo;

  /**
   * Gets the field caseInfo.
   * @return the value of the field caseInfo; may be null.
   */
  public selfServiceBPM.CaseDef getCaseInfo()
  {
    return caseInfo;
  }

  /**
   * Sets the field caseInfo.
   * @param _caseInfo the new value of the field caseInfo.
   */
  public void setCaseInfo(selfServiceBPM.CaseDef _caseInfo)
  {
    caseInfo = _caseInfo;
  }

  private selfServiceBPM.TaskDef newTask;

  /**
   * Gets the field newTask.
   * @return the value of the field newTask; may be null.
   */
  public selfServiceBPM.TaskDef getNewTask()
  {
    return newTask;
  }

  /**
   * Sets the field newTask.
   * @param _newTask the new value of the field newTask.
   */
  public void setNewTask(selfServiceBPM.TaskDef _newTask)
  {
    newTask = _newTask;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> wfusers;

  /**
   * Gets the field wfusers.
   * @return the value of the field wfusers; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getWfusers()
  {
    return wfusers;
  }

  /**
   * Sets the field wfusers.
   * @param _wfusers the new value of the field wfusers.
   */
  public void setWfusers(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _wfusers)
  {
    wfusers = _wfusers;
  }

  private ch.ivyteam.ivy.scripting.objects.DateTime started;

  /**
   * Gets the field started.
   * @return the value of the field started; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.DateTime getStarted()
  {
    return started;
  }

  /**
   * Sets the field started.
   * @param _started the new value of the field started.
   */
  public void setStarted(ch.ivyteam.ivy.scripting.objects.DateTime _started)
  {
    started = _started;
  }

  private ch.ivyteam.ivy.scripting.objects.List<selfServiceBPM.TaskDef> additionalTasks;

  /**
   * Gets the field additionalTasks.
   * @return the value of the field additionalTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<selfServiceBPM.TaskDef> getAdditionalTasks()
  {
    return additionalTasks;
  }

  /**
   * Sets the field additionalTasks.
   * @param _additionalTasks the new value of the field additionalTasks.
   */
  public void setAdditionalTasks(ch.ivyteam.ivy.scripting.objects.List<selfServiceBPM.TaskDef> _additionalTasks)
  {
    additionalTasks = _additionalTasks;
  }

  private java.lang.String errormsg;

  /**
   * Gets the field errormsg.
   * @return the value of the field errormsg; may be null.
   */
  public java.lang.String getErrormsg()
  {
    return errormsg;
  }

  /**
   * Sets the field errormsg.
   * @param _errormsg the new value of the field errormsg.
   */
  public void setErrormsg(java.lang.String _errormsg)
  {
    errormsg = _errormsg;
  }

  private java.lang.String user;

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

  private selfServiceBPM.TaskDef editTask;

  /**
   * Gets the field editTask.
   * @return the value of the field editTask; may be null.
   */
  public selfServiceBPM.TaskDef getEditTask()
  {
    return editTask;
  }

  /**
   * Sets the field editTask.
   * @param _editTask the new value of the field editTask.
   */
  public void setEditTask(selfServiceBPM.TaskDef _editTask)
  {
    editTask = _editTask;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> userList;

  /**
   * Gets the field userList.
   * @return the value of the field userList; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> getUserList()
  {
    return userList;
  }

  /**
   * Sets the field userList.
   * @param _userList the new value of the field userList.
   */
  public void setUserList(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> _userList)
  {
    userList = _userList;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> userSelection;

  /**
   * Gets the field userSelection.
   * @return the value of the field userSelection; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> getUserSelection()
  {
    return userSelection;
  }

  /**
   * Sets the field userSelection.
   * @param _userSelection the new value of the field userSelection.
   */
  public void setUserSelection(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> _userSelection)
  {
    userSelection = _userSelection;
  }

  private java.lang.String taskDescription;

  /**
   * Gets the field taskDescription.
   * @return the value of the field taskDescription; may be null.
   */
  public java.lang.String getTaskDescription()
  {
    return taskDescription;
  }

  /**
   * Sets the field taskDescription.
   * @param _taskDescription the new value of the field taskDescription.
   */
  public void setTaskDescription(java.lang.String _taskDescription)
  {
    taskDescription = _taskDescription;
  }

}
