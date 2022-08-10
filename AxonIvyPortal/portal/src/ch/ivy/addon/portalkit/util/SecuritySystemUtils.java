package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;

public class SecuritySystemUtils {
  public static boolean isIvySecuritySystem() {
    return IvyExecutor.executeAsSystem(() -> {
      return ISecurityContext.current().getExternalSecuritySystemName().equals(ISecurityConstants.IVY_ENGINE_SECURITY_SYSTEM_PROVIDER_NAME);
    });
  }
}
