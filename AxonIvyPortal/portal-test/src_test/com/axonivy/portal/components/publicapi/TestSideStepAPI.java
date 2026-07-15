package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * Only the "task not found" guard branches are covered here: exercising the
 * "task found" branches needs a real {@code ITask} from a running process,
 * which needs {@code @IvyProcessTest} (not usable yet in this environment).
 */
@IvyTest
class TestSideStepAPI {

  private static final String NOT_EXISTING_TASK_UUID = "00000000-0000-0000-0000-000000000000";

  @Test
  void finishSideStep_parallelSideStep_isNoOp() {
    assertThatCode(() -> SideStepAPI.finishSideStep(NOT_EXISTING_TASK_UUID, true)).doesNotThrowAnyException();
  }

  @Test
  void finishSideStep_taskNotFound_isNoOp() {
    assertThatCode(() -> SideStepAPI.finishSideStep(NOT_EXISTING_TASK_UUID, false)).doesNotThrowAnyException();
  }

  @Test
  void createSideStepTaskName_taskNotFound_returnsNull() {
    assertThat(SideStepAPI.createSideStepTaskName(NOT_EXISTING_TASK_UUID, null)).isNull();
  }
}
