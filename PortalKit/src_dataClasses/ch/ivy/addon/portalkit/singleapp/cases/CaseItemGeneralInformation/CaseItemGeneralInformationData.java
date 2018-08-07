package ch.ivy.addon.portalkit.singleapp.cases.CaseItemGeneralInformation;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseItemGeneralInformationData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseItemGeneralInformationData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 5290127102880798412L;

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

  private ch.ivyteam.ivy.workflow.ICase internalCase;

  /**
   * Gets the field internalCase.
   * @return the value of the field internalCase; may be null.
   */
  public ch.ivyteam.ivy.workflow.ICase getInternalCase()
  {
    return internalCase;
  }

  /**
   * Sets the field internalCase.
   * @param _internalCase the new value of the field internalCase.
   */
  public void setInternalCase(ch.ivyteam.ivy.workflow.ICase _internalCase)
  {
    internalCase = _internalCase;
  }

}
