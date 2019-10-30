package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;

public class CaseDescriptionChangeTest extends BaseTest {
  
  @Override
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Override
  protected void login(TestAccount testAccount) {
    LoginPage loginPage = new LoginPage(testAccount);
    loginPage.login();
  }

  @Test
  public void testChangeCaseDescription() {
    String newCaseDescription = "New Case Description";
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    CaseDetailsPage detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    detailsPage.changeCaseDescription(newCaseDescription);
    String updatedDescription = detailsPage.getDescriptionOfCaseAt();
    assertEquals(newCaseDescription, updatedDescription);
  }
  
  @Test
  public void testUserWithoutPermissionCannotChangeCaseDescription() {
    login(TestAccount.DEMO_USER);
    int caseIndex = 0;
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    CaseDetailsPage detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    assertFalse(detailsPage.isCaseDescriptionChangeComponentPresented(caseIndex));
  }
}
