package com.axonivy.portal.components.examples.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.util.IvyExecutor;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;

public class ProcessStartUtils {
  private ProcessStartUtils() {}

  /**
   * Find start link from friendly request path
   * @param friendlyRequestPath friendly path e.g "Start Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start link or empty string
   */
  public static String findRelativeUrlByProcessStartFriendlyRequestPath(String friendlyRequestPath) {
    IProcessStart processStart = findProcessStartByUserFriendlyRequestPath(friendlyRequestPath);
    return processStart != null ? processStart.getLink().getRelative() : StringUtils.EMPTY;
  }

  public static IProcessStart findProcessStartByUserFriendlyRequestPath(String requestPath) {
    return IvyExecutor.executeAsSystem(() -> {
      IProcessStart processStart = getProcessStart(requestPath, Ivy.request().getProcessModelVersion());
      if (processStart != null) {
        return processStart;
      }
      List<IApplication> applicationsInSecurityContext = IApplicationRepository.instance().allOf(ISecurityContext.current());
      
      for (IApplication app : applicationsInSecurityContext) {
        IProcessStart findProcessStart = filterPMV(requestPath, app).findFirst().orElse(null);
        if (findProcessStart != null) {
          return findProcessStart;
        }
      }
      return null;
    });
  }

  private static IProcessStart getProcessStart(String requestPath, IProcessModelVersion processModelVersion) {
    return IWorkflowProcessModelVersion.of(processModelVersion).findStartElementByUserFriendlyRequestPath(requestPath);
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

  private static boolean isActive(IProcessModelVersion processModelVersion) {
    return processModelVersion != null && processModelVersion.getActivityState() == ActivityState.ACTIVE;
  }

  private static boolean isActive(IProcessModel processModel) {
    return processModel.getActivityState() == ActivityState.ACTIVE;
  }
}
