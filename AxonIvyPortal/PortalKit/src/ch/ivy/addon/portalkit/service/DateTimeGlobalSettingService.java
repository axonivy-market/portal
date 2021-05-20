package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.ivydata.service.impl.UserSettingService;
import ch.ivyteam.ivy.environment.Ivy;

public class DateTimeGlobalSettingService {
  private final String SPACE_CHARACTER = " ";
  private GlobalSettingService globalSettingService;
  private static DateTimeGlobalSettingService instance;

  public static DateTimeGlobalSettingService getInstance() {
    if (instance == null) {
      instance = new DateTimeGlobalSettingService();
    }
    
    return instance;
  }

  public DateTimeGlobalSettingService() {
    globalSettingService = new GlobalSettingService();
  }

  public String getGlobalSettingPattern() {
    return isTimeHidden() ? getDatePattern() : getDateTimePattern();
  }

  private boolean isTimeHidden() {
    String dateTimeGlobalSetting = globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_TIME.toString());
    return Boolean.valueOf(dateTimeGlobalSetting);
  }

  public String getDatePattern() {
    return getDatePatternByYearSetting();
  }

  public String getDateTimePattern() {
    return getDatePatternByYearSetting() + SPACE_CHARACTER + Ivy.cms().co("/patterns/timePattern");
  }

  private String getDatePatternByYearSetting() {
    String pattern = UserSettingService.newInstance().getDateFormat();
    return isYearHidden() ? getDateWithoutYearPattern(pattern) : pattern;
  }

  private boolean isYearHidden() {
    return Boolean.parseBoolean(globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_YEAR.toString()));
  }

  private String getDateWithoutYearPattern(String pattern) {
    return pattern.replaceAll("\\W?[Yy]+\\W?", "");
  }

  public String getDateTimestampPattern() {
    return UserSettingService.newInstance().getDateFormat() + SPACE_CHARACTER
        + Ivy.cms().co("/patterns/timestampPattern");
  }
}
