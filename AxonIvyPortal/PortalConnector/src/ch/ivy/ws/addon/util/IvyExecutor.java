package ch.ivy.ws.addon.util;

import java.util.concurrent.Callable;

import ch.ivyteam.ivy.server.ServerFactory;

public class IvyExecutor {

  private IvyExecutor() {}

  public static <T> T executeAsSystem(Callable<T> callable) {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem2(callable);
  }
}
