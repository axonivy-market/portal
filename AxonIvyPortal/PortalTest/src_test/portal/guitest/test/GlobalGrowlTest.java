package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class GlobalGrowlTest extends BaseTest {

  private static final String CUSTOM_GROWL_URL = "portalExamples/16A8BFE42F8A31EF/start.ivp";
  
  @Override
  @Before
  public void setup() {
    super.setup();
  }

  @Test
  public void testDisplayCustomGrowlAfterFinishTask() {
    navigateToUrl(CUSTOM_GROWL_URL);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setDisplayMessageAfterFinishTaskVariable();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.clickSubmitButton();
    homePage = new HomePage();
    assertEquals("Task is done successfully", homePage.getGlobalGrowlMessage());
  }
  
  @Test
  public void testDisplayDefaultGrowlAfterFinishTask() {
    navigateToUrl(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setDisplayMessageAfterFinishTaskVariable();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.inputFields("Employee", "1.1.2019", "1.1.2019", "Representation");
    taskTemplatePage.clickSubmitButton();
    homePage = new HomePage();
    assertEquals("You have finished the task successfully", homePage.getGlobalGrowlMessage());
  }
  
  @Test
  public void testDisplayCustomGrowlAfterCancelTask() {
    navigateToUrl(CUSTOM_GROWL_URL);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setDisplayMessageAfterFinishTaskVariable();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.clickCancelAndLeftButton();
    homePage = new HomePage();
    assertEquals("You have cancelled and left the task successfully", homePage.getGlobalGrowlMessage());
  }
  
  @Test
  public void testDisplayDefaultGrowlAfterCancelTask() {
    navigateToUrl(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setDisplayMessageAfterFinishTaskVariable();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.clickCancelAndLeftButton();
    homePage = new HomePage();
    assertEquals("You have cancelled and left the task successfully. You can find the task in the dashboard or your task list", homePage.getGlobalGrowlMessage());
  }
}
