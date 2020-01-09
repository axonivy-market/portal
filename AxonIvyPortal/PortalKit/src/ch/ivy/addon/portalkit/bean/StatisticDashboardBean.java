package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.donut.DonutChartModel;

import ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;

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

  private StatisticService statisticService = new StatisticService();

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

  public String concatCreatedDate(StatisticFilter filter) {
    StatisticTimePeriodSelection timeSelection = filter.getTimePeriodSelection();
    if (timeSelection != StatisticTimePeriodSelection.CUSTOM) {
      return timeSelection.getLabel();
    }

    Date createdDateForm = filter.getCreatedDateFrom();
    Date createdDateTo = filter.getCreatedDateTo();
    DateTimeGlobalSettingService service = new DateTimeGlobalSettingService();
    DateFormat formatter = new SimpleDateFormat(service.getDateTimePattern());

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

  public List<StatisticChart> generatePlaceholderForChart(List<StatisticChart> statisticChartList) {
    DonutChartModel donutChartModel = statisticService.createDonutChartPlaceholder();
    BarChartModel barChartModel = statisticService.createBarChartPlaceholder();
    for (StatisticChart statisticChart : statisticChartList) {
      switch (statisticChart.getType()) {
        case TASK_BY_PRIORITY:
        case CASES_BY_STATE:
        case CASES_BY_FINISHED_TASK:
        case CASES_BY_FINISHED_TIME:
          statisticChart.setDonutChartModel(donutChartModel);
          break;
        case TASK_BY_EXPIRY:
        case ELAPSED_TIME_BY_CASE_CATEGORY:
          statisticChart.setBarChartModel(barChartModel);
          break;
        default:
          break;
      }
    }

    return statisticChartList;
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
