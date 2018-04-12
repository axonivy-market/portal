package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CasePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class SearchCaseTest extends BaseTest {

  private HomePage homePage;

  @Before
  @Override
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    navigateToUrl("internalSupport/14B2FC03D2E87141/CreateTaskWithSpecialCharacter.ivp");
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    homePage = new HomePage();
  }

  @Test
  public void testFindCaseByNameAndOpenCaseList() {
    GlobalSearch globalSearch = homePage.getGlobalSearch(); 
    assertTrue(globalSearch.isDisplayed());

    globalSearch.inputSearchKeyword("Leave Request");

    String caseName = "Leave Request";
    assertEquals(caseName, globalSearch.getCaseResult());

    int numberOfCases = globalSearch.countFoundCases();
    CasePage casePage = globalSearch.startCaseOnGlobalSearch(caseName);
    assertEquals(numberOfCases, casePage.getNumberOfCases());
    assertTrue(casePage.isCaseItemSelected(0));
  }

  @Test
  public void testFindCaseByNameWithSpecialCharacter() {
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    globalSearch.inputSearchKeyword("Öst");

    String caseName = "Österreich Resource with ID 1212";
    assertEquals(caseName, globalSearch.getCaseResult());

    CasePage casePage = globalSearch.startCaseOnGlobalSearch(caseName);
    assertEquals(1, casePage.getNumberOfCases());
    assertTrue(casePage.isCaseItemSelected(0));
  }

  @Test
  public void testFindNotFoundOutData() {
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    globalSearch.inputSearchKeyword("no case found");

    WebElement caseResult = globalSearch.getEmptySearchResult();

    assertEquals("No records found.", caseResult.getText());
  }
}
