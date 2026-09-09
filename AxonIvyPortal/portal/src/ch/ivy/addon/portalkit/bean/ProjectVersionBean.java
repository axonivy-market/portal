package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import org.primefaces.event.SelectEvent;

import com.axonivy.portal.bo.ApplicationInfo;
import com.axonivy.portal.service.ProjectVersionService;

import ch.ivyteam.ivy.application.app.Application;
import ch.ivyteam.ivy.application.app.state.ActivityState;
import ch.ivyteam.ivy.application.app.state.ReleaseState;
import ch.ivyteam.ivy.application.project.Project;
import ch.ivyteam.ivy.application.project.ProjectState.ProjectMode;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ProjectVersionBean implements Serializable {

  private static final long serialVersionUID = -2148042793400166168L;

  private String engineVersion;
  private String portalVersion;
  private List<ApplicationInfo> applications;

  public String getEngineVersion() {
    return engineVersion;
  }

  public String getPortalVersion() {
    return portalVersion;
  }

  public List<ApplicationInfo> getApplications() {
    return applications;
  }

  public void loadProjectVesion() {
    engineVersion = ProjectVersionService.getInstance().getEngineVersion();
    portalVersion = ProjectVersionService.getInstance().getPortalVersion();
    applications = ProjectVersionService.getInstance().getApplications();
  }

  public void selectVersion(SelectEvent<Application> event) {
    var appInfo = (ApplicationInfo) event.getComponent().getAttributes().get("appInfo");
    appInfo.setSelectedVersion(event.getObject());
  }

  public long getOpenCases(Application version) {
    return ProjectVersionService.getInstance().getOpenCases(version);
  }

  public long getDoneCases(Application version) {
    return ProjectVersionService.getInstance().getDoneCases(version);
  }

  public String translateReleaseState(ReleaseState state) {
    return ProjectVersionService.getInstance().translateReleaseState(state);
  }

  public String translateActivityState(ActivityState state) {
    return ProjectVersionService.getInstance().translateActivityState(state);
  }

  public String translateProjectMode(ProjectMode mode) {
    return ProjectVersionService.getInstance().translateProjectMode(mode);
  }

  public String getReleaseStateStyle(Application version) {
    return ProjectVersionService.getInstance().getReleaseStateStyle(version);
  }

  public String getActivityStateStyle(Application version) {
    return ProjectVersionService.getInstance().getActivityStateStyle(version);
  }

  public String getProjectModeStyle(Project project) {
    return ProjectVersionService.getInstance().getProjectModeStyle(project);
  }

  public String getReleaseStateIcon(Application version) {
    return ProjectVersionService.getInstance().getReleaseStateIcon(version);
  }

  public String getActivityStateIcon(Application version) {
    return ProjectVersionService.getInstance().getActivityStateIcon(version);
  }

  public String getProjectModeIcon(Project project) {
    return ProjectVersionService.getInstance().getProjectModeIcon(project);
  }
}
