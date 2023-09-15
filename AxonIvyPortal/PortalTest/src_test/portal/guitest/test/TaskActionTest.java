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
import portal.guitest.userexamples.page.LeaveRequestPage;

public class TaskActionTest extends BaseTest {

  private HomePage homePage;
  private TaskWidgetPage taskWidgetPage;
  private TaskDetailsPage taskDetailsPage;

  static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";

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

  @Override
  @Before
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
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
    assertTaskActionsByTaskState(READY_FOR_JOINING, Arrays.asList(DETAILS, PROCESS_VIEWER));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Suspended
    assertTaskActionsByTaskState(SUSPENDED,
        Arrays.asList(DETAILS, DELEGATE, RESERVE, CLEAR_EXPIRY, PROCESS_VIEWER, ADD_AD_HOC_TASK));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Reserved
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTaskActionsByTaskState(RESERVED,
        Arrays.asList(DETAILS, DELEGATE, RESET, CLEAR_EXPIRY, PROCESS_VIEWER, ADD_AD_HOC_TASK));

    // In progress
    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.clickCancelLink();
    taskDetailsPage = new TaskDetailsPage();
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    assertTaskActionsByTaskState(IN_PROGRESS,
        Arrays.asList(DETAILS, RESERVE, RESET, CLEAR_EXPIRY, PROCESS_VIEWER, ADD_AD_HOC_TASK));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testVisibilityTaskActionForAdminUser() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTaskWithSystemState);
    gotoTaskList();
    // Ready for Join
    assertTaskActionsByTaskState(READY_FOR_JOINING,
        Arrays.asList(DETAILS, RESET, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Suspended
    assertTaskActionsByTaskState(SUSPENDED, Arrays.asList(DETAILS, DELEGATE, RESERVE, CLEAR_EXPIRY, DESTROY,
        WORKFLOW_EVENTS, TRIGGER_ESCALATION, PROCESS_VIEWER, ADD_AD_HOC_TASK));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Reserved
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTaskActionsByTaskState(RESERVED, Arrays.asList(DETAILS, DELEGATE, RESET, CLEAR_EXPIRY, DESTROY,
        WORKFLOW_EVENTS, TRIGGER_ESCALATION, PROCESS_VIEWER, ADD_AD_HOC_TASK));

    // In progress
    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.clickCancelLink();
    taskDetailsPage = new TaskDetailsPage();
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    assertTaskActionsByTaskState(IN_PROGRESS, Arrays.asList(DETAILS, RESERVE, RESET, CLEAR_EXPIRY, DESTROY,
        WORKFLOW_EVENTS, PROCESS_VIEWER, ADD_AD_HOC_TASK));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Done
    assertTaskActionsByTaskState(DONE, Arrays.asList(DETAILS, WORKFLOW_EVENTS, PROCESS_VIEWER));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Delayed
    assertTaskActionsByTaskState(DELAYED,
        Arrays.asList(DETAILS, DELEGATE, CLEAR_DELAY, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER, ADD_AD_HOC_TASK));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Destroyed
    assertTaskActionsByTaskState(DESTROYED, Arrays.asList(DETAILS, WORKFLOW_EVENTS, PROCESS_VIEWER));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
  }

  @Test
  public void testVisibleTaskActionsWhenTaskStatusIsDoneAndDestroyed() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTaskWithSystemState);
    gotoTaskList();

    taskWidgetPage.clickOnStartTaskLink(4);
    LeaveRequestPage leaveRequestPage = new LeaveRequestPage();
    leaveRequestPage.fulfillAndSendMaternityLeaveRequest();

    redirectToRelativeLink(createTaskWithSystemState);
    gotoTaskList();

    // Done
    List<String> taskActionInTaskDetails = Arrays.asList(DETAILS, WORKFLOW_EVENTS, PROCESS_VIEWER);
    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList(DONE));
    List<String> actionInTaskList = taskWidgetPage.getActiveTaskAction(2);
    assertTrue(actionInTaskList.size() == taskActionInTaskDetails.size());
    assertTrue(actionInTaskList.containsAll(taskActionInTaskDetails));

    redirectToRelativeLink(createTaskWithSystemState);
    gotoTaskList();

    // Destroyed
    assertTaskActionsByTaskState(DESTROYED, Arrays.asList(DETAILS, WORKFLOW_EVENTS, PROCESS_VIEWER));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
  }

  @Test
  public void testVisibilityTaskActionForTechnicalStates() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTechnicalStateUrl);
    gotoTaskList();

    // Failed
    assertTaskActionsByTaskState(FAILED, Arrays.asList(DETAILS, RESET, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Join failed
    assertTaskActionsByTaskState(JOIN_FAILED, Arrays.asList(DETAILS, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // waiting for event
    assertTaskActionsByTaskState(WAITING_FOR_EVENT, Arrays.asList(DETAILS, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER));
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
    taskActionInTaskDetailsPage.remove(DETAILS);
    assertTrue(actionInDetails.size() == taskActionInTaskDetailsPage.size());
    assertTrue(actionInDetails.containsAll(taskActionInTaskDetailsPage));
  }

}
