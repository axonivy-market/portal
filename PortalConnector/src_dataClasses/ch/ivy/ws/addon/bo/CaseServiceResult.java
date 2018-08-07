package ch.ivy.ws.addon.bo;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseServiceResult", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseServiceResult extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 4152270928857944513L;

  private transient java.util.List<ch.ivy.ws.addon.types.IvyAdditionalProperty> addtionalProperties;

  /**
   * Gets the field addtionalProperties.
   * @return the value of the field addtionalProperties; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.IvyAdditionalProperty> getAddtionalProperties()
  {
    return addtionalProperties;
  }

  /**
   * Sets the field addtionalProperties.
   * @param _addtionalProperties the new value of the field addtionalProperties.
   */
  public void setAddtionalProperties(java.util.List<ch.ivy.ws.addon.types.IvyAdditionalProperty> _addtionalProperties)
  {
    addtionalProperties = _addtionalProperties;
  }

  private transient java.util.List<ch.ivy.ws.addon.types.IvyCase> cases;

  /**
   * Gets the field cases.
   * @return the value of the field cases; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.IvyCase> getCases()
  {
    return cases;
  }

  /**
   * Sets the field cases.
   * @param _cases the new value of the field cases.
   */
  public void setCases(java.util.List<ch.ivy.ws.addon.types.IvyCase> _cases)
  {
    cases = _cases;
  }

  private transient ch.ivy.ws.addon.types.IvyDocument document;

  /**
   * Gets the field document.
   * @return the value of the field document; may be null.
   */
  public ch.ivy.ws.addon.types.IvyDocument getDocument()
  {
    return document;
  }

  /**
   * Sets the field document.
   * @param _document the new value of the field document.
   */
  public void setDocument(ch.ivy.ws.addon.types.IvyDocument _document)
  {
    document = _document;
  }

  private transient ch.ivyteam.ivy.scripting.objects.Binary documentContent;

  /**
   * Gets the field documentContent.
   * @return the value of the field documentContent; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.Binary getDocumentContent()
  {
    return documentContent;
  }

  /**
   * Sets the field documentContent.
   * @param _documentContent the new value of the field documentContent.
   */
  public void setDocumentContent(ch.ivyteam.ivy.scripting.objects.Binary _documentContent)
  {
    documentContent = _documentContent;
  }

  private transient java.util.List<ch.ivy.ws.addon.types.IvyDocument> documents;

  /**
   * Gets the field documents.
   * @return the value of the field documents; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.types.IvyDocument> getDocuments()
  {
    return documents;
  }

  /**
   * Sets the field documents.
   * @param _documents the new value of the field documents.
   */
  public void setDocuments(java.util.List<ch.ivy.ws.addon.types.IvyDocument> _documents)
  {
    documents = _documents;
  }

  private transient java.util.List<ch.ivy.ws.addon.WSException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> _errors)
  {
    errors = _errors;
  }

  private transient ch.ivy.ws.addon.types.IvyCase oneCase;

  /**
   * Gets the field oneCase.
   * @return the value of the field oneCase; may be null.
   */
  public ch.ivy.ws.addon.types.IvyCase getOneCase()
  {
    return oneCase;
  }

  /**
   * Sets the field oneCase.
   * @param _oneCase the new value of the field oneCase.
   */
  public void setOneCase(ch.ivy.ws.addon.types.IvyCase _oneCase)
  {
    oneCase = _oneCase;
  }

  private transient java.lang.Long caseCount;

  /**
   * Gets the field caseCount.
   * @return the value of the field caseCount; may be null.
   */
  public java.lang.Long getCaseCount()
  {
    return caseCount;
  }

  /**
   * Sets the field caseCount.
   * @param _caseCount the new value of the field caseCount.
   */
  public void setCaseCount(java.lang.Long _caseCount)
  {
    caseCount = _caseCount;
  }

}
