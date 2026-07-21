package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * Only the "not found" branches of the UUID-based lookups are covered here.
 * The Id-based overloads pass the numeric id as a fallback "uuid" when the
 * case/task isn't found, which isn't a well-formed UUID and its parsing
 * behavior isn't verified in this environment, so they are skipped. Every
 * other method here (navigateToPortalHome/EndPage, getPasswordResetUrl,
 * buildPortalStaticPageUrl) needs {@code Ivy.request()}/{@code Ivy.session()}
 * / a real task, which needs {@code @IvyProcessTest} (not usable yet).
 */
@IvyTest
class TestPortalNavigatorAPI {

  private static final String NOT_EXISTING_UUID = "00000000-0000-0000-0000-000000000000";

  @Test
  void buildUrlToPortalCaseDetailsPageByUUID_notFound_returnsEmpty() {
    assertThat(PortalNavigatorAPI.buildUrlToPortalCaseDetailsPageByUUID(NOT_EXISTING_UUID)).isEmpty();
  }

  @Test
  void buildUrlToPortalTaskDetailsPageByUUID_notFound_returnsEmpty() {
    assertThat(PortalNavigatorAPI.buildUrlToPortalTaskDetailsPageByUUID(NOT_EXISTING_UUID)).isEmpty();
  }
}
