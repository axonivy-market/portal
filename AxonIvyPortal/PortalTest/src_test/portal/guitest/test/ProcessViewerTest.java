package portal.guitest.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.ProcessViewerComponentPage;
import portal.guitest.page.ProcessViewerPage;
import portal.guitest.page.TaskWidgetPage;

public class ProcessViewerTest extends BaseTest {

  private static final String PROCESS_VIEWER_IS_FOUND_ON_TASK_LIST_PAGE = "Process Viewer is found on TaskList page";
  private static final String PROCESS_VIEWER_IS_FOUND_ON_TASK_DETAILS_PAGE = "Process Viewer is found on CaseDetails page";
  private static final String PROCESS_VIEWER_IS_FOUND_IN_CASE_LIST = "Process Viewer is found in CaseList";
  private static final String PROCESS_VIEWER_IS_FOUND_ON_CASE_DETAILS_PAGE = "Process Viewer is found on CaseDetails page";
  private static final String PROCESS_VIEWER_IS_NOT_FOUND_IN_CASE_LIST = "Process Viewer is NOT found in CaseList";
  private static final String PROCESS_VIEWER_IS_NOT_FOUND_ON_CASE_DETAILS_PAGE = "Process Viewer is NOT found on CaseDetails page";
  private static final String PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_LIST_PAGE = "Process Viewer is NOT found on TaskList page";
  private static final String PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_DETAILS_PAGE = "Process Viewer is NOT found on TaskDetails page";
  private static final String PROCESS_VIEWER = "Process Viewer";
  private NewDashboardPage2 newDashboardPage2;
  private CaseWidgetPage caseWidgetPage;
  private TaskWidgetPage taskWidgetPage;

  @Override
  @Before
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
    assertTrue(processViewerPage.getErrorMessage().equalsIgnoreCase("You do not have the required permission to view this process."));
  }

  @Test
  public void testOpenProcessViewerFromCase() {
    createTestingTasks();
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    caseWidgetPage.clickOnProcessViewerOption();
    WaitHelper.assertTrueWithWait(() -> newDashboardPage2.countBrowserTab() > 1);
    newDashboardPage2.switchLastBrowserTab();
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    assertTrue(processViewerPage.getProcessRequestPath().equalsIgnoreCase("Categoried Leave Request"));
  }

  @Test
  public void testOpenProcessViewerFromTask() {
    createTestingTasks();
    gotoTaskList();
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.clickOnProcessViewerOption();
    WaitHelper.assertTrueWithWait(() -> newDashboardPage2.countBrowserTab() > 1);
    newDashboardPage2.switchLastBrowserTab();
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    assertTrue(processViewerPage.getProcessRequestPath().equalsIgnoreCase("Categoried Leave Request"));
  }

  @Test
  public void testCanSeeProcessViewerOptionInCaseAction() {
    createTestingTasks();
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    var steps = caseWidgetPage.getAvailableActionSteps();
    assertTrue(PROCESS_VIEWER_IS_NOT_FOUND_IN_CASE_LIST, steps.contains(PROCESS_VIEWER));

    var caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    caseDetailsPage.openActionMenu();
    var detailPageSteps = caseDetailsPage.getAvailableActionSteps();
    assertTrue(PROCESS_VIEWER_IS_NOT_FOUND_ON_CASE_DETAILS_PAGE, detailPageSteps.contains(PROCESS_VIEWER));
  }

  @Test
  public void testNotShowProcessViewerForTechnicalCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    var steps = caseWidgetPage.getAvailableActionSteps();
    assertTrue(PROCESS_VIEWER_IS_NOT_FOUND_IN_CASE_LIST, steps.contains(PROCESS_VIEWER));

    var caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Order Pizza");
    caseDetailsPage.clickRelatedCaseActionButton(0);
    var detailPageSteps = caseDetailsPage.getAvailableActionStepsOfTechnicalCase(0);
    assertTrue(PROCESS_VIEWER_IS_FOUND_ON_CASE_DETAILS_PAGE, !detailPageSteps.contains(PROCESS_VIEWER));
  }

  @Test
  public void testNotShowProcessViewerForExpressCase() {
    login(TestAccount.ADMIN_USER);
    var expressManagementTest = new ExpressManagementTest();
    expressManagementTest.prepareExpressWorkflowStep();
    expressManagementTest.executePromoteResourceTask();
    redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    var steps = caseWidgetPage.getAvailableActionSteps();
    assertTrue(PROCESS_VIEWER_IS_FOUND_IN_CASE_LIST, !steps.contains(PROCESS_VIEWER));

    var caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Request new Resources - Express process");
    caseDetailsPage.openActionMenu();
    var detailPageSteps = caseDetailsPage.getAvailableActionSteps();
    assertTrue(PROCESS_VIEWER_IS_FOUND_ON_CASE_DETAILS_PAGE, !detailPageSteps.contains(PROCESS_VIEWER));
  }

  @Test
  public void testCanSeeProcessViewerInTaskAction() {
    createTestingTasks();
    gotoTaskList();
    var actions = taskWidgetPage.getActiveTaskAction(0);
    assertTrue(PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_LIST_PAGE, actions.contains(PROCESS_VIEWER));
    taskWidgetPage.refresh();
    var taskDetailsPage = taskWidgetPage.openTaskDetailsFromActionMenu(0);
    var detailActions = taskDetailsPage.getActiveTaskAction();
    assertTrue(PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_DETAILS_PAGE, detailActions.contains(PROCESS_VIEWER));
  }

  @Test
  public void testProcessViewerPermissionInCaseAction() {
    redirectToRelativeLink(processViewerPermissionExampleUrl);
    redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    var steps = caseWidgetPage.getAvailableActionSteps();
    assertTrue(PROCESS_VIEWER_IS_FOUND_IN_CASE_LIST, !steps.contains(PROCESS_VIEWER));

    var caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Process Viewer Permission Example Case");
    caseDetailsPage.openActionMenu();
    var detailPageSteps = caseDetailsPage.getAvailableActionSteps();
    assertTrue(PROCESS_VIEWER_IS_FOUND_ON_CASE_DETAILS_PAGE, !detailPageSteps.contains(PROCESS_VIEWER));
  }

  @Test
  public void testProcessViewerPermissionInTaskAction() {
    redirectToRelativeLink(processViewerPermissionExampleUrl);
    redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    gotoTaskList();
    var actions = taskWidgetPage.getActiveTaskAction(0);
    assertTrue(PROCESS_VIEWER_IS_FOUND_ON_TASK_LIST_PAGE, !actions.contains(PROCESS_VIEWER));
    taskWidgetPage.refresh();
    var taskDetailsPage = taskWidgetPage.openTaskDetailsFromActionMenu(0);
    var detailActions = taskDetailsPage.getActiveTaskAction();
    assertTrue(PROCESS_VIEWER_IS_FOUND_ON_TASK_DETAILS_PAGE, !detailActions.contains(PROCESS_VIEWER));
  }

  @Test
  public void testCaseMapViewerPermissionInCaseAction() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    var steps = caseWidgetPage.getAvailableActionSteps();
    assertTrue(PROCESS_VIEWER_IS_NOT_FOUND_IN_CASE_LIST, steps.contains(PROCESS_VIEWER));

    var caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Business Case Map: Leave Request");
    caseDetailsPage.openActionMenu();
    var detailPageSteps = caseDetailsPage.getAvailableActionSteps();
    assertTrue(PROCESS_VIEWER_IS_NOT_FOUND_ON_CASE_DETAILS_PAGE, detailPageSteps.contains(PROCESS_VIEWER));
  }

  @Test
  public void testCaseMapViewerPermissionInTaskAction() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    gotoTaskList();
    var actions = taskWidgetPage.getActiveTaskAction(0);
    assertTrue(PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_LIST_PAGE, actions.contains(PROCESS_VIEWER));
    taskWidgetPage.refresh();
    var taskDetailsPage = taskWidgetPage.openTaskDetailsFromActionMenu(0);
    var detailActions = taskDetailsPage.getActiveTaskAction();
    assertTrue(PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_DETAILS_PAGE, detailActions.contains(PROCESS_VIEWER));
  }
  
  private CaseWidgetPage gotoCaseList() {
    newDashboardPage2 = new NewDashboardPage2();
    var mainMenuPage = newDashboardPage2.openMainMenu();
    caseWidgetPage = mainMenuPage.selectCaseMenu();
    return caseWidgetPage;
  }

  private void gotoTaskList() {
    newDashboardPage2 = new NewDashboardPage2();
    NavigationHelper.navigateToTasList();
  }
}
