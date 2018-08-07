package ch.ivy.ws.addon;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class SecurityServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class SecurityServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -764617244661363933L;

  private transient java.lang.String application;

  /**
   * Gets the field application.
   * @return the value of the field application; may be null.
   */
  public java.lang.String getApplication()
  {
    return application;
  }

  /**
   * Sets the field application.
   * @param _application the new value of the field application.
   */
  public void setApplication(java.lang.String _application)
  {
    application = _application;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> applications;

  /**
   * Gets the field applications.
   * @return the value of the field applications; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getApplications()
  {
    return applications;
  }

  /**
   * Sets the field applications.
   * @param _applications the new value of the field applications.
   */
  public void setApplications(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _applications)
  {
    applications = _applications;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WSException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WSException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WSException> _errors)
  {
    errors = _errors;
  }

  private transient ch.ivy.ws.addon.types.IvyRole ivyRole;

  /**
   * Gets the field ivyRole.
   * @return the value of the field ivyRole; may be null.
   */
  public ch.ivy.ws.addon.types.IvyRole getIvyRole()
  {
    return ivyRole;
  }

  /**
   * Sets the field ivyRole.
   * @param _ivyRole the new value of the field ivyRole.
   */
  public void setIvyRole(ch.ivy.ws.addon.types.IvyRole _ivyRole)
  {
    ivyRole = _ivyRole;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyRole> ivyRoles;

  /**
   * Gets the field ivyRoles.
   * @return the value of the field ivyRoles; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyRole> getIvyRoles()
  {
    return ivyRoles;
  }

  /**
   * Sets the field ivyRoles.
   * @param _ivyRoles the new value of the field ivyRoles.
   */
  public void setIvyRoles(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyRole> _ivyRoles)
  {
    ivyRoles = _ivyRoles;
  }

  private transient ch.ivy.ws.addon.types.IvyUser ivyUser;

  /**
   * Gets the field ivyUser.
   * @return the value of the field ivyUser; may be null.
   */
  public ch.ivy.ws.addon.types.IvyUser getIvyUser()
  {
    return ivyUser;
  }

  /**
   * Sets the field ivyUser.
   * @param _ivyUser the new value of the field ivyUser.
   */
  public void setIvyUser(ch.ivy.ws.addon.types.IvyUser _ivyUser)
  {
    ivyUser = _ivyUser;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyUser> ivyUsers;

  /**
   * Gets the field ivyUsers.
   * @return the value of the field ivyUsers; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyUser> getIvyUsers()
  {
    return ivyUsers;
  }

  /**
   * Sets the field ivyUsers.
   * @param _ivyUsers the new value of the field ivyUsers.
   */
  public void setIvyUsers(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyUser> _ivyUsers)
  {
    ivyUsers = _ivyUsers;
  }

  private transient ch.ivyteam.ivy.workflow.ITask task;

  /**
   * Gets the field task.
   * @return the value of the field task; may be null.
   */
  public ch.ivyteam.ivy.workflow.ITask getTask()
  {
    return task;
  }

  /**
   * Sets the field task.
   * @param _task the new value of the field task.
   */
  public void setTask(ch.ivyteam.ivy.workflow.ITask _task)
  {
    task = _task;
  }

  private transient java.lang.Long taskId;

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

}
