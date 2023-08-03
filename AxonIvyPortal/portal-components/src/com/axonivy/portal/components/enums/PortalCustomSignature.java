package com.axonivy.portal.components.enums;

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
  CONFIGURE_ROLES_FOR_GROUP_CHAT("portalConfigureRolesForGroupChat(ch.ivyteam.ivy.workflow.ITask)"),
  SET_GROUP_CHAT_NAME("portalSetGroupChatName()"),
  GET_GROUP_CHAT_PARAMS("portalGetGroupChatParams()"),
  SET_GROUP_CHAT_PARAMS("portalSetGroupChatParams()");

  private String signature;

  private PortalCustomSignature(String signature) {
    this.signature = signature;
  }

  public String getSignature() {
    return signature;
  }

  public String getDefaultSignature() {
    String defaultSignature = signature.replaceFirst("portal", "");
    return defaultSignature.substring(0, 1).toLowerCase() + defaultSignature.substring(1);
  }

}
