package com.axonivy.portal.components.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.enums.GlobalVariable;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestDateTimeGlobalSettingService {

  DateTimeGlobalSettingService service = DateTimeGlobalSettingService.getInstance();

  @AfterEach
  void tearDown() {
    Ivy.var().set(GlobalVariable.HIDE_YEAR.getKey(), "");
    Ivy.var().set(GlobalVariable.HIDE_TIME.getKey(), "");
    Ivy.var().set(GlobalVariable.DATE_FILTER_WITH_TIME.getKey(), "");
  }

  @Test
  void isTimeHidden_defaultEmpty_returnsFalse() {
    assertThat(service.isTimeHidden()).isFalse();
  }

  @Test
  void isTimeHidden_varTrue_returnsTrue() {
    Ivy.var().set(GlobalVariable.HIDE_TIME.getKey(), "true");
    assertThat(service.isTimeHidden()).isTrue();
  }

  @Test
  void isDateFilterWithTime_defaultEmpty_returnsFalse() {
    assertThat(service.isDateFilterWithTime()).isFalse();
  }

  @Test
  void isDateFilterWithTime_varTrue_returnsTrue() {
    Ivy.var().set(GlobalVariable.DATE_FILTER_WITH_TIME.getKey(), "true");
    assertThat(service.isDateFilterWithTime()).isTrue();
  }

  @Test
  void getDatePattern_hideYearFalse_containsYearSymbol() {
    Ivy.var().set(GlobalVariable.HIDE_YEAR.getKey(), "false");
    String pattern = service.getDatePattern();
    assertThat(pattern).containsIgnoringCase("y");
  }

  @Test
  void getDatePattern_hideYearTrue_stripsYearSymbolAndTrailingComma() {
    Ivy.var().set(GlobalVariable.HIDE_YEAR.getKey(), "true");
    String pattern = service.getDatePattern();
    assertThat(pattern).doesNotContain("y").doesNotContain("Y");
    assertThat(pattern).doesNotEndWith(",");
  }

  @Test
  void getDateTimePattern_appendsTimePattern() {
    String datePattern = service.getDatePattern();
    String dateTimePattern = service.getDateTimePattern();
    assertThat(dateTimePattern).startsWith(datePattern + " ");
  }

  @Test
  void getGlobalDateTimePattern_hideTimeTrue_returnsDatePatternOnly() {
    Ivy.var().set(GlobalVariable.HIDE_TIME.getKey(), "true");
    assertThat(service.getGlobalDateTimePattern()).isEqualTo(service.getDatePattern());
  }

  @Test
  void getGlobalDateTimePattern_hideTimeFalse_returnsDateTimePattern() {
    Ivy.var().set(GlobalVariable.HIDE_TIME.getKey(), "false");
    assertThat(service.getGlobalDateTimePattern()).isEqualTo(service.getDateTimePattern());
  }
}
