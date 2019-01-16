package ch.ivy.addon.portalkit.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portalkit.persistence.domain.Application;

@RunWith(PowerMockRunner.class)
public class ApplicationDaoTest {

  private ApplicationDao applicationDao;

  @Before
  public void setup() {
    applicationDao = PowerMockito.mock(ApplicationDao.class);
  }

  @Test
  public void testFindAllThirdPartyApplications() {
    List<Application> mockReturnAllApplications = new ArrayList<Application>();
    Application thirdPartyApplication = new Application();
    thirdPartyApplication.setId(1L);
    thirdPartyApplication.setDisplayName("test01");
    thirdPartyApplication.setServerId(null);

    Application application = new Application();
    application.setId(2L);
    application.setDisplayName("test02");
    application.setServerId(1L);

    mockReturnAllApplications.add(thirdPartyApplication);
    mockReturnAllApplications.add(application);

    PowerMockito.when(applicationDao.findAll()).thenReturn(mockReturnAllApplications);
    PowerMockito.when(applicationDao.findAllThirdPartyApplications()).thenCallRealMethod();

    List<Application> thirdPartyApplications = applicationDao.findAllThirdPartyApplications();

    assertEquals(1, thirdPartyApplications.size());

    Application result = thirdPartyApplications.get(0);
    assertEquals(thirdPartyApplication.getId(), result.getId());
    assertEquals(thirdPartyApplication.getServerId(), result.getServerId());

  }

  @Test
  public void testFindOtherApplicationsHaveSameNameAndServer() {
    List<Application> mockReturnAllApplications = new ArrayList<Application>();
    Application testApplication = new Application();
    testApplication.setId(1L);
    testApplication.setName("test01");
    testApplication.setServerId(1L);

    Application expectApplication = new Application();
    expectApplication.setId(2L);
    expectApplication.setName("test01");
    expectApplication.setServerId(1L);

    mockReturnAllApplications.add(testApplication);
    mockReturnAllApplications.add(expectApplication);

    PowerMockito.when(applicationDao.findAll()).thenReturn(mockReturnAllApplications);
    PowerMockito.when(applicationDao.findOtherApplicationsHaveSameNameAndServer(testApplication)).thenCallRealMethod();

    List<Application> sameApplications = applicationDao.findOtherApplicationsHaveSameNameAndServer(testApplication);

    assertEquals(1, sameApplications.size());

    assertEquals(expectApplication, sameApplications.get(0));
  }

}
