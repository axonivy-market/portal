package portal.guitest.test;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class GlobalGrowlTest extends BaseTest {

  private static final String CUSTOM_GROWL_URL = "portal-developer-examples/16A7BB2ADC9580A8/start.ivp";
  private static final String SKIP_TASK_LIST_URL = "portal-developer-examples/16FA8B451814E32A/start.ivp";
  
  @Test
  public void testDisplayCustomGrowlAfterFinishTask() {
    redirectToRelativeLink(CUSTOM_GROWL_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertGrowlMessage(homePage, "Task is done successfully");
  }
  
  @Test
  public void testDisplayDefaultGrowlAfterFinishTask() {
    redirectToRelativeLink(createTestingTasksUrl);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.inputFields("Employee", "1.1.2019", "1.1.2019", "Representation");
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertGrowlMessage(homePage, "You have finished the task successfully");
  }
  
  @Test
  public void testDisplayDefaultGrowlAfterFinishFirstTask() {
    redirectToRelativeLink(SKIP_TASK_LIST_URL);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.inputFields("Employee", "1.1.2019", "1.1.2019", "Representation");
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertGrowlMessage(homePage, "You have finished the task successfully");
  }

  @Test
  public void testDisplayDefaultGrowlAfterCancelTask() {
    redirectToRelativeLink(createTestingTasksUrl);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage.clickCancelAndLeftButton();
    assertGrowlMessage(homePage, "You have cancelled and left the task successfully. You can find the task in the dashboard or your task list."); 
  }
  
  @Test
  public void testDisplayCustomGrowlAfterCancelTask() {
    redirectToRelativeLink(CUSTOM_GROWL_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage.clickCancelAndLeftButton();
    assertGrowlMessage(homePage, "You have cancelled and left the task successfully");
  }
  
  @Test
  public void testDisplayDefaultGrowlAfterCancelFirstTask() {
    redirectToRelativeLink(SKIP_TASK_LIST_URL);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    HomePage homePage = taskTemplatePage.clickCancelAndLeftButton();
    assertGrowlMessage(homePage, "You have cancelled and left the task successfully. You can find the task in the dashboard or your task list.");
  }
  
  private void assertGrowlMessage(HomePage homePage, String message) {
    WaitHelper.assertTrueWithWait(() -> homePage.getGlobalGrowlMessage().equals(message));
  }
}
