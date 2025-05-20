package com.axonivy.portal.enums.statistic;

import com.axonivy.portal.enums.HasCmsName;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ChartType implements HasCmsName {
  BAR("bar"), LINE("line"), PIE("pie"), NUMBER("number");

  private String name;

  private ChartType(String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
    return name;
  }
}
