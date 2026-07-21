package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestCaseUtils {

  @Test
  void findCase_byId_notExisting_returnsNull() {
    assertThat(CaseUtils.findCase(999999L)).isNull();
  }

  @Test
  void findCase_byUuid_notExisting_returnsNull() {
    assertThat(CaseUtils.findCase("00000000-0000-0000-0000-000000000000")).isNull();
  }
}
