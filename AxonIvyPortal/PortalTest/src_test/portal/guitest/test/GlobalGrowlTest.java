package portal.guitest.test;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class GlobalGrowlTest extends BaseTest {

  private static final String CUSTOM_GROWL_URL = "portalExamples/16A7BB2ADC9580A8/start.ivp";
  
  @Override
  @Before
  public void setup() {
    super.setup();
  }

  @Test
  public void testDisplayCustomGrowlAfterFinishTask() {
    navigateToUrl(CUSTOM_GROWL_URL);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertEquals("Task is done successfully", homePage.getGlobalGrowlMessage());
  }
  
  @Test
  public void testDisplayDefaultGrowlAfterFinishTask() {
    navigateToUrl(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.inputFields("Employee", "1.1.2019", "1.1.2019", "Representation");
    HomePage homePage = taskTemplatePage.clickSubmitButton();
    assertEquals("Task left successfully", homePage.getGlobalGrowlMessage());
  }
}
