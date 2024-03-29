package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.ExpressApprovalPage;
import portal.guitest.page.ExpressEndPage;
import portal.guitest.page.ExpressFormDefinitionPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.ExpressReviewPage;
import portal.guitest.page.ExpressTaskPage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.TaskWidgetPage;

public class ExpressTest extends BaseTest{
  private static final int USER_TASK_INDEX = 0;
  private static final int APPROVAL_INDEX = 3;
  
  private static final int INPUT_TEXT_TYPE_INDEX = 0;
  private static final int INPUT_NUMBER_TYPE_INDEX = 1;
  
  private NewDashboardPage newDashboardPage;
  private TaskWidgetPage taskWidgetPage;
  ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
  @Override
  @Before
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
    ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description", Arrays.asList(responsible1));
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    assertEquals("Express Workflow", formDefinition.getTextOfCurrentBreadcrumb());

    newDashboardPage = formDefinition.goToHomeFromBreadcrumbWithWarning();
    assertEquals(true, newDashboardPage.isDisplayed());
  }
  
  private ExpressFormDefinitionPage configureExpressProcessWhenMultiApproval(ExpressProcessPage expressProcessPage) {
    ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    ExpressResponsible responsible2 = new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
    
    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description", Arrays.asList(responsible1, responsible2));
    
    expressProcessPage.addNewTask(0);
    expressProcessPage.createTask(1, APPROVAL_INDEX, "Task 2", "Task 2 description", Arrays.asList(responsible2));
    
    expressProcessPage.addNewTask(1);
    expressProcessPage.createTask(2, APPROVAL_INDEX, "Task 3", "Task 3 description", Arrays.asList(responsible1, responsible2));
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    formDefinition.createTextInputField("Input Text 1", INPUT_TEXT_TYPE_INDEX, false);
    formDefinition.createTextInputField("Input Number 2", INPUT_NUMBER_TYPE_INDEX, false);
    formDefinition.moveAllElementToDragAndDrogPanel();
    return formDefinition;
  }

  private void executeExpressProcessWhenMultiApproval() {
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    WaitHelper.waitForNavigation(expressTaskPage, () -> expressTaskPage.finish());
    login(TestAccount.ADMIN_USER);
    executeUserTask();
    login(TestAccount.DEMO_USER);
    executeApproval("Approved at first level", TestAccount.DEMO_USER.getFullName());
    executeApproval("Approved at second level", TestAccount.DEMO_USER.getFullName());
    login(TestAccount.ADMIN_USER);
    executeApproval("Approved at second level", "Task 3", 1, 0, TestAccount.ADMIN_USER.getFullName());
    login(TestAccount.DEMO_USER);
    
    String approvalResult = executeReview();
    Assert.assertEquals("Portal Demo User,Approved at first level,Yes,"
        + TestAccount.DEMO_USER.getFullName() + ",Approved at second level,Yes,"
        + TestAccount.ADMIN_USER.getFullName() + ",Approved at second level,Yes,"
        + TestAccount.DEMO_USER.getFullName() + ",Approved at first level,Yes,"
        + TestAccount.DEMO_USER.getFullName() + ",Approved at second level,Yes,"
        + TestAccount.ADMIN_USER.getFullName() + ",Approved at second level,Yes", approvalResult);
    new ExpressEndPage().finish();
  }

  private String executeReview() {
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.startTask(0);
    ExpressReviewPage reviewPage = new ExpressReviewPage();
    String approvalResult = reviewPage.getApprovalResult();
    reviewPage.finish();
    return approvalResult;
  }
  
  private void executeUserTask() {
    taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterByResponsible(TestAccount.ADMIN_USER.getFullName());
    taskWidgetPage.startTask(0);
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.finish();
    new TaskWidgetPage();
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
