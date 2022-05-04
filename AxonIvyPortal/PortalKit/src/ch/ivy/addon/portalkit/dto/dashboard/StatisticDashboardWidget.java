package ch.ivy.addon.portalkit.dto.dashboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.donut.DonutChartDataSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.statistics.StatisticChartConstants;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatisticDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 1803106705231907546L;
  private StatisticChart chart;
  @JsonIgnore
  private boolean isChartEmpty;

  public StatisticDashboardWidget() {}

  public StatisticDashboardWidget(StatisticDashboardWidget widget) {
    super(widget);
    chart = widget.getChart();
  }

  @JsonIgnore
  @Override
  public void resetWidgetFilters() {}

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.STATISTIC;
  }

  @Override
  public String getName() {
    if (chart != null) {
      var displayName = StatisticService.getInstance().getDisplayNameInUserLanguageForChart(chart);
      if (displayName != null) {
        return displayName.getValue();
      }
    }
    return super.getName();
  }

  @JsonIgnore
  public void generateChartModel() {
    if (chart != null) {
      StatisticService.getInstance().generateChartModelForStatisticCharts(Arrays.asList(chart));
      isChartEmpty = isChartDataSetEmpty();
    }
  }

  @JsonIgnore
  private boolean isChartDataSetEmpty() {
    if (chart == null) {
      return true;
    }
    var dataset = new ArrayList<Number>();
    var donutChart = chart.getDonutChartModel();
    var barChart = chart.getBarChartModel();
    if (donutChart != null && donutChart.getData().getDataSet().size() == 1) {
      var donutDataset = (DonutChartDataSet) donutChart.getData().getDataSet().get(0);
      dataset.addAll(donutDataset.getData().stream()
          .filter(data -> data.intValue() != 0)
          .collect(Collectors.toList()));
    } else if (barChart != null && barChart.getData().getDataSet().size() == 1) {
      var barDataset = (BarChartDataSet) barChart.getData().getDataSet().get(0);
      dataset.addAll(barDataset.getData().stream()
          .filter(data -> data.intValue() != 0)
          .collect(Collectors.toList()));
    }
    return CollectionUtils.isEmpty(dataset);
  }

  public StatisticChart getChart() {
    return chart;
  }

  public void setChart(StatisticChart chart) {
    this.chart = chart;
  }

  public boolean isChartEmpty() {
    return isChartEmpty;
  }

  public void setChartEmpty(boolean isChartEmpty) {
    this.isChartEmpty = isChartEmpty;
  }

  @JsonIgnore
  public String getEmptyChartIcon() {
    if (chart != null) {
      if (chart.getDonutChartModel() != null) {
        return StatisticChartConstants.DONUT_CHART_ICON;
      } else if (chart.getBarChartModel() != null) {
        return StatisticChartConstants.BAR_CHART_ICON;
      }
    }
    return StatisticChartConstants.DEFAULT_CHART_ICON;
  }
}
