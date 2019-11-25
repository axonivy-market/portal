package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.SearchResultPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class SearchProcessTest extends BaseTest {

  private HomePage homePage;

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
  }

  @Test
  public void testFindAndStartProcessByName() {
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    String processName = "Appraisal";
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(processName);
    assertEquals(processName, searchResultPage.getProcessResult(processName));
    assertTrue(searchResultPage.isProcessGroupDisplay("A"));
    
    searchResultPage.startProcess(processName);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertTrue(taskWidgetPage.getNameOfTaskInCompactListAt(0).contains("Request form"));
  }
  
  @Test
  public void testFindCaseMapByName() {
    String caseMapName = "Case Map: Leave Request";
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(caseMapName);
    assertEquals(caseMapName, searchResultPage.getProcessResult(caseMapName));
  }
}
