package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.CollectionCondition.size;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class DashboardFilterWidgetTest extends BaseTest {

  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String YOUR_CASES_WIDGET = "Your Cases";

  private static final String CREATE_USER_FILTER_URL =
      "portalKitTestHelper/153CACC26D0D4C3D/createUserTaskWidgetFilters.ivp";

  private NewDashboardPage dashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    dashboardPage = new NewDashboardPage();
  }

  @Test
  public void testSaveNewWidgetFilter() {
    TaskWidgetNewDashBoardPage taskWidget = createWidgetFilterForDemoUser();
    assumeTrue(taskWidget.hasSavedFilterItem("Tasks filter"));
  }

  @Test
  public void testDeleteWidgetFilter() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(CREATE_USER_FILTER_URL);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = openTaskWidgetFilter();
    taskWidget.clickOnManageFilterLink();
    taskWidget.getManageFilterDialog().shouldBe(Condition.visible);
    int totalSavedFilter = taskWidget.getTotalSavedFilterInManageFilterDialog();
    taskWidget.deleteFirstSavedFilter();
    assertTrue(taskWidget.getTotalSavedFilterInManageFilterDialog() != totalSavedFilter);
  }

  @Test
  public void testStickyWidgetFilters() {
    resizeBrowserTo2kResolution();
    TaskWidgetNewDashBoardPage taskWidget = createWidgetFilterForDemoUser();
    assumeTrue(taskWidget.hasSavedFilterItem("Tasks filter"));
    taskWidget.clickOnResetFilter();
    taskWidget.openFilterWidget();
    var filterId = taskWidget.clickOnASavedFilterItem("Tasks filter");
    taskWidget.getSelectedFilter(filterId).shouldHave(Condition.cssClass("selected"));
    taskWidget.applyFilter();
    int numberOfFiltersApplied = taskWidget.getNumberOfFilterApplied();
    refreshPage();
    redirectToNewDashBoard();
    assertTrue(numberOfFiltersApplied == taskWidget.getNumberOfFilterApplied());
  }

  @Test
  public void testSearchWidgetFilters() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(CREATE_USER_FILTER_URL);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = openTaskWidgetFilter();
    var totalSavedFilter = taskWidget.getTotalSavedFilters();
    taskWidget.searchWidgetFilter("Tasks Filterset 1");
    taskWidget.getSavedFilterItems().shouldHave(CollectionCondition.size(1));
    assertTrue(taskWidget.getTotalSavedFilters() != totalSavedFilter);
  }
  
  @Test
  public void testAddAndRemoveCategoryFilterForTaskWidget() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(CREATE_USER_FILTER_URL);
    redirectToNewDashBoard();

    
    TaskWidgetNewDashBoardPage taskWidget = openTaskWidgetFilter();
    taskWidget.addFilter("Category", null); // DEFAULT OPERATOR IS IN
    taskWidget.inputValueOnLatestFilter(FilterValueType.CATEGORY_TYPE, "TestCase11");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(size(1));

    taskWidget = openTaskWidgetFilter();
    taskWidget.removeFilter(0);
    taskWidget.applyFilter();

    taskWidget.countAllTasks().shouldHave(size(12));
  }
  
  @Test
  public void testAddAndRemoveCategoryFilterForCaseWidget() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(CREATE_USER_FILTER_URL);
    redirectToNewDashBoard();
    
    CaseWidgetNewDashBoardPage caseWidget = openCaseWidgetFilter();
    caseWidget.addFilter("Category", null); // DEFAULT OPERATOR IS IN
    caseWidget.inputValueOnLatestFilter(FilterValueType.CATEGORY_TYPE, "TestCase11");
    caseWidget.applyFilter();
    caseWidget.countAllCases().shouldHave(size(1));

    caseWidget = openCaseWidgetFilter();
    caseWidget.removeFilter(0);
    caseWidget.applyFilter();

    caseWidget.countAllCases().shouldHave(size(12));
  }


  private void loginAndGoToDashboard(TestAccount account) {
    login(account);
    redirectToNewDashBoard();
  }

  private TaskWidgetNewDashBoardPage createWidgetFilterForDemoUser() {
    ScreenshotUtils.maximizeBrowser();
    loginAndGoToDashboard(TestAccount.DEMO_USER);
    TaskWidgetNewDashBoardPage taskWidget = openTaskWidgetFilter();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, "Task");
    taskWidget.addFilter("Description", FilterOperator.NOT_EMPTY);
    taskWidget.addFilter("Expiry", FilterOperator.WITHIN_NEXT);
    taskWidget.inputValueOnLatestFilter(FilterValueType.WITHIN, "2","Year(s)");
    taskWidget.saveFilter("Tasks filter");
    return taskWidget;
  }

  private TaskWidgetNewDashBoardPage openTaskWidgetFilter() {
    TaskWidgetNewDashBoardPage taskWidget = dashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    return taskWidget;
  }

  private CaseWidgetNewDashBoardPage openCaseWidgetFilter() {
    CaseWidgetNewDashBoardPage caseWidget = dashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    caseWidget.openFilterWidget();
    return caseWidget;
  }

}
