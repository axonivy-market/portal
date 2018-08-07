package ch.ivy.ws.addon;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class ServerServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class ServerServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 2280840439971888722L;

  private transient java.lang.String externalHost;

  /**
   * Gets the field externalHost.
   * @return the value of the field externalHost; may be null.
   */
  public java.lang.String getExternalHost()
  {
    return externalHost;
  }

  /**
   * Sets the field externalHost.
   * @param _externalHost the new value of the field externalHost.
   */
  public void setExternalHost(java.lang.String _externalHost)
  {
    externalHost = _externalHost;
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

}
