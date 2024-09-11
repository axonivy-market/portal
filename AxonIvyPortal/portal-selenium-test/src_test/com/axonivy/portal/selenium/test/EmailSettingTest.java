package com.axonivy.portal.selenium.test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.UserProfilePage;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@IvyWebTest
public class EmailSettingTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
  }


  @Test
  public void testChangeEmailSetting() {
    HomePage homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    userProfilePage.switchOnEmailOnTaskAssignmentSetting();
    userProfilePage.switchOnFurtherEmailFromAppSetting();
    userProfilePage.selectDaysForDailySummary(Arrays.asList(1,2,3,4));
    assertEquals(4, userProfilePage.getSelectedDaySummary());
    homePage = userProfilePage.saveAndGoToHomePage();
    userProfilePage = homePage.openMyProfilePage();
    assertTrue(userProfilePage.isEmailOnTaskAssignmentSettingSwitchedOn());
    assertTrue(userProfilePage.isFurtherEmailFromAppSettingSwitchedOn());
    userProfilePage.switchOffFurtherEmailFromAppSetting();
    userProfilePage.switchOffEmailOnTaskAssignmentSetting();
    homePage = userProfilePage.saveAndGoToHomePage();
    userProfilePage = homePage.openMyProfilePage();
    assertFalse(userProfilePage.isEmailOnTaskAssignmentSettingSwitchedOn());
    assertFalse(userProfilePage.isFurtherEmailFromAppSettingSwitchedOn());
  } 
}
