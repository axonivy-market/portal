package ch.ivy.ws.addon.bo;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class UserSettingServiceResult", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class UserSettingServiceResult extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -1214692862217096929L;

  private transient ch.ivy.ws.addon.types.IvyUserSetting userSetting;

  /**
   * Gets the field userSetting.
   * @return the value of the field userSetting; may be null.
   */
  public ch.ivy.ws.addon.types.IvyUserSetting getUserSetting()
  {
    return userSetting;
  }

  /**
   * Sets the field userSetting.
   * @param _userSetting the new value of the field userSetting.
   */
  public void setUserSetting(ch.ivy.ws.addon.types.IvyUserSetting _userSetting)
  {
    userSetting = _userSetting;
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

  private transient java.util.List<ch.ivy.ws.addon.types.IvyEmailSetting> emailSettings;

  /**
   * Gets the field emailSettings.
   * @return the value of the field emailSettings; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.IvyEmailSetting> getEmailSettings()
  {
    return emailSettings;
  }

  /**
   * Sets the field emailSettings.
   * @param _emailSettings the new value of the field emailSettings.
   */
  public void setEmailSettings(java.util.List<ch.ivy.ws.addon.types.IvyEmailSetting> _emailSettings)
  {
    emailSettings = _emailSettings;
  }

}
