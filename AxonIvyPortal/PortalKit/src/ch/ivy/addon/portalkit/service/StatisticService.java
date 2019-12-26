package ch.ivy.addon.portalkit.service;

import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.AFTER_18;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.APRIL_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.AUGUST_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.BEFORE_8;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.CREATED_CASE_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.DECEMBER_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.DONE_CASE_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.DRILLDOWN_LEVEL_DAY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.DRILLDOWN_LEVEL_HOUR;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.DRILLDOWN_LEVEL_MONTH;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.DRILLDOWN_LEVEL_WEEK;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.DRILLDOWN_LEVEL_YEAR;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.EXCEPTION_PRIORITY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.EXPIRY_PERIOD_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.FAILED_CASE_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.FEBRUARY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.FIFTHWEEK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.FIRSTWEEK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.FOURTHWEEK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.FRIDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.HIGH_PRIORITY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.IN_10;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.IN_11;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.IN_12;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.IN_13;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.IN_14;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.IN_15;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.IN_16;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.IN_17;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.IN_8;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.IN_9;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.JANUARY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.JSON_QUERY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.JULY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.JUNE_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.LOW_PRIORITY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.MARCH_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.MAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.MONDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.NAME;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.NORMAL_PRIORITY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.NOVEMBER_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.NO_CATEGORY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.OCTOBER_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.RESULT;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.RUNNING_CASE_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.SATURDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.SECONDWEEK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.SEPTEMBER_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.SIXTHWEEK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.SUNDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.TASK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.TASK_DATATIP_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THIRDWEEK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THIS_MONTH_EXPIRY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THIS_WEEK_EXPIRY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THIS_YEAR_EXPIRY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THURSDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.TODAY_EXPIRY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.TUESDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.USER_ID;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.WEDNESDAY_CMS;

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

import org.apache.commons.lang3.ObjectUtils;
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
import ch.ivy.addon.portalkit.statistics.StatisticChartQueryUtils;
import ch.ivy.addon.portalkit.statistics.StatisticChartTimeUtils;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivy.ws.addon.CaseStateStatistic;
import ch.ivy.ws.addon.ElapsedTimeStatistic;
import ch.ivy.ws.addon.ExpiryStatistic;
import ch.ivy.ws.addon.PriorityStatistic;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;
import ch.ivyteam.ivy.environment.Ivy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class StatisticService extends BusinessDataService<StatisticChart> {

  private static final String[] DRILLDOWN_LEVELS = {DRILLDOWN_LEVEL_YEAR, DRILLDOWN_LEVEL_MONTH, DRILLDOWN_LEVEL_WEEK, DRILLDOWN_LEVEL_DAY, DRILLDOWN_LEVEL_HOUR};

  @Override
  public Class<StatisticChart> getType() {
    return StatisticChart.class;
  }

  @Override
  public BusinessDataInfo<StatisticChart> save(StatisticChart statisticChart) {
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
      Result<StatisticChart> queryResult = statisticChartQuery.orderBy().textField(NAME).ascending().execute();
      long totalCount = queryResult.totalCount();
      if (totalCount > LIMIT_10) {
        queryResult =
            statisticChartQuery.orderBy().textField(NAME).ascending().limit(Math.toIntExact(totalCount)).execute();
      }
      result =
          queryResult.getAll().stream().sorted(Comparator.comparing(StatisticChart::getPosition))
              .collect(Collectors.toList());
      return result;
    } catch (Exception e) {
      Ivy.log().error(e);
      return result;
    }
  }

  /**
   * Find all statistic charts
   * 
   * @return all statistic charts
   */
  public List<StatisticChart> findAllStatisticCharts() {
    List<StatisticChart> result = new ArrayList<>();
    try {
      result = repo().search(getType()).execute().getAll();
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return result;
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
      return statisticChartQuery.orderBy().textField(NAME).ascending().execute().totalCount();
    } catch (Exception e) {
      Ivy.log().error(e);
      return -1;
    }
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
   * Call web service to get Elapsed time of related tasks by Case Category
   * 
   * @param jsonQuery
   * @return Elapsed time by Case Category data
   */
  @SuppressWarnings("unchecked")
  public List<ElapsedTimeStatistic> getElapsedTimeOfTasksStatisticData(String jsonQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put(JSON_QUERY, jsonQuery);

    Map<String, Object> response = IvyAdapterService.startSubProcess("analyzeElapsedTimeOfTasks(String)", params,
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
    Map<String, Number> chartData = new LinkedHashMap<>();

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

    Map<Object, Number> chartData;
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
    Map<Object, Number> chartData = new LinkedHashMap<>();
    
    Long taskExpireBefore8 = 0L;
    Long taskExpireIn8 = 0L;
    Long taskExpireIn9 = 0L;
    Long taskExpireIn10 = 0L;
    Long taskExpireIn11 = 0L;
    Long taskExpireIn12 = 0L;
    Long taskExpireIn13 = 0L;
    Long taskExpireIn14 = 0L;
    Long taskExpireIn15 = 0L;
    Long taskExpireIn16 = 0L;
    Long taskExpireIn17 = 0L;
    Long taskExpireAfter18 = 0L;
    Long[] taskExpireInHour =
        {taskExpireIn8, taskExpireIn9, taskExpireIn10, taskExpireIn11, taskExpireIn12, taskExpireIn13, taskExpireIn14,
            taskExpireIn15, taskExpireIn16, taskExpireIn17};
    
    for (Entry<Date, Long> result : statisticResultMap.entrySet()) {
      Date resultDate = result.getKey();
      Calendar cal = Calendar.getInstance();
      cal.setTime(resultDate);
      
      if (StatisticChartTimeUtils.isSameDay(resultDate, selectedDay, previousSelectedWeek, previousSelectedMonth)) {
        if(cal.get(Calendar.HOUR_OF_DAY) < 8) {
          taskExpireBefore8 += result.getValue();
        } else if(cal.get(Calendar.HOUR_OF_DAY) >= 18) {
          taskExpireAfter18 += result.getValue();
        } else {
          int hourIndex = cal.get(Calendar.HOUR_OF_DAY);
          taskExpireInHour[hourIndex - 8] += result.getValue(); //example: hourIndex = 8 => taskExpireInHour[ 8 - 8] = taskExpireInHour[0] = taskExpireIn8 += result.getValue() ...
        }
      }
      
    }

    chartData.put(BEFORE_8, taskExpireBefore8);
    chartData.put(IN_8, taskExpireInHour[0]);
    chartData.put(IN_9, taskExpireInHour[1]);
    chartData.put(IN_10, taskExpireInHour[2]);
    chartData.put(IN_11, taskExpireInHour[3]);
    chartData.put(IN_12, taskExpireInHour[4]);
    chartData.put(IN_13, taskExpireInHour[5]);
    chartData.put(IN_14, taskExpireInHour[6]);
    chartData.put(IN_15, taskExpireInHour[7]);
    chartData.put(IN_16, taskExpireInHour[8]);
    chartData.put(IN_17, taskExpireInHour[9]);
    chartData.put(AFTER_18, taskExpireAfter18);
    
    return chartData;
  }

  private Map<Object, Number> generateExpiryModelForDrilldownLevelWeek(Map<Date, Long> statisticResultMap,
      String selectedWeek, String previousSelectedMonth) {
    Map<Object, Number> chartData = new LinkedHashMap<>();

    Date firstDateOfSelectedWeek = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfWeek(selectedWeek, previousSelectedMonth));
    Date firstDateOfNextWeek = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addWeeks(firstDateOfSelectedWeek, 1));
    
    if (!StringUtils.isEmpty(previousSelectedMonth)) {
      Date firstDateOfMonth = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfMonth(previousSelectedMonth));
      if (firstDateOfMonth.compareTo(firstDateOfSelectedWeek) > 0) {
        firstDateOfSelectedWeek = firstDateOfMonth;
      }
      Date firstDayOfNextMonth = DateUtils.addMonths(firstDateOfMonth, 1);
      if (firstDateOfNextWeek.compareTo(firstDayOfNextMonth) > 0) {
        firstDateOfNextWeek = firstDayOfNextMonth;
      }
    }

    Long taskExpireOnMonday = 0L;
    Long taskExpireOnTuesday = 0L;
    Long taskExpireOnWednesday = 0L;
    Long taskExpireOnThursday = 0L;
    Long taskExpireOnFriday = 0L;
    Long taskExpireOnSaturday = 0L;
    Long taskExpireOnSunday = 0L;
    Long[] taskExpireOnDaysOfWeek =
        {taskExpireOnMonday, taskExpireOnTuesday, taskExpireOnWednesday, taskExpireOnThursday, taskExpireOnFriday,
            taskExpireOnSaturday, taskExpireOnSunday};

    for (Entry<Date, Long> result : statisticResultMap.entrySet()) {
      Date resultDate = StatisticChartTimeUtils.truncateMinutesPart(result.getKey());
      Calendar cal = Calendar.getInstance();
      cal.setTime(resultDate);

      if (checkIfDateBetweenRange(firstDateOfSelectedWeek, firstDateOfNextWeek, resultDate)) {
        int dayOfWeekIndex = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeekIndex == 1) { //SUNDAY
          taskExpireOnDaysOfWeek[6] += result.getValue(); //index 6 means taskExpireOnSunday
        } else {// MONDAY TO SATURDAY - day of week index from 2 to 7
          taskExpireOnDaysOfWeek[dayOfWeekIndex - 2] += result.getValue(); 
        }
      }
    }

    chartData.put(Ivy.cms().co(MONDAY_CMS), taskExpireOnDaysOfWeek[0]);
    chartData.put(Ivy.cms().co(TUESDAY_CMS), taskExpireOnDaysOfWeek[1]);
    chartData.put(Ivy.cms().co(WEDNESDAY_CMS), taskExpireOnDaysOfWeek[2]);
    chartData.put(Ivy.cms().co(THURSDAY_CMS), taskExpireOnDaysOfWeek[3]);
    chartData.put(Ivy.cms().co(FRIDAY_CMS), taskExpireOnDaysOfWeek[4]);
    chartData.put(Ivy.cms().co(SATURDAY_CMS), taskExpireOnDaysOfWeek[5]);
    chartData.put(Ivy.cms().co(SUNDAY_CMS), taskExpireOnDaysOfWeek[6]);

    return chartData;
  }

  private Map<Object, Number> generateExpiryModelForDrilldownLevelMonth(Map<Date, Long> statisticResultMap,
      String selectedValue) {
    Map<Object, Number> chartData = new LinkedHashMap<>();

    Long taskExpireOnFirstWeek = 0L;
    Long taskExpireOnSecondWeek = 0L;
    Long taskExpireOnThirdWeek = 0L;
    Long taskExpireOnFourthWeek = 0L;
    Long taskExpireOnFifthWeek = 0L;
    Long taskExpireOnSixthWeek = 0L;

    Date firstDateOfSelectedMonth = StatisticChartTimeUtils.getFirstDateOfMonth(selectedValue);
    Date firstDateOfWeek = StatisticChartTimeUtils.getFirstDateOfWeekContainsDate(firstDateOfSelectedMonth);

    Date firstDateOfFirstWeek = StatisticChartTimeUtils.truncateMinutesPart(firstDateOfSelectedMonth);
    Date firstDateOfSecondWeek = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addDays(firstDateOfWeek, 7));
    Date firstDateOfThirdWeek = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addDays(firstDateOfWeek, 14));
    Date firstDateOfFourthWeek = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addDays(firstDateOfWeek, 21));
    Date firstDateOfFifthWeek = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addDays(firstDateOfWeek, 28));
    Date firstDateOfSixthWeek = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addDays(firstDateOfWeek, 35));
    Date firstDateOfNextMonth = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addMonths(firstDateOfSelectedMonth, 1));

    for (Entry<Date, Long> result : statisticResultMap.entrySet()) {
      Date resultDate = StatisticChartTimeUtils.truncateMinutesPart(result.getKey());
      if (checkIfDateBetweenRange(firstDateOfFirstWeek, firstDateOfSecondWeek, resultDate)) {
        taskExpireOnFirstWeek += result.getValue();
      }
      if (checkIfDateBetweenRange(firstDateOfSecondWeek, firstDateOfThirdWeek, resultDate)) {
        taskExpireOnSecondWeek += result.getValue();
      }
      if (checkIfDateBetweenRange(firstDateOfThirdWeek, firstDateOfFourthWeek, resultDate)) {
        taskExpireOnThirdWeek += result.getValue();
      }
      if (checkIfDateBetweenRange(firstDateOfFourthWeek, firstDateOfFifthWeek, resultDate)) {
        taskExpireOnFourthWeek += result.getValue();
      }
      Long[] taskOfFifthAndSixWeek = calculateTaskExpireOnFifthAndSixthWeek(firstDateOfFifthWeek, firstDateOfSixthWeek, firstDateOfNextMonth, resultDate, result.getValue());
      taskExpireOnFifthWeek += taskOfFifthAndSixWeek[0];
      taskExpireOnSixthWeek += taskOfFifthAndSixWeek[1];
    }

    chartData.put(Ivy.cms().co(FIRSTWEEK_CMS), taskExpireOnFirstWeek);
    chartData.put(Ivy.cms().co(SECONDWEEK_CMS), taskExpireOnSecondWeek);
    chartData.put(Ivy.cms().co(THIRDWEEK_CMS), taskExpireOnThirdWeek);
    chartData.put(Ivy.cms().co(FOURTHWEEK_CMS), taskExpireOnFourthWeek);
    if (firstDateOfNextMonth.compareTo(firstDateOfFifthWeek) > 0) {
      chartData.put(Ivy.cms().co(FIFTHWEEK_CMS), taskExpireOnFifthWeek);
    }
    if (firstDateOfNextMonth.compareTo(firstDateOfSixthWeek) > 0) {
      chartData.put(Ivy.cms().co(SIXTHWEEK_CMS), taskExpireOnSixthWeek);
    }
    return chartData;
  }

  private Long[] calculateTaskExpireOnFifthAndSixthWeek(Date firstDateOfFifthWeek, Date firstDateOfSixthWeek,
      Date firstDateOfNextMonth, Date resultDate, Long resultValue) {
    Long taskExpireOnFifthWeek = 0L;
    Long taskExpireOnSixthWeek = 0L;
    if (firstDateOfSixthWeek.compareTo(firstDateOfNextMonth) < 0) {
      if (checkIfDateBetweenRange(firstDateOfFifthWeek, firstDateOfSixthWeek, resultDate)) {
        taskExpireOnFifthWeek = resultValue;
      }
      if (checkIfDateBetweenRange(firstDateOfSixthWeek, firstDateOfNextMonth, resultDate)) {
        taskExpireOnSixthWeek = resultValue;
      }
    } else {
      if (checkIfDateBetweenRange(firstDateOfFifthWeek, firstDateOfNextMonth, resultDate)) {
        taskExpireOnFifthWeek = resultValue;
      }
    }
    Long[] taskForFifthAndSixthWeek = {taskExpireOnFifthWeek, taskExpireOnSixthWeek};
    return taskForFifthAndSixthWeek;
  }

  private boolean checkIfDateBetweenRange(Date startDate, Date endDate, Date resultDate) {
    return startDate.compareTo(resultDate) <= 0 && endDate.compareTo(resultDate) > 0;
  }

  private Map<Object, Number> generateExpiryModelForDrilldownLevelYear(Map<Date, Long> statisticResultMap) {
    Map<Object, Number> chartData = new LinkedHashMap<>();
    
    Long taskExpireOnJanuary = 0L;
    Long taskExpireOnFebruary = 0L;
    Long taskExpireOnMarch = 0L;
    Long taskExpireOnApril = 0L;
    Long taskExpireOnMay = 0L;
    Long taskExpireOnJune = 0L;
    Long taskExpireOnJuly = 0L;
    Long taskExpireOnAugust = 0L;
    Long taskExpireOnSeptember = 0L;
    Long taskExpireOnOctober = 0L;
    Long taskExpireOnNovember = 0L;
    Long taskExpireOnDecember = 0L;
    Long[] takExpireOnMonths =
        {taskExpireOnJanuary, taskExpireOnFebruary, taskExpireOnMarch, taskExpireOnApril, taskExpireOnMay,
            taskExpireOnJune, taskExpireOnJuly, taskExpireOnAugust, taskExpireOnSeptember, taskExpireOnOctober,
            taskExpireOnNovember, taskExpireOnDecember};
    
    for (Entry<Date, Long> result : statisticResultMap.entrySet()) {
      Date resultDate = StatisticChartTimeUtils.truncateMinutesPart(result.getKey());
      Calendar cal = Calendar.getInstance();
      cal.setTime(resultDate);
      
      int thisYear = Year.now().getValue();
      if (cal.get(Calendar.YEAR) == thisYear) {
        int monthIndex = cal.get(Calendar.MONTH); // from JANUARY to DECEMBER, month index from 0 to 11  
        takExpireOnMonths[monthIndex] += result.getValue();
      }
    }
    
    chartData.put(Ivy.cms().co(JANUARY_CMS), takExpireOnMonths[0]);
    chartData.put(Ivy.cms().co(FEBRUARY_CMS), takExpireOnMonths[1]);
    chartData.put(Ivy.cms().co(MARCH_CMS), takExpireOnMonths[2]);
    chartData.put(Ivy.cms().co(APRIL_CMS), takExpireOnMonths[3]);
    chartData.put(Ivy.cms().co(MAY_CMS), takExpireOnMonths[4]);
    chartData.put(Ivy.cms().co(JUNE_CMS), takExpireOnMonths[5]);
    chartData.put(Ivy.cms().co(JULY_CMS), takExpireOnMonths[6]);
    chartData.put(Ivy.cms().co(AUGUST_CMS), takExpireOnMonths[7]);
    chartData.put(Ivy.cms().co(SEPTEMBER_CMS), takExpireOnMonths[8]);
    chartData.put(Ivy.cms().co(OCTOBER_CMS), takExpireOnMonths[9]);
    chartData.put(Ivy.cms().co(NOVEMBER_CMS), takExpireOnMonths[10]);
    chartData.put(Ivy.cms().co(DECEMBER_CMS), takExpireOnMonths[11]);
    return chartData;
  }

  private Map<Object, Number> generateDefaultExpiryModel(Map<Date, Long> statisticResultMap) {
    Map<Object, Number> chartData = new LinkedHashMap<>();
    // Calculate result
    Long taskExpireToday = 0L;
    Long taskExpireThisWeek = 0L;
    Long taskExpireThisMonth = 0L;
    Long taskExpireThisYear = 0L;

    Date today = StatisticChartTimeUtils.truncateMinutesPart(new Date());
    Date firstDateOfWeek = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfWeekContainsDate(new Date()));
    Date firstDateOfNextWeek = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addWeeks(firstDateOfWeek, 1));
    Date firstDateOfMonth = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfThisMonth());
    Date firsDateOfNextMonth = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addMonths(firstDateOfMonth, 1));
    Date firsDateOfYear = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfThisYear());
    Date firsDateOfNextYear = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addYears(firsDateOfYear, 1));

    for (Entry<Date, Long> result : statisticResultMap.entrySet()) {
      Date resultDate = StatisticChartTimeUtils.truncateMinutesPart(result.getKey());

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
        Map<String, String> resultAsString = new HashMap<>();
        resultAsString.putAll(gsonConverter.fromJson(statistic.getResult(), type));
        resultAsString.entrySet().forEach(result -> {
          Number elapsedTime = NumberUtils.createNumber(result.getValue());
          chartDataTemp.put(result.getKey(), elapsedTime);
        });
      }
    });

    for (Entry<String, Number> entry : chartDataTemp.entrySet()) {
      String key = entry.getKey();
      if (StringUtils.isBlank(key)) {
        key = Ivy.cms().co(NO_CATEGORY_CMS);
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
    if (filter.getIsAllCaseStatesSelected() || filter.getIsAllCategoriesSelected()) {
      StatisticFilter newFilter = ObjectUtils.clone(filter);
      if (filter.getIsAllCategoriesSelected()) {
        newFilter.setSelectedCaseCategories(new ArrayList<>());
      }
      if (filter.getIsAllRolesSelected()) {
        newFilter.setSelectedRoles(new ArrayList<>());
      }
      statisticChart.setFilter(newFilter);
    } else {
      statisticChart.setFilter(filter);
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
      String label = Ivy.cms().co(EXPIRY_PERIOD_CMS);
      if (selectDayOfWeek(selectedValue)) {
        label = label + " " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/hour");
      }
      xAxis.setLabel(label);

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
  public DonutChartModel generateElapsedTimeModel(List<ElapsedTimeStatistic> statisticData, boolean isSetDefaultName) {
    Map<String, Number> chartData = generateDataForElapsedTimeChart(statisticData);
    DonutChartModel model = new DonutChartModel();

    if (chartData.size() == 0) {
      chartData.put(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseCategory"), 0);
    }

    float totalValue = 0f;
    float otherValue = 0f;

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

    
    if (Float.compare(otherValue, 0f) != 0) {
      chartData.put(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/other"), otherValue);
    }

    model.addCircle(chartData);
    model.setLegendPosition("s");
    model.setShowDataLabels(true);
    model.setExtender("elapsedTimeChartExtender");
    model.setShadow(false);
    model.setDataFormat("percent");
    model.setSliceMargin(3);
    model.setLegendCols(1);
    model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
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
            taskByPriorityData = getPriorityStatisticData(StatisticChartQueryUtils.generateTaskQuery(statisticChart.getFilter()).asJson());
          }
          statisticChart.setDonutChartModel(generateTaskByPriorityModel(taskByPriorityData, true));
          break;
        case TASK_BY_EXPIRY:
          generateChartModelForTaskByExpiryChart(statisticChart);
          break;
        case CASES_BY_STATE:
          CaseStateStatistic caseStateData = new CaseStateStatistic();
          if(statisticChart.getFilter() != null) {
            caseStateData = getCaseStateStatisticData(StatisticChartQueryUtils.generateCaseQueryForCaseChart(statisticChart.getFilter(), false).asJson());
          }
          statisticChart.setDonutChartModel(generateCaseByStateModel(caseStateData, StatisticChartType.CASES_BY_STATE,  true));
          break;
        case ELAPSED_TIME_BY_CASE_CATEGORY:
          List<ElapsedTimeStatistic> elapsedTimeData = new ArrayList<>();
          if(statisticChart.getFilter() != null) {
            elapsedTimeData = getElapsedTimeStatisticData(StatisticChartQueryUtils.generateCaseQueryForCaseChart(statisticChart.getFilter(), true).asJson());
          }
          statisticChart.setDonutChartModel(generateElapsedTimeModel(elapsedTimeData, true));
          break;
        case CASES_BY_FINISHED_TASK:
          CaseStateStatistic caseByFinishedTaskData = new CaseStateStatistic();
          if(statisticChart.getFilter() != null) {
            caseByFinishedTaskData = getCaseStateStatisticData(StatisticChartQueryUtils.generateCaseQueryForCaseHaveFinishedTask(statisticChart.getFilter()).asJson());
          }
          statisticChart.setDonutChartModel(generateCaseByStateModel(caseByFinishedTaskData, StatisticChartType.CASES_BY_FINISHED_TASK, true));
          break;
        case CASES_BY_FINISHED_TIME:
          CaseStateStatistic caseByFinishedTimeData = new CaseStateStatistic();
          if(statisticChart.getFilter() != null) {
            caseByFinishedTimeData = getCaseStateStatisticData(StatisticChartQueryUtils.generateCaseQueryByFinishedTime(statisticChart.getFilter()).asJson());
          }
          statisticChart.setDonutChartModel(generateCaseByStateModel(caseByFinishedTimeData, StatisticChartType.CASES_BY_FINISHED_TIME, true));
          break;
        default:
          break;
      }
    }
  }

  private void generateChartModelForTaskByExpiryChart(StatisticChart statisticChart) {
    if (!statisticChart.getId().contains("_")) {
      List<ExpiryStatistic> taskByExpiryData = new ArrayList<>();
      if(statisticChart.getFilter() != null){
        taskByExpiryData = getExpiryStatisticData(StatisticChartQueryUtils.generateTaskQueryForExpiry(statisticChart.getFilter()).asJson());
      }
      statisticChart.setBarChartModel(generateTaskByExpiryModel(taskByExpiryData, true, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY));
    }
  }

  public boolean isDrilldownToTaskList(String expiryLastDrilldownLevel, String selectedItem) {
    return isDrilldownToHourOrDay(expiryLastDrilldownLevel, selectedItem)
            || isDrilldownToWeek(expiryLastDrilldownLevel, selectedItem)
            || isDrilldownToMonth(expiryLastDrilldownLevel, selectedItem)
            || isDrilldownToYear(expiryLastDrilldownLevel);
  }

  private boolean isDrilldownToHourOrDay(String expiryLastDrilldownLevel, String selectedItem) {
    //this method is just used to avoid sonar issue about number of conditional operators used in expression
    return isDrilldownToHour(expiryLastDrilldownLevel, selectedItem)
            || isDrilldownToDay(expiryLastDrilldownLevel, selectedItem);
  }

  private boolean isDrilldownToHour(String expiryLastDrilldownLevel, String selectedItem) {
    return StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, DRILLDOWN_LEVEL_HOUR) && (selectHourOfDay(selectedItem));
  }

  private boolean isDrilldownToDay(String expiryLastDrilldownLevel, String selectedItem) {
    return StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, DRILLDOWN_LEVEL_DAY) && (selectDayOfWeek(selectedItem));
  }

  private boolean isDrilldownToWeek(String expiryLastDrilldownLevel, String selectedItem) {
    return StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, DRILLDOWN_LEVEL_WEEK)
            && (selectDayOfWeek(selectedItem) || selectWeekOfMonth(selectedItem));
  }

  private boolean isDrilldownToMonth(String expiryLastDrilldownLevel, String selectedItem) {
    return StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, DRILLDOWN_LEVEL_MONTH)
            && (selectDayOfWeek(selectedItem) || selectWeekOfMonth(selectedItem) || selectMonthOfYear(selectedItem));
  }

  private boolean isDrilldownToYear(String expiryLastDrilldownLevel) {
    return StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, DRILLDOWN_LEVEL_YEAR);
  }

  public static boolean selectThisYear(String selectedItem) {
    return StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(THIS_YEAR_EXPIRY_KEY));
  }

  public static boolean selectMonthOfYear(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    String[] monthsOfYear = {Ivy.cms().co(JANUARY_CMS), Ivy.cms().co(FEBRUARY_CMS), Ivy.cms().co(MARCH_CMS), Ivy.cms().co(APRIL_CMS), Ivy.cms().co(MAY_CMS), Ivy.cms().co(JUNE_CMS),
            Ivy.cms().co(JULY_CMS), Ivy.cms().co(AUGUST_CMS), Ivy.cms().co(SEPTEMBER_CMS), Ivy.cms().co(OCTOBER_CMS), Ivy.cms().co(NOVEMBER_CMS), Ivy.cms().co(DECEMBER_CMS)};

    return Arrays.asList(monthsOfYear).contains(selectedItem)
        || StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(THIS_MONTH_EXPIRY_KEY));
  }

  public static boolean selectWeekOfMonth(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    String[] weeksOfMonth = {Ivy.cms().co(FIRSTWEEK_CMS), Ivy.cms().co(SECONDWEEK_CMS), Ivy.cms().co(THIRDWEEK_CMS), Ivy.cms().co(FOURTHWEEK_CMS), Ivy.cms().co(FIFTHWEEK_CMS), Ivy.cms().co(SIXTHWEEK_CMS)};

    return Arrays.asList(weeksOfMonth).contains(selectedItem)
        || StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(THIS_WEEK_EXPIRY_KEY));
  }

  public static boolean selectDayOfWeek(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    String[] daysOfWeek = {Ivy.cms().co(MONDAY_CMS), Ivy.cms().co(TUESDAY_CMS), Ivy.cms().co(WEDNESDAY_CMS), Ivy.cms().co(THURSDAY_CMS), Ivy.cms().co(FRIDAY_CMS),
            Ivy.cms().co(SATURDAY_CMS), Ivy.cms().co(SUNDAY_CMS)};

    return Arrays.asList(daysOfWeek).contains(selectedItem)
        || StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(TODAY_EXPIRY_KEY));
  }

  public static boolean selectHourOfDay(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    String[] hoursOfDay = {BEFORE_8, IN_8, IN_9, IN_10, IN_11, IN_12, IN_13, IN_14, IN_15, IN_16, IN_17, AFTER_18};

    return Arrays.asList(hoursOfDay).contains(selectedItem);
  }

  public StatisticChart drilldownExpiryChart(String selectedValue, StatisticChart selectedChart, String previousSelectedMonth, String previousSelectedWeek) {
    List<ExpiryStatistic> taskByExpiryData = getExpiryStatisticData(StatisticChartQueryUtils.generateTaskQuery(selectedChart.getFilter()).asJson());
    StatisticChart newStatisticChart = new StatisticChart();
    newStatisticChart.setId(selectedChart.getId() + "_" + selectedValue); //chart with format: id + _ + suffix is lower level (month/week/day/hour) chart when drill down
    newStatisticChart.setName(selectedChart.getName() + " - " + selectedValue);
    newStatisticChart.setFilter(selectedChart.getFilter());
    newStatisticChart.setType(StatisticChartType.TASK_BY_EXPIRY);
    newStatisticChart.setUserId(selectedChart.getUserId());
    newStatisticChart.setBarChartModel(generateTaskByExpiryModel(taskByExpiryData, true, selectedValue, previousSelectedMonth, previousSelectedWeek));
    return newStatisticChart;
  }

  public static String getSelectedValueOfDonutChart(ItemSelectEvent event) {
    try {
      DonutChartModel model = (DonutChartModel) ((Chart) event.getSource()).getModel();
      int index = event.getItemIndex();
      return model.getData().get(0).keySet().toArray()[index].toString();
    } catch (Exception e) {
      Ivy.log().error(e);
      return "";
    }
  }

  public static String getSelectedValueOfBarChart(ItemSelectEvent event) {
    try {
      BarChartModel model = (BarChartModel) ((Chart) event.getSource()).getModel();
      int index = event.getItemIndex();
      return model.getSeries().get(0).getData().keySet().toArray()[index].toString();
    } catch (Exception e) {
      Ivy.log().error(e);
      return "";
    }
  }

  public static String getSelectedValueOfPieChart(ItemSelectEvent event) {
    try {
      PieChartModel model = (PieChartModel) ((Chart) event.getSource()).getModel();
      int index = event.getItemIndex();
      return model.getData().keySet().toArray()[index].toString();
    } catch (Exception e) {
      Ivy.log().error(e);
      return "";
    }
  }

  public boolean checkStatisticChartNameExisted(long userId, String chartName) {
    List<StatisticChart> statisticChartList = findStatisticChartsByUserId(userId);
    return statisticChartList.stream().filter(chart -> StringUtils.equals(chart.getName(), chartName)).findFirst()
        .isPresent();
  }

  public String[] getDrilldownLevels() {
    return DRILLDOWN_LEVELS;
  }
}
