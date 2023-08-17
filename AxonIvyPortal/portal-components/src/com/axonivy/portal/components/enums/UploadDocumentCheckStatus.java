package com.axonivy.portal.components.enums;

public enum UploadDocumentCheckStatus {
  OK, FAIL;
  
  public static UploadDocumentCheckStatus toEnum(String value) {
    for(UploadDocumentCheckStatus v : values())
        if(v.toString().equalsIgnoreCase(value)) return v;
    return FAIL;
  }
}
