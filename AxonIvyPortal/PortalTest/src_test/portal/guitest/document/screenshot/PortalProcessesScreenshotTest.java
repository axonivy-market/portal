package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProcessInformationPage;
import portal.guitest.page.ProcessWidgetPage;

public class PortalProcessesScreenshotTest extends ScreenshotTest {

  private HomePage homePage;
  ProcessWidgetPage processWidget;
  private static final int SCREENSHOT_WIDTH = 1440;
  private static final int SCREENSHOT_HD_WIDTH = 1920;
  private static final int SCREENSHOT_MOBILE_HEIGHT = 500;
  private static final int SCREENSHOT_HEIGHT = 1080;

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createUserFavoriteProcess);
    homePage = new HomePage();
  }

  @Test
  public void screenshotProcessNavigate() throws IOException {
    homePage.waitForStatisticRendered();
    homePage.openMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_HD_WIDTH, 700));
    executeDecorateJs("highlightProcessNavigation()");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "navigate-to-full-process-list");
  }

  @Test
  public void screenshotAddNewExternalLink() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_MOBILE_HEIGHT));
    login(TestAccount.ADMIN_USER);
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    executeDecorateJs("highlightAddExternalLink()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "how-to-add-a-new-external-link", new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_MOBILE_HEIGHT));
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();

    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT));
    processWidget.openNewExternalLinkDialog();
    executeDecorateJs("highlightAddExternalDialogItem()");
    WebElement externalDialog = processWidget.getAddExternalLinkDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(externalDialog, ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "add-external-link-dialog", new ScreenshotMargin(100, 150));
  }
  
  @Test
  public void screenshotPortalFullProcessesList() throws IOException {
    login(TestAccount.ADMIN_USER);
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    ScreenshotUtil.resizeBrowser(new Dimension(1000, 900));
    processWidget.navigateToProcessIndex("A");
    executeDecorateJs("highlightEditProcessLink()");
    processWidget.clickOnProcessEditLink(0);
    executeDecorateJs("highlightEditProcessIcon()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(processWidget.getEditProcessDialog(), ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "edit-process-dialog", new ScreenshotMargin(150, 200));
    ScreenshotUtil.resizeBrowser(new Dimension(1366, 800));
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();
    executeDecorateJs("highlightProcessItems()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "portal-full-process-list-page");
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "portal-process-grid-view-page");
    processWidget.clickOnSwitchButton();
    Sleeper.sleep(250); /* Wait for css styling */
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "portal-process-list-view-page");
  }
  
  @Test
  public void screenshotHowtoAddProcessFavorite() throws IOException {
    processWidget = homePage.getProcessWidget();
    ScreenshotUtil.resizeBrowser(new Dimension(1366, SCREENSHOT_MOBILE_HEIGHT));
    processWidget.openNewProcessDialog();
    executeDecorateJs("highlightAddFavoriteProcess()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "how-to-add-process-favorite");
  }
  
  @Test
  public void screenshotHowtoEditUserProcess() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1920, 800));
    processWidget = homePage.getProcessWidget();
    executeDecorateJs("highlightEditSwitchProcessButton()");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "how-to-edit-process-favorites");
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();
    processWidget.clickEditSwitchLink();
    executeDecorateJs("highlightEditStepUserProcess(true)");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "how-to-delete-process-favorites");
    
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();
    processWidget.clickEditSwitchLink();
    executeDecorateJs("highlightEditStepUserProcess()");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "how-to-order-process-favorites-individually");
    
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();
    executeDecorateJs("highlightSortUserProcess()");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "how-to-order-process-favorites-by-name");
  }

  @Test
  public void screenshotMoreInfoOnProcessList() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1920, 800));
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    processWidget.enterSearchKeyword("Process With Process Steps");
    executeDecorateJs("highlightProcessMoreInformationLink()");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.PROCESSES_INFORMATION_WIDGET_FOLDER + "more-information-link");
  }

  @Test
  public void screenshotProcessInformation() throws IOException {
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    processWidget.clickMoreInformationLink("Process With Process Steps");
    ProcessInformationPage processInformationPage = new ProcessInformationPage();
    processInformationPage.getPageTitle();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.PROCESSES_INFORMATION_WIDGET_FOLDER + "process-information");
  }
}
