package portal.guitest.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
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
    assertTaskActionsByTaskState("Ready for joining", new ArrayList<>());
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Suspended
    assertTaskActionsByTaskState("Suspended", Arrays.asList("Delegate", "Reserve", "Clear the expiry", "Add Ad-hoc Task"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Reserved
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTaskActionsByTaskState("Reserved", Arrays.asList("Delegate", "Reset", "Clear the expiry", "Add Ad-hoc Task"));

    // In progress
    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.clickCancelLink();
    taskDetailsPage = new TaskDetailsPage();
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    assertTaskActionsByTaskState("In progress", Arrays.asList("Reserve", "Reset", "Clear the expiry", "Add Ad-hoc Task"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testVisibilityTaskActionForAdminUser() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTaskWithSystemState);
    gotoTaskList();
    // Ready for Join
    assertTaskActionsByTaskState("Ready for joining", Arrays.asList("Reset", "Destroy"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Suspended
    assertTaskActionsByTaskState("Suspended", Arrays.asList("Delegate", "Reserve", "Clear the expiry", "Destroy", "Add Ad-hoc Task"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Reserved
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTaskActionsByTaskState("Reserved", Arrays.asList("Delegate", "Reset", "Clear the expiry", "Destroy", "Add Ad-hoc Task"));

    // In progress
    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.clickCancelLink();
    taskDetailsPage = new TaskDetailsPage();
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    assertTaskActionsByTaskState("In progress", Arrays.asList("Reserve", "Reset", "Clear the expiry", "Destroy", "Add Ad-hoc Task"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Done
    assertTaskActionsByTaskState("Done", new ArrayList<>());
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Delayed
    assertTaskActionsByTaskState("Delayed", Arrays.asList("Delegate", "Clear the delay", "Destroy", "Add Ad-hoc Task"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Destroyed
    assertTaskActionsByTaskState("Destroyed", new ArrayList<>());
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
  }
  
  @Test
  public void testVisibilityTaskActionForTechnicalStates() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTechnicalStateUrl);
    gotoTaskList();

    // Failed
    assertTaskActionsByTaskState("Failed", Arrays.asList("Reset", "Destroy"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Join failed
    assertTaskActionsByTaskState("Join failed", Arrays.asList("Destroy"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // waiting for event
    assertTaskActionsByTaskState("Waiting for event",  Arrays.asList("Destroy"));
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
    actionInTaskList.remove("Details");
    actionInTaskList.remove("Workflow Events");
    assertTrue(actionInTaskList.size() == taskActionInTaskDetails.size());
    assertTrue(actionInTaskList.containsAll(taskActionInTaskDetails));

    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    if (taskActionInTaskDetails.isEmpty()) {
      return;
    }
    assertTrue(taskDetailsPage.isActionLinkEnable());
    List<String> actionInDetails = taskDetailsPage.getActiveTaskAction();
    actionInDetails.remove("Workflow Events");
    assertTrue(actionInDetails.size() == taskActionInTaskDetails.size());
    assertTrue(actionInDetails.containsAll(taskActionInTaskDetails));
  }

}
