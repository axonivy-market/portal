package com.axonivy.portal.selenium.test.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class TaskWidgetTest extends BaseTest {

  private TaskDetailsPage taskDetailsPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    updateGlobalVariable(Variable.ENABLE_PINED_TASK.getKey(), "true");
    createTestingTasks();
  }

  @Test
  public void testOpenRelatedCaseOfTask() {
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openTaskDetailsPageByAction(0);
    CaseDetailsPage casePage = taskWidget.openRelatedCaseOfTask();
    String caseName = casePage.getCaseName();
    casePage.checkCaseName(caseName);
  }

  @Test
  public void testBreadCrumbInTaskDetail() {
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();

    taskDetailsPage = taskWidget.openTaskDetailsPageByAction(0);
    assertEquals("Task: Maternity Leave Request", taskDetailsPage.getTextOfCurrentBreadcrumb());

    taskDetailsPage.clickTaskListBreadCrumb();
    taskWidget = new TopMenuTaskWidgetPage();
    assertEquals(true, taskWidget.isDisplayed());

    taskDetailsPage = taskWidget.openTaskDetailsPageByAction(0);
    taskDetailsPage.goToHomeFromBreadcrumb();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    assertEquals(true, newDashboardPage.isDisplayed());
  }

  @Test
  public void testDisplayTaskAndCaseCategory() {
    //login(TestAccount.ADMIN_USER);
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openTaskDetailsPageByAction(0);
    taskWidget.getTaskCategory().shouldHave(Condition.text("Other Leave/Maternity"));
    taskWidget.getCaseCategory().shouldHave(Condition.text("Leave Request"));
  }

  @Test
  public void testShowTaskCount() {
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.waitUntilTaskCountDifferentThanZero();
    assertEquals(4, taskWidget.countAllTasks().size(), "In Task list, Task Count != 4");
  }

  @Test
  public void testFilterDateOnStandardFields() {
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();

    taskWidget.openFilterWidget();
    taskWidget.addFilter("Created Date", FilterOperator.AFTER);
    taskWidget.inputValueOnLatestFilter(FilterValueType.DATE, "01/01/2024");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(4));

    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.resetFilter();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Completed Date", FilterOperator.BEFORE);
    taskWidget.inputValueOnLatestFilter(FilterValueType.DATE,
        LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(1));

    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.resetFilter();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Expiry", FilterOperator.WITHIN_NEXT);
    taskWidget.inputValueOnLatestFilter(FilterValueType.WITHIN, "2", "Year(s)");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(3));
  }

  @Test
  public void testPinAndUnpinTask() {
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.pinTaskByIndex(0);
    assertEquals(true, taskWidget.isTaskPinned(0));
    NavigationHelper.navigateToTaskList();
    taskWidget.unpinTaskByIndex(0);
    assertEquals(true, taskWidget.isTaskUnpinned(0));

  }

  @Test
  public void testTogglePinnedTask() {
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    for (int i = 2; i > 0; i--) {
      taskWidget.pinTaskByIndex(i);
    }
    taskWidget.togglePinnedTask();
    taskWidget.waitUntilTaskFilterReturnResultCount(2);
    int visibleTaskCount = taskWidget.countAllTasks().size();
    assertEquals(2, visibleTaskCount);
  }

  @Test
  public void testClickOnPinColumn() {
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.clickOnPinColumn(0);
    assertEquals(true, taskWidget.isTaskPinned(0));
    NavigationHelper.navigateToTaskList();
    taskWidget.clickOnPinColumn(0);
    assertEquals(true, taskWidget.isTaskUnpinned(0));
  }
}
