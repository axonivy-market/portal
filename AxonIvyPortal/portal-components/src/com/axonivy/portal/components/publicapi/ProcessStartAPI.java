package com.axonivy.portal.components.publicapi;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.service.impl.ProcessService;
import com.axonivy.portal.components.util.ProcessStartUtils;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

/**
 * Portal API for process start
 *
 */
public final class ProcessStartAPI {

  private ProcessStartAPI() {
  }

  /**
   * Find startable link from friendly request path
   * 
   * @param friendlyRequestPath friendly path e.g "Start
   *                            Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start link which session user can start or empty string
   */
  public static String findStartableLinkByUserFriendlyRequestPath(String friendlyRequestPath) {
    return Sudo.get(() -> {
      var apps = IApplicationRepository.of(ISecurityContext.current()).allReleased();
      for (IApplication app : apps) {
        var webStartable = findWebStartableByUserFriendlyRequestPath(friendlyRequestPath, app);
        if (webStartable != null) {
          return webStartable.getLink().getRelative();
        }
      }
      return "";
    });
  }

  /**
   * Find start link from friendly request path
   * 
   * @param friendlyRequestPath friendly path e.g "Start
   *                            Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start link or empty string
   */
  public static String findRelativeUrlByProcessStartFriendlyRequestPath(String friendlyRequestPath) {
    IWebStartable webStartable = ProcessStartUtils.findWebStartableByUserFriendlyRequestPath(friendlyRequestPath);
    return webStartable != null ? webStartable.getLink().getRelative() : StringUtils.EMPTY;
  }

  private static IWebStartable findWebStartableByUserFriendlyRequestPath(String requestPath, IApplication application) {
    return application.getProcessModelVersions()
        .map(p -> findWebStartableByPathAndPmv(requestPath, p))
        .filter(Objects::nonNull)
        .filter(ws -> isStartableProcess(ws.getLink().getRelative()))
        .findFirst()
        .orElse(null);
  }

  private static IWebStartable findWebStartableByPathAndPmv(String requestPath, IProcessModelVersion processModelVersion) {
    return IWorkflowProcessModelVersion.of(processModelVersion).getAllStartables()
        .filter(ws -> ws.getId().endsWith(requestPath))
        .findFirst().orElse(null);
  }

  private static boolean isStartableProcess(String processRelativeLink) {
    return ProcessService.getInstance().findProcesses().stream()
        .map(IWebStartable::getLink)
        .anyMatch(webLink -> webLink.getRelative().equals(processRelativeLink));
  }
}
