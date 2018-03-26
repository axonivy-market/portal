package ch.ivy.addon.portalkit.statistics;

import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.AFTER_18;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.BEFORE_8;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.CREATED_CASE_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.DONE_CASE_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.EXCEPTION_PRIORITY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.FAILED_CASE_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.HIGH_PRIORITY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.LOW_PRIORITY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.NORMAL_PRIORITY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.NO_CATEGORY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.RUNNING_CASE_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.TODAY_EXPIRY_KEY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.event.ItemSelectEvent;

import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;


public class StatisticChartQueryUtils {

  private StatisticChartQueryUtils() {
    
  }

  private static final String FROM_DATE_KEY = "fromDate";
  private static final String TO_DATE_KEY = "toDate";
  /**
   * Get updated task query for Task by Priority chart based on selected item
   * 
   * @param event
   * @param statisticChart
   * @return updated task query
   */
  public static TaskQuery getQueryForSelectedItemOfTaskByPriorityChart(ItemSelectEvent event, StatisticChart statisticChart) {
    TaskQuery query = StatisticChartQueryUtils.generateTaskQuery(statisticChart.getFilter());
    String selectedValue = StatisticService.getSelectedValueOfDonutChart(event);

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
  public static TaskQuery getQueryForSelectedItemOfTaskByExpiryChart(ItemSelectEvent event, StatisticChart statisticChart, String previousSelectedMonth, String previousSelectedWeek, String previousSelectedDay) {
    TaskQuery query = StatisticChartQueryUtils.generateTaskQueryForExpiry(statisticChart.getFilter());
    String selectedValue = StatisticService.getSelectedValueOfBarChart(event);
    Date fromDate;
    Date toDate;
    HashMap<String, Date> fromToDateMap;
    if (StatisticService.selectHourOfDay(selectedValue)) {
      fromToDateMap = calculateTimeRangeOfSelectedHour(selectedValue, previousSelectedMonth, previousSelectedWeek, previousSelectedDay);
      fromDate = fromToDateMap.get(FROM_DATE_KEY);
      toDate = fromToDateMap.get(TO_DATE_KEY);
    } else if (StatisticService.selectDayOfWeek(selectedValue)) {
      fromToDateMap = calculateTimeRangeOfSelectedDay(selectedValue, previousSelectedMonth, previousSelectedWeek);
      fromDate = fromToDateMap.get(FROM_DATE_KEY);
      toDate = fromToDateMap.get(TO_DATE_KEY);
    } else if (StatisticService.selectWeekOfMonth(selectedValue)) {
      fromToDateMap = calculateTimeRangeOfSelectedWeek(selectedValue, previousSelectedMonth);
      fromDate = fromToDateMap.get(FROM_DATE_KEY);
      toDate = fromToDateMap.get(TO_DATE_KEY);
    } else if (StatisticService.selectMonthOfYear(selectedValue)) {
      fromToDateMap = calculateTimeRangeOfSelectedMonth(selectedValue);
      fromDate = fromToDateMap.get(FROM_DATE_KEY);
      toDate = fromToDateMap.get(TO_DATE_KEY);
    } else {
      fromDate = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfThisYear());
      toDate = DateUtils.addYears(fromDate, 1);
    }

    query.where().and().expiryTimestamp().isGreaterOrEqualThan(fromDate).and().expiryTimestamp()
    .isLowerThan(toDate);

    return query;
  }

  private static HashMap<String, Date>  calculateTimeRangeOfSelectedHour(String selectedValue, String previousSelectedMonth, String previousSelectedWeek, String previousSelectedDay) {
    Date fromDate;
    Date toDate;
    HashMap<String, Date> fromToDate = new HashMap<>();
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
    fromToDate.put(FROM_DATE_KEY, fromDate);
    fromToDate.put(TO_DATE_KEY, toDate);
    return fromToDate;
  }

  private static HashMap<String, Date>  calculateTimeRangeOfSelectedDay(String selectedValue, String previousSelectedMonth, String previousSelectedWeek) {
    Date fromDate;
    Date toDate;
    HashMap<String, Date> fromToDate = new HashMap<>();
    if (StringUtils.containsIgnoreCase(selectedValue, Ivy.cms().co(TODAY_EXPIRY_KEY))) {
      fromDate = StatisticChartTimeUtils.truncateMinutesPart(new Date());
    } else {
      int shiftDays = StatisticChartTimeUtils.getShiftDaysFromDayOfWeek(selectedValue);
      fromDate = DateUtils.addDays(StatisticChartTimeUtils.getFirstDateOfWeek(previousSelectedWeek, previousSelectedMonth), shiftDays);
      fromDate = StatisticChartTimeUtils.truncateMinutesPart(fromDate);
    }
    toDate = DateUtils.addDays(fromDate, 1);
    fromToDate.put(FROM_DATE_KEY, fromDate);
    fromToDate.put(TO_DATE_KEY, toDate);
    return fromToDate;
  }

  private static HashMap<String, Date>  calculateTimeRangeOfSelectedWeek(String selectedValue, String previousSelectedMonth) {
    Date fromDate;
    Date toDate;
    HashMap<String, Date> fromToDate = new HashMap<>();
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
    fromToDate.put(FROM_DATE_KEY, fromDate);
    fromToDate.put(TO_DATE_KEY, toDate);
    return fromToDate;
  }

  private static HashMap<String, Date>  calculateTimeRangeOfSelectedMonth(String selectedValue) {
    Date fromDate;
    Date toDate;
    HashMap<String, Date> fromToDate = new HashMap<>();
    fromDate = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfMonth(selectedValue));
    toDate = DateUtils.addMonths(fromDate, 1);
    fromToDate.put(FROM_DATE_KEY, fromDate);
    fromToDate.put(TO_DATE_KEY, toDate);
    return fromToDate;
  }

  /**
   * Get task query for Elapsed Time by Case Category chart based on selected item
   * 
   * @param caseCategory
   * @return task query for Elapsed Time by Case Category chart
   */
  public static TaskQuery getQueryForSelectedItemElapsedTime(String caseCategory) {
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
  public static CaseQuery getQueryForSelectedItemByCaseByState(ItemSelectEvent event, StatisticChart statisticChart) {
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
    
    String selectedValue = StatisticService.getSelectedValueOfDonutChart(event);

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

  /**
   * Generate task query for statistic
   * 
   * @param filter statistic filter
   * @return generated task query
   */
  public static TaskQuery generateTaskQuery(StatisticFilter filter) {
    TaskQuery taskQuery = TaskQuery.create();

    // Filter by created date
    generateTaskQueryForStartTimestamp(filter, taskQuery);
    
    // Filter by roles
    generateTaskQueryForRoles(filter, taskQuery);

    generateTaskQueryForTaskPriority(filter, taskQuery);

    taskQuery.where().and().cases(generateCaseQuery(filter, false));
    return taskQuery;
  }

  private static void generateTaskQueryForRoles(StatisticFilter filter, TaskQuery taskQuery) {
    List<String> selectedRoles = Optional.ofNullable(filter.getSelectedRoles()).orElse(new ArrayList<>());
    if (!selectedRoles.isEmpty()) {
      TaskQuery subTaskQueryForRoles = TaskQuery.create();
      IFilterQuery subTaskFilterForRoles = subTaskQueryForRoles.where();

      selectedRoles.forEach(role -> subTaskFilterForRoles.or().activatorName().isEqual(role));
      taskQuery.where().and(subTaskQueryForRoles);
    }
  }

  private static void generateTaskQueryForStartTimestamp(StatisticFilter filter, TaskQuery taskQuery) {
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
  public static TaskQuery generateTaskQueryForExpiry(StatisticFilter filter) {
    TaskQuery taskQuery = generateTaskQuery(filter);
    taskQuery = filterOnlyTasksExpireInThisYear(taskQuery);
    return taskQuery;
  }

  /**
   * Generate case query for statistic
   * 
   * @param filter statistic filter
   * @param isElapsedStatistic for elapsed statistic
   * @return generated case query
   */
  public static CaseQuery generateCaseQuery(StatisticFilter filter, boolean isElapsedStatistic) {
    CaseQuery caseQuery = CaseQuery.create();

    // Filter by created date
    generateCaseQueryForStartTimestamp(filter, caseQuery);

    // Filter by case state
    generateCaseQueryForCaseState(filter, isElapsedStatistic, caseQuery);

    // Filter by case category
    generateCaseQueryForCaseCategory(filter, caseQuery);

    return caseQuery;
  }

  private static void generateCaseQueryForCaseCategory(StatisticFilter filter, CaseQuery caseQuery) {
    List<String> selectedCaseCategories =
        Optional.ofNullable(filter.getSelectedCaseCategories()).orElse(new ArrayList<>());
    CaseQuery subCaseQueryForSelectedCaseCategories = CaseQuery.create();
    ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery subCaseFilterForSelectedCaseCategories =
        subCaseQueryForSelectedCaseCategories.where();
    if (selectedCaseCategories.isEmpty()) {
      List<String> caseCategories = Optional.ofNullable(filter.getCaseCategories()).orElse(new ArrayList<>());
      caseCategories.forEach(category -> {
        if (StringUtils.equals(category, Ivy.cms().co(NO_CATEGORY_CMS))) {
          subCaseFilterForSelectedCaseCategories.and().category().isNotNull();
        } else {
          subCaseFilterForSelectedCaseCategories.and().category().isNotEqual(category);
        }
      });
    } else {
      selectedCaseCategories.forEach(category -> {
        if (StringUtils.equals(category, Ivy.cms().co(NO_CATEGORY_CMS))) {
          subCaseFilterForSelectedCaseCategories.or().category().isNull();
        } else {
          subCaseFilterForSelectedCaseCategories.or().category().isEqual(category);
        }
      });
    }
    caseQuery.where().and(subCaseQueryForSelectedCaseCategories);
  }

  private static void generateCaseQueryForCaseState(StatisticFilter filter, boolean forElapsedStatistic,
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

  private static void generateCaseQueryForStartTimestamp(StatisticFilter filter, CaseQuery caseQuery) {
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
  public static CaseQuery generateCaseQueryForCaseHaveFinishedTask(StatisticFilter filter) {
      CaseQuery caseQuery = generateCaseQuery(filter, false);
      caseQuery.where().and().tasks(generateTaskQueryForFinishedTask(filter));
      return caseQuery;
  }
  
  /**
   * generate case query by finished time
   * @param filter
   * @return case query by finished time
   */
  public static CaseQuery generateCaseQueryByFinishedTime(StatisticFilter filter) {
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

  private static void generateCaseQueryForEndTimestamp(StatisticFilter filter, CaseQuery caseQuery) {
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
  
  private static TaskQuery generateTaskQueryForFinishedTask(StatisticFilter filter) {
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

  private static void generateTaskQueryForEndTimestamp(StatisticFilter filter, TaskQuery taskQuery) {
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

  private static void generateTaskQueryForTaskPriority(StatisticFilter filter, TaskQuery taskQuery) {
    List<WorkflowPriority> selectedPriorities =
        Optional.ofNullable(filter.getSelectedTaskPriorities()).orElse(new ArrayList<>());
    if (!selectedPriorities.isEmpty()) {
      TaskQuery subTaskQueryForPriority = TaskQuery.create();
      IFilterQuery subTaskFilterForPriority = subTaskQueryForPriority.where();

      selectedPriorities.forEach(priority -> subTaskFilterForPriority.or().priority().isEqual(priority));
      taskQuery.where().and(subTaskQueryForPriority);
    }
  }
  
  private static TaskQuery filterOnlyTasksExpireInThisYear(TaskQuery taskQuery) {
    TaskQuery result = TaskQuery.fromJson(taskQuery.asJson());
    Date firsDateOfThisYear = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfThisYear());
    Date firsDateOfNextYear = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addYears(firsDateOfThisYear, 1));

    TaskQuery taskQueryForExpiryDate = TaskQuery.create();
    IFilterQuery filterQueryForExpiryDate = taskQueryForExpiryDate.where();
    filterQueryForExpiryDate.and().expiryTimestamp().isGreaterOrEqualThan(firsDateOfThisYear).and().expiryTimestamp()
        .isLowerThan(firsDateOfNextYear).and().expiryTimestamp().isNotNull();

    result.where().and(taskQueryForExpiryDate);
    return result;
  }

}
