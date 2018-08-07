package ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseItemDocumentData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseItemDocumentData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 4162463953520686620L;

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

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.vo.DocumentVO> documents;

  /**
   * Gets the field documents.
   * @return the value of the field documents; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.vo.DocumentVO> getDocuments()
  {
    return documents;
  }

  /**
   * Sets the field documents.
   * @param _documents the new value of the field documents.
   */
  public void setDocuments(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.vo.DocumentVO> _documents)
  {
    documents = _documents;
  }

  private org.primefaces.event.FileUploadEvent documentUploadEvent;

  /**
   * Gets the field documentUploadEvent.
   * @return the value of the field documentUploadEvent; may be null.
   */
  public org.primefaces.event.FileUploadEvent getDocumentUploadEvent()
  {
    return documentUploadEvent;
  }

  /**
   * Sets the field documentUploadEvent.
   * @param _documentUploadEvent the new value of the field documentUploadEvent.
   */
  public void setDocumentUploadEvent(org.primefaces.event.FileUploadEvent _documentUploadEvent)
  {
    documentUploadEvent = _documentUploadEvent;
  }

  private javax.activation.DataHandler documentContent;

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

  private ch.ivy.addon.portalkit.vo.DocumentVO document;

  /**
   * Gets the field document.
   * @return the value of the field document; may be null.
   */
  public ch.ivy.addon.portalkit.vo.DocumentVO getDocument()
  {
    return document;
  }

  /**
   * Sets the field document.
   * @param _document the new value of the field document.
   */
  public void setDocument(ch.ivy.addon.portalkit.vo.DocumentVO _document)
  {
    document = _document;
  }

  private java.lang.String selectedFileName;

  /**
   * Gets the field selectedFileName.
   * @return the value of the field selectedFileName; may be null.
   */
  public java.lang.String getSelectedFileName()
  {
    return selectedFileName;
  }

  /**
   * Sets the field selectedFileName.
   * @param _selectedFileName the new value of the field selectedFileName.
   */
  public void setSelectedFileName(java.lang.String _selectedFileName)
  {
    selectedFileName = _selectedFileName;
  }

  private org.primefaces.model.StreamedContent documentFile;

  /**
   * Gets the field documentFile.
   * @return the value of the field documentFile; may be null.
   */
  public org.primefaces.model.StreamedContent getDocumentFile()
  {
    return documentFile;
  }

  /**
   * Sets the field documentFile.
   * @param _documentFile the new value of the field documentFile.
   */
  public void setDocumentFile(org.primefaces.model.StreamedContent _documentFile)
  {
    documentFile = _documentFile;
  }

  private java.lang.Boolean fileAlreadyExists;

  /**
   * Gets the field fileAlreadyExists.
   * @return the value of the field fileAlreadyExists; may be null.
   */
  public java.lang.Boolean getFileAlreadyExists()
  {
    return fileAlreadyExists;
  }

  /**
   * Sets the field fileAlreadyExists.
   * @param _fileAlreadyExists the new value of the field fileAlreadyExists.
   */
  public void setFileAlreadyExists(java.lang.Boolean _fileAlreadyExists)
  {
    fileAlreadyExists = _fileAlreadyExists;
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

}
