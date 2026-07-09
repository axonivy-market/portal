package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class PortalGrowlMessageAPITest {

  @AfterEach
  void tearDown() {
    Ivy.session().removeAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE.name());
    Ivy.session().removeAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL.name());
  }

  @Test
  void setCustomMessage_summaryOnly_storesSummaryUnderSessionAttributeKey() {
    PortalGrowlMessageAPI.setCustomMessage("Order submitted successfully.");

    assertThat(Ivy.session().getAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE.name()))
        .isEqualTo("Order submitted successfully.");
    assertThat(Ivy.session().getAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL.name())).isNull();
  }

  @Test
  void setCustomMessage_summaryAndDetail_storesBothUnderSessionAttributeKeys() {
    PortalGrowlMessageAPI.setCustomMessage("Order submitted.", "Order #12345 is now in progress.");

    assertThat(Ivy.session().getAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE.name()))
        .isEqualTo("Order submitted.");
    assertThat(Ivy.session().getAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL.name()))
        .isEqualTo("Order #12345 is now in progress.");
  }

  @Test
  void setCustomMessage_blankDetail_doesNotStoreDetail() {
    PortalGrowlMessageAPI.setCustomMessage("Order submitted.", "");

    assertThat(Ivy.session().getAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE.name()))
        .isEqualTo("Order submitted.");
    assertThat(Ivy.session().getAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL.name())).isNull();
  }
}
