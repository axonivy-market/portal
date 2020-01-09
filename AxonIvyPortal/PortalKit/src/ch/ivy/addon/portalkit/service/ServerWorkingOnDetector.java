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
    return myServerId == null ? getDefaultServer() : getServerWorkingOn(myServerId);  
  }

  private Server getDefaultServer() {
    return getActiveServers().get(0);
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
    return new ServerService().findActiveServers();
  }

  private Long getMyServerId() {
    IApplication app = new PortalConnectorDetector().getPortalConnectorApplication();
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        ICustomProperty prop = app.customProperties().property(MY_SERVER_ID_KEY);
        return prop.hasValue() ? prop.getLongValue() : null;
      });
    } catch (Exception e) {
      throw new PortalException(String.format("Error when getting application property %s",MY_SERVER_ID_KEY), e);
    }
  }
}
