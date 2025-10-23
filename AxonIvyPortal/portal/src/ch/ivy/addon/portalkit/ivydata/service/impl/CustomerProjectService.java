package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Strings;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.ReleaseState;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;

public class CustomerProjectService {

  private static final Set<ReleaseState> RELEASE_STATES = Set.of(ReleaseState.RELEASED, ReleaseState.DEPRECATED);

  public Map<String, List<IProcessModelVersion>> collect() {
    var appPmvs = new HashMap<String, List<IProcessModelVersion>>();
    var apps = IApplicationRepository.of(ISecurityContext.current()).all();
    for (var app : apps) {
      var pmvs = app.getProcessModelVersions()
          .filter(pmv -> !PortalConstants.PORTAL_LIBRARY_ID.equals(pmv.getLibraryId()) && RELEASE_STATES.contains(pmv.getReleaseState()))
          .sorted((pmv1, pmv2) -> Strings.CI.compare(pmv1.getProcessModel().getName(), pmv2.getProcessModel().getName()))
          .collect(Collectors.toList());
      appPmvs.put(app.getName(), pmvs);
    }
    return appPmvs;
  }
}
