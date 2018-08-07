package ch.ivy.addon.portal.generic.CaseDetails;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseDetailsData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseDetailsData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -3531517660098043515L;

  private ch.ivyteam.ivy.workflow.ICase currentCase;

  /**
   * Gets the field currentCase.
   * @return the value of the field currentCase; may be null.
   */
  public ch.ivyteam.ivy.workflow.ICase getCurrentCase()
  {
    return currentCase;
  }

  /**
   * Sets the field currentCase.
   * @param _currentCase the new value of the field currentCase.
   */
  public void setCurrentCase(ch.ivyteam.ivy.workflow.ICase _currentCase)
  {
    currentCase = _currentCase;
  }

  private java.lang.Number currentCaseId;

  /**
   * Gets the field currentCaseId.
   * @return the value of the field currentCaseId; may be null.
   */
  public java.lang.Number getCurrentCaseId()
  {
    return currentCaseId;
  }

  /**
   * Sets the field currentCaseId.
   * @param _currentCaseId the new value of the field currentCaseId.
   */
  public void setCurrentCaseId(java.lang.Number _currentCaseId)
  {
    currentCaseId = _currentCaseId;
  }

}
