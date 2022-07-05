package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.ElementsCollection;

@IvyWebTest
public class DashboardTaskWidgetActionTest extends BaseTest {

  // WIDGET NAME
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testVisibilityTaskActionForNormalUser() {
    login(TestAccount.DEMO_USER);
    createTasksForTesting();
    // Ready for Join
    assertTaskActionsByTaskState("Ready for joining", Arrays.asList("Details", "Process Viewer"));
    // Suspended
    assertTaskActionsByTaskState("Suspended",
        Arrays.asList("Details", "Delegate", "Reserve", "Clear expiry", "Process Viewer", "Add Ad-hoc Task"));
    // Done
    assertTaskActionsByTaskState("Done", Arrays.asList("Details", "Process Viewer"));
  }

  @Test
  public void testVisibilityTaskActionForAdminUser() {
    login(TestAccount.ADMIN_USER);
    createTasksForTesting();
    // Ready for Join
    assertTaskActionsByTaskState("Ready for joining",
        Arrays.asList("Details", "Reset", "Destroy", "Workflow Events", "Process Viewer"));
    // Suspended
    assertTaskActionsByTaskStateAndName("Sick Leave Request", "Suspended",
        Arrays.asList("Details", "Delegate", "Reserve", "Clear expiry", "Destroy", "Trigger Escalation",
            "Workflow Events", "Process Viewer", "Add Ad-hoc Task"));
    // Done
    assertTaskActionsByTaskState("Done", Arrays.asList("Details", "Workflow Events", "Process Viewer"));
    // Delayed
    assertTaskActionsByTaskState("Delayed", Arrays.asList("Details", "Delegate", "Clear delay", "Destroy",
        "Workflow Events", "Process Viewer", "Add Ad-hoc Task"));
    // Destroyed
    assertTaskActionsByTaskState("Destroyed", Arrays.asList("Details", "Workflow Events", "Process Viewer"));
  }

  @Test
  public void testVisibilityTaskActionForInprogressTasks() {
    login(TestAccount.ADMIN_USER);
    createTasksForTesting();
    filterTaskByNameAndState("Sick Leave Request", "Suspended");
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.startTask(0);
    taskWidget.clickCancelTask();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();

    // In progress for admin user
    assertTaskActionsByTaskState("In progress", Arrays.asList("Details", "Reserve", "Reset", "Clear expiry", "Destroy",
        "Workflow Events", "Process Viewer", "Add Ad-hoc Task"));

    login(TestAccount.DEMO_USER);
    createTasksForTesting();
    filterTaskByNameAndState("Sick Leave Request", "Suspended");
    taskWidget.startTask(0);
    taskWidget.clickCancelTask();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    // In progress for normal user
    assertTaskActionsByTaskState("In progress",
        Arrays.asList("Details", "Reserve", "Reset", "Clear expiry", "Process Viewer", "Add Ad-hoc Task"));
  }

  private void filterTaskByNameAndState(String name, String state) {
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.resetFilter();
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(name);
    taskWidget.filterTaskState();
    taskWidget.selectState(state);
    taskWidget.applyFilter();
  }

  @Test
  public void testVisibilityTaskActionForReserveTasks() {
    login(TestAccount.ADMIN_USER);
    createTasksForTesting();
    filterTaskByNameAndState("Maternity Leave Request", "Suspended");
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.reserveTask(0);
    refreshPage();

    // Reserved for admin user
    assertTaskActionsByTaskState("Reserved", Arrays.asList("Details", "Delegate", "Reset", "Clear expiry", "Destroy",
        "Trigger Escalation", "Workflow Events", "Process Viewer", "Add Ad-hoc Task"));

    login(TestAccount.DEMO_USER);
    createTasksForTesting();
    // Reserved for normal user
    assertTaskActionsByTaskState("Reserved", Arrays.asList("Details", "Process Viewer"));
  }

  private void createTasksForTesting() {
    redirectToRelativeLink(createTaskWithSystemState);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
  }

  @Test
  public void testVisibilityTaskActionForTechnicalStates() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTechnicalStateUrl);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    // Failed
    assertTaskActionsByTaskState("Failed",
        Arrays.asList("Details", "Reset", "Destroy", "Workflow Events", "Process Viewer"));
    // Join failed
    assertTaskActionsByTaskState("Join failed",
        Arrays.asList("Details", "Destroy", "Workflow Events", "Process Viewer"));
    // waiting for event
    assertTaskActionsByTaskState("Waiting for event",
        Arrays.asList("Details", "Destroy", "Workflow Events", "Process Viewer"));
  }

  private void assertTaskActionsByTaskState(String state, List<String> taskActionsInTask) {
    filterTaskByState(state);
    assertTaskAction(0, taskActionsInTask);
  }

  private void assertTaskActionsByTaskStateAndName(String name, String state, List<String> taskActionsInTask) {
    filterTaskByNameAndState(name, state);
    assertTaskAction(0, taskActionsInTask);
  }

  private void assertTaskAction(int index, List<String> taskActionsInTask) {
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    ElementsCollection actions = taskWidget.getActiveTaskActions(index);
    actions.shouldHaveSize(taskActionsInTask.size());
    assertTrue(actions.texts().containsAll(taskActionsInTask));
    taskWidget.clickOnTaskActionLink(index);
  }

  private void filterTaskByState(String state) {
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.resetFilter();
    taskWidget.openFilterWidget();
    taskWidget.filterTaskState();
    taskWidget.selectState(state);
    taskWidget.applyFilter();
  }
}
