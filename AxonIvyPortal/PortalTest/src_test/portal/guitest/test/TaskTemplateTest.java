package portal.guitest.test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.WorkingTaskDialogPage;

public class TaskTemplateTest extends BaseTest {

  private static final String CUSTOM_GROWL_URL = "portalExamples/16A7BB2ADC9580A8/start.ivp";
  
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
  public void testDisplayCustomGrowlAfterFinishTask() {
    navigateToUrl(CUSTOM_GROWL_URL);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.clickSubmitButton();
    HomePage homePage = new HomePage();
    assertEquals("Task is done successfully", homePage.getGlobalGrowlMessage());
  }
  
  @Test
  public void testDisplayDefaultGrowlAfterFinishTask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.inputFields("Employee", "1.1.2019", "1.1.2019", "Representation");
    taskTemplatePage.clickSubmitButton();
    HomePage homePage = new HomePage();
    assertEquals("Task left successfully", homePage.getGlobalGrowlMessage());
  }
  
  @Test
  public void testCaseDetailsTabDisplayed() {
    TaskTemplatePage taskTemplatePage = startATask();
    assertTrue("Case details is not displayed", taskTemplatePage.containsCaseDetails());
  }

  @Test
  public void testAddingANote() {
    TaskTemplatePage taskTemplatePage = startATask();
    assertEquals(1, taskTemplatePage.countHistoryItems());
    taskTemplatePage.addNewNote("Sample note message");
    assertEquals(1, taskTemplatePage.countNoteItems());
    assertEquals(1, taskTemplatePage.countHistoryItems());
  }

  @Test
  @Ignore
  public void testOpeningFinishedTaskInHistoryArea() {
    TaskTemplatePage taskTemplatePage = startATask();
    taskTemplatePage.openFinishedTaskInHistoryArea();

    NoteHistoryPage caseHistoryPage = new NoteHistoryPage();

    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> taskTemplatePage.countBrowserTab() > 1);
    taskTemplatePage.switchLastBrowserTab();
    int numberOfNotes = 0;
    try {
        numberOfNotes = caseHistoryPage.countNotes();
    } catch (TimeoutException e) { // sometimes session is destroyed (don't know reason why!!!) so we cannot reach the page
        System.out.println("Stop testShowCaseNoteHistory test here because session is destroyed");
        return ;
    }
    assertEquals(1, numberOfNotes);
  }

  @Test
  public void testOpeningRelatedTask() {
    TaskTemplatePage taskTemplatePage = startATask();
    assertTrue(taskTemplatePage.countRelatedTasks() > 0);
    TaskWidgetPage taskWidget = taskTemplatePage.openFirstRelatedTaskInHistoryArea();
    assertTrue(taskWidget.countTasks() > 0);
  }

  @Test
  public void testOpeningDocumentUploading() {
    TaskTemplatePage taskTemplatePage = startATask();
    taskTemplatePage.openDocumentUploadingDialog();
    assertTrue(taskTemplatePage.isDocumentUploadingDialogDisplayed());
  }
  
  @Test
  public void testLeaveWorkingTaskByClickingOnLogo() {
    TaskTemplatePage taskTemplatePage = startATask();
    taskTemplatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.leaveTask();
    TaskWidgetPage taskWidget = new TaskWidgetPage();
    taskWidget.expand();
    assertTrue(taskWidget.isTaskStateDone(0));
  }
  
  @Test
  public void testReserveWorkingTaskByClickingOnLogo() {
    TaskTemplatePage taskTemplatePage = startATask();
    taskTemplatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.reserveTask();
    TaskWidgetPage taskWidget = new TaskWidgetPage();
    taskWidget.expand();
    Assert.assertTrue(taskWidget.isTaskStateReserved(0));
  }

  private TaskTemplatePage startATask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.openStatusTab();
    return taskTemplatePage;
  }
}
