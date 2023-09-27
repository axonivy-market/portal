package com.axonivy.portal.selenium.document.screenshot;


import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.codeborne.selenide.SelenideElement;

@IvyWebTest
public class PortalTaskScreenshotTest extends ScreenshotBaseTest{
  
  private static final int SCREENSHOT_WIDTH = 1500;
  MainMenuPage mainMenuPage;
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingTasksUrl);
    mainMenuPage = new MainMenuPage();
  }
  
  @Test
  public void screenshotTaskList() throws IOException {
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
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1000));
    login(TestAccount.ADMIN_USER);
    refreshPage();
    mainMenuPage.expandMainMenu();
    TaskWidgetPage taskWidget = mainMenuPage.openTaskList();
    taskWidget.openTaskDetail(0);
    taskWidget.waitDocumentReady();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_FOLDER + "detailed-task-information");
    ScreenshotUtil.executeDecorateJs("highlightTaskDetailComponent()");
    taskWidget.waitDocumentReady();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.TASK_DETAIL_CUSTOMIZATION_FOLDER + "task-standard");
  }


  @Test
  public void screenshotActionButtonsOnTaskDetailsWithJsonFile() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1440));
    login(TestAccount.ADMIN_USER);
    refreshPage();
    TaskWidgetPage taskWidget = mainMenuPage.openTaskList();
    TaskDetailsPage taskDetails = taskWidget.openTaskDetail(0);
//    taskDetails.waitUtilsTaskDetailsDisplayed();

    ScreenshotUtil.executeDecorateJs("highlightSharePageButton()");
//    WebElement sharePageBtn = taskDetails.getSharePageButtonElement();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskDetails.getSharePageButtonElement(),
        ScreenshotUtil.TASK_DETAIL_FOLDER + "share-page-button", new ScreenshotMargin(100, 200));
    refreshPage();
    
    ScreenshotUtil.executeDecorateJs("highlightSwitchToEditMode()");
//    WebElement switchToEditMode = taskDetails.getSwitchToEditModeButtonElement();
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
    ScreenshotUtil.executeDecorateJs("highlightResetToDefault()");
    WebElement resetButton = taskDetails.getResetButtonElement();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(resetButton,
        ScreenshotUtil.TASK_DETAIL_FOLDER + "how-to-reset-to-default", new ScreenshotMargin(100, 200));
    taskDetails.clickOnResetToDefaultButton();
  }
}
