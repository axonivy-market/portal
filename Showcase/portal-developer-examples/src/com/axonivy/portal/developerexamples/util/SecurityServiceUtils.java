package com.axonivy.portal.developerexamples.util;


import ch.ivyteam.ivy.environment.Ivy;

public class SecurityServiceUtils {

  private SecurityServiceUtils() {}

  public static void removeSessionAttribute(String name) {
    Ivy.session().removeAttribute(name);
  }

}
