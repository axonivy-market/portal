package ch.ivy.addon.portalkit.util;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portalkit.service.CmsDateTimeUnitService;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CmsDateTimeUnitService.class, DateTimeFormatterUtils.class})
public class DateTimeFormatterTest {

  private static final String YEAR = "year";
  private static final String DAY = "day";
  private static final String DAYS = "days";
  private static final String HOUR = "hour";
  private static final String HOURS = "hours";
  private static final String MINUTE = "minute";
  private static final String MINUTES = "minutes";
  private static final String SPACE = " ";
  private static final String DASH_SPACE = " - ";

  @Before
  public void init() throws Exception {
    mockStatic(CmsDateTimeUnitService.class);
    when(CmsDateTimeUnitService.getCmsYearUnit(1)).thenReturn(YEAR);
    when(CmsDateTimeUnitService.getCmsDayUnit(0)).thenReturn(DAY);
    when(CmsDateTimeUnitService.getCmsDayUnit(1)).thenReturn(DAY);
    when(CmsDateTimeUnitService.getCmsDayUnit(10)).thenReturn(DAYS);
    when(CmsDateTimeUnitService.getCmsHourUnit(0)).thenReturn(HOUR);
    when(CmsDateTimeUnitService.getCmsHourUnit(1)).thenReturn(HOUR);
    when(CmsDateTimeUnitService.getCmsHourUnit(6)).thenReturn(HOURS);
    when(CmsDateTimeUnitService.getCmsMinuteUnit(0)).thenReturn(MINUTE);
    when(CmsDateTimeUnitService.getCmsMinuteUnit(3)).thenReturn(MINUTES);
    when(CmsDateTimeUnitService.getCmsMinuteUnit(10)).thenReturn(MINUTES);
  }

  @Test
  public void testFormatToShortTimeStringWithValueNotEqualOneMinute() {
    Number actualSeconds = 59;
    String actual = DateTimeFormatterUtils.formatToShortTimeString(actualSeconds);
    String expectedString = getExpectedDateTimeString(0, 0, 0);
    Assert.assertTrue(actual.equals(expectedString));
  }

  @Test
  public void testFormatToShortTimeStringWithValueNotEqualOneHourAndGreaterThanOneMinute() {
    Number seconds = 200;
    String actual = DateTimeFormatterUtils.formatToShortTimeString(seconds);
    String expectedString = getExpectedDateTimeString(0, 0, 3);
    Assert.assertTrue(actual.equals(expectedString));
  }

  @Test
  public void testFormatToShortTimeStringWithValueNotEqualOneDayAndGreaterThanOneHour() {
    Number seconds = 4200;
    String actual = DateTimeFormatterUtils.formatToShortTimeString(seconds);
    String expectedString = getExpectedDateTimeString(0, 1, 10);
    Assert.assertTrue(actual.equals(expectedString));
  }

  @Test
  public void testFormatToShortTimeStringWithThirtyHours() {
    Number seconds = 108000;
    String actual = DateTimeFormatterUtils.formatToShortTimeString(seconds);
    String expectedString = getExpectedDateTimeString(1, 6, 0);
    Assert.assertTrue(actual.equals(expectedString));
  }

  private String getExpectedDateTimeString(int days, int hours, int minutes) {
    String expectedString = "";
    if (days > 0) {
      expectedString = days + SPACE + CmsDateTimeUnitService.getCmsDayUnit(days);
    } else if (hours > 0) {
      expectedString = hours + SPACE + CmsDateTimeUnitService.getCmsHourUnit(hours);
    } else {
      expectedString = minutes + SPACE + CmsDateTimeUnitService.getCmsMinuteUnit(minutes);
    }
    return expectedString;
  }

  @Test
  public void testFormatToShortTimeStringWithValueGreaterThanOneYear() {
    Number seconds = 32400000;
    String actual = DateTimeFormatterUtils.formatToShortTimeString(seconds);
    String expectedYearsString = getExpectedYearsString(1, 10);
    Assert.assertTrue(actual.equals(expectedYearsString));
  }

  @Test
  public void testFormatToShortTimeStringWithValueEqualOneYear() {
    Number seconds = 31536000;
    String actual = DateTimeFormatterUtils.formatToShortTimeString(seconds);
    String expectedYearsString = getExpectedYearsString(1, 0);
    Assert.assertTrue(actual.equals(expectedYearsString));
  }

  private String getExpectedYearsString(int years, int days) {
    String expectedYearsString = "";
    if (years > 0) {
      expectedYearsString = years + SPACE + CmsDateTimeUnitService.getCmsYearUnit(years);
      if (days > 0) {
        expectedYearsString += DASH_SPACE + days + SPACE + CmsDateTimeUnitService.getCmsDayUnit(days);
      }
    }
    return expectedYearsString;
  }
}
