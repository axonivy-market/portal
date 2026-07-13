package com.axonivy.portal.components.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.ivydata.searchcriteria.CaseSearchCriteria;
import com.axonivy.portal.components.process.support.ProcessFixtures;
import com.axonivy.portal.components.service.PortalConponentCaseServiceData;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * Process test for {@code processes/Ivy Data Processes/PortalComponentCaseService.p.json}.
 *
 * <p>Delegates to {@code CaseService.countCasesByCriteria}. Asserts the callable runs and
 * returns a non-negative total count for a default search criteria.
 */
@IvyProcessTest
class TestPortalComponentCaseService {

  private static final BpmProcess CASE_SERVICE = BpmProcess.name("PortalComponentCaseService");
  private static final String COUNT_START = "countCasesByCriteria(CaseSearchCriteria)";

  @BeforeEach
  void login() {
    ProcessFixtures.login();
  }

  @Test
  void countCasesByCriteria_returnsNonNegative(BpmClient client) {
    ProcessFixtures.newBusinessCase(client);
    var criteria = new CaseSearchCriteria();

    PortalConponentCaseServiceData data = client.start()
        .subProcess(CASE_SERVICE.elementName(COUNT_START))
        .as().session(Ivy.session())
        .execute(criteria)
        .data().last();

    assertThat(data.getTotalCases()).isNotNull();
    assertThat(data.getTotalCases()).isGreaterThanOrEqualTo(0L);
  }
}
