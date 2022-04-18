package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.ItemSelectEvent;

import ch.ivy.addon.portalkit.dto.dashboard.StatisticDashboardWidget;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.statistics.StatisticChartDrilldownUtil;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
public class DashboardStatisticChartBean implements Serializable {

  private static final long serialVersionUID = -8491957877380766526L;
  private List<StatisticChart> availableCharts;
  private StatisticChart selectedChart;

  public void initChartConfiguration(StatisticDashboardWidget widget) {
    if (widget != null && widget.getChart() != null) {
      selectedChart = widget.getChart();
    } else {
      selectedChart = new StatisticChart();
      selectedChart.setType(StatisticChartType.TASK_BY_PRIORITY);
      selectedChart.setDonutChartModel(StatisticService.getInstance().createDonutChartPlaceholder());
    }
  }

  public void fetchStatisticCharts() {
    availableCharts = new ArrayList<>();
    availableCharts.addAll(StatisticService.getInstance().findStatisticCharts());
  }

  public void preview() {
    generateChartModel(selectedChart);
  }

  public void generateChartModel(StatisticChart chart) {
    if (chart != null) {
      StatisticService.getInstance().generateChartModelForStatisticCharts(Arrays.asList(chart));
    }
  }

  public boolean isCaseByFinishedTime(StatisticChart chart) {
    if (chart == null) {
      return false;
    }
    return StatisticService.getInstance().isCaseByFinishedTime(chart) && Objects.nonNull(chart.getDonutChartModel());
  }

  public boolean isCaseByFinishedTask(StatisticChart chart) {
    if (chart == null) {
      return false;
    }
    return StatisticService.getInstance().isCaseByFinishedTask(chart) && Objects.nonNull(chart.getDonutChartModel());
  }

  public boolean isElapsedTimeByCaseCategory(StatisticChart chart) {
    if (chart == null) {
      return false;
    }
    return StatisticService.getInstance().isElapsedTimeByCaseCategory(chart) && Objects.nonNull(chart.getBarChartModel());
  }

  public boolean isCaseByState(StatisticChart chart) {
    if (chart == null) {
      return false;
    }
    return StatisticService.getInstance().isCaseByState(chart) && Objects.nonNull(chart.getDonutChartModel());
  }

  public boolean isTaskByPriority(StatisticChart chart) {
    if (chart == null) {
      return false;
    }
    return StatisticService.getInstance().isTaskByPriority(chart) && Objects.nonNull(chart.getDonutChartModel());
  }

  public boolean isTaskByExpiry(StatisticChart chart) {
    if (chart == null) {
      return false;
    }
    return StatisticService.getInstance().isTaskByExpiry(chart) && Objects.nonNull(chart.getBarChartModel());
  }

  public List<StatisticChart> completeStatisticChart(String filter) {
    return getAvailableCharts().stream().filter(chart -> getDisplayChartName(chart).contains(filter))
        .collect(Collectors.toList());
  }

  public List<StatisticChart> getAvailableCharts() {
    if (CollectionUtils.isEmpty(availableCharts)) {
      fetchStatisticCharts();
    }
    return availableCharts;
  }

  public void setAvailableCharts(List<StatisticChart> availableCharts) {
    this.availableCharts = availableCharts;
  }

  public String getDisplayChartName(StatisticChart chart) {
    if (chart == null) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/noName");
    }
    var displayName = StatisticService.getInstance().getDisplayNameInUserLanguageForChart(chart);
    return Objects.isNull(displayName) ? chart.getName() : displayName.getValue();
  }

  public StatisticChart getSelectedChart() {
    return selectedChart;
  }

  public void setSelectedChart(StatisticChart selectedChart) {
    this.selectedChart = selectedChart;
  }

  public void drilldownCaseByState(ItemSelectEvent event) {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    if (selectedStatisticChart == null) {
      return;
    }
    StatisticChartDrilldownUtil.drilldownCaseByState(event, selectedStatisticChart);
  }

  private StatisticChart getSelectedStatisticChart(ItemSelectEvent event) {
    String selectedChartId = (String) event.getComponent().getAttributes().get("selectedChartId");
    return getAvailableCharts().stream()
        .filter(chart -> StringUtils.equals(chart.getId(), selectedChartId))
        .findFirst().orElse(null);
  }

  public void drilldownTaskByPriority(ItemSelectEvent event) {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    if (selectedStatisticChart == null) {
      return;
    }
    StatisticChartDrilldownUtil.drilldownTaskByPriority(event, selectedStatisticChart);
  }

  public void toTaskByExpiryTaskList(ItemSelectEvent event) {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    if (selectedStatisticChart == null) {
      return;
    }
    StatisticChartDrilldownUtil.toTaskByExpiryTaskList(event, selectedStatisticChart);
  }

}
