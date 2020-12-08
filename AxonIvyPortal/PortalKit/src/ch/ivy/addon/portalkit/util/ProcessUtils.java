package ch.ivy.addon.portalkit.util;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

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
}
