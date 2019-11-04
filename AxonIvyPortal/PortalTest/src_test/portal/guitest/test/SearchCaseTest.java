package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.SearchResultPage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class SearchCaseTest extends BaseTest {

  private HomePage homePage;

  @Before
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    redirectToRelativeLink("internalSupport/14B2FC03D2E87141/CreateTaskWithSpecialCharacter.ivp");
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
  }

  @Test
  public void testFindCaseByNameAndOpenCaseList() {
    GlobalSearch globalSearch = homePage.getGlobalSearch(); 
    assertTrue(globalSearch.isDisplayed());

    String caseName = "Leave Request";
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(caseName);
    searchResultPage.openCaseTab();
    assertEquals(caseName, searchResultPage.getCaseResult(0));
  }

  @Test
  public void testFindCaseByNameWithSpecialCharacter() {
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    String caseName = "\u00D6sterreich Resource with ID 1212";
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(caseName);
    searchResultPage.openCaseTab();
    assertEquals(caseName, searchResultPage.getCaseResult(0));
  }

  @Test
  public void testFindNotFoundOutData() {
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword("no case found");
    searchResultPage.openCaseTab();
    assertTrue(searchResultPage.isCaseResultEmpty());
  }
}
