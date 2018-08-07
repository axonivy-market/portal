package ch.ivy.addon.portalkit.singleapp.cases.CaseNote;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseNoteData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseNoteData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 8515297729979265124L;

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

  private ch.ivyteam.ivy.workflow.ICase currentCase;

  /**
   * Gets the field currentCase.
   * @return the value of the field currentCase; may be null.
   */
  public ch.ivyteam.ivy.workflow.ICase getCurrentCase()
  {
    return currentCase;
  }

  /**
   * Sets the field currentCase.
   * @param _currentCase the new value of the field currentCase.
   */
  public void setCurrentCase(ch.ivyteam.ivy.workflow.ICase _currentCase)
  {
    currentCase = _currentCase;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.INote> notes;

  /**
   * Gets the field notes.
   * @return the value of the field notes; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.INote> getNotes()
  {
    return notes;
  }

  /**
   * Sets the field notes.
   * @param _notes the new value of the field notes.
   */
  public void setNotes(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.INote> _notes)
  {
    notes = _notes;
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

}
