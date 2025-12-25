package com.axonivy.portal.components.examples.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import com.axonivy.portal.components.service.DateTimeGlobalSettingService;

@ManagedBean
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

  public boolean getIsTimeHidden() {
    return DateTimeGlobalSettingService.getInstance().isTimeHidden();
  }

}
