package com.axonivy.portal.components.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * There is no sub process registered under any of these made-up signatures in
 * this test project, so every lookup deterministically finds nothing. The
 * "sub process found and called" branches need a real registered sub process
 * start, which needs {@code @IvyProcessTest} (not usable yet in this environment).
 */
@IvyTest
class TestIvyAdapterService {

  private static final String NO_SUCH_SIGNATURE = "doesNotExist(String)";

  @Test
  void startSubProcessInApplication_noMatchingSubProcess_returnsNull() {
    assertThat(IvyAdapterService.startSubProcessInApplication(NO_SUCH_SIGNATURE, null)).isNull();
  }

  @Test
  void startSubProcessInSecurityContext_noMatchingSubProcess_returnsNull() {
    assertThat(IvyAdapterService.startSubProcessInSecurityContext(NO_SUCH_SIGNATURE, null)).isNull();
  }

  @Test
  void startSubProcessInProjectAndAllRequired_noMatchingSubProcess_returnsNull() {
    assertThat(IvyAdapterService.startSubProcessInProjectAndAllRequired(NO_SUCH_SIGNATURE, null)).isNull();
  }

  @Test
  void startSubProcessesInSecurityContext_noMatchingSubProcess_returnsNull() {
    assertThat(IvyAdapterService.startSubProcessesInSecurityContext(NO_SUCH_SIGNATURE, null)).isNull();
  }

  @Test
  void startSubProcessesInSecurityContextWithCollectCondition_noMatchingSubProcess_returnsEmptyMap() {
    Map<String, Object> result =
        IvyAdapterService.startSubProcessesInSecurityContext(NO_SUCH_SIGNATURE, null, Map::isEmpty);
    assertThat(result).isEmpty();
  }
}
