package ch.ivy.addon.portalkit.multiapp.settings.EmailSetting;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class EmailSettingData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class EmailSettingData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -1123134455184219436L;

  private java.lang.Boolean settingForAllApp;

  /**
   * Gets the field settingForAllApp.
   * @return the value of the field settingForAllApp; may be null.
   */
  public java.lang.Boolean getSettingForAllApp()
  {
    return settingForAllApp;
  }

  /**
   * Sets the field settingForAllApp.
   * @param _settingForAllApp the new value of the field settingForAllApp.
   */
  public void setSettingForAllApp(java.lang.Boolean _settingForAllApp)
  {
    settingForAllApp = _settingForAllApp;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.enums.RemoteWeekDay> dailySummaryList;

  /**
   * Gets the field dailySummaryList.
   * @return the value of the field dailySummaryList; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.enums.RemoteWeekDay> getDailySummaryList()
  {
    return dailySummaryList;
  }

  /**
   * Sets the field dailySummaryList.
   * @param _dailySummaryList the new value of the field dailySummaryList.
   */
  public void setDailySummaryList(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.enums.RemoteWeekDay> _dailySummaryList)
  {
    dailySummaryList = _dailySummaryList;
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

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> errors;

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

  private ch.ivy.addon.portalkit.bo.RemoteEmailSetting generalEmailSetting;

  /**
   * Gets the field generalEmailSetting.
   * @return the value of the field generalEmailSetting; may be null.
   */
  public ch.ivy.addon.portalkit.bo.RemoteEmailSetting getGeneralEmailSetting()
  {
    return generalEmailSetting;
  }

  /**
   * Sets the field generalEmailSetting.
   * @param _generalEmailSetting the new value of the field generalEmailSetting.
   */
  public void setGeneralEmailSetting(ch.ivy.addon.portalkit.bo.RemoteEmailSetting _generalEmailSetting)
  {
    generalEmailSetting = _generalEmailSetting;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteEmailSetting> remoteEmailSettings;

  /**
   * Gets the field remoteEmailSettings.
   * @return the value of the field remoteEmailSettings; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteEmailSetting> getRemoteEmailSettings()
  {
    return remoteEmailSettings;
  }

  /**
   * Sets the field remoteEmailSettings.
   * @param _remoteEmailSettings the new value of the field remoteEmailSettings.
   */
  public void setRemoteEmailSettings(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteEmailSetting> _remoteEmailSettings)
  {
    remoteEmailSettings = _remoteEmailSettings;
  }

  private java.lang.Boolean emptySetting;

  /**
   * Gets the field emptySetting.
   * @return the value of the field emptySetting; may be null.
   */
  public java.lang.Boolean getEmptySetting()
  {
    return emptySetting;
  }

  /**
   * Sets the field emptySetting.
   * @param _emptySetting the new value of the field emptySetting.
   */
  public void setEmptySetting(java.lang.Boolean _emptySetting)
  {
    emptySetting = _emptySetting;
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

}
