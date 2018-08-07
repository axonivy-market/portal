package ch.ivyteam.wf.processes;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class AbsenceServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class AbsenceServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 4136011376393977383L;

  private transient java.util.List<ch.ivy.addon.portalkit.bo.ServerApplication> absenceApplications;

  /**
   * Gets the field absenceApplications.
   * @return the value of the field absenceApplications; may be null.
   */
  public java.util.List<ch.ivy.addon.portalkit.bo.ServerApplication> getAbsenceApplications()
  {
    return absenceApplications;
  }

  /**
   * Sets the field absenceApplications.
   * @param _absenceApplications the new value of the field absenceApplications.
   */
  public void setAbsenceApplications(java.util.List<ch.ivy.addon.portalkit.bo.ServerApplication> _absenceApplications)
  {
    absenceApplications = _absenceApplications;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyAbsence> absences;

  /**
   * Gets the field absences.
   * @return the value of the field absences; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyAbsence> getAbsences()
  {
    return absences;
  }

  /**
   * Sets the field absences.
   * @param _absences the new value of the field absences.
   */
  public void setAbsences(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyAbsence> _absences)
  {
    absences = _absences;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.Absence> absencesGroupedByUserNames;

  /**
   * Gets the field absencesGroupedByUserNames.
   * @return the value of the field absencesGroupedByUserNames; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.Absence> getAbsencesGroupedByUserNames()
  {
    return absencesGroupedByUserNames;
  }

  /**
   * Sets the field absencesGroupedByUserNames.
   * @param _absencesGroupedByUserNames the new value of the field absencesGroupedByUserNames.
   */
  public void setAbsencesGroupedByUserNames(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.Absence> _absencesGroupedByUserNames)
  {
    absencesGroupedByUserNames = _absencesGroupedByUserNames;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Server> servers;

  /**
   * Gets the field servers.
   * @return the value of the field servers; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Server> getServers()
  {
    return servers;
  }

  /**
   * Sets the field servers.
   * @param _servers the new value of the field servers.
   */
  public void setServers(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Server> _servers)
  {
    servers = _servers;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> remoteAbsences;

  /**
   * Gets the field remoteAbsences.
   * @return the value of the field remoteAbsences; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> getRemoteAbsences()
  {
    return remoteAbsences;
  }

  /**
   * Sets the field remoteAbsences.
   * @param _remoteAbsences the new value of the field remoteAbsences.
   */
  public void setRemoteAbsences(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> _remoteAbsences)
  {
    remoteAbsences = _remoteAbsences;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> remoteAbsencesInput;

  /**
   * Gets the field remoteAbsencesInput.
   * @return the value of the field remoteAbsencesInput; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> getRemoteAbsencesInput()
  {
    return remoteAbsencesInput;
  }

  /**
   * Sets the field remoteAbsencesInput.
   * @param _remoteAbsencesInput the new value of the field remoteAbsencesInput.
   */
  public void setRemoteAbsencesInput(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteAbsence> _remoteAbsencesInput)
  {
    remoteAbsencesInput = _remoteAbsencesInput;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteApplicationUser> remoteApplicationUsers;

  /**
   * Gets the field remoteApplicationUsers.
   * @return the value of the field remoteApplicationUsers; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteApplicationUser> getRemoteApplicationUsers()
  {
    return remoteApplicationUsers;
  }

  /**
   * Sets the field remoteApplicationUsers.
   * @param _remoteApplicationUsers the new value of the field remoteApplicationUsers.
   */
  public void setRemoteApplicationUsers(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteApplicationUser> _remoteApplicationUsers)
  {
    remoteApplicationUsers = _remoteApplicationUsers;
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

}
