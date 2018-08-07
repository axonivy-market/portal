package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.MainMenuPage;
import portal.page.TaskWidgetPage;

public class TaskFilterTest extends BaseTest {

  @Before
  @Override
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testFilterTask() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(3, taskWidgetPage.countTasks());
    taskWidgetPage.filterTasksBy("Maternity");
    assertEquals(1, taskWidgetPage.countTasks());
  }

  @Test
  public void testAdvancedFilterTask() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    assertEquals(3, taskWidgetPage.countTasks());
    
    taskWidgetPage.openAdvancedFilter("Description");
    taskWidgetPage.filterByDescription("Maternity");
    
    assertEquals(1, taskWidgetPage.countTasks());
  }
}
