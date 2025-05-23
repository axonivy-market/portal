package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.StatisticConfigurationPage;
import com.axonivy.portal.selenium.page.StatisticWidgetNewDashboardPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class StatisticWidgetTest extends BaseTest {
  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    this.newDashboardPage = new NewDashboardPage();
  }
  
  @Test
  public void testNumberChart() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToNewDashBoard();
    StatisticWidgetNewDashboardPage openTasksWidget = newDashboardPage.selectStatisticChartWidget("Open Tasks");
    StatisticWidgetNewDashboardPage runningCasesWidget = newDashboardPage.selectStatisticChartWidget("Running Cases");
    ScreenshotUtils.maximizeBrowser();
    openTasksWidget.getAllChartNumbers().shouldHave(CollectionCondition.size(1));
    openTasksWidget.getAllChartLabels().shouldHave(CollectionCondition.size(1));
    openTasksWidget.getAllChartLabels().first().text().equals("Open");
    runningCasesWidget.getAllChartLabels().first().text().equals("Running");
  }
  
  @Test
  public void testAddNewStatisticWidget() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToNewDashBoard();
    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.maximizeBrowser();
    configurationPage.clickOnAddWidgetButton();
    StatisticWidgetNewDashboardPage runningCasesWidget = configurationPage.addNewStatisticWidget("Running Cases");
    runningCasesWidget.getAllChartLabels().first().text().equals("Running");
  }

  @Test
  public void testCreateCustomChartForCase() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToNewDashBoard();

    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.maximizeBrowser();
    configurationPage.clickOnAddWidgetButton();
    StatisticConfigurationPage statisticConfigurationPage = configurationPage.clickOnCreateCustomStatisticWidgetButton();
    statisticConfigurationPage.setChartName("Custom statistic chart CASE");
    // Chart target to CASE
    statisticConfigurationPage.changeChartTarget("Case");
    assertTrue(statisticConfigurationPage.getPermissions().size() == 2);

    statisticConfigurationPage.clickGeneratePreviewChart();
    statisticConfigurationPage.chartCanvasVisible();

    // BAR chart
    statisticConfigurationPage.changeChartType("Bar");
    statisticConfigurationPage.clickGeneratePreviewChart();
    assertEquals(statisticConfigurationPage.getAggregationItems().size(), 6);
    // Change to Created date
    statisticConfigurationPage.changeGroupBy("Created date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Completed date
    statisticConfigurationPage.changeGroupBy("Completed date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Custom field
    statisticConfigurationPage.changeGroupBy("Custom field");
    assertTrue(statisticConfigurationPage.getCaseCustomFieldItems().size() > 0);
    statisticConfigurationPage.axisXYVisible();
    statisticConfigurationPage.autoRefeshToggleVisible();
    statisticConfigurationPage.backgroundColorVisible();

    // LINE chart
    statisticConfigurationPage.changeChartType("Line");
    assertEquals(statisticConfigurationPage.getAggregationItems().size(), 6);
    // Change to Created date
    statisticConfigurationPage.changeGroupBy("Created date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Completed date
    statisticConfigurationPage.changeGroupBy("Completed date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Custom field
    statisticConfigurationPage.changeGroupBy("Custom field");
    assertTrue(statisticConfigurationPage.getCaseCustomFieldItems().size() > 0);
    statisticConfigurationPage.axisXYVisible();
    statisticConfigurationPage.autoRefeshToggleVisible();
    statisticConfigurationPage.backgroundColorVisible();

    // PIE chart
    statisticConfigurationPage.changeChartType("Pie");
    assertEquals(statisticConfigurationPage.getAggregationItems().size(), 6);
    // Change to Created date
    statisticConfigurationPage.changeGroupBy("Created date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Completed date
    statisticConfigurationPage.changeGroupBy("Completed date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Custom field
    statisticConfigurationPage.changeGroupBy("Custom field");
    assertTrue(statisticConfigurationPage.getCaseCustomFieldItems().size() > 0);
    statisticConfigurationPage.autoRefeshToggleVisible();
    statisticConfigurationPage.backgroundColorVisible();

    // NUMBER chart
    statisticConfigurationPage.changeChartType("Number");
    // Change to State
    statisticConfigurationPage.changeGroupBy("State");
    assertEquals(statisticConfigurationPage.getAggregationItems().size(), 3);
    statisticConfigurationPage.autoRefeshToggleVisible();
    statisticConfigurationPage.hideLabelVisible();

    // Configure for Filter
    statisticConfigurationPage.changeChartType("Number");
    // Change to State
    statisticConfigurationPage.changeGroupBy("State");

    // Filter State
    statisticConfigurationPage.addFilter("State", null);
    statisticConfigurationPage.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "OPEN");
    statisticConfigurationPage.clickGeneratePreviewChart();
    assertEquals(statisticConfigurationPage.getPreviewChartNumberLabel(), "Open");
    assertTrue(statisticConfigurationPage.getPreviewChartNumberValue() == 14);

    // Filter Category
    statisticConfigurationPage.addFilter("Category", null);
    statisticConfigurationPage.inputValueOnLatestFilter(FilterValueType.CATEGORY_TYPE, "TestCase11");
    statisticConfigurationPage.clickGeneratePreviewChart();
    assertEquals(statisticConfigurationPage.getPreviewChartNumberLabel(), "Open");
    assertTrue(statisticConfigurationPage.getPreviewChartNumberValue() == 1);

    statisticConfigurationPage.clickCreateStatisticChart();

    // Add Custom statistic widget
    configurationPage.clickOnAddWidgetButton();
    StatisticWidgetNewDashboardPage openCasesWidget = configurationPage.addNewStatisticWidget("Custom statistic chart CASE");
    openCasesWidget.getAllChartLabels().first().text().equals("Open");
    openCasesWidget.getAllChartNumbers().shouldHave(CollectionCondition.size(1));
  }

  @Test
  public void testCreateCustomChartForTask() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToNewDashBoard();

    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.maximizeBrowser();
    configurationPage.clickOnAddWidgetButton();
    StatisticConfigurationPage statisticConfigurationPage = configurationPage.clickOnCreateCustomStatisticWidgetButton();
    statisticConfigurationPage.setChartName("Custom statistic chart TASK");
    // Chart target to TASK
    statisticConfigurationPage.changeChartTarget("Task");
    assertTrue(statisticConfigurationPage.getPermissions().size() == 2);

    statisticConfigurationPage.clickGeneratePreviewChart();
    statisticConfigurationPage.chartCanvasVisible();

    // BAR chart
    statisticConfigurationPage.changeChartType("Bar");
    statisticConfigurationPage.clickGeneratePreviewChart();
    // Assert default Group is State
    assertEquals(statisticConfigurationPage.getAggregationItems().size(), 9);
    // Change to Created date
    statisticConfigurationPage.changeGroupBy("Created date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Completed date
    statisticConfigurationPage.changeGroupBy("Completed date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Expiry date
    statisticConfigurationPage.changeGroupBy("Expiry date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Custom field
    statisticConfigurationPage.changeGroupBy("Custom field");
    assertTrue(statisticConfigurationPage.getCaseCustomFieldItems().size() > 0);
    statisticConfigurationPage.axisXYVisible();
    statisticConfigurationPage.autoRefeshToggleVisible();
    statisticConfigurationPage.backgroundColorVisible();

    // LINE chart
    statisticConfigurationPage.changeChartType("Line");
    statisticConfigurationPage.clickGeneratePreviewChart();
    // Assert default Group is State
    assertEquals(statisticConfigurationPage.getAggregationItems().size(), 9);
    // Change to Created date
    statisticConfigurationPage.changeGroupBy("Created date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Completed date
    statisticConfigurationPage.changeGroupBy("Completed date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Expiry date
    statisticConfigurationPage.changeGroupBy("Expiry date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Custom field
    statisticConfigurationPage.changeGroupBy("Custom field");
    assertTrue(statisticConfigurationPage.getCaseCustomFieldItems().size() > 0);
    statisticConfigurationPage.axisXYVisible();
    statisticConfigurationPage.autoRefeshToggleVisible();
    statisticConfigurationPage.backgroundColorVisible();

    // PIE chart
    statisticConfigurationPage.changeChartType("Pie");
    statisticConfigurationPage.clickGeneratePreviewChart();
    // Assert default Group is State
    assertEquals(statisticConfigurationPage.getAggregationItems().size(), 9);
    // Change to Created date
    statisticConfigurationPage.changeGroupBy("Created date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Completed date
    statisticConfigurationPage.changeGroupBy("Completed date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Expiry date
    statisticConfigurationPage.changeGroupBy("Expiry date");
    assertEquals(statisticConfigurationPage.getTimeIntervalItems().size(), 4);
    // Change to Custom field
    statisticConfigurationPage.changeGroupBy("Custom field");
    assertTrue(statisticConfigurationPage.getCaseCustomFieldItems().size() > 0);
    statisticConfigurationPage.autoRefeshToggleVisible();
    statisticConfigurationPage.backgroundColorVisible();

    // NUMBER chart
    statisticConfigurationPage.changeChartType("Number");
    // Change to State
    statisticConfigurationPage.changeGroupBy("State");
    assertEquals(statisticConfigurationPage.getAggregationItems().size(), 4);
    statisticConfigurationPage.autoRefeshToggleVisible();
    statisticConfigurationPage.hideLabelVisible();

    // Configure for Filter
    statisticConfigurationPage.changeChartType("Number");
    statisticConfigurationPage.changeGroupBy("State");

    // Filter State
    statisticConfigurationPage.addFilter("State", null);
    statisticConfigurationPage.inputValueOnLatestFilter(FilterValueType.STATE_TYPE, "OPEN");
    statisticConfigurationPage.clickGeneratePreviewChart();
    assertEquals(statisticConfigurationPage.getPreviewChartNumberLabel(), "Open");
    assertTrue(statisticConfigurationPage.getPreviewChartNumberValue() == 14);

    // Filter Category
    statisticConfigurationPage.addFilter("Category", null);
    statisticConfigurationPage.inputValueOnLatestFilter(FilterValueType.CATEGORY_TYPE, "TestCase11");
    statisticConfigurationPage.clickGeneratePreviewChart();
    assertEquals(statisticConfigurationPage.getPreviewChartNumberLabel(), "Open");
    assertTrue(statisticConfigurationPage.getPreviewChartNumberValue() == 1);

    statisticConfigurationPage.clickCreateStatisticChart();

    // Add Custom statistic widget
    configurationPage.clickOnAddWidgetButton();
    StatisticWidgetNewDashboardPage openTasksWidget = configurationPage.addNewStatisticWidget("Custom statistic chart TASK");
    openTasksWidget.getAllChartLabels().first().text().equals("Open");
    openTasksWidget.getAllChartNumbers().shouldHave(CollectionCondition.size(1));
  }
}
