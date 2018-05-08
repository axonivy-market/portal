package ch.ivy.ws.addon.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class JavaDates {

  private JavaDates() {}

  public static Date today() {
    LocalDate now = LocalDate.now();
    return Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }
  
  public static Date plusDays(Date date, long daysToAdd) {
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return Date.from(localDate.plusDays(daysToAdd).atStartOfDay(ZoneId.systemDefault()).toInstant());
  }
}
