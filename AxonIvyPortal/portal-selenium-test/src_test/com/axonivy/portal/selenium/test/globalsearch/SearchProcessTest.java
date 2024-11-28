package com.axonivy.portal.selenium.test.globalsearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.SearchResultPage;
import com.axonivy.portal.selenium.page.TemplatePage.GlobalSearch;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;

@IvyWebTest
public class SearchProcessTest extends BaseTest {

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testFindAndStartProcessByName() {
    GlobalSearch globalSearch = newDashboardPage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    String processName = "Simple Payment";
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(processName);
    searchResultPage.waitForFirstTabFinishedLoading();
    assertEquals(processName, searchResultPage.getProcessResult(processName));
    assertTrue(searchResultPage.isProcessGroupDisplay("S"));

    searchResultPage.startProcess(processName);
    newDashboardPage = new NewDashboardPage();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    refreshPage();
    assertTrue(taskWidget.getNameOfTaskAt(0).contains("Payment"));
  }

  @Test
  public void testFindCaseMapByName() {
    String caseMapName = "Case Map: Leave Request";
    GlobalSearch globalSearch = newDashboardPage.getGlobalSearch();
    assertTrue(globalSearch.isDisplayed());

    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(caseMapName);
    searchResultPage.waitForFirstTabFinishedLoading();
    assertEquals(caseMapName, searchResultPage.getProcessResult(caseMapName));
  }
}
