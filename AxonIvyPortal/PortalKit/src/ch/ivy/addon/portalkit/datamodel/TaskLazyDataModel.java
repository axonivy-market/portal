package ch.ivy.addon.portalkit.datamodel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bo.TaskColumnsConfiguration;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.service.TaskColumnsConfigurationService;
import ch.ivy.addon.portalkit.service.TaskFilterService;
import ch.ivy.addon.portalkit.taskfilter.DefaultTaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.TaskCategoryFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivy.addon.portalkit.taskfilter.TaskInProgressByOthersFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskStateFilter;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskLazyDataModel extends LazyDataModel<ITask> {

  private static final long serialVersionUID = -6615871274830927272L;

  protected String taskWidgetComponentId;
  protected String caseName;
  protected int rowIndex;
  protected TaskSearchCriteria criteria;
  protected List<ITask> data;
  
  protected TaskFilterContainer filterContainer;
  protected TaskInProgressByOthersFilter inProgressFilter;
  protected TaskFilterData selectedTaskFilterData;

  protected List<TaskFilter> filters;
  protected List<TaskFilter> selectedFilters;
  protected List<String> allColumns = new ArrayList<>();
  protected List<String> selectedColumns = new ArrayList<>();
  protected List<String> portalDefaultColumns =
      Arrays.asList("PRIORITY", "NAME", "ACTIVATOR", "ID", "CREATION_TIME", "EXPIRY_TIME", "STATE");
  protected List<String> portalRequiredColumns = Arrays.asList("NAME");

  protected boolean compactMode;
  protected boolean isInProgressFilterDisplayed;
  protected boolean isAutoHideColumns;
  protected boolean isDisableSelectionCheckboxes;
  protected boolean isRelatedTaskDisplayed;
  protected boolean isNotKeepFilter;
  protected boolean disableTaskCount;

  private TaskFilter selectedTaskFilter;

  public TaskLazyDataModel(String taskWidgetComponentId) {
    super();
    this.taskWidgetComponentId = taskWidgetComponentId;
    selectedFilters = new ArrayList<>();
    buildCriteria();
    data = new ArrayList<>();
    if (shouldSaveAndLoadSessionFilters()) {
      selectedTaskFilterData = UserUtils.getSessionSelectedTaskFilterSetAttribute();
      inProgressFilter = UserUtils.getSessionTaskInProgressFilterAttribute();
      if (inProgressFilter != null) {
        isInProgressFilterDisplayed = true;
      } else {
        inProgressFilter = new TaskInProgressByOthersFilter();
      }
    }
    disableTaskCount = new GlobalSettingService().findGlobalSettingValueAsBoolean(GlobalVariable.DISABLE_TASK_COUNT.toString());
  }

  public TaskLazyDataModel() {
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
    filterContainer = new DefaultTaskFilterContainer();
  }

  public void initFilters() throws ReflectiveOperationException {
    if (filterContainer == null) {
      if (isRelatedTaskDisplayed) {
        if (!criteria.getIncludedStates().contains(TaskState.DONE)) {
          criteria.addIncludedStates(Arrays.asList(TaskState.DONE));
        }
        if (!criteria.getIncludedStates().contains(TaskState.UNASSIGNED)) {
          criteria.addIncludedStates(Arrays.asList(TaskState.UNASSIGNED));
        }
      }
      initFilterContainer();
      filters = filterContainer.getFilters();
      setValuesForStateFilter(criteria);
      TaskStateFilter stateFilter = filterContainer.getStateFilter();
      if (criteria.isAdminQuery() && !isRelatedTaskDisplayed) {
        stateFilter.setSelectedFilteredStatesAtBeginning(new ArrayList<>(stateFilter.getSelectedFilteredStates()));
      } else if (!stateFilter.getFilteredStates().contains(TaskState.DONE)) {
        stateFilter.addFilteredState(TaskState.DONE);
      }
      restoreSessionAdvancedFilters();
    }
  }

  private void restoreSessionAdvancedFilters() throws IllegalAccessException, InvocationTargetException {
    if (shouldSaveAndLoadSessionFilters()) {
      List<TaskFilter> sessionTaskFilters = UserUtils.getSessionTaskAdvancedFilterAttribute();
      if(sessionTaskFilters.isEmpty()) {
        selectedFilters.addAll(filters.stream().filter(TaskFilter::defaultFilter).collect(Collectors.toList()));
      } else {
        for (TaskFilter filter : filters) {
          for (TaskFilter sessionTaskFilter : sessionTaskFilters) {
            copyProperties(sessionTaskFilter, filter);
          }
        }
      }
    }
  }

  private void copyProperties(TaskFilter sessionTaskFilter, TaskFilter filter)
      throws IllegalAccessException, InvocationTargetException {
    if (sessionTaskFilter.getClass() == filter.getClass()) {
      BeanUtils.copyProperties(filter, sessionTaskFilter);
      if (filter instanceof TaskCategoryFilter) {
        ((TaskCategoryFilter) filter).updateRootAndCategoryPaths();
      }
      selectedFilters.add(filter);
    }
  }

  private boolean shouldSaveAndLoadSessionFilters() {
    boolean isValidCriteria =
        (this.criteria == null) || (this.criteria != null && !this.criteria.isQueryForUnassignedTask());
    return isValidCriteria && !isRelatedTaskDisplayed && !isNotKeepFilter;
  }

  @Override
  public List<ITask> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    if (selectedTaskFilter != null && !selectedTaskFilter.reloadView()) {
      selectedTaskFilter = null;
      return data;
    }

    if (first == 0) {
      initializedDataModel(criteria);
      if (!disableTaskCount) {
        PrimeFaces.current().executeScript("updateTaskCount()");
      }
      criteria.setFirstTimeLazyLoad(true);
    } else {
      criteria.setFirstTimeLazyLoad(false);
    }

    List<ITask> foundTasks = findTasks(criteria, first, pageSize);
    if (disableTaskCount) {
      int rowCount = 0;
      if (foundTasks.size() >= pageSize) {
        rowCount = first + pageSize + 1;
      } else {
        rowCount = first + foundTasks.size();
      }
      setRowCount(rowCount);
      PrimeFaces.current().executeScript("PF('task-list-scroller').cfg.totalSize = " + rowCount);
    }
    data.addAll(foundTasks);
    return foundTasks;
  }

  /**
   * Calls the findTasks logic of TaskWidget Html dialog to find tasks.
   * 
   * @param criteria
   * @param first
   * @param pageSize
   * @return List<ITask>
   */
  protected List<ITask> findTasks(TaskSearchCriteria criteria, int first, int pageSize) {
    IvyComponentLogicCaller<List<ITask>> findTaskCaller = new IvyComponentLogicCaller<>();
    int startIndex = first;
    int count = pageSize;
    if (startIndex < 0) {
      startIndex = 0;
      count = first + pageSize;
    }
    return findTaskCaller.invokeComponentLogic(taskWidgetComponentId, "#{logic.findTasks}",
        new Object[] {criteria, startIndex, count});
  }

  protected void initializedDataModel(TaskSearchCriteria criteria) {
    criteria.setInvolvedUsername(Ivy.session().getSessionUserName());
    setInvolvedApplications();
    data.clear();
    buildQueryToSearchCriteria();
    if (disableTaskCount) {
      setRowCount(0);
    } else {
      setRowCount(getTaskCount(criteria));
    }
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
        countTaskCaller.invokeComponentLogic(taskWidgetComponentId, "#{logic.countTasks}", new Object[] {criteria});
    return taskCount.intValue();
  }

  protected void buildCriteria() {
    criteria = new TaskSearchCriteria();
    criteria.setInvolvedUsername(Ivy.session().getSessionUserName());
    criteria.setQueryForUnassignedTask(false);
    criteria
        .setIncludedStates(new ArrayList<>(Arrays.asList(TaskState.CREATED, TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED)));
    criteria.setSortField(TaskSortField.ID.toString());
    criteria.setSortDescending(true);
    if (shouldSaveAndLoadSessionFilters()) {
      criteria.setKeyword(UserUtils.getSessionTaskKeywordFilterAttribute());
    }
  }

  /**
   * <p>
   * If your customized task list has new columns/fields, please extend the {@code taskQuery}
   * parameter with the sort query for these fields.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * if ("CustomVarCharField5".equalsIgnoreCase(criteria.getSortField())) {
   *   if (criteria.isSortDescending()) {
   *     taskQuery.orderBy().customField().stringField("CustomVarCharField5").descending();
   *   } else {
   *     taskQuery.orderBy().customField().stringField("CustomVarCharField5");
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

  public void setSortField(String sortField, boolean sortDescending) {
    criteria.setSortField(sortField);
    criteria.setSortDescending(sortDescending);
  }

  public void setCategory(String category) {
    criteria.setCategory(category);
  }

  public void setAdminQuery(boolean isAdminQuery) {
    List<TaskState> includedStates = criteria.getIncludedStates();
    List<TaskState> adminStateNotIncluded = Arrays.asList(TaskState.DONE, TaskState.UNASSIGNED)
        .stream()
        .filter(item -> !includedStates.contains(item))
        .collect(Collectors.toList());
    if (isAdminQuery && !adminStateNotIncluded.isEmpty()) {
      criteria.addIncludedStates(adminStateNotIncluded);
      setValuesForStateFilter(criteria);
    }
    criteria.setAdminQuery(isAdminQuery);
  }

  public void setInvolvedUsername(String involvedUsername) {
    criteria.setInvolvedUsername(involvedUsername);
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

  public void setTaskAssigneeType(TaskAssigneeType assigneeType) {
    criteria.setTaskAssigneeType(assigneeType);
  }

  public String getSortField() {
    return criteria.getSortField();
  }

  public boolean isSortDescending() {
    return criteria.isSortDescending();
  }

  public void setIncludedStates(List<TaskState> includedStates) {
    this.criteria.setIncludedStates(includedStates);
    setValuesForStateFilter(this.criteria);
  }

  public void addIncludedStates(List<TaskState> includedStates) {
    this.criteria.addIncludedStates(includedStates);
    setValuesForStateFilter(this.criteria);
  }

  public void setCriteria(TaskSearchCriteria criteria) {
    this.criteria = criteria;
  }

  public TaskSearchCriteria getCriteria() {
    return criteria;
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
    selectedTaskFilter = null;
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
    taskFilterData.setKeyword(criteria.getKeyword());
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
    criteria.setKeyword(taskFilterData.getKeyword());
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
    List<TaskFilter> toggleFilters = null;
    if (newSelectedFilters.size() > oldSelectedFilters.size()) {
      toggleFilters = (List<TaskFilter>) CollectionUtils.subtract(newSelectedFilters, oldSelectedFilters);
    } else {
      toggleFilters = (List<TaskFilter>) CollectionUtils.subtract(oldSelectedFilters, newSelectedFilters);
    }
    if (CollectionUtils.isNotEmpty(toggleFilters)) {
      selectedTaskFilter = toggleFilters.get(0);
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
    if (criteria.getCustomTaskQuery() == null) {
      TaskQuery taskQuery = SubProcessCall.withPath(PortalConstants.BUILD_TASK_QUERY_CALLABLE)
          .withStartSignature("buildTaskQuery(Boolean)").withParam("isQueryForHomePage", compactMode).call()
          .get("taskQuery", TaskQuery.class);
      criteria.setCustomTaskQuery(taskQuery);
    }

    if (compactMode) {
      criteria.setIncludedStates(new ArrayList<>(Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED)));
    } else {
      if (filterContainer != null) {
        if (selectedFilters.contains(filterContainer.getStateFilter())) {
          criteria.setIncludedStates(new ArrayList<>());
        } else {
          criteria.setIncludedStates(filterContainer.getStateFilter().getSelectedFilteredStates());
        }
      }

      criteria.setTaskStartedByAnotherDisplayed(inProgressFilter.getIsTaskInProgressByOthersDisplayed());
    }

    TaskQuery taskQuery = buildTaskQuery();
    extendSort(taskQuery);
    criteria.setFinalTaskQuery(taskQuery);
  }

  protected void setInvolvedApplications() {
    RegisteredApplicationService service = new RegisteredApplicationService();
    criteria.setApps(service.findActiveIvyAppsBasedOnConfiguration(Ivy.session().getSessionUserName()));
  }

  protected void setValuesForStateFilter(TaskSearchCriteria criteria) {
    if (filterContainer != null) {
      filterContainer.getStateFilter().setFilteredStates(new ArrayList<>(criteria.getIncludedStates()));
      filterContainer.getStateFilter().setSelectedFilteredStates(criteria.getIncludedStates());
      filterContainer.getStateFilter().setSelectedFilteredStatesAtBeginning(criteria.getIncludedStates());
    }
  }

  protected TaskQuery buildTaskQuery() {
    TaskQuery taskQuery = criteria.createQuery();
    IFilterQuery filterQuery = taskQuery.where();
    selectedFilters.forEach(selectedFilter -> {
      TaskQuery subQuery = selectedFilter.buildQuery();
      if (subQuery != null) {
        filterQuery.and(subQuery);
      }
    });
    if (shouldSaveAndLoadSessionFilters()) {
      UserUtils.setSessionSelectedTaskFilterSetAttribute(selectedTaskFilterData);
      UserUtils.setSessionTaskKeywordFilterAttribute(criteria.getKeyword());
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
      TaskColumnsConfiguration configData = service.getConfiguration(applicationId, userId, processModelId);
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

  @Override
  public void setRowIndex(int index) {
    int idx = index;
    if (idx >= data.size()) {
      idx = -1;
    }
    this.rowIndex = idx;
  }

  @Override
  public ITask getRowData() {
    if (rowIndex >= 0 && rowIndex < data.size()) {
      return data.get(rowIndex);
    } else {
      return null;
    }
  }

  @Override
  public boolean isRowAvailable() {
    if (data == null) {
      return false;
    }
    return rowIndex >= 0 && rowIndex < data.size();
  }
  
  /**
   * <p>
   * Your customized data model needs to override this method if your customized task list has new
   * columns/fields.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * 
   * return Arrays.asList("PRIORITY", "NAME", "ID" , "ACTIVATOR", "CREATION_TIME", "EXPIRY_TIME", "customVarCharField5", "customVarCharField1");
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
        service.getConfiguration(applicationId, Ivy.session().getSessionUser().getId(), processModelId);
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
    this.criteria.setQueryForUnassignedTask(isQueryForOnlyUnassignedTask);
  }

  /**
   * This is default of sort item, override it if you want to customize it
   * 
   * IMPORTANT: Item in this list must follow pattern : column name + "_ASC" or column name +
   * "_DESC"
   * 
   * E.g your customize portal column are Arrays.asList{"PRIORITY", "NAME", "ID" , "ACTIVATOR",
   * "CREATION_TIME", "EXPIRY_TIME", "customVarCharField5", "customVarCharField1"} You can have sort
   * fields like: return Arrays.asList("CREATION_TIME_ASC", "CREATION_TIME_DESC",
   * "customVarCharField5_ASC", "customVarCharField5_DESC", "customVarCharField1_ASC",
   * "customVarCharField1_DESC"}
   * 
   * @return list of sort criteria
   */
  public List<String> getPortalTaskSort() {
    return Arrays.asList("CREATION_TIME_ASC", "CREATION_TIME_DESC", "EXPIRY_TIME_ASC", "EXPIRY_TIME_DESC",
        "PRIORITY_ASC", "PRIORITY_DESC");
  }

  /**
   * Sort field label. Override this method and return cms in your project
   * 
   * Example you have custome sort fields like Arrays.asList("CREATION_TIME_ASC",
   * "CREATION_TIME_DESC", "customVarCharField5_ASC", "customVarCharField5_DESC",
   * "customVarCharField1_ASC", "customVarCharField1_DESC"}
   * 
   * Then create CMS folder in your project
   * 
   * sortFields/customized/CREATION_TIME_ASC sortFields/customized/CREATION_TIME_DESC
   * sortFields/customized/customVarCharField5_ASC sortFields/customized/customVarCharField5_DESC
   * sortFields/customized/customVarCharField1_ASC sortFields/customized/customVarCharField1_DESC
   * 
   * Override this method: return Ivy.cms().co("/sortFields/customized/" + fieldName);
   * 
   * @param fieldName
   * @return Sort field label
   */
  public String getSortFieldLabel(String fieldName) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/sortFields/" + fieldName);
  }

  public void sort(String sortField) {
    if (StringUtils.isNotBlank(sortField) && sortField.length() > 3) {
      boolean asc = StringUtils.equalsIgnoreCase("asc", sortField.substring(sortField.length() - 3));
      String sortColumn = StringUtils.substring(sortField, 0, asc ? sortField.length() - 4 : sortField.length() - 5);
      if (getDefaultColumns().contains(sortColumn)) {
        setSortField(sortColumn, !asc);
      }
    }
  }

  public boolean getDisableTaskCount() {
    return disableTaskCount;
  }

  public void setDisableTaskCount(boolean disableTaskCount) {
    this.disableTaskCount = disableTaskCount;
  }
  
}
