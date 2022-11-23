package com.axonivy.portal.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import ch.ivyteam.ivy.environment.Ivy;

public class NewsUtils {

  public static Date parseDate(String dateAsString) {
    Date date = null;
    try {
      date = DateUtils.parseDate(dateAsString, Ivy.cms().co("/patterns/dateTimestampPattern"),
          Ivy.cms().co("/patterns/datePattern"));
    } catch (ParseException e) {
      // Just continue
      Ivy.log().debug("NewsUtils: Cannot parse date " + e);
    }
    return date;
  }

  public static String formatDate(Date date) {
    if (date == null) {
      return "";
    }
    DateFormat dateFormat = new SimpleDateFormat(Ivy.cms().co("/patterns/dateTimestampPattern"));
    return dateFormat.format(date);
  }
}
