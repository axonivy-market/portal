package ch.ivy.addon.portalkit.persistence.dao;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

@RunWith(PowerMockRunner.class)
public class UserProcessDaoTest {

  private UserProcessDao userProcessDao;

  @Before
  public void setup() {
    userProcessDao = PowerMockito.mock(UserProcessDao.class);
  }

  @Test
  public void testFindByUserName() {
    String userName = "TestUser";
    UserProcess processBelongToUser = new UserProcess();
    processBelongToUser.setUserName(userName);
    UserProcess processNotBelongToUser = new UserProcess();

    List<UserProcess> processes = Arrays.asList(processBelongToUser, processNotBelongToUser);

    PowerMockito.when(userProcessDao.findAll()).thenReturn(processes);
    PowerMockito.when(userProcessDao.findByUserName(userName)).thenCallRealMethod();

    List<UserProcess> actualUserProcesses = userProcessDao.findByUserName(userName);

    Assert.assertEquals(Arrays.asList(processBelongToUser), actualUserProcesses);
  }
}
