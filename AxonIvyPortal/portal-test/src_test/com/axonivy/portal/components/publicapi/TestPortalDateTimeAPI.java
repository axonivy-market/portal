package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestPortalDateTimeAPI {

  @Test
  void getDatePattern_returnsNonBlankPattern() {
    assertThat(PortalDateTimeAPI.getDatePattern()).isNotBlank();
  }

  @Test
  void getDateTimePattern_returnsNonBlankPattern() {
    assertThat(PortalDateTimeAPI.getDateTimePattern()).isNotBlank();
  }
}
