package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.DonutChartModel;

import ch.ivy.addon.portalkit.bo.RemoteRole;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChartQueryUtils;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivy.ws.addon.CaseStateStatistic;
import ch.ivy.ws.addon.CategoryData;
import ch.ivy.ws.addon.ElapsedTimeStatistic;
import ch.ivy.ws.addon.ExpiryStatistic;
import ch.ivy.ws.addon.PriorityStatistic;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

@ManagedBean
@ViewScoped
public class StatisticChartCreationBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private DonutChartModel taskByPriorityModel;
  private DonutChartModel caseByStateModel;
  private DonutChartModel caseByFinishedTimeModel;
  private DonutChartModel caseByFinishedTaskModel;
  private BarChartModel taskByExpiryModel;
  private DonutChartModel elapsedTimeModel;
  StatisticService statisticService = new StatisticService();

  public StatisticChartCreationBean() {}

  /**
   * Update chart models
   * 
   * @param filter
   * @param oldFilter 
   */
  public void updateChartModels(StatisticFilter filter, StatisticFilter oldFilter) {
    if(filter.getTimePeriodSelection() != StatisticTimePeriodSelection.CUSTOM) {
      filter.setCreatedDateFrom(null);
      filter.setCreatedDateTo(null);
    }
    updateTaskByPriorityModel(filter);
    updateCaseByStateModel(filter);
    updateTaskByExpiryModel(filter);
    updateElapsedTimeByCaseCategory(filter);
    updateCaseByFinishedTaskModel(filter);
    updateCaseByFinishedTimeModel(filter);
    if (oldFilter != null) {
      setOldFiltersToCurrentValues(filter, oldFilter);
    }
  }

  private void setOldFiltersToCurrentValues(StatisticFilter filter, StatisticFilter oldFilter) {
    oldFilter.setTimePeriodSelection(filter.getTimePeriodSelection());
    Date createdDateFrom = filter.getCreatedDateFrom();
    Date createdDateTo = filter.getCreatedDateTo();
    oldFilter.setCreatedDateFrom(createdDateFrom == null ? null : new Date(createdDateFrom.getTime()));
    oldFilter.setCreatedDateTo(createdDateTo == null ? null : new Date(createdDateTo.getTime()));
    oldFilter.setIsAllCaseStatesSelected(filter.getIsAllCaseStatesSelected());
    oldFilter.setIsAllCategoriesSelected(filter.getIsAllCategoriesSelected());
    oldFilter.setIsAllRolesSelected(filter.getIsAllRolesSelected());
    oldFilter.setIsAllTaskPrioritiesSelected(filter.getIsAllTaskPrioritiesSelected());
    if (!filter.getIsAllCategoriesSelected()) {
      updateOldListToNewList(oldFilter.getSelectedCaseCategories(), filter.getSelectedCaseCategories());
    }
    if (!filter.getIsAllRolesSelected()) {
      updateOldListToNewList(oldFilter.getSelectedRoles(), filter.getSelectedRoles());
    }
    if (!filter.getIsAllCaseStatesSelected()) {
      updateOldListToNewList(oldFilter.getSelectedCaseStates(), filter.getSelectedCaseStates());
    }
    if (!filter.getIsAllTaskPrioritiesSelected()) {
      updateOldListToNewList(oldFilter.getSelectedTaskPriorities(), filter.getSelectedTaskPriorities());
    }
  }

  public boolean checkIfAnyFilterChanges(StatisticFilter filter, StatisticFilter oldFilter) {
    if (oldFilter.getTimePeriodSelection() != filter.getTimePeriodSelection()) {
      return true;
    }
    if (isDateChanged(oldFilter.getCreatedDateFrom(), filter.getCreatedDateFrom())) {
      return true;
    }
    if (isDateChanged(oldFilter.getCreatedDateTo(), filter.getCreatedDateTo())) {
      return true;
    }
    if (checkIfAnyCaseStateChanged(filter, oldFilter)) {
      return true;
    }
    if (checkIfAnyTaskPriorityChanged(filter, oldFilter)) {
      return true;
    }
    if (checkIfAnyCaseCategoryChanged(filter, oldFilter)) {
      return true;
    }
    if (checkIfAnyRoleChanged(filter, oldFilter)) {
      return true;
    }
    return false;
  }

  private boolean checkIfAnyCaseStateChanged(StatisticFilter filter, StatisticFilter oldFilter) {
    //compare check box select all of case states
    if (oldFilter.getIsAllCaseStatesSelected() != filter.getIsAllCaseStatesSelected()) {
      return true;
    }
    //compare other check box of case states if select all is not checked
    return !filter.getIsAllCaseStatesSelected() && !oldFilter.getSelectedCaseStates().equals(filter.getSelectedCaseStates());
  }

  private boolean checkIfAnyTaskPriorityChanged(StatisticFilter filter, StatisticFilter oldFilter) {
    //compare check box select all of task priorities
    if (oldFilter.getIsAllTaskPrioritiesSelected() != filter.getIsAllTaskPrioritiesSelected()) {
      return true;
    }
    //compare other check box of task priorities if select all is not checked
    return !filter.getIsAllTaskPrioritiesSelected() && !oldFilter.getSelectedTaskPriorities().equals(filter.getSelectedTaskPriorities());
  }

  private boolean checkIfAnyCaseCategoryChanged(StatisticFilter filter, StatisticFilter oldFilter) {
    //compare check box select all of case categories
    if (oldFilter.getIsAllCategoriesSelected() != filter.getIsAllCategoriesSelected()) {
      return true;
    }
    //compare other check box of case categories if select all is not checked
    return !filter.getIsAllCategoriesSelected() && !oldFilter.getSelectedCaseCategories().equals(filter.getSelectedCaseCategories());
  }

  private boolean checkIfAnyRoleChanged(StatisticFilter filter, StatisticFilter oldFilter) {
    //compare check box select all of roles
    if (oldFilter.getIsAllRolesSelected() != filter.getIsAllRolesSelected()) {
      return true;
    }
    //compare other check box of roles if select all is not checked
    return !filter.getIsAllRolesSelected() && !oldFilter.getSelectedRoles().equals(filter.getSelectedRoles());
  }

  private boolean isDateChanged(Date oldDate, Date currentDate) {
    if (oldDate == null) {
      return currentDate != null;
    }
    if (currentDate == null) {
      return true;
    }
    return oldDate.compareTo(currentDate) != 0;
  }

  public void updateCaseCategoriesCheckboxes(StatisticFilter filter) {
    List<String> selectedCaseCategories = filter.getSelectedCaseCategories();
    if (filter.getIsAllCategoriesSelected()) {
      for (String category : filter.getCaseCategories().stream().map(CategoryData::getRawPath).collect(Collectors.toList())) {
        addToListIfNotExist(selectedCaseCategories, category);
      }
    } else {
      selectedCaseCategories.clear();
    }
  }

  public void updateRolesCheckboxes(StatisticFilter filter) {
    List<String> selectedRoles = filter.getSelectedRoles();
    if (filter.getIsAllRolesSelected()) {
      for (Object role : filter.getRoles()) {
        if (role instanceof IUser) {
          IUser user = (IUser)role;
          addToListIfNotExist(selectedRoles, user.getMemberName());
        } else if (role instanceof RemoteRole) {
          RemoteRole remoteRole = (RemoteRole)role;
          addToListIfNotExist(selectedRoles, remoteRole.getMemberName());
        }
      }
    } else {
      selectedRoles.clear();
    }
  }

  public void updateCaseStatesCheckboxes(StatisticFilter filter) {
    List<CaseState> selectedCaseStates = filter.getSelectedCaseStates();
    if (filter.getIsAllCaseStatesSelected()) {
      for (CaseState state : filter.getCaseStates()) {
        addToListIfNotExist(selectedCaseStates, state);
      }
    } else {
      selectedCaseStates.clear();
    }
  }

  public void updateTaskPrioritiesCheckboxes(StatisticFilter filter) {
    List<WorkflowPriority> selectedTaskPriorities = filter.getSelectedTaskPriorities();
    if (filter.getIsAllTaskPrioritiesSelected()) {
      for (WorkflowPriority priority : filter.getTaskPriorities()) {
        addToListIfNotExist(selectedTaskPriorities, priority);
      }
    } else {
      selectedTaskPriorities.clear();
    }
  }

  private <T> void updateOldListToNewList(List<T> oldList, List<T> newList) {
    oldList.clear();
    if (!newList.isEmpty()) {
      oldList.addAll(newList);
    }
  }

  private <T> void addToListIfNotExist(List<T> list, T element) {
    if (!list.contains(element)) {
      list.add(element);
    }
  }

  /**
   * Create model for "Task by Priority" chart from given statistic filter
   * 
   * @param filter statistic filter
   */
  public void updateTaskByPriorityModel(StatisticFilter filter) {
    String jsonQuery = StatisticChartQueryUtils.generateTaskQuery(filter).asJson();
    PriorityStatistic priorityStatisticData = statisticService.getPriorityStatisticData(jsonQuery);
    taskByPriorityModel = statisticService.generateTaskByPriorityModel(priorityStatisticData, false);
  }

  /**
   * Create model for "Task by Expiry Date" chart from given statistic filter
   * 
   * @param filter statistic filter
   */
  public void updateTaskByExpiryModel(StatisticFilter filter) {
    String jsonQuery = StatisticChartQueryUtils.generateTaskQueryForExpiry(filter).asJson();
    List<ExpiryStatistic> expiryStatisticData = statisticService.getExpiryStatisticData(jsonQuery);
    taskByExpiryModel = statisticService.generateTaskByExpiryModel(expiryStatisticData, false, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
  }

  /**
   * Create model for "Case by State" chart from given statistic filter
   * 
   * @param filter statistic filter
   */
  public void updateCaseByStateModel(StatisticFilter filter) {
    String jsonQuery = StatisticChartQueryUtils.generateCaseQuery(filter, false).asJson();
    CaseStateStatistic caseStateStatisticData = statisticService.getCaseStateStatisticData(jsonQuery);
    caseByStateModel = statisticService.generateCaseByStateModel(caseStateStatisticData,StatisticChartType.CASES_BY_STATE, false);
  }
  
  /**
   * Create model for "Case by Finished task" chart from given statistic filter
   * @param filter
   */
  public void updateCaseByFinishedTaskModel(StatisticFilter filter) {
    String jsonQuery = StatisticChartQueryUtils.generateCaseQueryForCaseHaveFinishedTask(filter).asJson();
    CaseStateStatistic caseStateStatisticData = statisticService.getCaseStateStatisticData(jsonQuery);
    caseByFinishedTaskModel = statisticService.generateCaseByStateModel(caseStateStatisticData, StatisticChartType.CASES_BY_FINISHED_TASK, false);
  }
  
  /**
   * Create model for "Case by Finished time" chart from given statistic filter
   * @param filter
   */
  public void updateCaseByFinishedTimeModel(StatisticFilter filter) {
    String jsonQuery = StatisticChartQueryUtils.generateCaseQueryByFinishedTime(filter).asJson();
    CaseStateStatistic caseStateStatisticData = statisticService.getCaseStateStatisticData(jsonQuery);
    caseByFinishedTimeModel = statisticService.generateCaseByStateModel(caseStateStatisticData, StatisticChartType.CASES_BY_FINISHED_TIME, false);
  }

  /**
   * Create model for "Elapsed time by Case Category" chart from given statistic filter
   * 
   * @param filter statistic filter
   */
  public void updateElapsedTimeByCaseCategory(StatisticFilter filter) {
    String jsonQuery = StatisticChartQueryUtils.generateCaseQuery(filter, true).asJson();
    List<ElapsedTimeStatistic> elapsedTimeStatisticData = statisticService.getElapsedTimeStatisticData(jsonQuery);
    setElapsedTimeModel(statisticService.generateElapsedTimeModel(elapsedTimeStatisticData, false));
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

  public DonutChartModel getElapsedTimeModel() {
    return elapsedTimeModel;
  }

  public void setElapsedTimeModel(DonutChartModel elapsedTimeModel) {
    this.elapsedTimeModel = elapsedTimeModel;
  }

  public DonutChartModel getCaseByFinishedTaskModel() {
    return caseByFinishedTaskModel;
  }

  public void setCaseByFinishedTaskModel(DonutChartModel caseByFinishedTaskModel) {
    this.caseByFinishedTaskModel = caseByFinishedTaskModel;
  }

  public DonutChartModel getCaseByFinishedTimeModel() {
    return caseByFinishedTimeModel;
  }

  public void setCaseByFinishedTimeModel(DonutChartModel caseByFinishedTimeModel) {
    this.caseByFinishedTimeModel = caseByFinishedTimeModel;
  }
}