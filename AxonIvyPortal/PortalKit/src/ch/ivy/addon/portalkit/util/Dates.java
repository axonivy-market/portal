package ch.ivy.addon.portalkit.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public final class Dates {

  private Dates() {
  }

  /**
   * Check two periods of date is overlapped each other by comparing start and end date
   * of first period to start and end date of second period, then get absolute
   * total of sum 4 results, the total would be in range [0,1,2,3,4].<br>
   * If the total is 4 -> not overlapped<br>
   * Otherwise -> overlapped
   * 
   * @param startDate1
   * @param endDate1
   * @param startDate2
   * @param endDate2
   * @return
   */
  public static boolean isTwoPeriodsOfDateOverlapped(Date startDate1, Date endDate1, Date startDate2, Date endDate2) {
    int i1 = compareTruncatedByHour(startDate1, startDate2);
    int i2 = compareTruncatedByHour(startDate1, endDate2);
    int i3 = compareTruncatedByHour(endDate1, startDate2);
    int i4 = compareTruncatedByHour(endDate1, endDate2);
    int overlappingIndex = Math.abs(i1 + i2 + i3 + i4);
    if (overlappingIndex == 4) {
      return false;
    } else {
      return true;
    }
  }

  private static int compareTruncatedByHour(Date date1, Date date2) {
    return DateUtils.truncatedCompareTo(date1, date2, Calendar.HOUR);
  }
}
