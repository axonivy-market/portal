package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardCaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.DashboardCaseService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.util.threadcontext.IvyThreadContext;

public class DashboardCaseLazyDataModel extends LazyDataModel<ICase> {

  private static final long serialVersionUID = -6615871274830927272L;

  private static final int QUERY_PAGES_AT_FIRST_TIME = 5;
  private static final int QUERY_PAGES = 3;

  private DashboardCaseSearchCriteria criteria;
  private boolean isFirstTime = true;
  private List<ICase> cases;
  private CaseQuery query;
  private int rowIndex;
  private CompletableFuture<Void> future;
  private int countLoad;

  public DashboardCaseLazyDataModel() {
    criteria = new DashboardCaseSearchCriteria();
    cases = new ArrayList<>();
  }

  @Override
  public List<ICase> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
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
        Map.Entry<String, SortMeta> sortEntry = sortBy.entrySet().iterator().next();
        if (sortEntry != null && sortEntry.getValue() != null) {
          SortMeta sortMeta = sortEntry.getValue();
          criteria.setSortField(sortMeta.getField());
          criteria.setSortDescending(sortMeta.getOrder().isDescending());
        }
        query = criteria.buildQuery();
      }
      cases = DashboardCaseService.getInstance().findByCaseQuery(query, first,
          pageSize * (first <= pageSize ? QUERY_PAGES_AT_FIRST_TIME : QUERY_PAGES));
    }

    int rowCount = cases.size() + first;
    List<ICase> result = new ArrayList<>();
    for (int i = 0; i < Math.min(pageSize, cases.size()); i++) {
      result.add(cases.get(i));
    }
    setRowCount(rowCount);
    setCountLoad(getCountLoad() + 1);
    return result;
  }

  public void loadFirstTime() {
    query = criteria.buildQuery();
    Object memento = IvyThreadContext.saveToMemento();
    future = CompletableFuture.runAsync(() -> {
      IvyThreadContext.restoreFromMemento(memento);
      cases = DashboardCaseService.getInstance().findByCaseQuery(query, 0, getPageSize() * QUERY_PAGES_AT_FIRST_TIME);
      IvyThreadContext.reset();
    });
    isFirstTime = true;
    setCountLoad(getCountLoad() + 1);
  }

  @Override
  public ICase getRowData(String rowKey) {
    for (ICase caze : cases) {
      if (caze.getId() == Long.valueOf(rowKey)) {
        return caze;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(ICase caze) {
    return String.valueOf(caze.getId());
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
  public ICase getRowData() {
    return super.getRowData();
  }

  /**
   * @hidden
   */
  @Override
  public boolean isRowAvailable() {
    return super.isRowAvailable();
  }

  public DashboardCaseSearchCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(DashboardCaseSearchCriteria criteria) {
    this.criteria = criteria;
  }

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return this.getRowCount();
  }

  public int getCountLoad() {
    return countLoad;
  }

  public void setCountLoad(int countLoad) {
    this.countLoad = countLoad;
  }

}
