package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestPortalNavigatorAPI {

  @Test
  void getPasswordResetUrl_containsTokenAndUsernameParameters() {
    String url = PortalNavigatorAPI.getPasswordResetUrl("abc123", "demo");

    assertThat(url).contains("token=abc123").contains("username=demo");
  }

  @Test
  void buildPortalStaticPageUrl_containsDefaultFramePageReference() {
    String url = PortalNavigatorAPI.buildPortalStaticPageUrl("/some/page.xhtml");

    assertThat(url).isNotBlank();
    assertThat(url).contains("DefaultFramePage.ivp");
    assertThat(url).contains("relativeUrl");
  }

  @Test
  void buildUrlToPortalCaseDetailsPageById_returnsEmptyStringWhenCaseIsMissing() {
    assertThat(PortalNavigatorAPI.buildUrlToPortalCaseDetailsPageById(999999L)).isEmpty();
  }

  @Test
  void buildUrlToPortalTaskDetailsPageById_returnsEmptyStringWhenTaskIsMissing() {
    assertThat(PortalNavigatorAPI.buildUrlToPortalTaskDetailsPageById(999999L)).isEmpty();
  }

  @Test
  void buildUrlToPortalCaseDetailsPageByUUID_returnsEmptyForUnknownUuid() {
    assertThat(PortalNavigatorAPI.buildUrlToPortalCaseDetailsPageByUUID("does-not-exist")).isEmpty();
  }

  @Test
  void buildUrlToPortalTaskDetailsPageByUUID_returnsEmptyForUnknownUuid() {
    assertThat(PortalNavigatorAPI.buildUrlToPortalTaskDetailsPageByUUID("does-not-exist")).isEmpty();
  }
}
