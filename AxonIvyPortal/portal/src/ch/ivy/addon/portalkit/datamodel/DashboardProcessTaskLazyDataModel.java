package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardProcessTaskSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.DashboardTaskService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.jsf.primefaces.legazy.LazyDataModel7;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.util.threadcontext.IvyThreadContext;

public class DashboardProcessTaskLazyDataModel extends LazyDataModel7<ITask> {

  private static final long serialVersionUID = -6615871274830927272L;

  private static final int QUERY_PAGES_AT_FIRST_TIME = 5;
  private static final int QUERY_PAGES = 3;

  private DashboardProcessTaskSearchCriteria criteria;
  private boolean isFirstTime = true;
  private List<ITask> tasks;
  private CompletableFuture<Void> future;
  private TaskQuery query;

  public DashboardProcessTaskLazyDataModel(Long processStartId, String expressProcessName) {
    criteria = new DashboardProcessTaskSearchCriteria(processStartId, expressProcessName);
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
        query = criteria.buildQuery();
      }
      tasks = DashboardTaskService.getInstance().findByTaskQuery(query, first, pageSize * (first <= pageSize ? QUERY_PAGES_AT_FIRST_TIME : QUERY_PAGES));
    }
    int rowCount = tasks.size() + first;
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
      tasks = DashboardTaskService.getInstance().findByTaskQuery(query, 0, getPageSize() * QUERY_PAGES_AT_FIRST_TIME);
      IvyThreadContext.reset();
    });
    isFirstTime = true;
  }

  @Override
  public ITask getRowData(String rowKey) {
    for (ITask task : tasks) {
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
    super.setRowIndex(index);
  }

  /**
   * @hidden
   */
  @Override
  public ITask getRowData() {
    return super.getRowData();
  }

  /**
   * @hidden
   */
  @Override
  public boolean isRowAvailable() {
    return super.isRowAvailable();
  }

  public DashboardProcessTaskSearchCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(DashboardProcessTaskSearchCriteria criteria) {
    this.criteria = criteria;
  }
}
