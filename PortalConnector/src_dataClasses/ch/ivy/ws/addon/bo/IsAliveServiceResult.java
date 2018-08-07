package ch.ivy.ws.addon.bo;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class IsAliveServiceResult", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class IsAliveServiceResult extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -768919443308968909L;

  private transient java.util.List<ch.ivy.ws.addon.types.IvyApplication> apps;

  /**
   * Gets the field apps.
   * @return the value of the field apps; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.IvyApplication> getApps()
  {
    return apps;
  }

  /**
   * Sets the field apps.
   * @param _apps the new value of the field apps.
   */
  public void setApps(java.util.List<ch.ivy.ws.addon.types.IvyApplication> _apps)
  {
    apps = _apps;
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

}
