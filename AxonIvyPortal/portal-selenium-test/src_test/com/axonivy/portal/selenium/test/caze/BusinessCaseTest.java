package com.axonivy.portal.selenium.test.caze;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.SearchResultPage;
import com.axonivy.portal.selenium.page.TemplatePage.GlobalSearch;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;

@IvyWebTest
public class BusinessCaseTest extends BaseTest {

  private static final String BUSINESS_CASE_NAME = "Update checkin time";


  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(businessCaseUrl);
  }

  @Test
  public void testOnlyDisplayBusinessCaseOnGlobalSearch() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    GlobalSearch globalSearch = newDashboardPage.getGlobalSearch();
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(BUSINESS_CASE_NAME);
    searchResultPage.openCaseTab();
    assertEquals(1, searchResultPage.countCase());
    assertEquals(BUSINESS_CASE_NAME, searchResultPage.getCaseResult(0));
  }

  @Test
  public void testTaskOfTechnicalCaseDisplayBusinessCaseOnTaskDetails() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    int firstTask = 0;
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openTaskDetailsPageByAction(firstTask);
    assertTrue(taskWidget.getRelatedCase().contains(BUSINESS_CASE_NAME));
  }
}
