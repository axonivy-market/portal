package portal.guitest.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.DefaulExpresTaskPage;
import portal.guitest.page.ExpressApprovalPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.WorkingTaskDialogPage;

public class AdhocExpressTest extends BaseTest {

  private TaskWidgetPage taskWidgetPage;
  private TaskTemplatePage taskTemplatePage;
  private DefaulExpresTaskPage defaultExpressTaskPage;
  private ExpressApprovalPage expressApprovalPage;
  private HomePage homePage;

  @Override
  @Before
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
  }

  @Test
  public void testAddAdhocForTask() {
    String taskNamePrefix = "Maternity";
    String defaultTaskName = "Task 1";
    String approvalTaskName = "Approval 1: " + defaultTaskName;
    String defaultTaskComment = "good";
    String approvalTaskComment = "it's okay";
    
    //check if task Maternity exists
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy(taskNamePrefix);
    assertEquals(1, taskWidgetPage.countTasks());
    String taskId = taskWidgetPage.getTaskId();
    taskTemplatePage = taskWidgetPage.startTask(0);
    
    //create adhoc from Maternity task
    assertEquals(true, taskTemplatePage.isShowAdhocHistoryBtnNotExist());
    taskTemplatePage.clickAdhocCreationButton();
    assertEquals("You may lose your work in progress and start the Ad-hoc process. Do you want to continue?", taskTemplatePage.getAdhocCreationMessage());
    taskTemplatePage.clickAdhocOkButton();
    
    //create tasks in adhoc page
    ExpressProcessPage expressPage = new ExpressProcessPage();
    String processName = expressPage.getProcessName();
    assertEquals(String.format("AdHoc Process for Task %s - Maternity Leave Request", taskId), processName);
    ExpressResponsible responsible = new ExpressResponsible("demo", false);
    List<ExpressResponsible> responsibles = Arrays.asList(responsible);
    expressPage.createDefaultTask(0, defaultTaskName, responsibles);
    expressPage.addNewTask(0);
    expressPage.createDefaultTask(1, null, responsibles);
    expressPage.executeDirectlyAndGoToHomePage();
    
    //first task of adhoc
    assertEquals(0, taskWidgetPage.countTasks()); //no task name Maternity
    taskWidgetPage.filterTasksBy(defaultTaskName);
    taskWidgetPage.startTask(0);
    defaultExpressTaskPage = new DefaulExpresTaskPage();
    defaultExpressTaskPage.enterTextToDefaultTask(defaultTaskComment);
    defaultExpressTaskPage.finishDefaultTask();
    
    //approval task of adhoc
    homePage = new HomePage();
    taskWidgetPage.filterTasksBy(approvalTaskName);
    assertEquals(1, taskWidgetPage.countTasks());
    taskWidgetPage.startTask(0);
    expressApprovalPage = new ExpressApprovalPage();
    expressApprovalPage.comment(approvalTaskComment);
    expressApprovalPage.approve();
    
    //check if task Maternity task
    homePage = new HomePage();
    taskWidgetPage.filterTasksBy(taskNamePrefix);
    assertEquals(1, taskWidgetPage.countTasks());
    taskWidgetPage.startTask(0);
    
    //check adhoc history
    assertEquals(true, taskTemplatePage.isAdhocHistoryDialogExist());
    assertEquals(approvalTaskName, taskTemplatePage.getTaskNameOfAdhocHistoryRow(0));
    assertEquals("Approved - " + approvalTaskComment, taskTemplatePage.getCommentOfAdhocHistoryRow(0));
    assertEquals(defaultTaskName, taskTemplatePage.getTaskNameOfAdhocHistoryRow(1));
    assertEquals(defaultTaskComment, taskTemplatePage.getCommentOfAdhocHistoryRow(1));
    
    //open again by clicking adhoc dialog icon
    taskTemplatePage.closeAdhocHistoryDialog();
    taskTemplatePage.clickShowAdhocHistoryBtn();
    assertEquals(true, taskTemplatePage.isAdhocHistoryDialogExist());
    taskTemplatePage.closeAdhocHistoryDialog();
    
    //open Maternity task again and make sure adhoc history dialog doesn't appear
    taskTemplatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.leaveTask();
    taskWidgetPage.startTask(0);
    assertEquals(false, taskTemplatePage.isAdhocHistoryDialogExist());
    
    //click adhoc creation button and check warning message
    taskTemplatePage.clickAdhocCreationButton();
    assertEquals("There is already an adhoc process for this task, do you want to create another one?", taskTemplatePage.getAdhocCreationMessage());
  }
  
}
