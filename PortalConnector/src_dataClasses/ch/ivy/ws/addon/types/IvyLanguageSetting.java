package ch.ivy.ws.addon.types;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class IvyLanguageSetting", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class IvyLanguageSetting extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -7270799186764534844L;

  private transient java.lang.String appName;

  /**
   * Gets the field appName.
   * @return the value of the field appName; may be null.
   */
  public java.lang.String getAppName()
  {
    return appName;
  }

  /**
   * Sets the field appName.
   * @param _appName the new value of the field appName.
   */
  public void setAppName(java.lang.String _appName)
  {
    appName = _appName;
  }

  private transient java.lang.String userLanguage;

  /**
   * Gets the field userLanguage.
   * @return the value of the field userLanguage; may be null.
   */
  public java.lang.String getUserLanguage()
  {
    return userLanguage;
  }

  /**
   * Sets the field userLanguage.
   * @param _userLanguage the new value of the field userLanguage.
   */
  public void setUserLanguage(java.lang.String _userLanguage)
  {
    userLanguage = _userLanguage;
  }

  private transient java.util.List<java.lang.String> supportedLanguages;

  /**
   * Gets the field supportedLanguages.
   * @return the value of the field supportedLanguages; may be null.
   */
  public java.util.List<java.lang.String> getSupportedLanguages()
  {
    return supportedLanguages;
  }

  /**
   * Sets the field supportedLanguages.
   * @param _supportedLanguages the new value of the field supportedLanguages.
   */
  public void setSupportedLanguages(java.util.List<java.lang.String> _supportedLanguages)
  {
    supportedLanguages = _supportedLanguages;
  }

}
