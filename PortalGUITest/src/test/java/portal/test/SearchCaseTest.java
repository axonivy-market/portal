package portal.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.SearchResultPage;
import portal.page.TemplatePage.GlobalSearch;

public class SearchCaseTest extends BaseTest {

  private HomePage homePage;

  @Before
  public void setup() {
    super.setup();
    String prepareTaskForTestUrl = "internalSupport/14B2FC03D2E87141/CreateTestTasks.ivp";
    navigateToUrl(prepareTaskForTestUrl);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    homePage = new HomePage();
  }

  @Test
  public void testFindCaseByNameAndOpenCaseList() {
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    globalSearch.clickOnGlobalSearchIcon();
    homePage.waitForElementDisplayed(globalSearch.getSearchContainer(), true);
    globalSearch.inputSearchKeyword("Leave Request");

    String caseName = "Leave Request";
    assertEquals(caseName, globalSearch.getCaseResult());

    int numberOfCases = globalSearch.countFoundCases();
    SearchResultPage resultPage = globalSearch.startCaseOnGlobalSearch(caseName);
    assertEquals(numberOfCases, resultPage.countCases());
    assertTrue(resultPage.isCaseItemSelected(0));
  }

  @Test
  public void testFindNotFoundOutData() {
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    globalSearch.clickOnGlobalSearchIcon();
    homePage.waitForElementDisplayed(globalSearch.getSearchContainer(), true);
    globalSearch.inputSearchKeyword("no case found");

    WebElement caseResult = globalSearch.getEmptySearchResult();

    assertEquals("No records found.", caseResult.getText());
  }
}
