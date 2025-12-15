package com.axonivy.portal.components.publicapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ch.ivyteam.ivy.environment.Ivy;

public final class PortalDateTimePatternAPI {
  private static final String SPACE_CHARACTER = " ";
  private static final String COMMA_CHARACTER = ",";
  private static final String YEAR_PATTERN = "\\W?[Yy]+\\W?";
  
  private PortalDateTimePatternAPI() {}
  
  public static String getGlobalDateTimePattern() {
    return isTimeHidden() ? getDatePattern() : getDateTimePattern();
  }
  
  public String getDateTimePatternForDatePicker(boolean isDateFilter) {
    return isYearHidden() ? getDateWithoutYearPattern(getDefaultDateTimePattern(isDateFilter, DateFormat.SHORT)) :
            getDefaultDateTimePattern(isDateFilter, DateFormat.SHORT);
  }

  public String getDatePatternForDatePicker() {
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
  
  private static Boolean isYearHidden() {
    return PortalVariableAPI.isYearHidden();
  }
  
  private static Boolean isTimeHidden() {
    return PortalVariableAPI.isTimeHidden();
  }
  
  private static Boolean isDateFilterWithTime() {
    return PortalVariableAPI.isDateFilterWithTime();
  }
  
  private static String getDateWithoutYearPattern(String pattern) {
    String expectedPattern = pattern.replaceAll(YEAR_PATTERN, "").trim();
    return expectedPattern.endsWith(COMMA_CHARACTER) ? expectedPattern.substring(0, expectedPattern.length() - 1)
        : expectedPattern;
  }
  
  private static String getDefaultDatePattern() {
    return ((SimpleDateFormat) getDefaultDateFormatter()).toPattern();
  }
  
  private String getDefaultDatePattern(int dateFormat) {
    return ((SimpleDateFormat) getDefaultDateFormatter(dateFormat)).toPattern().replaceAll("y+", "yyyy");
  }
  
  private static DateFormat getDefaultDateFormatter() {
    return DateFormat.getDateInstance(DateFormat.MEDIUM, Ivy.session().getFormattingLocale());
  }
  
  private DateFormat getDefaultDateFormatter(int dateFormat) {
    return DateFormat.getDateInstance(dateFormat, Ivy.session().getFormattingLocale());
  }
  
  private String getDefaultDateTimePattern(boolean isDateFilter, int dateFormat) {
    String pattern = ((SimpleDateFormat) getDefaultDateFormatter(dateFormat)).toPattern().replaceAll("y+", "yyyy");

    if (isDateFilter) {
      return isDateFilterWithTime() ? pattern + SPACE_CHARACTER + Ivy.cms().co("/patterns/timePattern") : pattern;
    }

    return !isTimeHidden() ? pattern + SPACE_CHARACTER + Ivy.cms().co("/patterns/timePattern") : pattern;
  }
}
