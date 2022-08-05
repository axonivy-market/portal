package portal.guitest.document.screenshot;

import static portal.guitest.common.Variable.ENABLE_GROUP_CHAT;
import static portal.guitest.common.Variable.SHOW_ENVIRONMENT_INFO;
import static portal.guitest.common.Variable.SHOW_LEGACY_UI;
import static portal.guitest.common.Variable.SHOW_USER_GUIDE;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.DashboardConfigurationPage;
import portal.guitest.page.DashboardWidgetConfigurationDialogPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.TaskWidgetPage;

public class DashboardScreenshotTest extends ScreenshotTest {
  
  private HomePage homePage;
  private NewDashboardPage newDashboardPage;
  private static final int SCREENSHOT_WIDTH = 1500;
  private static final int SCREENSHOT_HD_WIDTH = 1920;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    updatePortalSetting(ENABLE_GROUP_CHAT.getKey(), "true");
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createUserFavoriteProcess);
    homePage = new HomePage();
    homePage.waitForStatisticRendered();
  }
  
  @Test
  public void takeScreenshotOverlayGuide() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 800));
    updatePortalSetting(SHOW_USER_GUIDE.getKey(), "true");
    homePage = new HomePage();
    homePage.waitForLeftMenuActive();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DASHBOARD_FOLDER + "overlay-guide");
  }
  
  @Test
  public void takeScreenshotWithEnvironmentInfo() throws IOException {
    updatePortalSetting(SHOW_ENVIRONMENT_INFO.getKey(), "true");
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 500));
    executeDecorateJs("highlightServerInfo()");
    ScreenshotUtil.captureHalfRightPageScreenShot(ScreenshotUtil.DASHBOARD_FOLDER + "environment-info");
  }
  
  
  @Test
  public void screenshotDashboard() throws IOException {
    ScreenshotUtil.maximizeBrowser();
    ScreenshotUtil.captureElementScreenshot(homePage.getProcessWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "process-widget");
    ScreenshotUtil.captureElementScreenshot(homePage.getStatisticWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "statistic-widget");
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_HD_WIDTH, 800));
    ScreenshotUtil.captureElementScreenshot(homePage.getTaskWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "task-widget");
  }
  
  @Test
  public void screenshotCustomizedDashboard() throws IOException {
    showNewCustomizedDashboard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.openTaskList();
    ScreenshotUtil.resizeBrowserAndCaptureWholeScreen(ScreenshotUtil.DASHBOARD_FOLDER + "page-header-footer", new Dimension(SCREENSHOT_WIDTH, 900));
  }
  
  @Test
  public void screenshotDashboardWithAnnotation() throws IOException {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    ScreenshotUtil.maximizeBrowser();
    executeDecorateJs("numberingStatisticWidget();");
    ScreenshotUtil.captureElementScreenshot(homePage.getStatisticWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "statistics-key-information");

    ScreenshotUtil.resizeBrowser(new Dimension(1400, 800));
    refreshHomePage();
    executeDecorateJs("numberingTopBar()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(homePage.getTopBar(), ScreenshotUtil.DASHBOARD_FOLDER + "portal-header-with-numbering-annotation", new ScreenshotMargin(20, 20, 20, 120));

    executeDecorateJs("numberingTaskItem();");
    ScreenshotUtil.captureElementScreenshot(homePage.getTaskWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "personal-tasks-key-information");
    
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    refreshHomePage();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DASHBOARD_FOLDER + "legacy-dashboard");
    executeDecorateJs("highlightAndNumberingDashboardSections();");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DASHBOARD_FOLDER + "dashboard-3-sections");

    refreshHomePage();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_HD_WIDTH, 800));
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.openCompactSortMenu();
    executeDecorateJs("numberingTaskFilterAndSort();");
    ScreenshotUtil.captureElementScreenshot(homePage.getTaskWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "personal-tasks-sort-and-search-features");
  }
  

  @Test
  public void screenshotNewDashboard() throws IOException{
    
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();

    ScreenshotUtil.resizeBrowserAndCaptureWholeScreen(ScreenshotUtil.DASHBOARD_FOLDER + "dashboard", new Dimension(1200, 800));

    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    executeDecorateJs("highlightLogo();");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.DASHBOARD_FOLDER + "left-menu");

    refreshPage();
    newDashboardPage.openMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.DASHBOARD_FOLDER + "expanded-left-menu");

    ScreenshotUtil.resizeBrowser(new Dimension(1400, 800));
    executeDecorateJs("highlightTopBar()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.DASHBOARD_FOLDER + "portal-header");
  }

  @Test
  public void screenshotConfigureCustomWidget() throws IOException {
    login(TestAccount.ADMIN_USER);
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    redirectToDashboardConfiguration();
    DashboardConfigurationPage configPage = new DashboardConfigurationPage();
    configPage.selectPublicDashboardType();
    configPage.selectEditPublicDashboards();
    configPage.configureDashboardByIndex(0);
    newDashboardPage.waitForPageLoaded();
    newDashboardPage.addNewCustomWidget();
    newDashboardPage.selectCustomWidgetTypeProcess();
    newDashboardPage.selectIvyProcessForCustomWidget("Investment List (Example for Custom Widget on Dashboard)");

    WebElement dateField = newDashboardPage.findElementById("widget-configuration-form:new-widget-configuration-component:parammeters:1:param-calendar-_input");
    newDashboardPage.type(dateField, "24 Nov, 2021 00:00");

    WebElement stringField = newDashboardPage.findElementById("widget-configuration-form:new-widget-configuration-component:parammeters:2:param-string-");
    newDashboardPage.type(stringField, "a short note");

    WebElement userField = newDashboardPage.findElementById("widget-configuration-form:new-widget-configuration-component:parammeters:0:param-user-:user-selection_input");
    newDashboardPage.type(userField, "demo");
    newDashboardPage.waitForElementDisplayed(By.id("widget-configuration-form:new-widget-configuration-component:parammeters:0:param-user-:user-selection_panel"), true);
    newDashboardPage.click(By.xpath("//*[@id='widget-configuration-form:new-widget-configuration-component:parammeters:0:param-user-:user-selection_panel']/ul/li"));

    ScreenshotUtil.captureElementScreenshot(newDashboardPage.getConfigurationDialog(), ScreenshotUtil.DASHBOARD_FOLDER + "process-custom-widget-configuration");
  }

  @Test
  public void screenshotNewDashboardUserGuide() throws IOException {
    login(TestAccount.ADMIN_USER);
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForTaskWidgetLoading();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.NEW_DASHBOARD_FOLDER + "dashboard");

    // Take screenshot of widget filter panel
    newDashboardPage.clickWidgetFilter(0);
    newDashboardPage.clickByCssSelector("input[id$=':filter-form-0:search-saved-filter-input']");
    WebElement taskFilterOverlayPanel = newDashboardPage.getFilterOverlayPanel(0);
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskFilterOverlayPanel, ScreenshotUtil.NEW_DASHBOARD_FOLDER + "widget-filter", new ScreenshotMargin(20));
    taskFilterOverlayPanel.findElement(By.className("ui-overlaypanel-footer__cancel")).click();

    // Take screenshot of widget info panel
    newDashboardPage.clickWidgetInfo(0);
    WebElement taskInfoOverlayPanel = newDashboardPage.getInfoOverlayPanel(0);
    taskInfoOverlayPanel.findElement(By.className("widget-info-type--label")).click();
    newDashboardPage.waitForWidgetInfoLoading(taskInfoOverlayPanel);
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskInfoOverlayPanel, ScreenshotUtil.NEW_DASHBOARD_FOLDER + "widget-info", new ScreenshotMargin(20));
    taskInfoOverlayPanel.findElement(By.className("info-overlay-panel__footer")).findElement(By.className("ui-link")).click();

    // Take screenshot of Edit dashboard page
    redirectToDashboardConfiguration();
    DashboardConfigurationPage configPage = new DashboardConfigurationPage();
    configPage.selectPublicDashboardType();
    configPage.selectEditPublicDashboards();
    configPage.configureDashboardByIndex(0);
    newDashboardPage.waitForPageLoaded();
    WaitHelper.assertTrueWithWait(() -> ScreenshotUtil.isDOMStatusComplete());
    newDashboardPage.waitForTaskWidgetLoading();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.NEW_DASHBOARD_FOLDER + "edit-widget");

    // Take screenshot of Add new widget dialog
    newDashboardPage.clickAddWidget();
    WebElement newWidgetDialog = newDashboardPage.getAddWidgetDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(newWidgetDialog, ScreenshotUtil.NEW_DASHBOARD_FOLDER + "add-widget", new ScreenshotMargin(40));

    // Take screenshots of Task widget configuration dialog
    newWidgetDialog.findElement(By.id("new-widget-dialog-content:0:add-widget")).click();
    DashboardWidgetConfigurationDialogPage configurationDialogPage = new DashboardWidgetConfigurationDialogPage();
    ScreenshotUtil.captureElementScreenshot(configurationDialogPage.getConfigurationFilter(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "task-list-widget-configuration");
    ScreenshotUtil.captureElementScreenshot(configurationDialogPage.openManageColumnDialog(true), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "task-list-widget-table-configuration");
    configurationDialogPage.closeManageColumnDialog();
    configurationDialogPage.uncheckTaskColumn(Arrays.asList("category", "description", "expiryTimestamp"), true);
    configurationDialogPage.waitForElementDisplayed(By.id("widget-configuration-form:new-widget-configuration-component:task-widget-preview:dashboard-tasks"), true);
    ScreenshotUtil.captureElementScreenshot(configurationDialogPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "task-list-widget");
    configurationDialogPage.closeConfigurationDialog();

    // Take screenshots of Case widget configuration dialog
    newDashboardPage.clickAddWidget();
    newWidgetDialog = newDashboardPage.getAddWidgetDialog();
    newWidgetDialog.findElement(By.id("new-widget-dialog-content:1:add-widget")).click();
    configurationDialogPage = new DashboardWidgetConfigurationDialogPage();
    ScreenshotUtil.captureElementScreenshot(configurationDialogPage.getConfigurationFilter(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "case-list-widget-configuration");
    ScreenshotUtil.captureElementScreenshot(configurationDialogPage.openManageColumnDialog(false), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "case-list-widget-table-configuration");
    configurationDialogPage.closeManageColumnDialog();
    configurationDialogPage.uncheckTaskColumn(Arrays.asList("category", "description", "endTimestamp"), false);
    configurationDialogPage.waitForElementDisplayed(By.id("widget-configuration-form:new-widget-configuration-component:case-widget-preview:dashboard-cases"), true);
    ScreenshotUtil.captureElementScreenshot(configurationDialogPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "case-list-widget");
    configurationDialogPage.closeConfigurationDialog();

    // Take screenshot of Process widget configuration dialog
    newDashboardPage.clickAddWidget();
    newWidgetDialog = newDashboardPage.getAddWidgetDialog();
    newWidgetDialog.findElement(By.id("new-widget-dialog-content:2:add-widget")).click();
    configurationDialogPage = new DashboardWidgetConfigurationDialogPage();

    // Combined mode
    configurationDialogPage.selectProcessMode("Combined mode");
    configurationDialogPage.selectProcessForCombinedModeProcessWidget("Categoried Leave Request");
    configurationDialogPage.clickPreviewButton();
    configurationDialogPage.waitForCombinedProcessLoadedAfterClickPreview();
    ScreenshotUtil.captureElementScreenshot(configurationDialogPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-widget-combined-mode");

    // Compact mode
    configurationDialogPage.selectProcessMode("Compact mode");
    configurationDialogPage.selectProcessesForCompactProcessWidget(Arrays.asList("Create New Payment", "Create Support Ticket", "Sales Management"));
    configurationDialogPage.selectProcessesForCompactProcessWidget(null);
    configurationDialogPage.clickPreviewButton();
    configurationDialogPage.waitForCompactProcessLoadedAfterClickPreview();
    ScreenshotUtil.captureElementScreenshot(configurationDialogPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-widget-compact-mode");

    // Full mode
    configurationDialogPage.selectProcessMode("Full mode");
    configurationDialogPage.selectProcessForFullModeProcessWidget("Sales Management");
    configurationDialogPage.clickPreviewButton();
    configurationDialogPage.waitForFullProcessLoadedAfterClickPreview();
    ScreenshotUtil.captureElementScreenshot(configurationDialogPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-widget-full-mode");

    // Image mode
    configurationDialogPage.selectProcessMode("Image mode");
    configurationDialogPage.selectProcessForImageModeProcessWidget("Create New Payment");
    configurationDialogPage.clickPreviewButton();
    configurationDialogPage.waitForImageProcessLoadedAfterClickPreview();
    ScreenshotUtil.captureElementScreenshot(configurationDialogPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-widget-image-mode");

    configurationDialogPage.clickProcessDisplayMode();
    executeDecorateJs("highlightProcessDisplayModePanel()");
    ScreenshotUtil.captureElementScreenshot(configurationDialogPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-widget-modes");
  }

  @Test
  public void screenshotProcessViewerWidget() throws IOException {
    login(TestAccount.ADMIN_USER);
    updatePortalSetting(SHOW_LEGACY_UI .getKey(), "false");
    redirectToDashboardConfiguration();
    DashboardConfigurationPage configPage = new DashboardConfigurationPage();
    configPage.selectPublicDashboardType();
    configPage.selectEditPublicDashboards();
    configPage.configureDashboardByIndex(0);
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForPageLoaded();
    WaitHelper.assertTrueWithWait(() -> ScreenshotUtil.isDOMStatusComplete());
    newDashboardPage.waitForTaskWidgetLoading();

    newDashboardPage.clickAddWidget();
    WebElement newWidgetDialog = newDashboardPage.getAddWidgetDialog();
    newWidgetDialog.findElement(By.id("new-widget-dialog-content:5:add-widget")).click();
    DashboardWidgetConfigurationDialogPage configurationDialogPage = new DashboardWidgetConfigurationDialogPage();
    configurationDialogPage.selectProcessForProcessViewerWidget("Categoried Leave Request");
    ScreenshotUtil.captureElementScreenshot(configurationDialogPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-viewer-widget-configuration");
    configurationDialogPage.saveConfiguration();

    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForProcessViewerWidgetLoading();
    Sleeper.sleep(1000);
    ScreenshotUtil.captureElementScreenshot(newDashboardPage.getProcessViewerWidget(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-viewer-widget");
  }

  private void showNewCustomizedDashboard() {
    updatePortalSetting(SHOW_LEGACY_UI .getKey(), "false");
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    newDashboardPage = new NewDashboardPage();
  }

  private void refreshHomePage() {
    refreshPage();
    homePage.waitForStatisticRendered();
  }

  private void redirectToDashboardConfiguration() {
    redirectToRelativeLink("portalTemplate/1549F58C18A6C562/PortalDashboardConfiguration.ivp");
  }
}
