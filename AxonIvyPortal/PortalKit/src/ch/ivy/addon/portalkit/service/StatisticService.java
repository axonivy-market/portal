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

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.primefaces.component.barchart.BarChart;
import org.primefaces.component.donutchart.DonutChart;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianAxes;
import org.primefaces.model.charts.axes.cartesian.CartesianScaleLabel;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.donut.DonutChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

import ch.ivy.addon.portalkit.bo.CaseStateStatistic;
import ch.ivy.addon.portalkit.bo.ElapsedTimeStatistic;
import ch.ivy.addon.portalkit.bo.ExpiryStatistic;
import ch.ivy.addon.portalkit.bo.PriorityStatistic;
import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomFieldSearchCriteria;
import ch.ivy.addon.portalkit.statistics.Colors;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.statistics.StatisticChartQueryUtils;
import ch.ivy.addon.portalkit.statistics.StatisticChartTimeUtils;
import ch.ivy.addon.portalkit.statistics.StatisticColors;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class StatisticService extends BusinessDataService<StatisticChart> {

  private static final String CHART_LEGEND_POSITION_LEFT = "left";
  private static final String CHART_LEGEND_POSITION_BOTTOM = "bottom";
  private static StatisticColors statisticColors;
  private String LOADING_MESSAGE = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/loadingCharts");

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
      Result<StatisticChart> queryResult = statisticChartQuery.execute();
      long totalCount = queryResult.totalCount();
      if(totalCount > LIMIT_10) {
        queryResult = statisticChartQuery.limit(Math.toIntExact(totalCount)).execute();
      }
      result = queryResult.getAll().stream().sorted(Comparator.comparing(StatisticChart::getPosition)).collect(Collectors.toList());
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
   * Build with correct order: CREATED, RUNNING, DONE, FAILED
   * 
   * @param caseStateStatistic statistic data
   * @return generated data
   */
  public Map<String, Number> generateDataForCaseStateChart(CaseStateStatistic caseStateStatistic) {
    Map<String, Number> chartData = new LinkedHashMap<>();

    if (caseStateStatistic.getCreated() > 0) {
      chartData.put(Ivy.cms().co(CREATED_CASE_KEY), caseStateStatistic.getCreated());
    }

    if (caseStateStatistic.getRunning() > 0) {
      chartData.put(Ivy.cms().co(RUNNING_CASE_KEY), caseStateStatistic.getRunning());
    }
    
    if (caseStateStatistic.getDone() > 0) {
      chartData.put(Ivy.cms().co(DONE_CASE_KEY), caseStateStatistic.getDone());
    }

    if (caseStateStatistic.getFailed() > 0) {
      chartData.put(Ivy.cms().co(FAILED_CASE_KEY), caseStateStatistic.getFailed());
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

  private DonutChartModel createDonutChartModel(Map<String, Number> chartData) {
    DonutChartModel model = new DonutChartModel();
    ChartData data = new ChartData();
    DonutChartOptions options = new DonutChartOptions();
    data.setLabels(chartData.keySet().stream().collect(Collectors.toList()));
    options.setLegend(buildChartLegend(CHART_LEGEND_POSITION_LEFT, true));

    model.setData(data);
    model.setOptions(options);
    model.setExtender("donutExtender");

    return model;
  }

  private Legend buildChartLegend(String legendPosition, boolean isDisplay) {
    Legend legend = new Legend();
    legend.setPosition(legendPosition);
    legend.setDisplay(isDisplay);
    LegendLabel labels = new LegendLabel();
    labels.setFontColor(statisticColors.getLegendColor());
    legend.setLabels(labels);
    return legend;
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

    DonutChartModel model = createDonutChartModel(chartData);
    DonutChartDataSet dataSet = createDonutChartDataSet(Colors.PRIORITY_COLOR, chartData, isEmptyData);
    model.getData().addChartDataSet(dataSet);

    if (isSetDefaultName) {
      Title title = generateChartTitle(StatisticChartType.TASK_BY_PRIORITY);
      model.getOptions().setTitle(title);
    }

    return model;
  }

  private Title generateChartTitle(StatisticChartType chartType) {
    Title title = new Title();
    title.setDisplay(true);
    title.setText(Ivy.cms().co(chartType.getCmsUri()));
    return title;
  }

  private DonutChartDataSet createDonutChartDataSet(String priorityColor, Map<String, Number> chartData,
      boolean isEmptyData) {
    DonutChartDataSet dataSet = new DonutChartDataSet();
    dataSet.setData(chartData.values().stream().collect(Collectors.toList()));
    if (!isEmptyData) {
      if (priorityColor == Colors.PRIORITY_COLOR) {
        dataSet.setBackgroundColor(Colors.getPriorityColors(chartData, statisticColors));
      } else {
        dataSet.setBackgroundColor(Colors.getCaseStateColors(chartData, statisticColors));
      }
    }
    return dataSet;
  }

  /**
   * set StatisticColors to session cache
   * @param statisticColors
   */
  public void setStatisticsColors(StatisticColors statisticColors) {
    IvyCacheService.newInstance().setSessionCache(IvyCacheIdentifier.STATISTIC_COLOR,
        IvyCacheIdentifier.STATISTIC_COLOR, statisticColors);
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
    Map<Object, Number> chartData = generateDataForTaskByExpiryOverviewChart(statisticData, selectedValue,
        previousSelectedMonth, previousSelectedWeek);

    BarChartModel model = new BarChartModel();
    ChartData data = new ChartData();
    BarChartDataSet dataSet = new BarChartDataSet();
    BarChartOptions options = new BarChartOptions();
    CartesianScales scales = new CartesianScales();

    options.setLegend(buildChartLegend(CHART_LEGEND_POSITION_BOTTOM, false));

    if (chartData.size() != 0) {
      buildBarChartDataSet(chartData, data, dataSet);
      buildExpiryColorBySelectedValue(selectedValue, dataSet);

      String label = Ivy.cms().co(EXPIRY_PERIOD_CMS);
      if (selectDayOfWeek(selectedValue)) {
        label = label + " " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/hour");
      }
      scales.addXAxesData(createLinearAxes(CHART_LEGEND_POSITION_LEFT, label));
      scales.addYAxesData(createLinearAxes(CHART_LEGEND_POSITION_BOTTOM, Ivy.cms().co(TASK_CMS)));
    }
    data.addChartDataSet(dataSet);

    if (isSetDefaultName) {
      options.setTitle(generateChartTitle(StatisticChartType.TASK_BY_EXPIRY));
    }
    options.setScales(scales);
    model.setData(data);
    model.setOptions(options);
    model.setExtender("taskByExpiryChartExtender");

    return model;
  }

  private void buildBarChartDataSet(Map<Object, Number> chartData, ChartData data, BarChartDataSet dataSet) {
    dataSet.setData(chartData.values().stream().collect(Collectors.toList()));
    data.setLabels(chartData.keySet().stream().collect(Collectors.toList()));
  }

  private BarChartDataSet buildExpiryColorBySelectedValue(String selectedValue, BarChartDataSet dataSet) {
    List<String> bgColor = new ArrayList<>();
    String today = Ivy.cms().co(TODAY_EXPIRY_KEY);
    String week = Ivy.cms().co(THIS_WEEK_EXPIRY_KEY);
    String month = Ivy.cms().co(THIS_MONTH_EXPIRY_KEY);
    String year = Ivy.cms().co(THIS_YEAR_EXPIRY_KEY);

    if (selectedValue.equalsIgnoreCase(today)) {
      // Build column color by hours
      bgColor.add(statisticColors.getTaskExpiriedTodayBefore8Color());
      bgColor.add(statisticColors.getTaskExpiriedToday8Color());
      bgColor.add(statisticColors.getTaskExpiriedToday9Color());
      bgColor.add(statisticColors.getTaskExpiriedToday10Color());
      bgColor.add(statisticColors.getTaskExpiriedToday11Color());
      bgColor.add(statisticColors.getTaskExpiriedToday8Color());
      bgColor.add(statisticColors.getTaskExpiriedToday9Color());
      bgColor.add(statisticColors.getTaskExpiriedToday10Color());
      bgColor.add(statisticColors.getTaskExpiriedToday11Color());
      bgColor.add(statisticColors.getTaskExpiriedToday12Color());
      bgColor.add(statisticColors.getTaskExpiriedToday13Color());
      bgColor.add(statisticColors.getTaskExpiriedToday14Color());
      bgColor.add(statisticColors.getTaskExpiriedToday15Color());
      bgColor.add(statisticColors.getTaskExpiriedToday16Color());
      bgColor.add(statisticColors.getTaskExpiriedToday17Color());
      bgColor.add(statisticColors.getTaskExpiriedTodayAfter18Color());
    } else if (selectedValue.equalsIgnoreCase(week)) {
      // Build column color by weekdays
      bgColor.add(statisticColors.getTaskExpiriedMonColor());
      bgColor.add(statisticColors.getTaskExpiriedTueColor());
      bgColor.add(statisticColors.getTaskExpiriedWedColor());
      bgColor.add(statisticColors.getTaskExpiriedThuColor());
      bgColor.add(statisticColors.getTaskExpiriedFriColor());
      bgColor.add(statisticColors.getTaskExpiriedSatColor());
      bgColor.add(statisticColors.getTaskExpiriedSunColor());
    } else if (selectedValue.equalsIgnoreCase(month)) {
      // Build column color by week of the month
      bgColor.add(statisticColors.getTaskExpiriedFirstWeekColor());
      bgColor.add(statisticColors.getTaskExpiriedSecondWeekColor());
      bgColor.add(statisticColors.getTaskExpiriedThirdWeekColor());
      bgColor.add(statisticColors.getTaskExpiriedFourthWeekColor());
      bgColor.add(statisticColors.getTaskExpiriedFifthWeekColor());
    } else if (selectedValue.equalsIgnoreCase(year)) {
      // Build column color by the month of the year
      bgColor.add(statisticColors.getTaskExpiriedJanColor());
      bgColor.add(statisticColors.getTaskExpiriedFebColor());
      bgColor.add(statisticColors.getTaskExpiriedMarColor());
      bgColor.add(statisticColors.getTaskExpiriedAprColor());
      bgColor.add(statisticColors.getTaskExpiriedMayColor());
      bgColor.add(statisticColors.getTaskExpiriedJuneColor());
      bgColor.add(statisticColors.getTaskExpiriedJulyColor());
      bgColor.add(statisticColors.getTaskExpiriedAugColor());
      bgColor.add(statisticColors.getTaskExpiriedSeptColor());
      bgColor.add(statisticColors.getTaskExpiriedOctColor());
      bgColor.add(statisticColors.getTaskExpiriedNovColor());
      bgColor.add(statisticColors.getTaskExpiriedDecColor());
    } else {
      // Build default column color
      bgColor.add(statisticColors.getTaskExpiriedColor());
      bgColor.add(statisticColors.getTaskExpiriedTodayColor());
      bgColor.add(statisticColors.getTaskExpiriedThisWeekColor());
      bgColor.add(statisticColors.getTaskExpiriedThisMonthColor());
      bgColor.add(statisticColors.getTaskExpiriedThisYearColor());

      List<String> borderColor = new ArrayList<>();
      borderColor.add(StatisticColors.DEFAULT_TASK_EXPIRIED_BORDER);
      borderColor.add(StatisticColors.DEFAULT_TASK_TODAY_BORDER);
      borderColor.add(StatisticColors.DEFAULT_TASK_WEEK_BORDER);
      borderColor.add(StatisticColors.DEFAULT_TASK_MONTH_BORDER);
      borderColor.add(StatisticColors.DEFAULT_TASK_YEAR_BORDER);
      dataSet.setBorderColor(borderColor);
    }

    dataSet.setBackgroundColor(bgColor);
    dataSet.setBorderWidth(1);
    return dataSet;
  }

  private CartesianLinearAxes createLinearAxes(String position, String axesLabel) {
    CartesianLinearAxes newAxesData = new CartesianLinearAxes();
    newAxesData.setPosition(position);
    CartesianLinearTicks ticks = new CartesianLinearTicks();
    ticks.setBeginAtZero(true);
    newAxesData.setTicks(ticks);

    CartesianScaleLabel scaleLabel = new CartesianScaleLabel();
    scaleLabel.setDisplay(true);
    scaleLabel.setLabelString(axesLabel);

    newAxesData.setScaleLabel(scaleLabel);

    return newAxesData;
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

    DonutChartModel model = createDonutChartModel(chartData);
    DonutChartDataSet dataSet = createDonutChartDataSet(Colors.STATE_COLOR, chartData, isEmptyData);
    model.getData().addChartDataSet(dataSet);
    if (isSetDefaultName) {
      model.getOptions().setTitle(generateChartTitle(chartType));
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
    ChartData data = new ChartData();
    BarChartDataSet dataSet = new BarChartDataSet();
    BarChartOptions options = new BarChartOptions();
    CartesianScales scales = new CartesianScales();
    if (chartData.size() != 0) {
      buildBarChartDataSet(new HashMap<>(chartData), data, dataSet);

      dataSet.setBackgroundColor(statisticColors.getElapsedTime());
      dataSet.setBorderColor(statisticColors.getElapsedTime());
      
      scales.addXAxesData(createLinearAxes(CHART_LEGEND_POSITION_LEFT, Ivy.cms().co(CASE_CATEGORIES_CMS)));
      scales.addYAxesData(createLinearAxes(CHART_LEGEND_POSITION_BOTTOM, Ivy.cms().co(ELAPSED_TIME_DETAIL_CHART_NAME_CMS)));
    }
    
    data.addChartDataSet(dataSet);
    if (isSetDefaultName) {
      options.setTitle(generateChartTitle(StatisticChartType.ELAPSED_TIME_BY_CASE_CATEGORY));
    }
    options.setScales(scales);
    options.setLegend(buildChartLegend(CHART_LEGEND_POSITION_BOTTOM, false));

    model.setData(data);
    model.setOptions(options);
    model.setExtender("elapsedTimeChartExtender");
    return model;
  }

  private Map<String, Number> getElapsedTimeChartData(ElapsedTimeStatistic statisticData) {
    Map<String, Number> caseCategoryToElapsedTime = generateDataForElapsedTimeChart(statisticData);

    if (caseCategoryToElapsedTime.isEmpty()) {
      caseCategoryToElapsedTime.put(StringUtils.EMPTY, 0);
    }

    for (Iterator<Entry<String, Number>> iterator = caseCategoryToElapsedTime.entrySet().iterator(); iterator.hasNext();) {
      Entry<String, Number> chartDataEntry = iterator.next();
      float floatValueOfChartData = chartDataEntry.getValue().floatValue()/3600;
      BigDecimal roundElapsedTime = new BigDecimal(floatValueOfChartData).setScale(2, RoundingMode.HALF_UP);
      chartDataEntry.setValue(roundElapsedTime);
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
    String taskScaleLabel = StringUtils.EMPTY;

    List<CartesianAxes> scales = Optional.ofNullable(statisticChart.getBarChartModel()).map(BarChartModel::getOptions)
        .map(BarChartOptions::getScales).map(CartesianScales::getXAxes).orElse(new ArrayList<>());
    if (!scales.isEmpty()) {
      taskScaleLabel = Optional.ofNullable(scales.get(0)).map(CartesianAxes::getScaleLabel)
          .map(CartesianScaleLabel::getLabelString).orElse(StringUtils.EMPTY);
    }

    return statisticChart.getType() == StatisticChartType.TASK_BY_EXPIRY
        && taskScaleLabel.contains(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/hour"));
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
      fetchStatisticColor();
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

  /**
   * 
   * Fetch Statistic Color from DEFAULT_CHART_COLORS_PROCESS, and store it to session
   */
  public void fetchStatisticColor() {
    Optional<Object> chartColor = IvyCacheService.newInstance().getSessionCacheValue(IvyCacheIdentifier.STATISTIC_COLOR,
        IvyCacheIdentifier.STATISTIC_COLOR);
    if (chartColor.isPresent()) {
      statisticColors = (StatisticColors) chartColor.get();
    }
  }

  /**
   * Create a BarChart's placeholder when Statistic chart is loading
   * @return BarChartModel
   * 
   */
  public BarChartModel createBarChartPlaceholder() {
    BarChartModel model = new BarChartModel();
    ChartData data = new ChartData();
    BarChartDataSet dataSet = new BarChartDataSet();
    dataSet.setData(new ArrayList<>());
    dataSet.setLabel(LOADING_MESSAGE);
    data.addChartDataSet(dataSet);
    model.setData(data);
    return model;
  }

  /**
   * Create a DonutChart's placeholder when Statistic chart is loading
   * @return DonutChartModel
   * 
   */
  public DonutChartModel createDonutChartPlaceholder() {
    DonutChartModel model = new DonutChartModel();
    Title title = new Title();
    title.setDisplay(true);
    title.setText(LOADING_MESSAGE);
    model.setOptions(new DonutChartOptions());
    model.getOptions().setTitle(title);
    return model;
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

  @SuppressWarnings("unchecked")
  public static String getSelectedValueOfDonutChart(ItemSelectEvent event) {
    try {
      List<String> labels = (List<String>) ((DonutChart) event.getSource()).getModel().getData().getLabels();
      int index = event.getItemIndex();
      return labels.get(index);
    } catch (Exception e) {
      Ivy.log().error(e);
      return StringUtils.EMPTY;
    }
  }

  @SuppressWarnings("unchecked")
  public static String getSelectedValueOfBarChart(ItemSelectEvent event) {
    try {
      List<String> labels = (List<String>) ((BarChart) event.getSource()).getModel().getData().getLabels();
      int index = event.getItemIndex();
      return labels.get(index);
    } catch (Exception e) {
      Ivy.log().error(e);
      return StringUtils.EMPTY;
    }
  }

  public boolean checkStatisticChartNameExisted(long userId, String chartName) {
    List<StatisticChart> statisticChartList = findStatisticChartsByUserId(userId);
    return statisticChartList.stream().filter(chart -> StringUtils.equals(chart.getName(), chartName)).findFirst()
        .isPresent();
  }

  public void removeStatisticChartsByUserId(long userId) {
    List<StatisticChart> result = findStatisticChartsByUserId(userId);
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
