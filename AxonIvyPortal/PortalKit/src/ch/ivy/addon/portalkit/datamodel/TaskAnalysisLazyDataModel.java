package ch.ivy.addon.portalkit.datamodel;

import java.lang.reflect.InvocationTargetException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.bo.TaskColumnsConfigurationData;
import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.CaseFilterContainer;
import ch.ivy.addon.portalkit.casefilter.TaskAnalysisCaseFilterContainer;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.service.CaseQueryService;
import ch.ivy.addon.portalkit.service.TaskAnalysisFilterService;
import ch.ivy.addon.portalkit.service.TaskColumnsConfigurationService;
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
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivy.ws.addon.TaskSearchCriteria;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskAnalysisLazyDataModel extends LazyDataModel<RemoteTask> {
  

  private static final long serialVersionUID = -6615871274830927272L;

  protected static final int BUFFER_LOAD = 10;
  protected String taskWidgetComponentId;
  protected List<RemoteTask> data;
  protected Map<String, RemoteTask> displayedTaskMap;
  protected Map<String, RemoteTask> notDisplayedTaskMap;

  protected int rowIndex;
  protected TaskSearchCriteria searchCriteria;
  protected TaskQueryCriteria taskQueryCriteria;
  protected Long serverId;
  protected Comparator<RemoteTask> comparator;

  protected String caseName;

  protected List<TaskFilter> taskFilters;
  protected List<TaskFilter> selectedTaskFilters;
  protected TaskFilterContainer taskFilterContainer;

  private TaskInProgressByOthersFilter inProgressFilter;
  private boolean isInProgressFilterDisplayed = false;
  private TaskAnalysisFilterData selectedTaskAnalysisFilterData;

  protected List<String> allColumns = new ArrayList<>();
  protected List<String> selectedColumns = new ArrayList<>();
  private List<String> portalDefaultColumns = Arrays.asList("PRIORITY", "NAME", "ACTIVATOR", "ID", "CREATION_TIME",
      "EXPIRY_TIME", "STATE");
  private List<String> portalRequiredColumns = Arrays.asList("NAME");

  private boolean isAutoHideColumns;
  private boolean isDisableSelectionCheckboxes;
  private boolean isRelatedTaskDisplayed = false;
  private boolean isNotKeepFilter = false;

  private CaseQueryCriteria caseQueryCriteria;

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
    if (shouldSaveAndLoadSessionFilters()) {
      inProgressFilter = UserUtils.getSessionTaskInProgressFilterAttribute();
      if (inProgressFilter != null) {
        isInProgressFilterDisplayed = true;
      } else {
        inProgressFilter = new TaskInProgressByOthersFilter();
      }
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
  protected void initFilterContainer() {
    taskFilterContainer = new TaskAnalysisTaskFilterContainer();
  }

  public void initTaskFilters() throws ReflectiveOperationException {
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
      restoreSessionAdvancedFilters();
    }
  }

  private void restoreSessionAdvancedFilters() throws IllegalAccessException, InvocationTargetException {
    if (shouldSaveAndLoadSessionFilters()) {
      List<TaskFilter> sessionTaskFilters = UserUtils.getSessionTaskAdvancedFilterAttribute();
      for (TaskFilter filter : taskFilters) {
        for (TaskFilter sessionTaskFilter : sessionTaskFilters) {
          copyProperties(sessionTaskFilter, filter);
        }
      }
    }
  }

  private void copyProperties(TaskFilter sessionTaskFilter, TaskFilter filter) throws IllegalAccessException,
      InvocationTargetException {
    if (sessionTaskFilter.getClass() == filter.getClass()) {
      BeanUtils.copyProperties(filter, sessionTaskFilter);
      selectedTaskFilters.add(filter);
    }
  }

  private boolean shouldSaveAndLoadSessionFilters() {
    boolean isValidQueryCriteria =
        (this.taskQueryCriteria == null) || (this.taskQueryCriteria != null && !this.taskQueryCriteria.isQueryForUnassignedTask());
    return isValidQueryCriteria && !isRelatedTaskDisplayed && !isNotKeepFilter;
  }

  @Override
  public List<RemoteTask> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    if (first == 0) {
      initializedDataModel(searchCriteria);
    }

    List<RemoteTask> foundTasks = findTasks(first, pageSize, searchCriteria);
    putTasksToNotDisplayedTaskMap(foundTasks);
    List<RemoteTask> notDisplayedTasks = sortTasksInNotDisplayedTaskMap();
    List<RemoteTask> displayedTasks = getDisplayedTasks(notDisplayedTasks, pageSize);
    storeDisplayedTasks(displayedTasks);

    RequestContext.getCurrentInstance().execute("taskListToolKit.responsive()");
    return displayedTasks;
  }

  private void storeDisplayedTasks(List<RemoteTask> displayedTasks) {
    data.addAll(displayedTasks);
    for (RemoteTask task : displayedTasks) {
      displayedTaskMap.put(keyOfTask(task), task);
    }
  }

  /**
   * Calls the findTasks logic of TaskWidget Html dialog to find tasks.
   * 
   * @param first
   * @param pageSize
   * @param criteria
   * @return List<RemoteTask>
   */
  protected List<RemoteTask> findTasks(int first, int pageSize, TaskSearchCriteria criteria) {
    IvyComponentLogicCaller<List<RemoteTask>> findTaskCaller = new IvyComponentLogicCaller<>();
    int startIndex = first - BUFFER_LOAD;
    int count = pageSize + BUFFER_LOAD;
    if (startIndex < 0) {
      startIndex = 0;
      count = first + pageSize;
    }
    return findTaskCaller.invokeComponentLogic(taskWidgetComponentId, "#{logic.findTasks}", new Object[] {
        startIndex, count, criteria, serverId});
  }

  private void initializedDataModel(TaskSearchCriteria criteria) {
    data.clear();
    displayedTaskMap.clear();
    notDisplayedTaskMap.clear();
    buildQueryToSearchCriteria();
    setRowCount(getTaskCount(criteria));
  }

  private List<RemoteTask> getDisplayedTasks(List<RemoteTask> notDisplayedTasks, int pageSize) {
    int displayedTaskCount = notDisplayedTasks.size() > pageSize ? pageSize : notDisplayedTasks.size();
    List<RemoteTask> displayedTasks = notDisplayedTasks.subList(0, displayedTaskCount);
    for (RemoteTask task : displayedTasks) {
      notDisplayedTaskMap.remove(keyOfTask(task));
    }
    return displayedTasks;
  }

  private void putTasksToNotDisplayedTaskMap(List<RemoteTask> tasks) {
    for (RemoteTask task : tasks) {
      String keyOfTask = keyOfTask(task);
      if (!displayedTaskMap.containsKey(keyOfTask) && !notDisplayedTaskMap.containsKey(keyOfTask)) {
        notDisplayedTaskMap.put(keyOfTask, task);
      }
    }
  }

  private List<RemoteTask> sortTasksInNotDisplayedTaskMap() {
    List<RemoteTask> notDisplayedTasks = new ArrayList<>();
    notDisplayedTasks.addAll(notDisplayedTaskMap.values());
    comparator = comparator(RemoteTask::getId);
    if (TaskSortField.PRIORITY.toString().equalsIgnoreCase(taskQueryCriteria.getSortField())) {
      comparator = comparator(RemoteTask::getPriority);
    } else if (TaskSortField.NAME.toString().equalsIgnoreCase(taskQueryCriteria.getSortField())) {
      comparator = comparatorString(RemoteTask::getName);
    } else if (TaskSortField.ACTIVATOR.toString().equalsIgnoreCase(taskQueryCriteria.getSortField())) {
      comparator = comparatorString(activatorName());
    } else if (TaskSortField.CREATION_TIME.toString().equalsIgnoreCase(taskQueryCriteria.getSortField())) {
      comparator = comparator(RemoteTask::getStartTimestamp);
    } else if (TaskSortField.EXPIRY_TIME.toString().equalsIgnoreCase(taskQueryCriteria.getSortField())) {
      comparator = comparator(RemoteTask::getExpiryTimestamp);
    } else if (TaskSortField.STATE.toString().equalsIgnoreCase(taskQueryCriteria.getSortField())) {
      comparator = comparator(RemoteTask::getState);
    }

    if (taskQueryCriteria.isSortDescending()) {
      comparator = comparator.reversed();
    }

    notDisplayedTasks.sort(comparator);
    return notDisplayedTasks;
  }

  protected Function<RemoteTask, String> activatorName() {
    return r -> {
      if (StringUtils.isNotEmpty(r.getActivatorFullName())) {
        return r.getActivatorFullName();
      }
      return r.getActivatorName();
    };
  }

  protected <U extends Comparable<? super U>> Comparator<RemoteTask> comparator( // NOSONAR
      Function<? super RemoteTask, ? extends U> function) {
    return Comparator.comparing(function, Comparator.nullsFirst(Comparator.naturalOrder()));
  }

  protected Comparator<RemoteTask> comparatorString(Function<? super RemoteTask, String> function) {
    Collator collator = Collator.getInstance(Locale.GERMAN);
    return Comparator.comparing(function, Comparator.nullsLast(collator));
  }

  protected String keyOfTask(RemoteTask task) {
    return "serverId=" + task.getApplicationRegister().getServerId() + ";taskId=" + task.getId();
  }

  /**
   * Calls the countTasks logic of TaskWidget Html dialog to count the number of found tasks.
   * 
   * @param criteria
   * @return task count
   */
  protected int getTaskCount(TaskSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countTaskCaller = new IvyComponentLogicCaller<>();
    Long taskCount =
        countTaskCaller.invokeComponentLogic(taskWidgetComponentId, "#{logic.countTasks}", new Object[] {criteria,
            serverId});
    return taskCount.intValue();
  }

  /**
   * Initializes TaskSearchCriteria
   * 
   * @return TaskSearchCriteria
   */
  protected TaskSearchCriteria buildCriteria() {
    TaskSearchCriteria criteria = new TaskSearchCriteria();
    criteria.setInvolvedUsername(Ivy.session().getSessionUserName());
    return criteria;
  }

  /**
   * Initializes TaskQueryCriteria
   * 
   * @return TaskQueryCriteria
   */
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
  public void setRowIndex(int index) {
    int idx = index;
    if (idx >= data.size()) {
      idx = -1;
    }
    this.rowIndex = idx;
  }

  @Override
  public RemoteTask getRowData() {
    return data.get(rowIndex);
  }

  @Override
  public boolean isRowAvailable() {
    if (data == null) {
      return false;
    }
    return rowIndex >= 0 && rowIndex < data.size();
  }

  public void setSortField(String sortField, boolean sortDescending) {
    taskQueryCriteria.setSortField(sortField);
    taskQueryCriteria.setSortDescending(sortDescending);
  }

  public void setCategory(String category) {
    taskQueryCriteria.setCategory(category);
  }

  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
    if (ignoreInvolvedUser && !taskQueryCriteria.getIncludedStates().contains(TaskState.DONE)) {
      taskQueryCriteria.addIncludedStates(Arrays.asList(TaskState.DONE));
      setValuesForStateFilter(taskQueryCriteria);
    }
    searchCriteria.setIgnoreInvolvedUser(ignoreInvolvedUser);
  }

  public void setInvolvedUsername(String involvedUsername) {
    searchCriteria.setInvolvedUsername(involvedUsername);
  }

  public void setTaskId(Long taskId) {
    taskQueryCriteria.setTaskId(taskId);
    taskQueryCriteria.setIncludedStates(new ArrayList<>());
    searchCriteria.setQueryByTaskId(true);
  }

  public void setCaseId(Long caseId) {
    taskQueryCriteria.setCaseId(caseId);
  }

  public void setServerId(Long serverId) {
    this.serverId = serverId;
  }

  public void setQueryByBusinessCaseId(boolean isQueryByBusinessCaseId) {
    taskQueryCriteria.setQueryByBusinessCaseId(isQueryByBusinessCaseId);
  }

  public void setInvolvedApplications(String... involvedApplications) {
    searchCriteria.setInvolvedApplications(involvedApplications);
  }

  public void setTaskAssigneeType(TaskAssigneeType assigneeType) {
    taskQueryCriteria.setTaskAssigneeType(assigneeType);
  }

  public String getSortField() {
    return taskQueryCriteria.getSortField();
  }

  public boolean isSortDescending() {
    return taskQueryCriteria.isSortDescending();
  }

  public void setIncludedStates(List<TaskState> includedStates) {
    this.taskQueryCriteria.setIncludedStates(includedStates);
    setValuesForStateFilter(this.taskQueryCriteria);
  }

  public void addIncludedStates(List<TaskState> includedStates) {
    this.taskQueryCriteria.addIncludedStates(includedStates);
    setValuesForStateFilter(this.taskQueryCriteria);
  }

  public void setSearchCriteria(TaskSearchCriteria searchCriteria) {
    this.searchCriteria = searchCriteria;
  }

  public void setTaskQueryCriteria(TaskQueryCriteria queryCriteria) {
    this.taskQueryCriteria = queryCriteria;
  }

  public TaskSearchCriteria getSearchCriteria() {
    return searchCriteria;
  }

  public TaskQueryCriteria getQueryCriteria() {
    return taskQueryCriteria;
  }

  public Long getServerId() {
    return serverId;
  }

  public String getCaseName() {
    return caseName;
  }

  public TaskInProgressByOthersFilter getInProgressFilter() {
    return inProgressFilter;
  }

  public void setInProgressFilter(TaskInProgressByOthersFilter inProgressFilter) {
    this.inProgressFilter = inProgressFilter;
  }

  public boolean isInProgressFilterDisplayed() {
    return isInProgressFilterDisplayed;
  }

  public void setInProgressFilterDisplayed(boolean isInProgressFilterDisplayed) {
    this.isInProgressFilterDisplayed = isInProgressFilterDisplayed;
  }

  public void setCaseName(String caseName) {
    this.caseName = caseName;
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

  public void removeFilter(TaskFilter filter) {
    filter.resetValues();
    selectedTaskFilters.remove(filter);
  }

  public void removeFilter(CaseFilter filter) {
    filter.resetValues();
    selectedCaseFilters.remove(filter);
  }
  

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
  public TaskAnalysisFilterData saveFilter(String filterName, FilterType filterType, Long taskFilterGroupId) {
    TaskAnalysisFilterData taskAnalysisFilterData = new TaskAnalysisFilterData();
    List<TaskFilter> taskFilters = new ArrayList<>(selectedTaskFilters);
    addCustomSettingsToTaskFilters(taskFilters);
    taskAnalysisFilterData.setTaskFilters(taskFilters);
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

  private void addCustomSettingsToTaskFilters(List<TaskFilter> taskFilters) {
    if (isInProgressFilterDisplayed) {
      taskFilters.add(inProgressFilter);
    }
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

  @SuppressWarnings("unchecked")
  public void onFilterChange(ValueChangeEvent event) {
    List<TaskFilter> oldSelectedFilters = (List<TaskFilter>) event.getOldValue();
    List<TaskFilter> newSelectedFilters = (List<TaskFilter>) event.getNewValue();
    List<TaskFilter> toggleFilters =
        (List<TaskFilter>) CollectionUtils.subtract(newSelectedFilters, oldSelectedFilters);
    if (CollectionUtils.isNotEmpty(toggleFilters)) {
      toggleFilters.get(0).resetValues();
    }
  }

  public boolean hasReadAllTasksPermisson() {
    return PermissionUtils.checkReadAllTasksPermission();
  }

  public void hideInProgressFilter() {
    inProgressFilter.resetValues();
    isInProgressFilterDisplayed = false;
  }

  /**
   * Builds and converts TaskQuery to JsonQuery and put it into TaskSearchCriteria.
   */
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
    searchCriteria.setJsonQuery(taskQuery.asJson());
  }

  private void autoInitForNoAppConfiguration() {
    String applicationName = StringUtils.EMPTY;
    String applicationNameFromRequest =
        Optional.ofNullable(Ivy.request().getApplication()).map(IApplication::getName).orElse(StringUtils.EMPTY);
    if (!IApplication.PORTAL_APPLICATION_NAME.equals(applicationNameFromRequest)) {
      applicationName = applicationNameFromRequest;
    }
    if (StringUtils.isNotBlank(applicationName)) {
      setInvolvedApplications(applicationName);
    }
  }

  private void setValuesForStateFilter(TaskQueryCriteria querycriteria) {
    if (taskFilterContainer != null) {
      taskFilterContainer.getStateFilter().setFilteredStates(new ArrayList<>(querycriteria.getIncludedStates()));
      taskFilterContainer.getStateFilter().setSelectedFilteredStates(querycriteria.getIncludedStates());
      taskFilterContainer.getStateFilter().setSelectedFilteredStatesAtBeginning(querycriteria.getIncludedStates());
    }
  }

  private TaskQuery buildTaskQuery() {
    TaskQuery taskQuery = TaskQueryService.service().createQuery(taskQueryCriteria);
    IFilterQuery filterQuery = taskQuery.where();
    selectedTaskFilters.forEach(selectedFilter -> {
      TaskQuery subQuery = selectedFilter.buildQuery();
      if (subQuery != null) {
        filterQuery.and(subQuery);
      }
    });
    if (shouldSaveAndLoadSessionFilters()) {
      UserUtils.setSessionTaskAdvancedFilterAttribute(selectedTaskFilters);
      if (isInProgressFilterDisplayed) {
        UserUtils.setSessionTaskInProgressFilterAttribute(inProgressFilter);
      } else {
        UserUtils.setSessionTaskInProgressFilterAttribute(null);
      }
    }
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


  protected void initColumnsConfiguration() {
    allColumns.addAll(getDefaultColumns());
    initSelectedColumns();
  }

  private void initSelectedColumns() {
    TaskColumnsConfigurationService service = new TaskColumnsConfigurationService();
    Long userId = Optional.ofNullable(Ivy.session().getSessionUser()).map(IUser::getId).orElse(null);
    Long applicationId = Ivy.request().getApplication().getId();
    Long taskColumnsConfigDataId = Ivy.request().getProcessModel().getId();
    if (userId != null) {
      TaskColumnsConfigurationData configData =
          service.getConfiguration(serverId, applicationId, userId, taskColumnsConfigDataId);
      if (configData != null) {
        selectedColumns = configData.getSelectedColumns();
        isAutoHideColumns = configData.isAutoHideColumns();
      }
    }
    if (selectedColumns.isEmpty()) {
      selectedColumns.addAll(getDefaultColumns());
      isAutoHideColumns = true;
    }
    setDisableSelectionCheckboxes(isAutoHideColumns);
  }

  /**
   * <p>
   * Your customized data model needs to override this method if your customized task list has new columns/fields.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * 
   * return Arrays.asList("PRIORITY", "NAME", "ID" , "ACTIVATOR", "CREATION_TIME", "EXPIRY_TIME", "customVarcharField5", "customVarcharField1");
   * 
   * </pre></code> This list is the list of sortFields in TaskColumnHeader Portal component when you use it to add new
   * column headers Also the list of checkboxes in config columns panel
   * </p>
   * 
   * @return default columns
   */
  protected List<String> getDefaultColumns() {
    return portalDefaultColumns;
  }

  /**
   * <p>
   * In case you adds new columns, these columns need cms to show in config columns panel
   * </p>
   * <p>
   * You can either add new entry to default folder below in PortalStyle or override this method to create your own
   * folder column must be the same with sortField
   * </p>
   * 
   * @param column
   * 
   * @return column label
   */
  public String getColumnLabel(String column) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/" + column);
  }

  public void saveColumnsConfiguration() {
    selectedColumns.addAll(portalRequiredColumns);
    setAutoHideColumns(isDisableSelectionCheckboxes);
    TaskColumnsConfigurationService service = new TaskColumnsConfigurationService();
    Long applicationId = Ivy.request().getApplication().getId();
    Long taskColumnsConfigDataId = Ivy.request().getProcessModel().getId();
    TaskColumnsConfigurationData taskColumnsConfigurationData =
        service.getConfiguration(serverId, applicationId, Ivy.session().getSessionUser().getId(),
            taskColumnsConfigDataId);
    if (taskColumnsConfigurationData != null) {
      updateTaskColumnsConfigurationData(taskColumnsConfigurationData);
    } else {
      taskColumnsConfigurationData = createNewTaskColumnsConfigurationData();
    }
    service.save(taskColumnsConfigurationData);
    initSelectedColumns();
  }

  private TaskColumnsConfigurationData createNewTaskColumnsConfigurationData() {
    TaskColumnsConfigurationData taskColumnsConfigurationData = new TaskColumnsConfigurationData();
    taskColumnsConfigurationData.setTaskColumnsConfigDataId(Ivy.request().getProcessModel().getId());
    taskColumnsConfigurationData.setUserId(Ivy.session().getSessionUser().getId());
    taskColumnsConfigurationData.setApplicationId(Ivy.request().getApplication().getId());
    taskColumnsConfigurationData.setServerId(serverId);
    updateTaskColumnsConfigurationData(taskColumnsConfigurationData);
    return taskColumnsConfigurationData;
  }

  private void updateTaskColumnsConfigurationData(TaskColumnsConfigurationData taskColumnsConfigurationData) {
    taskColumnsConfigurationData.setAutoHideColumns(isAutoHideColumns);
    if (isAutoHideColumns) {
      taskColumnsConfigurationData.setSelectedColumns(getDefaultColumns());
    } else {
      taskColumnsConfigurationData.setSelectedColumns(selectedColumns);
    }
  }

  public void setSelectedColumns(List<String> selectedColumns) {
    this.selectedColumns = selectedColumns;
  }

  public List<String> getSelectedColumns() {
    return selectedColumns;
  }

  public List<String> getAllColumns() {
    return allColumns;
  }

  public boolean isSelectedColumn(String column) {
    return selectedColumns.stream().anyMatch(selectedcolumn -> selectedcolumn.equalsIgnoreCase(column));
  }

  public List<String> getPortalRequiredColumns() {
    return portalRequiredColumns;
  }

  public boolean isAutoHideColumns() {
    return isAutoHideColumns;
  }

  public void setAutoHideColumns(boolean isAutoHideColumns) {
    this.isAutoHideColumns = isAutoHideColumns;
  }

  public boolean isDisableSelectionCheckboxes() {
    return isDisableSelectionCheckboxes;
  }

  public void setDisableSelectionCheckboxes(boolean isDisableSelectionCheckboxes) {
    this.isDisableSelectionCheckboxes = isDisableSelectionCheckboxes;
  }

  public boolean isRelatedTaskDisplayed() {
    return isRelatedTaskDisplayed;
  }

  public void setRelatedTaskDisplayed(boolean isRelatedTaskDisplayed) {
    this.isRelatedTaskDisplayed = isRelatedTaskDisplayed;
  }

  public boolean isNotKeepFilter() {
    return isNotKeepFilter;
  }

  public void setNotKeepFilter(boolean isNotKeepFilter) {
    this.isNotKeepFilter = isNotKeepFilter;
  }

  public void setQueryForUnassignedTask(boolean isQueryForOnlyUnassignedTask) {
    this.taskQueryCriteria.setQueryForUnassignedTask(isQueryForOnlyUnassignedTask);
  }
  
  ////////////=====================
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
  
  protected List<CaseFilter> caseFilters;
  protected List<CaseFilter> selectedCaseFilters;
  protected CaseFilterContainer caseFilterContainer;
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
  
  private boolean compactMode;

  public boolean isCompactMode() {
    return compactMode;
  }

  public void setCompactMode(boolean compactMode) {
    this.compactMode = compactMode;
  }
  
}
