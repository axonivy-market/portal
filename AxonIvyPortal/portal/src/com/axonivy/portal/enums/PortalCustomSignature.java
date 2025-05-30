package com.axonivy.portal.enums;

public enum PortalCustomSignature {
  LOGOUT("portalLogout(Boolean,ch.ivyteam.ivy.workflow.ITask)"),
  LOGOUT_PAGE("portalGetLogoutPage()"),
  RESET_PASSWORD("portalResetPassword(String,String,String,String)"),
  SEND_PASSWORD_RESET_EMAIL("portalSendPasswordResetEmail(String)"),
  CHANGE_PASSWORD("portalChangePassword(String, String)"),
  GET_DOCUMENT_LIST("portalGetDocumentList(ch.ivyteam.ivy.workflow.ICase)"),
  UPLOAD_DOCUMENT("portalUploadDocument(ch.ivyteam.ivy.workflow.ICase,org.primefaces.model.file.UploadedFile,Boolean,Boolean,String)"),
  DOWNLOAD_DOCUMENT("portalDownloadDocument(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)"),
  DELETE_DOCUMENT("portalDeleteDocument(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)"),
  DELEGATE("portalCalculateTaskDelegate(java.util.List<com.axonivy.portal.components.dto.RoleDTO>,java.util.List<com.axonivy.portal.components.dto.UserDTO>,com.axonivy.portal.components.dto.SecurityMemberDTO,ch.ivyteam.ivy.workflow.ITask)"),
  GET_PAGE_HEADER_AND_FOOTER("portalGetPageHeaderAndFooter()"),
  GROWL_MESSAGE("portalGrowlMessage()"),
  LOAD_SUB_MENU_ITEMS("portalLoadSubMenuItems()");

  private String signature;

  private PortalCustomSignature(String signature) {
    this.signature = signature;
  }

  public String getSignature() {
    return signature;
  }
}