package ch.ivy.addon.portalkit.datamodel.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class RelatedTaskLazyDataModel extends TaskLazyDataModel {

  private static final long serialVersionUID = 1L;
  private ICase iCase;

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

  private void updateCriteria() {
    criteria = new TaskSearchCriteria();
    criteria.setIncludedStates(new ArrayList<>(TaskSearchCriteria.STANDARD_STATES));
    this.setCaseId(iCase.getId());
    criteria.setSortField(TaskSortField.ID.toString());
    criteria.setSortDescending(false);
    this.getCriteria().setKeyword(StringUtils.EMPTY);
    this.setQueryByBusinessCaseId(true);
    boolean isOwner = iCase != null && iCase.getOwner() != null ? iCase.getOwner().isMember(Ivy.session(), true) : false;
    this.setAdminQuery(PermissionUtils.checkReadAllTasksPermission() || PermissionUtils.checkTaskReadOwnCaseTasksPermission() || isOwner);

    if (HiddenTasksCasesConfig.isHiddenTasksCasesExcluded()) {
      criteria.setCustomTaskQuery(TaskQuery.create().where().customField().stringField(AdditionalProperty.HIDE.toString()).isNull());
    }
  }

  @Override
  public List<ITask> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    criteria.setSortField(sortField);
    criteria.setSortDescending(sortOrder == SortOrder.DESCENDING);

    if (first == 0) {
      initializedDataModel();
    }
    setRowCount(getTaskCount(criteria));

    List<ITask> foundTasks = findTasks(criteria, first, pageSize);
    data.addAll(foundTasks);
    return foundTasks;
  }

  @Override
  protected List<ITask> findTasks(TaskSearchCriteria criteria, int first, int pageSize) {
    IvyComponentLogicCaller<List<ITask>> findTaskCaller = new IvyComponentLogicCaller<>();
    int startIndex = first;
    int count = pageSize;
    if (startIndex < 0) {
      startIndex = 0;
      count = first + pageSize;
    }
    UIComponent component = findRelatedTaskComponent();
    if (component != null) {
      return findTaskCaller.invokeComponentLogic(component, "#{logic.findTasks}", new Object[] {criteria, startIndex, count});
    }
    return new ArrayList<>();
  }

  private UIComponent findRelatedTaskComponent() {
    List<UIComponent> children = FacesContext.getCurrentInstance().getViewRoot().findComponent("case-item-details:case-details-container:widgets:case-details-related-running-tasks-card").getChildren();
    return children.stream().filter(child -> child.getId().equals(taskWidgetComponentId)).findFirst().orElse(null);
  }

  @Override
  protected int getTaskCount(TaskSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countTaskCaller = new IvyComponentLogicCaller<>();
    UIComponent component = findRelatedTaskComponent();
    if (component != null) {
      Long taskCount = countTaskCaller.invokeComponentLogic(component, "#{logic.countTasks}", new Object[] {criteria});
      return taskCount.intValue();
    }
    return 0;
  }

  private void initializedDataModel() {
    data.clear();
    criteria.setFinalTaskQuery(null);
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
  public void setRowIndex(int index) {
    setRowIndexAtSuper(index);
  }

  /**
   * @hidden
   */
  @Override
  public ITask getRowData() {
    return getRowDataAtSuper();
  }

  /**
   * @hidden
   */
  @Override
  public boolean isRowAvailable() {
    return isRowAvailableAtSuper();
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
}
