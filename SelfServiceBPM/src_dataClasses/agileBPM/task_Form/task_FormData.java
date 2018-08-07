package agileBPM.task_Form;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class task_FormData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class task_FormData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 8207601245424393825L;

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

  private transient selfServiceBPM.TaskDef nextTask;

  /**
   * Gets the field nextTask.
   * @return the value of the field nextTask; may be null.
   */
  public selfServiceBPM.TaskDef getNextTask()
  {
    return nextTask;
  }

  /**
   * Sets the field nextTask.
   * @param _nextTask the new value of the field nextTask.
   */
  public void setNextTask(selfServiceBPM.TaskDef _nextTask)
  {
    nextTask = _nextTask;
  }

  private transient java.lang.String ok;

  /**
   * Gets the field ok.
   * @return the value of the field ok; may be null.
   */
  public java.lang.String getOk()
  {
    return ok;
  }

  /**
   * Sets the field ok.
   * @param _ok the new value of the field ok.
   */
  public void setOk(java.lang.String _ok)
  {
    ok = _ok;
  }

  private transient java.lang.String answer;

  /**
   * Gets the field answer.
   * @return the value of the field answer; may be null.
   */
  public java.lang.String getAnswer()
  {
    return answer;
  }

  /**
   * Sets the field answer.
   * @param _answer the new value of the field answer.
   */
  public void setAnswer(java.lang.String _answer)
  {
    answer = _answer;
  }

  private java.lang.Boolean done;

  /**
   * Gets the field done.
   * @return the value of the field done; may be null.
   */
  public java.lang.Boolean getDone()
  {
    return done;
  }

  /**
   * Sets the field done.
   * @param _done the new value of the field done.
   */
  public void setDone(java.lang.Boolean _done)
  {
    done = _done;
  }

  private ch.ivyteam.ivy.scripting.objects.List<selfServiceBPM.TaskDef> definedTasks;

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

  private ch.ivyteam.ivy.scripting.objects.List<selfServiceBPM.TaskDef> taskHistory;

  /**
   * Gets the field taskHistory.
   * @return the value of the field taskHistory; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<selfServiceBPM.TaskDef> getTaskHistory()
  {
    return taskHistory;
  }

  /**
   * Sets the field taskHistory.
   * @param _taskHistory the new value of the field taskHistory.
   */
  public void setTaskHistory(ch.ivyteam.ivy.scripting.objects.List<selfServiceBPM.TaskDef> _taskHistory)
  {
    taskHistory = _taskHistory;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.INote> caseNotes;

  /**
   * Gets the field caseNotes.
   * @return the value of the field caseNotes; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.INote> getCaseNotes()
  {
    return caseNotes;
  }

  /**
   * Sets the field caseNotes.
   * @param _caseNotes the new value of the field caseNotes.
   */
  public void setCaseNotes(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.INote> _caseNotes)
  {
    caseNotes = _caseNotes;
  }

  private selfServiceBPM.TaskDef addTask;

  /**
   * Gets the field addTask.
   * @return the value of the field addTask; may be null.
   */
  public selfServiceBPM.TaskDef getAddTask()
  {
    return addTask;
  }

  /**
   * Sets the field addTask.
   * @param _addTask the new value of the field addTask.
   */
  public void setAddTask(selfServiceBPM.TaskDef _addTask)
  {
    addTask = _addTask;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> wfusers;

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

  private java.lang.String remark;

  /**
   * Gets the field remark.
   * @return the value of the field remark; may be null.
   */
  public java.lang.String getRemark()
  {
    return remark;
  }

  /**
   * Sets the field remark.
   * @param _remark the new value of the field remark.
   */
  public void setRemark(java.lang.String _remark)
  {
    remark = _remark;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> userSelection;

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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> userList;

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

  private java.lang.String msg;

  /**
   * Gets the field msg.
   * @return the value of the field msg; may be null.
   */
  public java.lang.String getMsg()
  {
    return msg;
  }

  /**
   * Sets the field msg.
   * @param _msg the new value of the field msg.
   */
  public void setMsg(java.lang.String _msg)
  {
    msg = _msg;
  }

}
