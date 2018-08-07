package portal.test;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.CaseDetailsPage;
import portal.page.CasePage;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.MainMenuPage;

public class CaseDetailsTest extends BaseTest {

  private CaseDetailsPage detailsPage;

  @Before
  public void setup() {
    super.setup();
    String prepareTaskForTestUrl = "internalSupport/14B2FC03D2E87141/CreateTestTasks.ivp";
    navigateToUrl(prepareTaskForTestUrl);
    navigateToUrl(prepareTaskForTestUrl);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
  }

  @Test
  public void testDisplayCaseProperties() {
    assertTrue(StringUtils.equalsIgnoreCase("InternalSupport", detailsPage.getProcessModelName()));
  }

  @Test
  public void testAddCaseNote() {
    assertEquals(1, detailsPage.getNumberOfHistory());
    detailsPage.addNote("Consider the remaining annual leaves before the approval");
    assertEquals(2, detailsPage.getNumberOfHistory());
    assertEquals("Consider the remaining annual leaves before the approval", detailsPage.getLatestHistory());
  }

}
