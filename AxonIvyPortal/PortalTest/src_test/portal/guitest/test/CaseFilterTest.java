package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;

public class CaseFilterTest extends BaseTest {
  @Override
  @Before
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testCaseAdvancedFilter() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();

    casePage.openAdvancedFilter("Description", "description");
    casePage.filterByDescription("random text");
    assertEquals(0, casePage.getNumberOfCases());

    casePage.filterByDescription("Leave Request Description");
    assertEquals(1, casePage.getNumberOfCases());
  }

  @Test
  public void testSaveFilter() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();

    casePage.openAdvancedFilter("Description", "description");
    casePage.filterByDescription("Leave");
    String filterName = "Leave";
    casePage.saveFilter(filterName);

    mainMenuPage.selectTaskMenu();
    casePage = mainMenuPage.openCaseList();
    assertEquals(filterName, casePage.getFilterName());
  }

  @Test
  public void testSaveCaseFilterOnDifferentCaseList() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    MainMenuPage mainMenuPage = new MainMenuPage();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();

    String filterName = "MyFilter";

    casePage.openAdvancedFilter("Description", "description");
    casePage.filterByDescription("Sick");
    casePage.saveFilter(filterName);

    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    casePage = mainMenuPage.selectCaseMenu();

    assertFalse(casePage.isFilterSelectionVisible());

    casePage.filterByDescription("Leave");
    casePage.saveFilter(filterName);

    mainMenuPage.selectTaskMenu();
    casePage = mainMenuPage.openCaseList();
    assertEquals(filterName, casePage.getFilterName());
  }
}
