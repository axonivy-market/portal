package ch.ivy.addon.portalkit.singleapp.tasks.TaskInformation;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskInformationData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskInformationData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -751976126007258704L;

  private ch.ivyteam.ivy.workflow.ITask iTask;

  /**
   * Gets the field iTask.
   * @return the value of the field iTask; may be null.
   */
  public ch.ivyteam.ivy.workflow.ITask getITask()
  {
    return iTask;
  }

  /**
   * Sets the field iTask.
   * @param _iTask the new value of the field iTask.
   */
  public void setITask(ch.ivyteam.ivy.workflow.ITask _iTask)
  {
    iTask = _iTask;
  }

  private java.lang.String newNoteMessage;

  /**
   * Gets the field newNoteMessage.
   * @return the value of the field newNoteMessage; may be null.
   */
  public java.lang.String getNewNoteMessage()
  {
    return newNoteMessage;
  }

  /**
   * Sets the field newNoteMessage.
   * @param _newNoteMessage the new value of the field newNoteMessage.
   */
  public void setNewNoteMessage(java.lang.String _newNoteMessage)
  {
    newNoteMessage = _newNoteMessage;
  }

  private ch.ivyteam.ivy.workflow.INote selectedNote;

  /**
   * Gets the field selectedNote.
   * @return the value of the field selectedNote; may be null.
   */
  public ch.ivyteam.ivy.workflow.INote getSelectedNote()
  {
    return selectedNote;
  }

  /**
   * Sets the field selectedNote.
   * @param _selectedNote the new value of the field selectedNote.
   */
  public void setSelectedNote(ch.ivyteam.ivy.workflow.INote _selectedNote)
  {
    selectedNote = _selectedNote;
  }

}
