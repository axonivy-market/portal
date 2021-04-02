package ch.ivy.addon.portalkit.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class TimesUtils {

  private TimesUtils() {}

  public static long getDurationInSeconds(Date startDate, Date endDate) {
    long diffInMillies = Math.abs(startDate.getTime() - endDate.getTime());
    return TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
  }

  public static boolean isDateInCurrentWeek(Date date) {
    Calendar currentCalendar = Calendar.getInstance();
    int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
    int year = currentCalendar.get(Calendar.YEAR);
    Calendar targetCalendar = Calendar.getInstance();
    targetCalendar.setTime(date);
    int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
    int targetYear = targetCalendar.get(Calendar.YEAR);
    return week == targetWeek && year == targetYear;
  }
}
