package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * Only the null/blank guard-clause branches are covered here: the remaining
 * branches need a real {@code ICase}/{@code ITask}/{@code IWebStartable} instance
 * from a running process, which needs {@code @IvyProcessTest} (not usable yet in
 * this environment) since Mockito is not used in this project.
 */
@IvyTest
class TestProcessViewerUtils {

  @Test
  void findCaseMapByCase_nullCase_returnsNull() {
    assertThat(ProcessViewerUtils.findCaseMapByCase(null)).isNull();
  }

  @Test
  void hasCaseMap_nullBusinessCase_returnsFalse() {
    assertThat(ProcessViewerUtils.hasCaseMap(null)).isFalse();
  }

  @Test
  void isViewerAllowed_nullCase_returnsFalse() {
    assertThat(ProcessViewerUtils.isViewerAllowed((ch.ivyteam.ivy.workflow.ICase) null)).isFalse();
  }

  @Test
  void isViewerAllowed_nullTask_returnsFalse() {
    assertThat(ProcessViewerUtils.isViewerAllowed((ch.ivyteam.ivy.workflow.ITask) null)).isFalse();
  }

  @Test
  void isViewerAllowed_nullWebStartable_returnsFalse() {
    assertThat(ProcessViewerUtils.isViewerAllowed((ch.ivyteam.ivy.workflow.start.IWebStartable) null)).isFalse();
  }

  @Test
  void findWebStartable_blankProcessLink_returnsNull() {
    assertThat(ProcessViewerUtils.findWebStartable("")).isNull();
  }

  @Test
  void findWebStartable_nullProcessLink_returnsNull() {
    assertThat(ProcessViewerUtils.findWebStartable(null)).isNull();
  }
}
