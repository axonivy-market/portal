package com.axonivy.portal.components.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.enums.GlobalVariable;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestGlobalSettingService {

  GlobalSettingService service = GlobalSettingService.getInstance();

  @AfterEach
  void tearDown() {
    Ivy.var().set(GlobalVariable.SHOW_AVATAR.getKey(), "");
  }

  @Test
  void findGlobalSettingValue_notSet_returnsEmpty() {
    assertThat(service.findGlobalSettingValue(GlobalVariable.SHOW_AVATAR)).isEmpty();
  }

  @Test
  void findGlobalSettingValue_set_returnsValue() {
    Ivy.var().set(GlobalVariable.SHOW_AVATAR.getKey(), "true");
    assertThat(service.findGlobalSettingValue(GlobalVariable.SHOW_AVATAR)).isEqualTo("true");
  }

  @Test
  void findGlobalSettingValueAsBoolean_notSet_returnsDefault() {
    assertThat(service.findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_AVATAR, true)).isTrue();
    assertThat(service.findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_AVATAR, false)).isFalse();
  }

  @Test
  void findGlobalSettingValueAsBoolean_set_returnsParsedValue() {
    Ivy.var().set(GlobalVariable.SHOW_AVATAR.getKey(), "true");
    assertThat(service.findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_AVATAR, false)).isTrue();

    Ivy.var().set(GlobalVariable.SHOW_AVATAR.getKey(), "false");
    assertThat(service.findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_AVATAR, true)).isFalse();
  }

  @Test
  void findGlobalSettingValueAsString_notSet_returnsDefault() {
    assertThat(service.findGlobalSettingValueAsString(GlobalVariable.SHOW_AVATAR, "fallback")).isEqualTo("fallback");
  }

  @Test
  void findGlobalSettingValueAsString_set_returnsValue() {
    Ivy.var().set(GlobalVariable.SHOW_AVATAR.getKey(), "custom");
    assertThat(service.findGlobalSettingValueAsString(GlobalVariable.SHOW_AVATAR, "fallback")).isEqualTo("custom");
  }

  @Test
  void findGlobalSettingValueAsString_whitespaceOnly_isNotTreatedAsEmpty() {
    Ivy.var().set(GlobalVariable.SHOW_AVATAR.getKey(), "   ");
    assertThat(service.findGlobalSettingValueAsString(GlobalVariable.SHOW_AVATAR, "fallback")).isEqualTo("   ");
  }
}
