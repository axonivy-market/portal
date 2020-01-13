package ch.ivy.addon.portalkit.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ch.ivy.addon.portalkit.bo.ExpressFormElement;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.ExpressTaskDefinition;
import ch.ivy.addon.portalkit.bo.ExpressWorkFlow;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.enums.ExpressMessageType;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class ExpressManagementUtils {

  private static final String VERSION = "version";
  private static final String EXPRESS_WORKFLOW = "expressWorkflow";
  private static final String PATTERN =  Ivy.cms().findContentObjectValue("/patterns/dateTimePattern", Locale.ENGLISH).getContentAsString();
  @SuppressWarnings("serial")
  private static final Type EXPRESS_LIST_CONVERT_TYPE = new TypeToken<List<ExpressWorkFlow>>() {}.getType();
  private static final String EXPRESS_TYPE = "AHWF";

  /**
   * Find express repeat workflow which is ready to execute and start by current user
   * 
   * @return List of Express process
   */
  public List<ExpressProcess> findExpressProcesses() {
    List<ExpressProcess> expressProcesses = new ArrayList<>();
    List<ExpressProcess> workflows = ExpressServiceRegistry.getProcessService().findReadyToExecuteProcessOrderByName(EXPRESS_TYPE);
    for (ExpressProcess wf : workflows) {
      if (PermissionUtils.checkAbleToStartAndAbleToEditExpressWorkflow(wf)) {
        expressProcesses.add(wf);
      }
    }
    return expressProcesses;
  }

  /**
   * Check if process name is existed or not
   * @param processName
   * @return true if process name was used, false if process name is available
   */
  public boolean isProcessNameDuplicated(String processName) {
    return ExpressServiceRegistry.getProcessService().findExpressProcessByName(processName) != null;
  }

  /**
   * Import Express workflow list from JSON file.
   *
   * @param expressData file contains express workflow list
   * @return output messages after run import process
   */
  @SuppressWarnings("unchecked")
  public List<String> importExpressProcesses(UploadedFile expressData) {
    List<String> outputMessages = new ArrayList<>(Arrays.asList(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/status/failed"), StringUtils.EMPTY));
    StringBuilder importExpressResult = new StringBuilder();
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    if (expressData == null || !expressData.getContentType().equals(MediaType.APPLICATION_JSON)) {
      outputMessages.set(1, addResultLog(importExpressResult,
              Ivy.cms().co(expressData != null ? "/Dialogs/components/CaseDocument/invalidFileMessage" : "/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/fileEmptyMessage"), ExpressMessageType.ERROR));
      return outputMessages;
    }

    addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/startDeployLog", Arrays.asList(expressData.getFileName())), ExpressMessageType.INFO);

    try {
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> jsonMap = mapper.readValue(expressData.getInputstream(), Map.class);
      if (jsonMap != null) {
        // Fetch all users/groups for express users validation 
        List<String> memberList = getExpressMember();

        for (Object object : jsonMap.keySet()) {
          switch (object.toString()) {
            case VERSION:
              // Validate version of express JSON file
              if (validateExpressVersion(Integer.valueOf(jsonMap.get(object).toString()))) {
                addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/versionLog", Arrays.asList(jsonMap.get(object))), ExpressMessageType.INFO);
                importExpressResult.append(StringUtils.LF);
              } else {
                addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/invalidVersionLog", Arrays.asList(jsonMap.get(object))), ExpressMessageType.ERROR);
                importExpressResult.append(StringUtils.LF);
                outputMessages.set(1, importExpressResult.toString());
                return outputMessages;
              }
              break;
            case EXPRESS_WORKFLOW:
              // Convert inputData to JSON
              Reader reader = new InputStreamReader(expressData.getInputstream());
              Gson gson = new GsonBuilder().serializeNulls().create();

              JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
              JsonElement jsonElement = jsonObject.get(EXPRESS_WORKFLOW);
              List<ExpressWorkFlow> expressWorkFlowsList = gson.fromJson(jsonElement, EXPRESS_LIST_CONVERT_TYPE);
              if (expressWorkFlowsList != null) {
                int errorCounts = deployExpressWorkflows(importExpressResult, memberList, expressWorkFlowsList);
                if (errorCounts == 0) {
                  outputMessages.set(0, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/status/successful"));
                } else if (errorCounts < expressWorkFlowsList.size()) {
                  outputMessages.set(0, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/status/warning"));
                }
              }
          }
        }

        importExpressResult.append(StringUtils.LF);
        addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/endDeployLog"), ExpressMessageType.INFO);
      }
    } catch (IOException e) {
      addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/deployErrorLog",Arrays.asList(expressData.getFileName())), ExpressMessageType.ERROR);
    }

    stopWatch.stop();
    addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/deploymentFinishedLog", Arrays.asList(expressData.getFileName(),stopWatch.getTime())), ExpressMessageType.INFO);
    importExpressResult.append(StringUtils.LF);
    outputMessages.set(1, importExpressResult.toString());
    return outputMessages;
  }

  private boolean validateExpressVersion(Integer expressVersion) {
    for (Integer version : PortalConstants.EXPRESS_INVALID_VERSION) {
      if (version == expressVersion) {
        return false;
      }
    }
    return true;
  }

  private List<String> getExpressMember() {
    List<String> memberList = new ArrayList<>();
    List<SecurityMemberDTO> securityResultDTOList = SecurityMemberUtils.findAllSecurityMembers();
    memberList.addAll(securityResultDTOList.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList()));
    return memberList;
  }

  private int deployExpressWorkflows(StringBuilder importExpressResult, List<String> memberList,
      List<ExpressWorkFlow> expressWorkFlowsList) {
    int errorCounts = 0;
    // Save to BusinessData
    for (ExpressWorkFlow expressWorkFlow : expressWorkFlowsList) {
      ExpressProcess expressProcess = expressWorkFlow.getExpressProcess();
      importExpressResult.append(StringUtils.LF);
      addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/startDeployProcessLog", Arrays.asList(expressProcess.getProcessName())), ExpressMessageType.INFO);

      if (isProcessNameDuplicated(expressProcess.getProcessName())) {
        errorCounts++;
        addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/duplicateProcessNameLog", Arrays.asList(expressProcess.getProcessName())), ExpressMessageType.ERROR);
        addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/installProcessFailedLog", Arrays.asList(expressProcess.getProcessName())), ExpressMessageType.ERROR);
        continue;
      }

      addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/installedProcessLog", Arrays.asList( expressProcess.getProcessName())), ExpressMessageType.INFO);
      importExpressResult.append(StringUtils.LF);

      // Validate user
      validateUsersForProcess(importExpressResult, memberList, expressProcess);
      // Save process
      expressProcess.setId(null);
      BusinessDataInfo<ExpressProcess> info = ExpressServiceRegistry.getProcessService().save(expressProcess);
      String processId = info.getId();

      // Save Task Definition
      deployTaskDefForProcess(importExpressResult, memberList, expressWorkFlow, expressProcess, processId);

      // Save Form Elements
      deployFormElementForProcess(importExpressResult, expressWorkFlow, expressProcess, processId);

      addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/installedProcessLog", Arrays.asList(expressProcess.getProcessName())), ExpressMessageType.INFO);
      importExpressResult.append(StringUtils.LF);
    }

    return errorCounts;
  }

  private List<ExpressFormElement> deployFormElementForProcess(StringBuilder importExpressResult,
      ExpressWorkFlow expressWorkFlow, ExpressProcess expressProcess, String processId) {
    List<ExpressFormElement> expressFormElementList = expressWorkFlow.getExpressFormElements();
    addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/formElement/installFormElement", Arrays.asList(expressProcess.getProcessName())), ExpressMessageType.INFO);
    expressFormElementList.forEach(formElement -> {
      formElement.setId(null);
      formElement.setProcessID(processId);
      ExpressServiceRegistry.getFormElementService().save(formElement);
    });
    addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/formElement/finishedInstallationFormElementLog", Arrays.asList(expressProcess.getProcessName())), ExpressMessageType.INFO);
    return expressFormElementList;
  }

  private List<ExpressTaskDefinition> deployTaskDefForProcess(StringBuilder importExpressResult,
      List<String> memberList, ExpressWorkFlow expressWorkFlow, ExpressProcess expressProcess, String processId) {
    List<ExpressTaskDefinition> expressTaskDefinitionList = expressWorkFlow.getExpressTaskDefinitions();
    addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/taskDef/installTaskDefLog", Arrays.asList(expressProcess.getProcessName())), ExpressMessageType.INFO);
    expressTaskDefinitionList.forEach(taskDefinition -> {
      // Validate Users
      validateUserForTaskDef(importExpressResult, memberList, expressProcess, taskDefinition);

      taskDefinition.setId(null);
      taskDefinition.setProcessID(processId);
      ExpressServiceRegistry.getTaskDefinitionService().save(taskDefinition);
    });

    addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/taskDef/finishedInstallationTaskDef", Arrays.asList(expressProcess.getProcessName())), ExpressMessageType.INFO);
    return expressTaskDefinitionList;
  }

  private void validateUserForTaskDef(StringBuilder importExpressResult, List<String> memberList,
      ExpressProcess expressProcess, ExpressTaskDefinition taskDefinition) {
    taskDefinition.getResponsibles().forEach(userName -> {
      if (!memberList.contains(userName)) {
        addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/validate/validateTaskDefUserLog", Arrays.asList(expressProcess.getProcessName(), userName)), ExpressMessageType.WARNING);
      }
    });
  }

  private void validateUsersForProcess(StringBuilder importExpressResult, List<String> memberList,
      ExpressProcess expressProcess) {
    expressProcess.getProcessPermissions().forEach(userName -> {
      if (!memberList.contains(userName)) {
        addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/validate/validateProcessPermissionLog", Arrays.asList(expressProcess.getProcessName(), userName)), ExpressMessageType.WARNING);
      }
    });

    expressProcess.getProcessCoOwners().forEach(userName -> {
      if (!memberList.contains(userName)) {
        addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/validate/validateProcessCoOwnerLog", Arrays.asList( expressProcess.getProcessName(), userName)), ExpressMessageType.WARNING);
      }
    });

    if (!memberList.contains(expressProcess.getProcessOwner())) {
      IUser importUser = Ivy.request().getSession().getSessionUser();
      expressProcess.setProcessOwner(importUser.getMemberName());
      String importUserName = importUser.getDisplayName() != null ? importUser.getDisplayName() : importUser.getName();
      addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/validate/validateProcessOwnerLog", Arrays.asList(expressProcess.getProcessName(), expressProcess.getProcessOwner(), importUserName)), ExpressMessageType.WARNING);
    }
  }

  private String addResultLog(StringBuilder importExpressResult, String message, ExpressMessageType messageType) {
    String curentDate = new SimpleDateFormat(PATTERN).format(new Date());
    importExpressResult.append(curentDate).append(StringUtils.SPACE);

    switch (messageType) {
      case INFO:
        importExpressResult.append(ExpressMessageType.INFO.getLabel()).append(":").append(StringUtils.SPACE);
        break;
      case ERROR:
        importExpressResult.append(ExpressMessageType.ERROR.getLabel()).append(":").append(StringUtils.SPACE);
        break;
      case WARNING:
        importExpressResult.append(ExpressMessageType.WARNING.getLabel()).append(":").append(StringUtils.SPACE);
        break;
      default:
        break;
    }
    importExpressResult.append(message);
    importExpressResult.append(StringUtils.LF);
    return importExpressResult.toString();
  }

  /**
   * Export Express process to JSON file.
   * 
   * @param selectedExpressProcesses
   * @return a StreamedContent with content type as a JSON file
   */
  public StreamedContent exportExpressProcess(List<ExpressProcess> selectedExpressProcesses) {
    List<ExpressWorkFlow> expressWorkFlowsList = new ArrayList<>();
    for (ExpressProcess expressProcess : selectedExpressProcesses) {
      ExpressWorkFlow expressWorkFlow = new ExpressWorkFlow();
      String processId = expressProcess.getId();

      List<ExpressTaskDefinition> expressTaskDefinitionsList =
          ExpressServiceRegistry.getTaskDefinitionService().findByProcessId(processId);

      List<ExpressFormElement> expressFormElementsList =
          ExpressServiceRegistry.getFormElementService().findByProcessId(processId);

      expressWorkFlow.setExpressProcess(expressProcess);
      expressWorkFlow.setExpressTaskDefinitions(expressTaskDefinitionsList);
      expressWorkFlow.setExpressFormElements(expressFormElementsList);

      expressWorkFlowsList.add(expressWorkFlow);
    }
    Gson gson = new Gson();
    JsonElement jsonElement = gson.toJsonTree(expressWorkFlowsList, EXPRESS_LIST_CONVERT_TYPE);

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty(VERSION, PortalConstants.EXPRESS_VERSION);
    jsonObject.add(EXPRESS_WORKFLOW, jsonElement);

    InputStream inputStream = new ByteArrayInputStream(jsonObject.toString().getBytes());
    return new DefaultStreamedContent(inputStream, MediaType.APPLICATION_JSON, getExportFileName());
  }

  private String getExportFileName() {
    String curentDate = new SimpleDateFormat(PATTERN).format(new Date());
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressExportName", Arrays.asList(curentDate));
  }

}
