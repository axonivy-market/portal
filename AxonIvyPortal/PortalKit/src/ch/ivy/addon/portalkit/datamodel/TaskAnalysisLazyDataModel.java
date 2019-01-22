package ch.ivy.addon.portalkit.datamodel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.CaseFilterContainer;
import ch.ivy.addon.portalkit.casefilter.TaskAnalysisCaseFilterContainer;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.enums.TaskAndCaseAnalysisColumn;
import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.service.TaskAnalysisFilterService;
import ch.ivy.addon.portalkit.service.TaskFilterService;
import ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData;
import ch.ivy.addon.portalkit.taskfilter.TaskAnalysisTaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.TaskInProgressByOthersFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskStateFilter;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.OrderByColumnQuery;

public class TaskAnalysisLazyDataModel extends TaskLazyDataModel {
  
  private static final long serialVersionUID = -6615871274830927272L;

  private static final String TASK_COLUMN_PREFIX = "TASK_";

  protected List<TaskFilter> taskFilters;
  protected List<TaskFilter> selectedTaskFilters;
  protected TaskFilterContainer taskFilterContainer;

  private TaskInProgressByOthersFilter inProgressFilter;
  private boolean isInProgressFilterDisplayed = false;
  private TaskAnalysisFilterData selectedTaskAnalysisFilterData;

  private boolean isRelatedTaskDisplayed = false;
  private boolean isNotKeepFilter = false;

  private CaseSearchCriteria caseCriteria;
  protected List<CaseFilter> caseFilters;
  protected List<CaseFilter> selectedCaseFilters;
  protected CaseFilterContainer caseFilterContainer;

  public TaskAnalysisLazyDataModel(String taskWidgetComponentId) {
    super();
    this.taskWidgetComponentId = taskWidgetComponentId;
    selectedTaskFilters = new ArrayList<>();
    selectedCaseFilters = new ArrayList<>();
    criteria = buildCriteria();
    caseCriteria = buildCaseCriteria();
    if (inProgressFilter != null) {
      isInProgressFilterDisplayed = true;
    } else {
      inProgressFilter = new TaskInProgressByOthersFilter();
    }

    setInvolvedApplications();
    initColumnsConfiguration();
  }
  
  public TaskAnalysisLazyDataModel() {
    this("task-widget");
  }

  /**
   * <p>
   * Initialize TaskFilterContainer with your customized TaskFilterContainer class.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * filterContainer = new CustomizedTaskFilterContainer();
   * </pre></code>
   * </p>
   */
  @Override
  protected void initFilterContainer() {
    taskFilterContainer = new TaskAnalysisTaskFilterContainer();
  }

  public void initTaskFilters() {
    if (taskFilterContainer == null) {
      if (isRelatedTaskDisplayed) {
        if (!criteria.getIncludedStates().contains(TaskState.DONE)) {
          criteria.addIncludedStates(Arrays.asList(TaskState.DONE));
        }
        if (!criteria.getIncludedStates().contains(TaskState.UNASSIGNED)) {
          criteria.addIncludedStates(Arrays.asList(TaskState.UNASSIGNED));
        }
      }
      initFilterContainer();
      taskFilters = taskFilterContainer.getFilters();
      setValuesForStateFilter(criteria);
      if (criteria.isAdminQuery() && !isRelatedTaskDisplayed) {
        TaskStateFilter stateFilter = taskFilterContainer.getStateFilter();
        stateFilter.setSelectedFilteredStatesAtBeginning(new ArrayList<>(stateFilter.getSelectedFilteredStates()));
      }
    }
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
  protected TaskSearchCriteria buildCriteria() {
    TaskSearchCriteria criteria = new TaskSearchCriteria();
    criteria.setQueryForUnassignedTask(false);
    criteria.setIncludedStates(new ArrayList<>(Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED)));
    criteria.setSortField(TaskSortField.ID.toString());
    criteria.setSortDescending(true);
    return criteria;
  }

  public void setSortField(String sortField, boolean sortDescending) {
    criteria.setSortField(sortField);
    criteria.setSortDescending(sortDescending);
  }

  public void setCategory(String category) {
    criteria.setCategory(category);
  }

  public void setAdminQuery(boolean isAdminQuery) {
    if (isAdminQuery && !criteria.getIncludedStates().contains(TaskState.DONE)) {
      criteria.addIncludedStates(Arrays.asList(TaskState.DONE));
      setValuesForStateFilter(criteria);
    }
    criteria.setAdminQuery(isAdminQuery);
  }

  public void setTaskId(Long taskId) {
    criteria.setTaskId(taskId);
    criteria.setIncludedStates(new ArrayList<>());
    criteria.setQueryByTaskId(true);
  }

  public void setCaseId(Long caseId) {
    criteria.setCaseId(caseId);
  }

  public void setQueryByBusinessCaseId(boolean isQueryByBusinessCaseId) {
    criteria.setQueryByBusinessCaseId(isQueryByBusinessCaseId);
  }

  public void setApps(List<String> apps) {
    criteria.setApps(apps);
  }

  public void setTaskAssigneeType(TaskAssigneeType assigneeType) {
    criteria.setTaskAssigneeType(assigneeType);
  }

  public List<TaskFilter> getTaskFilters() {
    return taskFilters;
  }

  public List<TaskFilter> getSelectedTaskFilters() {
    return selectedTaskFilters;
  }

  public void setSelectedTaskFilters(List<TaskFilter> selectedFilters) {
    this.selectedTaskFilters = selectedFilters;
  }

  public TaskFilterContainer getTaskFilterContainer() {
    return taskFilterContainer;
  }

  public void setTaskFilterContainer(TaskFilterContainer filterContainer) {
    this.taskFilterContainer = filterContainer;
  }

  public TaskAnalysisFilterData getSelectedTaskAnalysisFilterData() {
    return selectedTaskAnalysisFilterData;
  }

  public void setSelectedTaskAnalysisFilterData(TaskAnalysisFilterData selectedTaskAnalysisFilterData) {
    this.selectedTaskAnalysisFilterData = selectedTaskAnalysisFilterData;
  }

  @Override
  public void removeFilter(TaskFilter filter) {
    filter.resetValues();
    selectedTaskFilters.remove(filter);
  }

  public void removeFilter(CaseFilter filter) {
    filter.resetValues();
    selectedCaseFilters.remove(filter);
  }
  

  @Override
  public void resetFilters() {
    for (TaskFilter selectedFilter : selectedTaskFilters) {
      selectedFilter.resetValues();
    }
    for (CaseFilter selectedCaseFilter : selectedCaseFilters) {
      selectedCaseFilter.resetValues();
    }
    selectedTaskFilters = new ArrayList<>();
    selectedCaseFilters = new ArrayList<>();
    selectedTaskAnalysisFilterData = null;
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
    List<TaskFilter> taskFiltersToSave = new ArrayList<>(selectedTaskFilters);
    addCustomSettingsToTaskFilters(taskFiltersToSave);
    taskAnalysisFilterData.setTaskFilters(taskFiltersToSave);
    List<CaseFilter> filtersToSave = new ArrayList<>(selectedCaseFilters);
    taskAnalysisFilterData.setCaseFilters(filtersToSave);
    taskAnalysisFilterData.setUserId(Ivy.session().getSessionUser().getId());
    taskAnalysisFilterData.setFilterGroupId(taskFilterGroupId);
    taskAnalysisFilterData.setFilterName(filterName);
    taskAnalysisFilterData.setType(filterType);
    TaskAnalysisFilterService taskFilterService = new TaskAnalysisFilterService();
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
    selectedTaskAnalysisFilterData = taskAnalysisFilterData;
    new TaskAnalysisFilterService().applyFilter(this, taskAnalysisFilterData);
    applyCustomSettings(taskAnalysisFilterData);
  }

  private void applyCustomSettings(TaskAnalysisFilterData taskAnalysisFilterData) throws ReflectiveOperationException {
    isInProgressFilterDisplayed = false;
    inProgressFilter = new TaskInProgressByOthersFilter();
    for (TaskFilter savedTaskFilter : taskAnalysisFilterData.getTaskFilters()) {
      if (savedTaskFilter instanceof TaskInProgressByOthersFilter) {
        new TaskFilterService().copyFilterValues(inProgressFilter, savedTaskFilter);
        isInProgressFilterDisplayed = true;
      }
    }
  }

  /**
   * Builds TaskQuery and put it into TaskSearchCriteria.
   */
  @Override
  protected void buildQueryToSearchCriteria() {
    if (criteria.getCustomTaskQuery() == null) {
      TaskQuery taskQuery = SubProcessCall.withPath("Functional Processes/BuildTaskQuery")
          .withStartSignature("buildTaskQuery(Boolean)").withParam("isQueryForHomePage", compactMode).call()
          .get("taskQuery", TaskQuery.class);
      criteria.setCustomTaskQuery(taskQuery);
    }

    if (taskFilterContainer != null) {
      if (selectedTaskFilters.contains(taskFilterContainer.getStateFilter())) {
        criteria.setIncludedStates(new ArrayList<>());
      } else {
        criteria.setIncludedStates(taskFilterContainer.getStateFilter().getSelectedFilteredStates());
      }
    }
    if (caseFilterContainer != null) {
      if (selectedCaseFilters.contains(caseFilterContainer.getStateFilter())) {
        caseCriteria.setIncludedStates(new ArrayList<>());
      } else {
        caseCriteria.setIncludedStates(caseFilterContainer.getStateFilter().getSelectedFilteredStates());
      }
    }

    criteria.setTaskStartedByAnotherDisplayed(inProgressFilter.getIsTaskInProgressByOthersDisplayed());

    TaskQuery taskQuery = buildTaskQuery();
    CaseQuery caseQuery = buildCaseQuery();
    taskQuery = taskQuery.where().cases(caseQuery);

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
    selectedTaskFilters.forEach(selectedFilter -> {
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
      case TASK_DESCRIPTION:
        orderQuery =taskQuery.orderBy().description();
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
  public void setQueryForUnassignedTask(boolean isQueryForOnlyUnassignedTask) {
    this.criteria.setQueryForUnassignedTask(isQueryForOnlyUnassignedTask);
  }

  @Override
  public void initFilters() throws ReflectiveOperationException {
    initTaskFilters();
    initCaseFilters();
  }

  private void setValuesForCaseStateFilter(CaseSearchCriteria criteria) {
    if (caseFilterContainer != null) {
      caseFilterContainer.getStateFilter().setFilteredStates(new ArrayList<>(criteria.getIncludedStates()));
      caseFilterContainer.getStateFilter().setSelectedFilteredStates(criteria.getIncludedStates());
    }
  }
  
  @SuppressWarnings("unchecked")
  public void onCaseFilterChange(ValueChangeEvent event) {
    List<CaseFilter> oldSelectedFilters = (List<CaseFilter>) event.getOldValue();
    List<CaseFilter> newSelectedFilters = (List<CaseFilter>) event.getNewValue();
    List<CaseFilter> toggleFilters =
        (List<CaseFilter>) CollectionUtils.subtract(newSelectedFilters, oldSelectedFilters);
    if (CollectionUtils.isNotEmpty(toggleFilters)) {
      toggleFilters.get(0).resetValues();
    }
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

  ///================
  protected CaseSearchCriteria buildCaseCriteria() {
    CaseSearchCriteria caseCriteria = new CaseSearchCriteria();
    caseCriteria.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE)));
    caseCriteria.setSortField(CaseSortField.ID.toString());
    caseCriteria.setSortDescending(true);
    return caseCriteria;
  }

  private void restoreSessionAdvancedCaseFilters() throws IllegalAccessException, InvocationTargetException {
    if (!isNotKeepFilter) {
      List<CaseFilter> sessionCaseFilters = UserUtils.getSessionCaseAdvancedFilterAttribute();
      for (CaseFilter filter : caseFilters) {
        for (CaseFilter sessionCaseFilter : sessionCaseFilters) {
          copyProperties(sessionCaseFilter, filter);
        }
      }
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

  @Override
  public boolean isInProgressFilterDisplayed() {
    return isInProgressFilterDisplayed;
  }

  @Override
  public void setInProgressFilterDisplayed(boolean isInProgressFilterDisplayed) {
    this.isInProgressFilterDisplayed = isInProgressFilterDisplayed;
  }
}
