package ch.ivy.ws.addon.util;

import java.util.concurrent.Callable;

import ch.ivy.ws.addon.PortalException;
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
