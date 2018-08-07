package ch.ivy.addon.portalkit.statistics;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

public class StatisticChartModel {

  private static final String COMMA = ",";
  private static final String EXCEPTION_PRIORITY_COLOR = "EC7979";
  private static final String HIGH_PRIORITY_COLOR = "EC9679";
  private static final String NORMAL_PRIORITY_COLOR = "E8E864";
  private static final String LOW_PRIORITY_COLOR = "8FEF8F";
  private final String EXCEPTION_PRIORITY_KEY = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/piechart/exception");
  private final String HIGH_PRIORITY_KEY = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/piechart/high");
  private final String NORMAL_PRIORITY_KEY = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/piechart/normal");
  private final String LOW_PRIORITY_KEY = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/piechart/low");

  private final List<RemoteTask> tasks;

  public StatisticChartModel(List<RemoteTask> tasks) {
    this.tasks = tasks;
  };

  public PieChartModel createPriorityPieChart(String lowPriorityColor, String normalPriorityColor,
      String highPriorityColor, String exceptionPriorityColor) {
    Map<String, Number> prioPieChartData = calculateDataForPrioPieChart();
    String seriesColors =
        generateSeriesColors(prioPieChartData, lowPriorityColor, normalPriorityColor, highPriorityColor,
            exceptionPriorityColor);

    PieChartModel priorityPieChartModel = new PieChartModel(prioPieChartData);
    priorityPieChartModel.setSeriesColors(seriesColors);
    priorityPieChartModel.setShowDatatip(true);
    priorityPieChartModel.setShowDataLabels(true);
    priorityPieChartModel.setExtender("chartExtender");
    String datatipFormat = "%s: %d " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/tasks");
    priorityPieChartModel.setDatatipFormat(datatipFormat);
    priorityPieChartModel.setDiameter(220);
    priorityPieChartModel.setLegendPosition("s");
    priorityPieChartModel.setLegendRows(1);
    priorityPieChartModel.setShadow(false);
    priorityPieChartModel.setSliceMargin(3);
    priorityPieChartModel.setTitle(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/tasksPriority"));
    return priorityPieChartModel;
  }

  private String generateSeriesColors(Map<String, Number> chartData, String lowPriorityColor,
      String normalPriorityColor, String highPriorityColor, String exceptionPriorityColor) {
    Number exceptionPriorities = chartData.get(EXCEPTION_PRIORITY_KEY);
    Number highPriorities = chartData.get(HIGH_PRIORITY_KEY);
    Number normalPriorities = chartData.get(NORMAL_PRIORITY_KEY);
    Number lowPriorities = chartData.get(LOW_PRIORITY_KEY);

    String seriesColors = StringUtils.EMPTY;
    if (validPriorityData(exceptionPriorities)) {
      seriesColors += (StringUtils.EMPTY.equals(exceptionPriorityColor) ? EXCEPTION_PRIORITY_COLOR : exceptionPriorityColor) + COMMA;
    }
    if (validPriorityData(highPriorities)) {
      seriesColors += (StringUtils.EMPTY.equals(highPriorityColor) ? HIGH_PRIORITY_COLOR : highPriorityColor) + COMMA;
    }
    if (validPriorityData(normalPriorities)) {
      seriesColors += (StringUtils.EMPTY.equals(normalPriorityColor) ? NORMAL_PRIORITY_COLOR : normalPriorityColor) + COMMA;
    }
    if (validPriorityData(lowPriorities)) {
      seriesColors += (StringUtils.EMPTY.equals(lowPriorityColor) ? LOW_PRIORITY_COLOR : lowPriorityColor);
    }
    if (seriesColors.endsWith(COMMA)) {
      seriesColors = seriesColors.substring(0, seriesColors.length() - 1);
    }
    return seriesColors;
  }

  private boolean validPriorityData(Number exceptionPriorities) {
    return exceptionPriorities != null && exceptionPriorities.intValue() > 0;
  }

  private Map<String, Number> calculateDataForPrioPieChart() {
    int exceptionPriorities = 0;
    int highPriorities = 0;
    int normalPriorities = 0;
    int lowPriorities = 0;

    Map<String, Number> chartData = new LinkedHashMap<String, Number>();
    for (RemoteTask task : tasks) {
      if (task.getPriority().compareTo(WorkflowPriority.EXCEPTION) == 0) {
        exceptionPriorities++;
      } else if (task.getPriority().compareTo(WorkflowPriority.HIGH) == 0) {
        highPriorities++;
      } else if (task.getPriority().compareTo(WorkflowPriority.NORMAL) == 0) {
        normalPriorities++;
      } else if (task.getPriority().compareTo(WorkflowPriority.LOW) == 0) {
        lowPriorities++;
      }
    }

    if (exceptionPriorities > 0) {
      chartData.put(EXCEPTION_PRIORITY_KEY, exceptionPriorities);
    }
    if (highPriorities > 0) {
      chartData.put(HIGH_PRIORITY_KEY, highPriorities);
    }

    if (normalPriorities > 0) {
      chartData.put(NORMAL_PRIORITY_KEY, normalPriorities);
    }

    if (lowPriorities > 0) {
      chartData.put(LOW_PRIORITY_KEY, lowPriorities);
    }

    return chartData;
  }

  public BarChartModel createExpiryTasksBarChart(String expiryBarColor) {
    Optional<ChartSeries> chartSeries = createExpiryChartSeries();
    BarChartModel barChartModel = new BarChartModel();
    if (chartSeries.isPresent()) {
      barChartModel.setTitle(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskExpiry"));
      barChartModel.setShadow(false);
      if (!StringUtils.EMPTY.equals(expiryBarColor)) {
        barChartModel.setSeriesColors(expiryBarColor);
      }
      barChartModel.setExtender("barChartExtender");
      String datatipFormat = "%2$.0f " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/tasks");
      barChartModel.setDatatipFormat(datatipFormat);
      Axis xAxis = barChartModel.getAxis(AxisType.X);
      xAxis.setLabel(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/expiryPeriod"));
      Axis yAxis = barChartModel.getAxis(AxisType.Y);
      yAxis.setLabel(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/tasks"));
      barChartModel.addSeries(chartSeries.get());
    }
    return barChartModel;
  }

  private Optional<ChartSeries> createExpiryChartSeries() {
    int numberOfExpiredTasksToday = 0;
    int numberOfExpiredTasksTomorrow = 0;
    int numberOfExpiredTasksNextTwoDays = 0;
    int numberOfExpiredTasksNextThreeDays = 0;

    LocalDate today = LocalDate.now();
    LocalDate tomorrow = today.plusDays(1);
    LocalDate nextTwoDays = today.plusDays(2);
    LocalDate nextThreeDays = today.plusDays(3);

    for (RemoteTask task : tasks) {
      Optional<Date> dateOptional = Optional.ofNullable(task.getExpiryTimestamp());
      if (!dateOptional.isPresent()) {
        continue;
      }
      LocalDate expiryDate = dateOptional.get().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      if (expiryDate.isAfter(nextThreeDays)) {
        continue;
      }
      if (expiryDate.isEqual(today)) {
        numberOfExpiredTasksToday++;
      } else if (expiryDate.isEqual(tomorrow)) {
        numberOfExpiredTasksTomorrow++;
      } else if (expiryDate.isEqual(nextTwoDays)) {
        numberOfExpiredTasksNextTwoDays++;
      } else if (expiryDate.isEqual(nextThreeDays)) {
        numberOfExpiredTasksNextThreeDays++;
      }
    }

    ChartSeries chartSeries = new ChartSeries();
    chartSeries.set(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/today"),
        numberOfExpiredTasksToday);
    chartSeries.set(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/tomorrow"),
        numberOfExpiredTasksTomorrow);
    chartSeries.set(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/in2Days"),
        numberOfExpiredTasksNextTwoDays);
    chartSeries.set(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/in3Days"),
        numberOfExpiredTasksNextThreeDays);

    Optional<ChartSeries> optionalCharSeries = Optional.ofNullable(chartSeries);
    return optionalCharSeries;
  }
}
