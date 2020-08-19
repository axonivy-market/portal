package ch.ivy.addon.portalkit.datamodel;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardTaskSearchCriteria;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class DashboardTaskLazyDataModel extends LazyDataModel<ITask> {

  private static final long serialVersionUID = -6615871274830927272L;
  
  private DashboardTaskSearchCriteria criteria;
  private String widgetId;
  private boolean isFirstTime = true;
  private List<ITask> tasks;
  private String message;
  
  public DashboardTaskLazyDataModel() {
    criteria = new DashboardTaskSearchCriteria();
    message = "Loading";
  }
  
  @Override
  public List<ITask> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    if (isFirstTime) {
      isFirstTime = false;
      return tasks;
    }
    criteria.setSortField(sortField);
    criteria.setSortDescending(sortOrder == SortOrder.DESCENDING);
    TaskQuery query = criteria.buildQuery();
    List<ITask> data = Ivy.wf().getTaskQueryExecutor().getResults(query, first, pageSize);
    if (first == 0) {
      setRowCount((int) Ivy.wf().getTaskQueryExecutor().getCount(query));
      PrimeFaces.current().executeScript("rcUpdateTaskCount" + widgetId + "()");
    }
    return data;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void loadFirstTime() {
    TaskQuery query = criteria.buildQuery();
    tasks = Ivy.wf().getTaskQueryExecutor().getResults(query, 0, getPageSize());
    setRowCount((int) Ivy.wf().getTaskQueryExecutor().getCount(query));
    PrimeFaces.current().executeScript("rcUpdateTaskCount" + widgetId + "()");
    isFirstTime = true;
    message = "No records found.";
  }
  
  public String getWidgetId() {
    return widgetId;
  }
  
  public void setWidgetId(String widgetId) {
    this.widgetId = widgetId;
  }

  public DashboardTaskSearchCriteria getCriteria() {
    return criteria;
  }
  
  public void setCriteria(DashboardTaskSearchCriteria criteria) {
    this.criteria = criteria;
  }
  
  public boolean getCanWorkOn() {
    return criteria.getCanWorkOn();
  }
  
  public void setCanWorkOn(boolean canWorkOn) {
    criteria.setCanWorkOn(canWorkOn);
  }

  public String getTaskName() {
    return criteria.getName();
  }

  public void setTaskName(String taskName) {
    criteria.setName(taskName);
  }

  public String getDescription() {
    return criteria.getDescription();
  }

  public void setDescription(String description) {
    criteria.setDescription(description);
  }

  public List<WorkflowPriority> getPriorities() {
    return criteria.getPriorities();
  }

  public void setPriorities(List<WorkflowPriority> priorities) {
    criteria.setPriorities(priorities);
  }

  public List<TaskState> getStates() {
    return criteria.getStates();
  }

  public void setStates(List<TaskState> states) {
    criteria.setStates(states);
  }

  public List<String> getResponsibles() {
    return criteria.getResponsibles();
  }

  public void setResponsibles(List<String> responsibles) {
    criteria.setResponsibles(responsibles);
  }

  public Date getCreatedDateFrom() {
    return criteria.getCreatedFrom();
  }

  public void setCreatedDateFrom(Date createdDateFrom) {
    criteria.setCreatedFrom(createdDateFrom);
  }

  public Date getCreatedDateTo() {
    return criteria.getCreatedTo();
  }

  public void setCreatedDateTo(Date createdDateTo) {
    criteria.setCreatedTo(createdDateTo);
  }

  public Date getExpiryDateFrom() {
    return criteria.getExpiryFrom();
  }

  public void setExpiryDateFrom(Date expiryDateFrom) {
    criteria.setExpiryFrom(expiryDateFrom);
  }

  public Date getExpiryDateTo() {
    return criteria.getExpiryTo();
  }

  public void setExpiryDateTo(Date expiryDateTo) {
    criteria.setExpiryTo(expiryDateTo);
  }

  public String getCategory() {
    return criteria.getCategory();
  }

  public void setCategory(String category) {
    criteria.setCategory(category);
  }
  
}
