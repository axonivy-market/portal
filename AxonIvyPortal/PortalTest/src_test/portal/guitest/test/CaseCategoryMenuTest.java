package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CasePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;

public class CaseCategoryMenuTest extends BaseTest {

  private HomePage homePage;

  @Before
  @Override
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testSelectCaseCategoryMenuAsNormalUser() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage caseWidgetPage = mainMenuPage.openCaseList();
    caseWidgetPage.toggleCategoryMenu();
    assertEquals(1, caseWidgetPage.getNumberOfCases());
    assertEquals(1, caseWidgetPage.countCategoryRoots());
  }

  @Test
  public void testSelectCaseCategoryMenuAsAdminRole() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage caseWidgetPage = mainMenuPage.openCaseList();
    caseWidgetPage.toggleCategoryMenu();
    assertEquals(1, caseWidgetPage.getNumberOfCases());
    assertEquals(2, caseWidgetPage.countCategoryRoots());
  }
}
