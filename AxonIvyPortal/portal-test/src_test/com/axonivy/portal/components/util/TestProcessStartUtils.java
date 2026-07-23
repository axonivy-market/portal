package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * Only branches reachable without a real HTTP request / running process start
 * are covered: {@code findWebStartableByUserFriendlyRequestPath} (needs
 * {@code Ivy.request()}) and {@code redirect} (needs a real HTTP response) need
 * {@code @IvyProcessTest}, not usable yet in this environment, so they are skipped.
 */
@IvyTest
class TestProcessStartUtils {

  @Test
  void findFriendlyRequestPathContainsKeyword_pmvIdNotFoundInAnyApp_returnsEmpty() {
    assertThat(ProcessStartUtils.findFriendlyRequestPathContainsKeyword("anyKeyword", -1L)).isEmpty();
  }

  @Test
  void findTaskStartIdByUserFriendlyRequestPath_notFound_returnsNull() {
    assertThat(ProcessStartUtils.findTaskStartIdByUserFriendlyRequestPath("__does_not_exist__")).isNull();
  }
}
