package com.axonivy.portal.enums.statistic;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import com.axonivy.portal.enums.HasCmsName;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AggregationInterval implements HasCmsName {
  DAY("Day"),
  WEEK("Week"),
  MONTH("Month"),
  YEAR("Year");
  
  private String name;
  
  private AggregationInterval (String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
    return this.name;
  }
  
  public static final Set<AggregationInterval> DATE_TIME_INTERVALS = Collections
      .unmodifiableSet(EnumSet.of(DAY, WEEK, MONTH, YEAR));

}
