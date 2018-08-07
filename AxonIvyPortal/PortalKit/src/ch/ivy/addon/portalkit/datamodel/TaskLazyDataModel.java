package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.CallerBean;
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
  private SortType sortType;
  private String keyword;
  private String category;
  private boolean ignoreInvolvedUser;
  private Long taskId;
  private Long caseId;

  public TaskLazyDataModel() {
    super();
    data = new ArrayList<>();
    displayedTaskMap = new HashMap<>();
    notDisplayedTaskMap = new HashMap<>();
    sortType = SortType.BY_PRIORITY;
  }

  @Override
  public List<RemoteTask> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    TaskSearchCriteria criteria = buildCriteria();
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
    CallerBean<List<RemoteTask>> findTaskCaller = new CallerBean<>();
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
    if (SortType.BY_EXPIRY_TIME == sortType) {
      notDisplayedTasks.sort(new TaskExpiryComparator());
    } else {
      notDisplayedTasks.sort(new TaskPriorityComparator());
    }
    return notDisplayedTasks;
  }

  private String keyOfTask(RemoteTask task) {
    String keyOfTask = "serverId=" + task.getApplicationRegister().getServerId() + ";taskId=" + task.getId();
    return keyOfTask;
  }

  private int getTaskCount(TaskSearchCriteria criteria) {
    CallerBean<Long> countTaskCaller = new CallerBean<>();
    Long taskCount =
        countTaskCaller.invokeComponentLogic(TASK_WIDGET_COMPONENT_ID, "#{logic.countTasks}", new Object[] {criteria});
    return taskCount.intValue();
  }

  private TaskSearchCriteria buildCriteria() {
    TaskSearchCriteria crit = new TaskSearchCriteria();
    crit.setInvolvedUsername(Ivy.session().getSessionUserName());
    crit.setIncludedStates(new TaskState[] {TaskState.SUSPENDED, TaskState.PARKED, TaskState.RESUMED});
    if (taskId != null) {
      crit.setTaskId(taskId);
      crit.addIncludedStates(TaskState.DONE);
    }

    if (caseId != null) {
      crit.setCaseId(caseId);
    }

    if (ignoreInvolvedUser) {
      crit.setIgnoreInvolvedUser(true);
      crit.addIncludedStates(TaskState.DONE);
    }

    if (!StringUtils.isEmpty(category)) {
      crit.setCategory(category);
    }

    if (!StringUtils.isEmpty(keyword)) {
      crit.setKeyword(keyword);
    }

    if (sortType != null) {
      if (SortType.BY_PRIORITY == sortType) {
        crit.setSortByPriority(true);
      } else if (SortType.BY_EXPIRY_TIME == sortType) {
        crit.setSortByExpiryDate(true);
      }
    }

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

  public SortType getSortType() {
    return sortType;
  }

  public void setSortType(SortType sortType) {
    this.sortType = sortType;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
    this.ignoreInvolvedUser = ignoreInvolvedUser;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public void setCaseId(Long caseId) {
    this.caseId = caseId;
  }
}
