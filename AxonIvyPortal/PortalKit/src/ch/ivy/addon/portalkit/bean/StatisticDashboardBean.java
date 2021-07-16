package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.donut.DonutChartModel;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection;
import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.statistics.StatisticChartQueryUtils;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

@ManagedBean
@ViewScoped
public class StatisticDashboardBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final String STATISTIC_DASHBOARD_WIDGET_COMPONENT_ID = "statistics-widget:statistic-dashboard-widget";
  private static final int YEAR_CHART_WIDTH = 750;
  private static final int MONTH_CHART_WIDTH = 500;
  private static final int WEEK_CHART_WIDTH = 600;
  private static final int DAY_CHART_WIDTH = 500;
  private static final String GREATER_EQUAL = ">= %s";
  private static final String LESS_EQUAL = "<= %s";
  private static final String DASH = "%s - %s";
  private StatisticService statisticService = new StatisticService();
  private ItemSelectEvent taskByExpiryItemSelectEvent;


  public String getChartWidthStyle(List<StatisticChart> chartList) {
    List<String> chartIdSuffixes = new ArrayList<>();
    for (StatisticChart chart : chartList) {
      String chartId = chart.getId();
      if (chartId.contains("_")) {
        // chart with format: id + _ + suffix is lower level (month/week/day/hour)
        // chart when drilldown
        chartIdSuffixes.add(chartId.substring(chartId.indexOf('_') + 1));
      }
    }
    int maxWidth = 0;
    for (String suffix : chartIdSuffixes) {
      if (StatisticService.selectThisYear(suffix)) {
        maxWidth = Math.max(maxWidth, YEAR_CHART_WIDTH);
      } else if (StatisticService.selectWeekOfMonth(suffix)) {
        maxWidth = Math.max(maxWidth, WEEK_CHART_WIDTH);
      } else if (StatisticService.selectMonthOfYear(suffix)) {
        maxWidth = Math.max(maxWidth, MONTH_CHART_WIDTH);
      } else if (StatisticService.selectDayOfWeek(suffix)) {
        maxWidth = Math.max(maxWidth, DAY_CHART_WIDTH);
      }
    }
    if (maxWidth > 0) {
      return String.format("width: %spx", maxWidth);
    }
    return "";
  }

  public void drilldownTaskByPriority(ItemSelectEvent event) {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    TaskQuery taskQuery =
        StatisticChartQueryUtils.getQueryForSelectedItemOfTaskByPriorityChart(event, selectedStatisticChart);

    String currentLanguage = LanguageService.newInstance().findUserLanguages().getIvyLanguage().getUserLanguage();
    String chartName = selectedStatisticChart.getNames().stream()
        .filter(name -> StatisticService.equalsDisplayNameLocale(name, currentLanguage))
        .map(DisplayName::getValue)
        .findFirst().orElse("");

    IvyComponentLogicCaller<String> drillDownTaskByPriority = new IvyComponentLogicCaller<>();
    drillDownTaskByPriority.invokeComponentLogic(STATISTIC_DASHBOARD_WIDGET_COMPONENT_ID,
        "#{logic.drilldownTaskByPriority}", new Object[] {chartName, taskQuery});
  }

  public void drilldownCaseByState(ItemSelectEvent event) {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    CaseQuery caseQuery = StatisticChartQueryUtils.getQueryForSelectedItemByCaseByState(event, selectedStatisticChart);
    IvyComponentLogicCaller<String> drilldownCaseByState = new IvyComponentLogicCaller<>();
    drilldownCaseByState.invokeComponentLogic(STATISTIC_DASHBOARD_WIDGET_COMPONENT_ID, "#{logic.drilldownCaseByState}",
        new Object[] {selectedStatisticChart, caseQuery});
  }

  public void drilldownElapsedTime(ItemSelectEvent event) {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    String selectedCaseCategory = StatisticService.getSelectedValueOfBarChart(event);
    IvyComponentLogicCaller<String> drilldownElapsedTime = new IvyComponentLogicCaller<>();
    drilldownElapsedTime.invokeComponentLogic(STATISTIC_DASHBOARD_WIDGET_COMPONENT_ID, "#{logic.drilldownElapsedTime}",
        new Object[] {selectedStatisticChart, selectedCaseCategory});
  }

  public void onSelectDrilldownTaskByExpiry(ItemSelectEvent event) {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    String selectedDrilldownItem = StatisticService.getSelectedValueOfBarChart(event);
    Boolean isDrilldownToTaskList = StatisticService.selectHourOfDay(selectedDrilldownItem);
    String selectedItemOfDrilldown = StatisticService.getSelectedValueOfBarChart(event);

    TaskQuery taskQuery = generateQueryForTaskByExpiry(event, selectedStatisticChart);
    taskByExpiryItemSelectEvent = event;
    IvyComponentLogicCaller<String> onSelectDrilldownTaskByExpiry = new IvyComponentLogicCaller<>();
    onSelectDrilldownTaskByExpiry.invokeComponentLogic(STATISTIC_DASHBOARD_WIDGET_COMPONENT_ID,
        "#{logic.onSelectDrilldownTaskByExpiry}",
        new Object[] {isDrilldownToTaskList, selectedItemOfDrilldown, selectedStatisticChart, taskQuery});
  }

  public void toTaskByExpiryTaskList(ItemSelectEvent event) {
    String selectedItemOfDrilldown = StatisticService.getSelectedValueOfBarChart(event);
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    TaskQuery taskQuery = generateQueryForTaskByExpiry(event, selectedStatisticChart);
    IvyComponentLogicCaller<String> toTaskByExpiryTaskList = new IvyComponentLogicCaller<>();
    toTaskByExpiryTaskList.invokeComponentLogic(STATISTIC_DASHBOARD_WIDGET_COMPONENT_ID,
        "#{logic.toTaskByExpiryTaskList}", new Object[] {selectedItemOfDrilldown, selectedStatisticChart, taskQuery});
  }

  // It's used for "Go to task list" selection from Statistic Dashboard
  public void goToExpiriedTaskList() {
    toTaskByExpiryTaskList(taskByExpiryItemSelectEvent);
    releaseJSFEvent();
  }

  public void drilldownTaskByExpiry() {
    String selectedItemOfDrilldown = StatisticService.getSelectedValueOfBarChart(taskByExpiryItemSelectEvent);
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(taskByExpiryItemSelectEvent);

    IvyComponentLogicCaller<String> drilldownTaskByExpiry = new IvyComponentLogicCaller<>();
    drilldownTaskByExpiry.invokeComponentLogic(STATISTIC_DASHBOARD_WIDGET_COMPONENT_ID,
        "#{logic.drilldownTaskByExpiry}", new Object[] {selectedStatisticChart, selectedItemOfDrilldown});
    releaseJSFEvent();
  }

  private void releaseJSFEvent() {
    // Do not store JSF Event in a JSF bean that is not bound to request scope
    this.taskByExpiryItemSelectEvent = null;
  }
  
  public StatisticChart createDefaultEmptyChart() {
    StatisticChart emptyChart = new StatisticChart();
    emptyChart.setNames(generateNamesForEmptyChart());
    emptyChart.setType(StatisticChartType.TASK_BY_PRIORITY);
    StatisticService service = new StatisticService();
    DonutChartModel model = service.createDonutChartPlaceholder();
    model.getOptions().getTitle().setText(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/emptystate/defaultEmptyMessages"));
    emptyChart.setDonutChartModel(model);
    return emptyChart;
  }

  private List<DisplayName> generateNamesForEmptyChart() {
    List<DisplayName> namesForEmptyChart = new ArrayList<>();
    IvyLanguage ivyLanguage = LanguageService.newInstance().findUserLanguages().getIvyLanguage();
    for (String language : ivyLanguage.getSupportedLanguages()) {
      DisplayName name = new DisplayName();
      name.setLocale(Locale.forLanguageTag(language));
      name.setValue(Ivy.cms().coLocale(("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/statistics"), language));
      namesForEmptyChart.add(name);
    }
    return namesForEmptyChart;
  }

  private static StatisticChart getSelectedStatisticChart(ItemSelectEvent event) {
    String selectedChartId = (String) event.getComponent().getAttributes().get("selectedChartId");
    List<StatisticChart> statisticCharts =
        Attrs.currentContext().getAttribute("#{data.statisticChartList}", List.class);
    for (StatisticChart chart : statisticCharts) {
      if (chart.getId() == selectedChartId) {
        return chart;
      }
    }
    return null;
  }

  private static TaskQuery generateQueryForTaskByExpiry(ItemSelectEvent event, StatisticChart statisticChart) {
    String previousSelectedMonth = Attrs.currentContext().getAttribute("#{data.previousSelectedMonth}", String.class);
    String previousSelectedWeek = Attrs.currentContext().getAttribute("#{data.previousSelectedWeek}", String.class);
    String previousSelectedDay = Attrs.currentContext().getAttribute("#{data.previousSelectedDay}", String.class);
    return StatisticChartQueryUtils.getQueryForSelectedItemOfTaskByExpiryChart(event, statisticChart,
        previousSelectedMonth, previousSelectedWeek, previousSelectedDay);
  }

  public String concatCreatedDate(StatisticFilter filter) {
    StatisticTimePeriodSelection timeSelection = filter.getTimePeriodSelection();
    if (timeSelection != StatisticTimePeriodSelection.CUSTOM) {
      return timeSelection.getLabel();
    }

    Date createdDateForm = filter.getCreatedDateFrom();
    Date createdDateTo = filter.getCreatedDateTo();
    String pattern = DateTimeGlobalSettingService.getInstance().getDateTimePattern();
    DateFormat formatter = new SimpleDateFormat(pattern, Ivy.session().getContentLocale());

    if (createdDateForm != null && createdDateTo != null) {
      return String.format(DASH, formatter.format(createdDateForm), formatter.format(createdDateTo));
    }

    if (createdDateForm != null) {
      return String.format(GREATER_EQUAL, formatter.format(createdDateForm));
    }

    if (createdDateTo != null) {
      return String.format(LESS_EQUAL, formatter.format(createdDateTo));
    }

    return StringUtils.EMPTY;
  }

  public boolean isChartModelNotInitialized(List<StatisticChart> statisticChartList) {
    for (StatisticChart statisticChart : statisticChartList) {
      if (statisticChart.getBarChartModel() == null && statisticChart.getDonutChartModel() == null) {
        return true;
      }
    }
    return false;
  }

  public boolean isTaskByPriority(StatisticChart statisticChart) {
    if (statisticChart == null) {
      return false;
    }
    return statisticService.isTaskByPriority(statisticChart);
  }

  public boolean isTaskByExpiry(StatisticChart statisticChart) {
    if (statisticChart == null) {
      return false;
    }
    return statisticService.isTaskByExpiry(statisticChart);
  }

  public boolean isTaskByExpiryHour(StatisticChart statisticChart) {
    if (statisticChart == null) {
      return false;
    }
    return statisticService.isTaskByExpiryHour(statisticChart);
  }

  public boolean isCaseByState(StatisticChart statisticChart) {
    if (statisticChart == null) {
      return false;
    }
    return statisticService.isCaseByState(statisticChart);
  }

  public boolean isCaseByFinishedTask(StatisticChart statisticChart) {
    if (statisticChart == null) {
      return false;
    }
    return statisticService.isCaseByFinishedTask(statisticChart);
  }

  public boolean isCaseByFinishedTime(StatisticChart statisticChart) {
    if (statisticChart == null) {
      return false;
    }
    return statisticService.isCaseByFinishedTime(statisticChart);
  }

  public boolean isElapsedTimeByCaseCategory(StatisticChart statisticChart) {
    if (statisticChart == null) {
      return false;
    }
    return statisticService.isElapsedTimeByCaseCategory(statisticChart);
  }

  public String getId(StatisticChart chart) {
    return chart.getId();
  }
}
