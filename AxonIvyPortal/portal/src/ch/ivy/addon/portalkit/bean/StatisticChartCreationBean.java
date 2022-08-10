package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.donut.DonutChartModel;

import ch.ivy.addon.portalkit.bo.CaseStateStatistic;
import ch.ivy.addon.portalkit.bo.CaseCategoryStatistic;
import ch.ivy.addon.portalkit.bo.ElapsedTimeStatistic;
import ch.ivy.addon.portalkit.bo.ExpiryStatistic;
import ch.ivy.addon.portalkit.bo.PriorityStatistic;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChartQueryUtils;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

@ManagedBean
@ViewScoped
public class StatisticChartCreationBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private DonutChartModel taskByPriorityModel;
  private DonutChartModel caseByStateModel;
  private DonutChartModel caseByFinishedTimeModel;
  private DonutChartModel caseByFinishedTaskModel;
  private BarChartModel taskByExpiryModel;
  private BarChartModel elapsedTimeModel;
  private BarChartModel casesByCategoryModel;
  private StatisticService statisticService;
  private Map<String, List<String>> customFieldFilters = new HashMap<>();

  public static final int CASE_CATEGORIES_TYPE = 0;
  public static final int MINIMUM_STATISTIC_CHART_SCALING_INTERVAL = 10;

  @PostConstruct
  public void init() {
    statisticService = StatisticService.getInstance();
    initStatisticChart();
  }

  /**
   * Create a StatisticChart's placeholder when the data is collecting.
   */
  private void initStatisticChart() {
    taskByPriorityModel = statisticService.createDonutChartPlaceholder();
    caseByStateModel = statisticService.createDonutChartPlaceholder();
    caseByFinishedTimeModel = statisticService.createDonutChartPlaceholder();
    caseByFinishedTaskModel = statisticService.createDonutChartPlaceholder();
    taskByExpiryModel = statisticService.createBarChartPlaceholder();
    elapsedTimeModel = statisticService.createBarChartPlaceholder();
    casesByCategoryModel = statisticService.createBarChartPlaceholder();
  }

  /**
   * Update filters
   * 
   * @param filter
   * @param oldFilter 
   */
  public void updateFilters(StatisticFilter filter, StatisticFilter oldFilter) {
    filter.setCustomFieldFilters(this.customFieldFilters);
    if(filter.getTimePeriodSelection() != StatisticTimePeriodSelection.CUSTOM) {
      filter.setCreatedDateFrom(null);
      filter.setCreatedDateTo(null);
    }
    if (oldFilter != null) {
      setOldFiltersFromCurrentValues(filter, oldFilter);
    }
  }

  public void clearChartModels() {
    initStatisticChart();
  }
  
  private void setOldFiltersFromCurrentValues(StatisticFilter filter, StatisticFilter oldFilter) {
    oldFilter.setTimePeriodSelection(filter.getTimePeriodSelection());
    Date createdDateFrom = filter.getCreatedDateFrom();
    Date createdDateTo = filter.getCreatedDateTo();
    oldFilter.setCreatedDateFrom(createdDateFrom == null ? null : new Date(createdDateFrom.getTime()));
    oldFilter.setCreatedDateTo(createdDateTo == null ? null : new Date(createdDateTo.getTime()));
    oldFilter.setIsAllCaseStatesSelected(filter.getIsAllCaseStatesSelected());
    oldFilter.setIsAllRolesSelected(filter.getIsAllRolesSelected());
    oldFilter.setIsAllTaskPrioritiesSelected(filter.getIsAllTaskPrioritiesSelected());

    updateCaseCategory(filter, oldFilter);

    if (!filter.getIsAllRolesSelected()) {
      updateOldListFromNewList(oldFilter.getSelectedRoles(), filter.getSelectedRoles());
    }
    if (!filter.getIsAllCaseStatesSelected()) {
      updateOldListFromNewList(oldFilter.getSelectedCaseStates(), filter.getSelectedCaseStates());
    }
    if (!filter.getIsAllTaskPrioritiesSelected()) {
      updateOldListFromNewList(oldFilter.getSelectedTaskPriorities(), filter.getSelectedTaskPriorities());
    }
    oldFilter.setCustomFieldFilters(filter.getCustomFieldFilters());
  }

  private void updateCaseCategory(StatisticFilter filter, StatisticFilter oldFilter) {
    filter.setSelectedCaseCategories(filter.getCaseCategories().getCategoryPaths());
    oldFilter.setSelectedCaseCategories(filter.getSelectedCaseCategories());
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
    return checkIfAnyCustomFieldChanged(filter, oldFilter);
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
    return !oldFilter.getCaseCategories().getCategoryPaths().equals(filter.getCaseCategories().getCategoryPaths());
  }

  private boolean checkIfAnyRoleChanged(StatisticFilter filter, StatisticFilter oldFilter) {
    //compare check box select all of roles
    if (oldFilter.getIsAllRolesSelected() != filter.getIsAllRolesSelected()) {
      return true;
    }
    //compare other check box of roles if select all is not checked
    return !filter.getIsAllRolesSelected() && !oldFilter.getSelectedRoles().equals(filter.getSelectedRoles());
  }

  private boolean checkIfAnyCustomFieldChanged(StatisticFilter filter, StatisticFilter oldFilter) {
    for (Map.Entry<String, List<String>> entry : filter.getCustomFieldFilters().entrySet()) {
      List<String> list = oldFilter.getCustomFieldFilters().get(entry.getKey());
      if (list != null && entry.getValue() != null && !CollectionUtils.isEqualCollection(list, entry.getValue())) {
        return true;
      }
    }
    return false;
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

  public void updateRolesCheckboxes(StatisticFilter filter) {
    List<String> selectedRoles = filter.getSelectedRoles();
    if (filter.getIsAllRolesSelected()) {
      for (Object obj : filter.getRoles()) {
        if (obj instanceof IUser) {
          IUser user = (IUser) obj;
          addToListIfNotExist(selectedRoles, user.getMemberName());
        } else if (obj instanceof IRole) {
          IRole role = (IRole) obj;
          addToListIfNotExist(selectedRoles, role.getMemberName());
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

  private <T> void updateOldListFromNewList(List<T> oldList, List<T> newList) {
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
    TaskQuery query = StatisticChartQueryUtils.generateTaskQuery(filter);
    PriorityStatistic priorityStatisticData = statisticService.getPriorityStatisticData(query);
    taskByPriorityModel = statisticService.generateTaskByPriorityModel(priorityStatisticData, false);
  }

  /**
   * Create model for "Task by Expiry Date" chart from given statistic filter
   * 
   * @param filter statistic filter
   */
  public void updateTaskByExpiryModel(StatisticFilter filter) {
    TaskQuery taskQuery = StatisticChartQueryUtils.generateTaskQueryForExpiry(filter);
    ExpiryStatistic expiryStatisticData = statisticService.getExpiryStatisticData(taskQuery);
    taskByExpiryModel = statisticService.generateTaskByExpiryModel(expiryStatisticData, false, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
  }

  /**
   * Create model for "Case by State" chart from given statistic filter
   * 
   * @param filter statistic filter
   */
  public void updateCaseByStateModel(StatisticFilter filter) {
    CaseQuery caseQuery = StatisticChartQueryUtils.generateCaseQueryForCaseState(filter);
    CaseStateStatistic caseStateStatisticData = statisticService.getCaseStateStatisticData(caseQuery);
    caseByStateModel = statisticService.generateCaseByStateModel(caseStateStatisticData,StatisticChartType.CASES_BY_STATE, false);
  }
  
  /**
   * Create model for "Case by Finished task" chart from given statistic filter
   * @param filter
   */
  public void updateCaseByFinishedTaskModel(StatisticFilter filter) {
    CaseQuery caseQuery = StatisticChartQueryUtils.generateCaseQueryForCaseHaveFinishedTask(filter);
    CaseStateStatistic caseStateStatisticData = statisticService.getCaseStateStatisticData(caseQuery);
    caseByFinishedTaskModel = statisticService.generateCaseByStateModel(caseStateStatisticData, StatisticChartType.CASES_BY_FINISHED_TASK, false);
  }
  
  /**
   * Create model for "Case by Finished time" chart from given statistic filter
   * @param filter
   */
  public void updateCaseByFinishedTimeModel(StatisticFilter filter) {
    CaseQuery caseQuery = StatisticChartQueryUtils.generateCaseQueryByFinishedTime(filter);
    CaseStateStatistic caseStateStatisticData = statisticService.getCaseStateStatisticData(caseQuery);
    caseByFinishedTimeModel = statisticService.generateCaseByStateModel(caseStateStatisticData, StatisticChartType.CASES_BY_FINISHED_TIME, false);
  }

  /**
   * Create model for "Elapsed time by Case Category" chart from given statistic filter
   * 
   * @param filter statistic filter
   */
  public void updateElapsedTimeByCaseCategory(StatisticFilter filter) {
    CaseQuery caseQuery = StatisticChartQueryUtils.generateCaseQuery(filter, true);
    ElapsedTimeStatistic elapsedTimeStatisticData = statisticService.getElapsedTimeStatisticData(caseQuery);
    setElapsedTimeModel(statisticService.generateElapsedTimeModel(elapsedTimeStatisticData, false));
  }
  
  /**
   * Create model for "Cases by Category" chart from given statistic filter
   * 
   * @param filter statistic filter
   */
  public void updateCasesByCategoryModel(StatisticFilter filter) {
    CaseQuery caseQuery = StatisticChartQueryUtils.generateCaseQueryForCasesByCategoryChart(filter, null);
    CaseCategoryStatistic caseCategoryStatisticData = statisticService.getCasesByCategoryStatisticData(caseQuery,filter.getCaseCategories().getCategoryPaths());
    casesByCategoryModel = statisticService.generateCasesByCategoryModel(caseCategoryStatisticData, false);
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

  public BarChartModel getElapsedTimeModel() {
    return elapsedTimeModel;
  }

  public void setElapsedTimeModel(BarChartModel elapsedTimeModel) {
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

  public BarChartModel getCasesByCategoryModel() {
    return casesByCategoryModel;
  }

  public void setCasesByCategoryModel(BarChartModel casesByCategoryModel) {
    this.casesByCategoryModel = casesByCategoryModel;
  }

public List<String> populateCustomStringFieldAutoComplete(String query) {
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String fieldName = params.get("fieldName");
    return statisticService.getCustomFields(fieldName, query);
  }
  
  public void setCustomFieldFilter(String customFieldName, List<String> values) {
    this.customFieldFilters.put(customFieldName, values);
  }
  
  public long getStatisticChartScalingInterval() {
    String statisticChartScalingInterval = new GlobalSettingService().findGlobalSettingValue(GlobalVariable.STATISTIC_CHART_SCALING_INTERVAL);
    return StringUtils.isNotBlank(statisticChartScalingInterval) ? Long.valueOf(statisticChartScalingInterval) : 0;
  }

  public int getMinimumStatisticChartScalingInterval() {
    return MINIMUM_STATISTIC_CHART_SCALING_INTERVAL;
  }
}