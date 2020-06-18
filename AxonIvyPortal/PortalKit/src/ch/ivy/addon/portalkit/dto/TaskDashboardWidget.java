package ch.ivy.addon.portalkit.dto;

import java.util.Date;
import java.util.List;

import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

public class TaskDashboardWidget extends DashboardWidget {
  private static final long serialVersionUID = 3048837559125720787L;

  private String taskId;
  private String taskName;
  private String description;
  private List<WorkflowPriority> priorities;
  private List<TaskState> states;
  private String responsible;
  private Date createdDateFrom;
  private Date createdDateTo;
  private Date expiryDateFrom;
  private Date expiryDateTo;
  private List<String> categories;
  private List<String> taskColumns;

  public String getTaskId() {
    return taskId;
  }
  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }
  public String getTaskName() {
    return taskName;
  }
  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public List<WorkflowPriority> getPriorities() {
    return priorities;
  }
  public void setPriorities(List<WorkflowPriority> priorities) {
    this.priorities = priorities;
  }
  public List<TaskState> getStates() {
    return states;
  }
  public void setStates(List<TaskState> states) {
    this.states = states;
  }
  public String getResponsible() {
    return responsible;
  }
  public void setResponsible(String responsible) {
    this.responsible = responsible;
  }
  public Date getCreatedDateFrom() {
    return createdDateFrom;
  }
  public void setCreatedDateFrom(Date createdDateFrom) {
    this.createdDateFrom = createdDateFrom;
  }
  public Date getCreatedDateTo() {
    return createdDateTo;
  }
  public void setCreatedDateTo(Date createdDateTo) {
    this.createdDateTo = createdDateTo;
  }
  public Date getExpiryDateFrom() {
    return expiryDateFrom;
  }
  public void setExpiryDateFrom(Date expiryDateFrom) {
    this.expiryDateFrom = expiryDateFrom;
  }
  public Date getExpiryDateTo() {
    return expiryDateTo;
  }
  public void setExpiryDateTo(Date expiryDateTo) {
    this.expiryDateTo = expiryDateTo;
  }
  public List<String> getCategories() {
    return categories;
  }
  public void setCategories(List<String> categories) {
    this.categories = categories;
  }
  public List<String> getTaskColumns() {
    return taskColumns;
  }
  public void setTaskColumns(List<String> taskColumns) {
    this.taskColumns = taskColumns;
  }
}
