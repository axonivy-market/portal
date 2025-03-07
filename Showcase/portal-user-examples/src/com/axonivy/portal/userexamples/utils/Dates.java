package com.axonivy.portal.userexamples.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.axonivy.portal.components.service.exception.PortalException;

public final class Dates {

  public static final String GERMAN_DATE_FORMAT = "dd.MM.yyyy";
  public static final String ENGLISH_DATE_FORMAT = "MM/dd/yyyy";

  private Dates() {
  }

  public static Date parse(String dateInString){
    if (StringUtils.isBlank(dateInString)) {
      return null;
    }
    try {
      String decodeValue = URLDecoder.decode(dateInString, "UTF-8");
      return DateUtils.parseDate(decodeValue, ENGLISH_DATE_FORMAT, GERMAN_DATE_FORMAT);
    } catch (ParseException | UnsupportedEncodingException e) {
      throw new PortalException("Cannot parse date " + dateInString, e);
    }
  }

}