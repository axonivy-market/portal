package com.axonivy.portal.developerexamples.util;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;

public class PermissionUtils {
  private static final String ADMIN_ROLE = "AXONIVY_PORTAL_ADMIN";

  private PermissionUtils() {}

  public static boolean isSessionUserHasAdminRole() {
    return Ivy.session().hasRole(ISecurityContext.current().roles().find(ADMIN_ROLE), false);
  }
}