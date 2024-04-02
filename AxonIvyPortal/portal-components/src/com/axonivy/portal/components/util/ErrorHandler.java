package com.axonivy.portal.components.util;


import org.apache.log4j.Level;

import com.axonivy.portal.components.ivydata.exception.PortalIvyDataException;

import ch.ivyteam.ivy.environment.Ivy;

public class ErrorHandler {

  private ErrorHandler() {}
  
  public static void addError(Level level, PortalIvyDataException error) {
    Ivy.log().log(level, error.toString());
  }

}
