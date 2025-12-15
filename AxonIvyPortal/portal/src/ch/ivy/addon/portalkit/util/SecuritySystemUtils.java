package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.security.ISecurityContext;

public class SecuritySystemUtils {
  public static boolean isIvySecuritySystem() {
    return !ISecurityContext.current().managed();
  }
}
