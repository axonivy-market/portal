package ch.ivy.gawfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import gawfs.TaskDef;
import ch.ivy.addon.portalkit.bo.ExpressFormElement;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.ExpressTaskDefinition;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.gawfs.enums.FormElementType;
import ch.ivy.gawfs.enums.ProcessType;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;

public class ExpressProcessUtils {

  private static final String HEADER_PANEL = "HEADER";
  private static final String FOOTER_PANEL = "FOOTER";
  private static final String LEFT_PANEL = "LEFTPANEL";
  private static final String RIGHT_PANEL = "RIGHTPANEL";

  /**
   * Save Express process to repository as business data
   * 
   * @param processId
   * @param name
   * @param description
   * @param type
   * @param isUseDefaultUI
   * @param definedTasks
   * 
   * @return Express process after saved
   */
  public ExpressProcess saveProcess(String processId, String name, String description, ProcessType type, boolean isUseDefaultUI, List<TaskDef> definedTasks) {
    ExpressProcess processRepository
      = Optional.ofNullable(ExpressServiceRegistry.getProcessService().findById(processId)).orElse(new ExpressProcess());

    processRepository.setProcessName(name);
    processRepository.setProcessDescription(description);
    processRepository.setProcessType(type.getValue());
    processRepository.setUseDefaultUI(isUseDefaultUI);
    processRepository.setProcessOwner(Ivy.session().getSessionUser().getMemberName());
    processRepository.setProcessPermissions(definedTasks.get(0).getResponsibles().stream().map(ISecurityMember::getMemberName).collect(Collectors.toList()));

    BusinessDataInfo<ExpressProcess> info = ExpressServiceRegistry.getProcessService().save(processRepository);
    processRepository.setId(info.getId());

    saveDefinedTasks(processRepository.getId(), definedTasks);

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
    for (TaskDef taskDef: definedTasks){
        ExpressTaskDefinition expressTaskDef = new ExpressTaskDefinition();
        expressTaskDef.setType(taskDef.getTaskType().name());
        expressTaskDef.setSubject(taskDef.getSubject());
        expressTaskDef.setDescription(taskDef.getDescription());
        expressTaskDef.setResponsibles(taskDef.getResponsibles().stream().map(ISecurityMember::getMemberName).collect(Collectors.toList()));
        expressTaskDef.setUntilDays(taskDef.getUntilDays().intValue());
        expressTaskDef.setProcessID(processId);
        expressTaskDef.setTaskPosition(taskDef.getPosition());
        ExpressServiceRegistry.getTaskDefinitionService().save(expressTaskDef);

        saveFormElements(processId, taskDef.getPosition(), taskDef.getDragAndDropController());
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
    for (Formelement element : controller.getSelectedFormelementsHeader()) {
      saveFormElement(element, HEADER_PANEL, processId);
    }

    for (Formelement element : controller.getSelectedFormelementsLeftPanel()) {
      saveFormElement(element, LEFT_PANEL, processId);
    }

    for (Formelement element : controller.getSelectedFormelementsRightPanel()) {
      saveFormElement(element, RIGHT_PANEL, processId);
    }

    for (Formelement element : controller.getSelectedFormelementsFooter()) {
      saveFormElement(element, FOOTER_PANEL, processId);
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
    expressFormElement.setName(element.getName());
    expressFormElement.setRequired(element.getRequired());
    expressFormElement.setProcessID(processId);
    expressFormElement.setOptionsStr(element.getOptionsAsString());

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
    List<ExpressTaskDefinition> expressTaskDefinitions = ExpressServiceRegistry.getTaskDefinitionService().findByProcessId(processId);

    for (ExpressTaskDefinition expressTaskDef : expressTaskDefinitions) {
      TaskDef taskDef = new TaskDef();
      taskDef.setResponsibles(getSecurityMembers(expressTaskDef.getResponsibles()));
      taskDef.setPosition(expressTaskDef.getTaskPosition());
      taskDef.setDescription(expressTaskDef.getDescription());
      taskDef.setSubject(expressTaskDef.getSubject());
      taskDef.setUntilDays(expressTaskDef.getUntilDays());
      taskDef.setResponsibleDisplayName(expressTaskDef.getResponsibleDisplayName());

      initializeControllersForTaskDef(processId, taskDef);
      taskDefinitions.add(taskDef);
    }

    return Helper.sortTasks(taskDefinitions);
  }

  /**
   * Get security members based on responsible names
   * 
   * @param responsibleNames
   * @return security members
   */
  public List<ISecurityMember> getSecurityMembers(List<String> responsibleNames) {
    List<ISecurityMember> securityMembers = new ArrayList<>();
    for (String responsibleName : responsibleNames) {
      ISecurityMember securityMember = Ivy.session().getSecurityContext().findSecurityMember(responsibleName);
      if (securityMember != null) {
        securityMembers.add(securityMember);
      }
    }
    return securityMembers;
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
    updateDragAndDropController(processId, taskDef.getDragAndDropController());

    return taskDef;
  }

  /**
   * Update controller with form elements get from repository by process ID and task position
   * 
   * @param processId
   * @param controller
   */
  private void updateDragAndDropController(String processId, DragAndDropController controller) {
    List<ExpressFormElement> expressFormElements = ExpressServiceRegistry.getFormElementService().findByProcessId(processId);

    for (ExpressFormElement expressElement: expressFormElements){
      Formelement element = new Formelement();
      element.setId(expressElement.getElementID());
      element.setIntSetting(expressElement.getIntSetting());
      element.setLabel(expressElement.getLabel());
      element.setName(expressElement.getName());
      element.setRequired(expressElement.isRequired());

      for (FormElementType type : FormElementType.values()) {
        if (type.getValue() == expressElement.getElementType()) {
          element.setType(type);
        }
      }

      String[] optionsArray = expressElement.getOptionsStr().split(":",-1);
      for (String optionStr : optionsArray) {
        element.addOption(optionStr);
      }

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
      }
    }
  }
}
