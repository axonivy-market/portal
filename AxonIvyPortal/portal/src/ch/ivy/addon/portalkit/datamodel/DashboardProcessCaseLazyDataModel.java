package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardProcessCaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.DashboardCaseService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.jsf.primefaces.sort.SortMetaConverter;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.util.threadcontext.IvyThreadContext;

public class DashboardProcessCaseLazyDataModel extends LazyDataModel<ICase> {

  private static final long serialVersionUID = -6615871274830927272L;

  private static final int QUERY_PAGES_AT_FIRST_TIME = 5;
  private static final int QUERY_PAGES = 3;

  private DashboardProcessCaseSearchCriteria criteria;
  private boolean isFirstTime = true;
  private List<ICase> cases;
  private CompletableFuture<Void> future;

  public DashboardProcessCaseLazyDataModel(Long processStartId) {
    criteria = new DashboardProcessCaseSearchCriteria(processStartId);
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
        SortMetaConverter sort = new SortMetaConverter(sortBy);
        criteria.setSortField(sort.toField());
        criteria.setSortDescending(sort.toOrder() == SortOrder.DESCENDING);
      }
      cases = DashboardCaseService.getInstance().findByCaseQuery(criteria.buildQuery(), first,
          pageSize * (first <= pageSize ? QUERY_PAGES_AT_FIRST_TIME : QUERY_PAGES));
    }
    int rowCount = cases.size() + first;
    List<ICase> result = new ArrayList<>();
    for (int i = 0; i < Math.min(pageSize, cases.size()); i++) {
      result.add(cases.get(i));
    }
    setRowCount(rowCount);
    return result;
  }

  public void loadFirstTime() {
    Object memento = IvyThreadContext.saveToMemento();
    future = CompletableFuture.runAsync(() -> {
      IvyThreadContext.restoreFromMemento(memento);
      cases = DashboardCaseService.getInstance().findByCaseQuery(criteria.buildQuery(), 0,
          getPageSize() * QUERY_PAGES_AT_FIRST_TIME);
      IvyThreadContext.reset();
    });
    isFirstTime = true;
  }

  @Override
  public ICase getRowData(String rowKey) {
    for (ICase iCase : cases) {
      if (iCase.getId() == Long.valueOf(rowKey)) {
        return iCase;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(ICase iCase) {
    return String.valueOf(iCase.getId());
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

  public DashboardProcessCaseSearchCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(DashboardProcessCaseSearchCriteria criteria) {
    this.criteria = criteria;
  }

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    // TODO Auto-generated method stub
    return 0;
  }
}
