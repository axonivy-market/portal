package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestApplicationMultiLanguageAPI {

  @Test
  void getCmsValueByUserLocale_returnsValueForCurrentLocale() {
    String value = ApplicationMultiLanguageAPI.getCmsValueByUserLocale(
        "/ch.ivy.addon.portalkit.ui.jsf/common/processes");

    assertThat(value).isNotNull();
  }
}
