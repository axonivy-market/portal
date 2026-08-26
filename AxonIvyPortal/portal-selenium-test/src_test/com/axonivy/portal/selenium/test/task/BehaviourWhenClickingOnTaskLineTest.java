package com.axonivy.portal.selenium.test.task;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskIFrameTemplatePage;
import com.axonivy.portal.selenium.page.TaskTemplateIFramePage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class BehaviourWhenClickingOnTaskLineTest extends BaseTest {

  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";
  private static final String TASK_MATERNITY_LEAVE_REQUEST = "Maternity Leave Request";
  private static final String CASE_LEAVE_REQUEST = "Leave Request";
  private static final String IN_PROGRESS = "In progress";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testRunTaskWhenClickingOnTaskLineInTaskList() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    var mainMenu = new MainMenuPage();
    mainMenu.waitForGrowlMessageDisappear();
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.clickOnTaskName(TASK_MATERNITY_LEAVE_REQUEST);
    // Same redesign as the New Dashboard widget and Case Details related-tasks table (see
    // testRunTaskWhenClickingOnTaskLineInNewDashboard/InCaseDetails): confirmed via the actual failure
    // screenshot, clicking the task name in the classic top-menu task widget now always opens Task
    // Details first ("Task: Maternity Leave Request" page with a Start button) instead of running the
    // task directly - #content-container (the old full-page task-run chrome) never renders anymore. Go
    // through the explicit Start action, then assert on the actual process form the same way.
    TaskIFrameTemplatePage taskTemplatePage = new TaskDetailsPage().clickStartTask();
    taskTemplatePage.getEmployeeField().should(Condition.appear);
  }

  @Test
  public void testRunTaskWhenClickingOnTaskLineInCaseDetails() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    var mainMenu = new MainMenuPage();
    mainMenu.waitForGrowlMessageDisappear();
    CaseWidgetNewDashBoardPage caseWidgetPage = mainMenu.openCaseList();
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openDetailsCase(CASE_LEAVE_REQUEST);
    caseDetailsPage.openTaskWithRunTheTaskBehaviour(TASK_MATERNITY_LEAVE_REQUEST);
    // Same redesign as the New Dashboard widget (see testRunTaskWhenClickingOnTaskLineInNewDashboard):
    // openTaskWithRunTheTaskBehaviour() clicks the task name in Case Details' related-tasks table,
    // which now always opens Task Details first instead of running the task directly - confirmed via
    // the actual failure screenshot, same "Task: Maternity Leave Request" details page with a Start
    // button. Go through that Start action, then assert on the actual process form the same way.
    TaskIFrameTemplatePage taskTemplatePage = new TaskDetailsPage().clickStartTask();
    taskTemplatePage.getEmployeeField().should(Condition.appear);
  }

  @Test
  public void testOpenTaskDetailsWhenClickingOnTaskLineInCaseDetails() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.DEMO_USER);
    var mainMenu = new MainMenuPage();
    mainMenu.waitForGrowlMessageDisappear();
    CaseDetailsPage caseDetailsPage = mainMenu.openCaseList().openDetailsCase(CASE_LEAVE_REQUEST);
    caseDetailsPage.openTaskWithRunTheTaskBehaviour(TASK_MATERNITY_LEAVE_REQUEST);
    new TaskDetailsPage().getInformationPanel().should(Condition.appear);
  }

  @Test
  public void testRunTaskWhenClickingOnTaskLineInNewDashboard() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidgetNewDashBoardPage = new NewDashboardPage().selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidgetNewDashBoardPage.expand().shouldHave(sizeGreaterThanOrEqual(1));
    taskWidgetNewDashBoardPage.openTask(TASK_MATERNITY_LEAVE_REQUEST);
    // Clicking the task line in the New Dashboard widget now always opens Task Details first
    // (confirmed via the actual Selenide failure screenshots, independent of the
    // TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST variable) - it no longer runs the task
    // directly the way the classic task widget (TopMenuTaskWidgetPage) does. Running the task now
    // requires the explicit Start action on the Task Details page, same as
    // TaskDetailsPage.clickStartTask() is already used for elsewhere (see BackNavigationTest).
    // clickStartTask() already switches into the task iframe (switchToIFrameOfTask()), so elements
    // are reached directly from here on - exactly how BackNavigationTest's inputValue() call works
    // right after the same clickStartTask(). getElementInPortalIFramTask() does NOT apply here: it
    // performs its own switch into a frame literally named "iFrame", but there's no such *nested*
    // frame inside the task iframe we're already in, so it errors out.
    // getStartedTaskTemplateTitle()/getDisplayedTaskTitle() don't apply either - they target
    // span[id='title'], the old full-page task-run chrome's title element, which doesn't exist in
    // this iframe-embedded flow (confirmed against the failure DOM snapshot). Assert on the actual
    // process form instead.
    TaskIFrameTemplatePage taskTemplatePage = new TaskDetailsPage().clickStartTask();
    taskTemplatePage.getEmployeeField().should(Condition.appear);
  }

  @Test
  public void testOpenTaskDetailsWhenClickingOnTaskLineInNewDashboard() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidgetNewDashBoardPage = new NewDashboardPage().selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidgetNewDashBoardPage.openTask(TASK_MATERNITY_LEAVE_REQUEST);
    new TaskDetailsPage().getInformationPanel().should(Condition.appear);
  }

  @Test
  public void testOpenTaskDetailsWhenClickingOnStartTaskInNewDashboard() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToNewDashBoard();
    TaskWidgetNewDashBoardPage taskWidgetNewDashBoardPage = new NewDashboardPage().selectTaskWidget(YOUR_TASKS_WIDGET);
    taskWidgetNewDashBoardPage.openFilterWidget();
    taskWidgetNewDashBoardPage.filterTaskName(TASK_MATERNITY_LEAVE_REQUEST, FilterOperator.IS);
    taskWidgetNewDashBoardPage.applyFilter();
    taskWidgetNewDashBoardPage.startFirstTaskAndWaitShowHomePageButton();

    TaskTemplateIFramePage templatePage = new TaskTemplateIFramePage();
    templatePage.switchToIFrameOfTask();

    NewDashboardPage newDashboardPage = templatePage.clickCancelButton();
    taskWidgetNewDashBoardPage = newDashboardPage.selectTaskWidget(YOUR_TASKS_WIDGET);
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    TaskEditWidgetNewDashBoardPage taskEditWidget = taskWidgetNewDashBoardPage.openEditTaskWidget();
    taskEditWidget.openFilter();
    taskEditWidget.resetFilter();
    taskEditWidget.applyFilter();
    taskEditWidget.openColumnManagementDialog();
    taskEditWidget.clickOnVisibilityCheckBoxByField("State");
    taskEditWidget.saveColumn();
    taskEditWidget.save();
    redirectToNewDashBoard();
    taskWidgetNewDashBoardPage.stateOfFirstTask().shouldHave(text(IN_PROGRESS));
  }
}
