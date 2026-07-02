package com.axonivy.portal.components.publicapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.primefaces.PrimeFaces;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestPortalNavigatorInFrameAPI {

  @Test
  void navigateToPortalHome_executesRedirectCommand() {
    var executed = new StringBuilder();
    PrimeFaces original = PrimeFaces.current();
    PrimeFaces.setCurrent(new PrimeFaces() {
      @Override
      public void executeScript(String script) {
        executed.append(script);
      }
    });

    try {
      PortalNavigatorInFrameAPI.navigateToPortalHome();
    } finally {
      PrimeFaces.setCurrent(original);
    }

    assertThat(executed.toString()).contains("parent.redirectToUrlCommand");
  }
}
