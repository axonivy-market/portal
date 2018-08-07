package ch.ivy.ws.addon;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class UserServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class UserServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 1213567356845604392L;

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyAbsence> absences;

  /**
   * Gets the field absences.
   * @return the value of the field absences; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyAbsence> getAbsences()
  {
    return absences;
  }

  /**
   * Sets the field absences.
   * @param _absences the new value of the field absences.
   */
  public void setAbsences(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyAbsence> _absences)
  {
    absences = _absences;
  }

  private transient java.util.List<java.lang.String> applications;

  /**
   * Gets the field applications.
   * @return the value of the field applications; may be null.
   */
  public java.util.List<java.lang.String> getApplications()
  {
    return applications;
  }

  /**
   * Sets the field applications.
   * @param _applications the new value of the field applications.
   */
  public void setApplications(java.util.List<java.lang.String> _applications)
  {
    applications = _applications;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyLanguageSetting> languagesSettings;

  /**
   * Gets the field languagesSettings.
   * @return the value of the field languagesSettings; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyLanguageSetting> getLanguagesSettings()
  {
    return languagesSettings;
  }

  /**
   * Sets the field languagesSettings.
   * @param _languagesSettings the new value of the field languagesSettings.
   */
  public void setLanguagesSettings(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyLanguageSetting> _languagesSettings)
  {
    languagesSettings = _languagesSettings;
  }

  private transient java.lang.Long serverId;

  /**
   * Gets the field serverId.
   * @return the value of the field serverId; may be null.
   */
  public java.lang.Long getServerId()
  {
    return serverId;
  }

  /**
   * Sets the field serverId.
   * @param _serverId the new value of the field serverId.
   */
  public void setServerId(java.lang.Long _serverId)
  {
    serverId = _serverId;
  }

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

  private transient java.lang.String user;

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

}
