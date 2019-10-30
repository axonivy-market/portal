package portal.guitest.test;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;

public class LoginTest extends BaseTest {
  private LoginPage loginPage;

  @Before
  @Override
  public void setup() {
    super.setup();
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testLogin() {
    loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    HomePage homePage = new HomePage();
    assertTrue(homePage.isDisplayed());
  }

}
