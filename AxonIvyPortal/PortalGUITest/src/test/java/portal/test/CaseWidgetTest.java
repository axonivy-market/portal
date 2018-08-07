package portal.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.CasePage;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.MainMenuPage;

public class CaseWidgetTest extends BaseTest {
  
  @Before
  public void setup(){
    super.setup();
    String prepareTaskForTestUrl = "internalSupport/14B2FC03D2E87141/CreateTestTasks.ivp";
    navigateToUrl(prepareTaskForTestUrl);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
  }
  
  @Test
  public void testDestroyCaseWithPermission(){
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage casePage = mainMenuPage.selectCaseMenu();
    WebElement caseSecondItemBeforDestroy = casePage.selectCaseItem(0);
    casePage.clickDestroyButton(caseSecondItemBeforDestroy);
    casePage.confimDestruction();
    casePage.waitAjaxIndicatorDisappear();
    
    
    assertTrue(casePage.isCaseItemEmpty());
  }
  
  @Test
  public void testDestroyCaseWithoutPermission(){
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage casePage = mainMenuPage.selectCaseMenu();
    WebElement caseSecondItem = casePage.selectCaseItem(0);
    
    assertFalse(casePage.isDestroyButtonVisible(caseSecondItem));
  }
}
