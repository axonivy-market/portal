package com.axonivy.portal.components.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.ai.IvyToolResultType;
import com.axonivy.portal.components.service.impl.ProcessService;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class AssistantUtils {

  public static String generateLinkToIvyProcess(String link,
      Map<String, String> params) {
    try {
      IWebStartable process = initWebStartable(link);
      return IvyToolResultType.IFRAME
          .format(process.getLink().queryParams(params).getRelative());
    } catch (Exception e) {
      return IvyToolResultType.ERROR
          .format(Ivy.cms().co("/Labels/AI/Error/ErrorWhenProceedRequest"));
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
    return IvyToolResultType.ERROR.format(error);
  }
}
