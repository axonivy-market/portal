package ch.ivy.addon.portalkit.service;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portalkit.persistence.dao.ServerDao;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.security.PortalConnectorUser;
import ch.ivyteam.ivy.application.IApplication;

@RunWith(PowerMockRunner.class)
public class ServerServiceTest {

  private ServerService serverService;

  @Mock
  private PortalConnectorDetector portalConnectorDetector;
  @Mock
  private ServerDao serverDao;
  @Mock
  private IApplication application;
  @Mock
  private PortalConnectorUser portalConnectorUser;

  @Test
  @PrepareForTest(PortalConnectorUser.class)
  public void shouldCreateCurrentServerIfNoActiveServersFound() throws Exception {
    when(serverDao.findActiveServers()).thenReturn(Collections.emptyList());
    String expectedPortalConnectorURL = "http://localhost:8081/ivy/portalConnector/PortalConnector";
    when(portalConnectorDetector.getPortalConectorLocalhostURLFromSystemProperty()).thenReturn(
        expectedPortalConnectorURL);
    serverService = new ServerService(serverDao, portalConnectorDetector);
    PowerMockito.mockStatic(PortalConnectorUser.class);
    when(PortalConnectorUser.getPortalConnectorUser()).thenReturn(portalConnectorUser);
    when(portalConnectorUser.getUserName()).thenReturn("PortalConnector");
    when(portalConnectorUser.getPassword()).thenReturn("sample password");
    List<Server> servers = serverService.findActiveServers();
    assertEquals(1, servers.size());

    Server server = servers.get(0);
    assertEquals(expectedPortalConnectorURL, server.getPath());
    assertEquals("localhost", server.getName());
  }
}
