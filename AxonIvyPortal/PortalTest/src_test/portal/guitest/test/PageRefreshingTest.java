package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;

public class PageRefreshingTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    killBrowsers();
    super.setup();
    createTestingTasks();
  }

  @Test
  public void testTasksInPortalHomePageUpdatedAfterReloading() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(3, taskWidgetPage.countTasks());

    launchBrowserAndGotoRelativeLink(createTestingTasksUrl);
    taskWidgetPage.refresh();
    assertEquals(6, taskWidgetPage.countTasks());
  }


  @Test
  public void testTasksInPortalTaskPageUpdatedAfterReloading() {
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();

    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    assertEquals(3, taskWidgetPage.countTasks());

    launchBrowserAndGotoRelativeLink(createTestingTasksUrl);
    taskWidgetPage.refresh();
    assertEquals(6, taskWidgetPage.countTasks());
  }
  
  @Test
  public void testTasksInPortalHomePageUpdatedAfterExpandToFullMode() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(3, taskWidgetPage.countTasks());

    launchBrowserAndGotoRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertEquals(6, taskWidgetPage.countTasks());
  }

  @AfterClass
  public static void killOpenBrowsers() {
    killBrowsers();
  }
}
