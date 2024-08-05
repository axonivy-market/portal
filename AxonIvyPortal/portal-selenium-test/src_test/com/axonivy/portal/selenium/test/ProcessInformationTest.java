package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessInformationPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest
public class ProcessInformationTest extends BaseTest {

  private NewDashboardPage newDashboardPage;
  private ProcessWidgetPage processWidget;
  private ProcessInformationPage processInformationPage;
  private static String PROCESS_NAME = "Process With Process Steps";
  private static String PROCESS_DESCRIPTION =
      "Create task for a process with three process steps which can be show in Porcess Information page";

  @BeforeEach
  @Override
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testStartProcessFromProcessInformationPage() {
    navigateToProcessInformationPage();
    assertEquals(PROCESS_NAME, processInformationPage.getProcessName());
    assertEquals(PROCESS_DESCRIPTION, processInformationPage.getProcessDescription());

    processInformationPage.startProcess();
    newDashboardPage = new NewDashboardPage();
    TaskWidgetPage taskWidget = NavigationHelper.navigateToTaskList();
    taskWidget.filterTasksInExpandedModeBy(PROCESS_NAME, 1);
    assertEquals(1, taskWidget.countTasks().size());
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
    NewDashboardPage newDashboard = new NewDashboardPage();
    processWidget = NavigationHelper.navigateToProcessList();
    /**
     * Note: I change the way we click on process
     * the theme switcher is there to make sure the search completed
     * before we start the process
     */
    processWidget.findProcess(PROCESS_NAME);
    newDashboard.clickThemeSwitcher();
    processWidget.clickStart();

    CaseWidgetPage caseWidget = NavigationHelper.navigateToCaseList();
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

  /**
   * Test below seems don't exist in LTS
   */
//  @Test
//  public void testClickOnCustomProcessInformationPage() {
//    navigateToCustomProcessInformationPage();
//    assertEquals(CUSTOM_PROCESS_NAME, processInformationPage.getProcessName());
//    assertEquals(CUSTOM_PROCESS_DESCRIPTION, processInformationPage.getProcessDescription());
//    assertEquals(CUSTOM_PROCESS_INFORMATION_HEADER, processInformationPage.getProcessInfoWrapperContent());
//
//    processInformationPage.back();
//  }
//  
//
//  private void navigateToCustomProcessInformationPage() {
//    String processName = CUSTOM_PROCESS_NAME;
//    processWidget = NavigationHelper.navigateToProcessList();
//
//    processWidget.findProcess(CUSTOM_PROCESS_NAME);
//    processWidget.clickMoreInformationLink(processName);
//    processInformationPage = new ProcessInformationPage();
//  }
}
