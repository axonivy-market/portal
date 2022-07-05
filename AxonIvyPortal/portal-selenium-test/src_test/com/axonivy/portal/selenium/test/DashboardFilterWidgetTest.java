package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class DashboardFilterWidgetTest extends BaseTest {

  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  private static final String CREATE_USER_FILTER_URL = "portalKitTestHelper/153CACC26D0D4C3D/createUserTaskWidgetFilters.ivp";

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
  public void testApplySavedFilters() {
    TaskWidgetNewDashBoardPage taskWidget = createWidgetFilterForDemoUser();
    assumeTrue(taskWidget.hasSavedFilterItem("Tasks filter"));
    taskWidget.clickOnResetFilter();
    taskWidget.openFilterWidget();
    var filterId = taskWidget.clickOnASavedFilterItem("Tasks filter");
    taskWidget.getSelectedFilter(filterId).shouldHave(Condition.cssClass("selected"));
    assertEquals("Task", taskWidget.getTaskNameFilterValue());
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
    taskWidget.filterTaskName("Task");
    taskWidget.filterCategories("TaskGroup");
    taskWidget.filterPriority("Normal", "Low");
    taskWidget.clickOnSaveFilterButton();
    taskWidget.saveANewWidgetFilter("Tasks filter");
    return taskWidget;
  }

  private TaskWidgetNewDashBoardPage openTaskWidgetFilter() {
    TaskWidgetNewDashBoardPage taskWidget = dashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    return taskWidget;
  }
}
