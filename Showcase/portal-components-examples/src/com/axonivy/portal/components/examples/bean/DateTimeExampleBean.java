package com.axonivy.portal.components.examples.bean;

import java.io.Serializable;

import com.axonivy.portal.components.service.DateTimeGlobalSettingService;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class DateTimeExampleBean implements Serializable {
  private static final long serialVersionUID = 393379085832602153L;

  public String getGlobalDateTimePattern() {
    return DateTimeGlobalSettingService.getInstance().getGlobalDateTimePattern();
  }

  public String getDateTimePattern() {
    return DateTimeGlobalSettingService.getInstance().getDateTimePattern();
  }

  public String getDatePattern() {
    return DateTimeGlobalSettingService.getInstance().getDatePattern();
  }

  public String getShortDateTimePattern(boolean isDateFilter) {
    return DateTimeGlobalSettingService.getInstance().getDateTimePatternForDatePicker(isDateFilter);
  }

  public String getDatePatternForDatePicker() {
    return DateTimeGlobalSettingService.getInstance().getDatePatternForDatePicker();
  }

  public boolean getIsTimeHidden() {
    return DateTimeGlobalSettingService.getInstance().isTimeHidden();
  }

}
