package com.axonivy.portal.components.publicapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.axonivy.portal.components.enums.GlobalVariable;
import com.axonivy.portal.components.service.GlobalSettingService;

import ch.ivyteam.ivy.environment.Ivy;

public final class PortalDateTimePatternAPI {
  private static final String SPACE_CHARACTER = " ";
  private static final String COMMA_CHARACTER = ",";
  private static final String YEAR_PATTERN = "\\W?[Yy]+\\W?";
  
  private PortalDateTimePatternAPI() {}
  
  public static String getGlobalDateTimePattern() {
    return isTimeHidden() ? getDatePattern() : getDateTimePattern();
  }
  
  public static String getDateTimePatternForDatePicker(boolean isDateFilter) {
    return isYearHidden() ? getDateWithoutYearPattern(getDefaultDateTimePattern(isDateFilter, DateFormat.SHORT)) :
            getDefaultDateTimePattern(isDateFilter, DateFormat.SHORT);
  }

  public static String getDatePatternForDatePicker() {
    return isYearHidden() ? getDateWithoutYearPattern(getDefaultDatePattern(DateFormat.SHORT)) :
            getDefaultDatePattern(DateFormat.SHORT);
  }

  public static String getDatePattern() {
    return getDatePatternByYearSetting();
  }

  public static String getDateTimePattern() {
    return getDatePatternByYearSetting() + SPACE_CHARACTER + Ivy.cms().co("/patterns/timePattern");
  }
  
  private static String getDatePatternByYearSetting() {
    return isYearHidden() ? getDateWithoutYearPattern(getDefaultDatePattern()) : getDefaultDatePattern();
  }
  
  private static String getDateWithoutYearPattern(String pattern) {
    String expectedPattern = pattern.replaceAll(YEAR_PATTERN, "").trim();
    return expectedPattern.endsWith(COMMA_CHARACTER) ? expectedPattern.substring(0, expectedPattern.length() - 1)
        : expectedPattern;
  }
  
  private static String getDefaultDatePattern() {
    return ((SimpleDateFormat) getDefaultDateFormatter()).toPattern();
  }
  
  private static String getDefaultDatePattern(int dateFormat) {
    return ((SimpleDateFormat) getDefaultDateFormatter(dateFormat)).toPattern().replaceAll("y+", "yyyy");
  }
  
  private static DateFormat getDefaultDateFormatter() {
    return DateFormat.getDateInstance(DateFormat.MEDIUM, Ivy.session().getFormattingLocale());
  }
  
  private static DateFormat getDefaultDateFormatter(int dateFormat) {
    return DateFormat.getDateInstance(dateFormat, Ivy.session().getFormattingLocale());
  }
  
  private static String getDefaultDateTimePattern(boolean isDateFilter, int dateFormat) {
    String pattern = ((SimpleDateFormat) getDefaultDateFormatter(dateFormat)).toPattern().replaceAll("y+", "yyyy");

    if (isDateFilter) {
      return isDateFilterWithTime() ? pattern + SPACE_CHARACTER + Ivy.cms().co("/patterns/timePattern") : pattern;
    }

    return !isTimeHidden() ? pattern + SPACE_CHARACTER + Ivy.cms().co("/patterns/timePattern") : pattern;
  }
  
  private static Boolean isYearHidden() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_YEAR, Boolean.FALSE);
  }
  
  private static Boolean isTimeHidden() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_TIME, Boolean.FALSE);
  }
  
  private static Boolean isDateFilterWithTime() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.DATE_FILTER_WITH_TIME, Boolean.FALSE);
  }
}
