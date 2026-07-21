package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TestSanitizeAPI {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = { "   ", "\t" })
  void escapeForJavascript_blankInput_returnsEmpty(String value) {
    assertThat(SanitizeAPI.escapeForJavascript(value)).isEmpty();
  }

  @Test
  void escapeForJavascript_plainText_isUnchanged() {
    assertThat(SanitizeAPI.escapeForJavascript("hello world")).isEqualTo("hello world");
  }

  @Test
  void escapeForJavascript_backslashAndQuotes_areEscaped() {
    assertThat(SanitizeAPI.escapeForJavascript("a\\b\"c'd")).isEqualTo("a\\\\b\\\"c\\'d");
  }

  @Test
  void escapeForJavascript_whitespaceControlChars_areEscaped() {
    assertThat(SanitizeAPI.escapeForJavascript("a\nb\rc\td")).isEqualTo("a\\nb\\rc\\td");
  }

  @Test
  void escapeForJavascript_unicodeLineSeparators_areEscaped() {
    char lineSeparator = (char) 0x2028;
    char paragraphSeparator = (char) 0x2029;
    String input = "a" + lineSeparator + "b" + paragraphSeparator + "c";
    assertThat(SanitizeAPI.escapeForJavascript(input)).isEqualTo("a\\u2028b\\u2029c");
  }

  @Test
  void escapeForJavascript_xssRelevantChars_areEscaped() {
    assertThat(SanitizeAPI.escapeForJavascript("<script>${x}</script>/`"))
        .isEqualTo("\\u003Cscript\\u003E\\u0024\\u007Bx}\\u003C\\/script\\u003E\\/\\`");
  }
}
