package ch.ivy.addon.portalkit.service;

import static ch.ivy.addon.portalkit.service.GlobalSettingService.HIDE_TIME;
import ch.ivy.addon.portalkit.support.DataCache;
import ch.ivyteam.ivy.environment.Ivy;

public class DateTimeGlobalSettingService {

  private GlobalSettingService globalSettingService;

  public DateTimeGlobalSettingService() {
    globalSettingService = new GlobalSettingService();
  }

  public String getGlobalSettingPattern() {
    Object attribute = DataCache.getGlobalSettingFromCache(HIDE_TIME);
    if (attribute == null){
      boolean hideTime = isHideTime();
      DataCache.cacheGlobalSetting(HIDE_TIME, String.valueOf(hideTime));
      return hideTime ? getDatePattern() : getDateTimePattern();
    } 
    return Boolean.valueOf((String)attribute)? getDatePattern() : getDateTimePattern();
  }

  public String getDateTimePattern() {
    return Ivy.cms().co("/patterns/dateTimePattern");
  }
  
  public String getDatePattern() {
    return Ivy.cms().co("/patterns/datePattern");
  }
  
  public boolean isHideTime() {
    String dateTimeGlobalSetting = globalSettingService.findGlobalSettingValue(HIDE_TIME);
    return Boolean.valueOf(dateTimeGlobalSetting);
  }
}
