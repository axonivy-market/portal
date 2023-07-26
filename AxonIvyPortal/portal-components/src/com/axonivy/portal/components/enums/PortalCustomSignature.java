package com.axonivy.portal.components.enums;

public enum PortalCustomSignature {
  LOGOUT_PAGE("portalGetLogoutPage()"),
  RESET_PASSWORD("portalResetPassword(String,String,String,String)"),
  SEND_PASSWORD_RESET_EMAIL("portalSendPasswordResetEmail(String)");

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
