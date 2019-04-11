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
    
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy(taskNamePrefix);
    assertEquals(1, taskWidgetPage.countTasks());
    startATask();
    
    String taskId = taskTemplatePage.getTaskId();
    assertEquals(true, taskTemplatePage.isShowAdhocHistoryBtnNotExist());
    taskTemplatePage.clickAdhocButton();
    taskTemplatePage.clickAdhocOkButton();
    
    ExpressProcessPage expressPage = new ExpressProcessPage();
    String processName = expressPage.getProcessName();
    assertEquals(String.format("AdHoc Process for Task %s - Maternity Leave Request", taskId), processName);
    ExpressResponsible responsible = new ExpressResponsible("demo", false);
    List<ExpressResponsible> responsibles = Arrays.asList(responsible);
    expressPage.createDefaultTask(0, defaultTaskName, responsibles);
    expressPage.addNewTask(0);
    expressPage.createDefaultTask(1, null, responsibles);
    expressPage.executeDirectlyAndGoToHomePage();
    
    assertEquals(0, taskWidgetPage.countTasks()); //no task name Maternity
    taskWidgetPage.filterTasksBy(defaultTaskName);
    startATask();
    defaultExpressTaskPage = new DefaulExpresTaskPage();
    defaultExpressTaskPage.enterTextToDefaultTask(defaultTaskComment);
    defaultExpressTaskPage.finishDefaultTask();
    
    homePage = new HomePage();
    taskWidgetPage.filterTasksBy(approvalTaskName);
    assertEquals(1, taskWidgetPage.countTasks());
    startATask();
    expressApprovalPage = new ExpressApprovalPage();
    expressApprovalPage.comment(approvalTaskComment);
    expressApprovalPage.approve();
    
    homePage = new HomePage();
    taskWidgetPage.filterTasksBy(taskNamePrefix);
    assertEquals(1, taskWidgetPage.countTasks());
    startATask();
    assertEquals(true, taskTemplatePage.isAdhocHistoryDialogExist());
  }
  
  private void startATask() {
    taskTemplatePage = taskWidgetPage.startTask(0);
  }
}
