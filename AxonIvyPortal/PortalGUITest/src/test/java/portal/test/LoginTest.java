package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;

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
