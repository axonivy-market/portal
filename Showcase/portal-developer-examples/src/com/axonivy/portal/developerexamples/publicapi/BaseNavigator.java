package com.axonivy.portal.developerexamples.publicapi;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.developerexamples.exeption.PortalException;
import com.axonivy.portal.developerexamples.util.ProcessStartUtils;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.StandardProcessType;
import ch.ivyteam.ivy.workflow.standard.DefaultPagesConfigurator;

public class BaseNavigator {
  protected static void navigateByKeyword(String keyword, String defaultFriendlyRequestPath,
      Map<String, String> param) {
    String customizePortalFriendlyRequestPath = ProcessStartUtils.findFriendlyRequestPathContainsKeyword(keyword);
    String friendlyRequestPath =
        StringUtils.defaultIfBlank(customizePortalFriendlyRequestPath, defaultFriendlyRequestPath);
    redirectURL(buildUrl(friendlyRequestPath, param));
  }

  protected static void navigate(String friendlyRequestPath, Map<String, String> params) {
    redirectURL(buildUrl(friendlyRequestPath, params));
  }

  protected static void redirectURL(String url) {
    try {
      ProcessStartUtils.redirect(url);
    } catch (IOException ex) {
      throw new PortalException(ex);
    }
  }

  protected static String getRelativeLink(StandardProcessType standardProcess) {
    return Sudo.get(() -> {
      IProcessStart standardProcessImplementation =
          DefaultPagesConfigurator.of(IApplication.current()).findProcessStart(standardProcess).orElse(null);
      if (standardProcessImplementation != null) {
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
