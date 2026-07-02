package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TestSanitizeAPI {

  @Test
  void escapeForJavascript_escapesCommonSpecialCharacters() {
    String input = "Hello \"world\"\\n' and <tag>/";
    String escaped = SanitizeAPI.escapeForJavascript(input);

    assertThat(escaped).contains("Hello")
        .contains("\\\\")
        .contains("\\\"")
        .contains("\\'")
        .contains("\\/")
        .contains("\\u003C")
        .contains("\\u003E");
  }

  @Test
  void escapeForJavascript_escapesLineBreaksAndTemplateCharacters() {
    String input = "line1\nline2\rline3\t\u2028\u2029`$ {";
    String escaped = SanitizeAPI.escapeForJavascript(input);

    assertThat(escaped).contains("\\n")
        .contains("\\r")
        .contains("\\t")
        .contains("\\u2028")
        .contains("\\u2029")
        .contains("\\`")
        .contains("\\u0024")
        .contains("\\u007B");
  }

  @Test
  void escapeForJavascript_returnsEmptyForBlankInput() {
    assertThat(SanitizeAPI.escapeForJavascript(null)).isEmpty();
    assertThat(SanitizeAPI.escapeForJavascript("" )).isEmpty();
    assertThat(SanitizeAPI.escapeForJavascript("   ")).isEmpty();
  }

  @Test
  void escapeForJavascript_escapesSlashCharacter() {
    String input = "abc123-_.:/";
    assertThat(SanitizeAPI.escapeForJavascript(input)).isEqualTo("abc123-_.:\\/" );
  }
}
