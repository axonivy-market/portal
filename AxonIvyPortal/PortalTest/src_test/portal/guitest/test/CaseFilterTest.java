package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
public class CaseFilterTest extends BaseTest {
  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testCaseAdvancedFilter() {
    login(TestAccount.ADMIN_USER);
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
    login(TestAccount.ADMIN_USER);
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
