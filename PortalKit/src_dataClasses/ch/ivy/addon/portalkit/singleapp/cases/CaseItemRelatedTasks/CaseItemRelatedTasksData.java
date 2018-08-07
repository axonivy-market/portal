package ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseItemRelatedTasksData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseItemRelatedTasksData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -5207183887546540587L;

  private java.lang.Long caseId;

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

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ITask> relatedTasks;

  /**
   * Gets the field relatedTasks.
   * @return the value of the field relatedTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ITask> getRelatedTasks()
  {
    return relatedTasks;
  }

  /**
   * Sets the field relatedTasks.
   * @param _relatedTasks the new value of the field relatedTasks.
   */
  public void setRelatedTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ITask> _relatedTasks)
  {
    relatedTasks = _relatedTasks;
  }

  private ch.ivy.addon.portalkit.persistence.domain.Server currentServer;

  /**
   * Gets the field currentServer.
   * @return the value of the field currentServer; may be null.
   */
  public ch.ivy.addon.portalkit.persistence.domain.Server getCurrentServer()
  {
    return currentServer;
  }

  /**
   * Sets the field currentServer.
   * @param _currentServer the new value of the field currentServer.
   */
  public void setCurrentServer(ch.ivy.addon.portalkit.persistence.domain.Server _currentServer)
  {
    currentServer = _currentServer;
  }

  private ch.ivyteam.ivy.workflow.ICase internalCase;

  /**
   * Gets the field internalCase.
   * @return the value of the field internalCase; may be null.
   */
  public ch.ivyteam.ivy.workflow.ICase getInternalCase()
  {
    return internalCase;
  }

  /**
   * Sets the field internalCase.
   * @param _internalCase the new value of the field internalCase.
   */
  public void setInternalCase(ch.ivyteam.ivy.workflow.ICase _internalCase)
  {
    internalCase = _internalCase;
  }

  private java.lang.Long taskId;

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

}
