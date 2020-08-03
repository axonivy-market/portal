package portal.guitest.test;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class GlobalGrowlTest extends BaseTest {

  private static final String CUSTOM_GROWL_URL = "portalExamples/16A7BB2ADC9580A8/start.ivp";
  private static final String SKIP_TASK_LIST_URL = "portalExamples/16FA8B451814E32A/start.ivp";
  
  @Test
  public void testDisplayCustomGrowlAfterFinishTask() {
    redirectToRelativeLink(CUSTOM_GROWL_URL);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertEquals("Task is done successfully", homePage.getGlobalGrowlMessage());
  }
  
  @Test
  public void testDisplayDefaultGrowlAfterFinishTask() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.inputFields("Employee", "1.1.2019", "1.1.2019", "Representation");
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertEquals("You have finished the task successfully", homePage.getGlobalGrowlMessage());
  }
  
  @Test
  public void testDisplayDefaultGrowlAfterFinishFirstTask() {
    redirectToRelativeLink(SKIP_TASK_LIST_URL);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.inputFields("Employee", "1.1.2019", "1.1.2019", "Representation");
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertEquals("You have finished the task successfully", homePage.getGlobalGrowlMessage());
  }

  @Test
  public void testDisplayDefaultGrowlAfterCancelTask() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage.clickCancelAndLeftButton();
    assertEquals("You have cancelled and left the task successfully. You can find the task in the dashboard or your task list.", homePage.getGlobalGrowlMessage());
  }
  
  @Test
  public void testDisplayCustomGrowlAfterCancelTask() {
    redirectToRelativeLink(CUSTOM_GROWL_URL);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage.clickCancelAndLeftButton();
    assertEquals("You have cancelled and left the task successfully", homePage.getGlobalGrowlMessage());
  }
  
  @Test
  public void testDisplayDefaultGrowlAfterCancelFirstTask() {
    redirectToRelativeLink(SKIP_TASK_LIST_URL);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    HomePage homePage = taskTemplatePage.clickCancelAndLeftButton();
    assertEquals("You have cancelled and left the task successfully. You can find the task in the dashboard or your task list.", homePage.getGlobalGrowlMessage());
  }
}
