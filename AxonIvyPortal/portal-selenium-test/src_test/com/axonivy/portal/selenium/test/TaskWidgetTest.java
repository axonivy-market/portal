package com.axonivy.portal.selenium.test;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TaskState;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class TaskWidgetTest extends BaseTest {

  private static final String DISABLE_TASK_COUNT_SETTING = Variable.DISABLE_TASK_COUNT.getKey();
  private static final String GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/grantOnlyDelegateOwnTasksPermission.ivp";
  private static final String DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL =
      "portalKitTestHelper/14DE09882B540AD5/undoOnlyDelegateOwnTasksPermission.ivp";
  private static final String DENY_DESTROY_TASK_URL = "portalKitTestHelper/14DE09882B540AD5/denyDestroyTaskPermission.ivp";
  private static final String GRANT_DESTROY_TASK_URL = "portalKitTestHelper/14DE09882B540AD5/grantDestroyTaskPermission.ivp";

  private static final String MATERNITY_LEAVE_REQUEST = "Maternity Leave Request";

  private TaskDetailsPage taskDetailsPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
  }

  @Test
  public void testOpenRelatedCaseOfTask() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.openTaskDetails(0);

    CaseDetailsPage casePage = taskWidgetPage.openRelatedCaseOfTask();
    String caseName = casePage.getCaseName();
    casePage.checkCaseName(caseName);
  }

  @Test
  public void testReserveTask() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.checkNameOfTaskAt(0, MATERNITY_LEAVE_REQUEST);
    taskWidgetPage.sideStepMenuOnActionButton(0);
    taskWidgetPage.reserveTask(0);
    taskWidgetPage.isReserveLinkDisabled(0);
    taskWidgetPage.checkTaskState(0, TaskState.OPEN.getValue());

    taskWidgetPage.clickOnTaskStatesAndApply(List.of("Reserved"));
    taskWidgetPage.checkNameOfTaskAt(0, MATERNITY_LEAVE_REQUEST);

    taskWidgetPage.sideStepMenuOnActionButton(0);
    taskWidgetPage.resetTask(0);
    taskWidgetPage.isResetLinkDisabled(0);
    taskWidgetPage.checkTaskState(0, TaskState.OPEN.getValue());

    taskWidgetPage.clickOnTaskStatesAndApply(List.of("Reserved"));
    taskWidgetPage.countTasks().shouldHave(CollectionCondition.size(0));
    
    taskWidgetPage.clickOnTaskStatesAndApply(List.of("Suspended"));
    taskWidgetPage.checkNameOfTaskAt(0, MATERNITY_LEAVE_REQUEST);
  }

  @Test
  public void testStartButtonStatus() {
    login(TestAccount.ADMIN_USER);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();

    taskWidgetPage.filterTasksBy("Annual Leave Request");
    taskWidgetPage.waitTillNameOfFirstTaskToBe("Annual Leave Request");
    taskWidgetPage.isTaskDisabled(0);

    taskWidgetPage.filterTasksBy("Sick Leave Request");
    taskWidgetPage.waitTillNameOfFirstTaskToBe("Sick Leave Request");
//    taskWidgetPage.isTaskEnabled(0);
  }

  @Test
  public void testDisplayDelegateButton() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(GRANT_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();

    taskWidgetPage.filterTasksBy("Sick Leave Request");
    taskWidgetPage.waitTillNameOfFirstTaskToBe("Sick Leave Request");
    taskWidgetPage.isTaskDelegationEnabled(0);

    taskWidgetPage.filterTasksBy("Annual Leave Request");
    taskWidgetPage.waitTillNameOfFirstTaskToBe("Annual Leave Request");
//    taskWidgetPage.isTaskDelegationDisabled(0);

    redirectToRelativeLink(DENY_DELEGATE_OWN_TASK_PERMISSION_PROCESS_URL);
  }

  @Test
  public void testDestroyTask() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(GRANT_DESTROY_TASK_URL);

    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksBy("Annual Leave Request");
    taskWidgetPage.waitTillNameOfFirstTaskToBe("Annual Leave Request");

    taskWidgetPage.isTaskDestroyEnabled(0);
    taskWidgetPage.destroyTask(0);
    taskWidgetPage.confimDestruction();
    taskWidgetPage.checkTaskState(0, TaskState.DESTROYED.getValue());

    redirectToRelativeLink(DENY_DESTROY_TASK_URL);
  }
}
