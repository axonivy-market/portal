package com.axonivy.portal.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestMenuUtils {

  @Test
  void safeExternalUrl_allowsHttp() {
    assertThat(MenuUtils.safeExternalUrl("http://example.com")).isEqualTo("http://example.com");
  }

  @Test
  void safeExternalUrl_allowsHttps() {
    assertThat(MenuUtils.safeExternalUrl("https://www.google.com")).isEqualTo("https://www.google.com");
  }

  @Test
  void safeExternalUrl_allowsRelativePath() {
    assertThat(MenuUtils.safeExternalUrl("/my/page")).isEqualTo("/my/page");
  }

  @Test
  void safeExternalUrl_rejectsJavascriptScheme() {
    assertThat(MenuUtils.safeExternalUrl("javascript:alert(1)")).isNull();
  }

  @Test
  void safeExternalUrl_rejectsDataScheme() {
    assertThat(MenuUtils.safeExternalUrl("data:text/html,<script>alert(1)</script>")).isNull();
  }

  @Test
  void safeExternalUrl_returnsNullForBlank() {
    assertThat(MenuUtils.safeExternalUrl(null)).isNull();
    assertThat(MenuUtils.safeExternalUrl("")).isNull();
    assertThat(MenuUtils.safeExternalUrl("   ")).isNull();
  }

  @Test
  void safeExternalUrl_trimsMaliciousWhitespace() {
    assertThat(MenuUtils.safeExternalUrl("  javascript:alert(1)  ")).isNull();
  }

  // --- isSafeRelativePath ---

  @Test
  void isSafeRelativePath_allowsSimplePath() {
    assertThat(MenuUtils.isSafeRelativePath("pages/about")).isTrue();
  }

  @Test
  void isSafeRelativePath_allowsLeadingSlash() {
    assertThat(MenuUtils.isSafeRelativePath("/pages/about")).isTrue();
  }

  @Test
  void isSafeRelativePath_rejectsProtocolRelative() {
    assertThat(MenuUtils.isSafeRelativePath("//evil.com/page")).isFalse();
  }

  @Test
  void isSafeRelativePath_rejectsBackslash() {
    assertThat(MenuUtils.isSafeRelativePath("\\evil.com\\page")).isFalse();
  }

  @Test
  void isSafeRelativePath_rejectsHttpsUrl() {
    assertThat(MenuUtils.isSafeRelativePath("https://evil.com")).isFalse();
  }

  @Test
  void isSafeRelativePath_rejectsHttpUrl() {
    assertThat(MenuUtils.isSafeRelativePath("http://evil.com")).isFalse();
  }

  @Test
  void isSafeRelativePath_returnsFalseForBlank() {
    assertThat(MenuUtils.isSafeRelativePath(null)).isFalse();
    assertThat(MenuUtils.isSafeRelativePath("")).isFalse();
    assertThat(MenuUtils.isSafeRelativePath("   ")).isFalse();
  }

  // --- buildIconClass ---

  @Test
  void buildIconClass_prefixesTablerIcon() {
    assertThat(MenuUtils.buildIconClass("ti-coffee")).isEqualTo("ti ti-coffee");
  }

  @Test
  void buildIconClass_prefixesFontAwesomeIcon() {
    assertThat(MenuUtils.buildIconClass("fa-home")).isEqualTo("fa fa-home");
  }

  @Test
  void buildIconClass_prefixesTablerFilledIcon() {
    assertThat(MenuUtils.buildIconClass("tif-star")).isEqualTo("tif tif-star");
  }

  @Test
  void buildIconClass_prefixesSimpleIcon() {
    assertThat(MenuUtils.buildIconClass("si-github")).isEqualTo("si si-github");
  }

  @Test
  void buildIconClass_passesAlreadyPrefixedIconUnchanged() {
    assertThat(MenuUtils.buildIconClass("ti ti-coffee")).isEqualTo("ti ti-coffee");
    assertThat(MenuUtils.buildIconClass("fa fa-home")).isEqualTo("fa fa-home");
  }

  @Test
  void buildIconClass_returnsDefaultForNull() {
    assertThat(MenuUtils.buildIconClass(null)).isEqualTo("ti ti-layout-dashboard");
  }

  @Test
  void buildIconClass_returnsDefaultForBlank() {
    assertThat(MenuUtils.buildIconClass("")).isEqualTo("ti ti-layout-dashboard");
  }
}
