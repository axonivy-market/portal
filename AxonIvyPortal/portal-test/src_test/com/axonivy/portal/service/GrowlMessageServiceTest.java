package com.axonivy.portal.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.GrowlMessageService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class GrowlMessageServiceTest {

  GrowlMessageService service = GrowlMessageService.getInstance();

  @AfterEach
  void tearDown() {
    Ivy.session().removeAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE.name());
    Ivy.session().removeAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL.name());
  }

  @Test
  void setCustomMessage_summaryOnly_storesSummaryInSession() {
    String messageSummary = "Task is completed";
    service.setCustomMessage(messageSummary);

    assertThat(Ivy.session().getAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE.name()))
        .isEqualTo(messageSummary);
    assertThat(Ivy.session().getAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL.name())).isNull();
  }

  @Test
  void setCustomMessage_summaryAndDetail_storesBothInSession() {
    String messageSummary = "Order submitted.";
    String messageDetail = "Order #12345 is now in progress.";
    service.setCustomMessage(messageSummary, messageDetail);

    assertThat(Ivy.session().getAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE.name()))
        .isEqualTo(messageSummary);
    assertThat(Ivy.session().getAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL.name()))
        .isEqualTo(messageDetail);
  }

  @Test
  void setCustomMessage_blankDetail_doesNotStoreDetail() {
    String messageSummary = "Order submitted.";
    String messageDetail = "  ";
    service.setCustomMessage(messageSummary, messageDetail);

    assertThat(Ivy.session().getAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE.name()))
        .isEqualTo(messageSummary);
    assertThat(Ivy.session().getAttribute(SessionAttribute.CUSTOM_GROWL_MESSAGE_DETAIL.name())).isNull();
  }
}
