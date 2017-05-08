package de.eon.gawfs.portal.GAWFSPortalHome;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class GAWFSPortalHomeData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class GAWFSPortalHomeData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 8854750180982786661L;

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

  private org.primefaces.model.chart.BarChartModel barChartProcesses;

  /**
   * Gets the field barChartProcesses.
   * @return the value of the field barChartProcesses; may be null.
   */
  public org.primefaces.model.chart.BarChartModel getBarChartProcesses()
  {
    return barChartProcesses;
  }

  /**
   * Sets the field barChartProcesses.
   * @param _barChartProcesses the new value of the field barChartProcesses.
   */
  public void setBarChartProcesses(org.primefaces.model.chart.BarChartModel _barChartProcesses)
  {
    barChartProcesses = _barChartProcesses;
  }

}
