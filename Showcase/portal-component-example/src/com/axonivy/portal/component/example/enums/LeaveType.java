package com.axonivy.portal.component.example.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum LeaveType {
  ANNUAL_LEAVE,
  MATERNITY_LEAVE,
  SICK_LEAVE;
  
  public String getLabel(){
    return Ivy.cms().co("/Enums/" + getClass().getSimpleName() + "/" + name());
  }
}
