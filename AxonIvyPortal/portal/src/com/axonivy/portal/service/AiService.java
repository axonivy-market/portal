package com.axonivy.portal.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.axonivy.portal.components.dto.AiResultDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.enums.AIState;
import com.axonivy.portal.components.publicapi.AiAssistantAPI;
import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.axonivy.portal.components.util.UserUtils;
import com.axonivy.portal.util.AiToolUtils;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.ivydata.service.impl.DashboardCaseService;
import ch.ivy.addon.portalkit.ivydata.service.impl.DashboardTaskService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class AiService {

  private static final String FIND_TASK_AI_RESULT_DEFAULT_PATTERN = "ID: %s, Name: %s, Description: %s, State: %s, Priority %s";
  private static final String FIND_CASE_AI_RESULT_DEFAULT_PATTERN = "ID: %s, Name: %s, Description: %s, State: %s";
  private static final String FIND_USERS_AI_RESULT_DEFAULT_PATTERN = "Username: %s, Name: %s, Email: %s, State: %s";

  private static final String TASK_PROCESS_PATH = "/portal/AI Tool Processes/PortalTools/findTasksTool.ivp";
  private static final String CASE_PROCESS_PATH = "/portal/AI Tool Processes/PortalTools/findCasesTool.ivp";
  private static final String PROCESS_PROCESS_PATH = "/portal/AI Tool Processes/PortalTools/findProcessesTool.ivp";

  private static AiService instance;

  public static AiService getInstance() {
    if (instance == null) {
      instance = new AiService();
    }
    return instance;
  }

  public AiResultDTO generateFindTasksAiResult(String taskName,
      String taskDescription, String taskState, String taskPriority) {
    AiResultDTO result = new AiResultDTO();

    Map<String, String> params = new HashMap<>();
    params.put("taskName", taskName);
    params.put("taskDescription", taskDescription);
    params.put("taskState", taskState);
    params.put("taskPriority", taskPriority);

    String processPath = IApplication.current().getName()
        .concat(TASK_PROCESS_PATH);
    AiAssistantAPI.addIvyProcessLinkToAiResult(processPath, params, result);

    if (result.getState() == AIState.ERROR) {
      return result;
    }

    TaskDashboardWidget taskWidget = AiToolUtils
        .convertIvyToolToTaskDashboardWidget(taskName, taskDescription,
            taskPriority, taskState);

    // we only show 10 first matched results
    List<ITask> foundTasks = DashboardTaskService.getInstance().findByTaskQuery(
        taskWidget.getDataModel().getCriteria().buildQuery(), 0, 10);

    // Create result for AI based on found tasks
    String foundTasksStr = "Found tasks:".concat(System.lineSeparator());
    for (ITask task : foundTasks) {
      foundTasksStr = foundTasksStr
          .concat(String.format(FIND_TASK_AI_RESULT_DEFAULT_PATTERN,
              Long.valueOf(task.getId()).toString(), task.getName(),
              task.getDescription(), task.getBusinessState().name(),
              task.getPriority().name()))
          .concat(System.lineSeparator());
    }

    result.setResultForAI(foundTasksStr);
    result.setIsMemory(true);
    result.setState(AIState.DONE);
    return result;
  }

  public AiResultDTO generateFindCasesAiResult(String caseName,
      String caseDescription, String caseState) {
    AiResultDTO result = new AiResultDTO();

    Map<String, String> params = new HashMap<>();
    params.put("caseName", caseName);
    params.put("caseDescription", caseDescription);
    params.put("caseState", caseState);

    String processPath = IApplication.current().getName()
        .concat(CASE_PROCESS_PATH);
    AiAssistantAPI.addIvyProcessLinkToAiResult(processPath, params, result);

    if (result.getState() == AIState.ERROR) {
      return result;
    }

    CaseDashboardWidget caseWidget = AiToolUtils
        .convertIvyToolToCaseDashboardWidget(caseName, caseDescription,
            caseState);

    // we only show 10 first matched results
    List<ICase> foundCases = DashboardCaseService.getInstance().findByCaseQuery(
        caseWidget.getDataModel().getCriteria().buildQuery(), 0, 10);

    // Create result for AI based on found tasks
    String foundCasesStr = "Found cases:".concat(System.lineSeparator());
    for (ICase caze : foundCases) {
      foundCasesStr.concat(String.format(FIND_CASE_AI_RESULT_DEFAULT_PATTERN,
          Long.valueOf(caze.getId()).toString(), caze.getName(),
          caze.getDescription(),
          caze.getBusinessState().name()));
      foundCasesStr.concat(System.lineSeparator());
    }

    result.setResultForAI(foundCasesStr);
    result.setIsMemory(true);
    result.setState(AIState.DONE);
    return result;
  }
  
  public AiResultDTO generateFindProcessesAiResult(String processName, String processDescription) {
    AiResultDTO result = new AiResultDTO();
    Map<String, String> params = new HashMap<>();
    params.put("processName", processName);
    params.put("processDescription", processDescription);
    
    String processPath = IApplication.current().getName().concat(PROCESS_PROCESS_PATH);
    AiAssistantAPI.addIvyProcessLinkToAiResult(processPath, params, result);
    String foundProcessStr = "Found processes:".concat(System.lineSeparator());
    result.setResultForAI(foundProcessStr);
    return result;
  }

  public AiResultDTO generateFindUsersAiResult(String username, String role) {
    List<String> roleList = StringUtils.isNotBlank(role) ? Arrays.asList(role)
        : null;
    List<UserDTO> foundUsers = UserUtils.findUsers(username, 0, 101, roleList,
        Arrays.asList(Ivy.session().getSessionUserName()));

    if (CollectionUtils.isNotEmpty(foundUsers)) {
      // Create result for AI based on found users
      String foundUsersStr = "Found users:".concat(System.lineSeparator());
      for (UserDTO user : foundUsers) {
        String userState = user.isEnabled()
            ? Ivy.cms().coLocale("/ch.ivy.addon.portalkit.ui.jsf/common/active",
                Locale.ENGLISH)
            : Ivy.cms().coLocale(
                "/ch.ivy.addon.portalkit.ui.jsf/common/inactive",
                Locale.ENGLISH);
        foundUsersStr.concat(String.format(FIND_USERS_AI_RESULT_DEFAULT_PATTERN,
            user.getName(), user.getDisplayName(), user.getEmail(), userState))
            .concat(System.lineSeparator());
      }

      AiResultDTO result = new AiResultDTO();
      result.setResultForAI(foundUsersStr);
      result.setResult(foundUsersStr);
      result.setIsMemory(true);
      result.setState(AIState.DONE);
      return result;

    } else {
      return AiAssistantAPI.generateErrorAiResult(
          Ivy.cms().co("/Labels/AI/Error/CannotFindUser"));
    }
  }

  public AiResultDTO generateStartTasksAiResult(String taskId) {
    if (!NumberUtils.isDigits(taskId)) {
      return AiAssistantAPI.generateErrorAiResult("Cannot start task");
    }
    TaskQuery query = TaskQuery.create();
    query.where().taskId().isEqual(Long.valueOf(taskId));

    ITask foundTask = Optional.ofNullable(
        DashboardTaskService.getInstance().findByTaskQuery(query, 0, 1).get(0))
        .orElse(null);

    AiResultDTO result = new AiResultDTO();

    if (foundTask == null) {
      return AiAssistantAPI.generateErrorAiResult(
          "Cannot start task");
    }

    result.setResult(PortalNavigatorAPI
        .buildUrlToPortalTaskDetailsPageById(foundTask.getId()));
    result.setResultForAI(null);
    result.setIsMemory(false);
    result.setState(AIState.DONE);
    return result;

  }
}
