package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static portal.guitest.common.Variable.SHOW_USER_GUIDE;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.UserGuidePage;
import portal.guitest.page.UserProfilePage;

public class UserGuideTest extends BaseTest {
  
  @Override
  @Before
  public void setup() {
    super.setup();
    updatePortalSetting(SHOW_USER_GUIDE.getKey(), "true");
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
  
  @Test
  public void testChangeShowUserGuideInGeneralSetting() {
    updatePortalSetting(SHOW_USER_GUIDE.getKey(), "false");
    HomePage homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    assertTrue(userProfilePage.isDisableShowTutorialCheckbox());
    updatePortalSetting(SHOW_USER_GUIDE.getKey(), "true");
    homePage = new HomePage();
    userProfilePage = homePage.openMyProfilePage();
    assertFalse(userProfilePage.isDisableShowTutorialCheckbox());
    userProfilePage.checkShowTutorial();
    homePage = userProfilePage.save();
    UserGuidePage userGuidePage = new UserGuidePage();
    assertTrue(userGuidePage.isFinishButtonDisplay());
    userGuidePage.finishInStatisticGuide();
  }
  
  @After
  public void destroy() {
    updatePortalSetting(SHOW_USER_GUIDE.getKey(), "false");
  }
}
