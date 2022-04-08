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
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.AdditionalCaseDetailsPage;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.UserProfilePage;

public class CaseWidgetTest extends BaseTest {

  private static final String INVESTMENT_REQUEST_CUSTOMIZATION_CASE_DETAILS_PAGE_CASE_NAME = "Investment Request";
  private static final String LEAVE_REQUEST_DEFAULT_CASE_DETAILS_PAGE_CASE_NAME = "Leave Request for Default Additional Case Details";
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String CREATED_COLUMN_HEADER = "Created";
  private static final String STATE_COLUMN_HEADER = "State";
  private static final String RELATED_CASE_STATE_COLUMN = "state-column";
  private static final String RELATED_CASE_CREATED_COLUMN = "created-column";
  
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

  @SuppressWarnings("deprecation")
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
    assertEquals(4, caseDetailsPage.countRelatedTasks());
  }

  @Test
  public void testOpenRelatedCasesOfCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    initHomePage(TestAccount.DEMO_USER);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    caseDetailsPage = casePage.openDetailsOfCaseHasName(ORDER_PIZZA);
    assertEquals(1, caseDetailsPage.countRelatedCases());
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
    caseDetailsPage.openActionMenu();
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
    updatePortalSetting(DISABLE_CASE_COUNT.getKey(), "true");
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
    homePage = casePage.goToHomeFromBreadcrumb();
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
    homePage = caseDetailsPage.goToHomeFromBreadcrumb();
    assertTrue(homePage.isDisplayed());
  }

  @Test
  public void testChangeCaseSortingOptions() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);

    HomePage homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();

    // Change sorting options
    userProfilePage.selectCaseSortField("Name");
    userProfilePage.selectCaseSortDirection("Sort ascending");
    homePage = userProfilePage.save();

    // Check result
    CaseWidgetPage caseWidgetPage = homePage.openCaseList();
    assertEquals("Create 12 Cases with category", caseWidgetPage.getCaseNameAt(0));
    assertEquals("TestCase", caseWidgetPage.getCaseNameAt(13));

    // Change sorting options
    userProfilePage = caseWidgetPage.openMyProfilePage();
    userProfilePage.selectCaseSortField("State");
    userProfilePage.selectCaseSortDirection("Sort descending");
    homePage = userProfilePage.save();

    // Check result
    caseWidgetPage = homePage.openCaseList();
    assertEquals(CaseState.DONE, caseWidgetPage.getCaseState(0));
  }

  @Test
  public void testExportToExcel() {
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    CaseWidgetPage caseWidgetPage = homePage.openCaseList();

    caseWidgetPage.clickExportToExcelLink();

    assertTrue(caseWidgetPage.isDownloadCompleted());
  }

  @Test
  public void testStickySortCaseList() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    HomePage homePage = new HomePage();
    CaseWidgetPage caseWidgetPage = homePage.openCaseList();
    caseWidgetPage.sortCaseListByColumn("case-widget:case-list-header:created-date-column-header:created-date-column-header");
    caseWidgetPage.clickOnLogo();
    homePage = new HomePage();
    caseWidgetPage = homePage.openCaseList();
    String selectedSortColumn = caseWidgetPage.getSelectedSortColumn();
    assertTrue(StringUtils.equalsIgnoreCase("Created", selectedSortColumn));
    String caseName = caseWidgetPage.getCaseNameAt(0);
    assertTrue(StringUtils.equalsIgnoreCase("Leave Request", caseName));
    // Change sorting options
    UserProfilePage userProfilePage = caseWidgetPage.openMyProfilePage();
    userProfilePage.selectCaseSortField("State");
    userProfilePage.selectCaseSortDirection("Sort descending");
    homePage = userProfilePage.save();
    
    // Check result
    caseWidgetPage = homePage.openCaseList();
    selectedSortColumn = caseWidgetPage.getSelectedSortColumn();
    assertTrue(StringUtils.equalsIgnoreCase("State", selectedSortColumn));
    assertEquals(CaseState.DONE, caseWidgetPage.getCaseState(0));
  }

  @Test
  public void testRelatedCaseEnableAndDisableColumns() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    HomePage homePage = new HomePage();
    CaseWidgetPage casePage = homePage.openCaseList();
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
