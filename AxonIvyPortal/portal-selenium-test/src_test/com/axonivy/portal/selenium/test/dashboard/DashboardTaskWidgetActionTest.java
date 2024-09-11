package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

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

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class DashboardTaskWidgetActionTest extends BaseTest {
  static final String DONE = "Done";
  static final String SUSPENDED = "Suspended";
  static final String IN_PROGRESS = "In progress";
  static final String READY_FOR_JOINING = "Ready for joining";
  static final String RESERVED = "Reserved";
  static final String DELAYED = "Delayed";
  static final String DESTROYED = "Destroyed";
  static final String FAILED = "Failed";
  static final String JOIN_FAILED = "Join failed";
  static final String WAITING_FOR_EVENT = "Waiting for event";
  static final String OPEN = "Open";
  static final String ERROR = "Error";

  static final String DETAILS = "Details";
  static final String PROCESS_VIEWER = "Process Viewer";
  static final String CLEAR_EXPIRY = "Clear expiry";
  static final String DELEGATE = "Delegate";
  static final String RESERVE = "Reserve";
  static final String ADD_AD_HOC_TASK = "Add Ad-hoc Task";
  static final String RESET = "Reset";
  static final String DESTROY = "Destroy";
  static final String WORKFLOW_EVENTS = "Workflow Events";
  static final String TRIGGER_ESCALATION = "Trigger Escalation";
  static final String CLEAR_DELAY = "Clear delay";

  // WIDGET NAME
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    createJSonFile("dashboard-has-one-task-widget.json", PortalVariable.DASHBOARD.key);
    redirectToRelativeLink(createTestingTasksUrl);
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testVisibilityTaskActionForNormalUser() {
    login(TestAccount.DEMO_USER);
    createTasksForTesting();
    // TaskState : Ready for Join <=> TaskBusinessState : Done
    assertTaskActionsByTaskState("Ready for joining", Arrays.asList(DETAILS, PROCESS_VIEWER));
    // TaskState : Suspended <=> TaskBusinessState : Open
    assertTaskActionsByTaskState("Suspended",
        Arrays.asList(DETAILS, DELEGATE, RESERVE, CLEAR_EXPIRY, PROCESS_VIEWER, ADD_AD_HOC_TASK));
    // TaskState : Done <=> TaskBusinessState : Done
    assertTaskActionsByTaskState("Done", Arrays.asList(DETAILS, PROCESS_VIEWER));
  }

  @Test
  public void testVisibilityTaskActionForAdminUser() {
    login(TestAccount.ADMIN_USER);
    createTasksForTesting();

    // Ready for Join
    assertTaskActionsByTaskStateAndName(READY_FOR_JOINING, "Task Switch A",
        Arrays.asList(DETAILS, RESET, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER));

    // Suspended
    assertTaskActionsByTaskStateAndName(SUSPENDED, "Sick Leave Request", Arrays.asList(DETAILS, DELEGATE, RESERVE,
        CLEAR_EXPIRY, DESTROY, TRIGGER_ESCALATION, WORKFLOW_EVENTS, PROCESS_VIEWER, ADD_AD_HOC_TASK));

    // Done
    assertTaskActionsByTaskStateAndName(DONE, "Categoried Leave Request",
        Arrays.asList(DETAILS, WORKFLOW_EVENTS, PROCESS_VIEWER));

    // Delayed
    assertTaskActionsByTaskStateAndName(DELAYED, "Task Switch C",
        Arrays.asList(DETAILS, DELEGATE, CLEAR_DELAY, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER, ADD_AD_HOC_TASK));

    // Destroyed
    assertTaskActionsByTaskStateAndName(DESTROYED, "Task Switch B",
        Arrays.asList(DETAILS, WORKFLOW_EVENTS, PROCESS_VIEWER));
  }

  @Test
  public void testVisibilityTaskActionForInprogressTasks() {
    login(TestAccount.ADMIN_USER);
    createTasksForTesting();
    filterTaskByNameAndState("Sick Leave Request", SUSPENDED);
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.startTask(0);
    taskWidget.clickCancelTask();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();

    // In progress for admin user
    newDashboardPage.waitForGrowlMessageDisappear();
    assertTaskActionsByTaskState(IN_PROGRESS, Arrays.asList(DETAILS, RESERVE, RESET, CLEAR_EXPIRY, DESTROY,
        WORKFLOW_EVENTS, PROCESS_VIEWER, ADD_AD_HOC_TASK));

    login(TestAccount.DEMO_USER);
    createTasksForTesting();
    filterTaskByNameAndState("Sick Leave Request", SUSPENDED);
    taskWidget.startTask(0);
    taskWidget.clickCancelTask();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    // In progress for normal user
    newDashboardPage.waitForGrowlMessageDisappear();
    assertTaskActionsByTaskState(IN_PROGRESS,
        Arrays.asList(DETAILS, RESERVE, RESET, CLEAR_EXPIRY, PROCESS_VIEWER, ADD_AD_HOC_TASK));
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
    filterTaskByNameAndState("Maternity Leave Request", SUSPENDED);
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.reserveTask(0);
    refreshPage();

    // Reserved for admin user
    assertTaskActionsByTaskState(RESERVED, Arrays.asList(DETAILS, DELEGATE, RESET, CLEAR_EXPIRY, DESTROY,
        TRIGGER_ESCALATION, WORKFLOW_EVENTS, PROCESS_VIEWER, ADD_AD_HOC_TASK));

    login(TestAccount.DEMO_USER);
    createTasksForTesting();
    // Reserved for normal user
    assertTaskActionsByTaskState(RESERVED, Arrays.asList(DETAILS, PROCESS_VIEWER));
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
    resizeBrowserTo2kResolution();
    assertTaskActionsByTaskStateAndName(FAILED, "Signal create Task failed",
        Arrays.asList(DETAILS, RESET, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER));
    // Join failed
    assertTaskActionsByTaskStateAndName(JOIN_FAILED, "Signal create Technical task",
        Arrays.asList(DETAILS, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER));
    // waiting for event
    assertTaskActionsByTaskState(WAITING_FOR_EVENT, Arrays.asList(DETAILS, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER));
  }

  private void assertTaskActionsByTaskState(String state, List<String> taskActionsInTask) {
    filterTaskByState(state);
    assertTaskAction(0, taskActionsInTask);
  }

  private void assertTaskActionsByTaskStateAndName(String state, String name, List<String> taskActionsInTask) {
    filterTaskByNameAndState(name, state);
    assertTaskAction(0, taskActionsInTask);
  }

  private void assertTaskAction(int index, List<String> taskActionsInTask) {
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    ElementsCollection actions = taskWidget.getActiveTaskActions(index);
    actions.shouldHave(size(taskActionsInTask.size()));
    assertTrue(actions.texts().containsAll(taskActionsInTask));
    taskWidget.clickOnTaskActionLink(index);
  }

  private void filterTaskByState(String state) {
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.resetFilter();
    refreshPage();
    taskWidget.openFilterWidget();
    taskWidget.filterTaskState();
    taskWidget.selectState(state);
    taskWidget.applyFilter();
  }
}
