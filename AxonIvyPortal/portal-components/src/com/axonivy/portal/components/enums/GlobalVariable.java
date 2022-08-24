package com.axonivy.portal.components.enums;

public enum GlobalVariable {

  ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT("Portal.Document.EnableScriptChecking"),
  ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT("Portal.Document.EnableVirusScanner"),
  UPLOAD_DOCUMENT_WHITELIST_EXTENSION("Portal.Document.WhitelistExtension"),
  SHOW_AVATAR("Portal.ShowAvatar");

  private String key;

  GlobalVariable(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
