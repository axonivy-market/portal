package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.Strings;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardTaskSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.DashboardTaskService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.util.threadcontext.IvyThreadContext;

public class DashboardTaskLazyDataModel extends LazyDataModel<ITask> {

  private static final long serialVersionUID = -6615871274830927272L;

  private DashboardTaskSearchCriteria criteria;
  private List<ITask> tasks;
  private int countLoad;
  private boolean isFirstTime = true;
  private CompletableFuture<Void> future;
  List<ITask> foundTasks;

  public DashboardTaskLazyDataModel() {
    criteria = new DashboardTaskSearchCriteria();
    tasks = new ArrayList<>();
    foundTasks = new ArrayList<>();
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
      foundTasks = DashboardTaskService.getInstance().findDashboardTaskByCriteria(criteria, first, pageSize);
      addDistict(tasks, foundTasks);
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
      foundTasks = DashboardTaskService.getInstance().findDashboardTaskByCriteria(criteria, 0, 25);
      addDistict(tasks, foundTasks);
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
    for (ITask task : tasks) {
      if (Strings.CS.equals(rowKey, task.uuid())) {
        return task;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(ITask task) {
    return task.uuid();
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

  public boolean isFilterTasksByCurrentCaseOwner() {
    return criteria.isFilterTasksByCurrentCaseOwner();
  }

  public void setFilterTasksByCurrentCaseOwner(boolean filterTasksByCurrentCaseOwner) {
    this.criteria.setFilterTasksByCurrentCaseOwner(filterTasksByCurrentCaseOwner);
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
