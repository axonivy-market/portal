package com.axonivy.portal.selenium.test.express;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.bean.ExpressResponsible;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.ExpressApprovalPage;
import com.axonivy.portal.selenium.page.ExpressEndPage;
import com.axonivy.portal.selenium.page.ExpressFormDefinitionPage;
import com.axonivy.portal.selenium.page.ExpressProcessPage;
import com.axonivy.portal.selenium.page.ExpressReviewPage;
import com.axonivy.portal.selenium.page.ExpressTaskPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.codeborne.selenide.ElementsCollection;



@IvyWebTest
public class ExpressTest extends BaseTest {
  private static final int USER_TASK_INDEX = 0;
  private static final int APPROVAL_INDEX = 3;

  private static final int INPUT_TEXT_TYPE_INDEX = 0;
  private static final int INPUT_NUMBER_TYPE_INDEX = 1;

  private NewDashboardPage newDashboardPage;
  private TaskWidgetPage taskWidgetPage;
  ExpressResponsible responsible1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testAdhocMultiApprovalWhenMultiTask() {
    goToExpressCreationPage();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test approval", "Test description");
    ExpressFormDefinitionPage formDefinition = configureExpressProcessWhenMultiApproval(expressProcessPage);
    formDefinition.executeWorkflow();
    executeExpressProcessWhenMultiApproval();
  }

  @Test
  public void testBreadCrumb() {
    goToExpressCreationPage();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    assertEquals("Express Workflow", expressProcessPage.getTextOfCurrentBreadcrumb());

    expressProcessPage.fillProcessProperties(true, true, "Test approval", "Test description");
    ExpressResponsible responsible1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description", Arrays.asList(responsible1));
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    assertEquals("Express Workflow", formDefinition.getTextOfCurrentBreadcrumb());

    newDashboardPage = formDefinition.goToHomeFromBreadcrumbWithWarning();
    assertEquals(true, newDashboardPage.isDisplayed());
  }

  private ExpressFormDefinitionPage configureExpressProcessWhenMultiApproval(ExpressProcessPage expressProcessPage) {
    ExpressResponsible responsible1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    ExpressResponsible responsible2 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description",
        Arrays.asList(responsible1, responsible2));

    expressProcessPage.addNewTask(0);
    expressProcessPage.createTask(1, APPROVAL_INDEX, "Task 2", "Task 2 description", Arrays.asList(responsible2));

    expressProcessPage.addNewTask(1);
    expressProcessPage.createTask(2, APPROVAL_INDEX, "Task 3", "Task 3 description",
        Arrays.asList(responsible1, responsible2));
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    formDefinition.createTextInputField("Input Text 1", INPUT_TEXT_TYPE_INDEX, false);
    formDefinition.createTextInputField("Input Number 2", INPUT_NUMBER_TYPE_INDEX, false);
    formDefinition.countElementPrepareToDrag(2);
    formDefinition.moveAllElementToDragAndDrogPanel();
    formDefinition.countElementPrepareToDrag(0);
    return formDefinition;
  }

  private void executeExpressProcessWhenMultiApproval() {
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.finish();
    redirectToNewDashBoard();
    login(TestAccount.ADMIN_USER);
    newDashboardPage.waitPageLoaded();
    executeUserTask();
    // work-around so that engine has more time to finish the task (we do not want
    // spend too much time on this test)
    NavigationHelper.navigateToCaseList();
    login(TestAccount.DEMO_USER);
    executeApproval("Approved at first level", TestAccount.DEMO_USER.getFullName());
    executeApproval("Approved at second level", TestAccount.DEMO_USER.getFullName());
    login(TestAccount.ADMIN_USER);
    executeApproval("Approved at second level", "Task 3", 1, 0, TestAccount.ADMIN_USER.getFullName());
    login(TestAccount.DEMO_USER);

    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.clearFilterInput();
    taskWidgetPage.startTask(0);
    ExpressReviewPage reviewPage = new ExpressReviewPage();
    ElementsCollection task2 = reviewPage.getApprovalResults(0);
    assertEquals("Portal Demo User Approved at first level Yes", task2.get(0).getText());
    ElementsCollection task3 = reviewPage.getApprovalResults(1);
    assertEquals("Portal Demo User Approved at second level Yes", task3.get(0).getText());
    assertEquals("Portal Admin User Approved at second level Yes", task3.get(1).getText());
    reviewPage.finish();
    new ExpressEndPage().finish();
  }

  private void executeUserTask() {
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterByResponsible(TestAccount.ADMIN_USER.getFullName());
    taskWidgetPage.startTask(0);
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.waitForPageLoad();
    expressTaskPage.finish();
    redirectToNewDashBoard();
  }

  private void executeApproval(String comment, String responsible) {
    executeApproval(comment, "Task", 1, 0, responsible);
  }

  private void executeApproval(String comment, String taskNameFilter, int expectedNumber, int startTaskIndex,
      String responsible) {
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterByResponsible(responsible);
    taskWidgetPage.filterTasksInExpandedModeBy(taskNameFilter, expectedNumber);
    taskWidgetPage.startTask(startTaskIndex);
    ExpressApprovalPage approvalPage1 = new ExpressApprovalPage();
    approvalPage1.comment(comment);
    approvalPage1.approve();
  }

  private void goToExpressCreationPage() {
    redirectToRelativeLink(expressStartLink);
  }
}
