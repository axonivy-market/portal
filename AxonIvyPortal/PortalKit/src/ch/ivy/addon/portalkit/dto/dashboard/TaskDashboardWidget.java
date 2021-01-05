package ch.ivy.addon.portalkit.dto.dashboard;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.bo.ExpiryStatistic;
import ch.ivy.addon.portalkit.bo.TaskCategoryStatistic;
import ch.ivy.addon.portalkit.bo.TaskStateStatistic;
import ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;
import ch.ivyteam.ivy.workflow.TaskState;

public class TaskDashboardWidget extends DashboardWidget {
  private static final long serialVersionUID = 3048837559125720787L;

  @JsonIgnore
  private DashboardTaskLazyDataModel dataModel;
  @JsonIgnore
  private CheckboxTreeNode categoryTree;
  @JsonIgnore
  private CheckboxTreeNode[] categoryNodes;
  @JsonIgnore
  private Map<String, Long> taskByStateStatistic;
  @JsonIgnore
  private Map<String, Long> taskByCategoryStatistic;
  @JsonIgnore
  private Long numberOfTasksExpireThisWeek;
  @JsonIgnore
  private Long numberOfTasksExpireToday;
  
  public TaskDashboardWidget() {
    dataModel = new DashboardTaskLazyDataModel();
    setColumns(new ArrayList<>());
  }
  
  public CheckboxTreeNode[] getCategoryNodes() {
    return categoryNodes;
  }

  public void setCategoryNodes(CheckboxTreeNode[] categoryNodes) {
    this.categoryNodes = categoryNodes;
    setUserFilterCategories(CategoryUtils.getCategoryPaths(categoryNodes));
  }
  
  public CheckboxTreeNode getCategoryTree() {
    return categoryTree;
  }
  
  public void setCategoryTree(CheckboxTreeNode categoryTree) {
    this.categoryTree = categoryTree;
  }
  
  public void buildCategoryTree() {
    this.categoryTree = TaskTreeUtils.buildTaskCategoryCheckboxTreeRoot();
    CategoryUtils.disableSelectionExcept(this.categoryTree, getCategories());
  }
  
  public void buildStatisticInfos() throws ParseException {
    buildTaskByStateStatistic();
    buildTaskExpiryStatistic();
    buildTaskByCategoryStatistic();
  }
  
  private void buildTaskByStateStatistic() throws ParseException {
    taskByStateStatistic = new HashMap<>();

    Map<String, Object> params = new HashMap<>();
    params.put("criteria", generateTaskSearchCriteriaWithoutOrderByClause());

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeTaskStateStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));

    TaskStateStatistic taskStateStatistic = (TaskStateStatistic) response.get("taskStateStatistic");
    for (Entry<Integer, Long> entry : taskStateStatistic.getNumberOfTasksByState().entrySet()) {
      if (entry.getValue() == 0) {
        continue;
      }
      taskByStateStatistic.put(TaskState.valueOf(entry.getKey()).name(), entry.getValue());
    }
  }

  private void buildTaskExpiryStatistic() throws ParseException {
    numberOfTasksExpireToday = 0L;
    numberOfTasksExpireThisWeek = 0L;

    Map<String, Object> params = new HashMap<>();
    params.put("taskSearchCriteria", generateTaskSearchCriteriaWithoutOrderByClause());

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeExpiryStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    ExpiryStatistic expiryStatistic = (ExpiryStatistic) response.get("expiryStatistic");

    for (Entry<Date, Long> entry : expiryStatistic.getNumberOfTasksByExpiryTime().entrySet()) {
      if (DateUtils.isSameDay(new Date(), entry.getKey())) {
        numberOfTasksExpireToday++;
        numberOfTasksExpireThisWeek++;
        continue;
      }
      if (isDateInCurrentWeek(entry.getKey())) {
        numberOfTasksExpireThisWeek++;
      }
    }
  }

  private void buildTaskByCategoryStatistic() throws ParseException {
    taskByCategoryStatistic = new HashMap<>();

    Map<String, Object> params = new HashMap<>();
    params.put("criteria", generateTaskSearchCriteriaWithoutOrderByClause());

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeTaskCategoryStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));

    TaskCategoryStatistic taskCategoryStatistic = (TaskCategoryStatistic) response.get("taskCategoryStatistic");
    taskByCategoryStatistic.putAll(taskCategoryStatistic.getNumberOfTasksByCategory());
  }

  private TaskSearchCriteria generateTaskSearchCriteriaWithoutOrderByClause() throws ParseException {
    TaskSearchCriteria taskSearchCriteria = new TaskSearchCriteria();
    taskSearchCriteria.setFinalTaskQuery(dataModel.getCriteria().buildQueryWithoutOrderByClause());
    return taskSearchCriteria;
  }

  private boolean isDateInCurrentWeek(Date date) {
    Calendar currentCalendar = Calendar.getInstance();
    int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
    int year = currentCalendar.get(Calendar.YEAR);
    Calendar targetCalendar = Calendar.getInstance();
    targetCalendar.setTime(date);
    int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
    int targetYear = targetCalendar.get(Calendar.YEAR);
    return week == targetWeek && year == targetYear;
  }

  @JsonIgnore
  public List<ColumnModel> getFilterableColumns() {
    return getColumns().stream()
        .filter(c -> !StringUtils.equalsIgnoreCase(c.getField(), DashboardStandardTaskColumn.START.toString()) 
            && !StringUtils.equalsIgnoreCase(c.getField(), DashboardStandardTaskColumn.ID.toString())) 
        .collect(Collectors.toList());
  }
  
  public boolean getCanWorkOn() {
    return this.dataModel.getCanWorkOn();
  }
  
  public void setCanWorkOn(boolean canWorkOn) {
    this.dataModel.setCanWorkOn(canWorkOn);
  }

  public List<String> getCategories() {
    return this.dataModel.getCategories();
  }

  public void setCategories(List<String> categories) {
    this.dataModel.setCategories(categories);
  }
  
  @JsonIgnore
  public String getDisplayCategories() {
    return Optional.ofNullable(getCategories()).orElse(new ArrayList<>()).stream().collect(Collectors.joining(", "));
  }
  
  @JsonIgnore
  public List<String> getUserFilterCategories() {
    return this.dataModel.getUserFilterCategories();
  }

  public void setUserFilterCategories(List<String> categories) {
    this.dataModel.setUserFilterCategories(categories);
  }
  
  @JsonIgnore
  public String getUserFilterDisplayCategories() {
    return Optional.ofNullable(getUserFilterCategories()).orElse(new ArrayList<>()).stream().collect(Collectors.joining(", "));
  }
  
  public String getSortField() {
    return this.dataModel.getCriteria().getSortField();
  }
  
  public void setSortField(String sortField) {
    this.dataModel.getCriteria().setSortField(sortField);
  }
  
  public boolean isSortDescending() {
    return this.dataModel.getCriteria().isSortDescending();
  }
  
  public void setSortDescending(boolean sortDescending) {
    this.dataModel.getCriteria().setSortDescending(sortDescending);
  }

  public DashboardTaskLazyDataModel getDataModel() {
    return dataModel;
  }
  
  public void setDataModel(DashboardTaskLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }
  
  public List<ColumnModel> getColumns() {
    return this.dataModel.getCriteria().getColumns();
  }
  
  public void setColumns(List<ColumnModel> columns) {
    this.dataModel.getCriteria().setColumns(columns);
  }
  
  @JsonIgnore
  public int getTaskCount() {
    return getDataModel().getRowCount();
  }
  
  @JsonIgnore
  public boolean isInConfiguration() {
    return this.dataModel.getCriteria().isInConfiguration();
  }
  
  public void setInConfiguration(boolean isInConfiguration) {
    this.dataModel.getCriteria().setInConfiguration(isInConfiguration);
  }

  public Map<String, Long> getTaskByStateStatistic() {
    return taskByStateStatistic;
  }

  public void setTaskByStateStatistic(Map<String, Long> taskByStateStatistic) {
    this.taskByStateStatistic = taskByStateStatistic;
  }

  public Long getNumberOfTasksExpireThisWeek() {
    return numberOfTasksExpireThisWeek;
  }

  public void setNumberOfTasksExpireThisWeek(Long numberOfTasksExpireThisWeek) {
    this.numberOfTasksExpireThisWeek = numberOfTasksExpireThisWeek;
  }

  public Long getNumberOfTasksExpireToday() {
    return numberOfTasksExpireToday;
  }

  public void setNumberOfTasksExpireToday(Long numberOfTasksExpireToday) {
    this.numberOfTasksExpireToday = numberOfTasksExpireToday;
  }

  public Map<String, Long> getTaskByCategoryStatistic() {
    return taskByCategoryStatistic;
  }

  public void setTaskByCategoryStatistic(Map<String, Long> taskByCategoryStatistic) {
    this.taskByCategoryStatistic = taskByCategoryStatistic;
  }
}