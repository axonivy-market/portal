package ch.ivy.addon.portalkit.ivydata.utils;

import java.util.concurrent.TimeUnit;

import ch.ivyteam.ivy.environment.Ivy;

public class DateTimeFormatter {

  private static final String DAYS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/days";
  private static final String HOURS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/hours";
  private static final String MINUTES_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/minutes";
  private static final String SECONDS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/seconds";

  public static String formatDateTimeToString(Number secondsValue) {
    long timeInSeconds = secondsValue.longValue();
    int days = (int) TimeUnit.SECONDS.toDays(timeInSeconds);
    long hours = TimeUnit.SECONDS.toHours(timeInSeconds) - (days * 24);
    long minutes = TimeUnit.SECONDS.toMinutes(timeInSeconds) - (TimeUnit.SECONDS.toHours(timeInSeconds) * 60);
    long seconds = TimeUnit.SECONDS.toSeconds(timeInSeconds) - (TimeUnit.SECONDS.toMinutes(timeInSeconds) * 60);
    StringBuilder time = new StringBuilder();
    if (days > 0) {
      time.append(days + " " + Ivy.cms().co(DAYS_CMS) + " - ");
    }
    if (hours > 0) {
      time.append(hours + " " + Ivy.cms().co(HOURS_CMS) + " - ");
    }
    if (minutes > 0) {
      time.append(minutes + " " + Ivy.cms().co(MINUTES_CMS) + " - ");
    }
    time.append(seconds + " " + Ivy.cms().co(SECONDS_CMS));

    return time.toString();
  }
}
