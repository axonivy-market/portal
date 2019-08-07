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
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.IvyProcess;
import ch.ivy.addon.portalkit.bo.PortalExpressProcess;
import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class ProcessWidgetBean implements Serializable {

  private static final long serialVersionUID = -5889375917550618261L;
  private static final String SPECIAL_CHARACTER_KEY = "SPECIAL_CHARACTER";

  private String processWidgetComponentId;
  private Process deletedExpressProcess;
  private boolean mobileMode;
  private IProcessStart createExpressWorkflowProcessStart;
  private Map<String, List<Process>> processesByAlphabet;
  List<Process> portalProcesses;

  @PostConstruct
  public void init() {
    processWidgetComponentId = Attrs.currentContext().getBuildInAttribute("clientId");
    mobileMode = BooleanUtils.toBoolean((String) Attrs.currentContext().get("mobileMode"));

    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    createExpressWorkflowProcessStart = collector.findExpressCreationProcess();

    portalProcesses = findProcesses();
    portalProcesses.addAll(findExpressProcesses());
    groupProcessesByAlphabetIndex(portalProcesses);
  }

  private void groupProcessesByAlphabetIndex(List<Process> processes) {
    processesByAlphabet = new HashMap<>();
    // Follow Oracle document about regex for punctual character
    // https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
    String punctualRegex = "\\p{Punct}";

    for (Process process : processes) {
      String processNameUpperCase = StringUtils.trim(process.getName()).toUpperCase();
      if (StringUtils.isNotEmpty(processNameUpperCase)) {
        String firstLetter = processNameUpperCase.substring(0, 1);
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
    List<IWebStartable> processes = ivyComponentLogicCaller.invokeComponentLogic(processWidgetComponentId,
        "#{logic.collectProcesses}", new Object[] {});
    sortProcesses(processes);
    List<Process> defaultPortalProcesses = new ArrayList<>();
    processes.forEach(process -> defaultPortalProcesses.add(new IvyProcess(process)));
    return defaultPortalProcesses;
  }

  private List<Process> findExpressProcesses() {
    List<ExpressProcess> processes = new ArrayList<>();
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.request().getApplication());
    String expressStartLink = processStartCollector.findExpressWorkflowStartLink();
    if (StringUtils.isNotBlank(expressStartLink)) {
      List<ExpressProcess> workflows = ExpressServiceRegistry.getProcessService().findReadyToExecuteProcessOrderByName();
      for (ExpressProcess wf : workflows) {
        if (PermissionUtils.canStartExpressWorkflow(wf)) {
          wf.setAbleToEdit(isAbleToEditExpressProcess(wf));
          processes.add(wf);
        }
      }
    }
    sortExpressProcesses(processes);
    List<Process> defaultPortalProcesses = new ArrayList<>();
    processes.forEach(process -> defaultPortalProcesses.add(new PortalExpressProcess(process)));
    return defaultPortalProcesses;
  }
  
  /**
   * Portal admin or process creator able to edit/delete express process
   * @param process
   * @return true if current user able to edit express process, otherwise return false
   */
  private boolean isAbleToEditExpressProcess(ExpressProcess process) {
    return PermissionUtils.isSessionUserHasAdminRole() || Ivy.session().getSessionUser().getMemberName().equals(process.getProcessOwner()); 
  }

  private void sortProcesses(List<IWebStartable> processes) {
    processes.sort((process1, process2) -> StringUtils.compareIgnoreCase(process1.getName(), process2.getName()));
  }

  private void sortExpressProcesses(List<ExpressProcess> expressProcesses) {
    expressProcesses.sort(
        (process1, process2) -> StringUtils.compareIgnoreCase(process1.getProcessName(), process2.getProcessName()));
  }

  public boolean isMobileMode() {
    return mobileMode;
  }

  public void editExpressWorkflow(ExpressProcess process) throws IOException {
    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    String editLink = collector.findExpressWorkflowEditLink(process.getId());
    startProcess(editLink);
  }

  public void deleteExpressWorkflow() {
     String workflowId = deletedExpressProcess.getId();
     ExpressServiceRegistry.getProcessService().delete(workflowId);
     ExpressServiceRegistry.getTaskDefinitionService().deleteByProcessId(workflowId);
     ExpressServiceRegistry.getFormElementService().deleteByProcessId(workflowId);
     portalProcesses.remove(portalProcesses.stream().filter(process -> process.getId().equals(deletedExpressProcess.getId())).findFirst().get());
     groupProcessesByAlphabetIndex(portalProcesses);
  }

  public String getCreateExpessWorkflowLink() {
    return IvyExecutor.executeAsSystem(() -> {
      if (createExpressWorkflowProcessStart != null) {
        return RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(),
            createExpressWorkflowProcessStart).toString();
      }
      return StringUtils.EMPTY;
    });
  }

  public boolean canCreateExpessWorkflow() {
    return createExpressWorkflowProcessStart != null
        && PermissionUtils.hasPortalPermission(PortalPermission.EXPRESS_CREATE_WORKFLOW);
  }

  public void startProcess(String link) throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(link);
  }
  
  public Process getDeletedExpressProcess() {
    return deletedExpressProcess;
  }

  public void setDeletedExpressProcess(Process deletedExpressProcess) {
    this.deletedExpressProcess = deletedExpressProcess;
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

  public boolean isExpressProcess (Process process) {
    return process.getType() == ProcessType.EXPRESS_PROCESS;
  }
}
