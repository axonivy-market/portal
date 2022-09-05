package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static portal.guitest.common.Variable.DEFAULT_THEME_MODE;
import static portal.guitest.common.Variable.LOGGED_IN_USER_FORMAT;
import static portal.guitest.common.Variable.ENABLE_SWITCH_THEME_BUTTON;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.SystemProperties;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import vn.wawa.guitest.base.client.Browser;

public class TopbarTest extends BaseTest {

  private static final String LOGGED_IN_USER_FORMAT_SETTING = LOGGED_IN_USER_FORMAT.getKey();
  private static final String DEFAULT_THEME_MODE_SETTING = DEFAULT_THEME_MODE.getKey();
  private static final String ENABLE_SWITCH_THEME_BUTTON_SETTING = ENABLE_SWITCH_THEME_BUTTON.getKey();

  @Test
  public void testLoggedInUserWithUsernameFormat() {
    updatePortalSetting(LOGGED_IN_USER_FORMAT_SETTING, "USERNAME");
    HomePage homePage = new HomePage();
    assertEquals("demo", homePage.getLoggedInUserFormat());
  }
  
  @Test
  public void testLoggedInUserWithDisplayNameFormat() {
    updatePortalSetting(LOGGED_IN_USER_FORMAT_SETTING, "DISPLAY_NAME");
    HomePage homePage = new HomePage();
    assertEquals("Portal Demo User", homePage.getLoggedInUserFormat());
  }
  
  @Test
  public void testLoggedInUserWithDisplayNameUsernameFormat() {
    updatePortalSetting(LOGGED_IN_USER_FORMAT_SETTING, "DISPLAY_NAME_USERNAME");
    HomePage homePage = new HomePage();
    assertEquals("Portal Demo User (demo)", homePage.getLoggedInUserFormat());
  }

  @Test
  public void testLoggedInUserWithUsernameDisplayNameFormat() {
    updatePortalSetting(LOGGED_IN_USER_FORMAT_SETTING, "USERNAME_DISPLAY_NAME");
    HomePage homePage = new HomePage();
    assertEquals("demo (Portal Demo User)", homePage.getLoggedInUserFormat());
  }

  @Test
  public void testThemeMode() {
    updateGlobalVariable(DEFAULT_THEME_MODE_SETTING, "DARK");
    updateGlobalVariable(ENABLE_SWITCH_THEME_BUTTON_SETTING, "FALSE");
    setBrowser(Browser.getBrowser());
    if (!SystemProperties.isInServerMode()) {
      logoutDesigner();
    }
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    HomePage homePage = new HomePage();
    updateGlobalVariable(ENABLE_SWITCH_THEME_BUTTON_SETTING, "TRUE");
    assertTrue(homePage.isSwitchThemeLightModeLinkIconPresent());
  }
}
