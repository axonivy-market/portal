package portal.guitest.test;

import static portal.guitest.common.Variable.SHOW_GLOBAL_SEARCH;

import org.junit.Assert;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class GlobalSearchTest extends BaseTest{

  private NewDashboardPage newDashboardPage;
  private GlobalSearch globalSearch;
  @Test
  public void testHideGlobalSearch() {
    updatePortalSetting(SHOW_GLOBAL_SEARCH.getKey(), "false");
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    newDashboardPage = new NewDashboardPage();
    globalSearch = newDashboardPage.getGlobalSearch();
    Assert.assertFalse(globalSearch.isPresent());
  }
}
