package ch.ivy.addon.portal.generic.navigation;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.RequestUtil;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.StandardProcessType;

public class BaseNavigator {
  protected static void navigateByKeyword(String keyword, String defaultFriendlyRequestPath, Map<String, String> param) {
    String customizePortalFriendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword(keyword);
    String friendlyRequestPath = StringUtils.defaultIfBlank(customizePortalFriendlyRequestPath, defaultFriendlyRequestPath);
    redirectURL(buildUrl(friendlyRequestPath, param));
  }
  
  protected static void navigate(String friendlyRequestPath, Map<String, String> params) {
    redirectURL(buildUrl(friendlyRequestPath, params));
  }
  
  protected static void redirectURL(String url) {
    try {
      Ivy.log().error("go to redirect URL");
      RequestUtil.redirect(url);
    } catch (IOException ex) {
      throw new PortalException(ex);
    }
  }
  
  protected static String getRelativeLink(StandardProcessType standardProcess) {
    return IvyExecutor.executeAsSystem(() ->
      {
        Ivy.log().error("go to getRelativeLink");
        IProcessStart standardProcessImplementation = Ivy.wf().getStandardProcessImplementation(standardProcess);
        if (standardProcessImplementation != null) {
          Ivy.log().error("go to standardProcessImplementation.getLink().getRelative()");
          return standardProcessImplementation.getLink().getRelative();
        }
        return StringUtils.EMPTY;
      });
  }
  
  private static String buildUrl(String friendlyRequestPath, Map<String, String> params) {
    String requestPath = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(friendlyRequestPath);
    if (StringUtils.isEmpty(requestPath)) {
      return StringUtils.EMPTY;
    }
    String paramStr = params.entrySet().stream().map(e -> {
      return e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.ISO_8859_1);
    }).collect(Collectors.joining("&"));
    return requestPath + (StringUtils.isNotBlank(paramStr) ? "?" + paramStr : StringUtils.EMPTY);
  }
}
