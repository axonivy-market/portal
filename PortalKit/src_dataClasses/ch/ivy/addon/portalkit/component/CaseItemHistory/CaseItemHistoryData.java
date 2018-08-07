package ch.ivy.addon.portalkit.component.CaseItemHistory;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseItemHistoryData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseItemHistoryData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -4509782130381216435L;

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

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> remoteTasks;

  /**
   * Gets the field remoteTasks.
   * @return the value of the field remoteTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> getRemoteTasks()
  {
    return remoteTasks;
  }

  /**
   * Sets the field remoteTasks.
   * @param _remoteTasks the new value of the field remoteTasks.
   */
  public void setRemoteTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> _remoteTasks)
  {
    remoteTasks = _remoteTasks;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.History> histories;

  /**
   * Gets the field histories.
   * @return the value of the field histories; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.History> getHistories()
  {
    return histories;
  }

  /**
   * Sets the field histories.
   * @param _histories the new value of the field histories.
   */
  public void setHistories(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.History> _histories)
  {
    histories = _histories;
  }

  private java.lang.String noteContent;

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

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> errors;

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

  private ch.ivy.ws.addon.IvyNote note;

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

  private java.lang.String fullNote;

  /**
   * Gets the field fullNote.
   * @return the value of the field fullNote; may be null.
   */
  public java.lang.String getFullNote()
  {
    return fullNote;
  }

  /**
   * Sets the field fullNote.
   * @param _fullNote the new value of the field fullNote.
   */
  public void setFullNote(java.lang.String _fullNote)
  {
    fullNote = _fullNote;
  }

}
