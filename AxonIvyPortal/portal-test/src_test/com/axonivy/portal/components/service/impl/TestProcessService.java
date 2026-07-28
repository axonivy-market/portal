package com.axonivy.portal.components.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * Only the blank/null guard is covered here: the remaining methods need a real
 * workflow session with startable processes, which needs {@code @IvyProcessTest}
 * (not usable yet in this environment).
 */
@IvyTest
class TestProcessService {

  @Test
  void findWebStartable_blankProcessLink_returnsNull() {
    assertThat(ProcessService.getInstance().findWebStartable("")).isNull();
  }

  @Test
  void findWebStartable_nullProcessLink_returnsNull() {
    assertThat(ProcessService.getInstance().findWebStartable(null)).isNull();
  }
}
