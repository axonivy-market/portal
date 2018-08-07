package ch.ivyteam.wf.processes;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 6906234941250005101L;

  /**
   * used to store additional properties that use in get/setAdditionalProperties web service
   */
  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyAdditionalProperty> additionalProperties;

  /**
   * Gets the field additionalProperties.
   * @return the value of the field additionalProperties; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyAdditionalProperty> getAdditionalProperties()
  {
    return additionalProperties;
  }

  /**
   * Sets the field additionalProperties.
   * @param _additionalProperties the new value of the field additionalProperties.
   */
  public void setAdditionalProperties(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyAdditionalProperty> _additionalProperties)
  {
    additionalProperties = _additionalProperties;
  }

  private transient java.util.List<ch.ivy.ws.addon.IvyApplication> applications;

  /**
   * Gets the field applications.
   * @return the value of the field applications; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.IvyApplication> getApplications()
  {
    return applications;
  }

  /**
   * Sets the field applications.
   * @param _applications the new value of the field applications.
   */
  public void setApplications(java.util.List<ch.ivy.ws.addon.IvyApplication> _applications)
  {
    applications = _applications;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> apps;

  /**
   * Gets the field apps.
   * @return the value of the field apps; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getApps()
  {
    return apps;
  }

  /**
   * Sets the field apps.
   * @param _apps the new value of the field apps.
   */
  public void setApps(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _apps)
  {
    apps = _apps;
  }

  private transient ch.ivy.addon.portalkit.bo.RemoteCase remoteCase;

  /**
   * Gets the field remoteCase.
   * @return the value of the field remoteCase; may be null.
   */
  public ch.ivy.addon.portalkit.bo.RemoteCase getRemoteCase()
  {
    return remoteCase;
  }

  /**
   * Sets the field remoteCase.
   * @param _remoteCase the new value of the field remoteCase.
   */
  public void setRemoteCase(ch.ivy.addon.portalkit.bo.RemoteCase _remoteCase)
  {
    remoteCase = _remoteCase;
  }

  private transient java.util.List<ch.ivy.addon.portalkit.bo.RemoteCase> cases;

  /**
   * Gets the field cases.
   * @return the value of the field cases; may be null.
   */
  public java.util.List<ch.ivy.addon.portalkit.bo.RemoteCase> getCases()
  {
    return cases;
  }

  /**
   * Sets the field cases.
   * @param _cases the new value of the field cases.
   */
  public void setCases(java.util.List<ch.ivy.addon.portalkit.bo.RemoteCase> _cases)
  {
    cases = _cases;
  }

  private transient ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria;

  /**
   * Gets the field caseSearchCriteria.
   * @return the value of the field caseSearchCriteria; may be null.
   */
  public ch.ivy.ws.addon.CaseSearchCriteria getCaseSearchCriteria()
  {
    return caseSearchCriteria;
  }

  /**
   * Sets the field caseSearchCriteria.
   * @param _caseSearchCriteria the new value of the field caseSearchCriteria.
   */
  public void setCaseSearchCriteria(ch.ivy.ws.addon.CaseSearchCriteria _caseSearchCriteria)
  {
    caseSearchCriteria = _caseSearchCriteria;
  }

  private transient ch.ivy.ws.addon.IvyDocument document;

  /**
   * Gets the field document.
   * @return the value of the field document; may be null.
   */
  public ch.ivy.ws.addon.IvyDocument getDocument()
  {
    return document;
  }

  /**
   * Sets the field document.
   * @param _document the new value of the field document.
   */
  public void setDocument(ch.ivy.ws.addon.IvyDocument _document)
  {
    document = _document;
  }

  private transient javax.activation.DataHandler documentContent;

  /**
   * Gets the field documentContent.
   * @return the value of the field documentContent; may be null.
   */
  public javax.activation.DataHandler getDocumentContent()
  {
    return documentContent;
  }

  /**
   * Sets the field documentContent.
   * @param _documentContent the new value of the field documentContent.
   */
  public void setDocumentContent(javax.activation.DataHandler _documentContent)
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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyDocument> documents;

  /**
   * Gets the field documents.
   * @return the value of the field documents; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyDocument> getDocuments()
  {
    return documents;
  }

  /**
   * Sets the field documents.
   * @param _documents the new value of the field documents.
   */
  public void setDocuments(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyDocument> _documents)
  {
    documents = _documents;
  }

  private transient java.lang.String endpoint;

  /**
   * Gets the field endpoint.
   * @return the value of the field endpoint; may be null.
   */
  public java.lang.String getEndpoint()
  {
    return endpoint;
  }

  /**
   * Sets the field endpoint.
   * @param _endpoint the new value of the field endpoint.
   */
  public void setEndpoint(java.lang.String _endpoint)
  {
    endpoint = _endpoint;
  }

  private transient java.util.List<ch.ivy.ws.addon.WsException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.WsException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(java.util.List<ch.ivy.ws.addon.WsException> _errors)
  {
    errors = _errors;
  }

  private transient java.util.List<ch.ivy.ws.addon.IvyCase> ivyCases;

  /**
   * Gets the field ivyCases.
   * @return the value of the field ivyCases; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.IvyCase> getIvyCases()
  {
    return ivyCases;
  }

  /**
   * Sets the field ivyCases.
   * @param _ivyCases the new value of the field ivyCases.
   */
  public void setIvyCases(java.util.List<ch.ivy.ws.addon.IvyCase> _ivyCases)
  {
    ivyCases = _ivyCases;
  }

  private transient java.util.ListIterator listIterator;

  /**
   * Gets the field listIterator.
   * @return the value of the field listIterator; may be null.
   */
  public java.util.ListIterator getListIterator()
  {
    return listIterator;
  }

  /**
   * Sets the field listIterator.
   * @param _listIterator the new value of the field listIterator.
   */
  public void setListIterator(java.util.ListIterator _listIterator)
  {
    listIterator = _listIterator;
  }

  private transient java.lang.String nbv;

  /**
   * Gets the field nbv.
   * @return the value of the field nbv; may be null.
   */
  public java.lang.String getNbv()
  {
    return nbv;
  }

  /**
   * Sets the field nbv.
   * @param _nbv the new value of the field nbv.
   */
  public void setNbv(java.lang.String _nbv)
  {
    nbv = _nbv;
  }

  private transient ch.ivy.addon.portalkit.persistence.domain.Server server;

  /**
   * Gets the field server.
   * @return the value of the field server; may be null.
   */
  public ch.ivy.addon.portalkit.persistence.domain.Server getServer()
  {
    return server;
  }

  /**
   * Sets the field server.
   * @param _server the new value of the field server.
   */
  public void setServer(ch.ivy.addon.portalkit.persistence.domain.Server _server)
  {
    server = _server;
  }

  private transient java.util.List<ch.ivy.ws.addon.WsException> tempErrors;

  /**
   * Gets the field tempErrors.
   * @return the value of the field tempErrors; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.WsException> getTempErrors()
  {
    return tempErrors;
  }

  /**
   * Sets the field tempErrors.
   * @param _tempErrors the new value of the field tempErrors.
   */
  public void setTempErrors(java.util.List<ch.ivy.ws.addon.WsException> _tempErrors)
  {
    tempErrors = _tempErrors;
  }

  private transient java.lang.String username;

  /**
   * Gets the field username.
   * @return the value of the field username; may be null.
   */
  public java.lang.String getUsername()
  {
    return username;
  }

  /**
   * Sets the field username.
   * @param _username the new value of the field username.
   */
  public void setUsername(java.lang.String _username)
  {
    username = _username;
  }

  private transient java.lang.Long caseId;

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

  private transient ch.ivy.ws.addon.IvyCase ivyCase;

  /**
   * Gets the field ivyCase.
   * @return the value of the field ivyCase; may be null.
   */
  public ch.ivy.ws.addon.IvyCase getIvyCase()
  {
    return ivyCase;
  }

  /**
   * Sets the field ivyCase.
   * @param _ivyCase the new value of the field ivyCase.
   */
  public void setIvyCase(ch.ivy.ws.addon.IvyCase _ivyCase)
  {
    ivyCase = _ivyCase;
  }

  /**
   * to create note
   */
  private transient java.lang.String noteContent;

  /**
   * Gets the field noteContent.
   * @return the value of the field noteContent; may be null.
   */
  public java.lang.String getNoteContent()
  {
    return noteContent;
  }

  /**
   * Sets the field noteContent.
   * @param _noteContent the new value of the field noteContent.
   */
  public void setNoteContent(java.lang.String _noteContent)
  {
    noteContent = _noteContent;
  }

  /**
   * return value after creating note
   */
  private transient ch.ivy.ws.addon.IvyNote note;

  /**
   * Gets the field note.
   * @return the value of the field note; may be null.
   */
  public ch.ivy.ws.addon.IvyNote getNote()
  {
    return note;
  }

  /**
   * Sets the field note.
   * @param _note the new value of the field note.
   */
  public void setNote(ch.ivy.ws.addon.IvyNote _note)
  {
    note = _note;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyNote> notes;

  /**
   * Gets the field notes.
   * @return the value of the field notes; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyNote> getNotes()
  {
    return notes;
  }

  /**
   * Sets the field notes.
   * @param _notes the new value of the field notes.
   */
  public void setNotes(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyNote> _notes)
  {
    notes = _notes;
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

  private transient java.lang.Integer caseCountOfOneServer;

  /**
   * Gets the field caseCountOfOneServer.
   * @return the value of the field caseCountOfOneServer; may be null.
   */
  public java.lang.Integer getCaseCountOfOneServer()
  {
    return caseCountOfOneServer;
  }

  /**
   * Sets the field caseCountOfOneServer.
   * @param _caseCountOfOneServer the new value of the field caseCountOfOneServer.
   */
  public void setCaseCountOfOneServer(java.lang.Integer _caseCountOfOneServer)
  {
    caseCountOfOneServer = _caseCountOfOneServer;
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

}
