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
import ch.ivy.addon.portalkit.enums.PortalPermission;
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
  private static final String EXPRESS_WORKFLOW_ID_PARAM = "?workflowID=";

  private String processWidgetComponentId;
  private List<ExpressProcess> expressProcesses;
  private ExpressProcess deletedExpressProcess;
  private boolean mobileMode;
  private IProcessStart createExpressWorkflowProcessStart;
  private Map<String, List<IWebStartable>> processesByAlphabet;

  @PostConstruct
  public void init() {
    processWidgetComponentId = Attrs.currentContext().getBuildInAttribute("clientId");
    mobileMode = BooleanUtils.toBoolean((String) Attrs.currentContext().get("mobileMode"));

    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    createExpressWorkflowProcessStart = collector.findExpressCreationProcess();

    expressProcesses = findExpressProcesses();
    groupProcessesByAlphabetIndex(findProcesses());
  }

  private void groupProcessesByAlphabetIndex(List<IWebStartable> processes) {
    processesByAlphabet = new HashMap<>();
    // Follow Oracle document about regex for punctual character
    // https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
    String punctualRegex = "\\p{Punct}";

    for (IWebStartable process : processes) {
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

    List<IWebStartable> processesBySpecialCharacterGroup = processesByAlphabet.remove(SPECIAL_CHARACTER_KEY);
    Collator collator = Collator.getInstance(Locale.GERMAN);
    processesByAlphabet = processesByAlphabet.entrySet().stream().sorted(Map.Entry.comparingByKey(collator::compare))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    if (CollectionUtils.isNotEmpty(processesBySpecialCharacterGroup)) {
      processesByAlphabet.put(SPECIAL_CHARACTER_KEY, processesBySpecialCharacterGroup);
    }
  }

  private void addOrUpdateProcessesByKey(IWebStartable process, String key) {
    if (!processesByAlphabet.containsKey(key)) {
      List<IWebStartable> processes = new ArrayList<>();
      processes.add(process);
      processesByAlphabet.put(key, processes);
    } else {
      processesByAlphabet.get(key).add(process);
    }
  }

  private List<IWebStartable> findProcesses() {
    IvyComponentLogicCaller<List<IWebStartable>> ivyComponentLogicCaller = new IvyComponentLogicCaller<>();
    List<IWebStartable> processes = ivyComponentLogicCaller.invokeComponentLogic(processWidgetComponentId,
        "#{logic.collectProcesses}", new Object[] {});
    sortProcesses(processes);
    return processes;
  }

  private List<ExpressProcess> findExpressProcesses() {
    List<ExpressProcess> processes = new ArrayList<>();
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.request().getApplication());
    String expressStartLink = processStartCollector.findExpressWorkflowStartLink();
    if (StringUtils.isNotBlank(expressStartLink)) {
      List<ExpressProcess> workflows = ExpressServiceRegistry.getProcessService().findReadyToExecuteProcessOrderByName();
      for (ExpressProcess wf : workflows) {
        if (PermissionUtils.canStartExpressWorkflow(wf)) {
          processes.add(wf);
        }
      }
    }
    sortExpressProcesses(processes);
    return processes;
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
     expressProcesses.remove(deletedExpressProcess);
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
  
  public void startExpressProcess(ExpressProcess process) throws IOException {
    String startLink = generateWorkflowStartLink(process);
    startProcess(startLink);
  }
  
  private String generateWorkflowStartLink(ExpressProcess process) {
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.request().getApplication());
    return processStartCollector.findExpressWorkflowStartLink() + EXPRESS_WORKFLOW_ID_PARAM + process.getId();
  }

  public void startProcess(String link) throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(link);
  }

  public List<ExpressProcess> getExpressProcesses() {
    return expressProcesses;
  }

  public void setExpressProcesses(List<ExpressProcess> expressProcesses) {
    this.expressProcesses = expressProcesses;
  }
  
  public ExpressProcess getDeletedExpressProcess() {
    return deletedExpressProcess;
  }

  public void setDeletedExpressProcess(ExpressProcess deletedExpressProcess) {
    this.deletedExpressProcess = deletedExpressProcess;
  }

  public Map<String, List<IWebStartable>> getProcessesByAlphabet() {
    return processesByAlphabet;
  }

  public void setUserProcessByAlphabet(Map<String, List<IWebStartable>> processesByAlphabet) {
    this.processesByAlphabet = processesByAlphabet;
  }

  public IProcessStart getCreateExpressWorkflowProcessStart() {
    return createExpressWorkflowProcessStart;
  }

}
