package com.axonivy.portal.components.jsf;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.service.exception.PortalException;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * There is no live {@code FacesContext} in this test environment, so
 * {@code find}/{@code get} always fail the same way here; the happy path
 * (an actual bean resolved from a real JSF request) needs a running web
 * request, which is out of scope without {@code @IvyProcessTest}.
 */
@IvyTest
class TestManagedBeans {

  @Test
  void find_noFacesContext_throwsPortalException() {
    assertThatThrownBy(() -> ManagedBeans.find("anyBean"))
        .isInstanceOf(PortalException.class)
        .hasMessageContaining("anyBean");
  }

  @Test
  void get_noFacesContext_throwsPortalException() {
    assertThatThrownBy(() -> ManagedBeans.get("anyBean"))
        .isInstanceOf(PortalException.class)
        .hasMessageContaining("anyBean");
  }
}
