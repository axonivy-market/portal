package ch.ivy.addon.portalkit.util;

import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.service.ServerService;

public final class ServerUtil {

  public static boolean isLocalHostServer(Server server) {
    return ServerService.LOCAL_SERVER_ID == server.getId(); 
  }
}
