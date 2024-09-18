package com.axonivy.portal.selenium.test.globalsearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.SearchResultPage;
import com.axonivy.portal.selenium.page.TemplatePage.GlobalSearch;

@IvyWebTest
public class SearchTaskTest extends BaseTest {
  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
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
