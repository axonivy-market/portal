package portal.guitest.test;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.BaseTest;
import portal.guitest.page.DefaultExpresTaskPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.WorkingTaskDialogPage;

public class AdhocExpressTest extends BaseTest {

  private TaskWidgetPage taskWidgetPage;
  private TaskTemplatePage taskTemplatePage;
  private DefaultExpresTaskPage defaultExpressTaskPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testAddAdhocForTask() {
    String taskNamePrefix = "Maternity";
    String defaultTaskName1 = "Task 1";
    String defaultTaskName2 = "Task 2";
    String defaultTaskComment1 = "good";
    String defaultTaskComment2 = "it's okay";
    
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
    expressPage.createDefaultTask(0, defaultTaskName1, responsibles);
    expressPage.addNewTask(0);
    expressPage.createDefaultTask(1, defaultTaskName2, responsibles);
    expressPage.executeDirectly();
    
    //first task of adhoc
    defaultExpressTaskPage = new DefaultExpresTaskPage();
    defaultExpressTaskPage.enterTextToDefaultTask(defaultTaskComment1);
    defaultExpressTaskPage.finishDefaultTask();
    
    //approval task of adhoc
    new HomePage().isDisplayed();
    taskWidgetPage.filterTasksBy(defaultTaskName2);
    assertEquals(1, taskWidgetPage.countTasks());
    taskWidgetPage.startTask(0);
    defaultExpressTaskPage = new DefaultExpresTaskPage();
    defaultExpressTaskPage.enterTextToDefaultTask(defaultTaskComment2);
    defaultExpressTaskPage.finishDefaultTask();
    
    //check if task Maternity task
    new HomePage().isDisplayed();
    taskWidgetPage.filterTasksBy(taskNamePrefix);
    assertEquals(1, taskWidgetPage.countTasks());
    taskWidgetPage.startTask(0);
    
    //check adhoc history
    assertEquals(true, taskTemplatePage.isAdhocHistoryDialogExist());
    assertEquals(defaultTaskName2, taskTemplatePage.getTaskNameOfAdhocHistoryRow(0));
    assertEquals(defaultTaskComment2, taskTemplatePage.getCommentOfAdhocHistoryRow(0));
    assertEquals(defaultTaskName1, taskTemplatePage.getTaskNameOfAdhocHistoryRow(1));
    assertEquals(defaultTaskComment1, taskTemplatePage.getCommentOfAdhocHistoryRow(1));
    
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
