package com.axonivy.portal.service;

import static ch.ivy.addon.portalkit.constant.PortalConstants.PORTAL_LIBRARY_ID;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Strings;

import com.axonivy.portal.bo.ApplicationInfo;

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
    return iconFor(version.state().releaseState());
  }

  public String getActivityStateIcon(Application version) {
    return iconFor(version.state().activityState());
  }

  public String getProjectModeIcon(Project project) {
    return iconFor(project.state().mode());
  }

  private String iconFor(ReleaseState state) {
    return switch (state) {
      case RELEASED -> "pi-check-circle";
      case DEPRECATED -> "pi-times-circle";
      case CREATED, PREPARED -> "pi-clock";
      case ARCHIVED -> "pi-question-circle";
    };
  }

  private String iconFor(ActivityState state) {
    return switch (state) {
      case ACTIVE -> "pi-play";
      case INACTIVE -> "pi-exclamation-triangle";
    };
  }

  private String iconFor(ProjectMode mode) {
    return switch (mode) {
      case OK -> "pi-check-circle";
      case MISSING, OUTDATED, TOO_OLD, TOO_NEW -> "pi-exclamation-triangle";
      case UNKNOWN -> "pi-question-circle";
    };
  }
}
