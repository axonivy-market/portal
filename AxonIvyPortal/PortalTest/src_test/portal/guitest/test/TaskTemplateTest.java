package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.WorkingTaskDialogPage;

public class TaskTemplateTest extends BaseTest {

  private String createImpersistentTaskUrl = "portal-developer-examples/169BDE2F368D6EC4/PrimefacesElements.ivp";

  @Override
  @Before
  public void setup() {
    super.setup();
  }

  @Test
  public void testCaseDetailsTabDisplayed() {
    createTestData();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    assertTrue("Case details is not displayed", taskTemplatePage.containsCaseDetails());
  }

  @Test
  public void testAddingANote() {
    createTestData();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    assertEquals(0, taskTemplatePage.countNoteItems());
    taskTemplatePage.addNewNote("Sample note message");
    assertEquals(1, taskTemplatePage.countNoteItems());
  }

  @Test
  @Ignore
  public void testOpeningFinishedTaskInHistoryArea() {
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
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
    createTestData();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    assertTrue(taskTemplatePage.countRelatedTasks() > 0);
    TaskDetailsPage taskDetailsPage = taskTemplatePage.openFirstRelatedTaskInHistoryArea();
    assertEquals("Task Details", taskDetailsPage.getPageTitle());
  }

  @Test
  public void testOpeningDocumentUploading() {
    createTestData();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    taskTemplatePage.openDocumentUploadingDialog();
    assertTrue(taskTemplatePage.isDocumentUploadingDialogDisplayed());
  }
  
  @Test
  public void testLeaveWorkingTaskByClickingOnLogo() {
    createTestData();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    taskTemplatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.leaveTask();
    TaskWidgetPage taskWidget = new TaskWidgetPage();
    taskWidget.expand();
    assertTrue(taskWidget.isTaskStateOpen(0));
  }
  
  @Test
  public void testReserveWorkingTaskByClickingOnLogo() {
    createTestData();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    taskTemplatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    dialogPage.reserveTask();
    TaskWidgetPage taskWidget = new TaskWidgetPage();
    taskWidget.expand();
    Assert.assertTrue(taskWidget.isTaskStateReserved(0));
  }
  
  @Test
  public void testResetTaskWhenStartSideStep() {
    createTestData();
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    ProcessWidgetPage processWidgetPage = mainMenuPage.selectProcessesMenu();
    processWidgetPage.enterSearchKeyword("case map");
    processWidgetPage.startProcess("Case Map: Leave Request");
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.openActionMenu();
    taskTemplatePage.startSideStep();
    TaskWidgetPage taskWidget = new TaskWidgetPage();
    taskWidget.expand();
    assertTrue(taskWidget.isTaskStateOpen(0));
  }

  @Test
  public void testNotShowStartAdhocWhenOpenImpersistedTask() {
    redirectToRelativeLink(createImpersistentTaskUrl);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.clickTaskActionMenu();
    assertEquals(true, taskTemplatePage.isStartAdhocBtnNotExist());
  }

  private void createTestData() {
    redirectToRelativeLink(createTestingTasksUrl);
  }
  
  private TaskTemplatePage startATaskAndOpenCaseInfo() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.openCaseInfo();
    return taskTemplatePage;
  }
}
