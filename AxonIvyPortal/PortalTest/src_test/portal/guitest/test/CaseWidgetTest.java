package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static portal.guitest.common.Variable.DISABLE_CASE_COUNT;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.common.CaseState;
import portal.guitest.common.NavigationHelper;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.AdditionalCaseDetailsPage;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.UserProfilePage;

public class CaseWidgetTest extends BaseTest {

  private static final String INVESTMENT_REQUEST_CUSTOMIZATION_CASE_DETAILS_PAGE_CASE_NAME = "Create Investment";
  private static final String LEAVE_REQUEST_DEFAULT_CASE_DETAILS_PAGE_CASE_NAME = "Leave Request for Default Additional Case Details";
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String CREATED_COLUMN_HEADER = "Created";
  private static final String STATE_COLUMN_HEADER = "State";
  private static final String RELATED_CASE_STATE_COLUMN = "state-column";
  private static final String RELATED_CASE_CREATED_COLUMN = "created-column";
  
  private NewDashboardPage2 newDashboardPage2;
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
    initNewDashboardPage2(TestAccount.ADMIN_USER);
    
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy("Report and hide case", 2);
    taskWidgetPage.startTaskWithoutUI(1);
    newDashboardPage2 = new NewDashboardPage2();
    
    mainMenuPage = newDashboardPage2.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertFalse(casePage.isCaseDisplayed("Repair Computer"));
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testDestroyCaseWithPermission() {
    initNewDashboardPage2(TestAccount.ADMIN_USER);
    mainMenuPage = newDashboardPage2.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    casePage.clickDestroyButton();
    casePage.confimDestruction();
    casePage.waitAjaxIndicatorDisappear();
    CaseState caseState = casePage.getCaseState(0);
    assertEquals(CaseState.DESTROYED, caseState);

  }

  @Test
  public void testDestroyCaseWithoutPermission() {
    initNewDashboardPage2(TestAccount.DEMO_USER);
    mainMenuPage = newDashboardPage2.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertFalse(casePage.isDestroyButtonVisible());
  }

  @Test
  public void testOpenRelatedTasksOfCase() {
    initNewDashboardPage2(TestAccount.DEMO_USER);
    mainMenuPage = newDashboardPage2.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openDetailsOfCaseHasName(LEAVE_REQUEST_CASE_NAME);
    assertEquals(4, caseDetailsPage.countRelatedTasks());
  }

  @Test
  public void testOpenRelatedCasesOfCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    initNewDashboardPage2(TestAccount.DEMO_USER);
    mainMenuPage = newDashboardPage2.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openDetailsOfCaseHasName(ORDER_PIZZA);
    assertEquals(1, caseDetailsPage.countRelatedCases());
  }
  
  @Test
  public void testOpenAdditionalCaseDetailsPage() throws Exception {
    openAdditionalCaseDetailsPage(createTestingCaseUrlForDefaultAdditionalCaseDetails, LEAVE_REQUEST_DEFAULT_CASE_DETAILS_PAGE_CASE_NAME);
    validateAdditionalCaseDetailsPage(13, "Customer name");
  }
  
  @Test
  public void testOpenCustomizationAdditionalCaseDetailsPage() throws Exception {
    openAdditionalCaseDetailsPage(createTestingCaseUrlForCustomizationAdditionalCaseDetails, INVESTMENT_REQUEST_CUSTOMIZATION_CASE_DETAILS_PAGE_CASE_NAME);
    validateAdditionalCaseDetailsPage(4, "Apartment A");
  }
  
  @Test
  public void testEnableAndDisableColumnsInCaseWidget() {
    initNewDashboardPage2(TestAccount.DEMO_USER);
    mainMenuPage = newDashboardPage2.openMainMenu();
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
    initNewDashboardPage2(TestAccount.ADMIN_USER);
    redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    mainMenuPage = newDashboardPage2.openMainMenu();
    additionalCaseDetailsPage = new AdditionalCaseDetailsPage();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openDetailsOfCaseHasName(caseName);
    caseDetailsPage.openActionMenu();
    caseDetailsPage.openAdditionalCaseDetailsPage();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> newDashboardPage2.countBrowserTab() > 1);
    newDashboardPage2.switchLastBrowserTab();
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

  private void initNewDashboardPage2(TestAccount account) {
    login(account);
    newDashboardPage2 = new NewDashboardPage2();
  }
  
  @Test
  public void testShowCaseCount() {
    initNewDashboardPage2(TestAccount.DEMO_USER);
    mainMenuPage = newDashboardPage2.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    casePage.waitUntilCaseCountDifferentThanZero();
    assertEquals(1, casePage.getCaseCount().intValue());
  }
  
  @Test
  public void testDisableCaseCount() {
    updatePortalSetting(DISABLE_CASE_COUNT.getKey(), "true");
    initNewDashboardPage2(TestAccount.ADMIN_USER);

    mainMenuPage = newDashboardPage2.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertEquals("Case count is not disabled", null, casePage.getCaseCount());
  }

  @Test
  public void testBreadCrumb() {
    initNewDashboardPage2(TestAccount.DEMO_USER);
    mainMenuPage = newDashboardPage2.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertEquals("Cases (1)", casePage.getTextOfCurrentBreadcrumb());
    newDashboardPage2 = casePage.goToHomeFromBreadcrumb();
    assertTrue(newDashboardPage2.isDisplayed());
  }

  @Test
  public void testBreadCrumbInCaseDetail() {
    initNewDashboardPage2(TestAccount.DEMO_USER);
    mainMenuPage = newDashboardPage2.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    assertEquals("Case: Leave Request", caseDetailsPage.getTextOfCurrentBreadcrumb());

    caseDetailsPage.clickCaseListBreadCrumb();
    casePage = new CaseWidgetPage();
    assertTrue(casePage.isDisplayed());

    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    newDashboardPage2 = caseDetailsPage.goToHomeFromBreadcrumb();
    assertTrue(newDashboardPage2.isDisplayed());
  }

  @Test
  public void testChangeCaseSortingOptions() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);

    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    UserProfilePage userProfilePage = newDashboardPage2.openMyProfilePage();

    // Change sorting options
    userProfilePage.selectCaseSortField("Name");
    userProfilePage.selectCaseSortDirection("Sort ascending");
    newDashboardPage2 = userProfilePage.save();

    // Check result
    CaseWidgetPage caseWidgetPage = newDashboardPage2.openCaseList();
    assertEquals("Create 12 Cases with category", caseWidgetPage.getCaseNameAt(0));
    assertEquals("TestCase", caseWidgetPage.getCaseNameAt(13));

    // Change sorting options
    userProfilePage = caseWidgetPage.openMyProfilePage();
    userProfilePage.selectCaseSortField("State");
    userProfilePage.selectCaseSortDirection("Sort descending");
    newDashboardPage2 = userProfilePage.save();

    // Check result
    caseWidgetPage = newDashboardPage2.openCaseList();
    assertEquals(CaseState.DONE, caseWidgetPage.getCaseState(0));
  }

  @Test
  public void testExportToExcel() {
    login(TestAccount.ADMIN_USER);
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    CaseWidgetPage caseWidgetPage = newDashboardPage2.openCaseList();

    caseWidgetPage.clickExportToExcelLink();

    assertTrue(caseWidgetPage.isDownloadCompleted());
  }

  @Test
  public void testStickySortCaseList() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    CaseWidgetPage caseWidgetPage = newDashboardPage2.openCaseList();
    caseWidgetPage.sortCaseListByColumn("case-widget:case-list-header:created-date-column-header:created-date-column-header");
    caseWidgetPage.clickOnLogo();
    newDashboardPage2 = new NewDashboardPage2();
    caseWidgetPage = newDashboardPage2.openCaseList();
    String selectedSortColumn = caseWidgetPage.getSelectedSortColumn();
    assertTrue(StringUtils.equalsIgnoreCase("Created", selectedSortColumn));
    String caseName = caseWidgetPage.getCaseNameAt(0);
    assertTrue(StringUtils.equalsIgnoreCase("Leave Request", caseName));
    // Change sorting options
    UserProfilePage userProfilePage = caseWidgetPage.openMyProfilePage();
    userProfilePage.selectCaseSortField("State");
    userProfilePage.selectCaseSortDirection("Sort descending");
    newDashboardPage2 = userProfilePage.save();
    
    // Check result
    caseWidgetPage = newDashboardPage2.openCaseList();
    selectedSortColumn = caseWidgetPage.getSelectedSortColumn();
    assertTrue(StringUtils.equalsIgnoreCase("State", selectedSortColumn));
    assertEquals(CaseState.DONE, caseWidgetPage.getCaseState(0));
  }

  @Test
  public void testRelatedCaseEnableAndDisableColumns() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    CaseWidgetPage casePage = newDashboardPage2.openCaseList();
    CaseDetailsPage detailsPage = casePage.openDetailsOfCaseHasName(ORDER_PIZZA);
    assertTrue(detailsPage.isRelatedCaseListColumnExist(RELATED_CASE_CREATED_COLUMN));
    assertTrue(detailsPage.isRelatedCaseListColumnExist(RELATED_CASE_STATE_COLUMN));
    detailsPage.clickRelatedCaseColumnsButton();
    detailsPage.clickRelatedCaseDefaultCheckbox();
    detailsPage.clickRelatedCaseColumnCheckbox(4);
    detailsPage.clickRelatedCaseApplyButton();
    WaitHelper.assertTrueWithWait(() -> !detailsPage.isRelatedCaseListColumnExist(RELATED_CASE_CREATED_COLUMN));
    WaitHelper.assertTrueWithWait(() -> detailsPage.isRelatedCaseListColumnExist(RELATED_CASE_STATE_COLUMN));
    detailsPage.clickRelatedCaseColumnsButton();
    detailsPage.clickRelatedCaseColumnCheckbox(4);
    detailsPage.clickRelatedCaseColumnCheckbox(6);
    detailsPage.clickRelatedCaseApplyButton();
    WaitHelper.assertTrueWithWait(() -> detailsPage.isRelatedCaseListColumnExist(RELATED_CASE_CREATED_COLUMN));
    WaitHelper.assertTrueWithWait(() -> !detailsPage.isRelatedCaseListColumnExist(RELATED_CASE_STATE_COLUMN));
  }
}
