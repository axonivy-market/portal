package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.PieChartModel;

import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivy.ws.addon.CaseStateStatistic;
import ch.ivy.ws.addon.ElapsedTimeStatistic;
import ch.ivy.ws.addon.ExpiryStatistic;
import ch.ivy.ws.addon.PriorityStatistic;

@ManagedBean
@ViewScoped
public class StatisticChartCreationBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private DonutChartModel taskByPriorityModel;
  private DonutChartModel caseByStateModel;
  private BarChartModel taskByExpiryModel;
  private PieChartModel elapsedTimeModel;
  StatisticService statisticService = new StatisticService();

  public StatisticChartCreationBean() {
    updateChartModels(new StatisticFilter());
  }

  /**
   * Update chart models
   * 
   * @param filter
   */
  public void updateChartModels(StatisticFilter filter) {
    updateTaskByPriorityModel(filter);
    updateCaseByStateModel(filter);
    updateTaskByExpiryModel(filter);
    updateElapsedTimeByCaseCategory(filter);
  }

  /**
   * Create model for "Task by Priority" chart from given statistic filter
   * 
   * @param filter statistic filter
   * @return chart model
   */
  public void updateTaskByPriorityModel(StatisticFilter filter) {
    String jsonQuery = statisticService.generateTaskQuery(filter).asJson();
    PriorityStatistic result = statisticService.getPriorityStatisticData(jsonQuery);
    taskByPriorityModel = statisticService.generateTaskByPriorityModel(result, false);
  }

  /**
   * Create model for "Task by Expiry Date" chart from given statistic filter
   * 
   * @param filter statistic filter
   * @return chart model
   */
  public void updateTaskByExpiryModel(StatisticFilter filter) {
    String jsonQuery = statisticService.generateTaskQueryForExpiry(filter).asJson();
    List<ExpiryStatistic> result = statisticService.getExpiryStatisticData(jsonQuery);
    taskByExpiryModel = statisticService.generateTaskByExpiryModel(result, false, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
  }

  /**
   * Create model for "Case by State" chart from given statistic filter
   * 
   * @param filter statistic filter
   * @return chart model
   */
  public void updateCaseByStateModel(StatisticFilter filter) {
    String jsonQuery = statisticService.generateCaseQuery(filter, false).asJson();
    CaseStateStatistic result = statisticService.getCaseStateStatisticData(jsonQuery);
    caseByStateModel = statisticService.generateCaseByStateModel(result, false);
  }

  /**
   * Create model for "Elapsed time by Case Category" chart from given statistic filter
   * 
   * @param filter statistic filter
   * @return chart model
   */
  public void updateElapsedTimeByCaseCategory(StatisticFilter filter) {
    String jsonQuery = statisticService.generateCaseQuery(filter, true).asJson();
    List<ElapsedTimeStatistic> result = statisticService.getElapsedTimeStatisticData(jsonQuery);
    setElapsedTimeModel(statisticService.generateElapsedTimeModel(result, false));
  }

  public DonutChartModel getTaskByPriorityModel() {
    return taskByPriorityModel;
  }

  public void setTaskByPriorityModel(DonutChartModel taskByPriorityModel) {
    this.taskByPriorityModel = taskByPriorityModel;
  }

  public DonutChartModel getCaseByStateModel() {
    return caseByStateModel;
  }

  public void setCaseByStateModel(DonutChartModel caseByStateModel) {
    this.caseByStateModel = caseByStateModel;
  }

  public BarChartModel getTaskByExpiryModel() {
    return taskByExpiryModel;
  }

  public void setTaskByExpiryModel(BarChartModel taskByExpiryModel) {
    this.taskByExpiryModel = taskByExpiryModel;
  }

  public PieChartModel getElapsedTimeModel() {
    return elapsedTimeModel;
  }

  public void setElapsedTimeModel(PieChartModel elapsedTimeModel) {
    this.elapsedTimeModel = elapsedTimeModel;
  }
}