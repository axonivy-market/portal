package ch.ivy.addon.portalkit.persistence.dao;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portalkit.persistence.domain.Server;

@RunWith(PowerMockRunner.class)
public class ServerDaoTest {

  private ServerDao serverDao;

  @Before
  public void setup() {
    serverDao = PowerMockito.mock(ServerDao.class);
  }

  @Test
  public void testFindActiveServers() {
    Server activeServer = new Server();
    activeServer.setIsOnline(true);
    Server inactiveServer = new Server();
    inactiveServer.setIsOnline(false);

    List<Server> servers = Arrays.asList(activeServer, inactiveServer);

    PowerMockito.when(serverDao.findAll()).thenReturn(servers);
    PowerMockito.when(serverDao.findActiveServers()).thenCallRealMethod();

    List<Server> actualActiveServers = serverDao.findActiveServers();

    Assert.assertEquals(Arrays.asList(activeServer), actualActiveServers);
  }
}
