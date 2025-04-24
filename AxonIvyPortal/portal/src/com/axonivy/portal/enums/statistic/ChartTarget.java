package com.axonivy.portal.enums.statistic;

import com.axonivy.portal.enums.HasCmsName;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ChartTarget implements HasCmsName {
  CASE("case"), TASK("task");

  private String name;

  private ChartTarget(String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
    return name;
  }
}
