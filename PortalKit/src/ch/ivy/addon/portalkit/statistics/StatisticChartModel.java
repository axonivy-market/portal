package ch.ivy.addon.portalkit.statistics;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import ch.ivy.ws.addon.ExpiryStatistic;
import ch.ivy.ws.addon.PriorityStatistic;
import ch.ivyteam.ivy.environment.Ivy;

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

  private final PriorityStatistic priorityStatistic;
  private final ExpiryStatistic expiryStatistic;

  public StatisticChartModel(PriorityStatistic priorityStatistic, ExpiryStatistic expiryStatistic) {
    this.priorityStatistic = priorityStatistic;
    this.expiryStatistic = expiryStatistic;
  }

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
      seriesColors +=
          (StringUtils.EMPTY.equals(exceptionPriorityColor) ? EXCEPTION_PRIORITY_COLOR : exceptionPriorityColor)
              + COMMA;
    }
    if (validPriorityData(highPriorities)) {
      seriesColors += (StringUtils.EMPTY.equals(highPriorityColor) ? HIGH_PRIORITY_COLOR : highPriorityColor) + COMMA;
    }
    if (validPriorityData(normalPriorities)) {
      seriesColors +=
          (StringUtils.EMPTY.equals(normalPriorityColor) ? NORMAL_PRIORITY_COLOR : normalPriorityColor) + COMMA;
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
    Map<String, Number> chartData = new LinkedHashMap<String, Number>();

    if (priorityStatistic.getException() > 0) {
      chartData.put(EXCEPTION_PRIORITY_KEY, priorityStatistic.getException());
    }

    if (priorityStatistic.getHigh() > 0) {
      chartData.put(HIGH_PRIORITY_KEY, priorityStatistic.getHigh());
    }

    if (priorityStatistic.getNormal() > 0) {
      chartData.put(NORMAL_PRIORITY_KEY, priorityStatistic.getNormal());
    }

    if (priorityStatistic.getLow() > 0) {
      chartData.put(LOW_PRIORITY_KEY, priorityStatistic.getLow());
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
    ChartSeries chartSeries = new ChartSeries();
    chartSeries.set(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/today"),
        expiryStatistic.getToday());
    chartSeries.set(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/tomorrow"),
        expiryStatistic.getTomorrow());
    chartSeries.set(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/in2Days"),
        expiryStatistic.getIn2Days());
    chartSeries.set(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/in3Days"),
        expiryStatistic.getIn3Days());

    Optional<ChartSeries> optionalCharSeries = Optional.ofNullable(chartSeries);
    return optionalCharSeries;
  }
}
