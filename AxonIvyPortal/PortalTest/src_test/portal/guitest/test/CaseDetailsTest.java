package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.workflow.TaskState;
import portal.guitest.common.BaseTest;
import portal.guitest.common.CaseState;
import portal.guitest.common.TestAccount;
import portal.guitest.common.TestRole;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskTemplatePage;

public class CaseDetailsTest extends BaseTest {

  private static final String RELATED_TASK_STATE_COLUMN = "related-task-state-column";
  private static final String RELATED_TASK_EXPIRY_COLUMN = "related-task-expiry-column";
  private static final String RELATED_CASE_STATE_COLUMN = "state-column";
  private static final String RELATED_CASE_CREATED_COLUMN = "created-column";
  private HomePage homePage;
  private CaseDetailsPage detailsPage;

  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/grantOnlyDelegateOwnTasksPermission.ivp";
  private static final String DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/undoOnlyDelegateOwnTasksPermission.ivp";

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);

    homePage = new HomePage();
    grantSpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(LEAVE_REQUEST_CASE_NAME);
  }
  
  @Test
  public void testDestroyCase() {
    detailsPage.onClickDestroyCase();
    detailsPage.confimDestruction();
    CaseWidgetPage casePage = new CaseWidgetPage();
    CaseState caseState = casePage.getCaseState(0);
    assertEquals(CaseState.DESTROYED, caseState);
  }

  @Test
  public void testDisplayCaseProperties() {
    assertTrue(StringUtils.equalsIgnoreCase("LeaveRequest", detailsPage.getCaseCategory()));
    assertTrue(StringUtils.isNotBlank(detailsPage.getCaseDuration()));
  }

  @Test
  public void testAddCaseNote() {
    assertEquals(1, detailsPage.getNumberOfHistory());
    detailsPage.addNote("Consider the remaining annual leaves before the approval");
    assertEquals(2, detailsPage.getNumberOfHistory());
    assertEquals("Consider the remaining annual leaves before the approval", detailsPage.getLatestHistoryContent());
  }

  @Test
  public void testShowCaseDetail() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(ORDER_PIZZA);
    assertTrue(detailsPage.isGeneralInformationComponentPresented());
    assertTrue(detailsPage.isRelatedCasesComponentPresented());
    assertTrue(detailsPage.isRelatedTasksComponentPresented());
    assertTrue(detailsPage.isHistoryComponentPresented());
    assertTrue(detailsPage.isDocumentComponentPresented());
  }

  @Test
  public void testRelatedTaskStartButtonStatus() {
    assertFalse(detailsPage.isRelatedTaskStartEnabled(1));
    assertTrue(detailsPage.isRelatedTaskStartEnabled(2));
  }

  @Test
  public void testRelatedTaskStartTask() {
    TaskTemplatePage taskTemplate = detailsPage.startRelatedTask(2);
    assertEquals("Sick Leave Request", taskTemplate.getTaskName());
  }

  @Test
  public void testRelatedTaskReserveTask() {
    detailsPage.sideStepMenuOnActionButton(2);
    detailsPage.reserveTask(2);
    detailsPage.waitAjaxIndicatorDisappear();
    assertTrue(detailsPage.isTaskState(2, TaskState.PARKED));

    detailsPage.sideStepMenuOnActionButton(2);
    detailsPage.resetTask(2);
    detailsPage.waitAjaxIndicatorDisappear();
    assertTrue(detailsPage.isTaskState(2, TaskState.SUSPENDED));
  }

  @Test
  public void testRelatedTaskDestroyTask() {
    detailsPage.sideStepMenuOnActionButton(2);
    Assert.assertTrue(detailsPage.isRelatedTaskDestroyEnabled(2));
    detailsPage.destroyTask(2);
    detailsPage.confimRelatedTaskDestruction();
    detailsPage.waitAjaxIndicatorDisappear();
    assertTrue(detailsPage.isTaskState(2, TaskState.DESTROYED));
  }

  @Test
  public void testRelatedTaskDelegateTask() {
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
    detailsPage.sideStepMenuOnActionButton(0);
    TaskDetailsPage taskDetailsPage = detailsPage.openTasksOfCasePageViaDetailsAction(0);
    assertEquals("Task Details", taskDetailsPage.getPageTitle());
  }

  @Test
  public void testRelatedTaskOpenWorkflowEvents() {
    detailsPage.sideStepMenuOnActionButton(0);
    detailsPage.openRelatedTaskWorkflowEvents(0);
    assertTrue(detailsPage.isRelatedTaskWorkflowEventsOpened());
  }

  @Test
  public void testRelatedTaskAddAdHocTask() {
    detailsPage.sideStepMenuOnActionButton(2);
    ExpressProcessPage expressProcessPage = detailsPage.addAdHocTask(2);
    assertTrue(expressProcessPage.getProcessName().endsWith("Sick Leave Request"));
  }

  @Test
  public void testRelatedTaskExportToExcel() {
    detailsPage.clickExportToExcelLink("related-task-export-to-excel", "related-task-status-dialog");
    assertTrue(detailsPage.isDownloadCompleted("related-task-status-dialog"));
  }

  @Test
  public void testRelatedCaseExportToExcel() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(ORDER_PIZZA);
    detailsPage.clickExportToExcelLink("related-case-export-to-excel", "related-case-status-dialog");
    assertTrue(detailsPage.isDownloadCompleted("related-case-status-dialog"));
  }

  @Test
  public void testRelatedCaseEnableAndDisableColumns() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(ORDER_PIZZA);
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

  @Test
  public void testRelatedTaskEnableAndDisableColumns() {
    assertTrue(detailsPage.isRelatedTaskListColumnExist(RELATED_TASK_EXPIRY_COLUMN));
    assertTrue(detailsPage.isRelatedTaskListColumnExist(RELATED_TASK_STATE_COLUMN));
    detailsPage.clickRelatedTaskColumnsButton();
    detailsPage.clickRelatedTaskDefaultCheckbox();
    detailsPage.clickRelatedTaskColumnCheckbox(6);
    detailsPage.clickRelatedTaskApplyButton();
    //WaitHelper.assertTrueWithWait(() -> !detailsPage.isRelatedTaskListColumnExist(RELATED_TASK_EXPIRY_COLUMN));
    WaitHelper.assertTrueWithWait(() -> detailsPage.isRelatedTaskListColumnExist(RELATED_TASK_STATE_COLUMN));
    detailsPage.clickRelatedTaskColumnsButton();
    detailsPage.clickRelatedTaskColumnCheckbox(6);
    detailsPage.clickRelatedTaskColumnCheckbox(7);
    detailsPage.clickRelatedTaskApplyButton();
    WaitHelper.assertTrueWithWait(() -> detailsPage.isRelatedTaskListColumnExist(RELATED_TASK_EXPIRY_COLUMN));
    //WaitHelper.assertTrueWithWait(() -> !detailsPage.isRelatedTaskListColumnExist(RELATED_TASK_STATE_COLUMN));
  }

  @Test
  public void testHistoryAuthorIsUserFullName() {
    detailsPage.addNote("Sample case note");
    assertEquals(TestAccount.ADMIN_USER.getFullName(), detailsPage.getHistoryAuthor());
  }

  @Test
  public void testOpenViewNoteDialog() {
    detailsPage.addNote("Consider the remaining annual leaves before the approval");
    detailsPage.clickViewNote();
    assertTrue(detailsPage.isViewNoteDialogPresented());
  }
  
  @Test
  public void testDragDropWidgets() {
    detailsPage.switchToEditMode();
    detailsPage.waitForSaveButtonDisplayed();
    detailsPage.drapAndDropWidgets("information", "document");
    detailsPage.drapAndDropWidgets("document", "information");
    detailsPage.saveAndSwitchToViewMode();
    detailsPage.switchToEditMode();
    detailsPage.waitForResetButtonDisplayed();
  }

  @After
  public void teardown() {
    denySpecificPortalPermission(PortalPermission.TASK_CASE_ADD_NOTE);
  }
}
