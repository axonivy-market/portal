package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.property.ICustomProperty;
import ch.ivyteam.ivy.server.ServerFactory;

public class ServerWorkingOnDetector {
  public static final String MY_SERVER_ID_KEY = "AxonIvyPortal.MyServerId";

  public Server getServerWorkingOn() {
    Long myServerId = getMyServerId();
    if (myServerId == null) {
      return getDefaultServer();
    } else {
      return getServerWorkingOn(myServerId);
    }
  }

  private Server getDefaultServer() {
    List<Server> servers = getActiveServers();
    return servers.get(0);
  }

  private Server getServerWorkingOn(Long myServerId) {
    List<Server> servers = getActiveServers();
    for (Server server : servers) {
      if (myServerId.equals(server.getId())) {
        return server;
      }
    }
    throw new PortalException(
        "Server is not active or the configuration to detect the working server is not set correctly");
  }

  private List<Server> getActiveServers() {
    ServerService service = new ServerService();
    List<Server> servers = service.findActiveServers();
    return servers;
  }

  private Long getMyServerId() {
    IApplication app = new PortalConnectorDetector().getPortalConnectorApplication();
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        ICustomProperty prop = app.customProperties().property(MY_SERVER_ID_KEY);
        if (prop.hasValue()) {
          return Long.valueOf(prop.getValue());
        } else {
          return null;
        }

      });
    } catch (Exception e) {
      throw new PortalException("Error when getting application property " + MY_SERVER_ID_KEY, e);
    }
  }
}
