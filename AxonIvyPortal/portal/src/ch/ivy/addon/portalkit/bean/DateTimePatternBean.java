package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.axonivy.portal.components.service.DateTimeGlobalSettingService;

@ManagedBean
@SessionScoped
public class DateTimePatternBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private DateTimeGlobalSettingService dateTimePatternService;
  private boolean isTimeHidden;

  private boolean isDateFilterWithTime;
  private String datePattern;
  private String dateTimePattern;
  private String dateTimestampPattern;
  private String shortDatePattern;

  @PostConstruct
  public void init() {
    dateTimePatternService = DateTimeGlobalSettingService.getInstance();
    isTimeHidden = dateTimePatternService.isTimeHidden();
    isDateFilterWithTime = dateTimePatternService.isDateFilterWithTime();
    datePattern = dateTimePatternService.getDatePattern();
    dateTimePattern = dateTimePatternService.getDateTimePattern();
    dateTimestampPattern = dateTimePatternService.getGlobalDateTimePattern();
    shortDatePattern = dateTimePatternService.getDatePatternForDatePicker();
  }

  public String getDatePattern() {
    return datePattern;
  }

  public String getDateTimePattern() {
    return dateTimePattern;
  }

  public String getDateTimestampPattern() {
    return dateTimestampPattern;
  }

  public boolean getIsDateFilterWithTime() {
    return isDateFilterWithTime;
  }

  public boolean getIsTimeHidden() {
    return isTimeHidden;
  }
  
  public String getShortDatePattern() {
    return shortDatePattern;
  }

  public String getShortDateTimePattern(boolean isDateFilter) {
    return dateTimePatternService.getDateTimePatternForDatePicker(isDateFilter);
  }


}
