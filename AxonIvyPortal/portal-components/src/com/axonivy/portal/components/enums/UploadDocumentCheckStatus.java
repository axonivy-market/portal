package com.axonivy.portal.components.enums;

public enum UploadDocumentCheckStatus {
  OK, FAIL, SKIP;
  
  public static UploadDocumentCheckStatus toEnum(String value) {
    for(UploadDocumentCheckStatus status : values()) {
      if(status.toString().equalsIgnoreCase(value)) {
        return status;
      }
    }
    return FAIL;
  }
}
