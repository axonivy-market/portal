package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * Only {@code findStartableLinkByUserFriendlyRequestPath} is covered, since it
 * only needs {@code ISecurityContext}/{@code IApplicationRepository}.
 * {@code findRelativeUrlByProcessStartFriendlyRequestPath} needs {@code Ivy.request()}
 * (via {@code ProcessStartUtils}), which needs {@code @IvyProcessTest}
 * (not usable yet in this environment).
 */
@IvyTest
class TestProcessStartAPI {

  @Test
  void findStartableLinkByUserFriendlyRequestPath_notFound_returnsEmpty() {
    assertThat(ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath("__does_not_exist__")).isEmpty();
  }
}
