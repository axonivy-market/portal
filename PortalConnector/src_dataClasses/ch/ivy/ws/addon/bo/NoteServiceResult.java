package ch.ivy.ws.addon.bo;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class NoteServiceResult", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class NoteServiceResult extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 3352953885191763194L;

  private transient java.util.List<ch.ivy.ws.addon.types.IvyNote> notes;

  /**
   * Gets the field notes.
   * @return the value of the field notes; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.IvyNote> getNotes()
  {
    return notes;
  }

  /**
   * Sets the field notes.
   * @param _notes the new value of the field notes.
   */
  public void setNotes(java.util.List<ch.ivy.ws.addon.types.IvyNote> _notes)
  {
    notes = _notes;
  }

  private transient java.util.List<ch.ivy.ws.addon.WSException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> _errors)
  {
    errors = _errors;
  }

  private transient ch.ivy.ws.addon.types.IvyNote newNote;

  /**
   * Gets the field newNote.
   * @return the value of the field newNote; may be null.
   */
  public ch.ivy.ws.addon.types.IvyNote getNewNote()
  {
    return newNote;
  }

  /**
   * Sets the field newNote.
   * @param _newNote the new value of the field newNote.
   */
  public void setNewNote(ch.ivy.ws.addon.types.IvyNote _newNote)
  {
    newNote = _newNote;
  }

}
