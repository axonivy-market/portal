package ch.ivy.addon.portalkit.service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
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
import ch.ivy.addon.portalkit.statistics.Colors;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivy.ws.addon.CaseStateStatistic;
import ch.ivy.ws.addon.ElapsedTimeStatistic;
import ch.ivy.ws.addon.ExpiryStatistic;
import ch.ivy.ws.addon.PriorityStatistic;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class StatisticService extends BusinessDataService<StatisticChart> {
  private static final String EXCEPTION_PRIORITY_KEY =
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/exception";
  private static final String HIGH_PRIORITY_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/high";
  private static final String NORMAL_PRIORITY_KEY =
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/normal";
  private static final String LOW_PRIORITY_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/low";

  private static final String TODAY_EXPIRY_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/today";
  private static final String THIS_WEEK_EXPIRY_KEY =
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/thisWeek";
  private static final String THIS_MONTH_EXPIRY_KEY =
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/thisMonth";
  private static final String THIS_YEAR_EXPIRY_KEY =
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/thisYear";

  private static final String CREATED_CASE_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseState/created";
  private static final String RUNNING_CASE_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseState/running";
  private static final String DONE_CASE_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseState/done";
  private static final String FAILED_CASE_KEY = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseState/failed";

  private static final String EXPIRY_PERIOD_CMS = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/expiryPeriod";
  private static final String TASK_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/tasks";
  private static final String TASK_DATATIP_CMS = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/tasks";
  private static final String SECOND_DATATIP_CMS = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/second";

  private static final String NO_CATEGORY_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/filter/noCategory");

  private static final String USER_ID = "userId";
  private static final String NAME = "name";

  private static final String JSON_QUERY = "jsonQuery";
  private static final String RESULT = "result";

  private static final String JANUARY_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/monthsOfYear/January");
  private static final String FEBRUARY_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/monthsOfYear/February");
  private static final String MARCH_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/monthsOfYear/March");
  private static final String APRIL_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/monthsOfYear/April");
  private static final String MAY_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/monthsOfYear/May");
  private static final String JUNE_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/monthsOfYear/June");
  private static final String JULY_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/monthsOfYear/July");
  private static final String AUGUST_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/monthsOfYear/August");
  private static final String SEPTEMBER_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/monthsOfYear/September");
  private static final String OCTOBER_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/monthsOfYear/October");
  private static final String NOVEMBER_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/monthsOfYear/November");
  private static final String DECEMBER_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/monthsOfYear/December");

  private static final String FIRSTWEEK_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/weeksOfMonth/firstWeek");
  private static final String SECONDWEEK_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/weeksOfMonth/secondWeek");
  private static final String THIRDWEEK_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/weeksOfMonth/thirdWeek");
  private static final String FOURTHWEEK_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/weeksOfMonth/fourthWeek");

  private static final String MONDAY_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/daysOfWeek/monday");
  private static final String TUESDAY_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/daysOfWeek/tuesday");
  private static final String WEDNESDAY_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/daysOfWeek/wednesday");
  private static final String THURSDAY_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/daysOfWeek/thursday");
  private static final String FRIDAY_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/daysOfWeek/friday");
  private static final String SATURDAY_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/daysOfWeek/saturday");
  private static final String SUNDAY_CMS = Ivy.cms().co(
      "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/daysOfWeek/sunday");

  private static final String[] MONTHSOFYEAR = {JANUARY_CMS, FEBRUARY_CMS, MARCH_CMS, APRIL_CMS, MAY_CMS, JUNE_CMS,
      JULY_CMS, AUGUST_CMS, SEPTEMBER_CMS, OCTOBER_CMS, NOVEMBER_CMS, DECEMBER_CMS};
  private static final String[] WEEKSOFMONTH = {FIRSTWEEK_CMS, SECONDWEEK_CMS, THIRDWEEK_CMS, FOURTHWEEK_CMS};
  private static final String[] DAYSOFWEEK = {MONDAY_CMS, TUESDAY_CMS, WEDNESDAY_CMS, THURSDAY_CMS, FRIDAY_CMS,
      SATURDAY_CMS, SUNDAY_CMS};


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
      Filter<StatisticChart> statisticChartQuery = repo().search(getType()).numberField(USER_ID).isEqualTo(userId);
      result = statisticChartQuery.orderBy().textField(NAME).ascending().execute().getAll();
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
      Filter<StatisticChart> statisticChartQuery = repo().search(getType()).numberField(USER_ID).isEqualTo(userId);
      return statisticChartQuery.orderBy().textField(NAME).ascending().execute().count();
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
    Date createdDateFrom = filter.getCreatedDateFrom();
    Date createdDateTo = filter.getCreatedDateTo();
    if (createdDateFrom != null || createdDateTo != null) {
      TaskQuery subTaskQueryForCreatedDate = TaskQuery.create();
      IFilterQuery subTaskFilterForCreatedDate = subTaskQueryForCreatedDate.where();
      if (createdDateFrom != null) {
        subTaskFilterForCreatedDate.startTimestamp().isGreaterOrEqualThan(createdDateFrom);
      }
      if (createdDateTo != null) {
        subTaskFilterForCreatedDate.startTimestamp().isLowerOrEqualThan(createdDateTo);
      }
      taskQuery.where().and(subTaskQueryForCreatedDate);
    }

    // Filter by roles
    List<String> selectedRoles = Optional.ofNullable(filter.getSelectedRoles()).orElse(new ArrayList<>());
    if (!selectedRoles.isEmpty()) {
      TaskQuery subTaskQueryForRoles = TaskQuery.create();
      IFilterQuery subTaskFilterForRoles = subTaskQueryForRoles.where();

      selectedRoles.forEach(role -> subTaskFilterForRoles.or().activatorName().isEqual(role));
      taskQuery.where().and(subTaskQueryForRoles);
    }

    // Filter by task priority
    List<WorkflowPriority> selectedPriorities =
        Optional.ofNullable(filter.getSelectedTaskPriorities()).orElse(new ArrayList<>());
    if (!selectedPriorities.isEmpty()) {
      TaskQuery subTaskQueryForPriority = TaskQuery.create();
      IFilterQuery subTaskFilterForPriority = subTaskQueryForPriority.where();

      selectedPriorities.forEach(priority -> subTaskFilterForPriority.or().priority().isEqual(priority));
      taskQuery.where().and(subTaskQueryForPriority);
    }

    taskQuery.where().and().cases(generateCaseQuery(filter, false));
    return taskQuery;
  }

  /**
   * Generate task query for expiry statistic
   * 
   * @param filter statistic filter
   * @return generated task query
   */
  public TaskQuery generateTaskQueryForExpiry(StatisticFilter filter) {
    TaskQuery taskQuery = generateTaskQuery(filter);
    updateTaskQueryForExpiryDate(taskQuery);
    return taskQuery;
  }

  /**
   * Generate case query for statistic
   * 
   * @param filter statistic filter
   * @return generated case query
   */
  public CaseQuery generateCaseQuery(StatisticFilter filter, boolean forElapsedStatistic) {
    CaseQuery caseQuery = CaseQuery.create();

    // Filter by created date
    Date createdDateFrom = filter.getCreatedDateFrom();
    Date createdDateTo = filter.getCreatedDateTo();
    if (createdDateFrom != null || createdDateTo != null) {
      CaseQuery subCaseQueryForCreatedDate = CaseQuery.create();
      ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery subCaseFilterForCreatedDate =
          subCaseQueryForCreatedDate.where();
      if (createdDateFrom != null) {
        subCaseFilterForCreatedDate.startTimestamp().isGreaterOrEqualThan(createdDateFrom);
      }
      if (createdDateTo != null) {
        subCaseFilterForCreatedDate.startTimestamp().isLowerOrEqualThan(createdDateTo);
      }
      caseQuery.where().and(subCaseQueryForCreatedDate);
    }

    // Filter by case state
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

    // Filter by case category
    List<String> selectedCaseCategories =
        Optional.ofNullable(filter.getSelectedCaseCategories()).orElse(new ArrayList<>());
    CaseQuery subCaseQueryForSelectedCaseCategories = CaseQuery.create();
    ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery subCaseFilterForSelectedCaseCategories =
        subCaseQueryForSelectedCaseCategories.where();
    if (selectedCaseCategories.isEmpty()) {
      List<String> caseCategories = Optional.ofNullable(filter.getCaseCategories()).orElse(new ArrayList<>());
      caseCategories.forEach(category -> {
        if (StringUtils.equals(category, NO_CATEGORY_CMS)) {
          subCaseFilterForSelectedCaseCategories.and().category().isNotNull();
        } else {
          subCaseFilterForSelectedCaseCategories.and().category().isNotEqual(category);
        }
      });
    } else {
      selectedCaseCategories.forEach(category -> {
        if (StringUtils.equals(category, NO_CATEGORY_CMS)) {
          subCaseFilterForSelectedCaseCategories.or().category().isNull();
        } else {
          subCaseFilterForSelectedCaseCategories.or().category().isEqual(category);
        }
      });
    }
    caseQuery.where().and(subCaseQueryForSelectedCaseCategories);

    return caseQuery;
  }

  private TaskQuery updateTaskQueryForExpiryDate(TaskQuery taskQuery) {
    TaskQuery result = TaskQuery.fromJson(taskQuery.asJson());
    Date expiryFromDate = new Date();
    Date expiryToDate = new Date();
    Calendar calendar = Calendar.getInstance();

    calendar.setTime(expiryFromDate);
    calendar.add(Calendar.DAY_OF_YEAR, 7);
    expiryToDate = calendar.getTime();

    TaskQuery taskQueryForExpiryDate = TaskQuery.create();
    IFilterQuery filterQueryForExpiryDate = taskQueryForExpiryDate.where();
    filterQueryForExpiryDate.and().expiryTimestamp().isGreaterOrEqualThan(expiryFromDate).and().expiryTimestamp()
        .isLowerOrEqualThan(expiryToDate).and().expiryTimestamp().isNotNull();

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
    params.put(JSON_QUERY, jsonQuery);

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzePriorityStatistic(String)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (PriorityStatistic) response.get(RESULT);
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
    params.put(JSON_QUERY, jsonQuery);

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeExpiryStatistic(String)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (List<ExpiryStatistic>) response.get(RESULT);
  }

  /**
   * Call web service to get Case by State statistic data
   * 
   * @param jsonQuery
   * @return Case by State statistic data
   */
  public CaseStateStatistic getCaseStateStatisticData(String jsonQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put(JSON_QUERY, jsonQuery);

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeCaseStateStatistic(String)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (CaseStateStatistic) response.get(RESULT);
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
    params.put(JSON_QUERY, jsonQuery);

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeElapsedTimeStatistic(String)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (List<ElapsedTimeStatistic>) response.get(RESULT);
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
      chartData.put(Ivy.cms().co(EXCEPTION_PRIORITY_KEY), priorityStatistic.getException());
    }

    if (priorityStatistic.getHigh() > 0) {
      chartData.put(Ivy.cms().co(HIGH_PRIORITY_KEY), priorityStatistic.getHigh());
    }

    if (priorityStatistic.getNormal() > 0) {
      chartData.put(Ivy.cms().co(NORMAL_PRIORITY_KEY), priorityStatistic.getNormal());
    }

    if (priorityStatistic.getLow() > 0) {
      chartData.put(Ivy.cms().co(LOW_PRIORITY_KEY), priorityStatistic.getLow());
    }

    return chartData;
  }

  /**
   * generate data for "Task by Expiry" overview chart
   * 
   * @param expiryStatistic statistic data
   * @return generated data
   */
  public Map<Object, Number> generateDataForTaskByExpiryOverviewChart(List<ExpiryStatistic> expiryStatistic,
      String selectedValue) {

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
    if (StringUtils.containsIgnoreCase(selectedValue, "YEAR")) {
      chartData = generateExpiryModelForDrilldownLevelYear(statisticResultMap);
    } else if (selectMonthOfYear(selectedValue)) {
      chartData = generateExpiryModelForDrilldownLevelMonth(statisticResultMap);
    } else if (selectWeekOfMonth(selectedValue)) {
      chartData = generateExpiryModelForDrilldownLevelWeek(statisticResultMap);
    } else {
      chartData = generateDefaultExpiryModel(statisticResultMap);
    }

    return chartData;
  }

  private Map<Object, Number> generateExpiryModelForDrilldownLevelWeek(Map<Date, Long> statisticResultMap) {
    Map<Object, Number> chartData = new HashMap<Object, Number>();
    
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
      
      if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
        taskExpireOnMonday += result.getValue();
      }else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
        taskExpireOnTuesday += result.getValue();
      }else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
        taskExpireOnWednesday += result.getValue();
      }else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
        taskExpireOnThursday += result.getValue();
      }else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
        taskExpireOnFriday += result.getValue();
      }else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
        taskExpireOnSaturday += result.getValue();
      }else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
        taskExpireOnSunday += result.getValue();
      }
      
    }

    chartData.put(MONDAY_CMS, taskExpireOnMonday);
    chartData.put(TUESDAY_CMS, taskExpireOnTuesday);
    chartData.put(WEDNESDAY_CMS, taskExpireOnWednesday);
    chartData.put(THURSDAY_CMS, taskExpireOnThursday);
    chartData.put(FRIDAY_CMS, taskExpireOnFriday);
    chartData.put(SATURDAY_CMS, taskExpireOnSaturday);
    chartData.put(SUNDAY_CMS, taskExpireOnSunday);
    
    return chartData;
  }

  private Map<Object, Number> generateExpiryModelForDrilldownLevelMonth(Map<Date, Long> statisticResultMap) {
    Map<Object, Number> chartData = new HashMap<Object, Number>();
    chartData.put(FIRSTWEEK_CMS, 10);
    chartData.put(SECONDWEEK_CMS, 10);
    chartData.put(THIRDWEEK_CMS, 10);
    chartData.put(FOURTHWEEK_CMS, 10);
    return chartData;
  }

  private Map<Object, Number> generateExpiryModelForDrilldownLevelYear(Map<Date, Long> statisticResultMap) {
    Map<Object, Number> chartData = new HashMap<Object, Number>();
    
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
      
      if(cal.get(Calendar.MONTH) == Calendar.JANUARY){
        taskExpireOnJanuary += result.getValue();
      }else if(cal.get(Calendar.MONTH) == Calendar.FEBRUARY){
        taskExpireOnFebruary += result.getValue();
      }else if(cal.get(Calendar.MONTH) == Calendar.MARCH){
        taskExpireOnMarch += result.getValue();
      }else if(cal.get(Calendar.MONTH) == Calendar.APRIL){
        taskExpireOnApril += result.getValue();
      }else if(cal.get(Calendar.MONTH) == Calendar.MAY){
        taskExpireOnMay += result.getValue();
      }else if(cal.get(Calendar.MONTH) == Calendar.JUNE){
        taskExpireOnJune += result.getValue();
      }else if(cal.get(Calendar.MONTH) == Calendar.JULY){
        taskExpireOnJuly += result.getValue();
      }else if(cal.get(Calendar.MONTH) == Calendar.AUGUST){
        taskExpireOnAugust += result.getValue();
      }else if(cal.get(Calendar.MONTH) == Calendar.SEPTEMBER){
        taskExpireOnSeptember += result.getValue();
      }else if(cal.get(Calendar.MONTH) == Calendar.OCTOBER){
        taskExpireOnOctober += result.getValue();
      }else if(cal.get(Calendar.MONTH) == Calendar.NOVEMBER){
        taskExpireOnNovember += result.getValue();
      }else if(cal.get(Calendar.MONTH) == Calendar.DECEMBER){
        taskExpireOnDecember += result.getValue();
      }
    }
    
    chartData.put(JANUARY_CMS, taskExpireOnJanuary);
    chartData.put(FEBRUARY_CMS, taskExpireOnFebruary);
    chartData.put(MARCH_CMS, taskExpireOnMarch);
    chartData.put(APRIL_CMS, taskExpireOnApril);
    chartData.put(MAY_CMS, taskExpireOnMay);
    chartData.put(JUNE_CMS, taskExpireOnJune);
    chartData.put(JULY_CMS, taskExpireOnJuly);
    chartData.put(AUGUST_CMS, taskExpireOnAugust);
    chartData.put(SEPTEMBER_CMS, taskExpireOnSeptember);
    chartData.put(OCTOBER_CMS, taskExpireOnOctober);
    chartData.put(NOVEMBER_CMS, taskExpireOnNovember);
    chartData.put(DECEMBER_CMS, taskExpireOnDecember);
    return chartData;
  }

  private Map<Object, Number> generateDefaultExpiryModel(Map<Date, Long> statisticResultMap) {
    Map<Object, Number> chartData = new HashMap<Object, Number>();
    // Calculate result
    Long taskExpireToday = new Long(0L);
    Long taskExpireThisWeek = new Long(0L);
    Long taskExpireThisMonth = new Long(0L);
    Long taskExpireThisYear = new Long(0L);

    Date today = truncateMinutesPart(new Date());
    Date firstDateOfWeek = truncateMinutesPart(getFirstDateOfThisWeek());
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

    chartData.put(Ivy.cms().co(TODAY_EXPIRY_KEY), taskExpireToday);
    chartData.put(Ivy.cms().co(THIS_WEEK_EXPIRY_KEY), taskExpireThisWeek);
    chartData.put(Ivy.cms().co(THIS_MONTH_EXPIRY_KEY), taskExpireThisMonth);
    chartData.put(Ivy.cms().co(THIS_YEAR_EXPIRY_KEY), taskExpireThisYear);
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
      chartData.put(Ivy.cms().co(CREATED_CASE_KEY), caseStateStatistic.getCreated());
    }

    if (caseStateStatistic.getDone() > 0) {
      chartData.put(Ivy.cms().co(DONE_CASE_KEY), caseStateStatistic.getDone());
    }

    if (caseStateStatistic.getFailed() > 0) {
      chartData.put(Ivy.cms().co(FAILED_CASE_KEY), caseStateStatistic.getFailed());
    }

    if (caseStateStatistic.getRunning() > 0) {
      chartData.put(Ivy.cms().co(RUNNING_CASE_KEY), caseStateStatistic.getRunning());
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
        key = NO_CATEGORY_CMS;
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

    switch (chartType) {
      case TASK_BY_PRIORITY:
        statisticChart.setJsonQuery(generateTaskQuery(filter).asJson());
        break;
      case CASES_BY_STATE:
        statisticChart.setJsonQuery(generateCaseQuery(filter, false).asJson());
        break;
      case TASK_BY_EXPIRY:
        statisticChart.setJsonQuery(generateTaskQueryForExpiry(filter).asJson());
        break;
      case ELAPSED_TIME_BY_CASE_CATEGORY:
        statisticChart.setJsonQuery(generateCaseQuery(filter, true).asJson());
        break;
      default:
        break;
    }

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
   * @return chart model for "Task By Expiry" chart
   */
  public BarChartModel generateTaskByExpiryModel(List<ExpiryStatistic> statisticData, boolean isSetDefaultName,
      String selectedValue) {
    Map<Object, Number> chartData = generateDataForTaskByExpiryOverviewChart(statisticData, selectedValue);
    BarChartModel model = new BarChartModel();
    ChartSeries chartSeries = new ChartSeries();

    if (chartData.size() != 0) {

      chartSeries.setData(chartData);
      model.setExtender("barChartExtender");
      model.setShadow(false);

      Axis xAxis = model.getAxis(AxisType.X);
      xAxis.setLabel(Ivy.cms().co(EXPIRY_PERIOD_CMS));
      Axis yAxis = model.getAxis(AxisType.Y);
      yAxis.setLabel(Ivy.cms().co(TASK_CMS));

      String datatipFormat = StringUtils.join("%2$.0f ", Ivy.cms().co(TASK_DATATIP_CMS));
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
   * @param isSetDefaultName
   * @return chart model for "Case By State" chart
   */
  public DonutChartModel generateCaseByStateModel(CaseStateStatistic statisticData, boolean isSetDefaultName) {
    Map<String, Number> chartData = generateDataForCaseStateChart(statisticData);
    boolean isEmptyData = chartData.isEmpty();
    DonutChartModel model = new DonutChartModel();

    if (isEmptyData) {
      chartData.put(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/cases"), 0);
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
      model.setTitle(Ivy.cms().co(StatisticChartType.CASES_BY_STATE.getCmsUri()));
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

    model.setData(chartData);
    model.setLegendPosition("s");
    model.setShowDataLabels(true);
    model.setExtender("chartExtender");
    model.setShadow(false);
    model.setDataFormat("percent");
    model.setSliceMargin(3);
    model.setLegendCols(1);
    model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
    String datatipFormat = StringUtils.join("%2$.0f ", Ivy.cms().co(SECOND_DATATIP_CMS));
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

  /**
   * 
   * @param statisticChartList
   */
  public void generateChartModelForStatisticCharts(List<StatisticChart> statisticChartList) {
    for (StatisticChart statisticChart : statisticChartList) {
      switch (statisticChart.getType()) {
        case TASK_BY_PRIORITY:
          PriorityStatistic taskByPriorityData = getPriorityStatisticData(statisticChart.getJsonQuery());
          statisticChart.setDonutChartModel(generateTaskByPriorityModel(taskByPriorityData, true));
          break;
        case TASK_BY_EXPIRY:// TO BE REFACTOR
          List<ExpiryStatistic> taskByExpiryData = getExpiryStatisticData(statisticChart.getJsonQuery());
          statisticChart.setBarChartModel(generateTaskByExpiryModel(taskByExpiryData, true, StringUtils.EMPTY));
          break;
        case CASES_BY_STATE:
          CaseStateStatistic caseStateData = getCaseStateStatisticData(statisticChart.getJsonQuery());
          statisticChart.setDonutChartModel(generateCaseByStateModel(caseStateData, true));
          break;
        case ELAPSED_TIME_BY_CASE_CATEGORY:
          List<ElapsedTimeStatistic> elapsedTimeData = getElapsedTimeStatisticData(statisticChart.getJsonQuery());
          statisticChart.setPieChartModel(generateElapsedTimeModel(elapsedTimeData, true));
          break;
        default:
          break;
      }
    }
  }

  public boolean isDrilldownToTaskList(String expiryLastDrilldownLevel, String selectedItem) {
    // day
    if (StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, "DAY") && (selectDayOfWeek(selectedItem))) {
      return true;
    }
    // week
    if (StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, "WEEK")
        && (selectDayOfWeek(selectedItem) || selectWeekOfMonth(selectedItem))) {
      return true;
    }
    // month
    if (StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, "MONTH")
        && (selectDayOfWeek(selectedItem) || selectWeekOfMonth(selectedItem) || selectMonthOfYear(selectedItem))) {
      return true;
    }
    //year
    if(StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, "YEAR")){
      return true;
    }
    return false;
  }

  private boolean selectMonthOfYear(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    return Arrays.asList(MONTHSOFYEAR).contains(selectedItem)
        || StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(THIS_MONTH_EXPIRY_KEY));
  }

  public boolean selectWeekOfMonth(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    return Arrays.asList(WEEKSOFMONTH).contains(selectedItem)
        || StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(THIS_WEEK_EXPIRY_KEY));
  }

  private boolean selectDayOfWeek(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    return Arrays.asList(DAYSOFWEEK).contains(selectedItem)
        || StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(TODAY_EXPIRY_KEY));
  }

  public void drilldownExpiryChart(String selectedValue, StatisticChart selectedChart) {
    List<ExpiryStatistic> taskByExpiryData = getExpiryStatisticData(selectedChart.getJsonQuery());
    selectedChart.setBarChartModel(generateTaskByExpiryModel(taskByExpiryData, true, selectedValue));
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

  private Date getFirstDateOfThisWeek() {
    Calendar calendar = Calendar.getInstance();
    while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
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
    TaskQuery query = TaskQuery.fromJson(statisticChart.getJsonQuery());
    String selectedValue = getSelectedValueOfDonutChart(event);

    if (selectedValue.equals(Ivy.cms().co(EXCEPTION_PRIORITY_KEY))) {
      query.where().and().priority().isEqual(WorkflowPriority.EXCEPTION);
    } else if (selectedValue.equals(Ivy.cms().co(HIGH_PRIORITY_KEY))) {
      query.where().and().priority().isEqual(WorkflowPriority.HIGH);
    } else if (selectedValue.equals(Ivy.cms().co(NORMAL_PRIORITY_KEY))) {
      query.where().and().priority().isEqual(WorkflowPriority.NORMAL);
    } else if (selectedValue.equals(Ivy.cms().co(LOW_PRIORITY_KEY))) {
      query.where().and().priority().isEqual(WorkflowPriority.LOW);
    }

    return query;
  }

  /**
   * Get updated task query for Task by Expiry chart based on selected item
   * 
   * @param event
   * @param statisticChart
   * @return updated task query
   */
  public TaskQuery getQueryForSelectedItemOfTaskByExpiryChart(ItemSelectEvent event, StatisticChart statisticChart) {
    TaskQuery query = TaskQuery.fromJson(statisticChart.getJsonQuery());
    String selectedValue = getSelectedValueOfBarChart(event);

    Date today = truncateMinutesPart(new Date());
    if (selectedValue.equals(Ivy.cms().co(TODAY_EXPIRY_KEY))) {
      Date tomorrow = DateUtils.addDays(today, 1);
      query.where().and().expiryTimestamp().isGreaterOrEqualThan(today).and().expiryTimestamp().isLowerThan(tomorrow);
    } else if (selectedValue.equals(Ivy.cms().co(THIS_WEEK_EXPIRY_KEY))) {
      Date firstDateOfWeek = truncateMinutesPart(getFirstDateOfThisWeek());
      Date firstDateOfNextWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfWeek, 1));
      query.where().and().expiryTimestamp().isGreaterOrEqualThan(firstDateOfWeek).and().expiryTimestamp()
          .isLowerThan(firstDateOfNextWeek);
    } else if (selectedValue.equals(Ivy.cms().co(THIS_MONTH_EXPIRY_KEY))) {
      Date firstDateOfMonth = truncateMinutesPart(getFirstDateOfThisMonth());
      Date firsDateOfNextMonth = truncateMinutesPart(DateUtils.addMonths(firstDateOfMonth, 1));
      query.where().and().expiryTimestamp().isGreaterOrEqualThan(firstDateOfMonth).and().expiryTimestamp()
          .isLowerThan(firsDateOfNextMonth);
    }
    
    return query;
  }

  /**
   * Get updated task query for Case by State chart based on selected item
   * 
   * @param event
   * @param statisticChart
   * @return
   */
  public CaseQuery getQueryForSelectedItemByCaseByState(ItemSelectEvent event, StatisticChart statisticChart) {
    CaseQuery query = CaseQuery.fromJson(statisticChart.getJsonQuery());
    String selectedValue = getSelectedValueOfDonutChart(event);

    if (selectedValue.equals(Ivy.cms().co(CREATED_CASE_KEY))) {
      query.where().state().isEqual(CaseState.CREATED);
    } else if (selectedValue.equals(Ivy.cms().co(RUNNING_CASE_KEY))) {
      query.where().state().isEqual(CaseState.RUNNING);
    } else if (selectedValue.equals(Ivy.cms().co(DONE_CASE_KEY))) {
      query.where().state().isEqual(CaseState.DONE);
    } else if (selectedValue.equals(Ivy.cms().co(FAILED_CASE_KEY))) {
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
