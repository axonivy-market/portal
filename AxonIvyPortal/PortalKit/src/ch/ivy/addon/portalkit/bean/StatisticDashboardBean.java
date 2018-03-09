package ch.ivy.addon.portalkit.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.PieChartModel;

import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.ws.addon.CaseStateStatistic;
import ch.ivy.ws.addon.ElapsedTimeStatistic;
import ch.ivy.ws.addon.ExpiryStatistic;
import ch.ivy.ws.addon.PriorityStatistic;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class StatisticDashboardBean {
  private StatisticService statisticService = new StatisticService();
  private List<StatisticChart> statisticChartList;

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

  /**
   * Generate Task by Priority chart model from statistic chart data
   * 
   * @param statisticChart statistic chart
   * @return Task by Priority chart model
   */
  public DonutChartModel generateTaskByPriorityModel(StatisticChart statisticChart) {
    PriorityStatistic statisticData = statisticService.getPriorityStatisticData(statisticChart.getJsonQuery());
    return statisticService.generateTaskByPriorityModel(statisticData, true);
  }

  /**
   * Generate Task by Expiry chart model from statistic chart data
   * 
   * @param statisticChart statistic chart
   * @return Task by Expiry chart model
   */
  public BarChartModel generateTaskByExpiryModel(StatisticChart statisticChart) {
    List<ExpiryStatistic> statisticData = statisticService.getExpiryStatisticData(statisticChart.getJsonQuery());
    return statisticService.generateTaskByExpiryModel(statisticData, true);
  }

  /**
   * Generate Case by State chart model from statistic chart data
   * 
   * @param statisticChart statistic chart
   * @return Case by State chart model
   */
  public DonutChartModel generateCaseByStateModel(StatisticChart statisticChart) {
    CaseStateStatistic statisticData = statisticService.getCaseStateStatisticData(statisticChart.getJsonQuery());
    return statisticService.generateCaseByStateModel(statisticData, true);
  }

  /**
   * Generate Elapsed time by Case Category chart model from statistic chart data
   * 
   * @param statisticChart statistic chart
   * @return Elapsed time by Case Category chart model
   */
  public PieChartModel generateElapsedTimeModel(StatisticChart statisticChart) {
    List<ElapsedTimeStatistic> statisticData = statisticService.getElapsedTimeStatisticData(statisticChart.getJsonQuery());
    return statisticService.generateElapsedTimeModel(statisticData, true);
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
