package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static portal.guitest.common.Variable.DISABLE_TASK_COUNT;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TaskState;
import portal.guitest.common.TestAccount;
import portal.guitest.common.TestRole;
import portal.guitest.common.Variable;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskTemplatePage;
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
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(0);

    String relatedCaseName = taskWidgetPage.getRelatedCase();
    CaseDetailsPage casePage = taskWidgetPage.openRelatedCaseOfTask();

    String caseName = casePage.getCaseName();
    assertTrue(relatedCaseName.contains(caseName));
  }
  

  @SuppressWarnings("deprecation")
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
    assertEquals(TaskState.SUSPENDED, taskWidgetPage.getTaskState(0));
  }
  
  //////////////////////////
  
  @Test
  public void testStartButtonStatus() {
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskWidgetPage.filterTasksInExpandedModeBy("Annual Leave Request");
    WaitHelper.assertTrueWithWait(() -> !taskWidgetPage.isTaskStartEnabled(0));
    taskWidgetPage.filterTasksInExpandedModeBy("Sick Leave Request");
    WaitHelper.assertTrueWithWait(() -> taskWidgetPage.isTaskStartEnabled(0));
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
  public void testDestroyTask() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(GRANT_DESTROY_TASK_URL);
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
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
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(0);
    assertEquals("Other Leave/Maternity", taskWidgetPage.getTaskCategory());
    assertEquals("Leave Request", taskWidgetPage.getCaseCategory());
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
//    updatePortalSetting(DISABLE_TASK_COUNT_SETTING, "true");
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
    taskWidgetPage.goToHomeFromBreadcrumb();
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
    taskDetailsPage.goToHomeFromBreadcrumb();
    homePage = new HomePage();
    assertEquals(true, homePage.isDisplayed());
  }
  
  @Test
  public void testDelegateTask() {
    login(TestAccount.HR_ROLE_USER);
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
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
  public void testStartATaskAtHomePage() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestingCaseMapUrl);
    HomePage homePage = new HomePage();
    homePage.waitForPageLoaded();
    redirectToRelativeLink(simplePaymentUrl);
    final String NEW_PAYMENT = "Do New Payment";
    final String LEAVE_REQUEST = "Case Map Leave Request";

    homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    //Start first task
    assertFalse(taskWidgetPage.isResumedTask(0));
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    homePage = taskTemplatePage.clickCancelAndLeftButton();
    
    //First task is resumed
    taskWidgetPage = homePage.getTaskWidget();
    assertTrue(taskWidgetPage.isResumedTask(0));
    taskTemplatePage = taskWidgetPage.startTask(0);
    assertEquals(NEW_PAYMENT, taskTemplatePage.getTaskName());
    homePage = taskTemplatePage.clickCancelAndLeftButton();
    
    taskWidgetPage = homePage.getTaskWidget();
    //Start second task
    assertFalse(taskWidgetPage.isResumedTask(1));
    taskTemplatePage = taskWidgetPage.startTask(1);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();

    //Second task is resumed
    taskWidgetPage = homePage.getTaskWidget();
    assertTrue(taskWidgetPage.isResumedTask(1));
    taskTemplatePage = taskWidgetPage.startTask(1);
    assertEquals(LEAVE_REQUEST, taskTemplatePage.getTaskName());
  }

  @Test
  public void testChangeTaskSortingOptions() {
    HomePage homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();

    // Change sorting options
    userProfilePage.selectTaskSortField("Priority");
    userProfilePage.selectTaskSortDirection("Sort ascending");
    homePage = userProfilePage.save();

    // Check result
    TaskWidgetPage taskWidgetPage = homePage.openTaskList();
    assertEquals("high", taskWidgetPage.getPriorityOfTask(0));
    assertEquals("low", taskWidgetPage.getPriorityOfTask(taskWidgetPage.countTasks() - 1));

    // Change sorting options
    userProfilePage = taskWidgetPage.openMyProfilePage();
    userProfilePage.selectTaskSortField("Name");
    userProfilePage.selectTaskSortDirection("Sort descending");
    homePage = userProfilePage.save();

    // Check result
    taskWidgetPage = homePage.openTaskList();
    assertEquals("Sick Leave Request", taskWidgetPage.getNameOfTaskAt(0));
    assertEquals("Annual Leave Request", taskWidgetPage.getNameOfTaskAt(taskWidgetPage.countTasks() - 1));
  }

  @Test
  public void testExportToExcel() {
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();

    taskWidgetPage.clickExportToExcelLink();

    assertTrue(taskWidgetPage.isDownloadCompleted());
  }

  @Test
  public void testStickySortTaskList() {
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    // Sort task on Dashboard
    taskWidgetPage.openCompactSortMenu();
    taskWidgetPage.selectCompactSortByName("Expiry (Newest first)", 0, "Maternity Leave Request");
    // Navigate around Portal
    CaseWidgetPage caseWidgetPage = taskWidgetPage.openCaseList();
    // Check result at full Task List
    taskWidgetPage = caseWidgetPage.openTaskList();
    String selectedSortColumn = taskWidgetPage.getSelectedSortColumn();
    assertTrue(StringUtils.equalsIgnoreCase("Expiry", selectedSortColumn));
    String taskName = taskWidgetPage.getTaskListCustomCellValue(0, "task-name");
    assertTrue(StringUtils.equalsIgnoreCase("Maternity Leave Request", taskName));
    // Change to another column - which is not include at compact task list
    taskWidgetPage.sortTaskListByColumn("Name / Description", 0, "task-name", "Annual Leave Request");
    // Back to Dashboard - compact task list will sort by default column
    taskWidgetPage.clickOnLogo();
    // Create new task
    createTestingTasks();
    homePage = new HomePage();
    taskWidgetPage = homePage.getTaskWidget();
    selectedSortColumn = taskWidgetPage.getSelectedCompactSortLable();
    assertTrue(StringUtils.equalsIgnoreCase("Creation date (Newest first)", selectedSortColumn));
    // Change User sort selection
    UserProfilePage userProfilePage = taskWidgetPage.openMyProfilePage();
    userProfilePage.selectTaskSortField("Priority");
    userProfilePage.selectTaskSortDirection("Sort ascending");
    homePage = userProfilePage.save();
    // Check result
    taskWidgetPage = homePage.openTaskList();
    selectedSortColumn = taskWidgetPage.getSelectedSortColumn();
    assertTrue(StringUtils.equalsIgnoreCase("Prio", selectedSortColumn));
    assertEquals("high", taskWidgetPage.getPriorityOfTask(0));
  }

}
