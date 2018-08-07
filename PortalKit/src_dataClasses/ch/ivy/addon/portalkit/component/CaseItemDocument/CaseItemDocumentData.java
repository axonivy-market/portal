package ch.ivy.addon.portalkit.component.CaseItemDocument;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseItemDocumentData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseItemDocumentData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -4321175070744172549L;

  private ch.ivy.addon.portalkit.bo.RemoteCase remoteCase;

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

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyDocument> documents;

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

  private ch.ivy.ws.addon.IvyDocument document;

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

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> _errors)
  {
    errors = _errors;
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

}
