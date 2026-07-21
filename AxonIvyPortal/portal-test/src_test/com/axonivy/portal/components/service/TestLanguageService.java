package com.axonivy.portal.components.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestLanguageService {

  LanguageService service = LanguageService.getInstance();

  @Test
  void hasCountry_localeWithCountry_returnsTrue() {
    assertThat(service.hasCountry(Locale.US)).isTrue();
  }

  @Test
  void hasCountry_localeWithoutCountry_returnsFalse() {
    assertThat(service.hasCountry(Locale.forLanguageTag("en"))).isFalse();
  }

  @Test
  void getContentLocales_returnsListFromRepository() {
    List<Locale> locales = service.getContentLocales();
    assertThat(locales).isNotNull();
  }

  @Test
  void isLocaleSupported_unsupportedLocale_returnsFalse() {
    assertThat(service.isLocaleSupported(Locale.forLanguageTag("xx-XX"))).isFalse();
  }

  @Test
  void isLanguageSupported_unsupportedLanguage_returnsFalse() {
    assertThat(service.isLanguageSupported("xx")).isFalse();
  }

  @Test
  void convertToPortalUserLocale_unsupportedLocaleWithoutCountry_returnsDefault() {
    Locale result = service.convertToPortalUserLocale(Locale.forLanguageTag("xx"));
    assertThat(result).isEqualTo(Locale.forLanguageTag("en"));
  }

  @Test
  void convertToPortalUserLocale_unsupportedLocaleWithUnsupportedCountry_returnsDefault() {
    Locale result = service.convertToPortalUserLocale(Locale.forLanguageTag("xx-XX"));
    assertThat(result).isEqualTo(Locale.forLanguageTag("en"));
  }

  @Test
  void getDefaultLanguage_isNotNull() {
    assertThat(service.getDefaultLanguage()).isNotNull();
  }
}
