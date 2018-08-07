package ch.ivy.add.portalkit.admin;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class Task", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class Task extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -6081292788887507640L;

  private transient java.lang.Number id;

  /**
   * Gets the field id.
   * @return the value of the field id; may be null.
   */
  public java.lang.Number getId()
  {
    return id;
  }

  /**
   * Sets the field id.
   * @param _id the new value of the field id.
   */
  public void setId(java.lang.Number _id)
  {
    id = _id;
  }

  private transient java.lang.Number caseId;

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

  private transient java.lang.String description;

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

  private transient java.lang.String caseDescription;

  /**
   * Gets the field caseDescription.
   * @return the value of the field caseDescription; may be null.
   */
  public java.lang.String getCaseDescription()
  {
    return caseDescription;
  }

  /**
   * Sets the field caseDescription.
   * @param _caseDescription the new value of the field caseDescription.
   */
  public void setCaseDescription(java.lang.String _caseDescription)
  {
    caseDescription = _caseDescription;
  }

  private transient java.util.Date createdOn;

  /**
   * Gets the field createdOn.
   * @return the value of the field createdOn; may be null.
   */
  public java.util.Date getCreatedOn()
  {
    return createdOn;
  }

  /**
   * Sets the field createdOn.
   * @param _createdOn the new value of the field createdOn.
   */
  public void setCreatedOn(java.util.Date _createdOn)
  {
    createdOn = _createdOn;
  }

  private transient java.lang.String state;

  /**
   * Gets the field state.
   * @return the value of the field state; may be null.
   */
  public java.lang.String getState()
  {
    return state;
  }

  /**
   * Sets the field state.
   * @param _state the new value of the field state.
   */
  public void setState(java.lang.String _state)
  {
    state = _state;
  }

  private transient ch.ivyteam.ivy.security.ISecurityMember activator;

  /**
   * Gets the field activator.
   * @return the value of the field activator; may be null.
   */
  public ch.ivyteam.ivy.security.ISecurityMember getActivator()
  {
    return activator;
  }

  /**
   * Sets the field activator.
   * @param _activator the new value of the field activator.
   */
  public void setActivator(ch.ivyteam.ivy.security.ISecurityMember _activator)
  {
    activator = _activator;
  }

  private transient ch.ivyteam.ivy.workflow.ITask ivyTask;

  /**
   * Gets the field ivyTask.
   * @return the value of the field ivyTask; may be null.
   */
  public ch.ivyteam.ivy.workflow.ITask getIvyTask()
  {
    return ivyTask;
  }

  /**
   * Sets the field ivyTask.
   * @param _ivyTask the new value of the field ivyTask.
   */
  public void setIvyTask(ch.ivyteam.ivy.workflow.ITask _ivyTask)
  {
    ivyTask = _ivyTask;
  }

}
