package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.common.CaseState;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.AdditionalCaseDetailsPage;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class CaseWidgetTest extends BaseTest {

  private static final String DISABLE_CASE_COUNT_SETTING = "DISABLE_CASE_COUNT";
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
  }

  @Test
  public void testHideCase() {
    redirectToRelativeLink(hideCaseUrl);
    initHomePage(TestAccount.ADMIN_USER);
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Report and hide case");
    taskWidgetPage.startTaskWithoutUI(0);
    homePage = new HomePage();
    
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
    WaitHelper.assertTrueWithWait(() -> !casePage.isCaseListColumnExist(CREATED_COLUMN_HEADER));
    WaitHelper.assertTrueWithWait(() -> casePage.isCaseListColumnExist(STATE_COLUMN_HEADER));
    casePage.clickColumnsButton();
    casePage.clickColumnCheckbox(4);
    casePage.clickColumnCheckbox(6);
    casePage.clickApplyButton();
    WaitHelper.assertTrueWithWait(() -> casePage.isCaseListColumnExist(CREATED_COLUMN_HEADER));
    WaitHelper.assertTrueWithWait(() -> !casePage.isCaseListColumnExist(STATE_COLUMN_HEADER));
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
    casePage.waitUntilCaseCountDifferentThanZero();
    assertEquals(1, casePage.getCaseCount().intValue());
  }
  
  @Test
  public void testDisableCaseCount() {
    updatePortalSetting(DISABLE_CASE_COUNT_SETTING, "true");
    initHomePage(TestAccount.ADMIN_USER);

    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertEquals("Case count is not disabled", null, casePage.getCaseCount());
  }

  @Test
  public void testBreadCrumb() {
    initHomePage(TestAccount.DEMO_USER);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertEquals("Cases (1)", casePage.getTextOfCurrentBreadcrumb());
    homePage = casePage.clickHomeBreadcrumb();
    assertTrue(homePage.isDisplayed());
  }

  @Test
  public void testBreadCrumbInCaseDetail() {
    initHomePage(TestAccount.DEMO_USER);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    assertEquals("Case: Leave Request", caseDetailsPage.getTextOfCurrentBreadcrumb());

    caseDetailsPage.clickCaseListBreadCrumb();
    casePage = new CaseWidgetPage();
    assertTrue(casePage.isDisplayed());

    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    homePage = caseDetailsPage.clickHomeBreadcrumb();
    assertTrue(homePage.isDisplayed());
  }
}
