package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.WaitHelper;
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

    String processName = "Simple Payment";
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(processName);
    searchResultPage.waitForFirstTabFinishedLoading();
    assertEquals(processName, searchResultPage.getProcessResult(processName));
    assertTrue(searchResultPage.isProcessGroupDisplay("S"));

    searchResultPage.startProcess(processName);
    homePage = new HomePage();
    homePage.waitForStatisticRendered();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    WaitHelper.assertTrueWithRefreshPage(taskWidgetPage,
        () -> taskWidgetPage.getNameOfTaskInCompactListAt(0).contains("Payment"));
  }
  
  @Test
  public void testFindCaseMapByName() {
    String caseMapName = "Case Map: Leave Request";
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(caseMapName);
    searchResultPage.waitForFirstTabFinishedLoading();
    assertEquals(caseMapName, searchResultPage.getProcessResult(caseMapName));
  }
}
