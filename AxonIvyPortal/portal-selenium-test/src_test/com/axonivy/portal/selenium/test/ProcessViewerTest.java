package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessViewerPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.axonivy.portal.selenium.page.component.ProcessViewerComponentPage;

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
  private CaseWidgetNewDashBoardPage caseWidgetPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testOpenProcessViewerComponent() {
    redirectToRelativeLink(processViewerExampleInFrameUrl);
    ProcessViewerComponentPage processViewerPage = new ProcessViewerComponentPage();
    assertTrue("Leave Request".equalsIgnoreCase(processViewerPage.getProcessRequestPath()));
  }

  @Test
  public void testPermissionForProcessViewerComponent() {
    redirectToRelativeLink(testProcessViewerPermissionUrl);
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    assertTrue("You do not have the required permission to view this process."
        .equalsIgnoreCase(processViewerPage.getErrorMessage()));
  }

  @Test
  public void testOpenProcessViewerFromCase() {
    createTestingTasks();
    caseWidgetPage = NavigationHelper.navigateToCaseList();
    caseWidgetPage.openProcessViewer(0);
    WaitHelper.assertTrueWithWait(() -> caseWidgetPage.countBrowserTab() > 1);
    caseWidgetPage.switchLastBrowserTab();
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    assertTrue("Categoried Leave Request".equalsIgnoreCase(processViewerPage.getProcessRequestPath()));
  }

  @Test
  public void testOpenProcessViewerFromTask() {
    createTestingTasks();
    gotoTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.processViewerDashboardTaskList(0);
    WaitHelper.assertTrueWithWait(() -> newDashboardPage.countBrowserTab() > 1);
    newDashboardPage.switchLastBrowserTab();
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    assertTrue("Categoried Leave Request".equalsIgnoreCase(processViewerPage.getProcessRequestPath()));
  }

  @Test
  public void testCanSeeProcessViewerOptionInCaseAction() {
    createTestingTasks();
    caseWidgetPage = NavigationHelper.navigateToCaseList();
    assertTrue(caseWidgetPage.getActiveCaseActionsInFullCaseListPage(0).texts().contains(PROCESS_VIEWER));
    var caseDetailsPage = caseWidgetPage.openDetailsCase("Leave Request");
    caseDetailsPage.openActionMenu();
    var detailPageSteps = caseDetailsPage.getAvailableActionSteps();
    assertTrue(detailPageSteps.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_ON_CASE_DETAILS_PAGE);
  }

  @Test
  public void testNotShowProcessViewerForTechnicalCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    caseWidgetPage = NavigationHelper.navigateToCaseList();
    assertTrue(caseWidgetPage.getActiveCaseActionsInFullCaseListPage(0).texts().contains(PROCESS_VIEWER),
        PROCESS_VIEWER_IS_NOT_FOUND_IN_CASE_LIST);
    var caseDetailsPage = caseWidgetPage.openDetailsCase("Order Pizza");
    caseDetailsPage.clickRelatedCaseActionButton(0);
    var detailPageSteps = caseDetailsPage.getAvailableActionStepsOfTechnicalCase(0);
    assertTrue(!detailPageSteps.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_FOUND_ON_CASE_DETAILS_PAGE);
  }

  @Test
  public void testCanSeeProcessViewerInTaskAction() {
    createTestingTasks();
    gotoTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    var actions = taskWidget.getActiveTaskActionNames(0);
    assertTrue(actions.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_LIST_PAGE);
    refreshPage();
    var taskDetailsPage = taskWidget.openTaskDetailsPageByAction(0);
    var detailActions = taskDetailsPage.getActiveTaskAction();
    assertTrue(detailActions.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_DETAILS_PAGE);
  }

  @Test
  public void testProcessViewerPermissionInCaseAction() {
    redirectToRelativeLink(processViewerPermissionExampleUrl);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    caseWidgetPage = NavigationHelper.navigateToCaseList();
    assertFalse(caseWidgetPage.getActiveCaseActionsInFullCaseListPage(0).texts().contains(PROCESS_VIEWER),
        PROCESS_VIEWER_IS_FOUND_IN_CASE_LIST);
    var caseDetailsPage = caseWidgetPage.openDetailsCase("Process Viewer Permission Example Case");
    caseDetailsPage.openActionMenu();
    var detailPageSteps = caseDetailsPage.getAvailableActionSteps();
    assertTrue(!detailPageSteps.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_FOUND_ON_CASE_DETAILS_PAGE);
  }

  @Test
  public void testProcessViewerPermissionInTaskAction() {
    redirectToRelativeLink(processViewerPermissionExampleUrl);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    gotoTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    var actions = taskWidget.getActiveTaskActionNames(0);
    assertTrue(!actions.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_FOUND_ON_TASK_LIST_PAGE);
    refreshPage();
    var taskDetailsPage = taskWidget.openTaskDetailsPageByAction(0);
    var detailActions = taskDetailsPage.getActiveTaskAction();
    assertTrue(!detailActions.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_FOUND_ON_TASK_DETAILS_PAGE);
  }

  @Test
  public void testCaseMapViewerPermissionInCaseAction() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    caseWidgetPage = NavigationHelper.navigateToCaseList();
    assertTrue(caseWidgetPage.getActiveCaseActionsInFullCaseListPage(0).texts().contains(PROCESS_VIEWER),
        PROCESS_VIEWER_IS_NOT_FOUND_IN_CASE_LIST);
    var caseDetailsPage = caseWidgetPage.openDetailsCase("Business Case Map: Leave Request");
    caseDetailsPage.openActionMenu();
    var detailPageSteps = caseDetailsPage.getAvailableActionSteps();
    assertTrue(detailPageSteps.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_ON_CASE_DETAILS_PAGE);
  }

  @Test
  public void testCaseMapViewerPermissionInTaskAction() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    gotoTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    var actions = taskWidget.getActiveTaskActionNames(0);
    assertTrue(actions.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_LIST_PAGE);
    refreshPage();
    var taskDetailsPage = taskWidget.openTaskDetailsPageByAction(0);
    var detailActions = taskDetailsPage.getActiveTaskAction();
    assertTrue(detailActions.contains(PROCESS_VIEWER), PROCESS_VIEWER_IS_NOT_FOUND_ON_TASK_DETAILS_PAGE);
  }

  private void gotoTaskList() {
    newDashboardPage = new NewDashboardPage();
    NavigationHelper.navigateToTaskList();
  }
}
