package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.dto.AiResultDTO;
import com.axonivy.portal.components.enums.AIState;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * {@code addIframeIvyProcessLinkToAiResult} isn't covered: exercising its
 * success path needs a real {@code IWebStartable} from a running process
 * (needs {@code @IvyProcessTest}, not usable yet in this environment).
 */
@IvyTest
class TestAiAssistantAPI {

  @Test
  void generateExecutableResult_wrapsLinkInExecuteTag() {
    assertThat(AiAssistantAPI.generateExecutableResult("some/link")).isEqualTo("<execute>some/link</execute>");
  }

  @Test
  void generateErrorAiResult_fromThrowable_buildsErrorResult() {
    AiResultDTO result = AiAssistantAPI.generateErrorAiResult(new RuntimeException("boom"), "Error description");

    assertThat(result.getState()).isEqualTo(AIState.ERROR);
    assertThat(result.getResult()).contains("Error description").contains("boom");
  }

  @Test
  void generateErrorAiResult_fromString_buildsErrorResult() {
    AiResultDTO result = AiAssistantAPI.generateErrorAiResult("something failed");

    assertThat(result.getState()).isEqualTo(AIState.ERROR);
    assertThat(result.getResult()).isEqualTo("something failed");
    assertThat(result.getResultForAI()).isEqualTo("something failed");
  }

  @Test
  void createSomethingWentWrongError_returnsErrorState() {
    AiResultDTO result = AiAssistantAPI.createSomethingWentWrongError();

    assertThat(result.getState()).isEqualTo(AIState.ERROR);
    assertThat(result.getResult()).isNotNull();
  }
}
