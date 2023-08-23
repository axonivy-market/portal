package com.axonivy.portal.components.enums;

public enum CustomProcessStatus {
  OK, SKIP;

  public static CustomProcessStatus toEnum(String value) {
    for(CustomProcessStatus status : values()) {
      if (status.toString().equalsIgnoreCase(value)) {
        return status;
      }
    }
    return OK;
  }
}
