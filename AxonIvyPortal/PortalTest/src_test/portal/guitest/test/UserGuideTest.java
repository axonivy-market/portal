package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
  
  @Test
  public void testChangeShowUserGuideInGeneralSetting() {
    updatePortalSetting("SHOW_USER_GUIDE", "false");
    HomePage homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    assertTrue(userProfilePage.isDisableShowTutorialCheckbox());
    updatePortalSetting("SHOW_USER_GUIDE", "true");
    homePage = new HomePage();
    userProfilePage = homePage.openMyProfilePage();
    assertFalse(userProfilePage.isDisableShowTutorialCheckbox());
    userProfilePage.checkShowTutorial();
    userProfilePage.save();
    userProfilePage.clickOnLogo();
    homePage = new HomePage();
    UserGuidePage userGuidePage = new UserGuidePage();
    assertTrue(userGuidePage.isFinishButtonDisplay());
    userGuidePage.finishInStatisticGuide();
  }
  
  @After
  public void destroy() {
    updatePortalSetting("SHOW_USER_GUIDE", "false");
  }
}
