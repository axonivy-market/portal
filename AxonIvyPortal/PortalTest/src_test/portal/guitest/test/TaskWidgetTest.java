package portal.guitest.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.DateTimePattern;
import portal.guitest.common.TaskState;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskWidgetTest extends BaseTest {

  private static final String GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/grantOnlyDelegateOwnTasksPermission.ivp";
  private static final String DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/undoOnlyDelegateOwnTasksPermission.ivp";

  @Override
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testShowHideTaskDetailOnExpandedMode() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();

    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(0);
    assertTrue(taskWidgetPage.isTaskShowDetails(0));

    taskWidgetPage.closeTaskDetails(0);
    assertFalse(taskWidgetPage.isTaskShowDetails(0));
  }

  @Test
  public void testTasksInPortalHomePageUpdatedAfterExpandToFullMode() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(3, taskWidgetPage.countTasks());

    taskWidgetPage.createTestingTasksInNewWindow();
    taskWidgetPage.expand();
    assertEquals(6, taskWidgetPage.countTasks());
  }

  @Test
  public void testOpenRelatedCaseOfTask() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(0);

    String relatedCaseName = taskWidgetPage.getRelatedCase();
    CaseWidgetPage casePage = taskWidgetPage.openRelatedCaseOfTask();

    String caseName = casePage.getCaseName();
    assertEquals(relatedCaseName, caseName);
  }

  @Test
  public void testReserveTask() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertEquals(TaskState.RESERVED, taskWidgetPage.getTaskState(0));
    taskWidgetPage.resetTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertEquals(TaskState.OPEN, taskWidgetPage.getTaskState(0));
  }

  @Test
  public void testChangeTaskDeadline() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    int firstTask = 0;
    LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
    String tomorrowStringLiteral = tomorrow.format(DateTimeFormatter.ofPattern(DateTimePattern.DATE_TIME_PATTERN));

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(firstTask);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    taskWidgetPage.changeExpiryOfTaskAt(firstTask, tomorrowStringLiteral);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertEquals(tomorrowStringLiteral, taskWidgetPage.getExpiryOfTaskAt(firstTask));
  }

  @Test
  public void testStartButtonStatus() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskWidgetPage.filterTasksInExpendedModeBy("Annual Leave Request");
    Assert.assertFalse(taskWidgetPage.isTaskStartEnabled(0));
    taskWidgetPage.filterTasksInExpendedModeBy("Sick Leave Request");
    Assert.assertTrue(taskWidgetPage.isTaskStartEnabled(0));
  }

  @Test
  public void testDisplayDelegateButton() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
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
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.openTaskList();
    taskWidgetPage.openTaskDetails(0);
    TaskDetailsPage taskDetailsPage = taskWidgetPage.getTaskDetailsElement(0);
    assertEquals("OtherLeave/Maternity", taskDetailsPage.getTaskCategory());
    assertEquals("LeaveRequest", taskDetailsPage.getCaseCategory());
  }
  
  @Test
  public void testShowTaskCount() {
  TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
  Assert.assertEquals("In Dashboard, Task Count != 3", 3, taskWidgetPage.getTaskCount());
  taskWidgetPage.openTaskList();
  Assert.assertEquals("In Task list, Task Count != 3", 3, taskWidgetPage.getTaskCount());
  }
}
