package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessInformationPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class ProcessInformationTest extends BaseTest {

  private static final String CASE_DETAILS_PAGE_TITLE = "Case Details - Portal - Axon Ivy";
  private NewDashboardPage newDashboardPage;
  private ProcessWidgetPage processWidget;
  private ProcessInformationPage processInformationPage;
  private static String PROCESS_NAME = "Process With Process Steps";
  private static String PROCESS_DESCRIPTION =
      "Create task for a process with three process steps which can be show in Porcess Information page";

  private static String CUSTOM_PROCESS_NAME = "Custom Process Information";
  private static String CUSTOM_PROCESS_DESCRIPTION = "Click on More Information to see the customized process information page.";
  private static String CUSTOM_PROCESS_INFORMATION_HEADER = "Hello world example";

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
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, PROCESS_NAME);
    taskWidget.addFilter("State", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Open");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(1));
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
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.openCaseList();
    CaseDetailsPage caseDetails = caseWidget.openDetailsCase(PROCESS_NAME);
    caseDetails.openActionMenu();
    caseDetails.openProcessOverviewPage();

    processInformationPage = new ProcessInformationPage();
    assertEquals(PROCESS_NAME, processInformationPage.getProcessName());
    assertEquals(PROCESS_DESCRIPTION, processInformationPage.getProcessDescription());

    processInformationPage.back();
    caseDetails = new CaseDetailsPage();
    assertEquals(CASE_DETAILS_PAGE_TITLE, caseDetails.getPageTitle());
    assertEquals(PROCESS_NAME, caseDetails.getDescription());
  }

  private void navigateToProcessInformationPage() {
    String processName = "Process With Process Steps";
    processWidget = NavigationHelper.navigateToProcessList();

    processWidget.clickMoreInformationLink(processName);
    processInformationPage = new ProcessInformationPage();
  }

  @Test
  public void testClickOnCustomProcessInformationPage() {
    navigateToCustomProcessInformationPage();
    assertEquals(CUSTOM_PROCESS_NAME, processInformationPage.getProcessName());
    assertEquals(CUSTOM_PROCESS_DESCRIPTION, processInformationPage.getProcessDescription());
    assertEquals(CUSTOM_PROCESS_INFORMATION_HEADER, processInformationPage.getProcessInfoWrapperContent());

    processInformationPage.back();
  }
  

  private void navigateToCustomProcessInformationPage() {
    String processName = CUSTOM_PROCESS_NAME;
    processWidget = NavigationHelper.navigateToProcessList();

    processWidget.clickMoreInformationLink(processName);
    processInformationPage = new ProcessInformationPage();
  }
}
