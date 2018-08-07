package ch.ivy.addon.portalkit.singleapp.cases.CaseHistory;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseHistoryData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseHistoryData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -8120349433482293679L;

  private java.util.List<ch.ivy.addon.portalkit.vo.TaskVO> tasks;

  /**
   * Gets the field tasks.
   * @return the value of the field tasks; may be null.
   */
  public java.util.List<ch.ivy.addon.portalkit.vo.TaskVO> getTasks()
  {
    return tasks;
  }

  /**
   * Sets the field tasks.
   * @param _tasks the new value of the field tasks.
   */
  public void setTasks(java.util.List<ch.ivy.addon.portalkit.vo.TaskVO> _tasks)
  {
    tasks = _tasks;
  }

  private java.util.List<ch.ivy.addon.portalkit.vo.TaskVO> filteredTasks;

  /**
   * Gets the field filteredTasks.
   * @return the value of the field filteredTasks; may be null.
   */
  public java.util.List<ch.ivy.addon.portalkit.vo.TaskVO> getFilteredTasks()
  {
    return filteredTasks;
  }

  /**
   * Sets the field filteredTasks.
   * @param _filteredTasks the new value of the field filteredTasks.
   */
  public void setFilteredTasks(java.util.List<ch.ivy.addon.portalkit.vo.TaskVO> _filteredTasks)
  {
    filteredTasks = _filteredTasks;
  }

  private java.lang.Number caseId;

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

  private ch.ivyteam.ivy.workflow.ICase wfCase;

  /**
   * Gets the field wfCase.
   * @return the value of the field wfCase; may be null.
   */
  public ch.ivyteam.ivy.workflow.ICase getWfCase()
  {
    return wfCase;
  }

  /**
   * Sets the field wfCase.
   * @param _wfCase the new value of the field wfCase.
   */
  public void setWfCase(ch.ivyteam.ivy.workflow.ICase _wfCase)
  {
    wfCase = _wfCase;
  }

  private ch.ivyteam.ivy.workflow.ITask selectedTask;

  /**
   * Gets the field selectedTask.
   * @return the value of the field selectedTask; may be null.
   */
  public ch.ivyteam.ivy.workflow.ITask getSelectedTask()
  {
    return selectedTask;
  }

  /**
   * Sets the field selectedTask.
   * @param _selectedTask the new value of the field selectedTask.
   */
  public void setSelectedTask(ch.ivyteam.ivy.workflow.ITask _selectedTask)
  {
    selectedTask = _selectedTask;
  }

  private java.lang.String comment;

  /**
   * Gets the field comment.
   * @return the value of the field comment; may be null.
   */
  public java.lang.String getComment()
  {
    return comment;
  }

  /**
   * Sets the field comment.
   * @param _comment the new value of the field comment.
   */
  public void setComment(java.lang.String _comment)
  {
    comment = _comment;
  }

  private java.util.List<ch.ivyteam.ivy.workflow.ITask> iTasks;

  /**
   * Gets the field iTasks.
   * @return the value of the field iTasks; may be null.
   */
  public java.util.List<ch.ivyteam.ivy.workflow.ITask> getITasks()
  {
    return iTasks;
  }

  /**
   * Sets the field iTasks.
   * @param _iTasks the new value of the field iTasks.
   */
  public void setITasks(java.util.List<ch.ivyteam.ivy.workflow.ITask> _iTasks)
  {
    iTasks = _iTasks;
  }

}
