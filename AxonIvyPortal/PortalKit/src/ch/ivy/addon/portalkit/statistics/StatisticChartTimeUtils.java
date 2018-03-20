package ch.ivy.addon.portalkit.statistics;

import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.APRIL_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.AUGUST_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.DECEMBER_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.FEBRUARY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.FIFTHWEEK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.FIRSTWEEK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.FOURTHWEEK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.FRIDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.JANUARY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.JULY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.JUNE_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.MARCH_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.MAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.MONDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.NOVEMBER_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.OCTOBER_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.SATURDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.SECONDWEEK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.SEPTEMBER_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.SIXTHWEEK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.SUNDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THIRDWEEK_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THIS_MONTH_EXPIRY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THIS_WEEK_EXPIRY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.THURSDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.TODAY_EXPIRY_KEY;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.TUESDAY_CMS;
import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.WEDNESDAY_CMS;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import ch.ivyteam.ivy.environment.Ivy;

public class StatisticChartTimeUtils {

  public static int getShiftDaysFromDayOfWeek(String dayOfWeek) {
    int shiftDays = 0;
    if (dayOfWeek.equals(MONDAY_CMS)) {
      shiftDays = 0;
    } else if (dayOfWeek.equals(TUESDAY_CMS)) {
      shiftDays = 1;
    } else if (dayOfWeek.equals(WEDNESDAY_CMS)) {
      shiftDays = 2;
    } else if (dayOfWeek.equals(THURSDAY_CMS)) {
      shiftDays = 3;
    } else if (dayOfWeek.equals(FRIDAY_CMS)) {
      shiftDays = 4;
    } else if (dayOfWeek.equals(SATURDAY_CMS)) {
      shiftDays = 5;
    } else if (dayOfWeek.equals(SUNDAY_CMS)) {
      shiftDays = 6;
    }
    return shiftDays;
  }

  public static Date getFirstDateOfThisMonth() {
    Calendar calendar = Calendar.getInstance();
    while (calendar.get(Calendar.DAY_OF_MONTH) > 1) {
      calendar.add(Calendar.DATE, -1);
    }
    return calendar.getTime();
  }

  public static Date getFirstDateOfMonth(String selectedMonth) {
    if (StringUtils.containsIgnoreCase(selectedMonth, Ivy.cms().co(THIS_MONTH_EXPIRY_KEY))) {
      return getFirstDateOfThisMonth();
    }
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_MONTH, 1);
    if (StringUtils.containsIgnoreCase(selectedMonth, JANUARY_CMS)) {
      cal.set(Calendar.MONTH, 0);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, FEBRUARY_CMS)) {
      cal.set(Calendar.MONTH, 1);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, MARCH_CMS)) {
      cal.set(Calendar.MONTH, 2);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, APRIL_CMS)) {
      cal.set(Calendar.MONTH, 3);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, MAY_CMS)) {
      cal.set(Calendar.MONTH, 4);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, JUNE_CMS)) {
      cal.set(Calendar.MONTH, 5);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, JULY_CMS)) {
      cal.set(Calendar.MONTH, 6);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, AUGUST_CMS)) {
      cal.set(Calendar.MONTH, 7);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, SEPTEMBER_CMS)) {
      cal.set(Calendar.MONTH, 8);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, OCTOBER_CMS)) {
      cal.set(Calendar.MONTH, 9);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, NOVEMBER_CMS)) {
      cal.set(Calendar.MONTH, 10);
    } else if (StringUtils.containsIgnoreCase(selectedMonth, DECEMBER_CMS)) {
      cal.set(Calendar.MONTH, 11);
    }
    return cal.getTime();
  }

  public static Date getFirstDateOfWeek(String selectedWeek, String selectedMonth) {

    Date firstDateOfWeek = new Date();
    if(StringUtils.containsIgnoreCase(selectedWeek, Ivy.cms().co(THIS_WEEK_EXPIRY_KEY))){
      firstDateOfWeek = getFirstDateOfWeekContainsDate(new Date());
    } else {
      Date firstDateOfMonth = getFirstDateOfMonth(selectedMonth);
      Date firstDateOfFirstWeek = getFirstDateOfWeekContainsDate(firstDateOfMonth);
      if (StringUtils.containsIgnoreCase(selectedWeek, FIRSTWEEK_CMS)){
        firstDateOfWeek = firstDateOfFirstWeek;
      } else if (StringUtils.containsIgnoreCase(selectedWeek, SECONDWEEK_CMS)){
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 1));
      } else if (StringUtils.containsIgnoreCase(selectedWeek, THIRDWEEK_CMS)){
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 2));
      } else if (StringUtils.containsIgnoreCase(selectedWeek, FOURTHWEEK_CMS)){
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 3));
      } else if (StringUtils.containsIgnoreCase(selectedWeek, FIFTHWEEK_CMS)){
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 4));
      } else if (StringUtils.containsIgnoreCase(selectedWeek, SIXTHWEEK_CMS)) {
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 5));
      }
    }
    
    return firstDateOfWeek;
  }

  public static Date getFirstDateOfWeekContainsDate(Date containedDate) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(containedDate);
    while (calendar.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY) {
      calendar.add(Calendar.DATE, -1);
    }
    return calendar.getTime();
  }

  public static boolean isSameDay(Date resultDate, String selectedDay, String previousSelectedWeek, String previousSelectedMonth) {
    int shiftDays = 0;
    if (StringUtils.containsIgnoreCase(selectedDay, Ivy.cms().co(TODAY_EXPIRY_KEY))) {
      return DateUtils.isSameDay(resultDate, new Date());
    } else {
      shiftDays = getShiftDaysFromDayOfWeek(selectedDay);
    }

    Date compareDate = DateUtils.addDays(getFirstDateOfWeek(previousSelectedWeek, previousSelectedMonth), shiftDays);
    if (DateUtils.isSameDay(resultDate, compareDate)) {
      return true;
    }
    return false;
  }

  public static Date getFirstDateOfThisYear() {
    Calendar calendar = Calendar.getInstance();
    while (calendar.get(Calendar.DAY_OF_YEAR) > 1) {
      calendar.add(Calendar.DATE, -1);
    }
    return calendar.getTime();
  }

  public static Date truncateMinutesPart(Date date) {
    return DateUtils.truncate(date, Calendar.DATE);
  }
}
