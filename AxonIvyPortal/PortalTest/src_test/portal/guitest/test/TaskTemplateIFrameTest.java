package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import portal.guitest.common.BaseTest;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class TaskTemplateIFrameTest extends BaseTest {

  private static final String CUSTOM_PARAMS_TEMPLATE_TASK_URL = "portal-developer-examples/1718293B3F6E5478/start.ivp";
  private static final String IFRAME_TASK_URL =
      "portal-developer-examples/16E5DB746865BCEC/CreateInvestment.ivp?embedInFrame";

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testCustomParamsForTaskTemplate8() {
    redirectToRelativeLink(CUSTOM_PARAMS_TEMPLATE_TASK_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Task template 8 with custom params");
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTaskWithouWaitForTaskActionPresent(0);
    assertFalse(taskTemplatePage.isTaskNameDisplayed());
    assertFalse(taskTemplatePage.isTaskActionDisplayed());
    assertFalse(taskTemplatePage.isCaseInfoButtonDisplayed());
  }

  @Test
  public void testCustomParamsForIFrameTaskTemplate() {
    redirectToRelativeLink(CUSTOM_PARAMS_TEMPLATE_TASK_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("IFrame task with custom params");
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
    taskWidgetPage.filterTasksBy("Approve Investment", 1);
    TaskTemplatePage taskTemplatePage2 = taskWidgetPage.startTask(0);
    taskTemplatePage2.clickOnLogo();
    WaitHelper.assertTrueWithWait(
        () -> taskTemplatePage2.isElementDisplayed(By.id("task-widget:task-list-link:task-list-link")));
  }

  @Test
  public void testRedirectToApplicationHome() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    waitForTemplateRender();
    TaskTemplatePage taskTemplatePage1 = new TaskTemplatePage();
    TaskWidgetPage taskWidgetPage = taskTemplatePage1.finishCreateInvestmentTask();
    taskWidgetPage.filterTasksBy("Approve Investment", 1);
    TaskTemplatePage taskTemplatePage2 = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage2.backToHomeInIFrameApprovalTask();
    WaitHelper
        .assertTrueWithWait(() -> homePage.isElementDisplayed(By.id("task-widget:task-list-link:task-list-link")));
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
    WaitHelper.waitForPresenceOfElementLocatedInFrame(this.getBrowser().getDriver(), "[class$='task-template-container']");
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
    HomePage homePage = new HomePage();
    TaskWidgetPage taskWidget = homePage.openTaskList();
    taskWidget.filterTasksInExpandedModeBy("sale department", 1);
    TaskTemplatePage startTask = taskWidget.startTask(0);
    startTask.openCaseInfo();
    startTask.switchToCaseInfoIframe();
    assertTrue(startTask.isCategoryColumnDisplayed());
  }
}
