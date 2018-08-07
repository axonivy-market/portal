package ch.ivy.addon.portalkit.statistic;

import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.statistics.StatisticChartModel;
import ch.ivy.ws.addon.ExpiryStatistic;
import ch.ivy.ws.addon.PriorityStatistic;
import ch.ivyteam.ivy.cm.IContentManagementSystem;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Ivy.class)
public class StatisticChartModelTest {

  private PriorityStatistic priorityStatistic;
  private ExpiryStatistic expiryStatistic;
  
  @Test
  public void testCreatePriorityPieChart() {
    PowerMockito.mockStatic(Ivy.class);
    IContentManagementSystem managementSystem = PowerMockito.mock(IContentManagementSystem.class);
    PowerMockito.when(Ivy.cms()).thenReturn(managementSystem);
    PowerMockito.when(managementSystem.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskPriorityRatio"))
        .thenReturn(StringUtils.EMPTY);
    PowerMockito.when(managementSystem.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/piechart/exception"))
        .thenReturn("Exception");
    PowerMockito.when(managementSystem.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/piechart/high"))
        .thenReturn("High");
    PowerMockito.when(managementSystem.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/piechart/low"))
        .thenReturn("Low");
    PowerMockito.when(managementSystem.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/piechart/normal"))
        .thenReturn("Normal");
    
    createTestData();
    StatisticChartModel statisticChartModel = new StatisticChartModel(priorityStatistic, expiryStatistic);
    PieChartModel pieChartModel = statisticChartModel.createPriorityPieChart("0B6211", "F7D100", "D44200", "E53935");

    Assert.assertEquals(1, Long.parseLong(pieChartModel.getData().get("Low").toString()));
    Assert.assertEquals(1, Long.parseLong(pieChartModel.getData().get("Normal").toString()));
    Assert.assertEquals(1, Long.parseLong(pieChartModel.getData().get("High").toString()));
    Assert.assertEquals(1, Long.parseLong(pieChartModel.getData().get("Exception").toString()));
  }

  @Test
  public void testCreateExpiryTasksBarChart() {
    PowerMockito.mockStatic(Ivy.class);
    IContentManagementSystem managementSystem = PowerMockito.mock(IContentManagementSystem.class);
    PowerMockito.when(Ivy.cms()).thenReturn(managementSystem);
    PowerMockito.when(managementSystem.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/expiryPeriod")).thenReturn(
        StringUtils.EMPTY);
    PowerMockito.when(managementSystem.co("/ch.ivy.addon.portalkit.ui.jsf/common/tasks")).thenReturn(StringUtils.EMPTY);
    PowerMockito.when(managementSystem.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskExpiry")).thenReturn(
        StringUtils.EMPTY);
    PowerMockito.when(managementSystem.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/today"))
        .thenReturn("Today");
    PowerMockito.when(managementSystem.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/tomorrow"))
        .thenReturn("Tomorrow");
    PowerMockito.when(managementSystem.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/in2Days"))
        .thenReturn("In 2 days");
    PowerMockito.when(managementSystem.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/in3Days"))
        .thenReturn("In 3 days");
    createTestData();
    StatisticChartModel statisticChartModel = new StatisticChartModel(priorityStatistic, expiryStatistic);
    BarChartModel barChartModel = statisticChartModel.createExpiryTasksBarChart("E53935");
    List<ChartSeries> chartSeries = barChartModel.getSeries();
    Assert.assertEquals(1, chartSeries.size());
    ChartSeries chart = chartSeries.get(0);
    Assert.assertEquals(Long.parseLong(chart.getData().get("Today").toString()), 1);
    Assert.assertEquals(Long.parseLong(chart.getData().get("Tomorrow").toString()), 1);
    Assert.assertEquals(Long.parseLong(chart.getData().get("In 2 days").toString()), 1);
    Assert.assertEquals(Long.parseLong(chart.getData().get("In 3 days").toString()), 1);
  }

  private void createTestData() {
    priorityStatistic = new PriorityStatistic();
    priorityStatistic.setLow(1);
    priorityStatistic.setNormal(1);
    priorityStatistic.setHigh(1);
    priorityStatistic.setException(1);
    
    expiryStatistic = new ExpiryStatistic();
    expiryStatistic.setToday(1);
    expiryStatistic.setTomorrow(1);
    expiryStatistic.setIn2Days(1);
    expiryStatistic.setIn3Days(1);
  }
}
