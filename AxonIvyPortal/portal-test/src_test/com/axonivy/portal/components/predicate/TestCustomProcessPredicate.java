package com.axonivy.portal.components.predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class TestCustomProcessPredicate {

  private final CustomProcessPredicate predicate = new CustomProcessPredicate();

  @Test
  void test_statusOk_returnsTrue() {
    Map<String, Object> value = new HashMap<>();
    value.put("status", "OK");
    assertThat(predicate.test(value)).isTrue();
  }

  @Test
  void test_statusSkip_returnsFalse() {
    Map<String, Object> value = new HashMap<>();
    value.put("status", "SKIP");
    assertThat(predicate.test(value)).isFalse();
  }

  @Test
  void test_unknownStatus_returnsTrue() {
    Map<String, Object> value = new HashMap<>();
    value.put("status", "unknown");
    assertThat(predicate.test(value)).isTrue();
  }

  @Test
  void test_nullMap_throwsNoSuchElementException() {
    assertThatThrownBy(() -> predicate.test(null)).isInstanceOf(java.util.NoSuchElementException.class);
  }

  @Test
  void test_missingStatusKey_throwsNoSuchElementException() {
    Map<String, Object> value = new HashMap<>();
    assertThatThrownBy(() -> predicate.test(value)).isInstanceOf(java.util.NoSuchElementException.class);
  }
}
