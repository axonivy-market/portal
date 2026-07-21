package com.axonivy.portal.components.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.enums.PortalVariable;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestIvyCacheService {

  IvyCacheService service = IvyCacheService.getInstance();

  @AfterEach
  void tearDown() {
    Ivy.var().set(PortalVariable.SESSION_CACHE_TIMEOUT.key, "");
  }

  @Test
  void getSessionCacheTimeout_notSet_returnsMax() {
    assertThat(service.getSessionCacheTimeout()).isEqualTo(IvyCacheService.MAX_TIMEOUT);
  }

  @Test
  void getSessionCacheTimeout_notANumber_returnsMax() {
    Ivy.var().set(PortalVariable.SESSION_CACHE_TIMEOUT.key, "not-a-number");
    assertThat(service.getSessionCacheTimeout()).isEqualTo(IvyCacheService.MAX_TIMEOUT);
  }

  @Test
  void getSessionCacheTimeout_minusOne_returnsMinusOne() {
    Ivy.var().set(PortalVariable.SESSION_CACHE_TIMEOUT.key, "-1");
    assertThat(service.getSessionCacheTimeout()).isEqualTo(-1);
  }

  @Test
  void getSessionCacheTimeout_belowMin_returnsMax() {
    Ivy.var().set(PortalVariable.SESSION_CACHE_TIMEOUT.key, "100");
    assertThat(service.getSessionCacheTimeout()).isEqualTo(IvyCacheService.MAX_TIMEOUT);
  }

  @Test
  void getSessionCacheTimeout_aboveMax_returnsMax() {
    Ivy.var().set(PortalVariable.SESSION_CACHE_TIMEOUT.key, "999999");
    assertThat(service.getSessionCacheTimeout()).isEqualTo(IvyCacheService.MAX_TIMEOUT);
  }

  @Test
  void getSessionCacheTimeout_withinRange_returnsValue() {
    Ivy.var().set(PortalVariable.SESSION_CACHE_TIMEOUT.key, "600");
    assertThat(service.getSessionCacheTimeout()).isEqualTo(600);
  }

  @Test
  void getSessionCacheTimeout_atMinBoundary_returnsValue() {
    Ivy.var().set(PortalVariable.SESSION_CACHE_TIMEOUT.key, "300");
    assertThat(service.getSessionCacheTimeout()).isEqualTo(300);
  }

  @Test
  void getSessionCacheTimeout_atMaxBoundary_returnsMax() {
    Ivy.var().set(PortalVariable.SESSION_CACHE_TIMEOUT.key, String.valueOf(IvyCacheService.MAX_TIMEOUT));
    assertThat(service.getSessionCacheTimeout()).isEqualTo(IvyCacheService.MAX_TIMEOUT);
  }
}
