package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.Variable;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class EnhanceVisibilityTasksForMemberOfRoleTest extends BaseTest {

  private HomePage homePage;
  private TaskWidgetPage taskWidgetPage;
  private TaskDetailsPage taskDetailsPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    homePage = new HomePage();
  }

  @Test
  public void testVisibilityTaskOpen() {
    login(TestAccount.DEMO_USER);
    createTestingTasks();
    gotoTaskList();
    // Suspended
    TaskWidgetPage taskWidgetPageDemo = new TaskWidgetPage();
    taskWidgetPageDemo.filterByResponsible("Everybody");
    taskWidgetPageDemo.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));
    int countTasks = taskWidgetPageDemo.countTasks();
    // User Guest
    login(TestAccount.GUEST_USER);
    gotoTaskList();
    // Suspended
    TaskWidgetPage taskWidgetPageGuest = new TaskWidgetPage();
    taskWidgetPageGuest.filterByResponsible("Everybody");
    taskWidgetPageGuest.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));
    assertEquals(countTasks, taskWidgetPageGuest.countTasks());
  }
  
  @SuppressWarnings("deprecation")
  @Test
  public void testVisibilityTaskInprogress() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    login(TestAccount.DEMO_USER);
    createTestingTasks();
    gotoTaskList();
    // Suspended
    TaskWidgetPage taskWidgetPageDemo = new TaskWidgetPage();
    taskWidgetPageDemo.filterByResponsible("Everybody");
    taskWidgetPageDemo.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));   
    // Reserved
    taskWidgetPageDemo.clickOnTaskActionLink(0);
    taskWidgetPageDemo.reserveTask(0);
    taskWidgetPageDemo.waitAjaxIndicatorDisappear();
    taskWidgetPageDemo.clickOnTaskStatesAndApply(Arrays.asList("Reserved"));
    int countTasksReserved = taskWidgetPageDemo.countTasks();
    // User Guest
    login(TestAccount.GUEST_USER);
    gotoTaskList();
    // Reserved
    TaskWidgetPage taskWidgetPageGuest = new TaskWidgetPage();
    taskWidgetPageGuest.filterByResponsible("Everybody");
    taskWidgetPageGuest.clickOnTaskStatesAndApply(Arrays.asList("Reserved"));
    assertEquals(countTasksReserved, taskWidgetPageGuest.countTasks());
    assertFalse(taskWidgetPageGuest.isTaskStartEnabled(0));
    assertFalseTaskActionsByTaskState("Reserved", Arrays.asList("Delegate", "Reset", "Clear expiry", "Add Ad-hoc Task"));
  }

  @Test
  public void testVisibilityTaskDone() {
    login(TestAccount.DEMO_USER);
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    gotoTaskList();
    // Suspended
    TaskWidgetPage taskWidgetPageDemo = new TaskWidgetPage();
    taskWidgetPageDemo.filterByResponsible("Everybody");
    taskWidgetPageDemo.clickOnTaskStatesAndApply(Arrays.asList("Done"));
    int countTasks = taskWidgetPageDemo.countTasks();
    // User Guest
    login(TestAccount.GUEST_USER);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    gotoTaskList();
    // Suspended
    TaskWidgetPage taskWidgetPageGuest = new TaskWidgetPage();
    taskWidgetPageGuest.filterByResponsible("Everybody");
    taskWidgetPageGuest.clickOnTaskStatesAndApply(Arrays.asList("Done"));
    assertEquals(countTasks, taskWidgetPageGuest.countTasks());
  }

  private void gotoTaskList() {
    homePage = new HomePage();
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
  }
  
  private void assertFalseTaskActionsByTaskState(String state, List<String> taskActionInTaskDetails) {
    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList(state));
    List<String> actionInTaskList = taskWidgetPage.getActiveTaskAction(0);
    actionInTaskList.remove("Details");
    actionInTaskList.remove("Workflow Events");
    assertFalse(actionInTaskList.containsAll(taskActionInTaskDetails));
    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    if (taskActionInTaskDetails.isEmpty()) {
      return;
    }
    assertTrue(taskDetailsPage.isActionLinkEnable());
    List<String> actionInDetails = taskDetailsPage.getActiveTaskAction();
    actionInDetails.remove("Workflow Events");
    assertFalse(actionInDetails.containsAll(taskActionInTaskDetails));
  }
  
}
