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
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CustomWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.DashboardNewsWidgetConfigurationPage;
import com.axonivy.portal.selenium.page.DashboardNewsWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.ProcessViewerWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.StatisticEditWidgetNewDashboardPage;
import com.axonivy.portal.selenium.page.TaskEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.WelcomeEditWidgetNewDashboardPage;
import com.axonivy.portal.selenium.util.ConfigurationJsonUtil;
import com.codeborne.selenide.SelenideElement;

@IvyWebTest
public class DashboardScreenshotTest extends ScreenshotBaseTest{
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

    redirectToRelativeLink(grantDashboardWritePublicPermissionUrl);
    redirectToRelativeLink(grantDashboardWriteOwnPermissionUrl);
  }

  @Test
  public void screenshotNewDashboard() throws IOException{
    showNewDashboard();
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();

    ScreenshotUtil.resizeBrowser(new Dimension(1800, 1400));
    ScreenshotUtil.resizeBrowserAndCaptureWholeScreen(ScreenshotUtil.DASHBOARD_FOLDER + "dashboard", new Dimension(1800, 1400));

    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    ScreenshotUtil.executeDecorateJs("highlightLogo();");
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.DASHBOARD_FOLDER + "left-menu");

    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.expandMainMenu();
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    ScreenshotUtil.captureHalfLeftPageScreenShot(ScreenshotUtil.DASHBOARD_FOLDER + "expanded-left-menu");

    ScreenshotUtil.resizeBrowser(new Dimension(1400, 800));
    ScreenshotUtil.executeDecorateJs("highlightTopBar()");
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.DASHBOARD_FOLDER + "portal-header");
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

    CustomWidgetNewDashBoardPage customWidgetPage = detailsEditPage.addNewCustomrWidget("Investment List (Example for Custom Widget on Dashboard)");
    customWidgetPage.inputDateField(1, "24 Nov, 2021 00:00");
    customWidgetPage.inputStringField(2, "a short note");
    customWidgetPage.inputUserField(0, "demo");

    ScreenshotUtil.captureElementScreenshot(customWidgetPage.getConfigurationDialog(), ScreenshotUtil.DASHBOARD_FOLDER + "process-custom-widget-configuration");
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
    ScreenshotUtil.captureElementScreenshot(customWidgetPage.getConfigurationDialog(), ScreenshotUtil.DASHBOARD_FOLDER + "external-page-widget-configuration");
  }

  @Test
  public void screenshotDashboardWithAnnotation() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1100, 800));
    showNewDashboard();
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    homePage.clickOnGlobalSearch();
    ScreenshotUtil.executeDecorateJs("numberingTopBar()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(homePage.getTopBar(), ScreenshotUtil.DASHBOARD_FOLDER + "portal-header-with-numbering-annotation", new ScreenshotMargin(20, 20, 20, 120));
  }

  @Test
  public void screenshotNewDashboardUserGuide() throws IOException {
    showNewDashboard();
    ScreenshotUtil.maximizeBrowser();
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.NEW_DASHBOARD_FOLDER + "dashboard");

    // Take screenshot of widget filter panel
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(homePage.openWidgetFilter(1), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "widget-filter", new ScreenshotMargin(20));
    homePage.closeWidgetFilter(1);

    var taskInfoOverlayPanel = homePage.openWidgetInformation(0);
    // Take screenshot of widget info panel
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskInfoOverlayPanel, ScreenshotUtil.NEW_DASHBOARD_FOLDER + "widget-info", new ScreenshotMargin(20));
    
    // Take screenshot of task Excel export link
    ScreenshotUtil.executeDecorateJs("highlightWidgetExportToExcelLinkForTask()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskInfoOverlayPanel, ScreenshotUtil.NEW_DASHBOARD_FOLDER + "task-export-excel", new ScreenshotMargin(20));
    taskInfoOverlayPanel.findElement(By.className("info-overlay-panel__footer")).findElement(By.className("info-overlay-panel__close-link")).click();

    // Take screenshot of case Excel export link
    var caseInfoOverlayPanel = homePage.openWidgetInformation(1);
    ScreenshotUtil.executeDecorateJs("highlightWidgetExportToExcelLinkForCase()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(caseInfoOverlayPanel, ScreenshotUtil.NEW_DASHBOARD_FOLDER + "case-export-excel", new ScreenshotMargin(20));
    caseInfoOverlayPanel.findElement(By.className("info-overlay-panel__footer")).findElement(By.className("info-overlay-panel__close-link")).click();

    // Take screenshot of Edit dashboard page
    redirectToDashboardConfiguration();
    DashboardConfigurationPage configPage = new DashboardConfigurationPage();
    configPage.selectPublicDashboardType();
    DashboardModificationPage editPage = new DashboardModificationPage();
    NewDashboardDetailsEditPage detailsEditPage = editPage.navigateToEditDashboardDetailsByName("Dashboard");
    detailsEditPage.waitForCaseWidgetLoaded();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.NEW_DASHBOARD_FOLDER + "edit-widget");

    // Take screenshot of Add new widget dialog
    WebElement newWidgetDialog = detailsEditPage.addWidget();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(newWidgetDialog, ScreenshotUtil.NEW_DASHBOARD_FOLDER + "add-widget", new ScreenshotMargin(20));

    // Take screenshots of Task widget configuration dialog
    TaskEditWidgetNewDashBoardPage taskConfigurationPage = detailsEditPage.addNewTaskWidget();
    taskConfigurationPage.waitPreviewTableLoaded();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskConfigurationPage.openMultiLanguageDialogWhenAddWidget(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "dashboard-multi-language-widget-dialog", new ScreenshotMargin(20));

    taskConfigurationPage.cancelMultiLanguageDialogWhenAddWidget();
    ScreenshotUtil.captureElementScreenshot(taskConfigurationPage.getConfigurationFilter(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "task-list-widget-configuration");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskConfigurationPage.openColumnManagementDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "task-list-widget-table-configuration", new ScreenshotMargin(20));

    taskConfigurationPage.removeAddedField("category");
    taskConfigurationPage.removeAddedField("description");
    taskConfigurationPage.removeAddedField("expiryTimestamp");
    taskConfigurationPage.saveColumn();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskConfigurationPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "task-list-widget", new ScreenshotMargin(20));
    taskConfigurationPage.closeConfigurationDialog();

    // Take screenshots of Case widget configuration dialog
    detailsEditPage.addWidget();
    CaseEditWidgetNewDashBoardPage caseConfigurationPage = detailsEditPage.addNewCaseWidget();
    ScreenshotUtil.captureElementScreenshot(caseConfigurationPage.getConfigurationFilter(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "case-list-widget-configuration");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(caseConfigurationPage.openColumnManagementDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "case-list-widget-table-configuration", new ScreenshotMargin(20));

    caseConfigurationPage.removeAddedField("category");
    caseConfigurationPage.removeAddedField("description");
    caseConfigurationPage.removeAddedField("endTimestamp");
    caseConfigurationPage.saveColumn();
    caseConfigurationPage.waitPreviewTableLoaded();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(caseConfigurationPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "case-list-widget", new ScreenshotMargin(20));
    caseConfigurationPage.closeConfigurationDialog();

    // Take screenshot of Process widget configuration dialog
    detailsEditPage.addWidget();
    ProcessEditWidgetNewDashBoardPage processConfigurationPage = detailsEditPage.addNewProcessWidget();

    // Combined mode
    processConfigurationPage.selectCombinedMode();
    processConfigurationPage.selectProcessForCombinedMode("Categoried Leave Request");
    processConfigurationPage.clickPreviewButton();
    processConfigurationPage.getCombinedModeProcessPreview();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(processConfigurationPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-widget-combined-mode", new ScreenshotMargin(20));

    // Compact mode
    processConfigurationPage.selectCompactMode();
    processConfigurationPage.selectProcessesForCompactMode(Arrays.asList("Create New Payment", "Create Support Ticket", "Sales Management"));
    processConfigurationPage.clickPreviewButton();
    processConfigurationPage.getCompactModeProcessPreview();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(processConfigurationPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-widget-compact-mode", new ScreenshotMargin(20));

    // Full mode
    processConfigurationPage.selectFullMode();
    processConfigurationPage.selectProcessForFullMode("Sales Management");
    processConfigurationPage.clickPreviewButton();
    processConfigurationPage.getFullModeProcessPreview();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(processConfigurationPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-widget-full-mode", new ScreenshotMargin(20));

    // Image mode
    processConfigurationPage.selectImageMode();
    processConfigurationPage.selectProcessForImageMode("Create New Payment");
    processConfigurationPage.clickPreviewButton();
    processConfigurationPage.getImageModeProcessPreview();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(processConfigurationPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-widget-image-mode", new ScreenshotMargin(20));

    processConfigurationPage.getProcessDisplayMode().click();
    ScreenshotUtil.executeDecorateJs("highlightProcessDisplayModePanel()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(processConfigurationPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-widget-modes", new ScreenshotMargin(20));
  }

  @Test
  public void screenshotProcessViewerWidget() throws IOException {
    ScreenshotUtil.maximizeBrowser();
    addPublicWidget(NewDashboardDetailsEditPage.PROCESS_VIEWER_WIDGET);
    ProcessViewerWidgetNewDashBoardPage processViewerPage = new ProcessViewerWidgetNewDashBoardPage();
    processViewerPage.selectProcess("Categoried Leave Request");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(processViewerPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-viewer-widget-configuration", new ScreenshotMargin(20));

    processViewerPage.clickSaveProcessViewerWidget();
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new NewDashboardPage();
    ScreenshotUtil.captureElementScreenshot(homePage.waitAndGetProcessViewerWidget(0), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "process-viewer-widget");
  }

  @Test
  public void screenshotStatisticChartWidget() throws IOException {
    ScreenshotUtil.maximizeBrowser();
    addPublicWidget(NewDashboardDetailsEditPage.STATISTIC_WIDGET);
    StatisticEditWidgetNewDashboardPage statisticPage = new StatisticEditWidgetNewDashboardPage();
    statisticPage.selectFirstChart();
    statisticPage.clickPreviewButton();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(statisticPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "statistic-chart-widget-configuration", new ScreenshotMargin(20));

    statisticPage.save();
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new NewDashboardPage();
    ScreenshotUtil.captureElementScreenshot(homePage.waitAndGetStatisticChart(0), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "statistic-chart-widget");
  }

  @Test
  public void screenshotAddClientStatisticWidget() throws IOException {
    // Take screenshot of Add new widget dialog
    redirectToDashboardConfiguration();
    DashboardConfigurationPage configPage = new DashboardConfigurationPage();
    configPage.selectPublicDashboardType();
    DashboardModificationPage editPage = new DashboardModificationPage();
    NewDashboardDetailsEditPage detailsEditPage = editPage.navigateToEditDashboardDetailsByName("Dashboard");
    detailsEditPage.waitPageLoaded();
    WebElement newWidgetDialog = detailsEditPage.addWidget();
    detailsEditPage.collapseStandardWidgets();
    ScreenshotUtil.resizeBrowser(new Dimension(1920, 1080));
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(newWidgetDialog,
        ScreenshotUtil.NEW_DASHBOARD_FOLDER + "add-client-statistic-widget", new ScreenshotMargin(20));
  }

  @Test
  public void screenshotTaskByPriorityClientStatisticChartWidget() throws IOException {
    ScreenshotUtil.maximizeBrowser();

    addPublicStatisticWidget(NewDashboardDetailsEditPage.TASK_BY_PRIORITY);
    NewDashboardDetailsEditPage newDashboard = new NewDashboardDetailsEditPage();
    newDashboard.waitPageLoaded();

    SelenideElement clientStatisticWidget = newDashboard.getStatisticWidgetConfigurationDialog();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.NEW_DASHBOARD_FOLDER + "edit-statistic-widget");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(clientStatisticWidget,
        ScreenshotUtil.NEW_DASHBOARD_FOLDER + "task-by-priority-statistic-widget-configuration", new ScreenshotMargin(20));
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new NewDashboardPage();
    ScreenshotUtil.captureElementScreenshot(homePage.waitAndGetClientStatisticChart(0),
        ScreenshotUtil.NEW_DASHBOARD_FOLDER + "task-by-priority-statistic-chart-widget");
  }

  @Test
  public void screenshotTaskGroupedByPriorityClientStatisticChartWidget() throws IOException {
    ScreenshotUtil.maximizeBrowser();

    addPublicStatisticWidget(NewDashboardDetailsEditPage.TASK_GROUPED_BY_PRIORITY);
    NewDashboardDetailsEditPage newDashboard = new NewDashboardDetailsEditPage();
    newDashboard.waitPageLoaded();
    SelenideElement clientStatisticWidget = newDashboard.getStatisticWidgetConfigurationDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(clientStatisticWidget,
        ScreenshotUtil.NEW_DASHBOARD_FOLDER + "task-grouped-by-priority-statistic-widget-configuration", new ScreenshotMargin(20));
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new NewDashboardPage();
    ScreenshotUtil.captureElementScreenshot(homePage.waitAndGetClientStatisticChart(0),
        ScreenshotUtil.NEW_DASHBOARD_FOLDER + "task-grouped-by-priority-statistic-chart-widget");
  }

  @Test
  public void screenshotNumberOfOpenTasksClientStatisticChartWidget() throws IOException {
    ScreenshotUtil.maximizeBrowser();

    addPublicStatisticWidget(NewDashboardDetailsEditPage.NUMBER_OF_OPEN_TASKS);
    NewDashboardDetailsEditPage newDashboard = new NewDashboardDetailsEditPage();
    newDashboard.waitPageLoaded();
    SelenideElement clientStatisticWidget = newDashboard.getStatisticWidgetConfigurationDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(clientStatisticWidget,
        ScreenshotUtil.NEW_DASHBOARD_FOLDER + "number-of-open-tasks-statistic-widget-configuration", new ScreenshotMargin(20));
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new NewDashboardPage();
    ScreenshotUtil.captureElementScreenshot(homePage.waitAndGetClientStatisticChart(0),
        ScreenshotUtil.NEW_DASHBOARD_FOLDER + "number-of-open-tasks-statistic-chart-widget");
  }

  @Test
  public void screenshotCompletedTasksPerDaylientStatisticChartWidget() throws IOException {
    ScreenshotUtil.maximizeBrowser();

    addPublicStatisticWidget(NewDashboardDetailsEditPage.COMPLETED_TASKS_PER_DAY);
    NewDashboardDetailsEditPage newDashboard = new NewDashboardDetailsEditPage();
    newDashboard.waitPageLoaded();
    SelenideElement clientStatisticWidget = newDashboard.getStatisticWidgetConfigurationDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(clientStatisticWidget,
        ScreenshotUtil.NEW_DASHBOARD_FOLDER + "completed-tasks-per-day-statistic-widget-configuration", new ScreenshotMargin(20));
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new NewDashboardPage();
    ScreenshotUtil.captureElementScreenshot(homePage.waitAndGetClientStatisticChart(0),
        ScreenshotUtil.NEW_DASHBOARD_FOLDER + "completed-tasks-per-day-statistic-chart-widget");
  }

  @Test
  public void screenshotStartedCasesPerDaylientStatisticChartWidget() throws IOException {
    ScreenshotUtil.maximizeBrowser();

    addPublicStatisticWidget(NewDashboardDetailsEditPage.STARTED_CASES_PER_DAY);
    NewDashboardDetailsEditPage newDashboard = new NewDashboardDetailsEditPage();
    newDashboard.waitPageLoaded();
    SelenideElement clientStatisticWidget = newDashboard.getStatisticWidgetConfigurationDialog();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(clientStatisticWidget,
        ScreenshotUtil.NEW_DASHBOARD_FOLDER + "started-cases-per-day-statistic-widget-configuration", new ScreenshotMargin(20));
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new NewDashboardPage();
    ScreenshotUtil.captureElementScreenshot(homePage.waitAndGetClientStatisticChart(0),
        ScreenshotUtil.NEW_DASHBOARD_FOLDER + "started-cases-per-day-statistic-chart-widget");
  }

  @Test
  public void screenshotWelcomeWidget() throws IOException {
    ScreenshotUtil.maximizeBrowser();
    addPublicWidget(NewDashboardDetailsEditPage.WELCOME_WIDGET);
    WelcomeEditWidgetNewDashboardPage welcomeWidgetPage = new WelcomeEditWidgetNewDashboardPage();
    welcomeWidgetPage.waitForDialogLoaded();
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(welcomeWidgetPage.getConfigurationDialog(), ScreenshotUtil.NEW_DASHBOARD_FOLDER + "welcome-widget-configuration", new ScreenshotMargin(20));
  }

  @Test
  public void screenshotNewsFeedWidget() throws IOException {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink("portalKitTestHelper/153CACC26D0D4C3D/createSampleNewsFeed.ivp");
    ScreenshotUtil.maximizeBrowser();
    addPublicWidget(NewDashboardDetailsEditPage.NEWS_WIDGET);
    DashboardNewsWidgetConfigurationPage newsWidgetPage = new DashboardNewsWidgetConfigurationPage();

    ScreenshotUtil.captureElementWithMarginOptionScreenshot(newsWidgetPage.getConfigurationDialog(),
        ScreenshotUtil.NEW_DASHBOARD_FOLDER + "news-feed-widget-configuration", new ScreenshotMargin(20));
    newsWidgetPage.save();

    ConfigurationJsonUtil.updateJSONSetting("dashboard-has-newsfeed.json", Variable.DASHBOARD);
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    
    homePage = new NewDashboardPage();

    ScreenshotUtil.captureElementScreenshot(homePage.waitAndGetNewsWidget(0),
        ScreenshotUtil.NEW_DASHBOARD_FOLDER + "news-feed-widget");
    ScreenshotUtil.resizeBrowser(new Dimension(900, 850));
    DashboardNewsWidgetPage newDashboardPage = new DashboardNewsWidgetPage("News feed");
    
    newDashboardPage.openAddNewsFeedItemDialog();
    newDashboardPage.enterNewsItemData("en", "si-send-email", "Welcome to Portal News feed", "Welcome to Portal News feed");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.NEW_DASHBOARD_FOLDER + "news-feed-widget-manage-content");
    String tabIndex = newDashboardPage.selectNewsLanguage("fr");
    newDashboardPage.clickOnTitle(tabIndex);
    WebElement translation = newDashboardPage.getTranslationOverlayPanel(1);
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.NEW_DASHBOARD_FOLDER + "news-feed-widget-overlay-panel");
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
    detailsEditPage.waitPageLoaded();
    detailsEditPage.addWidget();
    detailsEditPage.addWidgetByName(widgetName);
  }

  private void addPublicStatisticWidget(String widgetName) {
    redirectToDashboardConfiguration();
    DashboardConfigurationPage configPage = new DashboardConfigurationPage();
    configPage.selectPublicDashboardType();
    DashboardModificationPage editPage = new DashboardModificationPage();
    NewDashboardDetailsEditPage detailsEditPage = editPage.navigateToEditDashboardDetailsByName("Dashboard");
    detailsEditPage.waitPageLoaded();
    detailsEditPage.addWidget();
    detailsEditPage.addStatisticWidgetByName(widgetName);
  }
}
