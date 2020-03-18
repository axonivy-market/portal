package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CasePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.TemplatePage.GlobalSearch;
public class BusinessCaseTest extends BaseTest {

  private static final String TECHNICAL_CASE_NAME = "TECH: Update checkin time";
  private static final String BUSINESS_CASE_NAME = "Update checkin time";

  @Override
  @Before
  public void setup() {
    super.setup();
    navigateToUrl(businessCaseUrl);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testOnlyDisplayBusinessCaseOnCaseList() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed(BUSINESS_CASE_NAME));
    assertFalse(casePage.isCaseDisplayed(TECHNICAL_CASE_NAME));
  }
  
  @Test
  public void testOnlyDisplayBusinessCaseOnCaseListWithAdmin() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CasePage casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed(BUSINESS_CASE_NAME));
    assertFalse(casePage.isCaseDisplayed(TECHNICAL_CASE_NAME));
  }
  
  @Test
  public void testOnlyDisplayBusinessCaseOnGlobalSearch() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    HomePage homePage = new HomePage();
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    globalSearch.inputSearchKeyword(BUSINESS_CASE_NAME);
    assertEquals(1, globalSearch.countFoundCases());
    assertEquals(BUSINESS_CASE_NAME, globalSearch.getCaseResult());
  }
  
  @Test
  public void testTaskOfTechnicalCaseDisplayBusinessCaseOnTaskDetails() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    int firstTask = 0;
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(firstTask);
    assertEquals(BUSINESS_CASE_NAME, taskWidgetPage.getRelatedCase());
  }
}
