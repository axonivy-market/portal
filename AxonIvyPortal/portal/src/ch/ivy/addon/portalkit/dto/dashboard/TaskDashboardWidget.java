package ch.ivy.addon.portalkit.dto.dashboard;

import static ch.ivy.addon.portalkit.constant.DashboardConstants.REMOTE_COMMAND_PATTERN;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.SortMeta;

import com.axonivy.portal.dto.dashboard.WidgetInformationCategoryStatisticData;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.service.DashboardWidgetInformationService;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.SortFieldUtil;
import ch.ivyteam.ivy.workflow.TaskState;

public class TaskDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3048837559125720787L;
  protected static final String LIKE_FORMAT = "%%%s%%";

  private int rowsPerPage = 5;

  @JsonIgnore
  private DashboardTaskLazyDataModel dataModel;
  @JsonIgnore
  private Map<TaskState, Long> taskByStateStatistic;
  @JsonIgnore
  private List<WidgetInformationCategoryStatisticData> taskByCategoryStatistic;
  @JsonIgnore
  private Long numberOfTasksExpireThisWeek;
  @JsonIgnore
  private Long numberOfTasksExpireToday;
  @JsonIgnore
  private List<ColumnModel> filterableColumns;
  private boolean enableQuickSearch;
  private boolean showWidgetInfo;
  private boolean showExpandMode;

  public TaskDashboardWidget() {
    dataModel = new DashboardTaskLazyDataModel();
    setColumns(new ArrayList<>());
    setShowWidgetInfo(true);
    setShowExpandMode(true);
  }

  @Override
  public void buildStatisticInfos() {
    String combinedAjaxCommand = String.format(REMOTE_COMMAND_PATTERN, "buildStatisticTaskExpiry", id)
        .concat(String.format(REMOTE_COMMAND_PATTERN, "buildStatisticTaskStates", id))
        .concat(String.format(REMOTE_COMMAND_PATTERN, "buildStatisticTaskCategory", id))
        .concat(String.format(REMOTE_COMMAND_PATTERN, "buildTaskDefinedFilter", id));
    PrimeFaces.current().executeScript(combinedAjaxCommand);
  }

  @JsonIgnore
  public void buildTaskByStateStatistic() {
    taskByStateStatistic = DashboardWidgetInformationService.getInstance().buildStatisticOfTaskByState(dataModel);
  }

  @JsonIgnore
  public void buildTaskExpiryStatistic() {
    var numberOfTasksExpireMap = DashboardWidgetInformationService.getInstance().buildStatisticOfTaskExpiry(dataModel);
    numberOfTasksExpireToday = numberOfTasksExpireMap.get(DashboardWidgetInformationService.TASKS_EXPIRE_TODAY);
    numberOfTasksExpireThisWeek = numberOfTasksExpireMap.get(DashboardWidgetInformationService.TASKS_EXPIRE_THIS_WEEK);
  }

  @JsonIgnore
  public void buildTaskByCategoryStatistic() {
    taskByCategoryStatistic = DashboardWidgetInformationService.getInstance().buildStatisticOfTaskByCategory(dataModel);
  }

  public List<ColumnModel> getFilterableColumns() {
    return filterableColumns;
  }

  @JsonIgnore
  public void buildFilterableColumns(List<TaskColumnModel> taskColumns) {
    this.filterableColumns = DashboardWidgetUtils.buildTaskFilterableColumns(taskColumns);
  }

  public boolean getCanWorkOn() {
    return this.dataModel.getCanWorkOn();
  }
  
  public void setCanWorkOn(boolean canWorkOn) {
    this.dataModel.setCanWorkOn(canWorkOn);
  }

  @JsonIgnore
  public SortMeta getSortBy() {
    List<TaskColumnModel> columnModels = getColumns();
    if (CollectionUtils.isNotEmpty(columnModels)) {
      String sortField = getSortField();
      for (TaskColumnModel taskColumnModel : columnModels) {
        if (taskColumnModel.getField().equalsIgnoreCase(sortField)) {
          return SortFieldUtil.buildSortMeta(taskColumnModel.getField(), isSortDescending());
        }
      }
    }
    return null;
  }

  public String getSortField() {
    return this.dataModel.getCriteria().getSortField();
  }
  
  public void setSortField(String sortField) {
    this.dataModel.getCriteria().setSortField(sortField);
  }
  
  public boolean isSortDescending() {
    return this.dataModel.getCriteria().isSortDescending();
  }
  
  public void setSortDescending(boolean sortDescending) {
    this.dataModel.getCriteria().setSortDescending(sortDescending);
  }

  public DashboardTaskLazyDataModel getDataModel() {
    return dataModel;
  }
  
  public void setDataModel(DashboardTaskLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }
  
  public List<TaskColumnModel> getColumns() {
    return this.dataModel.getCriteria().getColumns();
  }

  public void setColumns(List<TaskColumnModel> columns) {
    this.dataModel.getCriteria().setColumns(columns);
    buildFilterableColumns(columns);
  }

  @JsonIgnore
  public int getTaskCount() {
    return getDataModel().getRowCount();
  }
  
  @JsonIgnore
  public boolean isInConfiguration() {
    return this.dataModel.getCriteria().isInConfiguration();
  }
  
  public void setInConfiguration(boolean isInConfiguration) {
    this.dataModel.getCriteria().setInConfiguration(isInConfiguration);
  }

  public Map<TaskState, Long> getTaskByStateStatistic() {
    return taskByStateStatistic;
  }

  public Long getNumberOfTasksExpireThisWeek() {
    return numberOfTasksExpireThisWeek;
  }

  public void setNumberOfTasksExpireThisWeek(Long numberOfTasksExpireThisWeek) {
    this.numberOfTasksExpireThisWeek = numberOfTasksExpireThisWeek;
  }

  public Long getNumberOfTasksExpireToday() {
    return numberOfTasksExpireToday;
  }

  public void setNumberOfTasksExpireToday(Long numberOfTasksExpireToday) {
    this.numberOfTasksExpireToday = numberOfTasksExpireToday;
  }

  public List<WidgetInformationCategoryStatisticData> getTaskByCategoryStatistic() {
    return taskByCategoryStatistic;
  }

  public void setTaskByCategoryStatistic(List<WidgetInformationCategoryStatisticData> taskByCategoryStatistic) {
    this.taskByCategoryStatistic = taskByCategoryStatistic;
  }

  @Override
  public void buildPredefinedFilterData() {
    setHasPredefinedFilter(DashboardWidgetUtils.hasPredefinedTaskFilter(this));
  }

  @Override
  @JsonIgnore
  public void resetWidgetFilters() {
    DashboardWidgetUtils.resetUserFilterOnColumns(getColumns());
  }

  public boolean isEnableQuickSearch() {
    return enableQuickSearch;
  }

  public void setEnableQuickSearch(boolean enableQuickSearch) {
    this.enableQuickSearch = enableQuickSearch;
  }

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.TASK;
  }

  public int getRowsPerPage() {
    return isInConfiguration() ? 5 : rowsPerPage;
  }

  public void setRowsPerPage(int rowsPerPage) {
    this.rowsPerPage = rowsPerPage;
  }

  public void setShowWidgetInfo(boolean showWidgetInfo) {
    this.showWidgetInfo = showWidgetInfo;
  }

  public boolean isShowWidgetInfo() {
    return showWidgetInfo;
  }

  public void setShowExpandMode(boolean showExpandMode) {
    this.showExpandMode = showExpandMode;
  }

  public boolean isShowExpandMode() {
    return showExpandMode;
  }

  @Override
  public void setQuickSearchKeyword() {
    this.dataModel.getCriteria().setQuickSearchKeyword(this.getQuickSearchKeyword());
  }
}