package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.MainMenuPage;
import portal.page.TaskWidgetPage;

public class PageRefreshingTest extends BaseTest {

  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
  }

  @Test
  public void testTasksInPortalHomePageUpdatedAfterReloading() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(3, taskWidgetPage.countTasks());

    taskWidgetPage.createTestingTasksInNewWindow();
    taskWidgetPage.refresh();
    assertEquals(6, taskWidgetPage.countTasks());
  }


  @Test
  public void testTasksInPortalTaskPageUpdatedAfterReloading() {
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();

    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    assertEquals(3, taskWidgetPage.countTasks());

    taskWidgetPage.createTestingTasksInNewWindow();
    taskWidgetPage.refresh();
    assertEquals(6, taskWidgetPage.countTasks());
  }

}
