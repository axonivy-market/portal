package ch.ivy.addon.portalkit.component.TaskItem;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskItemData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskItemData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 8281069178895365608L;

  private ch.ivy.addon.portalkit.bo.RemoteTask task;

  /**
   * Gets the field task.
   * @return the value of the field task; may be null.
   */
  public ch.ivy.addon.portalkit.bo.RemoteTask getTask()
  {
    return task;
  }

  /**
   * Sets the field task.
   * @param _task the new value of the field task.
   */
  public void setTask(ch.ivy.addon.portalkit.bo.RemoteTask _task)
  {
    task = _task;
  }

  private java.lang.Boolean compactMode;

  /**
   * Gets the field compactMode.
   * @return the value of the field compactMode; may be null.
   */
  public java.lang.Boolean getCompactMode()
  {
    return compactMode;
  }

  /**
   * Sets the field compactMode.
   * @param _compactMode the new value of the field compactMode.
   */
  public void setCompactMode(java.lang.Boolean _compactMode)
  {
    compactMode = _compactMode;
  }

  private ch.ivy.addon.portalkit.bo.RemoteTask expandedTask;

  /**
   * Gets the field expandedTask.
   * @return the value of the field expandedTask; may be null.
   */
  public ch.ivy.addon.portalkit.bo.RemoteTask getExpandedTask()
  {
    return expandedTask;
  }

  /**
   * Sets the field expandedTask.
   * @param _expandedTask the new value of the field expandedTask.
   */
  public void setExpandedTask(ch.ivy.addon.portalkit.bo.RemoteTask _expandedTask)
  {
    expandedTask = _expandedTask;
  }

  private ch.ivy.addon.portalkit.bo.RemoteTask selectedTask;

  /**
   * Gets the field selectedTask.
   * @return the value of the field selectedTask; may be null.
   */
  public ch.ivy.addon.portalkit.bo.RemoteTask getSelectedTask()
  {
    return selectedTask;
  }

  /**
   * Sets the field selectedTask.
   * @param _selectedTask the new value of the field selectedTask.
   */
  public void setSelectedTask(ch.ivy.addon.portalkit.bo.RemoteTask _selectedTask)
  {
    selectedTask = _selectedTask;
  }

  private ch.ivy.addon.portalkit.persistence.domain.Server serverOfSelectedTask;

  /**
   * Gets the field serverOfSelectedTask.
   * @return the value of the field serverOfSelectedTask; may be null.
   */
  public ch.ivy.addon.portalkit.persistence.domain.Server getServerOfSelectedTask()
  {
    return serverOfSelectedTask;
  }

  /**
   * Sets the field serverOfSelectedTask.
   * @param _serverOfSelectedTask the new value of the field serverOfSelectedTask.
   */
  public void setServerOfSelectedTask(ch.ivy.addon.portalkit.persistence.domain.Server _serverOfSelectedTask)
  {
    serverOfSelectedTask = _serverOfSelectedTask;
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

  private ch.ivy.ws.addon.IvyRole selectedRole;

  /**
   * Gets the field selectedRole.
   * @return the value of the field selectedRole; may be null.
   */
  public ch.ivy.ws.addon.IvyRole getSelectedRole()
  {
    return selectedRole;
  }

  /**
   * Sets the field selectedRole.
   * @param _selectedRole the new value of the field selectedRole.
   */
  public void setSelectedRole(ch.ivy.ws.addon.IvyRole _selectedRole)
  {
    selectedRole = _selectedRole;
  }

  private ch.ivy.ws.addon.IvyUser selectedUser;

  /**
   * Gets the field selectedUser.
   * @return the value of the field selectedUser; may be null.
   */
  public ch.ivy.ws.addon.IvyUser getSelectedUser()
  {
    return selectedUser;
  }

  /**
   * Sets the field selectedUser.
   * @param _selectedUser the new value of the field selectedUser.
   */
  public void setSelectedUser(ch.ivy.ws.addon.IvyUser _selectedUser)
  {
    selectedUser = _selectedUser;
  }

  private java.lang.Boolean isUserDelegated;

  /**
   * Gets the field isUserDelegated.
   * @return the value of the field isUserDelegated; may be null.
   */
  public java.lang.Boolean getIsUserDelegated()
  {
    return isUserDelegated;
  }

  /**
   * Sets the field isUserDelegated.
   * @param _isUserDelegated the new value of the field isUserDelegated.
   */
  public void setIsUserDelegated(java.lang.Boolean _isUserDelegated)
  {
    isUserDelegated = _isUserDelegated;
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

  private ch.ivy.ws.addon.IvySecurityMember ivySecurityMember;

  /**
   * Gets the field ivySecurityMember.
   * @return the value of the field ivySecurityMember; may be null.
   */
  public ch.ivy.ws.addon.IvySecurityMember getIvySecurityMember()
  {
    return ivySecurityMember;
  }

  /**
   * Sets the field ivySecurityMember.
   * @param _ivySecurityMember the new value of the field ivySecurityMember.
   */
  public void setIvySecurityMember(ch.ivy.ws.addon.IvySecurityMember _ivySecurityMember)
  {
    ivySecurityMember = _ivySecurityMember;
  }

  private java.lang.Boolean canUserResumeTask;

  /**
   * Gets the field canUserResumeTask.
   * @return the value of the field canUserResumeTask; may be null.
   */
  public java.lang.Boolean getCanUserResumeTask()
  {
    return canUserResumeTask;
  }

  /**
   * Sets the field canUserResumeTask.
   * @param _canUserResumeTask the new value of the field canUserResumeTask.
   */
  public void setCanUserResumeTask(java.lang.Boolean _canUserResumeTask)
  {
    canUserResumeTask = _canUserResumeTask;
  }

  private ch.ivyteam.wf.processes.SecurityMemberData securityMemberData;

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

  private java.lang.String isAnotherUserWorkingMessage;

  /**
   * Gets the field isAnotherUserWorkingMessage.
   * @return the value of the field isAnotherUserWorkingMessage; may be null.
   */
  public java.lang.String getIsAnotherUserWorkingMessage()
  {
    return isAnotherUserWorkingMessage;
  }

  /**
   * Sets the field isAnotherUserWorkingMessage.
   * @param _isAnotherUserWorkingMessage the new value of the field isAnotherUserWorkingMessage.
   */
  public void setIsAnotherUserWorkingMessage(java.lang.String _isAnotherUserWorkingMessage)
  {
    isAnotherUserWorkingMessage = _isAnotherUserWorkingMessage;
  }

  private java.lang.String workerUserName;

  /**
   * Gets the field workerUserName.
   * @return the value of the field workerUserName; may be null.
   */
  public java.lang.String getWorkerUserName()
  {
    return workerUserName;
  }

  /**
   * Sets the field workerUserName.
   * @param _workerUserName the new value of the field workerUserName.
   */
  public void setWorkerUserName(java.lang.String _workerUserName)
  {
    workerUserName = _workerUserName;
  }

  private ch.ivy.ws.addon.IvyTask ivyTask;

  /**
   * Gets the field ivyTask.
   * @return the value of the field ivyTask; may be null.
   */
  public ch.ivy.ws.addon.IvyTask getIvyTask()
  {
    return ivyTask;
  }

  /**
   * Sets the field ivyTask.
   * @param _ivyTask the new value of the field ivyTask.
   */
  public void setIvyTask(ch.ivy.ws.addon.IvyTask _ivyTask)
  {
    ivyTask = _ivyTask;
  }

  private java.lang.String changeNameNoteContent;

  /**
   * Gets the field changeNameNoteContent.
   * @return the value of the field changeNameNoteContent; may be null.
   */
  public java.lang.String getChangeNameNoteContent()
  {
    return changeNameNoteContent;
  }

  /**
   * Sets the field changeNameNoteContent.
   * @param _changeNameNoteContent the new value of the field changeNameNoteContent.
   */
  public void setChangeNameNoteContent(java.lang.String _changeNameNoteContent)
  {
    changeNameNoteContent = _changeNameNoteContent;
  }

  private java.lang.String oldName;

  /**
   * Gets the field oldName.
   * @return the value of the field oldName; may be null.
   */
  public java.lang.String getOldName()
  {
    return oldName;
  }

  /**
   * Sets the field oldName.
   * @param _oldName the new value of the field oldName.
   */
  public void setOldName(java.lang.String _oldName)
  {
    oldName = _oldName;
  }

}
