package com.axonivy.portal.components.enums;

public enum CustomSignature {
  GET_DOCUMENT_ITEMS("portalGetDocumentItems(ch.ivyteam.ivy.workflow.ICase)"),
  UPLOAD_DOCUMENT_ITEM("portalUploadDocumentItem(ch.ivyteam.ivy.workflow.ICase,org.primefaces.model.file.UploadedFile,Boolean,Boolean,String)"),
  DOWNLOAD_DOCUMENT_ITEM("portalDownloadDocumentItem(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)"),
  DELETE_DOCUMENT_ITEM("portalDeleteDocumentItem(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)"),
  EXPRESS_GET_DOCUMENT_ITEMS("expressGetDocumentItems(ch.ivyteam.ivy.workflow.ICase)"),
  EXPRESS_UPLOAD_DOCUMENT_ITEM("expressUploadDocumentItem(ch.ivyteam.ivy.workflow.ICase,org.primefaces.model.file.UploadedFile,Boolean,Boolean,String)"),
  EXPRESS_DOWNLOAD_DOCUMENT_ITEM("expressDownloadDocumentItem(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)"),
  EXPRESS_DELETE_DOCUMENT_ITEM("expressDeleteDocumentItem(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)");

  private String signature;

  private CustomSignature(String signature) {
    this.signature = signature;
  }

  public String getSignature() {
    return signature;
  }

}
