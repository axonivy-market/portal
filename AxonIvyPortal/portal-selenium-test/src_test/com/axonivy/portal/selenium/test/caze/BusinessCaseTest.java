package com.axonivy.portal.selenium.test.caze;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.SearchResultPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.page.TemplatePage.GlobalSearch;

@IvyWebTest
public class BusinessCaseTest extends BaseTest {

  private static final String TECHNICAL_CASE_NAME = "TECH: Update checkin time";
  private static final String BUSINESS_CASE_NAME = "Update checkin time";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(businessCaseUrl);
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testOnlyDisplayBusinessCaseOnCaseList() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed(BUSINESS_CASE_NAME));
    assertFalse(casePage.isCaseDisplayed(TECHNICAL_CASE_NAME));
  }

  @Test
  public void testOnlyDisplayBusinessCaseOnCaseListWithAdmin() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);

    NewDashboardPage newDashboardPage = new NewDashboardPage();
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed(BUSINESS_CASE_NAME));
    assertFalse(casePage.isCaseDisplayed(TECHNICAL_CASE_NAME));
  }

  @Test
  public void testOnlyDisplayBusinessCaseOnGlobalSearch() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    GlobalSearch globalSearch = newDashboardPage.getGlobalSearch();
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(BUSINESS_CASE_NAME);
    searchResultPage.openCaseTab();
    assertEquals(1, searchResultPage.countCase());
    assertEquals(BUSINESS_CASE_NAME, searchResultPage.getGlobalSearchCaseResult(0));
  }

  @Test
  public void testTaskOfTechnicalCaseDisplayBusinessCaseOnTaskDetails() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    int firstTask = 0;
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.openTaskDetails(firstTask);
    assertTrue(taskWidgetPage.getRelatedCase().contains(BUSINESS_CASE_NAME));
  }
}
