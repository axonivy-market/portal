package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

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
public class BehaviourWhenClickingOnTaskLineTest extends BaseTest {

  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";
  private static final String TASK_MATERNITY_LEAVE_REQUEST = "Maternity Leave Request";
  private static final String CASE_LEAVE_REQUEST = "Leave Request";
  private TaskWidgetPage taskWidgetPage;
  private TaskDetailsPage taskDetailsPage;
  private NewDashboardPage newDashboardPage;
  private MainMenuPage mainMenuPage;
  private CaseWidgetPage caseWidgetPage;
  private CaseDetailsPage caseDetailsPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    taskWidgetPage = new TaskWidgetPage();
    taskDetailsPage = new TaskDetailsPage();
    newDashboardPage = new NewDashboardPage();
    mainMenuPage = new MainMenuPage();
    caseWidgetPage = new CaseWidgetPage();
    caseDetailsPage = new CaseDetailsPage();
  }

  @Test
  public void testRunTaskWhenClickingOnTaskLineInTaskList() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openTaskList();
    taskWidgetPage.runTaskWithRunTheTaskBehaviour(0);
    taskWidgetPage.getStartedTaskTemplateTitle().shouldHave(Condition.attribute("title", TASK_MATERNITY_LEAVE_REQUEST));
  }

  @Test
  public void testOpenTaskDetailsWhenClickingOnTaskLineInTaskList() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    mainMenuPage.openTaskList();
    taskWidgetPage.openTaskWithAccessTaskDetailsBehaviour(0);
    taskDetailsPage.getInformationPanel().should(Condition.appear);
  }

  @Test
  public void testRunTaskWhenClickingOnTaskLineInCaseDetails() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    mainMenuPage.openCaseList();
    caseWidgetPage.openCase(CASE_LEAVE_REQUEST);
    caseDetailsPage.openTaskWithRunTheTaskBehaviour(TASK_MATERNITY_LEAVE_REQUEST);
    caseWidgetPage.getStartedTaskTemplateTitle().shouldHave(Condition.attribute("title", TASK_MATERNITY_LEAVE_REQUEST));
  }

  @Test
  public void tesOpenTaskDetailsWhenClickingOnTaskLineInCaseDetails() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    mainMenuPage.openCaseList();
    caseWidgetPage.openCase(CASE_LEAVE_REQUEST);
    caseDetailsPage.openTaskWithRunTheTaskBehaviour(TASK_MATERNITY_LEAVE_REQUEST);
    taskDetailsPage.getInformationPanel().should(Condition.appear);
  }

  @Test
  public void testRunTaskWhenClickingOnTaskLineInNewDashboard() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidgetNewDashBoardPage = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidgetNewDashBoardPage.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidgetNewDashBoardPage.openTask(TASK_MATERNITY_LEAVE_REQUEST);
    caseWidgetPage.getStartedTaskTemplateTitle().shouldHave(Condition.attribute("title", TASK_MATERNITY_LEAVE_REQUEST));
  }

  @Test
  public void testOpenTaskDetailsWhenClickingOnTaskLineInNewDashboard() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidgetNewDashBoardPage = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidgetNewDashBoardPage.openTask(TASK_MATERNITY_LEAVE_REQUEST);
    taskDetailsPage.getInformationPanel().should(Condition.appear);
  }
}
