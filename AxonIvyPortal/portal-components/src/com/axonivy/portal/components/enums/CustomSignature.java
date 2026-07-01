package com.axonivy.portal.components.enums;

public enum CustomSignature {
  GET_DOCUMENT_ITEMS("portalGetDocumentItems(ch.ivyteam.ivy.workflow.ICase)"),
  UPLOAD_DOCUMENT_ITEM("portalUploadDocumentItem(ch.ivyteam.ivy.workflow.ICase,org.primefaces.model.file.UploadedFile,Boolean,Boolean,String)"),
  DOWNLOAD_DOCUMENT_ITEM("portalDownloadDocumentItem(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)"),
  DELETE_DOCUMENT_ITEM("portalDeleteDocumentItem(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)"),
  RENAME_DOCUMENT_ITEM("portalRenameDocumentItem(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)"),
  GET_TASK_NOTES("portalGetTaskNotes(Long)"),
  GET_AUDIT_TRAIL_DATA("portalGetAuditTrailData(ch.ivyteam.ivy.workflow.ICase)"),
  SAVE_ADDITIONAL_AUDIT_TRAIL_DATA("portalSaveAdditionalAuditTrailData(ch.ivyteam.ivy.workflow.ICase,java.util.List<com.axonivy.portal.components.dto.AuditTrailDTO>)");

  private String signature;

  private CustomSignature(String signature) {
    this.signature = signature;
  }

  public String getSignature() {
    return signature;
  }

}
