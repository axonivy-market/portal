package com.axonivy.portal.components.enums;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TestCustomProcessStatus {

  @ParameterizedTest
  @ValueSource(strings = { "OK", "ok", "Ok" })
  void toEnum_okCaseInsensitive_returnsOk(String value) {
    assertThat(CustomProcessStatus.toEnum(value)).isEqualTo(CustomProcessStatus.OK);
  }

  @ParameterizedTest
  @ValueSource(strings = { "SKIP", "skip", "Skip" })
  void toEnum_skipCaseInsensitive_returnsSkip(String value) {
    assertThat(CustomProcessStatus.toEnum(value)).isEqualTo(CustomProcessStatus.SKIP);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = { "unknown", "invalid" })
  void toEnum_unknownOrBlankValue_returnsOk(String value) {
    assertThat(CustomProcessStatus.toEnum(value)).isEqualTo(CustomProcessStatus.OK);
  }
}
