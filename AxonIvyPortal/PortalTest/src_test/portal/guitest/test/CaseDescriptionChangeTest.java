package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.UserProfilePage;

public class CaseDescriptionChangeTest extends BaseTest {
  
  private HomePage homePage;
  private CaseWidgetPage casePage;

  @Override
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
  }

  @Test
  public void testChangeCaseDescription() {
    var caseNameEn = "Leave Request";
    var caseNameGer = "Urlaubsantrag";
    var newCaseDescriptionEn = "New Case Description - English";
    var newCaseDescriptionGer = "New Case Description - German";
    login(TestAccount.ADMIN_USER);
    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    CaseDetailsPage detailsPage = casePage.openDetailsOfCaseHasName(caseNameEn);
    detailsPage.changeCaseDescription(newCaseDescriptionEn);
    assertEquals(newCaseDescriptionEn, detailsPage.getDescription());

    // Change to language to German then change description
    changeLanguage(3);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(caseNameGer);
    detailsPage.changeCaseDescription(newCaseDescriptionGer);
    assertEquals(newCaseDescriptionGer, detailsPage.getDescription());
    
    // Change to English then verify description
    changeLanguage(1);
    detailsPage = casePage.openDetailsOfCaseHasName(caseNameEn);
    assertEquals(newCaseDescriptionEn, detailsPage.getDescription());
    
    // Change to German then verify description
    changeLanguage(3);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    detailsPage = casePage.openDetailsOfCaseHasName(caseNameGer);
    assertEquals(newCaseDescriptionGer, detailsPage.getDescription());
  }

  @Test
  public void testUserWithoutPermissionCannotChangeCaseDescription() {
    int caseIndex = 0;
    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    CaseDetailsPage detailsPage = casePage.openDetailsOfCaseHasName("Leave Request");
    assertFalse(detailsPage.isCaseDescriptionChangeComponentPresented(caseIndex));
  }

  public void changeLanguage(int index) {
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    userProfilePage.selectLanguage(index);
    homePage = userProfilePage.save();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    var mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
  }

  @After
  public void resetlanguage() {
    resetLanguageOfCurrentUser();
  }
}
