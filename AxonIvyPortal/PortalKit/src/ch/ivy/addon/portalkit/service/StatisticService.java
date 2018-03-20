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
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.SECOND_DATATIP_CMS;
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
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class StatisticService extends BusinessDataService<StatisticChart> {

  private static final String[] MONTHSOFYEAR = {JANUARY_CMS, FEBRUARY_CMS, MARCH_CMS, APRIL_CMS, MAY_CMS, JUNE_CMS,
    JULY_CMS, AUGUST_CMS, SEPTEMBER_CMS, OCTOBER_CMS, NOVEMBER_CMS, DECEMBER_CMS};
  private static final String[] WEEKSOFMONTH = {FIRSTWEEK_CMS, SECONDWEEK_CMS, THIRDWEEK_CMS, FOURTHWEEK_CMS, FIFTHWEEK_CMS, SIXTHWEEK_CMS};
  private static final String[] DAYSOFWEEK = {MONDAY_CMS, TUESDAY_CMS, WEDNESDAY_CMS, THURSDAY_CMS, FRIDAY_CMS,
    SATURDAY_CMS, SUNDAY_CMS};
  private static final String[] HOURSOFDAY = {BEFORE_8, IN_8, IN_9, IN_10, IN_11, IN_12, IN_13, IN_14, IN_15, IN_16, IN_17, AFTER_18};
  private static final String[] DRILLDOWN_LEVELS = {DRILLDOWN_LEVEL_YEAR, DRILLDOWN_LEVEL_MONTH, DRILLDOWN_LEVEL_WEEK, DRILLDOWN_LEVEL_DAY, DRILLDOWN_LEVEL_HOUR};

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
      
      if (StatisticChartTimeUtils.isSameDay(resultDate, selectedDay, previousSelectedWeek, previousSelectedMonth)) {
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

    chartData.put(BEFORE_8, taskExpireBefore8);
    chartData.put(IN_8, taskExpireIn8);
    chartData.put(IN_9, taskExpireIn9);
    chartData.put(IN_10, taskExpireIn10);
    chartData.put(IN_11, taskExpireIn11);
    chartData.put(IN_12, taskExpireIn12);
    chartData.put(IN_13, taskExpireIn13);
    chartData.put(IN_14, taskExpireIn14);
    chartData.put(IN_15, taskExpireIn15);
    chartData.put(IN_16, taskExpireIn16);
    chartData.put(IN_17, taskExpireIn17);
    chartData.put(AFTER_18, taskExpireAfter18);
    
    return chartData;
  }

  private Map<Object, Number> generateExpiryModelForDrilldownLevelWeek(Map<Date, Long> statisticResultMap,
      String selectedWeek, String previousSelectedMonth) {
    Map<Object, Number> chartData = new LinkedHashMap<Object, Number>();

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

    Long taskExpireOnMonday = new Long(0L);
    Long taskExpireOnTuesday = new Long(0L);
    Long taskExpireOnWednesday = new Long(0L);
    Long taskExpireOnThursday = new Long(0L);
    Long taskExpireOnFriday = new Long(0L);
    Long taskExpireOnSaturday = new Long(0L);
    Long taskExpireOnSunday = new Long(0L);

    for (Entry<Date, Long> result : statisticResultMap.entrySet()) {
      Date resultDate = StatisticChartTimeUtils.truncateMinutesPart(result.getKey());
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

    chartData.put(MONDAY_CMS, taskExpireOnMonday);
    chartData.put(TUESDAY_CMS, taskExpireOnTuesday);
    chartData.put(WEDNESDAY_CMS, taskExpireOnWednesday);
    chartData.put(THURSDAY_CMS, taskExpireOnThursday);
    chartData.put(FRIDAY_CMS, taskExpireOnFriday);
    chartData.put(SATURDAY_CMS, taskExpireOnSaturday);
    chartData.put(SUNDAY_CMS, taskExpireOnSunday);

    return chartData;
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

    chartData.put(FIRSTWEEK_CMS, taskExpireOnFirstWeek);
    chartData.put(SECONDWEEK_CMS, taskExpireOnSecondWeek);
    chartData.put(THIRDWEEK_CMS, taskExpireOnThirdWeek);
    chartData.put(FOURTHWEEK_CMS, taskExpireOnFourthWeek);
    if (firstDateOfNextMonth.compareTo(firstDateOfFifthWeek) > 0) {
      chartData.put(FIFTHWEEK_CMS, taskExpireOnFifthWeek);
    }
    if (firstDateOfNextMonth.compareTo(firstDateOfSixthWeek) > 0) {
      chartData.put(SIXTHWEEK_CMS, taskExpireOnSixthWeek);
    }
    return chartData;
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
      Date resultDate = StatisticChartTimeUtils.truncateMinutesPart(result.getKey());
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
    Map<Object, Number> chartData = new LinkedHashMap<Object, Number>();
    // Calculate result
    Long taskExpireToday = new Long(0L);
    Long taskExpireThisWeek = new Long(0L);
    Long taskExpireThisMonth = new Long(0L);
    Long taskExpireThisYear = new Long(0L);

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
  public PieChartModel generateElapsedTimeModel(List<ElapsedTimeStatistic> statisticData, boolean isSetDefaultName) {
    Map<String, Number> chartData = generateDataForElapsedTimeChart(statisticData);
    PieChartModel model = new PieChartModel();

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
          List<ExpiryStatistic> taskByExpiryData = new ArrayList<>();
          if(statisticChart.getFilter() != null){
            taskByExpiryData = getExpiryStatisticData(StatisticChartQueryUtils.generateTaskQueryForExpiry(statisticChart.getFilter()).asJson());
          }
          statisticChart.setBarChartModel(generateTaskByExpiryModel(taskByExpiryData, true, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY));
          break;
        case CASES_BY_STATE:
          CaseStateStatistic caseStateData = new CaseStateStatistic();
          if(statisticChart.getFilter() != null) {
            caseStateData = getCaseStateStatisticData(StatisticChartQueryUtils.generateCaseQuery(statisticChart.getFilter(), false).asJson());
          }
          statisticChart.setDonutChartModel(generateCaseByStateModel(caseStateData, StatisticChartType.CASES_BY_STATE,  true));
          break;
        case ELAPSED_TIME_BY_CASE_CATEGORY:
          List<ElapsedTimeStatistic> elapsedTimeData = new ArrayList<>();
          if(statisticChart.getFilter() != null) {
            elapsedTimeData = getElapsedTimeStatisticData(StatisticChartQueryUtils.generateCaseQuery(statisticChart.getFilter(), true).asJson());
          }
          statisticChart.setPieChartModel(generateElapsedTimeModel(elapsedTimeData, true));
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

  public boolean isDrilldownToTaskList(String expiryLastDrilldownLevel, String selectedItem) {
    // hour
    if (StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, DRILLDOWN_LEVEL_HOUR) && (selectHourOfDay(selectedItem))) {
      return true;
    }
    // day
    if (StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, DRILLDOWN_LEVEL_DAY) && (selectDayOfWeek(selectedItem))) {
      return true;
    }
    // week
    if (StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, DRILLDOWN_LEVEL_WEEK)
        && (selectDayOfWeek(selectedItem) || selectWeekOfMonth(selectedItem))) {
      return true;
    }
    // month
    if (StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, DRILLDOWN_LEVEL_MONTH)
        && (selectDayOfWeek(selectedItem) || selectWeekOfMonth(selectedItem) || selectMonthOfYear(selectedItem))) {
      return true;
    }
    //year
    if(StringUtils.equalsIgnoreCase(expiryLastDrilldownLevel, DRILLDOWN_LEVEL_YEAR)){
      return true;
    }
    return false;
  }

  public boolean selectThisYear(String selectedItem) {
    return StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(THIS_YEAR_EXPIRY_KEY));
  }

  public boolean selectMonthOfYear(String selectedItem) {
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

  public boolean selectDayOfWeek(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    return Arrays.asList(DAYSOFWEEK).contains(selectedItem)
        || StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(TODAY_EXPIRY_KEY));
  }

  public boolean selectHourOfDay(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    return Arrays.asList(HOURSOFDAY).contains(selectedItem);
  }

  public StatisticChart drilldownExpiryChart(String selectedValue, StatisticChart selectedChart, String previousSelectedMonth, String previousSelectedWeek) {
    List<ExpiryStatistic> taskByExpiryData = getExpiryStatisticData(StatisticChartQueryUtils.generateTaskQuery(selectedChart.getFilter()).asJson());
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

  /**
   * Get updated task query for Task by Priority chart based on selected item
   * 
   * @param event
   * @param statisticChart
   * @return updated task query
   */
  public TaskQuery getQueryForSelectedItemOfTaskByPriorityChart(ItemSelectEvent event, StatisticChart statisticChart) {
    TaskQuery query = StatisticChartQueryUtils.generateTaskQuery(statisticChart.getFilter());
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
   * @param statisticChart statistic chart
   * @param previousSelectedMonth previous selected month
   * @param previousSelectedWeek previous selected week
   * @param previousSelectedDay previous selected day
   * @return updated task query
   */
  public TaskQuery getQueryForSelectedItemOfTaskByExpiryChart(ItemSelectEvent event, StatisticChart statisticChart, String previousSelectedMonth, String previousSelectedWeek, String previousSelectedDay) {
    TaskQuery query = StatisticChartQueryUtils.generateTaskQueryForExpiry(statisticChart.getFilter());
    String selectedValue = getSelectedValueOfBarChart(event);
    Date fromDate;
    Date toDate;
    if (selectHourOfDay(selectedValue)) {
      Date currentDate;
      if (StringUtils.containsIgnoreCase(previousSelectedDay, Ivy.cms().co(TODAY_EXPIRY_KEY))) {
        currentDate = new Date();
      } else {
        int shiftDays = StatisticChartTimeUtils.getShiftDaysFromDayOfWeek(previousSelectedDay);
        currentDate = DateUtils.addDays(StatisticChartTimeUtils.getFirstDateOfWeek(previousSelectedWeek, previousSelectedMonth), shiftDays);
      }

      Date dateWithoutTime = StatisticChartTimeUtils.truncateMinutesPart(currentDate);
      if (selectedValue.equals(BEFORE_8)) {
        fromDate = dateWithoutTime;
        toDate = DateUtils.setHours(dateWithoutTime, 8);
      } else if (selectedValue.equals(AFTER_18)) {
        fromDate = DateUtils.setHours(dateWithoutTime, 18);
        toDate = DateUtils.addDays(dateWithoutTime, 1);
      } else {
        fromDate = DateUtils.setHours(dateWithoutTime, Integer.parseInt(selectedValue));
        toDate = DateUtils.setHours(dateWithoutTime, Integer.parseInt(selectedValue) + 1);
      }
    } else if (selectDayOfWeek(selectedValue)) {
      if (StringUtils.containsIgnoreCase(selectedValue, Ivy.cms().co(TODAY_EXPIRY_KEY))) {
        fromDate = StatisticChartTimeUtils.truncateMinutesPart(new Date());
      } else {
        int shiftDays = StatisticChartTimeUtils.getShiftDaysFromDayOfWeek(selectedValue);
        fromDate = DateUtils.addDays(StatisticChartTimeUtils.getFirstDateOfWeek(previousSelectedWeek, previousSelectedMonth), shiftDays);
        fromDate = StatisticChartTimeUtils.truncateMinutesPart(fromDate);
      }
      toDate = DateUtils.addDays(fromDate, 1);
    } else if (selectWeekOfMonth(selectedValue)) {
      fromDate = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfWeek(selectedValue, previousSelectedMonth));
      toDate = DateUtils.addWeeks(fromDate, 1);

      if (!StringUtils.isEmpty(previousSelectedMonth)) {
        Date firstDateOfMonth = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfMonth(previousSelectedMonth));
        if (firstDateOfMonth.compareTo(fromDate) > 0) {
          fromDate = firstDateOfMonth;
        }
        Date firstDayOfNextMonth = DateUtils.addMonths(firstDateOfMonth, 1);
        if (toDate.compareTo(firstDayOfNextMonth) > 0) {
          toDate = firstDayOfNextMonth;
        }
      }
    } else if (selectMonthOfYear(selectedValue)) {
      fromDate = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfMonth(selectedValue));
      toDate = DateUtils.addMonths(fromDate, 1);
    } else {
      fromDate = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfThisYear());
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
      query = StatisticChartQueryUtils.generateCaseQuery(statisticChart.getFilter(), false);
    }
    else if(statisticChart.getType() == StatisticChartType.CASES_BY_FINISHED_TIME) {
      query = StatisticChartQueryUtils.generateCaseQueryByFinishedTime(statisticChart.getFilter());
    }
    else if(statisticChart.getType() == StatisticChartType.CASES_BY_FINISHED_TASK) {
      query = StatisticChartQueryUtils.generateCaseQueryForCaseHaveFinishedTask(statisticChart.getFilter());
    }
    
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

  public String[] getDrilldownLevels() {
    return DRILLDOWN_LEVELS;
  }
}
