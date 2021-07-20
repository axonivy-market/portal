package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.ExternalLink;
import ch.ivy.addon.portalkit.bo.ExternalLinkProcessItem;
import ch.ivy.addon.portalkit.bo.IvyProcess;
import ch.ivy.addon.portalkit.bo.PortalExpressProcess;
import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.enums.ProcessMode;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.ivydata.service.impl.UserSettingService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.persistence.domain.GlobalSetting;
import ch.ivy.addon.portalkit.service.ExpressProcessService;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class ProcessWidgetBean implements Serializable {

  private static final long serialVersionUID = -5889375917550618261L;
  private static final String SPECIAL_CHARACTER_KEY = "SPECIAL_CHARACTER";

  private Process deletedProcess;
  private Process editedProcess;
  private ExternalLink editedExternalLink;
  private String selectedIconProcess;
  private boolean isListMode;

  private IProcessStart createExpressWorkflowProcessStart;
  private Map<String, List<Process>> processesByAlphabet;
  List<Process> portalProcesses;

  @PostConstruct
  public void init() {
    initProcessViewMode();
    
    ProcessStartCollector collector = new ProcessStartCollector();
    createExpressWorkflowProcessStart = collector.findExpressCreationProcess();

    portalProcesses = findProcesses();
    portalProcesses.addAll(findExpressProcesses());
    portalProcesses.addAll(findExternalLink());
    sortProcesses(portalProcesses);
    groupProcessesByAlphabetIndex(portalProcesses);
  }

  public String getProcessInformationPageUrl(Process process) {
    String processId = StringUtils.EMPTY;
    
    Object nestedProcess = process.getProcess();
    if (nestedProcess instanceof IWebStartable) {
      processId = ((IWebStartable) nestedProcess).getId();
    }
    return PortalNavigator.buildProcessInfoUrl(processId);
  }

  private void initProcessViewMode() {
    String userProcessSetting = UserSettingService.newInstance().getDefaultProcessMode();
    if (StringUtils.isBlank(userProcessSetting) || StringUtils.equalsIgnoreCase(userProcessSetting, UserSettingService.DEFAULT)) {
      GlobalSettingService globalSettingService = new GlobalSettingService();
      GlobalSetting defaultSetting = globalSettingService.findGlobalSettingByKey(GlobalVariable.DEFAULT_PROCESS_MODE.name());
      userProcessSetting = defaultSetting.getDisplayValue();
    }

    isListMode = ProcessMode.LIST.getLabel().equalsIgnoreCase(userProcessSetting) ? true : false;
  }

  private void groupProcessesByAlphabetIndex(List<Process> processes) {
    processesByAlphabet = new HashMap<>();
    // Follow Oracle document about regex for punctual character
    // https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
    String punctualRegex = "\\p{Punct}";

    for (Process process : processes) {
      String firstLetter = extractProcessFirstLetter(process.getName());
      if (StringUtils.isNotEmpty(firstLetter)) {
        if (firstLetter.matches(punctualRegex)) {
          addOrUpdateProcessesByKey(process, SPECIAL_CHARACTER_KEY);
        } else {
          addOrUpdateProcessesByKey(process, firstLetter);
        }
      }
    }

    List<Process> processesBySpecialCharacterGroup = processesByAlphabet.remove(SPECIAL_CHARACTER_KEY);
    Collator collator = Collator.getInstance(Locale.GERMAN);
    processesByAlphabet = processesByAlphabet.entrySet().stream().sorted(Map.Entry.comparingByKey(collator::compare))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    if (CollectionUtils.isNotEmpty(processesBySpecialCharacterGroup)) {
      processesByAlphabet.put(SPECIAL_CHARACTER_KEY, processesBySpecialCharacterGroup);
    }
  }

  private String extractProcessFirstLetter(String processName) {
    String firstLetter = "";
    String processNameUpperCase = StringUtils.trim(processName).toUpperCase();
    if (StringUtils.isNotEmpty(processNameUpperCase)) {
      firstLetter = processNameUpperCase.substring(0, 1);
    }
    return firstLetter;
  }
  
  private void addOrUpdateProcessesByKey(Process process, String key) {
    if (!processesByAlphabet.containsKey(key)) {
      List<Process> processes = new ArrayList<>();
      processes.add(process);
      processesByAlphabet.put(key, processes);
    } else {
      processesByAlphabet.get(key).add(process);
    }
  }

  private List<Process> findProcesses() {
    IvyComponentLogicCaller<List<IWebStartable>> ivyComponentLogicCaller = new IvyComponentLogicCaller<>();
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    List<IWebStartable> processes = ivyComponentLogicCaller.invokeComponentLogic(componentId,
        "#{logic.collectProcesses}", new Object[] {});
    List<Process> defaultPortalProcesses = new ArrayList<>();
    processes.forEach(process -> defaultPortalProcesses.add(new IvyProcess(process)));
    return defaultPortalProcesses;
  }

  private List<Process> findExpressProcesses() {
    List<ExpressProcess> processes = new ArrayList<>();
    ProcessStartCollector processStartCollector = new ProcessStartCollector();
    String expressStartLink = processStartCollector.findExpressWorkflowStartLink();
    if (StringUtils.isNotBlank(expressStartLink)) {
      List<ExpressProcess> workflows = ExpressServiceRegistry.getProcessService().findReadyToExecuteProcessOrderByName();
      for (ExpressProcess wf : workflows) {
        if (PermissionUtils.checkAbleToStartAndAbleToEditExpressWorkflow(wf)) {
          processes.add(wf);
        }
      }
    }
    List<Process> defaultPortalProcesses = new ArrayList<>();
    processes.forEach(process -> defaultPortalProcesses.add(new PortalExpressProcess(process)));
    return defaultPortalProcesses;
  }
  
  private List<Process> findExternalLink() {
    List<ExternalLink> externalLinks = ExternalLinkService.getInstance().findStartableLink(Ivy.session().getSessionUser().getId());
    List<Process> defaultPortalProcesses = new ArrayList<>();
    externalLinks.forEach(externalLink -> defaultPortalProcesses.add(new ExternalLinkProcessItem(externalLink)));
    return defaultPortalProcesses;
  }
  
  private void sortProcesses(List<Process> processes) {
    processes.sort((process1, process2) -> StringUtils.compareIgnoreCase(process1.getName(), process2.getName()));
  }

  public void editExpressWorkflow(ExpressProcess process) throws IOException {
    ProcessStartCollector collector = new ProcessStartCollector();
    String editLink = collector.findExpressWorkflowEditLink(process.getId());
    FacesContext.getCurrentInstance().getExternalContext().redirect(editLink);
  }

  public void deleteProcessWorkflow() {
    if (this.deletedProcess == null) {
      return;
    }

    switch (deletedProcess.getType()) {
      case EXPRESS_PROCESS:
        deleteExpressWorkflow();
        break;
      case EXTERNAL_LINK:
        deleteExternalLink();
        break;
      default:
        break;
    }
  }

  public void deleteExpressWorkflow() {
    String workflowId = this.deletedProcess.getId();
    ExpressServiceRegistry.getProcessService().delete(workflowId);
    ExpressServiceRegistry.getTaskDefinitionService().deleteByProcessId(workflowId);
    ExpressServiceRegistry.getFormElementService().deleteByProcessId(workflowId);
    portalProcesses.remove(portalProcesses.stream().filter(process -> process.getId().equals(deletedProcess.getId())).findFirst().get());
    groupProcessesByAlphabetIndex(portalProcesses);
  }

  public void updateProcessData() {
    if (this.editedProcess == null) {
      return;
    }
    String oldProcessName = this.editedProcess.getName();
    switch (this.editedProcess.getType()) {
      case EXPRESS_PROCESS:
        ExpressProcess expressProcess = updateExpressProcess(editedProcess.getId());
        this.editedProcess = new PortalExpressProcess(expressProcess);
        break;
      case EXTERNAL_LINK:
        ExternalLink externalLink = updateExternalLink(editedProcess.getId());
        this.editedProcess = new ExternalLinkProcessItem(externalLink);
        break;
      default:
        break;
    }
    selectedIconProcess = null;
    updateStartProcessesList(oldProcessName);
    this.editedProcess = null;
  }

  private ExpressProcess updateExpressProcess(String processId) {
    ExpressProcessService expressProcessService = ExpressServiceRegistry.getProcessService();
    ExpressProcess expressProcess = expressProcessService.findById(processId);
    if (expressProcess != null) {
      expressProcess.setIcon(this.selectedIconProcess);
      expressProcessService.save(expressProcess);
    }
    PermissionUtils.checkAbleToStartAndAbleToEditExpressWorkflow(expressProcess);
    return expressProcess;
  }

  private ExternalLink updateExternalLink(String processId) {
    ExternalLinkService externalLinkService = ExternalLinkService.getInstance();
    ExternalLink externalLink = externalLinkService.findById(Long.valueOf(processId));
    if (externalLink != null) {
      externalLink.setIcon(this.selectedIconProcess);
      externalLink.setName(this.editedExternalLink.getName());
      externalLink.setDescription(this.editedExternalLink.getDescription());
      ExternalLinkBean externalLinkBean = ManagedBeans.get("externalLinkBean");
      String correctLink = externalLinkBean.correctLink(this.editedExternalLink.getLink());
      externalLink.setLink(correctLink);
      externalLinkService.save(externalLink);
    }
    return externalLink;
  }

  private void updateStartProcessesList(String oldProcessName) {
    String processId = this.editedProcess.getId();
    String oldProcessNameFirstLetter = extractProcessFirstLetter(oldProcessName);
    String firstLetter = extractProcessFirstLetter(this.editedProcess.getName());
    if (!StringUtils.equals(oldProcessNameFirstLetter, firstLetter)) {
      if (StringUtils.isNotEmpty(oldProcessNameFirstLetter) && this.processesByAlphabet.containsKey(oldProcessNameFirstLetter)) {
        List<Process> processes = this.processesByAlphabet.get(oldProcessNameFirstLetter);
        processes.removeIf(editProcess -> editProcess.getId().equals(processId));
        sortProcesses(processes);
        processesByAlphabet.put(oldProcessNameFirstLetter, processes);
      }
    }

    if (StringUtils.isNotEmpty(firstLetter)) {
      List<Process> processes = this.processesByAlphabet.get(firstLetter);
      if (CollectionUtils.isNotEmpty(processes)) {
        processes.removeIf(editProcess -> editProcess.getId().equals(processId));
      } else {
        processes = new ArrayList<>();
      }
      processes.add(editedProcess);
      sortProcesses(processes);
      processesByAlphabet.put(firstLetter, processes);
    }
  }

  public String getProcessIcon(Process process) {
    return process != null ? process.getIcon() : Process.DEFAULT_PROCESS_ICON;
  }
  
  public void createNewExternalLink() {
    ExternalLinkBean externalLinkBean = (ExternalLinkBean) FacesContext.getCurrentInstance()
        .getApplication()
        .getELResolver()
        .getValue(FacesContext.getCurrentInstance().getELContext(), null, "externalLinkBean");
    externalLinkBean.saveNewExternalLink();
    portalProcesses.add(new ExternalLinkProcessItem(externalLinkBean.getExternalLink()));
    sortProcesses(portalProcesses);
    groupProcessesByAlphabetIndex(portalProcesses);
  }
  
  public void deleteExternalLink() {
    ExternalLinkService.getInstance().delete(Long.parseLong(this.deletedProcess.getId()));
    portalProcesses.remove(portalProcesses.stream()
        .filter(process -> process.getId().equals(this.deletedProcess.getId()))
        .findFirst().get());
    groupProcessesByAlphabetIndex(portalProcesses);
  }

  public String getCreateExpessWorkflowLink() {
    return IvyExecutor.executeAsSystem(() -> {
      return (createExpressWorkflowProcessStart != null) ? createExpressWorkflowProcessStart.getLink().getRelative() : StringUtils.EMPTY;
    });
  }
  
  public void startExpressWorkflowCreationLink() throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(getCreateExpessWorkflowLink());
    return;
  }

  public boolean canCreateExpessWorkflow() {
    return createExpressWorkflowProcessStart != null
        && PermissionUtils.hasPortalPermission(PortalPermission.EXPRESS_CREATE_WORKFLOW);
  }

  public void startProcess(Process process) throws IOException {
    String link = process.getStartLink();
    if (process.getType() == ProcessType.EXPRESS_PROCESS || process.getType() == ProcessType.EXTERNAL_LINK) {
      FacesContext.getCurrentInstance().getExternalContext().redirect(link);
      return;
    }
     
    link += link.contains("?") ? "&" : "?";
    // Put the "embedInIFrame" param to the task start link to open it in the DefaultFramePage process
    // Then this process will open task in IFrame or not based on its "embedInIFrame" String custom field
    FacesContext.getCurrentInstance().getExternalContext().redirect(link + "embedInFrame");
  }

  public Process getDeletedProcess() {
    return deletedProcess;
  }

  public void setDeletedProcess(Process deletedProcess) {
    this.deletedProcess = deletedProcess;
  }

  public Process getEditedProcess() {
    return editedProcess;
  }

  public void setEditedProcess(Process editedProcess) {
    this.editedProcess = editedProcess;
    setSelectedIconProcess(editedProcess.getIcon());
    if (editedProcess.getType().equals(ProcessType.EXTERNAL_LINK)) {
      updateSeletedEditExternalLink(editedProcess);
    }
  }

  private void updateSeletedEditExternalLink(Process editedProcess) {
    this.editedExternalLink = new ExternalLink();
    this.editedExternalLink.setId(Long.valueOf(editedProcess.getId()));
    this.editedExternalLink.setName(editedProcess.getName());
    this.editedExternalLink.setLink(editedProcess.getStartLink());
    this.editedExternalLink.setDescription(editedProcess.getDescription());
  }

  public Map<String, List<Process>> getProcessesByAlphabet() {
    return processesByAlphabet;
  }

  public void setUserProcessByAlphabet(Map<String, List<Process>> processesByAlphabet) {
    this.processesByAlphabet = processesByAlphabet;
  }

  public IProcessStart getCreateExpressWorkflowProcessStart() {
    return createExpressWorkflowProcessStart;
  }

  public Map<String, String> getAllAlphabeticalCharacters() {
    Map<String, String> processGroups = new LinkedHashMap<>();
    for (String processGroupName : processesByAlphabet.keySet()) {
      if (!processGroupName.equals(SPECIAL_CHARACTER_KEY)) {
        processGroups.put(processGroupName, processGroupName);
      } else {
        processGroups.put(SPECIAL_CHARACTER_KEY, "#");
      }
    }
    
    return processGroups;
  }

  public boolean isListMode() {
    return isListMode;
  }

  public void setListMode(boolean isListMode) {
    this.isListMode = isListMode;
  }

  public boolean isIvyProcess(Process process) {
    return !Objects.isNull(process) && process.getType() == ProcessType.IVY_PROCESS;
  }
  
  public boolean isExpressProcess(Process process) {
    return !Objects.isNull(process) && process.getType() == ProcessType.EXPRESS_PROCESS;
  }
  
  public boolean isExternalLink(Process process) {
    return !Objects.isNull(process) && process.getType() == ProcessType.EXTERNAL_LINK;
  }
  
  public boolean isCaseMap(Process process) {
    return !Objects.isNull(process) && process.getStartLink().endsWith(".icm");
    
  }
  
  public String targetToStartProcess(Process process) {
    String target="_self";
    if (process.getType() == ProcessType.EXTERNAL_LINK) {
      target="_blank";
    }
    return target;
  }

  public String getSelectedIconProcess() {
    return selectedIconProcess;
  }

  public void setSelectedIconProcess(String selectedIconProcess) {
    this.selectedIconProcess = selectedIconProcess;
  }

  public ExternalLink getEditedExternalLink() {
    return editedExternalLink;
  }

  public void setEditedExternalLink(ExternalLink editedExternalLink) {
    this.editedExternalLink = editedExternalLink;
  }

  public boolean canChangeProcessIcon() {
    return PermissionUtils.isSessionUserHasAdminRole();
  }
}
