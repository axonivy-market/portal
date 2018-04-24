package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.StatisticWidgetPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.TemplatePage.GlobalSearch;

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
  public void testKeepTaskMenuStateWhenNavigateBackToTaskPage() throws Exception {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    mainMenuPage.openTaskMenu();

    taskWidgetPage.goToHomePage();
    mainMenuPage.selectTaskMenu();

    assertTrue(mainMenuPage.isTaskMenuOpen());
  }

  @Test
  public void testKeepClosedStateWhenNavigateToAnotherPage() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    StatisticWidgetPage dashboardPage = mainMenuPage.selectStatisticDashboard();
    dashboardPage.waitForElementDisplayed(By.cssSelector(".js-left-sidebar-toggle"), true, 30);
    dashboardPage.closeMainMenu();
    homePage = dashboardPage.goToHomePage();
    assertFalse(homePage.isMainMenuOpen());
  }

  @Test
  public void testChangeMenuHighlightWhenNavigateToAnotherPageBySearching() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    HomePage homePage = new HomePage();

    GlobalSearch globalSearch = homePage.getGlobalSearch();
    globalSearch.inputSearchKeyword("Sick Leave Request");

    homePage.waitAjaxIndicatorDisappear();

    globalSearch.startTaskOnGlobalSearch("Sick Leave Request");
    MainMenuPage mainMenuPage = new MainMenuPage();
    int taskMenuItemPosition = 2;
    assertTrue(mainMenuPage.isMenuItemHighlighted(taskMenuItemPosition));
  }

  @Test
  public void testSelectedTaskCategoryHighlighted() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    mainMenuPage.openTaskList();
    mainMenuPage.openTaskMenu();
    mainMenuPage.expandTaskCategory("Group_Tasks", "Other_Leave", "Sick_Leave");
    mainMenuPage.selectTaskCategory("Long");
    assertTrue(mainMenuPage
        .isTaskCategoryPathExpandedAndHighlighted("Group_Tasks", "Other_Leave", "Sick_Leave", "Long"));
  }
}
