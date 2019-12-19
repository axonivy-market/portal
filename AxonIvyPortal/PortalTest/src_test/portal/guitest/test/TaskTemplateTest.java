package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.WorkingTaskDialogPage;

public class TaskTemplateTest extends BaseTest {

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
    assertEquals(2, taskTemplatePage.countHistoryItems());
  }

  @Test
  public void testOpeningFinishedTaskInHistoryArea() {
    TaskTemplatePage taskTemplatePage = startATask();
    TaskWidgetPage taskWidget = taskTemplatePage.openFinishedTaskInHistoryArea();
    assertTrue(taskWidget.countTasks() > 0);
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
    HomePage homePage = dialogPage.leaveTask();
    TaskWidgetPage taskWidget = homePage.getTaskWidget();
    String state = taskWidget.getStateInCompactMode(0);
    assertEquals("OPEN", state);
  }
  
  @Test
  public void testReserveWorkingTaskByClickingOnLogo() {
    TaskTemplatePage taskTemplatePage = startATask();
    taskTemplatePage.clickOnLogo();
    WorkingTaskDialogPage dialogPage = new WorkingTaskDialogPage();
    HomePage homePage = dialogPage.reserveTask();
    TaskWidgetPage taskWidget = homePage.getTaskWidget();
    String state = taskWidget.getStateInCompactMode(0);
    assertEquals("RESERVED", state);
  }
  
  @Test
  public void testResetTaskWhenStartSideStep() {
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    ProcessWidgetPage processWidgetPage = mainMenuPage.selectProcessesMenu();
    processWidgetPage.enterSearchKeyword("case map");
    processWidgetPage.startProcess("Case Map Leave");
    TaskTemplatePage taskTemplatePage = startATask();
    taskTemplatePage.openSideStepMenu();
    taskTemplatePage.startSideStep();
    TaskWidgetPage taskWidget = new TaskWidgetPage();
    String state = taskWidget.getStateInCompactMode(0);
    assertEquals("OPEN", state);
  }

  private TaskTemplatePage startATask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.openStatusTab();
    return taskTemplatePage;
  }
}
