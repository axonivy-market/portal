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

  private String denyResetReadyForJoinURL = "portalKitTestHelper/14DE09882B540AD5/denyResetTaskReadyForJoin.ivp";
  private String denyDestroyTaskURL = "portalKitTestHelper/14DE09882B540AD5/denyDestroyTaskPermission.ivp";
  private String grantResetReadyForJoinURL = "portalKitTestHelper/14DE09882B540AD5/grantResetTaskReadyForJoin.ivp";
  private String grantDestroyTaskURL = "portalKitTestHelper/14DE09882B540AD5/grantDestroyTaskPermission.ivp";

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

  @Test
  public void testVisibilityTaskActionForNormalUser() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTaskWithSystemState);
    gotoTaskList();
    // Ready for Join
    assertTaskActionsByTaskState("Ready for joining", new ArrayList<>());
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Suspended
    assertTaskActionsByTaskState("Suspended", Arrays.asList("Delegate", "Reserve", "Add Ad-hoc Task"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Reserved
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTaskActionsByTaskState("Reserved", Arrays.asList("Delegate", "Reset", "Add Ad-hoc Task"));

    // In progress
    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.clickCancelLink();
    taskDetailsPage = new TaskDetailsPage();
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    assertTaskActionsByTaskState("In progress", Arrays.asList("Reserve", "Reset", "Add Ad-hoc Task"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Done
    assertTaskActionsByTaskState("Done", new ArrayList<>());
  }

  @Test
  public void testVisibilityTaskActionForAdminUser() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(grantResetReadyForJoinURL);
    redirectToRelativeLink(grantDestroyTaskURL);
    redirectToRelativeLink(createTaskWithSystemState);
    gotoTaskList();
    // Ready for Join
    assertTaskActionsByTaskState("Ready for joining", Arrays.asList("Reset", "Destroy"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Suspended
    assertTaskActionsByTaskState("Suspended", Arrays.asList("Delegate", "Reserve", "Destroy", "Add Ad-hoc Task"));
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();

    // Reserved
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTaskActionsByTaskState("Reserved", Arrays.asList("Delegate", "Reset", "Destroy", "Add Ad-hoc Task"));

    // In progress
    TaskTemplatePage taskTemplatePage = taskDetailsPage.clickStartTask();
    taskTemplatePage.clickCancelLink();
    taskDetailsPage = new TaskDetailsPage();
    taskWidgetPage = taskDetailsPage.goBackToTaskListFromTaskDetails();
    assertTaskActionsByTaskState("In progress", Arrays.asList("Reserve", "Reset", "Destroy", "Add Ad-hoc Task"));
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

    redirectToRelativeLink(denyResetReadyForJoinURL);
    redirectToRelativeLink(denyDestroyTaskURL);
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
    actionInTaskList.removeAll(taskActionInTaskDetails);
    assertTrue(actionInTaskList.isEmpty());

    taskDetailsPage = taskWidgetPage.openTaskDetails(0);
    if (taskActionInTaskDetails.isEmpty()) {
      assertTrue(!taskDetailsPage.isActionLinkEnable());
      return;
    }
    assertTrue(taskDetailsPage.isActionLinkEnable());
    List<String> actionInDetails = taskDetailsPage.getActiveTaskAction();
    actionInDetails.removeAll(taskActionInTaskDetails);
    assertTrue(actionInDetails.isEmpty());
  }

}
