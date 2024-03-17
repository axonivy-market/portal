package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessInformationPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;

@IvyWebTest
public class PortalProcessesScreenshotTest extends ScreenshotBaseTest {

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

    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_HD_WIDTH, 700));
    ScreenshotUtils.executeDecorateJs("highlightProcessNavigation()");
    ScreenshotUtils
        .captureHalfLeftPageScreenShot(ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "navigate-to-full-process-list");

    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_MOBILE_HEIGHT));
    processWidget = mainMenuPage.openProcessList();
    processWidget.waitUtilProcessWidgetDisplayed();
    ScreenshotUtils.executeDecorateJs("highlightAddExternalLink()");
    ScreenshotUtils.captureHalfTopPageScreenShot(
        ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "how-to-add-a-new-external-link",
        new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_MOBILE_HEIGHT));
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();

    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT));
    WebElement externalLinkDialog = processWidget.openExternalLinkDialog();
    ScreenshotUtils.executeDecorateJs("highlightAddExternalDialogItem()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(externalLinkDialog,
        ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "add-external-link-dialog", new ScreenshotMargin(100, 150));
  }

  @Test
  public void screenshotPortalFullProcessesList() throws IOException {
    redirectToRelativeLink(createUserFavoriteProcess);
    login(TestAccount.ADMIN_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    processWidget = mainMenuPage.openProcessList();
    processWidget.navigateToProcessIndex("A");
    ScreenshotUtils.executeDecorateJs("highlightProcessMoreMenuButton()");

    processWidget.clickMoreButtonOfFirstImageProcess();
    ScreenshotUtils.executeDecorateJs("highlightEditProcessLink()");

    ScreenshotUtils.captureElementWithMarginOptionScreenshot(processWidget.getProcessEditMenu(0),
        ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "edit-process-menu-item", new ScreenshotMargin(150, 200));
    processWidget.clickOnProcessEditMenu(0);
    ScreenshotUtils.executeDecorateJs("highlightEditProcessDialog()");
    ScreenshotUtils.executeDecorateJs("highlightEditProcessIcon()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(processWidget.getEditProcessDialog(),
        ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "edit-process-dialog", new ScreenshotMargin(250));
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 800));
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();
    ScreenshotUtils.executeDecorateJs("highlightProcessItems()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "portal-full-process-list-page");
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "portal-process-image-view-page");
    processWidget.selectGridMode();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "portal-process-grid-view-page");
    processWidget.selectCompactMode();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "portal-process-list-view-page");
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

    ScreenshotUtils.executeDecorateJs("highlightProcessMoreInformationLink()");
    ScreenshotUtils
        .captureHalfLeftPageScreenShot(ScreenshotUtils.PROCESSES_INFORMATION_WIDGET_FOLDER + "more-information-link");

    processWidget.clickMoreInformationLinkImage(processName);
    ProcessInformationPage processInformationPage = new ProcessInformationPage();
    processInformationPage.waitPageLoaded();
    ScreenshotUtils.resizeBrowser(new Dimension(1500, 800));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.PROCESSES_INFORMATION_WIDGET_FOLDER + "process-information");
  }

  private void screenshotProcessImageCustomization() throws IOException {
    String processName = "Default Process Image selection example";
    ScreenshotUtils.resizeBrowser(new Dimension(1920, 800));

    processWidget.getFilterTextfield().sendKeys(processName);
    processWidget.waitUntilProcessDisplayed(processName);
    processWidget.selectImageMode();

    ScreenshotUtils
        .captureHalfLeftPageScreenShot(ScreenshotUtils.PROCESSES_PROCESS_IMAGE_FOLDER + "image-process-list");
  }
  
  @Test
  public void screenshotHowToEditUserProcess() throws IOException {
    redirectToRelativeLink(createUserFavoriteProcess);

    ScreenshotUtils.resizeBrowser(new Dimension(1920, 800));
    updatePortalSetting(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updatePortalSetting(Variable.SHOW_USER_GUIDE.getKey(), "false");
    HomePage homePage = new HomePage();

    ScreenshotUtils.executeDecorateJs("highlightEditSwitchProcessButton()");
    ScreenshotUtils.captureHalfLeftPageScreenShot(ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "how-to-edit-process-favorites");

    refreshPage();
    homePage.waitUtilProcessWidgetUserFavoriteDisplayed();
    homePage.clickEditSwitchLink();
    ScreenshotUtils.executeDecorateJs("highlightEditStepUserProcess(true)");
    ScreenshotUtils.captureHalfLeftPageScreenShot(ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "how-to-delete-process-favorites");
    
    refreshPage();
    homePage.waitUtilProcessWidgetUserFavoriteDisplayed();
    homePage.clickEditSwitchLink();
    ScreenshotUtils.executeDecorateJs("highlightEditStepUserProcess()");
    ScreenshotUtils.captureHalfLeftPageScreenShot(ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "how-to-order-process-favorites-individually");
    
    refreshPage();
    homePage.waitUtilProcessWidgetUserFavoriteDisplayed();
    ScreenshotUtils.executeDecorateJs("highlightSortUserProcess()");
    ScreenshotUtils.captureHalfLeftPageScreenShot(ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "how-to-order-process-favorites-by-name");
  }
  
  @Test
  public void screenshotHowtoAddProcessFavorite() throws IOException {
    redirectToRelativeLink(createUserFavoriteProcess);
    ScreenshotUtils.resizeBrowser(new Dimension(1920, 800));
    updatePortalSetting(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updatePortalSetting(Variable.SHOW_USER_GUIDE.getKey(), "false");
    HomePage homePage = new HomePage();

    ScreenshotUtils.resizeBrowser(new Dimension(1366, SCREENSHOT_MOBILE_HEIGHT));
    homePage.openNewProcessDialog();
    ScreenshotUtils.executeDecorateJs("highlightAddFavoriteProcess()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.PROCESSES_WIDGET_FOLDER + "how-to-add-process-favorite");
  }


}
