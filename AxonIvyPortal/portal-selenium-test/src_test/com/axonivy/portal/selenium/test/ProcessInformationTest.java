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
  private static final String CASE_DETAILS_TITLE = "Case Details - Portal - Axon Ivy";

  private ProcessWidgetPage processWidget;
  private ProcessInformationPage processInformationPage;
  private static String PROCESS_NAME = "Process With Process Steps";
  private static String PROCESS_DESCRIPTION =
      "Create task for a process with three process steps which can be show in Porcess Information page";

  @BeforeEach
  @Override
  public void setup() {
    super.setup();
  }

  @Test
  public void testStartProcessFromProcessInformationPage() {
    navigateToProcessInformationPage();
    assertEquals(PROCESS_NAME, processInformationPage.getProcessName());
    assertEquals(PROCESS_DESCRIPTION, processInformationPage.getProcessDescription());

    processInformationPage.startProcess();
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
    processWidget = NavigationHelper.navigateToProcessList();
    processWidget.waitForStartListShow();
    processWidget.findProcess(PROCESS_NAME);
    processWidget = new ProcessWidgetPage();
    processWidget.startProcess(PROCESS_NAME);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGlobalGrowlDisappear();

    CaseWidgetPage caseWidget = NavigationHelper.navigateToCaseList();
    CaseDetailsPage caseDetails = caseWidget.openCaseDetailsFromActionMenuByCaseName(PROCESS_NAME);
    caseDetails.openActionMenu();
    caseDetails.openProcessOverviewPage();

    processInformationPage = new ProcessInformationPage();
    assertEquals(PROCESS_NAME, processInformationPage.getProcessName());
    assertEquals(PROCESS_DESCRIPTION, processInformationPage.getProcessDescription());

    processInformationPage.back();
    caseDetails = new CaseDetailsPage();
    assertEquals(CASE_DETAILS_TITLE, caseDetails.getPageTitle());
    assertEquals(PROCESS_NAME, caseDetails.getDescription());
  }

  private void navigateToProcessInformationPage() {
    String processName = "Process With Process Steps";
    processWidget = NavigationHelper.navigateToProcessList();

    processWidget.clickMoreInformationLink(processName);
    processInformationPage = new ProcessInformationPage();
  }

  /**
   * ticket: IVYPORTAL-16086-More Information Process Page
   * fix version 11.3 -> not on LTS -> delete testClickOnCustomProcessInformationPage
   */
}
