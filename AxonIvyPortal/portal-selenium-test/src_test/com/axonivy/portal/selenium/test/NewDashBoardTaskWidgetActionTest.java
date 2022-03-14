package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
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
public class NewDashBoardTaskWidgetActionTest extends BaseTest {

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
    assertTaskActionsByTaskState("Ready for joining", new ArrayList<>());
    // Suspended
    assertTaskActionsByTaskState("Suspended", Arrays.asList("Delegate", "Reserve", "Clear expiry", "Add Ad-hoc Task"));
    // Reserved
    taskWidget.reserveTask(0);
    assertTaskActionsByTaskState("Reserved", Arrays.asList("Delegate", "Reset", "Clear expiry", "Add Ad-hoc Task"));
    // In progress
    taskWidget.startFirstTask();
    taskWidget.clickCancelTask();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    assertTaskActionsByTaskState("In progress", Arrays.asList("Reserve", "Reset", "Clear expiry", "Add Ad-hoc Task"));
    // Done
    assertTaskActionsByTaskState("Done", new ArrayList<>());
  }

  @Test
  public void testVisibilityTaskActionForAdminUser() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTaskWithSystemState);
    redirectToNewDashBoard();
    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    // Ready for Join
    assertTaskActionsByTaskState("Ready for joining", Arrays.asList("Reset", "Destroy", "Workflow Events"));
    // Suspended
    assertTaskActionsByTaskState("Suspended",
        Arrays.asList("Delegate", "Reserve", "Clear expiry", "Destroy", "Workflow Events", "Add Ad-hoc Task"));
    // Reserved
    taskWidget.reserveTask(1);
    assertTaskActionsByTaskState("Reserved",
        Arrays.asList("Delegate", "Reset", "Clear expiry", "Destroy", "Workflow Events", "Add Ad-hoc Task"));
    // In progress
    taskWidget.startFirstTask();
    taskWidget.clickCancelTask();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    assertTaskActionsByTaskState("In progress",
        Arrays.asList("Reserve", "Reset", "Clear expiry", "Destroy", "Workflow Events", "Add Ad-hoc Task"));
    // Done
    assertTaskActionsByTaskState("Done", Arrays.asList("Workflow Events"));
    // Delayed
    assertTaskActionsByTaskState("Delayed",
        Arrays.asList("Delegate", "Clear delay", "Destroy", "Workflow Events", "Add Ad-hoc Task"));
    // Destroyed
    assertTaskActionsByTaskState("Destroyed", Arrays.asList("Workflow Events"));
  }

  @Test
  public void testVisibilityTaskActionForTechnicalStates() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTechnicalStateUrl);
    redirectToNewDashBoard();
    taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    // Failed
    assertTaskActionsByTaskState("Failed", Arrays.asList("Reset", "Destroy", "Workflow Events"));
    // Join failed
    assertTaskActionsByTaskState("Join failed", Arrays.asList("Destroy", "Workflow Events"));
    // waiting for event
    assertTaskActionsByTaskState("Waiting for event", Arrays.asList("Destroy", "Workflow Events"));

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
