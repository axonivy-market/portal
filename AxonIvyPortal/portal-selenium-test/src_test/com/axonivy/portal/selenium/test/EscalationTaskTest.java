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
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
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
    TaskWidgetPage taskWidgetPage = new MainMenuPage().openTaskList();
    taskWidgetPage.openTask(SICK_LEAVE_REQUEST);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    taskDetailsPage.openActionPanel();
    taskDetailsPage.triggerEscalation();
    taskDetailsPage.getPriorityOfTask().shouldHave(Condition.text("EXCEPTION"));
    taskDetailsPage.getStateOfTask().shouldHave(Condition.text("Destroyed"));
    taskDetailsPage.back();
    taskWidgetPage.filterTasksBy(SICK_LEAVE_REQUEST_ESCALATED);
    assertTrue(taskWidgetPage.getFilterTasksByKeyword().attr("value").equalsIgnoreCase(SICK_LEAVE_REQUEST_ESCALATED));
    taskWidgetPage.countTasks().shouldHave(size(1));
  }

  @Test
  public void testTriggerEscalationTaskOnTaskList() {
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openTaskList();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy(SICK_LEAVE_REQUEST);
    assertTrue(taskWidgetPage.getFilterTasksByKeyword().attr("value").equals(SICK_LEAVE_REQUEST));
    taskWidgetPage.countTasks().shouldHave(size(1));
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.triggerEscalation();
    // Try to refresh data
    refreshPage();
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy(SICK_LEAVE_REQUEST_ESCALATED);
    assertTrue(taskWidgetPage.getFilterTasksByKeyword().attr("value").equalsIgnoreCase(SICK_LEAVE_REQUEST_ESCALATED));
    taskWidgetPage.countTasks().shouldHave(size(1));
    assertTrue(taskWidgetPage.getPriorityOfTask(0).equalsIgnoreCase("high"));
  }

  @Test
  public void testTriggerEscalationTaskOnRelatedTasksOfCase() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    CaseWidgetPage caseWidgetPage = NavigationHelper.navigateToCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCase(TRIGGER_ESCALATION_CASE);
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
