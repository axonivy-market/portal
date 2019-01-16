package ch.ivy.addon.portalkit.persistence.dao;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.User;

@RunWith(PowerMockRunner.class)
public class UserDaoTest {

  private UserDao userDao;

  @Before
  public void setup() {
    userDao = PowerMockito.mock(UserDao.class);
  }

  @Test
  public void testFindByUserName() {
    String userName = "TestUser";
    User foundUser = new User();
    foundUser.setUserName(userName);
    User notFoundUser = new User();

    List<User> users = Arrays.asList(foundUser, notFoundUser);

    PowerMockito.when(userDao.findAll()).thenReturn(users);
    PowerMockito.when(userDao.findByUserName(userName)).thenCallRealMethod();


    List<User> actualUsers = userDao.findByUserName(userName);

    Assert.assertEquals(Arrays.asList(foundUser), actualUsers);
  }

  @Ignore
  @Test
  public void testFindByApplicationName() {
    Application application = new Application();
    application.setServerId(1L);
    application.setName("TestApplication");

    User userInSameAppAndServer = new User();
    userInSameAppAndServer.setApplicationName(application.getName());

    User userNotInSameServer = new User();
    userNotInSameServer.setApplicationName(application.getName());

    User userNotInSameApp = new User();
    userNotInSameApp.setApplicationName(application.getName() + "new");

    List<User> users = Arrays.asList(userInSameAppAndServer, userNotInSameServer, userNotInSameApp);

    PowerMockito.when(userDao.findAll()).thenReturn(users);
    PowerMockito.when(userDao.findByApplication(application)).thenCallRealMethod();

    List<User> actualUsers = userDao.findByApplication(application);

    Assert.assertEquals(Arrays.asList(userInSameAppAndServer), actualUsers);
  }

}
