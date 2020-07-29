package portal.guitest.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.UserGuidePage;

public class UserGuideTest extends BaseTest {
  
  @Override
  @Before
  public void setup() {
    super.setup();
    updatePortalSetting("SHOW_USER_GUIDE", "true");
    login(TestAccount.DEMO_USER);
  }

  @Test
  public void testFinishGuide() {
    UserGuidePage userGuidePage = new UserGuidePage();
    userGuidePage.nextToMainMenuGuide();
    userGuidePage.nextToProcessGuide();
    userGuidePage.nextToTaskGuide();
    userGuidePage.nextToUsernameGuide();
    userGuidePage.nextToStatisticGuide();
    userGuidePage.finishInStatisticGuide();
  }
  
  @After
  public void destroy() {
    updatePortalSetting("SHOW_USER_GUIDE", "false");
  }
}
