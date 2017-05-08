package ch.ivy.addon.portal.generic.CaseInformation;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseInformationData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseInformationData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -3483313457459252079L;

  private ch.ivyteam.ivy.workflow.ICase casex;

  /**
   * Gets the field casex.
   * @return the value of the field casex; may be null.
   */
  public ch.ivyteam.ivy.workflow.ICase getCasex()
  {
    return casex;
  }

  /**
   * Sets the field casex.
   * @param _casex the new value of the field casex.
   */
  public void setCasex(ch.ivyteam.ivy.workflow.ICase _casex)
  {
    casex = _casex;
  }

  private java.lang.Long caseId;

  /**
   * Gets the field caseId.
   * @return the value of the field caseId; may be null.
   */
  public java.lang.Long getCaseId()
  {
    return caseId;
  }

  /**
   * Sets the field caseId.
   * @param _caseId the new value of the field caseId.
   */
  public void setCaseId(java.lang.Long _caseId)
  {
    caseId = _caseId;
  }

  private java.lang.String statisticDetailLinkUsed;

  /**
   * Gets the field statisticDetailLinkUsed.
   * @return the value of the field statisticDetailLinkUsed; may be null.
   */
  public java.lang.String getStatisticDetailLinkUsed()
  {
    return statisticDetailLinkUsed;
  }

  /**
   * Sets the field statisticDetailLinkUsed.
   * @param _statisticDetailLinkUsed the new value of the field statisticDetailLinkUsed.
   */
  public void setStatisticDetailLinkUsed(java.lang.String _statisticDetailLinkUsed)
  {
    statisticDetailLinkUsed = _statisticDetailLinkUsed;
  }

}
