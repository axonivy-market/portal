package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class TopbarTest extends BaseTest {

  private static final String LOGGED_IN_USER_FORMAT_SETTING = Variable.LOGGED_IN_USER_FORMAT.getKey();
  private static final String DEFAULT_THEME_MODE_SETTING = Variable.DEFAULT_THEME_MODE.getKey();
  private static final String ENABLE_SWITCH_THEME_BUTTON_SETTING = Variable.ENABLE_SWITCH_THEME_BUTTON.getKey();

  @Test
  public void testLoggedInUserWithUsernameFormat() {
    updatePortalSetting(LOGGED_IN_USER_FORMAT_SETTING, "USERNAME");
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();

    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.checkNameOfLoggedInUserFormat("demo");
  }

  @Test
  public void testLoggedInUserWithDisplayNameFormat() {
    updatePortalSetting(LOGGED_IN_USER_FORMAT_SETTING, "DISPLAY_NAME");
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();

    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.checkNameOfLoggedInUserFormat("Portal Demo User");
  }

  @Test
  public void testLoggedInUserWithDisplayNameUsernameFormat() {
    updatePortalSetting(LOGGED_IN_USER_FORMAT_SETTING, "DISPLAY_NAME_USERNAME");
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();

    NewDashboardPage newDashboardPage = new NewDashboardPage();

    newDashboardPage.checkNameOfLoggedInUserFormat("Portal Demo User (demo)");
  }

  @Test
  public void testLoggedInUserWithUsernameDisplayNameFormat() {
    updatePortalSetting(LOGGED_IN_USER_FORMAT_SETTING, "USERNAME_DISPLAY_NAME");
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();

    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.checkNameOfLoggedInUserFormat("demo (Portal Demo User)");
  }

  @Test
  public void testThemeMode() {
    updatePortalSetting(DEFAULT_THEME_MODE_SETTING, "Dark");
    updatePortalSetting(ENABLE_SWITCH_THEME_BUTTON_SETTING, "False");
    login(TestAccount.DEMO_USER);
    redirectToNewDashBoard();

    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.isSwitchThemeToLightModeLinkIconDisplayed();
    newDashboardPage.isSwitchThemeLinkIconDisabled();
  }
}
