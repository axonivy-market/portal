package portalmigration.version112.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivyteam.ivy.workflow.TaskState;
import portalmigration.version112.datamodel.DashboardTaskLazyDataModel;
import portalmigration.version112.dto.taskcolumn.TaskColumnModel;
import portalmigration.version112.enums.DashboardWidgetType;
import portalmigration.version112.util.DashboardWidgetUtils;

public class TaskDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3048837559125720787L;

  private int rowsPerPage = 5;
  @JsonIgnore
  private DashboardTaskLazyDataModel dataModel;
  @JsonIgnore
  private Map<TaskState, Long> taskByStateStatistic;
  @JsonIgnore
  private Map<String, Long> taskByCategoryStatistic;
  @JsonIgnore
  private Long numberOfTasksExpireThisWeek;
  @JsonIgnore
  private Long numberOfTasksExpireToday;
  @JsonIgnore
  private List<ColumnModel> filterableColumns;

  public TaskDashboardWidget() {
    dataModel = new DashboardTaskLazyDataModel();
    setColumns(new ArrayList<>());
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

  public Map<String, Long> getTaskByCategoryStatistic() {
    return taskByCategoryStatistic;
  }

  public void setTaskByCategoryStatistic(Map<String, Long> taskByCategoryStatistic) {
    this.taskByCategoryStatistic = taskByCategoryStatistic;
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
}