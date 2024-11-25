package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.size;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class EnhanceVisibilityTasksForMemberOfRoleTest extends BaseTest {


  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testVisibilityTaskOpen() {
    login(TestAccount.DEMO_USER);
    createTestingTasks();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();

    // Suspended
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Responsible", FilterOperator.IN);
    taskWidget.inputValueOnLatestFilter(FilterValueType.RESPONSIBLE_TYPE, "Everybody");
    taskWidget.addFilter("state", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Open");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(size(2), DEFAULT_TIMEOUT);
    // User Guest
    login(TestAccount.GUEST_USER);
    NavigationHelper.navigateToTaskList();
    // Suspended
    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Responsible", FilterOperator.IN);
    taskWidget.inputValueOnLatestFilter(FilterValueType.RESPONSIBLE_TYPE, "Everybody");
    taskWidget.addFilter("state", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Open");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(size(2), DEFAULT_TIMEOUT);
  }

  @Test
  public void testVisibilityTaskInprogress() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    login(TestAccount.DEMO_USER);
    createTestingTasks();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    // Suspended
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Responsible", FilterOperator.IN);
    taskWidget.inputValueOnLatestFilter(FilterValueType.RESPONSIBLE_TYPE, "Everybody");
    taskWidget.addFilter("state", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Open");
    taskWidget.applyFilter();
    // Reserved
    taskWidget.reserveTask(0);
    int countTasksReserved = taskWidget.countAllTasks().size();
    // User Guest
    login(TestAccount.GUEST_USER);
    NavigationHelper.navigateToTaskList();
    taskWidget = new TopMenuTaskWidgetPage();

    // Reserved
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Responsible", FilterOperator.IN);
    taskWidget.inputValueOnLatestFilter(FilterValueType.RESPONSIBLE_TYPE, "Everybody");
    taskWidget.addFilter("state", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Open");
    taskWidget.applyFilter();
    assertEquals(countTasksReserved, taskWidget.countAllTasks().size());
  }

  @Test
  public void testVisibilityTaskDone() {
    login(TestAccount.GUEST_USER);
    createTestingTasks();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    // Suspended
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Responsible", FilterOperator.IN);
    taskWidget.inputValueOnLatestFilter(FilterValueType.RESPONSIBLE_TYPE, "Everybody");
    taskWidget.addFilter("state", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Open");
    taskWidget.applyFilter();
    int taskCount = taskWidget.countAllTasks().size();

    // User Guest
    login(TestAccount.DEMO_USER);
    NavigationHelper.navigateToTaskList();
    taskWidget = new TopMenuTaskWidgetPage();;
    // Suspended
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Responsible", FilterOperator.IN);
    taskWidget.inputValueOnLatestFilter(FilterValueType.RESPONSIBLE_TYPE, "Everybody");
    taskWidget.addFilter("state", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Open");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(CollectionCondition.size(taskCount));
  }

}
