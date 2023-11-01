package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
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

@ManagedBean
@ViewScoped
public class CompactProcessWidgetBean implements Serializable {

private static final long serialVersionUID = -5889375917550618261L;

  private boolean isDisplayShowAllProcessesLink;
  
  @PostConstruct
  public void init() {
    // used in global search page
    isDisplayShowAllProcessesLink = PermissionUtils.checkAccessFullProcessListPermission();
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

  public boolean isDisplayShowAllProcessesLink() {
    return isDisplayShowAllProcessesLink;
  }

  public void setDisplayShowAllProcessesLink(boolean isDisplayShowAllProcessesLink) {
    this.isDisplayShowAllProcessesLink = isDisplayShowAllProcessesLink;
  }

  private boolean isExternalLink(UserProcess process) {
    return process != null && ProcessStartUtils.isExternalLink(process.getProcessType());
  }

  private boolean isExpressProcess(UserProcess process) {
    return process != null && ProcessStartUtils.isExpressProcess(process.getProcessType());
  }
}
