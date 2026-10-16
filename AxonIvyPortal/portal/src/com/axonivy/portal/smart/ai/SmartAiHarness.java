package com.axonivy.portal.smart.ai;

import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCallStartEvent;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter.SearchScope;
import ch.ivyteam.ivy.security.exec.Sudo;

public final class SmartAiHarness {

  private static final String INVOKE_AGENT_SIGNATURE = "invokeAgent(String,String,List<String>,Class)";

  private static final String RESULT_OBJECT = "resultObject";
  private static final String PARAM_QUERY = "query";
  private static final String PARAM_SYSTEM_MESSAGE = "systemMessage";
  private static final String PARAM_RESULT_TYPE = "resultType";

  private static final String ERROR_GUARDRAIL_INPUT = "smartworkflow:guardrail:input:violation";
  private static final String ERROR_GUARDRAIL_OUTPUT = "smartworkflow:guardrail:output:violation";
  private static final String ERROR_STOP = "smartworkflow:stop";

  private SmartAiHarness() {}

  public static boolean isAvailable() {
    try {
      return Sudo.get(() -> CollectionUtils.isNotEmpty(SubProcessCallStartEvent.find(filter())));
    } catch (Exception e) {
      Ivy.log().warn("Smart AI: cannot look up the agent callable", e);
      return false;
    }
  }

  public static <T> SmartAiOutcome<T> run(SmartAiFunction<T> function, String query, String systemMessage) {
    try {
      return Sudo.get(() -> {
        var starts = SubProcessCallStartEvent.find(filter());
        if (CollectionUtils.isEmpty(starts)) {
          return SmartAiOutcome.<T>failed(SmartAiFailure.NOT_AVAILABLE, null);
        }

        Map<String, Object> response = starts.get(0)
            .withParam(PARAM_QUERY, query)
            .withParam(PARAM_SYSTEM_MESSAGE, systemMessage)
            .withParam(PARAM_RESULT_TYPE, function.resultType())
            .call()
            .asMap();

        SmartAiOutcome<T> outcome = toOutcome(function, response.get(RESULT_OBJECT));
        return outcome;
      });
    } catch (Exception e) {
      SmartAiOutcome<T> outcome = getError(function, e);
      return outcome;
    }
  }

  private static SubProcessSearchFilter filter() {
    return SubProcessSearchFilter.create()
        .setSearchScope(SearchScope.SECURITY_CONTEXT)
        .setSignature(INVOKE_AGENT_SIGNATURE)
        .toFilter();
  }

  private static <T> SmartAiOutcome<T> toOutcome(SmartAiFunction<T> function, Object resultObject) {
    if (resultObject == null) {
      Ivy.log().error("Smart AI [" + function.name() + "]: the agent returned no result object");
      return SmartAiOutcome.failed(SmartAiFailure.AGENT_ERROR, null);
    }

    if (!function.resultType().isInstance(resultObject)) {
      Ivy.log().error("Smart AI [" + function.name() + "]: the agent returned a "
          + resultObject.getClass().getName()
          + " instead of a " + function.resultType().getSimpleName());
      return SmartAiOutcome.failed(SmartAiFailure.AGENT_ERROR, null);
    }
    return SmartAiOutcome.success(function.resultType().cast(resultObject));
  }

  private static <T> SmartAiOutcome<T> getError(SmartAiFunction<T> function, Exception e) {
    BpmError bpmError = ExceptionUtils.throwableOfType(e, BpmError.class);
    if (bpmError != null) {
      String code = StringUtils.defaultString(bpmError.getErrorCode());
      
      SmartAiFailure failure = switch (code) {
        case ERROR_GUARDRAIL_INPUT -> SmartAiFailure.GUARDRAIL_INPUT;
        case ERROR_GUARDRAIL_OUTPUT -> SmartAiFailure.GUARDRAIL_OUTPUT;
        case ERROR_STOP -> SmartAiFailure.CIRCUIT_BREAKER;
        default -> SmartAiFailure.AGENT_ERROR;
      };
      return SmartAiOutcome.failed(failure, null);
    }
    Ivy.log().error("Smart AI [" + function.name() + "]: the call failed", e);
    return SmartAiOutcome.failed(SmartAiFailure.AGENT_ERROR, e.getMessage());
  }
}
