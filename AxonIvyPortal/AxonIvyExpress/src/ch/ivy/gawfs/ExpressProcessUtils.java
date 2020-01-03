package ch.ivy.gawfs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.ws.rs.core.MediaType;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ch.ivy.addon.portalkit.bo.ExpressFormElement;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.ExpressTaskDefinition;
import ch.ivy.addon.portalkit.bo.ExpressUserEmail;
import ch.ivy.addon.portalkit.bo.ExpressWorkFlow;
import ch.ivy.addon.portalkit.dto.ExpressAttachment;
import ch.ivy.addon.portalkit.enums.ExpressEmailAttachmentStatus;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.gawfs.enums.FormElementType;
import ch.ivy.gawfs.enums.TaskType;
import ch.ivy.gawfs.mail.MailAttachment;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.ISubProcessStart;
import ch.ivyteam.ivy.process.call.SubProcessRunner;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter.Builder;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import gawfs.Data;
import gawfs.ExternalDataProvider;
import gawfs.TaskDef;

public class ExpressProcessUtils {

  private static final String HEADER_PANEL = "HEADER";
  private static final String FOOTER_PANEL = "FOOTER";
  private static final String LEFT_PANEL = "LEFTPANEL";
  private static final String RIGHT_PANEL = "RIGHTPANEL";

  private static final String VERSION = "version";
  private static final String EXPRESS_WORKFLOW = "expressWorkflow";
  private static final List<Integer> Valid_Versions = Arrays.asList(0,1);
  @SuppressWarnings("serial")
  private static final Type Express_List_Convert_Type = new TypeToken<List<ExpressWorkFlow>>() {}.getType();

  /**
   * Save Express process to repository as business data
   * 
   * @param expressData
   * @return Express process after saved
   */
  public ExpressProcess saveProcess(Data expressData) {
    ExpressProcess processRepository =
        Optional.ofNullable(ExpressServiceRegistry.getProcessService().findById(expressData.getProcessID())).orElse(
            new ExpressProcess());

    processRepository.setProcessName(expressData.getProcessName());
    processRepository.setProcessDescription(expressData.getProcessDescription());
    processRepository.setProcessType(expressData.getProcessType().getValue());
    processRepository.setUseDefaultUI(expressData.getIsUseDefaultUI());
    if(StringUtils.isBlank(processRepository.getProcessOwner())) {
      processRepository.setProcessOwner(Ivy.session().getSessionUser().getMemberName());
    }
    processRepository.setProcessPermissions(expressData.getDefinedTasks().get(0).getResponsibles());
    processRepository.setProcessCoOwners(expressData.getProcessCoOwners());
    processRepository.setProcessFolder(expressData.getProcessFolder());
    processRepository.setReadyToExecute(expressData.getReadyToExecute());

    BusinessDataInfo<ExpressProcess> info = ExpressServiceRegistry.getProcessService().save(processRepository);
    processRepository.setId(info.getId());

    saveDefinedTasks(processRepository.getId(), expressData.getDefinedTasks());

    return processRepository;
  }

  /**
   * Save defined tasks to repository as business data
   * 
   * @param processId
   * @param definedTasks
   */
  private void saveDefinedTasks(String processId, List<TaskDef> definedTasks) {
    // Delete old tasks and form elements
    ExpressServiceRegistry.getTaskDefinitionService().deleteByProcessId(processId);
    ExpressServiceRegistry.getFormElementService().deleteByProcessId(processId);

    // Save the task definition with the order of the tasks
    for (TaskDef taskDef : definedTasks) {
      ExpressTaskDefinition expressTaskDef = new ExpressTaskDefinition();
      expressTaskDef.setType(taskDef.getTaskType().name());
      expressTaskDef.setSubject(taskDef.getSubject());
      expressTaskDef.setDescription(taskDef.getDescription());
      expressTaskDef.setResponsibles(taskDef.getResponsibles());
      expressTaskDef.setUntilDays(taskDef.getUntilDays().intValue());
      expressTaskDef.setProcessID(processId);
      expressTaskDef.setTaskPosition(taskDef.getPosition());
      expressTaskDef.setEmail(taskDef.getEmail());
      ExpressServiceRegistry.getTaskDefinitionService().save(expressTaskDef);
      if (taskDef.getTaskType() != TaskType.EMAIL && taskDef.getTaskType() != TaskType.APPROVAL) {
        saveFormElements(processId, taskDef.getPosition(), taskDef.getDragAndDropController());
      }
    }
  }

  /**
   * Save all form elements with Id, location, and order
   * 
   * @param processId
   * @param taskPosition
   * @param controller
   */
  private void saveFormElements(String processId, int taskPosition, DragAndDropController controller) {
    processAndSaveFormElements(controller.getSelectedFormelementsHeader(), taskPosition, processId, HEADER_PANEL);
    processAndSaveFormElements(controller.getSelectedFormelementsLeftPanel(), taskPosition, processId, LEFT_PANEL);
    processAndSaveFormElements(controller.getSelectedFormelementsRightPanel(), taskPosition, processId, RIGHT_PANEL);
    processAndSaveFormElements(controller.getSelectedFormelementsFooter(), taskPosition, processId, FOOTER_PANEL);
  }
  
  private void processAndSaveFormElements(List<Formelement> formElements, int taskPosition, String processId,  String location) {
    if (CollectionUtils.isNotEmpty(formElements)){
      int indexInPanel = 0;
      for (Formelement element : formElements) {
        element.setTaskPosition(taskPosition);
        element.setIndexInPanel(indexInPanel++);
        saveFormElement(element, location, processId);
      }
    }
  }

  /**
   * Save form element to repository as business data
   * 
   * @param element
   * @param location
   * @param processId
   */
  private void saveFormElement(Formelement element, String location, String processId) {
    ExpressFormElement expressFormElement = new ExpressFormElement();
    expressFormElement.setElementID(element.getId());
    expressFormElement.setElementPosition(location);
    expressFormElement.setElementType(element.getType().getValue());
    expressFormElement.setIntSetting(Optional.ofNullable(element.getIntSetting()).orElse(0));
    expressFormElement.setLabel(element.getLabel());
    expressFormElement.setRequired(Optional.ofNullable(element.getRequired()).orElse(false));
    expressFormElement.setProcessID(processId);
    expressFormElement.setOptionStrs(element.getOptionsStr());
    expressFormElement.setTaskPosition(element.getTaskPosition());
    expressFormElement.setIndexInPanel(element.getIndexInPanel());

    ExpressServiceRegistry.getFormElementService().save(expressFormElement);
  }

  /**
   * Get defined tasks by process ID from repository
   * 
   * @param processId
   * @return List of defined tasks
   */
  public List<TaskDef> getDefinedTasks(String processId) {
    List<TaskDef> taskDefinitions = new ArrayList<>();
    List<ExpressTaskDefinition> expressTaskDefinitions =
        ExpressServiceRegistry.getTaskDefinitionService().findByProcessId(processId);

    for (ExpressTaskDefinition expressTaskDef : expressTaskDefinitions) {
      TaskDef taskDef = new TaskDef();
      taskDef.setResponsibles(getValidSecurityMembers(expressTaskDef.getResponsibles()));
      taskDef.setTaskType(EnumUtils.getEnum(TaskType.class, expressTaskDef.getType()));
      taskDef.setPosition(expressTaskDef.getTaskPosition());
      taskDef.setDescription(expressTaskDef.getDescription());
      taskDef.setSubject(expressTaskDef.getSubject());
      taskDef.setUntilDays(expressTaskDef.getUntilDays());
      taskDef.setEmail(expressTaskDef.getEmail());
      taskDef.setResponsibleDisplayName(generateResponsibleDisplayName(taskDef.getResponsibles()));

      initializeControllersForTaskDef(processId, taskDef);
      taskDefinitions.add(taskDef);
    }

    return Helper.sortTasks(taskDefinitions);
  }

  /**
   * Get merged display name of responsibles
   * 
   * @param responsibleNames
   * @return merged display name
   */
  public String generateResponsibleDisplayName(List<String> responsibleNames) {
    return CollectionUtils.emptyIfNull(responsibleNames)
      .stream()
      .map(responsibleName -> Ivy.session().getSecurityContext().findSecurityMember(responsibleName))
      .filter(securityMember -> securityMember != null)
      .map(securityMember -> StringUtils.defaultIfBlank(securityMember.getDisplayName(), securityMember.getName()))
      .collect(Collectors.joining(", "));
  }

  /**
   * Get valid security members based on responsible names
   * 
   * @param responsibleNames
   * @return security members
   */
  public List<String> getValidSecurityMembers(List<String> responsibleNames) {
    return CollectionUtils.emptyIfNull(responsibleNames)
        .stream()
        .filter(responsibleName -> Ivy.session().getSecurityContext().findSecurityMember(responsibleName) != null)
        .collect(Collectors.toList());
  }

  /**
   * get email addresses from responsible names
   * 
   * @param responsibleNames
   * @return email addresses
   */
  public List<String> getRecipientEmailAddresses(List<String> responsibleNames) {
    return CollectionUtils.emptyIfNull(responsibleNames)
        .stream().map(responsibleName -> getEmailAddressFromSecurityMember(responsibleName))
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }
  
  private List<String> getEmailAddressFromSecurityMember(String memberName) {
    List<String> emailAddresses = new ArrayList<>(); 
    ISecurityMember securityMember = Ivy.session().getSecurityContext().findSecurityMember(memberName);
    if (securityMember != null) {
      if (securityMember.isUser()) {
        IUser iuser = (IUser) securityMember;
        if (StringUtils.isNotBlank(iuser.getEMailAddress())) {
          emailAddresses.add(iuser.getEMailAddress());
        }
      } else {
        IRole irole = (IRole) securityMember;
        for (IUser userInRole : irole.getUsers()) {
          if (StringUtils.isNotBlank(userInRole.getEMailAddress())) {
            emailAddresses.add(userInRole.getEMailAddress());
          }
        }
      }
    }
    return emailAddresses;
  }

  /**
   * Initialize controllers for task definition
   * 
   * @param processId
   * @param taskDef
   * @return initialized task definition
   */
  private TaskDef initializeControllersForTaskDef(String processId, TaskDef taskDef) {
    DragAndDropController dragAndDropController = new DragAndDropController();
    DynaFormController dynaFormController = new DynaFormController(dragAndDropController);
    dragAndDropController.setDynaFormController(dynaFormController);
    dynaFormController.createForm();

    taskDef.setDynaFormController(dynaFormController);
    taskDef.setDragAndDropController(dragAndDropController);
    updateDragAndDropController(processId, taskDef.getDragAndDropController(), taskDef.getPosition());

    return taskDef;
  }

  /**
   * Update controller with form elements get from repository by process ID and task position
   * 
   * @param processId
   * @param controller
   */
  private void updateDragAndDropController(String processId, DragAndDropController controller, int taskPosition) {
    List<ExpressFormElement> expressFormElements =
        ExpressServiceRegistry.getFormElementService().findByProcessId(processId);
    expressFormElements =
        expressFormElements.stream().filter(element -> element.getTaskPosition() == taskPosition)
            .collect(Collectors.toList());

    for (ExpressFormElement expressElement : expressFormElements) {
      Formelement element = new Formelement();
      element.setId(expressElement.getElementID());
      element.setIntSetting(expressElement.getIntSetting());
      element.setLabel(expressElement.getLabel());
      element.setRequired(expressElement.isRequired());
      element.setTaskPosition(taskPosition);
      element.setIndexInPanel(expressElement.getIndexInPanel());

      for (FormElementType type : FormElementType.values()) {
        if (expressElement.getElementType().equals(type.getValue())) {
          element.setType(type);
        }
      }

      element.setOptionsStr(expressElement.getOptionStrs());
      String location = expressElement.getElementPosition();
      switch (location) {
        case HEADER_PANEL:
          controller.getSelectedFormelementsHeader().add(element);
          break;
        case LEFT_PANEL:
          controller.getSelectedFormelementsLeftPanel().add(element);
          break;
        case RIGHT_PANEL:
          controller.getSelectedFormelementsRightPanel().add(element);
          break;
        case FOOTER_PANEL:
          controller.getSelectedFormelementsFooter().add(element);
          break;
        default:
          break;
      }
    }
    
    sortIndexInPanels(controller);
  }
  
  private void sortIndexInPanels(DragAndDropController controller) {
    controller.setSelectedFormelementsHeader(controller.getSelectedFormelementsHeader().stream().sorted(Comparator.comparingInt(Formelement::getIndexInPanel)).collect(Collectors.toList()));
    controller.setSelectedFormelementsLeftPanel(controller.getSelectedFormelementsLeftPanel().stream().sorted(Comparator.comparingInt(Formelement::getIndexInPanel)).collect(Collectors.toList()));
    controller.setSelectedFormelementsRightPanel(controller.getSelectedFormelementsRightPanel().stream().sorted(Comparator.comparingInt(Formelement::getIndexInPanel)).collect(Collectors.toList()));
    controller.setSelectedFormelementsFooter(controller.getSelectedFormelementsFooter().stream().sorted(Comparator.comparingInt(Formelement::getIndexInPanel)).collect(Collectors.toList()));
  }

  public boolean isNeedUpdatePathForAttachments(List<TaskDef> taskDefs) {
    for (TaskDef task : taskDefs) {
      if (task.getEmail() != null && task.getEmail().getAttachments() != null) {
        for (ExpressAttachment attachment : task.getEmail().getAttachments()) {
          return attachment.getPath() == null;
        }
      }
    }
    return false;
  }

  public String generateProcessFolder() {
    return UUID.randomUUID().toString();
  }

  public void saveAttachments(String folder, List<TaskDef> taskDefs) {
    String folderPath = "/Express/Process/" + folder + "/Attachment/";
    for (TaskDef task : taskDefs) {
      saveAttachmentsForEmail(folderPath, task.getEmail());
    }
  }

  public void saveAttachmentsForEmail(String folderPath, ExpressUserEmail mail) {
    if (mail != null && mail.getAttachments() != null) {
      List<ExpressAttachment> attachments = mail.getAttachments();
      setPathForAttachments(folderPath, attachments);
      MailAttachment mailAttachment = new MailAttachment(attachments);
      mailAttachment.updatePhysicalPaths();
      removeDeletedAttachment(attachments);
    }
  }

  private void setPathForAttachments(String folderPath, List<ExpressAttachment> attachments) {
    for (ExpressAttachment attachment : attachments) {
      if (attachment.getPath() == null && attachment.getContent() != null) {
        attachment.setPath(folderPath + attachment.getName());
      }
    }
  }

  private void removeDeletedAttachment(List<ExpressAttachment> attachments) {
    Iterator<ExpressAttachment> attachmentIter = attachments.iterator();
    if (attachmentIter.hasNext()) {
      ExpressAttachment attachment = attachmentIter.next();
      if (attachment.getStatus() == ExpressEmailAttachmentStatus.DELETED || attachment.getPath() == null) {
        attachmentIter.remove();
      }
    }
  }

  /**
   * Check whether a task is user task or not
   * 
   * @param task
   * @return check result
   */
  public boolean isUserTask(TaskDef task) {
    return task.getTaskType() == TaskType.USER_TASK || task.getTaskType() == TaskType.USER_TASK_WITH_EMAIL;
  }

  /**
   * Check if task has next approval task
   * 
   * @param taskList
   * @param userTaskIndex
   * @return check result
   */
  public boolean hasNextApprovalTask(List<TaskDef> taskList, int userTaskIndex) {
    List<TaskDef> subTaskList = taskList.subList(userTaskIndex + 1, taskList.size() - 1);
    for (TaskDef task : subTaskList) {
      if (task.getTaskType() == TaskType.USER_TASK || task.getTaskType() == TaskType.USER_TASK_WITH_EMAIL) {
        return false;
      }
      if (task.getTaskType() == TaskType.APPROVAL) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check whether form definition step can be finished or not
   * 
   * @param definedTasks
   * @return check result
   */
  public boolean canFinishFormDefinition(List<TaskDef> definedTasks) {
    boolean isAnyCreateFormNotDefined =
        definedTasks
            .stream()
            .filter(
                taskDef -> (taskDef.getTaskType() != TaskType.EMAIL && taskDef.getTaskType() != TaskType.APPROVAL && taskDef
                    .getDragAndDropController().isNotDefined())).findFirst().isPresent();

    boolean isAnyEmailEmpty =
        definedTasks.stream()
            .filter(taskDef -> taskDef.getTaskType() == TaskType.EMAIL && taskDef.getEmail().isEmpty()).findFirst()
            .isPresent();

    return !isAnyCreateFormNotDefined && !isAnyEmailEmpty;
  }

  /**
   * check whether next button should display
   * 
   * @param definedTasks
   * @param currentIndex
   * @return check result
   */
  public boolean displayNextButton(List<TaskDef> definedTasks, int currentIndex) {
    if (definedTasks == null || definedTasks.size() < 2) {
      return false;
    }
    if (currentIndex == definedTasks.size() - 1) {
      return false;
    }
    return !definedTasks.subList(currentIndex + 1, definedTasks.size()).stream()
        .filter(item -> item.getTaskType() != TaskType.APPROVAL).collect(Collectors.toList()).isEmpty();
  }

  public int nextAvailableTaskIndex(List<TaskDef> definedTasks, int currentIndex) {
    Optional<TaskDef> taskDef =
        definedTasks.subList(currentIndex + 1, definedTasks.size()).stream()
            .filter(item -> item.getTaskType() != TaskType.APPROVAL).findFirst();
    if (taskDef.isPresent()) {
      return definedTasks.indexOf(taskDef.get());
    }
    return -1;
  }
  
  /**
   * Check if process name is existed or not
   * @param processName
   * @return true if process name was used, false if process name is available
   */
  public boolean isProcessNameDuplicated(String processName) {
    return ExpressServiceRegistry.getProcessService().findExpressProcessByName(processName) != null;
  }

  public List<ExternalDataProvider> findDataProviders() {
    Builder subprocessFilter = SubProcessSearchFilter.create();
    SubProcessSearchFilter filter =
        subprocessFilter.setSignature("portalExpressDataProvider()").setSearchInAllProjects(true)
        .setSearchInDependentProjects(false).toFilter();
    return SubProcessRunner.findSubProcessStarts(filter).stream().map(this::toDataProvider).collect(Collectors.toList());
  }

  private ExternalDataProvider toDataProvider(ISubProcessStart subProcessStart) {
    ExternalDataProvider dataProvider = new ExternalDataProvider();
    dataProvider.setLibraryId(subProcessStart.getProcessModelVersion().getLibrary().getId());
    dataProvider.setSignature(subProcessStart.getSignature());
    dataProvider.setName(subProcessStart.getProcessName());
    return dataProvider;
  }

  /** Fetch all of Express process which is ready to execute in the Business data 
   * Just return the process which can start by current user
   * @return List of Express process
   */
  public List<ExpressProcess> findExpressProcesses() {
    List<ExpressProcess> processes = new ArrayList<>();
    List<ExpressProcess> workflows = ExpressServiceRegistry.getProcessService().findReadyToExecuteProcessOrderByName();
    for (ExpressProcess wf : workflows) {
      if (PermissionUtils.checkAbleToStartAndAbleToEditExpressWorkflow(wf)) {
        processes.add(wf);
      }
    }
    return processes;
  }

  /**
   * Import the Express process to Business data
   *
   * @param expressData should have Content type as JSon file
   * @return a message of import process
   * @throws JsonParseException
   * @throws JsonMappingException
   * @throws IOException
   */
  @SuppressWarnings("unchecked")
  public String importExpressProcess(UploadedFile expressData)
      throws JsonParseException, JsonMappingException, IOException {
    StringBuilder importExpressResult = new StringBuilder();
    if (expressData == null) {
      importExpressResult.append("Data is empty");
      importExpressResult.append("\n");
      return importExpressResult.toString();
    }

    if (!expressData.getContentType().equals(MediaType.APPLICATION_JSON)) {
      importExpressResult.append("File type is invalid");
      importExpressResult.append("\n");
      return importExpressResult.toString();
    }

    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> jsonMap = mapper.readValue(expressData.getInputstream(), Map.class);
    if (jsonMap != null) {
      for (Object object : jsonMap.keySet()) {
        // validate
        switch (object.toString()) {
          case VERSION:
            if (Valid_Versions.contains(jsonMap.get(object))) {
              addResultMessage(importExpressResult, "Version of express" + object);
            } else {
              addResultMessage(importExpressResult, "Non support version of express" + object);
              return importExpressResult.toString();
            }
            break;
          case EXPRESS_WORKFLOW:
            // convert to JSon
            Reader reader = new java.io.InputStreamReader(expressData.getInputstream());
            // Map<String, Object> jsonMap2 = mapper.readValue(jsonMap.get(object).toString(), Map.class);

            Gson gson = new GsonBuilder().serializeNulls().create();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonElement jsonElement = jsonObject.get("expressWorkflow");

            List<ExpressWorkFlow> expressWorkFlowsList = new ArrayList<>();
            expressWorkFlowsList = gson.fromJson(jsonElement, Express_List_Convert_Type);
            if (expressWorkFlowsList != null) {
              // save to DB
              for (ExpressWorkFlow expressWorkFlow : expressWorkFlowsList) {
                ExpressProcess expressProcess = expressWorkFlow.getExpressProcess();

                ExpressProcess processRepository = ExpressServiceRegistry.getProcessService()
                    .findExpressProcessByName(expressProcess.getProcessName());
                if (processRepository != null) {
                  addResultMessage(importExpressResult, "Duplicate process: " + expressProcess.getProcessName());
                  addResultMessage(importExpressResult, "Please edit the process name: " + expressProcess.getProcessName() + " to other");
                  addResultMessage(importExpressResult, "Installed process: " + expressProcess.getProcessName() + " failed");
                  break;
                }

                addResultMessage(importExpressResult, "Installed process: " + expressProcess.getProcessName());

                // Save process
                expressProcess.setId(null);
                BusinessDataInfo<ExpressProcess> info =
                    ExpressServiceRegistry.getProcessService().save(expressProcess);
                String processId = info.getId();

                // Save Task Definition
                List<ExpressTaskDefinition> expressTaskDefinitionList = expressWorkFlow.getExpressTaskDefinitions();
                addResultMessage(importExpressResult, "Installing task defination for process: " + expressProcess.getProcessName());
                expressTaskDefinitionList.forEach(taskDefinition -> {
                  taskDefinition.setId(null);
                  taskDefinition.setProcessID(processId);
                  ExpressServiceRegistry.getTaskDefinitionService().save(taskDefinition);
                });

                addResultMessage(importExpressResult, "Installed task defination for process: " + expressProcess.getProcessName() + "sucessfull");
                // Save Form Elements
                List<ExpressFormElement> expressFormElementList = expressWorkFlow.getExpressFormElements();
                addResultMessage(importExpressResult, "Installing Form element for process: " + expressProcess.getProcessName());
                expressFormElementList.forEach(formElement -> {
                  formElement.setId(null);
                  formElement.setProcessID(processId);
                  ExpressServiceRegistry.getFormElementService().save(formElement);
                });
                importExpressResult
                    .append("Installing Form element for process: " + expressProcess.getProcessName() + "sucessfull");
                importExpressResult.append("\n");
                addResultMessage(importExpressResult, "Installing Form element for process: " + expressProcess.getProcessName() + "sucessfull");

                addResultMessage(importExpressResult, "Installing process: "  + expressProcess.getProcessName() + "sucessfull");
              }
            }
        }
      }

      addResultMessage(importExpressResult, "Installation Express finished");
    }

    return importExpressResult.toString();
  }

  private void addResultMessage(StringBuilder importExpressResult, String message) {
    importExpressResult.append(message);
    importExpressResult.append("\n");
  }

  /** Export Express process to JSon file.
   * @param selectedExpressProcesses
   * @return a StreamedContent with content type as a JSon file
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
    JsonElement jsonElement = gson.toJsonTree(expressWorkFlowsList, Express_List_Convert_Type);

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty(VERSION, 1);
    jsonObject.add(EXPRESS_WORKFLOW, jsonElement);

    InputStream inputStream = new ByteArrayInputStream(jsonObject.toString().getBytes());
    return new DefaultStreamedContent(inputStream, MediaType.APPLICATION_JSON, "express-test.json");
  }

}
