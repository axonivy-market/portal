package portal.guitest.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.Variable;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class TaskActionTest extends BaseTest {

  private HomePage homePage;
  private TaskWidgetPage taskWidgetPage;
  private TaskDetailsPage taskDetailsPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
    homePage = new HomePage();
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testVisibilityTaskActionForNormalUser() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTaskWithSystemState);
    gotoTaskList();
    // Ready for Join
    assertTaskActionsByTaskState("Ready for joining", Arrays.asList("Details", "Process Viewer"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Suspended
    assertTaskActionsByTaskState("Suspended", Arrays.asList("Details", "Delegate", "Reserve", "Clear expiry", "Process Viewer", "Add Ad-hoc Task"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Reserved
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTaskActionsByTaskState("Reserved", Arrays.asList("Details", "Delegate", "Reset", "Clear expiry", "Process Viewer", "Add Ad-hoc Task"));

    // In progress
    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.clickCancelLink();
    taskDetailsPage = new TaskDetailsPage();
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    assertTaskActionsByTaskState("In progress", Arrays.asList("Details", "Reserve", "Reset", "Clear expiry", "Process Viewer", "Add Ad-hoc Task"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testVisibilityTaskActionForAdminUser() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTaskWithSystemState);
    gotoTaskList();
    // Ready for Join
    assertTaskActionsByTaskState("Ready for joining", Arrays.asList("Details", "Reset", "Destroy", "Workflow Events", "Process Viewer"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Suspended
    assertTaskActionsByTaskState("Suspended", Arrays.asList("Details", "Delegate", "Reserve", "Clear expiry", "Destroy", "Workflow Events", "Process Viewer", "Add Ad-hoc Task"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Reserved
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTaskActionsByTaskState("Reserved", Arrays.asList("Details", "Delegate", "Reset", "Clear expiry", "Destroy", "Workflow Events", "Process Viewer", "Add Ad-hoc Task"));

    // In progress
    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.clickCancelLink();
    taskDetailsPage = new TaskDetailsPage();
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    assertTaskActionsByTaskState("In progress", Arrays.asList("Details" ,"Reserve", "Reset", "Clear expiry", "Destroy", "Workflow Events", "Process Viewer", "Add Ad-hoc Task"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Done
    assertTaskActionsByTaskState("Done", Arrays.asList("Details", "Workflow Events", "Process Viewer"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Delayed
    assertTaskActionsByTaskState("Delayed", Arrays.asList("Details", "Delegate", "Clear delay", "Destroy", "Workflow Events", "Process Viewer", "Add Ad-hoc Task"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Destroyed
    assertTaskActionsByTaskState("Destroyed", Arrays.asList("Details", "Workflow Events", "Process Viewer"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
  }
  
  @Test
  public void testVisibilityTaskActionForTechnicalStates() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTechnicalStateUrl);
    gotoTaskList();

    // Failed
    assertTaskActionsByTaskState("Failed", Arrays.asList("Details", "Reset", "Destroy", "Workflow Events", "Process Viewer"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Join failed
    assertTaskActionsByTaskState("Join failed", Arrays.asList("Details", "Destroy", "Workflow Events", "Process Viewer"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // waiting for event
    assertTaskActionsByTaskState("Waiting for event",  Arrays.asList("Details", "Destroy", "Workflow Events", "Process Viewer"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

  }

  private void gotoTaskList() {
    homePage = new HomePage();
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
  }

  private void assertTaskActionsByTaskState(String state, List<String> taskActionInTaskDetails) {
    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList(state));
    List<String> actionInTaskList = taskWidgetPage.getActiveTaskAction(0);
    assertTrue(actionInTaskList.size() == taskActionInTaskDetails.size());
    assertTrue(actionInTaskList.containsAll(taskActionInTaskDetails));

    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    if (taskActionInTaskDetails.isEmpty()) {
      return;
    }
    assertTrue(taskDetailsPage.isActionLinkEnable());
    List<String> actionInDetails = taskDetailsPage.getActiveTaskAction();
    var taskActionInTaskDetailsPage = new ArrayList<>(taskActionInTaskDetails);
    taskActionInTaskDetailsPage.remove("Details");
    assertTrue(actionInDetails.size() == taskActionInTaskDetailsPage.size());
    assertTrue(actionInDetails.containsAll(taskActionInTaskDetailsPage));
  }

}
