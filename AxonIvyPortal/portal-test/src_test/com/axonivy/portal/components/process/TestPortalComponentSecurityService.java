package com.axonivy.portal.components.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.process.support.ProcessFixtures;
import com.axonivy.portal.components.service.PortalComponentSecurityServiceData;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * Process test for {@code processes/Ivy Data Processes/PortalComponentSecurityService.p.json}.
 *
 * <p>Delegates to {@code SecurityService.findUsers}. Asserts the callable runs (sudo) and
 * returns a users list (querying for the configured "admin" user).
 */
@IvyProcessTest
class TestPortalComponentSecurityService {

  private static final BpmProcess SECURITY = BpmProcess.name("PortalComponentSecurityService");
  private static final String START = "findUsers(String,Integer,Integer,List<String>,List<String>)";

  @BeforeEach
  void login() {
    ProcessFixtures.login();
  }

  @Test
  void findUsers_returnsMatchingUser(BpmClient client) {
    // params: query, startIndex, count, fromRoles, excludedUsernames
    PortalComponentSecurityServiceData data = client.start()
        .subProcess(SECURITY.elementName(START))
        .as().session(Ivy.session())
        .execute("admin", 0, 10, List.of(), List.of())
        .data().last();

    assertThat(data.getUsers()).isNotNull();
  }
}
