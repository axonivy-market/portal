package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.SearchResultPage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class SearchCaseTest extends BaseTest {

  private NewDashboardPage newDashboardPage;

  @Before
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink("internalSupport/14B2FC03D2E87141/CreateTaskWithSpecialCharacter.ivp");
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testFindCaseByNameAndOpenCaseList() {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    GlobalSearch globalSearch = newDashboardPage.getGlobalSearch(); 
    assertTrue(globalSearch.isDisplayed());

    String caseName = "Leave Request";
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(caseName);
    searchResultPage.waitForFirstTabFinishedLoading();
    searchResultPage.openCaseTab();
    assertEquals(caseName, searchResultPage.getCaseResult(0));
    assertTrue(searchResultPage.isCaseCategoryColumnDisplayed());
  }

  @Test
  public void testFindCaseByNameWithSpecialCharacter() {
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    GlobalSearch globalSearch = newDashboardPage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    String caseName = "\u00D6sterreich Resource with ID 1212";
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(caseName);
    searchResultPage.openCaseTab();
    assertEquals(caseName, searchResultPage.getCaseResult(0));
  }

  @Test
  public void testFindNotFoundOutData() {
    GlobalSearch globalSearch = newDashboardPage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword("no case found");
    searchResultPage.openCaseTab();
    assertTrue(searchResultPage.isCaseResultEmpty());
  }
}
