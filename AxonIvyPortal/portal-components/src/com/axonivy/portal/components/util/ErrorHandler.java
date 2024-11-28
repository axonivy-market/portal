package com.axonivy.portal.components.util;

import com.axonivy.portal.components.ivydata.exception.PortalIvyDataException;

import ch.ivyteam.ivy.environment.Ivy;

public class ErrorHandler {

  private ErrorHandler() {}

  public static void addError(PortalIvyDataException error) {
    Ivy.log().error(error.toString());
  }

}
