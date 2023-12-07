package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProcessInformationPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.TaskWidgetPage;

public class ProcessInformationTest extends BaseTest {

  private HomePage homePage;
  private ProcessWidgetPage processWidget;
  private ProcessInformationPage processInformationPage;
  private static String PROCESS_NAME = "Process With Process Steps";
  private static String PROCESS_DESCRIPTION = "Create task for a process with three process steps which can be show in Porcess Information page";
  private static String CUSTOM_INFORMATION_PROCESS_NAME = "CustomProcessInformation";
  private static String CUSTOM_INFORMATION_PROCESS_DESCRIPTION = "Click on More Information to see the customized process information page";

  @Before
  @Override
  public void setup() {
    super.setup();
    homePage = new HomePage();
  }

  @Test
  public void testStartProcessFromProcessInformationPage() {
    navigateToProcessInformationPage(PROCESS_NAME);
    assertEquals(PROCESS_NAME, processInformationPage.getProcessName());
    assertEquals(PROCESS_DESCRIPTION, processInformationPage.getProcessDescription());

    processInformationPage.startProcess();
    homePage = new HomePage();
    TaskWidgetPage taskWidget = homePage.getTaskWidget();
    taskWidget.filterTasksBy(PROCESS_NAME, 1);
    assertEquals(1, taskWidget.countTasks());
  }
  
  @Test
  public void testBackToProcessListFromProcessInformationPage() {
    navigateToProcessInformationPage(PROCESS_NAME);

    processInformationPage.back();
    processWidget = new ProcessWidgetPage();
    assertTrue(processWidget.isDisplayed());
  }
  
  @Test
  public void testBackToCaseDetailsFromProcessInformationPage() {
    login(TestAccount.ADMIN_USER);
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    processWidget.startProcess(PROCESS_NAME);

    homePage = new HomePage();
    CaseWidgetPage caseWidget = homePage.openCaseList();
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

  @Test
  public void testNavigateToCustomProcessInformationPage() {
    navigateToProcessInformationPage(CUSTOM_INFORMATION_PROCESS_NAME);
    assertEquals(CUSTOM_INFORMATION_PROCESS_NAME, processInformationPage.getProcessName());
    assertEquals(CUSTOM_INFORMATION_PROCESS_DESCRIPTION, processInformationPage.getProcessDescription());
  }

  private void navigateToProcessInformationPage(String processName) {
    processWidget = homePage.getProcessWidget();
    processWidget.expand();

    processWidget.clickMoreInformationLink(processName);
    processInformationPage = new ProcessInformationPage();
  }
}
