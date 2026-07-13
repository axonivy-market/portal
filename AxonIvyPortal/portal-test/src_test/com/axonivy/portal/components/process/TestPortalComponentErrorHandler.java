package com.axonivy.portal.components.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.ivydata.exception.PortalIvyDataException;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

/**
 * Process test for {@code processes/Functional Processes/PortalComponentErrorHandler.p.json}.
 *
 * <p>The process loops over the given exceptions and logs them via {@code ErrorHandler}. It has
 * no result data, so this is a smoke test asserting the callable runs to its end for both an
 * empty and a non-empty exception list.
 */
@IvyProcessTest
class TestPortalComponentErrorHandler {

  private static final BpmProcess ERROR_HANDLER = BpmProcess.name("PortalComponentErrorHandler");
  private static final String START = "handle(List<PortalIvyDataException>)";

  @Test
  void handlesEmptyList(BpmClient client) {
    var result = client.start()
        .subProcess(ERROR_HANDLER.elementName(START))
        .execute(List.of());

    assertThat(result.history().elementNames()).isNotEmpty();
  }

  @Test
  void handlesExceptions(BpmClient client) {
    var errors = List.of(new PortalIvyDataException("500"));

    var result = client.start()
        .subProcess(ERROR_HANDLER.elementName(START))
        .execute(errors);

    assertThat(result.history().elementNames()).isNotEmpty();
  }
}
