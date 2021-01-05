package ch.ivy.addon.portalkit.util;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@PrepareForTest({LocalDate.class, Dates.class})
@RunWith(PowerMockRunner.class)
public class DatesTest {

  @Test
  public void testIsTwoPeriodsOfDateOverlappedCase1() {
    Date startDate1 = newDateInstance(2015, 11, 11);
    Date endDate1 = newDateInstance(2015, 11, 11);
    Date startDate2 = newDateInstance(2015, 11, 11);
    Date endDate2 = newDateInstance(2015, 11, 11);
    boolean isOverlapped = Dates.isTwoPeriodsOfDateOverlapped(startDate1, endDate1, startDate2, endDate2);
    Assert.assertTrue(isOverlapped);
  }

  @Test
  public void testIsTwoPeriodsOfDateOverlappedCase2() {
    Date startDate1 = newDateInstance(2015, 11, 11);
    Date endDate1 = newDateInstance(2015, 11, 11);
    Date startDate2 = newDateInstance(2015, 11, 11);
    Date endDate2 = newDateInstance(2015, 11, 15);
    boolean isOverlapped = Dates.isTwoPeriodsOfDateOverlapped(startDate1, endDate1, startDate2, endDate2);
    Assert.assertTrue(isOverlapped);
  }

  @Test
  public void testIsTwoPeriodsOfDateOverlappedCase3() {
    Date startDate1 = newDateInstance(2015, 11, 11);
    Date endDate1 = newDateInstance(2015, 11, 11);
    Date startDate2 = newDateInstance(2015, 11, 9);
    Date endDate2 = newDateInstance(2015, 11, 11);
    boolean isOverlapped = Dates.isTwoPeriodsOfDateOverlapped(startDate1, endDate1, startDate2, endDate2);
    Assert.assertTrue(isOverlapped);
  }

  @Test
  public void testIsTwoPeriodsOfDateOverlappedCase4() {
    Date startDate1 = newDateInstance(2015, 11, 11);
    Date endDate1 = newDateInstance(2015, 11, 15);
    Date startDate2 = newDateInstance(2015, 11, 12);
    Date endDate2 = newDateInstance(2015, 11, 14);
    boolean isOverlapped = Dates.isTwoPeriodsOfDateOverlapped(startDate1, endDate1, startDate2, endDate2);
    Assert.assertTrue(isOverlapped);
  }

  @Test
  public void testIsTwoPeriodsOfDateOverlappedCase5() {
    Date startDate1 = newDateInstance(2015, 11, 11);
    Date endDate1 = newDateInstance(2015, 11, 15);
    Date startDate2 = newDateInstance(2015, 11, 19);
    Date endDate2 = newDateInstance(2015, 11, 21);
    boolean isOverlapped = Dates.isTwoPeriodsOfDateOverlapped(startDate1, endDate1, startDate2, endDate2);
    Assert.assertFalse(isOverlapped);
  }

  @Test
  public void testIsTwoPeriodsOfDateOverlappedCase6() {
    Date startDate1 = newDateInstance(2015, 11, 11);
    Date endDate1 = newDateInstance(2015, 11, 11);
    Date startDate2 = newDateInstance(2015, 10, 11);
    Date endDate2 = newDateInstance(2015, 10, 30);
    boolean isOverlapped = Dates.isTwoPeriodsOfDateOverlapped(startDate1, endDate1, startDate2, endDate2);
    Assert.assertFalse(isOverlapped);
  }

  @Test
  public void testIsTwoPeriodsOfDateOverlappedCase7() {
    Date startDate1 = newDateInstance(2015, 11, 11);
    Date endDate1 = newDateInstance(2015, 11, 11);
    Date startDate2 = newDateInstance(2014, 11, 11);
    Date endDate2 = newDateInstance(2014, 11, 11);
    boolean isOverlapped = Dates.isTwoPeriodsOfDateOverlapped(startDate1, endDate1, startDate2, endDate2);
    Assert.assertFalse(isOverlapped);
  }

  private Date newDateInstance(int year, int month, int dayOfMonth) {
    Calendar calendar = new Calendar.Builder().set(Calendar.YEAR, year).set(Calendar.MONTH, month)
        .set(Calendar.DAY_OF_MONTH, dayOfMonth).build();
    return calendar.getTime();
  }
  
  private Date newDateInstance(int year, int month, int dayOfMonth, int hour, int minute, int second) {
    Calendar calendar = new Calendar.Builder().set(Calendar.YEAR, year).set(Calendar.MONTH, month)
        .set(Calendar.DAY_OF_MONTH, dayOfMonth).set(Calendar.HOUR, hour).set(Calendar.MINUTE, minute).set(Calendar.SECOND, second).set(Calendar.MILLISECOND, 000).build();
    return calendar.getTime();
  }
  
  @Test
  public void testGetModayOfLastWeek() {
    //today is Mar 23rd, 2018
    LocalDate today = LocalDate.of(2018, 3, 23);
    mockStatic(LocalDate.class);
    when(LocalDate.now()).thenReturn(today);
    Date mondayOfLastWeek = Dates.getMondayOfLastWeek();
    //expected last week monday is Mar 12th, 2018
    Assert.assertTrue(DateUtils.isSameDay(newDateInstance(2018, 2, 12), mondayOfLastWeek));
  }
  
  @Test
  public void testGetSundayOfLastWeek() {
    //today is Mar 23rd, 2018
    LocalDate today = LocalDate.of(2018, 3, 23);
    mockStatic(LocalDate.class);
    when(LocalDate.now()).thenReturn(today);
    Date sundayOfLastWeek = Dates.getSundayOfLastWeek();
    //expected last week Sunday is Mar 18th, 2018
    Assert.assertTrue(DateUtils.isSameDay(sundayOfLastWeek, newDateInstance(2018, 2, 18, 23, 59, 59)));
  }
  
  @Test
  public void testFirstDayOfLastMonth() {
    //today is Mar 23rd, 2018
    LocalDate today = LocalDate.of(2018, 3, 23);
    mockStatic(LocalDate.class);
    when(LocalDate.now()).thenReturn(today);
    Date firstDayOfLastMonth = Dates.getFirstDayOfLastMonth();
    //expected first day of last month Feb 1st, 2018
    Assert.assertTrue(DateUtils.isSameDay(firstDayOfLastMonth, newDateInstance(2018, 1, 1, 23, 59, 59)));
  }
  
  @Test
  public void testLastDayOfLastMonth() {
    //today is Mar 23rd, 2018
    LocalDate today = LocalDate.of(2018, 3, 23);
    mockStatic(LocalDate.class);
    when(LocalDate.now()).thenReturn(today);
    Date lastDayOfLastMonth = Dates.getLastDayOfLastMonth();
    //expected last day of last month is Feb 28th, 2018
    Assert.assertTrue(DateUtils.isSameDay(lastDayOfLastMonth, newDateInstance(2018, 1, 28, 23, 59, 59)));
  }
  
  @Test
  public void testFirstDayOfLast6Month() {
    //today is Mar 23rd, 2018
    LocalDate today = LocalDate.of(2018, 3, 23);
    mockStatic(LocalDate.class);
    when(LocalDate.now()).thenReturn(today);
    Date lastDayOfLastMonth = Dates.getFirstDayOfLast6Month();
    //expected last day of last month is Oct 1st, 2017
    Assert.assertTrue(DateUtils.isSameDay(lastDayOfLastMonth, newDateInstance(2017, 9, 1)));
  }
  
}
