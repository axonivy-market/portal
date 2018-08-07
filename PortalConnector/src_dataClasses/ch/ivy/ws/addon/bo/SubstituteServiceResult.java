package ch.ivy.ws.addon.bo;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class SubstituteServiceResult", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class SubstituteServiceResult extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 7771983764099937696L;

  private transient java.util.List<ch.ivy.ws.addon.types.IvySubstitute> substitutes;

  /**
   * Gets the field substitutes.
   * @return the value of the field substitutes; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.IvySubstitute> getSubstitutes()
  {
    return substitutes;
  }

  /**
   * Sets the field substitutes.
   * @param _substitutes the new value of the field substitutes.
   */
  public void setSubstitutes(java.util.List<ch.ivy.ws.addon.types.IvySubstitute> _substitutes)
  {
    substitutes = _substitutes;
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

  private transient java.util.List<ch.ivy.ws.addon.types.IvyUser> applicationUsers;

  /**
   * Gets the field applicationUsers.
   * @return the value of the field applicationUsers; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.IvyUser> getApplicationUsers()
  {
    return applicationUsers;
  }

  /**
   * Sets the field applicationUsers.
   * @param _applicationUsers the new value of the field applicationUsers.
   */
  public void setApplicationUsers(java.util.List<ch.ivy.ws.addon.types.IvyUser> _applicationUsers)
  {
    applicationUsers = _applicationUsers;
  }

}
