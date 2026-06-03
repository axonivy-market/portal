package com.axonivy.portal.components.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import com.axonivy.portal.components.enums.GlobalVariable;

import ch.ivyteam.ivy.environment.Ivy;

public class DateTimeGlobalSettingService {
  private static final String SPACE_CHARACTER = " ";
  private static final String COMMA_CHARACTER = ",";
  private static final Pattern YEAR_PATTERN = Pattern.compile("\\W?[Yy]+\\W?");
  private static final Pattern YEAR_SYMBOL_PATTERN = Pattern.compile("y+");
  private GlobalSettingService globalSettingService;
  private static DateTimeGlobalSettingService instance;

  public static DateTimeGlobalSettingService getInstance() {
    if (instance == null) {
      instance = new DateTimeGlobalSettingService();
    }
    return instance;
  }

  public DateTimeGlobalSettingService() {
    globalSettingService = GlobalSettingService.getInstance();
  }

  public String getGlobalDateTimePattern() {
    return isTimeHidden() ? getDatePattern() : getDateTimePattern();
  }

  public boolean isTimeHidden() {
    String dateTimeGlobalSetting = globalSettingService.findGlobalSettingValueAsString(GlobalVariable.HIDE_TIME, Boolean.FALSE.toString());
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
    return Boolean.parseBoolean(globalSettingService.findGlobalSettingValueAsString(GlobalVariable.HIDE_YEAR, Boolean.FALSE.toString()));
  }

  private String getDateWithoutYearPattern(String pattern) {
    String expectedPattern = YEAR_PATTERN.matcher(pattern).replaceAll("").trim();
    return expectedPattern.endsWith(COMMA_CHARACTER) ? expectedPattern.substring(0, expectedPattern.length() - 1)
        : expectedPattern;
  }

  public boolean isDateFilterWithTime() {
    String dateFilterGlobalSetting = globalSettingService.findGlobalSettingValueAsString(GlobalVariable.DATE_FILTER_WITH_TIME, Boolean.FALSE.toString());
    return Boolean.valueOf(dateFilterGlobalSetting);
  }

  public DateFormat getDefaultDateTimeFormatter() {
    return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Ivy.session().getFormattingLocale());
  }

  public DateFormat getDefaultDateFormatter() {
    return DateFormat.getDateInstance(DateFormat.MEDIUM, Ivy.session().getFormattingLocale());
  }

  private String getDefaultDatePattern() {
    return ((SimpleDateFormat) getDefaultDateFormatter()).toPattern();
  }

  public String getDateTimePatternForDatePicker(boolean isDateFilter) {
    return isYearHidden() ? getDateWithoutYearPattern(getDefaultDateTimePattern(isDateFilter, DateFormat.SHORT)) :
            getDefaultDateTimePattern(isDateFilter, DateFormat.SHORT);
  }

  public String getDatePatternForDatePicker() {
    return isYearHidden() ? getDateWithoutYearPattern(getDefaultDatePattern(DateFormat.SHORT)) :
            getDefaultDatePattern(DateFormat.SHORT);
  }

  private DateFormat getDefaultDateFormatter(int dateFormat) {
    return DateFormat.getDateInstance(dateFormat, Ivy.session().getFormattingLocale());
  }

  private String getDefaultDatePattern(int dateFormat) {
    return YEAR_SYMBOL_PATTERN.matcher(((SimpleDateFormat) getDefaultDateFormatter(dateFormat)).toPattern()).replaceAll("yyyy");
  }

  private String getDefaultDateTimePattern(boolean isDateFilter, int dateFormat) {
    String pattern = YEAR_SYMBOL_PATTERN.matcher(((SimpleDateFormat) getDefaultDateFormatter(dateFormat)).toPattern()).replaceAll("yyyy");

    if (isDateFilter) {
      return isDateFilterWithTime() ? pattern + SPACE_CHARACTER + Ivy.cms().co("/patterns/timePattern") : pattern;
    }

    return !isTimeHidden() ? pattern + SPACE_CHARACTER + Ivy.cms().co("/patterns/timePattern") : pattern;
  }
}
