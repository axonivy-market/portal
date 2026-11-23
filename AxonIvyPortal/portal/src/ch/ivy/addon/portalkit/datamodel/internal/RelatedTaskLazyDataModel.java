package ch.ivy.addon.portalkit.datamodel.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.datamodel.LiveScrollLazyModel;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;
import ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.jsf.primefaces.sort.SortMetaConverter;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public class RelatedTaskLazyDataModel extends TaskLazyDataModel {

  private static final long serialVersionUID = 1L;
  private ICase iCase;
  private int chunkSize = LiveScrollLazyModel.DEFAULT_CHUNK_SIZE;
  private boolean isFirstTime = true;
  private List<ITask> firstChunk;

  public RelatedTaskLazyDataModel() {
    super();
  }

  public RelatedTaskLazyDataModel(String taskWidgetComponentId) {
    super(taskWidgetComponentId);
  }

  public RelatedTaskLazyDataModel(ICase iCase) {
    super();
    this.iCase = iCase;
    updateCriteria();
  }
  
  @Override
  public List<String> getDefaultColumns() {
    return Arrays.asList(TaskSortField.PRIORITY.name(), TaskSortField.NAME.name(), TaskSortField.ACTIVATOR.name(), TaskSortField.ID.name(),
        TaskSortField.CREATION_TIME.name(), TaskSortField.EXPIRY_TIME.name(),TaskSortField.COMPLETED_ON.name(), TaskSortField.STATE.name());
  }

  @Override
  public void initColumnsConfiguration() {
    super.initColumnsConfiguration();
    allColumns.add(TaskSortField.WORKER.name());
    allColumns.add(TaskSortField.BUSINESS_CASE_ID.name());
    allColumns.add(TaskSortField.TECHNICAL_CASE_ID.name());
  }

  private void updateCriteria() {
    criteria = new TaskSearchCriteria();
    criteria.setIncludedStates(new ArrayList<>(TaskSearchCriteria.STANDARD_STATES));
    this.setCaseId(iCase.getId());
    criteria.setSortField(TaskSortField.ID.toString());
    criteria.setSortDescending(false);
    this.getCriteria().setKeyword(StringUtils.EMPTY);
    this.setQueryByBusinessCaseId(true);
    boolean isOwner = isCaseOwnerUser(iCase);
    this.setAdminQuery(PermissionUtils.checkReadAllTasksPermission() || PermissionUtils.checkTaskReadOwnCaseTasksPermission() || isOwner);

    if (HiddenTasksCasesConfig.isHiddenTasksCasesExcluded()) {
      criteria.setCustomTaskQuery(TaskQuery.create().where().customField().stringField(AdditionalProperty.HIDE.toString()).isNull());
    }
  }
  
  private boolean isCaseOwnerUser(ICase caze) {
    return caze != null && caze.owners() != null && caze
        .owners()
        .all()
        .stream()
        .anyMatch(item -> item.member().isMember(Ivy.session()));
  }

  @Override
  public List<ITask> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    if (first == 0) {
      PrimeFaces.current().executeScript("resizeCaseDetailsTableBody();");
    }
    List<ITask> foundTasks;
    if (isFirstTime) {
      isFirstTime = false;
      foundTasks = firstChunk == null ? new ArrayList<>() : firstChunk;
      firstChunk = null;
      data.clear();
    } else {
      prepareCriteria();
      SortMetaConverter sort = new SortMetaConverter(sortBy);
      criteria.setSortField(sort.toField());
      criteria.setSortDescending(sort.toOrder() == SortOrder.DESCENDING);
      if (first == 0) {
        data.clear();
      }
      foundTasks = findTasks(criteria, first, pageSize);
    }
    data.addAll(foundTasks);

    if (foundTasks.size() >= pageSize) {
      setRowCount(first + pageSize + 1);
    } else {
      setRowCount(first + foundTasks.size());
    }
    return foundTasks;
  }

  public void loadFirstTime() {
    chunkSize = LiveScrollLazyModel.readChunkSizeFromRequest(chunkSize);
    prepareCriteria();
    criteria.setSortField(TaskSortField.ID.name());
    criteria.setSortDescending(true);
    firstChunk = findTasks(criteria, 0, chunkSize);
    isFirstTime = true;
  }

  private void prepareCriteria() {
    if (isOnlyShowOpenTask()) {
      criteria.setIncludedBusinessStates(Arrays.asList(TaskBusinessState.OPEN));
    } else {
      updateCriteria();
    }
    criteria.setColumns(allColumns);
  }

  @Override
  protected List<ITask> findTasks(TaskSearchCriteria criteria, int first, int pageSize) {
    return TaskService.newInstance().findTasksByCriteria(criteria, Math.max(first, 0), pageSize).getTasks();
  }

  public int getChunkSize() {
    return chunkSize;
  }

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return getRowCount();
  }

  @Override
  public ITask getRowData(String rowKey) {
    for (ITask task : data) {
      if (task.getId() == Long.valueOf(rowKey)) {
        return task;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(ITask task) {
    return String.valueOf(task.getId());
  }

  /**
   * @hidden
   */
  @Override
  public void saveColumnsConfiguration() {
    setAutoHideColumns(isDisableSelectionCheckboxes);
    if (isAutoHideColumns()) {
      selectedColumns.clear();
      selectedColumns.addAll(getDefaultColumns());
    } else {
      // avoid duplicating
      for (String requiredColumn : portalRequiredColumns) {
        if (!selectedColumns.contains(requiredColumn)) {
          selectedColumns.add(requiredColumn);
        }
      }
    }
    initSelectedColumns();
  }

  @Override
  protected void initSelectedColumns() {
    if (selectedColumns.isEmpty()) {
      selectedColumns.addAll(getDefaultColumns());
      isAutoHideColumns = true;
    }
    setDisableSelectionCheckboxes(isAutoHideColumns);
  }

  public boolean isOnlyShowOpenTask() {
    return this.criteria.isOnlyShowOpenTask();
  }

  public void setOnlyShowOpenTask(boolean isOnlyShowOpenTask) {
    this.criteria.setOnlyShowOpenTask(isOnlyShowOpenTask);
  }
}
