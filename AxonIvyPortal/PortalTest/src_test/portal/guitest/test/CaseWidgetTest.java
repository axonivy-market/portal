package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CasePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;

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
    int numberOfTasks = caseDetailsPage.countRelatedTasks();
    TaskWidgetPage taskOfCasePage = caseDetailsPage.openTasksOfCasePage(0);
    assertEquals(numberOfTasks, taskOfCasePage.countTasks());
  }

  @Test
  public void testHideCase() {
    navigateToUrl(hideCaseUrl);
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage casePage = mainMenuPage.selectCaseMenu();

    CaseDetailsPage caseDetailsPage = casePage.openDetailsOfCaseHasName("Repair Computer");
    int numberOfTasks = caseDetailsPage.countRelatedTasks();
    int numberOfTechnicalCases = caseDetailsPage.countTechnicalCases();
    assertEquals(3, numberOfTasks);
    assertEquals(2, numberOfTechnicalCases);

    TaskWidgetPage taskWidgetPage = caseDetailsPage.openTasksOfCasePage(2);
    taskWidgetPage.findElementByClassName("show-task-details-mode").findElement(By.cssSelector("*[id$=':task-item:task-start-command']")).click();
    taskWidgetPage.waitForElementPresent(By.id("user-menu-required-login:main-navigator"), true);


    mainMenuPage = taskWidgetPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertFalse(casePage.isCaseDisplayed("Repair Computer"));
  }
}
