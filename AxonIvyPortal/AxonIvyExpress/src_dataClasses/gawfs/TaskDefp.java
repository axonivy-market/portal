package gawfs;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskDefp", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
@javax.persistence.Entity
@javax.persistence.Table(name="taskDefinitions")
public class TaskDefp extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 9081809877928366596L;

  /**
   * Identifier
   */
  @javax.persistence.Id
  @javax.persistence.GeneratedValue
  private java.lang.Integer id;

  /**
   * Gets the field id.
   * @return the value of the field id; may be null.
   */
  public java.lang.Integer getId()
  {
    return id;
  }

  /**
   * Sets the field id.
   * @param _id the new value of the field id.
   */
  public void setId(java.lang.Integer _id)
  {
    id = _id;
  }

  private java.lang.Integer processID;

  /**
   * Gets the field processID.
   * @return the value of the field processID; may be null.
   */
  public java.lang.Integer getProcessID()
  {
    return processID;
  }

  /**
   * Sets the field processID.
   * @param _processID the new value of the field processID.
   */
  public void setProcessID(java.lang.Integer _processID)
  {
    processID = _processID;
  }

  private java.lang.String taskActor;

  /**
   * Gets the field taskActor.
   * @return the value of the field taskActor; may be null.
   */
  public java.lang.String getTaskActor()
  {
    return taskActor;
  }

  /**
   * Sets the field taskActor.
   * @param _taskActor the new value of the field taskActor.
   */
  public void setTaskActor(java.lang.String _taskActor)
  {
    taskActor = _taskActor;
  }

  private java.lang.String subject;

  /**
   * Gets the field subject.
   * @return the value of the field subject; may be null.
   */
  public java.lang.String getSubject()
  {
    return subject;
  }

  /**
   * Sets the field subject.
   * @param _subject the new value of the field subject.
   */
  public void setSubject(java.lang.String _subject)
  {
    subject = _subject;
  }

  private java.lang.String prio;

  /**
   * Gets the field prio.
   * @return the value of the field prio; may be null.
   */
  public java.lang.String getPrio()
  {
    return prio;
  }

  /**
   * Sets the field prio.
   * @param _prio the new value of the field prio.
   */
  public void setPrio(java.lang.String _prio)
  {
    prio = _prio;
  }

  private java.lang.String description;

  /**
   * Gets the field description.
   * @return the value of the field description; may be null.
   */
  public java.lang.String getDescription()
  {
    return description;
  }

  /**
   * Sets the field description.
   * @param _description the new value of the field description.
   */
  public void setDescription(java.lang.String _description)
  {
    description = _description;
  }

  private java.lang.Integer taskCount;

  /**
   * Gets the field taskCount.
   * @return the value of the field taskCount; may be null.
   */
  public java.lang.Integer getTaskCount()
  {
    return taskCount;
  }

  /**
   * Sets the field taskCount.
   * @param _taskCount the new value of the field taskCount.
   */
  public void setTaskCount(java.lang.Integer _taskCount)
  {
    taskCount = _taskCount;
  }

  private java.lang.Integer untilDays;

  /**
   * Gets the field untilDays.
   * @return the value of the field untilDays; may be null.
   */
  public java.lang.Integer getUntilDays()
  {
    return untilDays;
  }

  /**
   * Sets the field untilDays.
   * @param _untilDays the new value of the field untilDays.
   */
  public void setUntilDays(java.lang.Integer _untilDays)
  {
    untilDays = _untilDays;
  }

}
