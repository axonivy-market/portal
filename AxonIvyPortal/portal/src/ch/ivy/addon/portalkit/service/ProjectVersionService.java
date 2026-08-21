package ch.ivy.addon.portalkit.service;

import static ch.ivy.addon.portalkit.constant.PortalConstants.PORTAL_LIBRARY_ID;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Strings;

import ch.ivyteam.ivy.application.app.Application;
import ch.ivyteam.ivy.application.app.ApplicationRepository;
import ch.ivyteam.ivy.application.app.state.ActivityState;
import ch.ivyteam.ivy.application.app.state.CasesCounter;
import ch.ivyteam.ivy.application.app.state.ReleaseState;
import ch.ivyteam.ivy.application.project.Project;
import ch.ivyteam.ivy.application.project.ProjectState.ProjectMode;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;

public class ProjectVersionService {

  private static ProjectVersionService instance;

  private ProjectVersionService() {}

  public static ProjectVersionService getInstance() {
    if (instance == null) {
      instance = new ProjectVersionService();
    }
    return instance;
  }

  public String getEngineVersion() {
    return ch.ivyteam.ivy.Advisor.getAdvisor().getVersion().toString();
  }

  public String getPortalVersion() {
    return Application.current().projects().all()
        .filter(project -> PORTAL_LIBRARY_ID.equals(project.mavenCoordinates().id()))
        .findAny()
        .map(project -> project.mavenCoordinates().version())
        .orElse(null);
  }

  public List<ApplicationInfo> getApplications() {
    return ApplicationRepository.of(ISecurityContext.current()).all()
        .collect(Collectors.groupingBy(Application::name)).entrySet().stream()
        .map(entry -> new ApplicationInfo(entry.getKey(), entry.getValue()))
        .sorted(Comparator.comparing(ApplicationInfo::getName, Strings.CI::compare))
        .collect(Collectors.toList());
  }

  public long getOpenCases(Application version) {
    return CasesCounter.openOf(version);
  }

  public long getDoneCases(Application version) {
    return CasesCounter.doneOf(version);
  }

  public String translateReleaseState(ReleaseState state) {
    return Ivy.cms().co(String.format("/ch.ivy.addon.portalkit.ui.jsf/Enums/ReleaseState/%s", state.name()));
  }

  public String translateActivityState(ActivityState state) {
    return Ivy.cms().co(String.format("/ch.ivy.addon.portalkit.ui.jsf/Enums/ActivityState/%s", state.name()));
  }

  public String translateProjectMode(ProjectMode mode) {
    return Ivy.cms().co(String.format("/ch.ivy.addon.portalkit.ui.jsf/Enums/ProjectMode/%s", mode.name()));
  }

  public String getReleaseStateStyle(Application version) {
    return version.state().releaseState().name().toLowerCase();
  }

  public String getActivityStateStyle(Application version) {
    return version.state().activityState().name().toLowerCase();
  }

  public String getProjectModeStyle(Project project) {
    return project.state().mode().name().toLowerCase();
  }

  public String getReleaseStateIcon(Application version) {
    return iconFor(getReleaseStateStyle(version));
  }

  public String getActivityStateIcon(Application version) {
    return iconFor(getActivityStateStyle(version));
  }

  public String getProjectModeIcon(Project project) {
    return iconFor(getProjectModeStyle(project));
  }

  private String iconFor(String styleKey) {
    switch (styleKey) {
      case "released":
      case "ok":
        return "pi-check-circle";
      case "active":
      case "started":
        return "pi-play";
      case "deprecated":
      case "stopped":
      case "failed":
        return "pi-times-circle";
      case "inactive":
      case "starting":
      case "stopping":
      case "missing":
      case "outdated":
      case "too_old":
      case "too_new":
        return "pi-exclamation-triangle";
      case "created":
      case "prepared":
        return "pi-clock";
      default:
        return "pi-question-circle";
    }
  }

  public static class ApplicationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final List<Application> versions;
    private Application selectedVersion;

    public ApplicationInfo(String name, List<Application> versions) {
      this.name = name;
      this.versions = versions.stream()
          .sorted(Comparator.comparingInt(Application::version).reversed())
          .collect(Collectors.toList());
      this.selectedVersion = this.versions.stream()
          .filter(version -> version.state().releaseState() == ReleaseState.RELEASED)
          .findFirst()
          .orElse(this.versions.isEmpty() ? null : this.versions.get(0));
    }

    public String getName() {
      return name;
    }

    public List<Application> getVersions() {
      return versions;
    }

    public Application getSelectedVersion() {
      return selectedVersion;
    }

    public void setSelectedVersion(Application selectedVersion) {
      this.selectedVersion = selectedVersion;
    }

    public List<Project> getProjects() {
      if (selectedVersion == null) {
        return List.of();
      }
      return selectedVersion.projects().all()
          .filter(project -> !PORTAL_LIBRARY_ID.equals(project.mavenCoordinates().id()))
          .sorted((p1, p2) -> Strings.CI.compare(p1.name(), p2.name()))
          .collect(Collectors.toList());
    }
  }
}
