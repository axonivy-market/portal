package com.axonivy.portal.components.enums;

public enum GlobalVariable {

  ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT("Portal.Document.EnableScriptChecking"),
  ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT("Portal.Document.EnableVirusScanner"),
  UPLOAD_DOCUMENT_WHITELIST_EXTENSION("Portal.Document.WhitelistExtension"),
  SHOW_AVATAR("Portal.ShowAvatar"),
  SHOW_TOOLTIP_TECHNICAL_NAME("Portal.ShowTooltipTechnicalName"),
  HIDE_TIME("Portal.DateTimeFormat.HideTime"),
  HIDE_YEAR("Portal.DateTimeFormat.HideYear"),
  DATE_FILTER_WITH_TIME("Portal.DateTimeFormat.DateFilterWithTime");

  private String key;

  GlobalVariable(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
