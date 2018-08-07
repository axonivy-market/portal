package ch.ivy.add.portalkit.service.integrators;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class UserServiceIntegratorData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class UserServiceIntegratorData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -3586123880433327718L;

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.Absence> absences;

  /**
   * Gets the field absences.
   * @return the value of the field absences; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.Absence> getAbsences()
  {
    return absences;
  }

  /**
   * Sets the field absences.
   * @param _absences the new value of the field absences.
   */
  public void setAbsences(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.Absence> _absences)
  {
    absences = _absences;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyAbsence> absencesToUpdate;

  /**
   * Gets the field absencesToUpdate.
   * @return the value of the field absencesToUpdate; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyAbsence> getAbsencesToUpdate()
  {
    return absencesToUpdate;
  }

  /**
   * Sets the field absencesToUpdate.
   * @param _absencesToUpdate the new value of the field absencesToUpdate.
   */
  public void setAbsencesToUpdate(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyAbsence> _absencesToUpdate)
  {
    absencesToUpdate = _absencesToUpdate;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> applicationNames;

  /**
   * Gets the field applicationNames.
   * @return the value of the field applicationNames; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getApplicationNames()
  {
    return applicationNames;
  }

  /**
   * Sets the field applicationNames.
   * @param _applicationNames the new value of the field applicationNames.
   */
  public void setApplicationNames(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _applicationNames)
  {
    applicationNames = _applicationNames;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyEmailSetting> emailSettings;

  /**
   * Gets the field emailSettings.
   * @return the value of the field emailSettings; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyEmailSetting> getEmailSettings()
  {
    return emailSettings;
  }

  /**
   * Sets the field emailSettings.
   * @param _emailSettings the new value of the field emailSettings.
   */
  public void setEmailSettings(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyEmailSetting> _emailSettings)
  {
    emailSettings = _emailSettings;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyLanguageSetting> languageSettings;

  /**
   * Gets the field languageSettings.
   * @return the value of the field languageSettings; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyLanguageSetting> getLanguageSettings()
  {
    return languageSettings;
  }

  /**
   * Sets the field languageSettings.
   * @param _languageSettings the new value of the field languageSettings.
   */
  public void setLanguageSettings(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyLanguageSetting> _languageSettings)
  {
    languageSettings = _languageSettings;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvySubstitute> substitutes;

  /**
   * Gets the field substitutes.
   * @return the value of the field substitutes; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvySubstitute> getSubstitutes()
  {
    return substitutes;
  }

  /**
   * Sets the field substitutes.
   * @param _substitutes the new value of the field substitutes.
   */
  public void setSubstitutes(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvySubstitute> _substitutes)
  {
    substitutes = _substitutes;
  }

  private transient java.lang.String targetUser;

  /**
   * Gets the field targetUser.
   * @return the value of the field targetUser; may be null.
   */
  public java.lang.String getTargetUser()
  {
    return targetUser;
  }

  /**
   * Sets the field targetUser.
   * @param _targetUser the new value of the field targetUser.
   */
  public void setTargetUser(java.lang.String _targetUser)
  {
    targetUser = _targetUser;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyUser> users;

  /**
   * Gets the field users.
   * @return the value of the field users; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyUser> getUsers()
  {
    return users;
  }

  /**
   * Sets the field users.
   * @param _users the new value of the field users.
   */
  public void setUsers(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyUser> _users)
  {
    users = _users;
  }

}
