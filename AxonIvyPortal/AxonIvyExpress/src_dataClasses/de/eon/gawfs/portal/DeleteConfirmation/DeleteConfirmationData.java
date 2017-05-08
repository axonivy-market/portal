package de.eon.gawfs.portal.DeleteConfirmation;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class DeleteConfirmationData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class DeleteConfirmationData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 418187077613031999L;

  private gawfs.DevLoadWorkflowsData devLoadWorkflowsData;

  /**
   * Gets the field devLoadWorkflowsData.
   * @return the value of the field devLoadWorkflowsData; may be null.
   */
  public gawfs.DevLoadWorkflowsData getDevLoadWorkflowsData()
  {
    return devLoadWorkflowsData;
  }

  /**
   * Sets the field devLoadWorkflowsData.
   * @param _devLoadWorkflowsData the new value of the field devLoadWorkflowsData.
   */
  public void setDevLoadWorkflowsData(gawfs.DevLoadWorkflowsData _devLoadWorkflowsData)
  {
    devLoadWorkflowsData = _devLoadWorkflowsData;
  }

  private ch.ivyteam.ivy.scripting.objects.List<gawfs.AvailWorkflow> workflows;

  /**
   * Gets the field workflows.
   * @return the value of the field workflows; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<gawfs.AvailWorkflow> getWorkflows()
  {
    return workflows;
  }

  /**
   * Sets the field workflows.
   * @param _workflows the new value of the field workflows.
   */
  public void setWorkflows(ch.ivyteam.ivy.scripting.objects.List<gawfs.AvailWorkflow> _workflows)
  {
    workflows = _workflows;
  }

}
