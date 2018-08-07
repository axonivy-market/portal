package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.DashboardPage;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.MainMenuPage;

public class MenuTest extends BaseTest {

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
    DashboardPage dashboardPage = mainMenuPage.selectDashboard();
    assertTrue(dashboardPage.isMainMenuOpen());
  }

  @Test
  public void testKeepTaskCategoryStateWhenNavigateToAnotherPage() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    mainMenuPage.toggleTaskMenu();
    DashboardPage dashboardPage = mainMenuPage.selectDashboard();
    assertTrue(dashboardPage.isTaskMenuOpen());
  }

  @Test
  public void testKeepClosedStateWhenNavigateToAnotherPage() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    DashboardPage dashboardPage = mainMenuPage.selectDashboard();
    dashboardPage.closeMainMenu();
    homePage = dashboardPage.goToHomePage();
    assertFalse(homePage.isMainMenuOpen());
  }

  @Test
  public void testShowAllCaseWithReadAllCasePermission() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    assertTrue(mainMenuPage.hasReadAllCasePermission());
  }

  @Test
  public void testShowAllCaseWithoutReadAllCasePermission() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();

    assertFalse(mainMenuPage.hasReadAllCasePermission());
  }

}
