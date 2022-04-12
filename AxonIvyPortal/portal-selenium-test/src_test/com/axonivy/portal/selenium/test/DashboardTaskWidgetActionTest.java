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
  TaskWidgetNewDashBoardPage taskWidget;

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
    redirectToRelativeLink(createTaskWithSystemState);
    redirectToNewDashBoard();
    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    // Ready for Join
    assertTaskActionsByTaskState("Ready for joining", Arrays.asList("Details", "Process Viewer"));
    // Suspended
    assertTaskActionsByTaskState("Suspended",
        Arrays.asList("Details", "Delegate", "Reserve", "Clear expiry", "Process Viewer", "Add Ad-hoc Task"));
    // Reserved
    taskWidget.reserveTask(0);
    assertTaskActionsByTaskState("Reserved",
        Arrays.asList("Details", "Delegate", "Reset", "Clear expiry", "Process Viewer", "Add Ad-hoc Task"));
    // In progress
    taskWidget.startFirstTask();
    taskWidget.clickCancelTask();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    assertTaskActionsByTaskState("In progress",
        Arrays.asList("Details", "Reserve", "Reset", "Clear expiry", "Process Viewer", "Add Ad-hoc Task"));
    // Done
    assertTaskActionsByTaskState("Done", Arrays.asList("Details", "Process Viewer"));
  }

  @Test
  public void testVisibilityTaskActionForAdminUser() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTaskWithSystemState);
    redirectToNewDashBoard();
    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    // Ready for Join
    assertTaskActionsByTaskState("Ready for joining", Arrays.asList("Details", "Reset", "Destroy", "Workflow Events", "Process Viewer"));
    // Suspended
    assertTaskActionsByTaskState("Suspended", Arrays.asList("Details", "Delegate", "Reserve", "Clear expiry", "Destroy",
        "Workflow Events", "Process Viewer", "Add Ad-hoc Task"));
    // Reserved
    taskWidget.clickOnTaskActionLink(1);
    taskWidget.reserveTask(1);
    assertTaskActionsByTaskState("Reserved",
        Arrays.asList("Details", "Delegate", "Reset", "Clear expiry", "Destroy", "Workflow Events", "Process Viewer", "Add Ad-hoc Task"));
    // In progress
    taskWidget.startFirstTask();
    taskWidget.clickCancelTask();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    assertTaskActionsByTaskState("In progress",
        Arrays.asList("Details", "Reserve", "Reset", "Clear expiry", "Destroy", "Workflow Events", "Process Viewer", "Add Ad-hoc Task"));
    // Done
    assertTaskActionsByTaskState("Done", Arrays.asList("Details", "Workflow Events", "Process Viewer"));
    // Delayed
    assertTaskActionsByTaskState("Delayed",
        Arrays.asList("Details", "Delegate", "Clear delay", "Destroy", "Workflow Events", "Process Viewer", "Add Ad-hoc Task"));
    // Destroyed
    assertTaskActionsByTaskState("Destroyed", Arrays.asList("Details", "Workflow Events", "Process Viewer"));
  }

  @Test
  public void testVisibilityTaskActionForTechnicalStates() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTechnicalStateUrl);
    redirectToNewDashBoard();
    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    // Failed
    assertTaskActionsByTaskState("Failed", Arrays.asList("Details", "Reset", "Destroy", "Workflow Events", "Process Viewer"));
    // Join failed
    assertTaskActionsByTaskState("Join failed", Arrays.asList("Details", "Destroy", "Workflow Events", "Process Viewer"));
    // waiting for event
    assertTaskActionsByTaskState("Waiting for event", Arrays.asList("Details", "Destroy", "Workflow Events", "Process Viewer"));

  }

  private void assertTaskActionsByTaskState(String state, List<String> taskActionsInTask) {
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.resetFilter();
    taskWidget.openFilterWidget();
    taskWidget.filterTaskState();
    taskWidget.selectState(state);
    taskWidget.applyFilter();
    ElementsCollection actions;
    if ("Suspended".equalsIgnoreCase(state)) {
      actions = taskWidget.getActiveTaskActions(1);
    } else {
      actions = taskWidget.getActiveTaskActions(0);
    }
    actions.shouldHaveSize(taskActionsInTask.size());
    assertTrue(actions.texts().containsAll(taskActionsInTask));
  }
}
