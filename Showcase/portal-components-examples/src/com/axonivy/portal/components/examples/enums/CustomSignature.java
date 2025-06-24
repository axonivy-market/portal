package com.axonivy.portal.components.examples.enums;

public enum CustomSignature {
  GET_SECURITY_MEMBERS_SIDE_STEPS("getSecurityMembers()"),
  GET_SECURITY_MEMBERS_SIDE_STEPS2("getSecurityMembers2()");

  private String signature;

  private CustomSignature(String signature) {
    this.signature = signature;
  }

  public String getSignature() {
    return signature;
  }
}
