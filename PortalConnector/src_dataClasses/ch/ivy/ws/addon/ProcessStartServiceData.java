package ch.ivy.ws.addon;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class ProcessStartServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class ProcessStartServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -626789048008031877L;

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

  private transient java.lang.String language;

  /**
   * Gets the field language.
   * @return the value of the field language; may be null.
   */
  public java.lang.String getLanguage()
  {
    return language;
  }

  /**
   * Sets the field language.
   * @param _language the new value of the field language.
   */
  public void setLanguage(java.lang.String _language)
  {
    language = _language;
  }

  private transient ch.ivy.ws.addon.service.ProcessSearchCriteria processSearchCriteria;

  /**
   * Gets the field processSearchCriteria.
   * @return the value of the field processSearchCriteria; may be null.
   */
  public ch.ivy.ws.addon.service.ProcessSearchCriteria getProcessSearchCriteria()
  {
    return processSearchCriteria;
  }

  /**
   * Sets the field processSearchCriteria.
   * @param _processSearchCriteria the new value of the field processSearchCriteria.
   */
  public void setProcessSearchCriteria(ch.ivy.ws.addon.service.ProcessSearchCriteria _processSearchCriteria)
  {
    processSearchCriteria = _processSearchCriteria;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyProcessStart> processStarts;

  /**
   * Gets the field processStarts.
   * @return the value of the field processStarts; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyProcessStart> getProcessStarts()
  {
    return processStarts;
  }

  /**
   * Sets the field processStarts.
   * @param _processStarts the new value of the field processStarts.
   */
  public void setProcessStarts(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyProcessStart> _processStarts)
  {
    processStarts = _processStarts;
  }

  private transient java.lang.String serverUrl;

  /**
   * Gets the field serverUrl.
   * @return the value of the field serverUrl; may be null.
   */
  public java.lang.String getServerUrl()
  {
    return serverUrl;
  }

  /**
   * Sets the field serverUrl.
   * @param _serverUrl the new value of the field serverUrl.
   */
  public void setServerUrl(java.lang.String _serverUrl)
  {
    serverUrl = _serverUrl;
  }

  private transient java.lang.Boolean isNoConfigurationMode;

  /**
   * Gets the field isNoConfigurationMode.
   * @return the value of the field isNoConfigurationMode; may be null.
   */
  public java.lang.Boolean getIsNoConfigurationMode()
  {
    return isNoConfigurationMode;
  }

  /**
   * Sets the field isNoConfigurationMode.
   * @param _isNoConfigurationMode the new value of the field isNoConfigurationMode.
   */
  public void setIsNoConfigurationMode(java.lang.Boolean _isNoConfigurationMode)
  {
    isNoConfigurationMode = _isNoConfigurationMode;
  }

}
