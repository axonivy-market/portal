package ch.ivy.addon.portalkit.service;

import static ch.ivy.addon.portalkit.service.GlobalSettingService.HIDE_TIME;
import ch.ivyteam.ivy.environment.Ivy;

public class DateTimeGlobalSettingService {

  private GlobalSettingService globalSettingService;

  public DateTimeGlobalSettingService() {
    globalSettingService = new GlobalSettingService();
  }

  public String getGlobalSettingPattern() {
    return isHideTime() ? getDatePattern() : getDateTimePattern();
  }

  public String getDateTimePattern() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/dateTimePattern");
  }
  
  public String getDatePattern() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/datePattern");
  }
  
  public boolean isHideTime() {
    String dateTimeGlobalSetting = globalSettingService.findGlobalSettingValue(HIDE_TIME);
    return Boolean.valueOf(dateTimeGlobalSetting);
  }
}
