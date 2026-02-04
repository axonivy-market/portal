package com.axonivy.portal.components.util;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.SessionAttribute;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.ReleaseState;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpResponse;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;

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

      List<IApplication> apps = IApplicationRepository.of(ISecurityContext.current()).allReleased();
      List<IProcessModelVersion> processModelVersions = apps.stream()
        .filter(app -> app.getReleaseState() == ReleaseState.RELEASED)
        .flatMap(app -> app.getProcessModelVersions())
        .toList();
      for (var pmv : processModelVersions) {
        processStart = findProcessStartByUserFriendlyRequestPathAndPmv(requestPath, pmv);
        if (processStart != null) {
          return processStart;
        }
      }
      return processStart;
    });
  }

  private static IProcessStart findProcessStartByUserFriendlyRequestPathAndPmv(String requestPath,
      IProcessModelVersion processModelVersion) {
    return IWorkflowProcessModelVersion.of(processModelVersion).findStartElementByUserFriendlyRequestPath(requestPath);
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
        IProcessModelVersion findProcessModelVersion = app.getProcessModelVersions().filter(pmv -> pmv.getId() == (long) portalStartPmvId).findAny().orElse(null);
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
