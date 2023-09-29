package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.common.TestAccount;
import portal.guitest.common.Variable;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.SearchResultPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class BusinessCaseTest extends BaseTest {

  private static final String TECHNICAL_CASE_NAME = "TECH: Update checkin time";
  private static final String BUSINESS_CASE_NAME = "Update checkin time";

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(businessCaseUrl);
  }

  @Test
  public void testOnlyDisplayBusinessCaseOnCaseList() {

    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    MainMenuPage mainMenuPage = newDashboardPage2.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed(BUSINESS_CASE_NAME));
    assertFalse(casePage.isCaseDisplayed(TECHNICAL_CASE_NAME));
  }
  
  @Test
  public void testOnlyDisplayBusinessCaseOnCaseListWithAdmin() {
    login(TestAccount.ADMIN_USER);

    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    MainMenuPage mainMenuPage = newDashboardPage2.openMainMenu();
    CaseWidgetPage casePage = mainMenuPage.selectCaseMenu();
    assertTrue(casePage.isCaseDisplayed(BUSINESS_CASE_NAME));
    assertFalse(casePage.isCaseDisplayed(TECHNICAL_CASE_NAME));
  }
  
  @Test
  public void testOnlyDisplayBusinessCaseOnGlobalSearch() {
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    GlobalSearch globalSearch = newDashboardPage2.getGlobalSearch();
    SearchResultPage searchResultPage = globalSearch.inputSearchKeyword(BUSINESS_CASE_NAME);
    searchResultPage.openCaseTab();
    assertEquals(1, searchResultPage.countCase());
    assertEquals(BUSINESS_CASE_NAME, searchResultPage.getCaseResult(0));
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
