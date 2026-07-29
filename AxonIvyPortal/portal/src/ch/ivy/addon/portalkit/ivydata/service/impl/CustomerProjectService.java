package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Strings;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivyteam.ivy.application.app.ApplicationRepository;
import ch.ivyteam.ivy.application.app.state.ReleaseState;
import ch.ivyteam.ivy.application.project.Project;
import ch.ivyteam.ivy.security.ISecurityContext;

public class CustomerProjectService {

  private static final Set<ReleaseState> RELEASE_STATES = Set.of(ReleaseState.RELEASED, ReleaseState.DEPRECATED);

  public Map<String, List<Project>> collect() {
    var appPmvs = new HashMap<String, List<Project>>();
    var apps = ApplicationRepository.of(ISecurityContext.current()).all().toList();
    for (var app : apps) {
      var pmvs = app.projects().all()
          .filter(pmv -> !PortalConstants.PORTAL_LIBRARY_ID.equals(pmv.mavenCoordinates().id()) && RELEASE_STATES.contains(pmv.app().state().releaseState()))
          .sorted((pmv1, pmv2) -> Strings.CI.compare(pmv1.name(), pmv2.name()))
          .collect(Collectors.toList());
      appPmvs.put(app.name(), pmvs);
    }
    return appPmvs;
  }
}
