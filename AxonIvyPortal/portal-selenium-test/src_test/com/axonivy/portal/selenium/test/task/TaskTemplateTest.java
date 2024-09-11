package com.axonivy.portal.selenium.test.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.NoteHistoryPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.WorkingTaskDialogPage;
import com.codeborne.selenide.WebDriverRunner;

@IvyWebTest
public class TaskTemplateTest extends BaseTest {

  private static final String ANNUAL_LEAVE_REQUEST_TASK = "Annual Leave Request";
  private String createImpersistentTaskUrl = "portal-developer-examples/169BDE2F368D6EC4/ApplicationShowcase.ivp";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testCaseDetailsTabDisplayed() {
    createTestData();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    assertTrue(taskTemplatePage.containsCaseDetails());
  }

  @Test
  public void testAddingANote() {
    createTestData();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    assertEquals(0, taskTemplatePage.countNoteItems());
    taskTemplatePage.addNewNote("Sample note message");
    assertEquals(1, taskTemplatePage.countNoteItems());
  }

  @Test
  public void testOpeningRelatedTask() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestData();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();

    TaskDetailsPage taskDetailsPage = taskTemplatePage.openRelatedTaskInList(ANNUAL_LEAVE_REQUEST_TASK);
    assertEquals("Task: Annual Leave Request", taskDetailsPage.getTaskNameInDialog());

    taskDetailsPage.clickBackButton();
    WebDriverRunner.getWebDriver().switchTo().defaultContent();
    taskTemplatePage = new TaskTemplatePage();
    taskDetailsPage.switchToCaseInfoIframe();
    assertTrue(taskTemplatePage.countRelatedTasks() > 0);
  }

  @Test
  public void testOpeningDocumentUploading() {
    createTestData();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    taskTemplatePage.openDocumentUploadingDialog();
    assertTrue(taskTemplatePage.isDocumentUploadingDialogDisplayed());
  }

  @Test
  public void testLeaveWorkingTaskByClickingOnLogo() {
    createTestData();
    TaskTemplatePage taskTemplatePage = startATask();
    taskTemplatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.leaveTask();
    TaskWidgetPage taskWidget = NavigationHelper.navigateToTaskList();
    assertTrue(taskWidget.isTaskStateSuspended(0));
  }

  @Test
  public void testReserveWorkingTaskByClickingOnLogo() {
    redirectToRelativeLink(simplePaymentUrl);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    NewDashboardPage home = new NewDashboardPage();
    home.waitPageLoaded();
    TaskTemplatePage taskTemplatePage = startATask();
    taskTemplatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.reserveTask();
    TaskWidgetPage taskWidget = NavigationHelper.navigateToTaskList();
    assertTrue(taskWidget.isTaskStateReserved(0));
  }

  @Test
  public void testResetTaskWhenStartSideStep() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    int latestTask = taskWidgetPage.countTasks().size() - 1;
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(latestTask);
    taskTemplatePage.clickTaskActionMenu();
    taskTemplatePage.startSideStep();
    TaskWidgetPage taskWidget = NavigationHelper.navigateToTaskList();
    assertTrue(taskWidget.isTaskStateSuspended(0));
  }

  private void createTestData() {
    redirectToRelativeLink(createTestingTasksUrl);
    new NewDashboardPage();
  }

  private TaskTemplatePage startATask() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    return taskTemplatePage;
  }

  private TaskTemplatePage startATaskAndOpenCaseInfo() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.openCaseInfo();
    return taskTemplatePage;
  }

  @Test
  public void testShowCategoryColummnByDefault() {
    createTestData();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    TaskWidgetPage taskList = newDashboardPage.openTaskList();
    assertTrue(taskList.isCategoryColumnDisplayed());
  }

  @Test
  public void testOpeningFinishedTaskInHistoryArea() {
    createTestData();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    taskTemplatePage.openFinishedTaskInHistoryArea();
    taskTemplatePage.switchLastBrowserTab();
    NoteHistoryPage caseHistoryPage = new NoteHistoryPage();
    WaitHelper.assertTrueWithWait(() -> taskTemplatePage.countBrowserTab() > 1);
    int numberOfNotes = 0;
    numberOfNotes = caseHistoryPage.countNotes();
    assertEquals(1, numberOfNotes);
  }
  
  @Test
  public void testNotShowStartAdhocWhenOpenImpersistedTask() {
    redirectToRelativeLink(createImpersistentTaskUrl);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.clickTaskActionMenu();
    assertEquals(true, taskTemplatePage.isStartAdhocBtnNotExist());
  }
}
