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
import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.enums.ExpressManagementProperty;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;

public class ExpressManagementUtils {

  private static final String EXPRESS_INVALID_VERSION = "ExpressInvalidVersion";
  private static final String EXPRESS_VERSION = "ExpressVersion";
  private static final String VERSION = "version";
  private static final String EXPRESS_WORKFLOW = "expressWorkflow";
  private static final String PATTERN =  Ivy.cms().findContentObjectValue("/patterns/dateTimePattern", Locale.ENGLISH).getContentAsString();
  @SuppressWarnings("serial")
  private static final Type EXPRESS_LIST_CONVERT_TYPE = new TypeToken<List<ExpressWorkFlow>>() {}.getType();
  
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
              Ivy.cms().co(expressData != null ? "/Dialogs/components/CaseDocument/invalidFileMessage" : "/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/fileEmptyMessage"), ExpressManagementProperty.ERROR));
      return outputMessages;
    }

    addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/startDeployLog", Arrays.asList(expressData.getFileName())), ExpressManagementProperty.INFO);

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
              if (validateExpressVersion(jsonMap.get(object))) {
                addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/versionLog", Arrays.asList(jsonMap.get(object))), ExpressManagementProperty.INFO);
                importExpressResult.append(StringUtils.LF);
              } else {
                addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/invalidVersionLog", Arrays.asList(jsonMap.get(object))), ExpressManagementProperty.ERROR);
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
                  outputMessages.set(0, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/status/Success"));
                } else if (errorCounts < expressWorkFlowsList.size()) {
                  outputMessages.set(0, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/status/warning"));
                }
              }
          }
        }

        importExpressResult.append(StringUtils.LF);
        addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/endDeployLog"), ExpressManagementProperty.INFO);
      }
    } catch (IOException e) {
      addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/deployErrorLog",Arrays.asList(expressData.getFileName())), ExpressManagementProperty.ERROR);
    }

    stopWatch.stop();
    addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/deploymentFinishedLog", Arrays.asList(expressData.getFileName(),stopWatch.getTime())), ExpressManagementProperty.INFO);
    importExpressResult.append(StringUtils.LF);
    outputMessages.set(1, importExpressResult.toString());
    return outputMessages;
  }

  private boolean validateExpressVersion(Object object) {
    String invalidVersion = Ivy.var().get(EXPRESS_INVALID_VERSION);
    if (!StringUtils.isBlank(invalidVersion)) {
      String[] invalidList = invalidVersion.split(";");
      for (String version : invalidList) {
        if (version.equalsIgnoreCase(object.toString())) {
          return false;
        }
      }
    } else {
      return false;
    }
    return true;
  }

  private List<String> getExpressMember() {
    List<String> memberList = new ArrayList<>();
    
    IApplication currentApplication = Ivy.request().getApplication();
    IvySecurityResultDTO ivySecurityResultDTO = SecurityService.newInstance().findUsers(currentApplication);
    memberList.addAll(ivySecurityResultDTO.getUsers().stream().map(UserDTO::getMemberName).collect(Collectors.toList()));

    ivySecurityResultDTO = SecurityService.newInstance().findRoleDTOs(currentApplication);
    memberList.addAll(ivySecurityResultDTO.getRoleDTOs().stream().map(RoleDTO::getMemberName).collect(Collectors.toList()));
    return memberList;
  }

  private int deployExpressWorkflows(StringBuilder importExpressResult, List<String> memberList,
      List<ExpressWorkFlow> expressWorkFlowsList) {
    int errorCounts = 0;
    // Save to BusinessData
    for (ExpressWorkFlow expressWorkFlow : expressWorkFlowsList) {
      ExpressProcess expressProcess = expressWorkFlow.getExpressProcess();
      importExpressResult.append(StringUtils.LF);
      addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/startDeployProcessLog", Arrays.asList(expressProcess.getProcessName())), ExpressManagementProperty.INFO);

      if (isProcessNameDuplicated(expressProcess.getProcessName())) {
        errorCounts++;
        addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/duplicateProcessNameLog", Arrays.asList(expressProcess.getProcessName())), ExpressManagementProperty.ERROR);
        addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/installProcessFailedLog", Arrays.asList(expressProcess.getProcessName())), ExpressManagementProperty.ERROR);
        continue;
      }

      addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/installedProcessLog", Arrays.asList( expressProcess.getProcessName())), ExpressManagementProperty.INFO);
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

      addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/installedProcessLog", Arrays.asList(expressProcess.getProcessName())), ExpressManagementProperty.INFO);
      importExpressResult.append(StringUtils.LF);
    }

    return errorCounts;
  }

  private List<ExpressFormElement> deployFormElementForProcess(StringBuilder importExpressResult,
      ExpressWorkFlow expressWorkFlow, ExpressProcess expressProcess, String processId) {
    List<ExpressFormElement> expressFormElementList = expressWorkFlow.getExpressFormElements();
    addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/formElement/installFormElement", Arrays.asList(expressProcess.getProcessName())), ExpressManagementProperty.INFO);
    expressFormElementList.forEach(formElement -> {
      formElement.setId(null);
      formElement.setProcessID(processId);
      ExpressServiceRegistry.getFormElementService().save(formElement);
    });
    addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/formElement/finishedInstallationFormElementLog", Arrays.asList(expressProcess.getProcessName())), ExpressManagementProperty.INFO);
    return expressFormElementList;
  }

  private List<ExpressTaskDefinition> deployTaskDefForProcess(StringBuilder importExpressResult,
      List<String> memberList, ExpressWorkFlow expressWorkFlow, ExpressProcess expressProcess, String processId) {
    List<ExpressTaskDefinition> expressTaskDefinitionList = expressWorkFlow.getExpressTaskDefinitions();
    addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/taskDef/installTaskDefLog", Arrays.asList(expressProcess.getProcessName())), ExpressManagementProperty.INFO);
    expressTaskDefinitionList.forEach(taskDefinition -> {
      // Validate Users
      validateUserForTaskDef(importExpressResult, memberList, expressProcess, taskDefinition);

      taskDefinition.setId(null);
      taskDefinition.setProcessID(processId);
      ExpressServiceRegistry.getTaskDefinitionService().save(taskDefinition);
    });

    addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/taskDef/finishedInstallationTaskDef", Arrays.asList(expressProcess.getProcessName())), ExpressManagementProperty.INFO);
    return expressTaskDefinitionList;
  }

  private void validateUserForTaskDef(StringBuilder importExpressResult, List<String> memberList,
      ExpressProcess expressProcess, ExpressTaskDefinition taskDefinition) {
    taskDefinition.getResponsibles().forEach(userName -> {
      if (!memberList.contains(userName)) {
        addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/validate/validateTaskDefUserLog", Arrays.asList(expressProcess.getProcessName(), userName)), ExpressManagementProperty.WARNING);
      }
    });
  }

  private void validateUsersForProcess(StringBuilder importExpressResult, List<String> memberList,
      ExpressProcess expressProcess) {
    expressProcess.getProcessPermissions().forEach(userName -> {
      if (!memberList.contains(userName)) {
        addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/validate/validateProcessPermissionLog", Arrays.asList(expressProcess.getProcessName(), userName)), ExpressManagementProperty.WARNING);
      }
    });

    expressProcess.getProcessCoOwners().forEach(userName -> {
      if (!memberList.contains(userName)) {
        addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/validate/validateProcessCoOwnerLog", Arrays.asList( expressProcess.getProcessName(), userName)), ExpressManagementProperty.WARNING);
      }
    });

    if (!memberList.contains(expressProcess.getProcessOwner())) {
      addResultLog(importExpressResult, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/validate/validateProcessOwnerLog", Arrays.asList(expressProcess.getProcessName(), expressProcess.getProcessOwner())), ExpressManagementProperty.WARNING);
    }
  }

  private String addResultLog(StringBuilder importExpressResult, String message, ExpressManagementProperty messageType) {
    String curentDate = new SimpleDateFormat(PATTERN).format(new Date());
    importExpressResult.append(curentDate).append(StringUtils.SPACE);

    switch (messageType) {
      case INFO:
        importExpressResult.append(ExpressManagementProperty.INFO.getLabel()).append(":").append(StringUtils.SPACE);
        break;
      case ERROR:
        importExpressResult.append(ExpressManagementProperty.ERROR.getLabel()).append(":").append(StringUtils.SPACE);
        break;
      case WARNING:
        importExpressResult.append(ExpressManagementProperty.WARNING.getLabel()).append(":").append(StringUtils.SPACE);
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
    jsonObject.addProperty(VERSION, getLatestExpressVersion());
    jsonObject.add(EXPRESS_WORKFLOW, jsonElement);

    InputStream inputStream = new ByteArrayInputStream(jsonObject.toString().getBytes());
    return new DefaultStreamedContent(inputStream, MediaType.APPLICATION_JSON, getExportFileName());
  }

  private String getLatestExpressVersion() {
    String latestExpressVersion = Ivy.var().get(EXPRESS_VERSION);
    if (StringUtils.isBlank(latestExpressVersion)) {
      throw new PortalException(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/validate/validateExpressVersion"));
    }
    return latestExpressVersion.trim();
  }

  private String getExportFileName() {
    String curentDate = new SimpleDateFormat(PATTERN).format(new Date());
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressExportName", Arrays.asList(curentDate));
  }

}
