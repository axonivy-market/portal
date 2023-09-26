package portal.guitest.test;

import static portal.guitest.common.Variable.SHOW_GLOBAL_SEARCH;

import org.junit.Assert;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class GlobalSearchTest extends BaseTest{

  private NewDashboardPage2 newDashboardPage2;
  private GlobalSearch globalSearch;
  @Test
  public void testHideGlobalSearch() {
    updatePortalSetting(SHOW_GLOBAL_SEARCH.getKey(), "false");
    redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    newDashboardPage2 = new NewDashboardPage2();
    globalSearch = newDashboardPage2.getGlobalSearch();
    Assert.assertFalse(globalSearch.isPresent());
  }
}
