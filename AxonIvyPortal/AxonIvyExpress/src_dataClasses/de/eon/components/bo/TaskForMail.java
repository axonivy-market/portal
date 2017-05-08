package de.eon.components.bo;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskForMail", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskForMail extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 6322288436145290634L;

  private ch.ivyteam.ivy.workflow.WorkflowPriority priority;

  /**
   * Gets the field priority.
   * @return the value of the field priority; may be null.
   */
  public ch.ivyteam.ivy.workflow.WorkflowPriority getPriority()
  {
    return priority;
  }

  /**
   * Sets the field priority.
   * @param _priority the new value of the field priority.
   */
  public void setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority _priority)
  {
    priority = _priority;
  }

  private ch.ivyteam.ivy.workflow.TaskState state;

  /**
   * Gets the field state.
   * @return the value of the field state; may be null.
   */
  public ch.ivyteam.ivy.workflow.TaskState getState()
  {
    return state;
  }

  /**
   * Sets the field state.
   * @param _state the new value of the field state.
   */
  public void setState(ch.ivyteam.ivy.workflow.TaskState _state)
  {
    state = _state;
  }

  private java.lang.String name;

  /**
   * Gets the field name.
   * @return the value of the field name; may be null.
   */
  public java.lang.String getName()
  {
    return name;
  }

  /**
   * Sets the field name.
   * @param _name the new value of the field name.
   */
  public void setName(java.lang.String _name)
  {
    name = _name;
  }

  private ch.ivyteam.ivy.security.ISecurityMember activator;

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

  private ch.ivyteam.ivy.scripting.objects.DateTime startTimestamp;

  /**
   * Gets the field startTimestamp.
   * @return the value of the field startTimestamp; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.DateTime getStartTimestamp()
  {
    return startTimestamp;
  }

  /**
   * Sets the field startTimestamp.
   * @param _startTimestamp the new value of the field startTimestamp.
   */
  public void setStartTimestamp(ch.ivyteam.ivy.scripting.objects.DateTime _startTimestamp)
  {
    startTimestamp = _startTimestamp;
  }

  private ch.ivyteam.ivy.scripting.objects.DateTime expiryTimestamp;

  /**
   * Gets the field expiryTimestamp.
   * @return the value of the field expiryTimestamp; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.DateTime getExpiryTimestamp()
  {
    return expiryTimestamp;
  }

  /**
   * Sets the field expiryTimestamp.
   * @param _expiryTimestamp the new value of the field expiryTimestamp.
   */
  public void setExpiryTimestamp(ch.ivyteam.ivy.scripting.objects.DateTime _expiryTimestamp)
  {
    expiryTimestamp = _expiryTimestamp;
  }

  private java.lang.String startLink;

  /**
   * Gets the field startLink.
   * @return the value of the field startLink; may be null.
   */
  public java.lang.String getStartLink()
  {
    return startLink;
  }

  /**
   * Sets the field startLink.
   * @param _startLink the new value of the field startLink.
   */
  public void setStartLink(java.lang.String _startLink)
  {
    startLink = _startLink;
  }

}
