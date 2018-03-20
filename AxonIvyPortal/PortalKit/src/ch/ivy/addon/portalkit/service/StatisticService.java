package ch.ivy.addon.portalkit.service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.component.chart.Chart;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.PieChartModel;

import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection;
import ch.ivy.addon.portalkit.statistics.Colors;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.statistics.StatisticChartConstants;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivy.ws.addon.CaseStateStatistic;
import ch.ivy.ws.addon.ElapsedTimeStatistic;
import ch.ivy.ws.addon.ExpiryStatistic;
import ch.ivy.ws.addon.PriorityStatistic;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class StatisticService extends BusinessDataService<StatisticChart> {

  @Override
  public Class<StatisticChart> getType() {
    return StatisticChart.class;
  }

  @Override
  public BusinessDataInfo<StatisticChart> save(StatisticChart statisticChart) {
    statisticChart.setBarChartModel(null);
    statisticChart.setDonutChartModel(null);
    statisticChart.setPieChartModel(null);
    return super.save(statisticChart);

  }

  /**
   * Find all statistic charts by user id
   * 
   * @param userId user id
   * @return all statistic charts created by user id
   */
  public List<StatisticChart> findStatisticChartsByUserId(long userId) {
    List<StatisticChart> result = new ArrayList<>();
    try {
      Filter<StatisticChart> statisticChartQuery = repo().search(getType()).numberField(StatisticChartConstants.USER_ID).isEqualTo(userId);
      result = statisticChartQuery.orderBy().textField(StatisticChartConstants.NAME).ascending().execute().getAll();
      result = result.stream().sorted(Comparator.comparing(StatisticChart::getPosition)).collect(Collectors.toList());
      return result;
    } catch (Exception e) {
      Ivy.log().error(e);
      return result;
    }
  }

  /**
   * Count all statistic charts by user id
   * 
   * @param userId user id
   * @return Number of statistic charts created by user id
   */
  public long countStatisticChartsByUserId(long userId) {
    try {
      Filter<StatisticChart> statisticChartQuery = repo().search(getType()).numberField(StatisticChartConstants.USER_ID).isEqualTo(userId);
      return statisticChartQuery.orderBy().textField(StatisticChartConstants.NAME).ascending().execute().count();
    } catch (Exception e) {
      Ivy.log().error(e);
      return -1;
    }
  }

  /**
   * Generate task query for statistic
   * 
   * @param filter statistic filter
   * @return generated task query
   */
  public TaskQuery generateTaskQuery(StatisticFilter filter) {
    TaskQuery taskQuery = TaskQuery.create();

    // Filter by created date
    generateTaskQueryForStartTimestamp(filter, taskQuery);
    
    // Filter by roles
    generateTaskQueryForRoles(filter, taskQuery);

    generateTaskQueryForTaskPriority(filter, taskQuery);

    taskQuery.where().and().cases(generateCaseQuery(filter, false));
    return taskQuery;
  }

  private void generateTaskQueryForRoles(StatisticFilter filter, TaskQuery taskQuery) {
    List<String> selectedRoles = Optional.ofNullable(filter.getSelectedRoles()).orElse(new ArrayList<>());
    if (!selectedRoles.isEmpty()) {
      TaskQuery subTaskQueryForRoles = TaskQuery.create();
      IFilterQuery subTaskFilterForRoles = subTaskQueryForRoles.where();

      selectedRoles.forEach(role -> subTaskFilterForRoles.or().activatorName().isEqual(role));
      taskQuery.where().and(subTaskQueryForRoles);
    }
  }

  private void generateTaskQueryForStartTimestamp(StatisticFilter filter, TaskQuery taskQuery) {
    TaskQuery subTaskQueryForCreatedDate = TaskQuery.create();
    IFilterQuery subTaskFilterForCreatedDate = subTaskQueryForCreatedDate.where();
    if(filter.getTimePeriodSelection() == null || filter.getTimePeriodSelection() == StatisticTimePeriodSelection.CUSTOM){
      Date createdDateFrom = filter.getCreatedDateFrom();
      Date createdDateTo = filter.getCreatedDateTo();
      if (createdDateFrom != null || createdDateTo != null) {
        if (createdDateFrom != null) {
          subTaskFilterForCreatedDate.startTimestamp().isGreaterOrEqualThan(createdDateFrom);
        }
        if (createdDateTo != null) {
          subTaskFilterForCreatedDate.startTimestamp().isLowerOrEqualThan(createdDateTo);
        }
        taskQuery.where().and(subTaskQueryForCreatedDate);
      }
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_WEEK){
      subTaskFilterForCreatedDate.startTimestamp().isGreaterOrEqualThan(Dates.getMondayOfLastWeek());
      subTaskFilterForCreatedDate.startTimestamp().isLowerOrEqualThan(Dates.getSundayOfLastWeek());
      taskQuery.where().and(subTaskQueryForCreatedDate);
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_MONTH) {
      subTaskFilterForCreatedDate.startTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLastMonth());
      subTaskFilterForCreatedDate.startTimestamp().isLowerOrEqualThan(Dates.getLastDayOfLastMonth());
      taskQuery.where().and(subTaskQueryForCreatedDate);
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_6_MONTH){
      subTaskFilterForCreatedDate.startTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLast6Month());
      taskQuery.where().and(subTaskQueryForCreatedDate);
    }
  }

  /**
   * Generate task query for expiry statistic
   * 
   * @param filter statistic filter
   * @return generated task query
   */
  public TaskQuery generateTaskQueryForExpiry(StatisticFilter filter) {
    TaskQuery taskQuery = generateTaskQuery(filter);
    taskQuery = filterOnlyTasksExpireInThisYear(taskQuery);
    return taskQuery;
  }

  /**
   * Generate case query for statistic
   * 
   * @param filter statistic filter
   * @param forElapsedStatistic for elapsed statistic
   * @return generated case query
   */
  public CaseQuery generateCaseQuery(StatisticFilter filter, boolean forElapsedStatistic) {
    CaseQuery caseQuery = CaseQuery.create();

    // Filter by created date
    generateCaseQueryForStartTimestamp(filter, caseQuery);

    // Filter by case state
    generateCaseQueryForCaseState(filter, forElapsedStatistic, caseQuery);

    // Filter by case category
    generateCaseQueryForCaseCategory(filter, caseQuery);

    return caseQuery;
  }

  private void generateCaseQueryForCaseCategory(StatisticFilter filter, CaseQuery caseQuery) {
    List<String> selectedCaseCategories =
        Optional.ofNullable(filter.getSelectedCaseCategories()).orElse(new ArrayList<>());
    CaseQuery subCaseQueryForSelectedCaseCategories = CaseQuery.create();
    ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery subCaseFilterForSelectedCaseCategories =
        subCaseQueryForSelectedCaseCategories.where();
    if (selectedCaseCategories.isEmpty()) {
      List<String> caseCategories = Optional.ofNullable(filter.getCaseCategories()).orElse(new ArrayList<>());
      caseCategories.forEach(category -> {
        if (StringUtils.equals(category, StatisticChartConstants.NO_CATEGORY_CMS)) {
          subCaseFilterForSelectedCaseCategories.and().category().isNotNull();
        } else {
          subCaseFilterForSelectedCaseCategories.and().category().isNotEqual(category);
        }
      });
    } else {
      selectedCaseCategories.forEach(category -> {
        if (StringUtils.equals(category, StatisticChartConstants.NO_CATEGORY_CMS)) {
          subCaseFilterForSelectedCaseCategories.or().category().isNull();
        } else {
          subCaseFilterForSelectedCaseCategories.or().category().isEqual(category);
        }
      });
    }
    caseQuery.where().and(subCaseQueryForSelectedCaseCategories);
  }

  private void generateCaseQueryForCaseState(StatisticFilter filter, boolean forElapsedStatistic,
          CaseQuery caseQuery) {
    List<CaseState> selectedCaseStates = Optional.ofNullable(filter.getSelectedCaseStates()).orElse(new ArrayList<>());
    CaseQuery subCaseQueryForSelectedCaseStates = CaseQuery.create();
    ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery subCaseFilterForSelectedCaseStates =
        subCaseQueryForSelectedCaseStates.where();

    if (selectedCaseStates.isEmpty()) {
      List<CaseState> caseStates = Arrays.asList(CaseState.values());
      caseStates.forEach(caseState -> subCaseFilterForSelectedCaseStates.and().state().isNotEqual(caseState));
    } else if (forElapsedStatistic) {
      subCaseFilterForSelectedCaseStates.or().state().isEqual(CaseState.DONE);
    } else {
      selectedCaseStates.forEach(caseState -> subCaseFilterForSelectedCaseStates.or().state().isEqual(caseState));
    }
    caseQuery.where().and(subCaseQueryForSelectedCaseStates);
  }

  private void generateCaseQueryForStartTimestamp(StatisticFilter filter, CaseQuery caseQuery) {
    CaseQuery subCaseQueryForCreatedDate = CaseQuery.create();
    ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery subCaseFilterForCreatedDate =
        subCaseQueryForCreatedDate.where();
    if(filter.getTimePeriodSelection() == null || filter.getTimePeriodSelection() == StatisticTimePeriodSelection.CUSTOM){
      Date createdDateFrom = filter.getCreatedDateFrom();
      Date createdDateTo = filter.getCreatedDateTo();
      if (createdDateFrom != null || createdDateTo != null) {
      
        if (createdDateFrom != null) {
          subCaseFilterForCreatedDate.startTimestamp().isGreaterOrEqualThan(createdDateFrom);
        }
        if (createdDateTo != null) {
          subCaseFilterForCreatedDate.startTimestamp().isLowerOrEqualThan(createdDateTo);
        }
        caseQuery.where().and(subCaseQueryForCreatedDate);
      }
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_WEEK){
      subCaseFilterForCreatedDate.startTimestamp().isGreaterOrEqualThan(Dates.getMondayOfLastWeek());
      subCaseFilterForCreatedDate.startTimestamp().isLowerOrEqualThan(Dates.getSundayOfLastWeek());
      caseQuery.where().and(subCaseQueryForCreatedDate);
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_MONTH) {
      subCaseFilterForCreatedDate.startTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLastMonth());
      subCaseFilterForCreatedDate.startTimestamp().isLowerOrEqualThan(Dates.getLastDayOfLastMonth());
      caseQuery.where().and(subCaseQueryForCreatedDate);
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_6_MONTH) {
      subCaseFilterForCreatedDate.startTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLastMonth());
      caseQuery.where().and(subCaseQueryForCreatedDate);
    }
  }
  
  /**
   * generate case query for case which having finished task
   * @param filter
   * @return case query for case which having finished task
   */
  public CaseQuery generateCaseQueryForCaseHaveFinishedTask(StatisticFilter filter) {
      CaseQuery caseQuery = generateCaseQuery(filter, false);
      caseQuery.where().and().tasks(generateTaskQueryForFinishedTask(filter));
      return caseQuery;
  }
  
  /**
   * generate case query by finished time
   * @param filter
   * @return case query by finished time
   */
  public CaseQuery generateCaseQueryByFinishedTime(StatisticFilter filter) {
    CaseQuery caseQuery = CaseQuery.create();
    // Filter by finished date
    generateCaseQueryForEndTimestamp(filter, caseQuery);
    
    // Filter by case state
    List<CaseState> selectedCaseStates = Optional.ofNullable(filter.getSelectedCaseStates()).orElse(new ArrayList<>());
    CaseQuery subCaseQueryForSelectedCaseStates = CaseQuery.create();
    ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery subCaseFilterForSelectedCaseStates =
        subCaseQueryForSelectedCaseStates.where();

    if (selectedCaseStates.contains(CaseState.DONE)) {
      subCaseFilterForSelectedCaseStates.and().state().isEqual(CaseState.DONE);
    }
    else {
      //if user don't choose Done state, generate query to get no information
      subCaseFilterForSelectedCaseStates.and().state().isEqual(CaseState.DONE);
      subCaseFilterForSelectedCaseStates.and().state().isEqual(CaseState.RUNNING);
    }
    caseQuery.where().and(subCaseQueryForSelectedCaseStates);
    
    generateCaseQueryForCaseCategory(filter, caseQuery);
    
    return caseQuery;
  }

  private void generateCaseQueryForEndTimestamp(StatisticFilter filter, CaseQuery caseQuery) {
    CaseQuery subCaseQueryForCreatedDate = CaseQuery.create();
    ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery subCaseFilterForCreatedDate = subCaseQueryForCreatedDate.where();
    if(filter.getTimePeriodSelection() == null || filter.getTimePeriodSelection() == StatisticTimePeriodSelection.CUSTOM){
      Date startTimePeriod = filter.getCreatedDateFrom();
      Date endTimePeriod = filter.getCreatedDateTo();
      if (startTimePeriod != null || endTimePeriod != null) {
        
        if (startTimePeriod != null) {
          subCaseFilterForCreatedDate.endTimestamp().isGreaterOrEqualThan(startTimePeriod);
        }
        if (endTimePeriod != null) {
          subCaseFilterForCreatedDate.endTimestamp().isLowerOrEqualThan(endTimePeriod);
        }
        caseQuery.where().and(subCaseQueryForCreatedDate);
      }
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_WEEK){
      subCaseFilterForCreatedDate.endTimestamp().isGreaterOrEqualThan(Dates.getMondayOfLastWeek());
      subCaseFilterForCreatedDate.endTimestamp().isLowerOrEqualThan(Dates.getSundayOfLastWeek());
      caseQuery.where().and(subCaseQueryForCreatedDate);
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_MONTH) {
      subCaseFilterForCreatedDate.endTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLastMonth());
      subCaseFilterForCreatedDate.endTimestamp().isLowerOrEqualThan(Dates.getLastDayOfLastMonth());
      caseQuery.where().and(subCaseQueryForCreatedDate);
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_6_MONTH) {
      subCaseFilterForCreatedDate.endTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLastMonth());
      caseQuery.where().and(subCaseQueryForCreatedDate);
    }
    caseQuery.where().and().endTimestamp().isNotNull();
  }
  
  private TaskQuery generateTaskQueryForFinishedTask(StatisticFilter filter) {
    TaskQuery taskQuery = TaskQuery.create();
    
    // Filter by end date
    generateTaskQueryForEndTimestamp(filter, taskQuery);
    
    //Filter by DONE state
    taskQuery.where().and().state().isEqual(TaskState.DONE);
    
    //Filter by role which finished task
    generateTaskQueryForRoles(filter, taskQuery);

    // Filter by task priority
    generateTaskQueryForTaskPriority(filter, taskQuery);
    
    return taskQuery;
  }

  private void generateTaskQueryForEndTimestamp(StatisticFilter filter, TaskQuery taskQuery) {
    TaskQuery subTaskQueryForCreatedDate = TaskQuery.create();
    IFilterQuery subTaskFilterForCreatedDate = subTaskQueryForCreatedDate.where();
    if(filter.getTimePeriodSelection() == null || filter.getTimePeriodSelection() == StatisticTimePeriodSelection.CUSTOM){
      Date createdDateFrom = filter.getCreatedDateFrom();
      Date createdDateTo = filter.getCreatedDateTo();
      if (createdDateFrom != null || createdDateTo != null) {
        if (createdDateFrom != null) {
          subTaskFilterForCreatedDate.endTimestamp().isGreaterOrEqualThan(createdDateFrom);
        }
        if (createdDateTo != null) {
          subTaskFilterForCreatedDate.endTimestamp().isLowerOrEqualThan(createdDateTo);
        }
        taskQuery.where().and(subTaskQueryForCreatedDate);
      }
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_WEEK){
      subTaskFilterForCreatedDate.endTimestamp().isGreaterOrEqualThan(Dates.getMondayOfLastWeek());
      subTaskFilterForCreatedDate.endTimestamp().isLowerOrEqualThan(Dates.getSundayOfLastWeek());
      taskQuery.where().and(subTaskQueryForCreatedDate);
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_MONTH) {
      subTaskFilterForCreatedDate.endTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLastMonth());
      subTaskFilterForCreatedDate.endTimestamp().isLowerOrEqualThan(Dates.getLastDayOfLastMonth());
      taskQuery.where().and(subTaskQueryForCreatedDate);
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_6_MONTH){
      subTaskFilterForCreatedDate.endTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLast6Month());
      taskQuery.where().and(subTaskQueryForCreatedDate);
    }
    taskQuery.where().and().endTimestamp().isNotNull();
  }

  private void generateTaskQueryForTaskPriority(StatisticFilter filter, TaskQuery taskQuery) {
    List<WorkflowPriority> selectedPriorities =
        Optional.ofNullable(filter.getSelectedTaskPriorities()).orElse(new ArrayList<>());
    if (!selectedPriorities.isEmpty()) {
      TaskQuery subTaskQueryForPriority = TaskQuery.create();
      IFilterQuery subTaskFilterForPriority = subTaskQueryForPriority.where();

      selectedPriorities.forEach(priority -> subTaskFilterForPriority.or().priority().isEqual(priority));
      taskQuery.where().and(subTaskQueryForPriority);
    }
  }
  
  private TaskQuery filterOnlyTasksExpireInThisYear(TaskQuery taskQuery) {
    TaskQuery result = TaskQuery.fromJson(taskQuery.asJson());
    Date firsDateOfThisYear = truncateMinutesPart(getFirstDateOfThisYear());
    Date firsDateOfNextYear = truncateMinutesPart(DateUtils.addYears(firsDateOfThisYear, 1));

    TaskQuery taskQueryForExpiryDate = TaskQuery.create();
    IFilterQuery filterQueryForExpiryDate = taskQueryForExpiryDate.where();
    filterQueryForExpiryDate.and().expiryTimestamp().isGreaterOrEqualThan(firsDateOfThisYear).and().expiryTimestamp()
        .isLowerThan(firsDateOfNextYear).and().expiryTimestamp().isNotNull();

    result.where().and(taskQueryForExpiryDate);
    return result;
  }

  /**
   * Call web service to get Task by Priority statistic data
   * 
   * @param jsonQuery
   * @return Task by Priority statistic data
   */
  public PriorityStatistic getPriorityStatisticData(String jsonQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put(StatisticChartConstants.JSON_QUERY, jsonQuery);

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzePriorityStatistic(String)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (PriorityStatistic) response.get(StatisticChartConstants.RESULT);
  }

  /**
   * Call web service to get Task by Expiry statistic data
   * 
   * @param jsonQuery
   * @return Task by Expiry statistic data
   */
  @SuppressWarnings("unchecked")
  public List<ExpiryStatistic> getExpiryStatisticData(String jsonQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put(StatisticChartConstants.JSON_QUERY, jsonQuery);

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeExpiryStatistic(String)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (List<ExpiryStatistic>) response.get(StatisticChartConstants.RESULT);
  }

  /**
   * Call web service to get Case by State statistic data
   * 
   * @param jsonQuery
   * @return Case by State statistic data
   */
  public CaseStateStatistic getCaseStateStatisticData(String jsonQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put(StatisticChartConstants.JSON_QUERY, jsonQuery);

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeCaseStateStatistic(String)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (CaseStateStatistic) response.get(StatisticChartConstants.RESULT);
  }
  
  /**
   * Call web service to get Elapsed time by Case Category data
   * 
   * @param jsonQuery
   * @return Elapsed time by Case Category data
   */
  @SuppressWarnings("unchecked")
  public List<ElapsedTimeStatistic> getElapsedTimeStatisticData(String jsonQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put(StatisticChartConstants.JSON_QUERY, jsonQuery);

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeElapsedTimeStatistic(String)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (List<ElapsedTimeStatistic>) response.get(StatisticChartConstants.RESULT);
  }

  /**
   * Call web service to get Elapsed time of related tasks by Case Category
   * 
   * @param jsonQuery
   * @return Elapsed time by Case Category data
   */
  @SuppressWarnings("unchecked")
  public List<ElapsedTimeStatistic> getElapsedTimeOfTasksStatisticData(String jsonQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put(StatisticChartConstants.JSON_QUERY, jsonQuery);

    Map<String, Object> response = IvyAdapterService.startSubProcess("analyzeElapsedTimeOfTasks(String)", params,
        Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (List<ElapsedTimeStatistic>) response.get(StatisticChartConstants.RESULT);
  }

  /**
   * generate data for "Task by Priority" chart
   * 
   * @param priorityStatistic statistic data
   * @return generated data
   */
  public Map<String, Number> generateDataForTaskByPriorityChart(PriorityStatistic priorityStatistic) {
    Map<String, Number> chartData = new LinkedHashMap<String, Number>();

    if (priorityStatistic.getException() > 0) {
      chartData.put(Ivy.cms().co(StatisticChartConstants.EXCEPTION_PRIORITY_KEY), priorityStatistic.getException());
    }

    if (priorityStatistic.getHigh() > 0) {
      chartData.put(Ivy.cms().co(StatisticChartConstants.HIGH_PRIORITY_KEY), priorityStatistic.getHigh());
    }

    if (priorityStatistic.getNormal() > 0) {
      chartData.put(Ivy.cms().co(StatisticChartConstants.NORMAL_PRIORITY_KEY), priorityStatistic.getNormal());
    }

    if (priorityStatistic.getLow() > 0) {
      chartData.put(Ivy.cms().co(StatisticChartConstants.LOW_PRIORITY_KEY), priorityStatistic.getLow());
    }

    return chartData;
  }

  /**
   * generate data for "Task by Expiry" overview chart
   * 
   * @param expiryStatistic statistic data
   * @param selectedValue selected value
   * @param previousSelectedMonth previous selected month
   * @param previousSelectedWeek previous selected week
   * @return generated data
   */
  public Map<Object, Number> generateDataForTaskByExpiryOverviewChart(List<ExpiryStatistic> expiryStatistic,
      String selectedValue, String previousSelectedMonth, String previousSelectedWeek) {

    // Convert result
    Map<Date, Long> statisticResultMap = new HashMap<Date, Long>();
    Gson gsonConverter = new Gson();
    Type type = new TypeToken<Map<String, String>>() {}.getType();

    expiryStatistic.forEach(statistic -> {
      if (StringUtils.isNotBlank(statistic.getResult())) {
        Map<String, String> resultAsString = new HashMap<String, String>();
        resultAsString.putAll(gsonConverter.fromJson(statistic.getResult(), type));
        resultAsString.entrySet().forEach(result -> {
          try {
            Date date = DateUtils.parseDate(result.getKey(), "yyyy-MM-dd HH:mm:ss.SSS");
            statisticResultMap.put(date, Long.parseLong(result.getValue()));
          } catch (Exception e) {
            Ivy.log().error(e);
          }
        });
      }
    });

    Map<Object, Number> chartData = new HashMap<Object, Number>();
    if (selectThisYear(selectedValue)) {
      chartData = generateExpiryModelForDrilldownLevelYear(statisticResultMap);
    } else if (selectMonthOfYear(selectedValue)) {
      chartData = generateExpiryModelForDrilldownLevelMonth(statisticResultMap, selectedValue);
    } else if (selectWeekOfMonth(selectedValue)) {
      chartData = generateExpiryModelForDrilldownLevelWeek(statisticResultMap, selectedValue, previousSelectedMonth);
    } else if (selectDayOfWeek(selectedValue)) {
      chartData = generateExpiryModelForDrilldownLevelDay(statisticResultMap, selectedValue, previousSelectedWeek, previousSelectedMonth);
    } else {
      chartData = generateDefaultExpiryModel(statisticResultMap);
    }

    return chartData;
  }

  private Map<Object, Number> generateExpiryModelForDrilldownLevelDay(Map<Date, Long> statisticResultMap, String selectedDay, String previousSelectedWeek, String previousSelectedMonth) {
    Map<Object, Number> chartData = new LinkedHashMap<Object, Number>();
    
    Long taskExpireBefore8 = new Long(0L);
    Long taskExpireIn8 = new Long(0L);
    Long taskExpireIn9 = new Long(0L);
    Long taskExpireIn10 = new Long(0L);
    Long taskExpireIn11 = new Long(0L);
    Long taskExpireIn12 = new Long(0L);
    Long taskExpireIn13 = new Long(0L);
    Long taskExpireIn14 = new Long(0L);
    Long taskExpireIn15 = new Long(0L);
    Long taskExpireIn16 = new Long(0L);
    Long taskExpireIn17 = new Long(0L);
    Long taskExpireAfter18 = new Long(0L);
    
    for (Entry<Date, Long> result : statisticResultMap.entrySet()) {
      Date resultDate = result.getKey();
      Calendar cal = Calendar.getInstance();
      cal.setTime(resultDate);
      
      if (isSameDay(resultDate, selectedDay, previousSelectedWeek, previousSelectedMonth)) {
        if(cal.get(Calendar.HOUR_OF_DAY) < 8){
          taskExpireBefore8 += result.getValue();
        } else if(cal.get(Calendar.HOUR_OF_DAY) == 8) {
          taskExpireIn8 += result.getValue();
        } else if(cal.get(Calendar.HOUR_OF_DAY) == 9) {
          taskExpireIn9 += result.getValue();
        } else if(cal.get(Calendar.HOUR_OF_DAY) == 10) {
          taskExpireIn10 += result.getValue();
        } else if(cal.get(Calendar.HOUR_OF_DAY) == 11) {
          taskExpireIn11 += result.getValue();
        } else if(cal.get(Calendar.HOUR_OF_DAY) == 12) {
          taskExpireIn12 += result.getValue();
        } else if(cal.get(Calendar.HOUR_OF_DAY) == 13){
          taskExpireIn13 += result.getValue();
        } else if(cal.get(Calendar.HOUR_OF_DAY) == 14){
          taskExpireIn14 += result.getValue();
        } else if(cal.get(Calendar.HOUR_OF_DAY) == 15){
          taskExpireIn15 += result.getValue();
        } else if(cal.get(Calendar.HOUR_OF_DAY) == 16){
          taskExpireIn16 += result.getValue();
        } else if(cal.get(Calendar.HOUR_OF_DAY) == 17){
          taskExpireIn17 += result.getValue();
        } else if(cal.get(Calendar.HOUR_OF_DAY) >= 18){
          taskExpireAfter18 += result.getValue();
        }
      }
      
    }

    chartData.put(StatisticChartConstants.BEFORE_8, taskExpireBefore8);
    chartData.put(StatisticChartConstants.IN_8, taskExpireIn8);
    chartData.put(StatisticChartConstants.IN_9, taskExpireIn9);
    chartData.put(StatisticChartConstants.IN_10, taskExpireIn10);
    chartData.put(StatisticChartConstants.IN_11, taskExpireIn11);
    chartData.put(StatisticChartConstants.IN_12, taskExpireIn12);
    chartData.put(StatisticChartConstants.IN_13, taskExpireIn13);
    chartData.put(StatisticChartConstants.IN_14, taskExpireIn14);
    chartData.put(StatisticChartConstants.IN_15, taskExpireIn15);
    chartData.put(StatisticChartConstants.IN_16, taskExpireIn16);
    chartData.put(StatisticChartConstants.IN_17, taskExpireIn17);
    chartData.put(StatisticChartConstants.AFTER_18, taskExpireAfter18);
    
    return chartData;
  }

  private boolean isSameDay(Date resultDate, String selectedDay, String previousSelectedWeek, String previousSelectedMonth) {
    int shiftDays = 0;
    if (StringUtils.containsIgnoreCase(selectedDay, Ivy.cms().co(StatisticChartConstants.TODAY_EXPIRY_KEY))) {
      return DateUtils.isSameDay(resultDate, new Date());
    } else {
      shiftDays = getShiftDaysFromDayOfWeek(selectedDay);
    }

    Date compareDate = DateUtils.addDays(getFirstDateOfWeek(previousSelectedWeek, previousSelectedMonth), shiftDays);
    if (DateUtils.isSameDay(resultDate, compareDate)) {
      return true;
    }
    return false;
  }

  private int getShiftDaysFromDayOfWeek(String dayOfWeek) {
    int shiftDays = 0;
    if (dayOfWeek.equals(StatisticChartConstants.MONDAY_CMS)) {
      shiftDays = 0;
    } else if (dayOfWeek.equals(StatisticChartConstants.TUESDAY_CMS)) {
      shiftDays = 1;
    } else if (dayOfWeek.equals(StatisticChartConstants.WEDNESDAY_CMS)) {
      shiftDays = 2;
    } else if (dayOfWeek.equals(StatisticChartConstants.THURSDAY_CMS)) {
      shiftDays = 3;
    } else if (dayOfWeek.equals(StatisticChartConstants.FRIDAY_CMS)) {
      shiftDays = 4;
    } else if (dayOfWeek.equals(StatisticChartConstants.SATURDAY_CMS)) {
      shiftDays = 5;
    } else if (dayOfWeek.equals(StatisticChartConstants.SUNDAY_CMS)) {
      shiftDays = 6;
    }
    return shiftDays;
  }

  private Map<Object, Number> generateExpiryModelForDrilldownLevelWeek(Map<Date, Long> statisticResultMap,
      String selectedWeek, String previousSelectedMonth) {
    Map<Object, Number> chartData = new LinkedHashMap<Object, Number>();

    Date firstDateOfSelectedWeek = truncateMinutesPart(getFirstDateOfWeek(selectedWeek, previousSelectedMonth));
    Date firstDateOfNextWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfSelectedWeek, 1));
    
    if (!StringUtils.isEmpty(previousSelectedMonth)) {
      Date firstDateOfMonth = truncateMinutesPart(getFirstDateOfMonth(previousSelectedMonth));
      if (firstDateOfMonth.compareTo(firstDateOfSelectedWeek) > 0) {
        firstDateOfSelectedWeek = firstDateOfMonth;
      }
      Date firstDayOfNextMonth = DateUtils.addMonths(firstDateOfMonth, 1);
      if (firstDateOfNextWeek.compareTo(firstDayOfNextMonth) > 0) {
        firstDateOfNextWeek = firstDayOfNextMonth;
      }
    }

    Long taskExpireOnMonday = new Long(0L);
    Long taskExpireOnTuesday = new Long(0L);
    Long taskExpireOnWednesday = new Long(0L);
    Long taskExpireOnThursday = new Long(0L);
    Long taskExpireOnFriday = new Long(0L);
    Long taskExpireOnSaturday = new Long(0L);
    Long taskExpireOnSunday = new Long(0L);

    for (Entry<Date, Long> result : statisticResultMap.entrySet()) {
      Date resultDate = truncateMinutesPart(result.getKey());
      Calendar cal = Calendar.getInstance();
      cal.setTime(resultDate);

      if (firstDateOfSelectedWeek.compareTo(resultDate) <= 0 && firstDateOfNextWeek.compareTo(resultDate) > 0) {
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
          taskExpireOnMonday += result.getValue();
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
          taskExpireOnTuesday += result.getValue();
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
          taskExpireOnWednesday += result.getValue();
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
          taskExpireOnThursday += result.getValue();
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
          taskExpireOnFriday += result.getValue();
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
          taskExpireOnSaturday += result.getValue();
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
          taskExpireOnSunday += result.getValue();
        }
      }
    }

    chartData.put(StatisticChartConstants.MONDAY_CMS, taskExpireOnMonday);
    chartData.put(StatisticChartConstants.TUESDAY_CMS, taskExpireOnTuesday);
    chartData.put(StatisticChartConstants.WEDNESDAY_CMS, taskExpireOnWednesday);
    chartData.put(StatisticChartConstants.THURSDAY_CMS, taskExpireOnThursday);
    chartData.put(StatisticChartConstants.FRIDAY_CMS, taskExpireOnFriday);
    chartData.put(StatisticChartConstants.SATURDAY_CMS, taskExpireOnSaturday);
    chartData.put(StatisticChartConstants.SUNDAY_CMS, taskExpireOnSunday);

    return chartData;
  }

  private Date getFirstDateOfWeek(String selectedWeek, String selectedMonth) {

    Date firstDateOfWeek = new Date();
    if(StringUtils.containsIgnoreCase(selectedWeek, Ivy.cms().co(StatisticChartConstants.THIS_WEEK_EXPIRY_KEY))){
      firstDateOfWeek = getFirstDateOfWeekContainsDate(new Date());
    } else {
      Date firstDateOfMonth = getFirstDateOfMonth(selectedMonth);
      Date firstDateOfFirstWeek = getFirstDateOfWeekContainsDate(firstDateOfMonth);
      if (StringUtils.containsIgnoreCase(selectedWeek, StatisticChartConstants.FIRSTWEEK_CMS)){
        firstDateOfWeek = firstDateOfFirstWeek;
      } else if (StringUtils.containsIgnoreCase(selectedWeek, StatisticChartConstants.SECONDWEEK_CMS)){
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 1));
      } else if (StringUtils.containsIgnoreCase(selectedWeek, StatisticChartConstants.THIRDWEEK_CMS)){
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 2));
      } else if (StringUtils.containsIgnoreCase(selectedWeek, StatisticChartConstants.FOURTHWEEK_CMS)){
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 3));
      } else if (StringUtils.containsIgnoreCase(selectedWeek, StatisticChartConstants.FIFTHWEEK_CMS)){
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 4));
      } else if (StringUtils.containsIgnoreCase(selectedWeek, StatisticChartConstants.SIXTHWEEK_CMS)) {
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 5));
      }
    }
    
    return firstDateOfWeek;
  }

  private Map<Object, Number> generateExpiryModelForDrilldownLevelMonth(Map<Date, Long> statisticResultMap,
      String selectedValue) {
    Map<Object, Number> chartData = new LinkedHashMap<Object, Number>();

    Long taskExpireOnFirstWeek = new Long(0L);
    Long taskExpireOnSecondWeek = new Long(0L);
    Long taskExpireOnThirdWeek = new Long(0L);
    Long taskExpireOnFourthWeek = new Long(0L);
    Long taskExpireOnFifthWeek = new Long(0L);
    Long taskExpireOnSixthWeek = new Long(0L);

    Date firstDateOfSelectedMonth = getFirstDateOfMonth(selectedValue);
    Date firstDateOfWeek = getFirstDateOfWeekContainsDate(firstDateOfSelectedMonth);

    Date firstDateOfFirstWeek = truncateMinutesPart(firstDateOfSelectedMonth);
    Date firstDateOfSecondWeek = truncateMinutesPart(DateUtils.addDays(firstDateOfWeek, 7));
    Date firstDateOfThirdWeek = truncateMinutesPart(DateUtils.addDays(firstDateOfWeek, 14));
    Date firstDateOfFourthWeek = truncateMinutesPart(DateUtils.addDays(firstDateOfWeek, 21));
    Date firstDateOfFifthWeek = truncateMinutesPart(DateUtils.addDays(firstDateOfWeek, 28));
    Date firstDateOfSixthWeek = truncateMinutesPart(DateUtils.addDays(firstDateOfWeek, 35));
    Date firstDateOfNextMonth = truncateMinutesPart(DateUtils.addMonths(firstDateOfSelectedMonth, 1));

    for (Entry<Date, Long> result : statisticResultMap.entrySet()) {
      Date resultDate = truncateMinutesPart(result.getKey());
      if (firstDateOfFirstWeek.compareTo(resultDate) <= 0 && firstDateOfSecondWeek.compareTo(resultDate) > 0) {
        taskExpireOnFirstWeek += result.getValue();
      }
      if (firstDateOfSecondWeek.compareTo(resultDate) <= 0 && firstDateOfThirdWeek.compareTo(resultDate) > 0) {
        taskExpireOnSecondWeek += result.getValue();
      }
      if (firstDateOfThirdWeek.compareTo(resultDate) <= 0 && firstDateOfFourthWeek.compareTo(resultDate) > 0) {
        taskExpireOnThirdWeek += result.getValue();
      }
      if (firstDateOfFourthWeek.compareTo(resultDate) <= 0 && firstDateOfFifthWeek.compareTo(resultDate) > 0) {
        taskExpireOnFourthWeek += result.getValue();
      }
      if (firstDateOfSixthWeek.compareTo(firstDateOfNextMonth) < 0) {
        if (firstDateOfFifthWeek.compareTo(resultDate) <= 0 && firstDateOfSixthWeek.compareTo(resultDate) > 0) {
          taskExpireOnFifthWeek += result.getValue();
        }
        if (firstDateOfSixthWeek.compareTo(resultDate) <= 0 && firstDateOfNextMonth.compareTo(resultDate) > 0) {
          taskExpireOnSixthWeek += result.getValue();
        }
      } else {
        if (firstDateOfFifthWeek.compareTo(resultDate) <= 0 && firstDateOfNextMonth.compareTo(resultDate) > 0) {
          taskExpireOnFifthWeek += result.getValue();
        }
      }
    }

    chartData.put(StatisticChartConstants.FIRSTWEEK_CMS, taskExpireOnFirstWeek);
    chartData.put(StatisticChartConstants.SECONDWEEK_CMS, taskExpireOnSecondWeek);
    chartData.put(StatisticChartConstants.THIRDWEEK_CMS, taskExpireOnThirdWeek);
    chartData.put(StatisticChartConstants.FOURTHWEEK_CMS, taskExpireOnFourthWeek);
    if (firstDateOfNextMonth.compareTo(firstDateOfFifthWeek) > 0) {
      chartData.put(StatisticChartConstants.FIFTHWEEK_CMS, taskExpireOnFifthWeek);
    }
    if (firstDateOfNextMonth.compareTo(firstDateOfSixthWeek) > 0) {
      chartData.put(StatisticChartConstants.SIXTHWEEK_CMS, taskExpireOnSixthWeek);
    }
    return chartData;
  }

  private Date getFirstDateOfMonth(String selectedMonth) {
    if (StringUtils.containsIgnoreCase(selectedMonth, Ivy.cms().co(StatisticChartConstants.THIS_MONTH_EXPIRY_KEY))) {
      return getFirstDateOfThisMonth();
    }
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_MONTH, 1);
    if (StringUtils.containsIgnoreCase(selectedMonth, StatisticChartConstants.JANUARY_CMS)) {
      cal.set(Calendar.MONTH, 0);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, StatisticChartConstants.FEBRUARY_CMS)) {
      cal.set(Calendar.MONTH, 1);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, StatisticChartConstants.MARCH_CMS)) {
      cal.set(Calendar.MONTH, 2);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, StatisticChartConstants.APRIL_CMS)) {
      cal.set(Calendar.MONTH, 3);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, StatisticChartConstants.MAY_CMS)) {
      cal.set(Calendar.MONTH, 4);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, StatisticChartConstants.JUNE_CMS)) {
      cal.set(Calendar.MONTH, 5);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, StatisticChartConstants.JULY_CMS)) {
      cal.set(Calendar.MONTH, 6);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, StatisticChartConstants.AUGUST_CMS)) {
      cal.set(Calendar.MONTH, 7);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, StatisticChartConstants.SEPTEMBER_CMS)) {
      cal.set(Calendar.MONTH, 8);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, StatisticChartConstants.OCTOBER_CMS)) {
      cal.set(Calendar.MONTH, 9);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, StatisticChartConstants.NOVEMBER_CMS)) {
      cal.set(Calendar.MONTH, 10);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, StatisticChartConstants.DECEMBER_CMS)) {
      cal.set(Calendar.MONTH, 11);
    }
    return cal.getTime();
  }

  private Map<Object, Number> generateExpiryModelForDrilldownLevelYear(Map<Date, Long> statisticResultMap) {
    Map<Object, Number> chartData = new LinkedHashMap<Object, Number>();
    
    Long taskExpireOnJanuary = new Long(0L);
    Long taskExpireOnFebruary = new Long(0L);
    Long taskExpireOnMarch = new Long(0L);
    Long taskExpireOnApril = new Long(0L);
    Long taskExpireOnMay = new Long(0L);
    Long taskExpireOnJune = new Long(0L);
    Long taskExpireOnJuly = new Long(0L);
    Long taskExpireOnAugust = new Long(0L);
    Long taskExpireOnSeptember = new Long(0L);
    Long taskExpireOnOctober = new Long(0L);
    Long taskExpireOnNovember = new Long(0L);
    Long taskExpireOnDecember = new Long(0L);

    for (Entry<Date, Long> result : statisticResultMap.entrySet()) {
      Date resultDate = truncateMinutesPart(result.getKey());
      Calendar cal = Calendar.getInstance();
      cal.setTime(resultDate);
      
      int thisYear = Year.now().getValue();
      if (cal.get(Calendar.YEAR) == thisYear) {
        if(cal.get(Calendar.MONTH) == Calendar.JANUARY){
          taskExpireOnJanuary += result.getValue();
        } else if(cal.get(Calendar.MONTH) == Calendar.FEBRUARY){
          taskExpireOnFebruary += result.getValue();
        } else if(cal.get(Calendar.MONTH) == Calendar.MARCH){
          taskExpireOnMarch += result.getValue();
        } else if(cal.get(Calendar.MONTH) == Calendar.APRIL){
          taskExpireOnApril += result.getValue();
        } else if(cal.get(Calendar.MONTH) == Calendar.MAY){
          taskExpireOnMay += result.getValue();
        } else if(cal.get(Calendar.MONTH) == Calendar.JUNE){
          taskExpireOnJune += result.getValue();
        } else if(cal.get(Calendar.MONTH) == Calendar.JULY){
          taskExpireOnJuly += result.getValue();
        } else if(cal.get(Calendar.MONTH) == Calendar.AUGUST){
          taskExpireOnAugust += result.getValue();
        } else if(cal.get(Calendar.MONTH) == Calendar.SEPTEMBER){
          taskExpireOnSeptember += result.getValue();
        } else if(cal.get(Calendar.MONTH) == Calendar.OCTOBER){
          taskExpireOnOctober += result.getValue();
        } else if(cal.get(Calendar.MONTH) == Calendar.NOVEMBER){
          taskExpireOnNovember += result.getValue();
        } else if(cal.get(Calendar.MONTH) == Calendar.DECEMBER){
          taskExpireOnDecember += result.getValue();
        }
      }
    }
    
    chartData.put(StatisticChartConstants.JANUARY_CMS, taskExpireOnJanuary);
    chartData.put(StatisticChartConstants.FEBRUARY_CMS, taskExpireOnFebruary);
    chartData.put(StatisticChartConstants.MARCH_CMS, taskExpireOnMarch);
    chartData.put(StatisticChartConstants.APRIL_CMS, taskExpireOnApril);
    chartData.put(StatisticChartConstants.MAY_CMS, taskExpireOnMay);
    chartData.put(StatisticChartConstants.JUNE_CMS, taskExpireOnJune);
    chartData.put(StatisticChartConstants.JULY_CMS, taskExpireOnJuly);
    chartData.put(StatisticChartConstants.AUGUST_CMS, taskExpireOnAugust);
    chartData.put(StatisticChartConstants.SEPTEMBER_CMS, taskExpireOnSeptember);
    chartData.put(StatisticChartConstants.OCTOBER_CMS, taskExpireOnOctober);
    chartData.put(StatisticChartConstants.NOVEMBER_CMS, taskExpireOnNovember);
    chartData.put(StatisticChartConstants.DECEMBER_CMS, taskExpireOnDecember);
    return chartData;
  }

  private Map<Object, Number> generateDefaultExpiryModel(Map<Date, Long> statisticResultMap) {
    Map<Object, Number> chartData = new LinkedHashMap<Object, Number>();
    // Calculate result
    Long taskExpireToday = new Long(0L);
    Long taskExpireThisWeek = new Long(0L);
    Long taskExpireThisMonth = new Long(0L);
    Long taskExpireThisYear = new Long(0L);

    Date today = truncateMinutesPart(new Date());
    Date firstDateOfWeek = truncateMinutesPart(getFirstDateOfWeekContainsDate(new Date()));
    Date firstDateOfNextWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfWeek, 1));
    Date firstDateOfMonth = truncateMinutesPart(getFirstDateOfThisMonth());
    Date firsDateOfNextMonth = truncateMinutesPart(DateUtils.addMonths(firstDateOfMonth, 1));
    Date firsDateOfYear = truncateMinutesPart(getFirstDateOfThisYear());
    Date firsDateOfNextYear = truncateMinutesPart(DateUtils.addYears(firsDateOfYear, 1));

    for (Entry<Date, Long> result : statisticResultMap.entrySet()) {
      Date resultDate = truncateMinutesPart(result.getKey());

      if (today.compareTo(resultDate) == 0) {
        taskExpireToday += result.getValue();
      }

      if (firstDateOfWeek.compareTo(resultDate) <= 0 && firstDateOfNextWeek.compareTo(resultDate) > 0) {
        taskExpireThisWeek += result.getValue();
      }
      if (firstDateOfMonth.compareTo(resultDate) <= 0 && firsDateOfNextMonth.compareTo(resultDate) > 0) {
        taskExpireThisMonth += result.getValue();
      }
      if (firsDateOfYear.compareTo(resultDate) <= 0 && firsDateOfNextYear.compareTo(resultDate) > 0) {
        taskExpireThisYear += result.getValue();
      }
    }
    
    chartData.put(Ivy.cms().co(StatisticChartConstants.TODAY_EXPIRY_KEY), taskExpireToday);
    chartData.put(Ivy.cms().co(StatisticChartConstants.THIS_WEEK_EXPIRY_KEY), taskExpireThisWeek);
    chartData.put(Ivy.cms().co(StatisticChartConstants.THIS_MONTH_EXPIRY_KEY), taskExpireThisMonth);
    chartData.put(Ivy.cms().co(StatisticChartConstants.THIS_YEAR_EXPIRY_KEY), taskExpireThisYear);

    return chartData;
  }

  /**
   * generate data for "Case by State" chart
   * 
   * @param caseStateStatistic statistic data
   * @return generated data
   */
  public Map<String, Number> generateDataForCaseStateChart(CaseStateStatistic caseStateStatistic) {
    Map<String, Number> chartData = new LinkedHashMap<>();

    if (caseStateStatistic.getCreated() > 0) {
      chartData.put(Ivy.cms().co(StatisticChartConstants.CREATED_CASE_KEY), caseStateStatistic.getCreated());
    }

    if (caseStateStatistic.getDone() > 0) {
      chartData.put(Ivy.cms().co(StatisticChartConstants.DONE_CASE_KEY), caseStateStatistic.getDone());
    }

    if (caseStateStatistic.getFailed() > 0) {
      chartData.put(Ivy.cms().co(StatisticChartConstants.FAILED_CASE_KEY), caseStateStatistic.getFailed());
    }

    if (caseStateStatistic.getRunning() > 0) {
      chartData.put(Ivy.cms().co(StatisticChartConstants.RUNNING_CASE_KEY), caseStateStatistic.getRunning());
    }

    return chartData;
  }

  /**
   * generate data for "Elapsed Time by Case Category" chart
   * 
   * @param elapsedTimeStatistic statistic data
   * @return generated data
   */
  public Map<String, Number> generateDataForElapsedTimeChart(List<ElapsedTimeStatistic> elapsedTimeStatistic) {
    Map<String, Number> chartDataTemp = new LinkedHashMap<>();
    Map<String, Number> chartData = new LinkedHashMap<>();

    // Convert result
    Gson gsonConverter = new Gson();
    Type type = new TypeToken<Map<String, String>>() {}.getType();

    elapsedTimeStatistic.forEach(statistic -> {
      if (StringUtils.isNotBlank(statistic.getResult())) {
        Map<String, String> resultAsString = new HashMap<String, String>();
        resultAsString.putAll(gsonConverter.fromJson(statistic.getResult(), type));
        resultAsString.entrySet().forEach(result -> {
          Number elapsedTime = NumberUtils.createNumber(result.getValue());
          chartDataTemp.put(result.getKey(), elapsedTime);
        });
      }
    });

    for (Entry<String, Number> entry : chartDataTemp.entrySet()) {
      String key = entry.getKey().toString();
      if (StringUtils.isBlank(key)) {
        key = StatisticChartConstants.NO_CATEGORY_CMS;
      }
      if (chartData.containsKey(key)) {
        Number updatedTime = new BigDecimal(chartData.get(key).longValue() + entry.getValue().longValue());
        chartData.put(key, updatedTime);
      } else {
        chartData.put(key, entry.getValue());
      }
    }

    return chartData;
  }

  /**
   * Add statistic chart to business data
   * 
   * @param filter filter to generate json query for statistic chart
   * @param chartName chart name
   * @param chartType chart type
   * @param creatorId Id of the creator
   * @return Added statistic chart
   */
  public StatisticChart createStatisticChart(StatisticFilter filter, String chartName, StatisticChartType chartType,
      long creatorId) {
    StatisticChart statisticChart = new StatisticChart();

    statisticChart.setUserId(creatorId);
    statisticChart.setType(chartType);
    statisticChart.setName(Optional.ofNullable(chartName).orElse("New chart"));
    statisticChart.setPosition(countStatisticChartsByUserId(creatorId));
    statisticChart.setFilter(filter);
    BusinessDataInfo<StatisticChart> info = save(statisticChart);
    return findById(info.getId());
  }

  /**
   * Generate chart model for "Task By Priority" chart
   * 
   * @param statisticData statistic data
   * @param isSetDefaultName
   * @return chart model for "Task By Priority" chart
   */
  public DonutChartModel generateTaskByPriorityModel(PriorityStatistic statisticData, boolean isSetDefaultName) {
    Map<String, Number> chartData = generateDataForTaskByPriorityChart(statisticData);
    boolean isEmptyData = chartData.isEmpty();
    DonutChartModel model = new DonutChartModel();

    if (isEmptyData) {
      chartData.put(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/tasks"), 0);
    }

    model.addCircle(chartData);
    model.setLegendPosition("s");
    model.setShowDataLabels(true);
    model.setExtender("chartExtender");
    model.setShadow(false);
    model.setDataFormat("percent");
    model.setSliceMargin(3);
    model.setLegendRows(1);
    if (!isEmptyData) {
      model.setSeriesColors(Colors.getPriorityColors(chartData));
    }
    model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
    if (isSetDefaultName) {
      model.setTitle(Ivy.cms().co(StatisticChartType.TASK_BY_PRIORITY.getCmsUri()));
    }

    return model;
  }

  /**
   * Generate chart model for "Task By Expiry" chart
   * 
   * @param statisticData statistic data
   * @param isSetDefaultName
   * @param selectedValue selected value
   * @param previousSelectedMonth previous selected month
   * @param previousSelectedWeek previous selected week
   * @return chart model for "Task By Expiry" chart
   */
  public BarChartModel generateTaskByExpiryModel(List<ExpiryStatistic> statisticData, boolean isSetDefaultName,
      String selectedValue, String previousSelectedMonth, String previousSelectedWeek) {
    Map<Object, Number> chartData = generateDataForTaskByExpiryOverviewChart(statisticData, selectedValue, previousSelectedMonth, previousSelectedWeek);
    BarChartModel model = new BarChartModel();
    ChartSeries chartSeries = new ChartSeries();

    if (chartData.size() != 0) {

      chartSeries.setData(chartData);
      model.setExtender("barChartExtender");
      model.setShadow(false);

      Axis xAxis = model.getAxis(AxisType.X);
      String label = Ivy.cms().co(StatisticChartConstants.EXPIRY_PERIOD_CMS);
      if (selectDayOfWeek(selectedValue)) {
        label = label + " " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/hour");
      }
      xAxis.setLabel(label);

      Axis yAxis = model.getAxis(AxisType.Y);
      yAxis.setLabel(Ivy.cms().co(StatisticChartConstants.TASK_CMS));

      String datatipFormat = StringUtils.join("%2$.0f ", Ivy.cms().co(StatisticChartConstants.TASK_DATATIP_CMS));
      model.setDatatipFormat(datatipFormat);
    }
    if (isSetDefaultName) {
      model.setTitle(Ivy.cms().co(StatisticChartType.TASK_BY_EXPIRY.getCmsUri()));
    }
    model.addSeries(chartSeries);
    return model;
  }

  /**
   * Generate chart model for "Case By State" chart
   * 
   * @param statisticData statistic data
   * @param chartType statistic chart type
   * @param isSetDefaultName
   * @return chart model for "Case By State" chart
   */
  public DonutChartModel generateCaseByStateModel(CaseStateStatistic statisticData, StatisticChartType chartType, boolean isSetDefaultName) {
    Map<String, Number> chartData = generateDataForCaseStateChart(statisticData);
    boolean isEmptyData = chartData.isEmpty();
    DonutChartModel model = new DonutChartModel();

    if (isEmptyData) {
      chartData.put(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/cases"), 0);
    }

    model.addCircle(chartData);
    model.setLegendPosition("s");
    model.setShowDataLabels(true);
    model.setExtender("chartExtender");
    model.setShadow(false);
    model.setDataFormat("percent");
    model.setSliceMargin(3);
    model.setLegendRows(1);
    if (!isEmptyData) {
      model.setSeriesColors(Colors.getCaseStateColors(chartData));
    }
    model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
    if (isSetDefaultName) {
      if(chartType == StatisticChartType.CASES_BY_STATE){
        model.setTitle(Ivy.cms().co(StatisticChartType.CASES_BY_STATE.getCmsUri()));
      }
      else if(chartType == StatisticChartType.CASES_BY_FINISHED_TASK){
        model.setTitle(Ivy.cms().co(StatisticChartType.CASES_BY_FINISHED_TASK.getCmsUri()));
      }
      else if(chartType == StatisticChartType.CASES_BY_FINISHED_TIME){
        model.setTitle(Ivy.cms().co(StatisticChartType.CASES_BY_FINISHED_TIME.getCmsUri()));
      }
    }

    return model;
  }

  /**
   * Generate chart model for "Elapsed time by Case Category" chart
   * 
   * @param statisticData statistic data
   * @param isSetDefaultName
   * @return chart model for "Elapsed time by Case Category" chart
   */
  public PieChartModel generateElapsedTimeModel(List<ElapsedTimeStatistic> statisticData, boolean isSetDefaultName) {
    Map<String, Number> chartData = generateDataForElapsedTimeChart(statisticData);
    PieChartModel model = new PieChartModel();

    if (chartData.size() == 0) {
      chartData.put(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseCategory"), 0);
    }

    float totalValue = 0;
    float otherValue = 0;

    for (Number number : chartData.values()) {
      totalValue += number.floatValue();
    }

    for (Iterator<Entry<String, Number>> iterator = chartData.entrySet().iterator(); iterator.hasNext();) {
      Entry<String, Number> chartDataEntry = iterator.next();
      float floatValueOfChartData = chartDataEntry.getValue().floatValue();
      if (floatValueOfChartData < totalValue * 0.02) {
        otherValue = otherValue + floatValueOfChartData;
        iterator.remove();
      }
    }

    if (otherValue != 0) {
      chartData.put(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/other"), otherValue);
    }

    model.setData(chartData);
    model.setLegendPosition("s");
    model.setShowDataLabels(true);
    model.setExtender("chartExtender");
    model.setShadow(false);
    model.setDataFormat("percent");
    model.setSliceMargin(3);
    model.setLegendCols(1);
    model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
    String datatipFormat = StringUtils.join("%2$.0f ", Ivy.cms().co(StatisticChartConstants.SECOND_DATATIP_CMS));
    model.setDatatipFormat(datatipFormat);
    if (isSetDefaultName) {
      model.setTitle(Ivy.cms().co(StatisticChartType.ELAPSED_TIME_BY_CASE_CATEGORY.getCmsUri()));
    }

    return model;
  }

  public boolean isTaskByPriority(StatisticChart statisticChart) {
    return statisticChart.getType() == StatisticChartType.TASK_BY_PRIORITY;
  }

  public boolean isTaskByExpiry(StatisticChart statisticChart) {
    return statisticChart.getType() == StatisticChartType.TASK_BY_EXPIRY;
  }

  public boolean isCaseByState(StatisticChart statisticChart) {
    return statisticChart.getType() == StatisticChartType.CASES_BY_STATE;
  }

  public boolean isElapsedTimeByCaseCategory(StatisticChart statisticChart) {
    return statisticChart.getType() == StatisticChartType.ELAPSED_TIME_BY_CASE_CATEGORY;
  }
  
  public boolean isCaseByFinishedTask(StatisticChart statisticChart) {
    return statisticChart.getType() == StatisticChartType.CASES_BY_FINISHED_TASK;
  }
  
  public boolean isCaseByFinishedTime(StatisticChart statisticChart) {
    return statisticChart.getType() == StatisticChartType.CASES_BY_FINISHED_TIME;
  }

  /**
   * 
   * @param statisticChartList
   */
  public void generateChartModelForStatisticCharts(List<StatisticChart> statisticChartList) {
    for (StatisticChart statisticChart : statisticChartList) {
      switch (statisticChart.getType()) {
        case TASK_BY_PRIORITY:
          PriorityStatistic taskByPriorityData = new PriorityStatistic();
          if(statisticChart.getFilter() != null) {
            taskByPriorityData = getPriorityStatisticData(generateTaskQuery(statisticChart.getFilter()).asJson());
          }
          statisticChart.setDonutChartModel(generateTaskByPriorityModel(taskByPriorityData, true));
          break;
        case TASK_BY_EXPIRY:
          List<ExpiryStatistic> taskByExpiryData = new ArrayList<>();
          if(statisticChart.getFilter() != null){
            taskByExpiryData = getExpiryStatisticData(generateTaskQueryForExpiry(statisticChart.getFilter()).asJson());
          }
          statisticChart.setBarChartModel(generateTaskByExpiryModel(taskByExpiryData, true, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY));
          break;
        case CASES_BY_STATE:
          CaseStateStatistic caseStateData = new CaseStateStatistic();
          if(statisticChart.getFilter() != null) {
            caseStateData = getCaseStateStatisticData(generateCaseQuery(statisticChart.getFilter(), false).asJson());
          }
          statisticChart.setDonutChartModel(generateCaseByStateModel(caseStateData, StatisticChartType.CASES_BY_STATE,  true));
          break;
        case ELAPSED_TIME_BY_CASE_CATEGORY:
          List<ElapsedTimeStatistic> elapsedTimeData = new ArrayList<>();
          if(statisticChart.getFilter() != null) {
            elapsedTimeData = getElapsedTimeStatisticData(generateCaseQuery(statisticChart.getFilter(), true).asJson());
          }
          statisticChart.setPieChartModel(generateElapsedTimeModel(elapsedTimeData, true));
          break;
        case CASES_BY_FINISHED_TASK:
          CaseStateStatistic caseByFinishedTaskData = new CaseStateStatistic();
          if(statisticChart.getFilter() != null) {
            caseByFinishedTaskData = getCaseStateStatisticData(generateCaseQueryForCaseHaveFinishedTask(statisticChart.getFilter()).asJson());
          }
          statisticChart.setDonutChartModel(generateCaseByStateModel(caseByFinishedTaskData, StatisticChartType.CASES_BY_FINISHED_TASK, true));
          break;
        case CASES_BY_FINISHED_TIME:
          CaseStateStatistic caseByFinishedTimeData = new CaseStateStatistic();
          if(statisticChart.getFilter() != null) {
            caseByFinishedTimeData = getCaseStateStatisticData(generateCaseQueryByFinishedTime(statisticChart.getFilter()).asJson());
          }
          statisticChart.setDonutChartModel(generateCaseByStateModel(caseByFinishedTimeData, StatisticChartType.CASES_BY_FINISHED_TIME, true));
          break;
        default:
          break;
      }
    }
  }

  public boolean isDrilldownToTaskList(String expiryLastDrilldownLevel, String selectedItem) {
    // hour
    if (StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, StatisticChartConstants.DRILLDOWN_LEVEL_HOUR) && (selectHourOfDay(selectedItem))) {
      return true;
    }
    // day
    if (StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, StatisticChartConstants.DRILLDOWN_LEVEL_DAY) && (selectDayOfWeek(selectedItem))) {
      return true;
    }
    // week
    if (StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, StatisticChartConstants.DRILLDOWN_LEVEL_WEEK)
        && (selectDayOfWeek(selectedItem) || selectWeekOfMonth(selectedItem))) {
      return true;
    }
    // month
    if (StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, StatisticChartConstants.DRILLDOWN_LEVEL_MONTH)
        && (selectDayOfWeek(selectedItem) || selectWeekOfMonth(selectedItem) || selectMonthOfYear(selectedItem))) {
      return true;
    }
    //year
    if(StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, StatisticChartConstants.DRILLDOWN_LEVEL_YEAR)){
      return true;
    }
    return false;
  }

  public boolean selectThisYear(String selectedItem) {
    return StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(StatisticChartConstants.THIS_YEAR_EXPIRY_KEY));
  }

  public boolean selectMonthOfYear(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    return Arrays.asList(StatisticChartConstants.MONTHSOFYEAR).contains(selectedItem)
        || StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(StatisticChartConstants.THIS_MONTH_EXPIRY_KEY));
  }

  public boolean selectWeekOfMonth(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    return Arrays.asList(StatisticChartConstants.WEEKSOFMONTH).contains(selectedItem)
        || StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(StatisticChartConstants.THIS_WEEK_EXPIRY_KEY));
  }

  public boolean selectDayOfWeek(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    return Arrays.asList(StatisticChartConstants.DAYSOFWEEK).contains(selectedItem)
        || StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(StatisticChartConstants.TODAY_EXPIRY_KEY));
  }

  public boolean selectHourOfDay(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    return Arrays.asList(StatisticChartConstants.HOURSOFDAY).contains(selectedItem);
  }

  public StatisticChart drilldownExpiryChart(String selectedValue, StatisticChart selectedChart, String previousSelectedMonth, String previousSelectedWeek) {
    List<ExpiryStatistic> taskByExpiryData = getExpiryStatisticData(generateTaskQuery(selectedChart.getFilter()).asJson());
    StatisticChart newStatisticChart = new StatisticChart();
    newStatisticChart.setId(selectedChart.getId() + "_" + selectedValue); //chart with format: id + _ + suffix is lower level (month/week/day/hour) chart when drill down
    newStatisticChart.setName(selectedChart.getName() + " - " + selectedValue);
    newStatisticChart.setFilter(selectedChart.getFilter());
//    newStatisticChart.setJsonQuery(selectedChart.getJsonQuery());
    newStatisticChart.setType(StatisticChartType.TASK_BY_EXPIRY);
    newStatisticChart.setUserId(selectedChart.getUserId());
    newStatisticChart.setBarChartModel(generateTaskByExpiryModel(taskByExpiryData, true, selectedValue, previousSelectedMonth, previousSelectedWeek));
    return newStatisticChart;
  }

  private String getSelectedValueOfDonutChart(ItemSelectEvent event) {
    try {
      DonutChartModel model = (DonutChartModel) ((Chart) event.getSource()).getModel();
      int index = event.getItemIndex();
      return model.getData().get(0).keySet().toArray()[index].toString();
    } catch (Exception e) {
      return "";
    }
  }

  public String getSelectedValueOfBarChart(ItemSelectEvent event) {
    try {
      BarChartModel model = (BarChartModel) ((Chart) event.getSource()).getModel();
      int index = event.getItemIndex();
      return model.getSeries().get(0).getData().keySet().toArray()[index].toString();
    } catch (Exception e) {
      return "";
    }
  }

  public String getSelectedValueOfPieChart(ItemSelectEvent event) {
    try {
      PieChartModel model = (PieChartModel) ((Chart) event.getSource()).getModel();
      int index = event.getItemIndex();
      return model.getData().keySet().toArray()[index].toString();
    } catch (Exception e) {
      return "";
    }
  }

  private Date getFirstDateOfWeekContainsDate(Date containedDate) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(containedDate);
    while (calendar.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY) {
      calendar.add(Calendar.DATE, -1);
    }
    return calendar.getTime();
  }

  private Date getFirstDateOfThisMonth() {
    Calendar calendar = Calendar.getInstance();
    while (calendar.get(Calendar.DAY_OF_MONTH) > 1) {
      calendar.add(Calendar.DATE, -1);
    }
    return calendar.getTime();
  }

  private Date getFirstDateOfThisYear() {
    Calendar calendar = Calendar.getInstance();
    while (calendar.get(Calendar.DAY_OF_YEAR) > 1) {
      calendar.add(Calendar.DATE, -1);
    }
    return calendar.getTime();
  }

  /**
   * Get updated task query for Task by Priority chart based on selected item
   * 
   * @param event
   * @param statisticChart
   * @return updated task query
   */
  public TaskQuery getQueryForSelectedItemOfTaskByPriorityChart(ItemSelectEvent event, StatisticChart statisticChart) {
    TaskQuery query = generateTaskQuery(statisticChart.getFilter());
    String selectedValue = getSelectedValueOfDonutChart(event);

    if (selectedValue.equals(Ivy.cms().co(StatisticChartConstants.EXCEPTION_PRIORITY_KEY))) {
      query.where().and().priority().isEqual(WorkflowPriority.EXCEPTION);
    } else if (selectedValue.equals(Ivy.cms().co(StatisticChartConstants.HIGH_PRIORITY_KEY))) {
      query.where().and().priority().isEqual(WorkflowPriority.HIGH);
    } else if (selectedValue.equals(Ivy.cms().co(StatisticChartConstants.NORMAL_PRIORITY_KEY))) {
      query.where().and().priority().isEqual(WorkflowPriority.NORMAL);
    } else if (selectedValue.equals(Ivy.cms().co(StatisticChartConstants.LOW_PRIORITY_KEY))) {
      query.where().and().priority().isEqual(WorkflowPriority.LOW);
    }

    return query;
  }

  /**
   * Get updated task query for Task by Expiry chart based on selected item
   * 
   * @param event
   * @param statisticChart statistic chart
   * @param previousSelectedMonth previous selected month
   * @param previousSelectedWeek previous selected week
   * @param previousSelectedDay previous selected day
   * @return updated task query
   */
  public TaskQuery getQueryForSelectedItemOfTaskByExpiryChart(ItemSelectEvent event, StatisticChart statisticChart, String previousSelectedMonth, String previousSelectedWeek, String previousSelectedDay) {
    TaskQuery query = generateTaskQueryForExpiry(statisticChart.getFilter());
    String selectedValue = getSelectedValueOfBarChart(event);
    Date fromDate;
    Date toDate;
    if (selectHourOfDay(selectedValue)) {
      Date currentDate;
      if (StringUtils.containsIgnoreCase(previousSelectedDay, Ivy.cms().co(StatisticChartConstants.TODAY_EXPIRY_KEY))) {
        currentDate = new Date();
      } else {
        int shiftDays = getShiftDaysFromDayOfWeek(previousSelectedDay);
        currentDate = DateUtils.addDays(getFirstDateOfWeek(previousSelectedWeek, previousSelectedMonth), shiftDays);
      }

      Date dateWithoutTime = truncateMinutesPart(currentDate);
      if (selectedValue.equals(StatisticChartConstants.BEFORE_8)) {
        fromDate = dateWithoutTime;
        toDate = DateUtils.setHours(dateWithoutTime, 8);
      } else if (selectedValue.equals(StatisticChartConstants.AFTER_18)) {
        fromDate = DateUtils.setHours(dateWithoutTime, 18);
        toDate = DateUtils.addDays(dateWithoutTime, 1);
      } else {
        fromDate = DateUtils.setHours(dateWithoutTime, Integer.parseInt(selectedValue));
        toDate = DateUtils.setHours(dateWithoutTime, Integer.parseInt(selectedValue) + 1);
      }
    } else if (selectDayOfWeek(selectedValue)) {
      if (StringUtils.containsIgnoreCase(selectedValue, Ivy.cms().co(StatisticChartConstants.TODAY_EXPIRY_KEY))) {
        fromDate = truncateMinutesPart(new Date());
      } else {
        int shiftDays = getShiftDaysFromDayOfWeek(selectedValue);
        fromDate = DateUtils.addDays(getFirstDateOfWeek(previousSelectedWeek, previousSelectedMonth), shiftDays);
        fromDate = truncateMinutesPart(fromDate);
      }
      toDate = DateUtils.addDays(fromDate, 1);
    } else if (selectWeekOfMonth(selectedValue)) {
      fromDate = truncateMinutesPart(getFirstDateOfWeek(selectedValue, previousSelectedMonth));
      toDate = DateUtils.addWeeks(fromDate, 1);

      if (!StringUtils.isEmpty(previousSelectedMonth)) {
        Date firstDateOfMonth = truncateMinutesPart(getFirstDateOfMonth(previousSelectedMonth));
        if (firstDateOfMonth.compareTo(fromDate) > 0) {
          fromDate = firstDateOfMonth;
        }
        Date firstDayOfNextMonth = DateUtils.addMonths(firstDateOfMonth, 1);
        if (toDate.compareTo(firstDayOfNextMonth) > 0) {
          toDate = firstDayOfNextMonth;
        }
      }
    } else if (selectMonthOfYear(selectedValue)) {
      fromDate = truncateMinutesPart(getFirstDateOfMonth(selectedValue));
      toDate = DateUtils.addMonths(fromDate, 1);
    } else {
      fromDate = truncateMinutesPart(getFirstDateOfThisYear());
      toDate = DateUtils.addYears(fromDate, 1);
    }

    query.where().and().expiryTimestamp().isGreaterOrEqualThan(fromDate).and().expiryTimestamp()
    .isLowerThan(toDate);

    return query;
  }

  /**
   * Get task query for Elapsed Time by Case Category chart based on selected item
   * 
   * @param caseCategory
   * @return task query for Elapsed Time by Case Category chart
   */
  public TaskQuery getQueryForSelectedItemElapsedTime(String caseCategory) {
    CaseQuery caseQuery = CaseQuery.create();
    caseQuery.where().state().isEqual(CaseState.DONE).and().category().isEqual(caseCategory);

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);

    return query;
  }

  /**
   * Get updated task query for Case by State chart based on selected item
   * 
   * @param event
   * @param statisticChart
   * @return case query for selected item by case state
   */
  public CaseQuery getQueryForSelectedItemByCaseByState(ItemSelectEvent event, StatisticChart statisticChart) {
    CaseQuery query = CaseQuery.create();
    if(statisticChart.getType() == StatisticChartType.CASES_BY_STATE){
      query = generateCaseQuery(statisticChart.getFilter(), false);
    }
    else if(statisticChart.getType() == StatisticChartType.CASES_BY_FINISHED_TIME) {
      query = generateCaseQueryByFinishedTime(statisticChart.getFilter());
    }
    else if(statisticChart.getType() == StatisticChartType.CASES_BY_FINISHED_TASK) {
      query = generateCaseQueryForCaseHaveFinishedTask(statisticChart.getFilter());
    }
    
    String selectedValue = getSelectedValueOfDonutChart(event);

    if (selectedValue.equals(Ivy.cms().co(StatisticChartConstants.CREATED_CASE_KEY))) {
      query.where().state().isEqual(CaseState.CREATED);
    } else if (selectedValue.equals(Ivy.cms().co(StatisticChartConstants.RUNNING_CASE_KEY))) {
      query.where().state().isEqual(CaseState.RUNNING);
    } else if (selectedValue.equals(Ivy.cms().co(StatisticChartConstants.DONE_CASE_KEY))) {
      query.where().state().isEqual(CaseState.DONE);
    } else if (selectedValue.equals(Ivy.cms().co(StatisticChartConstants.FAILED_CASE_KEY))) {
      query.where().state().isEqual(CaseState.DESTROYED).or().state().isEqual(CaseState.ZOMBIE);
    }

    return query;
  }

  public boolean checkStatisticChartNameExisted(long userId, String chartName) {
    List<StatisticChart> statisticChartList = findStatisticChartsByUserId(userId);
    return statisticChartList.stream().filter(chart -> StringUtils.equals(chart.getName(), chartName)).findFirst()
        .isPresent();
  }

  private Date truncateMinutesPart(Date date) {
    return DateUtils.truncate(date, Calendar.DATE);
  }

}
