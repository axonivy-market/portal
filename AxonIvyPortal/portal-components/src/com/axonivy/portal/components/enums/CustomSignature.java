package com.axonivy.portal.components.enums;

public enum CustomSignature {
  GET_DOCUMENT_ITEMS("portalGetDocumentItems(ch.ivyteam.ivy.workflow.ICase)"),
  UPLOAD_DOCUMENT_ITEM("portalUploadDocumentItem(ch.ivyteam.ivy.workflow.ICase,org.primefaces.model.file.UploadedFile,Boolean,Boolean,String)"),
  DOWNLOAD_DOCUMENT_ITEM("portalDownloadDocumentItem(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)"),
  GROWL_MESSAGE("portalGrowlMessage(ch.ivyteam.ivy.workflow.ITask, ch.ivyteam.ivy.workflow.ICase)"),
  DELETE_DOCUMENT_ITEM("portalDeleteDocumentItem(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)");

  private String signature;

  private CustomSignature(String signature) {
    this.signature = signature;
  }

  public String getSignature() {
    return signature;
  }

}
