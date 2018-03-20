package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class StatisticDashboardBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final int YEAR_CHART_WIDTH = 750;
  private static final int MONTH_CHART_WIDTH = 500;
  private static final int WEEK_CHART_WIDTH = 600;
  private static final int DAY_CHART_WIDTH = 500;

  private StatisticService statisticService = new StatisticService();
  private List<StatisticChart> statisticChartList;

  public String getChartWidthStyle(List<StatisticChart> chartList) {
    List<String> chartIdSuffixes = new ArrayList<>();
    for (StatisticChart chart : chartList) {
      String chartId = chart.getId();
      if (chartId.contains("_")) {
        // chart with format: id + _ + suffix is lower level (month/week/day/hour) 
        // chart when drilldown
        chartIdSuffixes.add(chartId.substring(chartId.indexOf("_") + 1));
      }
    }
    int maxWidth = 0;
    for (String suffix : chartIdSuffixes) {
      if (statisticService.selectThisYear(suffix)) {
        if (maxWidth < YEAR_CHART_WIDTH) {
          maxWidth = YEAR_CHART_WIDTH;
        }
      } else if (statisticService.selectWeekOfMonth(suffix)) {
        if (maxWidth < WEEK_CHART_WIDTH) {
          maxWidth = WEEK_CHART_WIDTH;
        }
      } else if (statisticService.selectMonthOfYear(suffix)) {
        if (maxWidth < MONTH_CHART_WIDTH) {
          maxWidth = MONTH_CHART_WIDTH;
        }
      } else if (statisticService.selectDayOfWeek(suffix)) {
        if (maxWidth < DAY_CHART_WIDTH) {
          maxWidth = DAY_CHART_WIDTH;
        }
      }
    }
    if (maxWidth > 0) {
      return "width: " + maxWidth + "px";
    }
    return "";
  }

  public String getAdditionalStyleForChart(List<StatisticChart> chartList) {
    boolean isDrilldownModeOfExpiryChart = false;
    for (StatisticChart chart : chartList) {
      if (chart.getId().contains("_")) {
      //chart with format: id + _ + suffix is lower level (month/week/day/hour) chart when drill down
        isDrilldownModeOfExpiryChart = true;
        break;
      }
    }
    if (isDrilldownModeOfExpiryChart) {
      return "task-by-expiry-drilldown-mode";
    }
    return "";
  }

  public boolean shouldRenderArrows(List<StatisticChart> chartList) {
    for (StatisticChart chart : chartList) {
      if (chart.getId().contains("_")) {
        return false;
      }
    }
    return true;
  }

  @PostConstruct
  public void initialize() {
    statisticChartList = statisticService.findStatisticChartsByUserId(Ivy.session().getSessionUser().getId());
  }

  public List<StatisticChart> getStatisticChartList() {
    return statisticChartList;
  }

  public void setStatisticChartList(List<StatisticChart> statisticChartList) {
    this.statisticChartList = statisticChartList;
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
