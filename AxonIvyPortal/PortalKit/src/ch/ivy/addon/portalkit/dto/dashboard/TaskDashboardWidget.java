package ch.ivy.addon.portalkit.dto.dashboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.primefaces.event.ToggleEvent;
import org.primefaces.event.data.SortEvent;
import org.primefaces.model.Visibility;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.TaskColumn;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

public class TaskDashboardWidget extends DashboardWidget {
  private static final long serialVersionUID = 3048837559125720787L;

  private Map<String, Boolean> columns;
  private String sortField;
  private boolean sortAscending;
  
  @JsonIgnore
  private DashboardTaskLazyDataModel dataModel;
  
  public TaskDashboardWidget() {
    dataModel = new DashboardTaskLazyDataModel();
    columns = new LinkedHashMap<>();
    for (TaskColumn column : TaskColumn.values()) {
      columns.put(column.toString().toLowerCase(), true);
    }
  }
  
  public void onSort(SortEvent event) {
    setSortField(event.getSortColumn().getField());
    setSortAscending(event.isAscending());
  }
  
  public void onToggleColumns(ToggleEvent e) {
    this.columns.put(columns.keySet().toArray(String[]::new)[(int) e.getData() - 1], e.getVisibility() == Visibility.VISIBLE);
  }
  
  public boolean isRendered(String column) {
    return this.columns.keySet().contains(column.toLowerCase());
  }
  
  public boolean isVisible(String column) {
    return this.columns.getOrDefault(column.toLowerCase(), false);
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
    this.dataModel.setExpiryDateFrom(expiryDateFrom);
  }

  public Date getExpiryDateTo() {
    return this.dataModel.getExpiryDateTo();
  }

  public void setExpiryDateTo(Date expiryDateTo) {
    this.dataModel.setExpiryDateTo(expiryDateTo);
  }

  public List<String> getCategories() {
    return this.dataModel.getCategories();
  }

  public void setCategories(List<String> categories) {
    this.dataModel.setCategories(categories);
  }
  
  @JsonIgnore
  public String getDisplayCategories() {
    return Optional.ofNullable(getCategories()).orElse(new ArrayList<>()).stream().collect(Collectors.joining(", "));
  }
  
  public Map<String, Boolean> getColumns() {
    return columns;
  }

  public void setColumns(Map<String, Boolean> columns) {
    this.columns = columns;
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