package com.axonivy.portal.selenium.test.task;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TaskState;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.TestRole;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.UserProfilePage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class TaskWidgetTest extends BaseTest {

  private static final String DISABLE_TASK_COUNT_SETTING = Variable.DISABLE_TASK_COUNT.getKey();
  private static final String GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/grantOnlyDelegateOwnTasksPermission.ivp";
  private static final String DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/undoOnlyDelegateOwnTasksPermission.ivp";
  private static final String DENY_DESTROY_TASK_URL =
      "portalKitTestHelper/14DE09882B540AD5/denyDestroyTaskPermission.ivp";
  private static final String GRANT_DESTROY_TASK_URL =
      "portalKitTestHelper/14DE09882B540AD5/grantDestroyTaskPermission.ivp";

  private static final String MATERNITY_LEAVE_REQUEST = "Maternity Leave Request";

  private TaskDetailsPage taskDetailsPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
  }

  @Test
  public void testOpenRelatedCaseOfTask() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.openTaskDetails(0);

    CaseDetailsPage casePage = taskWidgetPage.openRelatedCaseOfTask();
    String caseName = casePage.getCaseName();
    casePage.checkCaseName(caseName);
  }

  @Test
  public void testReserveTask() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.checkNameOfTaskAt(0, MATERNITY_LEAVE_REQUEST);
    taskWidgetPage.sideStepMenuOnActionButton(0);
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.isReserveLinkDisabled(0);
    taskWidgetPage.checkTaskState(0, TaskState.OPEN.getValue());

    taskWidgetPage.clickOnTaskStatesAndApply(List.of("Reserved"));
    taskWidgetPage.checkNameOfTaskAt(0, MATERNITY_LEAVE_REQUEST);

    taskWidgetPage.sideStepMenuOnActionButton(0);
    taskWidgetPage.resetTask(0);
    assertTrue(taskWidgetPage.isResetLinkDisabled(0));

    taskWidgetPage.checkTaskState(0, TaskState.OPEN.getValue());

    taskWidgetPage.clickOnTaskStatesAndApply(List.of("Reserved"));
    taskWidgetPage.countTasks().shouldHave(CollectionCondition.size(0));

    taskWidgetPage.clickOnTaskStatesAndApply(List.of("Suspended"));
    taskWidgetPage.checkNameOfTaskAt(0, MATERNITY_LEAVE_REQUEST);
  }

  @Test
  public void testStartButtonStatus() {
    login(TestAccount.ADMIN_USER);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();

    taskWidgetPage.filterTasksBy("Annual Leave Request");
    taskWidgetPage.waitTillNameOfFirstTaskToBe("Annual Leave Request");
    taskWidgetPage.isTaskDisabled(0);

    taskWidgetPage.filterTasksBy("Sick Leave Request");
    taskWidgetPage.waitTillNameOfFirstTaskToBe("Sick Leave Request");
  }

  @Test
  public void testDisplayDelegateButton() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();

    taskWidgetPage.filterTasksBy("Sick Leave Request");
    taskWidgetPage.waitTillNameOfFirstTaskToBe("Sick Leave Request");
    taskWidgetPage.isTaskDelegationEnabled(0);

    taskWidgetPage.filterTasksBy("Annual Leave Request");
    taskWidgetPage.waitTillNameOfFirstTaskToBe("Annual Leave Request");

    redirectToRelativeLink(DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL);
  }

  @Test
  public void testDestroyTask() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(GRANT_DESTROY_TASK_URL);

    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksBy("Annual Leave Request");
    taskWidgetPage.waitTillNameOfFirstTaskToBe("Annual Leave Request");

    taskWidgetPage.isTaskDestroyEnabled(0);
    taskWidgetPage.destroyTask(0);
    taskWidgetPage.confimDestruction();
    taskWidgetPage.checkTaskState(0, TaskState.DESTROYED.getValue());

    redirectToRelativeLink(DENY_DESTROY_TASK_URL);
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
  public void testDisplayTaskAndCaseCategory() {
    login(TestAccount.ADMIN_USER);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.openTaskDetails(0);
    taskWidgetPage.getTaskCategory().shouldHave(Condition.text("Other Leave/Maternity"));
    taskWidgetPage.getCaseCategory().shouldHave(Condition.text("Leave Request"));
  }

  @Test
  public void testShowTaskCount() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.waitUntilTaskCountDifferentThanZero();
    assertEquals(3, taskWidgetPage.getTaskCount().intValue(), "In Task list, Task Count != 3");
  }

  @Test
  public void testDisableTaskCount() {
    updatePortalSetting(DISABLE_TASK_COUNT_SETTING, "true");
    login(TestAccount.ADMIN_USER);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    assertEquals(null, taskWidgetPage.getTaskCount(), "In Task list, Task Count is disabled");
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
  public void testDelegateTask() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    assertEquals(TestRole.EVERYBODY_ROLE, taskWidgetPage.getResponsibleOfTaskAt(0));
    taskWidgetPage.openTaskDelegateDialog(0);
    taskWidgetPage.isDelegateTypeSelectAvailable();
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
    assertEquals("low", taskWidgetPage.getPriorityOfTask(taskWidgetPage.countTasks().size() - 1));

    // Change sorting options
    userProfilePage = taskWidgetPage.openMyProfilePage();
    userProfilePage.selectTaskSortField("Name");
    userProfilePage.selectTaskSortDirection("Sort descending");
    newDashboardPage = userProfilePage.save();

    // Check result
    taskWidgetPage = newDashboardPage.openTaskList();
    assertEquals("Sick Leave Request", taskWidgetPage.getNameOfTaskAt(0));
    assertEquals("Annual Leave Request", taskWidgetPage.getNameOfTaskAt(taskWidgetPage.countTasks().size() - 1));
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
  
  
  @Test
  public void testStartATaskAtHomePage() {
    updateLegacyUIConfiguration();
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestingCaseMapUrl);
    HomePage homePage = new HomePage();
    redirectToRelativeLink(simplePaymentUrl);
    final String NEW_PAYMENT = "Do New Payment";
    final String LEAVE_REQUEST = "Case Map Leave Request";

    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    //Start first task
    assertFalse(taskWidgetPage.isResumedTask(0));
    taskWidgetPage.startTaskInLegacyMode(0);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.clickCancelAndLeftButton();
    
    //First task is resumed
    taskWidgetPage = homePage.getTaskWidget();
    assertTrue(taskWidgetPage.isResumedTask(0));
    taskWidgetPage.startTaskInLegacyMode(0);
    taskWidgetPage.resetResumedTask();
    taskTemplatePage = new TaskTemplatePage();
    assertEquals(NEW_PAYMENT, taskTemplatePage.getTaskName());
    taskTemplatePage.clickCancelAndLeftButton();
    
    taskWidgetPage = homePage.getTaskWidget();
    //Start second task
    assertFalse(taskWidgetPage.isResumedTask(1));
    taskWidgetPage.startTaskInLegacyMode(1);
    taskTemplatePage.waitForMainAreaPanelRendered();
    assertEquals(LEAVE_REQUEST, taskTemplatePage.getTaskName());
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    taskTemplatePage = new TaskTemplatePage();

    //Second task is resumed
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.waitForPageLoad();
    assertTrue(taskWidgetPage.isResumedTask(1));
    taskWidgetPage.startTaskInLegacyMode(1);
    taskWidgetPage.resetResumedTask();
    taskTemplatePage = new TaskTemplatePage();
    assertEquals(LEAVE_REQUEST, taskTemplatePage.getTaskName());
  }

}
