package portal.guitest.document.screenshot;

import static portal.guitest.common.FileHelper.getAbsolutePathToTestFile;
import static portal.guitest.common.Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import ch.ivy.addon.portalkit.util.ConfigurationJsonUtil;
import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.Variable;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class PortalTaskScreenshotTest extends ScreenshotTest {

  private static final int SCREENSHOT_WIDTH = 1500;
  private HomePage homePage;
  
  @Override
  public void setup() {
    super.setup();
    updatePortalSetting(TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingTasksUrl);
    homePage = new HomePage();
  }
  
  @Test
  public void screenshotTaskList() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    TaskWidgetPage taskWidgetPage = homePage.openTaskList();
    taskWidgetPage.closeMainMenu();
    taskWidgetPage.waitForLeftMenuActive();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_WIDGET_FOLDER + "task-key-information");
    WebElement saveTaskFilterDialog = taskWidgetPage.getSaveFilterDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(saveTaskFilterDialog, ScreenshotUtil.TASK_WIDGET_FOLDER + "how-to-create-task-filter",new ScreenshotMargin(100, 200));
  }
  
  @Test
  public void screenshotCustomTaskList() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 900));
    login(TestAccount.ADMIN_USER);
    refreshPage();
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
    ScreenshotUtil.captureHalfRightPageScreenShot(ScreenshotUtil.TASK_WIDGET_CUSTOMIZATION_FOLDER + "task-columns-configuration");
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
  public void screenshotBasicTaskDetails() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1000));
    login(TestAccount.ADMIN_USER);
    refreshPage();
    TaskWidgetPage taskWidget = homePage.openTaskList();
    TaskDetailsPage taskDetails = taskWidget.openTaskDetails(0);
    taskDetails.waitUtilsTaskDetailsDisplayed();
    taskDetails.waitForElementDisplayed(By.id("task-details-information-panel"), true);

    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_FOLDER + "detailed-task-information");
    executeDecorateJs("highlightTaskDetailComponent()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-standard");
  }
 
  @Test
  public void screenshotActionButtonsOnTaskDetailsWithJsonFile() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1440));
    login(TestAccount.ADMIN_USER);
    refreshPage();
    TaskWidgetPage taskWidget = homePage.openTaskList();
    TaskDetailsPage taskDetails = taskWidget.openTaskDetails(0);
    taskDetails.waitUtilsTaskDetailsDisplayed();

    executeDecorateJs("highlightSwitchToEditMode()");
    WebElement switchToEditMode = taskDetails.getSwitchToEditModeButtonElement();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(switchToEditMode,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-switch-to-edit-mode", new ScreenshotMargin(100, 200));

    taskDetails.clickOnSwitchToEditModeButton();
    taskDetails.waitForSwitchToViewModeButtonDisplayed();
    taskDetails.drapAndDropWidgets("note", "document");
    executeDecorateJs("highlightSwitchToViewMode()");
    WebElement switchToViewMode = taskDetails.getSwitchToViewModeButtonElement();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(switchToViewMode,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-switch-to-view-mode", new ScreenshotMargin(100, 200));

    taskDetails.clickOnSwitchToViewModeButton();
    taskDetails.clickOnSwitchToEditModeButton();
    executeDecorateJs("highlightResetToDefault()");
    WebElement resetButton = taskDetails.getResetButtonElement();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(resetButton,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-reset-to-default", new ScreenshotMargin(100, 200));
    taskDetails.clickOnResetToDefaultButton();
  }

  @Test
  public void screenshotTaskDetails() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(2560, 1440));
    login(TestAccount.ADMIN_USER);
    refreshPage();
    TaskWidgetPage taskWidget = homePage.openTaskList();
    TaskDetailsPage taskDetails = taskWidget.openTaskDetails(0);
    taskDetails.waitUtilsTaskDetailsDisplayed();

    WebElement generalInfo = taskDetails.getTaskGeneralInformation();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(generalInfo,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "detailed-task-information-data-description", new ScreenshotMargin(10));
    
    ScreenshotUtil.resizeBrowser(new Dimension(1440, 1000));
    taskDetails.openAddNoteDialog();
    WebElement addNoteDialog = taskDetails.getAddNoteDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(addNoteDialog,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-add-note", new ScreenshotMargin(10));
    taskDetails.addNoteToTaskWithContent("Add a note for this task");

    taskDetails.openAddAttachmentDialog();
    WebElement addDocument = taskDetails.getAddAttachmentDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(addDocument,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-upload-document", new ScreenshotMargin(10));
    taskDetails.uploadDocument(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));

    ScreenshotUtil.resizeBrowser(new Dimension(2560, 1440));
    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    WebElement taskHistories = taskDetails.getTaskHistories();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskHistories,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "detailed-task-information-note", new ScreenshotMargin(10));

    WebElement taskDocument = taskDetails.getTaskAttachment();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskDocument,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "detailed-task-information-documents", new ScreenshotMargin(10));

    taskDetails.clickOnDeleteDocumentIcon(0);
    WebElement deleteDocumentDialog = taskDetails.getDeleteDocumentConfirmDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(deleteDocumentDialog,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-delete-document", new ScreenshotMargin(100, 150));

    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    taskDetails.openActionPanel();
    executeDecorateJs("highlightShowWorkflowEvents()");
    ScreenshotUtil.captureHalfCenterTopPageScreenShot(ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-show-workflow-event");

    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    ScreenshotUtil.resizeBrowser(new Dimension(1440, 1000));
    taskDetails.openWorkflowEventDialog();
    WebElement workflowEventTable = taskDetails.getWorkflowEventsTable();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(workflowEventTable,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "workflow-events-table", new ScreenshotMargin(100, 50));
  }
  
  @Test
  public void screenshotShowMoreTaskHistories() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(2560, 1000));
    login(TestAccount.ADMIN_USER);
    refreshPage();
    TaskWidgetPage taskWidget = homePage.openTaskList();
    TaskDetailsPage taskDetails = taskWidget.openTaskDetails(0);
    taskDetails.waitUtilsTaskDetailsDisplayed();
    taskDetails.openAddNoteDialog();
    taskDetails.addNoteToTaskWithContent("Add a note for this task");
    taskDetails.openAddAttachmentDialog();
    taskDetails.uploadDocument(getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();

    executeDecorateJs("highlightShowMoreTaskHistories()");
    WebElement showMoreTaskHistories = taskDetails.getTaskHistories();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(showMoreTaskHistories, ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-show-note-details", new ScreenshotMargin(10));
    taskDetails.clickOnShowMoreHistories();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> homePage.countBrowserTab() > 1);
    homePage.switchLastBrowserTab();
    WaitHelper.assertTrueWithWait(() -> ScreenshotUtil.isDOMStatusComplete());
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-export-note-details", new Dimension(SCREENSHOT_WIDTH, 1000));
  }

  @Test
  public void screenshotExportToExcel() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    TaskWidgetPage taskWidgetPage = homePage.openTaskList();
    taskWidgetPage.closeMainMenu();
    taskWidgetPage.waitForLeftMenuActive();
    executeDecorateJs("highlightTaskExportToExcelButton()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_WIDGET_FOLDER + "export-to-excel-button");
  }

  @Test
  public void screenshotCustomTaskDetails() throws IOException {
    redirectToRelativeLink("portal-developer-examples/1791D27754935B10/SaleManagment.ivp");
    TaskDetailsPage taskDetails = setupCustomWidgetByJSONFile("task-details-custom-panel.json");
    executeDecorateJs("addStepToCustomWidgetTopTaskDetails()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-customized-top");
    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    executeDecorateJs("scrollToBottomOfLayoutContent()");
    executeDecorateJs("addStepTCustomWidgetTopTaskDetails()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-customized-bottom");

    setupCustomWidgetByJSONFile("task-details-custom-iframe.json");
    executeDecorateJs("highlightIFrameWidgetTaskDetails()");
    taskDetails.waitForIFrameURLWidgetLoad();
    taskDetails.switchToDefaultContent();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-customized-iframe-url");

    setupCustomWidgetByJSONFile("task-details-custom-process-iframe.json");
    executeDecorateJs("highlightIFrameWidgetTaskDetails()");
    taskDetails.waitForIFrameWidgetLoad();
    taskDetails.switchToDefaultContent();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-customized-iframe-process");
  }

  private TaskDetailsPage setupCustomWidgetByJSONFile(String configFile) throws IOException {
    ConfigurationJsonUtil.updateJSONSetting(configFile, Variable.TASK_DETAIL);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    homePage = new HomePage();
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1200));
    TaskWidgetPage taskWidgetPage = homePage.openTaskList();
    TaskDetailsPage taskDetails = taskWidgetPage.openTaskDetails(0);
    taskDetails.waitUtilsTaskDetailsDisplayed();
    taskWidgetPage.closeMainMenu();
    return taskDetails;
  }
}
