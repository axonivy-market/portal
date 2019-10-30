package portal.guitest.test;

import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;

public class CaseOwnerTest extends BaseTest {

  private static final String USER_IS_OWNER_URL = "internalSupport/16A68510A341BE6E/userIsOwner.ivp";
  private static final String ROLE_IS_OWNER_URL = "internalSupport/16A68510A341BE6E/roleIsOwner.ivp";
  
  private HomePage homePage;
  private MainMenuPage mainMenuPage;
  private CaseWidgetPage casePage;

  @Test
  public void testUserIsOwner() {
    navigateToUrl(USER_IS_OWNER_URL);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.DEMO_USER);
    
    homePage = new HomePage();
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed("demo user is owner"));
  }

  @Test
  public void testRoleIsOwner() {
    navigateToUrl(ROLE_IS_OWNER_URL);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.CASE_OWNER_USER);
    
    homePage = new HomePage();
    mainMenuPage = homePage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed("Test role is owner"));
  }
}
