package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.ws.addon.SortType;
import ch.ivy.ws.addon.TaskAssigneeType;
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
  private Long serverId;

  public TaskLazyDataModel() {
    super();
    data = new ArrayList<>();
    displayedTaskMap = new HashMap<>();
    notDisplayedTaskMap = new HashMap<>();
    criteria = buildCriteria();
    criteria.setSortType(SortType.ID);
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
    
    RequestContext.getCurrentInstance().execute("taskListToolKit.responsive()");

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
            count, criteria, serverId});
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
    Comparator<RemoteTask> comparator = comparator(RemoteTask::getId);
    if (criteria.getSortType() == SortType.PRIORITY) {
      comparator = comparator(RemoteTask::getPriority);
    } else if (criteria.getSortType() == SortType.NAME) {
      comparator = comparatorString(RemoteTask::getName);
    } else if (criteria.getSortType() == SortType.ACTIVATOR) {
      comparator = comparatorString(activatorName());
    } else if (criteria.getSortType() == SortType.CREATION_TIME) {
      comparator = comparator(RemoteTask::getStartTimestamp);
    } else if (criteria.getSortType() == SortType.EXPIRY_TIME) {
      comparator = comparator(RemoteTask::getExpiryTimestamp);
    } else if (criteria.getSortType() == SortType.STATE) {
      comparator = comparator(RemoteTask::getState);
    }

    if (criteria.getSortDescending()) {
      comparator = comparator.reversed();
    }

    notDisplayedTasks.sort(comparator);
    return notDisplayedTasks;
  }

  private Function<? super RemoteTask, String> activatorName() {
    return r -> {
      if(StringUtils.isNotEmpty(r.getActivatorFullName())){
        return r.getActivatorFullName();
      }
      return r.getActivatorName();
    };
  }
  
  private <U extends Comparable<? super U>> Comparator<RemoteTask> comparator(
      Function<? super RemoteTask, ? extends U> function) {
    return Comparator.comparing(function, Comparator.nullsFirst(Comparator.naturalOrder()));
  }
  
  private <U extends Comparable<String>> Comparator<RemoteTask> comparatorString(
      Function<? super RemoteTask, String> function) {
    return Comparator.comparing(function, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
  }

  private String keyOfTask(RemoteTask task) {
    String keyOfTask = "serverId=" + task.getApplicationRegister().getServerId() + ";taskId=" + task.getId();
    return keyOfTask;
  }

  private int getTaskCount(TaskSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countTaskCaller = new IvyComponentLogicCaller<>();
    Long taskCount =
        countTaskCaller.invokeComponentLogic(TASK_WIDGET_COMPONENT_ID, "#{logic.countTasks}", new Object[] {criteria, serverId});
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

  public void setSortType(SortType sortType, boolean sortDescending) {
    criteria.setSortType(sortType);
    criteria.setSortDescending(sortDescending);
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
      criteria.addIncludedStates(TaskState.UNASSIGNED);
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
    this.serverId = serverId;
  }

  public void setInvolvedApplications(String... involvedApplications) {
    criteria.setInvolvedApplications(involvedApplications);
  }
  
  public void setTaskAssigneeType(TaskAssigneeType assigneeType) {
    criteria.setTaskAssigneeType(assigneeType);
  }

  public TaskSearchCriteria getCriteria() {
    return criteria;
  }

  public SortType getSortType() {
    return criteria.getSortType();
  }

  public boolean isSortDescending() {
    return criteria.getSortDescending();
  }
}
