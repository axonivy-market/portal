package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.MainMenuPage;
import portal.page.TaskWidgetPage;

public class TaskCategoryMenuTest extends BaseTest {

  private HomePage homePage;

  @Before
  @Override
  public void setup() {
    super.setup();
    String prepareTaskForTestUrl = "internalSupport/14B2FC03D2E87141/CreateTestTasks.ivp";
    navigateToUrl(prepareTaskForTestUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testSelectTaskCategoryMenuAsNormalUser() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    mainMenuPage.toggleTaskMenu();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskCategory("Other Leave");
    assertEquals(2, taskWidgetPage.countTasks());
  }
  
  @Test
  public void testSelectTaskCategoryMenuAsAdminRole() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    mainMenuPage.toggleTaskMenu();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskCategory("My tasks");
    assertEquals(2, taskWidgetPage.countTasks());
    
    homePage = taskWidgetPage.goToHomePage();
    taskWidgetPage = mainMenuPage.selectTaskCategory("All tasks");
    assertEquals(3, taskWidgetPage.countTasks());
  }
}
