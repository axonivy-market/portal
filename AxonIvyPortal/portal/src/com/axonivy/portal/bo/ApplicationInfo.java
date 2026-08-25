package com.axonivy.portal.bo;

import static ch.ivy.addon.portalkit.constant.PortalConstants.PORTAL_LIBRARY_ID;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Strings;

import ch.ivyteam.ivy.application.app.Application;
import ch.ivyteam.ivy.application.app.state.ReleaseState;
import ch.ivyteam.ivy.application.project.Project;

public class ApplicationInfo implements Serializable {

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
