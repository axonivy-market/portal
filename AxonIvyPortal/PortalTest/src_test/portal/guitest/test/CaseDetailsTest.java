package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.util.ConfigurationJsonUtil;
import ch.ivyteam.ivy.workflow.TaskState;
import portal.guitest.common.BaseTest;
import portal.guitest.common.CaseState;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.common.TestRole;
import portal.guitest.common.Variable;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.AdditionalCaseDetailsPage;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskTemplatePage;

public class CaseDetailsTest extends BaseTest {

  private static final String RELATED_TASK_STATE_COLUMN = "related-task-state-column";
  private static final String RELATED_TASK_EXPIRY_COLUMN = "related-task-expiry-column";
  private HomePage homePage;
  private CaseDetailsPage detailsPage;

  private static final String BUSINESS_CASE_MAP_LEAVE_REQUEST = "Business Case Map: Leave Request";
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/grantOnlyDelegateOwnTasksPermission.ivp";
  private static final String DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/undoOnlyDelegateOwnTasksPermission.ivp";
  public static final String CUSTOM_CASE_WIDGET_NAME = "Create Event: Test custom case details";
  public static final String CREATE_EVENT_TEST_URL ="portal-developer-examples/17A2C6D73AB4186E/CreateEventTest.ivp";

  @Override
  @Before
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);

    homePage = new HomePage();
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }

  private void createTestingTask() {
    redirectToRelativeLink(createTestingTasksUrl);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(LEAVE_REQUEST_CASE_NAME);
  }

  private void createTestingCaseContainTechnicalCases() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(ORDER_PIZZA);
  }

  @Test
  public void testDestroyCase() {
    createTestingTask();
    detailsPage.openActionMenu();
    detailsPage.onClickDestroyCase();
    detailsPage.confimDestruction();
    CaseWidgetPage casePage = new CaseWidgetPage();
    CaseState caseState = casePage.getCaseState(0);
    assertEquals(CaseState.DESTROYED, caseState);
  }

  @Test
  public void testDisplayCaseProperties() {
    createTestingTask();
    assertTrue(StringUtils.equalsIgnoreCase("Leave Request", detailsPage.getCaseCategory()));
    assertTrue(StringUtils.isNotBlank(detailsPage.getCaseDuration()));
  }

  @Test
  public void testAddCaseNote() {
    createTestingTask();
    assertEquals(1, detailsPage.getNumberOfHistory());
    detailsPage.addNote("Consider the remaining annual leaves before the approval");
    assertEquals(2, detailsPage.getNumberOfHistory());
    assertEquals("Consider the remaining annual leaves before the approval", detailsPage.getLatestHistoryContent());
  }

  @Test
  public void testShowCaseDetail() {
    createTestingCaseContainTechnicalCases();
    assertTrue(detailsPage.isGeneralInformationComponentPresented());
    assertTrue(detailsPage.isRelatedCasesComponentPresented());
    assertTrue(detailsPage.isRelatedTasksComponentPresented());
    assertTrue(detailsPage.isHistoryComponentPresented());
    assertTrue(detailsPage.isDocumentComponentPresented());
  }

  @Test
  public void testShowBusinessCaseInTechnicalCase() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    login(TestAccount.DEMO_USER);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openCaseDetailsFromActionMenuByCaseName(BUSINESS_CASE_MAP_LEAVE_REQUEST);
    // check business case information is hidden in business case details
    assertFalse(detailsPage.isBusinessCaseInformationSectionDisplayed());

    // keep business case id
    String originalBusinessCaseId = detailsPage.getCaseId();

    // open related case detail - technical case detail
    detailsPage.clickRelatedCaseActionButton(0);
    CaseDetailsPage technicalCaseDetailsPage = detailsPage.openCasesOfCasePageViaDetailsAction(0);
    WaitHelper.assertTrueWithWait(() -> "Case Details".equals(technicalCaseDetailsPage.getPageTitle()));

    // check business case information is displayed in technical case
    WaitHelper.assertTrueWithWait(() -> detailsPage.isBusinessCaseInformationSectionDisplayed());

    // open business case detail from technical case details
    CaseDetailsPage businessCaseDetailsPage = technicalCaseDetailsPage.openBusinessCaseFromTechnicalCase();

    // compare business case id
    String businessCaseId = businessCaseDetailsPage.getCaseId();
    assertEquals(originalBusinessCaseId, businessCaseId);
  }

  @Test
  public void testRelatedTaskStartButtonStatus() {
    createTestingTask();
    assertFalse(detailsPage.isRelatedTaskStartEnabled(1));
    assertTrue(detailsPage.isRelatedTaskStartEnabled(2));
  }

  @Test
  public void testRelatedTaskStartTask() {
    createTestingTask();
    TaskTemplatePage taskTemplate = detailsPage.startRelatedTask(2);
    assertEquals("Sick Leave Request", taskTemplate.getTaskName());
  }

  @Test
  public void testRelatedTaskReserveTask() {
    createTestingTask();
    detailsPage.clickRelatedTaskActionButton(2);
    detailsPage.reserveTask(2);
    assertTrue(detailsPage.isTaskState(2, TaskState.PARKED));

    detailsPage.clickRelatedTaskActionButton(2);
    detailsPage.resetTask(2);
    assertTrue(detailsPage.isTaskState(2, TaskState.SUSPENDED));
  }

  @Test
  public void testRelatedTaskDestroyTask() {
    createTestingTask();
    detailsPage.clickRelatedTaskActionButton(2);
    Assert.assertTrue(detailsPage.isRelatedTaskDestroyEnabled(2));
    detailsPage.destroyTask(2);
    detailsPage.confimRelatedTaskDestruction();
    assertTrue(detailsPage.isTaskState(2, TaskState.DESTROYED));
  }

  @Test
  public void testRelatedTaskDelegateTask() {
    createTestingTask();
    assertEquals(TestRole.EVERYBODY_ROLE, detailsPage.getResponsibleOfRelatedTaskAt(2));

    detailsPage.openTaskDelegateDialog(2);
    WaitHelper.assertTrueWithWait(() -> detailsPage.isDelegateTypeSelectAvailable());
    detailsPage.selectDelegateResponsible(TestAccount.HR_ROLE_USER.getFullName(), false);
    assertEquals(TestAccount.HR_ROLE_USER.getFullName(), detailsPage.getResponsibleOfRelatedTaskAt(2));

    detailsPage.openTaskDelegateDialog(2);
    detailsPage.selectDelegateResponsible(TestRole.HR_ROLE, true);
    assertEquals(TestRole.HR_ROLE, detailsPage.getResponsibleOfRelatedTaskAt(2));
  }

  @Test
  public void testRelatedTaskDisplayDelegateButton() {
    createTestingTask();
    redirectToRelativeLink(GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(LEAVE_REQUEST_CASE_NAME);
    assertFalse(detailsPage.isTaskDelegateOptionDisable(2));
    assertTrue(detailsPage.isTaskDelegateOptionDisable(1));
    redirectToRelativeLink(DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL);
  }

  @Test
  public void testRelatedTaskOpenDetails() {
    createTestingTask();
    detailsPage.clickRelatedTaskActionButton(0);
    TaskDetailsPage taskDetailsPage = detailsPage.openTasksOfCasePageViaDetailsAction(0);
    WaitHelper.assertTrueWithWait(() -> "Task Details".equals(taskDetailsPage.getPageTitle()));
  }

  @Test
  public void testRelatedCaseOpenDetails() {
    createTestingCaseContainTechnicalCases();
    detailsPage.clickRelatedCaseActionButton(0);
    CaseDetailsPage caseDetailsPage = detailsPage.openCasesOfCasePageViaDetailsAction(0);
    WaitHelper.assertTrueWithWait(() -> "Case Details".equals(caseDetailsPage.getPageTitle()));
  }

  @Test
  public void testRelatedCaseOpenBusinessDetails() {
    createTestingCaseContainTechnicalCases();
    detailsPage.clickRelatedCaseActionButton(0);
    AdditionalCaseDetailsPage caseDetailsPage = detailsPage.openRelatedCaseBusinessDetail(0);
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> caseDetailsPage.countBrowserTab() > 1);
    caseDetailsPage.switchLastBrowserTab();
    AdditionalCaseDetailsPage additionalCaseDetailsPage = new AdditionalCaseDetailsPage();
    WaitHelper.assertTrueWithWait(() -> "Additional Case Details".equals(additionalCaseDetailsPage.getPageTitle()));
  }

  @Test
  public void testRelatedCaseSideSteps() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(BUSINESS_CASE_MAP_LEAVE_REQUEST);
    assertEquals(1, detailsPage.countRelatedCases());
    detailsPage.clickRelatedCaseActionButton(0);
    detailsPage.clickRelatedCaseSubmitLeaveReason(0);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(BUSINESS_CASE_MAP_LEAVE_REQUEST);
    assertEquals(2, detailsPage.countRelatedCases());
    detailsPage.clickRelatedCaseActionButton(0);
    detailsPage.clickRelatedCaseUploadAdditionalDocument(0);
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(BUSINESS_CASE_MAP_LEAVE_REQUEST);
    assertEquals(3, detailsPage.countRelatedCases());
  }

  @Test
  public void testRelatedTaskOpenWorkflowEvents() {
    createTestingTask();
    detailsPage.clickRelatedTaskActionButton(0);
    detailsPage.openRelatedTaskWorkflowEvents(0);
    assertTrue(detailsPage.isRelatedTaskWorkflowEventsOpened());
  }

  @Test
  public void testRelatedTaskAddAdHocTask() {
    createTestingTask();
    detailsPage.clickRelatedTaskActionButton(2);
    ExpressProcessPage expressProcessPage = detailsPage.addAdHocTask(2);
    assertTrue(expressProcessPage.getProcessName().endsWith("Sick Leave Request"));
  }

  @Test
  public void testRelatedTaskExportToExcel() {
    createTestingTask();
    detailsPage.clickExportToExcelLink("related-task-export-to-excel", "related-task-status-dialog");
    assertTrue(detailsPage.isDownloadCompleted("related-task-status-dialog"));
  }

  @Test
  public void testRelatedCaseExportToExcel() {
    createTestingCaseContainTechnicalCases();
    detailsPage.clickExportToExcelLink("related-case-export-to-excel", "related-case-status-dialog");
    assertTrue(detailsPage.isDownloadCompleted("related-case-status-dialog"));
  }

  @Test
  public void testRelatedTaskEnableAndDisableColumns() {
    createTestingTask();
    assertTrue(detailsPage.isRelatedTaskListColumnExist(RELATED_TASK_EXPIRY_COLUMN));
    assertTrue(detailsPage.isRelatedTaskListColumnExist(RELATED_TASK_STATE_COLUMN));
    detailsPage.clickRelatedTaskColumnsButton();
    detailsPage.clickRelatedTaskDefaultCheckbox();
    detailsPage.clickRelatedTaskColumnCheckbox(6);
    detailsPage.clickRelatedTaskApplyButton();
    WaitHelper.assertTrueWithWait(() -> !detailsPage.isRelatedTaskListColumnExist(RELATED_TASK_EXPIRY_COLUMN));
    WaitHelper.assertTrueWithWait(() -> detailsPage.isRelatedTaskListColumnExist(RELATED_TASK_STATE_COLUMN));
    detailsPage.clickRelatedTaskColumnsButton();
    detailsPage.clickRelatedTaskColumnCheckbox(6);
    detailsPage.clickRelatedTaskColumnCheckbox(8);
    detailsPage.clickRelatedTaskApplyButton();
    WaitHelper.assertTrueWithWait(() -> detailsPage.isRelatedTaskListColumnExist(RELATED_TASK_EXPIRY_COLUMN));
    WaitHelper.assertTrueWithWait(() -> !detailsPage.isRelatedTaskListColumnExist(RELATED_TASK_STATE_COLUMN));
  }

  @Test
  public void testHistoryAuthorIsUserFullName() {
    createTestingTask();
    detailsPage.addNote("Sample case note");
    assertEquals(TestAccount.ADMIN_USER.getFullName(), detailsPage.getHistoryAuthor());
  }

  @Test
  public void testHistoryShowDoneTasks() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    login(TestAccount.DEMO_USER);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openCaseDetailsFromActionMenuByCaseName(BUSINESS_CASE_MAP_LEAVE_REQUEST);
    assertTrue(detailsPage.checkDoneTasksOfHistory());

    int relatedDoneTasks = detailsPage.countRelatedDoneTasks();
    detailsPage.showNoteHistory();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> detailsPage.countBrowserTab() > 1);
    detailsPage.switchLastBrowserTab();
    NoteHistoryPage caseHistoryPage = new NoteHistoryPage();
    assertEquals(relatedDoneTasks, caseHistoryPage.countDoneTasks());
  }

  @Test
  public void testOpenViewNoteDialog() {
    createTestingTask();
    detailsPage.addNote("Consider the remaining annual leaves before the approval");
    detailsPage.clickViewNote();
    assertTrue(detailsPage.isViewNoteDialogPresented());
  }
  
  @Test
  public void testDragDropWidgets() {
    createTestingTask();
    detailsPage.switchToEditMode();
    detailsPage.waitForSaveButtonDisplayed();
    detailsPage.drapAndDropWidgets("information", "document");
    detailsPage.drapAndDropWidgets("document", "information");
    detailsPage.saveAndSwitchToViewMode();
    detailsPage.switchToEditMode();
    detailsPage.waitForResetButtonDisplayed();
    detailsPage.resetToDefault();
    detailsPage.confirmResetToDefault();
    detailsPage.saveAndSwitchToViewMode();
  }
  
  @Test
  public void testCustomWidgetsInCaseDetails() throws IOException {
    redirectToRelativeLink(CREATE_EVENT_TEST_URL);

    setupCaseDetailsWithIFrameProcess();
    assumeTrue("iframe CustomWidget is displayed", detailsPage.iframeCustomWidgetIsDisplayed());
    String processLink = detailsPage.getProcessLinkInCustomIFrameWidget();
    assertTrue(processLink.contains("portal-developer-examples/17A2C6D73AB4186E/startReview.ivp"));
    
    setupCaseDetailsWithIFrameURL();
    String url = detailsPage.getIFrameURLOfCustomWidget();
    assertTrue(url.contains("www.axonivy.com"));
    
    setupCaseDetailsWith2Panels();
    assertTrue(detailsPage.isCustomMiddlePanelDisplay());
  }

  public void setupCaseDetailsWith2Panels() throws IOException {
    ConfigurationJsonUtil.updateJSONSetting("custom-case-details-with-panel.json", Variable.CASE_DETAIL);
    detailsPage = goToCaseList().openDetailsOfCaseHasName(CUSTOM_CASE_WIDGET_NAME);
  }

  public void setupCaseDetailsWithIFrameURL() throws IOException {
    ConfigurationJsonUtil.updateJSONSetting("custom-case-details-with-url.json", Variable.CASE_DETAIL);
    detailsPage = goToCaseList().openDetailsOfCaseHasName(CUSTOM_CASE_WIDGET_NAME);
  }

  public void setupCaseDetailsWithIFrameProcess() throws IOException {
    ConfigurationJsonUtil.updateJSONSetting("custom-case-details.json", Variable.CASE_DETAIL);
    detailsPage = goToCaseList().openDetailsOfCaseHasName(CUSTOM_CASE_WIDGET_NAME);
  }

  public CaseWidgetPage goToCaseList() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    return casePage;
  }

  @After
  public void teardown() {
    denySpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }

  @Test
  public void testShowRelatedCaseLinkInNote() {
    createTestingCaseContainTechnicalCases();
    assertEquals(1, detailsPage.getNumberOfHistory());
    detailsPage.addNote("This is note on business case");
    assertEquals(2, detailsPage.getNumberOfHistory());
    assertEquals("This is note on business case", detailsPage.getLatestHistoryContent());
    detailsPage.clickRelatedCaseActionButton(0);
    var relatedCaseDetailsPage = detailsPage.openCasesOfCasePageViaDetailsAction(0);
    WaitHelper.assertTrueWithWait(() -> "Case Details".equals(relatedCaseDetailsPage.getPageTitle()));
    relatedCaseDetailsPage.addNote("The first note of sub-case");
    relatedCaseDetailsPage.addNote("The second note of sub-case");
    var subCaseId = relatedCaseDetailsPage.getCaseId();
    var caseName = relatedCaseDetailsPage.getCaseName();
    assertEquals(2, relatedCaseDetailsPage.getNumberOfHistory());
    assertEquals(0, relatedCaseDetailsPage.getNumberOfHistoryForRelatedCaseLink());
    detailsPage = relatedCaseDetailsPage.openBusinessCaseFromTechnicalCase();
    assertEquals(4, detailsPage.getNumberOfHistory());
    assertEquals(2, detailsPage.getNumberOfHistoryForRelatedCaseLink());
    var relaledCaseName = detailsPage.getContentOfHistoryTableRelatedCaseColumn(0);
    assertTrue(relaledCaseName.startsWith("#"));
    assertTrue(relaledCaseName.contains(subCaseId));
    assertTrue(relaledCaseName.contains(caseName));
  }
}
