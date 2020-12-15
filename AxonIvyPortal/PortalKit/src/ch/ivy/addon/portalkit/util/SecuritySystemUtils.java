package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;

public class SecuritySystemUtils {
  public static boolean isIvySecuritySystem() {
    return IvyExecutor.executeAsSystem(() -> {
      return Ivy.session().getSecurityContext().getExternalSecuritySystemName()
        .equals(ISecurityConstants.IVY_ENGINE_SECURITY_SYSTEM_PROVIDER_NAME);
    });
  }
}
