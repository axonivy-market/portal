package portal.guitest.test;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.ExpressApprovalPage;
import portal.guitest.page.ExpressEndPage;
import portal.guitest.page.ExpressFormDefinitionPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.ExpressReviewPage;
import portal.guitest.page.ExpressTaskPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.TaskWidgetPage;

public class ExpressTest extends BaseTest{
  private static final int USER_TASK_INDEX = 0;
  private static final int APPROVAL_INDEX = 3;
  
  private static final int INPUT_TEXT_TYPE_INDEX = 0;
  private static final int INPUT_NUMBER_TYPE_INDEX = 1;
  
  private HomePage homePage;
  private ProcessWidgetPage processWidget;
  private TaskWidgetPage taskWidgetPage;
  ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
  }

  @Test
  public void testAdhocMultiApprovalWhenMultiTask() {
    goToCreateExpressProcess();
//    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
//    expressProcessPage.fillProcessProperties(true, true, "Test approval", "Test description");
//    ExpressFormDefinitionPage formDefinition = configureExpressProcessWhenMultiApproval(expressProcessPage);
//    formDefinition.executeWorkflow();
//    executeExpressProcessWhenMultiApproval();
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
    expressTaskPage.finish();
    login(TestAccount.ADMIN_USER);
    executeUserTask();
    assertEquals(0, new TaskWidgetPage().countTasks());
    login(TestAccount.DEMO_USER);
    executeApproval("Approved at first level");
    executeApproval("Approved at second level");
    assertEquals(0, new TaskWidgetPage().countTasks());
    login(TestAccount.ADMIN_USER);
    executeApproval("Approved at second level");
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
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.startTask(0);
    ExpressReviewPage reviewPage = new ExpressReviewPage();
    String approvalResult = reviewPage.getApprovalResult();
    reviewPage.finish();
    return approvalResult;
  }
  
  private void executeUserTask() {
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.startTask(0);
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    expressTaskPage.finish();
    HomePage home = new HomePage();
    home.waitForPageLoaded();
  }
  
  private void executeApproval(String comment) {
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.startTask(0);
    ExpressApprovalPage approvalPage1 = new ExpressApprovalPage();
    approvalPage1.comment(comment);
    approvalPage1.approve();
    HomePage home = new HomePage();
    home.waitForPageLoaded();
  }

  private void goToCreateExpressProcess() {
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    processWidget.openExpressPage();
  }
}
