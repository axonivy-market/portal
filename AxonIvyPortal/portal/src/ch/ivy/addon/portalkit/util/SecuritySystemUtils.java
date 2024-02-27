package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.security.restricted.ISecurityContextInternal;

public class SecuritySystemUtils {
  public static boolean isIvySecuritySystem() {
    return Sudo.get(() -> {
      return ((ISecurityContextInternal) ISecurityContext.current()).identityProviders().stream().anyMatch(idp -> ISecurityConstants.IVY_ENGINE_SECURITY_SYSTEM_PROVIDER_NAME.equals(idp.id()));
    });
  }
}
