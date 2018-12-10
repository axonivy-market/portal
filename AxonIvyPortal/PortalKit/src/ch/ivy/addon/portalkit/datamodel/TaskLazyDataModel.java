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
import ch.ivy.addon.portalkit.bo.TaskColumnsConfiguration;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.service.TaskColumnsConfigurationService;
import ch.ivy.addon.portalkit.service.TaskFilterService;
import ch.ivy.addon.portalkit.service.TaskQueryService;
import ch.ivy.addon.portalkit.support.TaskQueryCriteria;
import ch.ivy.addon.portalkit.taskfilter.DefaultTaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
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
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskLazyDataModel extends LazyDataModel<RemoteTask> {

  private static final long serialVersionUID = -6615871274830927272L;

  protected static final int BUFFER_LOAD = 10;
  protected String taskWidgetComponentId;
  protected String caseName;
  
  protected Map<String, RemoteTask> displayedTaskMap;
  protected Map<String, RemoteTask> notDisplayedTaskMap;

  protected int rowIndex;
  protected Long serverId;
  protected Comparator<RemoteTask> comparator;

  protected TaskSearchCriteria searchCriteria;
  protected TaskQueryCriteria queryCriteria;
  protected TaskFilterContainer filterContainer;
  private TaskInProgressByOthersFilter inProgressFilter;
  private TaskFilterData selectedTaskFilterData;

  protected List<TaskFilter> filters;
  protected List<TaskFilter> selectedFilters;
  protected List<RemoteTask> data;
  protected List<String> allColumns = new ArrayList<>();
  protected List<String> selectedColumns = new ArrayList<>();
  private List<String> portalDefaultColumns = Arrays.asList("PRIORITY", 
                                                            "NAME", 
                                                            "ACTIVATOR", 
                                                            "ID", 
                                                            "CREATION_TIME", 
                                                            "EXPIRY_TIME", 
                                                            "STATE");
  private List<String> portalRequiredColumns = Arrays.asList("NAME");

  protected boolean compactMode;
  private boolean isInProgressFilterDisplayed = false;
  private boolean isAutoHideColumns;
  private boolean isDisableSelectionCheckboxes;
  private boolean isRelatedTaskDisplayed = false;
  private boolean isNotKeepFilter = false;
  private boolean isMobile = false;

  public TaskLazyDataModel(String taskWidgetComponentId) {
    super();
    this.taskWidgetComponentId = taskWidgetComponentId;
    data = new ArrayList<>();
    displayedTaskMap = new HashMap<>();
    notDisplayedTaskMap = new HashMap<>();
    selectedFilters = new ArrayList<>();
    searchCriteria = buildCriteria();
    queryCriteria = buildQueryCriteria();
    comparator = comparator(RemoteTask::getId);
    serverId = SecurityServiceUtils.getServerIdFromSession();
    if (shouldSaveAndLoadSessionFilters() && !isMobile) {
      selectedTaskFilterData = UserUtils.getSessionSelectedTaskFilterSetAttribute();
      inProgressFilter = UserUtils.getSessionTaskInProgressFilterAttribute();
      if (inProgressFilter != null) {
        isInProgressFilterDisplayed = true;
      } else {
        inProgressFilter = new TaskInProgressByOthersFilter();
      }
    }
    autoInitForNoAppConfiguration();
  }

  public TaskLazyDataModel() {
    this("task-widget");
  }

  public TaskLazyDataModel(Boolean isMobile) {
    this("task-widget");
    this.setMobile(isMobile);
    queryCriteria.setMobile(isMobile);
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
    filterContainer = new DefaultTaskFilterContainer();
  }

  public void initFilters() throws ReflectiveOperationException {
    if (filterContainer == null) {
      if (isRelatedTaskDisplayed) {
        if (!queryCriteria.getIncludedStates().contains(TaskState.DONE)) {
          queryCriteria.addIncludedStates(Arrays.asList(TaskState.DONE));
        }
        if (!queryCriteria.getIncludedStates().contains(TaskState.UNASSIGNED)) {
          queryCriteria.addIncludedStates(Arrays.asList(TaskState.UNASSIGNED));
        }
      }
      initFilterContainer();
      filters = filterContainer.getFilters();
      setValuesForStateFilter(queryCriteria);
      if (searchCriteria.getIgnoreInvolvedUser() && !isRelatedTaskDisplayed) {
        TaskStateFilter stateFilter = filterContainer.getStateFilter();
        stateFilter.setSelectedFilteredStatesAtBeginning(new ArrayList<>(stateFilter.getSelectedFilteredStates()));
      }
      restoreSessionAdvancedFilters();
    }
  }

  private void restoreSessionAdvancedFilters() throws IllegalAccessException, InvocationTargetException {
    if (shouldSaveAndLoadSessionFilters()) {
      List<TaskFilter> sessionTaskFilters = UserUtils.getSessionTaskAdvancedFilterAttribute();
      for (TaskFilter filter : filters) {
        for (TaskFilter sessionTaskFilter : sessionTaskFilters) {
          copyProperties(sessionTaskFilter, filter);
        }
      }
    }
  }

  private void copyProperties(TaskFilter sessionTaskFilter, TaskFilter filter)
      throws IllegalAccessException, InvocationTargetException {
    if (sessionTaskFilter.getClass() == filter.getClass()) {
      BeanUtils.copyProperties(filter, sessionTaskFilter);
      selectedFilters.add(filter);
    }
  }

  private boolean shouldSaveAndLoadSessionFilters() {
    boolean isValidQueryCriteria =
        (this.queryCriteria == null) || (this.queryCriteria != null && !this.queryCriteria.isQueryForUnassignedTask());
    return isValidQueryCriteria && !isRelatedTaskDisplayed && !isNotKeepFilter;
  }

  @Override
  public List<RemoteTask> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    if (first == 0) {
      initializedDataModel(searchCriteria);
      if (!isMobile) {
        RequestContext.getCurrentInstance().execute("updateTaskCount()");
      }
    }

    List<RemoteTask> foundTasks = findTasks(first, pageSize, searchCriteria);
    putTasksToNotDisplayedTaskMap(foundTasks);
    List<RemoteTask> notDisplayedTasks = sortTasksInNotDisplayedTaskMap();
    List<RemoteTask> displayedTasks = getDisplayedTasks(notDisplayedTasks, pageSize);
    storeDisplayedTasks(displayedTasks);

    if (!isMobile) {
      RequestContext.getCurrentInstance().execute("taskListToolKit.responsive()");
    }

    return displayedTasks;
  }

  protected void storeDisplayedTasks(List<RemoteTask> displayedTasks) {
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
    return findTaskCaller.invokeComponentLogic(taskWidgetComponentId, "#{logic.findTasks}",
        new Object[] {startIndex, count, criteria, serverId});
  }

  protected void initializedDataModel(TaskSearchCriteria criteria) {
    criteria.setInvolvedUsername(Ivy.session().getSessionUserName());
    data.clear();
    displayedTaskMap.clear();
    notDisplayedTaskMap.clear();
    buildQueryToSearchCriteria();
    setRowCount(getTaskCount(criteria));
  }

  protected List<RemoteTask> getDisplayedTasks(List<RemoteTask> notDisplayedTasks, int pageSize) {
    int displayedTaskCount = notDisplayedTasks.size() > pageSize ? pageSize : notDisplayedTasks.size();
    List<RemoteTask> displayedTasks = notDisplayedTasks.subList(0, displayedTaskCount);
    for (RemoteTask task : displayedTasks) {
      notDisplayedTaskMap.remove(keyOfTask(task));
    }
    return displayedTasks;
  }

  protected void putTasksToNotDisplayedTaskMap(List<RemoteTask> tasks) {
    for (RemoteTask task : tasks) {
      String keyOfTask = keyOfTask(task);
      if (!displayedTaskMap.containsKey(keyOfTask) && !notDisplayedTaskMap.containsKey(keyOfTask)) {
        notDisplayedTaskMap.put(keyOfTask, task);
      }
    }
  }

  protected List<RemoteTask> sortTasksInNotDisplayedTaskMap() {
    List<RemoteTask> notDisplayedTasks = new ArrayList<>();
    notDisplayedTasks.addAll(notDisplayedTaskMap.values());
    comparator = comparator(RemoteTask::getId);
    if (TaskSortField.PRIORITY.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparator(RemoteTask::getPriority);
    } else if (TaskSortField.NAME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparatorString(RemoteTask::getName);
    } else if (TaskSortField.ACTIVATOR.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparator(activatorName());
    } else if (TaskSortField.CREATION_TIME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparator(RemoteTask::getStartTimestamp);
    } else if (TaskSortField.EXPIRY_TIME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparatorNullsLast(RemoteTask::getExpiryTimestamp);
    } else if (TaskSortField.STATE.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparator(RemoteTask::getState);
    } else {
      extendSortTasksInNotDisplayedTaskMap();
    }

    if (queryCriteria.isSortDescending()) {
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

  protected <U extends Comparable<? super U>> Comparator<RemoteTask> comparatorNullsLast( // NOSONAR
      Function<? super RemoteTask, ? extends U> function) {
    return Comparator.comparing(function, Comparator.nullsLast(Comparator.naturalOrder()));
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
    Long taskCount = countTaskCaller.invokeComponentLogic(taskWidgetComponentId, "#{logic.countTasks}",
        new Object[] {criteria, serverId});
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
    jsonQuerycriteria
        .setIncludedStates(new ArrayList<>(Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED)));
    jsonQuerycriteria.setSortField(TaskSortField.ID.toString());
    jsonQuerycriteria.setSortDescending(true);
    if (shouldSaveAndLoadSessionFilters()) {
      jsonQuerycriteria.setKeyword(UserUtils.getSessionTaskKeywordFilterAttribute());
    }
    return jsonQuerycriteria;
  }

  /**
   * <p>
   * If your customized task list has new columns/fields, please extend the {@code taskQuery}
   * parameter with the sort query for these fields and also override the
   * "extendSortTasksInNotDisplayedTaskMap" method.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * if ("CustomVarcharField5".equalsIgnoreCase(queryCriteria.getSortField())) {
   *   if (queryCriteria.isSortDescending()) {
   *     taskQuery.orderBy().customVarCharField5().descending();
   *   } else {
   *     taskQuery.orderBy().customVarCharField5();
   *   }
   * }
   * </pre></code>
   * </p>
   * 
   * @param taskQuery
   */
  protected void extendSort(@SuppressWarnings("unused") TaskQuery taskQuery) {
    // Placeholder for customization
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
    queryCriteria.setSortField(sortField);
    queryCriteria.setSortDescending(sortDescending);
  }

  public void setCategory(String category) {
    queryCriteria.setCategory(category);
  }

  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
    if (ignoreInvolvedUser && !queryCriteria.getIncludedStates().contains(TaskState.DONE)) {
      queryCriteria.addIncludedStates(Arrays.asList(TaskState.DONE));
      setValuesForStateFilter(queryCriteria);
    }
    searchCriteria.setIgnoreInvolvedUser(ignoreInvolvedUser);
  }

  public void setInvolvedUsername(String involvedUsername) {
    searchCriteria.setInvolvedUsername(involvedUsername);
  }

  public void setTaskId(Long taskId) {
    queryCriteria.setTaskId(taskId);
    queryCriteria.setIncludedStates(new ArrayList<>());
    searchCriteria.setQueryByTaskId(true);
  }

  public void setCaseId(Long caseId) {
    queryCriteria.setCaseId(caseId);
  }

  public void setServerId(Long serverId) {
    this.serverId = serverId;
  }

  public void setQueryByBusinessCaseId(boolean isQueryByBusinessCaseId) {
    queryCriteria.setQueryByBusinessCaseId(isQueryByBusinessCaseId);
  }

  public void setInvolvedApplications(String... involvedApplications) {
    searchCriteria.setInvolvedApplications(involvedApplications);
  }

  public void setTaskAssigneeType(TaskAssigneeType assigneeType) {
    queryCriteria.setTaskAssigneeType(assigneeType);
  }

  public String getSortField() {
    return queryCriteria.getSortField();
  }

  public boolean isSortDescending() {
    return queryCriteria.isSortDescending();
  }

  public void setIncludedStates(List<TaskState> includedStates) {
    this.queryCriteria.setIncludedStates(includedStates);
    setValuesForStateFilter(this.queryCriteria);
  }

  public void addIncludedStates(List<TaskState> includedStates) {
    this.queryCriteria.addIncludedStates(includedStates);
    setValuesForStateFilter(this.queryCriteria);
  }

  public void setSearchCriteria(TaskSearchCriteria searchCriteria) {
    this.searchCriteria = searchCriteria;
  }

  public void setQueryCriteria(TaskQueryCriteria queryCriteria) {
    this.queryCriteria = queryCriteria;
  }

  public TaskSearchCriteria getSearchCriteria() {
    return searchCriteria;
  }

  public TaskQueryCriteria getQueryCriteria() {
    return queryCriteria;
  }

  public Long getServerId() {
    return serverId;
  }

  public boolean isCompactMode() {
    return compactMode;
  }

  public void setCompactMode(boolean compactMode) {
    this.compactMode = compactMode;
    if (compactMode) {
      selectedFilters.clear();
    }
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

  public List<TaskFilter> getFilters() {
    return filters;
  }

  public List<TaskFilter> getSelectedFilters() {
    return selectedFilters;
  }

  public void setSelectedFilters(List<TaskFilter> selectedFilters) {
    this.selectedFilters = selectedFilters;
  }

  public TaskFilterContainer getFilterContainer() {
    return filterContainer;
  }

  public void setFilterContainer(TaskFilterContainer filterContainer) {
    this.filterContainer = filterContainer;
  }

  public TaskFilterData getSelectedTaskFilterData() {
    return selectedTaskFilterData;
  }

  public void setSelectedTaskFilterData(TaskFilterData selectedTaskFilterData) {
    this.selectedTaskFilterData = selectedTaskFilterData;
  }

  public void removeFilter(TaskFilter filter) {
    filter.resetValues();
    selectedFilters.remove(filter);
    resetFilterData();
  }


  public void resetFilters() {
    for (TaskFilter selectedFilter : selectedFilters) {
      selectedFilter.resetValues();
    }
    selectedFilters = new ArrayList<>();
    selectedTaskFilterData = null;
  }

  /**
   * Save all filter settings to business data
   * 
   * @param filterName
   * @param filterType
   * @param taskFilterGroupId
   * @return TaskFilterData
   */
  public TaskFilterData saveFilter(String filterName, FilterType filterType, Long taskFilterGroupId) {
    TaskFilterData taskFilterData = new TaskFilterData();
    List<TaskFilter> taskFilters = new ArrayList<>(selectedFilters);
    addCustomSettingsToTaskFilters(taskFilters);
    taskFilterData.setFilters(taskFilters);
    taskFilterData.setKeyword(queryCriteria.getKeyword());
    taskFilterData.setUserId(Ivy.session().getSessionUser().getId());
    taskFilterData.setFilterGroupId(taskFilterGroupId);
    taskFilterData.setFilterName(filterName);
    taskFilterData.setType(filterType);
    TaskFilterService taskFilterService = new TaskFilterService();
    BusinessDataInfo<TaskFilterData> info = taskFilterService.save(taskFilterData);
    taskFilterData = taskFilterService.findById(info.getId());
    UserUtils.setSessionSelectedTaskFilterSetAttribute(taskFilterData);
    return taskFilterData;
  }

  public void addCustomSettingsToTaskFilters(List<TaskFilter> taskFilters) {
    if (isInProgressFilterDisplayed) {
      taskFilters.add(inProgressFilter);
    }
  }

  /**
   * Apply filter settings loaded from business data to this {@link #TaskLazyDataModel}
   * 
   * @param taskFilterData
   * @throws ReflectiveOperationException
   */
  public void applyFilter(TaskFilterData taskFilterData) throws ReflectiveOperationException {
    selectedTaskFilterData = taskFilterData;
    new TaskFilterService().applyFilter(this, taskFilterData);
    applyCustomSettings(taskFilterData);
  }

  private void applyCustomSettings(TaskFilterData taskFilterData) throws ReflectiveOperationException {
    queryCriteria.setKeyword(taskFilterData.getKeyword());
    isInProgressFilterDisplayed = false;
    inProgressFilter = new TaskInProgressByOthersFilter();
    for (TaskFilter savedTaskFilter : taskFilterData.getFilters()) {
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
    resetFilterData();
  }

  public void onKeywordChange() {
    resetFilterData();
  }

  private void resetFilterData() {
    if (this.selectedTaskFilterData != null) {
      this.selectedTaskFilterData = null;
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
    if (queryCriteria.getTaskQuery() == null) {
      String jsonQuery = SubProcessCall.withPath("Functional Processes/BuildTaskJsonQuery")
          .withStartSignature("buildTaskJsonQuery(Boolean)").withParam("isQueryForHomePage", compactMode).call()
          .get("jsonQuery", String.class);
      TaskQuery customizedTaskQuery =
          StringUtils.isNotBlank(jsonQuery) ? TaskQuery.fromJson(jsonQuery) : TaskQuery.create();
      queryCriteria.setTaskQuery(customizedTaskQuery);
    }

    if (compactMode) {
      queryCriteria
          .setIncludedStates(new ArrayList<>(Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED)));
    } else {
      if (filterContainer != null) {
        if (selectedFilters.contains(filterContainer.getStateFilter())) {
          queryCriteria.setIncludedStates(new ArrayList<>());
        } else {
          queryCriteria.setIncludedStates(filterContainer.getStateFilter().getSelectedFilteredStates());
        }
      }

      searchCriteria.setTaskStartedByAnotherDisplayed(inProgressFilter.getIsTaskInProgressByOthersDisplayed());
    }

    TaskQuery taskQuery = buildTaskQuery();
    extendSort(taskQuery);
    searchCriteria.setJsonQuery(taskQuery.asJson());
  }

  /**
   * <p>
   * Your customized data model needs to override this method if your customized task list has new
   * columns/fields.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * import ch.ivy.addon.portalkit.bo.RemoteTask;
   * 
   * // The value of queryCriteria.getSortField() is defined in the TaskColumnHeader Portal component when you use it to add new column headers.
   * if ("CustomVarcharField5".equalsIgnoreCase(queryCriteria.getSortField())) {
   * 
   *   // comparatorString(...): String, comparator(...): others.
   *   comparator = comparatorString(RemoteTask::getCustomVarCharField5);
   * }
   * </pre></code>
   * </p>
   */
  protected void extendSortTasksInNotDisplayedTaskMap() {
    // Placeholder for customization
  }

  protected void autoInitForNoAppConfiguration() {
    String applicationName = StringUtils.EMPTY;
    String applicationNameFromRequest =
        Optional.ofNullable(Ivy.request().getApplication()).map(IApplication::getName).orElse(StringUtils.EMPTY);
    if (!PortalConstants.PORTAL_APPLICATION_NAME.equals(applicationNameFromRequest)) {
      applicationName = applicationNameFromRequest;
    }
    if (StringUtils.isNotBlank(applicationName)) {
      setInvolvedApplications(applicationName);
    }
  }

  protected void setValuesForStateFilter(TaskQueryCriteria querycriteria) {
    if (filterContainer != null) {
      filterContainer.getStateFilter().setFilteredStates(new ArrayList<>(querycriteria.getIncludedStates()));
      filterContainer.getStateFilter().setSelectedFilteredStates(querycriteria.getIncludedStates());
      filterContainer.getStateFilter().setSelectedFilteredStatesAtBeginning(querycriteria.getIncludedStates());
    }
  }

  protected TaskQuery buildTaskQuery() {
    TaskQuery taskQuery = TaskQueryService.service().createQuery(queryCriteria);
    IFilterQuery filterQuery = taskQuery.where();
    selectedFilters.forEach(selectedFilter -> {
      TaskQuery subQuery = selectedFilter.buildQuery();
      if (subQuery != null) {
        filterQuery.and(subQuery);
      }
    });
    if (shouldSaveAndLoadSessionFilters()) {
      UserUtils.setSessionSelectedTaskFilterSetAttribute(selectedTaskFilterData);
      UserUtils.setSessionTaskKeywordFilterAttribute(queryCriteria.getKeyword());
      if (!compactMode) {
        UserUtils.setSessionTaskAdvancedFilterAttribute(selectedFilters);
        if (isInProgressFilterDisplayed) {
          UserUtils.setSessionTaskInProgressFilterAttribute(inProgressFilter);
        } else {
          UserUtils.setSessionTaskInProgressFilterAttribute(null);
        }
      }
    }
    return taskQuery;
  }


  public void initColumnsConfiguration() {
    if (CollectionUtils.isEmpty(allColumns)) {
      allColumns.addAll(getDefaultColumns());
      initSelectedColumns();
    }
  }

  protected void initSelectedColumns() {
    TaskColumnsConfigurationService service = new TaskColumnsConfigurationService();
    Long userId = Optional.ofNullable(Ivy.session().getSessionUser()).map(IUser::getId).orElse(null);
    Long applicationId = Ivy.request().getApplication().getId();
    Long processModelId = Ivy.request().getProcessModel().getId();
    if (userId != null) {
      TaskColumnsConfiguration configData = service.getConfiguration(serverId, applicationId, userId, processModelId);
      if (configData != null) {
        selectedColumns = configData.getSelectedColumns();
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
   * Your customized data model needs to override this method if your customized task list has new
   * columns/fields.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * 
   * return Arrays.asList("PRIORITY", "NAME", "ID" , "ACTIVATOR", "CREATION_TIME", "EXPIRY_TIME", "customVarcharField5", "customVarcharField1");
   * 
   * </pre></code> This list is the list of sortFields in TaskColumnHeader Portal component when you
   * use it to add new column headers Also the list of checkboxes in config columns panel
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
   * You can either add new entry to default folder below in PortalStyle or override this method to
   * create your own folder column must be the same with sortField
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
    Long processModelId = Ivy.request().getProcessModel().getId();
    TaskColumnsConfiguration taskColumnsConfiguration =
        service.getConfiguration(serverId, applicationId, Ivy.session().getSessionUser().getId(), processModelId);
    if (taskColumnsConfiguration != null) {
      updateTaskColumnsConfiguration(taskColumnsConfiguration);
    } else {
      taskColumnsConfiguration = createNewTaskColumnsConfigurationData();
    }
    service.save(taskColumnsConfiguration);
    initSelectedColumns();
  }

  private TaskColumnsConfiguration createNewTaskColumnsConfigurationData() {
    TaskColumnsConfiguration taskColumnsConfiguration = new TaskColumnsConfiguration();
    taskColumnsConfiguration.setProcessModelId(Ivy.request().getProcessModel().getId());
    taskColumnsConfiguration.setUserId(Ivy.session().getSessionUser().getId());
    taskColumnsConfiguration.setApplicationId(Ivy.request().getApplication().getId());
    taskColumnsConfiguration.setServerId(serverId);
    updateTaskColumnsConfiguration(taskColumnsConfiguration);
    return taskColumnsConfiguration;
  }

  private void updateTaskColumnsConfiguration(TaskColumnsConfiguration taskColumnsConfiguration) {
    taskColumnsConfiguration.setAutoHideColumns(isAutoHideColumns);
    if (isAutoHideColumns) {
      taskColumnsConfiguration.setSelectedColumns(getDefaultColumns());
    } else {
      taskColumnsConfiguration.setSelectedColumns(selectedColumns);
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
    this.queryCriteria.setQueryForUnassignedTask(isQueryForOnlyUnassignedTask);
  }

  public boolean isMobile() {
    return isMobile;
  }

  public void setMobile(boolean isMobile) {
    this.isMobile = isMobile;
  }

  /**
   * This is default of sort item in mobile, override it if you want to customize it
   * 
   * IMPORTANT: Item in this list must follow pattern : column name + "_ASC" or column name + "_DESC"
   * 
   * E.g your customize portal column are  Arrays.asList{"PRIORITY", "NAME", "ID" , "ACTIVATOR", "CREATION_TIME", "EXPIRY_TIME", "customVarcharField5", "customVarcharField1"}
   * You can have sort fields like: return Arrays.asList("CREATION_TIME_ASC", "CREATION_TIME_DESC", "customVarcharField5_ASC", "customVarcharField5_DESC", "customVarcharField1_ASC", "customVarcharField1_DESC"}
   * @return list of sort criteria for mobile
   */
  public List<String> getPortalTaskMobileSort() {
    return Arrays.asList(
        "CREATION_TIME_ASC", 
        "CREATION_TIME_DESC", 
        "EXPIRY_TIME_ASC", 
        "EXPIRY_TIME_DESC",
        "PRIORITY_ASC", 
        "PRIORITY_DESC");
  }

  /**
   * Sort field label in mobile
   * Override this method and return cms in your project
   * 
   * Example you have custome sort fields like Arrays.asList("CREATION_TIME_ASC", "CREATION_TIME_DESC", "customVarcharField5_ASC", "customVarcharField5_DESC", "customVarcharField1_ASC", "customVarcharField1_DESC"}
   * 
   * Then create CMS folder in your project 
   * 
   * sortFields/customized/CREATION_TIME_ASC
   * sortFields/customized/CREATION_TIME_DESC
   * sortFields/customized/customVarcharField5_ASC
   * sortFields/customized/customVarcharField5_DESC
   * sortFields/customized/customVarcharField1_ASC
   * sortFields/customized/customVarcharField1_DESC
   * 
   * Override this method: return Ivy.cms().co("/sortFields/customized/" + fieldName);
   * @param fieldName
   * @return Sort field label in mobile
   */
  public String getSortFieldLabel(String fieldName) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/sortFields/" + fieldName);
  }

  public void sort(String sortField) {
    if (StringUtils.isNotBlank(sortField) && sortField.length() > 3) {
      boolean asc = StringUtils.equalsIgnoreCase("asc", sortField.substring(sortField.length() - 3));
      String sortColumn = StringUtils.substring(sortField, 0, asc? sortField.length() - 4 : sortField.length() - 5);
      if (getDefaultColumns().contains(sortColumn)) {
        setSortField(sortColumn, !asc);
      }
    }
  }
}
