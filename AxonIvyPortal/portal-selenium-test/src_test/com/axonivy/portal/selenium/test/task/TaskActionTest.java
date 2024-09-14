package com.axonivy.portal.selenium.test.task;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.LeaveRequestPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class TaskActionTest extends BaseTest {

  private TaskWidgetPage taskWidgetPage;
  private TaskDetailsPage taskDetailsPage;

  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";

  private static final String DONE = "Done";
  private static final String SUSPENDED = "Suspended";
  private static final String IN_PROGRESS = "In progress";
  private static final String READY_FOR_JOINING = "Ready for joining";
  private static final String RESERVED = "Reserved";
  private static final String DELAYED = "Delayed";
  private static final String DESTROYED = "Destroyed";
  private static final String FAILED = "Failed";
  private static final String JOIN_FAILED = "Join failed";
  private static final String WAITING_FOR_EVENT = "Waiting for event";

  private static final String DETAILS = "Details";
  private static final String PROCESS_VIEWER = "Process Viewer";
  private static final String CLEAR_EXPIRY = "Clear expiry";
  private static final String DELEGATE = "Delegate";
  private static final String RESERVE = "Reserve";
  private static final String ADD_AD_HOC_TASK = "Add Ad-hoc Task";
  private static final String RESET = "Reset";
  private static final String DESTROY = "Destroy";
  private static final String WORKFLOW_EVENTS = "Workflow Events";
  private static final String TRIGGER_ESCALATION = "Trigger Escalation";
  private static final String CLEAR_DELAY = "Clear delay";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    createTestingTasks();
  }

  @Test
  public void testVisibilityTaskActionForNormalUser() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTaskWithSystemState);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
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
    assertTaskActionsByTaskState(RESERVED,
        Arrays.asList(DETAILS, DELEGATE, RESET, CLEAR_EXPIRY, PROCESS_VIEWER, ADD_AD_HOC_TASK));

    // In progress
    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.clickCancelLink();
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    assertTaskActionsByTaskState(IN_PROGRESS,
        Arrays.asList(DETAILS, RESERVE, RESET, CLEAR_EXPIRY, PROCESS_VIEWER, ADD_AD_HOC_TASK));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
  }

  @Test
  public void testVisibilityTaskActionForAdminUser() {
    login(TestAccount.ADMIN_USER);
    grantSpecificPortalPermission(PortalPermission.SYSTEM_TASK_READ_ALL);
    redirectToRelativeLink(createTaskWithSystemState);
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    // Ready for Join
    assertTaskActionsByTaskState(READY_FOR_JOINING,
        Arrays.asList(DETAILS, RESET, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER));
    taskWidgetPage = NavigationHelper.navigateToTaskList();

    // Suspended
    assertTaskActionsByTaskState(SUSPENDED, Arrays.asList(DETAILS, DELEGATE, RESERVE, CLEAR_EXPIRY, DESTROY,
        WORKFLOW_EVENTS, TRIGGER_ESCALATION, PROCESS_VIEWER, ADD_AD_HOC_TASK));
    taskWidgetPage = NavigationHelper.navigateToTaskList();

    // Reserved
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.reserveTask(0);
    assertTaskActionsByTaskState(RESERVED, Arrays.asList(DETAILS, DELEGATE, RESET, CLEAR_EXPIRY, DESTROY,
        WORKFLOW_EVENTS, TRIGGER_ESCALATION, PROCESS_VIEWER, ADD_AD_HOC_TASK));

    // In progress
    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.clickCancelLink();
    taskDetailsPage = new TaskDetailsPage();
    taskWidgetPage = NavigationHelper.navigateToTaskList();

    assertTaskActionsByTaskState(IN_PROGRESS, Arrays.asList(DETAILS, RESERVE, RESET, CLEAR_EXPIRY, DESTROY,
        WORKFLOW_EVENTS, PROCESS_VIEWER, ADD_AD_HOC_TASK));
    taskWidgetPage = NavigationHelper.navigateToTaskList();

    // Done
    assertTaskActionsByTaskState(DONE, Arrays.asList(DETAILS, WORKFLOW_EVENTS, PROCESS_VIEWER));
    taskWidgetPage = NavigationHelper.navigateToTaskList();

    // Delayed
    assertTaskActionsByTaskState(DELAYED,
        Arrays.asList(DETAILS, DELEGATE, CLEAR_DELAY, DESTROY, WORKFLOW_EVENTS, PROCESS_VIEWER, ADD_AD_HOC_TASK));
    taskWidgetPage = NavigationHelper.navigateToTaskList();

    // Destroyed
    assertTaskActionsByTaskState(DESTROYED, Arrays.asList(DETAILS, WORKFLOW_EVENTS, PROCESS_VIEWER));
    taskWidgetPage = NavigationHelper.navigateToTaskList();
  }

  @Test
  public void testVisibleTaskActionsWhenTaskStatusIsDoneAndDestroyed() {
    login(TestAccount.ADMIN_USER);
    grantSpecificPortalPermission(PortalPermission.SYSTEM_TASK_READ_ALL);
    redirectToRelativeLink(createTaskWithSystemState);
    taskWidgetPage = NavigationHelper.navigateToTaskList();

    taskWidgetPage.clickOnStartTaskLink(4);
    LeaveRequestPage leaveRequestPage = new LeaveRequestPage();
    leaveRequestPage.fulfillAndSendMaternityLeaveRequest();

    redirectToRelativeLink(createTaskWithSystemState);
    taskWidgetPage = NavigationHelper.navigateToTaskList();

    // Done
    List<String> taskActionInTaskDetails = Arrays.asList(DETAILS, WORKFLOW_EVENTS, PROCESS_VIEWER);
    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList(DONE));
    List<String> actionInTaskList = taskWidgetPage.getActiveTaskAction(2);
    assertTrue(actionInTaskList.size() == taskActionInTaskDetails.size());
    assertTrue(actionInTaskList.containsAll(taskActionInTaskDetails));

    redirectToRelativeLink(createTaskWithSystemState);
    taskWidgetPage = NavigationHelper.navigateToTaskList();

    // Destroyed
    assertTaskActionsByTaskState(DESTROYED, Arrays.asList(DETAILS, WORKFLOW_EVENTS, PROCESS_VIEWER));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
  }

  @Test
  public void testVisibilityTaskActionForTechnicalStates() {
    login(TestAccount.ADMIN_USER);
    grantSpecificPortalPermission(PortalPermission.SYSTEM_TASK_READ_ALL);
    redirectToRelativeLink(createTechnicalStateUrl);
    taskWidgetPage = NavigationHelper.navigateToTaskList();

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
