package ch.ivy.gawfs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.ExpressFormElement;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.ExpressTaskDefinition;
import ch.ivy.addon.portalkit.bo.ExpressUserEmail;
import ch.ivy.addon.portalkit.dto.ExpressAttachment;
import ch.ivy.addon.portalkit.enums.ExpressEmailAttachmentStatus;
import ch.ivy.addon.portalkit.service.ExpressProcessService;
import ch.ivy.addon.portalkit.util.ExpressManagementUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivy.gawfs.enums.FormElementType;
import ch.ivy.gawfs.enums.TaskType;
import ch.ivy.gawfs.mail.MailAttachment;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.ISubProcessStart;
import ch.ivyteam.ivy.process.call.SubProcessRunner;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter.Builder;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
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

  /**
   * Save Express process
   * 
   * @param expressData
   * @return Express process after saved
   */
  public static ExpressProcess saveProcess(Data expressData) {
    return saveOrUpdateWorkflow(expressData);
  }

  /**
   * Save or update Express process to Variable External File
   * 
   * @param expressData
   * @return Express process after saved
   */
  public static ExpressProcess saveOrUpdateWorkflow(Data expressData) {
    ExpressProcess processSaved = ExpressProcessService.getInstance().findById(expressData.getProcessID());
    if (processSaved == null) {
      processSaved =  new ExpressProcess();
    }
    processSaved.setProcessName(expressData.getProcessName());
    processSaved.setProcessDescription(expressData.getProcessDescription());
    processSaved.setProcessType(expressData.getProcessType().getValue());
    processSaved.setUseDefaultUI(expressData.getIsUseDefaultUI());
    processSaved.setIcon(expressData.getProcessIcon());

    if(StringUtils.isBlank(processSaved.getProcessOwner())) {
      String ownerName = Ivy.session().getSessionUser().getMemberName();
      processSaved.setProcessOwner(ExpressManagementUtils.updateExternalIdToSecurityMemberName(ownerName));
    }

    processSaved.setProcessPermissions(ExpressManagementUtils.updateExternalIdsToSecurityMemberNames(expressData.getDefinedTasks().get(0).getResponsibles()));
    processSaved.setProcessCoOwners(ExpressManagementUtils.updateExternalIdsToSecurityMemberNames(expressData.getProcessCoOwners()));
    processSaved.setProcessFolder(expressData.getProcessFolder());
    processSaved.setReadyToExecute(expressData.getReadyToExecute());
    processSaved.setTaskDefinitions(buildDefinedTasks(expressData.getDefinedTasks()));
    return ExpressProcessService.getInstance().save(processSaved);
  }

  /**
   * Build defined tasks from TaskDef
   * 
   * @param definedTasks
   * @return taskDefinitions of process
   */
  private static List<ExpressTaskDefinition> buildDefinedTasks(List<TaskDef> definedTasks) {
    List<ExpressTaskDefinition> taskDefinitions = new ArrayList<>();
    for (TaskDef taskDef : definedTasks) {
      ExpressTaskDefinition taskDefinition = new ExpressTaskDefinition();
      taskDefinition.setType(taskDef.getTaskType().name());
      taskDefinition.setSubject(taskDef.getSubject());
      taskDefinition.setDescription(taskDef.getDescription());
      taskDefinition.setResponsibles(ExpressManagementUtils.updateExternalIdsToSecurityMemberNames(taskDef.getResponsibles()));
      taskDefinition.setUntilDays(taskDef.getUntilDays().intValue());
      taskDefinition.setTaskPosition(taskDef.getPosition());
      taskDefinition.setEmail(taskDef.getEmail());

      if (taskDef.getTaskType() != TaskType.EMAIL && taskDef.getTaskType() != TaskType.APPROVAL) {
        taskDefinition.setFormElements(buildExpressFormElements(taskDef.getDragAndDropController()));
      }
      taskDefinitions.add(taskDefinition);
    }
    return taskDefinitions;
  }

  /**
   * Build form elements with location, and order
   * 
   * @param controller
   */
  private static List<ExpressFormElement> buildExpressFormElements(DragAndDropController controller) {
    List<ExpressFormElement> expressFormElements = new ArrayList<>();
    expressFormElements.addAll(createElementsByLocation(controller.getSelectedFormelementsHeader(), HEADER_PANEL));
    expressFormElements.addAll(createElementsByLocation(controller.getSelectedFormelementsLeftPanel(), LEFT_PANEL));
    expressFormElements.addAll(createElementsByLocation(controller.getSelectedFormelementsRightPanel(), RIGHT_PANEL));
    expressFormElements.addAll(createElementsByLocation(controller.getSelectedFormelementsFooter(), FOOTER_PANEL));
    return expressFormElements;
  }

  private static List<ExpressFormElement> createElementsByLocation(List<Formelement> formElements, String location) {
    List<ExpressFormElement> expressFormElements = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(formElements)) {
      for (Formelement element : formElements) {
        ExpressFormElement expressFormElement = new ExpressFormElement();
        expressFormElement.setElementID(element.getId());
        expressFormElement.setElementPosition(location);
        expressFormElement.setElementType(element.getType().getValue());
        expressFormElement.setIntSetting(Optional.ofNullable(element.getIntSetting()).orElse(0));
        expressFormElement.setLabel(element.getLabel());
        expressFormElement.setRequired(Optional.ofNullable(element.getRequired()).orElse(false));
        expressFormElement.setOptionStrs(element.getOptionsStr());
        expressFormElement.setIndexInPanel(expressFormElements.size());

        expressFormElements.add(expressFormElement);
      }
    }
    return expressFormElements;
  }

  public static List<TaskDef> convertExpressTaskDefinitionToTaskDef(List<ExpressTaskDefinition> expressTaskDefinitions) {
    List<TaskDef> taskDefinitions = new ArrayList<>();
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

      initializeControllersForTaskDef(taskDef, expressTaskDef);
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
  public static String generateResponsibleDisplayName(List<String> responsibleNames) {
    return CollectionUtils.emptyIfNull(responsibleNames)
      .stream()
      .map(responsibleName -> ISecurityContext.current().members().find(responsibleName))
      .filter(securityMember -> securityMember != null)
      .map(securityMember -> SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(securityMember, securityMember.getName()))
      .collect(Collectors.joining(", "));
  }

  /**
   * Get valid security members based on responsible names
   * 
   * @param responsibleNames
   * @return security members
   */
  private static List<String> getValidSecurityMembers(List<String> responsibleNames) {
    if (CollectionUtils.isEmpty(responsibleNames)) {
      return new ArrayList<>();
    }

    List<String> result = new ArrayList<>();
    responsibleNames.forEach(responsibleName -> {
      String validMemberName = ExpressManagementUtils.getValidMemberName(responsibleName);
      if (StringUtils.isNotBlank(validMemberName)) {
        result.add(validMemberName);
      }
    });
    return result;
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
    ISecurityMember securityMember = ISecurityContext.current().members().find(memberName);
    if (securityMember != null) {
      if (securityMember.isUser()) {
        IUser iuser = (IUser) securityMember;
        if (StringUtils.isNotBlank(iuser.getEMailAddress())) {
          emailAddresses.add(iuser.getEMailAddress());
        }
      } else {
        IRole irole = (IRole) securityMember;
        irole.users().allPaged().forEach(userInRole ->{
          if (StringUtils.isNotBlank(userInRole.getEMailAddress())) {
            emailAddresses.add(userInRole.getEMailAddress());
          }
        });
      }
    }
    return emailAddresses;
  }

  /**
   * Initialize controllers for task definition
   * 
   * @param taskDef
   * @param expressTaskDefinition 
   * @return initialized task definition
   */
  private static TaskDef initializeControllersForTaskDef(TaskDef taskDef, ExpressTaskDefinition expressTaskDefinition) {
    DragAndDropController dragAndDropController = new DragAndDropController();
    DynaFormController dynaFormController = new DynaFormController(dragAndDropController);
    dragAndDropController.setDynaFormController(dynaFormController);
    dynaFormController.createForm();

    taskDef.setDynaFormController(dynaFormController);
    taskDef.setDragAndDropController(dragAndDropController);
    if (taskDef.getTaskType() != TaskType.EMAIL && taskDef.getTaskType() != TaskType.APPROVAL) {
      updateDragAndDropController(taskDef, expressTaskDefinition);
    }
    return taskDef;
  }

  private static void updateDragAndDropController(TaskDef taskDef, ExpressTaskDefinition expressTaskDefinition) {
    DragAndDropController controller = taskDef.getDragAndDropController();
    List<ExpressFormElement> expressFormElements = expressTaskDefinition.getFormElements();

    for (ExpressFormElement expressElement : expressFormElements) {
      Formelement element = new Formelement();
      element.setId(expressElement.getElementID());
      element.setIntSetting(expressElement.getIntSetting());
      element.setLabel(expressElement.getLabel());
      element.setRequired(expressElement.isRequired());
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
  
  private static void sortIndexInPanels(DragAndDropController controller) {
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
    return ExpressProcessService.getInstance().findExpressProcessByName(processName) != null;
  }

  public List<ExternalDataProvider> findDataProviders() {
    Builder subprocessFilter = SubProcessSearchFilter.create();
    SubProcessSearchFilter filter = subprocessFilter.setSignature("portalExpressDataProvider()")
        .setSearchInAllProjects(true)
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
}
