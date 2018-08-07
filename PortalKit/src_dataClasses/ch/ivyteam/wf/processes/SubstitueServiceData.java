package ch.ivyteam.wf.processes;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class SubstitueServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class SubstitueServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -7062396454267722925L;

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

  private transient java.util.List<ch.ivy.addon.portalkit.bo.RemoteSubstitute> remoteSusbtitutes;

  /**
   * Gets the field remoteSusbtitutes.
   * @return the value of the field remoteSusbtitutes; may be null.
   */
  public java.util.List<ch.ivy.addon.portalkit.bo.RemoteSubstitute> getRemoteSusbtitutes()
  {
    return remoteSusbtitutes;
  }

  /**
   * Sets the field remoteSusbtitutes.
   * @param _remoteSusbtitutes the new value of the field remoteSusbtitutes.
   */
  public void setRemoteSusbtitutes(java.util.List<ch.ivy.addon.portalkit.bo.RemoteSubstitute> _remoteSusbtitutes)
  {
    remoteSusbtitutes = _remoteSusbtitutes;
  }

  private transient java.util.List<ch.ivy.ws.addon.IvySubstitute> substitutes;

  /**
   * Gets the field substitutes.
   * @return the value of the field substitutes; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.IvySubstitute> getSubstitutes()
  {
    return substitutes;
  }

  /**
   * Sets the field substitutes.
   * @param _substitutes the new value of the field substitutes.
   */
  public void setSubstitutes(java.util.List<ch.ivy.ws.addon.IvySubstitute> _substitutes)
  {
    substitutes = _substitutes;
  }

  private transient java.util.List<ch.ivy.ws.addon.IvyUser> applicationUsers;

  /**
   * Gets the field applicationUsers.
   * @return the value of the field applicationUsers; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.IvyUser> getApplicationUsers()
  {
    return applicationUsers;
  }

  /**
   * Sets the field applicationUsers.
   * @param _applicationUsers the new value of the field applicationUsers.
   */
  public void setApplicationUsers(java.util.List<ch.ivy.ws.addon.IvyUser> _applicationUsers)
  {
    applicationUsers = _applicationUsers;
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

}
