package ch.ivy.addon.portalkit.util;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.service.CmsDateTimeUnitService;

public class DateTimeFormatterUtils {

  private static final String SPACE = " ";
  private static final String DASH_SPACE = " - ";
  private static final long DAYS_IN_A_YEAR = 365;
  private static final int HOURS_IN_A_DAY = 24;
  private static final int MINUTES_IN_A_HOUR = 60;
  private static final int SECONDS_IN_A_MINUTE = 60;

  public static String formatToExactTime(Number secondsValue) {
    long timeInSeconds = secondsValue.longValue();
    long days = TimeUnit.SECONDS.toDays(timeInSeconds);
    return days >= DAYS_IN_A_YEAR ? prepareShortYearDisplayText(days) : makeExactDateTimeDisplayText(timeInSeconds);
  }

  private static String prepareShortYearDisplayText(long days) {
    long years = days / DAYS_IN_A_YEAR;
    long remainingDays = days - years * DAYS_IN_A_YEAR;
    String dayDisplayText = remainingDays > 0
        ? DASH_SPACE + prepareShortDateTimeDisplayText(remainingDays, CmsDateTimeUnitService.getCmsDayUnit(remainingDays))
        : StringUtils.EMPTY;
    return prepareDateTimeDisplayText(years, CmsDateTimeUnitService.getCmsYearUnit(years), true) + dayDisplayText;
  }

  private static String makeExactDateTimeDisplayText(long timeInSeconds) {
    long days = TimeUnit.SECONDS.toDays(timeInSeconds);
    long hours = TimeUnit.SECONDS.toHours(timeInSeconds) - (days * HOURS_IN_A_DAY);
    long minutes =
        TimeUnit.SECONDS.toMinutes(timeInSeconds) - (TimeUnit.SECONDS.toHours(timeInSeconds) * MINUTES_IN_A_HOUR);
    long seconds =
        TimeUnit.SECONDS.toSeconds(timeInSeconds) - (TimeUnit.SECONDS.toMinutes(timeInSeconds) * SECONDS_IN_A_MINUTE);
    StringBuilder time = new StringBuilder();
    if (days > 0) {
      time.append(prepareDateTimeDisplayText(days, CmsDateTimeUnitService.getCmsDayUnit(days), false));
    }
    if (hours > 0) {
      time.append(prepareDateTimeDisplayText(hours, CmsDateTimeUnitService.getCmsHourUnit(hours), false));
    }
    if (minutes > 0) {
      time.append(prepareDateTimeDisplayText(minutes, CmsDateTimeUnitService.getCmsMinuteUnit(minutes), false));
    }
    time.append(prepareDateTimeDisplayText(seconds, CmsDateTimeUnitService.getCmsSecondUnit(seconds), true));

    return time.toString();
  }

  private static String prepareDateTimeDisplayText(long time, String unit, boolean isFinalPosition) {
    return time + SPACE + unit + (isFinalPosition ? "" : DASH_SPACE);
  }

  public static String formatToShortTimeString(Number secondsValue) {
    long timeInSeconds = secondsValue.longValue();
    long days = TimeUnit.SECONDS.toDays(timeInSeconds);
    return days > 0 ? makeDaysDisplayText(days) : makeTimeDisplayText(timeInSeconds);
  }

  private static String makeDaysDisplayText(long days) {
    return days >= DAYS_IN_A_YEAR ? prepareShortYearDisplayText(days)
        : prepareShortDateTimeDisplayText(days, CmsDateTimeUnitService.getCmsDayUnit(days));
  }

  private static String prepareShortDateTimeDisplayText(long time, String unit) {
    return prepareDateTimeDisplayText(time, unit, true);
  }

  private static String makeTimeDisplayText(long seconds) {
    long hours = TimeUnit.SECONDS.toHours(seconds) - (TimeUnit.SECONDS.toDays(seconds) * HOURS_IN_A_DAY);
    return hours > 0 ? makeHoursDisplayText(hours) : makeMinutesDisplayText(seconds);
  }

  private static String makeHoursDisplayText(long hours) {
    return prepareShortDateTimeDisplayText(hours, CmsDateTimeUnitService.getCmsHourUnit(hours));
  }

  private static String makeMinutesDisplayText(long seconds) {
    long minutes = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * MINUTES_IN_A_HOUR);
    return prepareShortDateTimeDisplayText(minutes, CmsDateTimeUnitService.getCmsMinuteUnit(minutes));
  }
}
