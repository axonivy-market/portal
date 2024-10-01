package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardProcessTaskSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.DashboardTaskService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.jsf.primefaces.sort.SortMetaConverter;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.util.threadcontext.IvyThreadContext;

public class DashboardProcessTaskLazyDataModel extends LazyDataModel<ITask> {

  private static final long serialVersionUID = -6615871274830927272L;

  private static final int QUERY_PAGES_AT_FIRST_TIME = 5;
  private static final int QUERY_PAGES = 3;

  private DashboardProcessTaskSearchCriteria criteria;
  private boolean isFirstTime = true;
  private List<ITask> tasks;
  private CompletableFuture<Void> future;

  public DashboardProcessTaskLazyDataModel(Long processStartId, String expressProcessName) {
    criteria = new DashboardProcessTaskSearchCriteria(processStartId, expressProcessName);
    tasks = new ArrayList<>();
  }

  @Override
  public List<ITask> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
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
        SortMetaConverter sort = new SortMetaConverter(sortBy);
        criteria.setSortField(sort.toField());
        criteria.setSortDescending(sort.toOrder() == SortOrder.DESCENDING);
      }
      tasks = DashboardTaskService.getInstance().findByTaskQuery(criteria.buildQuery(), first,
          pageSize * (first <= pageSize ? QUERY_PAGES_AT_FIRST_TIME : QUERY_PAGES));
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
    Object memento = IvyThreadContext.saveToMemento();
    future = CompletableFuture.runAsync(() -> {
      IvyThreadContext.restoreFromMemento(memento);
      tasks = DashboardTaskService.getInstance().findByTaskQuery(criteria.buildQuery(), 0,
          getPageSize() * QUERY_PAGES_AT_FIRST_TIME);
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

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    // TODO Auto-generated method stub
    return 0;
  }
}
