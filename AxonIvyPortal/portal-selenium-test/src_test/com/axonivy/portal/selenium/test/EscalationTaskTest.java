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
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class EscalationTaskTest extends BaseTest {

  // WIDGET NAME
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String SICK_LEAVE_REQUEST = "Sick Leave Request";
  private static final String SICK_LEAVE_REQUEST_ESCALATED = "Sick Leave Request Escalated";
  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";
  private static final String TRIGGER_ESCALATION_CASE = "Create Test Data For Trigger Escalation";

  static final String OPEN = "Open";
  static final String DESTROYED = "Destroyed ";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingEscalationTasksUrl);
  }

  @Test
  public void testTriggerEscalationTaskOnTaskDetails() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.clickOnTaskName(SICK_LEAVE_REQUEST);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.openActionPanel();
    taskDetailsPage.triggerEscalation();
    taskDetailsPage.getPriorityOfTask().shouldHave(Condition.text("EXCEPTION"));
    taskDetailsPage.getStateOfTask().shouldHave(Condition.text("Destroyed"));
    taskDetailsPage.back();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, SICK_LEAVE_REQUEST_ESCALATED);
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(size(1));
  }

  @Test
  public void testTriggerEscalationTaskOnTaskList() {
    login(TestAccount.ADMIN_USER);
    resizeBrowserToFullHDResolution();
    redirectToNewDashBoard();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("Name", FilterOperator.IS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, SICK_LEAVE_REQUEST);
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(size(1));
    taskWidget.triggerEscalationTask(0);
    // Try to refresh data
    refreshPage();
    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("State", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "Destroyed");
    taskWidget.applyFilter();
    taskWidget.countAllTasks().shouldHave(size(1));
    assertTrue("Destroyed".equalsIgnoreCase(taskWidget.stateOfFirstTask().text()));
  }

  @Test
  public void testTriggerEscalationTaskOnRelatedTasksOfCase() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    CaseWidgetNewDashBoardPage caseWidgetPage = NavigationHelper.navigateToCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsCase(TRIGGER_ESCALATION_CASE);
    caseDetailsPage.getNameOfRelatedTask(0).shouldHave(Condition.text(SICK_LEAVE_REQUEST));
    caseDetailsPage.clickRelatedTaskActionButton(0);
    caseDetailsPage.triggerEscalationTask(0);
    refreshPage();
    caseDetailsPage = new CaseDetailsPage();
    caseDetailsPage.getNameOfRelatedTask(2).shouldHave(Condition.text(SICK_LEAVE_REQUEST));
    caseDetailsPage.getStateOfRelatedTask(2).shouldHave(Condition.text("Destroyed"));
    caseDetailsPage.getNameOfRelatedTask(0).shouldHave(Condition.text(SICK_LEAVE_REQUEST_ESCALATED));
  }

  @Test
  public void testTriggerEscalationTaskWidgetOfDashboard() {
    createJSonFile("dashboard-has-one-task-widget.json", PortalVariable.DASHBOARD.key);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    filterTaskByNameAndState(SICK_LEAVE_REQUEST, OPEN);
    taskWidget.triggerEscalationTask(0);
    taskWidget.openFilterWidget();
    taskWidget.resetFilter();
    filterTaskByNameAndState(SICK_LEAVE_REQUEST, DESTROYED);
  }

  private void filterTaskByNameAndState(String name, String state) {
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.openFilterWidget();
    taskWidget.addFilter("State", null);
    taskWidget.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, state);
    taskWidget.addFilter("Name", FilterOperator.CONTAINS);
    taskWidget.inputValueOnLatestFilter(FilterValueType.TEXT, name);
    taskWidget.applyFilter();
  }
}
