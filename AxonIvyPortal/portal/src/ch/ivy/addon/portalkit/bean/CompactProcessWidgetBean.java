package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.configuration.UserProcess;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.ProcessStartUtils;
import ch.ivy.addon.portalkit.util.UserUtils;

@Deprecated(since = "9.3")
@ManagedBean
@ViewScoped
public class CompactProcessWidgetBean implements Serializable {

private static final long serialVersionUID = -5889375917550618261L;

  private List<UserProcess> selectedUserProcesses;

  private UserProcess selectedProcess;

  private boolean isDisplayShowAllProcessesLink;
  
  @PostConstruct
  public void init() {
    // used in global search page
    isDisplayShowAllProcessesLink = PermissionUtils.checkAccessFullProcessListPermission();
  }
  
  public void preRender() {
    selectedUserProcesses = new ArrayList<>();
  }
  
  public void initProcesses() {
    isDisplayShowAllProcessesLink = PermissionUtils.checkAccessFullProcessListPermission();
  }

  public boolean isRequiredLanguage(Locale locale) {
    final String requireLanguage = UserUtils.getUserLanguage();
    return StringUtils.equalsIgnoreCase(requireLanguage, locale.getLanguage());
  }
  
  public void sortUserProcessList(List<UserProcess> processes) {
    processes.sort((process1, process2) -> StringUtils.compareIgnoreCase(process1.getProcessName(),
        process2.getProcessName().toLowerCase()));
  }

  public void startProcess(UserProcess userProcess) throws IOException {
    Objects.requireNonNull(userProcess, "User process must not be null");
    String link = userProcess.getLink();
    if (isExternalLink(userProcess)) {
      FacesContext.getCurrentInstance().getExternalContext().redirect(link);
      return;
    }

    if (isExpressProcess(userProcess) && StringUtils.isNotBlank(userProcess.getProcessId())) {
      ProcessStartCollector processStartCollector = new ProcessStartCollector();
      String expressStartLink = processStartCollector.findExpressWorkflowStartLink();
      if (StringUtils.isNotBlank(expressStartLink)) {
        FacesContext.getCurrentInstance().getExternalContext()
            .redirect(expressStartLink + "?workflowID=" + userProcess.getProcessId());
        return;
      }
    }

    link += link.contains("?") ? "&" : "?";
    // Put the "embedInIFrame" param to the process link to open it in the DefaultFramePage process
    // Then this process will open task in IFrame or not based on its "embedInIFrame" String custom field
    FacesContext.getCurrentInstance().getExternalContext().redirect(link + "embedInFrame");
  }

  public UserProcess getSelectedProcess() {
    return selectedProcess;
  }

  public void setSelectedProcess(UserProcess selectedProcess) {
    this.selectedProcess = selectedProcess;
  }

  public List<UserProcess> getSelectedUserProcesses() {
    return selectedUserProcesses;
  }

  public void setSelectedUserProcesses(List<UserProcess> selectedUserProcesses) {
    this.selectedUserProcesses = selectedUserProcesses;
  }

  public boolean isDisplayShowAllProcessesLink() {
    return isDisplayShowAllProcessesLink;
  }

  public void setDisplayShowAllProcessesLink(boolean isDisplayShowAllProcessesLink) {
    this.isDisplayShowAllProcessesLink = isDisplayShowAllProcessesLink;
  }
  
  public String targetToStartProcess(UserProcess process) {
    String target = "_self";
    if (isExternalLink(process)) {
      target = "_blank";
    }
    return target;
  }

  public boolean isExternalLink(UserProcess process) {
    return process != null && ProcessStartUtils.isExternalLink(process.getProcessType());
  }

  public boolean isExpressProcess(UserProcess process) {
    return process != null && ProcessStartUtils.isExpressProcess(process.getProcessType());
  }
}
