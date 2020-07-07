package portal.guitest.test;

import org.junit.Assert;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.SearchResultPage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class GlobalSearchTest extends BaseTest{

  private HomePage homePage;
  private GlobalSearch globalSearch;
  private SearchResultPage searchResultPage;
  
  @Test
  public void testSearchCustomResult() {
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    homePage = new HomePage();
    globalSearch = homePage.getGlobalSearch();
    searchResultPage = globalSearch.inputSearchKeyword("Nam");
    searchResultPage.openEmployeeTab();
    Assert.assertEquals(2, searchResultPage.countNumberOfEmployee());
  }
  
  @Test
  public void testHideGlobalSearch() {
    updatePortalSetting("SHOW_GLOBAL_SEARCH", "false");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    globalSearch = homePage.getGlobalSearch();
    Assert.assertFalse(globalSearch.isPresent());
    updatePortalSetting("SHOW_GLOBAL_SEARCH", "true");
  }
}
