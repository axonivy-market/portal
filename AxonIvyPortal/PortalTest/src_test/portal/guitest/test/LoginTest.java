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

public class LoginTest extends BaseTest {
  private LoginPage loginPage;

  @Before
  @Override
  public void setup() {
    setBrowser(Browser.getBrowser());
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    if (!SystemProperties.isInServerMode()) {
      logoutDesigner();
    }
  }

  @Test
  public void testLogin() {
    updatePortalSettingToShowLegacyUI();
    loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    HomePage homePage = new HomePage();
    assertTrue(homePage.isDisplayed());
  }

  @Test
  public void testForgotPassword() {
    loginPage = new LoginPage();
    loginPage.forgotPassword();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    assertTrue(forgotPasswordPage.isDisplayed());
  }
}
