package com.axonivy.portal.components.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.process.support.ProcessFixtures;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Process test for {@code processes/Functional Processes/SetBusinessDetailsPage.p.json}.
 *
 * <p>Stores the business details link in the case's {@code businessDetails} custom field.
 * An external URL is used so the process takes the direct branch (no startable lookup).
 */
@IvyProcessTest
class TestSetBusinessDetailsPage {

  private static final BpmProcess SET_PAGE = BpmProcess.name("SetBusinessDetailsPage");

  @BeforeEach
  void login() {
    ProcessFixtures.login();
  }

  @Test
  void storesExternalLinkInCustomField(BpmClient client) {
    String link = "https://www.example.com/details";

    var result = client.start()
        .subProcess(SET_PAGE.elementName("call(String)"))
        .as().session(Ivy.session())
        .execute(link);

    ICase businessCase = result.workflow().activeCase().getBusinessCase();
    String stored = businessCase.customFields().stringField("businessDetails").getOrNull();
    assertThat(stored).isEqualTo(link);
  }
}
