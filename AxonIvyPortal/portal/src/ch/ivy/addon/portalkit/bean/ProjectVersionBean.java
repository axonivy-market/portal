package ch.ivy.addon.portalkit.bean;

import static ch.ivy.addon.portalkit.constant.PortalConstants.PORTAL_LIBRARY_ID;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.ivydata.service.impl.CustomerProjectService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.ReleaseState;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ProjectVersionBean implements Serializable {

  private static final long serialVersionUID = -2148042793400166168L;
  private String engineVersion;
  private String portalVersion;
  private Map<String, List<IProcessModelVersion>> customerProjects;

  public String getEngineVersion() {
    return engineVersion;
  }

  public String getPortalVersion() {
    return portalVersion;
  }

  public Map<String, List<IProcessModelVersion>> getCustomersProjects() {
    return customerProjects;
  }

  public void loadProjectVesion() {
    engineVersion = ch.ivyteam.ivy.Advisor.getAdvisor().getVersion().toString();
    portalVersion = portalVersion();
    customerProjects = new CustomerProjectService().collect();
  }

  public String translateReleaseState(ReleaseState state) {
    return Ivy.cms().co(String.format("/ch.ivy.addon.portalkit.ui.jsf/Enums/ReleaseState/%s", state.toString()));
  }

  private String portalVersion() {
    return IApplication.current().getProcessModelVersions()
        .filter(pmv -> ReleaseState.RELEASED.equals(pmv.getApplication().getReleaseState()))
        .filter(pmv -> PORTAL_LIBRARY_ID.equals(pmv.getLibraryId()))
        .findAny()
        .map(IProcessModelVersion::getLibraryVersion)
        .orElse(null);
  }
}
