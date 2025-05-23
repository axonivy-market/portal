package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskTemplateIFramePage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.ElementsCollection;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class DashboardTaskWidgetActionTest extends BaseTest {
  static final String DONE = "Done";
  static final String SUSPENDED = "Suspended";
  static final String IN_PROGRESS = "In Progress";
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
  static final String PIN = "Pin";
  static final String PROCESS_VIEWER = "Process Viewer";
  static final String CLEAR_EXPIRY = "Clear expiry";
  static final String DELEGATE = "Delegate";
  static final String RESERVE = "Reserve";
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
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForTaskListDisplay();
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    // TaskState : Ready for Join <=> TaskBusinessState : Done
    assertTaskActionsByTaskState("Done", Arrays.asList(DETAILS, PIN, PROCESS_VIEWER));
    // TaskState : Suspended <=> TaskBusinessState : Open
    taskWidget.openFilterWidget();
    removeAllExistingFilter();
    taskWidget.applyFilter();
    assertTaskActionsByTaskState("Open",
        Arrays.asList(DETAILS, PIN, DELEGATE, RESERVE, CLEAR_EXPIRY, PROCESS_VIEWER));
    // TaskState : Done <=> TaskBusinessState : Done
    taskWidget.openFilterWidget();
    removeAllExistingFilter();
    taskWidget.applyFilter();
    assertTaskActionsByTaskState("Done", Arrays.asList(DETAILS, PIN, PROCESS_VIEWER));
  }

  @Test
  public void testVisibilityTaskActionForAdminUser() {
    login(TestAccount.ADMIN_USER);
    createTasksForTesting();
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();

    // Ready for Join
    assertTaskActionsByTaskStateAndName(DONE, "Task Switch A",
        Arrays.asList(DETAILS, PIN, RESET, DESTROY, TRIGGER_ESCALATION, WORKFLOW_EVENTS, PROCESS_VIEWER));

    // Suspended
    taskWidget.openFilterWidget();
    removeAllExistingFilter();
    taskWidget.applyFilter();
    assertTaskActionsByTaskStateAndName(OPEN, "Sick Leave Request", Arrays.asList(DETAILS, PIN, DELEGATE, RESERVE,
        CLEAR_EXPIRY, DESTROY, TRIGGER_ESCALATION, WORKFLOW_EVENTS, PROCESS_VIEWER));

    // Done
    taskWidget.openFilterWidget();
    removeAllExistingFilter();
    taskWidget.applyFilter();
    assertTaskActionsByTaskStateAndName(DONE, "Categoried Leave Request",
        Arrays.asList(DETAILS, PIN, WORKFLOW_EVENTS, PROCESS_VIEWER));

    // Delayed
    taskWidget.openFilterWidget();
    removeAllExistingFilter();
    taskWidget.applyFilter();
    assertTaskActionsByTaskStateAndName(DELAYED, "Task Switch C",
        Arrays.asList(DETAILS, PIN, DELEGATE, CLEAR_DELAY, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER));

    // Destroyed
    taskWidget.openFilterWidget();
    removeAllExistingFilter();
    taskWidget.applyFilter();
    assertTaskActionsByTaskStateAndName(DESTROYED, "Task Switch B",
        Arrays.asList(DETAILS, PIN, WORKFLOW_EVENTS, PROCESS_VIEWER));
  }

  @Test
  public void testVisibilityTaskActionForInprogressTasks() {
    login(TestAccount.ADMIN_USER);
    createTasksForTesting();
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName("Sick Leave Request", FilterOperator.IS);
    taskWidget.applyFilter();
    taskWidget.clickOnTaskName("Sick Leave Request");
    
    TaskTemplateIFramePage templatePage = new TaskTemplateIFramePage();
    templatePage.switchToIFrameOfTask();
    newDashboardPage = templatePage.clickCancelButton();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();

    // In progress for admin user
    newDashboardPage.waitForGrowlMessageDisappear();
    taskWidget.openFilterWidget();
    taskWidget.removeFilter(0);
    taskWidget.applyFilter();
    assertTaskActionsByTaskState(IN_PROGRESS, Arrays.asList(DETAILS, PIN, RESERVE, RESET, CLEAR_EXPIRY, DESTROY,
        WORKFLOW_EVENTS, PROCESS_VIEWER));

    login(TestAccount.DEMO_USER);
    createTasksForTesting();
    filterTaskByNameAndState("Sick Leave Request", OPEN);
    taskWidget.clickOnTaskName("Sick Leave Request");
    templatePage = new TaskTemplateIFramePage();
    templatePage.switchToIFrameOfTask();
    newDashboardPage = templatePage.clickCancelButton();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    // In progress for normal user
    newDashboardPage.waitForGrowlMessageDisappear();
    taskWidget.openFilterWidget();
    taskWidget.removeFilter(1);
    taskWidget.applyFilter();
    assertTaskActionsByTaskState(IN_PROGRESS,
        Arrays.asList(DETAILS, PIN, RESERVE, RESET, CLEAR_EXPIRY, PROCESS_VIEWER));
  }

  private void filterTaskByNameAndState(String name, String state) {
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(name, FilterOperator.IS);
    taskWidget.filterTaskState(state);
    taskWidget.applyFilter();
  }

  @Test
  public void testVisibilityTaskActionForReserveTasks() {
    login(TestAccount.ADMIN_USER);
    createTasksForTesting();
    filterTaskByNameAndState("Maternity Leave Request", OPEN);
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.reserveTask(0);
    refreshPage();

    // Reserved for admin user
    assertTaskActionsByTaskState(OPEN, Arrays.asList(DETAILS, PIN, DELEGATE, RESET, CLEAR_EXPIRY, DESTROY,
        TRIGGER_ESCALATION, WORKFLOW_EVENTS, PROCESS_VIEWER));

    login(TestAccount.DEMO_USER);
    createTasksForTesting();
    // Reserved for normal user
    assertTaskActionsByTaskState(OPEN, Arrays.asList(DETAILS, PIN, PROCESS_VIEWER));
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
    assertTaskActionsByTaskStateAndName(ERROR, "Signal create Task failed",
        Arrays.asList(DETAILS, PIN, RESET, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER));
    // Join failed
    taskWidget.openFilterWidget();
    removeAllExistingFilter();
    taskWidget.applyFilter();
    assertTaskActionsByTaskStateAndName(ERROR, "Signal create Technical task",
        Arrays.asList(DETAILS, PIN, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER));
    // waiting for event
    taskWidget.openFilterWidget();
    removeAllExistingFilter();
    taskWidget.applyFilter();
    assertTaskActionsByTaskState(ERROR, Arrays.asList(DETAILS, PIN, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER));
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
    taskWidget.addFilter("State", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, state);
    taskWidget.applyFilter();
  }
  
  private void removeAllExistingFilter() {
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    int numberOfFilter = taskWidget.getFilterNotiNumber();
    for(int i = 0; i < numberOfFilter; i++) {
      taskWidget.removeFilter(0);
    }
  }
}
