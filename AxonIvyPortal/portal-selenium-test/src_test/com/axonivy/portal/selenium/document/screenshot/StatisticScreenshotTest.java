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
import com.axonivy.portal.selenium.page.StatisticWidgetNewDashboardPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

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
    StatisticWidgetNewDashboardPage tasksByPriorWidget = newDashboardPage.selectStatisticChartWidget("Tasks By Priority");
    StatisticWidgetNewDashboardPage topPriorWidget = newDashboardPage.selectStatisticChartWidget("Top Priority: 3 Days");
    StatisticWidgetNewDashboardPage runningCasesWidget = newDashboardPage.selectStatisticChartWidget("Running Cases");
    StatisticWidgetNewDashboardPage newCasesWidget = newDashboardPage.selectStatisticChartWidget("New Cases Per Day");
    StatisticWidgetNewDashboardPage avgRuntimeWidget = newDashboardPage.selectStatisticChartWidget("Case Category Avg. Runtime");
    StatisticWidgetNewDashboardPage dueTodayWidget = newDashboardPage.selectStatisticChartWidget("Due Today");
    StatisticWidgetNewDashboardPage completedCasesPerDayWidget = newDashboardPage.selectStatisticChartWidget("Completed Cases Per Day");
    StatisticWidgetNewDashboardPage tasksExpireTheEndOfWeek = newDashboardPage.selectStatisticChartWidget("Tasks that expire by the end of the week");
    StatisticWidgetNewDashboardPage openTasksWidget = newDashboardPage.selectStatisticChartWidget("Open Tasks");

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
    StatisticWidgetNewDashboardPage tasksByPriorWidget = newDashboardPage.selectStatisticChartWidget("Tasks By Priority");
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
    StatisticWidgetNewDashboardPage tasksByPriorWidget = newDashboardPage.selectStatisticChartWidget("Tasks By Priority");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(tasksByPriorWidget.getWidget(),ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "tasks-by-prior-number-chart", new ScreenshotMargin(5, 5));
  }
}
