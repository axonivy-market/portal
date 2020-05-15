package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.SearchResultPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class BusinessCaseTest extends BaseTest {

  private static final String TECHNICAL_CASE_NAME = "TECH: Update checkin time";
  private static final String BUSINESS_CASE_NAME = "Update checkin time";

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(businessCaseUrl);
  }

  @Test
  public void testOnlyDisplayBusinessCaseOnCaseList() {

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed(BUSINESS_CASE_NAME));
    assertFalse(casePage.isCaseDisplayed(TECHNICAL_CASE_NAME));
  }
  
  @Test
  public void testOnlyDisplayBusinessCaseOnCaseListWithAdmin() {
    login(TestAccount.ADMIN_USER);

    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed(BUSINESS_CASE_NAME));
    assertFalse(casePage.isCaseDisplayed(TECHNICAL_CASE_NAME));
  }
  
  @Test
  public void testOnlyDisplayBusinessCaseOnGlobalSearch() {
    HomePage homePage = new HomePage();
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(BUSINESS_CASE_NAME);
    searchResultPage.openCaseTab();
    assertEquals(1, searchResultPage.countCase());
    assertEquals(BUSINESS_CASE_NAME, searchResultPage.getCaseResult(0));
  }
  
  @Test
  public void testTaskOfTechnicalCaseDisplayBusinessCaseOnTaskDetails() {
    int firstTask = 0;
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(firstTask);
    assertTrue(taskWidgetPage.getRelatedCase().contains(BUSINESS_CASE_NAME));
  }
}
