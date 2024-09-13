package com.axonivy.portal.selenium.test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest
public class EnhanceVisibilityTasksForMemberOfRoleTest extends BaseTest {

  private TaskWidgetPage taskWidgetPage;
  private TaskDetailsPage taskDetailsPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testVisibilityTaskOpen() {
    login(TestAccount.DEMO_USER);
    createTestingTasks();
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    // Suspended
    TaskWidgetPage taskWidgetPageDemo = new TaskWidgetPage();
    taskWidgetPageDemo.filterByResponsible("Everybody");
    taskWidgetPageDemo.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));
    int countTasks = taskWidgetPageDemo.countTasks().size();
    // User Guest
    login(TestAccount.GUEST_USER);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    // Suspended
    TaskWidgetPage taskWidgetPageGuest = new TaskWidgetPage();
    taskWidgetPageGuest.filterByResponsible("Everybody");
    taskWidgetPageGuest.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));
    assertEquals(countTasks, taskWidgetPageGuest.countTasks().size());
  }

  @Test
  public void testVisibilityTaskInprogress() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    login(TestAccount.DEMO_USER);
    createTestingTasks();
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    // Suspended
    TaskWidgetPage taskWidgetPageDemo = new TaskWidgetPage();
    taskWidgetPageDemo.filterByResponsible("Everybody");
    taskWidgetPageDemo.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));
    // Reserved
    taskWidgetPageDemo.clickOnTaskActionLink(0);
    taskWidgetPageDemo.reserveTask(0);
    taskWidgetPageDemo.clickOnTaskStatesAndApply(Arrays.asList("Reserved"));
    int countTasksReserved = taskWidgetPageDemo.countTasks().size();
    // User Guest
    login(TestAccount.GUEST_USER);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    // Reserved
    TaskWidgetPage taskWidgetPageGuest = new TaskWidgetPage();
    taskWidgetPageGuest.filterByResponsible("Everybody");
    taskWidgetPageGuest.clickOnTaskStatesAndApply(Arrays.asList("Reserved"));
    assertEquals(countTasksReserved, taskWidgetPageGuest.countTasks().size());
    assertFalse(taskWidgetPageGuest.isTaskStartEnabled(0));
    assertFalseTaskActionsByTaskState("Reserved",
        Arrays.asList("Delegate", "Reset", "Clear expiry", "Add Ad-hoc Task"));
  }

  @Test
  public void testVisibilityTaskDone() {
    login(TestAccount.DEMO_USER);
    createTestingTasks();
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    // Suspended
    TaskWidgetPage taskWidgetPageDemo = new TaskWidgetPage();
    taskWidgetPageDemo.filterByResponsible("Everybody");
    taskWidgetPageDemo.clickOnTaskStatesAndApply(Arrays.asList("Done"));
    int countTasks = taskWidgetPageDemo.countTasks().size();
    // User Guest
    login(TestAccount.GUEST_USER);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    // Suspended
    TaskWidgetPage taskWidgetPageGuest = new TaskWidgetPage();
    taskWidgetPageGuest.filterByResponsible("Everybody");
    taskWidgetPageGuest.clickOnTaskStatesAndApply(Arrays.asList("Done"));
    assertEquals(countTasks, taskWidgetPageGuest.countTasks().size());
  }

  private void assertFalseTaskActionsByTaskState(String state, List<String> taskActionInTaskDetails) {
    taskWidgetPage = new TaskWidgetPage();
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
