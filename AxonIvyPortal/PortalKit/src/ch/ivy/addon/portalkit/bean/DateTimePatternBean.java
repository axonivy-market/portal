package ch.ivy.addon.portalkit.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;

@ManagedBean
@SessionScoped
public class DateTimePatternBean {

  private DateTimeGlobalSettingService dateTimePatternService;
  
  @PostConstruct
  public void init() {
    dateTimePatternService = new DateTimeGlobalSettingService();
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
}
