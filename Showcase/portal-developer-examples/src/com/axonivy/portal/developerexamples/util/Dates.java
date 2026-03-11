package com.axonivy.portal.developerexamples.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.axonivy.portal.components.service.exception.PortalException;

public final class Dates {

  public static final String GERMAN_DATE_FORMAT = "dd.MM.yyyy";
  public static final String ENGLISH_DATE_FORMAT = "MM/dd/yyyy";

  private Dates() {}


  public static Date parse(String dateInString) {
    if (StringUtils.isBlank(dateInString)) {
      return null;
    }
    try {
      return DateUtils.parseDate(dateInString, ENGLISH_DATE_FORMAT, GERMAN_DATE_FORMAT);
    } catch (ParseException e) {
      throw new PortalException("Cannot parse date " + dateInString, e);
    }
  }


}
