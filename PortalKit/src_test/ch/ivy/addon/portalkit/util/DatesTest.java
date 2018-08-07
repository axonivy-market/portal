package ch.ivy.addon.portalkit.util;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

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

}
