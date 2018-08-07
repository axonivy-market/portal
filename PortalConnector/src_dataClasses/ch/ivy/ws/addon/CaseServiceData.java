package ch.ivy.ws.addon;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -2527624526140560103L;

  private transient java.util.Map<java.lang.String,java.lang.String> additionalProperties;

  /**
   * Gets the field additionalProperties.
   * @return the value of the field additionalProperties; may be null.
   */
  public java.util.Map<java.lang.String,java.lang.String> getAdditionalProperties()
  {
    return additionalProperties;
  }

  /**
   * Sets the field additionalProperties.
   * @param _additionalProperties the new value of the field additionalProperties.
   */
  public void setAdditionalProperties(java.util.Map<java.lang.String,java.lang.String> _additionalProperties)
  {
    additionalProperties = _additionalProperties;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> applications;

  /**
   * Gets the field applications.
   * @return the value of the field applications; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getApplications()
  {
    return applications;
  }

  /**
   * Sets the field applications.
   * @param _applications the new value of the field applications.
   */
  public void setApplications(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _applications)
  {
    applications = _applications;
  }

  private transient ch.ivy.ws.addon.service.CaseSearchCriteria caseSearchCriteria;

  /**
   * Gets the field caseSearchCriteria.
   * @return the value of the field caseSearchCriteria; may be null.
   */
  public ch.ivy.ws.addon.service.CaseSearchCriteria getCaseSearchCriteria()
  {
    return caseSearchCriteria;
  }

  /**
   * Sets the field caseSearchCriteria.
   * @param _caseSearchCriteria the new value of the field caseSearchCriteria.
   */
  public void setCaseSearchCriteria(ch.ivy.ws.addon.service.CaseSearchCriteria _caseSearchCriteria)
  {
    caseSearchCriteria = _caseSearchCriteria;
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

  private transient java.lang.Long documentId;

  /**
   * Gets the field documentId.
   * @return the value of the field documentId; may be null.
   */
  public java.lang.Long getDocumentId()
  {
    return documentId;
  }

  /**
   * Sets the field documentId.
   * @param _documentId the new value of the field documentId.
   */
  public void setDocumentId(java.lang.Long _documentId)
  {
    documentId = _documentId;
  }

  private transient java.lang.String documentName;

  /**
   * Gets the field documentName.
   * @return the value of the field documentName; may be null.
   */
  public java.lang.String getDocumentName()
  {
    return documentName;
  }

  /**
   * Sets the field documentName.
   * @param _documentName the new value of the field documentName.
   */
  public void setDocumentName(java.lang.String _documentName)
  {
    documentName = _documentName;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyDocument> documents;

  /**
   * Gets the field documents.
   * @return the value of the field documents; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyDocument> getDocuments()
  {
    return documents;
  }

  /**
   * Sets the field documents.
   * @param _documents the new value of the field documents.
   */
  public void setDocuments(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyDocument> _documents)
  {
    documents = _documents;
  }

  private transient ch.ivy.ws.addon.WSException error;

  /**
   * Gets the field error.
   * @return the value of the field error; may be null.
   */
  public ch.ivy.ws.addon.WSException getError()
  {
    return error;
  }

  /**
   * Sets the field error.
   * @param _error the new value of the field error.
   */
  public void setError(ch.ivy.ws.addon.WSException _error)
  {
    error = _error;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyAdditionalProperty> ivyAdditionalPropertiesList;

  /**
   * Gets the field ivyAdditionalPropertiesList.
   * @return the value of the field ivyAdditionalPropertiesList; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyAdditionalProperty> getIvyAdditionalPropertiesList()
  {
    return ivyAdditionalPropertiesList;
  }

  /**
   * Sets the field ivyAdditionalPropertiesList.
   * @param _ivyAdditionalPropertiesList the new value of the field ivyAdditionalPropertiesList.
   */
  public void setIvyAdditionalPropertiesList(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyAdditionalProperty> _ivyAdditionalPropertiesList)
  {
    ivyAdditionalPropertiesList = _ivyAdditionalPropertiesList;
  }

  private transient ch.ivy.ws.addon.types.IvyCase ivyCase;

  /**
   * Gets the field ivyCase.
   * @return the value of the field ivyCase; may be null.
   */
  public ch.ivy.ws.addon.types.IvyCase getIvyCase()
  {
    return ivyCase;
  }

  /**
   * Sets the field ivyCase.
   * @param _ivyCase the new value of the field ivyCase.
   */
  public void setIvyCase(ch.ivy.ws.addon.types.IvyCase _ivyCase)
  {
    ivyCase = _ivyCase;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyCase> ivyCases;

  /**
   * Gets the field ivyCases.
   * @return the value of the field ivyCases; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyCase> getIvyCases()
  {
    return ivyCases;
  }

  /**
   * Sets the field ivyCases.
   * @param _ivyCases the new value of the field ivyCases.
   */
  public void setIvyCases(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyCase> _ivyCases)
  {
    ivyCases = _ivyCases;
  }

  private transient ch.ivy.ws.addon.types.IvyNote note;

  /**
   * Gets the field note.
   * @return the value of the field note; may be null.
   */
  public ch.ivy.ws.addon.types.IvyNote getNote()
  {
    return note;
  }

  /**
   * Sets the field note.
   * @param _note the new value of the field note.
   */
  public void setNote(ch.ivy.ws.addon.types.IvyNote _note)
  {
    note = _note;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyNote> notes;

  /**
   * Gets the field notes.
   * @return the value of the field notes; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyNote> getNotes()
  {
    return notes;
  }

  /**
   * Sets the field notes.
   * @param _notes the new value of the field notes.
   */
  public void setNotes(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyNote> _notes)
  {
    notes = _notes;
  }

  private transient java.lang.String user;

  /**
   * Gets the field user.
   * @return the value of the field user; may be null.
   */
  public java.lang.String getUser()
  {
    return user;
  }

  /**
   * Sets the field user.
   * @param _user the new value of the field user.
   */
  public void setUser(java.lang.String _user)
  {
    user = _user;
  }

  private transient java.lang.Integer startIndex;

  /**
   * Gets the field startIndex.
   * @return the value of the field startIndex; may be null.
   */
  public java.lang.Integer getStartIndex()
  {
    return startIndex;
  }

  /**
   * Sets the field startIndex.
   * @param _startIndex the new value of the field startIndex.
   */
  public void setStartIndex(java.lang.Integer _startIndex)
  {
    startIndex = _startIndex;
  }

  private transient java.lang.Integer count;

  /**
   * Gets the field count.
   * @return the value of the field count; may be null.
   */
  public java.lang.Integer getCount()
  {
    return count;
  }

  /**
   * Sets the field count.
   * @param _count the new value of the field count.
   */
  public void setCount(java.lang.Integer _count)
  {
    count = _count;
  }

  private transient java.lang.Integer caseCount;

  /**
   * Gets the field caseCount.
   * @return the value of the field caseCount; may be null.
   */
  public java.lang.Integer getCaseCount()
  {
    return caseCount;
  }

  /**
   * Sets the field caseCount.
   * @param _caseCount the new value of the field caseCount.
   */
  public void setCaseCount(java.lang.Integer _caseCount)
  {
    caseCount = _caseCount;
  }

}
