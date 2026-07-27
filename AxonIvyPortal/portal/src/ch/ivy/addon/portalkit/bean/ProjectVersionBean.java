package ch.ivy.addon.portalkit.bean;

import static ch.ivy.addon.portalkit.constant.PortalConstants.PORTAL_LIBRARY_ID;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import ch.ivy.addon.portalkit.ivydata.service.impl.CustomerProjectService;
import ch.ivyteam.ivy.application.app.Application;
import ch.ivyteam.ivy.application.app.state.ReleaseState;
import ch.ivyteam.ivy.application.project.Project;
import ch.ivyteam.ivy.environment.Ivy;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ProjectVersionBean implements Serializable {

  private static final long serialVersionUID = -2148042793400166168L;
  private String engineVersion;
  private String portalVersion;
  private Map<String, List<Project>> customerProjects;

  public String getEngineVersion() {
    return engineVersion;
  }

  public String getPortalVersion() {
    return portalVersion;
  }

  public Map<String, List<Project>> getCustomersProjects() {
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
    return Application.current().projects().all()
        .filter(project -> PORTAL_LIBRARY_ID.equals(project.mavenCoordinates().id()))
        .findAny()
        .map(project -> project.mavenCoordinates().version())
        .orElse(null);
  }
}
