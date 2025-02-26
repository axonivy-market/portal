package com.axonivy.portal.enums.statistic;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ChartTarget {
  CASE("case"),
  TASK("task");
  
  private String name;

  private ChartTarget(String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
      return name;
  }
}
