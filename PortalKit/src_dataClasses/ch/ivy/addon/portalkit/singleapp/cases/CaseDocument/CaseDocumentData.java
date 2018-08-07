package ch.ivy.addon.portalkit.singleapp.cases.CaseDocument;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseDocumentData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseDocumentData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -4447168548720383411L;

  private org.primefaces.model.UploadedFile selectedFile;

  /**
   * Gets the field selectedFile.
   * @return the value of the field selectedFile; may be null.
   */
  public org.primefaces.model.UploadedFile getSelectedFile()
  {
    return selectedFile;
  }

  /**
   * Sets the field selectedFile.
   * @param _selectedFile the new value of the field selectedFile.
   */
  public void setSelectedFile(org.primefaces.model.UploadedFile _selectedFile)
  {
    selectedFile = _selectedFile;
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

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.vo.DocumentVO> caseDocuments;

  /**
   * Gets the field caseDocuments.
   * @return the value of the field caseDocuments; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.vo.DocumentVO> getCaseDocuments()
  {
    return caseDocuments;
  }

  /**
   * Sets the field caseDocuments.
   * @param _caseDocuments the new value of the field caseDocuments.
   */
  public void setCaseDocuments(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.vo.DocumentVO> _caseDocuments)
  {
    caseDocuments = _caseDocuments;
  }

  private org.primefaces.model.StreamedContent downloadFile;

  /**
   * Gets the field downloadFile.
   * @return the value of the field downloadFile; may be null.
   */
  public org.primefaces.model.StreamedContent getDownloadFile()
  {
    return downloadFile;
  }

  /**
   * Sets the field downloadFile.
   * @param _downloadFile the new value of the field downloadFile.
   */
  public void setDownloadFile(org.primefaces.model.StreamedContent _downloadFile)
  {
    downloadFile = _downloadFile;
  }

  private java.lang.String selectedFilePath;

  /**
   * Gets the field selectedFilePath.
   * @return the value of the field selectedFilePath; may be null.
   */
  public java.lang.String getSelectedFilePath()
  {
    return selectedFilePath;
  }

  /**
   * Sets the field selectedFilePath.
   * @param _selectedFilePath the new value of the field selectedFilePath.
   */
  public void setSelectedFilePath(java.lang.String _selectedFilePath)
  {
    selectedFilePath = _selectedFilePath;
  }

  private java.lang.Boolean uploadingFileExists;

  /**
   * Gets the field uploadingFileExists.
   * @return the value of the field uploadingFileExists; may be null.
   */
  public java.lang.Boolean getUploadingFileExists()
  {
    return uploadingFileExists;
  }

  /**
   * Sets the field uploadingFileExists.
   * @param _uploadingFileExists the new value of the field uploadingFileExists.
   */
  public void setUploadingFileExists(java.lang.Boolean _uploadingFileExists)
  {
    uploadingFileExists = _uploadingFileExists;
  }

}
