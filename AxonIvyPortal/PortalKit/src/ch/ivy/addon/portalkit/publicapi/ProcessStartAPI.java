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
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.WorkflowNavigationUtil;

/**
 * Portal API for process start
 *
 */
public final class ProcessStartAPI {
  private ProcessStartAPI() {}
  
  /**
   * Find startable link from friendly request path
   * @param application application
   * @param friendlyRequestPath friendly path e.g "Start Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start link which session user can start or empty string
   */
  public static String findStartableLinkByUserFriendlyRequestPath(IApplication application, String friendlyRequestPath) {
    return IvyExecutor.executeAsSystem(() -> {
      IProcessStart processStart = findStartableProcessStartByUserFriendlyRequestPath(application, friendlyRequestPath);
      if (processStart != null) {
        return RequestUriFactory.createProcessStartUri(processStart).toASCIIString();
      }
      return StringUtils.EMPTY;
    });
  }
  
  /**
   * Find link from friendly request path
   * @param application application
   * @param friendlyRequestPath friendly path e.g "Start Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start link or empty string
   */
  public static String findLinkByFriendlyRequestPath(IApplication application, String friendlyRequestPath) {
    return IvyExecutor.executeAsSystem(() -> {
      IProcessStart process = findProcessStartByUserFriendlyRequestPath(application, friendlyRequestPath);
      if (process != null) {
        return RequestUriFactory.createProcessStartUri(process).toASCIIString();
      }
      return StringUtils.EMPTY;
    });
  }
  
  private static IProcessStart findStartableProcessStartByUserFriendlyRequestPath(IApplication application, String requestPath) {
    if (isActive(application)) {
      return filterPMV(requestPath, application)
        .filter(processStart -> Ivy.session().getStartableProcessStarts().contains(processStart))
        .findFirst().orElse(null);
      
    }
    return null;
  }
  
  private static IProcessStart findProcessStartByUserFriendlyRequestPath(IApplication application, String requestPath) {
    IProcessStart processStart = null;
    if (isActive(application)) {
      if (Ivy.request().getApplication().equals(application)) {
        processStart = findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, Ivy.wfTask().getProcessModelVersion());
      }
      if (processStart != null) {
        return processStart;
      }

      return filterPMV(requestPath, application).findFirst().orElse(null);

    }
    return processStart;
  }
  
  private static IProcessStart findProcessStartByUserFriendlyRequestPathAndPmv(String requestPath,
      IProcessModelVersion processModelVersion) {
    IWorkflowProcessModelVersion workflowPmv =
        WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion);
    return workflowPmv.findProcessStartByUserFriendlyRequestPath(requestPath);
  }
  
  private static boolean isActive(IProcessModelVersion processModelVersion) {
    return processModelVersion != null && processModelVersion.getActivityState() == ActivityState.ACTIVE;
  }

  private static boolean isActive(IProcessModel processModel) {
    return processModel.getActivityState() == ActivityState.ACTIVE;
  }

  private static boolean isActive(IApplication ivyApplication) {
    return ivyApplication.getActivityState() == ActivityState.ACTIVE;
  }
  
  private static Stream<IProcessStart> filterPMV(String requestPath, IApplication application) {
    return application.getProcessModelsSortedByName()
      .stream()
      .filter(pm -> isActive(pm))
      .map(IProcessModel::getReleasedProcessModelVersion)
      .filter(pmv -> isActive(pmv))
      .map(p -> findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, p))
      .filter(Objects::nonNull);
  }
}
