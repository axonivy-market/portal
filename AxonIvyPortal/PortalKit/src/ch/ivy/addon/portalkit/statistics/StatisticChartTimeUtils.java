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

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import ch.ivyteam.ivy.environment.Ivy;

public class StatisticChartTimeUtils {

  private StatisticChartTimeUtils() {

  }

  public static int getShiftDaysFromDayOfWeek(String dayOfWeek) {
    int shiftDays = 0;
    if (dayOfWeek.equals(Ivy.cms().co(MONDAY_CMS))) {
      shiftDays = 0;
    } else if (dayOfWeek.equals(Ivy.cms().co(TUESDAY_CMS))) {
      shiftDays = 1;
    } else if (dayOfWeek.equals(Ivy.cms().co(WEDNESDAY_CMS))) {
      shiftDays = 2;
    } else if (dayOfWeek.equals(Ivy.cms().co(THURSDAY_CMS))) {
      shiftDays = 3;
    } else if (dayOfWeek.equals(Ivy.cms().co(FRIDAY_CMS))) {
      shiftDays = 4;
    } else if (dayOfWeek.equals(Ivy.cms().co(SATURDAY_CMS))) {
      shiftDays = 5;
    } else if (dayOfWeek.equals(Ivy.cms().co(SUNDAY_CMS))) {
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
    String[] monthsOfYear =
        {Ivy.cms().co(JANUARY_CMS), Ivy.cms().co(FEBRUARY_CMS), Ivy.cms().co(MARCH_CMS), Ivy.cms().co(APRIL_CMS),
            Ivy.cms().co(MAY_CMS), Ivy.cms().co(JUNE_CMS), Ivy.cms().co(JULY_CMS), Ivy.cms().co(AUGUST_CMS),
            Ivy.cms().co(SEPTEMBER_CMS), Ivy.cms().co(OCTOBER_CMS), Ivy.cms().co(NOVEMBER_CMS),
            Ivy.cms().co(DECEMBER_CMS)};
    int monthIndex = Arrays.asList(monthsOfYear).indexOf(selectedMonth);
    cal.set(Calendar.MONTH, monthIndex);
    return cal.getTime();
  }

  public static Date getFirstDateOfWeek(String selectedWeek, String selectedMonth) {

    Date firstDateOfWeek = new Date();
    if (StringUtils.containsIgnoreCase(selectedWeek, Ivy.cms().co(THIS_WEEK_EXPIRY_KEY))) {
      firstDateOfWeek = getFirstDateOfWeekContainsDate(new Date());
    } else {
      Date firstDateOfMonth = getFirstDateOfMonth(selectedMonth);
      Date firstDateOfFirstWeek = getFirstDateOfWeekContainsDate(firstDateOfMonth);
      if (StringUtils.containsIgnoreCase(selectedWeek, Ivy.cms().co(FIRSTWEEK_CMS))) {
        firstDateOfWeek = firstDateOfFirstWeek;
      } else if (StringUtils.containsIgnoreCase(selectedWeek, Ivy.cms().co(SECONDWEEK_CMS))) {
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 1));
      } else if (StringUtils.containsIgnoreCase(selectedWeek, Ivy.cms().co(THIRDWEEK_CMS))) {
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 2));
      } else if (StringUtils.containsIgnoreCase(selectedWeek, Ivy.cms().co(FOURTHWEEK_CMS))) {
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 3));
      } else if (StringUtils.containsIgnoreCase(selectedWeek, Ivy.cms().co(FIFTHWEEK_CMS))) {
        firstDateOfWeek = truncateMinutesPart(DateUtils.addWeeks(firstDateOfFirstWeek, 4));
      } else if (StringUtils.containsIgnoreCase(selectedWeek, Ivy.cms().co(SIXTHWEEK_CMS))) {
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

  public static boolean isSameDay(Date resultDate, String selectedDay, String previousSelectedWeek,
      String previousSelectedMonth) {
    int shiftDays = 0;
    if (StringUtils.containsIgnoreCase(selectedDay, Ivy.cms().co(TODAY_EXPIRY_KEY))) {
      return DateUtils.isSameDay(resultDate, new Date());
    } else {
      shiftDays = getShiftDaysFromDayOfWeek(selectedDay);
    }

    Date compareDate = DateUtils.addDays(getFirstDateOfWeek(previousSelectedWeek, previousSelectedMonth), shiftDays);
    return DateUtils.isSameDay(resultDate, compareDate);
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
