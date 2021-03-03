package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;

public class CaseDescriptionChangeTest extends BaseTest {
  
  @Override
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
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
    String updatedDescription = detailsPage.getDescription();
    assertEquals(newCaseDescription, updatedDescription);
  }
  
  @Test
  public void testUserWithoutPermissionCannotChangeCaseDescription() {
    int caseIndex = 0;
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    CaseDetailsPage detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    assertFalse(detailsPage.isCaseDescriptionChangeComponentPresented(caseIndex));
  }
}
