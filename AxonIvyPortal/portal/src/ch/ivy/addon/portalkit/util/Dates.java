package ch.ivy.addon.portalkit.util;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import ch.ivy.addon.portalkit.service.exception.PortalException;

public final class Dates {

  public static final String GERMAN_DATE_FORMAT = "dd.MM.yyyy";
  public static final String ENGLISH_DATE_FORMAT = "MM/dd/yyyy";

  private Dates() {}

  /**
   * Check two periods of date is overlapped each other by comparing start and end date of first period to start and end
   * date of second period, then get absolute total of sum 4 results, the total would be in range [0,1,2,3,4].<br>
   * If the total is 4 -> not overlapped<br>
   * Otherwise -> overlapped
   * 
   * @param startDate1
   * @param endDate1
   * @param startDate2
   * @param endDate2
   * @return boolean
   */
  public static boolean isTwoPeriodsOfDateOverlapped(Date startDate1, Date endDate1, Date startDate2, Date endDate2) {
    int i1 = compareTruncatedByHour(startDate1, startDate2);
    int i2 = compareTruncatedByHour(startDate1, endDate2);
    int i3 = compareTruncatedByHour(endDate1, startDate2);
    int i4 = compareTruncatedByHour(endDate1, endDate2);
    int overlappingIndex = Math.abs(i1 + i2 + i3 + i4);
    return overlappingIndex != 4;
  }

  private static int compareTruncatedByHour(Date date1, Date date2) {
    return DateUtils.truncatedCompareTo(date1, date2, Calendar.HOUR);
  }

  /**
   * Return new Date object same date with given date but time is end of day (23h59m59s)
   * 
   * @param date
   * @return end of date
   */
  public static Date toEndOfDate(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.set(Calendar.HOUR_OF_DAY, 23);
    c.set(Calendar.MINUTE, 59);
    c.set(Calendar.SECOND, 59);
    return c.getTime();
  }

  /**
   * return Monday of last week at start time of day (00:00:00)
   * 
   * @return Monday of last week at start time of day (00:00:00)
   */
  public static Date getMondayOfLastWeek() {
    LocalDate ld = LocalDate.now();
    ld = ld.minusWeeks(1);
    ld = ld.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    return getLocalDateWithTime(ld, LocalTime.MIN);
  }

  /**
   * return Sunday of last week at end time of day (23:59:59)
   * 
   * @return Sunday of last week at end time of day (23:59:59)
   */
  public static Date getSundayOfLastWeek() {
    LocalDate ld = LocalDate.now();
    ld = ld.minusWeeks(1);
    ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    return getLocalDateWithTime(ld, LocalTime.MAX);
  }

  /**
   * return first day of last month at start time of day (00:00:00)
   * 
   * @return first day of last month at start time of day (00:00:00)
   */
  public static Date getFirstDayOfLastMonth() {
    LocalDate ld = LocalDate.now();
    ld = ld.minusMonths(1);
    ld = ld.with(TemporalAdjusters.firstDayOfMonth());
    return getLocalDateWithTime(ld, LocalTime.MIN);
  }

  /**
   * return last day of last month at end time of day (23:59:59)
   * 
   * @return last day of last month at end time of day (23:59:59)
   */
  public static Date getLastDayOfLastMonth() {
    LocalDate ld = LocalDate.now();
    ld = ld.minusMonths(1);
    ld = ld.with(TemporalAdjusters.lastDayOfMonth());
    return getLocalDateWithTime(ld, LocalTime.MAX);
  }

  /**
   * return the first day of 6 month ago, at start time of day (00:00:00)
   * 
   * @return the first day of 6 month ago, at start time of day (00:00:00)
   */
  public static Date getFirstDayOfLast6Month() {
    LocalDate ld = LocalDate.now();
    // Because we include current month, so just minus 5 month
    ld = ld.minusMonths(5);
    ld = ld.with(TemporalAdjusters.firstDayOfMonth());
    return getLocalDateWithTime(ld, LocalTime.MIN);
  }

  private static Date getLocalDateWithTime(LocalDate localDate, LocalTime localTime) {
    return Date.from(LocalDateTime.of(localDate, localTime).atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date parse(String dateInString) {
    if (StringUtils.isBlank(dateInString)) {
      return null;
    }
    try {
      return DateUtils.parseDate(dateInString, ENGLISH_DATE_FORMAT, GERMAN_DATE_FORMAT);
    } catch (ParseException e) {
      throw new PortalException("Cannot parse date " + dateInString, e);
    }
  }

  public static String format(Date date) {
    if (date == null) {
      return null;
    }
    
    return DateFormatUtils.format(date, ENGLISH_DATE_FORMAT);
  }
}
