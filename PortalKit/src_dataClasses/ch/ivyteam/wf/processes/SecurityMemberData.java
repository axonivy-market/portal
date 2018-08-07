package ch.ivyteam.wf.processes;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class SecurityMemberData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class SecurityMemberData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 2509726007123415109L;

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

}
