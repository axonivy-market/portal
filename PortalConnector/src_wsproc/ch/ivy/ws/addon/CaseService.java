package ch.ivy.ws.addon;

/**
 * 
 */
@SuppressWarnings("all")
@javax.jws.WebService
public class CaseService extends ch.ivyteam.ivy.webservice.process.restricted.AbstractWebServiceProcess
{

  /**
   * Constructor
   * @param webServiceProcess the web service process this class represents
   * @param processEngineManager the process engine manager to start the ws process
   */
  public CaseService(ch.ivyteam.ivy.webservice.process.restricted.IWebServiceProcessBeanRuntime webServiceProcessBeanRuntime)
  {
    super(webServiceProcessBeanRuntime);
  }

  /**
   * @param id
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public FindNotesResult findNotes(@javax.jws.WebParam(name="id") java.lang.Long id) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    FindNotesResult result = new FindNotesResult();

    params.put("id", id);

    processResult = executeProcess("findNotes(Long)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setNotes((java.util.List<ch.ivy.ws.addon.types.IvyNote>)getTupleField(processResult, "notes"));
    return result;
  }

  /**
   * @param user
   * @param message
   * @param id
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public CreateNoteResult createNote(@javax.jws.WebParam(name="user") java.lang.String user, @javax.jws.WebParam(name="message") java.lang.String message, @javax.jws.WebParam(name="id") java.lang.Long id) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    CreateNoteResult result = new CreateNoteResult();

    params.put("user", user);
    params.put("message", message);
    params.put("id", id);

    processResult = executeProcess("createNote(String,String,Long)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setNote((ch.ivy.ws.addon.types.IvyNote)getTupleField(processResult, "note"));
    return result;
  }

  /**
   * @param id
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public FindCaseResult findCase(@javax.jws.WebParam(name="id") java.lang.Long id) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    FindCaseResult result = new FindCaseResult();

    params.put("id", id);

    processResult = executeProcess("findCase(Long)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setIvyCase((ch.ivy.ws.addon.types.IvyCase)getTupleField(processResult, "ivyCase"));
    return result;
  }

  /**
   * @param id
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public GetAddtionalPropertiesResult getAddtionalProperties(@javax.jws.WebParam(name="id") java.lang.Long id) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    GetAddtionalPropertiesResult result = new GetAddtionalPropertiesResult();

    params.put("id", id);

    processResult = executeProcess("getAddtionalProperties(Long)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setAddtionalProperties((java.util.List<ch.ivy.ws.addon.types.IvyAdditionalProperty>)getTupleField(processResult, "addtionalProperties"));
    return result;
  }

  /**
   * @param caseId
   * @param additionalProperties
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="errors")
  public java.util.List<ch.ivy.ws.addon.WSException> setAdditionalProperties(@javax.jws.WebParam(name="caseId") java.lang.Long caseId, @javax.jws.WebParam(name="additionalProperties") java.util.List<ch.ivy.ws.addon.types.IvyAdditionalProperty> additionalProperties) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;

    params.put("caseId", caseId);
    params.put("additionalProperties", additionalProperties);

    processResult = executeProcess("setAdditionalProperties(Long,List<ch.ivy.ws.addon.types.IvyAdditionalProperty>)", params);

    return (java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors");
  }

  /**
   * @param id
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public FindDocumentsResult findDocuments(@javax.jws.WebParam(name="id") java.lang.Long id) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    FindDocumentsResult result = new FindDocumentsResult();

    params.put("id", id);

    processResult = executeProcess("findDocuments(Long)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setDocuments((java.util.List<ch.ivy.ws.addon.types.IvyDocument>)getTupleField(processResult, "documents"));
    return result;
  }

  /**
   * @param caseID
   * @param documentName
   * @param documentContent
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public UploadDocumentResult uploadDocument(@javax.jws.WebParam(name="caseID") java.lang.Long caseID, @javax.jws.WebParam(name="documentName") java.lang.String documentName, @javax.jws.WebParam(name="documentContent") ch.ivyteam.ivy.scripting.objects.Binary documentContent) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    UploadDocumentResult result = new UploadDocumentResult();

    params.put("caseID", caseID);
    params.put("documentName", documentName);
    params.put("documentContent", documentContent);

    processResult = executeProcess("uploadDocument(Long,String,Binary)", params);

    result.setDocument((ch.ivy.ws.addon.types.IvyDocument)getTupleField(processResult, "document"));
    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    return result;
  }

  /**
   * @param caseID
   * @param documentId
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public DownloadDocumentResult downloadDocument(@javax.jws.WebParam(name="caseID") java.lang.Long caseID, @javax.jws.WebParam(name="documentId") java.lang.Long documentId) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    DownloadDocumentResult result = new DownloadDocumentResult();

    params.put("caseID", caseID);
    params.put("documentId", documentId);

    processResult = executeProcess("downloadDocument(Long,Long)", params);

    result.setDocumentContent((ch.ivyteam.ivy.scripting.objects.Binary)getTupleField(processResult, "documentContent"));
    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    return result;
  }

  /**
   * @param caseId
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="errors")
  public java.util.List<ch.ivy.ws.addon.WSException> destroyCase(@javax.jws.WebParam(name="caseId") java.lang.Integer caseId) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;

    params.put("caseId", caseId);

    processResult = executeProcess("destroyCase(Integer)", params);

    return (java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors");
  }

  /**
   * @param startIndex
   * @param count
   * @param caseSearchCriteria
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public FindCasesByCriteriaResult findCasesByCriteria(@javax.jws.WebParam(name="startIndex") java.lang.Integer startIndex, @javax.jws.WebParam(name="count") java.lang.Integer count, @javax.jws.WebParam(name="caseSearchCriteria") ch.ivy.ws.addon.service.CaseSearchCriteria caseSearchCriteria) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    FindCasesByCriteriaResult result = new FindCasesByCriteriaResult();

    params.put("startIndex", startIndex);
    params.put("count", count);
    params.put("caseSearchCriteria", caseSearchCriteria);

    processResult = executeProcess("findCasesByCriteria(Integer,Integer,ch.ivy.ws.addon.service.CaseSearchCriteria)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setIvyCases((java.util.List<ch.ivy.ws.addon.types.IvyCase>)getTupleField(processResult, "ivyCases"));
    return result;
  }

  /**
   * @param caseID
   * @param documentId
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="errors")
  public java.util.List<ch.ivy.ws.addon.WSException> removeDocument(@javax.jws.WebParam(name="caseID") java.lang.Long caseID, @javax.jws.WebParam(name="documentId") java.lang.Long documentId) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;

    params.put("caseID", caseID);
    params.put("documentId", documentId);

    processResult = executeProcess("removeDocument(Long,Long)", params);

    return (java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors");
  }

  /**
   * @param caseSearchCriteria
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public CountCasesByCriteriaResult countCasesByCriteria(@javax.jws.WebParam(name="caseSearchCriteria") ch.ivy.ws.addon.service.CaseSearchCriteria caseSearchCriteria) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    CountCasesByCriteriaResult result = new CountCasesByCriteriaResult();

    params.put("caseSearchCriteria", caseSearchCriteria);

    processResult = executeProcess("countCasesByCriteria(ch.ivy.ws.addon.service.CaseSearchCriteria)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setCaseCount((java.lang.Long)getTupleField(processResult, "caseCount"));
    return result;
  }

  /**
   * @param ivyCase
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="error")
  public java.util.List<ch.ivy.ws.addon.WSException> saveCase(@javax.jws.WebParam(name="ivyCase") ch.ivy.ws.addon.types.IvyCase ivyCase) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;

    params.put("ivyCase", ivyCase);

    processResult = executeProcess("saveCase(ch.ivy.ws.addon.types.IvyCase)", params);

    return (java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "error");
  }

  /**
   * Result type for the findNotes method
   */
  public static class FindNotesResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyNote> fNotes;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyNote> getNotes()
    {
      return fNotes;
    }

    public void setNotes(java.util.List<ch.ivy.ws.addon.types.IvyNote> notes)
    {
      fNotes = notes;
    }

  }
  /**
   * Result type for the createNote method
   */
  public static class CreateNoteResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private ch.ivy.ws.addon.types.IvyNote fNote;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public ch.ivy.ws.addon.types.IvyNote getNote()
    {
      return fNote;
    }

    public void setNote(ch.ivy.ws.addon.types.IvyNote note)
    {
      fNote = note;
    }

  }
  /**
   * Result type for the findCase method
   */
  public static class FindCaseResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private ch.ivy.ws.addon.types.IvyCase fIvyCase;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public ch.ivy.ws.addon.types.IvyCase getIvyCase()
    {
      return fIvyCase;
    }

    public void setIvyCase(ch.ivy.ws.addon.types.IvyCase ivyCase)
    {
      fIvyCase = ivyCase;
    }

  }
  /**
   * Result type for the getAddtionalProperties method
   */
  public static class GetAddtionalPropertiesResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyAdditionalProperty> fAddtionalProperties;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyAdditionalProperty> getAddtionalProperties()
    {
      return fAddtionalProperties;
    }

    public void setAddtionalProperties(java.util.List<ch.ivy.ws.addon.types.IvyAdditionalProperty> addtionalProperties)
    {
      fAddtionalProperties = addtionalProperties;
    }

  }
  /**
   * Result type for the findDocuments method
   */
  public static class FindDocumentsResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyDocument> fDocuments;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyDocument> getDocuments()
    {
      return fDocuments;
    }

    public void setDocuments(java.util.List<ch.ivy.ws.addon.types.IvyDocument> documents)
    {
      fDocuments = documents;
    }

  }
  /**
   * Result type for the uploadDocument method
   */
  public static class UploadDocumentResult
  {
    private ch.ivy.ws.addon.types.IvyDocument fDocument;

    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    public ch.ivy.ws.addon.types.IvyDocument getDocument()
    {
      return fDocument;
    }

    public void setDocument(ch.ivy.ws.addon.types.IvyDocument document)
    {
      fDocument = document;
    }

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

  }
  /**
   * Result type for the downloadDocument method
   */
  public static class DownloadDocumentResult
  {
    private ch.ivyteam.ivy.scripting.objects.Binary fDocumentContent;

    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    public ch.ivyteam.ivy.scripting.objects.Binary getDocumentContent()
    {
      return fDocumentContent;
    }

    public void setDocumentContent(ch.ivyteam.ivy.scripting.objects.Binary documentContent)
    {
      fDocumentContent = documentContent;
    }

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

  }
  /**
   * Result type for the findCasesByCriteria method
   */
  public static class FindCasesByCriteriaResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyCase> fIvyCases;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyCase> getIvyCases()
    {
      return fIvyCases;
    }

    public void setIvyCases(java.util.List<ch.ivy.ws.addon.types.IvyCase> ivyCases)
    {
      fIvyCases = ivyCases;
    }

  }
  /**
   * Result type for the countCasesByCriteria method
   */
  public static class CountCasesByCriteriaResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.lang.Long fCaseCount;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.lang.Long getCaseCount()
    {
      return fCaseCount;
    }

    public void setCaseCount(java.lang.Long caseCount)
    {
      fCaseCount = caseCount;
    }

  }
}