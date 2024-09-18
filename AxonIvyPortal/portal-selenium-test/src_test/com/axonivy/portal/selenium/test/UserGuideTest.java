package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.UserGuidePage;
import com.axonivy.portal.selenium.page.UserProfilePage;


@IvyWebTest
public class UserGuideTest extends BaseTest {
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updatePortalSetting(Variable.SHOW_USER_GUIDE.getKey(), "true");
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
    updatePortalSetting(Variable.SHOW_USER_GUIDE.getKey(), "false");
    HomePage homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    assertTrue(userProfilePage.isDisableShowTutorialCheckbox());
    updatePortalSetting(Variable.SHOW_USER_GUIDE.getKey(), "true");
    homePage = new HomePage();
    userProfilePage = homePage.openMyProfilePage();
    assertFalse(userProfilePage.isDisableShowTutorialCheckbox());
    userProfilePage.checkShowTutorial();
    homePage = userProfilePage.saveAndGoToHomePage();
    UserGuidePage userGuidePage = new UserGuidePage();
    userGuidePage.nextToMainMenuGuide();
    userGuidePage.nextToProcessGuide();
    userGuidePage.nextToTaskGuide();
    userGuidePage.nextToUsernameGuide();
    userGuidePage.nextToStatisticGuide();
    assertTrue(userGuidePage.isFinishButtonDisplay());
    userGuidePage.finishInStatisticGuide();
  }
  
  
}
