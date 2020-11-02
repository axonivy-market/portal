package portal.guitest.test;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import portal.guitest.common.BaseTest;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class TaskTemplateIFrameTest extends BaseTest {

  private static final String CUSTOM_PARAMS_TEMPLATE_TASK_URL= "portal-developer-examples/1718293B3F6E5478/start.ivp";
  private static final String IFRAME_TASK_URL= "portal-developer-examples/16E5DB746865BCEC/CreateInvestment.ivp?embedInFrame";
  
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
    final TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.clickOnLogo();
    By leaveButton = By.id("task-leave-warning-component:leave-button");
    WaitHelper.assertTrueWithWait(() -> taskTemplatePage.isElementDisplayed(leaveButton));
  }
  
  @Test
  public void testNotDisplayWarningInIFrameTaskTemplate() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    TaskTemplatePage taskTemplatePage1 = new TaskTemplatePage();
    TaskWidgetPage taskWidgetPage = taskTemplatePage1.finishCreateInvestmentTask();
    TaskTemplatePage taskTemplatePage2 = taskWidgetPage.startTask(1);
    taskTemplatePage2.clickOnLogo();
    WaitHelper.assertTrueWithWait(() -> taskTemplatePage2.isElementDisplayed(By.id("task-widget:task-list-link:task-list-link")));
  }
  
  @Test
  public void testRedirectToApplicationHome() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    TaskTemplatePage taskTemplatePage1 = new TaskTemplatePage();
    TaskWidgetPage taskWidgetPage = taskTemplatePage1.finishCreateInvestmentTask();
    TaskTemplatePage taskTemplatePage2 = taskWidgetPage.startTask(1);
    HomePage homePage = taskTemplatePage2.backToHomeInIFrameApprovalTask();
    WaitHelper.assertTrueWithWait(() -> homePage.isElementDisplayed(By.id("task-widget:task-list-link:task-list-link")));
  }
  
  @Test
  public void testStickyTaskList() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    TaskTemplatePage taskTemplatePage1 = new TaskTemplatePage();
    TaskWidgetPage taskWidgetPage1 = taskTemplatePage1.finishCreateInvestmentTask();
    taskWidgetPage1 = taskWidgetPage1.openTaskList();
    TaskTemplatePage taskTemplatePage2 = taskWidgetPage1.startTask(1);
    TaskWidgetPage taskWidgetPage2 = taskTemplatePage2.finishIFrameApprovalTask();
    WaitHelper.assertTrueWithWait(() -> taskWidgetPage2.isElementDisplayed(By.cssSelector("[id$='task-config-command']")));
  }
}
