package portal.guitest.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProcessViewerPage;
import portal.guitest.page.TaskWidgetPage;

public class ProcessViewerTest extends BaseTest {

  private HomePage homePage;
  private CaseWidgetPage caseWidgetPage;
  private TaskWidgetPage taskWidgetPage;

  @Override
  @Before
  public void setup() {
    super.setup();
  }

  @Test
  public void testOpenProcessViewerFromCase() {
    createTestingTasks();
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    caseWidgetPage.clickOnProcessViewerOption();
    WaitHelper.assertTrueWithWait(() -> homePage.countBrowserTab() > 1);
    homePage.switchLastBrowserTab();
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    processViewerPage.getProcessRequestPath();
    assertTrue(processViewerPage.getProcessRequestPath().equalsIgnoreCase("CategoriedLeaveRequest.ivp"));
  }

  @Test
  public void testOpenProcessViewerFromTask() {
    createTestingTasks();
    gotoTaskList();
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.clickOnProcessViewerOption();
    WaitHelper.assertTrueWithWait(() -> homePage.countBrowserTab() > 1);
    homePage.switchLastBrowserTab();
    ProcessViewerPage processViewerPage = new ProcessViewerPage();
    processViewerPage.getProcessRequestPath();
    assertTrue(processViewerPage.getProcessRequestPath().equalsIgnoreCase("CategoriedLeaveRequest.ivp"));
  }

  @Test
  public void testCanSeeProcessViewerOptionInCaseAction() {
    createTestingTasks();
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    var steps = caseWidgetPage.getAvailableActionSteps();
    assertTrue("Process Viewer is NOT found in CaseList", steps.contains("Process Viewer"));

    var caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Leave Request");
    caseDetailsPage.openActionMenu();
    var detailPageSteps = caseDetailsPage.getAvailableActionSteps();
    assertTrue("Process Viewer is NOT found on CaseDetails page", detailPageSteps.contains("Process Viewer"));
  }

  @Test
  public void testNotShowProcessViewerForTechnicalCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    var steps = caseWidgetPage.getAvailableActionSteps();
    assertTrue("Process Viewer is NOT found in CaseList", steps.contains("Process Viewer"));

    var caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Order Pizza");
    caseDetailsPage.clickRelatedCaseActionButton(0);
    var detailPageSteps = caseDetailsPage.getAvailableActionStepsOfTechnicalCase(0);
    assertTrue("Process Viewer is found on CaseDetails page", !detailPageSteps.contains("Process Viewer"));
  }

  @Test
  public void testNotShowProcessViewerForExpressCase() {
    login(TestAccount.ADMIN_USER);
    var expressManagementTest = new ExpressManagementTest();
    expressManagementTest.prepareExpressWorkflowStep();
    expressManagementTest.executePromoteResourceTask();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    gotoCaseList();
    caseWidgetPage.openActionStepMenu();
    var steps = caseWidgetPage.getAvailableActionSteps();
    assertTrue("Process Viewer is found in CaseList", !steps.contains("Process Viewer"));

    var caseDetailsPage = caseWidgetPage.openDetailsOfCaseHasName("Request new Resources - Express process");
    caseDetailsPage.openActionMenu();
    var detailPageSteps = caseDetailsPage.getAvailableActionSteps();
    assertTrue("Process Viewer is found on CaseDetails page", !detailPageSteps.contains("Process Viewer"));
  }

  @Test
  public void testCanSeeProcessViewerInTaskAction() {
    createTestingTasks();
    gotoTaskList();
    var actions = taskWidgetPage.getActiveTaskAction(0);
    assertTrue("Process Viewer is NOT found on TaskList page", actions.contains("Process Viewer"));
    taskWidgetPage.refresh();
    var taskDetailsPage = taskWidgetPage.openTaskDetailsFromActionMenu(0);
    var detailActions = taskDetailsPage.getActiveTaskAction();
    assertTrue("Process Viewer is NOT found on TaskList page", detailActions.contains("Process Viewer"));
  }


  private CaseWidgetPage gotoCaseList() {
    homePage = new HomePage();
    var mainMenuPage = homePage.openMainMenu();
    caseWidgetPage = mainMenuPage.selectCaseMenu();
    return caseWidgetPage;
  }

  private void gotoTaskList() {
    homePage = new HomePage();
    taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
  }
}
