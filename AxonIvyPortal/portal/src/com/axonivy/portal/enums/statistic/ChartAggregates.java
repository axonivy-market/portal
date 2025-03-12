package com.axonivy.portal.enums.statistic;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ChartAggregates {
  PRIORITY("priority"),
  BUSINESS_STATE("businessState"),
  CATEGORY("category"),
  CREATOR_NAME("creator.name"),
  START_TIMESTAMP("startTimestamp"),
  MODIFIED_TIMESTAMP("modifiedTimestamp"),
  END_TIMESTAMP("endTimestamp"),
  CUSTOM_STRING_FIELD("customFields.strings"),
  CUSTOM_TIMESTAMP_FIELD("customFields.timestamps");

  private ChartAggregates(String name) {
    this.name = name;
  }

  private String name;

  @JsonValue
  public String getName() {
    return name;
  }
}
