package com.axonivy.portal.component.enums;

public enum GlobalVariable {

  SHOW_AVATAR("Portal.ShowAvatar");

  private String key;

  GlobalVariable(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
