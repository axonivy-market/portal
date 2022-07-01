package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
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

@IvyWebTest
public class EscalationTaskTest extends BaseTest {

  // WIDGET NAME
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String SICK_LEAVE_REQUEST = "Sick Leave Request";
  private static final String SICK_LEAVE_REQUEST_ESCALATED = "Sick Leave Request Escalated";
  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";
  private static final String TRIGGER_ESCALATION_CASE= "Create Test Data For Trigger Escalation";
  
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
    taskWidgetPage.countTasks().shouldHaveSize(1);
  }
  
  @Test
  public void testTriggerEscalationTaskOnTaskList() {
    login(TestAccount.ADMIN_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openTaskList();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy(SICK_LEAVE_REQUEST);
    assertTrue(taskWidgetPage.getFilterTasksByKeyword().attr("value").equals(SICK_LEAVE_REQUEST));
    taskWidgetPage.countTasks().shouldHaveSize(1);
    taskWidgetPage.clickOnTaskActionLink(0);
    taskWidgetPage.triggerEscalation();
    // Try to refresh data
    refreshPage();
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy(SICK_LEAVE_REQUEST_ESCALATED);
    assertTrue(taskWidgetPage.getFilterTasksByKeyword().attr("value").equalsIgnoreCase(SICK_LEAVE_REQUEST_ESCALATED));
    taskWidgetPage.countTasks().shouldHaveSize(1);
    assertTrue(taskWidgetPage.getPriorityOfTask(0).equalsIgnoreCase("high"));
  }
  
  @Test
  public void testTriggerEscalationTaskOnRelatedTasksOfCase() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    login(TestAccount.ADMIN_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCase(TRIGGER_ESCALATION_CASE);
    caseDetailsPage.getNameOfRelatedTask(1).shouldHave(Condition.text(SICK_LEAVE_REQUEST));
    caseDetailsPage.clickRelatedTaskActionButton(1);
    caseDetailsPage.triggerEscalationTask(1);
    refreshPage();
    caseDetailsPage = new CaseDetailsPage();
    caseDetailsPage.getNameOfRelatedTask(1).shouldHave(Condition.text(SICK_LEAVE_REQUEST));
    caseDetailsPage.getStateOfRelatedTask(1).shouldHave(Condition.text("Destroyed"));
    caseDetailsPage.getNameOfRelatedTask(3).shouldHave(Condition.text(SICK_LEAVE_REQUEST_ESCALATED));
  }
  
  @Test
  public void testTriggerEscalationTaskWidgetOfDashboard() {
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidget = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    filterTaskByNameAndState(SICK_LEAVE_REQUEST,"Suspended");
    taskWidget.triggerEscalationTask(0);
    filterTaskByNameAndState(SICK_LEAVE_REQUEST,"Destroyed");
  }
  
  private void filterTaskByNameAndState(String name, String state) {
    TaskWidgetNewDashBoardPage taskWidget = new TaskWidgetNewDashBoardPage();
    taskWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidget.openFilterWidget();
    taskWidget.resetFilter();
    taskWidget.openFilterWidget();
    taskWidget.filterTaskName(name);
    taskWidget.filterTaskState();
    taskWidget.selectState(state);
    taskWidget.applyFilter();
  }
}
