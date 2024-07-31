package ch.ivy.addon.portalkit.datamodel;

import static ch.ivy.addon.portalkit.enums.FilterType.ALL_ADMINS;
import static ch.ivy.addon.portalkit.enums.FilterType.ALL_USERS;

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
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bo.TaskColumnsConfiguration;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.UserSettingService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.TaskColumnsConfigurationService;
import ch.ivy.addon.portalkit.service.TaskFilterService;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.impl.DefaultTaskFilterContainer;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskStateFilter;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
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
  public static final String DESCRIPTION = "DESCRIPTION";

  private static final long serialVersionUID = -6615871274830927272L;

  protected String taskWidgetComponentId;
  protected String caseName;
  protected int rowIndex;
  protected TaskSearchCriteria criteria;
  protected List<ITask> data;

  protected TaskFilterContainer filterContainer;
  protected TaskFilterData selectedTaskFilterData;
  protected TaskFilterData defaultTaskFilterData;
  protected Long filterGroupId;

  protected List<TaskFilter> filters;
  protected List<TaskFilter> selectedFilters;
  protected List<TaskFilter> submittedFilterSelection = new ArrayList<>();
  protected List<TaskFilter> oldSelectedFilters = new ArrayList<>();
  protected List<String> allColumns = new ArrayList<>();
  protected List<String> selectedColumns = new ArrayList<>();
  protected List<String> portalDefaultColumns = Arrays.asList(TaskSortField.PRIORITY.name(), 
                                                              TaskSortField.NAME.name(), 
                                                              TaskSortField.ACTIVATOR.name(), 
                                                              TaskSortField.ID.name(), 
                                                              TaskSortField.CREATION_TIME.name(),
                                                              TaskSortField.EXPIRY_TIME.name(), 
                                                              TaskSortField.COMPLETED_ON.name(), 
                                                              TaskSortField.STATE.name(), 
                                                              TaskSortField.CATEGORY.name());
  protected List<String> portalRequiredColumns = Arrays.asList(TaskSortField.NAME.name());

  protected boolean isAutoHideColumns;
  protected boolean isDisableSelectionCheckboxes;
  protected boolean isRelatedTaskDisplayed;
  protected boolean isNotKeepFilter;
  protected boolean disableTaskCount;
  protected Boolean isSelectedDefaultFilter;
  protected List<String> standardSortFields;
  protected boolean isSelectedAllFilters;

  public TaskLazyDataModel(String taskWidgetComponentId) {
    super();
    this.taskWidgetComponentId = taskWidgetComponentId;
    selectedFilters = new ArrayList<>();
    buildCriteria();
    data = new ArrayList<>();

    loadSessionTaskFiltersAttribute();
    if (isSelectedDefaultFilter == null) {
      buildDefaultTaskFilterData();
    }
  }

  private void loadSessionTaskFiltersAttribute() {
    if (shouldSaveAndLoadSessionFilters()) {
      if (isSameFilterGroupId()) {
        isSelectedDefaultFilter = UserUtils.getSessionSelectedDefaultTaskFilterSetAttribute();
        selectedTaskFilterData = UserUtils.getSessionSelectedTaskFilterSetAttribute();
      } else {
        isSelectedDefaultFilter = true;
        selectedTaskFilterData = null;
      }
    }
  }

  private boolean isSameFilterGroupId() {
    filterGroupId = UserUtils.getSessionFilterGroupIdAttribute();
    return filterGroupId == null || filterGroupId.equals(Ivy.request().getProcessModel().getId());
  }

  public TaskFilterData buildDefaultTaskFilterData() {
    if (defaultTaskFilterData == null) {
      defaultTaskFilterData = new TaskFilterData();
      defaultTaskFilterData.setType(FilterType.DEFAULT);
      defaultTaskFilterData.setFilterName(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultFilter"));
      collectFiltersForDefaultFilterSet();
    }
    isSelectedDefaultFilter = isSelectedDefaultFilter == null ? true: isSelectedDefaultFilter;
    return defaultTaskFilterData;
  }

  public TaskLazyDataModel() {
    this("task-widget");
  }

  public void updateDisableTaskCount() {
    disableTaskCount = GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.DISABLE_TASK_COUNT);
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
  public void initFilterContainer() {
    filterContainer = new DefaultTaskFilterContainer(criteria.isAdminQuery());
  }

  public void initFilters() throws ReflectiveOperationException {
    if (filterContainer == null) {
      updateStateForTaskCriteria();
      initFilterContainer();
      filters = filterContainer.getFilters();
      setValuesForStateFilter(criteria, filterContainer);
      buildTaskStateFilter(filterContainer);
      restoreSessionAdvancedFilters();
    }
    checkToApplyDefaultSet();
    submittedFilterSelection = new ArrayList<>(selectedFilters);
  }

  protected void buildTaskStateFilter(TaskFilterContainer filterContainer) {
    TaskStateFilter stateFilter = filterContainer.getStateFilter();
    if (criteria.isAdminQuery() && !isRelatedTaskDisplayed) {
      stateFilter.setSelectedFilteredStatesAtBeginning(new ArrayList<>(stateFilter.getSelectedFilteredStates()));
      stateFilter.setSubmittedFilteredStates(new ArrayList<>(stateFilter.getSelectedFilteredStates()));
    } else if (!stateFilter.getFilteredStates().contains(TaskState.DONE)) {
      stateFilter.addFilteredState(TaskState.DONE);
    }
  }

  protected void updateStateForTaskCriteria() {
    if (isRelatedTaskDisplayed && !criteria.getIncludedStates().contains(TaskState.DONE)) {
      criteria.addIncludedStates(Arrays.asList(TaskState.DONE));
    }
  }

  protected void collectFiltersForDefaultFilterSet() {
    if (defaultTaskFilterData != null && CollectionUtils.isEmpty(defaultTaskFilterData.getFilters())) {
      TaskFilterContainer tempFilterContainer = null;
      tempFilterContainer = ObjectUtils.defaultIfNull(this.filterContainer, new DefaultTaskFilterContainer());
      updateStateForTaskCriteria();
      setValuesForStateFilter(criteria, tempFilterContainer);
      buildTaskStateFilter(tempFilterContainer);
      defaultTaskFilterData.setFilters(tempFilterContainer.getFilters().stream().filter(TaskFilter::defaultFilter).collect(Collectors.toList()));
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

  protected void checkToApplyDefaultSet() {
    if (isSelectedDefaultFilter == null || isSelectedDefaultFilter) {
      try {
        applyFilter(defaultTaskFilterData);
      } catch (ReflectiveOperationException e) {
        Ivy.log().error("Cannot apply DefaultFilter for Task list", e);
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
    return !isRelatedTaskDisplayed && !isNotKeepFilter;
  }
  
  @Override
  public List<ITask> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    if (first == 0) {
      initializedDataModel(criteria);
      if (!disableTaskCount) {
        PrimeFaces.current().executeScript("updateTaskCount()");
      }
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
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    return findTaskCaller.invokeComponentLogic(componentId, "#{logic.findTasks}",
        new Object[] {criteria, startIndex, count});
  }

  protected void initializedDataModel(TaskSearchCriteria criteria) {
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
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    Long taskCount =
        countTaskCaller.invokeComponentLogic(componentId, "#{logic.countTasks}", new Object[] {criteria});
    return taskCount.intValue();
  }

  protected void buildCriteria() {
    criteria = new TaskSearchCriteria();
    criteria.setIncludedStates(new ArrayList<>(TaskSearchCriteria.STANDARD_STATES));
    String sortInCache = UserUtils.getSessionTaskSortAttribute();
    if (StringUtils.isBlank(sortInCache)) {
      updateSortCriteria(getDefaultSortField(), isSortedDescendingByDefault(), false);
    } else {
      sort(sortInCache);
    }
    if (shouldSaveAndLoadSessionFilters()) {
      criteria.setKeyword(UserUtils.getSessionTaskKeywordFilterAttribute());
    }
  }

  private String getDefaultSortField() {
   String defaultSortField = UserSettingService.newInstance().getDefaultSortFieldOfTaskList();
   if (StringUtils.isBlank(defaultSortField) || UserSettingService.DEFAULT.equals(defaultSortField)) {
     GlobalSettingService globalSettingService = GlobalSettingService.getInstance();
     defaultSortField = globalSettingService.findGlobalSettingValue(GlobalVariable.DEFAULT_SORT_FIELD_OF_TASK_LIST);
   }
   return defaultSortField;
  }

  private boolean isSortedDescendingByDefault() {
    String defaultSortDirection = UserSettingService.newInstance().getDefaultSortDirectionOfTaskList();
    if (StringUtils.isBlank(defaultSortDirection) || UserSettingService.DEFAULT.equals(defaultSortDirection)) {
      GlobalSettingService globalSettingService = GlobalSettingService.getInstance();
      defaultSortDirection =
          globalSettingService.findGlobalSettingValue(GlobalVariable.DEFAULT_SORT_DIRECTION_OF_TASK_LIST);
    }

    return !SortFieldUtil.isAscendingSort(defaultSortDirection);
  }

  /**
   * <p>
   * If your customized task list has new columns/fields, please extend the {@code taskQuery}
   * parameter with the sort query for these fields.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * if ("CustomerType".equalsIgnoreCase(criteria.getSortField())) {
   *   if (criteria.isSortDescending()) {
   *     taskQuery.orderBy().customField().stringField("CustomerType").descending();
   *   } else {
   *     taskQuery.orderBy().customField().stringField("CustomerType");
   *   }
   * }
   * </pre></code>
   * </p>
   *
   * @param taskQuery task query {@link TaskQuery}
   */
  public void extendSort(@SuppressWarnings("unused") TaskQuery taskQuery) {
    // Placeholder for customization
  }

  public void setSortField(String sortField, boolean sortDescending) {
    updateSortCriteria(sortField, sortDescending, true);
  }

  private void updateSortCriteria(String sortField, boolean sortDescending, boolean updateCache) {
    criteria.setSortField(sortField);
    criteria.setSortDescending(sortDescending);
    if (updateCache) {
      storeTaskSortIntoSession(sortField, sortDescending);
    }
  }

  public void setCategory(String category) {
    criteria.setCategory(category);
  }

  public void setAdminQuery(boolean isAdminQuery) {
    criteria.extendStatesQueryByPermission(isAdminQuery);
    if (isAdminQuery) {
      setValuesForStateFilter(criteria, this.filterContainer);
    }
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
    setValuesForStateFilter(this.criteria, this.filterContainer);
  }

  public void addIncludedStates(List<TaskState> includedStates) {
    this.criteria.addIncludedStates(includedStates);
    setValuesForStateFilter(this.criteria, this.filterContainer);
  }

  public void setCriteria(TaskSearchCriteria criteria) {
    this.criteria = criteria;
  }

  public TaskSearchCriteria getCriteria() {
    return criteria;
  }

  public String getCaseName() {
    return caseName;
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

  public void resetFilters() throws ReflectiveOperationException {
    for (TaskFilter selectedFilter : selectedFilters) {
      selectedFilter.resetValues();
    }

    applyFilter(buildDefaultTaskFilterData());
  }

  /**
   * @param filterToBeRemoved
   * @return is same task filter data
   */
  public boolean isSameTaskFilterData(TaskFilterData filterToBeRemoved) {
    if (filterToBeRemoved == null || selectedTaskFilterData == null) {
      return false;
    }
    return filterToBeRemoved.getFilterGroupId().equals(selectedTaskFilterData.getFilterGroupId())
        && filterToBeRemoved.getType() == selectedTaskFilterData.getType()
        && filterToBeRemoved.getFilterName().equals(selectedTaskFilterData.getFilterName());
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
    isSelectedDefaultFilter = false;
    TaskFilterData taskFilterData = new TaskFilterData();
    List<TaskFilter> taskFilters = new ArrayList<>(selectedFilters);
    taskFilterData.setFilters(taskFilters);
    taskFilterData.setKeyword(criteria.getKeyword());
    taskFilterData.setUserId(Ivy.session().getSessionUser().getId());
    taskFilterData.setSecurityMemberId(Ivy.session().getSessionUser().getSecurityMemberId());
    taskFilterData.setFilterGroupId(taskFilterGroupId);
    taskFilterData.setFilterName(filterName);
    taskFilterData.setType(filterType);
    boolean isPublic = ALL_USERS == taskFilterData.getType() || ALL_ADMINS == taskFilterData.getType();
    taskFilterData.setIsPublic(isPublic);
    TaskFilterService taskFilterService = new TaskFilterService();
    taskFilterService.save(taskFilterData);
    BusinessDataInfo<TaskFilterData> info = taskFilterService.save(taskFilterData);
    taskFilterData = taskFilterService.findById(info.getId());
    UserUtils.setSessionSelectedTaskFilterSetAttribute(taskFilterData);
    UserUtils.setSessionSelectedDefaultTaskFilterSetAttribute(isSelectedDefaultFilter);
    return taskFilterData;
  }

  /**
   * Apply filter settings loaded from business data to this {@link #TaskLazyDataModel}
   *
   * @param taskFilterData task filter data {@link TaskFilterData}
   * @throws ReflectiveOperationException
   */
  public void applyFilter(TaskFilterData taskFilterData) throws ReflectiveOperationException {
    isSelectedDefaultFilter = FilterType.DEFAULT.equals(taskFilterData.getType());
    selectedTaskFilterData = taskFilterData;
    new TaskFilterService().applyFilter(this, taskFilterData);
    criteria.setKeyword(taskFilterData.getKeyword());
  }

  @SuppressWarnings("unchecked")
  public void onFilterChange(ValueChangeEvent event) {
    oldSelectedFilters = (List<TaskFilter>) event.getOldValue();
  }

  @SuppressWarnings("unchecked")
  public void updateSelectedFilter() {
    List<TaskFilter> toggleFilters = null;
    if (selectedFilters.size() > oldSelectedFilters.size()) {
      toggleFilters = (List<TaskFilter>) CollectionUtils.subtract(selectedFilters, oldSelectedFilters);
    } else {
      toggleFilters = (List<TaskFilter>) CollectionUtils.subtract(oldSelectedFilters, selectedFilters);
    }
    if (CollectionUtils.isNotEmpty(toggleFilters)) {
      toggleFilters.forEach(filter -> filter.resetValues());
    }
    resetFilterData();
    storeTaskFiltersIntoSession();
    submittedFilterSelection = new ArrayList<>(selectedFilters);
  }

  public void restoreFiltersSelection() {
    if (CollectionUtils.isNotEmpty(submittedFilterSelection)) {
      selectedFilters = new ArrayList<>(submittedFilterSelection);
    }
    isSelectedAllFilters = selectedFilters.size() == filters.size();
  }

  public void onFilterApply() {
    resetFilterData();
  }

  public void onKeywordChange() {
    resetFilterData();
  }

  private void resetFilterData() {
    if (this.selectedTaskFilterData != null) {
      this.selectedTaskFilterData = null;
    }
    this.isSelectedDefaultFilter = false;
  }

  public boolean hasReadAllTasksPermisson() {
    return PermissionUtils.checkReadAllTasksPermission();
  }

  /**
   * Builds and converts TaskQuery to JsonQuery and put it into TaskSearchCriteria.
   */
  protected void buildQueryToSearchCriteria() {
    if (criteria.getCustomTaskQuery() == null) {
      TaskQuery taskQuery = SubProcessCall.withPath(PortalConstants.BUILD_TASK_QUERY_CALLABLE)
          .withStartSignature("buildTaskQuery()")
          .call()
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

    TaskQuery taskQuery = buildTaskQuery();
    extendSort(taskQuery);
    criteria.setFinalTaskQuery(taskQuery);
  }

  protected void setValuesForStateFilter(TaskSearchCriteria criteria, TaskFilterContainer filterContainer) {
    if (filterContainer != null) {
      filterContainer.getStateFilter().setFilteredStates(new ArrayList<>(criteria.getIncludedStates()));
      filterContainer.getStateFilter().setSelectedFilteredStates(criteria.getIncludedStates());
      filterContainer.getStateFilter().setSelectedFilteredStatesAtBeginning(criteria.getIncludedStates());
      filterContainer.getStateFilter().setSubmittedFilteredStates(criteria.getIncludedStates());
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
    storeTaskFiltersIntoSession();
    return taskQuery;
  }

  private void storeTaskFiltersIntoSession() {
    if (shouldSaveAndLoadSessionFilters()) {
      UserUtils.setSessionSelectedDefaultTaskFilterSetAttribute(isSelectedDefaultFilter);
      UserUtils.setSessionSelectedTaskFilterSetAttribute(selectedTaskFilterData);
      UserUtils.setSessionTaskKeywordFilterAttribute(criteria.getKeyword());
      UserUtils.setSessionFilterGroupIdAttribute(Ivy.request().getProcessModel().getId());
      UserUtils.setSessionTaskAdvancedFilterAttribute(selectedFilters);
    }
  }

  public void initColumnsConfiguration() {
    if (CollectionUtils.isEmpty(allColumns)) {
      allColumns.addAll(getDefaultColumns());
      allColumns.add(TaskSortField.APPLICATION.name());
      initSelectedColumns();
    }
  }

  protected void initSelectedColumns() {
    TaskColumnsConfigurationService service = TaskColumnsConfigurationService.getInstance();
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

  public void setRowIndexAtSuper(int index) {
    super.setRowIndex(index);
  }

  @Override
  public ITask getRowData() {
    if (rowIndex >= 0 && rowIndex < data.size()) {
      return data.get(rowIndex);
    } else {
      return null;
    }
  }

  public ITask getRowDataAtSuper() {
    return super.getRowData();
  }

  @Override
  public boolean isRowAvailable() {
    if (data == null) {
      return false;
    }
    return rowIndex >= 0 && rowIndex < data.size();
  }

  public boolean isRowAvailableAtSuper() {
    return super.isRowAvailable();
  }

  /**
   * <p>
   * Your customized data model needs to override this method if your customized task list has new
   * columns/fields.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   *
   * return Arrays.asList("PRIORITY", "NAME", "ID" , "ACTIVATOR", "CREATION_TIME", "EXPIRY_TIME", "CustomerName", "CustomerType");
   *
   * </pre></code> This list is the list of sortFields in TaskColumnHeader Portal component when you
   * use it to add new column headers Also the list of checkboxes in config columns panel
   * </p>
   *
   * @return default columns
   */
  public List<String> getDefaultColumns() {
    return portalDefaultColumns;
  }

  /**
   * <p>
   * In case you adds new columns, these columns need cms to show in config columns panel
   * </p>
   * <p>
   * You can either add new entry to default folder below in PortalKit or override this method to
   * create your own folder column must be the same with sortField
   * </p>
   *
   * @param column column name
   *
   * @return column label
   */
  public String getColumnLabel(String column) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/" + column);
  }

  public void saveColumnsConfiguration() {
    // avoid duplicating
    for (String requiredColumn : portalRequiredColumns) {
      if (!selectedColumns.contains(requiredColumn)) {
        selectedColumns.add(requiredColumn);
      }
    }
    setAutoHideColumns(isDisableSelectionCheckboxes);
    TaskColumnsConfigurationService service = TaskColumnsConfigurationService.getInstance();
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

  /**
   * Check if your column is selected
   * @param column column name
   * @return is column selected
   */
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
    this.selectedTaskFilterData = null;
    this.isSelectedDefaultFilter = false;
  }

  /**
   * This is default of sort item, override it if you want to customize it
   *
   * IMPORTANT: Item in this list must follow pattern : column name + "_ASC" or column name +
   * "_DESC"
   *
   * E.g your customize portal column are Arrays.asList{"PRIORITY", "NAME", "ID" , "ACTIVATOR",
   * "CREATION_TIME", "EXPIRY_TIME", "CustomerName", "CustomerType"} You can have sort
   * fields like: return Arrays.asList("CREATION_TIME_ASC", "CREATION_TIME_DESC",
   * "CustomerName_ASC", "CustomerName_DESC", "CustomerType_ASC",
   * "CustomerType_DESC"}
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
   * "CREATION_TIME_DESC", "CustomerName_ASC", "CustomerName_DESC",
   * "CustomerType_ASC", "CustomerType_DESC"}
   *
   * Then create CMS folder in your project
   *
   * sortFields/customized/CREATION_TIME_ASC sortFields/customized/CREATION_TIME_DESC
   * sortFields/customized/CustomerName_ASC sortFields/customized/CustomerName_DESC
   * sortFields/customized/CustomerType_ASC sortFields/customized/CustomerType_DESC
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
    String sortColumn = SortFieldUtil.extractSortColumn(sortField);
    if (getDefaultColumns().contains(sortColumn)) {
      setSortField(sortColumn, !SortFieldUtil.isAscendingSort(sortField));
      return;
    }

    criteria.setSortField(getDefaultSortField());
    criteria.setSortDescending(isSortedDescendingByDefault());
  }

  private void storeTaskSortIntoSession(String sortColumn, boolean sortDescending) {
    UserUtils.setSessionTaskSortAttribute(SortFieldUtil.buildSortField(sortColumn, sortDescending));
  }

  public boolean isSortable(String sortField) {
    return standardSortFields.contains(sortField);
  }

  public boolean getDisableTaskCount() {
    return disableTaskCount;
  }

  public void setDisableTaskCount(boolean disableTaskCount) {
    this.disableTaskCount = disableTaskCount;
  }

  public void setDefaultTaskFilterData(TaskFilterData defaultTaskFilterData) {
    this.defaultTaskFilterData = defaultTaskFilterData;
  }

  public TaskFilterData getDefaultTaskFilterData() {
    return defaultTaskFilterData;
  }

  public boolean isSelectedDefaultFilter() {
    return isSelectedDefaultFilter;
  }

  public void setSelectedDefaultFilter(boolean isSelectedDefaultFilter) {
    this.isSelectedDefaultFilter = isSelectedDefaultFilter;
  }

  public boolean isSelectedAllFilters() {
    return isSelectedAllFilters;
  }

  public void setSelectedAllFilters(boolean isSelectedAllFilters) {
    this.isSelectedAllFilters = isSelectedAllFilters;
  }

  public void onSelectedAllFilters() {
    if (isSelectedAllFilters) {
      selectedFilters = new ArrayList<>(filters);
    } else {
      selectedFilters = new ArrayList<>();
    }
  }

  public void onSelectedFilter() {
    isSelectedAllFilters = selectedFilters.size() == filters.size();
  }
  
  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return 0;
  }
}
