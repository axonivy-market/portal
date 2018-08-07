package ch.ivy.ws.addon;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class SupportedLanguageServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class SupportedLanguageServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 4303623031036192507L;

  private transient java.lang.String applicationName;

  /**
   * Gets the field applicationName.
   * @return the value of the field applicationName; may be null.
   */
  public java.lang.String getApplicationName()
  {
    return applicationName;
  }

  /**
   * Sets the field applicationName.
   * @param _applicationName the new value of the field applicationName.
   */
  public void setApplicationName(java.lang.String _applicationName)
  {
    applicationName = _applicationName;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> supportedLanguages;

  /**
   * Gets the field supportedLanguages.
   * @return the value of the field supportedLanguages; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getSupportedLanguages()
  {
    return supportedLanguages;
  }

  /**
   * Sets the field supportedLanguages.
   * @param _supportedLanguages the new value of the field supportedLanguages.
   */
  public void setSupportedLanguages(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _supportedLanguages)
  {
    supportedLanguages = _supportedLanguages;
  }

}
