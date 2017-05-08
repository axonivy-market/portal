package de.eon.gawfs.workflowExecution.WfFirstInputForm;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class WfFirstInputFormData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class WfFirstInputFormData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 4619006407335208851L;

  private gawfs.ExecutePredefinedWorkflowData executePredefinedWorkflowData;

  /**
   * Gets the field executePredefinedWorkflowData.
   * @return the value of the field executePredefinedWorkflowData; may be null.
   */
  public gawfs.ExecutePredefinedWorkflowData getExecutePredefinedWorkflowData()
  {
    return executePredefinedWorkflowData;
  }

  /**
   * Sets the field executePredefinedWorkflowData.
   * @param _executePredefinedWorkflowData the new value of the field executePredefinedWorkflowData.
   */
  public void setExecutePredefinedWorkflowData(gawfs.ExecutePredefinedWorkflowData _executePredefinedWorkflowData)
  {
    executePredefinedWorkflowData = _executePredefinedWorkflowData;
  }

  /**
   * 0=Self 1=s.o.else
   */
  private java.lang.Integer assignementType;

  /**
   * Gets the field assignementType.
   * @return the value of the field assignementType; may be null.
   */
  public java.lang.Integer getAssignementType()
  {
    return assignementType;
  }

  /**
   * Sets the field assignementType.
   * @param _assignementType the new value of the field assignementType.
   */
  public void setAssignementType(java.lang.Integer _assignementType)
  {
    assignementType = _assignementType;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> availUsers;

  /**
   * Gets the field availUsers.
   * @return the value of the field availUsers; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> getAvailUsers()
  {
    return availUsers;
  }

  /**
   * Sets the field availUsers.
   * @param _availUsers the new value of the field availUsers.
   */
  public void setAvailUsers(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> _availUsers)
  {
    availUsers = _availUsers;
  }

}
