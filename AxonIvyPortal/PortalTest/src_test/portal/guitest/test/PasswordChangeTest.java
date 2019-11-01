package portal.guitest.test;

import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.SystemProperties;
import portal.guitest.common.TestAccount;
import portal.guitest.page.ChangePasswordPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;

public class PasswordChangeTest extends BaseTest {

  @Test
  public void passwordChangeTest() {
    redirectToRelativeLink("portalKitTestHelper/153CACC26D0D4C3D/createTestUser.ivp");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.TEST_CHANGE_PASSWORD_USER);
    HomePage homePage = new HomePage();

    String newPassword = "abc";

    ChangePasswordPage changePasswordPage = homePage.openChangePasswordPage();

    changePasswordPage.changePassword("random password", newPassword);
    assertTrue(changePasswordPage.isWrongCurrentPasswordError());

    changePasswordPage.changePassword(TestAccount.TEST_CHANGE_PASSWORD_USER.getPassword(), newPassword);
    assertTrue(changePasswordPage.isNewCurrentPasswordStrongEnough());
    
    newPassword = "a2C!";
    changePasswordPage.changePassword(TestAccount.TEST_CHANGE_PASSWORD_USER.getPassword(), newPassword);
    if (!SystemProperties.isInServerMode()) {
      launchBrowserAndLogoutInDesigner();
      redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    } else {
      launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    }
    new LoginPage(TestAccount.TEST_CHANGE_PASSWORD_USER).login(TestAccount.TEST_CHANGE_PASSWORD_USER.getUsername(),
        newPassword);

    assertTrue(homePage.isDisplayed());
  }

}
