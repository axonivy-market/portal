package com.axonivy.portal.components.util;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.SessionAttribute;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpResponse;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessStartUtils {

  public static String findFriendlyRequestPathContainsKeyword(String keyword) {
    return Sudo.get(() -> {
      Object portalStartPmvId = Ivy.session().getAttribute(SessionAttribute.PORTAL_START_PMV_ID.toString());
      return findFriendlyRequestPathContainsKeyword(keyword, portalStartPmvId);
    });
  }

  public static IProcessStart findProcessStartByUserFriendlyRequestPath(String requestPath) {
    return Sudo.get(() -> {
      IProcessStart processStart = findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, Ivy.request().getProcessModelVersion());
      if (processStart != null) {
        return processStart;
      }

      List<IApplication> applicationsInSecurityContext = IApplicationRepository.of(ISecurityContext.current()).all();

      List<IProcessModel> processModels = applicationsInSecurityContext.stream()
          .map(IApplication::getProcessModelsSortedByName).flatMap(List::stream).collect(Collectors.toList());

      for (IProcessModel processModel : processModels) {
        Optional<IProcessStart> processStartOptional = Optional.of(processModel).filter(pm -> isActive(pm))
            .map(IProcessModel::getReleasedProcessModelVersion).filter(pmv -> isActive(pmv))
            .map(p -> findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, p)).filter(Objects::nonNull);
        if (processStartOptional.isPresent()) {
          return processStartOptional.get();
        }
      }
      return processStart;
    });
  }

  private static IProcessStart findProcessStartByUserFriendlyRequestPathAndPmv(String requestPath,
      IProcessModelVersion processModelVersion) {
    return IWorkflowProcessModelVersion.of(processModelVersion).findStartElementByUserFriendlyRequestPath(requestPath);
  }

  /**
   * Find startable link from friendly request path, only matching against the given list of allowed/startable
   * processes.
   *
   * @param friendlyRequestPath friendly path e.g "Start
   *                            Processes/UserExampleGuide/userExampleGuide.ivp"
   * @param startableProcesses processes that the resolved link is allowed to match
   * @return start link which session user can start or empty string
   */
  public static String findStartableLinkByUserFriendlyRequestPath(String friendlyRequestPath,
      List<IWebStartable> startableProcesses) {
    return Sudo.get(() -> {
      List<IApplication> applicationsInSecurityContext = IApplicationRepository.of(ISecurityContext.current()).all();
      for (IApplication app : applicationsInSecurityContext) {
        IProcessStart processStart = findStartableProcessStartByUserFriendlyRequestPath(friendlyRequestPath, app,
            startableProcesses);
        if (processStart != null) {
          return processStart.getLink().getRelative();
        }
      }
      return StringUtils.EMPTY;
    });
  }

  private static IProcessStart findStartableProcessStartByUserFriendlyRequestPath(String requestPath,
      IApplication application, List<IWebStartable> startableProcesses) {
    return filterActivePMVOfApp(application)
        .map(pmv -> findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, pmv)).filter(Objects::nonNull)
        .filter(processStart -> isStartableProcessStart(processStart.getLink().getRelative(), startableProcesses))
        .findFirst().orElse(null);
  }

  private static boolean isStartableProcessStart(String processRelativeLink, List<IWebStartable> startableProcesses) {
    return startableProcesses.stream().map(IWebStartable::getLink)
        .filter(webLink -> webLink.getRelative().equals(processRelativeLink)).findFirst().isPresent();
  }

  private static Stream<IProcessModelVersion> filterActivePMVOfApp(IApplication application) {
    return application.getProcessModelsSortedByName().stream().filter(pm -> isActive(pm))
        .map(IProcessModel::getReleasedProcessModelVersion).filter(pmv -> isActive(pmv));
  }

  private static boolean isActive(IProcessModel processModel) {
    return processModel.getActivityState() == ActivityState.ACTIVE;
  }

  private static boolean isActive(IProcessModelVersion processModelVersion) {
    return processModelVersion != null && processModelVersion.getActivityState() == ActivityState.ACTIVE;
  }

  public static void redirect(String uri) throws java.io.IOException {
    IHttpResponse httpResponse = (IHttpResponse) Ivy.response();
    httpResponse.sendRedirect(uri);
  }
  
  public static String findFriendlyRequestPathContainsKeyword(String keyword, Object portalStartPmvId) {
    if (portalStartPmvId == null) {
      return findFriendlyRequestPathContainsKeywordInPMV(keyword, Ivy.wfTask().getProcessModelVersion());
    } else {
      List<IApplication> applicationsInSecurityContext = IApplicationRepository.of(ISecurityContext.current()).all();
      for (IApplication app : applicationsInSecurityContext) {
        IProcessModelVersion findProcessModelVersion = app.findProcessModelVersion(portalStartPmvId);
        if (findProcessModelVersion != null) {
          return findFriendlyRequestPathContainsKeywordInPMV(keyword, findProcessModelVersion);
        }
      }
    }
    return StringUtils.EMPTY;
  }
  
  private static String findFriendlyRequestPathContainsKeywordInPMV(String keyword, IProcessModelVersion processModelVersion) {
    if (processModelVersion != null) {
      List<IProcessStart> processStarts =
          findProcessStartRequestPathContainsKeywordAndPmv(keyword, processModelVersion);
      if (CollectionUtils.isNotEmpty(processStarts)) {
        return processStarts.get(0).getUserFriendlyRequestPath();
      }
    }
    return StringUtils.EMPTY;
  }

  private static List<IProcessStart> findProcessStartRequestPathContainsKeywordAndPmv(String keyword,
      IProcessModelVersion processModelVersion) {
    IWorkflowProcessModelVersion workflowPmv = IWorkflowProcessModelVersion.of(processModelVersion);
    return workflowPmv.getProcessStarts().stream()
        .filter(processStart -> processStart.getUserFriendlyRequestPath().contains(keyword))
        .collect(Collectors.toList());
  }
}
