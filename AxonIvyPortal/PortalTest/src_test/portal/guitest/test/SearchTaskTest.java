package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.SearchResultPage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class SearchTaskTest extends BaseTest {
  private NewDashboardPage newDashboardPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testFindTaskByName() {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    GlobalSearch globalSearch = newDashboardPage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());
    
    String taskName = "Annual Leave Request";
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(taskName);
    searchResultPage.openTaskTab();
    assertEquals(taskName, searchResultPage.getTaskResult(0));
    assertTrue(searchResultPage.isTaskCategoryColumnDisplayed());
  }
}
