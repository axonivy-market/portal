package de.eon.gawfs.workflowCreation.WorkflowDefinition;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class WorkflowDefinitionData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class WorkflowDefinitionData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -2609494539449598105L;

  private gawfs.Data data;

  /**
   * Gets the field data.
   * @return the value of the field data; may be null.
   */
  public gawfs.Data getData()
  {
    return data;
  }

  /**
   * Sets the field data.
   * @param _data the new value of the field data.
   */
  public void setData(gawfs.Data _data)
  {
    data = _data;
  }

  private java.lang.Number one;

  /**
   * Gets the field one.
   * @return the value of the field one; may be null.
   */
  public java.lang.Number getOne()
  {
    return one;
  }

  /**
   * Sets the field one.
   * @param _one the new value of the field one.
   */
  public void setOne(java.lang.Number _one)
  {
    one = _one;
  }

  private gawfs.TaskDef openTask;

  /**
   * Gets the field openTask.
   * @return the value of the field openTask; may be null.
   */
  public gawfs.TaskDef getOpenTask()
  {
    return openTask;
  }

  /**
   * Sets the field openTask.
   * @param _openTask the new value of the field openTask.
   */
  public void setOpenTask(gawfs.TaskDef _openTask)
  {
    openTask = _openTask;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.ISecurityMember> AvailableRolesAndUsers;

  /**
   * Gets the field AvailableRolesAndUsers.
   * @return the value of the field AvailableRolesAndUsers; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.ISecurityMember> getAvailableRolesAndUsers()
  {
    return AvailableRolesAndUsers;
  }

  /**
   * Sets the field AvailableRolesAndUsers.
   * @param _AvailableRolesAndUsers the new value of the field AvailableRolesAndUsers.
   */
  public void setAvailableRolesAndUsers(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.ISecurityMember> _AvailableRolesAndUsers)
  {
    AvailableRolesAndUsers = _AvailableRolesAndUsers;
  }

  private ch.ivyteam.ivy.security.ISecurityMember selectedRoleOrUser;

  /**
   * Gets the field selectedRoleOrUser.
   * @return the value of the field selectedRoleOrUser; may be null.
   */
  public ch.ivyteam.ivy.security.ISecurityMember getSelectedRoleOrUser()
  {
    return selectedRoleOrUser;
  }

  /**
   * Sets the field selectedRoleOrUser.
   * @param _selectedRoleOrUser the new value of the field selectedRoleOrUser.
   */
  public void setSelectedRoleOrUser(ch.ivyteam.ivy.security.ISecurityMember _selectedRoleOrUser)
  {
    selectedRoleOrUser = _selectedRoleOrUser;
  }

}
