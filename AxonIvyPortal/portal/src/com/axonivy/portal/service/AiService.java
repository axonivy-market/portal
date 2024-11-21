package com.axonivy.portal.service;

import java.io.IOException;
import java.util.ArrayList;
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
import com.axonivy.portal.components.persistence.converter.BusinessEntityConverter;
import com.axonivy.portal.components.publicapi.AiAssistantAPI;
import com.axonivy.portal.components.service.impl.ProcessService;
import com.axonivy.portal.components.util.UserUtils;
import com.axonivy.portal.dto.IvyToolParameter;
import com.axonivy.portal.util.AiToolUtils;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.ivydata.service.impl.DashboardCaseService;
import ch.ivy.addon.portalkit.ivydata.service.impl.DashboardTaskService;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class AiService {
  private static final String START_PROCESS_SUCCESSFULLY_FORMAT = "Process **%s** is started successfully";
  private static final String START_TASK_SUCCESSFULLY_FORMAT = "Task **%s(#%s)** is started successfully";

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
      String taskDescription, String taskState, String taskPriority,
      String taskExpiryDateFrom, String taskExpiryDateTo, String onlyMyTask) {
    AiResultDTO result = new AiResultDTO();

    Map<String, String> params = new HashMap<>();
    params.put("taskName", taskName);
    params.put("taskDescription", taskDescription);
    params.put("taskState", taskState);
    params.put("taskPriority", taskPriority);
    params.put("taskExpiryDateFrom", taskExpiryDateFrom);
    params.put("taskExpiryDateTo", taskExpiryDateTo);
    params.put("onlyMyTask", onlyMyTask);

    String processPath = IApplication.current().getName()
        .concat(TASK_PROCESS_PATH);
    AiAssistantAPI.addIframeIvyProcessLinkToAiResult(processPath, params,
        result);

    if (result.getState() == AIState.ERROR) {
      return result;
    }

    TaskDashboardWidget taskWidget = AiToolUtils
        .convertIvyToolToTaskDashboardWidget(taskName, taskDescription,
            taskPriority, taskState, taskExpiryDateFrom, taskExpiryDateTo,
            onlyMyTask);

    // we only show 10 first matched results
    List<ITask> foundTasks = DashboardTaskService.getInstance().findByTaskQuery(
        taskWidget.getDataModel().getCriteria().buildQuery(), 0, 10);

    if (CollectionUtils.isEmpty(foundTasks)) {
      return AiAssistantAPI.generateErrorAiResult(
          Ivy.cms().co("/Labels/AI/Error/NoMatchingTask"));
    }

    // Create result for AI based on found tasks
    String foundTasksStr = Ivy.cms().co("/Labels/AI/FoundTaskHeader")
        .concat(System.lineSeparator());
    for (ITask task : foundTasks) {
      foundTasksStr = foundTasksStr
          .concat(Ivy.cms().co("/Labels/AI/FindTaskAIResult",
              Arrays.asList(Long.valueOf(task.getId()).toString(),
                  task.getName(), task.getDescription(),
                  task.getBusinessState().name(), task.getPriority().name())))
          .concat(System.lineSeparator());
    }

    result.setResultForAI(foundTasksStr);
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
    AiAssistantAPI.addIframeIvyProcessLinkToAiResult(processPath, params,
        result);

    if (result.getState() == AIState.ERROR) {
      return result;
    }

    CaseDashboardWidget caseWidget = AiToolUtils
        .convertIvyToolToCaseDashboardWidget(caseName, caseDescription,
            caseState);

    // we only show 10 first matched results
    List<ICase> foundCases = DashboardCaseService.getInstance().findByCaseQuery(
        caseWidget.getDataModel().getCriteria().buildQuery(), 0, 10);

    if (CollectionUtils.isEmpty(foundCases)) {
      return AiAssistantAPI.generateErrorAiResult(
          Ivy.cms().co("/Labels/AI/Error/NoMatchingCase"));
    }

    // Create result for AI based on found tasks
    String foundCasesStr = Ivy.cms().co("/Labels/AI/FoundCaseHeader")
        .concat(System.lineSeparator());

    for (ICase caze : foundCases) {
      foundCasesStr = foundCasesStr
          .concat(Ivy.cms().co("/Labels/AI/FindCaseAIResult",
              Arrays.asList(Long.valueOf(caze.getId()).toString(),
                  caze.getName(), caze.getDescription(),
                  caze.getBusinessState().name())))
          .concat(System.lineSeparator());
    }

    result.setResultForAI(foundCasesStr);
    result.setState(AIState.DONE);
    return result;
  }

  public AiResultDTO generateFindProcessesAiResult(String processName,
      String processDescription) {
    AiResultDTO result = new AiResultDTO();
    Map<String, String> params = new HashMap<>();
    params.put("processName", processName);
    params.put("processDescription", processDescription);

    String processPath = IApplication.current().getName()
        .concat(PROCESS_PROCESS_PATH);
    AiAssistantAPI.addIframeIvyProcessLinkToAiResult(processPath, params,
        result);

    CompactProcessDashboardWidget resultWidget = (CompactProcessDashboardWidget) AiToolUtils
        .convertIvyToolToProcessDashboardWidget(processName,
            processDescription);
    resultWidget.filterProcessesByUser();
    List<DashboardProcess> processes = resultWidget.getDisplayProcesses();
    if (CollectionUtils.isEmpty(processes)) {
      return AiAssistantAPI.generateErrorAiResult(
          Ivy.cms().co("/Labels/AI/Error/NoMatchingProcesses"));
    }
    String foundProcessStr = Ivy.cms().co("/Labels/AI/FoundProcessesHeader")
        .concat(Integer.toString(processes.size()))
        .concat(System.lineSeparator());
    for (var process : processes) {
      foundProcessStr = foundProcessStr
          .concat(
              Ivy.cms().co("/Labels/AI/FindProcessAIResult",
                  Arrays.asList(process.getId(), process.getName(),
                      process.getDescription())))
          .concat(System.lineSeparator());
    }
    result.setResultForAI(foundProcessStr);
    result.setState(AIState.DONE);
    return result;
  }

  public AiResultDTO generateFindUsersAiResult(String username, String role) {
    List<String> roleList = StringUtils.isNotBlank(role) ? Arrays.asList(role)
        : null;
    List<UserDTO> foundUsers = UserUtils.findUsers(username, 0, 101, roleList,
        Arrays.asList(Ivy.session().getSessionUserName()));

    if (CollectionUtils.isNotEmpty(foundUsers)) {
      // Create result for AI based on found users
      String foundUsersStr = Ivy.cms().co("/Labels/AI/FoundUsersHeader")
          .concat(System.lineSeparator());

      for (UserDTO user : foundUsers) {
        String userState = user.isEnabled()
            ? Ivy.cms().coLocale("/ch.ivy.addon.portalkit.ui.jsf/common/active",
                Locale.ENGLISH)
            : Ivy.cms().coLocale(
                "/ch.ivy.addon.portalkit.ui.jsf/common/inactive",
                Locale.ENGLISH);

        foundUsersStr = foundUsersStr
            .concat(Ivy.cms().co("/Labels/AI/FindUserAIResult",
                Arrays.asList(user.getName(), user.getDisplayName(),
                    user.getEmail(), userState)))
            .concat(System.lineSeparator());
      }

      AiResultDTO result = new AiResultDTO();
      result.setResultForAI(foundUsersStr);
      result.setResult(foundUsersStr);
      result.setState(AIState.DONE);
      return result;

    } else {
      return AiAssistantAPI.generateErrorAiResult(
          Ivy.cms().co("/Labels/AI/Error/CannotFindUser"));
    }
  }

  public AiResultDTO generateStartTasksAiResult(String taskId) {
    if (!NumberUtils.isDigits(taskId)) {
      return AiAssistantAPI.generateErrorAiResult(
          Ivy.cms().co("/Labels/AI/Error/CannotStartTask"));
    }
    TaskQuery query = TaskQuery.create();
    query.where().taskId().isEqual(Long.valueOf(taskId));

    ITask foundTask = Optional.ofNullable(
        DashboardTaskService.getInstance().findByTaskQuery(query, 0, 1).get(0))
        .orElse(null);

    if (foundTask == null || !foundTask
        .canUserResumeTask(Ivy.session().getSessionUser().getUserToken())
        .wasSuccessful()) {
      return AiAssistantAPI.generateErrorAiResult(
          Ivy.cms().co("/Labels/AI/Error/CannotStartTask",
              Arrays.asList(Long.toString(foundTask.getId()))));
    }

    AiResultDTO result = new AiResultDTO();

    result.setResultForAI(
        AiAssistantAPI.generateExecutableResult(
            foundTask.getStartLinkEmbedded().getRelative()));

    result.setResult(String.format(START_TASK_SUCCESSFULLY_FORMAT,
        foundTask.getName(), Long.toString(foundTask.getId())));

    result.setState(AIState.DONE);
    return result;

  }

  public AiResultDTO generateFindTaskDetailsAiResult(String taskId) {
    if (!NumberUtils.isDigits(taskId)) {
      return AiAssistantAPI.generateErrorAiResult(
          Ivy.cms().co("/Labels/AI/Error/NoMatchingTask"));
    }
    TaskQuery query = TaskQuery.create();
    query.where().taskId().isEqual(Long.valueOf(taskId));

    ITask foundTask = Optional.ofNullable(
        DashboardTaskService.getInstance().findByTaskQuery(query, 0, 1).get(0))
        .orElse(null);


    if (foundTask == null) {
      return AiAssistantAPI.generateErrorAiResult(
          Ivy.cms().co("/Labels/AI/Error/NoMatchingTask"));
    }

    AiResultDTO result = new AiResultDTO();

    result.setResultForAI(Ivy.cms().co("/Labels/AI/TaskDetailsResult",
        Arrays.asList(foundTask.getName(), Long.toString(foundTask.getId()),
            foundTask.getDetailLink().getAbsolute())));

    result.setResult(result.getResultForAI());
    result.setState(AIState.DONE);

    return result;

  }

  public AiResultDTO generateAIResultForWebstartableInfoById(String processId) {
    Optional<IWebStartable> foundProcessOptional = ProcessService.getInstance()
        .findAllProcesses().stream()
        .filter(process -> process.getId().contentEquals(processId))
        .findFirst();

    // If cannot find process with the given ID, return error
    if (foundProcessOptional.isEmpty()) {
      return AiAssistantAPI.createSomethingWentWrongError();
    }

    AiResultDTO result = new AiResultDTO();
    updateResultForWebstartableInfo(result, foundProcessOptional.get());
    result.setState(AIState.DONE);
    return result;
  }

  private void updateResultForWebstartableInfo(AiResultDTO resultDTO,
      IWebStartable process) {
    List<Object> params = new ArrayList<>();
    params.addAll(
        Arrays.asList(process.getDisplayName(), process.getDescription()));

    if (CollectionUtils.isEmpty(process.parameters())) {
      String resultStr = Ivy.cms().co("/Labels/AI/ProcessInfoHeader", params);
      resultDTO.setResult(resultStr);
      resultDTO.setResultForAI(resultStr);
      return;
    } else {
      params.add(Integer.toString(process.parameters().size()));
      String resultStr = Ivy.cms()
          .co("/Labels/AI/ProcessInfoHeaderWithParameters", params)
          .concat(System.lineSeparator());

      String resultForAIStr = Ivy.cms()
          .co("/Labels/AI/ProcessInfoHeaderWithParameters", params)
          .concat(System.lineSeparator());

      for (var param : process.parameters()) {
        List<Object> subParams = Arrays.asList(param.name(),
            param.description());

        resultForAIStr = resultForAIStr.concat(
            Ivy.cms().co("/Labels/AI/ProcessParameterInfoWithValue", subParams))
            .concat(System.lineSeparator());

        resultStr = resultStr
            .concat(Ivy.cms().co("/Labels/AI/ProcessParameterInfo", subParams))
            .concat(System.lineSeparator());

        resultDTO.setResult(resultStr);
        resultDTO.setResultForAI(resultForAIStr);
      }
    }
  }

  public static AiResultDTO generateAiResultForStartProcess(String processId,
      String parameters) throws IOException {
    if (StringUtils.isBlank(processId)) {
      return AiAssistantAPI.createSomethingWentWrongError();
    }

    List<IvyToolParameter> params = new ArrayList<>();
    if (StringUtils.isNotBlank(parameters)) {
      params = BusinessEntityConverter.jsonValueToEntities(parameters,
          IvyToolParameter.class);
    }

    IWebStartable startable = ProcessService.getInstance().findAllProcesses()
        .stream().filter(process -> process.getId().contentEquals(processId))
        .findFirst().get();
    AiResultDTO result = new AiResultDTO();
    result.setState(AIState.DONE);
    result.setResult(String.format(START_PROCESS_SUCCESSFULLY_FORMAT,
        startable.getDisplayName()));

    String startLink = startable.getLink().getAbsolute();
    if (!startLink.contains("?")) {
      startLink = startLink.concat("?");
    }

    Map<String, String> paramsMap = new HashMap<>();
    for (var param : params) {
      paramsMap.put(param.getName(), param.getValue());
    }
    startLink = startLink.concat(UrlUtils.buildUrlQueryString(paramsMap));

    result.setResultForAI(AiAssistantAPI
        .generateExecutableResult(startLink));
    return result;
  }
}
