package ch.ivy.addon.portalkit.singleapp.cases.CaseItem;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseItemData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseItemData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -7241147723013342355L;

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

  private java.lang.Boolean canChangeCaseDescription;

  /**
   * Gets the field canChangeCaseDescription.
   * @return the value of the field canChangeCaseDescription; may be null.
   */
  public java.lang.Boolean getCanChangeCaseDescription()
  {
    return canChangeCaseDescription;
  }

  /**
   * Sets the field canChangeCaseDescription.
   * @param _canChangeCaseDescription the new value of the field canChangeCaseDescription.
   */
  public void setCanChangeCaseDescription(java.lang.Boolean _canChangeCaseDescription)
  {
    canChangeCaseDescription = _canChangeCaseDescription;
  }

  private java.lang.Boolean canChangeCaseName;

  /**
   * Gets the field canChangeCaseName.
   * @return the value of the field canChangeCaseName; may be null.
   */
  public java.lang.Boolean getCanChangeCaseName()
  {
    return canChangeCaseName;
  }

  /**
   * Sets the field canChangeCaseName.
   * @param _canChangeCaseName the new value of the field canChangeCaseName.
   */
  public void setCanChangeCaseName(java.lang.Boolean _canChangeCaseName)
  {
    canChangeCaseName = _canChangeCaseName;
  }

}
