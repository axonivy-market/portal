package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestHiddenTasksCasesConfig {

  @BeforeEach
  void setup(AppFixture fixture) {
    fixture.var(HiddenTasksCasesConfig.PORTAL_HIDDEN_TASK_CASE_EXCLUDED, "");
  }

  @Test
  void isHiddenTasksCasesExcluded_notSet_returnsFalse() {
    assertThat(HiddenTasksCasesConfig.isHiddenTasksCasesExcluded()).isFalse();
  }

  @Test
  void isHiddenTasksCasesExcluded_true_returnsTrue(AppFixture fixture) {
    fixture.var(HiddenTasksCasesConfig.PORTAL_HIDDEN_TASK_CASE_EXCLUDED, "true");
    assertThat(HiddenTasksCasesConfig.isHiddenTasksCasesExcluded()).isTrue();
  }

  @Test
  void isHiddenTasksCasesExcluded_false_returnsFalse(AppFixture fixture) {
    fixture.var(HiddenTasksCasesConfig.PORTAL_HIDDEN_TASK_CASE_EXCLUDED, "false");
    assertThat(HiddenTasksCasesConfig.isHiddenTasksCasesExcluded()).isFalse();
  }

  @Test
  void isHiddenTasksCasesExcluded_notABoolean_returnsFalse(AppFixture fixture) {
    fixture.var(HiddenTasksCasesConfig.PORTAL_HIDDEN_TASK_CASE_EXCLUDED, "not-a-boolean");
    assertThat(HiddenTasksCasesConfig.isHiddenTasksCasesExcluded()).isFalse();
  }
}
