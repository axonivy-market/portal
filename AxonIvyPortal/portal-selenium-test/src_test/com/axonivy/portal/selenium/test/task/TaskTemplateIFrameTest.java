package com.axonivy.portal.selenium.test.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest
public class TaskTemplateIFrameTest extends BaseTest {

  private static final String CUSTOM_PARAMS_TEMPLATE_TASK_URL = "portal-developer-examples/1718293B3F6E5478/start.ivp";
  private static final String IFRAME_TASK_URL =
      "portal-developer-examples/16E5DB746865BCEC/CreateInvestment.ivp?embedInFrame";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testCustomParamsForIFrameTaskTemplate() {
    redirectToRelativeLink(CUSTOM_PARAMS_TEMPLATE_TASK_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy("IFrame task with custom params");
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTaskWithouWaitForTaskActionPresent(0);
    assertFalse(taskTemplatePage.isTaskNameDisplayed());
    assertFalse(taskTemplatePage.isTaskActionDisplayed());
    assertFalse(taskTemplatePage.isCaseInfoButtonDisplayed());
  }

  @Test
  public void testDisplayWarningInIFrameTaskTemplate() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    waitForTemplateRender();
    final TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.clickOnLogo();
    By leaveButton = By.id("task-leave-warning-component:leave-button");
    WaitHelper.assertTrueWithWait(() -> taskTemplatePage.isElementDisplayed(leaveButton));
  }

  @Test
  public void testNotDisplayWarningInIFrameTaskTemplate() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    waitForTemplateRender();
    TaskTemplatePage taskTemplatePage1 = new TaskTemplatePage();
    TaskWidgetPage taskWidgetPage = taskTemplatePage1.finishCreateInvestmentTask();
    taskWidgetPage.filterTasksInExpandedModeBy("Approve Investment", 1);
    TaskTemplatePage taskTemplatePage2 = taskWidgetPage.startTask(0);
    taskTemplatePage2.clickOnLogo();
    new NewDashboardPage();
  }

  @Test
  public void testRedirectToApplicationHome() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    redirectToRelativeLink(IFRAME_TASK_URL);
    waitForTemplateRender();
    TaskTemplatePage taskTemplatePage1 = new TaskTemplatePage();
    TaskWidgetPage taskWidgetPage = taskTemplatePage1.finishCreateInvestmentTask();
    taskWidgetPage.filterTasksInExpandedModeBy("Approve Investment", 1);
    TaskTemplatePage taskTemplatePage2 = taskWidgetPage.startTask(0);
    taskTemplatePage2.backToHomeInIFrameApprovalTask();
    new NewDashboardPage();
  }

  @Test
  public void testStickyTaskList() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    waitForTemplateRender();
    TaskTemplatePage taskTemplatePage1 = new TaskTemplatePage();
    TaskWidgetPage taskWidgetPage1 = taskTemplatePage1.finishCreateInvestmentTask();
    taskWidgetPage1 = taskWidgetPage1.openTaskList();
    taskWidgetPage1.filterTasksInExpandedModeBy("Approve Investment", 1);
    TaskTemplatePage taskTemplatePage2 = taskWidgetPage1.startTask(0);
    TaskWidgetPage taskWidgetPage2 = taskTemplatePage2.finishIFrameReviewTask();
    WaitHelper
        .assertTrueWithWait(() -> taskWidgetPage2.isElementDisplayed(By.cssSelector("[id$='task-config-command']")));
  }

  public void waitForTemplateRender() {
    WaitHelper.waitForPresenceOfElementLocatedInFrame("[class$='task-template-container']");
  }

  @Test
  public void testTextOutIFrameChangeWithSkipTaskList() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    waitForTemplateRender();
    TaskTemplatePage taskTemplatePage1 = new TaskTemplatePage();
    TaskWidgetPage taskWidgetPage1 = taskTemplatePage1.finishCreateInvestmentTask();
    taskWidgetPage1 = taskWidgetPage1.openTaskList();
    taskWidgetPage1.filterTasksInExpandedModeBy("Approve Investment", 1);
    TaskTemplatePage taskTemplatePage2 = taskWidgetPage1.startTask(0);
    assertEquals("Review Request (Skip Tasklist in IFrame)",
        taskTemplatePage2.getTaskNameOutsideIFrameWithSkipTaskList());
  }

  @Test
  public void testShowCategoryInCaseByDefaultIframe() {
    redirectToRelativeLink("InternalSupport/15B1EA24CCF377E8/saleAndInform.ivp");
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    TaskWidgetPage taskWidget = newDashboardPage.openTaskList();
    taskWidget.filterTasksInExpandedModeBy("sale department", 1);
    TaskTemplatePage startTask = taskWidget.startTask(0);
    startTask.openCaseInfo();
    resizeBrowserTo2kResolution();
    assertTrue(startTask.isCategoryColumnDisplayed());
  }
  
  @Test
  public void testCustomParamsForTaskTemplate8() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(CUSTOM_PARAMS_TEMPLATE_TASK_URL);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksByOnHomePage("Task template 8 with custom params");
    taskWidgetPage.waitTillOnlyOneTaskAppear();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTaskOnHomePage(0);
    taskTemplatePage .waitForIFrameContentVisible();
    assertFalse(taskTemplatePage.isTaskNameDisplayed());
    assertFalse(taskTemplatePage.isTaskActionDisplayed());
    assertFalse(taskTemplatePage.isCaseInfoButtonDisplayed());
    
  }
}
