package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardCaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.DashboardCaseService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.util.threadcontext.IvyThreadContext;

public class DashboardCaseLazyDataModel extends LiveScrollLazyModel<ICase> {

  private static final long serialVersionUID = -6615871274830927272L;

  private DashboardCaseSearchCriteria criteria;
  private List<ICase> cases;
  private Map<Long, ICase> mapCases;
  private int countLoad;
  private boolean isFirstTime = true;
  private CompletableFuture<Void> future;
  List<ICase> foundCases;

  public DashboardCaseLazyDataModel() {
    criteria = new DashboardCaseSearchCriteria();
    cases = new ArrayList<>();
    mapCases = new HashMap<>();
    foundCases = new ArrayList<>();
  }

  @Override
  public List<ICase> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    if (first == 0) {
      PrimeFaces.current().executeScript("resizeTableBody();");
    }
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
        cases.clear();
        if (sortBy.entrySet().iterator().hasNext()) {
          Map.Entry<String, SortMeta> sortEntry = sortBy.entrySet().iterator().next();
          if (sortEntry != null && sortEntry.getValue() != null) {
            SortMeta sortMeta = sortEntry.getValue();
            criteria.setSortField(sortMeta.getField());
            criteria.setSortDescending(sortMeta.getOrder().isDescending());
          }
        }
      }
      foundCases = DashboardCaseService.getInstance().findByCaseQuery(criteria.buildQuery(), first, pageSize);
      addDistict(cases, foundCases);
      mapCases.putAll(foundCases.stream().collect(Collectors.toMap(o -> o.getId(), Function.identity())));
    }

    int rowCount = 0;
    if (foundCases.size() >= pageSize) {
      rowCount = first + pageSize + 1;
    } else {
      rowCount = first + foundCases.size();
    }
    setRowCount(rowCount);
    return foundCases;
  }

  public void loadFirstTime() {
    Object memento = IvyThreadContext.saveToMemento();
    future = CompletableFuture.runAsync(() -> {
      IvyThreadContext.restoreFromMemento(memento);
      foundCases = DashboardCaseService.getInstance().findByCaseQuery(criteria.buildQuery(), 0, 25);
      addDistict(cases, foundCases);
      mapCases.putAll(foundCases.stream().collect(Collectors.toMap(o -> o.getId(), Function.identity())));
      IvyThreadContext.reset();
    });
    isFirstTime = true;
  }

  private void addDistict(List<ICase> cases, List<ICase> foundCases) {
    for (ICase found : foundCases) {
      cases.removeIf(task -> task.getId() == found.getId());
    }
    cases.addAll(foundCases);
  }

  @Override
  public List<ICase> getResults() {
    return this.cases;
  }

  @Override
  public ICase getRowData(String rowKey) {
    ICase caze = mapCases.get(Long.valueOf(rowKey));
    if (caze != null) {
      return caze;
    }
    return null;
  }

  @Override
  public String getRowKey(ICase caze) {
    return String.valueOf(caze.getId());
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

  public void setShowPinnedItem(boolean showPinnedItem) {
    criteria.setShowPinnedItem(showPinnedItem);
  }
}
