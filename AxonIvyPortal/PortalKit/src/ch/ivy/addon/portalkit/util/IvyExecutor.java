package ch.ivy.addon.portalkit.util;

import java.util.concurrent.Callable;

import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.server.ServerFactory;

public class IvyExecutor {

  private IvyExecutor() {}

  public static <T> T executeAsSystem(Callable<T> callable) {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(callable);
    } catch (Exception ex) {
      throw new PortalException(ex);

    }
  }

}
