package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jayway.awaitility.Awaitility;

import portal.guitest.common.BaseTest;
import portal.guitest.common.DateTimePattern;
import portal.guitest.common.TaskState;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskWidgetTest extends BaseTest {

  private static final String DISABLE_TASK_COUNT_SETTING = "DISABLE_TASK_COUNT";
  private static final String GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/grantOnlyDelegateOwnTasksPermission.ivp";
  private static final String DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/undoOnlyDelegateOwnTasksPermission.ivp";

  private TaskDetailsPage taskDetailsPage;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
  }

  @Test
  public void testEnterTaskDetailWhenClickOnTaskRowAndGoBack() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();

    taskWidgetPage.expand();
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    assertEquals("Task Details", taskDetailsPage.getPageTitle());

    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    assertEquals("Tasks", taskWidgetPage.getPageTitle());
  }
  
  @Test
  public void testEnterTaskDetailFromTaskActionAndGoBack() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();

    taskWidgetPage.expand();
    taskDetailsPage = taskWidgetPage.openTaskDetailsFromActionMenu(0);
    assertEquals("Task Details", taskDetailsPage.getPageTitle());

    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    assertEquals("Tasks", taskWidgetPage.getPageTitle());
  }

  @Test
  public void testOpenRelatedCaseOfTask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(0);

    String relatedCaseName = taskWidgetPage.getRelatedCase();
    CaseDetailsPage casePage = taskWidgetPage.openRelatedCaseOfTask();

    String caseName = casePage.getCaseName();
    assertTrue(relatedCaseName.contains(caseName));
  }

  @Test
  public void testReserveTask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.sideStepMenuOnActionButton(0);
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertEquals(TaskState.RESERVED, taskWidgetPage.getTaskState(0));
    taskWidgetPage.sideStepMenuOnActionButton(0);
    taskWidgetPage.resetTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertEquals(TaskState.OPEN, taskWidgetPage.getTaskState(0));
  }

  @Test
  public void testChangeTaskDeadline() {
    int firstTask = 0;
    LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
    String tomorrowStringLiteral = tomorrow.format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(firstTask);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    taskWidgetPage.changeExpiryOfTaskAt(tomorrowStringLiteral);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertEquals(tomorrowStringLiteral, taskWidgetPage.getExpiryOfTaskAt());
  }

  @Test
  public void testStartButtonStatus() {
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskWidgetPage.filterTasksInExpandedModeBy("Annual Leave Request");
    Assert.assertFalse(taskWidgetPage.isTaskStartEnabled(0));
    taskWidgetPage.filterTasksInExpandedModeBy("Sick Leave Request");
    Awaitility.waitAtMost(5, TimeUnit.SECONDS).until(() -> taskWidgetPage.isTaskStartEnabled(0));
  }

  @Test
  public void testDisplayDelegateButton() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL);
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    assertFalse(taskWidgetPage.isTaskDelegateOptionDisable(1));
    assertTrue(taskWidgetPage.isTaskDelegateOptionDisable(2));
    redirectToRelativeLink(DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL);
  }

  @Test
  public void testDisplayTaskAndCaseCategory() {
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(0);
    assertEquals("OtherLeave/Maternity", taskWidgetPage.getTaskCategory());
    assertEquals("LeaveRequest", taskWidgetPage.getCaseCategory());
  }

  @Test
  public void testShowTaskCount() { 
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.waitUntilTaskCountDifferentThanZero();
    assertEquals("In Dashboard, Task Count != 3", 3, taskWidgetPage.getTaskCount().intValue());
    taskWidgetPage.expand();
    taskWidgetPage.waitUntilTaskCountDifferentThanZero();
    assertEquals("In Task list, Task Count != 3", 3, taskWidgetPage.getTaskCount().intValue());
  }
  
  @Test
  public void testDisableTaskCount() {
    updatePortalSetting(DISABLE_TASK_COUNT_SETTING, "true");
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();

    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    assertEquals("In HomePage, Task Count is disabled", null, taskWidgetPage.getTaskCount());
    taskWidgetPage.expand();
    assertEquals("In Task list, Task Count is disabled", null, taskWidgetPage.getTaskCount());
  }

  @Test
  public void testBreadCrumb() {
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    assertEquals("Tasks (3)", taskWidgetPage.getTextOfCurrentBreadcrumb());
    taskWidgetPage.clickHomeBreadcrumb();
    homePage = new HomePage();
    assertEquals(true, homePage.isDisplayed());
  }

  @Test
  public void testBreadCrumbInTaskDetail() {
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    assertEquals("Task: Maternity Leave Request", taskDetailsPage.getTextOfCurrentBreadcrumb());

    taskDetailsPage.clickTaskListBreadCrumb();
    taskWidgetPage = new TaskWidgetPage();
    assertEquals(true, taskWidgetPage.isDisplayed());

    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    taskDetailsPage.clickHomeBreadcrumb();
    homePage = new HomePage();
    assertEquals(true, homePage.isDisplayed());
  }
}
