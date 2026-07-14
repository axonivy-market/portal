package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestHtmlUtils {

  @Test
  void sanitize_null_returnsNull() {
    assertThat(HtmlUtils.sanitize(null)).isNull();
  }

  @Test
  void sanitize_safeHtml_preservesTags() {
    String html = "<p>Hello <b>World</b>!</p>";
    assertThat(HtmlUtils.sanitize(html)).isEqualTo(html);
  }

  @Test
  void sanitize_unsafeHtml_removesScript() {
    String html = "<p>Hello <script>alert('xss')</script>World</p>";
    assertThat(HtmlUtils.sanitize(html)).isEqualTo("<p>Hello World</p>");
  }

  @Test
  void sanitize_relativeLinks_preservesRelativeLinks() {
    String html = "<a href=\"relative/path/to/page\">Link</a>";
    assertThat(HtmlUtils.sanitize(html)).isEqualTo(html);
  }

  @Test
  void sanitize_javascriptProtocols_removesJavascriptLink() {
    String html = "<a href=\"javascript:alert('xss')\">Link</a>";
    assertThat(HtmlUtils.sanitize(html)).isEqualTo("<a>Link</a>");
  }

  @Test
  void parseTextFromHtml_validHtml_extractsText() {
    String html = "<p>Hello <b>World</b>!</p>";
    assertThat(HtmlUtils.parseTextFromHtml(html)).isEqualTo("Hello World!");
  }

  @Test
  void parseTextFromHtml_null_throwsException() {
    assertThatThrownBy(() -> HtmlUtils.parseTextFromHtml(null))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void escapeForIcon_null_returnsEmptyString() {
    assertThat(HtmlUtils.escapeForIcon(null)).isEmpty();
  }

  @Test
  void escapeForIcon_validIcon_returnsIcon() {
    assertThat(HtmlUtils.escapeForIcon("fa fa-home")).isEqualTo("fa fa-home");
    assertThat(HtmlUtils.escapeForIcon("ti ti-user")).isEqualTo("ti ti-user");
    assertThat(HtmlUtils.escapeForIcon("si si-github")).isEqualTo("si si-github");
  }

  @Test
  void escapeForIcon_invalidIcon_returnsEmptyString() {
    assertThat(HtmlUtils.escapeForIcon("invalid icon!")).isEmpty();
    assertThat(HtmlUtils.escapeForIcon("<script>")).isEmpty();
  }
}
