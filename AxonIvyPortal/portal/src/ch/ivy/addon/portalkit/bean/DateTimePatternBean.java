package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

import com.axonivy.portal.components.service.DateTimeGlobalSettingService;

@Named
@SessionScoped
public class DateTimePatternBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private DateTimeGlobalSettingService dateTimePatternService;

  @PostConstruct
  public void init() {
    dateTimePatternService = DateTimeGlobalSettingService.getInstance();
  }

  public String getDatePattern() {
    return dateTimePatternService.getDatePattern();
  }

  public String getDateTimePattern() {
    return dateTimePatternService.getDateTimePattern();
  }

  public String getDateTimestampPattern() {
    return dateTimePatternService.getGlobalDateTimePattern();
  }

  public boolean getIsDateFilterWithTime() {
    return dateTimePatternService.isDateFilterWithTime();
  }

  public boolean getIsTimeHidden() {
    return dateTimePatternService.isTimeHidden();
  }
  
  public String getShortDatePattern() {
    return dateTimePatternService.getDatePatternForDatePicker();
  }

  public String getShortDateTimePattern(boolean isDateFilter) {
    return dateTimePatternService.getDateTimePatternForDatePicker(isDateFilter);
  }


}
