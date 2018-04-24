package ch.ivy.addon.portalkit.service;

import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.enums.WSAuthenticationType;
import ch.ivy.addon.portalkit.persistence.dao.ServerDao;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.security.PortalConnectorUser;


public class ServerService extends AbstractService<Server> {

  public static final long LOCAL_SERVER_ID = -1L;

  public ServerService(ServerDao serverDao, PortalConnectorDetector connectorDetector) {
    super(serverDao, connectorDetector);
  }

  public ServerService() {
    super(ServerDao.class);
  }

  @Override
  protected ServerDao getDao() {
    return (ServerDao) super.getDao();
  }

  public List<Server> findActiveServers() {
    List<Server> servers = getDao().findActiveServers();
    if (servers.isEmpty()) {
      Server localServer = localhost();
      return Arrays.asList(localServer);
    }
    return servers;
  }
  
  public List<Server> findActiveServersNotLocalhost() {
    return getDao().findActiveServers();
  }
  
  public boolean isMultiServers() {
    return findActiveServers().size() > 1;
  }

  @Override
  public Server findById(long id) {
    if(id == LOCAL_SERVER_ID) {
      return localhost();
    } else {
      Server server = super.findById(id);
      return server;
    }
  }

  private Server localhost() {
    Server localhost = new Server();
    PortalConnectorDetector connectorDetector = getPortalConnectorDetector();
    localhost.setPath(connectorDetector.getPortalConectorLocalhostURLFromSystemProperty());
    localhost.setId(LOCAL_SERVER_ID);
    localhost.setIsOnline(true);
    localhost.setName("localhost");
    List<Application> applications = getDao().getAllApplicationsUserCanWorkOn(localhost);
    localhost.setApplications(applications);
    PortalConnectorUser user = PortalConnectorUser.getPortalConnectorUser();
    localhost.setWsAuthenticationType(WSAuthenticationType.HTTP_BASIC);
    localhost.setUsername(user.getUserName());
    localhost.setPassword(user.getPassword());
    return localhost;
  }
}
