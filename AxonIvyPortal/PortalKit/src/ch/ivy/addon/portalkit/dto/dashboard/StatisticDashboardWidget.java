package ch.ivy.addon.portalkit.dto.dashboard;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChart;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatisticDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 1803106705231907546L;
  private StatisticChart chart;

  public StatisticDashboardWidget() {}

  public StatisticDashboardWidget(StatisticDashboardWidget widget) {
    super(widget);
    chart = widget.getChart();
  }

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

  public void generateChartModel() {
    if (chart != null) {
      StatisticService.getInstance().generateChartModelForStatisticCharts(Arrays.asList(chart));
    }
  }

  public StatisticChart getChart() {
    return chart;
  }

  public void setChart(StatisticChart chart) {
    this.chart = chart;
  }
}
