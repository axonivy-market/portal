package ch.ivy.ws.addon.types;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class IvySubstitute", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class IvySubstitute extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -710604789756951602L;

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

  /**
   * this is empty in case of substitute for personally assigned tasks
   */
  private transient java.lang.String forThisRole;

  /**
   * Gets the field forThisRole.
   * @return the value of the field forThisRole; may be null.
   */
  public java.lang.String getForThisRole()
  {
    return forThisRole;
  }

  /**
   * Sets the field forThisRole.
   * @param _forThisRole the new value of the field forThisRole.
   */
  public void setForThisRole(java.lang.String _forThisRole)
  {
    forThisRole = _forThisRole;
  }

  /**
   * this is empty, if there is no substitute for this role
   */
  private transient java.lang.String mySubstitute;

  /**
   * Gets the field mySubstitute.
   * @return the value of the field mySubstitute; may be null.
   */
  public java.lang.String getMySubstitute()
  {
    return mySubstitute;
  }

  /**
   * Sets the field mySubstitute.
   * @param _mySubstitute the new value of the field mySubstitute.
   */
  public void setMySubstitute(java.lang.String _mySubstitute)
  {
    mySubstitute = _mySubstitute;
  }

  private transient java.lang.String description;

  /**
   * Gets the field description.
   * @return the value of the field description; may be null.
   */
  public java.lang.String getDescription()
  {
    return description;
  }

  /**
   * Sets the field description.
   * @param _description the new value of the field description.
   */
  public void setDescription(java.lang.String _description)
  {
    description = _description;
  }

  /**
   * Display name of Role in case substitute is for role
   */
  private transient java.lang.String roleDisplayName;

  /**
   * Gets the field roleDisplayName.
   * @return the value of the field roleDisplayName; may be null.
   */
  public java.lang.String getRoleDisplayName()
  {
    return roleDisplayName;
  }

  /**
   * Sets the field roleDisplayName.
   * @param _roleDisplayName the new value of the field roleDisplayName.
   */
  public void setRoleDisplayName(java.lang.String _roleDisplayName)
  {
    roleDisplayName = _roleDisplayName;
  }

}
