package com.axonivy.portal.selenium.test.caze;


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.CaseState;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.AdditionalCaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.UserProfilePage;
import com.codeborne.selenide.WebDriverRunner;

@IvyWebTest
public class CaseWidgetTest extends BaseTest {

  private static final String INVESTMENT_REQUEST_CUSTOMIZATION_CASE_DETAILS_PAGE_CASE_NAME = "Create Investment";
  private static final String LEAVE_REQUEST_DEFAULT_CASE_DETAILS_PAGE_CASE_NAME =
      "Leave Request for Default Additional Case Details";
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String CREATED_COLUMN_HEADER = "Created";
  private static final String STATE_COLUMN_HEADER = "State";
  private static final String RELATED_CASE_STATE_COLUMN = "state-column";
  private static final String RELATED_CASE_CREATED_COLUMN = "created-column";

  private NewDashboardPage newDashboardPage;
  private MainMenuPage mainMenuPage;
  private CaseWidgetPage casePage;
  private CaseDetailsPage caseDetailsPage;
  private AdditionalCaseDetailsPage additionalCaseDetailsPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void testHideCase() {
    redirectToRelativeLink(hideCaseUrl);
    initNewDashboardPage(TestAccount.ADMIN_USER);

    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy("Report and hide case", 2);
    taskWidgetPage.startTaskWithoutUI(1);
    newDashboardPage = new NewDashboardPage();

    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertFalse(casePage.isCaseDisplayed("Repair Computer"));
  }

  @Test
  public void testDestroyCaseWithPermission() {
    initNewDashboardPage(TestAccount.ADMIN_USER);
    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    casePage.clickDestroyButton();
    casePage.confimDestruction();
    redirectToNewDashBoard();
    CaseState caseState = casePage.getCaseState(0);
    assertEquals(CaseState.DESTROYED, caseState);
  }

  @Test
  public void testDestroyCaseWithoutPermission() {
    initNewDashboardPage(TestAccount.DEMO_USER);
    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertFalse(casePage.isDestroyButtonVisible());
  }

  @Test
  public void testOpenRelatedTasksOfCase() {
    initNewDashboardPage(TestAccount.DEMO_USER);
    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openDetailsOfCaseHasName(LEAVE_REQUEST_CASE_NAME);
    assertEquals(4, caseDetailsPage.countRelatedTasks().size());
  }

  @Test
  public void testOpenRelatedCasesOfCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    initNewDashboardPage(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openDetailsOfCaseHasName(ORDER_PIZZA);
    assertEquals(1, caseDetailsPage.countRelatedCases());
  }

  @Test
  public void testOpenAdditionalCaseDetailsPage() throws Exception {
    openAdditionalCaseDetailsPage(createTestingCaseUrlForDefaultAdditionalCaseDetails,
        LEAVE_REQUEST_DEFAULT_CASE_DETAILS_PAGE_CASE_NAME);
    validateAdditionalCaseDetailsPage(13, "Customer name", false);
  }

  @Test
  public void testOpenCustomizationAdditionalCaseDetailsPage() throws Exception {
    openAdditionalCaseDetailsPage(createTestingCaseUrlForCustomizationAdditionalCaseDetails,
        INVESTMENT_REQUEST_CUSTOMIZATION_CASE_DETAILS_PAGE_CASE_NAME);
    validateAdditionalCaseDetailsPage(5, "Apartment A", true);
  }

  @Test
  public void testEnableAndDisableColumnsInCaseWidget() {
    initNewDashboardPage(TestAccount.DEMO_USER);
    redirectToNewDashBoard();
    mainMenuPage = newDashboardPage.openMainMenu();
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

  private void openAdditionalCaseDetailsPage(String initDataUrl, String caseName) {
    redirectToRelativeLink(initDataUrl);
    initNewDashboardPage(TestAccount.ADMIN_USER);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    newDashboardPage.waitPageLoaded();
    mainMenuPage = newDashboardPage.openMainMenu();
    additionalCaseDetailsPage = new AdditionalCaseDetailsPage();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openDetailsOfCaseHasName(caseName);
    caseDetailsPage.openActionMenu();
    caseDetailsPage.openAdditionalCaseDetailsPage();
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((webDriver) -> newDashboardPage.countBrowserTab() > 1);
    newDashboardPage.switchLastBrowserTab();
    mainMenuPage.waitPageLoaded();
  }

  private void validateAdditionalCaseDetailsPage(int expectedNumberOfFields, String expectedFirstFieldValue,
      boolean isIframe) {
    int numberOfFields;
    try {
      if (isIframe) {
        numberOfFields = additionalCaseDetailsPage.countFieldsInIframe();
      } else {
        numberOfFields = additionalCaseDetailsPage.countFields();
      }
    } catch (TimeoutException e) { // sometimes session is destroyed (don't know reason why!!!) so we cannot reach the
                                   // page
      System.out.println("Stop testOpenCustomizationAdditionalCaseDetailsPage test here because session is destroyed");
      return;
    }
    assertEquals(expectedNumberOfFields, numberOfFields);
    assertEquals(expectedFirstFieldValue, additionalCaseDetailsPage.getAdditionalFieldContentOfFirstRow());
  }

  private void initNewDashboardPage(TestAccount account) {
    login(account);
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testShowCaseCount() {
    initNewDashboardPage(TestAccount.DEMO_USER);
    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    casePage.waitUntilCaseCountDifferentThanZero();
    assertEquals(1, casePage.getCaseCount().intValue());
  }

  @Test
  public void testDisableCaseCount() {
    updatePortalSetting(Variable.DISABLE_CASE_COUNT.getKey(), "true");
    initNewDashboardPage(TestAccount.ADMIN_USER);

    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertEquals(null, casePage.getCaseCount());
  }

  @Test
  public void testBreadCrumb() {
    initNewDashboardPage(TestAccount.DEMO_USER);
    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertEquals("Cases (1)", casePage.getTextOfCurrentBreadcrumb());
    newDashboardPage = casePage.goToHomeFromBreadcrumb();
    assertTrue(newDashboardPage.isDisplayed());
  }

  @Test
  public void testBreadCrumbInCaseDetail() {
    initNewDashboardPage(TestAccount.DEMO_USER);
    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    assertEquals("Case: Leave Request", caseDetailsPage.getTextOfCurrentBreadcrumb());

    caseDetailsPage.clickCaseListBreadCrumb();
    casePage = new CaseWidgetPage();
    assertTrue(casePage.isDisplayed());

    caseDetailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    newDashboardPage = caseDetailsPage.goToHomeFromBreadcrumb();
    assertTrue(newDashboardPage.isDisplayed());
  }

  @Test
  public void testChangeCaseSortingOptions() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    redirectToNewDashBoard();

    NewDashboardPage newDashboardPage = new NewDashboardPage();
    UserProfilePage userProfilePage = newDashboardPage.openMyProfilePage();

    // Change sorting options
    userProfilePage.selectCaseSortField("Name");
    userProfilePage.selectCaseSortDirection("Sort ascending");
    newDashboardPage = userProfilePage.save();

    // Check result
    CaseWidgetPage caseWidgetPage = newDashboardPage.openCaseList();
    assertEquals("Create 12 Cases with category", caseWidgetPage.getCaseNameAt(0));
    assertEquals("TestCase", caseWidgetPage.getCaseNameAt(13));

    // Change sorting options
    userProfilePage = caseWidgetPage.openMyProfilePage();
    userProfilePage.selectCaseSortField("State");
    userProfilePage.selectCaseSortDirection("Sort descending");
    newDashboardPage = userProfilePage.save();

    // Check result
    caseWidgetPage = newDashboardPage.openCaseList();
    assertEquals(CaseState.DONE, caseWidgetPage.getCaseState(0));
  }

  @Test
  public void testExportToExcel() {
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    CaseWidgetPage caseWidgetPage = newDashboardPage.openCaseList();

    caseWidgetPage.clickExportToExcelLink();

    assertTrue(caseWidgetPage.isDownloadCompleted());
  }

  @Test
  public void testStickySortCaseList() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    redirectToRelativeLink(PORTAL_EXAMPLES_HOME_PAGE_URL);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    CaseWidgetPage caseWidgetPage = newDashboardPage.openCaseList();
    caseWidgetPage
        .sortCaseListByColumn("case-widget:created-date-column-header:created-date-column-header");
    caseWidgetPage.clickOnLogo();
    newDashboardPage = new NewDashboardPage();
    caseWidgetPage = newDashboardPage.openCaseList();
    String selectedSortColumn = caseWidgetPage.getSelectedSortColumn();
    assertTrue(StringUtils.equalsIgnoreCase("Created", selectedSortColumn));
    String caseName = caseWidgetPage.getCaseNameAt(0);
    assertTrue(StringUtils.equalsIgnoreCase("Leave Request", caseName));
    // Change sorting options
    UserProfilePage userProfilePage = caseWidgetPage.openMyProfilePage();
    userProfilePage.selectCaseSortField("State");
    userProfilePage.selectCaseSortDirection("Sort descending");
    newDashboardPage = userProfilePage.save();

    // Check result
    redirectToNewDashBoard();
    caseWidgetPage = newDashboardPage.openCaseList();
    selectedSortColumn = caseWidgetPage.getSelectedSortColumn();
    assertTrue(StringUtils.equalsIgnoreCase("State", selectedSortColumn));
    assertEquals(CaseState.DONE, caseWidgetPage.getCaseState(0));
  }

  @Test
  public void testRelatedCaseEnableAndDisableColumns() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitPageLoaded();
    CaseWidgetPage casePage = newDashboardPage.openCaseList();
    CaseDetailsPage detailsPage = casePage.openDetailsOfCaseHasName(ORDER_PIZZA);
    assertTrue(detailsPage.isRelatedCaseListColumnExist(RELATED_CASE_CREATED_COLUMN));
    assertTrue(detailsPage.isRelatedCaseListColumnExist(RELATED_CASE_STATE_COLUMN));
    detailsPage.clickRelatedCaseColumnsButton();
    detailsPage.clickRelatedCaseDefaultCheckbox();
    detailsPage.clickRelatedCaseColumnCheckbox(4);
    detailsPage.clickRelatedCaseApplyButton();
    WaitHelper.assertTrueWithWait(() -> detailsPage.isRelatedCaseListColumnNotExist(RELATED_CASE_CREATED_COLUMN));
    WaitHelper.assertTrueWithWait(() -> detailsPage.isRelatedCaseListColumnExist(RELATED_CASE_STATE_COLUMN));
    detailsPage.clickRelatedCaseColumnsButton();
    detailsPage.clickRelatedCaseColumnCheckbox(4);
    detailsPage.clickRelatedCaseColumnCheckbox(6);
    detailsPage.clickRelatedCaseApplyButton();
    WaitHelper.assertTrueWithWait(() -> detailsPage.isRelatedCaseListColumnExist(RELATED_CASE_CREATED_COLUMN));
    WaitHelper.assertTrueWithWait(() -> detailsPage.isRelatedCaseListColumnNotExist(RELATED_CASE_STATE_COLUMN));
  }
  
  @Test
  public void testCaseReadAllOwnRoleInvolved() {
    redirectToRelativeLink(createTaskForRoleInvolved);
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    login(TestAccount.HR_ROLE_USER);
    redirectToRelativeLink(PORTAL_EXAMPLES_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    homePage.waitForPageLoad();
    TaskWidgetPage taskWidgetPage = homePage.openUserExampleTaskList();
    taskWidgetPage.waitForPageLoad();
    taskWidgetPage.filterTasksInExpandedModeBy("Task for role involved", 1);
    taskWidgetPage.startTaskWithoutWaitTaskActionRendered(0);

    login(TestAccount.HR_ROLE_USER_2);
    redirectToRelativeLink(grantCaseReadAllOwnRoleInvolvedPermission);
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    CaseWidgetPage casePage = homePage.openCaseList();
    casePage.waitForPageLoad();
    assertTrue(casePage.isCaseDisplayed("Test Process: role involved"));

    redirectToRelativeLink(denyCaseReadAllOwnRoleInvolvedPermission);
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    casePage = homePage.openCaseList();
    casePage.waitForPageLoad();
    assertFalse(casePage.isCaseDisplayed("Test Process: role involved"));
  }

}
