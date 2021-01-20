package ch.ivy.addon.portalkit.publicapi;

import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;

/**
 * Portal API for process start
 *
 */
public final class ProcessStartAPI {
  private ProcessStartAPI() {}
  
  /**
   * Find startable link from friendly request path
   * @param friendlyRequestPath friendly path e.g "Start Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start link which session user can start or empty string
   */
  public static String findStartableLinkByUserFriendlyRequestPath(String friendlyRequestPath) {
    return IvyExecutor.executeAsSystem(() -> {
      IProcessStart processStart = findStartableProcessStartByUserFriendlyRequestPath(friendlyRequestPath);
        return processStart != null ? processStart.getLink().getRelative() : StringUtils.EMPTY; 
    });
  }
  
  /**
   * Find start link from friendly request path
   * @param friendlyRequestPath friendly path e.g "Start Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start link or empty string
   */
  public static String findRelativeUrlByProcessStartFriendlyRequestPath(String friendlyRequestPath) {
    IProcessStart processStart = findProcessStartByUserFriendlyRequestPath(friendlyRequestPath);
    return processStart != null ? processStart.getLink().getRelative() : StringUtils.EMPTY;
  }
  
  private static IProcessStart findStartableProcessStartByUserFriendlyRequestPath(String requestPath) {
    IApplication application = Ivy.wf().getApplication();
    return filterPMV(requestPath, application)
      .filter(processStart -> isStartableProcessStart(processStart.getFullUserFriendlyRequestPath()))
      .findFirst().orElse(null);
  }

  
  private static boolean isActive(IProcessModelVersion processModelVersion) {
    return processModelVersion != null && processModelVersion.getActivityState() == ActivityState.ACTIVE;
  }

  private static boolean isActive(IProcessModel processModel) {
    return processModel.getActivityState() == ActivityState.ACTIVE;
  }
  
  private static IProcessStart getProcessStart(String requestPath, IProcessModelVersion processModelVersion) {
    return IWorkflowProcessModelVersion.of(processModelVersion).findStartElementByUserFriendlyRequestPath(requestPath);
  }
  
  private static boolean isStartableProcessStart(String fullUserFriendlyRequestPath) {
    return Ivy.session().getStartableProcessStarts()
        .stream()
        .map(IProcessStart::getFullUserFriendlyRequestPath)
        .filter(startablePorcessRequestPath -> startablePorcessRequestPath.equals(fullUserFriendlyRequestPath))
        .findFirst().isPresent();
  }
  
  private static IProcessStart findProcessStartByUserFriendlyRequestPath(String requestPath) {
    return IvyExecutor.executeAsSystem(() -> {
      IProcessStart processStart = getProcessStart(requestPath, Ivy.request().getProcessModelVersion());
      if (processStart != null) {
        return processStart;
      }

      IApplication application = Ivy.wf().getApplication();
      return filterPMV(requestPath, application)
        .findFirst().orElse(null);
    });
  }
  
  private static Stream<IProcessStart> filterPMV(String requestPath, IApplication application) {
    return application.getProcessModelsSortedByName()
      .stream()
      .filter(pm -> isActive(pm))
      .map(IProcessModel::getReleasedProcessModelVersion)
      .filter(pmv -> isActive(pmv))
      .map(p -> getProcessStart(requestPath, p))
      .filter(Objects::nonNull);
  }
}
