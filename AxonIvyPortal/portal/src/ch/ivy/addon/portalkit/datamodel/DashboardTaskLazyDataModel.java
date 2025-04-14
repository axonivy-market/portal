package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardTaskSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.DashboardTaskService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.util.threadcontext.IvyThreadContext;

public class DashboardTaskLazyDataModel extends LiveScrollLazyModel<ITask> {

  private static final long serialVersionUID = -6615871274830927272L;

  private DashboardTaskSearchCriteria criteria;
  private List<ITask> tasks;
  private Map<Long, ITask> mapTasks;
  private int countLoad;
  private boolean isFirstTime = true;
  private CompletableFuture<Void> future;
  List<ITask> foundTasks;

  public DashboardTaskLazyDataModel() {
    criteria = new DashboardTaskSearchCriteria();
    tasks = new ArrayList<>();
    foundTasks = new ArrayList<>();
    mapTasks = new HashedMap<>();
  }

  @Override
  public List<ITask> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
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
        tasks.clear();
        if (sortBy.entrySet().iterator().hasNext()) {
          Map.Entry<String, SortMeta> sortEntry = sortBy.entrySet().iterator().next();
          if (sortEntry != null && sortEntry.getValue() != null) {
            SortMeta sortMeta = sortEntry.getValue();
            criteria.setSortField(sortMeta.getField());
            criteria.setSortDescending(sortMeta.getOrder().isDescending());
          }
        }
      }
      foundTasks = DashboardTaskService.getInstance().findByTaskQuery(criteria.buildQuery(), first, pageSize);
      addDistict(tasks, foundTasks);
      mapTasks.putAll(foundTasks.stream().collect(Collectors.toMap(o -> o.getId(), Function.identity())));
    }

    int rowCount = 0;
    if (foundTasks.size() >= pageSize) {
      rowCount = first + pageSize + 1;
    } else {
      rowCount = first + foundTasks.size();
    }
    setRowCount(rowCount);
    return foundTasks;
  }

  public void loadFirstTime() {
    Object memento = IvyThreadContext.saveToMemento();
    future = CompletableFuture.runAsync(() -> {
      IvyThreadContext.restoreFromMemento(memento);
      foundTasks = DashboardTaskService.getInstance().findByTaskQuery(criteria.buildQuery(), 0, 25);
      addDistict(tasks, foundTasks);
      mapTasks.putAll(foundTasks.stream().collect(Collectors.toMap(o -> o.getId(), Function.identity())));
      IvyThreadContext.reset();
    });
    isFirstTime = true;
  }

  private void addDistict(List<ITask> tasks, List<ITask> foundTasks) {
    for (ITask found : foundTasks) {
      tasks.removeIf(task -> task.getId() == found.getId());
    }
    tasks.addAll(foundTasks);
  }

  @Override
  public ITask getRowData(String rowKey) {
    ITask task = mapTasks.get(Long.valueOf(rowKey));
    if (task != null) {
      return task;
    }
    return null;
  }

  @Override
  public String getRowKey(ITask task) {
    return String.valueOf(task.getId());
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

  @Override
  public List<ITask> getResults() {
    return this.tasks;
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
