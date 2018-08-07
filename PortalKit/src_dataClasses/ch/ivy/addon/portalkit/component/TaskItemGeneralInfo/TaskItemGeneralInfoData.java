package ch.ivy.addon.portalkit.component.TaskItemGeneralInfo;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskItemGeneralInfoData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskItemGeneralInfoData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 2446929902108838747L;

  private ch.ivy.addon.portalkit.dto.GlobalCaseId globalCaseId;

  /**
   * Gets the field globalCaseId.
   * @return the value of the field globalCaseId; may be null.
   */
  public ch.ivy.addon.portalkit.dto.GlobalCaseId getGlobalCaseId()
  {
    return globalCaseId;
  }

  /**
   * Sets the field globalCaseId.
   * @param _globalCaseId the new value of the field globalCaseId.
   */
  public void setGlobalCaseId(ch.ivy.addon.portalkit.dto.GlobalCaseId _globalCaseId)
  {
    globalCaseId = _globalCaseId;
  }

  private ch.ivy.ws.addon.IvyTask ivyTask;

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

  private ch.ivy.addon.portalkit.bo.RemoteTask task;

  /**
   * Gets the field task.
   * @return the value of the field task; may be null.
   */
  public ch.ivy.addon.portalkit.bo.RemoteTask getTask()
  {
    return task;
  }

  /**
   * Sets the field task.
   * @param _task the new value of the field task.
   */
  public void setTask(ch.ivy.addon.portalkit.bo.RemoteTask _task)
  {
    task = _task;
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

  private java.lang.String changeDeadlineNoteContent;

  /**
   * Gets the field changeDeadlineNoteContent.
   * @return the value of the field changeDeadlineNoteContent; may be null.
   */
  public java.lang.String getChangeDeadlineNoteContent()
  {
    return changeDeadlineNoteContent;
  }

  /**
   * Sets the field changeDeadlineNoteContent.
   * @param _changeDeadlineNoteContent the new value of the field changeDeadlineNoteContent.
   */
  public void setChangeDeadlineNoteContent(java.lang.String _changeDeadlineNoteContent)
  {
    changeDeadlineNoteContent = _changeDeadlineNoteContent;
  }

  private java.lang.String oldNameValue;

  /**
   * Gets the field oldNameValue.
   * @return the value of the field oldNameValue; may be null.
   */
  public java.lang.String getOldNameValue()
  {
    return oldNameValue;
  }

  /**
   * Sets the field oldNameValue.
   * @param _oldNameValue the new value of the field oldNameValue.
   */
  public void setOldNameValue(java.lang.String _oldNameValue)
  {
    oldNameValue = _oldNameValue;
  }

}
