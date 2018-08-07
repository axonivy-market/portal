package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.Arrays;
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
import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.service.TaskQueryService;
import ch.ivy.addon.portalkit.support.TaskQueryCriteria;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.ws.addon.TaskSearchCriteria;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskLazyDataModel extends LazyDataModel<RemoteTask> {

  private static final long serialVersionUID = -6615871274830927272L;
  
  protected static final String TASK_WIDGET_COMPONENT_ID = "task-widget";
  protected static final int BUFFER_LOAD = 10;
  protected List<RemoteTask> data;
  protected Map<String, RemoteTask> displayedTaskMap;
  protected Map<String, RemoteTask> notDisplayedTaskMap;

  protected int rowIndex;
  protected TaskSearchCriteria searchCriteria;
  protected TaskQueryCriteria queryCriteria;
  protected Long serverId;
  protected Comparator<RemoteTask> comparator;
  
  protected boolean compactMode;
  protected String caseName;

  public TaskLazyDataModel() {
    super();
    data = new ArrayList<>();
    displayedTaskMap = new HashMap<>();
    notDisplayedTaskMap = new HashMap<>();
    searchCriteria = buildCriteria();
    queryCriteria = buildQueryCriteria();
    comparator = comparator(RemoteTask::getId);

    autoInitForNoAppConfiguration();
  }
  
  /**
   * Creates a new instance which extends from TaskLazyDataModel, and copy its properties from source.
   * 
   * @param source
   * @param toClass
   * @return
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public static <T extends TaskLazyDataModel> T newInstance(TaskLazyDataModel source, Class<T> toClass)
      throws InstantiationException, IllegalAccessException {
    T instance = toClass.newInstance();
    instance.setSearchCriteria(source.getSearchCriteria());
    instance.setQueryCriteria(source.getQueryCriteria());
    instance.setServerId(source.getServerId());
    instance.setCompactMode(source.isCompactMode());
    instance.setCaseName(source.getCaseName());
    return instance;
  }
  
  @Override
  public List<RemoteTask> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    if (first == 0) {
      initializedDataModel(searchCriteria);
    }

    List<RemoteTask> foundTasks = findTasks(first, pageSize, searchCriteria);
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

  /**
   * Calls the findTasks logic of TaskWidget Html dialog to find tasks.
   * 
   * @param first
   * @param pageSize
   * @param criteria
   * @return
   */
  protected List<RemoteTask> findTasks(int first, int pageSize, TaskSearchCriteria criteria) {
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
    buildQueryToSearchCriteria();
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
    comparator = comparator(RemoteTask::getId);
    if (TaskSortField.PRIORITY.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparator(RemoteTask::getPriority);
    } else if (TaskSortField.NAME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparatorString(RemoteTask::getName);
    } else if (TaskSortField.ACTIVATOR.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparatorString(activatorName());
    } else if (TaskSortField.CREATION_TIME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparator(RemoteTask::getStartTimestamp);
    } else if (TaskSortField.EXPIRY_TIME.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparator(RemoteTask::getExpiryTimestamp);
    } else if (TaskSortField.STATE.toString().equalsIgnoreCase(queryCriteria.getSortField())) {
      comparator = comparator(RemoteTask::getState);
    } else {
      extendSortTasksInNotDisplayedTaskMap();
    }

    if (queryCriteria.isSortDescending()) {
      comparator = comparator.reversed();
    }

    notDisplayedTasks.sort(comparator);
    return notDisplayedTasks;
  }

  protected Function<? super RemoteTask, String> activatorName() {
    return r -> {
      if (StringUtils.isNotEmpty(r.getActivatorFullName())) {
        return r.getActivatorFullName();
      }
      return r.getActivatorName();
    };
  }

  protected <U extends Comparable<? super U>> Comparator<RemoteTask> comparator(
      Function<? super RemoteTask, ? extends U> function) {
    return Comparator.comparing(function, Comparator.nullsFirst(Comparator.naturalOrder()));
  }

  protected <U extends Comparable<String>> Comparator<RemoteTask> comparatorString(
      Function<? super RemoteTask, String> function) {
    return Comparator.comparing(function, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
  }

  protected String keyOfTask(RemoteTask task) {
    String keyOfTask = "serverId=" + task.getApplicationRegister().getServerId() + ";taskId=" + task.getId();
    return keyOfTask;
  }

  /**
   * Calls the countTasks logic of TaskWidget Html dialog to count the number of found tasks.
   * 
   * @param criteria
   * @return
   */
  protected int getTaskCount(TaskSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countTaskCaller = new IvyComponentLogicCaller<>();
    Long taskCount =
        countTaskCaller.invokeComponentLogic(TASK_WIDGET_COMPONENT_ID, "#{logic.countTasks}", new Object[] {criteria,
            serverId});
    return taskCount.intValue();
  }

  /**
   * Initializes TaskSearchCriteria
   * 
   * @return
   */
  protected TaskSearchCriteria buildCriteria() {
    TaskSearchCriteria criteria = new TaskSearchCriteria();
    criteria.setInvolvedUsername(Ivy.session().getSessionUserName());
    return criteria;
  }

  /**
   * Initializes TaskQueryCriteria
   * 
   * @return
   */
  protected TaskQueryCriteria buildQueryCriteria() {
    TaskQueryCriteria jsonQuerycriteria = new TaskQueryCriteria();
    jsonQuerycriteria.setIncludedStates(new ArrayList<>(Arrays.asList(TaskState.SUSPENDED, TaskState.PARKED,
        TaskState.RESUMED)));
    jsonQuerycriteria.setSortField(TaskSortField.ID.toString());
    jsonQuerycriteria.setSortDescending(true);
    return jsonQuerycriteria;
  }

  /**
   * <p>
   * If your customized task list has new columns/fields, please get TaskQuery in queryCriteria and extend the sort query for these fields and
   * also override the "extendSortTasksInNotDisplayedTaskMap" method.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * if ("CustomVarcharField5".equalsIgnoreCase(queryCriteria.getSortField())) {
   *   if (queryCriteria.isSortDescending()) {
   *     queryCriteria.getTaskQuery().orderBy().customVarCharField5().descending();
   *   } else {
   *     queryCriteria.getTaskQuery().orderBy().customVarCharField5();
   *   }
   * }
   * </pre></code>
   * </p>
   * 
   * @return
   */
  protected void extendSort() {
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

  public void setSortField(String sortField, boolean sortDescending) {
    queryCriteria.setSortField(sortField);
    queryCriteria.setSortDescending(sortDescending);
  }

  public void setKeyword(String keyword) {
    queryCriteria.setKeyword(keyword.trim());
  }

  public void setCategory(String category) {
    queryCriteria.setCategory(category);
  }

  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
    if (ignoreInvolvedUser) {
      queryCriteria.addIncludedStates(Arrays.asList(TaskState.UNASSIGNED));
    }
    searchCriteria.setIgnoreInvolvedUser(ignoreInvolvedUser);
  }

  public void setTaskId(Long taskId) {
    queryCriteria.setTaskId(taskId);
    queryCriteria.setIncludedStates(new ArrayList<>());
    searchCriteria.setQueryByTaskId(true);
  }

  public void setCaseId(Long caseId) {
    queryCriteria.setCaseId(caseId);
  }

  public void setServerId(Long serverId) {
    this.serverId = serverId;
  }

  public void setQueryByBusinessCaseId(boolean isQueryByBusinessCaseId){
	  queryCriteria.setQueryByBusinessCaseId(isQueryByBusinessCaseId);
  }
  
  public void setInvolvedApplications(String... involvedApplications) {
    searchCriteria.setInvolvedApplications(involvedApplications);
  }

  public void setTaskAssigneeType(TaskAssigneeType assigneeType) {
    queryCriteria.setTaskAssigneeType(assigneeType);
  }

  public String getSortField() {
    return queryCriteria.getSortField();
  }

  public boolean isSortDescending() {
    return queryCriteria.isSortDescending();
  }

  public void addIncludedStates(List<TaskState> includedStates) {
    this.queryCriteria.addIncludedStates(includedStates);
  }

  public void setSearchCriteria(TaskSearchCriteria searchCriteria) {
    this.searchCriteria = searchCriteria;
  }

  public void setQueryCriteria(TaskQueryCriteria queryCriteria) {
    this.queryCriteria = queryCriteria;
  }

  public TaskSearchCriteria getSearchCriteria() {
    return searchCriteria;
  }

  public TaskQueryCriteria getQueryCriteria() {
    return queryCriteria;
  }

  public Long getServerId() {
    return serverId;
  }
  
  public boolean isCompactMode() {
    return compactMode;
  }

  public void setCompactMode(boolean compactMode) {
    this.compactMode = compactMode;
  }

  public String getCaseName() {
    return caseName;
  }

  public void setCaseName(String caseName) {
    this.caseName = caseName;
  }

  /**
   * Builds and converts TaskQuery to JsonQuery and put it into TaskSearchCriteria.
   */
  protected void buildQueryToSearchCriteria() {
    String jsonQuery =
        SubProcessCall.withPath("Functional Processes/BuildTaskJsonQuery").withStartSignature("buildTaskJsonQuery()").call()
            .get("jsonQuery", String.class);
    TaskQuery customizedTaskQuery = StringUtils.isNotBlank(jsonQuery) ? TaskQuery.fromJson(jsonQuery) : TaskQuery.create();
    queryCriteria.setTaskQuery(customizedTaskQuery);
    extendSort();
    searchCriteria.setJsonQuery(TaskQueryService.service().createQuery(queryCriteria).asJson());
  }

  /**
   * <p>
   * Your customized data model needs to override this method if your customized task list has new
   * columns/fields.
   * </p>
   * <p>
   * <b>Example: </b> <code><pre>
   * import ch.ivy.addon.portalkit.bo.RemoteTask;
   * 
   * // The value of queryCriteria.getSortField() is defined in the TaskColumnHeader Portal component when you use it to add new column headers.
   * if ("CustomVarcharField5".equalsIgnoreCase(queryCriteria.getSortField())) {
   * 
   *   // comparatorString(...): String, comparator(...): others.
   *   comparator = comparatorString(RemoteTask::getCustomVarCharField5);
   * }
   * </pre></code>
   * </p>
   * 
   * @return
   */
  protected void extendSortTasksInNotDisplayedTaskMap() {}

  private void autoInitForNoAppConfiguration() {
    Long serverId = SecurityServiceUtils.getServerIdFromSession();
    if (serverId != null) {
      setServerId(serverId);
    }

    String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
    if (StringUtils.isNotBlank(applicationName)) {
      setInvolvedApplications(applicationName);
    }
  }
}
