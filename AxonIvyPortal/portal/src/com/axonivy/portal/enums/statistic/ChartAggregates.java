package com.axonivy.portal.enums.statistic;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ChartAggregates {
  BUSINESS_STATE("businessState"),
  STATE("state"),
  PRIORITY("priority"),
  CATEGORY("category"),
  IS_EXPIRED("isExpired"),
  WORKER_NAME("worker.name"),
  ACTIVATOR_NAME("activator.name"),
  ORIGINAL_ACTIVATOR_NAME("originalActivator.name"),
  BUSINESS_RUNTIME("businessRuntime"),
  WORKING_TIME("workingTime"),
  NUMBER_OF_RESUMES("numberOfResumes"),
  START_TIMESTAMP("startTimestamp"),
  MODIFIED_TIMESTAMP("modifiedTimestamp"),
  END_TIMESTAMP("endTimestamp"),
  EXPIRY_TIMESTAMP("expiryTimestamp"),
  CUSTOM_STRING_FIELD("customFields.strings.*"),
  CUSTOM_NUMBERS_FIELD("customFields.numbers.*"),
  CUSTOM_TIMESTAMP_FIELD("customFields.timestamps.*");

  private ChartAggregates(String name) {
    this.name = name;
  }

  private String name;

  @JsonValue
  public String getName() {
    return name;
  }
}
