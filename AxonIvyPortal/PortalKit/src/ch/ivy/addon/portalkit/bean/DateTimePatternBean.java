package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class DateTimePatternBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private DateTimeGlobalSettingService dateTimePatternService;
  
  @PostConstruct
  public void init() {
    dateTimePatternService = DateTimeGlobalSettingService.getInstance();
  }
  
  public String getConfiguredPattern() {
    return dateTimePatternService.getGlobalSettingPattern();
  }
  
  public String getDatePattern() {
    return dateTimePatternService.getDatePattern();
  }
  
  public String getDateTimePattern() {
    return dateTimePatternService.getDateTimePattern();
  }
  
  public String getDateTimestampPattern() {
    return dateTimePatternService.getDateTimestampPattern();
  }

  public String getConfiguredDateFilterPattern() {
    return dateTimePatternService.getGlobalSettingDateFilterPattern();
  }

  public String getSimpleDatePattern() {
    var userLocale = Ivy.session().getContentLocale().getLanguage();
    if (userLocale != null && Locale.GERMAN.getLanguage().equalsIgnoreCase(userLocale)) {
      return Dates.GERMAN_DATE_FORMAT;
    }
    return Dates.ENGLISH_DATE_FORMAT;
  }
}
