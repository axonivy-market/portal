package ch.ivy.addon.portal.generic.CaseDetails;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseDetailsData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseDetailsData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -6892666519187369671L;

  private ch.ivy.addon.portal.generic.CaseDetailsData caseDetailsData;

  /**
   * Gets the field caseDetailsData.
   * @return the value of the field caseDetailsData; may be null.
   */
  public ch.ivy.addon.portal.generic.CaseDetailsData getCaseDetailsData()
  {
    return caseDetailsData;
  }

  /**
   * Sets the field caseDetailsData.
   * @param _caseDetailsData the new value of the field caseDetailsData.
   */
  public void setCaseDetailsData(ch.ivy.addon.portal.generic.CaseDetailsData _caseDetailsData)
  {
    caseDetailsData = _caseDetailsData;
  }

  private ch.ivyteam.ivy.workflow.ICase thisCase;

  /**
   * Gets the field thisCase.
   * @return the value of the field thisCase; may be null.
   */
  public ch.ivyteam.ivy.workflow.ICase getThisCase()
  {
    return thisCase;
  }

  /**
   * Sets the field thisCase.
   * @param _thisCase the new value of the field thisCase.
   */
  public void setThisCase(ch.ivyteam.ivy.workflow.ICase _thisCase)
  {
    thisCase = _thisCase;
  }

  private ch.ivyteam.ivy.workflow.ICase thisTask;

  /**
   * Gets the field thisTask.
   * @return the value of the field thisTask; may be null.
   */
  public ch.ivyteam.ivy.workflow.ICase getThisTask()
  {
    return thisTask;
  }

  /**
   * Sets the field thisTask.
   * @param _thisTask the new value of the field thisTask.
   */
  public void setThisTask(ch.ivyteam.ivy.workflow.ICase _thisTask)
  {
    thisTask = _thisTask;
  }

}
