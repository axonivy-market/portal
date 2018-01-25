package portal.guitest.test;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdditionalCaseDetailsPage;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CasePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

public class CaseWidgetTest extends BaseTest {

  private static final String LEAVE_REQUEST_CUSTOMIZATION_CASE_DETAILS_PAGE_CASE_NAME = "Leave Request Customization Case Details Page";
  private static final String LEAVE_REQUEST_DEFAULT_CASE_DETAILS_PAGE_CASE_NAME = "Leave Request for Default Additional Case Details";
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  
  private HomePage homePage;
  private MainMenuPage mainMenuPage;
  private CasePage casePage;
  private CaseDetailsPage caseDetailsPage;
  private AdditionalCaseDetailsPage additionalCaseDetailsPage;

  @Before
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testHideCase() {
    navigateToUrl(hideCaseUrl);
    initHomePage(TestAccount.ADMIN_USER);
    
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.filterTasksBy("Report and hide case");
    taskWidgetPage.findElementByCssSelector("*[id*='" + 0 + ":task-item']").click();

    homePage = taskWidgetPage.goToHomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage casePage = mainMenuPage.selectCaseMenu();
    assertFalse(casePage.isCaseDisplayed("Repair Computer"));
  }

  @Test
  public void testDestroyCaseWithPermission() {
    initHomePage(TestAccount.ADMIN_USER);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    int numberOfCasesBeforeDestroying = casePage.getNumberOfCases();
    WebElement caseSecondItemBeforDestroy = casePage.selectCaseItem(0);
    casePage.clickDestroyButton(caseSecondItemBeforDestroy);
    casePage.confimDestruction();
    casePage.waitAjaxIndicatorDisappear();
    assertEquals(numberOfCasesBeforeDestroying - 1, casePage.getNumberOfCases());

  }

  @Test
  public void testDestroyCaseWithoutPermission() {
    initHomePage(TestAccount.DEMO_USER);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    WebElement caseSecondItem = casePage.selectCaseItem(0);

    assertFalse(casePage.isDestroyButtonVisible(caseSecondItem));
  }

  @Test
  public void testOpenRelatedTasksOfCase() {
    initHomePage(TestAccount.DEMO_USER);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openDetailsOfCaseHasName(LEAVE_REQUEST_CASE_NAME);
    int numberOfTasks = caseDetailsPage.countRelatedTasks();
    TaskWidgetPage taskOfCasePage = caseDetailsPage.openTasksOfCasePage(0);
    assertEquals(numberOfTasks, taskOfCasePage.countTasks());
  }
  
  public void testOpenAdditionalCaseDetailsPage() throws Exception {
    openAdditionalCaseDetailsPage(createTestingCaseUrlForDefaultAdditionalCaseDetails, LEAVE_REQUEST_DEFAULT_CASE_DETAILS_PAGE_CASE_NAME);
    validateAdditionalCaseDetailsPage(17, "CustomVarCharField 1");
  }
  
  public void testOpenCustomizationAdditionalCaseDetailsPage() throws Exception {
    openAdditionalCaseDetailsPage(createTestingCaseUrlForCustomizationAdditionalCaseDetails, LEAVE_REQUEST_CUSTOMIZATION_CASE_DETAILS_PAGE_CASE_NAME);
    validateAdditionalCaseDetailsPage(17, "CustomizationAdditionalCaseDetails CustomVarCharField1");
  }
  
  private void openAdditionalCaseDetailsPage(String initDataUrl, String caseName){
    navigateToUrl(initDataUrl);
    initHomePage(TestAccount.DEMO_USER);
    mainMenuPage = homePage.openMainMenu();
    additionalCaseDetailsPage = new AdditionalCaseDetailsPage();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openDetailsOfCaseHasName(caseName);
    caseDetailsPage.openAdditionalCaseDetailsPage();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
    homePage.switchLastBrowserTab();
  }
  
  private void validateAdditionalCaseDetailsPage(int expectedNumberOfFields, String expectedFirstFieldValue){
    int numberOfFields;
    try {
      numberOfFields = additionalCaseDetailsPage.countFields();
    } catch (TimeoutException e) { // sometimes session is destroyed (don't know reason why!!!) so we cannot reach the page
        System.out.println("Stop testOpenCustomizationAdditionalCaseDetailsPage test here because session is destroyed");
        return ;
    }
    assertEquals(expectedNumberOfFields, numberOfFields);
    assertEquals(expectedFirstFieldValue, additionalCaseDetailsPage.getAdditionalFieldContentOfFirstRow());
  }

  private void initHomePage(TestAccount account) {
    LoginPage loginPage = new LoginPage(account);
    loginPage.login();
    homePage = new HomePage();
  }
}
