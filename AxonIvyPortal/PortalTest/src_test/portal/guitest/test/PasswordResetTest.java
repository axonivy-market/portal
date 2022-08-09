package portal.guitest.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.SystemProperties;
import portal.guitest.common.TestAccount;
import portal.guitest.page.ForgotPasswordPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.PasswordResetErrorPage;
import portal.guitest.page.PasswordResetPage;
import vn.wawa.guitest.base.client.Browser;

public class PasswordResetTest extends BaseTest {
  private PasswordResetPage passwordResetPage;
  private PasswordResetErrorPage passwordResetErrorPage;

  @Before
  @Override
  public void setup() {
    setBrowser(Browser.getBrowser());
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    if (!SystemProperties.isInServerMode()) {
      logoutDesigner();
    }
    redirectToRelativeLink(portalKitTestHelperPasswordResetUrl);
  }

  @Test
  public void testResetPasswordSuccess() {
    redirectToRelativeLink(String.format(portalPasswordResetUrl, TestAccount.TEST_FORGOT_PASSWORD_USER.getPassword(), TestAccount.TEST_FORGOT_PASSWORD_USER.getUsername()));
    passwordResetPage = new PasswordResetPage();
    passwordResetPage.resetPassword();
    assertTrue(passwordResetPage.isReset());
    passwordResetPage.goHome();
    LoginPage loginPage = new LoginPage();
    assertTrue(loginPage.isDisplayed());
  }

  @Test
  public void testResetPasswordFailed() {
    redirectToRelativeLink(String.format(portalPasswordResetUrl, "1234", TestAccount.TEST_FORGOT_PASSWORD_USER.getUsername()));
    passwordResetErrorPage = new PasswordResetErrorPage();
    assertTrue(passwordResetErrorPage.isDisplayed());
    passwordResetErrorPage.goForgotPassword();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    assertTrue(forgotPasswordPage.isDisplayed());
  }

}
