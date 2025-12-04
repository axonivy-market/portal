package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.StatisticWidgetNewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CustomStatisticConfigurationPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.StatisticConfigurationPage;

import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
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
  
  @Test
  public void screenshotCreateNewCustomStatistic() throws IOException {
    showNewDashboard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    ScreenshotUtils.resizeBrowser(new Dimension(1386, 1200));
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    var newDashboardDetailsEditPage = modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    WebElement newWidgetDialog = newDashboardDetailsEditPage.addWidget();
    newDashboardDetailsEditPage.collapseStandardWidgets();
    ScreenshotUtils.executeDecorateJs("highlightCreateCustomStatisticWidgetButton()");
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(newWidgetDialog,
        ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "create-new-custom-statistic-widget", new ScreenshotMargin(20));

    newDashboardDetailsEditPage.clickOnCreateCustomStatisiticWidget();
    CustomStatisticConfigurationPage customStatisticConfigurationPage = new CustomStatisticConfigurationPage();
    customStatisticConfigurationPage.waitForPageLoad();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "custom-statistic-widget-configuration-page");
    StatisticConfigurationPage statisticConfigurationPage = new StatisticConfigurationPage();
    statisticConfigurationPage.enableDrillDownFeature();
    statisticConfigurationPage.toggleAutoRefresh();
    statisticConfigurationPage.toggleConditionBasedColoring();
    statisticConfigurationPage.addNewCondition();
    statisticConfigurationPage.configureThreshold(0, "Greater than", "5", "#6299f7");
    ScreenshotUtils.maximizeBrowser();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(customStatisticConfigurationPage.getAdvancedSettings(),ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "advanced-settings", new ScreenshotMargin(5, 5));
  }
  
  @Test
  public void screenshotChartDrillDownDashboard() throws IOException {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestDataForCustomFieldsWithCMS);
    redirectToRelativeLink(testCaseListPermission);
    redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    configurationPage.openCreatePublicDashboardMenu();
    configurationPage.createPublicDashboardFromScratch("My Dashboard", "fa-coffee", "description", Arrays.asList("Everybody"), DashboardDisplayType.SUB_MENU);
    ScreenshotUtils.maximizeBrowser();
    configurationPage.clickOnAddWidgetButton();
    StatisticConfigurationPage statisticConfigurationPage = configurationPage
        .clickOnCreateCustomStatisticWidgetButton();
    statisticConfigurationPage.setChartName("Task Number Chart");
    statisticConfigurationPage.changeChartTarget("Task");
    statisticConfigurationPage.changeChartType(ChartType.NUMBER.getName());
    statisticConfigurationPage.addFilter("Created Date", FilterOperator.TODAY);
    statisticConfigurationPage.enableDrillDownFeature();
    
    assertTrue(statisticConfigurationPage.isDrillDownFeatureEnabled());
    statisticConfigurationPage.clickCreateStatisticChart();
    configurationPage.clickOnAddWidgetButton();
    configurationPage.addNewStatisticWidget("Task Number Chart");
    configurationPage.backToHomePage();
    StatisticWidgetNewDashboardPage statisticWidget = newDashboardPage.selectStatisticChartWidget("Case Number Chart");
    assertTrue(statisticWidget.isChartNumberElementClickable());
    newDashboardPage = statisticWidget.clickOnElementOnNumberChart("0");
    TaskWidgetNewDashBoardPage drillDownWidget = newDashboardPage.selectTaskWidget("Drill-down task widget for Task Number Chart");
    assertTrue(newDashboardPage.isBackButtonAppear());
    assertTrue(drillDownWidget.isDisplayed());
    ScreenshotUtils.maximizeBrowser();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.STATISTIC_WIDGET_FOLDER + "drill-down-dashboard");
  }
}
