package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class DashboardFilterWidgetTest extends BaseTest {

  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  private static final String CREATE_USER_FILTER_URL = "portalKitTestHelper/153CACC26D0D4C3D/createUserTaskWidgetFilters.ivp";

  private NewDashBoardPage dashBoardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    dashBoardPage = new NewDashBoardPage();
    redirectToRelativeLink(create12CasesWithCategoryUrl);
  }

  @Test()
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
  public void testApplySavedFilters() {
    TaskWidgetNewDashBoardPage taskWidget = createWidgetFilterForDemoUser();
    assumeTrue(taskWidget.hasSavedFilterItem("Tasks filter"));
    taskWidget.clickOnResetFilter();
    taskWidget.openFilterWidget();
    var filterId = taskWidget.clickOnASavedFilterItem("Tasks filter");
    taskWidget.getSelectedFilter(filterId).shouldHave(Condition.cssClass("selected"));
    assertTrue(taskWidget.getTaskNameFilterValue().contains("Task"));
  }

  @Test
  public void testStickyWidgetFilters() {
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

  private void loginAndGoToDashboard(TestAccount account) {
    login(account);
    redirectToNewDashBoard();
  }

  private TaskWidgetNewDashBoardPage createWidgetFilterForDemoUser() {
    loginAndGoToDashboard(TestAccount.DEMO_USER);
    TaskWidgetNewDashBoardPage taskWidget = openTaskWidgetFilter();
    taskWidget.filterPriority("Normal", "Low");
    taskWidget.filterCategories("TaskGroup");
    taskWidget.filterTaskName("Task");
    taskWidget.clickOnSaveFilterButton();
    taskWidget.saveANewWidgetFilter("Tasks filter");
    return taskWidget;
  }

  private TaskWidgetNewDashBoardPage openTaskWidgetFilter() {
    TaskWidgetNewDashBoardPage taskWidget = dashBoardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    return taskWidget;
  }
}
