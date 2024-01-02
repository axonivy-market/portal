package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.ProcessInformationPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.TaskWidgetPage;

public class ProcessInformationTest extends BaseTest {

  private NewDashboardPage newDashboardPage;
  private ProcessWidgetPage processWidget;
  private ProcessInformationPage processInformationPage;
  private static String PROCESS_NAME = "Process With Process Steps";
  private static String PROCESS_DESCRIPTION = "Create task for a process with three process steps which can be show in Porcess Information page";

  private static String CUSTOM_PROCESS_NAME = "CustomProcessInformation";
  private static String CUSTOM_PROCESS_DESCRIPTION = "Click on More Information to see the customized process information page";
  private static String CUSTOM_PROCESS_INFORMATION_HEADER = "Hello world example";

  @Before
  @Override
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testStartProcessFromProcessInformationPage() {
    navigateToProcessInformationPage();
    assertEquals(PROCESS_NAME, processInformationPage.getProcessName());
    assertEquals(PROCESS_DESCRIPTION , processInformationPage.getProcessDescription());
    processInformationPage.startProcess();
    newDashboardPage = new NewDashboardPage();
    TaskWidgetPage taskWidget = NavigationHelper.navigateToTaskList();
    taskWidget.filterTasksInExpandedModeBy(PROCESS_NAME, 1);
    assertEquals(1, taskWidget.countTasks());
  }
  
  @Test
  public void testClickOnCustomProcessInformationPage() {
    navigateToCustomProcessInformationPage();
    assertEquals(CUSTOM_PROCESS_NAME, processInformationPage.getProcessName());
    assertEquals(CUSTOM_PROCESS_DESCRIPTION , processInformationPage.getProcessDescription());
    
    assertEquals(CUSTOM_PROCESS_INFORMATION_HEADER, processInformationPage.getProcessInfoWrapperContent());
  }
  
  @Test
  public void testBackToProcessListFromProcessInformationPage() {
    navigateToProcessInformationPage();

    processInformationPage.back();
    processWidget = new ProcessWidgetPage();
    assertTrue(processWidget.isDisplayed());
  }
  
  @Test
  public void testBackToCaseDetailsFromProcessInformationPage() {
    login(TestAccount.ADMIN_USER);
    processWidget = NavigationHelper.navigateToProcessList();
    processWidget.startProcess(PROCESS_NAME);

    newDashboardPage = new NewDashboardPage();
    CaseWidgetPage caseWidget = newDashboardPage.openCaseList();
    CaseDetailsPage caseDetails = caseWidget.openCaseDetailsFromActionMenuByCaseName(PROCESS_NAME);
    caseDetails.openActionMenu();
    caseDetails.openProcessOverviewPage();

    processInformationPage = new ProcessInformationPage();
    assertEquals(PROCESS_NAME, processInformationPage.getProcessName());
    assertEquals(PROCESS_DESCRIPTION, processInformationPage.getProcessDescription());

    processInformationPage.back();
    caseDetails = new CaseDetailsPage();
    assertEquals("Case Details", caseDetails.getPageTitle());
    assertEquals(PROCESS_NAME, caseDetails.getDescription());
  }

  private void navigateToProcessInformationPage() {
    String processName = "Process With Process Steps";
    processWidget = NavigationHelper.navigateToProcessList();

    processWidget.clickMoreInformationLink(processName);
    processInformationPage = new ProcessInformationPage();
  }

  private void navigateToCustomProcessInformationPage() {
    String processName = CUSTOM_PROCESS_NAME ;
    processWidget = NavigationHelper.navigateToProcessList();

    processWidget.clickMoreInformationLink(processName);
    processInformationPage = new ProcessInformationPage();
  }
}
