package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;

public final class ProcessUtils {
  private static final String PORTAL_CASE_DETAILS_SHORT_NAME = "CaseDetailsPage.ivp";
  private static final String PORTAL_CASE_DETAILS = "Start Processes/PortalStart/CaseDetailsPage.ivp";
  private static final String SLASH = "/";

  public static String getURLPortalCaseDetails(Long caseId) {
    Map<String, String> params = createPortalCaseDetailsParams(caseId);
    return getURLByKeyword(PORTAL_CASE_DETAILS_SHORT_NAME, PORTAL_CASE_DETAILS, params);
  }

  public static String getURLByKeyword(String keyword, String defaultFriendlyRequestPath, Map<String, String> param) {
    String customizePortalFriendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword(keyword);
    if (StringUtils.isNotEmpty(customizePortalFriendlyRequestPath)) {
      return getURL(customizePortalFriendlyRequestPath, param);
    } else {
      return getURL(defaultFriendlyRequestPath, param);
    }
  }

  private static Map<String, String> createPortalCaseDetailsParams(Long caseId) {
    Map<String, String> params = new HashMap<>();
    params.put("caseId", String.valueOf(caseId));
    return params;
  }

  private static String getURL(String friendlyRequestPath, Map<String, String> params) {
    String requestPath = SecurityServiceUtils.findProcessByUserFriendlyRequestPath(friendlyRequestPath);
    if (StringUtils.isNotEmpty(requestPath)) {
      try {
        String ivyContextName =
            ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> RequestUriFactory.getIvyContextName());
        String paramStr =
            params.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
        return SLASH + ivyContextName + requestPath
            + (StringUtils.isNotBlank(paramStr) ? "?" + paramStr : StringUtils.EMPTY);
      } catch (Exception e) {
        Ivy.log().error(e);
      }
    }
    return StringUtils.EMPTY;
  }
  
  public static List<IProcessModelVersion> getAllActiveReleasePMVs(IApplication application) {
    if (isActive(application)) {
      return CollectionUtils.emptyIfNull(application.getProcessModelsSortedByName())
        .stream().filter(pm -> isActive(pm))
        .map(IProcessModel::getReleasedProcessModelVersion)
        .filter(pmv -> isActive(pmv))
        .collect(Collectors.toList());
    }
    return new ArrayList<>();
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
}
