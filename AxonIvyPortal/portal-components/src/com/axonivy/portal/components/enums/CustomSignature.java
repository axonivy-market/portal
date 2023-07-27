package com.axonivy.portal.components.enums;

public enum CustomSignature {
  GET_DOCUMENT_ITEMS("customGetDocumentItems(ch.ivyteam.ivy.workflow.ICase)"),
  UPLOAD_DOCUMENT_ITEM("customUploadDocumentItem(ch.ivyteam.ivy.workflow.ICase,org.primefaces.model.file.UploadedFile,Boolean,Boolean,String)"),
  DOWNLOAD_DOCUMENT_ITEM("customDownloadDocumentItem(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)"),
  DELETE_DOCUMENT_ITEM("customDeleteDocumentItem(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)");

  private String signature;

  private CustomSignature(String signature) {
    this.signature = signature;
  }

  public String getSignature() {
    return signature;
  }

  public String getDefaultSignature() {
    String defaultSignature = signature.replaceFirst("custom", "");
    return defaultSignature.substring(0, 1).toLowerCase() + defaultSignature.substring(1);
  }

}
