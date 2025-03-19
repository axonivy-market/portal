package com.axonivy.portal.enums.statistic;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ChartAggregates {
  BUSINESS_STATE("businessState"),
  STATE("state"),
  PRIORITY("priority"),
  CATEGORY("category"),
  IS_EXPIRED("isExpired"),
  WORKER_NAME("worker.name"),
  ACTIVATOR_NAME("activator.name"),
  CREATOR_NAME("creator.name"),
  BUSINESS_RUNTIME("businessRuntime"),
  WORKING_TIME("workingTime"),
  NUMBER_OF_RESUMES("numberOfResumes"),
  START_TIMESTAMP("startTimestamp"),
  MODIFIED_TIMESTAMP("modifiedTimestamp"),
  END_TIMESTAMP("endTimestamp"),
  EXPIRY_TIMESTAMP("expiryTimestamp"),
  CUSTOM_FIELD("customFields.typeOfCustomField.*");

  private ChartAggregates(String name) {
    this.name = name;
  }

  private String name;

  @JsonValue
  public String getName() {
    return name;
  }

  public static final Set<ChartAggregates> TASK_AGGREGATES = Collections.unmodifiableSet(EnumSet.of(BUSINESS_STATE,
      STATE, PRIORITY, CATEGORY, ACTIVATOR_NAME, START_TIMESTAMP, END_TIMESTAMP, EXPIRY_TIMESTAMP, CUSTOM_FIELD));

  public static final Set<ChartAggregates> CASE_AGGREGATES = Collections.unmodifiableSet(EnumSet.of(BUSINESS_STATE,
      STATE, PRIORITY, CATEGORY, CREATOR_NAME, START_TIMESTAMP, END_TIMESTAMP, EXPIRY_TIMESTAMP, CUSTOM_FIELD));

  public static final Set<ChartAggregates> TASK_NUMBER_AGGREGATES = Collections.unmodifiableSet(EnumSet
      .of(BUSINESS_STATE, STATE, PRIORITY, CATEGORY, BUSINESS_RUNTIME, WORKING_TIME, NUMBER_OF_RESUMES, CUSTOM_FIELD));

  public static final Set<ChartAggregates> CASE_NUMBER_AGGREGATES = Collections.unmodifiableSet(
      EnumSet.of(BUSINESS_STATE, STATE, PRIORITY, CATEGORY, BUSINESS_RUNTIME, WORKING_TIME, CUSTOM_FIELD));
}
