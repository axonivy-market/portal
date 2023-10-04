package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portal.generic.util.ProcessStepUtils;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.ExternalLinkProcessItem;
import ch.ivy.addon.portalkit.bo.PortalExpressProcess;
import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.bo.ProcessStep;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.mapper.UserProcessMapper;
import ch.ivy.addon.portalkit.service.ExpressProcessService;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public abstract class AbstractProcessBean implements Serializable {

  private static final long serialVersionUID = -8450309463672220642L;
  protected static final String SLASH = "/";
  private List<Process> portalProcesses;

  public synchronized void init() {
    List<Process> processes = new ArrayList<>();
    processes.addAll(findProcesses());
    processes.addAll(findExpressProcesses());
    processes.addAll(findExternalLink());
    portalProcesses = new CopyOnWriteArrayList<Process>(sortProcesses(processes));
  }

  public String getProcessInformationPageUrl(Process process) {
    String processId = StringUtils.EMPTY;

    Object nestedProcess = process.getProcess();
    if (nestedProcess instanceof IWebStartable) {
      processId = ((IWebStartable) nestedProcess).getId();
    }
    return PortalNavigator.buildProcessInfoUrl(processId);
  }

  protected abstract List<Process> findProcesses();

  protected List<Process> findExpressProcesses() {
    List<ExpressProcess> processes = new ArrayList<>();
    String expressStartLink = ProcessStartCollector.getInstance().findExpressWorkflowStartLink();
    if (StringUtils.isNotBlank(expressStartLink)) {
      List<ExpressProcess> workflows = ExpressProcessService.getInstance().findReadyToExecuteProcessOrderByName();
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

  protected List<Process> findExternalLink() {
    List<ExternalLink> privateExternalLinks = ExternalLinkService.getInstance().getPrivateConfig();
    List<ExternalLink> externalLinks = new ArrayList<>(privateExternalLinks);
    externalLinks.addAll(ExternalLinkService.getInstance().filterPublicExternalLinksForIvySessionUser());
    List<Process> defaultPortalProcesses = new ArrayList<>();
    externalLinks.forEach(externalLink -> defaultPortalProcesses.add(new ExternalLinkProcessItem(externalLink)));
    return defaultPortalProcesses;
  }

  protected List<Process> sortProcesses(List<Process> processes) {
    return processes.stream()
        .sorted(Comparator.comparing(Process::getName))
        .collect(Collectors.toList());
  }

  public String getProcessIcon(Process process) {
    return process != null ? process.getIcon() : Process.DEFAULT_PROCESS_ICON;
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

  public boolean isIvyProcess(Process process) {
    return !Objects.isNull(process) && process.getType() == ProcessType.IVY_PROCESS;
  }

  public boolean isExpressProcess(Process process) {
    return !Objects.isNull(process) && process.getType() == ProcessType.EXPRESS_PROCESS;
  }

  public boolean isExternalLink(Process process) {
    return !Objects.isNull(process) && process.getType() == ProcessType.EXTERNAL_LINK;
  }

  public boolean isDefaultProcessImage(String objectPath) {
    return objectPath != null && objectPath.contains("/cm");
  }

  public boolean isCaseMap(Process process) {
    return !Objects.isNull(process) && process.getStartLink().endsWith(".icm");
  }

  public String targetToStartProcess(Process process) {
    return process.getType() == ProcessType.EXTERNAL_LINK ? "_blank" : "_self";
  }

  public String getDisplayProcessCategory(Process process) {
    if (Objects.isNull(process) || Objects.isNull(process.getCategory())) {
      return "";
    }
    String categoryFullPath = process.getCategory().getName();
    String[] arrayOfCategoyPaths = categoryFullPath.split(SLASH);
    int lengthOfCategoryPaths = arrayOfCategoyPaths.length;
    return lengthOfCategoryPaths > 0 ? arrayOfCategoyPaths[lengthOfCategoryPaths - 1] : StringUtils.EMPTY;
  }

  public synchronized List<Process> getPortalProcesses() {
    return portalProcesses;
  }
  
  public Boolean isShowInformationLink(Process process) {
    Boolean hasProcessInfo = false;
    List<ProcessStep> processSteps = null;
    Object nestedProcess = process.getProcess();
    if (nestedProcess instanceof IWebStartable) {
      processSteps = ProcessStepUtils.findProcessStepsOfProcess(UserProcessMapper.toUserProcess(((IWebStartable) nestedProcess)));
    } else if (nestedProcess instanceof DashboardProcess) {
      processSteps = ProcessStepUtils.findProcessStepsOfProcess(UserProcessMapper.toUserProcess(((DashboardProcess) nestedProcess)));
    }
    hasProcessInfo = processSteps != null && processSteps.size() > 0;
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_PROCESS_INFORMATION) && hasProcessInfo;
  }
}
