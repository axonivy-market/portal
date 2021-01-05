package ch.ivy.addon.portalkit.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class TimesUtils {

  private TimesUtils() {}

  public static long getDurationInSeconds(Date startDate, Date endDate) {
    long diffInMillies = Math.abs(startDate.getTime() - endDate.getTime());
    return TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
  }
}
