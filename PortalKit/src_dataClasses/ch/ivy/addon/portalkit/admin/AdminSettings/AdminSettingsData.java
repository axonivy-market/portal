package ch.ivy.addon.portalkit.admin.AdminSettings;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class AdminSettingsData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class AdminSettingsData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -6775060844977764252L;

  private ch.ivy.addon.portalkit.persistence.domain.Application application;

  /**
   * Gets the field application.
   * @return the value of the field application; may be null.
   */
  public ch.ivy.addon.portalkit.persistence.domain.Application getApplication()
  {
    return application;
  }

  /**
   * Sets the field application.
   * @param _application the new value of the field application.
   */
  public void setApplication(ch.ivy.addon.portalkit.persistence.domain.Application _application)
  {
    application = _application;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Application> applicationList;

  /**
   * Gets the field applicationList.
   * @return the value of the field applicationList; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Application> getApplicationList()
  {
    return applicationList;
  }

  /**
   * Sets the field applicationList.
   * @param _applicationList the new value of the field applicationList.
   */
  public void setApplicationList(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Application> _applicationList)
  {
    applicationList = _applicationList;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> applicationListWS;

  /**
   * Gets the field applicationListWS.
   * @return the value of the field applicationListWS; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getApplicationListWS()
  {
    return applicationListWS;
  }

  /**
   * Sets the field applicationListWS.
   * @param _applicationListWS the new value of the field applicationListWS.
   */
  public void setApplicationListWS(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _applicationListWS)
  {
    applicationListWS = _applicationListWS;
  }

  private java.lang.String confirmMsg;

  /**
   * Gets the field confirmMsg.
   * @return the value of the field confirmMsg; may be null.
   */
  public java.lang.String getConfirmMsg()
  {
    return confirmMsg;
  }

  /**
   * Sets the field confirmMsg.
   * @param _confirmMsg the new value of the field confirmMsg.
   */
  public void setConfirmMsg(java.lang.String _confirmMsg)
  {
    confirmMsg = _confirmMsg;
  }

  private java.lang.String dialogTitle;

  /**
   * Gets the field dialogTitle.
   * @return the value of the field dialogTitle; may be null.
   */
  public java.lang.String getDialogTitle()
  {
    return dialogTitle;
  }

  /**
   * Sets the field dialogTitle.
   * @param _dialogTitle the new value of the field dialogTitle.
   */
  public void setDialogTitle(java.lang.String _dialogTitle)
  {
    dialogTitle = _dialogTitle;
  }

  /**
   * Display application name in current portal language
   */
  private transient java.lang.String displayNameInCurrentLanguage;

  /**
   * Gets the field displayNameInCurrentLanguage.
   * @return the value of the field displayNameInCurrentLanguage; may be null.
   */
  public java.lang.String getDisplayNameInCurrentLanguage()
  {
    return displayNameInCurrentLanguage;
  }

  /**
   * Sets the field displayNameInCurrentLanguage.
   * @param _displayNameInCurrentLanguage the new value of the field displayNameInCurrentLanguage.
   */
  public void setDisplayNameInCurrentLanguage(java.lang.String _displayNameInCurrentLanguage)
  {
    displayNameInCurrentLanguage = _displayNameInCurrentLanguage;
  }

  private java.lang.Boolean duplicateApp;

  /**
   * Gets the field duplicateApp.
   * @return the value of the field duplicateApp; may be null.
   */
  public java.lang.Boolean getDuplicateApp()
  {
    return duplicateApp;
  }

  /**
   * Sets the field duplicateApp.
   * @param _duplicateApp the new value of the field duplicateApp.
   */
  public void setDuplicateApp(java.lang.Boolean _duplicateApp)
  {
    duplicateApp = _duplicateApp;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Application> effectedApplications;

  /**
   * Gets the field effectedApplications.
   * @return the value of the field effectedApplications; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Application> getEffectedApplications()
  {
    return effectedApplications;
  }

  /**
   * Sets the field effectedApplications.
   * @param _effectedApplications the new value of the field effectedApplications.
   */
  public void setEffectedApplications(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Application> _effectedApplications)
  {
    effectedApplications = _effectedApplications;
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

  private java.lang.String errorMsg;

  /**
   * Gets the field errorMsg.
   * @return the value of the field errorMsg; may be null.
   */
  public java.lang.String getErrorMsg()
  {
    return errorMsg;
  }

  /**
   * Sets the field errorMsg.
   * @param _errorMsg the new value of the field errorMsg.
   */
  public void setErrorMsg(java.lang.String _errorMsg)
  {
    errorMsg = _errorMsg;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> errorMsgs;

  /**
   * Gets the field errorMsgs.
   * @return the value of the field errorMsgs; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getErrorMsgs()
  {
    return errorMsgs;
  }

  /**
   * Sets the field errorMsgs.
   * @param _errorMsgs the new value of the field errorMsgs.
   */
  public void setErrorMsgs(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _errorMsgs)
  {
    errorMsgs = _errorMsgs;
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

  private java.lang.Boolean hasPermission;

  /**
   * Gets the field hasPermission.
   * @return the value of the field hasPermission; may be null.
   */
  public java.lang.Boolean getHasPermission()
  {
    return hasPermission;
  }

  /**
   * Sets the field hasPermission.
   * @param _hasPermission the new value of the field hasPermission.
   */
  public void setHasPermission(java.lang.Boolean _hasPermission)
  {
    hasPermission = _hasPermission;
  }

  private java.lang.Boolean isAddMode;

  /**
   * Gets the field isAddMode.
   * @return the value of the field isAddMode; may be null.
   */
  public java.lang.Boolean getIsAddMode()
  {
    return isAddMode;
  }

  /**
   * Sets the field isAddMode.
   * @param _isAddMode the new value of the field isAddMode.
   */
  public void setIsAddMode(java.lang.Boolean _isAddMode)
  {
    isAddMode = _isAddMode;
  }

  private java.lang.Boolean isIvyApplicationType;

  /**
   * Gets the field isIvyApplicationType.
   * @return the value of the field isIvyApplicationType; may be null.
   */
  public java.lang.Boolean getIsIvyApplicationType()
  {
    return isIvyApplicationType;
  }

  /**
   * Sets the field isIvyApplicationType.
   * @param _isIvyApplicationType the new value of the field isIvyApplicationType.
   */
  public void setIsIvyApplicationType(java.lang.Boolean _isIvyApplicationType)
  {
    isIvyApplicationType = _isIvyApplicationType;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyApplication> ivyApplications;

  /**
   * Gets the field ivyApplications.
   * @return the value of the field ivyApplications; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyApplication> getIvyApplications()
  {
    return ivyApplications;
  }

  /**
   * Sets the field ivyApplications.
   * @param _ivyApplications the new value of the field ivyApplications.
   */
  public void setIvyApplications(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyApplication> _ivyApplications)
  {
    ivyApplications = _ivyApplications;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> languages;

  /**
   * Gets the field languages.
   * @return the value of the field languages; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getLanguages()
  {
    return languages;
  }

  /**
   * Sets the field languages.
   * @param _languages the new value of the field languages.
   */
  public void setLanguages(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _languages)
  {
    languages = _languages;
  }

  /**
   * 1-Server, 2-Application, 3-Setting
   */
  private java.lang.Number objToDelete;

  /**
   * Gets the field objToDelete.
   * @return the value of the field objToDelete; may be null.
   */
  public java.lang.Number getObjToDelete()
  {
    return objToDelete;
  }

  /**
   * Sets the field objToDelete.
   * @param _objToDelete the new value of the field objToDelete.
   */
  public void setObjToDelete(java.lang.Number _objToDelete)
  {
    objToDelete = _objToDelete;
  }

  private transient org.primefaces.event.ReorderEvent reorderEvent;

  /**
   * Gets the field reorderEvent.
   * @return the value of the field reorderEvent; may be null.
   */
  public org.primefaces.event.ReorderEvent getReorderEvent()
  {
    return reorderEvent;
  }

  /**
   * Sets the field reorderEvent.
   * @param _reorderEvent the new value of the field reorderEvent.
   */
  public void setReorderEvent(org.primefaces.event.ReorderEvent _reorderEvent)
  {
    reorderEvent = _reorderEvent;
  }

  private ch.ivy.addon.portalkit.persistence.domain.Application selectedApp;

  /**
   * Gets the field selectedApp.
   * @return the value of the field selectedApp; may be null.
   */
  public ch.ivy.addon.portalkit.persistence.domain.Application getSelectedApp()
  {
    return selectedApp;
  }

  /**
   * Sets the field selectedApp.
   * @param _selectedApp the new value of the field selectedApp.
   */
  public void setSelectedApp(ch.ivy.addon.portalkit.persistence.domain.Application _selectedApp)
  {
    selectedApp = _selectedApp;
  }

  private ch.ivy.ws.addon.IvyApplication selectedIvyApplication;

  /**
   * Gets the field selectedIvyApplication.
   * @return the value of the field selectedIvyApplication; may be null.
   */
  public ch.ivy.ws.addon.IvyApplication getSelectedIvyApplication()
  {
    return selectedIvyApplication;
  }

  /**
   * Sets the field selectedIvyApplication.
   * @param _selectedIvyApplication the new value of the field selectedIvyApplication.
   */
  public void setSelectedIvyApplication(ch.ivy.ws.addon.IvyApplication _selectedIvyApplication)
  {
    selectedIvyApplication = _selectedIvyApplication;
  }

  private ch.ivy.addon.portalkit.persistence.domain.Server selectedServer;

  /**
   * Gets the field selectedServer.
   * @return the value of the field selectedServer; may be null.
   */
  public ch.ivy.addon.portalkit.persistence.domain.Server getSelectedServer()
  {
    return selectedServer;
  }

  /**
   * Sets the field selectedServer.
   * @param _selectedServer the new value of the field selectedServer.
   */
  public void setSelectedServer(ch.ivy.addon.portalkit.persistence.domain.Server _selectedServer)
  {
    selectedServer = _selectedServer;
  }

  private ch.ivy.addon.portalkit.persistence.domain.GlobalSetting selectedSetting;

  /**
   * Gets the field selectedSetting.
   * @return the value of the field selectedSetting; may be null.
   */
  public ch.ivy.addon.portalkit.persistence.domain.GlobalSetting getSelectedSetting()
  {
    return selectedSetting;
  }

  /**
   * Sets the field selectedSetting.
   * @param _selectedSetting the new value of the field selectedSetting.
   */
  public void setSelectedSetting(ch.ivy.addon.portalkit.persistence.domain.GlobalSetting _selectedSetting)
  {
    selectedSetting = _selectedSetting;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Server> serverList;

  /**
   * Gets the field serverList.
   * @return the value of the field serverList; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Server> getServerList()
  {
    return serverList;
  }

  /**
   * Sets the field serverList.
   * @param _serverList the new value of the field serverList.
   */
  public void setServerList(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Server> _serverList)
  {
    serverList = _serverList;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.GlobalSetting> settingList;

  /**
   * Gets the field settingList.
   * @return the value of the field settingList; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.GlobalSetting> getSettingList()
  {
    return settingList;
  }

  /**
   * Sets the field settingList.
   * @param _settingList the new value of the field settingList.
   */
  public void setSettingList(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.GlobalSetting> _settingList)
  {
    settingList = _settingList;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.dto.DisplayName> supportedLanguages;

  /**
   * Gets the field supportedLanguages.
   * @return the value of the field supportedLanguages; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.dto.DisplayName> getSupportedLanguages()
  {
    return supportedLanguages;
  }

  /**
   * Sets the field supportedLanguages.
   * @param _supportedLanguages the new value of the field supportedLanguages.
   */
  public void setSupportedLanguages(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.dto.DisplayName> _supportedLanguages)
  {
    supportedLanguages = _supportedLanguages;
  }

  private java.lang.Integer tabIndexActive;

  /**
   * Gets the field tabIndexActive.
   * @return the value of the field tabIndexActive; may be null.
   */
  public java.lang.Integer getTabIndexActive()
  {
    return tabIndexActive;
  }

  /**
   * Sets the field tabIndexActive.
   * @param _tabIndexActive the new value of the field tabIndexActive.
   */
  public void setTabIndexActive(java.lang.Integer _tabIndexActive)
  {
    tabIndexActive = _tabIndexActive;
  }

  private ch.ivy.addon.portalkit.enums.ApplicationType type;

  /**
   * Gets the field type.
   * @return the value of the field type; may be null.
   */
  public ch.ivy.addon.portalkit.enums.ApplicationType getType()
  {
    return type;
  }

  /**
   * Sets the field type.
   * @param _type the new value of the field type.
   */
  public void setType(ch.ivy.addon.portalkit.enums.ApplicationType _type)
  {
    type = _type;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.User> usersApplicationList;

  /**
   * Gets the field usersApplicationList.
   * @return the value of the field usersApplicationList; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.User> getUsersApplicationList()
  {
    return usersApplicationList;
  }

  /**
   * Sets the field usersApplicationList.
   * @param _usersApplicationList the new value of the field usersApplicationList.
   */
  public void setUsersApplicationList(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.User> _usersApplicationList)
  {
    usersApplicationList = _usersApplicationList;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.User> filteredUsers;

  /**
   * Gets the field filteredUsers.
   * @return the value of the field filteredUsers; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.User> getFilteredUsers()
  {
    return filteredUsers;
  }

  /**
   * Sets the field filteredUsers.
   * @param _filteredUsers the new value of the field filteredUsers.
   */
  public void setFilteredUsers(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.User> _filteredUsers)
  {
    filteredUsers = _filteredUsers;
  }

  private java.lang.String serverExternalHost;

  /**
   * Gets the field serverExternalHost.
   * @return the value of the field serverExternalHost; may be null.
   */
  public java.lang.String getServerExternalHost()
  {
    return serverExternalHost;
  }

  /**
   * Sets the field serverExternalHost.
   * @param _serverExternalHost the new value of the field serverExternalHost.
   */
  public void setServerExternalHost(java.lang.String _serverExternalHost)
  {
    serverExternalHost = _serverExternalHost;
  }

}
