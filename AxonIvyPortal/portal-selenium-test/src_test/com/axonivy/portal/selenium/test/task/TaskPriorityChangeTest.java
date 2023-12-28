package com.axonivy.portal.selenium.test.task;

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
public class TaskPriorityChangeTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
  }

  @Test
  public void testChangeTaskPriority() {
    login(TestAccount.ADMIN_USER);
    int firstTask = 0;
    int priorityIntValue = 2;
    String priorityStringValue = "NORMAL";
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TaskDetailsPage taskDetailsPage = taskWidgetPage.openTaskDetails(firstTask);
    taskDetailsPage.changePriorityOfTask(priorityIntValue);
    assertTrue(priorityStringValue.equals(taskDetailsPage.getPriorityOfTask().getText()));
  }

  @Test
  public void testUserWithoutPermissionCannotChangeTaskPriority() {
    int firstTask = 0;
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.openTaskDetails(firstTask);
    assertFalse(taskWidgetPage.isTaskPriorityChangeComponentPresented(firstTask));
  }

}
