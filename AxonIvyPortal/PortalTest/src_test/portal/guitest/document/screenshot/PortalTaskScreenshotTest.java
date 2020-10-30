package portal.guitest.document.screenshot;

import static portal.guitest.common.FileHelper.getAbsolutePathToTestFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class PortalTaskScreenshotTest extends ScreenshotTest {

  private static final int SCREENSHOT_WIDTH = 1500;
  private HomePage homePage;
  
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingTasksUrl);
    homePage = new HomePage();
  }
  
  @Test
  public void screenshotTaskList() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 600));
    TaskWidgetPage taskWidgetPage = homePage.openTaskList();
    taskWidgetPage.closeMainMenu();
    Sleeper.sleep(500); // wait for Layout.js renders left menu
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_WIDGET_FOLDER + "task-key-information");
    WebElement saveTaskFilterDialog = taskWidgetPage.getSaveFilterDialog();
    Sleeper.sleep(1000);//wait for focus animation finish to capture screenshot
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(saveTaskFilterDialog, ScreenshotUtil.TASK_WIDGET_FOLDER + "how-to-create-task-filter",new ScreenshotMargin(100, 200));
  }
  
  @Test
  public void screenshotCustomTaskList() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 900));
    TaskWidgetPage taskWidget = new TaskWidgetPage();
    taskWidget.expand();
    executeDecorateJs("highlightCustomTaskList()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.TASK_WIDGET_CUSTOMIZATION_FOLDER + "task-list");
    
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    taskWidget = new TaskWidgetPage();
    taskWidget.expand();
    taskWidget.clickColumnsButton();
    executeDecorateJs("highlightCustomColumnsConfigOnTaskList()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_WIDGET_CUSTOMIZATION_FOLDER + "task-columns-configuration");
  }
  
  @Test
  public void screenshotTaskFilter() throws IOException {
    TaskWidgetPage taskWidget = homePage.openTaskList();
    taskWidget.clickOnTaskStatesAndApply(Arrays.asList("Created", "Suspended", "Reserved", "Done"));
    taskWidget.openStateFilter();
    executeDecorateJs("highlightTaskStateFilter()");
    ScreenshotUtil.captureHalfCenterTopPageScreenShot(ScreenshotUtil.TASK_WIDGET_CUSTOMIZATION_FOLDER + "task-filter");
  }
  
  @Test
  public void screenshotTaskDetails() throws IOException {
    login(TestAccount.ADMIN_USER);
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1000));
    TaskWidgetPage taskWidget = homePage.openTaskList();
    taskWidget.closeMainMenu();
    TaskDetailsPage taskDetails = taskWidget.openTaskDetails(0);
    WebElement generalInfo = taskDetails.getTaskGeneralInformation();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(generalInfo, ScreenshotUtil.TASK_DETAIL_FOLDER + "detailed-task-information-data-description", new ScreenshotMargin(20));
    
    taskDetails.openAddNoteDialog();
    Sleeper.sleep(2000);//wait for focus animation to capture screenshot
    WebElement addNoteDialog = taskDetails.getAddNoteDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(addNoteDialog, ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-add-note", new ScreenshotMargin(50));
    taskDetails.addNoteToTaskWithContent("Add a note for this task");
    
    taskDetails.openAddAttachmentDialog();
    Sleeper.sleep(2000);//wait for focus animation to capture screenshot
    WebElement addDocument = taskDetails.getAddAttachmentDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(addDocument, ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-upload-document", new ScreenshotMargin(50));
    taskDetails.uploadDocument(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    
    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    WebElement taskHistories = taskDetails.getTaskHistories();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskHistories, ScreenshotUtil.TASK_DETAIL_FOLDER + "detailed-task-information-note", new ScreenshotMargin(20));
    
    WebElement taskDocument = taskDetails.getTaskAttachment();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskDocument, ScreenshotUtil.TASK_DETAIL_FOLDER + "detailed-task-information-documents", new ScreenshotMargin(20));
    
    taskDetails.clickOnDeleteDocumentIcon(0);
    WebElement deleteDocumentDialog = taskDetails.getDeleteDocumentConfirmDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(deleteDocumentDialog, ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-delete-document", new ScreenshotMargin(100, 150));

    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    taskDetails.openActionPanel();
    executeDecorateJs("highlightShowWorkflowEvents()");
    ScreenshotUtil.captureHalfCenterTopPageScreenShot(ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-show-workflow-event");

    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    taskDetails.openWorkflowEventDialog();
    WebElement workflowEventTable = taskDetails.getWorkflowEventsTable();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(workflowEventTable, ScreenshotUtil.TASK_DETAIL_FOLDER + "workflow-events-table", new ScreenshotMargin(100, 50));
  }
  
  @Test
  public void screenshotShowMoreTaskHistories() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1000));
    login(TestAccount.ADMIN_USER);
    TaskWidgetPage taskWidget = homePage.openTaskList();
    taskWidget.closeMainMenu();
    TaskDetailsPage taskDetails = taskWidget.openTaskDetails(0);
    taskDetails.waitUtilsTaskDetailsDisplayed();
    taskDetails.addNoteToTaskWithContent("Add a note for this task");
    taskDetails.uploadDocument(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();

    executeDecorateJs("highlightShowMoreTaskHistories()");
    WebElement showMoreTaskHistories = taskDetails.getTaskHistories();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(showMoreTaskHistories, ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-show-note-details", new ScreenshotMargin(100, 50));
    taskDetails.clickOnShowMoreHistories();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
    homePage.switchLastBrowserTab();
    Sleeper.sleep(3000);
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-export-note-details", new Dimension(SCREENSHOT_WIDTH, 1000));
  }
  
  @Test
  public void screenshotCustomTaskDetails() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1000));
    TaskWidgetPage taskWidget = homePage.openTaskList();
    TaskDetailsPage taskDetails = taskWidget.openTaskDetails(0);
    taskDetails.waitUtilsTaskDetailsDisplayed();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_FOLDER + "detailed-task-information");
    executeDecorateJs("highlightTaskDetailComponent()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-standard");
    
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    taskWidget = homePage.openTaskList();
    taskDetails = taskWidget.openTaskDetails(0);
    taskDetails.waitUtilsTaskDetailsDisplayed();
    executeDecorateJs("highlightCustomTaskDetail()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-customized-top");
    
    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    executeDecorateJs("scrollToBottomOfLayoutContent()");
    Sleeper.sleep(500);
    executeDecorateJs("highlightCustomTaskDetail()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-customized-bottom");
  }

  @Test
  public void screenshotExportToExcel() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 600));
    TaskWidgetPage taskWidgetPage = homePage.openTaskList();
    taskWidgetPage.closeMainMenu();
    Sleeper.sleep(500); // wait for Layout.js renders left menu
    executeDecorateJs("highlightTaskExportToExcelButton()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_WIDGET_FOLDER + "export-to-excel-button");
  }
}
