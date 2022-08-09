package ch.ivy.addon.portalkit.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;

import ch.ivy.addon.portalkit.bo.CaseCategoryStatistic;
import ch.ivy.addon.portalkit.bo.CaseStateStatistic;
import ch.ivy.addon.portalkit.bo.ExpiryStatistic;
import ch.ivy.addon.portalkit.bo.TaskCategoryStatistic;
import ch.ivy.addon.portalkit.bo.TaskStateStatistic;
import ch.ivy.addon.portalkit.datamodel.DashboardCaseLazyDataModel;
import ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.util.TimesUtils;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.TaskState;

public class DashboardWidgetInformationService {

  public static final String TASKS_EXPIRE_TODAY = "Tasks_Expire_Today";
  public static final String TASKS_EXPIRE_THIS_WEEK = "Tasks_Expire_This_Week";
  private static final String TASK_CRITERIA_PARAM = "criteria";
  private static final String CASE_CRITERIA_PARAM = "caseSearchCriteria";
  private static final String ANALYZE_TASK_STATE = "analyzeTaskStateStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)";
  private static final String ANALYZE_TASK_EXPIRY = "analyzeExpiryStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)";
  private static final String ANALYZE_TASK_CATEGORY = "analyzeTaskCategoryStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)";
  private static final String ANALYZE_CASE_STATE = "analyzeCaseStateStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)";
  private static final String ANALYZE_CASE_CATEGORY = "analyzeCaseCategoryStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)";

  private static DashboardWidgetInformationService instance;

  public static DashboardWidgetInformationService getInstance() {
    if (instance == null) {
      instance = new DashboardWidgetInformationService();
    }
    return instance;
  }

  public Map<TaskState, Long> buildStatisticOfTaskByState(DashboardTaskLazyDataModel dataModel) {
    Map<String, Object> params = new HashMap<>();
    params.put(TASK_CRITERIA_PARAM, generateTaskSearchCriteriaWithoutOrderByClause(dataModel));
    Map<String, Object> response = IvyAdapterService.startSubProcess(ANALYZE_TASK_STATE, params,
          Arrays.asList(PortalLibrary.PORTAL.getValue()));

    Map<TaskState, Long> result = new HashMap<>();
    TaskStateStatistic taskStateStatistic = (TaskStateStatistic) response.get("taskStateStatistic");
    for (Entry<Integer, Long> entry : taskStateStatistic.getNumberOfTasksByState().entrySet()) {
      if (entry.getValue() != 0) {
        result.put(TaskState.valueOf(entry.getKey()), entry.getValue());
      }
    }
    Map<TaskState, Long> taskByStateStatistic = result.entrySet().stream()
          .sorted(Comparator.comparingInt(s -> s.getKey().ordinal()))
          .collect(collectToTaskStateMap());
    return taskByStateStatistic;
  }

  private static Collector<Entry<TaskState, Long>, ?, LinkedHashMap<TaskState, Long>> collectToTaskStateMap() {
    return Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
        LinkedHashMap::new);
  }

  public Map<String, Long> buildStatisticOfTaskExpiry(DashboardTaskLazyDataModel dataModel) {
    Map<String, Long> numberOfTasksExpireMap = new HashMap<>();
    long numberOfTasksExpireToday = 0L;
    long numberOfTasksExpireThisWeek = 0L;

    Map<String, Object> params = new HashMap<>();
    params.put("taskSearchCriteria", generateTaskSearchCriteriaWithoutOrderByClause(dataModel));

    Map<String, Object> response = IvyAdapterService.startSubProcess(ANALYZE_TASK_EXPIRY, params,
          Arrays.asList(PortalLibrary.PORTAL.getValue()));
    var expiryStatistic = (ExpiryStatistic) response.get("expiryStatistic");

    for (Entry<Date, Long> entry : expiryStatistic.getNumberOfTasksByExpiryTime().entrySet()) {
      if (DateUtils.isSameDay(new Date(), entry.getKey())) {
        numberOfTasksExpireToday++;
        numberOfTasksExpireThisWeek++;
      } else if (TimesUtils.isDateInCurrentWeek(entry.getKey())) {
        numberOfTasksExpireThisWeek++;
      }
    }
    numberOfTasksExpireMap.put(TASKS_EXPIRE_TODAY, numberOfTasksExpireToday);
    numberOfTasksExpireMap.put(TASKS_EXPIRE_THIS_WEEK, numberOfTasksExpireThisWeek);
    return numberOfTasksExpireMap;
  }

  public Map<String, Long> buildStatisticOfTaskByCategory(DashboardTaskLazyDataModel dataModel) {
    Map<String, Long> taskByCategoryStatistic = new HashMap<>();
    Map<String, Object> params = new HashMap<>();
    params.put(TASK_CRITERIA_PARAM, generateTaskSearchCriteriaWithoutOrderByClause(dataModel));

    Map<String, Object> response = IvyAdapterService.startSubProcess(ANALYZE_TASK_CATEGORY, params,
          Arrays.asList(PortalLibrary.PORTAL.getValue()));

    var taskCategoryStatistic = (TaskCategoryStatistic) response.get("taskCategoryStatistic");
    taskByCategoryStatistic.putAll(taskCategoryStatistic.getNumberOfTasksByCategory());
    return taskByCategoryStatistic;
  }

  private TaskSearchCriteria generateTaskSearchCriteriaWithoutOrderByClause(DashboardTaskLazyDataModel dataModel) {
    var taskSearchCriteria = new TaskSearchCriteria();
    taskSearchCriteria.setFinalTaskQuery(dataModel.getCriteria().buildQueryWithoutOrderByClause());
    return taskSearchCriteria;
  }

  public Map<CaseState, Long> buildStatisticOfCaseByState(DashboardCaseLazyDataModel dataModel) {
    Map<String, Object> params = new HashMap<>();
    params.put(CASE_CRITERIA_PARAM, generateCaseSearchCriteriaWithoutOrderByClause(dataModel));

    var response = IvyAdapterService.startSubProcess(ANALYZE_CASE_STATE, params,
        Arrays.asList(PortalLibrary.PORTAL.getValue()));

    var caseStateStatistic = (CaseStateStatistic) response.get("caseStateStatistic");
    Map<CaseState, Long> result = new HashMap<>();
    Optional<CaseColumnModel> caseStateColumn = dataModel.getCriteria().getColumns().stream()
        .filter(column -> DashboardStandardCaseColumn.STATE.getField().equalsIgnoreCase(column.getField())).findFirst();
    List<CaseState> selectedStates = null;
    if (caseStateColumn.isPresent()) {
      selectedStates = ((ch.ivy.addon.portalkit.dto.dashboard.casecolumn.StateColumnModel) caseStateColumn.get()).getStates();
      for (CaseState state : selectedStates) {
        switch (state) {
          case CREATED:
            result.put(CaseState.CREATED, caseStateStatistic.getCreated());
            break;
          case DONE:
            result.put(CaseState.DONE, caseStateStatistic.getDone());
            break;
          case RUNNING:
            result.put(CaseState.RUNNING, caseStateStatistic.getRunning());
            break;
          case DESTROYED:
            result.put(CaseState.DESTROYED, caseStateStatistic.getFailed());
            break;
          default:
            break;
        }
      }
    }

    if (!caseStateColumn.isPresent() || CollectionUtils.isEmpty(selectedStates)) {
      result.put(CaseState.CREATED, caseStateStatistic.getCreated());
      result.put(CaseState.DONE, caseStateStatistic.getDone());
      result.put(CaseState.DESTROYED, caseStateStatistic.getFailed());
      result.put(CaseState.RUNNING, caseStateStatistic.getRunning());
    }

    return result.entrySet().stream().sorted(Comparator.comparingInt(s -> s.getKey().ordinal()))
        .collect(collectToCaseStateMap());
  }

  private Collector<Entry<CaseState, Long>, ?, LinkedHashMap<CaseState, Long>> collectToCaseStateMap() {
    return Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
        LinkedHashMap::new);
  }

  public Map<String, Long> buildStatisticOfCaseByCategory(DashboardCaseLazyDataModel dataModel) {
    Map<String, Long> caseByCategoryStatistic = new HashMap<>();
    Map<String, Object> params = new HashMap<>();
    params.put(CASE_CRITERIA_PARAM, generateCaseSearchCriteriaWithoutOrderByClause(dataModel));

    var response = IvyAdapterService.startSubProcess(ANALYZE_CASE_CATEGORY, params,
          Arrays.asList(PortalLibrary.PORTAL.getValue()));

    var caseCategoryStatistic = (CaseCategoryStatistic) response.get("caseCategoryStatistic");
    caseByCategoryStatistic.putAll(caseCategoryStatistic.getNumberOfCasesByCategory());
    return caseByCategoryStatistic;
  }

  private CaseSearchCriteria generateCaseSearchCriteriaWithoutOrderByClause(DashboardCaseLazyDataModel dataModel) {
    var caseSearchCriteria = new CaseSearchCriteria();
    caseSearchCriteria.setFinalCaseQuery(dataModel.getCriteria().buildQueryWithoutOrderByClause());
    return caseSearchCriteria;
  }

  public Map<ProcessType, Long> buildStatisticOfProcessByType(List<DashboardProcess> processes) {
    if (CollectionUtils.isEmpty(processes)) {
      return new HashMap<>();
    }
    Map<ProcessType, Long> processByTypeStatistic = new HashMap<>();
    Long numberOfIvyProcesses = 0l;
    Long numberOfExpressProcesses = 0l;
    Long numberOfExternalLink = 0l;
    for (var process : processes) {
      if (process.getType().equals(ProcessType.IVY_PROCESS)) {
        numberOfIvyProcesses++;
      } else if (process.getType().equals(ProcessType.EXPRESS_PROCESS)) {
        numberOfExpressProcesses++;
      } else {
        numberOfExternalLink++;
      }
    }
    processByTypeStatistic.put(ProcessType.IVY_PROCESS, numberOfIvyProcesses);
    processByTypeStatistic.put(ProcessType.EXPRESS_PROCESS, numberOfExpressProcesses);
    processByTypeStatistic.put(ProcessType.EXTERNAL_LINK, numberOfExternalLink);
    return processByTypeStatistic;
  }
}
