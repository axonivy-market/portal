package ch.ivyteam.wf.processes;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class SynchronizeApplicationUserData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class SynchronizeApplicationUserData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 7069620738616144728L;

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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.User> users;

  /**
   * Gets the field users.
   * @return the value of the field users; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.User> getUsers()
  {
    return users;
  }

  /**
   * Sets the field users.
   * @param _users the new value of the field users.
   */
  public void setUsers(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.User> _users)
  {
    users = _users;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> errorMsgs;

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

}
