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
import ch.ivyteam.ivy.cm.IContentManagementSystem;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Ivy.class)
public class StatisticChartModelTest {

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
    List<RemoteTask> remoteTasks = createTestData();
    StatisticChartModel statisticChartModel = new StatisticChartModel(remoteTasks);
    PieChartModel pieChartModel = statisticChartModel.createPriorityPieChart("0B6211", "F7D100", "D44200", "E53935");
    Map<String, Number> expectedData = new HashMap<String, Number>();
    expectedData.put("Exception", 1);
    expectedData.put("High", 1);
    expectedData.put("Low", 1);
    expectedData.put("Normal", 1);

    Assert.assertEquals(expectedData, pieChartModel.getData());
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
    List<RemoteTask> remoteTasks = createTestData();
    StatisticChartModel statisticChartModel = new StatisticChartModel(remoteTasks);
    BarChartModel barChartModel = statisticChartModel.createExpiryTasksBarChart("E53935");
    List<ChartSeries> chartSeries = barChartModel.getSeries();
    Assert.assertEquals(1, chartSeries.size());
    ChartSeries chart = chartSeries.get(0);
    Assert.assertEquals(chart.getData().get("Today"), 1);
    Assert.assertEquals(chart.getData().get("Tomorrow"), 1);
    Assert.assertEquals(chart.getData().get("In 2 days"), 1);
    Assert.assertEquals(chart.getData().get("In 3 days"), 1);
  }

  private List<RemoteTask> createTestData() {
    RemoteTask remoteTaskA = new RemoteTask();
    remoteTaskA.setName("Task A");
    remoteTaskA.setPriority(WorkflowPriority.EXCEPTION);
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
    calendar.setTime(new Date());
    calendar.add(Calendar.HOUR, 5);
    remoteTaskA.setExpiryTimestamp(calendar.getTime());

    RemoteTask remoteTaskB = new RemoteTask();
    remoteTaskB.setName("Task B");
    remoteTaskB.setPriority(WorkflowPriority.HIGH);
    calendar.add(Calendar.DATE, 1);
    remoteTaskB.setExpiryTimestamp(calendar.getTime());

    RemoteTask remoteTaskC = new RemoteTask();
    remoteTaskC.setName("Task C");
    remoteTaskC.setPriority(WorkflowPriority.LOW);
    calendar.add(Calendar.DATE, 1);
    remoteTaskC.setExpiryTimestamp(calendar.getTime());

    RemoteTask remoteTaskD = new RemoteTask();
    remoteTaskD.setName("Task D");
    remoteTaskD.setPriority(WorkflowPriority.NORMAL);
    calendar.add(Calendar.DATE, 1);
    remoteTaskD.setExpiryTimestamp(calendar.getTime());

    List<RemoteTask> remoteTasks = Arrays.asList(remoteTaskA, remoteTaskB, remoteTaskC, remoteTaskD);
    return remoteTasks;
  }
}
