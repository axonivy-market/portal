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
  HOUR("Hour");
  
  private DateTimeOperator (String name) {
    this.name = name;
  }
  
  private String name;
  
  @JsonValue
  public String getName() {
    return this.name;
  }
  
  public static final Set<DateTimeOperator> DATE_TIME_OPERATORS = Collections
      .unmodifiableSet(EnumSet.of(YEAR, MONTH, WEEK, DAY, HOUR));
}
