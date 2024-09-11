package com.axonivy.portal.selenium.test.globalsearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.SearchResultPage;
import com.axonivy.portal.selenium.page.TemplatePage.GlobalSearch;

@IvyWebTest
public class SearchCaseTest extends BaseTest {

  private NewDashboardPage newDashboardPage;

  @BeforeEach
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
    assertEquals(caseName, searchResultPage.getGlobalSearchCaseResult(0));
    assertTrue(searchResultPage.isCaseCategoryColumnDisplayed());
  }

  @Test
  public void testFindCaseByNameWithSpecialCharacter() {
    redirectToNewDashBoard();
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
