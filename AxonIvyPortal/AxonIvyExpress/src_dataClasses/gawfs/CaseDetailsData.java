package gawfs;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseDetailsData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseDetailsData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -5360095390055382459L;

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

  private java.lang.Number caseId;

  /**
   * Gets the field caseId.
   * @return the value of the field caseId; may be null.
   */
  public java.lang.Number getCaseId()
  {
    return caseId;
  }

  /**
   * Sets the field caseId.
   * @param _caseId the new value of the field caseId.
   */
  public void setCaseId(java.lang.Number _caseId)
  {
    caseId = _caseId;
  }

}
