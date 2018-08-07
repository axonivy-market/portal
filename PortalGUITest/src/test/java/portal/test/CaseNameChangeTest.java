package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.CaseDetailsPage;
import portal.page.CasePage;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.MainMenuPage;

public class CaseNameChangeTest extends BaseTest {
  
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  private void login(TestAccount testAccount) {
    LoginPage loginPage = new LoginPage(testAccount);
    loginPage.login();
  }

  @Test
  public void testChangeCaseName() {
    String newCaseName = "New Case Name";
    int caseIndex = 0;
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage casePage = mainMenuPage.selectCaseMenu();
    CaseDetailsPage detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    detailsPage.changeCaseName(newCaseName, caseIndex);
    assertEquals(detailsPage.getNameOfCaseAt(caseIndex), newCaseName);
  }
  
  @Test
  public void testUserWithoutPermissionCannotChangeCaseName() {
    login(TestAccount.DEMO_USER);
    int caseIndex = 0;
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage casePage = mainMenuPage.selectCaseMenu();
    CaseDetailsPage detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    assertFalse(detailsPage.isCaseNameChangeComponentPresented(caseIndex));
  }
}
