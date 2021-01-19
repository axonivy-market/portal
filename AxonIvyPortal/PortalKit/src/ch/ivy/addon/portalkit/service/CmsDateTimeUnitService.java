package ch.ivy.addon.portalkit.service;

import ch.ivyteam.ivy.environment.Ivy;

public class CmsDateTimeUnitService {
  private static final String YEAR_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/year";
  private static final String YEARS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/years";
  private static final String DAY_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/day";
  private static final String DAYS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/days";
  private static final String HOUR_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/hour";
  private static final String HOURS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/hours";
  private static final String MINUTE_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/minute";
  private static final String MINUTES_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/minutes";
  private static final String SECOND_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/second";
  private static final String SECONDS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/seconds";
  private static final long ONE_TIME_UNIT = 1L;

  public static String getCmsYearUnit(long years) {
    return years > ONE_TIME_UNIT ? Ivy.cms().co(YEARS_CMS) : Ivy.cms().co(YEAR_CMS);
  }

  public static String getCmsDayUnit(long days) {
    return days > ONE_TIME_UNIT ? Ivy.cms().co(DAYS_CMS) : Ivy.cms().co(DAY_CMS);
  }

  public static String getCmsHourUnit(long hours) {
    return hours > ONE_TIME_UNIT ? Ivy.cms().co(HOURS_CMS) : Ivy.cms().co(HOUR_CMS);
  }

  public static String getCmsMinuteUnit(long minutes) {
    return minutes > ONE_TIME_UNIT ? Ivy.cms().co(MINUTES_CMS) : Ivy.cms().co(MINUTE_CMS);
  }

  public static String getCmsSecondUnit(long seconds) {
    return seconds > ONE_TIME_UNIT ? Ivy.cms().co(SECONDS_CMS) : Ivy.cms().co(SECOND_CMS);
  }
}
