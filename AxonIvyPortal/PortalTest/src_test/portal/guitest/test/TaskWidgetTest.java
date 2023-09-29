package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static portal.guitest.common.Variable.DISABLE_TASK_COUNT;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.common.TaskState;
import portal.guitest.common.TestAccount;
import portal.guitest.common.TestRole;
import portal.guitest.common.Variable;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.UserProfilePage;

public class TaskWidgetTest extends BaseTest {

  private static final String DISABLE_TASK_COUNT_SETTING = DISABLE_TASK_COUNT.getKey();
  private static final String GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/grantOnlyDelegateOwnTasksPermission.ivp";
  private static final String DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/undoOnlyDelegateOwnTasksPermission.ivp";
  private static final String DENY_DESTROY_TASK_URL = "portalKitTestHelper/14DE09882B540AD5/denyDestroyTaskPermission.ivp";
  private static final String GRANT_DESTROY_TASK_URL = "portalKitTestHelper/14DE09882B540AD5/grantDestroyTaskPermission.ivp";

  private static final String MATERNITY_LEAVE_REQUEST = "Maternity Leave Request";

  private TaskDetailsPage taskDetailsPage;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
  }

  @Test
  public void testOpenRelatedCaseOfTask() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.openTaskDetails(0);

    String relatedCaseName = taskWidgetPage.getRelatedCase();
    CaseDetailsPage casePage = taskWidgetPage.openRelatedCaseOfTask();

    String caseName = casePage.getCaseName();
    assertTrue(relatedCaseName.contains(caseName));
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testReserveTask() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.sideStepMenuOnActionButton(0);
    assertEquals(MATERNITY_LEAVE_REQUEST, taskWidgetPage.getNameOfTaskAt(0));
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertEquals(TaskState.OPEN, taskWidgetPage.getTaskState(0));
    taskWidgetPage.clickOnTaskStatesAndApply(List.of("Reserved"));
    assertEquals(MATERNITY_LEAVE_REQUEST, taskWidgetPage.getNameOfTaskAt(0));
    taskWidgetPage.sideStepMenuOnActionButton(0);
    taskWidgetPage.resetTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertEquals(TaskState.OPEN, taskWidgetPage.getTaskState(0));
    taskWidgetPage.clickOnTaskStatesAndApply(List.of("Reserved"));
    assertEquals(0, taskWidgetPage.countTasks());
    taskWidgetPage.clickOnTaskStatesAndApply(List.of("Suspended"));
    assertEquals(MATERNITY_LEAVE_REQUEST, taskWidgetPage.getNameOfTaskAt(0));
  }

  @Test
  public void testStartButtonStatus() {
    login(TestAccount.ADMIN_USER);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy("Annual Leave Request");
    WaitHelper.assertTrueWithWait(() -> !taskWidgetPage.isTaskStartEnabled(0));
    taskWidgetPage.filterTasksInExpandedModeBy("Sick Leave Request");
    WaitHelper.assertTrueWithWait(() -> taskWidgetPage.isTaskStartEnabled(0));
  }

  @Test
  public void testDisplayDelegateButton() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    assertFalse(taskWidgetPage.isTaskDelegateOptionDisable("Sick Leave Request"));
    assertTrue(taskWidgetPage.isTaskDelegateOptionDisable("Annual Leave Request"));
    redirectToRelativeLink(DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL);
  }

  @Test
  public void testDestroyTask() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(GRANT_DESTROY_TASK_URL);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy("Annual Leave Request");
    taskWidgetPage.sideStepMenuOnActionButton(0);
    Assert.assertTrue(taskWidgetPage.isTaskDestroyEnabled(0));
    taskWidgetPage.destroyTask(0);
    taskWidgetPage.confimDestruction();
    assertEquals(TaskState.DESTROYED, taskWidgetPage.getTaskState(0));
    redirectToRelativeLink(DENY_DESTROY_TASK_URL);
  }

  @Test
  public void testDisplayTaskAndCaseCategory() {
    login(TestAccount.ADMIN_USER);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.openTaskDetails(0);
    assertEquals("Other Leave/Maternity", taskWidgetPage.getTaskCategory());
    assertEquals("Leave Request", taskWidgetPage.getCaseCategory());
  }

  @Test
  public void testShowTaskCount() { 
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.waitUntilTaskCountDifferentThanZero();
    assertEquals("In Task list, Task Count != 3", 3, taskWidgetPage.getTaskCount().intValue());
  }

  @Test
  public void testDisableTaskCount() {
    updatePortalSetting(DISABLE_TASK_COUNT_SETTING, "true");
    login(TestAccount.ADMIN_USER);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    assertEquals("In Task list, Task Count is disabled", null, taskWidgetPage.getTaskCount());
  }

  @Test
  public void testBreadCrumb() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    assertEquals("Tasks (3)", taskWidgetPage.getTextOfCurrentBreadcrumb());
    taskWidgetPage.goToHomeFromBreadcrumb();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    assertEquals(true, newDashboardPage.isDisplayed());
  }

  @Test
  public void testBreadCrumbInTaskDetail() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    assertEquals("Task: Maternity Leave Request", taskDetailsPage.getTextOfCurrentBreadcrumb());

    taskDetailsPage.clickTaskListBreadCrumb();
    taskWidgetPage = new TaskWidgetPage();
    assertEquals(true, taskWidgetPage.isDisplayed());

    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    taskDetailsPage.goToHomeFromBreadcrumb();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    assertEquals(true, newDashboardPage.isDisplayed());
  }
  
  @Test
  public void testDelegateTask() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    assertEquals(TestRole.EVERYBODY_ROLE, taskWidgetPage.getResponsibleOfTaskAt(0));
    taskWidgetPage.openTaskDelegateDialog(0);
    WaitHelper.assertTrueWithWait(() -> taskWidgetPage.isDelegateTypeSelectAvailable());
    taskWidgetPage.selectDelegateResponsible(TestAccount.HR_ROLE_USER.getFullName(), false);
    assertEquals(TestAccount.HR_ROLE_USER.getFullName(), taskWidgetPage.getResponsibleOfTaskAt(0));
    
    taskWidgetPage.openTaskDelegateDialog(0);
    taskWidgetPage.selectDelegateResponsible(TestRole.HR_ROLE, true);
    assertEquals(TestRole.HR_ROLE, taskWidgetPage.getResponsibleOfTaskAt(0));
  }

  @Test
  public void testChangeTaskSortingOptions() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    UserProfilePage userProfilePage = newDashboardPage.openMyProfilePage();

    // Change sorting options
    userProfilePage.selectTaskSortField("Priority");
    userProfilePage.selectTaskSortDirection("Sort ascending");
    newDashboardPage = userProfilePage.save();

    // Check result
    TaskWidgetPage taskWidgetPage = newDashboardPage.openTaskList();
    assertEquals("high", taskWidgetPage.getPriorityOfTask(0));
    assertEquals("low", taskWidgetPage.getPriorityOfTask(taskWidgetPage.countTasks() - 1));

    // Change sorting options
    userProfilePage = taskWidgetPage.openMyProfilePage();
    userProfilePage.selectTaskSortField("Name");
    userProfilePage.selectTaskSortDirection("Sort descending");
    newDashboardPage = userProfilePage.save();

    // Check result
    taskWidgetPage = newDashboardPage.openTaskList();
    assertEquals("Sick Leave Request", taskWidgetPage.getNameOfTaskAt(0));
    assertEquals("Annual Leave Request", taskWidgetPage.getNameOfTaskAt(taskWidgetPage.countTasks() - 1));
  }

  @Test
  public void testExportToExcel() {
    login(TestAccount.ADMIN_USER);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();

    taskWidgetPage.clickExportToExcelLink();

    assertTrue(taskWidgetPage.isDownloadCompleted());
  }

  @Test
  public void testStickySortTaskList() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.sortTaskListByColumn("Name / Description", 0, "task-name", "Annual Leave Request");
    String taskName = taskWidgetPage.getTaskListCustomCellValue(0, "task-name");
    assertTrue(StringUtils.equalsIgnoreCase("Annual Leave Request", taskName));
    taskWidgetPage.sortTaskListByColumn("Name / Description", 0, "task-name", "Sick Leave Request");
    taskWidgetPage.clickOnLogo();
    new NewDashboardPage();
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskName = taskWidgetPage.getTaskListCustomCellValue(0, "task-name");
    assertTrue(StringUtils.equalsIgnoreCase("Sick Leave Request", taskName));

    UserProfilePage userProfilePage = taskWidgetPage.openMyProfilePage();
    userProfilePage.selectTaskSortField("Priority");
    userProfilePage.selectTaskSortDirection("Sort ascending");
    NewDashboardPage newDashboardPage = userProfilePage.save();

    taskWidgetPage = newDashboardPage.openTaskList();
    assertEquals("high", taskWidgetPage.getPriorityOfTask(0));
  }

}
