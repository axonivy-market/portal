package com.axonivy.portal.enums;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TestTaskHeaderMode {

  @Test
  void parse_validValues_returnsMatchingMode() {
    assertThat(TaskHeaderMode.parse("DEFAULT")).isEqualTo(TaskHeaderMode.DEFAULT);
    assertThat(TaskHeaderMode.parse("LEGACY")).isEqualTo(TaskHeaderMode.LEGACY);
    assertThat(TaskHeaderMode.parse("HIDDEN")).isEqualTo(TaskHeaderMode.HIDDEN);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "\t"})
  void parse_blank_returnsDefault(String storedMode) {
    assertThat(TaskHeaderMode.parse(storedMode)).isEqualTo(TaskHeaderMode.DEFAULT);
  }

  @ParameterizedTest
  @ValueSource(strings = {"UNKNOWN", "legacy", "Hidden"})
  void parse_unknownOrDifferentCase_returnsDefault(String storedMode) {
    assertThat(TaskHeaderMode.parse(storedMode)).isEqualTo(TaskHeaderMode.DEFAULT);
  }
}
