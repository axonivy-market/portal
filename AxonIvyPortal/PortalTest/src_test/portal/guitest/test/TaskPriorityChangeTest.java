package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.Variable;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskPriorityChangeTest extends BaseTest {

  @Override
  @Before
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
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    TaskDetailsPage taskDetailsPage = taskWidgetPage.openTaskDetails(firstTask);
    taskDetailsPage.changePriorityOfTask(priorityIntValue);
    assertTrue(priorityStringValue.equals(taskDetailsPage.getPriorityOfTask()));
  }

  @Test
  public void testUserWithoutPermissionCannotChangeTaskPriority() {
    int firstTask = 0;
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(firstTask);
    assertFalse(taskWidgetPage.isTaskPriorityChangeComponentPresented(firstTask));
  }

}
