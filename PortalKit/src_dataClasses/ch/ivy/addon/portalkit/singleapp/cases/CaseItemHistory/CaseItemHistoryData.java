package ch.ivy.addon.portalkit.singleapp.cases.CaseItemHistory;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseItemHistoryData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseItemHistoryData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 7615617476748565064L;

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

  private ch.ivyteam.ivy.workflow.INote note;

  /**
   * Gets the field note.
   * @return the value of the field note; may be null.
   */
  public ch.ivyteam.ivy.workflow.INote getNote()
  {
    return note;
  }

  /**
   * Sets the field note.
   * @param _note the new value of the field note.
   */
  public void setNote(ch.ivyteam.ivy.workflow.INote _note)
  {
    note = _note;
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
