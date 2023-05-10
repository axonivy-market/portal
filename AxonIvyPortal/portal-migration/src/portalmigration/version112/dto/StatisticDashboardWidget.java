package portalmigration.version112.dto;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.donut.DonutChartDataSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import portalmigration.version112.enums.DashboardWidgetType;
import portalmigration.version112.statistics.StatisticChart;

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

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.STATISTIC;
  }

  @Override
  public String getName() {
    return super.getName();
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
      if(barDataset.getData() != null) {
        dataset.addAll(barDataset.getData().stream()
                .filter(data -> data.intValue() != 0)
                .collect(Collectors.toList())); 
      }      
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
}
