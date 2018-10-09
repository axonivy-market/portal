package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.ProcessWidgetPage;

public class FullProcessPageTest extends BaseTest{
  
  protected HomePage homePage;
  protected ProcessWidgetPage processWidgetPage;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    processWidgetPage = mainMenuPage.selectProcessesMenu();
  }
  
  @Test
  public void testFindProcess() {
    processWidgetPage.enterSearchKeyword("Self Service");
    assertTrue(processWidgetPage.isProcessDisplay("Axon.ivy Self Service"));
    assertTrue(processWidgetPage.isProcessGroupDisplay("A"));
    processWidgetPage.enterSearchKeyword("random keyword never match with any process");
    assertFalse(processWidgetPage.isProcessGroupDisplay("A"));
  }
}
