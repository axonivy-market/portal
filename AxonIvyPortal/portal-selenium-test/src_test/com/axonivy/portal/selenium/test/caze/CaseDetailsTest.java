package com.axonivy.portal.selenium.test.caze;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.TestRole;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.AdditionalCaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.NoteHistoryPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.util.ConfigurationJsonUtils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

@IvyWebTest
public class CaseDetailsTest extends BaseTest {
  private static final String BUSINESS_DETAILS_TITLE = "Business Details - Portal - Axon Ivy";
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String TAKE_ORDER = "Take Order";
  private static final String TAKE_ORDER_AND_MAKE_PIZZA = "Take Order and Make Pizza";

  private static final String TASK_DETAILS_TITLE = "Task Details - Portal - Axon Ivy";
  private static final String CASE_DETAILS_TITLE = "Case Details - Portal - Axon Ivy";
  
  // NOTE CONTENT
  private static final String NOTE_TECHNICAL_CASE = "Note is added on Technical Case";
  private static final String NOTE_BUSINESS_CASE = "Note is added on Business Case";

  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";

  private static final String RELATED_TASK_STATE_COLUMN = "related-task-state-column";
  private static final String RELATED_TASK_EXPIRY_COLUMN = "related-task-expiry-column";
  private com.axonivy.portal.selenium.page.NewDashboardPage newDashboardPage;
  private CaseDetailsPage detailsPage;

  private static final String BUSINESS_CASE_MAP_LEAVE_REQUEST = "Business Case Map: Leave Request";
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/grantOnlyDelegateOwnTasksPermission.ivp";
  private static final String DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/undoOnlyDelegateOwnTasksPermission.ivp";
  public static final String CUSTOM_CASE_WIDGET_NAME = "Create Event: Test custom case details";
  public static final String CREATE_EVENT_TEST_URL = "portal-developer-examples/17A2C6D73AB4186E/CreateEventTest.ivp";
  private static final String SICK_LEAVE_REQUEST_TASK = "Sick Leave Request";
  private static final String ANNUAL_LEAVE_REQUEST_TASK = "Annual Leave Request";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    newDashboardPage = new NewDashboardPage();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }

  @Test
  public void testVisibilityOfNotesWhenAddNoteOnBusinessCaseDetails() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidgetPage = NavigationHelper.navigateToCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsCase(ORDER_PIZZA);
    caseDetailsPage.getRelatedCasesComponents().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.getHitoriesComponent().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.addNote(NOTE_BUSINESS_CASE);
    caseDetailsPage.getNotesWithContent(NOTE_BUSINESS_CASE).shouldHave(size(1));
    caseDetailsPage.gotoTaskDetailsPageOfRelatedTask(ORDER_PIZZA);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.getNotesWithContent(NOTE_BUSINESS_CASE).shouldHave(size(1));
  }

  @Test
  public void testVisibilityOfNotesWhenAddNoteOnTechnicalCaseDetails() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openCaseList();
    CaseWidgetNewDashBoardPage caseWidgetPage = new CaseWidgetNewDashBoardPage();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsCase(ORDER_PIZZA);
    caseDetailsPage.getRelatedCasesComponents().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.gotoCaseDetailsPageOfRelatedCase(TAKE_ORDER_AND_MAKE_PIZZA);
    caseDetailsPage.getHitoriesComponent().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.addNote(NOTE_TECHNICAL_CASE);
    caseDetailsPage.getNotesWithContent(NOTE_TECHNICAL_CASE).shouldHave(size(1));
    caseDetailsPage.gotoTaskDetailsPageOfRelatedTask(TAKE_ORDER);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.getNotesWithContent(NOTE_TECHNICAL_CASE).shouldHave(size(1));
    taskDetailsPage.gotoBusinessCase();
    caseDetailsPage.getNotesWithContent(NOTE_TECHNICAL_CASE).shouldHave(size(1));
  }

  @Test
  public void testShareCaseDetails() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(grantShareLinkCaseDetailsPermission);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openCaseList();
    CaseWidgetNewDashBoardPage caseWidgetPage = new CaseWidgetNewDashBoardPage();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsCase(ORDER_PIZZA);
    caseDetailsPage.getRelatedCasesComponents().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.gotoCaseDetailsPageOfRelatedCase(TAKE_ORDER_AND_MAKE_PIZZA);
    caseDetailsPage.getShareButton().shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    caseDetailsPage.getShareDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    redirectToRelativeLink(denyShareLinkCaseDetailsPermission);
    redirectToNewDashBoard();
    mainMenuPage.openCaseList();
    caseDetailsPage = caseWidgetPage.openDetailsCase(ORDER_PIZZA);
    caseDetailsPage.getRelatedCasesComponents().shouldHave(sizeGreaterThanOrEqual(1));
    caseDetailsPage.gotoCaseDetailsPageOfRelatedCase(TAKE_ORDER_AND_MAKE_PIZZA);
    caseDetailsPage.getShareButton().shouldBe(Condition.disappear);
  }

  private void createTestingTask() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    newDashboardPage.waitPageLoaded();
    CaseWidgetNewDashBoardPage casePage = NavigationHelper.navigateToCaseList();
    detailsPage = casePage.openDetailsCase(LEAVE_REQUEST_CASE_NAME);
    detailsPage.waitPageLoaded();
    detailsPage.waitRelatedTasks();
  }

  private void createTestingCaseContainTechnicalCases() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    newDashboardPage.waitPageLoaded();
    CaseWidgetNewDashBoardPage casePage = NavigationHelper.navigateToCaseList();
    detailsPage = casePage.openDetailsCase(ORDER_PIZZA);
    detailsPage.waitPageLoaded();
  }

  @Test
  public void testDestroyCase() {
    createTestingTask();
    assertEquals("Open", detailsPage.getCaseState());
    detailsPage.openActionMenu();
    detailsPage.onClickDestroyCase();
    detailsPage.confimDestruction();
    CaseWidgetNewDashBoardPage casePage = new CaseWidgetNewDashBoardPage();
    casePage.stateOfFirstCase().shouldHave(text("Destroyed"));
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
    detailsPage.getNumberOfHistory().shouldHave(size(1), DEFAULT_TIMEOUT);
    detailsPage.addNote("Consider the remaining annual leaves before the approval");
    detailsPage.getNumberOfHistory().shouldHave(size(2), DEFAULT_TIMEOUT);
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
    newDashboardPage.waitPageLoaded();
    CaseWidgetNewDashBoardPage casePage = NavigationHelper.navigateToCaseList();
    detailsPage = casePage.openDetailsCase(BUSINESS_CASE_MAP_LEAVE_REQUEST);
    // check business case information is hidden in business case details
    assertFalse(detailsPage.isBusinessCaseInformationSectionDisplayed());

    // keep business case id
    String originalBusinessCaseId = detailsPage.getCaseId();

    // open related case detail - technical case detail
    detailsPage.clickRelatedCaseActionButton(0);
    CaseDetailsPage technicalCaseDetailsPage = detailsPage.openCasesOfCasePageViaDetailsAction(0);
    WaitHelper.assertTrueWithWait(() -> CASE_DETAILS_TITLE.equals(technicalCaseDetailsPage.getPageTitle()));

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
    SelenideElement element1 = detailsPage.getRelatedTaskStart(ANNUAL_LEAVE_REQUEST_TASK);
    element1.shouldHave(Condition.cssClass("ui-state-disabled"));

    SelenideElement element2 = detailsPage.getRelatedTaskStart(SICK_LEAVE_REQUEST_TASK);
    element2.shouldNotHave(Condition.cssClass("ui-state-disabled"));
  }

  @Test
  public void testRelatedTaskStartTask() {
    createTestingTask();
    TaskTemplatePage taskTemplate = detailsPage.startRelatedTask(SICK_LEAVE_REQUEST_TASK);
    assertEquals(SICK_LEAVE_REQUEST_TASK, taskTemplate.getTaskName());
  }

  @Test
  public void testRelatedTaskReserveTask() {
    createTestingTask();
    detailsPage.clickRelatedTaskActionButton(SICK_LEAVE_REQUEST_TASK);
    detailsPage.reserveTask(SICK_LEAVE_REQUEST_TASK);
    assertTrue(detailsPage.isTaskState(SICK_LEAVE_REQUEST_TASK, TaskBusinessState.OPEN));

    detailsPage.clickRelatedTaskActionButton(SICK_LEAVE_REQUEST_TASK);
    detailsPage.resetTask(SICK_LEAVE_REQUEST_TASK);
    assertTrue(detailsPage.isTaskState(SICK_LEAVE_REQUEST_TASK, TaskBusinessState.OPEN));
    detailsPage.clickRelatedTaskActionButton(SICK_LEAVE_REQUEST_TASK);
    detailsPage.openRelatedTaskWorkflowEvents(detailsPage.getTaskRowIndex(SICK_LEAVE_REQUEST_TASK));

    String dataFromWorkflowEvents = detailsPage.getEventTypeInWorkflowEvents();
    assertTrue(dataFromWorkflowEvents.contains("EVENT_ROLLBACK_TASK"));
    assertTrue(dataFromWorkflowEvents.contains("EVENT_PARK_TASK"));
  }

  @Test
  public void testRelatedTaskDestroyTask() {
    createTestingTask();
    detailsPage.clickRelatedTaskActionButton(SICK_LEAVE_REQUEST_TASK);
    assertTrue(detailsPage.isRelatedTaskDestroyEnabled(SICK_LEAVE_REQUEST_TASK));
    detailsPage.destroyTask(SICK_LEAVE_REQUEST_TASK);
    detailsPage.confimRelatedTaskDestruction();
    assertTrue(detailsPage.isTaskState(SICK_LEAVE_REQUEST_TASK, TaskBusinessState.DESTROYED));
  }

  @Test
  // unstable test
  public void testRelatedTaskDelegateTask() {
    createTestingTask();
    ScreenshotUtils.resizeBrowser(new Dimension(1920, 1080));
    assertEquals(TestRole.EVERYBODY_ROLE, detailsPage.getResponsibleOfRelatedTaskAt(SICK_LEAVE_REQUEST_TASK));

    detailsPage.openTaskDelegateDialog(SICK_LEAVE_REQUEST_TASK);
    WaitHelper.assertTrueWithWait(() -> detailsPage.isDelegateTypeSelectAvailable());
    detailsPage.selectDelegateResponsible(TestAccount.HR_ROLE_USER.getFullName(), false);
    assertEquals(TestAccount.HR_ROLE_USER.getFullName(),
        detailsPage.getResponsibleOfRelatedTaskAt(SICK_LEAVE_REQUEST_TASK));

    detailsPage.openTaskDelegateDialog(SICK_LEAVE_REQUEST_TASK);
    detailsPage.selectDelegateResponsible(TestRole.HR_ROLE, true);
    assertEquals(TestRole.HR_ROLE, detailsPage.getResponsibleOfRelatedTaskAt(SICK_LEAVE_REQUEST_TASK));
  }

  @Test
  public void testRelatedTaskDisplayDelegateButton() {
    createTestingTask();
    redirectToRelativeLink(GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage casePage = NavigationHelper.navigateToCaseList();
    detailsPage = casePage.openDetailsCase(LEAVE_REQUEST_CASE_NAME);
    assertFalse(detailsPage.isTaskDelegateOptionDisable(SICK_LEAVE_REQUEST_TASK));
    redirectToRelativeLink(DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL);
  }

  @Test
  public void testRelatedTaskOpenDetails() {
    createTestingTask();
    detailsPage.clickRelatedTaskActionButton(SICK_LEAVE_REQUEST_TASK);
    int index = detailsPage.getTaskRowIndexFromDetailPage(SICK_LEAVE_REQUEST_TASK);
    TaskDetailsPage taskDetailsPage = detailsPage.openTasksOfCasePageViaDetailsAction(index);
    WaitHelper.assertTrueWithWait(() -> TASK_DETAILS_TITLE.equals(taskDetailsPage.getPageTitle()));
  }

  @Test
  public void testRelatedCaseOpenDetails() {
    createTestingCaseContainTechnicalCases();
    detailsPage.clickRelatedCaseActionButton(0);
    CaseDetailsPage caseDetailsPage = detailsPage.openCasesOfCasePageViaDetailsAction(0);
    WaitHelper.assertTrueWithWait(() -> CASE_DETAILS_TITLE.equals(caseDetailsPage.getPageTitle()));
  }

  @Test
  public void testRelatedCaseOpenBusinessDetails() {
    createTestingCaseContainTechnicalCases();
    detailsPage.clickRelatedCaseActionButton(0);
    AdditionalCaseDetailsPage caseDetailsPage = detailsPage.openRelatedCaseBusinessDetail(0);
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((webDriver) -> caseDetailsPage.countBrowserTab() > 1);
    caseDetailsPage.switchLastBrowserTab();
    AdditionalCaseDetailsPage additionalCaseDetailsPage = new AdditionalCaseDetailsPage();
    WaitHelper.assertTrueWithWait(() -> BUSINESS_DETAILS_TITLE.equals(additionalCaseDetailsPage.getPageTitle()));
  }

  @Test
  public void testRelatedCaseSideSteps() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    newDashboardPage.waitPageLoaded();
    CaseWidgetNewDashBoardPage casePage = NavigationHelper.navigateToCaseList();
    detailsPage = casePage.openDetailsCase(BUSINESS_CASE_MAP_LEAVE_REQUEST);
    assertEquals(1, detailsPage.countRelatedCases());
    detailsPage.clickRelatedCaseActionButton(0);
    detailsPage.clickRelatedCaseSubmitLeaveReason(0);
    casePage = NavigationHelper.navigateToCaseList();
    detailsPage = casePage.openDetailsCase(BUSINESS_CASE_MAP_LEAVE_REQUEST);
    assertEquals(2, detailsPage.countRelatedCases());
    detailsPage.clickRelatedCaseActionButton(0);
    detailsPage.clickRelatedCaseUploadAdditionalDocument(0);
    casePage = NavigationHelper.navigateToCaseList();
    detailsPage = casePage.openDetailsCase(BUSINESS_CASE_MAP_LEAVE_REQUEST);
    assertEquals(3, detailsPage.countRelatedCases());
  }

  @Test
  public void testRelatedTaskOpenWorkflowEvents() {
    createTestingTask();
    detailsPage.clickRelatedTaskActionButton(SICK_LEAVE_REQUEST_TASK);
    int index = detailsPage.getTaskRowIndexFromDetailPage(SICK_LEAVE_REQUEST_TASK);
    detailsPage.openRelatedTaskWorkflowEvents(index);
    WaitHelper.assertTrueWithWait(() -> detailsPage.isRelatedTaskWorkflowEventsOpened());
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
    ScreenshotUtils.resizeBrowser(new Dimension(2560, 1440));
    detailsPage.relatedTaskListColumnShouldBeExist(RELATED_TASK_EXPIRY_COLUMN, true);
    detailsPage.relatedTaskListColumnShouldBeExist(RELATED_TASK_STATE_COLUMN, true);
    detailsPage.clickRelatedTaskColumnsButton();
    detailsPage.clickRelatedTaskDefaultCheckbox();
    detailsPage.clickRelatedTaskColumnCheckbox(6);
    detailsPage.clickRelatedTaskApplyButton();
    detailsPage.relatedTaskListColumnShouldBeExist(RELATED_TASK_EXPIRY_COLUMN, false);
    detailsPage.relatedTaskListColumnShouldBeExist(RELATED_TASK_STATE_COLUMN, true);
    detailsPage.clickRelatedTaskColumnsButton();
    detailsPage.clickRelatedTaskColumnCheckbox(6);
    detailsPage.clickRelatedTaskColumnCheckbox(8);
    detailsPage.clickRelatedTaskApplyButton();
    detailsPage.relatedTaskListColumnShouldBeExist(RELATED_TASK_EXPIRY_COLUMN, true);
    detailsPage.relatedTaskListColumnShouldBeExist(RELATED_TASK_STATE_COLUMN, false);
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
    newDashboardPage.waitPageLoaded();
    CaseWidgetNewDashBoardPage casePage = NavigationHelper.navigateToCaseList();
    detailsPage = casePage.openDetailsCase(BUSINESS_CASE_MAP_LEAVE_REQUEST);
    assertTrue(detailsPage.checkDoneTasksOfHistory());

    int relatedDoneTasks = detailsPage.countRelatedDoneTasks();
    detailsPage.showNoteHistory();
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((webDriver) -> detailsPage.countBrowserTab() > 1);
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
    assumeTrue(detailsPage.iframeCustomWidgetIsDisplayed(), "iframe CustomWidget is displayed");
    String processLink = detailsPage.getProcessLinkInCustomIFrameWidget();
    assertTrue(processLink.contains("portal-developer-examples/17A2C6D73AB4186E/startReview.ivp"));

    setupCaseDetailsWithIFrameURL();
    String url = detailsPage.getIFrameURLOfCustomWidget();
    assertTrue(url.contains("www.example.com"));

    setupCaseDetailsWith2Panels();
    assertTrue(detailsPage.isCustomMiddlePanelDisplay());
  }

  public void setupCaseDetailsWith2Panels() throws IOException {
    ConfigurationJsonUtils.updateJSONSetting("custom-case-details-with-panel.json", Variable.CASE_DETAIL);
    detailsPage = goToCaseList().openDetailsCase(CUSTOM_CASE_WIDGET_NAME);
  }

  public void setupCaseDetailsWithIFrameURL() throws IOException {
    ConfigurationJsonUtils.updateJSONSetting("custom-case-details-with-url.json", Variable.CASE_DETAIL);
    WaitHelper.waitForNavigation(() -> detailsPage = goToCaseList().openDetailsCase(CUSTOM_CASE_WIDGET_NAME));
  }

  public void setupCaseDetailsWithIFrameProcess() throws IOException {
    ConfigurationJsonUtils.updateJSONSetting("custom-case-details.json", Variable.CASE_DETAIL);
    detailsPage = goToCaseList().openDetailsCase(CUSTOM_CASE_WIDGET_NAME);
  }

  public CaseWidgetNewDashBoardPage goToCaseList() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    newDashboardPage = new NewDashboardPage();
    CaseWidgetNewDashBoardPage casePage = NavigationHelper.navigateToCaseList();
    return casePage;
  }

  @AfterEach
  public void teardown() {
    denySpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }

  @Test
  public void testShowRelatedCaseLinkInNote() {
    createTestingCaseContainTechnicalCases();
    detailsPage.getNumberOfHistory().shouldHave(size(1), DEFAULT_TIMEOUT);
    detailsPage.addNote("This is note on business case");
    detailsPage.getNumberOfHistory().shouldHave(size(2), DEFAULT_TIMEOUT);
    assertEquals("This is note on business case", detailsPage.getLatestHistoryContent());
    detailsPage.clickRelatedCaseActionButton(0);
    var relatedCaseDetailsPage = detailsPage.openCasesOfCasePageViaDetailsAction(0);
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((webDriver) -> CASE_DETAILS_TITLE.equals(relatedCaseDetailsPage.getPageTitle()));
    relatedCaseDetailsPage.addNote("The first note of sub-case");
    relatedCaseDetailsPage.addNote("The second note of sub-case");
    var subCaseId = relatedCaseDetailsPage.getCaseId();
    var caseName = relatedCaseDetailsPage.getCaseName();
    relatedCaseDetailsPage.getNumberOfHistory().shouldHave(size(2), DEFAULT_TIMEOUT);
    relatedCaseDetailsPage.getNumberOfHistoryForRelatedCaseLink().shouldHave(size(0), DEFAULT_TIMEOUT);
    detailsPage = relatedCaseDetailsPage.openBusinessCaseFromTechnicalCase();
    detailsPage.getNumberOfHistory().shouldHave(size(4), DEFAULT_TIMEOUT);
    detailsPage.getNumberOfHistoryForRelatedCaseLink().shouldHave(size(4), DEFAULT_TIMEOUT);
    var relaledCaseName = detailsPage.getContentOfHistoryTableRelatedCaseColumn(0);
    assertTrue(relaledCaseName.startsWith("#"));
    assertTrue(relaledCaseName.contains(subCaseId));
    assertTrue(relaledCaseName.contains(caseName));
  }

  @Test
  public void testShowRelatedCaseInfoByConfigInCaseHistory() {
    updateGlobalVariable(Variable.HIDE_RELATED_CASE_INFO_FROM_HISTORY.getKey(), "false");
    createTestingCaseContainTechnicalCases();
    assertTrue(detailsPage.isShowRelatedCaseCheckbox());
    detailsPage.clickOnRelatedCaseCheckbox(true);
    detailsPage.getRelatedCaseInfoColumn().shouldBe(Condition.appear);
    detailsPage.clickOnRelatedCaseCheckbox(false);
    detailsPage.getRelatedCaseInfoColumn().shouldBe(Condition.disappear);
    updateGlobalVariable(Variable.HIDE_RELATED_CASE_INFO_FROM_HISTORY.getKey(), "true");
    CaseWidgetNewDashBoardPage casePage = NavigationHelper.navigateToCaseList();
    detailsPage = casePage.openDetailsCase(ORDER_PIZZA);
    assertFalse(detailsPage.isShowRelatedCaseCheckbox());
  }
}
