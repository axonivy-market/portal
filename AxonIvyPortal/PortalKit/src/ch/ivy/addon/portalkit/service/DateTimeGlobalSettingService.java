package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
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
    return Ivy.cms().co("/patterns/dateTimePattern");
  }
  
  public String getDatePattern() {
    return Ivy.cms().co("/patterns/datePattern");
  }
  
  public boolean isHideTime() {
    String dateTimeGlobalSetting = globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_TIME.toString());
    return Boolean.valueOf(dateTimeGlobalSetting);
  }
}
