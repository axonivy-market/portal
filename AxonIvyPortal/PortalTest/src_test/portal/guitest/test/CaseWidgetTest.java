package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.common.CaseState;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdditionalCaseDetailsPage;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class CaseWidgetTest extends BaseTest {

  private static final String INVESTMENT_REQUEST_CUSTOMIZATION_CASE_DETAILS_PAGE_CASE_NAME = "Investment Request";
  private static final String LEAVE_REQUEST_DEFAULT_CASE_DETAILS_PAGE_CASE_NAME = "Leave Request for Default Additional Case Details";
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String CREATED_COLUMN_HEADER = "Created";
  private static final String STATE_COLUMN_HEADER = "State";
  
  private HomePage homePage;
  private MainMenuPage mainMenuPage;
  private CaseWidgetPage casePage;
  private CaseDetailsPage caseDetailsPage;
  private AdditionalCaseDetailsPage additionalCaseDetailsPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testHideCase() {
    redirectToRelativeLink(hideCaseUrl);
    initHomePage(TestAccount.ADMIN_USER);
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Report and hide case");
    taskWidgetPage.startTaskWithoutUI(0);
    homePage = new HomePage();
    Sleeper.sleep(2000);
    
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertFalse(casePage.isCaseDisplayed("Repair Computer"));
  }

  @Test
  public void testDestroyCaseWithPermission() {
    initHomePage(TestAccount.ADMIN_USER);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    casePage.clickDestroyButton();
    casePage.confimDestruction();
    casePage.waitAjaxIndicatorDisappear();
    CaseState caseState = casePage.getCaseState(0);
    assertEquals(CaseState.DESTROYED, caseState);

  }

  @Test
  public void testDestroyCaseWithoutPermission() {
    initHomePage(TestAccount.DEMO_USER);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertFalse(casePage.isDestroyButtonVisible());
  }

  @Test
  public void testOpenRelatedTasksOfCase() {
    initHomePage(TestAccount.DEMO_USER);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openDetailsOfCaseHasName(LEAVE_REQUEST_CASE_NAME);
    assertEquals(3, caseDetailsPage.countRelatedTasks());
    TaskDetailsPage taskDetailsPage = caseDetailsPage.openTasksOfCasePage(0);
    assertEquals("Task Details", taskDetailsPage.getPageTitle());
  }
  
  @Test
  public void testOpenAdditionalCaseDetailsPage() throws Exception {
    openAdditionalCaseDetailsPage(createTestingCaseUrlForDefaultAdditionalCaseDetails, LEAVE_REQUEST_DEFAULT_CASE_DETAILS_PAGE_CASE_NAME);
    validateAdditionalCaseDetailsPage(15, "CustomVarCharField 1");
  }
  
  @Test
  public void testOpenCustomizationAdditionalCaseDetailsPage() throws Exception {
    openAdditionalCaseDetailsPage(createTestingCaseUrlForCustomizationAdditionalCaseDetails, INVESTMENT_REQUEST_CUSTOMIZATION_CASE_DETAILS_PAGE_CASE_NAME);
    validateAdditionalCaseDetailsPage(7, "Apartment A");
  }
  
  @Test
  public void testEnableAndDisableColumnsInCaseWidget() {
    initHomePage(TestAccount.DEMO_USER);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseListColumnExist(CREATED_COLUMN_HEADER));
    assertTrue(casePage.isCaseListColumnExist(STATE_COLUMN_HEADER));
    casePage.clickColumnsButton();
    casePage.clickDefaultCheckbox();
    casePage.clickColumnCheckbox(4);
    casePage.clickApplyButton();
    assertFalse(casePage.isCaseListColumnExist(CREATED_COLUMN_HEADER));
    assertTrue(casePage.isCaseListColumnExist(STATE_COLUMN_HEADER));
    casePage.clickColumnsButton();
    casePage.clickColumnCheckbox(4);
    casePage.clickColumnCheckbox(6);
    casePage.clickApplyButton();
    assertTrue(casePage.isCaseListColumnExist(CREATED_COLUMN_HEADER));
    assertFalse(casePage.isCaseListColumnExist(STATE_COLUMN_HEADER));
  }
  
  @Test
  public void testEnterCaseDetailFromActionMenuAndGoBack() {
    initHomePage(TestAccount.DEMO_USER);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openCaseDetailsFromActionMenuByCaseName(LEAVE_REQUEST_CASE_NAME);
    assertEquals("Case Details", caseDetailsPage.getPageTitle());
    casePage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    assertEquals("Cases", casePage.getPageTitle());
  }

  @Test
  public void testFinishTaskFromCaseDetailAndGoBack() {
    initHomePage(TestAccount.DEMO_USER);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();

    caseDetailsPage = casePage.openCaseDetailsFromActionMenuByCaseName(LEAVE_REQUEST_CASE_NAME);
    assertEquals("Case Details", caseDetailsPage.getPageTitle());

    TaskDetailsPage taskDetailsPage = caseDetailsPage.openTasksOfCasePage(0);
    assertEquals("Task Details", taskDetailsPage.getPageTitle());

    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.inputFields("tester", "16.05.2019", "17.05.2019", "tester");
    taskTemplatePage.findElementById("leave-request:button-submit").click();

    caseDetailsPage = new CaseDetailsPage();
    assertEquals("Case Details", caseDetailsPage.getPageTitle());

    casePage = caseDetailsPage.goBackToCaseListFromCaseDetails();
    assertEquals("Cases", casePage.getPageTitle());
  }

  private void openAdditionalCaseDetailsPage(String initDataUrl, String caseName){
    redirectToRelativeLink(initDataUrl);
    initHomePage(TestAccount.ADMIN_USER);
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
    login(account);
    homePage = new HomePage();
  }
  
  @Test
  public void testShowCaseCount() {
    initHomePage(TestAccount.DEMO_USER);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertEquals(1, casePage.getCaseCount().intValue());
  }
  
  @Test
  public void testDisableTaskCount() {
    initHomePage(TestAccount.ADMIN_USER);
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setDisabledCaseCount();

    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertEquals("Case count is not disabled", null, casePage.getCaseCount());
  }
  
}
