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
import ch.ivy.addon.portalkit.statistics.StatisticChartDrilldownUtils;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class StatisticDashboardBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final int YEAR_CHART_WIDTH = 750;
  private static final int MONTH_CHART_WIDTH = 500;
  private static final int WEEK_CHART_WIDTH = 600;
  private static final int DAY_CHART_WIDTH = 500;
  private static final String GREATER_EQUAL = ">= %s";
  private static final String LESS_EQUAL = "<= %s";
  private static final String DASH = "%s - %s";
  private StatisticService statisticService = StatisticService.getInstance();
  private ItemSelectEvent taskByExpiryItemSelectEvent;
  private ItemSelectEvent casesByCategoryItemSelectEvent;

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
    StatisticChartDrilldownUtils.drilldownTaskByPriority(event, selectedStatisticChart);
  }

  public void drilldownCaseByState(ItemSelectEvent event) {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    StatisticChartDrilldownUtils.drilldownCaseByState(event, selectedStatisticChart);
  }

  public void drilldownElapsedTime(ItemSelectEvent event) {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    StatisticChartDrilldownUtils.drilldownElapsedTime(event, selectedStatisticChart);
  }

  public void onSelectDrilldownTaskByExpiry(ItemSelectEvent event) {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    taskByExpiryItemSelectEvent = event;
    StatisticChartDrilldownUtils.onSelectDrilldownTaskByExpiry(event, selectedStatisticChart);
  }

  public void toTaskByExpiryTaskList(ItemSelectEvent event) {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    StatisticChartDrilldownUtils.toTaskByExpiryTaskList(event, selectedStatisticChart);
  }

  // It's used for "Go to task list" selection from Statistic Dashboard
  public void goToExpiriedTaskList() {
    toTaskByExpiryTaskList(taskByExpiryItemSelectEvent);
    releaseJSFEvent();
  }

  public void drilldownTaskByExpiry() {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(taskByExpiryItemSelectEvent);
    StatisticChartDrilldownUtils.drilldownTaskByExpiry(taskByExpiryItemSelectEvent, selectedStatisticChart);
    releaseJSFEvent();
  }
  
  public void drilldownCasesByCategory() {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(casesByCategoryItemSelectEvent);
    StatisticChartDrilldownUtils.drilldownCasesByCategory(casesByCategoryItemSelectEvent, selectedStatisticChart);
    releaseJSFEvent();
  }
  
  public void onSelectDrilldownCasesByCategory(ItemSelectEvent event) {
    StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
    casesByCategoryItemSelectEvent = event;
    StatisticChartDrilldownUtils.onSelectDrilldownCasesByCategory(event, selectedStatisticChart);
  }
  
 //It's used for "Go to case list" selection from Statistic Dashboard
 public void goToCasesByCategoryList() {
   toCasesByCategoryList(casesByCategoryItemSelectEvent);
   releaseJSFEvent();
 }
 
 public void toCasesByCategoryList(ItemSelectEvent event) {
   StatisticChart selectedStatisticChart = getSelectedStatisticChart(event);
   StatisticChartDrilldownUtils.toCasesByCategoryCaseList(event, selectedStatisticChart);
 }

  private void releaseJSFEvent() {
    // Do not store JSF Event in a JSF bean that is not bound to request scope
    this.taskByExpiryItemSelectEvent = null;
    this.casesByCategoryItemSelectEvent = null;
  }

  public StatisticChart createDefaultEmptyChart() {
    StatisticChart emptyChart = new StatisticChart();
    emptyChart.setNames(generateNamesForEmptyChart());
    emptyChart.setType(StatisticChartType.TASK_BY_PRIORITY);
    StatisticService service = StatisticService.getInstance();
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
  
  public boolean isCasesByCategory(StatisticChart statisticChart) {
    if (statisticChart == null) {
      return false;
    }
    return statisticService.isCasesByCategory(statisticChart);
  }

  public String getId(StatisticChart chart) {
    return chart.getId();
  }
}
