package com.axonivy.portal.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class PortalDateUtils {

  public static Date getStartOfDate(Date date) {
    if (date == null) {
      return null;
    }

    return DateUtils.truncate(date, Calendar.DATE);
  }

  public static Date getEndOfDate(Date date) {
    if (date == null) {
      return null;
    }

    return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1);
  }

  public static Date getStartOfToday() {
    return getStartOfDate(new Date());
  }

  public static Date getEndOfToday() {
    return getEndOfDate(new Date());
  }

  public static Date getStartOfYesterday() {
    return DateUtils.addDays(getStartOfToday(), -1);
  }

  public static Date getEndOfYesterday() {
    Date yesterday = DateUtils.addDays(DateUtils.truncate(new Date(), Calendar.DATE), -1);
    return getEndOfDate(yesterday);
  }

  public static Date getStartOfCurrentYear() {
    return DateUtils.truncate(new Date(), Calendar.YEAR);
  }

  public static Date getEndOfCurrentYear() {
    Date startDateOfNextYear = DateUtils.addYears(getStartOfCurrentYear(), 1);
    return getEndOfDate(DateUtils.addDays(startDateOfNextYear, -1));
  }

  public static Date getStartOfCurrentMonth() {
    return DateUtils.truncate(new Date(), Calendar.MONTH);
  }

  public static Date getEndOfCurrentMonth() {
    Date endOfCurrentMonth = DateUtils.addDays(DateUtils.addMonths(getStartOfCurrentMonth(), 1), -1);
    return getEndOfDate(endOfCurrentMonth);
  }

  public static Date getStartOfCurrentWeek() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    return DateUtils.truncate(calendar.getTime(), Calendar.DATE);
  }

  public static Date getEndOfCurrentWeek() {
    Date endOfCurrentWeek = DateUtils.addDays(getStartOfCurrentWeek(), 6);
    return getEndOfDate(endOfCurrentWeek);
  }

  public static Date getYearByPeriod(Long period) {
    LocalDateTime today = LocalDateTime.now();
    return toDate(today.plusYears(period).toLocalDate());
  }

  public static Date getMonthByPeriod(Long period) {
    LocalDateTime today = LocalDateTime.now();
    return toDate(today.plusMonths(period).toLocalDate());
  }

  public static Date getWeekByPeriod(Long period) {
    LocalDateTime today = LocalDateTime.now();
    return toDate(today.plusWeeks(period).toLocalDate());
  }

  public static Date getDayByPeriod(Long period) {
    LocalDateTime today = LocalDateTime.now();
    return toDate(today.plusDays(period).toLocalDate());
  }

  private static Date toDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }
  
  public static Date getEndOfMinute(Date date) {
    if (date == null) {
      return null;
    }

    return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.MINUTE), -1);
  }
  
  public static Date getStartOfMinute(Date date) {
    if (date == null) {
      return null;
    }

    return DateUtils.truncate(date, Calendar.MINUTE);
  }
}
