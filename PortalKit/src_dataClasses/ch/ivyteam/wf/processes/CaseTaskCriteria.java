package ch.ivyteam.wf.processes;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseTaskCriteria", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseTaskCriteria extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 4827986761757429864L;

  private transient java.lang.String user;

  /**
   * Gets the field user.
   * @return the value of the field user; may be null.
   */
  public java.lang.String getUser()
  {
    return user;
  }

  /**
   * Sets the field user.
   * @param _user the new value of the field user.
   */
  public void setUser(java.lang.String _user)
  {
    user = _user;
  }

  private transient java.util.Date createdFrom;

  /**
   * Gets the field createdFrom.
   * @return the value of the field createdFrom; may be null.
   */
  public java.util.Date getCreatedFrom()
  {
    return createdFrom;
  }

  /**
   * Sets the field createdFrom.
   * @param _createdFrom the new value of the field createdFrom.
   */
  public void setCreatedFrom(java.util.Date _createdFrom)
  {
    createdFrom = _createdFrom;
  }

  private transient java.util.Date createdTo;

  /**
   * Gets the field createdTo.
   * @return the value of the field createdTo; may be null.
   */
  public java.util.Date getCreatedTo()
  {
    return createdTo;
  }

  /**
   * Sets the field createdTo.
   * @param _createdTo the new value of the field createdTo.
   */
  public void setCreatedTo(java.util.Date _createdTo)
  {
    createdTo = _createdTo;
  }

  private transient java.util.List<java.lang.String> priorities;

  /**
   * Gets the field priorities.
   * @return the value of the field priorities; may be null.
   */
  public java.util.List<java.lang.String> getPriorities()
  {
    return priorities;
  }

  /**
   * Sets the field priorities.
   * @param _priorities the new value of the field priorities.
   */
  public void setPriorities(java.util.List<java.lang.String> _priorities)
  {
    priorities = _priorities;
  }

  private transient java.util.List<ch.ivyteam.ivy.workflow.ICase> cases;

  /**
   * Gets the field cases.
   * @return the value of the field cases; may be null.
   */
  public java.util.List<ch.ivyteam.ivy.workflow.ICase> getCases()
  {
    return cases;
  }

  /**
   * Sets the field cases.
   * @param _cases the new value of the field cases.
   */
  public void setCases(java.util.List<ch.ivyteam.ivy.workflow.ICase> _cases)
  {
    cases = _cases;
  }

  private transient java.util.List<ch.ivy.ws.addon.WsException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.WsException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(java.util.List<ch.ivy.ws.addon.WsException> _errors)
  {
    errors = _errors;
  }

  private transient java.util.List<ch.ivyteam.ivy.workflow.ITask> tasks;

  /**
   * Gets the field tasks.
   * @return the value of the field tasks; may be null.
   */
  public java.util.List<ch.ivyteam.ivy.workflow.ITask> getTasks()
  {
    return tasks;
  }

  /**
   * Sets the field tasks.
   * @param _tasks the new value of the field tasks.
   */
  public void setTasks(java.util.List<ch.ivyteam.ivy.workflow.ITask> _tasks)
  {
    tasks = _tasks;
  }

}
