package com.axonivy.portal.enums.statistic;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ChartType {
  BAR("bar"), 
  LINE("line"),
  PIE("pie"), 
  NUMBER("number");
  
  private String name;

  private ChartType(String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
      return name;
  }
}
