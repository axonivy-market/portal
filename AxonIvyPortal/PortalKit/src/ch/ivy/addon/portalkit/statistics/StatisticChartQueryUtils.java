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
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.RUNNING_CASE_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.TODAY_EXPIRY_KEY;
import static ch.ivyteam.ivy.workflow.WorkflowPriority.EXCEPTION;
import static ch.ivyteam.ivy.workflow.WorkflowPriority.HIGH;
import static ch.ivyteam.ivy.workflow.WorkflowPriority.NORMAL;
import static ch.ivyteam.ivy.workflow.WorkflowPriority.LOW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.event.ItemSelectEvent;

import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.enums.StatisticTimePeriodSelection;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class StatisticChartQueryUtils {

  private static final String FROM_DATE_KEY = "fromDate";
  private static final String TO_DATE_KEY = "toDate";
  
  private StatisticChartQueryUtils() {
    
  }
  
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
    WorkflowPriority priority = null;
    
    if (selectedValue.equals(Ivy.cms().co(EXCEPTION_PRIORITY_KEY))) {
      priority = EXCEPTION;
    } else if (selectedValue.equals(Ivy.cms().co(HIGH_PRIORITY_KEY))) {
      priority = HIGH;
    } else if (selectedValue.equals(Ivy.cms().co(NORMAL_PRIORITY_KEY))) {
      priority = NORMAL;
    } else if (selectedValue.equals(Ivy.cms().co(LOW_PRIORITY_KEY))) {
      priority = LOW;
    }
    return priority != null ? query.where().and().priority().isEqual(priority) : query;
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
    if (StringUtils.equals(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/expired"), selectedValue)) {
      query.where().isExpired().isEqual(true);
      return query;
    } else if (StatisticService.selectHourOfDay(selectedValue)) {
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
      .isLowerThan(toDate).and().isExpired().isEqual(false);

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
      query = StatisticChartQueryUtils.generateCaseQueryForCaseState(statisticChart.getFilter());
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
    if (!isStartTimeFilterEmpty(filter)) {
      taskQuery.where().and(generateTaskQueryForStartTimestamp(filter));
    }

    // Filter by roles
    if (!filter.getIsAllRolesSelected()) {
      taskQuery.where().and(generateTaskQueryForRoles(filter));
    }

    if (!filter.getIsAllTaskPrioritiesSelected()) {
      taskQuery.where().and(generateTaskQueryForTaskPriority(filter));
    }

    if (isGenerateCaseQuery(filter)) {
      taskQuery.where().and().cases(generateCaseQuery(filter, false));
    }
    
    return taskQuery;
  }

  private static boolean isStartTimeFilterEmpty(StatisticFilter filter) {
    return filter.getTimePeriodSelection() == StatisticTimePeriodSelection.CUSTOM
        && filter.getCreatedDateFrom() == null && filter.getCreatedDateTo() == null;
  }

  private static boolean isGenerateCaseQuery(StatisticFilter filter) {
    return !isStartTimeFilterEmpty(filter) || !filter.getIsAllCaseStatesSelected()
        || CollectionUtils.isNotEmpty(filter.getSelectedCaseCategories()) || isCustomFieldFilterNotEmpty(filter);
  }

  private static TaskQuery generateTaskQueryForRoles(StatisticFilter filter) {
    TaskQuery subTaskQueryForRoles = TaskQuery.create();
    if (CollectionUtils.isNotEmpty(filter.getSelectedRoles())) {
      filter.getSelectedRoles().forEach(role -> subTaskQueryForRoles.where().or().activatorName().isEqual(role));
    } else {
      filter.initRoles();
      subTaskQueryForRoles.where().and().activatorUserId().isNull(); //exclude other users
      List<IRole> roles = CollectionUtils.emptyIfNull(filter.getRoles()).stream().filter(role -> role instanceof IRole).map(role -> (IRole)role).collect(Collectors.toList());
      roles.forEach(role -> subTaskQueryForRoles.where().and().activatorName().isNotEqual(role.getMemberName())); //exclude role
    }

    return subTaskQueryForRoles;
  }

  private static TaskQuery generateTaskQueryForStartTimestamp(StatisticFilter filter) {
    TaskQuery subTaskQueryForCreatedDate = TaskQuery.create();
    if(filter.getTimePeriodSelection() == null || filter.getTimePeriodSelection() == StatisticTimePeriodSelection.CUSTOM){
      Date createdDateFrom = filter.getCreatedDateFrom();
      Date createdDateTo = filter.getCreatedDateTo();
      if (createdDateFrom != null || createdDateTo != null) {
        if (createdDateFrom != null) {
          subTaskQueryForCreatedDate.where().startTimestamp().isGreaterOrEqualThan(createdDateFrom);
        }
        if (createdDateTo != null) {
          subTaskQueryForCreatedDate.where().startTimestamp().isLowerOrEqualThan(createdDateTo);
        }
      }
    } else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_WEEK){
      subTaskQueryForCreatedDate.where().startTimestamp().isGreaterOrEqualThan(Dates.getMondayOfLastWeek());
      subTaskQueryForCreatedDate.where().startTimestamp().isLowerOrEqualThan(Dates.getSundayOfLastWeek());
    } else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_MONTH) {
      subTaskQueryForCreatedDate.where().startTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLastMonth());
      subTaskQueryForCreatedDate.where().startTimestamp().isLowerOrEqualThan(Dates.getLastDayOfLastMonth());
    } else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_6_MONTH){
      subTaskQueryForCreatedDate.where().startTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLast6Month());
    }
    return subTaskQueryForCreatedDate;
  }

  /**
   * Generate task query for expiry statistic
   * 
   * @param filter statistic filter
   * @return generated task query
   */
  public static TaskQuery generateTaskQueryForExpiry(StatisticFilter filter) {
    TaskQuery taskQuery = generateTaskQuery(filter);
    filterOnlyTasksExpireInThisYear(taskQuery);
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
    if (!isStartTimeFilterEmpty(filter)) {
      caseQuery.where().and(generateCaseQueryForStartTimestamp(filter));
    }

    // Filter by case state
    if (!filter.getIsAllCaseStatesSelected()) {
      caseQuery.where().and(generateCaseQueryForCaseState(filter, isElapsedStatistic));
    }
    // Filter by case category
    if (isCaseCategoriesNotEmpty(filter)) {
      CaseQuery query = generateCaseQueryForCaseCategory(filter);
      if (query != null) {
        caseQuery.where().and(query);
      }
    }
    
    // Filter by custom field
    if (isCustomFieldFilterNotEmpty(filter)) {
      generateCaseQueryForCustomField(filter, caseQuery);
    }
    return caseQuery;
  }
  
  private static boolean isCustomFieldFilterNotEmpty(StatisticFilter filter) {
    return filter.getCustomFieldFilters() != null && !filter.getCustomFieldFilters().values().isEmpty();
  }

  private static CaseQuery generateCaseQueryForCaseState(StatisticFilter filter, boolean forElapsedStatistic) {
    List<CaseState> selectedCaseStates = Optional.ofNullable(filter.getSelectedCaseStates()).orElse(new ArrayList<>());
    CaseQuery subCaseQueryForSelectedCaseStates = CaseQuery.create();

    if (selectedCaseStates.isEmpty()) {
      List<CaseState> caseStates = Arrays.asList(CaseState.values());
      caseStates.forEach(caseState -> subCaseQueryForSelectedCaseStates.where().and().state().isNotEqual(caseState));
    } else if (forElapsedStatistic) {
      subCaseQueryForSelectedCaseStates.where().or().state().isEqual(CaseState.DONE);
    } else {
      selectedCaseStates.forEach(caseState -> subCaseQueryForSelectedCaseStates.where().or().state().isEqual(caseState));
    }
    return subCaseQueryForSelectedCaseStates;
  }

  private static CaseQuery generateCaseQueryForStartTimestamp(StatisticFilter filter) {
    CaseQuery subCaseQueryForCreatedDate = CaseQuery.create();
    if(filter.getTimePeriodSelection() == null || filter.getTimePeriodSelection() == StatisticTimePeriodSelection.CUSTOM){
      Date createdDateFrom = filter.getCreatedDateFrom();
      Date createdDateTo = filter.getCreatedDateTo();
      if (createdDateFrom != null || createdDateTo != null) {
      
        if (createdDateFrom != null) {
          subCaseQueryForCreatedDate.where().startTimestamp().isGreaterOrEqualThan(createdDateFrom);
        }
        if (createdDateTo != null) {
          subCaseQueryForCreatedDate.where().startTimestamp().isLowerOrEqualThan(createdDateTo);
        }
      }
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_WEEK){
      subCaseQueryForCreatedDate.where().startTimestamp().isGreaterOrEqualThan(Dates.getMondayOfLastWeek());
      subCaseQueryForCreatedDate.where().startTimestamp().isLowerOrEqualThan(Dates.getSundayOfLastWeek());
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_MONTH) {
      subCaseQueryForCreatedDate.where().startTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLastMonth());
      subCaseQueryForCreatedDate.where().startTimestamp().isLowerOrEqualThan(Dates.getLastDayOfLastMonth());
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_6_MONTH) {
      subCaseQueryForCreatedDate.where().startTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLastMonth());
    }
    return subCaseQueryForCreatedDate;
  }
  
  private static void generateCaseQueryForRole(StatisticFilter filter, CaseQuery caseQuery) {
    TaskQuery taskQuery = TaskQuery.create();
    taskQuery.where().and(generateTaskQueryForRoles(filter));
    caseQuery.where().and().tasks(taskQuery);
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
    
    if (isCaseCategoriesNotEmpty(filter)) {
      CaseQuery query = generateCaseQueryForCaseCategory(filter);
      if (query != null) {
        caseQuery.where().and(query);
      }
    }
    
    // Filter by customVarChar
    generateCaseQueryForCustomField(filter, caseQuery);
    
    return caseQuery;
  }

  /**
   * generate case query for case by state
   * 
   * @param filter
   * @return case query for case by state
   */
  public static CaseQuery generateCaseQueryForCaseState(StatisticFilter filter) {
    CaseQuery caseQuery = CaseQuery.create();
    if (isGenerateCaseQuery(filter)) {
      caseQuery = StatisticChartQueryUtils.generateCaseQuery(filter, false);
    }
    if (!filter.getIsAllRolesSelected()) {
      generateCaseQueryForRole(filter, caseQuery);
    }
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
    taskQuery.where().and(generateTaskQueryForEndTimestamp(filter));
    taskQuery.where().and().endTimestamp().isNotNull();
    
    //Filter by DONE state
    taskQuery.where().and().state().isEqual(TaskState.DONE);
    
    //Filter by role which finished task
    if(!filter.getIsAllRolesSelected()) {
      taskQuery.where().and(generateTaskQueryForRoles(filter));
    }
    
    // Filter by task priority
    if(!filter.getIsAllTaskPrioritiesSelected()) {
      taskQuery.where().and(generateTaskQueryForTaskPriority(filter));
    }
   
    return taskQuery;
  }
  
  private static boolean isCaseCategoriesNotEmpty(StatisticFilter filter) {
    return (CollectionUtils.isNotEmpty(filter.getSelectedCaseCategories()))
        || (filter.getCaseCategories() != null && filter.getCaseCategories().getCategories() != null && filter.getCaseCategories().getCategories().length > 0);
  }

  private static CaseQuery generateCaseQueryForCaseCategory(StatisticFilter filter) {
    return Optional.ofNullable(filter).map(StatisticFilter::getCaseCategories).map(StatisticCaseCategoryFilter::buildQuery).orElse(null);
  }

  private static TaskQuery generateTaskQueryForEndTimestamp(StatisticFilter filter) {
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
      }
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_WEEK){
      subTaskFilterForCreatedDate.endTimestamp().isGreaterOrEqualThan(Dates.getMondayOfLastWeek());
      subTaskFilterForCreatedDate.endTimestamp().isLowerOrEqualThan(Dates.getSundayOfLastWeek());
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_MONTH) {
      subTaskFilterForCreatedDate.endTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLastMonth());
      subTaskFilterForCreatedDate.endTimestamp().isLowerOrEqualThan(Dates.getLastDayOfLastMonth());
    }
    else if (filter.getTimePeriodSelection() == StatisticTimePeriodSelection.LAST_6_MONTH){
      subTaskFilterForCreatedDate.endTimestamp().isGreaterOrEqualThan(Dates.getFirstDayOfLast6Month());
    }
    return subTaskQueryForCreatedDate;
  }

  private static TaskQuery generateTaskQueryForTaskPriority(StatisticFilter filter) {
    List<WorkflowPriority> selectedPriorities =
        Optional.ofNullable(filter.getSelectedTaskPriorities()).orElse(new ArrayList<>());
    TaskQuery subTaskQueryForPriority = TaskQuery.create();
    if (!selectedPriorities.isEmpty()) {
      selectedPriorities.forEach(priority -> subTaskQueryForPriority.where().or().priority().isEqual(priority));
    } else {
      List<WorkflowPriority> priorities = Optional.ofNullable(filter.getTaskPriorities()).orElse(new ArrayList<>());
      priorities.forEach(priority -> subTaskQueryForPriority.where().and().priority().isNotEqual(priority));
    }
    return subTaskQueryForPriority;
  }
  
  private static void filterOnlyTasksExpireInThisYear(TaskQuery taskQuery) {
    Date firsDateOfThisYear = StatisticChartTimeUtils.truncateMinutesPart(StatisticChartTimeUtils.getFirstDateOfThisYear());
    Date firsDateOfNextYear = StatisticChartTimeUtils.truncateMinutesPart(DateUtils.addYears(firsDateOfThisYear, 1));

    TaskQuery taskQueryForExpiryDate = TaskQuery.create();
    IFilterQuery filterQueryForExpiryDate = taskQueryForExpiryDate.where();
    filterQueryForExpiryDate.and().expiryTimestamp().isGreaterOrEqualThan(firsDateOfThisYear).and().expiryTimestamp()
        .isLowerThan(firsDateOfNextYear).and().expiryTimestamp().isNotNull();

    taskQuery.where().and(taskQueryForExpiryDate);
  }
  
  private static void generateCaseQueryForCustomField(StatisticFilter filter, CaseQuery caseQuery) {
    Map<String, List<String>> customFieldFilters = filter.getCustomFieldFilters();
    if (customFieldFilters != null) {
      customFieldFilters.forEach((k,v) -> {
        if (CollectionUtils.isNotEmpty(v)) {
          CaseQuery subTaskQueryForCustomVarCharField = CaseQuery.create();                                                                         
          ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery subCaseFilterForCustomVarCharField = subTaskQueryForCustomVarCharField.where();      
          v.forEach(item -> subCaseFilterForCustomVarCharField.or().customField().stringField(k).isEqual(item)); 
          caseQuery.where().and(subTaskQueryForCustomVarCharField); 
        }
      });
    }
  }
}                                                                                                                                             