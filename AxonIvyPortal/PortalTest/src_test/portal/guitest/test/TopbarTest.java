package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;

public class TopbarTest extends BaseTest {

  private static final String LOGGED_IN_USER_FORMAT = "LOGGED_IN_USER_FORMAT";
  
  @Test
  public void testLoggedInUserWithUsernameFormat() {
    updatePortalSetting(LOGGED_IN_USER_FORMAT, "USERNAME");
    HomePage homePage = new HomePage();
    assertEquals("demo", homePage.getLoggedInUserFormat());
  }
  
  @Test
  public void testLoggedInUserWithDisplayNameFormat() {
    updatePortalSetting(LOGGED_IN_USER_FORMAT, "DISPLAY_NAME");
    HomePage homePage = new HomePage();
    assertEquals("Portal Demo User", homePage.getLoggedInUserFormat());
  }
  
  @Test
  public void testLoggedInUserWithDisplayNameUsernameFormat() {
    updatePortalSetting(LOGGED_IN_USER_FORMAT, "DISPLAY_NAME_USERNAME");
    HomePage homePage = new HomePage();
    assertEquals("Portal Demo User (demo)", homePage.getLoggedInUserFormat());
  }

  @Test
  public void testLoggedInUserWithUsernameDisplayNameFormat() {
    updatePortalSetting(LOGGED_IN_USER_FORMAT, "USERNAME_DISPLAY_NAME");
    HomePage homePage = new HomePage();
    assertEquals("demo (Portal Demo User)", homePage.getLoggedInUserFormat());
  }
}
