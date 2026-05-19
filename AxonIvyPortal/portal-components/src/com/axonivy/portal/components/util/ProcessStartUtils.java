package com.axonivy.portal.components.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.SessionAttribute;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpResponse;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessStartUtils {

  public static String findFriendlyRequestPathContainsKeyword(String keyword) {
    return Sudo.get(() -> {
      Object portalStartPmvId = Ivy.session().getAttribute(SessionAttribute.PORTAL_START_PMV_ID.toString());
      return findFriendlyRequestPathContainsKeyword(keyword, portalStartPmvId);
    });
  }

  public static Long findTaskStartIdByUserFriendlyRequestPath(String requestPath) {
    return Sudo.get(() -> Ivy.session().getStartableProcessStarts().stream()
        .filter(ps -> ps.getUserFriendlyRequestPath().endsWith(requestPath))
        .findFirst()
        .map(ps -> ps.getTaskStart().getId())
        .orElse(null));
  }

  public static IWebStartable findWebStartableByUserFriendlyRequestPath(String requestPath) {
    return Sudo.get(() -> {
      IWebStartable webStartable = findWebStartableByPathAndPmv(requestPath, Ivy.request().getProcessModelVersion());
      if (webStartable != null) {
        return webStartable;
      }

      List<IApplication> apps = IApplicationRepository.of(ISecurityContext.current()).allReleased();
      List<IProcessModelVersion> processModelVersions = apps.stream()
        .flatMap(app -> app.getProcessModelVersions())
        .toList();
      for (var pmv : processModelVersions) {
        webStartable = findWebStartableByPathAndPmv(requestPath, pmv);
        if (webStartable != null) {
          return webStartable;
        }
      }
      return webStartable;
    });
  }

  private static IWebStartable findWebStartableByPathAndPmv(String requestPath,
      IProcessModelVersion processModelVersion) {
    return IWorkflowProcessModelVersion.of(processModelVersion).getAllStartables()
        .filter(ws -> ws.getId().endsWith(requestPath))
        .findFirst().orElse(null);
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
      return IWorkflowProcessModelVersion.of(processModelVersion).getAllStartables()
          .filter(ws -> ws.getId().contains(keyword))
          .findFirst()
          .map(IWebStartable::getId)
          .orElse(StringUtils.EMPTY);
    }
    return StringUtils.EMPTY;
  }
}
