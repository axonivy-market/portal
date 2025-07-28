package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
@ManagedBean
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

  public String getShortDatePattern() {
    return dateTimePatternService.getDatePatternForDatePicker();
  }

  public String getShortDateTimePattern(boolean isDateFilter) {
    return dateTimePatternService.getDateTimePatternForDatePicker(isDateFilter);
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

}
