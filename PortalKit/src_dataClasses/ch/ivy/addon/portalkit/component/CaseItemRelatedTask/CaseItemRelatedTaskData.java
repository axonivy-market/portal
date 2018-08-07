package ch.ivy.addon.portalkit.component.CaseItemRelatedTask;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseItemRelatedTaskData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseItemRelatedTaskData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -7794910225491576215L;

  private ch.ivy.addon.portalkit.bo.RemoteCase remoteCase;

  /**
   * Gets the field remoteCase.
   * @return the value of the field remoteCase; may be null.
   */
  public ch.ivy.addon.portalkit.bo.RemoteCase getRemoteCase()
  {
    return remoteCase;
  }

  /**
   * Sets the field remoteCase.
   * @param _remoteCase the new value of the field remoteCase.
   */
  public void setRemoteCase(ch.ivy.addon.portalkit.bo.RemoteCase _remoteCase)
  {
    remoteCase = _remoteCase;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> relatedTasks;

  /**
   * Gets the field relatedTasks.
   * @return the value of the field relatedTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> getRelatedTasks()
  {
    return relatedTasks;
  }

  /**
   * Sets the field relatedTasks.
   * @param _relatedTasks the new value of the field relatedTasks.
   */
  public void setRelatedTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> _relatedTasks)
  {
    relatedTasks = _relatedTasks;
  }

  private ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria;

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

  private java.lang.String caseName;

  /**
   * Gets the field caseName.
   * @return the value of the field caseName; may be null.
   */
  public java.lang.String getCaseName()
  {
    return caseName;
  }

  /**
   * Sets the field caseName.
   * @param _caseName the new value of the field caseName.
   */
  public void setCaseName(java.lang.String _caseName)
  {
    caseName = _caseName;
  }

  private ch.ivy.addon.portalkit.dto.GlobalCaseId caseId;

  /**
   * Gets the field caseId.
   * @return the value of the field caseId; may be null.
   */
  public ch.ivy.addon.portalkit.dto.GlobalCaseId getCaseId()
  {
    return caseId;
  }

  /**
   * Sets the field caseId.
   * @param _caseId the new value of the field caseId.
   */
  public void setCaseId(ch.ivy.addon.portalkit.dto.GlobalCaseId _caseId)
  {
    caseId = _caseId;
  }

}
