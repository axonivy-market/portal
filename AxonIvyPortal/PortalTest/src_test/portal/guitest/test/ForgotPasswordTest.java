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
import vn.wawa.guitest.base.client.Browser;

public class ForgotPasswordTest extends BaseTest {
  private ForgotPasswordPage forgotPasswordPage;

  @Before
  @Override
  public void setup() {
    setBrowser(Browser.getBrowser());
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    if (!SystemProperties.isInServerMode()) {
      logoutDesigner();
    }
    LoginPage loginPage = new LoginPage();
    loginPage.forgotPassword();
  }

  @Test
  public void testForgotPassword() {
    forgotPasswordPage = new ForgotPasswordPage(TestAccount.TEST_FORGOT_PASSWORD_USER);
    forgotPasswordPage.send();
    assertTrue(forgotPasswordPage.isProcessed());
  }

}
