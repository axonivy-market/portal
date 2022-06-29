package ch.ivy.addon.portalkit.util;

import static ch.ivy.addon.portalkit.enums.ExpressMessageType.ERROR;
import static ch.ivy.addon.portalkit.enums.ExpressMessageType.IMPORT_EXPRESS_PROCESSES;
import static ch.ivy.addon.portalkit.enums.ExpressMessageType.IMPORT_RESULT;
import static ch.ivy.addon.portalkit.enums.ExpressMessageType.IMPORT_STATUS;
import static ch.ivy.addon.portalkit.enums.ExpressMessageType.INFO;
import static ch.ivy.addon.portalkit.enums.ExpressMessageType.WARNING;
import static org.apache.commons.lang3.StringUtils.LF;
import static org.apache.commons.lang3.StringUtils.SPACE;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.core.MediaType;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ch.ivy.addon.portalkit.bo.ExpressFormElement;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.ExpressTaskDefinition;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.enums.ExpressMessageType;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.service.ExpressProcessService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.query.UserQuery;

public class ExpressManagementUtils {

  private static final String VERSION = "version";
  private static final String EXPRESS_WORKFLOW = "expressWorkflow";
  private static final String PATTERN = Ivy.cms().coLocale("/patterns/dateTimePattern", Locale.ENGLISH);
  private static final String REPEAT_EXPRESS = "AHWF";
  private static final String JSON_EXTENSION = "json";
  private static final String EXTERNAL_ID_PREFIX = " externalId:";
  private static Gson gson = new GsonBuilder().serializeNulls().create();

  /**
   * Find express repeat workflow list which are ready to execute and start
   *
   * @return List of Express workflow
   */
  public static List<ExpressProcess> findExpressProcesses() {
    List<ExpressProcess> expressProcesses = new ArrayList<>();
    List<ExpressProcess> workflows = ExpressProcessService.getInstance().findReadyToExecuteProcessByProcessType(REPEAT_EXPRESS);
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
  public static boolean isProcessNameDuplicated(String processName) {
    return ExpressProcessService.getInstance().findExpressProcessByName(processName) != null;
  }

  /**
   * Import Express workflow list from JSON file.
   *
   * @param expressData file contains express workflow list
   * @return output messages after run import process
   */
  public static Map<ExpressMessageType, Object> importExpressProcesses(UploadedFile expressData) {
    Map<ExpressMessageType, Object> outputMessages = initOutputMessage();
    StringBuilder importResult = (StringBuilder) outputMessages.get(IMPORT_RESULT);
    StopWatch sw = new StopWatch();
    sw.start();

    if (expressData == null || !FilenameUtils.isExtension(expressData.getFileName(), JSON_EXTENSION)) {
      StringBuilder message = (StringBuilder) outputMessages.get(IMPORT_RESULT);
      message.append(addResultLog(cms(expressData != null ? "/Dialogs/components/CaseDocument/invalidFileMessage" : "/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/fileEmptyMessage"), ERROR));
      return outputMessages;
    }

    importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/startDeployLog", Arrays.asList(expressData.getFileName())), INFO));

    try {
      installExpressWorkflows(expressData.getInputStream(), outputMessages);

      importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/endDeployLog"), INFO));
      sw.stop();
      importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/deploymentFinishedLog", Arrays.asList(expressData.getFileName(),sw.getTime())), INFO));
    } catch (Exception e) {
      importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/deployErrorLog",Arrays.asList(expressData.getFileName())), ERROR));
      importResult.append(addResultLog(e.getMessage(), ERROR));
      Ivy.log().error(e);
    }

    importResult.append(LF);
    outputMessages.put(IMPORT_RESULT, importResult);
    return outputMessages;
  }

  public static Map<ExpressMessageType, Object> initOutputMessage() {
    Map<ExpressMessageType, Object> outputMessages = new HashMap<>();
    outputMessages.put(IMPORT_STATUS, cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/status/failed"));
    outputMessages.put(IMPORT_RESULT, new StringBuilder());
    outputMessages.put(IMPORT_EXPRESS_PROCESSES, new ArrayList<ExpressProcess>());
    return outputMessages;
  }

  public static Map<ExpressMessageType, Object> installExpressWorkflows(InputStream expressInputstream, Map<ExpressMessageType, Object> outputMessages) {
    JsonObject jsonObject = extractJsonObject(expressInputstream);
    if (jsonObject != null) {
      Integer version = jsonObject.get(VERSION).getAsInt();
      if (!validateExpressVersion(outputMessages, version)) {
        List<ExpressProcess> expressProcesses = new ArrayList<>();
        JsonElement workflowsElement = jsonObject.get(EXPRESS_WORKFLOW);
        if (version == 1) {
          expressProcesses = proceedForOldWorkflow(workflowsElement);
        } else if (version == 2) {
          expressProcesses = BusinessEntityConverter.jsonValueToEntities(workflowsElement.toString(), ExpressProcess.class);
        }
        processExpressJsonObject(outputMessages, expressProcesses);
      }
    }
    return outputMessages;
  }

  private static JsonObject extractJsonObject(InputStream expressInputstream) {
    Reader reader = new InputStreamReader(expressInputstream, StandardCharsets.UTF_8);
    JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
    return jsonObject;
  }

  private static List<ExpressProcess> proceedForOldWorkflow(JsonElement workflowsElement) {
    List<ExpressProcess> expressProcesses = new ArrayList<>();
    for (JsonElement element : workflowsElement.getAsJsonArray()) {
      JsonObject jsonObject = element.getAsJsonObject();
      ExpressProcess process = gson.fromJson(jsonObject.get("expressProcess"), ExpressProcess.class);
      Map<Integer, ExpressTaskDefinition> expressTaskDefinitionMap = proceedOldTaskDefinition(jsonObject);
      process.setTaskDefinitions(new ArrayList<>(expressTaskDefinitionMap.values()));
      expressProcesses.add(process);
    }
    return expressProcesses;
  }

  private static  Map<Integer, ExpressTaskDefinition> proceedOldTaskDefinition(JsonObject jsonObject) {
    Map<Integer, ExpressTaskDefinition> expressTaskDefinitionMap = new HashMap<>();
    JsonArray expressTaskDefinitionArray = jsonObject.get("expressTaskDefinitions").getAsJsonArray();
    for (JsonElement taskDefJson : expressTaskDefinitionArray) {
      int taskPosition = extractTaskPositionNode(taskDefJson);
      expressTaskDefinitionMap.put(taskPosition, gson.fromJson(taskDefJson, ExpressTaskDefinition.class));
    }

    JsonArray expressFormElementArray = jsonObject.get("expressFormElements").getAsJsonArray();
    for (JsonElement formElementJson : expressFormElementArray) {
      int taskPosition = extractTaskPositionNode(formElementJson);

      ExpressTaskDefinition expressTaskDefinition = expressTaskDefinitionMap.get(taskPosition);
      if (expressTaskDefinition.getFormElements() == null) {
        expressTaskDefinition.setFormElements(new ArrayList<>());
      }
      expressTaskDefinition.getFormElements().add(gson.fromJson(formElementJson, ExpressFormElement.class));
    }
    return expressTaskDefinitionMap;
  }

  private static int extractTaskPositionNode(JsonElement jsonElement) {
    return jsonElement.getAsJsonObject().get("taskPosition").getAsInt();
  }

  private static void processExpressJsonObject(Map<ExpressMessageType, Object> outputMessages, List<ExpressProcess> expressProcesses) {
    if (expressProcesses != null) {
      int errorCounts = deployExpressWorkflows(outputMessages, expressProcesses);
      if (errorCounts == 0) {
        outputMessages.put(IMPORT_STATUS, cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/status/successful"));
      } else if (errorCounts < expressProcesses.size()) {
        outputMessages.put(IMPORT_STATUS, cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/status/warning"));
      }
    }
  }

  private static boolean validateExpressVersion(Map<ExpressMessageType, Object> outputMessages, Integer version) {
    StringBuilder importResult = (StringBuilder) outputMessages.get(IMPORT_RESULT);
    if (validateExpressVersion(version)) {
      importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/versionLog", Arrays.asList(version)), INFO));
      importResult.append(LF);
    } else {
      importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/invalidVersionLog", Arrays.asList(version)), ERROR));
      importResult.append(LF);
      return true;
    }
    return false;
  }

  private static boolean validateExpressVersion(Integer expressVersion) {
    for (Integer version : PortalConstants.EXPRESS_INVALID_VERSION) {
      if (version == expressVersion) {
        return false;
      }
    }
    return true;
  }

  private static List<String> getExpressMember() {
    List<SecurityMemberDTO> securityResultDTOList = SecurityMemberUtils.findSecurityMembers("", 0, -1);
    return securityResultDTOList.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
  }

  @SuppressWarnings("unchecked")
  private static int deployExpressWorkflows(Map<ExpressMessageType, Object> outputMessages, List<ExpressProcess> expressProcesses) {
    StringBuilder importResult = (StringBuilder) outputMessages.get(IMPORT_RESULT);
    List<ExpressProcess> outputExpressProcessList = (List<ExpressProcess>) outputMessages.get(IMPORT_EXPRESS_PROCESSES);
    List<String> memberList = getExpressMember();

    int errorCounts = 0;
    // Save to BusinessData
    for (ExpressProcess expressProcess : expressProcesses) {
      importResult.append(LF);
      importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/startDeployProcessLog", Arrays.asList(expressProcess.getProcessName())), INFO));

      if (isProcessNameDuplicated(expressProcess.getProcessName())) {
        errorCounts++;
        importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/duplicateProcessNameLog", Arrays.asList(expressProcess.getProcessName())), ERROR));
        importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/installProcessFailedLog", Arrays.asList(expressProcess.getProcessName())), ERROR));
        continue;
      }

      importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/installedProcessLog", Arrays.asList( expressProcess.getProcessName())), INFO));
      importResult.append(LF);

      // Validate user
      validateUsersForProcess(importResult, memberList, expressProcess);
      expressProcess.getTaskDefinitions().forEach(taskDefinition -> {
        validateUserForTaskDef(importResult, memberList, expressProcess, taskDefinition);

        taskDefinition.setResponsibles(updateExternalIdsToSecurityMemberNames(taskDefinition.getResponsibles()));
      });

      // Save process
      ExpressProcess workflowSaved = new ExpressProcess();
      String newId = workflowSaved.getId();
      workflowSaved = expressProcess;
      workflowSaved.setId(newId);
      workflowSaved.setProcessPermissions(updateExternalIdsToSecurityMemberNames(expressProcess.getProcessPermissions()));
      workflowSaved.setProcessOwner(updateExternalIdToSecurityMemberName(expressProcess.getProcessOwner()));
      workflowSaved.setProcessCoOwners(updateExternalIdsToSecurityMemberNames(expressProcess.getProcessCoOwners()));
      ExpressProcessService.getInstance().save(workflowSaved);

      importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/process/installedProcessLog", Arrays.asList(expressProcess.getProcessName())), INFO));
      importResult.append(LF);

      // Update output process
      outputExpressProcessList.add(expressProcess);
    }

    return errorCounts;
  }

  private static void validateUserForTaskDef(StringBuilder importResult, List<String> memberList,
      ExpressProcess expressProcess, ExpressTaskDefinition taskDefinition) {
    taskDefinition.getResponsibles().forEach(userName -> {
      if (!memberList.contains(userName)) {
        importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/validate/validateTaskDefUserLog", Arrays.asList(expressProcess.getProcessName(), userName)), WARNING));
      }
    });
  }

  private static void validateUsersForProcess(StringBuilder importResult, List<String> memberList,
      ExpressProcess expressProcess) {
    expressProcess.getProcessPermissions().forEach(userName -> {
      if (!memberList.contains(userName)) {
        importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/validate/validateProcessPermissionLog", Arrays.asList(expressProcess.getProcessName(), userName)), WARNING));
      }
    });

    expressProcess.getProcessCoOwners().forEach(userName -> {
      if (!memberList.contains(userName)) {
        importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/validate/validateProcessCoOwnerLog", Arrays.asList( expressProcess.getProcessName(), userName)), WARNING));
      }
    });

    if (!memberList.contains(expressProcess.getProcessOwner())) {
      IUser importUser = Ivy.request().getSession().getSessionUser();
      expressProcess.setProcessOwner(importUser.getMemberName());
      String importUserName = importUser.getDisplayName() != null ? importUser.getDisplayName() : importUser.getName();
      importResult.append(addResultLog(cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressMessages/validate/validateProcessOwnerLog", Arrays.asList(expressProcess.getProcessName(), expressProcess.getProcessOwner(), importUserName)), WARNING));
    }
  }

  private static String addResultLog(String message, ExpressMessageType messageType) {
    StringBuilder importExpressResult = new StringBuilder();
    String pattern = DateTimeGlobalSettingService.getInstance().getDatePattern();
    String curentDate = new SimpleDateFormat(pattern).format(new Date());
    importExpressResult.append(curentDate).append(SPACE);

    switch (messageType) {
      case INFO:
        importExpressResult.append(INFO.getLabel()).append(":").append(SPACE);
        break;
      case ERROR:
        importExpressResult.append(ERROR.getLabel()).append(":").append(SPACE);
        break;
      case WARNING:
        importExpressResult.append(WARNING.getLabel()).append(":").append(SPACE);
        break;
      default:
        break;
    }
    importExpressResult.append(message);
    importExpressResult.append(LF);
    return importExpressResult.toString();
  }

  /**
   * Export Express process to JSON file.
   *
   * @param selectedExpressProcesses
   * @return a StreamedContent with content type as a JSON file
   */
  public static StreamedContent exportExpressProcess(List<ExpressProcess> selectedExpressProcesses) {
    Gson gson = new Gson();
    JsonElement jsonElement = gson.toJsonTree(selectedExpressProcesses);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty(VERSION, 2);
    jsonObject.add(EXPRESS_WORKFLOW, jsonElement);

    InputStream inputStream = new ByteArrayInputStream(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
    return DefaultStreamedContent
        .builder()
        .stream(() -> inputStream)
        .contentType(MediaType.APPLICATION_JSON)
        .name(getExportFileName()).build();
  }

  private static String getExportFileName() {
    String curentDate = new SimpleDateFormat(PATTERN).format(new Date());
    return cms("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/expressExportName", Arrays.asList(curentDate));
  }

  /**
   * Some users have external security ID,
   * need to add correspond external security ID of those user into security members list instead of username.
   *
   * @param securityNames
   * @return names of security members with external ID.
   */
  public static List<String> updateExternalIdsToSecurityMemberNames(List<String> securityNames) {
    List<String> result = new ArrayList<>();
    securityNames.forEach(securityName -> {
      result.add(updateExternalIdToSecurityMemberName(securityName));
    });
    return result;
  }

  /**
   * Try to get external ID of security member,
   * If there is no external ID, use old securityName instead.
   *
   * @param securityName
   * @return name of security member with external ID.
   */
  public static String updateExternalIdToSecurityMemberName(String securityName) {
    if (securityName.startsWith("#")) {
      IUser securityMember = UserUtils.findUserByUsername(securityName.substring(1));
      if (securityMember != null) {
        return StringUtils.isBlank(securityMember.getExternalId()) ? securityName : securityName.concat(EXTERNAL_ID_PREFIX).concat(securityMember.getExternalId());
      } else {
        return securityName;
      }
    } else {
      ISecurityMember securityMember = Ivy.security().members().find(securityName);
      return securityMember == null ? securityName : securityMember.getMemberName();
    }
  }

  /**
   * Find valid member name with external lookup
   *
   * @param memberName
   * @return valid member name
   */
  public static String getValidMemberName(String memberName) {
    String result = "";
    if (StringUtils.isBlank(memberName)) {
      return result;
    }

    ISecurityMember responsible;
    if (memberName.contains(EXTERNAL_ID_PREFIX)) {
      UserQuery query = Ivy.security().users().query();
      responsible = query.where().externalId().isEqual(memberName.split(EXTERNAL_ID_PREFIX)[1]).executor().firstResult();
      result = Optional.ofNullable(responsible).map(ISecurityMember::getMemberName).orElse("");
    } else {
      responsible = Ivy.security().members().find(memberName);
      if (responsible != null) {
        result = Optional.ofNullable(responsible).map(ISecurityMember::getMemberName).orElse("");
      }
    }
    return result;
  }

  /**
   * Find valid member names with external lookup
   *
   * @param memberNames
   * @return valid member names
   */
  public static List<String> getValidMemberNames(List<String> memberNames) {
    List<String> result = new ArrayList<>();
    if (CollectionUtils.isEmpty(memberNames)) {
      return result;
    }

    memberNames.forEach(name -> {
      String updatedMemberName = getValidMemberName(name);
      if(StringUtils.isNotBlank(updatedMemberName)) {
        result.add(updatedMemberName);
      }
    });

    return result;
  }

  private static String cms(String cmsUri) {
    return Ivy.cms().co(cmsUri);
  }

  private static String cms(String cmsUri, List<Object> params) {
    return Ivy.cms().co(cmsUri, params);
  }
}
