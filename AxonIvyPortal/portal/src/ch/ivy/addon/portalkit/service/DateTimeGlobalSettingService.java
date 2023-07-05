package ch.ivy.addon.portalkit.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.Ivy;

public class DateTimeGlobalSettingService {
  private final String SPACE_CHARACTER = " ";
  private final String COMMA_CHARACTER = ",";
  private final String YEAR_PATTERN = "\\W?[Yy]+\\W?";
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
    return isTimeHidden() ? getDefaultDatePattern() : getDefaultDateTimePattern();
  }

  public boolean isTimeHidden() {
    String dateTimeGlobalSetting = globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_TIME);
    return Boolean.valueOf(dateTimeGlobalSetting);
  }

  public String getDatePattern() {
    return getDatePatternByYearSetting();
  }

  public String getDateTimePattern() {
    return getDatePatternByYearSetting() + SPACE_CHARACTER + Ivy.cms().co("/patterns/timePattern");
  }

  private String getDatePatternByYearSetting() {
    return isYearHidden() ? getDateWithoutYearPattern(getDefaultDatePattern()) : getDefaultDatePattern();
  }

  private boolean isYearHidden() {
    return Boolean.parseBoolean(globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_YEAR));
  }

  private String getDateWithoutYearPattern(String pattern) {
    String expectedPattern = pattern.replaceAll(YEAR_PATTERN, "").trim();
    return expectedPattern.endsWith(COMMA_CHARACTER) ? expectedPattern.substring(0, expectedPattern.length() - 1)
        : expectedPattern;
  }

  public String getGlobalSettingDateFilterPattern() {
    String dateFilterPattern = getDefaultDatePattern();
    String dateTimeFilterPattern = dateFilterPattern + SPACE_CHARACTER + Ivy.cms().co("/patterns/timePattern");
    return isDateFilterWithTime() ? dateTimeFilterPattern : dateFilterPattern;
  }

  public boolean isDateFilterWithTime() {
    String dateFilterGlobalSetting = globalSettingService.findGlobalSettingValue(GlobalVariable.DATE_FILTER_WITH_TIME);
    return Boolean.valueOf(dateFilterGlobalSetting);
  }

  public String getGlobalSettingCalendarPattern() {
    return isTimeHidden() ? getDefaultDatePattern() : getDefaultDateTimePattern();
  }

  public String getDateWithoutTimePattern() {
    return getDefaultDatePattern();
  }

  public DateFormat getDefaultDateTimeFormater() {
    return DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, Ivy.session().getFormattingLocale());
  }

  public DateFormat getDefaultDateFormater() {
    return DateFormat.getDateInstance(DateFormat.LONG, Ivy.session().getFormattingLocale());
  }

  public String getDefaultDateTimePattern() {
    return ((SimpleDateFormat) getDefaultDateTimeFormater()).toPattern();
  }

  public String getDefaultDatePattern() {
    return ((SimpleDateFormat) getDefaultDateFormater()).toPattern();
  }

}
