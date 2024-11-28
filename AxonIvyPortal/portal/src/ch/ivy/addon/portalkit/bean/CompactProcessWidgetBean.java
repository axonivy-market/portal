package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.axonivy.portal.service.GlobalSearchService;

import ch.ivy.addon.portalkit.configuration.UserProcess;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.util.PermissionUtils;

@ManagedBean
@ViewScoped
public class CompactProcessWidgetBean implements Serializable {

private static final long serialVersionUID = -5889375917550618261L;

  private boolean isDisplayShowAllProcessesLink;
  private boolean isGlobalSearchScope;
  
  @PostConstruct
  public void init() {
    // used in global search page
    isDisplayShowAllProcessesLink = PermissionUtils.checkAccessFullProcessListPermission();
    setGlobalSearchScope(isEnableGlobalSearchScopeProcesses());
  }
  
  public void startProcess(UserProcess userProcess) throws IOException {
    Objects.requireNonNull(userProcess, "User process must not be null");
    String link = userProcess.getLink();
    if (isExternalLink(userProcess)) {
      FacesContext.getCurrentInstance().getExternalContext().redirect(link);
      return;
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

  public boolean isGlobalSearchScope() {
    return isGlobalSearchScope;
  }

  public void setGlobalSearchScope(boolean isGlobalSearchScope) {
    this.isGlobalSearchScope = isGlobalSearchScope;
  }

  private boolean isExternalLink(UserProcess process) {
    return process != null && process.getProcessType() == ProcessType.EXTERNAL_LINK;
  }

  private boolean isEnableGlobalSearchScopeProcesses() {
    return GlobalSearchService.getInstance().isShowGlobalSearchByProcesses();
  }

}
