package ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CentralLanguageData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CentralLanguageData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 8206778770897274404L;

  private java.util.List<ch.ivy.ws.addon.IvyLanguageSetting> ivyLanguagesSettings;

  /**
   * Gets the field ivyLanguagesSettings.
   * @return the value of the field ivyLanguagesSettings; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.IvyLanguageSetting> getIvyLanguagesSettings()
  {
    return ivyLanguagesSettings;
  }

  /**
   * Sets the field ivyLanguagesSettings.
   * @param _ivyLanguagesSettings the new value of the field ivyLanguagesSettings.
   */
  public void setIvyLanguagesSettings(java.util.List<ch.ivy.ws.addon.IvyLanguageSetting> _ivyLanguagesSettings)
  {
    ivyLanguagesSettings = _ivyLanguagesSettings;
  }

  private java.lang.String user;

  /**
   * Gets the field user.
   * @return the value of the field user; may be null.
   */
  public java.lang.String getUser()
  {
    return user;
  }

  /**
   * Sets the field user.
   * @param _user the new value of the field user.
   */
  public void setUser(java.lang.String _user)
  {
    user = _user;
  }

  private java.util.List<java.lang.String> appNames;

  /**
   * Gets the field appNames.
   * @return the value of the field appNames; may be null.
   */
  public java.util.List<java.lang.String> getAppNames()
  {
    return appNames;
  }

  /**
   * Sets the field appNames.
   * @param _appNames the new value of the field appNames.
   */
  public void setAppNames(java.util.List<java.lang.String> _appNames)
  {
    appNames = _appNames;
  }

  private java.util.List<ch.ivy.ws.addon.WsException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.WsException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(java.util.List<ch.ivy.ws.addon.WsException> _errors)
  {
    errors = _errors;
  }

  private java.lang.String errorLink;

  /**
   * Gets the field errorLink.
   * @return the value of the field errorLink; may be null.
   */
  public java.lang.String getErrorLink()
  {
    return errorLink;
  }

  /**
   * Sets the field errorLink.
   * @param _errorLink the new value of the field errorLink.
   */
  public void setErrorLink(java.lang.String _errorLink)
  {
    errorLink = _errorLink;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteLanguageSetting> remoteLanguageSettings;

  /**
   * Gets the field remoteLanguageSettings.
   * @return the value of the field remoteLanguageSettings; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteLanguageSetting> getRemoteLanguageSettings()
  {
    return remoteLanguageSettings;
  }

  /**
   * Sets the field remoteLanguageSettings.
   * @param _remoteLanguageSettings the new value of the field remoteLanguageSettings.
   */
  public void setRemoteLanguageSettings(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteLanguageSetting> _remoteLanguageSettings)
  {
    remoteLanguageSettings = _remoteLanguageSettings;
  }

}
