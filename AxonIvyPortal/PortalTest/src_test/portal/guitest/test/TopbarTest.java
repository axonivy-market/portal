package portal.guitest.test;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;

public class TopbarTest extends BaseTest {

  private static final String LOGGED_IN_USER_FORMAT = "LOGGED_IN_USER_FORMAT";
  
  @Test
  public void testLoggedInUserWithUsernameFormat() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    updatePortalSetting(LOGGED_IN_USER_FORMAT, "USERNAME");
    login(TestAccount.DEMO_USER);
    HomePage homePage = new HomePage();
    assertEquals("demo", homePage.getLoggedInUserFormat());
  }
  
  @Test
  public void testLoggedInUserWithDisplayNameFormat() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    updatePortalSetting(LOGGED_IN_USER_FORMAT, "DISPLAY_NAME");
    login(TestAccount.DEMO_USER);
    HomePage homePage = new HomePage();
    assertEquals("Portal Demo User", homePage.getLoggedInUserFormat());
  }
  
  @Test
  public void testLoggedInUserWithDisplayNameUsernameFormat() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    updatePortalSetting(LOGGED_IN_USER_FORMAT, "DISPLAY_NAME_USERNAME");
    login(TestAccount.DEMO_USER);
    HomePage homePage = new HomePage();
    assertEquals("Portal Demo User (demo)", homePage.getLoggedInUserFormat());
  }

  @Test
  public void testLoggedInUserWithUsernameDisplayNameFormat() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    updatePortalSetting(LOGGED_IN_USER_FORMAT, "USERNAME_DISPLAY_NAME");
    login(TestAccount.DEMO_USER);
    HomePage homePage = new HomePage();
    assertEquals("demo (Portal Demo User)", homePage.getLoggedInUserFormat());
  }
}
