package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Only the null-case guard of {@code getOpenTasksByCase} is covered here.
 * {@code findTaskById} and {@code parkTask} need a real {@code ITask}/{@code ICase}
 * from a running process, and exercising the non-null branch of
 * {@code getOpenTasksByCase} needs a real {@code ICase} too — both need Mockito
 * (not used in this project) or {@code @IvyProcessTest} (not usable yet).
 */
class TestTaskUtils {

  @Test
  void getOpenTasksByCase_nullCase_returnsEmptyList() {
    assertThat(TaskUtils.getOpenTasksByCase(null)).isEmpty();
  }
}
