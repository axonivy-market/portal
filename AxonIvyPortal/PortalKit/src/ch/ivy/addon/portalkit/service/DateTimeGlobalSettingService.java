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

  public String getGlobalSettingDateFilterPattern() {
    return isDateFilterWithTime() ? getDateFilterWithTimePattern() : getDateFilterWithoutTimePattern();
  }

  private boolean isDateFilterWithTime() {
    String dateTimeFilterGlobalSetting =
        globalSettingService.findGlobalSettingValue(GlobalVariable.DATE_FILTER_WITH_TIME.toString());
    return Boolean.valueOf(dateTimeFilterGlobalSetting);
  }

  private String getDateFilterWithTimePattern() {
    return Ivy.cms().co("/patterns/dateTimeFilterPattern");
  }

  private String getDateFilterWithoutTimePattern() {
    return Ivy.cms().co("/patterns/dateFilterPattern");
  }
}
