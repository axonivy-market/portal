package com.axonivy.portal.components.publicapi;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.service.impl.ProcessService;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class AiAssistantAPI {
  private static final String IFRAME_RESULT_PATTERN = "<iframe>%s</iframe>";
  private static final String ERROR_RESULT_PATTERN = "<error>%s</error>";

  public static String generateLinkToIvyProcess(String link,
      Map<String, String> params) {
    try {
      IWebStartable process = initWebStartable(link);
      return String.format(IFRAME_RESULT_PATTERN,
          process.getLink().queryParams(params).getRelative());
    } catch (Exception e) {
      return String.format(ERROR_RESULT_PATTERN,
          Ivy.cms().co("/Labels/AI/Error/ErrorWhenProceedRequest"));
    }
  }

  private static IWebStartable initWebStartable(String processPath) {
    if (StringUtils.isBlank(processPath)) {
      return null;
    }

    return ProcessService.getInstance().findAllProcesses().stream()
        .filter(process -> process.getId().contentEquals(processPath))
        .findFirst().orElse(null);
  }

  public static String generateErrorResult(String error) {
    if (StringUtils.isBlank(error)) {
      return "";
    }
    return String.format(ERROR_RESULT_PATTERN, error);
  }
}
