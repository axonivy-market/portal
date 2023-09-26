package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.SearchResultPage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class SearchTaskTest extends BaseTest {
  private NewDashboardPage2 newDashboardPage2;

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    newDashboardPage2 = new NewDashboardPage2();
  }

  @Test
  public void testFindTaskByName() {
    redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    GlobalSearch globalSearch = newDashboardPage2.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());
    
    String taskName = "Annual Leave Request";
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(taskName);
    searchResultPage.openTaskTab();
    assertEquals(taskName, searchResultPage.getTaskResult(0));
    assertTrue(searchResultPage.isTaskCategoryColumnDisplayed());
  }
}
