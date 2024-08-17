package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessViewerPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.component.ProcessViewerComponentPage;
import com.axonivy.portal.selenium.test.express.ExpressManagementTest;

@IvyWebTest
public class ProcessViewerTest extends BaseTest {

  private static final String PROCESS_VIEWER_IS_FOUND_ON_TASK_LIST_PAGE = "Process Viewer is found on TaskList page";
  private static final String PROCESS_VIEWER_IS_FOUND_ON_TASK_DETAILS_PAGE =
      "Process Viewer is found on CaseDetails page";
  private static final String PROCESS_VIEWER_IS_FOUND_IN_CASE_LIST = "Process Viewer is found in CaseList";
  private static final String PROCESS_VIEWER_IS_FOUND_ON_CASE_DETAILS_PAGE =
      "Process Viewer is found on CaseDetails page";
  private static final String PROCESS_VIEWER_IS_NOT_FOUND_IN_CASE_LIST = "Process Viewer is NOT found in CaseList";
  private static final String PROCESS_VIEWER_IS_NOT_FOUND_ON_CASE_DETAILS_PAGE =
      "Process Viewer is NOT found on CaseDetails page";
  private static final String PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_LIST_PAGE =
      "Process Viewer is NOT found on TaskList page";
  private static final String PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_DETAILS_PAGE =
      "Process Viewer is NOT found on TaskDetails page";
  private static final String PROCESS_VIEWER = "Process Viewer";
  private NewDashboardPage newDashboardPage;
  private CaseWidgetPage caseWidgetPage;
  private TaskWidgetPage taskWidgetPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testOpenProcessViewerComponent() {
    redirectToRelativeLink(processViewerExampleInFrameUrl);
    ProcessViewerComponentPage processViewerPage = new ProcessViewerComponentPage();
    assertTrue(processViewerPage.getProcessRequestPath().equalsIgnoreCase("Leave Request"));
  }

  @Test
  public void testPermissionForProcessViewerComponent() {
    redirectToRelativeLink(testProcessViewerPermissionUrl);
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    assertTrue(processViewerPage.getErrorMessage()
        .equalsIgnoreCase("You do not have the required permission to view this process."));
  }

  @Test
  public void testOpenProcessViewerFromCase() {
    createTestingTasks();
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    caseWidgetPage.clickOnProcessViewerOption();
    WaitHelper.assertTrueWithWait(() -> newDashboardPage.countBrowserTab() > 1);
    newDashboardPage.switchLastBrowserTab();
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    assertTrue(processViewerPage.getProcessRequestPath().equalsIgnoreCase("Categoried Leave Request"));
  }

  @Test
  public void testOpenProcessViewerFromTask() {
    createTestingTasks();
    gotoTaskList();
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.clickOnProcessViewerOption();
    WaitHelper.assertTrueWithWait(() -> newDashboardPage.countBrowserTab() > 1);
    newDashboardPage.switchLastBrowserTab();
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    assertTrue(processViewerPage.getProcessRequestPath().equalsIgnoreCase("Categoried Leave Request"));
  }

  @Test
  public void testCanSeeProcessViewerOptionInCaseAction() {
    createTestingTasks();
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    var steps = caseWidgetPage.getAvailableActionSteps();
    assertTrue(steps.contains(PROCESS_VIEWER));

    var caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    caseDetailsPage.openActionMenu();
    var detailPageSteps = caseDetailsPage.getAvailableActionSteps();
    assertTrue(detailPageSteps.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_ON_CASE_DETAILS_PAGE);
  }

  @Test
  public void testNotShowProcessViewerForTechnicalCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToNewDashBoard();
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    var steps = caseWidgetPage.getAvailableActionSteps();
    assertTrue(steps.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_IN_CASE_LIST);

    var caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Order Pizza");
    caseDetailsPage.clickRelatedCaseActionButton(0);
    var detailPageSteps = caseDetailsPage.getAvailableActionStepsOfTechnicalCase(0);
    assertTrue(!detailPageSteps.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_FOUND_ON_CASE_DETAILS_PAGE);
  }

  @Test
  public void testNotShowProcessViewerForExpressCase() {
    login(TestAccount.ADMIN_USER);
    var expressManagementTest = new ExpressManagementTest();
    expressManagementTest.prepareExpressWorkflowStep();
    expressManagementTest.executePromoteResourceTask();
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    var steps = caseWidgetPage.getAvailableActionSteps();
    assertTrue(!steps.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_FOUND_IN_CASE_LIST);

    var caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Request new Resources - Express process");
    caseDetailsPage.openActionMenu();
    var detailPageSteps = caseDetailsPage.getAvailableActionSteps();
    assertTrue(!detailPageSteps.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_FOUND_ON_CASE_DETAILS_PAGE);
  }

  @Test
  public void testCanSeeProcessViewerInTaskAction() {
    createTestingTasks();
    gotoTaskList();
    var actions = taskWidgetPage.getActiveTaskAction(0);
    assertTrue(actions.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_LIST_PAGE);
    refreshPage();
    var taskDetailsPage = taskWidgetPage.openTaskDetailsFromActionMenu(0);
    var detailActions = taskDetailsPage.getActiveTaskAction();
    assertTrue(detailActions.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_DETAILS_PAGE);
  }

  @Test
  public void testProcessViewerPermissionInCaseAction() {
    redirectToRelativeLink(processViewerPermissionExampleUrl);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    var steps = caseWidgetPage.getAvailableActionSteps();
    assertTrue(!steps.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_FOUND_IN_CASE_LIST);

    var caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Process Viewer Permission Example Case");
    caseDetailsPage.openActionMenu();
    var detailPageSteps = caseDetailsPage.getAvailableActionSteps();
    assertTrue(!detailPageSteps.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_FOUND_ON_CASE_DETAILS_PAGE);
  }

  @Test
  public void testProcessViewerPermissionInTaskAction() {
    redirectToRelativeLink(processViewerPermissionExampleUrl);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    gotoTaskList();
    var actions = taskWidgetPage.getActiveTaskAction(0);
    assertTrue(!actions.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_FOUND_ON_TASK_LIST_PAGE);
    refreshPage();
    var taskDetailsPage = taskWidgetPage.openTaskDetailsFromActionMenu(0);
    var detailActions = taskDetailsPage.getActiveTaskAction();
    assertTrue(!detailActions.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_FOUND_ON_TASK_DETAILS_PAGE);
  }

  @Test
  public void testCaseMapViewerPermissionInCaseAction() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForTaskListDisplay();
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    var steps = caseWidgetPage.getAvailableActionSteps();
    assertTrue(steps.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_IN_CASE_LIST);

    var caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Business Case Map: Leave Request");
    caseDetailsPage.openActionMenu();
    var detailPageSteps = caseDetailsPage.getAvailableActionSteps();
    assertTrue(detailPageSteps.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_ON_CASE_DETAILS_PAGE);
  }

  @Test
  public void testCaseMapViewerPermissionInTaskAction() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    gotoTaskList();
    var actions = taskWidgetPage.getActiveTaskAction(0);
    assertTrue(actions.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_LIST_PAGE);
    refreshPage();
    var taskDetailsPage = taskWidgetPage.openTaskDetailsFromActionMenu(0);
    var detailActions = taskDetailsPage.getActiveTaskAction();
    assertTrue(detailActions.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_DETAILS_PAGE);
  }

  private CaseWidgetPage gotoCaseList() {
    newDashboardPage = new NewDashboardPage();
    var mainMenuPage = newDashboardPage.openMainMenu();
    caseWidgetPage = mainMenuPage.selectCaseMenu();
    return caseWidgetPage;
  }

  private void gotoTaskList() {
    newDashboardPage = new NewDashboardPage();
    taskWidgetPage = NavigationHelper.navigateToTaskList();
  }
}
