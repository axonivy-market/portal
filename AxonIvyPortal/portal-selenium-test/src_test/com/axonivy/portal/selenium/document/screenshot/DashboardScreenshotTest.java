package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CustomWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.DashboardNewsWidgetConfigurationPage;
import com.axonivy.portal.selenium.page.DashboardNewsWidgetPage;
import com.axonivy.portal.selenium.page.DashboardNotificationWidgetConfigurationPage;
import com.axonivy.portal.selenium.page.DashboardNotificationWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.ProcessViewerWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.StatisticEditWidgetNewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.WelcomeEditWidgetNewDashboardPage;
import com.axonivy.portal.selenium.util.ConfigurationJsonUtils;

@IvyWebTest
public class DashboardScreenshotTest extends ScreenshotBaseTest {
  private NewDashboardPage homePage;
  private static final int SCREENSHOT_WIDTH = 1500;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.ENABLE_GROUP_CHAT.getKey(), "true");
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
  }

  @Test
  public void screenshotNewDashboard() throws IOException {
    showNewDashboard();
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();

    ScreenshotUtils.resizeBrowser(new Dimension(1800, 1400));
    ScreenshotUtils.resizeBrowserAndCaptureWholeScreen(ScreenshotUtils.DASHBOARD_FOLDER + "dashboard",
        new Dimension(1800, 1400));

    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    ScreenshotUtils.executeDecorateJs("highlightLogo();");
    ScreenshotUtils.captureHalfLeftPageScreenShot(ScreenshotUtils.DASHBOARD_FOLDER + "left-menu");

    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.expandMainMenu();
    ScreenshotUtils.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    ScreenshotUtils.captureHalfLeftPageScreenShot(ScreenshotUtils.DASHBOARD_FOLDER + "expanded-left-menu");

    ScreenshotUtils.resizeBrowser(new Dimension(1400, 800));
    ScreenshotUtils.executeDecorateJs("highlightTopBar()");
    ScreenshotUtils.captureHalfTopPageScreenShot(ScreenshotUtils.DASHBOARD_FOLDER + "portal-header");
  }

  @Test
  public void screenshotConfigureCustomWidget() throws IOException {
    redirectToDashboardConfiguration();
    DashboardConfigurationPage configPage = new DashboardConfigurationPage();
    configPage.selectPublicDashboardType();
    DashboardModificationPage editPage = new DashboardModificationPage();
    NewDashboardDetailsEditPage detailsEditPage = editPage.navigateToEditDashboardDetailsByName("Dashboard");
    detailsEditPage.waitPageLoaded();
    detailsEditPage.addWidget();

    CustomWidgetNewDashBoardPage customWidgetPage =
        detailsEditPage.addNewCustomrWidget("Investment List (Example for Custom Widget on Dashboard)");
    customWidgetPage.inputDateField(1, "24 Nov, 2021 00:00");
    customWidgetPage.inputStringField(2, "a short note");
    customWidgetPage.inputUserField(0, "demo");

    ScreenshotUtils.captureElementScreenshot(customWidgetPage.getConfigurationDialog(),
        ScreenshotUtils.DASHBOARD_FOLDER + "process-custom-widget-configuration");
  }

  @Test
  public void screenshotConfigureExternalPageWidget() throws IOException {
    redirectToDashboardConfiguration();
    DashboardConfigurationPage configPage = new DashboardConfigurationPage();
    configPage.selectPublicDashboardType();
    DashboardModificationPage editPage = new DashboardModificationPage();
    NewDashboardDetailsEditPage detailsEditPage = editPage.navigateToEditDashboardDetailsByName("Dashboard");
    detailsEditPage.waitPageLoaded();
    detailsEditPage.addWidget();

    CustomWidgetNewDashBoardPage customWidgetPage = detailsEditPage.addExternalPageWidget();
    customWidgetPage.inputExternalUrlField("https://developer.axonivy.com");
    ScreenshotUtils.captureElementScreenshot(customWidgetPage.getConfigurationDialog(),
        ScreenshotUtils.DASHBOARD_FOLDER + "external-page-widget-configuration");
  }

  @Test
  public void screenshotConfigureNotificationsWidget() throws IOException {
    redirectToDashboardConfiguration();
    DashboardConfigurationPage configPage = new DashboardConfigurationPage();
    configPage.selectPublicDashboardType();
    DashboardModificationPage editPage = new DashboardModificationPage();
    NewDashboardDetailsEditPage detailsEditPage = editPage.navigateToEditDashboardDetailsByName("Dashboard");
    detailsEditPage.waitPageLoaded();
    detailsEditPage.addWidget();

    DashboardNotificationWidgetConfigurationPage notiWidgetPage = detailsEditPage.addNotificationWidget();
    notiWidgetPage.changeFilter();
    ScreenshotUtils.captureElementScreenshot(notiWidgetPage.getConfigurationDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "notification-widget-configuration");
    notiWidgetPage.save();
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    DashboardNotificationWidgetPage notiPage = new DashboardNotificationWidgetPage();
    ScreenshotUtils.captureElementScreenshot(notiPage.getWidgetElement(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "notification-widget");
  }

  @Test
  public void screenshotDashboardWithAnnotation() throws IOException {
    ScreenshotUtils.resizeBrowser(new Dimension(1100, 800));
    showNewDashboard();
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    homePage.clickOnGlobalSearch();
    ScreenshotUtils.executeDecorateJs("numberingTopBar()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(homePage.getTopBar(),
        ScreenshotUtils.DASHBOARD_FOLDER + "portal-header-with-numbering-annotation",
        new ScreenshotMargin(20, 20, 20, 120));
  }

  @Test
  public void screenshotNewDashboardUserGuide() throws IOException {
    showNewDashboard();
    ScreenshotUtils.maximizeBrowser();
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.NEW_DASHBOARD_FOLDER + "dashboard");

    // Take screenshot of widget filter panel
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(homePage.openWidgetFilter(1),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "widget-filter", new ScreenshotMargin(20));
    homePage.closeWidgetFilter(1);

    var taskInfoOverlayPanel = homePage.openWidgetInformation(0);
    // Take screenshot of widget info panel
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskInfoOverlayPanel,
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "widget-info", new ScreenshotMargin(20));

    // Take screenshot of task Excel export link
    ScreenshotUtils.executeDecorateJs("highlightWidgetExportToExcelLinkForTask()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskInfoOverlayPanel,
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "task-export-excel", new ScreenshotMargin(20));
    taskInfoOverlayPanel.findElement(By.className("info-overlay-panel__footer"))
        .findElement(By.className("info-overlay-panel__close-link")).click();

    // Take screenshot of case Excel export link
    var caseInfoOverlayPanel = homePage.openWidgetInformation(1);
    ScreenshotUtils.executeDecorateJs("highlightWidgetExportToExcelLinkForCase()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(caseInfoOverlayPanel,
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "case-export-excel", new ScreenshotMargin(20));
    caseInfoOverlayPanel.findElement(By.className("info-overlay-panel__footer"))
        .findElement(By.className("info-overlay-panel__close-link")).click();

    // Take screenshot of Edit dashboard page
    redirectToDashboardConfiguration();
    DashboardConfigurationPage configPage = new DashboardConfigurationPage();
    configPage.selectPublicDashboardType();
    DashboardModificationPage editPage = new DashboardModificationPage();
    NewDashboardDetailsEditPage detailsEditPage = editPage.navigateToEditDashboardDetailsByName("Dashboard");
    detailsEditPage.waitForCaseWidgetLoaded();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.NEW_DASHBOARD_FOLDER + "edit-widget");

    // Take screenshot of Add new widget dialog
    WebElement newWidgetDialog = detailsEditPage.addWidget();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(newWidgetDialog,
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "add-widget", new ScreenshotMargin(20));

    // Take screenshots of Task widget configuration dialog
    TaskEditWidgetNewDashBoardPage taskConfigurationPage = detailsEditPage.addNewTaskWidget();
    taskConfigurationPage.waitPreviewTableLoaded();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(
        taskConfigurationPage.openMultiLanguageDialogWhenAddWidget(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "dashboard-multi-language-widget-dialog", new ScreenshotMargin(20));

    taskConfigurationPage.cancelMultiLanguageDialogWhenAddWidget();
    ScreenshotUtils.captureElementScreenshot(taskConfigurationPage.getConfigurationFilter(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "task-list-widget-configuration");
    WebElement columnManagementDialog = taskConfigurationPage.openColumnManagementDialog();
    ScreenshotUtils.captureElementScreenshot(columnManagementDialog,
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "task-list-widget-table-configuration");
    taskConfigurationPage.getAddingFieldColumnType().click();
    ScreenshotUtils.executeDecorateJs("highlightProcessDisplayModePanel()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(columnManagementDialog,
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "task-column-field-type-configuration", new ScreenshotMargin(20));

    taskConfigurationPage.removeAddedField("category");
    taskConfigurationPage.removeAddedField("description");
    taskConfigurationPage.removeAddedField("expiryTimestamp");
    taskConfigurationPage.saveColumn();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskConfigurationPage.getConfigurationDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "task-list-widget", new ScreenshotMargin(20));
    taskConfigurationPage.closeConfigurationDialog();

    // Take screenshots of Case widget configuration dialog
    detailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage caseConfigurationPage = detailsEditPage.addNewCaseWidget();
    ScreenshotUtils.captureElementScreenshot(caseConfigurationPage.getConfigurationFilter(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "case-list-widget-configuration");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(caseConfigurationPage.openColumnManagementDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "case-list-widget-table-configuration", new ScreenshotMargin(20));

    caseConfigurationPage.removeAddedField("category");
    caseConfigurationPage.removeAddedField("description");
    caseConfigurationPage.removeAddedField("endTimestamp");
    caseConfigurationPage.saveColumn();
    caseConfigurationPage.waitPreviewTableLoaded();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(caseConfigurationPage.getConfigurationDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "case-list-widget", new ScreenshotMargin(20));
    caseConfigurationPage.closeConfigurationDialog();

    // Take screenshot of Process widget configuration dialog
    detailsEditPage.addWidget();
    ProcessEditWidgetNewDashBoardPage processConfigurationPage = detailsEditPage.addNewProcessWidget();

    // Combined mode
    processConfigurationPage.selectCombinedMode();
    processConfigurationPage.selectProcessForCombinedMode("Categoried Leave Request");
    processConfigurationPage.clickPreviewButton();
    processConfigurationPage.getCombinedModeProcessPreview();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(processConfigurationPage.getConfigurationDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "process-widget-combined-mode", new ScreenshotMargin(20));

    // Compact mode
    processConfigurationPage.selectCompactMode();
    processConfigurationPage.selectProcessesForCompactMode(
        Arrays.asList("Create New Payment", "Create Support Ticket", "Sales Management"));
    processConfigurationPage.clickPreviewButton();
    processConfigurationPage.getCompactModeProcessPreview();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(processConfigurationPage.getConfigurationDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "process-widget-compact-mode", new ScreenshotMargin(20));

    // Full mode
    processConfigurationPage.selectFullMode();
    processConfigurationPage.selectProcessForFullMode("Sales Management");
    processConfigurationPage.clickPreviewButton();
    processConfigurationPage.getFullModeProcessPreview();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(processConfigurationPage.getConfigurationDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "process-widget-full-mode", new ScreenshotMargin(20));

    // Image mode
    processConfigurationPage.selectImageMode();
    processConfigurationPage.selectProcessForImageMode("Create New Payment");
    processConfigurationPage.clickPreviewButton();
    processConfigurationPage.getImageModeProcessPreview();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(processConfigurationPage.getConfigurationDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "process-widget-image-mode", new ScreenshotMargin(20));

    processConfigurationPage.getProcessDisplayMode().click();
    ScreenshotUtils.executeDecorateJs("highlightProcessDisplayModePanel()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(processConfigurationPage.getConfigurationDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "process-widget-modes", new ScreenshotMargin(20));
  }

  @Test
  public void screenshotProcessViewerWidget() throws IOException {
    ScreenshotUtils.maximizeBrowser();
    addPublicWidget(NewDashboardDetailsEditPage.PROCESS_VIEWER_WIDGET);
    ProcessViewerWidgetNewDashBoardPage processViewerPage = new ProcessViewerWidgetNewDashBoardPage();
    processViewerPage.selectProcess("Categoried Leave Request");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(processViewerPage.getConfigurationDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "process-viewer-widget-configuration", new ScreenshotMargin(20));

    processViewerPage.clickSaveProcessViewerWidget();
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new NewDashboardPage();
    ScreenshotUtils.captureElementScreenshot(homePage.waitAndGetProcessViewerWidget(0),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "process-viewer-widget");
  }

  @Test
  public void screenshotStatisticChartWidget() throws IOException {
    ScreenshotUtils.maximizeBrowser();
    addPublicWidget(NewDashboardDetailsEditPage.STATISTIC_WIDGET);
    StatisticEditWidgetNewDashboardPage statisticPage = new StatisticEditWidgetNewDashboardPage();
    statisticPage.selectFirstChart();
    statisticPage.clickPreviewButton();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(statisticPage.getConfigurationDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "statistic-chart-widget-configuration", new ScreenshotMargin(20));

    statisticPage.save();
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new NewDashboardPage();
    ScreenshotUtils.captureElementScreenshot(homePage.waitAndGetStatisticChart(0),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "statistic-chart-widget");
  }

  @Test
  public void screenshotWelcomeWidget() throws IOException {
    ScreenshotUtils.maximizeBrowser();
    addPublicWidget(NewDashboardDetailsEditPage.WELCOME_WIDGET);
    WelcomeEditWidgetNewDashboardPage welcomeWidgetPage = new WelcomeEditWidgetNewDashboardPage();
    welcomeWidgetPage.waitForDialogLoaded();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(welcomeWidgetPage.getConfigurationDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "welcome-widget-configuration", new ScreenshotMargin(20));
  }

  @Test
  public void screenshotNewsFeedWidget() throws IOException {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink("portalKitTestHelper/153CACC26D0D4C3D/createSampleNewsFeed.ivp");
    ScreenshotUtils.maximizeBrowser();
    addPublicWidget(NewDashboardDetailsEditPage.NEWS_WIDGET);
    DashboardNewsWidgetConfigurationPage newsWidgetPage = new DashboardNewsWidgetConfigurationPage();

    ScreenshotUtils.captureElementWithMarginOptionScreenshot(newsWidgetPage.getConfigurationDialog(),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "news-feed-widget-configuration", new ScreenshotMargin(20));
    newsWidgetPage.save();

    ConfigurationJsonUtils.updateJSONSetting("dashboard-has-newsfeed.json", Variable.DASHBOARD);
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);

    homePage = new NewDashboardPage();

    ScreenshotUtils.captureElementScreenshot(homePage.waitAndGetNewsWidget(0),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "news-feed-widget");
    ScreenshotUtils.resizeBrowser(new Dimension(900, 850));
    DashboardNewsWidgetPage newDashboardPage = new DashboardNewsWidgetPage("News feed");

    newDashboardPage.openAddNewsFeedItemDialog();
    newDashboardPage.enterNewsItemData("en", "si-send-email", "Welcome to Portal News feed",
        "Welcome to Portal News feed");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.NEW_DASHBOARD_FOLDER + "news-feed-widget-manage-content");
    String tabIndex = newDashboardPage.selectNewsLanguage("fr");
    newDashboardPage.clickOnTitle(tabIndex);
    WebElement translation = newDashboardPage.getTranslationOverlayPanel(1);
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.NEW_DASHBOARD_FOLDER + "news-feed-widget-overlay-panel");
    translation.findElement(By.cssSelector("span.ui-icon-closethick")).click();
    newDashboardPage.findTranslationButton(tabIndex);
  }

  private void redirectToDashboardConfiguration() {
    redirectToRelativeLink("portal/1549F58C18A6C562/PortalDashboardConfiguration.ivp");
  }

  private void addPublicWidget(String widgetName) {
    redirectToDashboardConfiguration();
    DashboardConfigurationPage configPage = new DashboardConfigurationPage();
    configPage.selectPublicDashboardType();
    DashboardModificationPage editPage = new DashboardModificationPage();
    NewDashboardDetailsEditPage detailsEditPage = editPage.navigateToEditDashboardDetailsByName("Dashboard");
    detailsEditPage.waitForCaseWidgetLoaded();
    detailsEditPage.waitPageLoaded();
    detailsEditPage.addWidget();
    detailsEditPage.addWidgetByName(widgetName);
  }
}
