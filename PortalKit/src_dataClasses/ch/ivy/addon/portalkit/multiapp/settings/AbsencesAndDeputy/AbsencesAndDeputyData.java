package ch.ivy.addon.portalkit.multiapp.settings.AbsencesAndDeputy;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class AbsencesAndDeputyData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class AbsencesAndDeputyData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -4527862267313494337L;

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> absences;

  /**
   * Gets the field absences.
   * @return the value of the field absences; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> getAbsences()
  {
    return absences;
  }

  /**
   * Sets the field absences.
   * @param _absences the new value of the field absences.
   */
  public void setAbsences(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> _absences)
  {
    absences = _absences;
  }

  private java.util.List<ch.ivy.addon.portalkit.bo.ServerApplication> absencesApplications;

  /**
   * Gets the field absencesApplications.
   * @return the value of the field absencesApplications; may be null.
   */
  public java.util.List<ch.ivy.addon.portalkit.bo.ServerApplication> getAbsencesApplications()
  {
    return absencesApplications;
  }

  /**
   * Sets the field absencesApplications.
   * @param _absencesApplications the new value of the field absencesApplications.
   */
  public void setAbsencesApplications(java.util.List<ch.ivy.addon.portalkit.bo.ServerApplication> _absencesApplications)
  {
    absencesApplications = _absencesApplications;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> absencesToUpdate;

  /**
   * Gets the field absencesToUpdate.
   * @return the value of the field absencesToUpdate; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> getAbsencesToUpdate()
  {
    return absencesToUpdate;
  }

  /**
   * Sets the field absencesToUpdate.
   * @param _absencesToUpdate the new value of the field absencesToUpdate.
   */
  public void setAbsencesToUpdate(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> _absencesToUpdate)
  {
    absencesToUpdate = _absencesToUpdate;
  }

  private ch.ivy.addon.portalkit.bo.RemoteAbsence backupAbsence;

  /**
   * Gets the field backupAbsence.
   * @return the value of the field backupAbsence; may be null.
   */
  public ch.ivy.addon.portalkit.bo.RemoteAbsence getBackupAbsence()
  {
    return backupAbsence;
  }

  /**
   * Sets the field backupAbsence.
   * @param _backupAbsence the new value of the field backupAbsence.
   */
  public void setBackupAbsence(ch.ivy.addon.portalkit.bo.RemoteAbsence _backupAbsence)
  {
    backupAbsence = _backupAbsence;
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

  private transient java.lang.Boolean isLoadDeputy;

  /**
   * Gets the field isLoadDeputy.
   * @return the value of the field isLoadDeputy; may be null.
   */
  public java.lang.Boolean getIsLoadDeputy()
  {
    return isLoadDeputy;
  }

  /**
   * Sets the field isLoadDeputy.
   * @param _isLoadDeputy the new value of the field isLoadDeputy.
   */
  public void setIsLoadDeputy(java.lang.Boolean _isLoadDeputy)
  {
    isLoadDeputy = _isLoadDeputy;
  }

  private transient java.lang.Boolean isSupervisor;

  /**
   * Gets the field isSupervisor.
   * @return the value of the field isSupervisor; may be null.
   */
  public java.lang.Boolean getIsSupervisor()
  {
    return isSupervisor;
  }

  /**
   * Sets the field isSupervisor.
   * @param _isSupervisor the new value of the field isSupervisor.
   */
  public void setIsSupervisor(java.lang.Boolean _isSupervisor)
  {
    isSupervisor = _isSupervisor;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteApplicationUser> ivyUsers;

  /**
   * Gets the field ivyUsers.
   * @return the value of the field ivyUsers; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteApplicationUser> getIvyUsers()
  {
    return ivyUsers;
  }

  /**
   * Sets the field ivyUsers.
   * @param _ivyUsers the new value of the field ivyUsers.
   */
  public void setIvyUsers(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteApplicationUser> _ivyUsers)
  {
    ivyUsers = _ivyUsers;
  }

  private transient java.lang.String queryAutoComplete;

  /**
   * Gets the field queryAutoComplete.
   * @return the value of the field queryAutoComplete; may be null.
   */
  public java.lang.String getQueryAutoComplete()
  {
    return queryAutoComplete;
  }

  /**
   * Sets the field queryAutoComplete.
   * @param _queryAutoComplete the new value of the field queryAutoComplete.
   */
  public void setQueryAutoComplete(java.lang.String _queryAutoComplete)
  {
    queryAutoComplete = _queryAutoComplete;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteUser> remoteUsers;

  /**
   * Gets the field remoteUsers.
   * @return the value of the field remoteUsers; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteUser> getRemoteUsers()
  {
    return remoteUsers;
  }

  /**
   * Sets the field remoteUsers.
   * @param _remoteUsers the new value of the field remoteUsers.
   */
  public void setRemoteUsers(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteUser> _remoteUsers)
  {
    remoteUsers = _remoteUsers;
  }

  private ch.ivy.addon.portalkit.bo.RemoteAbsence selectedAbsence;

  /**
   * Gets the field selectedAbsence.
   * @return the value of the field selectedAbsence; may be null.
   */
  public ch.ivy.addon.portalkit.bo.RemoteAbsence getSelectedAbsence()
  {
    return selectedAbsence;
  }

  /**
   * Sets the field selectedAbsence.
   * @param _selectedAbsence the new value of the field selectedAbsence.
   */
  public void setSelectedAbsence(ch.ivy.addon.portalkit.bo.RemoteAbsence _selectedAbsence)
  {
    selectedAbsence = _selectedAbsence;
  }

  private org.primefaces.model.TreeNode substituteRoot;

  /**
   * Gets the field substituteRoot.
   * @return the value of the field substituteRoot; may be null.
   */
  public org.primefaces.model.TreeNode getSubstituteRoot()
  {
    return substituteRoot;
  }

  /**
   * Sets the field substituteRoot.
   * @param _substituteRoot the new value of the field substituteRoot.
   */
  public void setSubstituteRoot(org.primefaces.model.TreeNode _substituteRoot)
  {
    substituteRoot = _substituteRoot;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteSubstitute> substitutes;

  /**
   * Gets the field substitutes.
   * @return the value of the field substitutes; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteSubstitute> getSubstitutes()
  {
    return substitutes;
  }

  /**
   * Sets the field substitutes.
   * @param _substitutes the new value of the field substitutes.
   */
  public void setSubstitutes(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteSubstitute> _substitutes)
  {
    substitutes = _substitutes;
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

  private transient java.util.Set<java.lang.String> userNames;

  /**
   * Gets the field userNames.
   * @return the value of the field userNames; may be null.
   */
  public java.util.Set<java.lang.String> getUserNames()
  {
    return userNames;
  }

  /**
   * Sets the field userNames.
   * @param _userNames the new value of the field userNames.
   */
  public void setUserNames(java.util.Set<java.lang.String> _userNames)
  {
    userNames = _userNames;
  }

  private transient java.lang.Boolean validationError;

  /**
   * Gets the field validationError.
   * @return the value of the field validationError; may be null.
   */
  public java.lang.Boolean getValidationError()
  {
    return validationError;
  }

  /**
   * Sets the field validationError.
   * @param _validationError the new value of the field validationError.
   */
  public void setValidationError(java.lang.Boolean _validationError)
  {
    validationError = _validationError;
  }

  private java.lang.Boolean absenceInThePastShown;

  /**
   * Gets the field absenceInThePastShown.
   * @return the value of the field absenceInThePastShown; may be null.
   */
  public java.lang.Boolean getAbsenceInThePastShown()
  {
    return absenceInThePastShown;
  }

  /**
   * Sets the field absenceInThePastShown.
   * @param _absenceInThePastShown the new value of the field absenceInThePastShown.
   */
  public void setAbsenceInThePastShown(java.lang.Boolean _absenceInThePastShown)
  {
    absenceInThePastShown = _absenceInThePastShown;
  }

  private ch.ivy.addon.portalkit.bo.RemoteUser selectedUser;

  /**
   * Gets the field selectedUser.
   * @return the value of the field selectedUser; may be null.
   */
  public ch.ivy.addon.portalkit.bo.RemoteUser getSelectedUser()
  {
    return selectedUser;
  }

  /**
   * Sets the field selectedUser.
   * @param _selectedUser the new value of the field selectedUser.
   */
  public void setSelectedUser(ch.ivy.addon.portalkit.bo.RemoteUser _selectedUser)
  {
    selectedUser = _selectedUser;
  }

  private java.lang.String userFullName;

  /**
   * Gets the field userFullName.
   * @return the value of the field userFullName; may be null.
   */
  public java.lang.String getUserFullName()
  {
    return userFullName;
  }

  /**
   * Sets the field userFullName.
   * @param _userFullName the new value of the field userFullName.
   */
  public void setUserFullName(java.lang.String _userFullName)
  {
    userFullName = _userFullName;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> displayedAbsences;

  /**
   * Gets the field displayedAbsences.
   * @return the value of the field displayedAbsences; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> getDisplayedAbsences()
  {
    return displayedAbsences;
  }

  /**
   * Sets the field displayedAbsences.
   * @param _displayedAbsences the new value of the field displayedAbsences.
   */
  public void setDisplayedAbsences(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> _displayedAbsences)
  {
    displayedAbsences = _displayedAbsences;
  }

}
