package com.axonivy.portal.developerexamples.publicapi;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;
import com.axonivy.portal.developerexamples.enums.SessionAttribute;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.StandardProcessType;

public final class PortalNavigatorAPI extends BaseNavigator {
  private static final String PORTAL_PROCESS_START_NAME = "Start Processes/PortalStart/DefaultApplicationHomePage.ivp";
  private static final String PORTAL_PASSWORD_RESET = "Start Processes/PortalStart/PasswordResetPage.ivp";
  private PortalNavigatorAPI() {}


  public static void navigateToPortalHome() {
    navigateByKeyword("DefaultApplicationHomePage.ivp", PORTAL_PROCESS_START_NAME, new HashMap<>());
  }


  public static void navigateToPortalEndPage() {
    String customizePortalEndPage = getRelativeLink(StandardProcessType.DefaultEndPage);
    Ivy.session().setAttribute(SessionAttribute.IS_TASK_FINISHED.toString(), false);
    redirectURL(String.format("%s?endedTaskId=%s", customizePortalEndPage, Ivy.wfTask().getId()));
  }

  public static String getPasswordResetUrl(String token, String username) {
    Map<String, String> params = new HashMap<>();
    params.put("token", token);
    params.put("username", username);
    return buildUrl(PORTAL_PASSWORD_RESET, params);
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
