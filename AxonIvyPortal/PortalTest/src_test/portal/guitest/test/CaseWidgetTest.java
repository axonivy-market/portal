package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;
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

  private static final String CUSTOMIZATION_SHOW_ADDITIONAL_CASE_DETAILS_SUBPROCESS = "/ivy/pro/designer/InternalSupport/16102669E18BD8F5/showAdditionalCaseDetails.ivp";
  private static final String DEFAULT_SHOW_ADDITIONAL_CASE_DETAILS_SUBPROCESS = "/ivy/pro/designer/PortalTemplate/160FD01492D362BE/showAdditionalCaseDetails.ivp";
  private static final String LEAVE_REQUEST_CUSTOMIZATION_CASE_DETAILS_PAGE_CASE_NAME = "Leave Request Customization Case Details Page";
  private static final String LEAVE_REQUEST_DEFAULT_CASE_DETAILS_PAGE_CASE_NAME = "Leave Request for Default Additional Case Details";
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";

  @Before
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testHideCase() {
    navigateToUrl(hideCaseUrl);
    HomePage homePage = selectHomePage(TestAccount.ADMIN_USER);
    
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
    CasePage casePage = selectCasePage(TestAccount.ADMIN_USER);
    int numberOfCasesBeforeDestroying = casePage.getNumberOfCases();
    WebElement caseSecondItemBeforDestroy = casePage.selectCaseItem(0);
    casePage.clickDestroyButton(caseSecondItemBeforDestroy);
    casePage.confimDestruction();
    casePage.waitAjaxIndicatorDisappear();
    assertEquals(numberOfCasesBeforeDestroying - 1, casePage.getNumberOfCases());

  }

  @Test
  public void testDestroyCaseWithoutPermission() {
    CasePage casePage = selectCasePage(TestAccount.DEMO_USER);
    WebElement caseSecondItem = casePage.selectCaseItem(0);

    assertFalse(casePage.isDestroyButtonVisible(caseSecondItem));
  }

  @Test
  public void testOpenRelatedTasksOfCase() {
    CaseDetailsPage caseDetailsPage = selectCaseDetailsPage(LEAVE_REQUEST_CASE_NAME, TestAccount.DEMO_USER);
    int numberOfTasks = caseDetailsPage.countRelatedTasks();
    TaskWidgetPage taskOfCasePage = caseDetailsPage.openTasksOfCasePage(0);
    assertEquals(numberOfTasks, taskOfCasePage.countTasks());
  }
  
  @Test
  public void testOpenAdditionalCaseDetailsPage() throws Exception {
    navigateToUrl(createTestingCaseUrlForDefaultAdditionalCaseDetails);
    assertTrue(getAdditionalCaseDetailsUrl(LEAVE_REQUEST_DEFAULT_CASE_DETAILS_PAGE_CASE_NAME, TestAccount.DEMO_USER).contains(DEFAULT_SHOW_ADDITIONAL_CASE_DETAILS_SUBPROCESS));
  }
  
  @Test
  public void testOpenCustomizationAdditionalCaseDetailsPage() throws Exception {
    navigateToUrl(createTestingCaseUrlForCustomizationAdditionalCaseDetails);
    assertTrue(getAdditionalCaseDetailsUrl(LEAVE_REQUEST_CUSTOMIZATION_CASE_DETAILS_PAGE_CASE_NAME, TestAccount.DEMO_USER).contains(CUSTOMIZATION_SHOW_ADDITIONAL_CASE_DETAILS_SUBPROCESS));
  }
  
  private String getAdditionalCaseDetailsUrl(String caseName, TestAccount account){
    return selectCaseDetailsPage(caseName, account).getAdditionalCaseDetailsUrl();
  }

  private CaseDetailsPage selectCaseDetailsPage(String caseName, TestAccount account) {
    return selectCasePage(account).openDetailsOfCaseHasName(caseName);
  }

  private CasePage selectCasePage(TestAccount account) {
    return selectHomePage(account).openMainMenu().selectCaseMenu();
  }

  private HomePage selectHomePage(TestAccount account) {
    LoginPage loginPage = new LoginPage(account);
    loginPage.login();
    return new HomePage();
  }
}
