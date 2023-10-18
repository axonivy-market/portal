package com.axonivy.portal.selenium.document.screenshot;


import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.NoteHistoryPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.util.ConfigurationJsonUtil;
import com.codeborne.selenide.SelenideElement;


@IvyWebTest
public class PortalTaskScreenshotTest extends ScreenshotBaseTest{
  
  private static final int SCREENSHOT_WIDTH = 1500;
  MainMenuPage mainMenuPage;
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.SHOW_LEGACY_UI.getKey(), "false");
    updatePortalSetting(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingTasksUrl);
  }
  
  @Test
  public void screenshotTaskList() throws IOException {
    mainMenuPage = new MainMenuPage();
    showNewDashboard();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    SelenideElement saveTaskFilterDialog = taskWidgetPage.getSaveFilterDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(saveTaskFilterDialog, ScreenshotUtil.TASK_WIDGET_FOLDER + "how-to-create-task-filter",new ScreenshotMargin(100, 200));
    taskWidgetPage.closeSaveFilterDialog();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_WIDGET_FOLDER + "task-key-information");
  }

  @Test
  public void screenshotBasicTaskDetails() throws IOException {
    login(TestAccount.ADMIN_USER);
    mainMenuPage = new MainMenuPage();
    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    TaskWidgetPage taskWidget = mainMenuPage.openTaskList();
    WaitHelper.waitForNavigation(() -> taskWidget.openTaskDetail(0));
    TaskDetailsPage detailsPage = new TaskDetailsPage();
    detailsPage.waitUtilsTaskDetailsDisplayed();
    mainMenuPage.expandMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1000));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_FOLDER + "detailed-task-information");
    ScreenshotUtil.executeDecorateJs("highlightTaskDetailComponent()");
    detailsPage.waitUtilsTaskDetailsDisplayed();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-standard");
  }


  @Test
  public void screenshotActionButtonsOnTaskDetailsWithJsonFile() throws IOException {
    login(TestAccount.ADMIN_USER);
    mainMenuPage = new MainMenuPage();
    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    TaskWidgetPage taskWidget = mainMenuPage.openTaskList();
    WaitHelper.waitForNavigation(() -> taskWidget.openTaskDetail(0));
    TaskDetailsPage taskDetails = new TaskDetailsPage();

    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1440));
    ScreenshotUtil.executeDecorateJs("highlightSharePageButton()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskDetails.getSharePageButtonElement(),
        ScreenshotUtil.TASK_DETAIL_FOLDER + "share-page-button", new ScreenshotMargin(100, 200));

    taskDetails = new TaskDetailsPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    ScreenshotUtil.executeDecorateJs("removeHighlightSharePageButton()");
    ScreenshotUtil.executeDecorateJs("highlightSwitchToEditMode()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskDetails.getSwitchToEditModeButtonElement(),
        ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-switch-to-edit-mode", new ScreenshotMargin(100, 200));

    taskDetails.clickOnSwitchToEditModeButton();
    taskDetails.waitForSwitchToViewModeButtonDisplayed();
    taskDetails.drapAndDropWidgets("note", "document");
    ScreenshotUtil.executeDecorateJs("highlightSwitchToViewMode()");
    WebElement switchToViewMode = taskDetails.getSwitchToViewModeButtonElement();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(switchToViewMode,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-switch-to-view-mode", new ScreenshotMargin(100, 200));

    taskDetails.clickOnSwitchToViewModeButton();
    taskDetails.clickOnSwitchToEditModeButton();
    WebElement resetButton = taskDetails.getResetButtonElement();
    ScreenshotUtil.executeDecorateJs("highlightResetToDefault()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(resetButton,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-reset-to-default", new ScreenshotMargin(100, 200));
    taskDetails.clickOnResetToDefaultButton();
  }

  @Test
  public void screenshotTaskDetails() throws IOException {
    login(TestAccount.ADMIN_USER);
    mainMenuPage = new MainMenuPage();
    showNewDashboard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();
    TaskWidgetPage taskWidget = mainMenuPage.openTaskList();
    TaskDetailsPage taskDetails = taskWidget.openTaskDetail(0);
    taskDetails.waitUtilsTaskDetailsDisplayed();

    ScreenshotUtil.resizeBrowser(new Dimension(2560, 1440));
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskDetails.getTaskGeneralInformation(),
        ScreenshotUtil.TASK_DETAIL_FOLDER + "detailed-task-information-data-description", new ScreenshotMargin(10));
    
    ScreenshotUtil.resizeBrowser(new Dimension(1440, 1000));
    taskDetails.openAddNoteDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskDetails.getAddNoteDialog(),
        ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-add-note", new ScreenshotMargin(10));
    taskDetails.addNoteToTaskWithContent("Add a note for this task");

    taskDetails.openAddAttachmentDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskDetails.getAddAttachmentDialog(),
        ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-upload-document", new ScreenshotMargin(10));
    taskDetails.uploadDocument(FileHelper.getAbsolutePathToTestFile("test-no-files-no-js.pdf"));

    ScreenshotUtil.resizeBrowser(new Dimension(2560, 1440));
    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskDetails.getTaskHistories(),
        ScreenshotUtil.TASK_DETAIL_FOLDER + "detailed-task-information-note", new ScreenshotMargin(10));

    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskDetails.getTaskAttachment(),
        ScreenshotUtil.TASK_DETAIL_FOLDER + "detailed-task-information-documents", new ScreenshotMargin(10));

    WebElement deleteDocumentDialog = taskDetails.getDeleteDocumentConfirmDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(deleteDocumentDialog,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-delete-document", new ScreenshotMargin(100, 150));

    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    taskDetails.openActionPanel();
    ScreenshotUtil.executeDecorateJs("highlightShowWorkflowEvents()");
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
    login(TestAccount.ADMIN_USER);
    mainMenuPage = new MainMenuPage();
    ScreenshotUtil.resizeBrowser(new Dimension(2560, 1000));
    showNewDashboard();
    TaskWidgetPage taskWidget = mainMenuPage.openTaskList();
    TaskDetailsPage taskDetails = taskWidget.openTaskDetail(0);
    taskDetails.waitUtilsTaskDetailsDisplayed();
    taskDetails.openAddNoteDialog();
    taskDetails.addNoteToTaskWithContent("Add a note for this task");
    taskDetails.openAddAttachmentDialog();
    taskDetails.uploadDocument(FileHelper.getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();

    ScreenshotUtil.executeDecorateJs("highlightShowMoreTaskHistories()");
    WebElement showMoreTaskHistories = taskDetails.getTaskHistories();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(showMoreTaskHistories, ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-show-note-details", new ScreenshotMargin(10));
    taskDetails.clickOnShowMoreHistories();
    taskDetails.waitForNewTabOpen();
    taskDetails.switchLastBrowserTab();
    NoteHistoryPage noteHistoryPage = new NoteHistoryPage();
    noteHistoryPage.waitDocumentReady();
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-export-note-details", new Dimension(SCREENSHOT_WIDTH, 1000));
  }

  @Test
  public void screenshotExportToExcel() throws IOException {
    mainMenuPage = new MainMenuPage();
    showNewDashboard();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.waitDocumentReady();
    ScreenshotUtil.executeDecorateJs("highlightTaskExportToExcelButton()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_WIDGET_FOLDER + "export-to-excel-button");
  }
  
  @Test
  public void screenshotCustomTaskDetails() throws IOException {
    redirectToRelativeLink("portal-developer-examples/1791D27754935B10/SaleManagment.ivp");
    showNewDashboard();
    TaskDetailsPage taskDetails = setupCustomWidgetByJSONFile("task-details-custom-panel.json");
    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();

    setupCustomWidgetByJSONFile("task-details-custom-iframe.json");
    ScreenshotUtil.executeDecorateJs("highlightIFrameWidgetTaskDetails()");
    taskDetails.waitForIFrameURLWidgetLoad();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-customized-iframe-url");

    setupCustomWidgetByJSONFile("task-details-custom-process-iframe.json");
    ScreenshotUtil.executeDecorateJs("highlightIFrameWidgetTaskDetails()");
    taskDetails.waitForIFrameWidgetLoad();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-customized-iframe-process");
  }

  private TaskDetailsPage setupCustomWidgetByJSONFile(String configFile) throws IOException {
    ConfigurationJsonUtil.updateJSONSetting(configFile, Variable.TASK_DETAIL);
    login(TestAccount.ADMIN_USER);
    mainMenuPage = new MainMenuPage();
    redirectToRelativeLink(PORTAL_EXAMPLES_HOME_PAGE_URL);
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 1200));
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    TaskDetailsPage taskDetails = taskWidgetPage.openTaskDetail(0);
    taskDetails.waitUtilsTaskDetailsDisplayed();
    return taskDetails;
  }
}
