package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
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

import com.axonivy.portal.bo.ItemByCategoryStatistic;
import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.dto.dashboard.WidgetInformationCategoryStatisticData;

import ch.ivy.addon.portalkit.bo.CaseStateStatistic;
import ch.ivy.addon.portalkit.bo.ExpiryStatistic;
import ch.ivy.addon.portalkit.bo.TaskStateStatistic;
import ch.ivy.addon.portalkit.datamodel.DashboardCaseLazyDataModel;
import ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.TimesUtils;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public class DashboardWidgetInformationService {

  public static final String TASKS_EXPIRE_TODAY = "Tasks_Expire_Today";
  public static final String TASKS_EXPIRE_THIS_WEEK = "Tasks_Expire_This_Week";
  private static final String TASK_CRITERIA_PARAM = "criteria";
  private static final String CASE_CRITERIA_PARAM = "caseSearchCriteria";
  private static final String ANALYZE_TASK_STATE = "analyzeTaskBusinessStateStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)";
  private static final String ANALYZE_TASK_EXPIRY = "analyzeExpiryStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)";
  private static final String ANALYZE_TASK_CATEGORY = "analyzeTaskCategoryStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)";
  private static final String ANALYZE_CASE_STATE = "analyzeCaseBusinessStateStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)";
  private static final String ANALYZE_CASE_CATEGORY = "analyzeCaseCategoryStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)";

  private static DashboardWidgetInformationService instance;

  public static DashboardWidgetInformationService getInstance() {
    if (instance == null) {
      instance = new DashboardWidgetInformationService();
    }
    return instance;
  }

  public Map<TaskBusinessState, Long> buildStatisticOfTaskByState(DashboardTaskLazyDataModel dataModel) {
    Map<String, Object> params = new HashMap<>();
    params.put(TASK_CRITERIA_PARAM, generateTaskSearchCriteriaWithoutOrderByClause(dataModel));
    Map<String, Object> response = IvyAdapterService.startSubProcessInProjectAndAllRequired(ANALYZE_TASK_STATE, params);

    Map<TaskBusinessState, Long> taskStateResult = new HashMap<>();
    TaskStateStatistic taskStateStatistic = (TaskStateStatistic) response.get("taskStateStatistic");
    for (Entry<Integer, Long> entry : taskStateStatistic.getNumberOfTasksByState().entrySet()) {
      if (entry.getValue() != 0) {
        taskStateResult.put(TaskBusinessState.valueOf(entry.getKey()), entry.getValue());
      }
    }
    Map<TaskBusinessState, Long> taskByStateStatistic = taskStateResult.entrySet().stream()
        .sorted(Comparator.comparingInt(s -> s.getKey().ordinal())).collect(collectToTaskStateMap());
    return taskByStateStatistic;
  }

  private static Collector<Entry<TaskBusinessState, Long>, ?, LinkedHashMap<TaskBusinessState, Long>> collectToTaskStateMap() {
    return Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
        LinkedHashMap::new);
  }

  public Map<String, Long> buildStatisticOfTaskExpiry(DashboardTaskLazyDataModel dataModel) {
    Map<String, Long> numberOfTasksExpireMap = new HashMap<>();
    long numberOfTasksExpireToday = 0L;
    long numberOfTasksExpireThisWeek = 0L;

    Map<String, Object> params = new HashMap<>();
    params.put("taskSearchCriteria", generateTaskSearchCriteriaWithoutOrderByClause(dataModel));

    Map<String, Object> response = IvyAdapterService.startSubProcessInProjectAndAllRequired(ANALYZE_TASK_EXPIRY, params);
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

  @SuppressWarnings("unchecked")
  public List<WidgetInformationCategoryStatisticData> buildStatisticOfTaskByCategory(DashboardTaskLazyDataModel dataModel) {
    List<WidgetInformationCategoryStatisticData> taskByCategoryStatistic = new ArrayList<>();
    Map<String, Object> params = new HashMap<>();
    params.put(TASK_CRITERIA_PARAM, generateTaskSearchCriteriaWithoutOrderByClause(dataModel));

    Map<String, Object> response = IvyAdapterService.startSubProcessInProjectAndAllRequired(ANALYZE_TASK_CATEGORY, params);

    List<ItemByCategoryStatistic> taskByCategoryStatistics = (List<ItemByCategoryStatistic>) response.get("taskByCategoryStatistics");
    taskByCategoryStatistics.forEach(cate -> {
      WidgetInformationCategoryStatisticData data = new WidgetInformationCategoryStatisticData();
      data.setCategoryDisplayName(cate.getDisplayName());
      data.setCategoryDisplayPath(cate.getDisplayPath());
      data.setSummary(cate.getValue());
      taskByCategoryStatistic.add(data);
    });
    return taskByCategoryStatistic;
  }

  private TaskSearchCriteria generateTaskSearchCriteriaWithoutOrderByClause(DashboardTaskLazyDataModel dataModel) {
    var taskSearchCriteria = new TaskSearchCriteria();
    taskSearchCriteria.setCustomTaskQuery(
        dataModel.getCriteria().buildQueryWithoutOrderByClause());
    taskSearchCriteria.setAdminQuery(PermissionUtils.checkReadAllCasesPermission());
    return taskSearchCriteria;
  }

  public Map<CaseBusinessState, Long> buildStatisticOfCaseByState(DashboardCaseLazyDataModel dataModel) {
    Map<String, Object> params = new HashMap<>();
    params.put(CASE_CRITERIA_PARAM, generateCaseSearchCriteriaWithoutOrderByClause(dataModel));

    var response = IvyAdapterService.startSubProcessInProjectAndAllRequired(ANALYZE_CASE_STATE, params);

    var caseStateStatistic = (CaseStateStatistic) response.get("caseStateStatistic");
    Map<CaseBusinessState, Long> result = new HashMap<>();
    Optional<CaseColumnModel> caseStateColumn = dataModel.getCriteria().getColumns().stream()
        .filter(column -> DashboardStandardCaseColumn.STATE.getField().equalsIgnoreCase(column.getField())).findFirst();
    List<CaseBusinessState> selectedStates = null;
    if (caseStateColumn.isPresent()) {
      selectedStates = ((ch.ivy.addon.portalkit.dto.dashboard.casecolumn.StateColumnModel) caseStateColumn.get()).getStates();
      for (CaseBusinessState state : selectedStates) {
        switch (state) {
          case OPEN:
            result.put(CaseBusinessState.OPEN, caseStateStatistic.getOpen());
            break;
          case DONE:
            result.put(CaseBusinessState.DONE, caseStateStatistic.getDone());
            break;
          case DESTROYED:
            result.put(CaseBusinessState.DESTROYED, caseStateStatistic.getFailed());
            break;
          default:
            break;
        }
      }
    }

    if (!caseStateColumn.isPresent() || CollectionUtils.isEmpty(selectedStates)) {
      result.put(CaseBusinessState.OPEN, caseStateStatistic.getOpen());
      result.put(CaseBusinessState.DONE, caseStateStatistic.getDone());
      result.put(CaseBusinessState.DESTROYED, caseStateStatistic.getFailed());
    }

    return result.entrySet().stream().sorted(Comparator.comparingInt(s -> s.getKey().ordinal()))
        .collect(collectToCaseStateMap());
  }

  private Collector<Entry<CaseBusinessState, Long>, ?, LinkedHashMap<CaseBusinessState, Long>> collectToCaseStateMap() {
    return Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
        LinkedHashMap::new);
  }

  @SuppressWarnings("unchecked")
  public List<WidgetInformationCategoryStatisticData> buildStatisticOfCaseByCategory(DashboardCaseLazyDataModel dataModel) {
    List<WidgetInformationCategoryStatisticData> caseByCategoryStatistics = new ArrayList<>();
    Map<String, Object> params = new HashMap<>();
    params.put(CASE_CRITERIA_PARAM, generateCaseSearchCriteriaWithoutOrderByClause(dataModel));

    var response = IvyAdapterService.startSubProcessInProjectAndAllRequired(ANALYZE_CASE_CATEGORY, params);

    List<ItemByCategoryStatistic> caseByCategoryStatistic = (List<ItemByCategoryStatistic>) response.get("caseByCategoryStatistic"); 
    caseByCategoryStatistic.forEach(cate -> {
      WidgetInformationCategoryStatisticData data = new WidgetInformationCategoryStatisticData();
      data.setCategoryDisplayName(cate.getDisplayName());
      data.setCategoryDisplayPath(cate.getDisplayPath());
      data.setSummary(cate.getValue());
      caseByCategoryStatistics.add(data);
    });
    return caseByCategoryStatistics;
  }

  private CaseSearchCriteria generateCaseSearchCriteriaWithoutOrderByClause(DashboardCaseLazyDataModel dataModel) {
    var caseSearchCriteria = new CaseSearchCriteria();
    caseSearchCriteria.setCustomCaseQuery(
        dataModel.getCriteria().buildQueryWithoutOrderByClause());
    caseSearchCriteria.setAdminQuery(PermissionUtils.checkReadAllCasesPermission());
    return caseSearchCriteria;
  }

  public Map<ProcessType, Long> buildStatisticOfProcessByType(List<DashboardProcess> processes) {
    if (CollectionUtils.isEmpty(processes)) {
      return new HashMap<>();
    }
    Map<ProcessType, Long> processByTypeStatistic = new HashMap<>();
    Long numberOfIvyProcesses = 0l;
    Long numberOfExternalLink = 0l;
    for (var process : processes) {
      if (process.getType().equals(ProcessType.IVY_PROCESS)) {
        numberOfIvyProcesses++;
      } else {
        numberOfExternalLink++;
      }
    }
    processByTypeStatistic.put(ProcessType.IVY_PROCESS, numberOfIvyProcesses);
    processByTypeStatistic.put(ProcessType.EXTERNAL_LINK, numberOfExternalLink);
    return processByTypeStatistic;
  }
}
