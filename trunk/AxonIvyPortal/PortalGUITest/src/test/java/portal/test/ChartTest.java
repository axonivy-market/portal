package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.MainMenuPage;
import portal.page.StatisticWidgetPage;

public class ChartTest extends BaseTest {
  private HomePage homePage;
  private StatisticWidgetPage statisticWidgetPage;
  private MainMenuPage mainMenuPage;

  @Before
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    homePage = new HomePage();
  }

  @Test
  public void testNavigateToChartFromMenu() {
    mainMenuPage = homePage.openMainMenu();
    mainMenuPage.selectDashboard();
    statisticWidgetPage = homePage.getStatisticsWidget();
    assertTrue(statisticWidgetPage.isFullMode());
  }
}
