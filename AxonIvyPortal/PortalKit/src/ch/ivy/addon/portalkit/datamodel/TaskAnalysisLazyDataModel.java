package ch.ivy.addon.portalkit.datamodel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.CaseFilterContainer;
import ch.ivy.addon.portalkit.casefilter.TaskAnalysisCaseFilterContainer;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.enums.TaskAndCaseAnalysisColumn;
import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.service.CaseQueryService;
import ch.ivy.addon.portalkit.service.TaskAnalysisFilterService;
import ch.ivy.addon.portalkit.service.TaskFilterService;
import ch.ivy.addon.portalkit.service.TaskQueryService;
import ch.ivy.addon.portalkit.support.CaseQueryCriteria;
import ch.ivy.addon.portalkit.support.TaskQueryCriteria;
import ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData;
import ch.ivy.addon.portalkit.taskfilter.TaskAnalysisTaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.TaskInProgressByOthersFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskStateFilter;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.OrderByColumnQuery;

public class TaskAnalysisLazyDataModel extends TaskLazyDataModel {
  

  private static final long serialVersionUID = -6615871274830927272L;

  private static final String TASK_COLUMN_PREFIX = "TASK_";

  protected TaskQueryCriteria taskQueryCriteria;

  protected List<TaskFilter> taskFilters;
  protected List<TaskFilter> selectedTaskFilters;
  protected TaskFilterContainer taskFilterContainer;

  private TaskInProgressByOthersFilter inProgressFilter;
  private boolean isInProgressFilterDisplayed = false;
  private TaskAnalysisFilterData selectedTaskAnalysisFilterData;

  private boolean isRelatedTaskDisplayed = false;
  private boolean isNotKeepFilter = false;

  private CaseQueryCriteria caseQueryCriteria;
  protected List<CaseFilter> caseFilters;
  protected List<CaseFilter> selectedCaseFilters;
  protected CaseFilterContainer caseFilterContainer;

  public TaskAnalysisLazyDataModel(String taskWidgetComponentId) {
    super();
    this.taskWidgetComponentId = taskWidgetComponentId;
    data = new ArrayList<>();
    displayedTaskMap = new HashMap<>();
    notDisplayedTaskMap = new HashMap<>();
    selectedTaskFilters = new ArrayList<>();
    selectedCaseFilters = new ArrayList<>();
    searchCriteria = buildCriteria();
    taskQueryCriteria = buildQueryCriteria();
    caseQueryCriteria = buildInitQueryCriteria();
    comparator = comparator(RemoteTask::getId);
    serverId = SecurityServiceUtils.getServerIdFromSession();
    if (inProgressFilter != null) {
      isInProgressFilterDisplayed = true;
    } else {
      inProgressFilter = new TaskInProgressByOthersFilter();
    }

    autoInitForNoAppConfiguration();
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
        if (!taskQueryCriteria.getIncludedStates().contains(TaskState.DONE)) {
          taskQueryCriteria.addIncludedStates(Arrays.asList(TaskState.DONE));
        }
        if (!taskQueryCriteria.getIncludedStates().contains(TaskState.UNASSIGNED)) {
          taskQueryCriteria.addIncludedStates(Arrays.asList(TaskState.UNASSIGNED));
        }
      }
      initFilterContainer();
      taskFilters = taskFilterContainer.getFilters();
      setValuesForStateFilter(taskQueryCriteria);
      if (searchCriteria.getIgnoreInvolvedUser() && !isRelatedTaskDisplayed) {
        TaskStateFilter stateFilter = taskFilterContainer.getStateFilter();
        stateFilter.setSelectedFilteredStatesAtBeginning(new ArrayList<>(stateFilter.getSelectedFilteredStates()));
      }
    }
  }

  @Override
  public List<RemoteTask> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    taskQueryCriteria.setSortField(sortField);
    taskQueryCriteria.setSortDescending(sortOrder == SortOrder.DESCENDING);

    if (first == 0) {
      initializedDataModel(searchCriteria);
    }

    List<RemoteTask> foundTasks = findTasks(first, pageSize, searchCriteria);
    putTasksToNotDisplayedTaskMap(foundTasks);
    List<RemoteTask> notDisplayedTasks = sortTasksInNotDisplayedTaskMap();
    List<RemoteTask> displayedTasks = getDisplayedTasks(notDisplayedTasks, pageSize);
    storeDisplayedTasks(displayedTasks);

    return displayedTasks;
  }

  @Override
  protected List<RemoteTask> sortTasksInNotDisplayedTaskMap() {
    List<RemoteTask> notDisplayedTasks = new ArrayList<>();
    notDisplayedTasks.addAll(notDisplayedTaskMap.values());

    if (taskQueryCriteria.getSortField() == null) {
      taskQueryCriteria.setSortField(TaskAndCaseAnalysisColumn.TASK_ID.name());
    }

    if (taskQueryCriteria.getSortField().startsWith(TASK_COLUMN_PREFIX)) {
      TaskSortField taskSortField = TaskSortField.valueOf(taskQueryCriteria.getSortField().replaceAll(TASK_COLUMN_PREFIX, ""));
      comparator = generateComparatorForTaskColumns(taskSortField);

      if (taskQueryCriteria.isSortDescending()) {
        comparator = comparator.reversed();
      }

      notDisplayedTasks.sort(comparator);
    }
    return notDisplayedTasks;
  }

  private Comparator<RemoteTask> generateComparatorForTaskColumns(TaskSortField taskSortField) {
    switch (taskSortField) {
      case PRIORITY:
        return comparator(RemoteTask::getPriority);
      case CREATION_TIME:
        return comparator(RemoteTask::getStartTimestamp);
      case EXPIRY_TIME:
        return comparatorNullsLast(RemoteTask::getExpiryTimestamp);
      case STATE:
        return comparator(RemoteTask::getState);
      case FINISHED_TIME:
        return comparator(RemoteTask::getEndTimestamp);
      case NAME:
        return comparatorString(RemoteTask::getName);
      case ACTIVATOR:
        return comparator(RemoteTask::getActivatorFullName);
      case CATEGORY:
        return comparatorString(RemoteTask::getCategoryName);
      case DESCRIPTION:
        return comparatorString(RemoteTask::getDescription);
      case WORKER:
        return comparator(RemoteTask::getWorkerFullName);
      default:
        return comparator(RemoteTask::getId);
    }
  }

  /**
   * Initializes TaskQueryCriteria
   * 
   * @return TaskQueryCriteria
   */
  @Override
  protected TaskQueryCriteria buildQueryCriteria() {
    TaskQueryCriteria jsonQuerycriteria = new TaskQueryCriteria();
    jsonQuerycriteria.setQueryForUnassignedTask(false);
    jsonQuerycriteria.setIncludedStates(new ArrayList<>(Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED,
        TaskState.PARKED)));
    jsonQuerycriteria.setSortField(TaskSortField.ID.toString());
    jsonQuerycriteria.setSortDescending(true);
    return jsonQuerycriteria;
  }

  @Override
  public void setSortField(String sortField, boolean sortDescending) {
    taskQueryCriteria.setSortField(sortField);
    taskQueryCriteria.setSortDescending(sortDescending);
  }

  @Override
  public void setCategory(String category) {
    taskQueryCriteria.setCategory(category);
  }

  @Override
  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
    if (ignoreInvolvedUser && !taskQueryCriteria.getIncludedStates().contains(TaskState.DONE)) {
      taskQueryCriteria.addIncludedStates(Arrays.asList(TaskState.DONE));
      setValuesForStateFilter(taskQueryCriteria);
    }
    searchCriteria.setIgnoreInvolvedUser(ignoreInvolvedUser);
  }

  @Override
  public void setTaskId(Long taskId) {
    taskQueryCriteria.setTaskId(taskId);
    taskQueryCriteria.setIncludedStates(new ArrayList<>());
    searchCriteria.setQueryByTaskId(true);
  }

  @Override
  public void setCaseId(Long caseId) {
    taskQueryCriteria.setCaseId(caseId);
  }

  @Override
  public void setQueryByBusinessCaseId(boolean isQueryByBusinessCaseId) {
    taskQueryCriteria.setQueryByBusinessCaseId(isQueryByBusinessCaseId);
  }

  @Override
  public void setInvolvedApplications(String... involvedApplications) {
    searchCriteria.setInvolvedApplications(involvedApplications);
  }

  @Override
  public void setTaskAssigneeType(TaskAssigneeType assigneeType) {
    taskQueryCriteria.setTaskAssigneeType(assigneeType);
  }

  @Override
  public String getSortField() {
    return taskQueryCriteria.getSortField();
  }

  @Override
  public boolean isSortDescending() {
    return taskQueryCriteria.isSortDescending();
  }

  @Override
  public void setIncludedStates(List<TaskState> includedStates) {
    this.taskQueryCriteria.setIncludedStates(includedStates);
    setValuesForStateFilter(this.taskQueryCriteria);
  }

  @Override
  public void addIncludedStates(List<TaskState> includedStates) {
    this.taskQueryCriteria.addIncludedStates(includedStates);
    setValuesForStateFilter(this.taskQueryCriteria);
  }

  public void setTaskQueryCriteria(TaskQueryCriteria queryCriteria) {
    this.taskQueryCriteria = queryCriteria;
  }

  @Override
  public TaskQueryCriteria getQueryCriteria() {
    return taskQueryCriteria;
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
   * Builds and converts TaskQuery to JsonQuery and put it into TaskSearchCriteria.
   * 
   */
  @Override
  protected void buildQueryToSearchCriteria() {
    if (taskQueryCriteria.getTaskQuery() == null) {
      String jsonQuery =
          SubProcessCall.withPath("Functional Processes/BuildTaskJsonQuery").withStartSignature("buildTaskJsonQuery()")
              .call().get("jsonQuery", String.class);
      TaskQuery customizedTaskQuery =
          StringUtils.isNotBlank(jsonQuery) ? TaskQuery.fromJson(jsonQuery) : TaskQuery.create();
      taskQueryCriteria.setTaskQuery(customizedTaskQuery);
    }

    if (taskFilterContainer != null) {
      if (selectedTaskFilters.contains(taskFilterContainer.getStateFilter())) {
        taskQueryCriteria.setIncludedStates(new ArrayList<>());
      } else {
        taskQueryCriteria.setIncludedStates(taskFilterContainer.getStateFilter().getSelectedFilteredStates());
      }
    }
    if (caseFilterContainer != null) {
      if (selectedCaseFilters.contains(caseFilterContainer.getStateFilter())) {
        caseQueryCriteria.setIncludedStates(new ArrayList<>());
      } else {
        caseQueryCriteria.setIncludedStates(caseFilterContainer.getStateFilter().getSelectedFilteredStates());
      }
    }

    searchCriteria.setTaskStartedByAnotherDisplayed(inProgressFilter.getIsTaskInProgressByOthersDisplayed());

    TaskQuery taskQuery = buildTaskQuery();
    CaseQuery caseQuery = buildCaseQuery();
    taskQuery = taskQuery.where().cases(caseQuery);

    String sortField = taskQueryCriteria.getSortField();
    if (sortField.startsWith(TASK_COLUMN_PREFIX)) {
      buildSortTaskQuery(taskQuery);
    }

    extendSort(taskQuery);
    searchCriteria.setJsonQuery(taskQuery.asJson());
  }

  @Override
  protected TaskQuery buildTaskQuery() {
    TaskQuery taskQuery = TaskQueryService.service().createQuery(taskQueryCriteria);
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
    CaseQuery caseQuery = CaseQueryService.service().createQuery(caseQueryCriteria);
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
    TaskAndCaseAnalysisColumn sortColumn = TaskAndCaseAnalysisColumn.valueOf(taskQueryCriteria.getSortField());
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
      if (taskQueryCriteria.isSortDescending()) {
        orderQuery.descendingNullFirst();
      } else {
        orderQuery.ascendingNullLast();
      }
      return;
    }

    if (taskQueryCriteria.isSortDescending()) {
      orderQuery.descending();
    } else {
      orderQuery.ascending();
    }
  }

  @Override
  public void setQueryForUnassignedTask(boolean isQueryForOnlyUnassignedTask) {
    this.taskQueryCriteria.setQueryForUnassignedTask(isQueryForOnlyUnassignedTask);
  }

  @Override
  public void initFilters() throws ReflectiveOperationException {
    initTaskFilters();
    initCaseFilters();
  }

  private void setValuesForCaseStateFilter(CaseQueryCriteria criteria) {
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
  protected CaseQueryCriteria buildInitQueryCriteria() {
    CaseQueryCriteria jsonQueryCriteria = new CaseQueryCriteria();
    jsonQueryCriteria.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.CREATED, CaseState.RUNNING,
        CaseState.DONE)));
    jsonQueryCriteria.setSortField(CaseSortField.ID.toString());
    jsonQueryCriteria.setSortDescending(true);
    return jsonQueryCriteria;
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
      setValuesForCaseStateFilter(caseQueryCriteria);
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
