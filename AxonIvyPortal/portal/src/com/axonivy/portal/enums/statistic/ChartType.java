package com.axonivy.portal.enums.statistic;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ChartType {
  BAR("bar"), 
  LINE("line"),
  PIE("pie"), 
  NUMBER("number");
  
  private String key;

  private ChartType(String key) {
    this.key = key;
  }

  @JsonValue
  public String getKey() {
      return key;
  }
}
