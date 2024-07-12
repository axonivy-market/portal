package com.axonivy.portal.components.publicapi;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.AiResultDTO;
import com.axonivy.portal.components.enums.AiResultState;
import com.axonivy.portal.components.service.impl.ProcessService;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.start.IWebStartable;
import ch.ivyteam.util.ExceptionUtil;

public class AiAssistantAPI {
  private static final String IFRAME_RESULT_PATTERN = "<iframe>%s</iframe>";

  public static void addIvyProcessLinkToAiResult(String link,
      Map<String, String> params, AiResultDTO result) {
    try {
      IWebStartable process = initWebStartable(link);
      result.setResult(String.format(IFRAME_RESULT_PATTERN,
          process.getLink().queryParams(params).getRelative()));
    } catch (Exception e) {
      result = generateErrorAiResult(e,
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

  public static AiResultDTO generateErrorAiResult(Throwable error,
      String errorDescription) {
    AiResultDTO result = new AiResultDTO();
    result.setIsMemory(false);
    result.setResult(errorDescription.concat("\n")
        .concat(ExceptionUtil.getAllMessages(error)));
    result.setState(AiResultState.ERROR);

    return result;
  }

  public static AiResultDTO generateErrorAiResult(String error) {
    AiResultDTO result = new AiResultDTO();
    result.setIsMemory(false);
    result.setResult(error);
    result.setState(AiResultState.ERROR);

    return result;
  }
}
