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
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.ClientStatisticWidgetNewDashboardPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.StatisticWidgetPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class StatisticScreenshotTest extends ScreenshotBaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.ENABLE_GROUP_CHAT.getKey(), "true");
    redirectToRelativeLink(createTestingCaseContainOneTask);
    login(TestAccount.ADMIN_USER);
  }

  @Test
  public void screenshotForStatistic() throws IOException {
    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    MainMenuPage mainMenu = new MainMenuPage();

    TaskWidgetPage taskWidgetPage = mainMenu.openTaskList();
    taskWidgetPage.openTask("SupportTicket");

    ScreenshotUtils.resizeBrowser(new Dimension(1460, 800));
    WaitHelper.waitForNavigation(() -> redirectToRelativeLink(createTestingTasksUrl));
    homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    homePage.waitForGrowlMessageDisappear();
    mainMenu.expandMainMenu();

    ScreenshotUtils.executeDecorateJs("highlightStatisticNavigation()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "navigate-to-full-statistics-page");
    ScreenshotUtils.resizeBrowser(new Dimension(1386, 2210));

    StatisticWidgetPage statisticWidgetPage = mainMenu.openStatisticPage();
    statisticWidgetPage.switchCreateMode();
    mainMenu.closeMainMenu();

    statisticWidgetPage.createTaskByPriorityChart();
    statisticWidgetPage.createTaskByExpiryChart();
    ScreenshotUtils.captureElementScreenshot(statisticWidgetPage.waitAndGetChartCreationContainer(),
        ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "available-charts");

    WebElement chartCreationDialog = statisticWidgetPage.getCaseByFinishedTaskCreationDialog();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(chartCreationDialog,
        ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "chart-creation-dialog", new ScreenshotMargin(10, 10));

    refreshPage();
    statisticWidgetPage.waitForChartCreationPageRendered();
    statisticWidgetPage.backToDashboard();
    statisticWidgetPage.waitForAllChartLoaded();
    ScreenshotUtils.executeDecorateJs("numberingChartPanel()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(statisticWidgetPage.waitAndGetChartPanelByIndex(1),
        ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "chart-detail-with-annotation", new ScreenshotMargin(20, 10));
    refreshPage();
    statisticWidgetPage.waitForAllChartLoaded();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(statisticWidgetPage.getChartInfoDialogOfChart(1),
        ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "chart-info-dialog", new ScreenshotMargin(20, 10));
  }
  
  @Test
  public void screenshotStatisticWidgetList() throws IOException {
    showNewDashboard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    ScreenshotUtils.resizeBrowser(new Dimension(1386, 1200));
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    var newDashboardDetailsEditPage = modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    WebElement newWidgetDialog = newDashboardDetailsEditPage.addWidget();
    newDashboardDetailsEditPage.scrollToStatistic();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(newWidgetDialog,
        ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "statistic-widget-list", new ScreenshotMargin(20));
  }
  
  @Test
  public void screenshotStatisticStandardDemo() throws IOException {
    redirectToRelativeLink(createDataCreatedDate);
    redirectToRelativeLink(createDataForStatisticWidget);
    redirectToRelativeLink(createDataFinishedDate);
    redirectToRelativeLink(createCasesForCaseListCustomization);
    createJSonFile("dashboard-statistic-widget-demo.json", PortalVariable.DASHBOARD.key);
    redirectToNewDashBoard();
    ScreenshotUtils.resizeBrowser(new Dimension(1500, 1500));
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitStatisticChartLoaded();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "dashboard-statistic-widget-demo");
    ClientStatisticWidgetNewDashboardPage tasksByPriorWidget = newDashboardPage.selectClientStatisticChartWidget("Tasks By Priority");
    ClientStatisticWidgetNewDashboardPage topPriorWidget = newDashboardPage.selectClientStatisticChartWidget("Top Priority: 3 Days");
    ClientStatisticWidgetNewDashboardPage runningCasesWidget = newDashboardPage.selectClientStatisticChartWidget("Running Cases");
    ClientStatisticWidgetNewDashboardPage newCasesWidget = newDashboardPage.selectClientStatisticChartWidget("New Cases Per Day");
    ClientStatisticWidgetNewDashboardPage avgRuntimeWidget = newDashboardPage.selectClientStatisticChartWidget("Case Category Avg. Runtime");
    ClientStatisticWidgetNewDashboardPage dueTodayWidget = newDashboardPage.selectClientStatisticChartWidget("Due Today");
    ClientStatisticWidgetNewDashboardPage completedCasesPerDayWidget = newDashboardPage.selectClientStatisticChartWidget("Completed Cases Per Day");
    ClientStatisticWidgetNewDashboardPage tasksExpireTheEndOfWeek = newDashboardPage.selectClientStatisticChartWidget("Tasks that expire by the end of the week");
    ClientStatisticWidgetNewDashboardPage openTasksWidget = newDashboardPage.selectClientStatisticChartWidget("Open Tasks");

    ScreenshotUtils.captureElementWithMarginOptionScreenshot(tasksByPriorWidget.getWidget(),ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "tasks-by-prior-pie-chart", new ScreenshotMargin(5, 5));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(topPriorWidget.getWidget(),ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "top-prior-chart", new ScreenshotMargin(5, 5));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(runningCasesWidget.getWidget(),ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "running-cases-chart", new ScreenshotMargin(5, 5));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(newCasesWidget.getWidget(),ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "new-cases-chart", new ScreenshotMargin(5, 5));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(avgRuntimeWidget.getWidget(),ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "avg-runtime-chart", new ScreenshotMargin(5, 5));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(dueTodayWidget.getWidget(),ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "due-today-chart", new ScreenshotMargin(5, 5));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(completedCasesPerDayWidget.getWidget(),ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "completed-cases-chart", new ScreenshotMargin(5, 5));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(tasksExpireTheEndOfWeek.getWidget(),ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "tasks-expire-end-week-chart", new ScreenshotMargin(5, 5));
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(openTasksWidget.getWidget(),ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "open-tasks-chart", new ScreenshotMargin(5, 5));
  }
  
  @Test
  public void screenshotBarChart() throws IOException {
    redirectToRelativeLink(createDataCreatedDate);
    redirectToRelativeLink(createDataForStatisticWidget);
    redirectToRelativeLink(createDataFinishedDate);
    redirectToRelativeLink(createCasesForCaseListCustomization);
    createJSonFile("dashboard-has-one-bar-chart.json", PortalVariable.DASHBOARD.key);
    redirectToNewDashBoard();
    ScreenshotUtils.resizeBrowser(new Dimension(1500, 1500));
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitStatisticChartLoaded();
    ClientStatisticWidgetNewDashboardPage tasksByPriorWidget = newDashboardPage.selectClientStatisticChartWidget("Tasks By Priority");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(tasksByPriorWidget.getWidget(),ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "tasks-by-prior-bar-chart", new ScreenshotMargin(5, 5));
  }
  
  @Test
  public void screenshotNumberChart() throws IOException {
    redirectToRelativeLink(createDataCreatedDate);
    redirectToRelativeLink(createDataForStatisticWidget);
    redirectToRelativeLink(createDataFinishedDate);
    redirectToRelativeLink(createCasesForCaseListCustomization);
    createJSonFile("dashboard-has-one-number-chart.json", PortalVariable.DASHBOARD.key);
    redirectToNewDashBoard();
    ScreenshotUtils.resizeBrowser(new Dimension(1500, 1500));
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitStatisticChartLoaded();
    ClientStatisticWidgetNewDashboardPage tasksByPriorWidget = newDashboardPage.selectClientStatisticChartWidget("Tasks By Priority");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(tasksByPriorWidget.getWidget(),ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "tasks-by-prior-number-chart", new ScreenshotMargin(5, 5));
  }
}
