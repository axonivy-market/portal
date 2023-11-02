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
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessInformationPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;

@IvyWebTest
public class PortalProcessesScreenshotTest extends ScreenshotBaseTest{

  private static final int SCREENSHOT_WIDTH = 1440;
  private static final int SCREENSHOT_HD_WIDTH = 1920;
  private static final int SCREENSHOT_MOBILE_HEIGHT = 500;
  private static final int SCREENSHOT_HEIGHT = 1080;

  private ProcessWidgetPage processWidget;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    showNewDashboard();
  }

  @Test
  public void screenshotProcessNavigate() throws IOException {
    NewDashboardPage homepage = new NewDashboardPage();
    homepage.waitForCaseWidgetLoaded();
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.expandMainMenu();

    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_HD_WIDTH, 700));
    ScreenshotUtil.executeDecorateJs("highlightProcessNavigation()");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "navigate-to-full-process-list");

    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_MOBILE_HEIGHT));
    processWidget = mainMenuPage.openProcessList();
    processWidget.waitUtilProcessWidgetDisplayed();
    ScreenshotUtil.executeDecorateJs("highlightAddExternalLink()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "how-to-add-a-new-external-link", new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_MOBILE_HEIGHT));
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();

    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT));
    WebElement externalLinkDialog = processWidget.openExternalLinkDialog();
    ScreenshotUtil.executeDecorateJs("highlightAddExternalDialogItem()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(externalLinkDialog, ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "add-external-link-dialog", new ScreenshotMargin(100, 150));
  }

  @Test
  public void screenshotPortalFullProcessesList() throws IOException {
    redirectToRelativeLink(createSampleProcesses);
    login(TestAccount.ADMIN_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    processWidget = mainMenuPage.openProcessList();
    processWidget.navigateToProcessIndex("A");
    ScreenshotUtil.executeDecorateJs("highlightProcessMoreMenuButton()");

    processWidget.clickMoreButtonOfFirstImageProcess();
    ScreenshotUtil.executeDecorateJs("highlightEditProcessLink()");

    ScreenshotUtil.captureElementWithMarginOptionScreenshot(processWidget.getProcessEditMenu(0), ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "edit-process-menu-item", new ScreenshotMargin(150, 200));
    processWidget.clickOnProcessEditMenu(0);
    ScreenshotUtil.executeDecorateJs("highlightEditProcessDialog()");
    ScreenshotUtil.executeDecorateJs("highlightEditProcessIcon()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(processWidget.getEditProcessDialog(), ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "edit-process-dialog", new ScreenshotMargin(10));
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 800));
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();
    ScreenshotUtil.executeDecorateJs("highlightProcessItems()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "portal-full-process-list-page");
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "portal-process-image-view-page");
    processWidget.selectGridMode();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "portal-process-grid-view-page");
    processWidget.selectCompactMode();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "portal-process-list-view-page");
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();
    screenshotProcessImageCustomization();
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();
    screenshotMoreInfoOnProcessList();
  }

  private void screenshotMoreInfoOnProcessList() throws IOException {
    String processName = "Process With Process Steps";
    processWidget.selectGridMode();

    processWidget.getFilterTextfield().sendKeys(processName);
    processWidget.waitUntilProcessDisplayed(processName);

    ScreenshotUtil.executeDecorateJs("highlightProcessMoreInformationLink()"); 
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.PROCESSES_INFORMATION_WIDGET_FOLDER + "more-information-link");

    processWidget.clickMoreInformationLink(processName);
    ProcessInformationPage processInformationPage = new ProcessInformationPage();
    processInformationPage.waitPageLoaded();
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 800));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.PROCESSES_INFORMATION_WIDGET_FOLDER + "process-information");
  }

  private void screenshotProcessImageCustomization() throws IOException {
    String processName = "Default Process Image selection example";
    ScreenshotUtil.resizeBrowser(new Dimension(1920, 800));

    processWidget.getFilterTextfield().sendKeys(processName);
    processWidget.waitUntilProcessDisplayed(processName);
    processWidget.selectImageMode();

    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.PROCESSES_PROCESS_IMAGE_FOLDER + "image-process-list");
  }
}
