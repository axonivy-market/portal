package ch.ivy.addon.portalkit.singleapp.tasks.TaskDelegate;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskDelegateData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskDelegateData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -2457680110658876084L;

  private java.lang.Boolean isUser;

  /**
   * Gets the field isUser.
   * @return the value of the field isUser; may be null.
   */
  public java.lang.Boolean getIsUser()
  {
    return isUser;
  }

  /**
   * Sets the field isUser.
   * @param _isUser the new value of the field isUser.
   */
  public void setIsUser(java.lang.Boolean _isUser)
  {
    isUser = _isUser;
  }

  private ch.ivyteam.ivy.security.IUser selectedUser;

  /**
   * Gets the field selectedUser.
   * @return the value of the field selectedUser; may be null.
   */
  public ch.ivyteam.ivy.security.IUser getSelectedUser()
  {
    return selectedUser;
  }

  /**
   * Sets the field selectedUser.
   * @param _selectedUser the new value of the field selectedUser.
   */
  public void setSelectedUser(ch.ivyteam.ivy.security.IUser _selectedUser)
  {
    selectedUser = _selectedUser;
  }

  private ch.ivyteam.ivy.security.IRole selectedRole;

  /**
   * Gets the field selectedRole.
   * @return the value of the field selectedRole; may be null.
   */
  public ch.ivyteam.ivy.security.IRole getSelectedRole()
  {
    return selectedRole;
  }

  /**
   * Sets the field selectedRole.
   * @param _selectedRole the new value of the field selectedRole.
   */
  public void setSelectedRole(ch.ivyteam.ivy.security.IRole _selectedRole)
  {
    selectedRole = _selectedRole;
  }

  private ch.ivyteam.ivy.workflow.ITask itask;

  /**
   * Gets the field itask.
   * @return the value of the field itask; may be null.
   */
  public ch.ivyteam.ivy.workflow.ITask getItask()
  {
    return itask;
  }

  /**
   * Sets the field itask.
   * @param _itask the new value of the field itask.
   */
  public void setItask(ch.ivyteam.ivy.workflow.ITask _itask)
  {
    itask = _itask;
  }

  private java.lang.Boolean disabledDelegateButton;

  /**
   * Gets the field disabledDelegateButton.
   * @return the value of the field disabledDelegateButton; may be null.
   */
  public java.lang.Boolean getDisabledDelegateButton()
  {
    return disabledDelegateButton;
  }

  /**
   * Sets the field disabledDelegateButton.
   * @param _disabledDelegateButton the new value of the field disabledDelegateButton.
   */
  public void setDisabledDelegateButton(java.lang.Boolean _disabledDelegateButton)
  {
    disabledDelegateButton = _disabledDelegateButton;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IRole> roles;

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

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> users;

  /**
   * Gets the field users.
   * @return the value of the field users; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> getUsers()
  {
    return users;
  }

  /**
   * Sets the field users.
   * @param _users the new value of the field users.
   */
  public void setUsers(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> _users)
  {
    users = _users;
  }

}
