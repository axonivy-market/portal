package ch.ivy.addon.portalkit.datamodel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardTaskSearchCriteria;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.util.threadcontext.IvyThreadContext;

public class DashboardTaskLazyDataModel extends LazyDataModel<ITask> {

  private static final long serialVersionUID = -6615871274830927272L;
  
  private static final int QUERY_PAGES = 5;
  private static final int QUERY_PAGES_AT_FIRST_TIME = 3;
  
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
    if (isFirstTime) {
      isFirstTime = false;
      if (future != null) {
        try {
          future.get();
        } catch (Exception e) {
          throw new PortalException(e);
        }
      }
    } else {
      if (first == 0) {
        criteria.setSortField(sortField);
        criteria.setSortDescending(sortOrder == SortOrder.DESCENDING);
        try {
          query = criteria.buildQuery();
        } catch (ParseException e) {
          throw new PortalException(e);
        }
      }
      tasks = Ivy.wf().getTaskQueryExecutor().getResults(query, first, pageSize * (first <= pageSize ? QUERY_PAGES : QUERY_PAGES_AT_FIRST_TIME));
    }
    int rowCount = tasks.size() + first;
    List<ITask> result = new ArrayList<>();
    for (int i = 0; i < Math.min(pageSize, tasks.size()); i++) {
      result.add(tasks.get(i));
    }
    setRowCount(rowCount);
    return result;
  }
  
  public void loadFirstTime() throws ParseException {
    query = criteria.buildQuery();
    Object memento = IvyThreadContext.saveToMemento();
    future = CompletableFuture.runAsync(() -> {
      IvyThreadContext.restoreFromMemento(memento);
      tasks = Ivy.wf().getTaskQueryExecutor().getResults(query, 0, getPageSize() * QUERY_PAGES);
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

  public List<String> getCategories() {
    return criteria.getCategories();
  }

  public void setCategories(List<String> categories) {
    criteria.setCategories(categories);
  }
  
  public List<String> getUserFilterCategories() {
    return criteria.getUserFilterCategories();
  }

  public void setUserFilterCategories(List<String> categories) {
    criteria.setUserFilterCategories(categories);
  }
}
