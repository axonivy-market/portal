package com.axonivy.portal.components.enums;

public enum CustomProcessStatus {
  OK, SKIP;

  public static CustomProcessStatus toEnum(String value) {
    for(CustomProcessStatus v : values())
        if(v.toString().equalsIgnoreCase(value)) return v;
    return OK;
  }
}
