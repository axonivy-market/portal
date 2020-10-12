package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProcessWidgetPage;

public class PortalProcessesScreenshotTest extends ScreenshotTest {

  private HomePage homePage;
  ProcessWidgetPage processWidget;
  private static final int SCREENSHOT_WIDTH = 1500;
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
    homePage.waitForStatisticRendered(HomePage.PORTAL_HOME_PAGE_URL);
    homePage.openMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_HD_WIDTH, 700));
    executeDecorateJs("highlightProcessNavigation()");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "navigate-to-full-process-list");
  }

  @Test
  public void screenshotAddNewExternalLink() throws IOException {
    login(TestAccount.ADMIN_USER);
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_MOBILE_HEIGHT));
    executeDecorateJs("highlightAddExternalLink()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "how-to-add-a-new-external-link", new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_MOBILE_HEIGHT));
    
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT));
    refreshPage();
    processWidget.waitUtilProcessWidgetDisplayed();
    processWidget.openNewExternalLinkDialog();
    executeDecorateJs("highlightAddExternalDialogItem()");
    WebElement externalDialog = processWidget.getAddExternalLinkDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(externalDialog, ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "add-external-link-dialog", new ScreenshotMargin(100, 150));
  }
  
  @Test
  public void screenshotPortalFullProcessesList() throws IOException {
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT));
    processWidget.navigateToProcessIndex("A");
    executeDecorateJs("highlightProcessItems()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.PROCESSES_WIDGET_FOLDER + "portal-full-process-list-page");
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
    ScreenshotUtil.resizeBrowser(new Dimension(1920, SCREENSHOT_MOBILE_HEIGHT));
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

}
