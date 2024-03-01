package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.security.internal.ivy.IvySecuritySystem;
import ch.ivyteam.ivy.security.restricted.ISecurityContextInternal;

public class SecuritySystemUtils {
  public static boolean isIvySecuritySystem() {
    return Sudo.get(() -> {
      var provider = ((ISecurityContextInternal) ISecurityContext.current()).identityProviders().get(0);
      return provider instanceof IvySecuritySystem;
    });
  }
}
