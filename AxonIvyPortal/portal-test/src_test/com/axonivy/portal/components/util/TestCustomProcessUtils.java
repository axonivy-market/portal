package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class TestCustomProcessUtils {

  @Test
  void isSkipCustomProcess_nullMap_returnsTrue() {
    assertThat(CustomProcessUtils.isSkipCustomProcess(null)).isTrue();
  }

  @Test
  void isSkipCustomProcess_statusSkip_returnsTrue() {
    Map<String, Object> response = new HashMap<>();
    response.put("status", "SKIP");
    assertThat(CustomProcessUtils.isSkipCustomProcess(response)).isTrue();
  }

  @Test
  void isSkipCustomProcess_statusOk_returnsFalse() {
    Map<String, Object> response = new HashMap<>();
    response.put("status", "OK");
    assertThat(CustomProcessUtils.isSkipCustomProcess(response)).isFalse();
  }

  @Test
  void isSkipCustomProcess_missingStatusKey_returnsFalse() {
    Map<String, Object> response = new HashMap<>();
    assertThat(CustomProcessUtils.isSkipCustomProcess(response)).isFalse();
  }

  @Test
  void isSkipCustomProcess_unknownStatus_returnsFalse() {
    Map<String, Object> response = new HashMap<>();
    response.put("status", "unknown");
    assertThat(CustomProcessUtils.isSkipCustomProcess(response)).isFalse();
  }
}
