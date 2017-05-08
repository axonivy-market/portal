package component.CaseDocument;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseDocumentData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseDocumentData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -3730952596057941013L;

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

  private java.util.List<ch.ivy.addon.portalkit.vo.DocumentFile> caseDocuments;

  /**
   * Gets the field caseDocuments.
   * @return the value of the field caseDocuments; may be null.
   */
  public java.util.List<ch.ivy.addon.portalkit.vo.DocumentFile> getCaseDocuments()
  {
    return caseDocuments;
  }

  /**
   * Sets the field caseDocuments.
   * @param _caseDocuments the new value of the field caseDocuments.
   */
  public void setCaseDocuments(java.util.List<ch.ivy.addon.portalkit.vo.DocumentFile> _caseDocuments)
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

  private java.lang.Integer numberOfFilesLimit;

  /**
   * Gets the field numberOfFilesLimit.
   * @return the value of the field numberOfFilesLimit; may be null.
   */
  public java.lang.Integer getNumberOfFilesLimit()
  {
    return numberOfFilesLimit;
  }

  /**
   * Sets the field numberOfFilesLimit.
   * @param _numberOfFilesLimit the new value of the field numberOfFilesLimit.
   */
  public void setNumberOfFilesLimit(java.lang.Integer _numberOfFilesLimit)
  {
    numberOfFilesLimit = _numberOfFilesLimit;
  }

  private java.lang.String allowedFileTypes;

  /**
   * Gets the field allowedFileTypes.
   * @return the value of the field allowedFileTypes; may be null.
   */
  public java.lang.String getAllowedFileTypes()
  {
    return allowedFileTypes;
  }

  /**
   * Sets the field allowedFileTypes.
   * @param _allowedFileTypes the new value of the field allowedFileTypes.
   */
  public void setAllowedFileTypes(java.lang.String _allowedFileTypes)
  {
    allowedFileTypes = _allowedFileTypes;
  }

  private java.lang.Integer sizeLimitBytes;

  /**
   * Gets the field sizeLimitBytes.
   * @return the value of the field sizeLimitBytes; may be null.
   */
  public java.lang.Integer getSizeLimitBytes()
  {
    return sizeLimitBytes;
  }

  /**
   * Sets the field sizeLimitBytes.
   * @param _sizeLimitBytes the new value of the field sizeLimitBytes.
   */
  public void setSizeLimitBytes(java.lang.Integer _sizeLimitBytes)
  {
    sizeLimitBytes = _sizeLimitBytes;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> inputFileTypes;

  /**
   * Gets the field inputFileTypes.
   * @return the value of the field inputFileTypes; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getInputFileTypes()
  {
    return inputFileTypes;
  }

  /**
   * Sets the field inputFileTypes.
   * @param _inputFileTypes the new value of the field inputFileTypes.
   */
  public void setInputFileTypes(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _inputFileTypes)
  {
    inputFileTypes = _inputFileTypes;
  }

  private java.lang.Boolean toManyFiles;

  /**
   * Gets the field toManyFiles.
   * @return the value of the field toManyFiles; may be null.
   */
  public java.lang.Boolean getToManyFiles()
  {
    return toManyFiles;
  }

  /**
   * Sets the field toManyFiles.
   * @param _toManyFiles the new value of the field toManyFiles.
   */
  public void setToManyFiles(java.lang.Boolean _toManyFiles)
  {
    toManyFiles = _toManyFiles;
  }

}
