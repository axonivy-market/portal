package portal.guitest.test;

import org.junit.Assert;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.SearchResultPage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class GlobalSearchTest extends BaseTest{

  private HomePage homePage;
  private GlobalSearch globalSearch;
  private SearchResultPage searchResultPage;
  
  @Test
  public void testSearchCustomResult() {
    navigateToUrl(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    homePage = new HomePage();
    globalSearch = homePage.getGlobalSearch();
    searchResultPage = globalSearch.inputSearchKeyword("Nam");
    searchResultPage.openEmployeeTab();
    Assert.assertEquals(2, searchResultPage.countNumberOfEmployee());
    searchResultPage.openEmployeeDetail(0);
  }
}
