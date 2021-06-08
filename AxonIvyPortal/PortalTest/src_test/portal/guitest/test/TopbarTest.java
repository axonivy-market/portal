package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static portal.guitest.common.Variable.LOGGED_IN_USER_FORMAT;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;

public class TopbarTest extends BaseTest {

  private static final String LOGGED_IN_USER_FORMAT_SETTING = LOGGED_IN_USER_FORMAT.getKey();

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
}
