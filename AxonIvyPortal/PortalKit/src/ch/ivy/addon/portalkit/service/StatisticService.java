package ch.ivy.addon.portalkit.service;

import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.AFTER_18;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.APRIL_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.AUGUST_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.BEFORE_8;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.CASE_CATEGORIES_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.CASE_QUERY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.CREATED_CASE_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.DECEMBER_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.DONE_CASE_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.ELAPSED_TIME_DETAIL_CHART_NAME_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.EXCEPTION_PRIORITY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.EXPIRED_KEY;
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
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.TASK_QUERY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THIRDWEEK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THIS_MONTH_EXPIRY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THIS_WEEK_EXPIRY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THIS_YEAR_EXPIRY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THURSDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.TODAY_EXPIRY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.TUESDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.USER_ID;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.WEDNESDAY_CMS;

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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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

import ch.ivy.addon.portalkit.bo.CaseStateStatistic;
import ch.ivy.addon.portalkit.bo.ElapsedTimeStatistic;
import ch.ivy.addon.portalkit.bo.ExpiryStatistic;
import ch.ivy.addon.portalkit.bo.PriorityStatistic;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomFieldSearchCriteria;
import ch.ivy.addon.portalkit.statistics.Colors;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.statistics.StatisticChartQueryUtils;
import ch.ivy.addon.portalkit.statistics.StatisticChartTimeUtils;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class StatisticService extends BusinessDataService<StatisticChart> {

  @Override
  public Class<StatisticChart> getType() {
    return StatisticChart.class;
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
      return statisticChartQuery.orderBy().textField(NAME).ascending().execute().count();
    } catch (Exception e) {
      Ivy.log().error(e);
      return -1;
    }
  }

  /**
   * Get Task by Priority statistic data
   * 
   * @param taskQuery
   * @return Task by Priority statistic data
   */
  public PriorityStatistic getPriorityStatisticData(TaskQuery taskQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put(TASK_QUERY, taskQuery);

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzePriorityStatistic(ch.ivyteam.ivy.workflow.query.TaskQuery)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (PriorityStatistic) response.get(RESULT);
  }

  /**
   * Get Task by Expiry statistic data
   * 
   * @param taskQuery
   * @return Task by Expiry statistic data
   */
  public ExpiryStatistic getExpiryStatisticData(TaskQuery taskQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put(TASK_QUERY, taskQuery);

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeExpiryStatistic(ch.ivyteam.ivy.workflow.query.TaskQuery)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (ExpiryStatistic) response.get(RESULT);
  }

  /**
   * Get Case by State statistic data
   * 
   * @param caseQuery
   * @return Case by State statistic data
   */
  public CaseStateStatistic getCaseStateStatisticData(CaseQuery caseQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put(CASE_QUERY, caseQuery);

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeCaseStateStatistic(ch.ivyteam.ivy.workflow.query.CaseQuery)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (CaseStateStatistic) response.get(RESULT);
  }

  /**
   * Get Elapsed time by Case Category data
   * 
   * @param caseQuery
   * @return Elapsed time by Case Category data
   */
  public ElapsedTimeStatistic getElapsedTimeStatisticData(CaseQuery caseQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put(CASE_QUERY, caseQuery);

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeElapsedTimeStatistic(ch.ivyteam.ivy.workflow.query.CaseQuery)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (ElapsedTimeStatistic) response.get(RESULT);
  }

  /**
   * Get Elapsed time of related tasks by Case Category
   * 
   * @param taskQuery
   * @return Elapsed time of related tasks by Case Category
   */
  public ElapsedTimeStatistic getElapsedTimeOfTasksStatisticData(TaskQuery taskQuery) {
    Map<String, Object> params = new HashMap<>();
    params.put(TASK_QUERY, taskQuery);

    Map<String, Object> response =
        IvyAdapterService.startSubProcess("analyzeElapsedTimeOfTasks(ch.ivyteam.ivy.workflow.query.TaskQuery)", params,
            Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    return (ElapsedTimeStatistic) response.get(RESULT);
  }

  @SuppressWarnings("unchecked")
  public List<String> getCustomFields(String fieldName, String keyword){
    CaseCustomFieldSearchCriteria criteria = new CaseCustomFieldSearchCriteria();
    criteria.setKeyword(keyword);
    criteria.setFieldName(fieldName);
    return SubProcessCall.withPath(PortalConstants.ANALYZE_STATISTIC_CALLABLE)
        .withStartSignature("findCaseCustomFields(CaseCustomFieldSearchCriteria)")
        .withParam("caseCustomFieldSearchCriteria", criteria).call().get(RESULT, List.class);
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
  public Map<Object, Number> generateDataForTaskByExpiryOverviewChart(ExpiryStatistic expiryStatistic,
      String selectedValue, String previousSelectedMonth, String previousSelectedWeek) {
    Map<Object, Number> chartData;
    Map<Date, Long> numberOfTasksByExpiryTime = expiryStatistic.getNumberOfTasksByExpiryTime();
    if (selectThisYear(selectedValue)) {
      chartData = generateExpiryModelForDrilldownLevelYear(numberOfTasksByExpiryTime);
    } else if (selectMonthOfYear(selectedValue)) {
      chartData = generateExpiryModelForDrilldownLevelMonth(numberOfTasksByExpiryTime, selectedValue);
    } else if (selectWeekOfMonth(selectedValue)) {
      chartData = generateExpiryModelForDrilldownLevelWeek(numberOfTasksByExpiryTime, selectedValue, previousSelectedMonth);
    } else if (selectDayOfWeek(selectedValue)) {
      chartData =
          generateExpiryModelForDrilldownLevelDay(numberOfTasksByExpiryTime, selectedValue, previousSelectedWeek,
              previousSelectedMonth);
    } else {
      chartData = generateDefaultExpiryModel(numberOfTasksByExpiryTime);
    }

    return chartData;
  }

  private Map<Object, Number> generateExpiryModelForDrilldownLevelDay(Map<Date, Long> numberOfTasksByExpiryTime,
      String selectedDay, String previousSelectedWeek, String previousSelectedMonth) {
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

    for (Entry<Date, Long> result : numberOfTasksByExpiryTime.entrySet()) {
      Date resultDate = result.getKey();
      Calendar cal = Calendar.getInstance();
      cal.setTime(resultDate);

      if (StatisticChartTimeUtils.isSameDay(resultDate, selectedDay, previousSelectedWeek, previousSelectedMonth)) {
        if (cal.get(Calendar.HOUR_OF_DAY) < 8) {
          taskExpireBefore8 += result.getValue();
        } else if (cal.get(Calendar.HOUR_OF_DAY) >= 18) {
          taskExpireAfter18 += result.getValue();
        } else {
          int hourIndex = cal.get(Calendar.HOUR_OF_DAY);
          taskExpireInHour[hourIndex - 8] += result.getValue(); // example: hourIndex = 8 => taskExpireInHour[ 8 - 8] =
                                                                // taskExpireInHour[0] = taskExpireIn8 +=
                                                                // result.getValue() ...
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

  private Map<Object, Number> generateExpiryModelForDrilldownLevelWeek(Map<Date, Long> numberOfTasksByExpiryTime,
      String selectedWeek, String previousSelectedMonth) {
    Map<Object, Number> chartData = new LinkedHashMap<>();

    Date firstDateOfSelectedWeek =
        StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfWeek(selectedWeek,
            previousSelectedMonth));
    Date firstDateOfNextWeek =
        StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addWeeks(firstDateOfSelectedWeek, 1));

    if (!StringUtils.isEmpty(previousSelectedMonth)) {
      Date firstDateOfMonth =
          StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils
              .getFirstDateOfMonth(previousSelectedMonth));
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

    for (Entry<Date, Long> result : numberOfTasksByExpiryTime.entrySet()) {
      Date resultDate = StatisticChartTimeUtils.truncateMinutesPart(result.getKey());
      Calendar cal = Calendar.getInstance();
      cal.setTime(resultDate);

      if (checkIfDateBetweenRange(firstDateOfSelectedWeek, firstDateOfNextWeek, resultDate)) {
        int dayOfWeekIndex = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeekIndex == 1) { // SUNDAY
          taskExpireOnDaysOfWeek[6] += result.getValue(); // index 6 means taskExpireOnSunday
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

  private Map<Object, Number> generateExpiryModelForDrilldownLevelMonth(Map<Date, Long> numberOfTasksByExpiryTime,
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
    Date firstDateOfNextMonth =
        StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addMonths(firstDateOfSelectedMonth, 1));

    for (Entry<Date, Long> result : numberOfTasksByExpiryTime.entrySet()) {
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
      Long[] taskOfFifthAndSixWeek =
          calculateTaskExpireOnFifthAndSixthWeek(firstDateOfFifthWeek, firstDateOfSixthWeek, firstDateOfNextMonth,
              resultDate, result.getValue());
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
    return new Long[] {taskExpireOnFifthWeek, taskExpireOnSixthWeek};
  }

  private boolean checkIfDateBetweenRange(Date startDate, Date endDate, Date resultDate) {
    return startDate.compareTo(resultDate) <= 0 && endDate.compareTo(resultDate) > 0;
  }

  private Map<Object, Number> generateExpiryModelForDrilldownLevelYear(Map<Date, Long> numberOfTasksByExpiryTime) {
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

    if (numberOfTasksByExpiryTime != null) {
      for (Entry<Date, Long> result : numberOfTasksByExpiryTime.entrySet()) {
        Date resultDate = StatisticChartTimeUtils.truncateMinutesPart(result.getKey());
        Calendar cal = Calendar.getInstance();
        cal.setTime(resultDate);
  
        int thisYear = Year.now().getValue();
        if (cal.get(Calendar.YEAR) == thisYear) {
          int monthIndex = cal.get(Calendar.MONTH); // from JANUARY to DECEMBER, month index from 0 to 11
          takExpireOnMonths[monthIndex] += result.getValue();
        }
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

  private Map<Object, Number> generateDefaultExpiryModel(Map<Date, Long> numberOfTasksByExpiryTime) {
    Map<Object, Number> chartData = new LinkedHashMap<>();
    // Calculate result
    Long expiredTasks = 0L;
    Long taskExpireToday = 0L;
    Long taskExpireThisWeek = 0L;
    Long taskExpireThisMonth = 0L;
    Long taskExpireThisYear = 0L;

    Date today = StatisticChartTimeUtils.truncateMinutesPart(new Date());
    Date firstDateOfWeek =
        StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfWeekContainsDate(new Date()));
    Date firstDateOfNextWeek = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addWeeks(firstDateOfWeek, 1));
    Date firstDateOfMonth =
        StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfThisMonth());
    Date firsDateOfNextMonth = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addMonths(firstDateOfMonth, 1));
    Date firsDateOfYear = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfThisYear());
    Date firsDateOfNextYear = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addYears(firsDateOfYear, 1));

    if (numberOfTasksByExpiryTime != null) {
      for (Entry<Date, Long> result : numberOfTasksByExpiryTime.entrySet()) {
        Date resultDate = StatisticChartTimeUtils.truncateMinutesPart(result.getKey());

        if (new Date().after(result.getKey())) {
          expiredTasks += result.getValue();
          continue; // Not include expired tasks to other bars
        }
        
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
    }

    chartData.put(Ivy.cms().co(EXPIRED_KEY), expiredTasks);
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
  public Map<String, Number> generateDataForElapsedTimeChart(ElapsedTimeStatistic elapsedTimeStatistic) {
    Map<String, Number> chartData = new LinkedHashMap<>();
    if(!Objects.isNull(elapsedTimeStatistic.getAverageElapsedTimeByCategory())) {
      for (Entry<String, Long> entry : elapsedTimeStatistic.getAverageElapsedTimeByCategory().entrySet()) {
        String key = entry.getKey();
        if (StringUtils.isBlank(key)) {
          key = Ivy.cms().co(NO_CATEGORY_CMS);
        }
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
   * @param isDefault is a default chart
   * @return Added statistic chart
   */
  public StatisticChart createStatisticChart(StatisticFilter filter, String chartName, StatisticChartType chartType,
      long creatorId, boolean isDefault) {
    StatisticChart statisticChart = new StatisticChart();

    statisticChart.setUserId(creatorId);
    statisticChart.setType(chartType);
    statisticChart.setName(Optional.ofNullable(chartName).orElse("New chart"));
    statisticChart.setPosition(countStatisticChartsByUserId(creatorId));
    statisticChart.setDefaultChart(String.valueOf(isDefault));
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

  private DonutChartModel createDonutChartModel(Map<String, Number> chartData, String extender) {
    DonutChartModel model = new DonutChartModel();
    model.addCircle(chartData);
    model.setLegendPosition("s");
    model.setShowDataLabels(true);
    model.setExtender(extender);
    model.setShadow(false);
    model.setDataFormat("percent");
    model.setSliceMargin(3);
    model.setLegendRows(1);
    model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
    return model;
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

    if (isEmptyData) {
      chartData.put(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/tasks"), 0);
    }

    DonutChartModel model = createDonutChartModel(chartData, "chartExtender");
    if (!isEmptyData) {
      model.setSeriesColors(Colors.getPriorityColors(chartData));
    }
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
  public BarChartModel generateTaskByExpiryModel(ExpiryStatistic statisticData, boolean isSetDefaultName,
      String selectedValue, String previousSelectedMonth, String previousSelectedWeek) {
    Map<Object, Number> chartData =
        generateDataForTaskByExpiryOverviewChart(statisticData, selectedValue, previousSelectedMonth,
            previousSelectedWeek);
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
  public DonutChartModel generateCaseByStateModel(CaseStateStatistic statisticData, StatisticChartType chartType,
      boolean isSetDefaultName) {
    Map<String, Number> chartData = generateDataForCaseStateChart(statisticData);
    boolean isEmptyData = chartData.isEmpty();

    if (isEmptyData) {
      chartData.put(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/cases"), 0);
    }

    DonutChartModel model = createDonutChartModel(chartData, "chartExtender");
    if (!isEmptyData) {
      model.setSeriesColors(Colors.getCaseStateColors(chartData));
    }
    if (isSetDefaultName) {
      if (chartType == StatisticChartType.CASES_BY_STATE) {
        model.setTitle(Ivy.cms().co(StatisticChartType.CASES_BY_STATE.getCmsUri()));
      } else if (chartType == StatisticChartType.CASES_BY_FINISHED_TASK) {
        model.setTitle(Ivy.cms().co(StatisticChartType.CASES_BY_FINISHED_TASK.getCmsUri()));
      } else if (chartType == StatisticChartType.CASES_BY_FINISHED_TIME) {
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
  public BarChartModel generateElapsedTimeModel(ElapsedTimeStatistic statisticData, boolean isSetDefaultName) {
    Map<String, Number> chartData = getElapsedTimeChartData(statisticData);

    BarChartModel model = new BarChartModel();
    ChartSeries chartSeries = new ChartSeries();

    if (chartData.size() != 0) {
      if(chartData.size() == 1) {
        model.setBarWidth(80);
      }
      model.setBarMargin(1);
      chartSeries.setData(new HashMap<>(chartData));
      model.setExtender("elapsedTimeBarChartExtender");
      model.setShadow(false);

      Axis xAxis = model.getAxis(AxisType.X);
      xAxis.setLabel(Ivy.cms().co(CASE_CATEGORIES_CMS));

      Axis yAxis = model.getAxis(AxisType.Y);
      yAxis.setLabel(Ivy.cms().co(ELAPSED_TIME_DETAIL_CHART_NAME_CMS));
    }
    if (isSetDefaultName) {
      model.setTitle(Ivy.cms().co(StatisticChartType.ELAPSED_TIME_BY_CASE_CATEGORY.getCmsUri()));
    }
    model.addSeries(chartSeries);
    return model;
  }

  private Map<String, Number> getElapsedTimeChartData(ElapsedTimeStatistic statisticData) {
    Map<String, Number> caseCategoryToElapsedTime = generateDataForElapsedTimeChart(statisticData);

    if (caseCategoryToElapsedTime.isEmpty()) {
      caseCategoryToElapsedTime.put("", 0);
    }

    for (Iterator<Entry<String, Number>> iterator = caseCategoryToElapsedTime.entrySet().iterator(); iterator.hasNext();) {
      Entry<String, Number> chartDataEntry = iterator.next();
      float floatValueOfChartData = chartDataEntry.getValue().floatValue()/3600;

        chartDataEntry.setValue(floatValueOfChartData);
    }

    return new HashMap<>(caseCategoryToElapsedTime);
  }

  public boolean isTaskByPriority(StatisticChart statisticChart) {
    return statisticChart.getType() == StatisticChartType.TASK_BY_PRIORITY;
  }

  public boolean isTaskByExpiry(StatisticChart statisticChart) {
    return statisticChart.getType() == StatisticChartType.TASK_BY_EXPIRY;
  }

  public boolean isTaskByExpiryHour(StatisticChart statisticChart) {
    return statisticChart.getType() == StatisticChartType.TASK_BY_EXPIRY
        && statisticChart.getBarChartModel().getAxis(AxisType.X).getLabel()
            .contains(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/hour"));
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
          statisticChart.setDonutChartModel(buildChartModelForTaskPriority(statisticChart));
          break;
        case TASK_BY_EXPIRY:
          BarChartModel chartModelForTaskExpiry = buildChartModelForTaskExpiry(statisticChart);
          statisticChart.setBarChartModel(chartModelForTaskExpiry);
          break;
        case CASES_BY_STATE:
          statisticChart.setDonutChartModel(buildChartModelForCaseState(statisticChart));
          break;
        case ELAPSED_TIME_BY_CASE_CATEGORY:
          statisticChart.setBarChartModel(buildChartModelForCaseElapsedTime(statisticChart));
          break;
        case CASES_BY_FINISHED_TASK:
          statisticChart.setDonutChartModel(buildCharModelForCaseHasFinishedTask(statisticChart));
          break;
        case CASES_BY_FINISHED_TIME:
          statisticChart.setDonutChartModel(buildChartModelForCaseFinishedTime(statisticChart));
          break;
        default:
          break;
      }
    }
  }

  private DonutChartModel buildChartModelForCaseFinishedTime(StatisticChart statisticChart) {
    CaseStateStatistic caseByFinishedTimeData = new CaseStateStatistic();
    if (statisticChart.getFilter() != null) {
      caseByFinishedTimeData =
          getCaseStateStatisticData(StatisticChartQueryUtils
              .generateCaseQueryByFinishedTime(statisticChart.getFilter()));
    }
    return generateCaseByStateModel(caseByFinishedTimeData, StatisticChartType.CASES_BY_FINISHED_TIME, true);
  }

  private DonutChartModel buildCharModelForCaseHasFinishedTask(StatisticChart statisticChart) {
    CaseStateStatistic caseByFinishedTaskData = new CaseStateStatistic();
    if (statisticChart.getFilter() != null) {
      caseByFinishedTaskData =
          getCaseStateStatisticData(StatisticChartQueryUtils.generateCaseQueryForCaseHaveFinishedTask(
              statisticChart.getFilter()));
    }
    return generateCaseByStateModel(caseByFinishedTaskData, StatisticChartType.CASES_BY_FINISHED_TASK, true);
  }

  private BarChartModel buildChartModelForCaseElapsedTime(StatisticChart statisticChart) {
    ElapsedTimeStatistic elapsedTimeData = new ElapsedTimeStatistic();
    if (statisticChart.getFilter() != null) {
      elapsedTimeData =
          getElapsedTimeStatisticData(StatisticChartQueryUtils.generateCaseQuery(statisticChart.getFilter(), true));
    }
    return generateElapsedTimeModel(elapsedTimeData, true);
  }

  private DonutChartModel buildChartModelForCaseState(StatisticChart statisticChart) {
    CaseStateStatistic caseStateData = new CaseStateStatistic();
    if (statisticChart.getFilter() != null) {
      caseStateData =
          getCaseStateStatisticData(StatisticChartQueryUtils.generateCaseQueryForCaseState(statisticChart.getFilter()));
    }
    return generateCaseByStateModel(caseStateData, StatisticChartType.CASES_BY_STATE, true);
  }

  private DonutChartModel buildChartModelForTaskPriority(StatisticChart statisticChart) {
    PriorityStatistic taskByPriorityData = new PriorityStatistic();
    if (statisticChart.getFilter() != null) {
      taskByPriorityData =
          getPriorityStatisticData(StatisticChartQueryUtils.generateTaskQuery(statisticChart.getFilter()));
    }
    return generateTaskByPriorityModel(taskByPriorityData, true);
  }

  private BarChartModel buildChartModelForTaskExpiry(StatisticChart statisticChart) {
    if (!statisticChart.getId().contains("_")) {
      ExpiryStatistic taskByExpiryData = new ExpiryStatistic();
      if (statisticChart.getFilter() != null) {
        taskByExpiryData =
            getExpiryStatisticData(StatisticChartQueryUtils.generateTaskQueryForExpiry(statisticChart.getFilter()));
      }
      return generateTaskByExpiryModel(taskByExpiryData, true, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
    }
    return statisticChart.getBarChartModel();
  }

  public static boolean selectThisYear(String selectedItem) {
    return StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(THIS_YEAR_EXPIRY_KEY));
  }

  public static boolean selectMonthOfYear(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    String[] monthsOfYear =
        {Ivy.cms().co(JANUARY_CMS), Ivy.cms().co(FEBRUARY_CMS), Ivy.cms().co(MARCH_CMS), Ivy.cms().co(APRIL_CMS),
            Ivy.cms().co(MAY_CMS), Ivy.cms().co(JUNE_CMS), Ivy.cms().co(JULY_CMS), Ivy.cms().co(AUGUST_CMS),
            Ivy.cms().co(SEPTEMBER_CMS), Ivy.cms().co(OCTOBER_CMS), Ivy.cms().co(NOVEMBER_CMS),
            Ivy.cms().co(DECEMBER_CMS)};

    return Arrays.asList(monthsOfYear).contains(selectedItem)
        || StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(THIS_MONTH_EXPIRY_KEY));
  }

  public static boolean selectWeekOfMonth(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    String[] weeksOfMonth =
        {Ivy.cms().co(FIRSTWEEK_CMS), Ivy.cms().co(SECONDWEEK_CMS), Ivy.cms().co(THIRDWEEK_CMS),
            Ivy.cms().co(FOURTHWEEK_CMS), Ivy.cms().co(FIFTHWEEK_CMS), Ivy.cms().co(SIXTHWEEK_CMS)};

    return Arrays.asList(weeksOfMonth).contains(selectedItem)
        || StringUtils.containsIgnoreCase(selectedItem, Ivy.cms().co(THIS_WEEK_EXPIRY_KEY));
  }

  public static boolean selectDayOfWeek(String selectedItem) {
    if (selectedItem.isEmpty()) {
      return false;
    }
    String[] daysOfWeek =
        {Ivy.cms().co(MONDAY_CMS), Ivy.cms().co(TUESDAY_CMS), Ivy.cms().co(WEDNESDAY_CMS), Ivy.cms().co(THURSDAY_CMS),
            Ivy.cms().co(FRIDAY_CMS), Ivy.cms().co(SATURDAY_CMS), Ivy.cms().co(SUNDAY_CMS)};

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

  public StatisticChart drilldownExpiryChart(String selectedValue, StatisticChart selectedChart,
      String previousSelectedMonth, String previousSelectedWeek) {
    ExpiryStatistic taskByExpiryData =
        getExpiryStatisticData(StatisticChartQueryUtils.generateTaskQuery(selectedChart.getFilter()));
    StatisticChart newStatisticChart = new StatisticChart();
    newStatisticChart.setId(selectedChart.getId() + "_" + selectedValue); // chart with format: id + _ + suffix is lower
                                                                          // level (month/week/day/hour) chart when
                                                                          // drill down
    newStatisticChart.setName(selectedChart.getName() + " - " + selectedValue);
    newStatisticChart.setFilter(selectedChart.getFilter());
    newStatisticChart.setType(StatisticChartType.TASK_BY_EXPIRY);
    newStatisticChart.setUserId(selectedChart.getUserId());
    newStatisticChart.setBarChartModel(generateTaskByExpiryModel(taskByExpiryData, true, selectedValue,
        previousSelectedMonth, previousSelectedWeek));
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

  public void removeStatisticChartsByUserId(long userId) {
    List<StatisticChart> result =
        repo().search(getType()).numberField(USER_ID).isEqualTo(userId).execute().getAll();
    result.stream().forEach(item -> repo().delete(item));
  }

  public boolean hasDefaultChart(long userId) {
    List<StatisticChart> findStatisticChartsByUserId = findStatisticChartsByUserId(userId);
    return findStatisticChartsByUserId.stream().anyMatch(chart -> StringUtils.equalsIgnoreCase("true", chart.getDefaultChart()));
  }
  
  public boolean checkDefaultStatisticChartNameExisted(long userId, String chartName) {
    List<StatisticChart> statisticChartList = findStatisticChartsByUserId(userId);
    return statisticChartList.stream().filter(
        chart -> StringUtils.equals(chart.getName(), chartName) && 
        StringUtils.equalsIgnoreCase("true", chart.getDefaultChart()))
        .findFirst().isPresent();
  }
}
