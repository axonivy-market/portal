package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardTaskSearchCriteria;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.util.threadcontext.IvyThreadContext;

public class DashboardTaskLazyDataModel extends LazyDataModel<ITask> {

  private static final long serialVersionUID = -6615871274830927272L;
  
  private DashboardTaskSearchCriteria criteria;
  private boolean isFirstTime = true;
  private List<ITask> tasks;
  private CompletableFuture<Void> future;
  private TaskQuery query;
  
  public DashboardTaskLazyDataModel() {
    criteria = new DashboardTaskSearchCriteria();
    tasks = new ArrayList<>();
  }
  
  @Override
  public List<ITask> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    int page = (first / pageSize) + 1;
    if (isFirstTime) {
      isFirstTime = false;
      if (future != null) {
        try {
          future.get();
        } catch (InterruptedException | ExecutionException e) {
          throw new PortalException(e);
        }
      }
    } else {
      if (page == 1) {
        criteria.setSortField(sortField);
        criteria.setSortDescending(sortOrder == SortOrder.DESCENDING);
        query = criteria.buildQuery();
      }
      tasks = Ivy.wf().getTaskQueryExecutor().getResults(query, first, pageSize * (page <= 2 ? 5 : 3));
    }
    int rowsOfPreviousPages = (page - 1) * pageSize;
    int rowCount = tasks.size() + rowsOfPreviousPages;
    List<ITask> result = new ArrayList<>();
    for (int i = 0; i < Math.min(pageSize, tasks.size()); i++) {
      result.add(tasks.get(i));
    }
    setRowCount(rowCount);
    return result;
  }
  
  public void loadFirstTime() {
    query = criteria.buildQuery();
    Object memento = IvyThreadContext.saveToMemento();
    future = CompletableFuture.runAsync(() -> {
      IvyThreadContext.restoreFromMemento(memento);
      tasks = Ivy.wf().getTaskQueryExecutor().getResults(query, 0, getPageSize() * 5);
      IvyThreadContext.reset();
    });
    isFirstTime = true;
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
