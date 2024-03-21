package com.axonivy.portal.selenium.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class StatisticDashboardWidgetTest extends BaseTest {

  private static final String CHART_ID = "statistic_1";
  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testShowChartInfo() {
    login(TestAccount.ADMIN_USER);
    createJSonFile("dashboard-has-one-chart-example.json", PortalVariable.DASHBOARD.key);
    redirectToRelativeLink(createTestingTasksUrl);
    var chartWidget = newDashboardPage.selectStatisticWidget();
    var chartName = chartWidget.getChartName(CHART_ID);
    assertTrue(StringUtils.equalsIgnoreCase("Tasks by Priority", chartName));
    var infoIcon = chartWidget.getStatisticInfoIconOfChart(CHART_ID);
    infoIcon.should(Condition.appear);
    chartWidget.openStatisticInfoPanel(CHART_ID);
    var chartFilter = chartWidget.countFilterOfStatistic(CHART_ID);
    chartFilter.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(4));
  }

  @Test
  public void testEditStatisticChart() {
    login(TestAccount.ADMIN_USER);
    createJSonFile("dashboard-has-one-chart-example.json", PortalVariable.DASHBOARD.key);
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    var chartWidget = newDashboardDetailsEditPage.selectStatisticWidget();
    chartWidget.getEditIconOfChart(CHART_ID).shouldBe(Condition.visible, Condition.enabled);
    var editChartDialog = chartWidget.openEditStatisticWidgetDialog(CHART_ID);
    assertTrue(StringUtils.equalsIgnoreCase(editChartDialog, "Edit Widget Configuration"));
    chartWidget.clickOnCancelConfiguration();
  }

  private NewDashboardDetailsEditPage gotoEditPublicDashboardPage() {
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    return modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
  }

  @Test
  public void testShowChartCasesByCategoryInfo() {
    login(TestAccount.ADMIN_USER);
    createJSonFile("dashboard-has-chart-cases-by-category.json", PortalVariable.DASHBOARD.key);
    redirectToRelativeLink(createTestingTasksUrl);
    var chartWidget = newDashboardPage.selectStatisticWidget();
    var chartName = chartWidget.getChartName(CHART_ID);
    assertTrue(StringUtils.equalsIgnoreCase("Cases by Category", chartName));
    var infoIcon = chartWidget.getStatisticInfoIconOfChart(CHART_ID);
    infoIcon.should(Condition.appear);
    chartWidget.openStatisticInfoPanel(CHART_ID);
    var chartFilter = chartWidget.countFilterOfStatistic(CHART_ID);
    chartFilter.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(4));
  }

  @Test
  public void testEditCasesByCategoryChart() {
    login(TestAccount.ADMIN_USER);
    createJSonFile("dashboard-has-chart-cases-by-category.json", PortalVariable.DASHBOARD.key);
    redirectToRelativeLink(createTestingTasksUrl);
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    var chartWidget = newDashboardDetailsEditPage.selectStatisticWidget();
    chartWidget.getEditIconOfChart(CHART_ID).shouldBe(Condition.visible, Condition.enabled);
    var editChartDialog = chartWidget.openEditStatisticWidgetDialog(CHART_ID);
    assertTrue(StringUtils.equalsIgnoreCase(editChartDialog, "Edit Widget Configuration"));
    chartWidget.clickOnCancelConfiguration();
  }
}
