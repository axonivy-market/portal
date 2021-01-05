package ch.ivy.addon.portalkit.service;

import ch.ivyteam.ivy.environment.Ivy;

public class CmsDateTimeUnitService {
  private static final String YEARS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/years";
  private static final String DAYS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/days";
  private static final String HOURS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/hours";
  private static final String MINUTES_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/minutes";
  private static final String SECONDS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/seconds";

  public static String getCmsYearUnit() {
    return Ivy.cms().co(YEARS_CMS);
  }

  public static String getCmsDayUnit() {
    return Ivy.cms().co(DAYS_CMS);
  }

  public static String getCmsHourUnit() {
    return Ivy.cms().co(HOURS_CMS);
  }

  public static String getCmsMinuteUnit() {
    return Ivy.cms().co(MINUTES_CMS);
  }

  public static String getCmsSecondUnit() {
    return Ivy.cms().co(SECONDS_CMS);
  }
}
