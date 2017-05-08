package gawfs;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class loadWorkflowData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class loadWorkflowData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -6562888102468854410L;

  private ch.ivyteam.ivy.scripting.objects.List<gawfs.Workflow> workflows;

  /**
   * Gets the field workflows.
   * @return the value of the field workflows; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<gawfs.Workflow> getWorkflows()
  {
    return workflows;
  }

  /**
   * Sets the field workflows.
   * @param _workflows the new value of the field workflows.
   */
  public void setWorkflows(ch.ivyteam.ivy.scripting.objects.List<gawfs.Workflow> _workflows)
  {
    workflows = _workflows;
  }

  private java.lang.Integer workflowID;

  /**
   * Gets the field workflowID.
   * @return the value of the field workflowID; may be null.
   */
  public java.lang.Integer getWorkflowID()
  {
    return workflowID;
  }

  /**
   * Sets the field workflowID.
   * @param _workflowID the new value of the field workflowID.
   */
  public void setWorkflowID(java.lang.Integer _workflowID)
  {
    workflowID = _workflowID;
  }

  private java.lang.String workflowName;

  /**
   * Gets the field workflowName.
   * @return the value of the field workflowName; may be null.
   */
  public java.lang.String getWorkflowName()
  {
    return workflowName;
  }

  /**
   * Sets the field workflowName.
   * @param _workflowName the new value of the field workflowName.
   */
  public void setWorkflowName(java.lang.String _workflowName)
  {
    workflowName = _workflowName;
  }

  private java.lang.String workflowDescription;

  /**
   * Gets the field workflowDescription.
   * @return the value of the field workflowDescription; may be null.
   */
  public java.lang.String getWorkflowDescription()
  {
    return workflowDescription;
  }

  /**
   * Sets the field workflowDescription.
   * @param _workflowDescription the new value of the field workflowDescription.
   */
  public void setWorkflowDescription(java.lang.String _workflowDescription)
  {
    workflowDescription = _workflowDescription;
  }

  private java.lang.String workflowType;

  /**
   * Gets the field workflowType.
   * @return the value of the field workflowType; may be null.
   */
  public java.lang.String getWorkflowType()
  {
    return workflowType;
  }

  /**
   * Sets the field workflowType.
   * @param _workflowType the new value of the field workflowType.
   */
  public void setWorkflowType(java.lang.String _workflowType)
  {
    workflowType = _workflowType;
  }

  private ch.ivyteam.ivy.scripting.objects.List<gawfs.Workflow> devWorkflows;

  /**
   * Gets the field devWorkflows.
   * @return the value of the field devWorkflows; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<gawfs.Workflow> getDevWorkflows()
  {
    return devWorkflows;
  }

  /**
   * Sets the field devWorkflows.
   * @param _devWorkflows the new value of the field devWorkflows.
   */
  public void setDevWorkflows(ch.ivyteam.ivy.scripting.objects.List<gawfs.Workflow> _devWorkflows)
  {
    devWorkflows = _devWorkflows;
  }

  private java.lang.Boolean deleteFlag;

  /**
   * Gets the field deleteFlag.
   * @return the value of the field deleteFlag; may be null.
   */
  public java.lang.Boolean getDeleteFlag()
  {
    return deleteFlag;
  }

  /**
   * Sets the field deleteFlag.
   * @param _deleteFlag the new value of the field deleteFlag.
   */
  public void setDeleteFlag(java.lang.Boolean _deleteFlag)
  {
    deleteFlag = _deleteFlag;
  }

  private java.lang.Boolean editFlag;

  /**
   * Gets the field editFlag.
   * @return the value of the field editFlag; may be null.
   */
  public java.lang.Boolean getEditFlag()
  {
    return editFlag;
  }

  /**
   * Sets the field editFlag.
   * @param _editFlag the new value of the field editFlag.
   */
  public void setEditFlag(java.lang.Boolean _editFlag)
  {
    editFlag = _editFlag;
  }

}
