package ch.ivyteam.wf.processes;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class EmailSettingServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class EmailSettingServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 495007172883802890L;

  private transient java.lang.String username;

  /**
   * Gets the field username.
   * @return the value of the field username; may be null.
   */
  public java.lang.String getUsername()
  {
    return username;
  }

  /**
   * Sets the field username.
   * @param _username the new value of the field username.
   */
  public void setUsername(java.lang.String _username)
  {
    username = _username;
  }

  private transient ch.ivy.addon.portalkit.bo.PortalConfiguration portalConfig;

  /**
   * Gets the field portalConfig.
   * @return the value of the field portalConfig; may be null.
   */
  public ch.ivy.addon.portalkit.bo.PortalConfiguration getPortalConfig()
  {
    return portalConfig;
  }

  /**
   * Sets the field portalConfig.
   * @param _portalConfig the new value of the field portalConfig.
   */
  public void setPortalConfig(ch.ivy.addon.portalkit.bo.PortalConfiguration _portalConfig)
  {
    portalConfig = _portalConfig;
  }

  private transient java.util.ListIterator listIterator;

  /**
   * Gets the field listIterator.
   * @return the value of the field listIterator; may be null.
   */
  public java.util.ListIterator getListIterator()
  {
    return listIterator;
  }

  /**
   * Sets the field listIterator.
   * @param _listIterator the new value of the field listIterator.
   */
  public void setListIterator(java.util.ListIterator _listIterator)
  {
    listIterator = _listIterator;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> apps;

  /**
   * Gets the field apps.
   * @return the value of the field apps; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getApps()
  {
    return apps;
  }

  /**
   * Sets the field apps.
   * @param _apps the new value of the field apps.
   */
  public void setApps(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _apps)
  {
    apps = _apps;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyApplication> applications;

  /**
   * Gets the field applications.
   * @return the value of the field applications; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyApplication> getApplications()
  {
    return applications;
  }

  /**
   * Sets the field applications.
   * @param _applications the new value of the field applications.
   */
  public void setApplications(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyApplication> _applications)
  {
    applications = _applications;
  }

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

  private transient ch.ivy.ws.addon.WsException errorWs;

  /**
   * Gets the field errorWs.
   * @return the value of the field errorWs; may be null.
   */
  public ch.ivy.ws.addon.WsException getErrorWs()
  {
    return errorWs;
  }

  /**
   * Sets the field errorWs.
   * @param _errorWs the new value of the field errorWs.
   */
  public void setErrorWs(ch.ivy.ws.addon.WsException _errorWs)
  {
    errorWs = _errorWs;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> tempErrors;

  /**
   * Gets the field tempErrors.
   * @return the value of the field tempErrors; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> getTempErrors()
  {
    return tempErrors;
  }

  /**
   * Sets the field tempErrors.
   * @param _tempErrors the new value of the field tempErrors.
   */
  public void setTempErrors(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> _tempErrors)
  {
    tempErrors = _tempErrors;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyEmailSetting> ivyEmailSettings;

  /**
   * Gets the field ivyEmailSettings.
   * @return the value of the field ivyEmailSettings; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyEmailSetting> getIvyEmailSettings()
  {
    return ivyEmailSettings;
  }

  /**
   * Sets the field ivyEmailSettings.
   * @param _ivyEmailSettings the new value of the field ivyEmailSettings.
   */
  public void setIvyEmailSettings(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyEmailSetting> _ivyEmailSettings)
  {
    ivyEmailSettings = _ivyEmailSettings;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteEmailSetting> emailSettings;

  /**
   * Gets the field emailSettings.
   * @return the value of the field emailSettings; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteEmailSetting> getEmailSettings()
  {
    return emailSettings;
  }

  /**
   * Sets the field emailSettings.
   * @param _emailSettings the new value of the field emailSettings.
   */
  public void setEmailSettings(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteEmailSetting> _emailSettings)
  {
    emailSettings = _emailSettings;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.util.Map> emailSettingMaps;

  /**
   * Gets the field emailSettingMaps.
   * @return the value of the field emailSettingMaps; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.util.Map> getEmailSettingMaps()
  {
    return emailSettingMaps;
  }

  /**
   * Sets the field emailSettingMaps.
   * @param _emailSettingMaps the new value of the field emailSettingMaps.
   */
  public void setEmailSettingMaps(ch.ivyteam.ivy.scripting.objects.List<java.util.Map> _emailSettingMaps)
  {
    emailSettingMaps = _emailSettingMaps;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> totalApps;

  /**
   * Gets the field totalApps.
   * @return the value of the field totalApps; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getTotalApps()
  {
    return totalApps;
  }

  /**
   * Sets the field totalApps.
   * @param _totalApps the new value of the field totalApps.
   */
  public void setTotalApps(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _totalApps)
  {
    totalApps = _totalApps;
  }

}
