package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NewDashboardPage;
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
  public void testTasksInPortalTaskPageUpdatedAfterReloading() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();

    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    assertEquals(3, taskWidgetPage.countTasks());

    launchBrowserAndGotoRelativeLink(createTestingTasksUrl);
    taskWidgetPage.refresh();
    assertEquals(6, taskWidgetPage.countTasks());
  }
  
  @AfterClass
  public static void killOpenBrowsers() {
    killBrowsers();
  }
}
