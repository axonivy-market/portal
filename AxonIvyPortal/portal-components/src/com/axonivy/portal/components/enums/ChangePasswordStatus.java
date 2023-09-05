package com.axonivy.portal.components.enums;

public enum ChangePasswordStatus {
  OK, FAIL;
  
  public static ChangePasswordStatus toEnum(String value) {
    for(ChangePasswordStatus v : values()) {
    	if(v.toString().equalsIgnoreCase(value)) {
    		return v;
    	}
    }
    return FAIL;
  }
}
