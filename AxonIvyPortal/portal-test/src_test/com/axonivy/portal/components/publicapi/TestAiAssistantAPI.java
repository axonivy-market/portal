package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.dto.AiResultDTO;
import com.axonivy.portal.components.enums.AIState;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestAiAssistantAPI {

  @Test
  void generateExecutableResult_wrapsTheLinkInExecuteTag() {
    assertThat(AiAssistantAPI.generateExecutableResult("/start/process.ivp"))
        .isEqualTo("<execute>/start/process.ivp</execute>");
  }

  @Test
  void generateErrorAiResult_setsErrorStateAndIncludesDetails() {
    AiResultDTO result = AiAssistantAPI.generateErrorAiResult(new IllegalStateException("boom"), "failed");

    assertThat(result.getState()).isEqualTo(AIState.ERROR);
    assertThat(result.getResult()).contains("failed").contains("boom");
  }

  @Test
  void createSomethingWentWrongError_usesCmsMessageAndErrorState() {
    AiResultDTO result = AiAssistantAPI.createSomethingWentWrongError();

    assertThat(result.getState()).isEqualTo(AIState.ERROR);
    assertThat(result.getResult()).isNotBlank();
  }
}
