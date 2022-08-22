package ch.ivy.addon.portalkit.datamodel.internal;

import static ch.ivy.addon.portalkit.enums.FilterType.ALL_ADMINS;
import static ch.ivy.addon.portalkit.enums.FilterType.ALL_USERS;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.CaseFilterContainer;
import ch.ivy.addon.portalkit.casefilter.impl.TaskAnalysisCaseFilterContainer;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.enums.TaskAndCaseAnalysisColumn;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.service.TaskAnalysisFilterService;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskAnalysisFilterData;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskAnalysisTaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.OrderByColumnQuery;

public class TaskAnalysisLazyDataModel extends TaskLazyDataModel {
  
  private static final long serialVersionUID = -6615871274830927272L;

  private static final String TASK_COLUMN_PREFIX = "TASK_";

  private TaskAnalysisFilterData selectedTaskAnalysisFilterData;
  private TaskAnalysisFilterData defaultTaskAnalysisFilterData;

  private CaseSearchCriteria caseCriteria;
  private List<CaseFilter> caseFilters;
  private List<CaseFilter> selectedCaseFilters;
  private List<CaseFilter> oldSelectedCaseFilters = new ArrayList<>();
  private CaseFilterContainer caseFilterContainer;

  public TaskAnalysisLazyDataModel() {
    super();
    isNotKeepFilter = true;
    selectedCaseFilters = new ArrayList<>();
    buildCaseCriteria();
    buildDefaultTaskAnalysisFilterData();
  }

  public TaskAnalysisFilterData buildDefaultTaskAnalysisFilterData() {
    if (defaultTaskAnalysisFilterData == null) {
      defaultTaskAnalysisFilterData = new TaskAnalysisFilterData();
      defaultTaskAnalysisFilterData.setFilterName(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultFilter"));
      defaultTaskAnalysisFilterData.setType(FilterType.DEFAULT);
      collectFiltersForDefaultFilterSet();
    }
    isSelectedDefaultFilter = isSelectedDefaultFilter == null ? true : isSelectedDefaultFilter;
    return defaultTaskAnalysisFilterData;
  }

  @Override
  public TaskFilterData buildDefaultTaskFilterData() {
    return null;
  }

  @Override
  public void initFilterContainer() {
    filterContainer = new TaskAnalysisTaskFilterContainer(criteria.isAdminQuery());
  }

  @Override
  public List<ITask> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    criteria.setSortField(sortField);
    criteria.setSortDescending(sortOrder == SortOrder.DESCENDING);

    if (first == 0) {
      initializedDataModel(criteria);
    }

    List<ITask> foundTasks = findTasks(criteria, first, pageSize);
    data.addAll(foundTasks);
    return foundTasks;
  }
  
  @Override
  protected void buildCriteria() {
    criteria = new TaskSearchCriteria();
    criteria.setIncludedStates(new ArrayList<>(TaskSearchCriteria.STANDARD_STATES));
    criteria.setSortField(TaskSortField.ID.toString());
    criteria.setSortDescending(true);
  }

  public List<TaskFilter> getTaskFilters() {
    return filters;
  }

  public List<TaskFilter> getSelectedTaskFilters() {
    return selectedFilters;
  }

  public void setSelectedTaskFilters(List<TaskFilter> selectedFilters) {
    this.selectedFilters = selectedFilters;
  }

  public TaskFilterContainer getTaskFilterContainer() {
    return filterContainer;
  }

  public void setTaskFilterContainer(TaskFilterContainer filterContainer) {
    this.filterContainer = filterContainer;
  }

  public TaskAnalysisFilterData getSelectedTaskAnalysisFilterData() {
    return selectedTaskAnalysisFilterData;
  }

  public void setSelectedTaskAnalysisFilterData(TaskAnalysisFilterData selectedTaskAnalysisFilterData) {
    this.selectedTaskAnalysisFilterData = selectedTaskAnalysisFilterData;
  }
  
  @Override
  public void onFilterApply() {
    selectedTaskAnalysisFilterData = null;
    isSelectedDefaultFilter = false;
  }
  @Override
  public void removeFilter(TaskFilter filter) {
    filter.resetValues();
    selectedFilters.remove(filter);
    updateSelectedFilter();
  }

  public void removeFilter(CaseFilter filter) {
    filter.resetValues();
    selectedCaseFilters.remove(filter);
    updateSelectedFilter();
  }

  @Override
  public void resetFilters() {
    for (TaskFilter selectedFilter : selectedFilters) {
      selectedFilter.resetValues();
    }
    for (CaseFilter selectedCaseFilter : selectedCaseFilters) {
      selectedCaseFilter.resetValues();
    }
    selectedFilters = new ArrayList<>();
    selectedCaseFilters = new ArrayList<>();
    selectedTaskAnalysisFilterData = null;
    isSelectedDefaultFilter = false;
  }

  public boolean isSameTaskFilterData(TaskAnalysisFilterData filterToBeRemoved) {
    if (filterToBeRemoved == null || selectedTaskAnalysisFilterData == null) {
      return false;
    }
    return filterToBeRemoved.getFilterGroupId().equals(selectedTaskAnalysisFilterData.getFilterGroupId())
        && filterToBeRemoved.getType() == selectedTaskAnalysisFilterData.getType()
        && filterToBeRemoved.getFilterName().equals(selectedTaskAnalysisFilterData.getFilterName());
  }

  /**
   * Save all filter settings to business data
   * 
   * @param filterName
   * @param filterType
   * @param taskFilterGroupId
   * @return TaskAnalysisFilterData
   */
  public TaskAnalysisFilterData saveTaskAnalysisFilter(String filterName, FilterType filterType, Long taskFilterGroupId) {
    TaskAnalysisFilterData taskAnalysisFilterData = new TaskAnalysisFilterData();
    List<TaskFilter> taskFiltersToSave = new ArrayList<>(selectedFilters);
    taskAnalysisFilterData.setTaskFilters(taskFiltersToSave);
    List<CaseFilter> filtersToSave = new ArrayList<>(selectedCaseFilters);
    taskAnalysisFilterData.setCaseFilters(filtersToSave);
    taskAnalysisFilterData.setUserId(Ivy.session().getSessionUser().getId());
    taskAnalysisFilterData.setFilterGroupId(taskFilterGroupId);
    taskAnalysisFilterData.setFilterName(filterName);
    taskAnalysisFilterData.setType(filterType);
    boolean isPublic = ALL_USERS == taskAnalysisFilterData.getType() || ALL_ADMINS == taskAnalysisFilterData.getType();
    taskAnalysisFilterData.setIsPublic(isPublic);
    TaskAnalysisFilterService taskFilterService = new TaskAnalysisFilterService();
    taskFilterService.save(taskAnalysisFilterData);
    BusinessDataInfo<TaskAnalysisFilterData> info = taskFilterService.save(taskAnalysisFilterData);
    taskAnalysisFilterData = taskFilterService.findById(info.getId());
    return taskAnalysisFilterData;
  }

  /**
   * Apply filter settings loaded from business data to this {@link #TaskAnalysisLazyDataModel}
   * 
   * @param taskAnalysisFilterData
   * @throws ReflectiveOperationException
   */
  public void applyFilter(TaskAnalysisFilterData taskAnalysisFilterData) throws ReflectiveOperationException {
    isSelectedDefaultFilter = FilterType.DEFAULT.equals(taskAnalysisFilterData.getType());
    selectedTaskAnalysisFilterData = taskAnalysisFilterData;
    new TaskAnalysisFilterService().applyFilter(this, taskAnalysisFilterData);
  }

  /**
   * Builds TaskQuery and put it into TaskSearchCriteria.
   */
  @Override
  protected void buildQueryToSearchCriteria() {
    if (criteria.getCustomTaskQuery() == null) {
      TaskQuery taskQuery = SubProcessCall.withPath(PortalConstants.BUILD_TASK_QUERY_CALLABLE)
          .withStartSignature("buildTaskQuery(Boolean)").withParam("isQueryForHomePage", compactMode).call()
          .get("taskQuery", TaskQuery.class);
      criteria.setCustomTaskQuery(taskQuery);
    }

    if (filterContainer != null) {
      if (selectedFilters.contains(filterContainer.getStateFilter())) {
        criteria.setIncludedStates(new ArrayList<>());
      } else {
        criteria.setIncludedStates(filterContainer.getStateFilter().getSelectedFilteredStates());
      }
    }
    if (caseFilterContainer != null) {
      if (selectedCaseFilters.contains(caseFilterContainer.getStateFilter())) {
        caseCriteria.setIncludedStates(new ArrayList<>());
      } else {
        caseCriteria.setIncludedStates(caseFilterContainer.getStateFilter().getSelectedFilteredStates());
      }
    }

    TaskQuery taskQuery = buildTaskQuery();
    if (CollectionUtils.isNotEmpty(selectedCaseFilters)) {
      CaseQuery caseQuery = buildCaseQuery();
      taskQuery = taskQuery.where().cases(caseQuery);
    }

    String sortField = criteria.getSortField();
    if (sortField.startsWith(TASK_COLUMN_PREFIX)) {
      buildSortTaskQuery(taskQuery);
    }

    extendSort(taskQuery);
    criteria.setFinalTaskQuery(taskQuery);
  }

  @Override
  protected TaskQuery buildTaskQuery() {
    TaskQuery taskQuery = criteria.createQuery();
    IFilterQuery filterQuery = taskQuery.where();
    selectedFilters.forEach(selectedFilter -> {
      TaskQuery subQuery = selectedFilter.buildQuery();
      if (subQuery != null) {
        filterQuery.and(subQuery);
      }
    });
    return taskQuery;
  }

  private CaseQuery buildCaseQuery() {
    CaseQuery caseQuery = caseCriteria.createQuery();
    CaseQuery.IFilterQuery filterQuery = caseQuery.where();
    selectedCaseFilters.forEach(selectedFilter -> {
      CaseQuery subQuery = selectedFilter.buildQuery();
      if (subQuery != null) {
        filterQuery.and(subQuery);
      }
    });
    return caseQuery;
  }

  private void buildSortTaskQuery(TaskQuery taskQuery) {
    TaskAndCaseAnalysisColumn sortColumn = TaskAndCaseAnalysisColumn.valueOf(criteria.getSortField());
    OrderByColumnQuery orderQuery = null;
    switch (sortColumn) {
      case TASK_ACTIVATOR:
        orderQuery = taskQuery.orderBy().activatorDisplayName();
        break;
      case TASK_CATEGORY:
        orderQuery = taskQuery.orderBy().category();
        break;
      case TASK_CREATION_TIME:
        orderQuery = taskQuery.orderBy().startTimestamp();
        break;
      case TASK_EXPIRY_TIME:
        orderQuery = taskQuery.orderBy().expiryTimestamp();
        break;
      case TASK_FINISHED_TIME:
        orderQuery = taskQuery.orderBy().endTimestamp();
        break;
      case TASK_NAME:
        orderQuery = taskQuery.orderBy().name();
        break;
      case TASK_PRIORITY:
        orderQuery = taskQuery.orderBy().priority();
        break;
      case TASK_STATE:
        orderQuery = taskQuery.orderBy().state();
        break;
      case TASK_WORKER:
        orderQuery = taskQuery.orderBy().workerUserDisplayName();
        break;
      default:
        orderQuery = taskQuery.orderBy().taskId();
        break;
    }
    
    if (sortColumn == TaskAndCaseAnalysisColumn.TASK_EXPIRY_TIME) {
      if (criteria.isSortDescending()) {
        orderQuery.descendingNullFirst();
      } else {
        orderQuery.ascendingNullLast();
      }
      return;
    }

    if (criteria.isSortDescending()) {
      orderQuery.descending();
    } else {
      orderQuery.ascending();
    }
  }

  @Override
  public void initFilters() throws ReflectiveOperationException {
    super.initFilters();
    initCaseFilters();
    if (isNotKeepFilter) {
      applyFilter(defaultTaskAnalysisFilterData);
    }
  }

  @Override
  public void collectFiltersForDefaultFilterSet() {
    defaultTaskAnalysisFilterData.setTaskFilters(new ArrayList<>());
    defaultTaskAnalysisFilterData.setCaseFilters(new ArrayList<>());
  }
  
  @Override
  public void checkToApplyDefaultSet() {
    if (isNotKeepFilter && defaultTaskAnalysisFilterData != null) {
      selectedFilters.addAll(defaultTaskAnalysisFilterData.getTaskFilters());
    }
  }

  private void setValuesForCaseStateFilter(CaseSearchCriteria criteria) {
    if (caseFilterContainer != null) {
      caseFilterContainer.getStateFilter().setFilteredStates(new ArrayList<>(criteria.getIncludedStates()));
      caseFilterContainer.getStateFilter().setSelectedFilteredStates(criteria.getIncludedStates());
    }
  }

  @SuppressWarnings("unchecked")
  public void onCaseFilterChange(ValueChangeEvent event) {
    oldSelectedCaseFilters = (List<CaseFilter>) event.getOldValue();
  }

  @SuppressWarnings("unchecked")
  @Override
  public void updateSelectedFilter() {
    List<TaskFilter> toggleFilters = (List<TaskFilter>) CollectionUtils.subtract(selectedFilters, oldSelectedFilters);
    if (CollectionUtils.isNotEmpty(toggleFilters)) {
      toggleFilters.forEach(filter -> filter.resetValues());
    }
    resetFilterData();
  }

  public void resetFilterData() {
    if (selectedTaskAnalysisFilterData != null) {
      selectedTaskAnalysisFilterData = null;
    }
    this.isSelectedDefaultFilter = false;
  }

  @SuppressWarnings("unchecked")
  public void updateSelectedCaseFilter() {
    List<CaseFilter> toggleFilters =
        (List<CaseFilter>) CollectionUtils.subtract(selectedCaseFilters, oldSelectedCaseFilters);
    if (CollectionUtils.isNotEmpty(toggleFilters)) {
      toggleFilters.forEach(filter -> filter.resetValues());
    }
    resetFilterData();
  }

  public List<CaseFilter> getSelectedCaseFilters() {
    return selectedCaseFilters;
  }

  public void setSelectedCaseFilters(List<CaseFilter> selectedCaseFilters) {
    this.selectedCaseFilters = selectedCaseFilters;
  }

  public CaseFilterContainer getCaseFilterContainer() {
    return caseFilterContainer;
  }

  public void setCaseFilterContainer(CaseFilterContainer caseFilterContainer) {
    this.caseFilterContainer = caseFilterContainer;
  }

  public List<CaseFilter> getCaseFilters() {
    return caseFilters;
  }

  protected void buildCaseCriteria() {
    caseCriteria = new CaseSearchCriteria();
    caseCriteria.setBusinessCase(true);
    caseCriteria.setIncludedStates(new ArrayList<>(CaseSearchCriteria.STANDARD_STATES));
    caseCriteria.extendStatesQueryByPermission(PermissionUtils.checkReadAllCasesPermission());
    caseCriteria.setSortField(CaseSortField.ID.toString());
    caseCriteria.setSortDescending(true);
  }

  private void restoreSessionAdvancedCaseFilters() throws IllegalAccessException, InvocationTargetException {
    if (!isNotKeepFilter) {
      List<CaseFilter> sessionCaseFilters = UserUtils.getSessionCaseAdvancedFilterAttribute();
      for (CaseFilter filter : caseFilters) {
        for (CaseFilter sessionCaseFilter : sessionCaseFilters) {
          copyProperties(sessionCaseFilter, filter);
        }
      }
    } else if (defaultTaskAnalysisFilterData != null) {
      selectedCaseFilters.addAll(defaultTaskAnalysisFilterData.getCaseFilters());
    }
  }

  private void copyProperties(CaseFilter sessionCaseFilter, CaseFilter filter) throws IllegalAccessException,
      InvocationTargetException {
    if (sessionCaseFilter.getClass() == filter.getClass()) {
      BeanUtils.copyProperties(filter, sessionCaseFilter);
      selectedCaseFilters.add(filter);
    }
}

  protected void initCaseFilterContainer() {
    caseFilterContainer = new TaskAnalysisCaseFilterContainer();
  }

  private void initCaseFilters() throws IllegalAccessException, InvocationTargetException {
    if (caseFilterContainer == null) {
      initCaseFilterContainer();
      caseFilters = caseFilterContainer.getFilters();
      setValuesForCaseStateFilter(caseCriteria);
      restoreSessionAdvancedCaseFilters();
    }
  }

  public TaskAnalysisFilterData getDefaultTaskAnalysisFilterData() {
    return defaultTaskAnalysisFilterData;
  }

  public void setDefaultTaskAnalysisFilterData(TaskAnalysisFilterData defaultTaskAnalysisFilterData) {
    this.defaultTaskAnalysisFilterData = defaultTaskAnalysisFilterData;
  }

}
