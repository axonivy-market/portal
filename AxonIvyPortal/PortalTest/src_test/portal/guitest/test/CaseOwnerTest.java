package portal.guitest.test;

import static org.junit.Assert.assertTrue;
import static portal.guitest.common.Variable.ENABLE_CASE_OWNER;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.MainMenuPage;

public class CaseOwnerTest extends BaseTest {

  private static final String USER_IS_OWNER_URL = "internalSupport/16A68510A341BE6E/userIsOwner.ivp";
  private static final String ROLE_IS_OWNER_URL = "internalSupport/16A68510A341BE6E/roleIsOwner.ivp";
  
  private NewDashboardPage newDashboardPage;
  private MainMenuPage mainMenuPage;
  private CaseWidgetPage casePage;

  @Override
  @Before
  public void setup() {
    super.setup();
    updatePortalSetting(ENABLE_CASE_OWNER.getKey(), "true");
  }
  
  @After
  public void destroy() {
    updatePortalSetting(ENABLE_CASE_OWNER.getKey(), "false");
  }
  
  @Test
  public void testUserIsOwner() {
    redirectToRelativeLink(USER_IS_OWNER_URL);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    
    newDashboardPage = new NewDashboardPage();
    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed("demo user is owner"));
  }

  @Test
  public void testRoleIsOwner() {
    redirectToRelativeLink(ROLE_IS_OWNER_URL);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    login(TestAccount.CASE_OWNER_USER);
    
    newDashboardPage = new NewDashboardPage();
    mainMenuPage = newDashboardPage.openMainMenu();
    casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed("Test role is owner"));
  }
}
