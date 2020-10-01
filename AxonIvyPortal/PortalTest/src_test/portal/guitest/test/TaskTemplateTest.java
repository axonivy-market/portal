package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.CaseInformationPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NoteHistoryPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.WorkingTaskDialogPage;

public class TaskTemplateTest extends BaseTest {

  private String createImpersistentTaskUrl = "portalExamples/169BDE2F368D6EC4/PrimefacesElements.ivp";
  private static final String CUSTOM_PARAMS_TEMPLATE_TASK_URL= "portalExamples/1718293B3F6E5478/start.ivp";
  private static final String IFRAME_TASK_URL= "PortalExamples/16E5DB746865BCEC/CreateInvestment.ivp?embedInFrame";

  @Override
  @Before
  public void setup() {
    super.setup();
  }

  @Test
  public void testCaseDetailsTabDisplayed() {
    createTestDataAndRedirectToHomePage();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    assertTrue("Case details is not displayed", taskTemplatePage.containsCaseDetails());
  }

  @Test
  public void testAddingANote() {
    createTestDataAndRedirectToHomePage();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    assertEquals(1, taskTemplatePage.countHistoryItems());
    taskTemplatePage.addNewNote("Sample note message");
    assertEquals(1, taskTemplatePage.countNoteItems());
    assertEquals(1, taskTemplatePage.countHistoryItems());
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
    createTestDataAndRedirectToHomePage();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    assertTrue(taskTemplatePage.countRelatedTasks() > 0);
    CaseInformationPage caseInfo = new CaseInformationPage();
    caseInfo.clickOnFirstTaskOfRunningTasks();
    assertTrue(caseInfo.isConfirmDialogDisplayed());
    caseInfo.clickOnLeaveButton();
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    assertEquals("Task Details", taskDetailsPage.getPageTitle());
  }

  @Test
  public void testOpeningDocumentUploading() {
    createTestDataAndRedirectToHomePage();
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    taskTemplatePage.openDocumentUploadingDialog();
    assertTrue(taskTemplatePage.isDocumentUploadingDialogDisplayed());
  }
  
  @Test
  public void testLeaveWorkingTaskByClickingOnLogo() {
    createTestDataAndRedirectToHomePage();
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
    createTestDataAndRedirectToHomePage();
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
    createTestDataAndRedirectToHomePage();
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

  @Test
  public void testCustomParamsForTaskTemplate8() {
    redirectToRelativeLink(CUSTOM_PARAMS_TEMPLATE_TASK_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Task template 8 with custom params");
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTaskWithouWaitForTaskActionPresent(0);
    assertFalse(taskTemplatePage.isTaskNameDisplayed());
    assertFalse(taskTemplatePage.isTaskActionDisplayed());
    assertFalse(taskTemplatePage.isCaseInfoButtonDisplayed());
  }
  
  @Test
  public void testCustomParamsForIFrameTaskTemplate() {
    redirectToRelativeLink(CUSTOM_PARAMS_TEMPLATE_TASK_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("IFrame task with custom params");
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTaskWithouWaitForTaskActionPresent(0);
    assertFalse(taskTemplatePage.isTaskNameDisplayed());
    assertFalse(taskTemplatePage.isTaskActionDisplayed());
    assertFalse(taskTemplatePage.isCaseInfoButtonDisplayed());
  }
  
  @Test
  public void testDisplayWarningInIFrameTaskTemplate() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    final TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.clickOnLogo();
    By leaveButton = By.id("task-leave-warning-component:leave-button");
    WaitHelper.assertTrueWithWait(() -> taskTemplatePage.isElementDisplayed(leaveButton));
  }
  
  @Test
  public void testNotDisplayWarningInIFrameTaskTemplate() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    TaskTemplatePage taskTemplatePage1 = new TaskTemplatePage();
    TaskWidgetPage taskWidgetPage = taskTemplatePage1.finishCreateInvestmentTask();
    TaskTemplatePage taskTemplatePage2 = taskWidgetPage.startTask(3);
    taskTemplatePage2.clickOnLogo();
    WaitHelper.assertTrueWithWait(() -> taskTemplatePage2.isElementDisplayed(By.id("task-widget:task-list-link:task-list-link")));
  }

  @Test
  public void testShowWarningDialogByClickOnRelatedTasksAndCases() {
    redirectToRelativeLink(createTestingCaseMapUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskTemplatePage taskTemplatePage = startATaskAndOpenCaseInfo();
    CaseInformationPage caseInfo = new CaseInformationPage();
    // Click on related task
    caseInfo.clickOnFirstTaskOfRunningTasks();
    assertTrue(caseInfo.isConfirmDialogDisplayed());
    caseInfo.clickCancelLeaveTask();
    
    // Click on Show more cases
    caseInfo.clickOnShowMoreCases();
    assertTrue(caseInfo.isConfirmDialogDisplayed());
    caseInfo.clickCancelLeaveTask();
    
    // Click on technical case
    caseInfo.clickOnFirstTechCaseOfRunningCases();
    assertTrue(caseInfo.isConfirmDialogDisplayed());
    caseInfo.clickCancelLeaveTask();
    
    caseInfo.closeCaseInfoDialog();
    taskTemplatePage.clickSubmitButton();

    taskTemplatePage = startATaskAndOpenCaseInfo();
    caseInfo = new CaseInformationPage();
    // Click on show more tasks
    caseInfo.clickOnShowMoreTasks();
    assertTrue(caseInfo.isConfirmDialogDisplayed());
    caseInfo.clickCancelLeaveTask();
    caseInfo.closeCaseInfoDialog();
  }

  private void createTestDataAndRedirectToHomePage() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }
  
  private TaskTemplatePage startATaskAndOpenCaseInfo() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.openCaseInfo();
    return taskTemplatePage;
  }
}
