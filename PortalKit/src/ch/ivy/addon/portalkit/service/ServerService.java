package ch.ivy.addon.portalkit.service;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.persistence.dao.ServerDao;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.service.ServiceException;


public class ServerService extends AbstractService<Server> {

  private static final long LOCAL_SERVER_ID = -1L;

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
    try {
      List<Server> servers = getDao().findActiveServers();
      if (servers.isEmpty()) {
        Server localhost = localhost();
        servers = Arrays.asList(localhost);
      }
      return servers;
    } catch (UnknownHostException e) {
      String message = "Can not get address of server";
      Ivy.log().error(message, e);
      throw new ServiceException(message, e);
    }
  }

  @Override
  public Server findById(long id) {
    try {
      return id == LOCAL_SERVER_ID ? localhost() : super.findById(id);
    } catch (UnknownHostException e) {
      String message = "Can not get address of server";
      Ivy.log().error(message, e);
      throw new ServiceException(message, e);
    }
  }

  private Server localhost() throws UnknownHostException {
    Server localhost = new Server();
    PortalConnectorDetector connectorDetector = getPortalConnectorDetector();
    localhost.setPath(connectorDetector.getPortalConectorLocalhostURL());
    localhost.setId(LOCAL_SERVER_ID);
    localhost.setIsNTLMAuthentication(false);
    localhost.setIsOnline(true);
    localhost.setName("localhost");
    List<Application> applications = getDao().getAllApplicationsUserCanWorkOn(localhost);
    localhost.setApplications(applications);
    return localhost;
  }
}
