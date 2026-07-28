package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.service.exception.PortalException;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * Only the "no matching IWebStartable" failure of the internal-link branch is
 * covered here. The external-link branch and the success branch both end up
 * calling {@code businessDetailsDTO.getCase()} (defaults to {@code Ivy.wfCase()}),
 * which needs a real case from a running process, i.e. {@code @IvyProcessTest}
 * (not usable yet in this environment).
 */
@IvyTest
class TestBusinessDetailsAPI {

  @Test
  void create_internalLinkNotFound_throwsPortalException() {
    assertThatThrownBy(() -> BusinessDetailsAPI.create("__does_not_exist__"))
        .isInstanceOf(PortalException.class)
        .hasMessageContaining("__does_not_exist__");
  }
}
