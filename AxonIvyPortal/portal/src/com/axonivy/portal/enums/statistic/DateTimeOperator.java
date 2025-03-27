package com.axonivy.portal.enums.statistic;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DateTimeOperator {
  YEAR("Year"),
  MONTH("Month"),
  WEEK("Week"),
  DAY("Day"),
  MAX("Max"),
  MIN("Min"),
  AVG("Avg"),
  HOUR("Hour");
  
  private DateTimeOperator (String name) {
    this.name = name;
  }
  
  private String name;
  
  @JsonValue
  public String getName() {
    return this.name;
  }
  
  /*
   * Hour unit is too small > many results > need a way to handle on UI 
   * or don't support hour
   * Currently opt out the metric operator 
   * since haven't found a way to handle on the UI
   */
//  public static final Set<DateTimeOperator> DATE_TIME_OPERATORS = Collections
//      .unmodifiableSet(EnumSet.of(YEAR, MONTH, WEEK, DAY, MAX, MIN, AVG));
  public static final Set<DateTimeOperator> DATE_TIME_OPERATORS = Collections
      .unmodifiableSet(EnumSet.of(YEAR, MONTH, WEEK, DAY));

}
