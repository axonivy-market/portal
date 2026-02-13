package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.security.identity.spi.IdentityProvider;
import ch.ivyteam.ivy.security.internal.ivy.IvySecuritySystem;
import ch.ivyteam.ivy.security.restricted.ISecurityContextInternal;

public class SecuritySystemUtils {
  public static boolean isIvySecuritySystem() {
    return Sudo.get(() -> {
      IdentityProvider provider = ((ISecurityContextInternal) ISecurityContext.current()).identityProvider();
      return provider instanceof IvySecuritySystem;
    });
  }
}
