package ch.ivyteam.wf.processes;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class SecurityServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class SecurityServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 2713189066583285918L;

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

  private transient ch.ivy.ws.addon.WsException errorWs;

  /**
   * Gets the field errorWs.
   * @return the value of the field errorWs; may be null.
   */
  public ch.ivy.ws.addon.WsException getErrorWs()
  {
    return errorWs;
  }

  /**
   * Sets the field errorWs.
   * @param _errorWs the new value of the field errorWs.
   */
  public void setErrorWs(ch.ivy.ws.addon.WsException _errorWs)
  {
    errorWs = _errorWs;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyRole> ivyRoles;

  /**
   * Gets the field ivyRoles.
   * @return the value of the field ivyRoles; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyRole> getIvyRoles()
  {
    return ivyRoles;
  }

  /**
   * Sets the field ivyRoles.
   * @param _ivyRoles the new value of the field ivyRoles.
   */
  public void setIvyRoles(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyRole> _ivyRoles)
  {
    ivyRoles = _ivyRoles;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyUser> ivyUsers;

  /**
   * Gets the field ivyUsers.
   * @return the value of the field ivyUsers; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyUser> getIvyUsers()
  {
    return ivyUsers;
  }

  /**
   * Sets the field ivyUsers.
   * @param _ivyUsers the new value of the field ivyUsers.
   */
  public void setIvyUsers(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyUser> _ivyUsers)
  {
    ivyUsers = _ivyUsers;
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

  private transient ch.ivy.addon.portalkit.bo.PortalConfiguration portalConfig;

  /**
   * Gets the field portalConfig.
   * @return the value of the field portalConfig; may be null.
   */
  public ch.ivy.addon.portalkit.bo.PortalConfiguration getPortalConfig()
  {
    return portalConfig;
  }

  /**
   * Sets the field portalConfig.
   * @param _portalConfig the new value of the field portalConfig.
   */
  public void setPortalConfig(ch.ivy.addon.portalkit.bo.PortalConfiguration _portalConfig)
  {
    portalConfig = _portalConfig;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IRole> roles;

  /**
   * Gets the field roles.
   * @return the value of the field roles; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IRole> getRoles()
  {
    return roles;
  }

  /**
   * Sets the field roles.
   * @param _roles the new value of the field roles.
   */
  public void setRoles(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IRole> _roles)
  {
    roles = _roles;
  }

  private transient ch.ivyteam.wf.processes.SecurityMemberData securityMemberData;

  /**
   * Gets the field securityMemberData.
   * @return the value of the field securityMemberData; may be null.
   */
  public ch.ivyteam.wf.processes.SecurityMemberData getSecurityMemberData()
  {
    return securityMemberData;
  }

  /**
   * Sets the field securityMemberData.
   * @param _securityMemberData the new value of the field securityMemberData.
   */
  public void setSecurityMemberData(ch.ivyteam.wf.processes.SecurityMemberData _securityMemberData)
  {
    securityMemberData = _securityMemberData;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.ISecurityMember> securityMembers;

  /**
   * Gets the field securityMembers.
   * @return the value of the field securityMembers; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.ISecurityMember> getSecurityMembers()
  {
    return securityMembers;
  }

  /**
   * Sets the field securityMembers.
   * @param _securityMembers the new value of the field securityMembers.
   */
  public void setSecurityMembers(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.ISecurityMember> _securityMembers)
  {
    securityMembers = _securityMembers;
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

  private transient java.lang.Long taskId;

  /**
   * Gets the field taskId.
   * @return the value of the field taskId; may be null.
   */
  public java.lang.Long getTaskId()
  {
    return taskId;
  }

  /**
   * Sets the field taskId.
   * @param _taskId the new value of the field taskId.
   */
  public void setTaskId(java.lang.Long _taskId)
  {
    taskId = _taskId;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteUser> users;

  /**
   * Gets the field users.
   * @return the value of the field users; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteUser> getUsers()
  {
    return users;
  }

  /**
   * Sets the field users.
   * @param _users the new value of the field users.
   */
  public void setUsers(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteUser> _users)
  {
    users = _users;
  }

}
