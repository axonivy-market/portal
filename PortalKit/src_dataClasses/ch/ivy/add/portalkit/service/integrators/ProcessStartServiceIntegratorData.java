package ch.ivy.add.portalkit.service.integrators;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class ProcessStartServiceIntegratorData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class ProcessStartServiceIntegratorData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -3080670153943731176L;

  private transient java.lang.String endpoint;

  /**
   * Gets the field endpoint.
   * @return the value of the field endpoint; may be null.
   */
  public java.lang.String getEndpoint()
  {
    return endpoint;
  }

  /**
   * Sets the field endpoint.
   * @param _endpoint the new value of the field endpoint.
   */
  public void setEndpoint(java.lang.String _endpoint)
  {
    endpoint = _endpoint;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> _errors)
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

  private transient java.lang.String password;

  /**
   * Gets the field password.
   * @return the value of the field password; may be null.
   */
  public java.lang.String getPassword()
  {
    return password;
  }

  /**
   * Sets the field password.
   * @param _password the new value of the field password.
   */
  public void setPassword(java.lang.String _password)
  {
    password = _password;
  }

  private transient ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria;

  /**
   * Gets the field processSearchCriteria.
   * @return the value of the field processSearchCriteria; may be null.
   */
  public ch.ivy.ws.addon.ProcessSearchCriteria getProcessSearchCriteria()
  {
    return processSearchCriteria;
  }

  /**
   * Sets the field processSearchCriteria.
   * @param _processSearchCriteria the new value of the field processSearchCriteria.
   */
  public void setProcessSearchCriteria(ch.ivy.ws.addon.ProcessSearchCriteria _processSearchCriteria)
  {
    processSearchCriteria = _processSearchCriteria;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyProcessStart> processStarts;

  /**
   * Gets the field processStarts.
   * @return the value of the field processStarts; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyProcessStart> getProcessStarts()
  {
    return processStarts;
  }

  /**
   * Sets the field processStarts.
   * @param _processStarts the new value of the field processStarts.
   */
  public void setProcessStarts(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyProcessStart> _processStarts)
  {
    processStarts = _processStarts;
  }

  private transient ch.ivy.addon.portalkit.persistence.domain.Server server;

  /**
   * Gets the field server.
   * @return the value of the field server; may be null.
   */
  public ch.ivy.addon.portalkit.persistence.domain.Server getServer()
  {
    return server;
  }

  /**
   * Sets the field server.
   * @param _server the new value of the field server.
   */
  public void setServer(ch.ivy.addon.portalkit.persistence.domain.Server _server)
  {
    server = _server;
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

  private transient java.lang.String userName;

  /**
   * Gets the field userName.
   * @return the value of the field userName; may be null.
   */
  public java.lang.String getUserName()
  {
    return userName;
  }

  /**
   * Sets the field userName.
   * @param _userName the new value of the field userName.
   */
  public void setUserName(java.lang.String _userName)
  {
    userName = _userName;
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
