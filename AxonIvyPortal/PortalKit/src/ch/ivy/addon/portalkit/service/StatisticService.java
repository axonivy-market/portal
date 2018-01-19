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
  private static final String EXCEPTION_PRIORITY_KEY
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/exception";
  private static final String HIGH_PRIORITY_KEY
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/high";
  private static final String NORMAL_PRIORITY_KEY
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/normal";
  private static final String LOW_PRIORITY_KEY
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByPriority/low";

  private static final String TODAY_EXPIRY_KEY
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/today";
  private static final String TOMORROW_EXPIRY_KEY
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/tomorrow";
  private static final String THIS_WEEK_EXPIRY_KEY
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/thisWeek";
  private static final String THIS_MONTH_EXPIRY_KEY
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/thisMonth";

  private static final String CREATED_CASE_KEY
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseState/created";
  private static final String RUNNING_CASE_KEY
  = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseState/running";
  private static final String DONE_CASE_KEY
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseState/done";
  private static final String FAILED_CASE_KEY
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseState/failed";

  private static final String EXPIRY_PERIOD_CMS
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/expiryPeriod";
  private static final String TASK_CMS
    = "/ch.ivy.addon.portalkit.ui.jsf/common/tasks";
  private static final String TASK_DATATIP_CMS
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/tasks";
  private static final String SECOND_DATATIP_CMS
    = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/second";

  private static final String NO_CATEGORY_CMS
    = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/filter/noCategory");

  private static final String USER_ID = "userId";
  private static final String NAME = "name";

  private static final String JSON_QUERY = "jsonQuery";
  private static final String RESULT = "result";

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
      Filter<StatisticChart> statisticChartQuery
        = repo().search(getType()).numberField(USER_ID).isEqualTo(userId);
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
      Filter<StatisticChart> statisticChartQuery
      = repo().search(getType()).numberField(USER_ID).isEqualTo(userId);
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

      selectedRoles.forEach(role ->
        subTaskFilterForRoles.or().activatorName().isEqual(role));
      taskQuery.where().and(subTaskQueryForRoles);
    }

    // Filter by task priority
    List<WorkflowPriority> selectedPriorities = Optional.ofNullable(filter.getSelectedTaskPriorities()).orElse(new ArrayList<>());
    if (!selectedPriorities.isEmpty()) {
      TaskQuery subTaskQueryForPriority = TaskQuery.create();
      IFilterQuery subTaskFilterForPriority = subTaskQueryForPriority.where();

      selectedPriorities.forEach(priority ->
        subTaskFilterForPriority.or().priority().isEqual(priority));
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
      ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery subCaseFilterForCreatedDate = subCaseQueryForCreatedDate.where();
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
    ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery subCaseFilterForSelectedCaseStates = subCaseQueryForSelectedCaseStates.where();

    if (selectedCaseStates.isEmpty()) {
      List<CaseState> caseStates = Arrays.asList(CaseState.values());
      caseStates.forEach(caseState ->
        subCaseFilterForSelectedCaseStates.and().state().isNotEqual(caseState));
    } else if (forElapsedStatistic) {
      subCaseFilterForSelectedCaseStates.or().state().isEqual(CaseState.DONE);
    } else {
      selectedCaseStates.forEach(caseState ->
      subCaseFilterForSelectedCaseStates.or().state().isEqual(caseState));
    }
    caseQuery.where().and(subCaseQueryForSelectedCaseStates);

    // Filter by case category
    List<String> selectedCaseCategories = Optional.ofNullable(filter.getSelectedCaseCategories()).orElse(new ArrayList<>());
    CaseQuery subCaseQueryForSelectedCaseCategories = CaseQuery.create();
    ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery subCaseFilterForSelectedCaseCategories = subCaseQueryForSelectedCaseCategories.where();
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
    filterQueryForExpiryDate.and().expiryTimestamp().isGreaterOrEqualThan(expiryFromDate)
      .and().expiryTimestamp().isLowerOrEqualThan(expiryToDate)
      .and().expiryTimestamp().isNotNull();

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

    Map<String, Object> response = IvyAdapterService.startSubProcess("analyzePriorityStatistic(String)", params,
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

    Map<String, Object> response = IvyAdapterService.startSubProcess("analyzeExpiryStatistic(String)", params,
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

    Map<String, Object> response = IvyAdapterService.startSubProcess("analyzeCaseStateStatistic(String)", params,
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

    Map<String, Object> response = IvyAdapterService.startSubProcess("analyzeElapsedTimeStatistic(String)", params,
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
  public Map<Object, Number> generateDataForTaskByExpiryOverviewChart(List<ExpiryStatistic> expiryStatistic) {
    Map<Object, Number> chartData = new LinkedHashMap<Object, Number>();

    //Convert result
    Map<Date, Long> statisticResultMap = new HashMap<Date, Long>();
    Gson gsonConverter = new Gson();
    Type type = new TypeToken<Map<String, String>>(){}.getType();

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

    //Calculate result
    Long taskExpireToday = new Long(0L);
    Long taskExpireTomorrow = new Long(0L);
    Long taskExpireThisWeek = new Long(0L);
    Long taskExpireThisMonth = new Long(0L);

    Date today = truncateMinutesPart(new Date());
    Date firstDateOfWeek = truncateMinutesPart(getFirstDateOfThisWeek());
    Date firstDateOfNextWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfWeek, 1));
    Date firstDateOfMonth = truncateMinutesPart(getFirstDateOfThisMonth());
    Date firsDateOfNextMonth = truncateMinutesPart(DateUtils.addMonths(firstDateOfMonth, 1));

    for (Entry<Date, Long> result : statisticResultMap.entrySet()) {
      Date resultDate = truncateMinutesPart(result.getKey());

      if (today.compareTo(resultDate) == 0) {
        taskExpireToday += result.getValue();
      } else if (DateUtils.addDays(today, 1).compareTo(resultDate) == 0) {
        taskExpireTomorrow += result.getValue();
      }

      if (firstDateOfWeek.compareTo(resultDate) <= 0 && firstDateOfNextWeek.compareTo(resultDate) > 0) {
        taskExpireThisWeek += result.getValue();
      }
      if (firstDateOfMonth.compareTo(resultDate) <= 0 && firsDateOfNextMonth.compareTo(resultDate) > 0) {
        taskExpireThisMonth += result.getValue();
      }
    }

    chartData.put(Ivy.cms().co(TODAY_EXPIRY_KEY), taskExpireToday);
    chartData.put(Ivy.cms().co(TOMORROW_EXPIRY_KEY), taskExpireTomorrow);
    chartData.put(Ivy.cms().co(THIS_WEEK_EXPIRY_KEY), taskExpireThisWeek);
    chartData.put(Ivy.cms().co(THIS_MONTH_EXPIRY_KEY), taskExpireThisMonth);

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
    Type type = new TypeToken<Map<String, String>>(){}.getType();

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

    for(Entry<String, Number> entry : chartDataTemp.entrySet()) {
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
  public StatisticChart createStatisticChart(StatisticFilter filter, String chartName, StatisticChartType chartType, long creatorId) {
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
    DonutChartModel model = new DonutChartModel();

    if (chartData.size() == 0) {
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
    model.setSeriesColors(Colors.getPriorityColors(chartData));
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
  public BarChartModel generateTaskByExpiryModel(List<ExpiryStatistic> statisticData, boolean isSetDefaultName) {
    Map<Object, Number> chartData = generateDataForTaskByExpiryOverviewChart(statisticData);
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
    DonutChartModel model = new DonutChartModel();

    if (chartData.size() == 0) {
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
    model.setSeriesColors(Colors.getCaseStateColors(chartData));
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
      case TASK_BY_EXPIRY:
        List<ExpiryStatistic> taskByExpiryData = getExpiryStatisticData(statisticChart.getJsonQuery());
        statisticChart.setBarChartModel(generateTaskByExpiryModel(taskByExpiryData, true));
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

  private String getSelectedValueOfDonutChart(ItemSelectEvent event) {
    try {
      DonutChartModel model = (DonutChartModel)((Chart)event.getSource()).getModel();
      int index= event.getItemIndex();
      return model.getData().get(0).keySet().toArray()[index].toString();
    } catch (Exception e) {
      return "";
    }
    
  }

  private String getSelectedValueOfBarChart(ItemSelectEvent event) {
    try {
      BarChartModel model = (BarChartModel)((Chart)event.getSource()).getModel();
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
    while(calendar.get(Calendar.DAY_OF_MONTH) > 1) {
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
    } else if (selectedValue.equals(Ivy.cms().co(TOMORROW_EXPIRY_KEY))) {
      Date dateAfterTomorrow = DateUtils.addDays(today, 2);
      Date tomorrow = DateUtils.addDays(today, 1);
      query.where().and().expiryTimestamp().isGreaterOrEqualThan(tomorrow).and().expiryTimestamp().isLowerThan(dateAfterTomorrow);
    } else if (selectedValue.equals(Ivy.cms().co(THIS_WEEK_EXPIRY_KEY))) {
      Date firstDateOfWeek = truncateMinutesPart(getFirstDateOfThisWeek());
      Date firstDateOfNextWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfWeek, 1));
      query.where().and().expiryTimestamp().isGreaterOrEqualThan(firstDateOfWeek).and().expiryTimestamp().isLowerThan(firstDateOfNextWeek);
    } else if (selectedValue.equals(Ivy.cms().co(THIS_MONTH_EXPIRY_KEY))) {
      Date firstDateOfMonth = truncateMinutesPart(getFirstDateOfThisMonth());
      Date firsDateOfNextMonth = truncateMinutesPart(DateUtils.addMonths(firstDateOfMonth, 1));
      query.where().and().expiryTimestamp().isGreaterOrEqualThan(firstDateOfMonth).and().expiryTimestamp().isLowerThan(firsDateOfNextMonth);
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
    return statisticChartList.stream()
        .filter(chart -> StringUtils.equals(chart.getName(), chartName))
        .findFirst().isPresent();
  }

  private Date truncateMinutesPart(Date date) {
    return DateUtils.truncate(date, Calendar.DATE);
  }
}