package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestSideStepAPI {

  @Test
  void createSideStepTaskName_returnsNullWhenOriginalTaskIsMissing() {
    assertThat(SideStepAPI.createSideStepTaskName("missing-task", "some-cms-uri")).isNull();
  }

  @Test
  void finishSideStep_doesNotThrowWhenOriginalTaskIsMissing() {
    assertThatCode(() -> SideStepAPI.finishSideStep("missing-task", false)).doesNotThrowAnyException();
  }

  @Test
  void finishSideStep_doesNotThrowWhenParallelSideStepIsEnabled() {
    assertThatCode(() -> SideStepAPI.finishSideStep("ignored-task", true)).doesNotThrowAnyException();
  }
}
