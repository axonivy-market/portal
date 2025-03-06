package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bo.TaskColumnsConfiguration;
import ch.ivy.addon.portalkit.enums.SortDirection;
import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.service.TaskColumnsConfigurationService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

public class TaskLazyDataModel extends LazyDataModel<ITask> {
  public static final String DESCRIPTION = "DESCRIPTION";

  private static final long serialVersionUID = -6615871274830927272L;

  protected String taskWidgetComponentId;
  protected String caseName;
  protected int rowIndex;
  protected TaskSearchCriteria criteria;
  protected List<ITask> data;
  protected Long filterGroupId;
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
    buildCriteria();
    data = new ArrayList<>();
  }

  public TaskLazyDataModel() {
    this("task-widget");
  }

  public void updateDisableTaskCount() {
    disableTaskCount = false; // set default value instead of variable
  }

  protected void updateStateForTaskCriteria() {
    if (isRelatedTaskDisplayed && !criteria.getIncludedStates().contains(TaskState.DONE)) {
      criteria.addIncludedStates(Arrays.asList(TaskState.DONE));
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
    return TaskSortField.ID.name();
  }

  private boolean isSortedDescendingByDefault() {
    return !SortFieldUtil.isAscendingSort(SortDirection.DESC.name());
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
  }

  public void addIncludedStates(List<TaskState> includedStates) {
    this.criteria.addIncludedStates(includedStates);
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

  public boolean hasReadAllTasksPermisson() {
    return PermissionUtils.checkReadAllTasksPermission();
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

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return 0;
  }
}
