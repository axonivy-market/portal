package portal.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.CaseDetailsPage;
import portal.page.CasePage;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.MainMenuPage;
import portal.page.TaskWidgetPage;

public class CaseWidgetTest extends BaseTest {

  @Before
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testDestroyCaseWithPermission() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage casePage = mainMenuPage.selectCaseMenu();
    int numberOfCasesBeforeDestroying = casePage.getNumberOfCases();
    WebElement caseSecondItemBeforDestroy = casePage.selectCaseItem(0);
    casePage.clickDestroyButton(caseSecondItemBeforDestroy);
    casePage.confimDestruction();
    casePage.waitAjaxIndicatorDisappear();
    assertEquals(numberOfCasesBeforeDestroying - 1, casePage.getNumberOfCases());

  }

  @Test
  public void testDestroyCaseWithoutPermission() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage casePage = mainMenuPage.selectCaseMenu();
    WebElement caseSecondItem = casePage.selectCaseItem(0);

    assertFalse(casePage.isDestroyButtonVisible(caseSecondItem));
  }

  @Test
  public void testOpenRelatedTasksOfCase() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage casePage = mainMenuPage.selectCaseMenu();
    CaseDetailsPage caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    int numberOfTasks = caseDetailsPage.countTasks();
    TaskWidgetPage taskOfCasePage = caseDetailsPage.openTasksOfCasePage(0);
    assertEquals(numberOfTasks, taskOfCasePage.countTasks());
  }
}
