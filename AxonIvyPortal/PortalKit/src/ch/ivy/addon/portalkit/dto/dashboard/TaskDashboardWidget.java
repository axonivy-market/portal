package ch.ivy.addon.portalkit.dto.dashboard;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.bo.ExpiryStatistic;
import ch.ivy.addon.portalkit.bo.TaskCategoryStatistic;
import ch.ivy.addon.portalkit.bo.TaskStateStatistic;
import ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.CategoryColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.CreatedDateColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.DescriptionColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.ExpiryDateColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.IdColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.NameColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.PriorityColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.ResponsibleColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.StartColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.StateColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.util.TimesUtils;
import ch.ivyteam.ivy.workflow.TaskState;

public class TaskDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 3048837559125720787L;
  private static final String CRITERIA_PARAM = "criteria";

  private int rowsPerPage = 10;
  @JsonIgnore
  private DashboardTaskLazyDataModel dataModel;
  @JsonIgnore
  private Map<TaskState, Long> taskByStateStatistic;
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

  @Override
  public void buildStatisticInfos() throws ParseException {
    buildTaskByStateStatistic();
    buildTaskExpiryStatistic();
    buildTaskByCategoryStatistic();
  }

  private void buildTaskByStateStatistic() throws ParseException {
    Map<String, Object> params = new HashMap<>();
    params.put(CRITERIA_PARAM, generateTaskSearchCriteriaWithoutOrderByClause());

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeTaskStateStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));

    Map<TaskState, Long> result = new HashMap<>();
    TaskStateStatistic taskStateStatistic = (TaskStateStatistic) response.get("taskStateStatistic");
    for (Entry<Integer, Long> entry : taskStateStatistic.getNumberOfTasksByState().entrySet()) {
      if (entry.getValue() != 0) {
        result.put(TaskState.valueOf(entry.getKey()), entry.getValue());
      }
    }
    taskByStateStatistic = result.entrySet().stream()
        .sorted(Comparator.comparingInt(s -> s.getKey().ordinal()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
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
      } else if (TimesUtils.isDateInCurrentWeek(entry.getKey())) {
        numberOfTasksExpireThisWeek++;
      }
    }
  }

  private void buildTaskByCategoryStatistic() throws ParseException {
    taskByCategoryStatistic = new HashMap<>();

    Map<String, Object> params = new HashMap<>();
    params.put(CRITERIA_PARAM, generateTaskSearchCriteriaWithoutOrderByClause());

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
  
  public List<TaskColumnModel> getColumns() {
    return this.dataModel.getCriteria().getColumns();
  }
  
  public void setColumns(List<TaskColumnModel> columns) {
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

  public Map<TaskState, Long> getTaskByStateStatistic() {
    return taskByStateStatistic;
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

  @Override
  @JsonIgnore
  public void onApplyUserFilters() throws ParseException {
    super.onApplyUserFilters();
    this.userDefinedFiltersCount = countDefinedUserFilter(this);
  }

  @Override
  public void buildPredefinedFilterData() throws ParseException {
    setHasPredefinedFilter(hasPredefinedFilter(this));
  }

  @JsonIgnore
  public static TaskDashboardWidget buildDefaultWidget(String id, String name) {
    TaskDashboardWidget widget = new TaskDashboardWidget();
    widget.setId(id);
    widget.setName(name);

    WidgetLayout layout = new WidgetLayout();
    layout.setWidth(10);
    layout.setHeight(9);
    widget.setLayout(layout);

    widget.setAutoPosition(true);
    widget.setSortField(TaskSortField.ID.toString());
    widget.setSortDescending(true);
    widget.setColumns(initStandardColumns());
    return buildColumns(widget);
  }

  @JsonIgnore
  public static TaskDashboardWidget buildColumns(TaskDashboardWidget widget) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    List<TaskColumnModel> columns = widget.getColumns();
    for (int i = 0; i < columns.size(); i++) {
      TaskColumnModel column = columns.get(i);
      String field = column.getField();
      if (DashboardStandardTaskColumn.START.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, StartColumnModel.class);
      } else if (DashboardStandardTaskColumn.PRIORITY.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, PriorityColumnModel.class);
      } else if (DashboardStandardTaskColumn.ID.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, IdColumnModel.class);
      } else if (DashboardStandardTaskColumn.NAME.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, NameColumnModel.class);
      } else if (DashboardStandardTaskColumn.DESCRIPTION.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, DescriptionColumnModel.class);
      } else if (DashboardStandardTaskColumn.RESPONSIBLE.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, ResponsibleColumnModel.class);
      } else if (DashboardStandardTaskColumn.STATE.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, StateColumnModel.class);
      } else if (DashboardStandardTaskColumn.CREATED.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, CreatedDateColumnModel.class);
      } else if (DashboardStandardTaskColumn.EXPIRY.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, ExpiryDateColumnModel.class);
      } else if (DashboardStandardTaskColumn.CATEGORY.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, CategoryColumnModel.class);
      }
      column.initDefaultValue();
      columns.set(i, column);
    }
    return widget;
  }
  
  @JsonIgnore
  public static List<TaskColumnModel> initStandardColumns() {
    List<TaskColumnModel> columnModels = new ArrayList<>();
    for (DashboardStandardTaskColumn col : DashboardStandardTaskColumn.values()) {
      TaskColumnModel columnModel = new TaskColumnModel();
      columnModel.setField(col.getField());
      columnModels.add(columnModel);
    }
    return columnModels;
  }

  @JsonIgnore
  public static boolean hasPredefinedFilter(TaskDashboardWidget widget) throws ParseException {
    List<ColumnModel> filterableColumns = widget.getFilterableColumns();
    if (CollectionUtils.isEmpty(filterableColumns)) {
      return false;
    }
    for (ColumnModel col : filterableColumns) {
      if (col instanceof PriorityColumnModel && !CollectionUtils.isEmpty(((PriorityColumnModel) col).getPriorities())) {
        return true;
      }
      else if (col instanceof StateColumnModel && !CollectionUtils.isEmpty(((StateColumnModel) col).getStates())) {
        return true;
      }
      else if (col instanceof ResponsibleColumnModel
          && !CollectionUtils.isEmpty(((ResponsibleColumnModel) col).getResponsibles())) {
        return true;
      }
      else if ((col.getFormat() == DashboardColumnFormat.TEXT || col.getFormat() == DashboardColumnFormat.STRING)
          && !(CollectionUtils.isEmpty(col.getFilterList()) && StringUtils.isBlank(col.getFilter()))) {
        return true;
      }
      else if (col.getFormat() == DashboardColumnFormat.NUMBER
          && !(StringUtils.isBlank(col.getFilterFrom()) && StringUtils.isBlank(col.getFilterTo()))) {
        return true;
      }
      else if (col.getFormat() == DashboardColumnFormat.TIMESTAMP
          && !(col.getDateFilterFrom() == null && col.getDateFilterTo() == null)) {
        return true;
      }
      else if (DashboardStandardTaskColumn.CATEGORY.getField().equalsIgnoreCase(col.getField())
          && CollectionUtils.isNotEmpty(col.getFilterList())) {
        return true;
      }
    }
    return false;
  }

  @JsonIgnore
  public static Optional<String> countDefinedUserFilter(TaskDashboardWidget widget) throws ParseException {
    int numberOfFilters = 0;
    List<ColumnModel> filterableColumns = widget.getFilterableColumns();
    if (CollectionUtils.isEmpty(filterableColumns)) {
      return Optional.empty();
    }
    for (ColumnModel col : filterableColumns) {
      if (StringUtils.isNotEmpty(col.getUserFilter()) || StringUtils.isNotEmpty(col.getUserFilterFrom())
          || col.getUserDateFilterFrom() != null || CollectionUtils.isNotEmpty(col.getUserFilterList())) {
        numberOfFilters++;
      }
      if (StringUtils.isNotEmpty(col.getUserFilterTo()) || col.getUserDateFilterTo() != null) {
        numberOfFilters++;
      }
      if (numberOfFilters > MAX_NOTI_FILTERS) {
        break;
      }
    }

    if (numberOfFilters > MAX_NOTI_FILTERS) {
      return Optional.of(String.format(MAX_NOTI_PATTERN, MAX_NOTI_FILTERS));
    }
    if (numberOfFilters == 0) {
      return Optional.empty();
    }

    return Optional.of(String.valueOf(numberOfFilters));
  }

  @Override
  @JsonIgnore
  public void resetWidgetFilters() {
    for (ColumnModel column : this.getColumns()) {
      column.setUserFilter(StringUtils.EMPTY);
      column.setUserFilterList(new ArrayList<>());
      column.setUserFilterFrom(StringUtils.EMPTY);
      column.setUserFilterTo(StringUtils.EMPTY);
      column.setUserDateFilterFrom(null);
      column.setUserDateFilterTo(null);
    }
  }

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.TASK;
  }

  public int getRowsPerPage() {
    return rowsPerPage;
  }

  public void setRowsPerPage(int rowsPerPage) {
    this.rowsPerPage = rowsPerPage;
  }
}