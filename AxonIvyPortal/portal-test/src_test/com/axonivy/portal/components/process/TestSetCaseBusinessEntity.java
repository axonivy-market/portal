package com.axonivy.portal.components.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.enums.AdditionalProperty;
import com.axonivy.portal.components.process.support.ProcessFixtures;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Process test for {@code processes/Functional Processes/SetCaseBusinessEntity.p.json}.
 *
 * <p>Stores the given business entity id in the business case's custom field.
 */
@IvyProcessTest
class TestSetCaseBusinessEntity {

  private static final BpmProcess SET_ENTITY = BpmProcess.name("SetCaseBusinessEntity");

  @BeforeEach
  void login() {
    ProcessFixtures.login();
  }

  @Test
  void storesEntityIdInCustomField(BpmClient client) {
    var result = client.start()
        .subProcess(SET_ENTITY.elementName("call(String)"))
        .as().session(Ivy.session())
        .execute("entity-42");

    ICase businessCase = result.workflow().activeCase().getBusinessCase();
    String stored = businessCase.customFields()
        .textField(AdditionalProperty.CASE_BUSINESS_ENTITY_PROPERTY.toString())
        .getOrNull();
    assertThat(stored).isEqualTo("entity-42");
  }
}
