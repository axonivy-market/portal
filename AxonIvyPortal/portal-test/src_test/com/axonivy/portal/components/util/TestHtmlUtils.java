package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class TestHtmlUtils {

  @Test
  void sanitize_nullInput_returnsNull() {
    assertThat(HtmlUtils.sanitize(null)).isNull();
  }

  @Test
  void sanitize_scriptTag_isStripped() {
    String result = HtmlUtils.sanitize("<script>alert(1)</script><p>hello</p>");
    assertThat(result).doesNotContain("<script>").contains("hello");
  }

  @Test
  void sanitize_allowsStyleAndClassAttributes() {
    String result = HtmlUtils.sanitize("<p style=\"color:red\" class=\"foo\">hi</p>");
    assertThat(result).contains("style=\"color:red\"").contains("class=\"foo\"");
  }

  @Test
  void sanitize_relativeHref_isPreserved() {
    String result = HtmlUtils.sanitize("<a href=\"/relative/path\">link</a>");
    assertThat(result).contains("href=\"/relative/path\"");
  }

  @Test
  void parseTextFromHtml_stripsTagsAndScript() {
    String result = HtmlUtils.parseTextFromHtml("<div><script>alert(1)</script><p>Hello <b>World</b></p></div>");
    assertThat(result).isEqualTo("Hello World");
  }

  @Test
  void parseTextFromHtml_plainText_isUnchanged() {
    assertThat(HtmlUtils.parseTextFromHtml("just text")).isEqualTo("just text");
  }

  @ParameterizedTest
  @NullSource
  void escapeForIcon_nullInput_returnsEmpty(String input) {
    assertThat(HtmlUtils.escapeForIcon(input)).isEmpty();
  }

  @ParameterizedTest
  @ValueSource(strings = { "fa fa-home", "  fa fa-home  ", "si si-github", "ti ti-check", "tif tif-user" })
  void escapeForIcon_validIconClass_isReturnedTrimmed(String input) {
    assertThat(HtmlUtils.escapeForIcon(input)).isEqualTo(input.trim());
  }

  @ParameterizedTest
  @ValueSource(strings = { "javascript:alert(1)", "fa home", "fa fa-home; background:url(x)", "<script>alert(1)</script>", "" })
  void escapeForIcon_invalidIconClass_returnsEmpty(String input) {
    assertThat(HtmlUtils.escapeForIcon(input)).isEmpty();
  }
}
