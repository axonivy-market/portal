package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import ch.ivy.ws.addon.ElapsedTimeStatistic;
import ch.ivy.ws.addon.ExpiryStatistic;
import ch.ivy.ws.addon.PortalCaseCustomVarField;
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
  private BarChartModel elapsedTimeModel;
  private List<String> availableCustomValues;
  StatisticService statisticService = new StatisticService();

  public static final int CASE_CATEGORIES_TYPE = 0;
  public StatisticChartCreationBean() {
    updateChartModels(new StatisticFilter());
  }

  /**
   * Update chart models
   * 
   * @param filter
   */
  public void updateChartModels(StatisticFilter filter) {
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
    setOldFiltersToCurrentValues(filter);
  }

  private void setOldFiltersToCurrentValues(StatisticFilter filter) {
    filter.setOldTimePeriodSelection(filter.getTimePeriodSelection());
    Date createdDateFrom = filter.getCreatedDateFrom();
    Date createdDateTo = filter.getCreatedDateTo();
    filter.setOldCreatedDateFrom(createdDateFrom == null ? null : new Date(createdDateFrom.getTime()));
    filter.setOldCreatedDateTo(createdDateTo == null ? null : new Date(createdDateTo.getTime()));
    filter.setIsAllOldCaseStatesSelected(filter.getIsAllCaseStatesSelected());
    filter.setIsAllOldCategoriesSelected(filter.getIsAllCategoriesSelected());
    filter.setIsAllOldRolesSelected(filter.getIsAllRolesSelected());
    filter.setIsAllOldTaskPrioritiesSelected(filter.getIsAllTaskPrioritiesSelected());
    if (!filter.getIsAllCategoriesSelected()) {
      updateOldListToNewList(filter.getOldSelectedCaseCategories(), filter.getSelectedCaseCategories());
    }
    if (!filter.getIsAllRolesSelected()) {
      updateOldListToNewList(filter.getOldSelectedRoles(), filter.getSelectedRoles());
    }
    if (!filter.getIsAllCaseStatesSelected()) {
      updateOldListToNewList(filter.getOldSelectedCaseStates(), filter.getSelectedCaseStates());
    }
    if (!filter.getIsAllTaskPrioritiesSelected()) {
      updateOldListToNewList(filter.getOldSelectedTaskPriorities(), filter.getSelectedTaskPriorities());
    }
    updateOldListToNewList(filter.getOldSelectedCustomVarCharFields1(), filter.getSelectedCustomVarCharFields1());
    updateOldListToNewList(filter.getOldSelectedCustomVarCharFields2(), filter.getSelectedCustomVarCharFields2());
    updateOldListToNewList(filter.getOldSelectedCustomVarCharFields3(), filter.getSelectedCustomVarCharFields3());
    updateOldListToNewList(filter.getOldSelectedCustomVarCharFields4(), filter.getSelectedCustomVarCharFields4());
    updateOldListToNewList(filter.getOldSelectedCustomVarCharFields5(), filter.getSelectedCustomVarCharFields5());
  }

  public boolean checkIfAnyFilterChanges(StatisticFilter filter) {
    if (filter.getOldTimePeriodSelection() != filter.getTimePeriodSelection()) {
      return true;
    }
    if (isDateChanged(filter.getOldCreatedDateFrom(), filter.getCreatedDateFrom())) {
      return true;
    }
    if (isDateChanged(filter.getOldCreatedDateTo(), filter.getCreatedDateTo())) {
      return true;
    }
    if (checkIfAnyCaseStateChanged(filter)) {
      return true;
    }
    if (checkIfAnyTaskPriorityChanged(filter)) {
      return true;
    }
    if (checkIfAnyCaseCategoryChanged(filter)) {
      return true;
    }
    if (checkIfAnyRoleChanged(filter)) {
      return true;
    }
    return checkIfAnyCustomVarcharChanged(filter);
  }

  private boolean checkIfAnyCaseStateChanged(StatisticFilter filter) {
    //compare check box select all of case states
    if (filter.getIsAllOldCaseStatesSelected() != filter.getIsAllCaseStatesSelected()) {
      return true;
    }
    //compare other check box of case states if select all is not checked
    return !filter.getIsAllCaseStatesSelected() && !filter.getOldSelectedCaseStates().equals(filter.getSelectedCaseStates());
  }

  private boolean checkIfAnyTaskPriorityChanged(StatisticFilter filter) {
    //compare check box select all of task priorities
    if (filter.getIsAllOldTaskPrioritiesSelected() != filter.getIsAllTaskPrioritiesSelected()) {
      return true;
    }
    //compare other check box of task priorities if select all is not checked
    return !filter.getIsAllTaskPrioritiesSelected() && !filter.getOldSelectedTaskPriorities().equals(filter.getSelectedTaskPriorities());
  }

  private boolean checkIfAnyCaseCategoryChanged(StatisticFilter filter) {
    //compare check box select all of case categories
    if (filter.getIsAllOldCategoriesSelected() != filter.getIsAllCategoriesSelected()) {
      return true;
    }
    //compare other check box of case categories if select all is not checked
    return !filter.getIsAllCategoriesSelected() && !filter.getOldSelectedCaseCategories().equals(filter.getSelectedCaseCategories());
  }

  private boolean checkIfAnyRoleChanged(StatisticFilter filter) {
    //compare check box select all of roles
    if (filter.getIsAllOldRolesSelected() != filter.getIsAllRolesSelected()) {
      return true;
    }
    //compare other check box of roles if select all is not checked
    return !filter.getIsAllRolesSelected() && !filter.getOldSelectedRoles().equals(filter.getSelectedRoles());
  }

  private boolean checkIfAnyCustomVarcharChanged(StatisticFilter filter) {
    //compare check boxes of custom varchar 1
    if (!filter.getOldSelectedCustomVarCharFields1().equals(filter.getSelectedCustomVarCharFields1())) {
      return true;
    }
    //compare check boxes of custom varchar 2
    if (!filter.getOldSelectedCustomVarCharFields2().equals(filter.getSelectedCustomVarCharFields2())) {
      return true;
    }
    //compare check boxes of custom varchar 3
    if (!filter.getOldSelectedCustomVarCharFields3().equals(filter.getSelectedCustomVarCharFields3())) {
      return true;
    }
    //compare check boxes of custom varchar 4
    if (!filter.getOldSelectedCustomVarCharFields4().equals(filter.getSelectedCustomVarCharFields4())) {
      return true;
    }
    //compare check boxes of custom varchar 5
    return !filter.getOldSelectedCustomVarCharFields5().equals(filter.getSelectedCustomVarCharFields5());
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
      for (String category : filter.getCaseCategories()) {
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
    String jsonQuery = StatisticChartQueryUtils.generateCaseQueryForCaseState(filter).asJson();
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
    return populateCustomVarCharAutoComplete(query, PortalCaseCustomVarField.CUSTOM_VAR_CHAR_1);
  }
  
  /**
   * Populate values for Auto Complete of cutomVarCharField2
   * 
   * @param query
   * @return values of available customVarCharField2
   */
  public List<String> populateCustomVarChar2AutoComplete(String query) {
    return populateCustomVarCharAutoComplete(query, PortalCaseCustomVarField.CUSTOM_VAR_CHAR_2);
  }
  
  /**
   * Populate values for Auto Complete of cutomVarCharField3
   * 
   * @param query
   * @return values of available customVarCharField3
   */
  public List<String> populateCustomVarChar3AutoComplete(String query) {
    return populateCustomVarCharAutoComplete(query, PortalCaseCustomVarField.CUSTOM_VAR_CHAR_3);
  }
  
  /**
   * Populate values for Auto Complete of cutomVarCharField4
   * 
   * @param query
   * @return values of available customVarCharField4
   */
  public List<String> populateCustomVarChar4AutoComplete(String query) {
    return populateCustomVarCharAutoComplete(query, PortalCaseCustomVarField.CUSTOM_VAR_CHAR_4);
  }
  
  /**
   * Populate values for Auto Complete of cutomVarCharField5
   * 
   * @param query
   * @return values of available customVarCharField5
   */
  public List<String> populateCustomVarChar5AutoComplete(String query) {
    return populateCustomVarCharAutoComplete(query, PortalCaseCustomVarField.CUSTOM_VAR_CHAR_5);
  }
  
  private List<String> populateCustomVarCharAutoComplete(String query, PortalCaseCustomVarField type) {
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