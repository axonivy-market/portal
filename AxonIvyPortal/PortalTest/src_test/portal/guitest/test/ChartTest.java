package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.StatisticWidgetPage;

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
