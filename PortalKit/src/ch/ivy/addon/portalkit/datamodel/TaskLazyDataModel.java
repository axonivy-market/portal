package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.comparator.TaskExpiryComparator;
import ch.ivy.addon.portalkit.comparator.TaskPriorityComparator;
import ch.ivy.addon.portalkit.enums.SortType;
import ch.ivy.ws.addon.TaskSearchCriteria;
import ch.ivy.ws.addon.TaskState;
import ch.ivyteam.ivy.environment.Ivy;

public class TaskLazyDataModel extends LazyDataModel<RemoteTask> {
  private static final String TASK_WIDGET_COMPONENT_ID = "task-widget";
  private static final int BUFFER_LOAD = 10;
  private static final long serialVersionUID = 1L;
  private final List<RemoteTask> data;
  private Map<String, RemoteTask> displayedTaskMap;
  private Map<String, RemoteTask> notDisplayedTaskMap;

  private int rowIndex;
  private TaskSearchCriteria criteria;

  public TaskLazyDataModel() {
    super();
    data = new ArrayList<>();
    displayedTaskMap = new HashMap<>();
    notDisplayedTaskMap = new HashMap<>();
    criteria = buildCriteria();
    criteria.setSortById(true);
    criteria.setSortDescending(true);
  }

  @Override
  public List<RemoteTask> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    if (first == 0) {
      initializedDataModel(criteria);
    }

    List<RemoteTask> foundTasks = findTasks(first, pageSize, criteria);
    putTasksToNotDisplayedTaskMap(foundTasks);
    List<RemoteTask> notDisplayedTasks = sortTasksInNotDisplayedTaskMap();
    List<RemoteTask> displayedTasks = getDisplayedTasks(notDisplayedTasks, pageSize);
    storeDisplayedTasks(displayedTasks);

    return displayedTasks;
  }

  private void storeDisplayedTasks(List<RemoteTask> displayedTasks) {
    data.addAll(displayedTasks);
    for (RemoteTask task : displayedTasks) {
      displayedTaskMap.put(keyOfTask(task), task);
    }
  }

  private List<RemoteTask> findTasks(int first, int pageSize, TaskSearchCriteria criteria) {
    IvyComponentLogicCaller<List<RemoteTask>> findTaskCaller = new IvyComponentLogicCaller<>();
    int startIndex = first - BUFFER_LOAD;
    int count = pageSize + BUFFER_LOAD;
    if (startIndex < 0) {
      startIndex = 0;
      count = first + pageSize;
    }
    List<RemoteTask> tasks =
        findTaskCaller.invokeComponentLogic(TASK_WIDGET_COMPONENT_ID, "#{logic.findTasks}", new Object[] {startIndex,
            count, criteria});
    return tasks;
  }

  private void initializedDataModel(TaskSearchCriteria criteria) {
    data.clear();
    displayedTaskMap.clear();
    notDisplayedTaskMap.clear();
    setRowCount(getTaskCount(criteria));
  }

  private List<RemoteTask> getDisplayedTasks(List<RemoteTask> notDisplayedTasks, int pageSize) {
    int displayedTaskCount = notDisplayedTasks.size() > pageSize ? pageSize : notDisplayedTasks.size();
    List<RemoteTask> displayedTasks = notDisplayedTasks.subList(0, displayedTaskCount);
    for (RemoteTask task : displayedTasks) {
      notDisplayedTaskMap.remove(keyOfTask(task));
    }
    return displayedTasks;
  }

  private void putTasksToNotDisplayedTaskMap(List<RemoteTask> tasks) {
    for (RemoteTask task : tasks) {
      String keyOfTask = keyOfTask(task);
      if (!displayedTaskMap.containsKey(keyOfTask) && !notDisplayedTaskMap.containsKey(keyOfTask)) {
        notDisplayedTaskMap.put(keyOfTask, task);
      }
    }
  }

  private List<RemoteTask> sortTasksInNotDisplayedTaskMap() {
    List<RemoteTask> notDisplayedTasks = new ArrayList<>();
    notDisplayedTasks.addAll(notDisplayedTaskMap.values());
    if (criteria.getSortByExpiryDate()) {
      notDisplayedTasks.sort(new TaskExpiryComparator());
    } else if (criteria.getSortByPriority()) {
      notDisplayedTasks.sort(new TaskPriorityComparator());
    } else if (criteria.getSortById()) {
      notDisplayedTasks.sort((a, b) -> (int) (b.getId() - a.getId()));
    }
    return notDisplayedTasks;
  }

  private String keyOfTask(RemoteTask task) {
    String keyOfTask = "serverId=" + task.getApplicationRegister().getServerId() + ";taskId=" + task.getId();
    return keyOfTask;
  }

  private int getTaskCount(TaskSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countTaskCaller = new IvyComponentLogicCaller<>();
    Long taskCount =
        countTaskCaller.invokeComponentLogic(TASK_WIDGET_COMPONENT_ID, "#{logic.countTasks}", new Object[] {criteria});
    return taskCount.intValue();
  }

  private TaskSearchCriteria buildCriteria() {
    TaskSearchCriteria crit = new TaskSearchCriteria();
    crit.setInvolvedUsername(Ivy.session().getSessionUserName());
    crit.setIncludedStates(new TaskState[] {TaskState.SUSPENDED, TaskState.PARKED, TaskState.RESUMED});
    return crit;
  }

  @Override
  public void setRowIndex(int index) {
    if (index >= data.size()) {
      index = -1;
    }
    this.rowIndex = index;
  }

  @Override
  public RemoteTask getRowData() {
    return data.get(rowIndex);
  }

  @Override
  public boolean isRowAvailable() {
    if (data == null) {
      return false;
    }
    return rowIndex >= 0 && rowIndex < data.size();
  }

  public void setSortType(SortType sortType) {
    criteria.setSortByExpiryDate(false);
    criteria.setSortByName(false);
    criteria.setSortByPriority(false);
    criteria.setSortById(false);
    criteria.setSortDescending(false);
    if (SortType.BY_PRIORITY == sortType) {
      criteria.setSortByPriority(true);
    } else if (SortType.BY_EXPIRY_TIME == sortType) {
      criteria.setSortByExpiryDate(true);
    }
  }

  public void setKeyword(String keyword) {
    criteria.setKeyword(keyword.trim());
  }

  public void setCategory(String category) {
    criteria.setCategory(category);
  }

  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
    criteria.setIgnoreInvolvedUser(ignoreInvolvedUser);
    if (ignoreInvolvedUser) {
      criteria.addIncludedStates(TaskState.DONE);
    }
  }

  public void setTaskId(Long taskId) {
    criteria.setTaskId(taskId);
    criteria.setIncludedStates(new TaskState[] {});
  }

  public void setCaseId(Long caseId) {
    criteria.setCaseId(caseId);
  }

  public void setServerId(Long serverId) {
    criteria.setServerId(serverId);
  }

  public void setInvolvedApplications(String... involvedApplications) {
    criteria.setInvolvedApplications(involvedApplications);
  }

  public TaskSearchCriteria getCriteria() {
    return criteria;
  }
}
