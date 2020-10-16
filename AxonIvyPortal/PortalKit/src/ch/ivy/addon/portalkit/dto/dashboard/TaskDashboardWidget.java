package ch.ivy.addon.portalkit.dto.dashboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.TaskColumn;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

public class TaskDashboardWidget extends DashboardWidget {
  private static final long serialVersionUID = 3048837559125720787L;

  private List<String> taskColumns;
  private String sortField;
  private boolean sortAscending;
  
  @JsonIgnore
  private DashboardTaskLazyDataModel dataModel;
  
  public TaskDashboardWidget() {
    dataModel = new DashboardTaskLazyDataModel();
  }
  
  @Override
  public void setId(String id) {
    super.setId(id);
  }
  
  public boolean getCanWorkOn() {
    return this.dataModel.getCanWorkOn();
  }
  
  public void setCanWorkOn(boolean canWorkOn) {
    this.dataModel.setCanWorkOn(canWorkOn);
  }

  public String getTaskName() {
    return this.dataModel.getTaskName();
  }

  public void setTaskName(String taskName) {
    this.dataModel.setTaskName(taskName);
  }

  public String getDescription() {
    return this.dataModel.getDescription();
  }

  public void setDescription(String description) {
    this.dataModel.setDescription(description);
  }

  public List<WorkflowPriority> getPriorities() {
    return this.dataModel.getPriorities();
  }

  public void setPriorities(List<WorkflowPriority> priorities) {
    this.dataModel.setPriorities(priorities);
  }

  public List<TaskState> getStates() {
    return this.dataModel.getStates();
  }

  public void setStates(List<TaskState> states) {
    this.dataModel.setStates(states);
  }

  public List<String> getResponsibles() {
    return this.dataModel.getResponsibles();
  }

  public void setResponsibles(List<String> responsibles) {
    this.dataModel.setResponsibles(responsibles);
  }

  public Date getCreatedDateFrom() {
    return this.dataModel.getCreatedDateFrom();
  }

  public void setCreatedDateFrom(Date createdDateFrom) {
    this.dataModel.setCreatedDateFrom(createdDateFrom);
  }

  public Date getCreatedDateTo() {
    return this.dataModel.getCreatedDateTo();
  }

  public void setCreatedDateTo(Date createdDateTo) {
    this.dataModel.setCreatedDateTo(createdDateTo);
  }

  public Date getExpiryDateFrom() {
    return this.dataModel.getExpiryDateFrom();
  }

  public void setExpiryDateFrom(Date expiryDateFrom) {
    this.dataModel.setExpiryDateTo(expiryDateFrom);
  }

  public Date getExpiryDateTo() {
    return this.dataModel.getExpiryDateTo();
  }

  public void setExpiryDateTo(Date expiryDateTo) {
    this.dataModel.setExpiryDateTo(expiryDateTo);
  }

  public String getCategory() {
    return this.dataModel.getCategory();
  }

  public void setCategory(String category) {
    this.dataModel.setCategory(category);
  }

  public List<String> getTaskColumns() {
    if (CollectionUtils.isEmpty(taskColumns)) {
      taskColumns = new ArrayList<>();
      for(TaskColumn column : TaskColumn.values()) {
        taskColumns.add(column.toString());
      }
    }
    return taskColumns;
  }

  public void setTaskColumns(List<String> taskColumns) {
    this.taskColumns = taskColumns;
  }
  
  public String getSortField() {
    return sortField;
  }
  
  public void setSortField(String sortField) {
    this.sortField = sortField;
  }
  
  public boolean isSortAscending() {
    return sortAscending;
  }
  
  public void setSortAscending(boolean sortAscending) {
    this.sortAscending = sortAscending;
  }

  public DashboardTaskLazyDataModel getDataModel() {
    return dataModel;
  }
  
  public void setDataModel(DashboardTaskLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }
  
  @JsonIgnore
  public int getTaskCount() {
    return getDataModel().getRowCount();
  }
}