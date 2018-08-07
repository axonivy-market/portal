package ch.ivy.ws.addon.bo;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class SecurityServiceResult", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class SecurityServiceResult extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -7901050533121040748L;

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

}
