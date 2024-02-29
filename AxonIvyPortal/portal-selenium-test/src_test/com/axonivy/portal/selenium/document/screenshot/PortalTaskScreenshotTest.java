package com.axonivy.portal.selenium.document.screenshot;


import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.NoteHistoryPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.axonivy.portal.selenium.util.ConfigurationJsonUtils;
import com.codeborne.selenide.SelenideElement;


@IvyWebTest
public class PortalTaskScreenshotTest extends ScreenshotBaseTest {

  private static final int SCREENSHOT_WIDTH = 1500;
  private static final String CREATED= "Created";
  private static final String SUSPENDED = "Suspended";
  private static final String RESERVED = "Reserved";
  private static final String DONE = "Done";
  MainMenuPage mainMenuPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void screenshotTaskList() throws IOException {
    mainMenuPage = new MainMenuPage();
    showNewDashboard();
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    SelenideElement saveTaskFilterDialog = taskWidgetPage.getSaveFilterDialog();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(saveTaskFilterDialog,
        ScreenshotUtils.TASK_WIDGET_FOLDER + "how-to-create-task-filter", new ScreenshotMargin(100, 200));
    taskWidgetPage.closeSaveFilterDialog();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_WIDGET_FOLDER + "task-key-information");
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
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1000));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_FOLDER + "detailed-task-information");
    ScreenshotUtils.executeDecorateJs("highlightTaskDetailComponent()");
    detailsPage.waitUtilsTaskDetailsDisplayed();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-standard");
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

    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1440));
    ScreenshotUtils.executeDecorateJs("highlightSharePageButton()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskDetails.getSharePageButtonElement(),
        ScreenshotUtils.TASK_DETAIL_FOLDER + "share-page-button", new ScreenshotMargin(100, 200));

    taskDetails = new TaskDetailsPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    refreshPage();
    ScreenshotUtils.executeDecorateJs("highlightSwitchToEditMode()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskDetails.getSwitchToEditModeButtonElement(),
        ScreenshotUtils.TASK_DETAIL_FOLDER + "how-to-switch-to-edit-mode", new ScreenshotMargin(100, 200));

    taskDetails.clickOnSwitchToEditModeButton();
    taskDetails.waitForSwitchToViewModeButtonDisplayed();
    taskDetails.drapAndDropWidgets("note", "document");
    ScreenshotUtils.executeDecorateJs("highlightSwitchToViewMode()");
    WebElement switchToViewMode = taskDetails.getSwitchToViewModeButtonElement();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(switchToViewMode,
        ScreenshotUtils.TASK_DETAIL_FOLDER + "how-to-switch-to-view-mode", new ScreenshotMargin(100, 200));

    taskDetails.clickOnSwitchToViewModeButton();
    taskDetails.clickOnSwitchToEditModeButton();
    WebElement resetButton = taskDetails.getResetButtonElement();
    ScreenshotUtils.executeDecorateJs("highlightResetToDefault()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(resetButton,
        ScreenshotUtils.TASK_DETAIL_FOLDER + "how-to-reset-to-default", new ScreenshotMargin(100, 200));
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

    ScreenshotUtils.resizeBrowser(new Dimension(2560, 1440));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskDetails.getTaskGeneralInformation(),
        ScreenshotUtils.TASK_DETAIL_FOLDER + "detailed-task-information-data-description", new ScreenshotMargin(10));

    ScreenshotUtils.resizeBrowser(new Dimension(1440, 1000));
    taskDetails.openAddNoteDialog();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskDetails.getAddNoteDialog(),
        ScreenshotUtils.TASK_DETAIL_FOLDER + "how-to-add-note", new ScreenshotMargin(10));
    taskDetails.addNoteToTaskWithContent("Add a note for this task");

    taskDetails.openAddAttachmentDialog();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskDetails.getAddAttachmentDialog(),
        ScreenshotUtils.TASK_DETAIL_FOLDER + "how-to-upload-document", new ScreenshotMargin(10));
    taskDetails.uploadDocument(FileHelper.getAbsolutePathToTestFile("test-no-files-no-js.pdf"));

    ScreenshotUtils.resizeBrowser(new Dimension(2560, 1440));
    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskDetails.getTaskHistories(),
        ScreenshotUtils.TASK_DETAIL_FOLDER + "detailed-task-information-note", new ScreenshotMargin(10));

    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskDetails.getTaskAttachment(),
        ScreenshotUtils.TASK_DETAIL_FOLDER + "detailed-task-information-documents", new ScreenshotMargin(10));

    WebElement deleteDocumentDialog = taskDetails.getDeleteDocumentConfirmDialog();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(deleteDocumentDialog,
        ScreenshotUtils.TASK_DETAIL_FOLDER + "how-to-delete-document", new ScreenshotMargin(100, 150));

    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    taskDetails.openActionPanel();
    ScreenshotUtils.executeDecorateJs("highlightShowWorkflowEvents()");
    ScreenshotUtils
        .captureHalfCenterTopPageScreenShot(ScreenshotUtils.TASK_DETAIL_FOLDER + "how-to-show-workflow-event");

    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    ScreenshotUtils.resizeBrowser(new Dimension(1440, 1000));
    taskDetails.openWorkflowEventDialog();
    WebElement workflowEventTable = taskDetails.getWorkflowEventsTable();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(workflowEventTable,
        ScreenshotUtils.TASK_DETAIL_FOLDER + "workflow-events-table", new ScreenshotMargin(100, 50));
  }

  @Test
  public void screenshotShowMoreTaskHistories() throws IOException {
    login(TestAccount.ADMIN_USER);
    mainMenuPage = new MainMenuPage();
    ScreenshotUtils.resizeBrowser(new Dimension(2560, 1000));
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

    ScreenshotUtils.executeDecorateJs("highlightShowMoreTaskHistories()");
    WebElement showMoreTaskHistories = taskDetails.getTaskHistories();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(showMoreTaskHistories,
        ScreenshotUtils.TASK_DETAIL_FOLDER + "how-to-show-note-details", new ScreenshotMargin(10));
    taskDetails.clickOnShowMoreHistories();
    taskDetails.waitForNewTabOpen();
    taskDetails.switchLastBrowserTab();
    NoteHistoryPage noteHistoryPage = new NoteHistoryPage();
    noteHistoryPage.waitDocumentReady();
    ScreenshotUtils.captureHalfTopPageScreenShot(ScreenshotUtils.TASK_DETAIL_FOLDER + "how-to-export-note-details",
        new Dimension(SCREENSHOT_WIDTH, 1000));
  }

  @Test
  public void screenshotExportToExcel() throws IOException {
    mainMenuPage = new MainMenuPage();
    showNewDashboard();
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.waitDocumentReady();
    ScreenshotUtils.executeDecorateJs("highlightTaskExportToExcelButton()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_WIDGET_FOLDER + "export-to-excel-button");
  }

  @Test
  public void screenshotCustomTaskDetails() throws IOException {
    redirectToRelativeLink("portal-developer-examples/1791D27754935B10/SaleManagement.ivp");
    TaskDetailsPage taskDetails = setupCustomWidgetByJSONFile("task-details-custom-panel.json");
    ScreenshotUtils.executeDecorateJs("addStepToCustomWidgetTopTaskDetails()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-customized-top");
    refreshPage();
    taskDetails.waitUtilsTaskDetailsDisplayed();
    ScreenshotUtils.executeDecorateJs("scrollToBottomOfLayoutContent()");
    ScreenshotUtils.executeDecorateJs("addStepTCustomWidgetTopTaskDetails()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-customized-bottom");


    setupCustomWidgetByJSONFile("task-details-custom-iframe.json");
    ScreenshotUtils.executeDecorateJs("highlightIFrameWidgetTaskDetails()");
    taskDetails.waitForIFrameURLWidgetLoad();
    ScreenshotUtils
        .capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-customized-iframe-url");

    setupCustomWidgetByJSONFile("task-details-custom-process-iframe.json");
    ScreenshotUtils.executeDecorateJs("highlightIFrameWidgetTaskDetails()");
    taskDetails.waitForIFrameWidgetLoad();
    ScreenshotUtils
        .capturePageScreenshot(ScreenshotUtils.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-customized-iframe-process");
  }

  private TaskDetailsPage setupCustomWidgetByJSONFile(String configFile) throws IOException {
    ConfigurationJsonUtils.updateJSONSetting(configFile, Variable.TASK_DETAIL);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(BaseTest.PORTAL_EXAMPLES_HOME_PAGE_URL);
    mainMenuPage = new MainMenuPage();
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 1200));
    TaskWidgetPage taskWidgetPage = mainMenuPage.openDeveloperExamplesTaskList();
    TaskDetailsPage taskDetails = taskWidgetPage.openTaskDetail(0);
    taskDetails.waitUtilsTaskDetailsDisplayed();
    taskWidgetPage.closeMainMenu();
    return taskDetails;
  }
  
  @Test
  public void screenshotTaskFilter() throws IOException {
    updatePortalSetting(Variable.SHOW_LEGACY_UI.getKey(), "true");
    ScreenshotUtils.resizeBrowser(new Dimension(1440, 1280));
    mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidget = mainMenuPage.openTaskList();
    taskWidget.clickOnTaskStatesAndApply(Arrays.asList(CREATED, DONE, RESERVED, SUSPENDED));
    taskWidget.openStateFilter();
    ScreenshotUtils.executeDecorateJs("highlightTaskStateFilter()");
    ScreenshotUtils.captureHalfCenterTopPageScreenShot(ScreenshotUtils.TASK_WIDGET_CUSTOMIZATION_FOLDER + "task-filter");
  }

  @Test
  public void screenshotCustomTaskList() throws IOException {
    updatePortalSetting(Variable.SHOW_LEGACY_UI.getKey(), "true");
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 900));
    mainMenuPage = new MainMenuPage();
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    refreshPage();

    mainMenuPage.openTaskList();
    ScreenshotUtils.executeDecorateJs("highlightCustomTaskList()");
    ScreenshotUtils.captureHalfTopPageScreenShot(ScreenshotUtils.TASK_WIDGET_CUSTOMIZATION_FOLDER + "task-list");
    
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    TaskWidgetPage taskWidgetPage = mainMenuPage.openDeveloperExamplesTaskList();
    taskWidgetPage.clickColumnsButton();
    ScreenshotUtils.executeDecorateJs("highlightCustomColumnsConfigOnTaskList()");
    ScreenshotUtils.captureHalfRightPageScreenShot(ScreenshotUtils.TASK_WIDGET_CUSTOMIZATION_FOLDER + "task-columns-configuration");
  }

}
