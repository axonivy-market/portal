package ch.ivy.ws.addon.bo;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class AvailableAppsResult", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class AvailableAppsResult extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -8364384410803298350L;

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

  private transient java.util.List<java.lang.String> availableApps;

  /**
   * Gets the field availableApps.
   * @return the value of the field availableApps; may be null.
   */
  public java.util.List<java.lang.String> getAvailableApps()
  {
    return availableApps;
  }

  /**
   * Sets the field availableApps.
   * @param _availableApps the new value of the field availableApps.
   */
  public void setAvailableApps(java.util.List<java.lang.String> _availableApps)
  {
    availableApps = _availableApps;
  }

  private transient java.util.List<ch.ivyteam.ivy.security.IUser> users;

  /**
   * Gets the field users.
   * @return the value of the field users; may be null.
   */
  public java.util.List<ch.ivyteam.ivy.security.IUser> getUsers()
  {
    return users;
  }

  /**
   * Sets the field users.
   * @param _users the new value of the field users.
   */
  public void setUsers(java.util.List<ch.ivyteam.ivy.security.IUser> _users)
  {
    users = _users;
  }

}
