package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestProcessStartAPI {

  @Test
  void findRelativeUrlByProcessStartFriendlyRequestPath_returnsPortalRelativeUrl() {
    String url = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(
        "Start Processes/PortalStart/DefaultApplicationHomePage.ivp");

    assertThat(url).isNotNull();
  }

  @Test
  void findStartableLinkByUserFriendlyRequestPath_returnsPortalRelativeUrl() {
    String url = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath(
        "Start Processes/PortalStart/DefaultApplicationHomePage.ivp");

    assertThat(url).isNotNull();
  }
}
