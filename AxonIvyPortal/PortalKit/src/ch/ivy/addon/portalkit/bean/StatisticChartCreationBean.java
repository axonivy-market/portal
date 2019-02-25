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

import ch.ivy.addon.portalkit.bo.CaseStateStatistic;
import ch.ivy.addon.portalkit.bo.ElapsedTimeStatistic;
import ch.ivy.addon.portalkit.bo.ExpiryStatistic;
import ch.ivy.addon.portalkit.bo.PriorityStatistic;
import ch.ivy.addon.portalkit.enums.CustomVarCharField;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChartQueryUtils;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.category.CategoryTree;
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
  private List<String> availableCustomValues;
  StatisticService statisticService = new StatisticService();

  public static final int CASE_CATEGORIES_TYPE = 0;

  /**
   * Update filters
   * 
   * @param filter
   * @param oldFilter 
   */
  public void updateFilters(StatisticFilter filter, StatisticFilter oldFilter) {
    if(filter.getTimePeriodSelection() != StatisticTimePeriodSelection.CUSTOM) {
      filter.setCreatedDateFrom(null);
      filter.setCreatedDateTo(null);
    }
    if (oldFilter != null) {
      setOldFiltersToCurrentValues(filter, oldFilter);
    }
  }

  public void clearChartModels() {
    taskByPriorityModel = null;
    caseByStateModel = null;
    caseByFinishedTimeModel = null;
    caseByFinishedTaskModel = null;
    taskByExpiryModel = null;
    elapsedTimeModel = null;
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
    updateOldListToNewList(oldFilter.getSelectedCustomVarCharFields1(), filter.getSelectedCustomVarCharFields1());
    updateOldListToNewList(oldFilter.getSelectedCustomVarCharFields2(), filter.getSelectedCustomVarCharFields2());
    updateOldListToNewList(oldFilter.getSelectedCustomVarCharFields3(), filter.getSelectedCustomVarCharFields3());
    updateOldListToNewList(oldFilter.getSelectedCustomVarCharFields4(), filter.getSelectedCustomVarCharFields4());
    updateOldListToNewList(oldFilter.getSelectedCustomVarCharFields5(), filter.getSelectedCustomVarCharFields5());
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
    return checkIfAnyCustomVarcharChanged(filter, oldFilter);
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

  private boolean checkIfAnyCustomVarcharChanged(StatisticFilter filter, StatisticFilter oldFilter) {
    //compare check boxes of custom varchar 1
    if (!oldFilter.getSelectedCustomVarCharFields1().equals(filter.getSelectedCustomVarCharFields1())) {
      return true;
    }
    //compare check boxes of custom varchar 2
    if (!oldFilter.getSelectedCustomVarCharFields2().equals(filter.getSelectedCustomVarCharFields2())) {
      return true;
    }
    //compare check boxes of custom varchar 3
    if (!oldFilter.getSelectedCustomVarCharFields3().equals(filter.getSelectedCustomVarCharFields3())) {
      return true;
    }
    //compare check boxes of custom varchar 4
    if (!oldFilter.getSelectedCustomVarCharFields4().equals(filter.getSelectedCustomVarCharFields4())) {
      return true;
    }
    //compare check boxes of custom varchar 5
    return !oldFilter.getSelectedCustomVarCharFields5().equals(filter.getSelectedCustomVarCharFields5());
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
      for (String category : filter.getCaseCategoryTree().getAllChildren().stream().map(CategoryTree::getRawPath).collect(Collectors.toList())) {
        addToListIfNotExist(selectedCaseCategories, category);
      }
    } else {
      selectedCaseCategories.clear();
    }
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

  public List<String> getAvailableCustomValues() {
    return availableCustomValues;
  }

  public void setAvailableCustomValues(List<String> availableCustomValues) {
    this.availableCustomValues = availableCustomValues;
  }

  /**
   * Populate values for Auto Complete of cutomVarCharField1
   * 
   * @param query
   * @return values of available customVarCharField1
   */
  public List<String> populateCustomVarChar1AutoComplete(String query) {
    return populateCustomVarCharAutoComplete(query, CustomVarCharField.CUSTOM_VAR_CHAR_1);
  }
  
  /**
   * Populate values for Auto Complete of cutomVarCharField2
   * 
   * @param query
   * @return values of available customVarCharField2
   */
  public List<String> populateCustomVarChar2AutoComplete(String query) {
    return populateCustomVarCharAutoComplete(query, CustomVarCharField.CUSTOM_VAR_CHAR_2);
  }
  
  /**
   * Populate values for Auto Complete of cutomVarCharField3
   * 
   * @param query
   * @return values of available customVarCharField3
   */
  public List<String> populateCustomVarChar3AutoComplete(String query) {
    return populateCustomVarCharAutoComplete(query, CustomVarCharField.CUSTOM_VAR_CHAR_3);
  }
  
  /**
   * Populate values for Auto Complete of cutomVarCharField4
   * 
   * @param query
   * @return values of available customVarCharField4
   */
  public List<String> populateCustomVarChar4AutoComplete(String query) {
    return populateCustomVarCharAutoComplete(query, CustomVarCharField.CUSTOM_VAR_CHAR_4);
  }
  
  /**
   * Populate values for Auto Complete of cutomVarCharField5
   * 
   * @param query
   * @return values of available customVarCharField5
   */
  public List<String> populateCustomVarChar5AutoComplete(String query) {
    return populateCustomVarCharAutoComplete(query, CustomVarCharField.CUSTOM_VAR_CHAR_5);
  }
  
  private List<String> populateCustomVarCharAutoComplete(String query, CustomVarCharField type) {
    List<String> result = null;
    if (StringUtils.isEmpty(query)) {
      result = statisticService.getCustomVarCharFields(type, StringUtils.EMPTY, 11);
    }
    else {
      result = statisticService.getCustomVarCharFields(type, query, 11);
    }
    result.sort((first, second) -> first.toLowerCase()
        .compareTo(second.toLowerCase()));

    return result;
  }
}