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

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.StatisticDashboardWidget;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.statistics.StatisticChartQueryUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

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
    CaseQuery caseQuery = StatisticChartQueryUtils.getQueryForSelectedItemByCaseByState(event, selectedStatisticChart);
    IvyComponentLogicCaller<String> drilldownCaseByState = new IvyComponentLogicCaller<>();
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    drilldownCaseByState.invokeComponentLogic(componentId, "#{logic.drilldownCaseByState}",
        new Object[] {selectedStatisticChart, caseQuery});
  }

  private StatisticChart getSelectedStatisticChart(ItemSelectEvent event) {
    String selectedChartId = (String) event.getComponent().getAttributes().get("selectedChartId");
    for (StatisticChart chart : getAvailableCharts()) {
      if (StringUtils.equals(chart.getId(), selectedChartId)) {
        return chart;
      }
    }
    return null;
  }

  public void drilldownTaskByPriority(ItemSelectEvent event) {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    if (selectedStatisticChart == null) {
      return;
    }
    TaskQuery taskQuery =
        StatisticChartQueryUtils.getQueryForSelectedItemOfTaskByPriorityChart(event, selectedStatisticChart);

    String currentLanguage = LanguageService.newInstance().findUserLanguages().getIvyLanguage().getUserLanguage();
    String chartName = selectedStatisticChart.getNames().stream()
        .filter(name -> StatisticService.equalsDisplayNameLocale(name, currentLanguage)).map(DisplayName::getValue)
        .findFirst().orElse("");

    IvyComponentLogicCaller<String> drillDownTaskByPriority = new IvyComponentLogicCaller<>();
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    drillDownTaskByPriority.invokeComponentLogic(componentId, "#{logic.drilldownTaskByPriority}",
        new Object[] {chartName, taskQuery});
  }

  private static TaskQuery generateQueryForTaskByExpiry(ItemSelectEvent event, StatisticChart statisticChart) {
    String previousSelectedMonth = Attrs.currentContext().getAttribute("#{data.previousSelectedMonth}", String.class);
    String previousSelectedWeek = Attrs.currentContext().getAttribute("#{data.previousSelectedWeek}", String.class);
    String previousSelectedDay = Attrs.currentContext().getAttribute("#{data.previousSelectedDay}", String.class);
    return StatisticChartQueryUtils.getQueryForSelectedItemOfTaskByExpiryChart(event, statisticChart,
        previousSelectedMonth, previousSelectedWeek, previousSelectedDay);
  }

  public void toTaskByExpiryTaskList(ItemSelectEvent event) {
    String selectedItemOfDrilldown = StatisticService.getSelectedValueOfBarChart(event);
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    if (selectedStatisticChart == null) {
      return;
    }
    TaskQuery taskQuery = generateQueryForTaskByExpiry(event, selectedStatisticChart);
    IvyComponentLogicCaller<String> toTaskByExpiryTaskList = new IvyComponentLogicCaller<>();
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    toTaskByExpiryTaskList.invokeComponentLogic(componentId, "#{logic.toTaskByExpiryTaskList}",
        new Object[] {selectedItemOfDrilldown, selectedStatisticChart, taskQuery});
  }
}
