package portal.guitest.test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.StatisticWidgetPage;
import portal.guitest.page.TaskWidgetPage;

public class MenuTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testKeepOpenStateWhenNavigateToAnotherPage() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    assertTrue(taskWidgetPage.isMainMenuOpen());
  }

  @Test
  public void testKeepClosedStateWhenNavigateToAnotherPage() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    StatisticWidgetPage dashboardPage = mainMenuPage.selectStatisticDashboard();
    dashboardPage.waitForPageLoaded();

    dashboardPage.closeMainMenu();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    assertFalse(homePage.isMainMenuOpen());
  }
}
