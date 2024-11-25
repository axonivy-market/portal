package com.axonivy.portal.selenium.test.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.NoteHistoryPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskIFrameTemplatePage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.axonivy.portal.selenium.page.WorkingTaskDialogPage;

@IvyWebTest
public class TaskTemplateTest extends BaseTest {

  private static final String ANNUAL_LEAVE_REQUEST_TASK = "Annual Leave Request";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testCaseDetailsTabDisplayed() {
    createTestData();
    TaskIFrameTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    assertTrue(taskTemplatePage.containsCaseDetails());
  }

  @Test
  public void testAddingANote() {
    createTestData();
    TaskIFrameTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    assertEquals(0, taskTemplatePage.countNoteItems());
    taskTemplatePage.addNewNote("Sample note message");
    assertEquals(1, taskTemplatePage.countNoteItems());
  }

  @Test
  public void testOpeningRelatedTask() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestData();
    TaskIFrameTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();

    TaskDetailsPage taskDetailsPage = taskTemplatePage.openRelatedTaskInList(ANNUAL_LEAVE_REQUEST_TASK);
    assertEquals("Task: Annual Leave Request", taskDetailsPage.getTaskNameInDialog());

    taskDetailsPage.clickBackButton();
    assertTrue(taskTemplatePage.countRelatedTasks() > 0);
  }

  @Test
  public void testOpeningDocumentUploading() {
    createTestData();
    TaskIFrameTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    taskTemplatePage.openDocumentUploadingDialog();
    assertTrue(taskTemplatePage.isDocumentUploadingDialogDisplayed());
  }

  @Test
  public void testLeaveWorkingTaskByClickingOnLogo() {
    createTestData();
    TaskIFrameTemplatePage taskTemplatePage = startATask();
    taskTemplatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.leaveTask();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    assertTrue("open".equalsIgnoreCase(taskWidget.stateOfFirstTask().text()));
  }

  @Test
  public void testReserveWorkingTaskByClickingOnLogo() {
    redirectToRelativeLink(simplePaymentUrl);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    NewDashboardPage home = new NewDashboardPage();
    home.waitPageLoaded();
    TaskIFrameTemplatePage taskTemplatePage = startATask();
    taskTemplatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.reserveTask();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    assertTrue("open".equalsIgnoreCase(taskWidget.stateOfFirstTask().text()));
  }

  @Test
  public void testResetTaskWhenStartSideStep() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Case Map Leave Request");
    taskWidget.applyFilter();
    TaskTemplatePage taskTemplatePage = taskWidget.startTaskByIndex(0);
    taskTemplatePage.clickTaskActionMenu();
    taskTemplatePage.startSideStep();
    NavigationHelper.navigateToTaskList();
    taskWidget = new TopMenuTaskWidgetPage();
    assertTrue("open".equalsIgnoreCase(taskWidget.stateOfFirstTask().text()));
  }

  private void createTestData() {
    redirectToRelativeLink(createTestingTasksUrl);
    new NewDashboardPage();
  }

  private TaskIFrameTemplatePage startATask() {
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    TaskIFrameTemplatePage taskTemplatePage = taskWidget.startTaskIFrameByIndex(0);
    return taskTemplatePage;
  }

  private TaskIFrameTemplatePage startATaskAndOpenCaseInfo() {
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    TaskIFrameTemplatePage taskTemplatePage = taskWidget.startTaskIFrameByIndex(0);
    taskTemplatePage.openCaseInfo();
    return taskTemplatePage;
  }

  @Test
  public void testOpeningFinishedTaskInHistoryArea() {
    createTestData();
    TaskIFrameTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    taskTemplatePage.openFinishedTaskInHistoryArea();
    taskTemplatePage.switchLastBrowserTab();
    NoteHistoryPage caseHistoryPage = new NoteHistoryPage();
    WaitHelper.assertTrueWithWait(() -> taskTemplatePage.countBrowserTab() > 1);
    int numberOfNotes = 0;
    numberOfNotes = caseHistoryPage.countNotes();
    assertEquals(1, numberOfNotes);
  }
}
